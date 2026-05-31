package androidx.compose.foundation.text.selection;

import android.app.RemoteAction;
import android.content.Context;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope_androidKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: PlatformSelectionBehaviors.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\"\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0096@¢\u0006\u0004\b\"\u0010#J \u0010$\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0082@¢\u0006\u0004\b&\u0010#J*\u0010'\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001e2\b\u0010(\u001a\u0004\u0018\u00010)H\u0096@¢\u0006\u0004\b*\u0010+J \u0010,\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0096@¢\u0006\u0004\b-\u0010#J(\u0010.\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\u000fH\u0082@¢\u0006\u0004\b0\u00101J<\u00104\u001a\u00020%*\u0002052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001e2\u0017\u00106\u001a\u0013\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020%07¢\u0006\u0002\b8H\u0000¢\u0006\u0004\b9\u0010:J\u001f\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001e¢\u0006\u0004\b=\u0010>J?\u0010?\u001a\u0004\u0018\u0001H@\"\u0004\b\u0000\u0010@2'\u0010A\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H@0C\u0012\u0006\u0012\u0004\u0018\u0001030B¢\u0006\u0002\b8H\u0082@¢\u0006\u0002\u0010DR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviorsImpl;", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "context", "Landroid/content/Context;", "selectedTextType", "Landroidx/compose/foundation/text/selection/SelectedTextType;", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "<init>", "(Lkotlin/coroutines/CoroutineContext;Landroid/content/Context;Landroidx/compose/foundation/text/selection/SelectedTextType;Landroidx/compose/ui/text/intl/LocaleList;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "textClassificationSession", "Landroid/view/textclassifier/TextClassifier;", "<set-?>", "Landroidx/compose/foundation/text/selection/TextClassificationResult;", "textClassificationResult", "getTextClassificationResult", "()Landroidx/compose/foundation/text/selection/TextClassificationResult;", "setTextClassificationResult", "(Landroidx/compose/foundation/text/selection/TextClassificationResult;)V", "textClassificationResult$delegate", "Landroidx/compose/runtime/MutableState;", "androidLocalList", "Landroid/os/LocaleList;", "getAndroidLocalList", "()Landroid/os/LocaleList;", "suggestSelectionForLongPressOrDoubleClick", "Landroidx/compose/ui/text/TextRange;", "text", "", "selection", "suggestSelectionForLongPressOrDoubleClick-pYaCw-w", "(Ljava/lang/CharSequence;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onShowContextMenuOrSelectionToolbar", "", "onShowContextMenuOrSelectionToolbar-Sb-Bc2M", "onShowContextMenu", "secondaryClickLocation", "Landroidx/compose/ui/geometry/Offset;", "onShowContextMenu-_2OEclM", "(Ljava/lang/CharSequence;JLandroidx/compose/ui/geometry/Offset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onShowSelectionToolbar", "onShowSelectionToolbar-Sb-Bc2M", "classifyText", "textClassifier", "classifyText-M8tDOmk", "(Ljava/lang/CharSequence;JLandroid/view/textclassifier/TextClassifier;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AssistantItemKey", "", "addSmartSelectionTextContextMenuItems", "Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;", "child", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "addSmartSelectionTextContextMenuItems-YmzfRxQ$foundation", "(Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;Ljava/lang/CharSequence;JLkotlin/jvm/functions/Function1;)V", "tryGetTextClassification", "Landroid/view/textclassifier/TextClassification;", "tryGetTextClassification-FDrldGo", "(Ljava/lang/CharSequence;J)Landroid/view/textclassifier/TextClassification;", "requireTextClassificationSession", "T", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PlatformSelectionBehaviorsImpl implements PlatformSelectionBehaviors {
    public static final int $stable = 8;
    private final Context context;
    private final CoroutineContext coroutineContext;
    private final LocaleList localeList;
    private final SelectedTextType selectedTextType;
    private TextClassifier textClassificationSession;
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, null);

    /* JADX INFO: renamed from: textClassificationResult$delegate, reason: from kotlin metadata */
    private final MutableState textClassificationResult = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    private final Object AssistantItemKey = new Object();

    public PlatformSelectionBehaviorsImpl(CoroutineContext coroutineContext, Context context, SelectedTextType selectedTextType, LocaleList localeList) {
        this.coroutineContext = coroutineContext;
        this.context = context;
        this.selectedTextType = selectedTextType;
        this.localeList = localeList;
    }

    private final TextClassificationResult getTextClassificationResult() {
        State $this$getValue$iv = this.textClassificationResult;
        return (TextClassificationResult) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextClassificationResult(TextClassificationResult textClassificationResult) {
        MutableState $this$setValue$iv = this.textClassificationResult;
        $this$setValue$iv.setValue(textClassificationResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final android.os.LocaleList getAndroidLocalList() {
        android.os.LocaleList androidLocaleList;
        LocaleList it = this.localeList;
        return (it == null || (androidLocaleList = TextClassifierHelperMethods.INSTANCE.toAndroidLocaleList(it)) == null) ? new android.os.LocaleList(Locale.INSTANCE.getCurrent().getPlatformLocale()) : androidLocaleList;
    }

    @Override // androidx.compose.foundation.text.selection.PlatformSelectionBehaviors
    /* JADX INFO: renamed from: suggestSelectionForLongPressOrDoubleClick-pYaCw-w */
    public Object mo2035suggestSelectionForLongPressOrDoubleClickpYaCww(CharSequence text, long selection, Continuation<? super TextRange> continuation) {
        if (!(text.length() == 0) && !TextRange.m7567getCollapsedimpl(selection)) {
            return requireTextClassificationSession(new PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2(text, selection, this, null), continuation);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onShowContextMenuOrSelectionToolbar-Sb-Bc2M, reason: not valid java name */
    public final Object m2039onShowContextMenuOrSelectionToolbarSbBc2M(CharSequence text, long selection, Continuation<? super Unit> continuation) {
        if ((text.length() == 0) || TextRange.m7567getCollapsedimpl(selection)) {
            return Unit.INSTANCE;
        }
        return requireTextClassificationSession(new PlatformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2(this, text, selection, null), continuation);
    }

    @Override // androidx.compose.foundation.text.selection.PlatformSelectionBehaviors
    /* JADX INFO: renamed from: onShowContextMenu-_2OEclM */
    public Object mo2033onShowContextMenu_2OEclM(CharSequence text, long selection, Offset secondaryClickLocation, Continuation<? super Unit> continuation) {
        Object objM2039onShowContextMenuOrSelectionToolbarSbBc2M = m2039onShowContextMenuOrSelectionToolbarSbBc2M(text, selection, continuation);
        return objM2039onShowContextMenuOrSelectionToolbarSbBc2M == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM2039onShowContextMenuOrSelectionToolbarSbBc2M : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.text.selection.PlatformSelectionBehaviors
    /* JADX INFO: renamed from: onShowSelectionToolbar-Sb-Bc2M */
    public Object mo2034onShowSelectionToolbarSbBc2M(CharSequence text, long selection, Continuation<? super Unit> continuation) {
        Object objM2039onShowContextMenuOrSelectionToolbarSbBc2M = m2039onShowContextMenuOrSelectionToolbarSbBc2M(text, selection, continuation);
        return objM2039onShowContextMenuOrSelectionToolbarSbBc2M == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM2039onShowContextMenuOrSelectionToolbarSbBc2M : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a8 A[Catch: all -> 0x010f, TRY_LEAVE, TryCatch #1 {all -> 0x010f, blocks: (B:19:0x0095, B:21:0x009d, B:26:0x00a8, B:30:0x00b0), top: B:47:0x0095 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b0 A[Catch: all -> 0x010f, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x010f, blocks: (B:19:0x0095, B:21:0x009d, B:26:0x00a8, B:30:0x00b0), top: B:47:0x0095 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX INFO: renamed from: classifyText-M8tDOmk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m2038classifyTextM8tDOmk(java.lang.CharSequence r19, long r20, android.view.textclassifier.TextClassifier r22, kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            Method dump skipped, instruction units count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl.m2038classifyTextM8tDOmk(java.lang.CharSequence, long, android.view.textclassifier.TextClassifier, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: addSmartSelectionTextContextMenuItems-YmzfRxQ$foundation, reason: not valid java name */
    public final void m2040addSmartSelectionTextContextMenuItemsYmzfRxQ$foundation(TextContextMenuBuilderScope $this$addSmartSelectionTextContextMenuItems_u2dYmzfRxQ, CharSequence text, long selection, Function1<? super TextContextMenuBuilderScope, Unit> function1) {
        TextClassification textClassification = m2041tryGetTextClassificationFDrldGo(text, selection);
        if (textClassification == null) {
            function1.invoke($this$addSmartSelectionTextContextMenuItems_u2dYmzfRxQ);
            return;
        }
        if (!textClassification.getActions().isEmpty()) {
            TextContextMenuBuilderScope_androidKt.textClassificationItem($this$addSmartSelectionTextContextMenuItems_u2dYmzfRxQ, this.AssistantItemKey, textClassification, 0);
        } else if (TextClassifierHelperMethods.INSTANCE.hasLegacyAssistItem$foundation(textClassification)) {
            TextContextMenuBuilderScope_androidKt.textClassificationItem($this$addSmartSelectionTextContextMenuItems_u2dYmzfRxQ, this.AssistantItemKey, textClassification, -1);
        }
        function1.invoke($this$addSmartSelectionTextContextMenuItems_u2dYmzfRxQ);
        List<RemoteAction> actions = textClassification.getActions();
        int size = actions.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = actions.get(index$iv);
            int index = index$iv;
            if (index > 0) {
                TextContextMenuBuilderScope_androidKt.textClassificationItem($this$addSmartSelectionTextContextMenuItems_u2dYmzfRxQ, this.AssistantItemKey, textClassification, index);
            }
        }
    }

    /* JADX INFO: renamed from: tryGetTextClassification-FDrldGo, reason: not valid java name */
    public final TextClassification m2041tryGetTextClassificationFDrldGo(CharSequence text, long selection) {
        TextClassification textClassification;
        boolean acquired = Mutex.DefaultImpls.tryLock$default(this.mutex, null, 1, null);
        if (!acquired) {
            return null;
        }
        TextClassificationResult textClassificationResult = getTextClassificationResult();
        boolean z = false;
        if (textClassificationResult != null && PlatformSelectionBehaviors_androidKt.m2044canReuseh5sm0ck(textClassificationResult, text, selection)) {
            z = true;
        }
        if (z) {
            textClassification = textClassificationResult.getTextClassification();
        } else {
            textClassification = null;
        }
        Mutex.DefaultImpls.unlock$default(this.mutex, null, 1, null);
        return textClassification;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2, reason: invalid class name */
    /* JADX INFO: compiled from: PlatformSelectionBehaviors.android.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2", f = "PlatformSelectionBehaviors.android.kt", i = {0, 1}, l = {369, 273, 282}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, s = {"L$0", "L$0"}, v = 1)
    static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function2<TextClassifier, Continuation<? super T>, Object> $block;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function2<? super TextClassifier, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlatformSelectionBehaviorsImpl.this.new AnonymousClass2(this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0080 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0081  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00ae A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Throwable {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                r2 = 0
                switch(r1) {
                    case 0: goto L38;
                    case 1: goto L2a;
                    case 2: goto L19;
                    case 3: goto L13;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L13:
                kotlin.ResultKt.throwOnFailure(r12)
                r1 = r12
                goto Laf
            L19:
                r1 = 0
                r3 = 0
                r4 = 0
                java.lang.Object r5 = r11.L$0
                kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L27
                r6 = r3
                r3 = r1
                r1 = r12
                goto L85
            L27:
                r0 = move-exception
                goto Lb3
            L2a:
                r1 = 0
                java.lang.Object r3 = r11.L$1
                androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl r3 = (androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl) r3
                r4 = 0
                java.lang.Object r5 = r11.L$0
                kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
                kotlin.ResultKt.throwOnFailure(r12)
                goto L5a
            L38:
                kotlin.ResultKt.throwOnFailure(r12)
                androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl r1 = androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl.this
                kotlinx.coroutines.sync.Mutex r1 = androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl.access$getMutex$p(r1)
                androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl r3 = androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl.this
                r4 = 0
                r5 = 0
                r6 = r11
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r11.L$0 = r1
                r11.L$1 = r3
                r7 = 1
                r11.label = r7
                java.lang.Object r6 = r1.lock(r4, r6)
                if (r6 != r0) goto L57
                return r0
            L57:
                r10 = r5
                r5 = r1
                r1 = r10
            L5a:
                r6 = 0
                android.view.textclassifier.TextClassifier r7 = androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl.access$getTextClassificationSession$p(r3)     // Catch: java.lang.Throwable -> L27
                if (r7 == 0) goto L6a
                boolean r8 = r7.isDestroyed()     // Catch: java.lang.Throwable -> L27
                if (r8 == 0) goto L69
                goto L6a
            L69:
                goto L8a
            L6a:
                androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1 r7 = new androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1     // Catch: java.lang.Throwable -> L27
                r7.<init>(r3, r2)     // Catch: java.lang.Throwable -> L27
                kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7     // Catch: java.lang.Throwable -> L27
                r11.L$0 = r5     // Catch: java.lang.Throwable -> L27
                r11.L$1 = r2     // Catch: java.lang.Throwable -> L27
                r3 = 2
                r11.label = r3     // Catch: java.lang.Throwable -> L27
                r8 = 300(0x12c, double:1.48E-321)
                java.lang.Object r3 = kotlinx.coroutines.TimeoutKt.withTimeoutOrNull(r8, r7, r11)     // Catch: java.lang.Throwable -> L27
                if (r3 != r0) goto L81
                return r0
            L81:
                r10 = r1
                r1 = r12
                r12 = r3
                r3 = r10
            L85:
                r7 = r12
                android.view.textclassifier.TextClassifier r7 = (android.view.textclassifier.TextClassifier) r7     // Catch: java.lang.Throwable -> Lb0
                r12 = r1
                r1 = r3
            L8a:
                r5.unlock(r4)
                androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$1 r1 = new androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$1
                kotlin.jvm.functions.Function2<android.view.textclassifier.TextClassifier, kotlin.coroutines.Continuation<? super T>, java.lang.Object> r3 = r11.$block
                r1.<init>(r7, r3, r2)
                kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
                r3 = r11
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r11.L$0 = r2
                r11.L$1 = r2
                r2 = 3
                r11.label = r2
                r4 = 200(0xc8, double:9.9E-322)
                java.lang.Object r1 = kotlinx.coroutines.TimeoutKt.withTimeoutOrNull(r4, r1, r3)
                if (r1 != r0) goto Laf
                return r0
            Laf:
                return r1
            Lb0:
                r0 = move-exception
                r12 = r1
                r1 = r3
            Lb3:
                r5.unlock(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: PlatformSelectionBehaviors.android.kt */
        @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$1", f = "PlatformSelectionBehaviors.android.kt", i = {}, l = {283}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
            final /* synthetic */ Function2<TextClassifier, Continuation<? super T>, Object> $block;
            final /* synthetic */ TextClassifier $textClassificationSession;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(TextClassifier textClassifier, Function2<? super TextClassifier, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$textClassificationSession = textClassifier;
                this.$block = function2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$textClassificationSession, this.$block, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        TextClassifier textClassifier = this.$textClassificationSession;
                        if (textClassifier == null) {
                            return null;
                        }
                        Function2<TextClassifier, Continuation<? super T>, Object> function2 = this.$block;
                        this.label = 1;
                        Object objInvoke = function2.invoke(textClassifier, this);
                        return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> Object requireTextClassificationSession(Function2<? super TextClassifier, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        return BuildersKt.withContext(this.coroutineContext, new AnonymousClass2(function2, null), continuation);
    }
}
