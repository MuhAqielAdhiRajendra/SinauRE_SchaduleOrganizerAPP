package androidx.compose.ui.adaptive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.Looper;
import android.view.InputDevice;
import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.UiMediaScope;
import androidx.compose.ui.platform.WindowInfo;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MediaQuery.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010\b\u001a\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0002\u0010\r\u001a\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002\u001a\u0017\u0010\u0012\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0002\u0010\u0014\u001a\u001e\u0010\u0015\u001a\u00020\u000f*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0002\u001a\u0012\u0010\u001d\u001a\u00020\u000f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002\u001a\f\u0010 \u001a\u00020\u000f*\u00020!H\u0002\u001a\f\u0010\"\u001a\u00020\u000f*\u00020!H\u0002\u001a\f\u0010#\u001a\u00020\u000f*\u00020!H\u0002\u001a\f\u0010$\u001a\u00020\u000f*\u00020!H\u0002\u001a\u0014\u0010%\u001a\u00020\u000f*\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\"\u001a\u0010\u001a\u001a\u00020\u000f*\u0004\u0018\u00010\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001c¨\u0006&"}, d2 = {"obtainUiMediaScope", "Landroidx/compose/ui/UiMediaScope;", "context", "Landroid/content/Context;", "view", "Landroid/view/View;", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "(Landroid/content/Context;Landroid/view/View;Landroidx/compose/ui/platform/WindowInfo;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/UiMediaScope;", "resolvePosture", "Landroidx/compose/ui/UiMediaScope$Posture;", "layoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "(Landroidx/window/layout/WindowLayoutInfo;)Ljava/lang/String;", "hasPhysicalKeyboard", "", "inputManager", "Landroid/hardware/input/InputManager;", "resolvePointerPrecision", "Landroidx/compose/ui/UiMediaScope$PointerPrecision;", "(Landroid/hardware/input/InputManager;)Ljava/lang/String;", "hasValidPointerSource", "Landroid/view/InputDevice;", "source", "", "axis", "isImeVisible", "Landroidx/core/view/WindowInsetsCompat;", "(Landroidx/core/view/WindowInsetsCompat;)Z", "isDocked", "intent", "Landroid/content/Intent;", "isCameraAvailable", "Landroid/content/pm/PackageManager;", "isMicAvailable", "isAutomotiveDevice", "isTvDevice", "hasSource", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class MediaQuery_androidKt {
    public static final UiMediaScope obtainUiMediaScope(final Context context, View view, WindowInfo windowInfo, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -590796729, "C(obtainUiMediaScope)N(context,view,windowInfo)122@4434L76,123@4542L62,124@4621L98,130@4806L174,130@4782L198,137@5063L716,137@5037L742,160@5847L336,160@5824L359,172@6261L613,172@6235L639:MediaQuery.android.kt#xs8cwh");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-590796729, $changed, -1, "androidx.compose.ui.adaptive.obtainUiMediaScope (MediaQuery.android.kt:121)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -819969229, "CC(remember):MediaQuery.android.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object systemService = context.getSystemService("input");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.input.InputManager");
            Object value$iv = (InputManager) systemService;
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final InputManager inputManager = (InputManager) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -819965787, "CC(remember):MediaQuery.android.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = Boolean.valueOf(isImeVisible(ViewCompat.getRootWindowInsets(view)));
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        boolean initialImeVisibility = ((Boolean) it$iv2).booleanValue();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -819963223, "CC(remember):MediaQuery.android.kt#9igjgp");
        Object it$iv3 = $composer.rememberedValue();
        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = new UiMediaScopeImpl(context, inputManager, windowInfo, initialImeVisibility);
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        final UiMediaScopeImpl scope = (UiMediaScopeImpl) it$iv3;
        ComposerKt.sourceInformationMarkerEnd($composer);
        scope.set_windowInfo(windowInfo);
        ComposerKt.sourceInformationMarkerStart($composer, -819957227, "CC(remember):MediaQuery.android.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(context);
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv || it$iv4 == Composer.INSTANCE.getEmpty()) {
            Object value$iv4 = (Function2) new MediaQuery_androidKt$obtainUiMediaScope$1$1(context, scope, null);
            $composer.updateRememberedValue(value$iv4);
            it$iv4 = value$iv4;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(context, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv4, $composer, $changed & 14);
        ComposerKt.sourceInformationMarkerStart($composer, -819948461, "CC(remember):MediaQuery.android.kt#9igjgp");
        boolean invalid$iv2 = $composer.changedInstance(inputManager);
        Object it$iv5 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv5 == Composer.INSTANCE.getEmpty()) {
            Object value$iv5 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$2$1$listener$1] */
                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                    final UiMediaScopeImpl uiMediaScopeImpl = scope;
                    final InputManager inputManager2 = inputManager;
                    final ?? r0 = new InputManager.InputDeviceListener() { // from class: androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$2$1$listener$1
                        @Override // android.hardware.input.InputManager.InputDeviceListener
                        public void onInputDeviceAdded(int id) {
                            update();
                        }

                        @Override // android.hardware.input.InputManager.InputDeviceListener
                        public void onInputDeviceRemoved(int id) {
                            update();
                        }

                        @Override // android.hardware.input.InputManager.InputDeviceListener
                        public void onInputDeviceChanged(int id) {
                            update();
                        }

                        public final void update() {
                            uiMediaScopeImpl.m4808set_anyPointerZYK4Wgo(MediaQuery_androidKt.resolvePointerPrecision(inputManager2));
                            uiMediaScopeImpl.setHasPhysicalKeyboard(MediaQuery_androidKt.hasPhysicalKeyboard(inputManager2));
                        }
                    };
                    inputManager.registerInputDeviceListener((InputManager.InputDeviceListener) r0, new Handler(Looper.getMainLooper()));
                    r0.update();
                    final InputManager inputManager3 = inputManager;
                    return new DisposableEffectResult() { // from class: androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$2$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            inputManager3.unregisterInputDeviceListener(r0);
                        }
                    };
                }
            };
            $composer.updateRememberedValue(value$iv5);
            it$iv5 = value$iv5;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(context, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv5, $composer, $changed & 14);
        ComposerKt.sourceInformationMarkerStart($composer, -819923753, "CC(remember):MediaQuery.android.kt#9igjgp");
        boolean invalid$iv3 = $composer.changedInstance(view);
        Object it$iv6 = $composer.rememberedValue();
        if (invalid$iv3 || it$iv6 == Composer.INSTANCE.getEmpty()) {
            Object value$iv6 = (Function1) new MediaQuery_androidKt$obtainUiMediaScope$3$1(view, scope);
            $composer.updateRememberedValue(value$iv6);
            it$iv6 = value$iv6;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(view, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv6, $composer, ($changed >> 3) & 14);
        ComposerKt.sourceInformationMarkerStart($composer, -819910228, "CC(remember):MediaQuery.android.kt#9igjgp");
        boolean invalid$iv4 = $composer.changedInstance(context);
        Object it$iv7 = $composer.rememberedValue();
        if (invalid$iv4 || it$iv7 == Composer.INSTANCE.getEmpty()) {
            Object value$iv7 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$4$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$4$1$receiver$1] */
                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                    IntentFilter filter = new IntentFilter("android.intent.action.DOCK_EVENT");
                    final UiMediaScopeImpl uiMediaScopeImpl = scope;
                    final ?? r1 = new BroadcastReceiver() { // from class: androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$4$1$receiver$1
                        @Override // android.content.BroadcastReceiver
                        public void onReceive(Context context2, Intent intent) {
                            uiMediaScopeImpl.setDocked(MediaQuery_androidKt.isDocked(intent));
                        }
                    };
                    Intent stickyIntent = ContextCompat.registerReceiver(context, (BroadcastReceiver) r1, filter, 2);
                    scope.setDocked(MediaQuery_androidKt.isDocked(stickyIntent));
                    final Context context2 = context;
                    return new DisposableEffectResult() { // from class: androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$4$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            context2.unregisterReceiver(r1);
                        }
                    };
                }
            };
            $composer.updateRememberedValue(value$iv7);
            it$iv7 = value$iv7;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(context, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv7, $composer, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return scope;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String resolvePosture(WindowLayoutInfo layoutInfo) {
        Object element$iv;
        Iterable $this$filterIsInstance$iv = layoutInfo.getDisplayFeatures();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof FoldingFeature) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$firstOrNull$iv = (List) destination$iv$iv;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                FoldingFeature it2 = (FoldingFeature) element$iv;
                if (Intrinsics.areEqual(it2.getState(), FoldingFeature.State.HALF_OPENED)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        FoldingFeature fold = (FoldingFeature) element$iv;
        if (fold == null) {
            return UiMediaScope.Posture.INSTANCE.m4794getFlatm18o9QQ();
        }
        if (Intrinsics.areEqual(fold.getOrientation(), FoldingFeature.Orientation.HORIZONTAL)) {
            return UiMediaScope.Posture.INSTANCE.m4795getTabletopm18o9QQ();
        }
        return UiMediaScope.Posture.INSTANCE.m4793getBookm18o9QQ();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasPhysicalKeyboard(InputManager inputManager) {
        int[] $this$any$iv;
        if (inputManager == null || ($this$any$iv = inputManager.getInputDeviceIds()) == null) {
            return false;
        }
        for (int element$iv : $this$any$iv) {
            InputDevice device = inputManager.getInputDevice(element$iv);
            int id = (device == null || device.getKeyboardType() != 2 || device.isVirtual()) ? 0 : 1;
            if (id != 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String resolvePointerPrecision(InputManager inputManager) {
        if (inputManager == null) {
            return UiMediaScope.PointerPrecision.INSTANCE.m4785getNonefpxItnM();
        }
        String pointerPrecision = UiMediaScope.PointerPrecision.INSTANCE.m4785getNonefpxItnM();
        for (int id : inputManager.getInputDeviceIds()) {
            InputDevice device = inputManager.getInputDevice(id);
            if (device != null) {
                if (hasValidPointerSource$default(device, 8194, 0, 2, null) || hasValidPointerSource$default(device, InputDeviceCompat.SOURCE_STYLUS, 0, 2, null) || hasValidPointerSource$default(device, InputDeviceCompat.SOURCE_TOUCHPAD, 0, 2, null)) {
                    return UiMediaScope.PointerPrecision.INSTANCE.m4784getFinefpxItnM();
                }
                if (hasValidPointerSource$default(device, InputDeviceCompat.SOURCE_TOUCHSCREEN, 0, 2, null)) {
                    pointerPrecision = UiMediaScope.PointerPrecision.INSTANCE.m4783getCoarsefpxItnM();
                } else if (UiMediaScope.PointerPrecision.m4778equalsimpl0(pointerPrecision, UiMediaScope.PointerPrecision.INSTANCE.m4785getNonefpxItnM()) && (hasValidPointerSource$default(device, InputDeviceCompat.SOURCE_JOYSTICK, 0, 2, null) || hasValidPointerSource$default(device, InputDeviceCompat.SOURCE_GAMEPAD, 0, 2, null))) {
                    pointerPrecision = UiMediaScope.PointerPrecision.INSTANCE.m4782getBluntfpxItnM();
                }
            }
        }
        return pointerPrecision;
    }

    static /* synthetic */ boolean hasValidPointerSource$default(InputDevice inputDevice, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return hasValidPointerSource(inputDevice, i, i2);
    }

    private static final boolean hasValidPointerSource(InputDevice $this$hasValidPointerSource, int source, int axis) {
        return ($this$hasValidPointerSource.getSources() & source) == source && $this$hasValidPointerSource.getMotionRange(axis, source) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isImeVisible(WindowInsetsCompat $this$isImeVisible) {
        return $this$isImeVisible != null && $this$isImeVisible.isVisible(WindowInsetsCompat.Type.ime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isDocked(Intent intent) {
        if (intent == null) {
            return false;
        }
        int dockState = intent.getIntExtra("android.intent.extra.DOCK_STATE", 0);
        return dockState != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isCameraAvailable(PackageManager $this$isCameraAvailable) {
        return $this$isCameraAvailable.hasSystemFeature("android.hardware.camera.any");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMicAvailable(PackageManager $this$isMicAvailable) {
        return $this$isMicAvailable.hasSystemFeature("android.hardware.microphone");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAutomotiveDevice(PackageManager $this$isAutomotiveDevice) {
        return $this$isAutomotiveDevice.hasSystemFeature("android.hardware.type.automotive");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isTvDevice(PackageManager $this$isTvDevice) {
        return $this$isTvDevice.hasSystemFeature("android.software.leanback");
    }

    private static final boolean hasSource(int $this$hasSource, int source) {
        return ($this$hasSource & source) == source;
    }
}
