package com.example.scheduleorganizer.ui.screen;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: ChatScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/example/scheduleorganizer/ui/screen/ChatAssistant;", "", "<init>", "()V", "greetings", "", "", "gratitudeKeywords", "support", "conversationKeywords", "scheduleKeywords", "focusKeywords", "restKeywords", "effortKeywords", "moodKeywords", "questionWords", "fallbackResponses", "getResponse", "message", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ChatAssistant {
    public static final ChatAssistant INSTANCE = new ChatAssistant();
    private static final List<String> greetings = CollectionsKt.listOf((Object[]) new String[]{"halo", "hai", "selamat", "pagi", "siang", "sore", "malam"});
    private static final List<String> gratitudeKeywords = CollectionsKt.listOf((Object[]) new String[]{"terima kasih", "makasih", "sip", "oke", "ok", "thanks"});
    private static final List<String> support = CollectionsKt.listOf((Object[]) new String[]{"konsultasi", "curhat", "stress", "motivas", "galau", "bingung", "emosi"});
    private static final List<String> conversationKeywords = CollectionsKt.listOf((Object[]) new String[]{"ngobrol", "cerita", "ngomong", "sambil", "cek"});
    private static final List<String> scheduleKeywords = CollectionsKt.listOf((Object[]) new String[]{"jadwal", "waktu", "atur", "planning", "rencana", "agenda", "istirahat", "kerja", "meeting"});
    private static final List<String> focusKeywords = CollectionsKt.listOf((Object[]) new String[]{"fokus", "belajar", "tugas", "kerja", "rapat", "produktif", "konsentrasi", "disiplin"});
    private static final List<String> restKeywords = CollectionsKt.listOf((Object[]) new String[]{"istirahat", "santai", "break", "lelah", "refresh", "tidur"});
    private static final List<String> effortKeywords = CollectionsKt.listOf((Object[]) new String[]{"deadline", "sesuai", "tepat", "cepat", "terlambat", TypedValues.AttributesType.S_TARGET, "pengerjaan"});
    private static final List<String> moodKeywords = CollectionsKt.listOf((Object[]) new String[]{"senang", "sedih", "cemas", "khawatir", "termotivasi", "stres", "bosan"});
    private static final List<String> questionWords = CollectionsKt.listOf((Object[]) new String[]{"apa", "bagaimana", "kenapa", "mengapa", "kapan", "boleh", "bisa"});
    private static final List<String> fallbackResponses = CollectionsKt.listOf((Object[]) new String[]{"Coba ceritakan lebih rinci: apakah kamu ingin bantuan mengatur jadwal, fokus, atau pengingat?", "Saya bisa bantu atur jam belajar, rekomendasi rutinitas, atau tips fokus. Apa yang ingin kamu tanyakan?", "Kalau kamu sedang bingung, mulai dengan tujuan utama hari ini. Saya bantu susun langkahnya."});
    public static final int $stable = 8;

    private ChatAssistant() {
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x0251  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getResponse(java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 722
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.ChatAssistant.getResponse(java.lang.String):java.lang.String");
    }
}
