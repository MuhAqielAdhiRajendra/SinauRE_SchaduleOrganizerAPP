package androidx.core.app;

/* JADX INFO: loaded from: classes12.dex */
public class AppLocalesStorageHelper {
    static final String APPLICATION_LOCALES_RECORD_FILE = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file";
    static final boolean DEBUG = false;
    static final String LOCALE_RECORD_ATTRIBUTE_TAG = "application_locales";
    static final String LOCALE_RECORD_FILE_TAG = "locales";
    static final String TAG = "AppLocalesStorageHelper";
    private static final Object sAppLocaleStorageSync = new Object();

    private AppLocalesStorageHelper() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0044, code lost:
    
        r1 = r3.getAttributeValue(null, androidx.core.app.AppLocalesStorageHelper.LOCALE_RECORD_ATTRIBUTE_TAG);
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x006d: IF  (r2 I:??[int, boolean, OBJECT, ARRAY, byte, short, char] A[D('fis' java.io.FileInputStream)]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:46:0x0075 (LINE:94), block:B:41:0x006d */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0066 A[Catch: all -> 0x0079, TryCatch #2 {, blocks: (B:5:0x0005, B:23:0x0049, B:35:0x005f, B:39:0x006b, B:38:0x0066, B:42:0x006f, B:46:0x0075, B:48:0x0077, B:6:0x000c, B:7:0x0019, B:11:0x0024, B:17:0x0030, B:19:0x003d, B:32:0x0054), top: B:55:0x0005, inners: #3, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0049 A[EXC_TOP_SPLITTER, PHI: r1
  0x0049: PHI (r1v2 'appLocales' java.lang.String) = (r1v0 'appLocales' java.lang.String), (r1v4 'appLocales' java.lang.String) binds: [B:33:0x005c, B:22:0x0047] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readLocales(android.content.Context r9) {
        /*
            java.lang.Object r0 = androidx.core.app.AppLocalesStorageHelper.sAppLocaleStorageSync
            monitor-enter(r0)
            java.lang.String r1 = ""
            java.lang.String r2 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.io.FileInputStream r2 = r9.openFileInput(r2)     // Catch: java.io.FileNotFoundException -> L76 java.lang.Throwable -> L79
            org.xmlpull.v1.XmlPullParser r3 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 org.xmlpull.v1.XmlPullParserException -> L53
            java.lang.String r4 = "UTF-8"
            r3.setInput(r2, r4)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 org.xmlpull.v1.XmlPullParserException -> L53
            int r4 = r3.getDepth()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 org.xmlpull.v1.XmlPullParserException -> L53
        L19:
            int r5 = r3.next()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 org.xmlpull.v1.XmlPullParserException -> L53
            r6 = r5
            r7 = 1
            if (r5 == r7) goto L47
            r5 = 3
            if (r6 != r5) goto L2a
            int r7 = r3.getDepth()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 org.xmlpull.v1.XmlPullParserException -> L53
            if (r7 <= r4) goto L47
        L2a:
            if (r6 == r5) goto L19
            r5 = 4
            if (r6 != r5) goto L30
            goto L19
        L30:
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 org.xmlpull.v1.XmlPullParserException -> L53
            java.lang.String r7 = "locales"
            boolean r7 = r5.equals(r7)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 org.xmlpull.v1.XmlPullParserException -> L53
            if (r7 == 0) goto L46
            java.lang.String r7 = "application_locales"
            r8 = 0
            java.lang.String r7 = r3.getAttributeValue(r8, r7)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 org.xmlpull.v1.XmlPullParserException -> L53
            r1 = r7
            goto L47
        L46:
            goto L19
        L47:
            if (r2 == 0) goto L5f
        L49:
            r2.close()     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L79
        L4c:
            goto L5f
        L4d:
            r3 = move-exception
            goto L4c
        L4f:
            r3 = move-exception
            goto L6d
        L51:
            r3 = move-exception
            goto L54
        L53:
            r3 = move-exception
        L54:
            java.lang.String r4 = "AppLocalesStorageHelper"
            java.lang.String r5 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r4, r5)     // Catch: java.lang.Throwable -> L4f
            if (r2 == 0) goto L5f
            goto L49
        L5f:
            boolean r3 = r1.isEmpty()     // Catch: java.lang.Throwable -> L79
            if (r3 != 0) goto L66
            goto L6b
        L66:
            java.lang.String r3 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            r9.deleteFile(r3)     // Catch: java.lang.Throwable -> L79
        L6b:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L79
            return r1
        L6d:
            if (r2 == 0) goto L74
            r2.close()     // Catch: java.io.IOException -> L73 java.lang.Throwable -> L79
            goto L74
        L73:
            r4 = move-exception
        L74:
            throw r3     // Catch: java.lang.Throwable -> L79
        L76:
            r2 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L79
            return r1
        L79:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L79
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.AppLocalesStorageHelper.readLocales(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void persistLocales(android.content.Context r6, java.lang.String r7) {
        /*
            java.lang.Object r0 = androidx.core.app.AppLocalesStorageHelper.sAppLocaleStorageSync
            monitor-enter(r0)
            java.lang.String r1 = ""
            boolean r1 = r7.equals(r1)     // Catch: java.lang.Throwable -> L75
            if (r1 == 0) goto L12
            java.lang.String r1 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            r6.deleteFile(r1)     // Catch: java.lang.Throwable -> L75
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L75
            return
        L12:
            java.lang.String r1 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            r2 = 0
            java.io.FileOutputStream r1 = r6.openFileOutput(r1, r2)     // Catch: java.io.FileNotFoundException -> L61 java.lang.Throwable -> L75
            org.xmlpull.v1.XmlSerializer r2 = android.util.Xml.newSerializer()     // Catch: java.lang.Throwable -> L75
            r3 = 0
            r2.setOutput(r1, r3)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L45
            java.lang.String r4 = "UTF-8"
            r5 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L45
            r2.startDocument(r4, r5)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L45
            java.lang.String r4 = "locales"
            r2.startTag(r3, r4)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L45
            java.lang.String r4 = "application_locales"
            r2.attribute(r3, r4, r7)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L45
            java.lang.String r4 = "locales"
            r2.endTag(r3, r4)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L45
            r2.endDocument()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L45
            if (r1 == 0) goto L56
            goto L50
        L43:
            r3 = move-exception
            goto L58
        L45:
            r3 = move-exception
            java.lang.String r4 = "AppLocalesStorageHelper"
            java.lang.String r5 = "Storing App Locales : Failed to persist app-locales in storage "
            android.util.Log.w(r4, r5, r3)     // Catch: java.lang.Throwable -> L43
            if (r1 == 0) goto L56
        L50:
            r1.close()     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L75
        L53:
            goto L56
        L54:
            r3 = move-exception
            goto L53
        L56:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L75
            return
        L58:
            if (r1 == 0) goto L5f
            r1.close()     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L75
            goto L5f
        L5e:
            r4 = move-exception
        L5f:
            throw r3     // Catch: java.lang.Throwable -> L75
        L61:
            r1 = move-exception
            java.lang.String r2 = "AppLocalesStorageHelper"
            java.lang.String r3 = "Storing App Locales : FileNotFoundException: Cannot open file %s for writing "
            java.lang.String r4 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch: java.lang.Throwable -> L75
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch: java.lang.Throwable -> L75
            android.util.Log.w(r2, r3)     // Catch: java.lang.Throwable -> L75
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L75
            return
        L75:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L75
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.AppLocalesStorageHelper.persistLocales(android.content.Context, java.lang.String):void");
    }
}
