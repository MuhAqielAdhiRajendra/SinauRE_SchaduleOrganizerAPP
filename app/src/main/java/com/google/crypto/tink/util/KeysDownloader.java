package com.google.crypto.tink.util;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.Instant;

/* JADX INFO: loaded from: classes13.dex */
public class KeysDownloader {
    private final Executor backgroundExecutor;
    private long cacheExpirationDurationInMillis;
    private String cachedData;
    private long cachedTimeInMillis;
    private final Object fetchDataLock;
    private final HttpTransport httpTransport;
    private final Object instanceStateLock;
    private Runnable pendingRefreshRunnable;
    private final String url;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final NetHttpTransport DEFAULT_HTTP_TRANSPORT = new NetHttpTransport.Builder().build();
    private static final Executor DEFAULT_BACKGROUND_EXECUTOR = Executors.newCachedThreadPool();
    private static final Pattern MAX_AGE_PATTERN = Pattern.compile("\\s*max-age\\s*=\\s*(\\d+)\\s*");

    public KeysDownloader(Executor backgroundExecutor, HttpTransport httpTransport, String url) {
        validate(url);
        this.backgroundExecutor = backgroundExecutor;
        this.httpTransport = httpTransport;
        this.instanceStateLock = new Object();
        this.fetchDataLock = new Object();
        this.url = url;
        this.cachedTimeInMillis = Long.MIN_VALUE;
        this.cacheExpirationDurationInMillis = 0L;
    }

    public String download() throws IOException {
        synchronized (this.instanceStateLock) {
            if (hasNonExpiredDataCached()) {
                if (shouldProactivelyRefreshDataInBackground()) {
                    refreshInBackground();
                }
                return this.cachedData;
            }
            synchronized (this.fetchDataLock) {
                synchronized (this.instanceStateLock) {
                    if (hasNonExpiredDataCached()) {
                        return this.cachedData;
                    }
                    return fetchAndCacheData();
                }
            }
        }
    }

    public HttpTransport getHttpTransport() {
        return this.httpTransport;
    }

    public String getUrl() {
        return this.url;
    }

    private boolean hasNonExpiredDataCached() {
        long currentTimeInMillis = getCurrentTimeInMillis();
        boolean cachedInFuture = this.cachedTimeInMillis > currentTimeInMillis;
        boolean cacheExpired = this.cachedTimeInMillis + this.cacheExpirationDurationInMillis <= currentTimeInMillis;
        return (cacheExpired || cachedInFuture) ? false : true;
    }

    private boolean shouldProactivelyRefreshDataInBackground() {
        return this.cachedTimeInMillis + (this.cacheExpirationDurationInMillis / 2) <= getCurrentTimeInMillis();
    }

    long getCurrentTimeInMillis() {
        return Instant.now().getMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String fetchAndCacheData() throws IOException {
        long currentTimeInMillis = getCurrentTimeInMillis();
        HttpRequest httpRequest = this.httpTransport.createRequestFactory().buildGetRequest(new GenericUrl(this.url));
        HttpResponse httpResponse = httpRequest.execute();
        if (httpResponse.getStatusCode() != 200) {
            throw new IOException("Unexpected status code = " + httpResponse.getStatusCode());
        }
        InputStream contentStream = httpResponse.getContent();
        try {
            InputStreamReader reader = new InputStreamReader(contentStream, UTF_8);
            String data = readerToString(reader);
            contentStream.close();
            synchronized (this.instanceStateLock) {
                this.cachedTimeInMillis = currentTimeInMillis;
                this.cacheExpirationDurationInMillis = getExpirationDurationInSeconds(httpResponse.getHeaders()) * 1000;
                this.cachedData = data;
            }
            return data;
        } catch (Throwable th) {
            contentStream.close();
            throw th;
        }
    }

    private static String readerToString(Reader reader) throws IOException {
        Reader reader2 = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int c = reader2.read();
            if (c != -1) {
                stringBuilder.append((char) c);
            } else {
                return stringBuilder.toString();
            }
        }
    }

    long getExpirationDurationInSeconds(HttpHeaders httpHeaders) {
        long expirationDurationInSeconds = 0;
        if (httpHeaders.getCacheControl() != null) {
            String[] strArrSplit = httpHeaders.getCacheControl().split(",");
            int length = strArrSplit.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String arg = strArrSplit[i];
                Matcher m = MAX_AGE_PATTERN.matcher(arg);
                if (!m.matches()) {
                    i++;
                } else {
                    expirationDurationInSeconds = Long.valueOf(m.group(1)).longValue();
                    break;
                }
            }
        }
        if (httpHeaders.getAge() != null) {
            expirationDurationInSeconds -= httpHeaders.getAge().longValue();
        }
        return Math.max(0L, expirationDurationInSeconds);
    }

    public void refreshInBackground() {
        Runnable refreshRunnable = newRefreshRunnable();
        synchronized (this.instanceStateLock) {
            if (this.pendingRefreshRunnable != null) {
                return;
            }
            this.pendingRefreshRunnable = refreshRunnable;
            try {
                this.backgroundExecutor.execute(refreshRunnable);
            } catch (Throwable e) {
                synchronized (this.instanceStateLock) {
                    if (this.pendingRefreshRunnable == refreshRunnable) {
                        this.pendingRefreshRunnable = null;
                    }
                    throw e;
                }
            }
        }
    }

    private Runnable newRefreshRunnable() {
        return new Runnable() { // from class: com.google.crypto.tink.util.KeysDownloader.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (KeysDownloader.this.fetchDataLock) {
                    try {
                        KeysDownloader.this.fetchAndCacheData();
                        synchronized (KeysDownloader.this.instanceStateLock) {
                            if (KeysDownloader.this.pendingRefreshRunnable == this) {
                                KeysDownloader.this.pendingRefreshRunnable = null;
                            }
                        }
                    } catch (IOException e) {
                        synchronized (KeysDownloader.this.instanceStateLock) {
                            if (KeysDownloader.this.pendingRefreshRunnable == this) {
                                KeysDownloader.this.pendingRefreshRunnable = null;
                            }
                        }
                    } catch (Throwable th) {
                        synchronized (KeysDownloader.this.instanceStateLock) {
                            try {
                                if (KeysDownloader.this.pendingRefreshRunnable == this) {
                                    KeysDownloader.this.pendingRefreshRunnable = null;
                                }
                                throw th;
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    }
                }
            }
        };
    }

    private static void validate(String url) {
        try {
            URL tmp = new URL(url);
            if (!tmp.getProtocol().toLowerCase(Locale.US).equals("https")) {
                throw new IllegalArgumentException("url must point to a HTTPS server");
            }
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static class Builder {
        private String url;
        private HttpTransport httpTransport = KeysDownloader.DEFAULT_HTTP_TRANSPORT;
        private Executor executor = KeysDownloader.DEFAULT_BACKGROUND_EXECUTOR;

        public Builder setUrl(String val) {
            this.url = val;
            return this;
        }

        public Builder setExecutor(Executor val) {
            this.executor = val;
            return this;
        }

        public Builder setHttpTransport(HttpTransport httpTransport) {
            this.httpTransport = httpTransport;
            return this;
        }

        public KeysDownloader build() {
            if (this.url == null) {
                throw new IllegalArgumentException("must provide a url with {#setUrl}");
            }
            return new KeysDownloader(this.executor, this.httpTransport, this.url);
        }
    }
}
