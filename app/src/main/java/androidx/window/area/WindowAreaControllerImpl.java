package androidx.window.area;

import android.app.Activity;
import android.os.Binder;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.window.area.WindowAreaCapability;
import androidx.window.area.WindowAreaControllerImpl;
import androidx.window.area.WindowAreaInfo;
import androidx.window.area.adapter.WindowAreaAdapter;
import androidx.window.core.BuildConfig;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.area.ExtensionWindowAreaPresentation;
import androidx.window.extensions.area.ExtensionWindowAreaStatus;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;
import androidx.window.reflection.Consumer2;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: WindowAreaControllerImpl.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000 62\u00020\u0001:\u0003456B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bH\u0002J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\u0012H\u0002J(\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016J(\u0010/\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u00100\u001a\u000201H\u0016J \u00102\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J \u00103\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u00100\u001a\u000201H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00160\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u00067"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl;", "Landroidx/window/area/WindowAreaController;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "<init>", "(Landroidx/window/extensions/area/WindowAreaComponent;)V", "rearDisplaySessionConsumer", "Landroidx/window/reflection/Consumer2;", "", "currentRearDisplayModeStatus", "Landroidx/window/area/WindowAreaCapability$Status;", "currentRearDisplayPresentationStatus", "activeWindowAreaSession", "", "presentationSessionActive", "currentWindowAreaInfoMap", "Ljava/util/HashMap;", "", "Landroidx/window/area/WindowAreaInfo;", "Lkotlin/collections/HashMap;", "windowAreaInfos", "Lkotlinx/coroutines/flow/Flow;", "", "getWindowAreaInfos", "()Lkotlinx/coroutines/flow/Flow;", "updateRearDisplayAvailability", "", NotificationCompat.CATEGORY_STATUS, "updateRearDisplayPresentationAvailability", "extensionWindowAreaStatus", "Landroidx/window/extensions/area/ExtensionWindowAreaStatus;", "updateRearDisplayWindowArea", "operation", "Landroidx/window/area/WindowAreaCapability$Operation;", "metrics", "Landroidx/window/layout/WindowMetrics;", "shouldRemoveWindowAreaInfo", "windowAreaInfo", "transferActivityToWindowArea", "token", "Landroid/os/Binder;", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "windowAreaSessionCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "presentContentOnWindowArea", "windowAreaPresentationSessionCallback", "Landroidx/window/area/WindowAreaPresentationSessionCallback;", "startRearDisplayMode", "startRearDisplayPresentationMode", "RearDisplaySessionConsumer", "RearDisplayPresentationSessionConsumer", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WindowAreaControllerImpl extends WindowAreaController {
    private static final String REAR_DISPLAY_BINDER_DESCRIPTOR = "WINDOW_AREA_REAR_DISPLAY";
    private boolean activeWindowAreaSession;
    private WindowAreaCapability.Status currentRearDisplayModeStatus;
    private WindowAreaCapability.Status currentRearDisplayPresentationStatus;
    private final HashMap<String, WindowAreaInfo> currentWindowAreaInfoMap;
    private boolean presentationSessionActive;
    private Consumer2<Integer> rearDisplaySessionConsumer;
    private final WindowAreaComponent windowAreaComponent;
    private static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaControllerImpl.class).getSimpleName();

    public WindowAreaControllerImpl(WindowAreaComponent windowAreaComponent) {
        Intrinsics.checkNotNullParameter(windowAreaComponent, "windowAreaComponent");
        this.windowAreaComponent = windowAreaComponent;
        this.currentRearDisplayModeStatus = WindowAreaCapability.Status.INSTANCE.getWINDOW_AREA_STATUS_UNKNOWN$window_release();
        this.currentRearDisplayPresentationStatus = WindowAreaCapability.Status.INSTANCE.getWINDOW_AREA_STATUS_UNKNOWN$window_release();
        this.currentWindowAreaInfoMap = new HashMap<>();
    }

    @Override // androidx.window.area.WindowAreaController
    public Flow<List<WindowAreaInfo>> getWindowAreaInfos() {
        return FlowKt.callbackFlow(new WindowAreaControllerImpl$windowAreaInfos$1(this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRearDisplayAvailability(int status) {
        WindowMetricsCalculator.Companion companion = WindowMetricsCalculator.INSTANCE;
        DisplayMetrics rearDisplayMetrics = this.windowAreaComponent.getRearDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(rearDisplayMetrics, "getRearDisplayMetrics(...)");
        WindowMetrics windowMetrics = companion.fromDisplayMetrics$window_release(rearDisplayMetrics);
        this.currentRearDisplayModeStatus = WindowAreaAdapter.translate$window_release$default(WindowAreaAdapter.INSTANCE, status, this.activeWindowAreaSession, 0, 4, null);
        updateRearDisplayWindowArea(WindowAreaCapability.Operation.OPERATION_TRANSFER_ACTIVITY_TO_AREA, this.currentRearDisplayModeStatus, windowMetrics);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRearDisplayPresentationAvailability(ExtensionWindowAreaStatus extensionWindowAreaStatus) {
        this.currentRearDisplayPresentationStatus = WindowAreaAdapter.translate$window_release$default(WindowAreaAdapter.INSTANCE, extensionWindowAreaStatus.getWindowAreaStatus(), this.presentationSessionActive, 0, 4, null);
        WindowMetricsCalculator.Companion companion = WindowMetricsCalculator.INSTANCE;
        DisplayMetrics windowAreaDisplayMetrics = extensionWindowAreaStatus.getWindowAreaDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(windowAreaDisplayMetrics, "getWindowAreaDisplayMetrics(...)");
        WindowMetrics windowMetrics = companion.fromDisplayMetrics$window_release(windowAreaDisplayMetrics);
        updateRearDisplayWindowArea(WindowAreaCapability.Operation.OPERATION_PRESENT_ON_AREA, this.currentRearDisplayPresentationStatus, windowMetrics);
    }

    private final void updateRearDisplayWindowArea(WindowAreaCapability.Operation operation, WindowAreaCapability.Status status, WindowMetrics metrics) {
        WindowAreaInfo rearDisplayAreaInfo = this.currentWindowAreaInfoMap.get(REAR_DISPLAY_BINDER_DESCRIPTOR);
        if (Intrinsics.areEqual(status, WindowAreaCapability.Status.WINDOW_AREA_STATUS_UNSUPPORTED)) {
            if (rearDisplayAreaInfo != null) {
                if (shouldRemoveWindowAreaInfo(rearDisplayAreaInfo)) {
                    this.currentWindowAreaInfoMap.remove(REAR_DISPLAY_BINDER_DESCRIPTOR);
                    return;
                } else {
                    WindowAreaCapability capability = new WindowAreaCapability(operation, status);
                    rearDisplayAreaInfo.getCapabilityMap$window_release().put(operation, capability);
                    return;
                }
            }
            return;
        }
        if (rearDisplayAreaInfo == null) {
            rearDisplayAreaInfo = new WindowAreaInfo(metrics, WindowAreaInfo.Type.TYPE_REAR_FACING, new Binder(REAR_DISPLAY_BINDER_DESCRIPTOR), this.windowAreaComponent);
        }
        WindowAreaCapability capability2 = new WindowAreaCapability(operation, status);
        rearDisplayAreaInfo.getCapabilityMap$window_release().put(operation, capability2);
        rearDisplayAreaInfo.setMetrics(metrics);
        this.currentWindowAreaInfoMap.put(REAR_DISPLAY_BINDER_DESCRIPTOR, rearDisplayAreaInfo);
    }

    private final boolean shouldRemoveWindowAreaInfo(WindowAreaInfo windowAreaInfo) {
        for (WindowAreaCapability windowAreaCapability : windowAreaInfo.getCapabilityMap$window_release().values()) {
            Intrinsics.checkNotNullExpressionValue(windowAreaCapability, "next(...)");
            WindowAreaCapability capability = windowAreaCapability;
            if (!Intrinsics.areEqual(capability.getStatus(), WindowAreaCapability.Status.WINDOW_AREA_STATUS_UNSUPPORTED)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.window.area.WindowAreaController
    public void transferActivityToWindowArea(Binder token, Activity activity, Executor executor, final WindowAreaSessionCallback windowAreaSessionCallback) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(windowAreaSessionCallback, "windowAreaSessionCallback");
        if (!Intrinsics.areEqual(token.getInterfaceDescriptor(), REAR_DISPLAY_BINDER_DESCRIPTOR)) {
            executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    windowAreaSessionCallback.onSessionEnded(new IllegalArgumentException("Invalid WindowAreaInfo token"));
                }
            });
        } else {
            startRearDisplayMode(activity, executor, windowAreaSessionCallback);
        }
    }

    @Override // androidx.window.area.WindowAreaController
    public void presentContentOnWindowArea(Binder token, Activity activity, Executor executor, final WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(windowAreaPresentationSessionCallback, "windowAreaPresentationSessionCallback");
        if (!Intrinsics.areEqual(token.getInterfaceDescriptor(), REAR_DISPLAY_BINDER_DESCRIPTOR)) {
            executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    windowAreaPresentationSessionCallback.onSessionEnded(new IllegalArgumentException("Invalid WindowAreaInfo token"));
                }
            });
        } else {
            startRearDisplayPresentationMode(activity, executor, windowAreaPresentationSessionCallback);
        }
    }

    private final void startRearDisplayMode(Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback) {
        if (Intrinsics.areEqual(this.currentRearDisplayModeStatus, WindowAreaCapability.Status.WINDOW_AREA_STATUS_ACTIVE)) {
            windowAreaSessionCallback.onSessionEnded(new IllegalStateException("The WindowArea feature is currently active, WindowAreaInfo#getActiveSessioncan be used to get an instance of the current active session"));
            return;
        }
        if (!Intrinsics.areEqual(this.currentRearDisplayModeStatus, WindowAreaCapability.Status.WINDOW_AREA_STATUS_AVAILABLE)) {
            windowAreaSessionCallback.onSessionEnded(new IllegalStateException("The WindowArea feature is currently not available to be entered"));
            return;
        }
        this.activeWindowAreaSession = true;
        this.rearDisplaySessionConsumer = new RearDisplaySessionConsumer(this, executor, windowAreaSessionCallback, this.windowAreaComponent);
        WindowAreaComponent windowAreaComponent = this.windowAreaComponent;
        Consumer2<Integer> consumer2 = this.rearDisplaySessionConsumer;
        if (consumer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rearDisplaySessionConsumer");
            consumer2 = null;
        }
        windowAreaComponent.startRearDisplaySession(activity, consumer2);
    }

    private final void startRearDisplayPresentationMode(Activity activity, Executor executor, WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback) {
        if (!Intrinsics.areEqual(this.currentRearDisplayPresentationStatus, WindowAreaCapability.Status.WINDOW_AREA_STATUS_AVAILABLE)) {
            windowAreaPresentationSessionCallback.onSessionEnded(new IllegalStateException("The WindowArea feature is currently not available to be entered"));
        } else {
            this.presentationSessionActive = true;
            this.windowAreaComponent.startRearDisplayPresentationSession(activity, new RearDisplayPresentationSessionConsumer(this, executor, windowAreaPresentationSessionCallback, this.windowAreaComponent));
        }
    }

    /* JADX INFO: compiled from: WindowAreaControllerImpl.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0080\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl$RearDisplaySessionConsumer;", "Landroidx/window/reflection/Consumer2;", "", "executor", "Ljava/util/concurrent/Executor;", "appCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "extensionsComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "<init>", "(Landroidx/window/area/WindowAreaControllerImpl;Ljava/util/concurrent/Executor;Landroidx/window/area/WindowAreaSessionCallback;Landroidx/window/extensions/area/WindowAreaComponent;)V", "session", "Landroidx/window/area/WindowAreaSession;", "accept", "", "value", "onSessionStarted", "onSessionFinished", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class RearDisplaySessionConsumer implements Consumer2<Integer> {
        private final WindowAreaSessionCallback appCallback;
        private final Executor executor;
        private final WindowAreaComponent extensionsComponent;
        private WindowAreaSession session;
        final /* synthetic */ WindowAreaControllerImpl this$0;

        public RearDisplaySessionConsumer(WindowAreaControllerImpl this$0, Executor executor, WindowAreaSessionCallback appCallback, WindowAreaComponent extensionsComponent) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            Intrinsics.checkNotNullParameter(appCallback, "appCallback");
            Intrinsics.checkNotNullParameter(extensionsComponent, "extensionsComponent");
            this.this$0 = this$0;
            this.executor = executor;
            this.appCallback = appCallback;
            this.extensionsComponent = extensionsComponent;
        }

        @Override // androidx.window.reflection.Consumer2
        public /* bridge */ /* synthetic */ void accept(Integer num) {
            accept(num.intValue());
        }

        public void accept(int value) {
            switch (value) {
                case 0:
                    onSessionFinished();
                    break;
                case 1:
                    onSessionStarted();
                    break;
                default:
                    if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.STRICT) {
                        Log.d(WindowAreaControllerImpl.TAG, "Received an unknown session status value: " + value);
                    }
                    onSessionFinished();
                    break;
            }
        }

        private final void onSessionStarted() {
            this.session = new RearDisplaySessionImpl(this.extensionsComponent);
            final WindowAreaSession it = this.session;
            if (it != null) {
                this.executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.appCallback.onSessionStarted(it);
                    }
                });
            }
        }

        private final void onSessionFinished() {
            this.this$0.activeWindowAreaSession = false;
            this.session = null;
            this.executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.appCallback.onSessionEnded(null);
                }
            });
        }
    }

    /* JADX INFO: compiled from: WindowAreaControllerImpl.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl$RearDisplayPresentationSessionConsumer;", "Landroidx/window/reflection/Consumer2;", "", "executor", "Ljava/util/concurrent/Executor;", "windowAreaPresentationSessionCallback", "Landroidx/window/area/WindowAreaPresentationSessionCallback;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "<init>", "(Landroidx/window/area/WindowAreaControllerImpl;Ljava/util/concurrent/Executor;Landroidx/window/area/WindowAreaPresentationSessionCallback;Landroidx/window/extensions/area/WindowAreaComponent;)V", "lastReportedSessionStatus", "accept", "", "value", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class RearDisplayPresentationSessionConsumer implements Consumer2<Integer> {
        private final Executor executor;
        private int lastReportedSessionStatus;
        final /* synthetic */ WindowAreaControllerImpl this$0;
        private final WindowAreaComponent windowAreaComponent;
        private final WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback;

        public RearDisplayPresentationSessionConsumer(WindowAreaControllerImpl this$0, Executor executor, WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback, WindowAreaComponent windowAreaComponent) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            Intrinsics.checkNotNullParameter(windowAreaPresentationSessionCallback, "windowAreaPresentationSessionCallback");
            Intrinsics.checkNotNullParameter(windowAreaComponent, "windowAreaComponent");
            this.this$0 = this$0;
            this.executor = executor;
            this.windowAreaPresentationSessionCallback = windowAreaPresentationSessionCallback;
            this.windowAreaComponent = windowAreaComponent;
        }

        @Override // androidx.window.reflection.Consumer2
        public /* bridge */ /* synthetic */ void accept(Integer num) {
            accept(num.intValue());
        }

        public void accept(final int value) {
            final int previousStatus = this.lastReportedSessionStatus;
            this.lastReportedSessionStatus = value;
            Executor executor = this.executor;
            final WindowAreaControllerImpl windowAreaControllerImpl = this.this$0;
            executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplayPresentationSessionConsumer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplayPresentationSessionConsumer.accept$lambda$0(value, previousStatus, this, windowAreaControllerImpl);
                }
            });
        }

        static final void accept$lambda$0(int $value, int $previousStatus, RearDisplayPresentationSessionConsumer this$0, WindowAreaControllerImpl this$1) {
            switch ($value) {
                case 0:
                    this$1.presentationSessionActive = false;
                    this$0.windowAreaPresentationSessionCallback.onSessionEnded(null);
                    break;
                case 1:
                    if ($previousStatus == 2) {
                        this$0.windowAreaPresentationSessionCallback.onContainerVisibilityChanged(false);
                    } else {
                        WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback = this$0.windowAreaPresentationSessionCallback;
                        WindowAreaComponent windowAreaComponent = this$0.windowAreaComponent;
                        ExtensionWindowAreaPresentation rearDisplayPresentation = this$0.windowAreaComponent.getRearDisplayPresentation();
                        Intrinsics.checkNotNull(rearDisplayPresentation);
                        windowAreaPresentationSessionCallback.onSessionStarted(new RearDisplayPresentationSessionPresenterImpl(windowAreaComponent, rearDisplayPresentation, ExtensionsUtil.INSTANCE.getSafeVendorApiLevel()));
                    }
                    break;
                case 2:
                    this$0.windowAreaPresentationSessionCallback.onContainerVisibilityChanged(true);
                    break;
                default:
                    Log.e(WindowAreaControllerImpl.TAG, "Invalid session state value received: " + $value);
                    break;
            }
        }
    }
}
