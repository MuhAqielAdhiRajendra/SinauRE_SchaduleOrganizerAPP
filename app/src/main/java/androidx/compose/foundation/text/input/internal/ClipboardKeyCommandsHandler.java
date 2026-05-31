package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.KeyCommand;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldKeyEventHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0088\u0001\u0002¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/text/input/internal/ClipboardKeyCommandsHandler;", "", "handler", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/KeyCommand;", "", "constructor-impl", "(Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function1;", "getHandler", "()Lkotlin/jvm/functions/Function1;", "equals", "", "other", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class ClipboardKeyCommandsHandler {
    private final Function1<KeyCommand, Unit> handler;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ClipboardKeyCommandsHandler m1752boximpl(Function1 function1) {
        return new ClipboardKeyCommandsHandler(function1);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static Function1<? super KeyCommand, ? extends Unit> m1753constructorimpl(Function1<? super KeyCommand, Unit> function1) {
        return function1;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1754equalsimpl(Function1<? super KeyCommand, ? extends Unit> function1, Object obj) {
        return (obj instanceof ClipboardKeyCommandsHandler) && Intrinsics.areEqual(function1, ((ClipboardKeyCommandsHandler) obj).getHandler());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1755equalsimpl0(Function1<? super KeyCommand, ? extends Unit> function1, Function1<? super KeyCommand, ? extends Unit> function12) {
        return Intrinsics.areEqual(function1, function12);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1756hashCodeimpl(Function1<? super KeyCommand, ? extends Unit> function1) {
        return function1.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1757toStringimpl(Function1<? super KeyCommand, ? extends Unit> function1) {
        return "ClipboardKeyCommandsHandler(handler=" + function1 + ')';
    }

    public boolean equals(Object other) {
        return m1754equalsimpl(this.handler, other);
    }

    public int hashCode() {
        return m1756hashCodeimpl(this.handler);
    }

    public String toString() {
        return m1757toStringimpl(this.handler);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Function1 getHandler() {
        return this.handler;
    }

    private /* synthetic */ ClipboardKeyCommandsHandler(Function1 handler) {
        this.handler = handler;
    }

    public final Function1<KeyCommand, Unit> getHandler() {
        return this.handler;
    }
}
