package com.example.scheduleorganizer;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.BarChartKt;
import androidx.compose.material.icons.filled.CalendarTodayKt;
import androidx.compose.material.icons.filled.ChatBubbleKt;
import androidx.compose.material.icons.filled.CheckCircleKt;
import androidx.compose.material.icons.filled.HomeKt;
import androidx.compose.material.icons.filled.InfoKt;
import androidx.compose.material.icons.filled.NoteAltKt;
import androidx.compose.material.icons.filled.PersonKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\b\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015B!\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0001\b\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d¨\u0006\u001e"}, d2 = {"Lcom/example/scheduleorganizer/Screen;", "", "route", "", "label", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;)V", "getRoute", "()Ljava/lang/String;", "getLabel", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "Home", "Jadwal", "Tugas", "Stats", "Notes", "Tour", "Profil", "Chat", "Lcom/example/scheduleorganizer/Screen$Chat;", "Lcom/example/scheduleorganizer/Screen$Home;", "Lcom/example/scheduleorganizer/Screen$Jadwal;", "Lcom/example/scheduleorganizer/Screen$Notes;", "Lcom/example/scheduleorganizer/Screen$Profil;", "Lcom/example/scheduleorganizer/Screen$Stats;", "Lcom/example/scheduleorganizer/Screen$Tour;", "Lcom/example/scheduleorganizer/Screen$Tugas;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Screen {
    public static final int $stable = 0;
    private final ImageVector icon;
    private final String label;
    private final String route;

    public /* synthetic */ Screen(String str, String str2, ImageVector imageVector, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, imageVector);
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/scheduleorganizer/Screen$Home;", "Lcom/example/scheduleorganizer/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Home extends Screen {
        public static final int $stable = 0;
        public static final Home INSTANCE = new Home();

        private Home() {
            super("home", "Home", HomeKt.getHome(Icons.INSTANCE.getDefault()), null);
        }
    }

    private Screen(String route, String label, ImageVector icon) {
        this.route = route;
        this.label = label;
        this.icon = icon;
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getRoute() {
        return this.route;
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/scheduleorganizer/Screen$Jadwal;", "Lcom/example/scheduleorganizer/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Jadwal extends Screen {
        public static final int $stable = 0;
        public static final Jadwal INSTANCE = new Jadwal();

        private Jadwal() {
            super("jadwal", "Jadwal", CalendarTodayKt.getCalendarToday(Icons.INSTANCE.getDefault()), null);
        }
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/scheduleorganizer/Screen$Tugas;", "Lcom/example/scheduleorganizer/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Tugas extends Screen {
        public static final int $stable = 0;
        public static final Tugas INSTANCE = new Tugas();

        private Tugas() {
            super("tugas", "Tugas", CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), null);
        }
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/scheduleorganizer/Screen$Stats;", "Lcom/example/scheduleorganizer/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Stats extends Screen {
        public static final int $stable = 0;
        public static final Stats INSTANCE = new Stats();

        private Stats() {
            super("stats", "Stats", BarChartKt.getBarChart(Icons.INSTANCE.getDefault()), null);
        }
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/scheduleorganizer/Screen$Notes;", "Lcom/example/scheduleorganizer/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Notes extends Screen {
        public static final int $stable = 0;
        public static final Notes INSTANCE = new Notes();

        private Notes() {
            super("notes", "Notes", NoteAltKt.getNoteAlt(Icons.INSTANCE.getDefault()), null);
        }
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/scheduleorganizer/Screen$Tour;", "Lcom/example/scheduleorganizer/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Tour extends Screen {
        public static final int $stable = 0;
        public static final Tour INSTANCE = new Tour();

        private Tour() {
            super("tour", "Panduan", InfoKt.getInfo(Icons.INSTANCE.getDefault()), null);
        }
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/scheduleorganizer/Screen$Profil;", "Lcom/example/scheduleorganizer/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Profil extends Screen {
        public static final int $stable = 0;
        public static final Profil INSTANCE = new Profil();

        private Profil() {
            super("profil", "Preferensi", PersonKt.getPerson(Icons.INSTANCE.getDefault()), null);
        }
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/scheduleorganizer/Screen$Chat;", "Lcom/example/scheduleorganizer/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Chat extends Screen {
        public static final int $stable = 0;
        public static final Chat INSTANCE = new Chat();

        private Chat() {
            super("chat", "Chat", ChatBubbleKt.getChatBubble(Icons.INSTANCE.getDefault()), null);
        }
    }
}
