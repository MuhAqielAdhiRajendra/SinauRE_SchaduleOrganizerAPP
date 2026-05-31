package androidx.compose.ui.adaptive;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.input.InputManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.UiMediaScope;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.unit.DpSize;
import kotlin.Metadata;

/* JADX INFO: compiled from: MediaQuery.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00078F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R+\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u00178F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR+\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u000f\u001a\u00020\u001e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\"\u0010\u0016\u001a\u0004\b \u0010\u001a\"\u0004\b!\u0010\u001cR+\u0010#\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0016\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R+\u0010(\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010\u0016\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R+\u0010+\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010\u0016\u001a\u0004\b,\u0010$\"\u0004\b-\u0010&R\u0014\u0010/\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010$R\u0014\u00101\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010$R\u0014\u00103\u001a\u0002048WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u0002048WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00106R\u0014\u00109\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010\u001aR\u0014\u0010;\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\u001aR\u0014\u0010=\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010\u001aR\u0014\u0010@\u001a\u00020A8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010\u001a¨\u0006C"}, d2 = {"Landroidx/compose/ui/adaptive/UiMediaScopeImpl;", "Landroidx/compose/ui/UiMediaScope;", "context", "Landroid/content/Context;", "inputManager", "Landroid/hardware/input/InputManager;", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "imeVisibility", "", "<init>", "(Landroid/content/Context;Landroid/hardware/input/InputManager;Landroidx/compose/ui/platform/WindowInfo;Z)V", "packageManager", "Landroid/content/pm/PackageManager;", "kotlin.jvm.PlatformType", "<set-?>", "_windowInfo", "get_windowInfo", "()Landroidx/compose/ui/platform/WindowInfo;", "set_windowInfo", "(Landroidx/compose/ui/platform/WindowInfo;)V", "_windowInfo$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/UiMediaScope$Posture;", "_windowPosture", "get_windowPosture-m18o9QQ", "()Ljava/lang/String;", "set_windowPosture-InyEWag", "(Ljava/lang/String;)V", "_windowPosture$delegate", "Landroidx/compose/ui/UiMediaScope$PointerPrecision;", "_anyPointer", "get_anyPointer-fpxItnM", "set_anyPointer-ZYK4Wgo", "_anyPointer$delegate", "isDocked", "()Z", "setDocked", "(Z)V", "isDocked$delegate", "isImeVisible", "setImeVisible", "isImeVisible$delegate", "hasPhysicalKeyboard", "getHasPhysicalKeyboard", "setHasPhysicalKeyboard", "hasPhysicalKeyboard$delegate", "hasMicrophone", "getHasMicrophone", "hasCamera", "getHasCamera", "windowWidth", "Landroidx/compose/ui/unit/Dp;", "getWindowWidth-D9Ej5fM", "()F", "windowHeight", "getWindowHeight-D9Ej5fM", "windowPosture", "getWindowPosture-m18o9QQ", "pointerPrecision", "getPointerPrecision-fpxItnM", "keyboardKind", "Landroidx/compose/ui/UiMediaScope$KeyboardKind;", "getKeyboardKind-J9_QTjY", "viewingDistance", "Landroidx/compose/ui/UiMediaScope$ViewingDistance;", "getViewingDistance-tKro-MQ", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class UiMediaScopeImpl implements UiMediaScope {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: _anyPointer$delegate, reason: from kotlin metadata */
    private final MutableState _anyPointer;

    /* JADX INFO: renamed from: _windowInfo$delegate, reason: from kotlin metadata */
    private final MutableState _windowInfo;

    /* JADX INFO: renamed from: hasPhysicalKeyboard$delegate, reason: from kotlin metadata */
    private final MutableState hasPhysicalKeyboard;

    /* JADX INFO: renamed from: isImeVisible$delegate, reason: from kotlin metadata */
    private final MutableState isImeVisible;
    private final PackageManager packageManager;

    /* JADX INFO: renamed from: _windowPosture$delegate, reason: from kotlin metadata */
    private final MutableState _windowPosture = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(UiMediaScope.Posture.m4786boximpl(UiMediaScope.Posture.INSTANCE.m4794getFlatm18o9QQ()), null, 2, null);

    /* JADX INFO: renamed from: isDocked$delegate, reason: from kotlin metadata */
    private final MutableState isDocked = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    public UiMediaScopeImpl(Context context, InputManager inputManager, WindowInfo windowInfo, boolean imeVisibility) {
        this.packageManager = context.getPackageManager();
        this._windowInfo = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(windowInfo, null, 2, null);
        this._anyPointer = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(UiMediaScope.PointerPrecision.m4775boximpl(MediaQuery_androidKt.resolvePointerPrecision(inputManager)), null, 2, null);
        this.isImeVisible = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(imeVisibility), null, 2, null);
        this.hasPhysicalKeyboard = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(MediaQuery_androidKt.hasPhysicalKeyboard(inputManager)), null, 2, null);
    }

    public final WindowInfo get_windowInfo() {
        State $this$getValue$iv = this._windowInfo;
        return (WindowInfo) $this$getValue$iv.getValue();
    }

    public final void set_windowInfo(WindowInfo windowInfo) {
        MutableState $this$setValue$iv = this._windowInfo;
        $this$setValue$iv.setValue(windowInfo);
    }

    /* JADX INFO: renamed from: get_windowPosture-m18o9QQ, reason: not valid java name */
    public final String m4807get_windowPosturem18o9QQ() {
        State $this$getValue$iv = this._windowPosture;
        return ((UiMediaScope.Posture) $this$getValue$iv.getValue()).getDescription();
    }

    /* JADX INFO: renamed from: set_windowPosture-InyEWag, reason: not valid java name */
    public final void m4809set_windowPostureInyEWag(String str) {
        MutableState $this$setValue$iv = this._windowPosture;
        $this$setValue$iv.setValue(UiMediaScope.Posture.m4786boximpl(str));
    }

    /* JADX INFO: renamed from: get_anyPointer-fpxItnM, reason: not valid java name */
    public final String m4806get_anyPointerfpxItnM() {
        State $this$getValue$iv = this._anyPointer;
        return ((UiMediaScope.PointerPrecision) $this$getValue$iv.getValue()).getDescription();
    }

    /* JADX INFO: renamed from: set_anyPointer-ZYK4Wgo, reason: not valid java name */
    public final void m4808set_anyPointerZYK4Wgo(String str) {
        MutableState $this$setValue$iv = this._anyPointer;
        $this$setValue$iv.setValue(UiMediaScope.PointerPrecision.m4775boximpl(str));
    }

    public final boolean isDocked() {
        State $this$getValue$iv = this.isDocked;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setDocked(boolean z) {
        MutableState $this$setValue$iv = this.isDocked;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean isImeVisible() {
        State $this$getValue$iv = this.isImeVisible;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setImeVisible(boolean z) {
        MutableState $this$setValue$iv = this.isImeVisible;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean getHasPhysicalKeyboard() {
        State $this$getValue$iv = this.hasPhysicalKeyboard;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setHasPhysicalKeyboard(boolean z) {
        MutableState $this$setValue$iv = this.hasPhysicalKeyboard;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.ui.UiMediaScope
    public boolean getHasMicrophone() {
        return MediaQuery_androidKt.isMicAvailable(this.packageManager);
    }

    @Override // androidx.compose.ui.UiMediaScope
    public boolean getHasCamera() {
        return MediaQuery_androidKt.isCameraAvailable(this.packageManager);
    }

    @Override // androidx.compose.ui.UiMediaScope
    /* JADX INFO: renamed from: getWindowWidth-D9Ej5fM */
    public float mo4764getWindowWidthD9Ej5fM() {
        return DpSize.m8248getWidthD9Ej5fM(get_windowInfo().mo7289getContainerDpSizeMYxV2XQ());
    }

    @Override // androidx.compose.ui.UiMediaScope
    /* JADX INFO: renamed from: getWindowHeight-D9Ej5fM */
    public float mo4762getWindowHeightD9Ej5fM() {
        return DpSize.m8246getHeightD9Ej5fM(get_windowInfo().mo7289getContainerDpSizeMYxV2XQ());
    }

    @Override // androidx.compose.ui.UiMediaScope
    /* JADX INFO: renamed from: getWindowPosture-m18o9QQ */
    public String mo4763getWindowPosturem18o9QQ() {
        return m4807get_windowPosturem18o9QQ();
    }

    @Override // androidx.compose.ui.UiMediaScope
    /* JADX INFO: renamed from: getPointerPrecision-fpxItnM */
    public String mo4760getPointerPrecisionfpxItnM() {
        return m4806get_anyPointerfpxItnM();
    }

    @Override // androidx.compose.ui.UiMediaScope
    /* JADX INFO: renamed from: getKeyboardKind-J9_QTjY */
    public String mo4759getKeyboardKindJ9_QTjY() {
        return getHasPhysicalKeyboard() ? UiMediaScope.KeyboardKind.INSTANCE.m4773getPhysicalJ9_QTjY() : isImeVisible() ? UiMediaScope.KeyboardKind.INSTANCE.m4774getVirtualJ9_QTjY() : UiMediaScope.KeyboardKind.INSTANCE.m4772getNoneJ9_QTjY();
    }

    @Override // androidx.compose.ui.UiMediaScope
    /* JADX INFO: renamed from: getViewingDistance-tKro-MQ */
    public String mo4761getViewingDistancetKroMQ() {
        if (MediaQuery_androidKt.isTvDevice(this.packageManager)) {
            return UiMediaScope.ViewingDistance.INSTANCE.m4803getFartKroMQ();
        }
        if (MediaQuery_androidKt.isAutomotiveDevice(this.packageManager) || isDocked()) {
            return UiMediaScope.ViewingDistance.INSTANCE.m4804getMediumtKroMQ();
        }
        return UiMediaScope.ViewingDistance.INSTANCE.m4805getNeartKroMQ();
    }
}
