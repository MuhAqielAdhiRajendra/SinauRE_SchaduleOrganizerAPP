## Catatan Keamanan - Enkripsi API Key (Aplikasi berjalan Ofline) 

Aplikasi ini menyimpan API key, provider, dan model AI menggunakan `EncryptedSharedPreferences` dengan skema:
- MasterKey: AES256_GCM (disimpan di Android Keystore)
- Enkripsi key: AES256_SIV
- Enkripsi value: AES256_GCM

Meskipun data terenkripsi di file `ai_settings.xml`, pada lingkungan rooted atau emulator, data dapat dibajak melalui hooking  pada fungsi `getApiKey()` saat runtime.
