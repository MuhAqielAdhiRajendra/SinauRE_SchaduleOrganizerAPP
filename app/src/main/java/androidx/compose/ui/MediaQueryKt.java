package androidx.compose.ui;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalAccessorScope;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: MediaQuery.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\f\u001a,\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000e2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\u000f\u001a&\u0010\u0007\u001a\u00020\b*\u00020\u00102\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\b\u000bH\u0087\b\u001a&\u0010\u0007\u001a\u00020\b*\u00020\u00112\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\b\u000bH\u0087\b\"\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012²\u0006\u001b\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\b\u000bX\u008a\u0084\u0002"}, d2 = {"LocalUiMediaScope", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/ui/UiMediaScope;", "getLocalUiMediaScope$annotations", "()V", "getLocalUiMediaScope", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "mediaQuery", "", "query", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Z", "derivedMediaQuery", "Landroidx/compose/runtime/State;", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "Landroidx/compose/runtime/CompositionLocalAccessorScope;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "ui", "currentQuery"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class MediaQueryKt {
    private static final ProvidableCompositionLocal<UiMediaScope> LocalUiMediaScope = CompositionLocalKt.staticCompositionLocalOf(new Function0<UiMediaScope>() { // from class: androidx.compose.ui.MediaQueryKt$LocalUiMediaScope$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final UiMediaScope invoke() {
            throw new IllegalStateException("CompositionLocal LocalUiMediaScope not present".toString());
        }
    });

    public static /* synthetic */ void getLocalUiMediaScope$annotations() {
    }

    public static final ProvidableCompositionLocal<UiMediaScope> getLocalUiMediaScope() {
        return LocalUiMediaScope;
    }

    public static final boolean mediaQuery(Function1<? super UiMediaScope, Boolean> function1, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1936917258, "C(mediaQuery)N(query)232@9276L7:MediaQuery.kt#quzd79");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1936917258, $changed, -1, "androidx.compose.ui.mediaQuery (MediaQuery.kt:232)");
        }
        ProvidableCompositionLocal<UiMediaScope> providableCompositionLocal = LocalUiMediaScope;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        boolean zBooleanValue = function1.invoke(objConsume).booleanValue();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return zBooleanValue;
    }

    public static final State<Boolean> derivedMediaQuery(Function1<? super UiMediaScope, Boolean> function1, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -287752256, "C(derivedMediaQuery)N(query)252@10230L7,253@10262L27,255@10302L69:MediaQuery.kt#quzd79");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-287752256, $changed, -1, "androidx.compose.ui.derivedMediaQuery (MediaQuery.kt:251)");
        }
        ProvidableCompositionLocal<UiMediaScope> providableCompositionLocal = LocalUiMediaScope;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final UiMediaScope mediaScope = (UiMediaScope) objConsume;
        final State currentQuery$delegate = SnapshotStateKt.rememberUpdatedState(function1, $composer, $changed & 14);
        ComposerKt.sourceInformationMarkerStart($composer, -1652005051, "CC(remember):MediaQuery.kt#9igjgp");
        boolean invalid$iv = $composer.changed(mediaScope);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotStateKt.derivedStateOf(new Function0<Boolean>() { // from class: androidx.compose.ui.MediaQueryKt$derivedMediaQuery$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return (Boolean) MediaQueryKt.derivedMediaQuery$lambda$0(currentQuery$delegate).invoke(mediaScope);
                }
            });
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        State<Boolean> state = (State) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<UiMediaScope, Boolean> derivedMediaQuery$lambda$0(State<? extends Function1<? super UiMediaScope, Boolean>> state) {
        Object thisObj$iv = state.getValue();
        return (Function1) thisObj$iv;
    }

    public static final boolean mediaQuery(CompositionLocalAccessorScope $this$mediaQuery, Function1<? super UiMediaScope, Boolean> function1) {
        return function1.invoke($this$mediaQuery.getCurrentValue(getLocalUiMediaScope())).booleanValue();
    }

    public static final boolean mediaQuery(CompositionLocalConsumerModifierNode $this$mediaQuery, Function1<? super UiMediaScope, Boolean> function1) {
        return function1.invoke(CompositionLocalConsumerModifierNodeKt.currentValueOf($this$mediaQuery, getLocalUiMediaScope())).booleanValue();
    }
}
