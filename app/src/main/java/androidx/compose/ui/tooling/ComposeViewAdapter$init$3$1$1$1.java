package androidx.compose.ui.tooling;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: ComposeViewAdapter.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
final /* synthetic */ class ComposeViewAdapter$init$3$1$1$1 extends FunctionReferenceImpl implements Function0<Unit> {
    ComposeViewAdapter$init$3$1$1$1(Object obj) {
        super(0, obj, ComposeViewAdapter.class, "requestLayout", "requestLayout()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((ComposeViewAdapter) this.receiver).requestLayout();
    }
}
