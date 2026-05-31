package androidx.security.crypto;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/* JADX INFO: loaded from: classes13.dex */
@Deprecated
public final class MasterKey {

    @Deprecated
    public static final int DEFAULT_AES_GCM_MASTER_KEY_SIZE = 256;
    private static final int DEFAULT_AUTHENTICATION_VALIDITY_DURATION_SECONDS = 300;

    @Deprecated
    public static final String DEFAULT_MASTER_KEY_ALIAS = "_androidx_security_master_key_";
    static final String KEYSTORE_PATH_URI = "android-keystore://";
    private final String mKeyAlias;
    private final KeyGenParameterSpec mKeyGenParameterSpec;

    @Deprecated
    public enum KeyScheme {
        AES256_GCM
    }

    public static int getDefaultAuthenticationValidityDurationSeconds() {
        return 300;
    }

    MasterKey(String keyAlias, Object keyGenParameterSpec) {
        this.mKeyAlias = keyAlias;
        this.mKeyGenParameterSpec = (KeyGenParameterSpec) keyGenParameterSpec;
    }

    public boolean isKeyStoreBacked() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.containsAlias(this.mKeyAlias);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            return false;
        }
    }

    public boolean isUserAuthenticationRequired() {
        return this.mKeyGenParameterSpec != null && Api23Impl.isUserAuthenticationRequired(this.mKeyGenParameterSpec);
    }

    public int getUserAuthenticationValidityDurationSeconds() {
        if (this.mKeyGenParameterSpec == null) {
            return 0;
        }
        return Api23Impl.getUserAuthenticationValidityDurationSeconds(this.mKeyGenParameterSpec);
    }

    public boolean isStrongBoxBacked() {
        if (Build.VERSION.SDK_INT < 28 || this.mKeyGenParameterSpec == null) {
            return false;
        }
        return Api28Impl.isStrongBoxBacked(this.mKeyGenParameterSpec);
    }

    public String toString() {
        return "MasterKey{keyAlias=" + this.mKeyAlias + ", isKeyStoreBacked=" + isKeyStoreBacked() + "}";
    }

    String getKeyAlias() {
        return this.mKeyAlias;
    }

    @Deprecated
    public static final class Builder {
        boolean mAuthenticationRequired;
        final Context mContext;
        final String mKeyAlias;
        KeyGenParameterSpec mKeyGenParameterSpec;
        KeyScheme mKeyScheme;
        boolean mRequestStrongBoxBacked;
        int mUserAuthenticationValidityDurationSeconds;

        public Builder(Context context) {
            this(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS);
        }

        public Builder(Context context, String keyAlias) {
            this.mContext = context.getApplicationContext();
            this.mKeyAlias = keyAlias;
        }

        public Builder setKeyScheme(KeyScheme keyScheme) {
            switch (keyScheme) {
                case AES256_GCM:
                    if (this.mKeyGenParameterSpec != null) {
                        throw new IllegalArgumentException("KeyScheme set after setting a KeyGenParamSpec");
                    }
                    this.mKeyScheme = keyScheme;
                    return this;
                default:
                    throw new IllegalArgumentException("Unsupported scheme: " + keyScheme);
            }
        }

        public Builder setUserAuthenticationRequired(boolean authenticationRequired) {
            return setUserAuthenticationRequired(authenticationRequired, MasterKey.getDefaultAuthenticationValidityDurationSeconds());
        }

        public Builder setUserAuthenticationRequired(boolean authenticationRequired, int userAuthenticationValidityDurationSeconds) {
            this.mAuthenticationRequired = authenticationRequired;
            this.mUserAuthenticationValidityDurationSeconds = userAuthenticationValidityDurationSeconds;
            return this;
        }

        public Builder setRequestStrongBoxBacked(boolean requestStrongBoxBacked) {
            this.mRequestStrongBoxBacked = requestStrongBoxBacked;
            return this;
        }

        public Builder setKeyGenParameterSpec(KeyGenParameterSpec keyGenParameterSpec) {
            if (this.mKeyScheme != null) {
                throw new IllegalArgumentException("KeyGenParamSpec set after setting a KeyScheme");
            }
            if (!this.mKeyAlias.equals(Api23Impl.getKeystoreAlias(keyGenParameterSpec))) {
                throw new IllegalArgumentException("KeyGenParamSpec's key alias does not match provided alias (" + this.mKeyAlias + " vs " + Api23Impl.getKeystoreAlias(keyGenParameterSpec));
            }
            this.mKeyGenParameterSpec = keyGenParameterSpec;
            return this;
        }

        public MasterKey build() throws GeneralSecurityException, IOException {
            return Api23Impl.build(this);
        }

        static class Api23Impl {
            private Api23Impl() {
            }

            static String getKeystoreAlias(KeyGenParameterSpec keyGenParameterSpec) {
                return keyGenParameterSpec.getKeystoreAlias();
            }

            static MasterKey build(Builder builder) throws GeneralSecurityException, IOException {
                if (builder.mKeyScheme == null && builder.mKeyGenParameterSpec == null) {
                    throw new IllegalArgumentException("build() called before setKeyGenParameterSpec or setKeyScheme.");
                }
                if (builder.mKeyScheme == KeyScheme.AES256_GCM) {
                    KeyGenParameterSpec.Builder keyGenBuilder = new KeyGenParameterSpec.Builder(builder.mKeyAlias, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256);
                    if (builder.mAuthenticationRequired) {
                        keyGenBuilder.setUserAuthenticationRequired(true);
                        if (Build.VERSION.SDK_INT >= 30) {
                            Api30Impl.setUserAuthenticationParameters(keyGenBuilder, builder.mUserAuthenticationValidityDurationSeconds, 3);
                        } else {
                            keyGenBuilder.setUserAuthenticationValidityDurationSeconds(builder.mUserAuthenticationValidityDurationSeconds);
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 28 && builder.mRequestStrongBoxBacked && builder.mContext.getPackageManager().hasSystemFeature("android.hardware.strongbox_keystore")) {
                        Api28Impl.setIsStrongBoxBacked(keyGenBuilder);
                    }
                    builder.mKeyGenParameterSpec = keyGenBuilder.build();
                }
                if (builder.mKeyGenParameterSpec == null) {
                    throw new NullPointerException("KeyGenParameterSpec was null after build() check");
                }
                String keyAlias = MasterKeys.getOrCreate(builder.mKeyGenParameterSpec);
                return new MasterKey(keyAlias, builder.mKeyGenParameterSpec);
            }

            static class Api28Impl {
                private Api28Impl() {
                }

                static void setIsStrongBoxBacked(KeyGenParameterSpec.Builder builder) {
                    builder.setIsStrongBoxBacked(true);
                }
            }

            static class Api30Impl {
                private Api30Impl() {
                }

                static void setUserAuthenticationParameters(KeyGenParameterSpec.Builder builder, int timeout, int type) {
                    builder.setUserAuthenticationParameters(timeout, type);
                }
            }
        }
    }

    static class Api23Impl {
        private Api23Impl() {
        }

        static boolean isUserAuthenticationRequired(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.isUserAuthenticationRequired();
        }

        static int getUserAuthenticationValidityDurationSeconds(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.getUserAuthenticationValidityDurationSeconds();
        }
    }

    static class Api28Impl {
        private Api28Impl() {
        }

        static boolean isStrongBoxBacked(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.isStrongBoxBacked();
        }
    }
}
