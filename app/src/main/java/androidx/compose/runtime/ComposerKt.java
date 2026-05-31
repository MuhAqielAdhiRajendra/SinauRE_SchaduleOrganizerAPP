package androidx.compose.runtime;

import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.gapbuffer.GapAnchorKt;
import androidx.compose.runtime.composer.gapbuffer.SlotReader;
import androidx.compose.runtime.composer.gapbuffer.SlotWriter;
import androidx.compose.runtime.tooling.ComposeStackTraceMode;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Composer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b%\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a3\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u0002H\u00060\u000b¢\u0006\u0002\b\fH\u0087\b¢\u0006\u0002\u0010\r\u001a\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0011H\u0007\u001a \u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0011H\u0007\u001a\b\u0010\u001f\u001a\u00020\tH\u0007\u001a\u0018\u0010 \u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u0011H\u0007\u001a(\u0010 \u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u0011H\u0007\u001a\b\u0010$\u001a\u00020\u000fH\u0007\u001a\u0010\u0010%\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0007\u001a\u0014\u0010&\u001a\u00020\u000f*\u00020\u00022\u0006\u0010'\u001a\u00020(H\u0000\u001a7\u0010)\u001a\u00020\u000f\"\u0004\b\u0000\u0010**\u00020\u00022\b\u0010+\u001a\u0004\u0018\u00010,2\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H*0.H\u0080\b\u001a*\u0010W\u001a\u00020\u000f2\u0006\u0010X\u001a\u00020\t2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\u0080\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a\u001f\u0010[\u001a\u00020\u000f2\u0006\u0010X\u001a\u00020\t2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\u0080\b\u001a\u0011\u0010[\u001a\u00020\u000f2\u0006\u0010X\u001a\u00020\tH\u0080\b\u001a\u0011\u0010W\u001a\u00020\u000f2\u0006\u0010X\u001a\u00020\tH\u0080\b\u001a\u0010\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020\u0011H\u0000\u001a\u0010\u0010_\u001a\u00020\u000f2\u0006\u0010^\u001a\u00020\u0011H\u0000\u001a.\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020c2\u0006\u0010Q\u001a\u00020d2\u0006\u0010e\u001a\u00020\u00022\f\u0010f\u001a\b\u0012\u0002\b\u0003\u0018\u00010gH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0017\"\u001c\u0010\u0018\u001a\u00020\u0019X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\"\u0018\u0010/\u001a\u00020\t*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b/\u00100\"\u0018\u0010/\u001a\u00020\t*\u0002018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b/\u00102\"\u000e\u00103\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u00104\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b5\u0010\u0017\"\u001c\u00106\u001a\u0002078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b8\u0010\u0017\u001a\u0004\b9\u0010:\"\u0016\u0010;\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b<\u0010\u0017\"\u001c\u0010=\u001a\u0002078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b>\u0010\u0017\u001a\u0004\b?\u0010:\"\u0016\u0010@\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bA\u0010\u0017\"\u001c\u0010B\u001a\u0002078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bC\u0010\u0017\u001a\u0004\bD\u0010:\"\u0016\u0010E\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bF\u0010\u0017\"\u001c\u0010G\u001a\u0002078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bH\u0010\u0017\u001a\u0004\bI\u0010:\"\u0016\u0010J\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bK\u0010\u0017\"\u001c\u0010L\u001a\u0002078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bM\u0010\u0017\u001a\u0004\bN\u0010:\"\u0016\u0010O\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bP\u0010\u0017\"\u001c\u0010Q\u001a\u0002078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bR\u0010\u0017\u001a\u0004\bS\u0010:\"\u0016\u0010T\u001a\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\bU\u0010\u0017\"\u000e\u0010V\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010Z\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000¨\u0006h"}, d2 = {"nextGroup", "", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "getNextGroup", "(Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;)I", "cache", "T", "Landroidx/compose/runtime/Composer;", "invalid", "", "block", "Lkotlin/Function0;", "Landroidx/compose/runtime/DisallowComposableCalls;", "(Landroidx/compose/runtime/Composer;ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "sourceInformation", "", "composer", "", "sourceInformationMarkerStart", "key", "compositionTracer", "Landroidx/compose/runtime/CompositionTracer;", "getCompositionTracer$annotations", "()V", "composeStackTraceMode", "Landroidx/compose/runtime/tooling/ComposeStackTraceMode;", "getComposeStackTraceMode", "()I", "setComposeStackTraceMode-76WK1J0", "(I)V", "I", "isTraceInProgress", "traceEventStart", "info", "dirty1", "dirty2", "traceEventEnd", "sourceInformationMarkerEnd", "removeCurrentGroup", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "withAfterAnchorInfo", "R", "anchor", "Landroidx/compose/runtime/Anchor;", "cb", "Lkotlin/Function2;", "isAfterFirstChild", "(Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;)Z", "Landroidx/compose/runtime/composer/gapbuffer/SlotReader;", "(Landroidx/compose/runtime/composer/gapbuffer/SlotReader;)Z", "defaultsKey", "invocationKey", "getInvocationKey$annotations", "invocation", "", "getInvocation$annotations", "getInvocation", "()Ljava/lang/Object;", "providerKey", "getProviderKey$annotations", "provider", "getProvider$annotations", "getProvider", "compositionLocalMapKey", "getCompositionLocalMapKey$annotations", "compositionLocalMap", "getCompositionLocalMap$annotations", "getCompositionLocalMap", "providerValuesKey", "getProviderValuesKey$annotations", "providerValues", "getProviderValues$annotations", "getProviderValues", "providerMapsKey", "getProviderMapsKey$annotations", "providerMaps", "getProviderMaps$annotations", "getProviderMaps", "referenceKey", "getReferenceKey$annotations", TypedValues.Custom.S_REFERENCE, "getReference$annotations", "getReference", "reuseKey", "getReuseKey$annotations", "invalidGroupLocation", "runtimeCheck", "value", "lazyMessage", "EnableDebugRuntimeChecks", "debugRuntimeCheck", "composeRuntimeError", "", "message", "composeImmediateRuntimeError", "extractMovableContentAtCurrent", "Landroidx/compose/runtime/MovableContentState;", "composition", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/MovableContentStateReference;", "slots", "applier", "Landroidx/compose/runtime/Applier;", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ComposerKt {
    public static final boolean EnableDebugRuntimeChecks = false;
    public static final int compositionLocalMapKey = 202;
    private static CompositionTracer compositionTracer = null;
    public static final int defaultsKey = -127;
    private static final int invalidGroupLocation = -2;
    public static final int invocationKey = 200;
    public static final int providerKey = 201;
    public static final int providerMapsKey = 204;
    public static final int providerValuesKey = 203;
    public static final int referenceKey = 206;
    public static final int reuseKey = 207;
    private static int composeStackTraceMode = ComposeStackTraceMode.INSTANCE.m4734getNoneMD5MrJc();
    private static final Object invocation = new OpaqueKey("provider");
    private static final Object provider = new OpaqueKey("provider");
    private static final Object compositionLocalMap = new OpaqueKey("compositionLocalMap");
    private static final Object providerValues = new OpaqueKey("providerValues");
    private static final Object providerMaps = new OpaqueKey("providers");
    private static final Object reference = new OpaqueKey(TypedValues.Custom.S_REFERENCE);

    public static /* synthetic */ void getCompositionLocalMap$annotations() {
    }

    public static /* synthetic */ void getCompositionLocalMapKey$annotations() {
    }

    private static /* synthetic */ void getCompositionTracer$annotations() {
    }

    public static /* synthetic */ void getInvocation$annotations() {
    }

    public static /* synthetic */ void getInvocationKey$annotations() {
    }

    public static /* synthetic */ void getProvider$annotations() {
    }

    public static /* synthetic */ void getProviderKey$annotations() {
    }

    public static /* synthetic */ void getProviderMaps$annotations() {
    }

    public static /* synthetic */ void getProviderMapsKey$annotations() {
    }

    public static /* synthetic */ void getProviderValues$annotations() {
    }

    public static /* synthetic */ void getProviderValuesKey$annotations() {
    }

    public static /* synthetic */ void getReference$annotations() {
    }

    public static /* synthetic */ void getReferenceKey$annotations() {
    }

    public static /* synthetic */ void getReuseKey$annotations() {
    }

    private static final int getNextGroup(SlotWriter $this$nextGroup) {
        return $this$nextGroup.getCurrentGroup() + $this$nextGroup.groupSize($this$nextGroup.getCurrentGroup());
    }

    @ComposeCompilerApi
    public static final <T> T cache(Composer composer, boolean z, Function0<? extends T> function0) {
        T t = (T) composer.rememberedValue();
        if (!z && t != Composer.INSTANCE.getEmpty()) {
            return t;
        }
        T tInvoke = function0.invoke();
        composer.updateRememberedValue(tInvoke);
        return tInvoke;
    }

    @ComposeCompilerApi
    public static final void sourceInformation(Composer composer, String sourceInformation) {
        composer.sourceInformation(sourceInformation);
    }

    @ComposeCompilerApi
    public static final void sourceInformationMarkerStart(Composer composer, int key, String sourceInformation) {
        composer.sourceInformationMarkerStart(key, sourceInformation);
    }

    public static final int getComposeStackTraceMode() {
        return composeStackTraceMode;
    }

    /* JADX INFO: renamed from: setComposeStackTraceMode-76WK1J0, reason: not valid java name */
    public static final void m4388setComposeStackTraceMode76WK1J0(int i) {
        composeStackTraceMode = i;
    }

    @ComposeCompilerApi
    public static final boolean isTraceInProgress() {
        CompositionTracer it = compositionTracer;
        return it != null && it.isTraceInProgress();
    }

    @ComposeCompilerApi
    public static final void traceEventStart(int key, int dirty1, int dirty2, String info) {
        CompositionTracer compositionTracer2 = compositionTracer;
        if (compositionTracer2 != null) {
            compositionTracer2.traceEventStart(key, dirty1, dirty2, info);
        }
    }

    @ComposeCompilerApi
    public static final void traceEventEnd() {
        CompositionTracer compositionTracer2 = compositionTracer;
        if (compositionTracer2 != null) {
            compositionTracer2.traceEventEnd();
        }
    }

    @ComposeCompilerApi
    public static final void sourceInformationMarkerEnd(Composer composer) {
        composer.sourceInformationMarkerEnd();
    }

    public static final void removeCurrentGroup(SlotWriter $this$removeCurrentGroup, final RememberManager rememberManager) {
        $this$removeCurrentGroup.forAllDataInRememberOrder($this$removeCurrentGroup.getCurrentGroup(), new Function2() { // from class: androidx.compose.runtime.ComposerKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ComposerKt.removeCurrentGroup$lambda$0(rememberManager, ((Integer) obj).intValue(), obj2);
            }
        });
        $this$removeCurrentGroup.removeGroup();
    }

    static final Unit removeCurrentGroup$lambda$0(RememberManager $rememberManager, int i, Object slot) {
        if (slot instanceof ComposeNodeLifecycleCallback) {
            $rememberManager.releasing((ComposeNodeLifecycleCallback) slot);
        }
        if (slot instanceof RememberObserverHolder) {
            $rememberManager.forgetting((RememberObserverHolder) slot);
        }
        if (slot instanceof RecomposeScopeImpl) {
            ((RecomposeScopeImpl) slot).release();
        }
        return Unit.INSTANCE;
    }

    public static final <R> void withAfterAnchorInfo(SlotWriter $this$withAfterAnchorInfo, Anchor anchor, Function2<? super Integer, ? super Integer, ? extends R> function2) {
        int priority = -1;
        int endRelativeAfter = -1;
        if (anchor != null && anchor.getValid()) {
            priority = $this$withAfterAnchorInfo.anchorIndex(GapAnchorKt.asGapAnchor(anchor));
            endRelativeAfter = $this$withAfterAnchorInfo.getSlotsSize() - $this$withAfterAnchorInfo.slotsEndAllIndex$runtime(priority);
        }
        function2.invoke(Integer.valueOf(priority), Integer.valueOf(endRelativeAfter));
    }

    public static final boolean isAfterFirstChild(SlotWriter $this$isAfterFirstChild) {
        return $this$isAfterFirstChild.getCurrentGroup() > $this$isAfterFirstChild.getParent() + 1;
    }

    public static final boolean isAfterFirstChild(SlotReader $this$isAfterFirstChild) {
        return $this$isAfterFirstChild.getCurrent() > $this$isAfterFirstChild.getParent() + 1;
    }

    public static final Object getInvocation() {
        return invocation;
    }

    public static final Object getProvider() {
        return provider;
    }

    public static final Object getCompositionLocalMap() {
        return compositionLocalMap;
    }

    public static final Object getProviderValues() {
        return providerValues;
    }

    public static final Object getProviderMaps() {
        return providerMaps;
    }

    public static final Object getReference() {
        return reference;
    }

    public static final void runtimeCheck(boolean value, Function0<String> function0) {
        if (!value) {
            composeImmediateRuntimeError(function0.invoke());
        }
    }

    public static final void debugRuntimeCheck(boolean value, Function0<String> function0) {
    }

    public static final void debugRuntimeCheck(boolean value) {
    }

    public static final void runtimeCheck(boolean value) {
        if (value) {
            return;
        }
        composeImmediateRuntimeError("Check failed");
    }

    public static final Void composeRuntimeError(String message) {
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + message + "). Please report to Google or use https://goo.gle/compose-feedback");
    }

    public static final void composeImmediateRuntimeError(String message) {
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + message + "). Please report to Google or use https://goo.gle/compose-feedback");
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01c0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.runtime.MovableContentState extractMovableContentAtCurrent(final androidx.compose.runtime.ControlledComposition r59, androidx.compose.runtime.MovableContentStateReference r60, androidx.compose.runtime.composer.gapbuffer.SlotWriter r61, androidx.compose.runtime.Applier<?> r62) {
        /*
            Method dump skipped, instruction units count: 847
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerKt.extractMovableContentAtCurrent(androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.MovableContentStateReference, androidx.compose.runtime.composer.gapbuffer.SlotWriter, androidx.compose.runtime.Applier):androidx.compose.runtime.MovableContentState");
    }
}
