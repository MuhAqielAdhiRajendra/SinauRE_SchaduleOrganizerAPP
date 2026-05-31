package androidx.compose.runtime.tooling;

import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.GapComposerKt;
import androidx.compose.runtime.MovableContentKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ComposeStackTrace.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0000\u001a\u001c\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\b*\u00060\tj\u0002`\n2\u0006\u0010\u0003\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u000b\u001a\u00020\b*\u00060\tj\u0002`\n2\u0006\u0010\u0003\u001a\u00020\u0005H\u0000\u001a\u0012\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u0005H\u0000\u001a\u0018\u0010\u000f\u001a\u00020\b*\u00060\tj\u0002`\n2\u0006\u0010\u0003\u001a\u00020\u0005H\u0000\"\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"tryAttachComposeStackTrace", "", "", "trace", "Lkotlin/Function0;", "Landroidx/compose/runtime/tooling/ComposeStackTrace;", "attachComposeStackTrace", "appendStackTrace", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "appendSourceInformationStackTrace", "filterInternalFramesByGroupKey", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "appendGroupKeyStackTrace", "RuntimePackageHash", "", "IncludeDebugInfo", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ComposeStackTraceKt {
    private static final boolean IncludeDebugInfo = false;
    private static final String RuntimePackageHash = "9igjgp";

    public static final boolean tryAttachComposeStackTrace(Throwable $this$tryAttachComposeStackTrace, Function0<ComposeStackTrace> function0) {
        boolean z;
        boolean z2;
        Throwable traceException;
        Throwable diagnosticComposeException;
        boolean z3;
        boolean result = false;
        List<Throwable> suppressedExceptions = ExceptionsKt.getSuppressedExceptions($this$tryAttachComposeStackTrace);
        int index$iv$iv = 0;
        int size = suppressedExceptions.size();
        while (true) {
            z = false;
            if (index$iv$iv >= size) {
                z2 = true;
                break;
            }
            Object item$iv$iv = suppressedExceptions.get(index$iv$iv);
            Throwable it = (Throwable) item$iv$iv;
            if (it instanceof DiagnosticComposeException) {
                z2 = false;
                break;
            }
            index$iv$iv++;
        }
        if (z2) {
            try {
                ComposeStackTrace stackTrace = function0.invoke();
                if (stackTrace != null) {
                    if (stackTrace.getHasSourceInformation()) {
                        List<ComposeStackTraceFrame> frames = stackTrace.getFrames();
                        int index$iv$iv2 = 0;
                        int size2 = frames.size();
                        while (true) {
                            if (index$iv$iv2 >= size2) {
                                z3 = false;
                                break;
                            }
                            Object item$iv$iv2 = frames.get(index$iv$iv2);
                            ComposeStackTraceFrame it2 = (ComposeStackTraceFrame) item$iv$iv2;
                            if (it2.getSourceInfo() != null) {
                                z3 = true;
                                break;
                            }
                            index$iv$iv2++;
                        }
                    } else {
                        z3 = !stackTrace.getFrames().isEmpty();
                    }
                    if (z3) {
                        z = true;
                    }
                }
                result = z;
                if (result) {
                    Intrinsics.checkNotNull(stackTrace);
                    diagnosticComposeException = new DiagnosticComposeException(stackTrace);
                } else {
                    diagnosticComposeException = null;
                }
                traceException = diagnosticComposeException;
            } catch (Throwable th) {
                traceException = th;
            }
            if (traceException != null) {
                ExceptionsKt.addSuppressed($this$tryAttachComposeStackTrace, traceException);
            }
        }
        return result;
    }

    public static final Throwable attachComposeStackTrace(Throwable $this$attachComposeStackTrace, Function0<ComposeStackTrace> function0) {
        tryAttachComposeStackTrace($this$attachComposeStackTrace, function0);
        return $this$attachComposeStackTrace;
    }

    public static final void appendStackTrace(StringBuilder $this$appendStackTrace, ComposeStackTrace trace) {
        if (trace.getHasSourceInformation()) {
            appendSourceInformationStackTrace($this$appendStackTrace, trace);
        } else {
            appendGroupKeyStackTrace($this$appendStackTrace, trace);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void appendSourceInformationStackTrace(java.lang.StringBuilder r22, androidx.compose.runtime.tooling.ComposeStackTrace r23) {
        /*
            Method dump skipped, instruction units count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.tooling.ComposeStackTraceKt.appendSourceInformationStackTrace(java.lang.StringBuilder, androidx.compose.runtime.tooling.ComposeStackTrace):void");
    }

    public static final List<ComposeStackTraceFrame> filterInternalFramesByGroupKey(ComposeStackTrace $this$filterInternalFramesByGroupKey) {
        int[] knownKeys = {ComposerKt.providerKey, ComposerKt.compositionLocalMapKey, ComposerKt.providerMapsKey, ComposerKt.referenceKey, ComposerKt.reuseKey, GapComposerKt.nodeKey, ComposerKt.defaultsKey, MovableContentKt.movableContentKey, 200};
        int i = 0;
        int fCount = $this$filterInternalFramesByGroupKey.getFrames().size();
        List filteredFrames = new ArrayList();
        while (i < fCount) {
            int i2 = i + 1;
            ComposeStackTraceFrame frame = $this$filterInternalFramesByGroupKey.getFrames().get(i);
            if (!ArraysKt.contains(knownKeys, frame.getGroupKey())) {
                if (frame.getGroupKey() != 100) {
                    filteredFrames.add(frame);
                } else {
                    if (i2 + 1 < fCount && $this$filterInternalFramesByGroupKey.getFrames().get(i2 + 1).getGroupKey() == 1000) {
                        break;
                    }
                    CollectionsKt.removeLastOrNull(filteredFrames);
                }
            }
            i = i2;
        }
        return filteredFrames;
    }

    public static final void appendGroupKeyStackTrace(StringBuilder $this$appendGroupKeyStackTrace, ComposeStackTrace trace) {
        List<ComposeStackTraceFrame> listFilterInternalFramesByGroupKey = filterInternalFramesByGroupKey(trace);
        int size = listFilterInternalFramesByGroupKey.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = listFilterInternalFramesByGroupKey.get(index$iv);
            ComposeStackTraceFrame it = (ComposeStackTraceFrame) item$iv;
            $this$appendGroupKeyStackTrace.append("\tat $$compose.m$");
            $this$appendGroupKeyStackTrace.append(it.getGroupKey());
            $this$appendGroupKeyStackTrace.append("(SourceFile:1)");
            $this$appendGroupKeyStackTrace.append('\n');
        }
    }
}
