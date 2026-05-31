package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.foundation.text.contextmenu.data.TextContextMenuData;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProvider;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProviderKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: TextContextMenuToolbarHandlerModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003Be\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001e\u0010\u0006\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0007\u0012\u001e\u0010\u000b\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0007\u0012\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u000e\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u0005J\b\u0010)\u001a\u00020\tH\u0016J\b\u0010*\u001a\u00020\tH\u0016J\u0006\u0010+\u001a\u00020\tJ\u0006\u0010,\u001a\u00020\tJ\u0017\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\rH\u0016¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\rH\u0016J\b\u00103\u001a\u00020!H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R4\u0010\u0006\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R4\u0010\u000b\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0016\"\u0004\b\u001d\u0010\u0018R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\u000e\u0010&\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuToolbarHandlerNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;", "requester", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "onShow", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "onHide", "computeContentBounds", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/geometry/Rect;", "<init>", "(Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getRequester", "()Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "setRequester", "(Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;)V", "getOnShow", "()Lkotlin/jvm/functions/Function1;", "setOnShow", "(Lkotlin/jvm/functions/Function1;)V", "Lkotlin/jvm/functions/Function1;", "getOnHide", "setOnHide", "getComputeContentBounds", "setComputeContentBounds", "textToolbarJob", "Lkotlinx/coroutines/Job;", "derivedData", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuData;", "getDerivedData", "()Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuData;", "derivedData$delegate", "Landroidx/compose/runtime/State;", "previousContentBounds", "update", "toolbarRequester", "onAttach", "onDetach", "show", "hide", "position", "Landroidx/compose/ui/geometry/Offset;", "destinationCoordinates", "position-tuRUvjQ", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "contentBounds", "data", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextContextMenuToolbarHandlerNode extends DelegatingNode implements CompositionLocalConsumerModifierNode, TextContextMenuDataProvider {
    public static final int $stable = 8;
    private Function1<? super LayoutCoordinates, Rect> computeContentBounds;
    private Function1<? super Continuation<? super Unit>, ? extends Object> onHide;
    private Function1<? super Continuation<? super Unit>, ? extends Object> onShow;
    private ToolbarRequester requester;
    private Job textToolbarJob;

    /* JADX INFO: renamed from: derivedData$delegate, reason: from kotlin metadata */
    private final State derivedData = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return TextContextMenuToolbarHandlerNode.derivedData_delegate$lambda$0(this.f$0);
        }
    });
    private Rect previousContentBounds = Rect.INSTANCE.getZero();

    public TextContextMenuToolbarHandlerNode(ToolbarRequester requester, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Function1<? super Continuation<? super Unit>, ? extends Object> function12, Function1<? super LayoutCoordinates, Rect> function13) {
        this.requester = requester;
        this.onShow = function1;
        this.onHide = function12;
        this.computeContentBounds = function13;
    }

    public final ToolbarRequester getRequester() {
        return this.requester;
    }

    public final void setRequester(ToolbarRequester toolbarRequester) {
        this.requester = toolbarRequester;
    }

    public final Function1<Continuation<? super Unit>, Object> getOnShow() {
        return this.onShow;
    }

    public final void setOnShow(Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        this.onShow = function1;
    }

    public final Function1<Continuation<? super Unit>, Object> getOnHide() {
        return this.onHide;
    }

    public final void setOnHide(Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        this.onHide = function1;
    }

    public final Function1<LayoutCoordinates, Rect> getComputeContentBounds() {
        return this.computeContentBounds;
    }

    public final void setComputeContentBounds(Function1<? super LayoutCoordinates, Rect> function1) {
        this.computeContentBounds = function1;
    }

    private final TextContextMenuData getDerivedData() {
        State $this$getValue$iv = this.derivedData;
        return (TextContextMenuData) $this$getValue$iv.getValue();
    }

    static final TextContextMenuData derivedData_delegate$lambda$0(TextContextMenuToolbarHandlerNode this$0) {
        return this$0.getIsAttached() ? TextContextMenuModifierKt.collectTextContextMenuData(this$0) : TextContextMenuData.INSTANCE.getEmpty();
    }

    public final void update(ToolbarRequester toolbarRequester) {
        ToolbarHandlerState toolbarHandlerState;
        this.requester.setToolbarHandlerNode$foundation(null);
        this.requester = toolbarRequester;
        this.requester.setToolbarHandlerNode$foundation(this);
        ToolbarRequester toolbarRequester2 = this.requester;
        if (getIsAttached()) {
            toolbarHandlerState = ToolbarHandlerState.Attached;
        } else {
            toolbarHandlerState = ToolbarHandlerState.Detached;
        }
        toolbarRequester2.setToolbarHandlerState$foundation(toolbarHandlerState);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        super.onAttach();
        this.requester.setToolbarHandlerState$foundation(ToolbarHandlerState.Attached);
        this.requester.setToolbarHandlerNode$foundation(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.requester.setToolbarHandlerState$foundation(ToolbarHandlerState.Detached);
        this.requester.setToolbarHandlerNode$foundation(null);
        super.onDetach();
    }

    public final void show() {
        TextContextMenuProvider provider;
        if (getIsAttached()) {
            Job job = this.textToolbarJob;
            boolean z = false;
            if (job != null && job.isActive()) {
                z = true;
            }
            if (z || (provider = (TextContextMenuProvider) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, TextContextMenuProviderKt.getLocalTextContextMenuToolbarProvider())) == null) {
                return;
            }
            this.textToolbarJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(provider, null), 1, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode$show$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextContextMenuToolbarHandlerModifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode$show$1", f = "TextContextMenuToolbarHandlerModifier.kt", i = {}, l = {205, ComposerKt.referenceKey, 208, 208}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TextContextMenuProvider $provider;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TextContextMenuProvider textContextMenuProvider, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$provider = textContextMenuProvider;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TextContextMenuToolbarHandlerNode.this.new AnonymousClass1(this.$provider, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0051 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:24:0x005a  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) throws java.lang.Throwable {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                switch(r1) {
                    case 0: goto L28;
                    case 1: goto L22;
                    case 2: goto L1e;
                    case 3: goto L1a;
                    case 4: goto L12;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L12:
                java.lang.Object r0 = r5.L$0
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                kotlin.ResultKt.throwOnFailure(r6)
                goto L7d
            L1a:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L64
            L1e:
                kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L26
                goto L52
            L22:
                kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L26
                goto L3e
            L26:
                r1 = move-exception
                goto L68
            L28:
                kotlin.ResultKt.throwOnFailure(r6)
                androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode r1 = androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode.this     // Catch: java.lang.Throwable -> L26
                kotlin.jvm.functions.Function1 r1 = r1.getOnShow()     // Catch: java.lang.Throwable -> L26
                if (r1 == 0) goto L3e
                r2 = 1
                r5.label = r2     // Catch: java.lang.Throwable -> L26
                java.lang.Object r1 = r1.invoke(r5)     // Catch: java.lang.Throwable -> L26
                if (r1 != r0) goto L3e
                return r0
            L3e:
                androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProvider r1 = r5.$provider     // Catch: java.lang.Throwable -> L26
                androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode r2 = androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode.this     // Catch: java.lang.Throwable -> L26
                androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider r2 = (androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider) r2     // Catch: java.lang.Throwable -> L26
                r3 = r5
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.lang.Throwable -> L26
                r4 = 2
                r5.label = r4     // Catch: java.lang.Throwable -> L26
                java.lang.Object r1 = r1.showTextContextMenu(r2, r3)     // Catch: java.lang.Throwable -> L26
                if (r1 != r0) goto L52
                return r0
            L52:
                androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode r1 = androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode.this
                kotlin.jvm.functions.Function1 r1 = r1.getOnHide()
                if (r1 == 0) goto L64
                r2 = 3
                r5.label = r2
                java.lang.Object r1 = r1.invoke(r5)
                if (r1 != r0) goto L64
                return r0
            L64:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            L68:
                androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode r2 = androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode.this
                kotlin.jvm.functions.Function1 r2 = r2.getOnHide()
                if (r2 == 0) goto L7e
                r5.L$0 = r1
                r3 = 4
                r5.label = r3
                java.lang.Object r2 = r2.invoke(r5)
                if (r2 != r0) goto L7c
                return r0
            L7c:
                r0 = r1
            L7d:
                r1 = r0
            L7e:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void hide() {
        Job job = this.textToolbarJob;
        if (job == null) {
            return;
        }
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        this.textToolbarJob = null;
    }

    @Override // androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider
    /* JADX INFO: renamed from: position-tuRUvjQ */
    public long mo1705positiontuRUvjQ(LayoutCoordinates destinationCoordinates) {
        return contentBounds(destinationCoordinates).m5103getTopLeftF1C5BW0();
    }

    @Override // androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider
    public Rect contentBounds(LayoutCoordinates destinationCoordinates) {
        Rect computedContentBounds;
        if (getIsAttached() && (computedContentBounds = this.computeContentBounds.invoke(destinationCoordinates)) != null) {
            this.previousContentBounds = computedContentBounds;
            return computedContentBounds;
        }
        return this.previousContentBounds;
    }

    @Override // androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider
    public TextContextMenuData data() {
        return getDerivedData();
    }
}
