package androidx.compose.runtime.tooling;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ComposeStackTrace.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTrace;", "", "frames", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "hasSourceInformation", "", "<init>", "(Ljava/util/List;Z)V", "getFrames", "()Ljava/util/List;", "getHasSourceInformation", "()Z", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ComposeStackTrace {
    public static final int $stable = 8;
    private final List<ComposeStackTraceFrame> frames;
    private final boolean hasSourceInformation;

    public ComposeStackTrace(List<ComposeStackTraceFrame> list, boolean hasSourceInformation) {
        this.frames = list;
        this.hasSourceInformation = hasSourceInformation;
    }

    public final List<ComposeStackTraceFrame> getFrames() {
        return this.frames;
    }

    public final boolean getHasSourceInformation() {
        return this.hasSourceInformation;
    }
}
