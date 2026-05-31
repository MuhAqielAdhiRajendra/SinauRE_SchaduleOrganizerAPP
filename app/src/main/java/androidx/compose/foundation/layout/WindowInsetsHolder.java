package androidx.compose.foundation.layout;

import android.graphics.Path;
import android.view.View;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.layout.WindowInsetsHolder;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.graphics.Insets;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowInsets.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0001\u0018\u0000 Q2\u00020\u0001:\u0001QB\u001b\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010I\u001a\u00020J2\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010K\u001a\u00020J2\u0006\u0010\u0004\u001a\u00020\u0005J\u0018\u0010L\u001a\u00020J2\u0006\u0010M\u001a\u00020\u00032\b\b\u0002\u0010N\u001a\u00020FJ\u000e\u0010O\u001a\u00020J2\u0006\u0010M\u001a\u00020\u0003J\u000e\u0010P\u001a\u00020J2\u0006\u0010M\u001a\u00020\u0003R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u0011\u0010\u0016\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0018\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000bR\u0011\u0010\u001a\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR/\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010 \u001a\u0004\u0018\u00010!8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010)\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010-\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b.\u0010,R\u0011\u0010/\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b0\u0010,R\u0011\u00101\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001fR\u0011\u00103\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001fR\u0011\u00105\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001fR\u0011\u00107\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u001fR\u0011\u00109\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001fR\u0011\u0010;\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u001fR\u0011\u0010=\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u001fR\u0017\u0010?\u001a\u00020@¢\u0006\u000e\n\u0000\u0012\u0004\bA\u0010B\u001a\u0004\bC\u0010DR\u000e\u0010E\u001a\u00020FX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsHolder;", "", "insets", "Landroidx/core/view/WindowInsetsCompat;", "view", "Landroid/view/View;", "<init>", "(Landroidx/core/view/WindowInsetsCompat;Landroid/view/View;)V", "captionBar", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "getCaptionBar", "()Landroidx/compose/foundation/layout/AndroidWindowInsets;", "displayCutout", "getDisplayCutout", "ime", "getIme", "mandatorySystemGestures", "getMandatorySystemGestures", "navigationBars", "getNavigationBars", "statusBars", "getStatusBars", "systemBars", "getSystemBars", "systemGestures", "getSystemGestures", "tappableElement", "getTappableElement", "waterfall", "Landroidx/compose/foundation/layout/ValueInsets;", "getWaterfall", "()Landroidx/compose/foundation/layout/ValueInsets;", "<set-?>", "Landroidx/compose/ui/graphics/Path;", "cutoutPath", "getCutoutPath", "()Landroidx/compose/ui/graphics/Path;", "setCutoutPath", "(Landroidx/compose/ui/graphics/Path;)V", "cutoutPath$delegate", "Landroidx/compose/runtime/MutableState;", "safeDrawing", "Landroidx/compose/foundation/layout/WindowInsets;", "getSafeDrawing", "()Landroidx/compose/foundation/layout/WindowInsets;", "safeGestures", "getSafeGestures", "safeContent", "getSafeContent", "captionBarIgnoringVisibility", "getCaptionBarIgnoringVisibility", "navigationBarsIgnoringVisibility", "getNavigationBarsIgnoringVisibility", "statusBarsIgnoringVisibility", "getStatusBarsIgnoringVisibility", "systemBarsIgnoringVisibility", "getSystemBarsIgnoringVisibility", "tappableElementIgnoringVisibility", "getTappableElementIgnoringVisibility", "imeAnimationTarget", "getImeAnimationTarget", "imeAnimationSource", "getImeAnimationSource", "consumes", "", "getConsumes$annotations", "()V", "getConsumes", "()Z", "accessCount", "", "insetsListener", "Landroidx/compose/foundation/layout/InsetsListener;", "incrementAccessors", "", "decrementAccessors", "update", "windowInsets", "types", "updateImeAnimationSource", "updateImeAnimationTarget", "Companion", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class WindowInsetsHolder {
    private static boolean testInsets;
    private int accessCount;
    private final AndroidWindowInsets captionBar;
    private final ValueInsets captionBarIgnoringVisibility;
    private final boolean consumes;

    /* JADX INFO: renamed from: cutoutPath$delegate, reason: from kotlin metadata */
    private final MutableState cutoutPath;
    private final AndroidWindowInsets displayCutout;
    private final AndroidWindowInsets ime;
    private final ValueInsets imeAnimationSource;
    private final ValueInsets imeAnimationTarget;
    private final InsetsListener insetsListener;
    private final AndroidWindowInsets mandatorySystemGestures;
    private final AndroidWindowInsets navigationBars;
    private final ValueInsets navigationBarsIgnoringVisibility;
    private final WindowInsets safeContent;
    private final WindowInsets safeDrawing;
    private final WindowInsets safeGestures;
    private final AndroidWindowInsets statusBars;
    private final ValueInsets statusBarsIgnoringVisibility;
    private final AndroidWindowInsets systemBars;
    private final ValueInsets systemBarsIgnoringVisibility;
    private final AndroidWindowInsets systemGestures;
    private final AndroidWindowInsets tappableElement;
    private final ValueInsets tappableElementIgnoringVisibility;
    private final ValueInsets waterfall;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final WeakHashMap<View, WindowInsetsHolder> viewMap = new WeakHashMap<>();

    public /* synthetic */ WindowInsetsHolder(WindowInsetsCompat windowInsetsCompat, View view, DefaultConstructorMarker defaultConstructorMarker) {
        this(windowInsetsCompat, view);
    }

    public static /* synthetic */ void getConsumes$annotations() {
    }

    private WindowInsetsHolder(WindowInsetsCompat insets, View view) {
        DisplayCutoutCompat displayCutout;
        Path cutoutPath;
        DisplayCutoutCompat displayCutout2;
        Insets waterfallInsets;
        this.captionBar = INSTANCE.systemInsets(insets, WindowInsetsCompat.Type.captionBar(), "captionBar");
        this.displayCutout = INSTANCE.systemInsets(insets, WindowInsetsCompat.Type.displayCutout(), "displayCutout");
        this.ime = INSTANCE.systemInsets(insets, WindowInsetsCompat.Type.ime(), "ime");
        this.mandatorySystemGestures = INSTANCE.systemInsets(insets, WindowInsetsCompat.Type.mandatorySystemGestures(), "mandatorySystemGestures");
        this.navigationBars = INSTANCE.systemInsets(insets, WindowInsetsCompat.Type.navigationBars(), "navigationBars");
        this.statusBars = INSTANCE.systemInsets(insets, WindowInsetsCompat.Type.statusBars(), "statusBars");
        this.systemBars = INSTANCE.systemInsets(insets, WindowInsetsCompat.Type.systemBars(), "systemBars");
        this.systemGestures = INSTANCE.systemInsets(insets, WindowInsetsCompat.Type.systemGestures(), "systemGestures");
        this.tappableElement = INSTANCE.systemInsets(insets, WindowInsetsCompat.Type.tappableElement(), "tappableElement");
        this.waterfall = WindowInsets_androidKt.ValueInsets((insets == null || (displayCutout2 = insets.getDisplayCutout()) == null || (waterfallInsets = displayCutout2.getWaterfallInsets()) == null) ? Insets.NONE : waterfallInsets, "waterfall");
        this.cutoutPath = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default((insets == null || (displayCutout = insets.getDisplayCutout()) == null || (cutoutPath = displayCutout.getCutoutPath()) == null) ? null : AndroidPath_androidKt.asComposePath(cutoutPath), null, 2, null);
        this.safeDrawing = WindowInsetsKt.union(WindowInsetsKt.union(this.systemBars, this.ime), this.displayCutout);
        this.safeGestures = WindowInsetsKt.union(WindowInsetsKt.union(WindowInsetsKt.union(this.tappableElement, this.mandatorySystemGestures), this.systemGestures), this.waterfall);
        this.safeContent = WindowInsetsKt.union(this.safeDrawing, this.safeGestures);
        this.captionBarIgnoringVisibility = INSTANCE.valueInsetsIgnoringVisibility(insets, WindowInsetsCompat.Type.captionBar(), "captionBarIgnoringVisibility");
        this.navigationBarsIgnoringVisibility = INSTANCE.valueInsetsIgnoringVisibility(insets, WindowInsetsCompat.Type.navigationBars(), "navigationBarsIgnoringVisibility");
        this.statusBarsIgnoringVisibility = INSTANCE.valueInsetsIgnoringVisibility(insets, WindowInsetsCompat.Type.statusBars(), "statusBarsIgnoringVisibility");
        this.systemBarsIgnoringVisibility = INSTANCE.valueInsetsIgnoringVisibility(insets, WindowInsetsCompat.Type.systemBars(), "systemBarsIgnoringVisibility");
        this.tappableElementIgnoringVisibility = INSTANCE.valueInsetsIgnoringVisibility(insets, WindowInsetsCompat.Type.tappableElement(), "tappableElementIgnoringVisibility");
        this.imeAnimationTarget = WindowInsets_androidKt.ValueInsets(Insets.NONE, "imeAnimationTarget");
        this.imeAnimationSource = WindowInsets_androidKt.ValueInsets(Insets.NONE, "imeAnimationSource");
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        Object tag = view2 != null ? view2.getTag(androidx.compose.ui.R.id.consume_window_insets_tag) : null;
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        this.consumes = bool != null ? bool.booleanValue() : false;
        this.insetsListener = new InsetsListener(this);
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
        if (rootWindowInsets == null) {
            return;
        }
        this.captionBar.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.captionBar()));
        this.displayCutout.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.displayCutout()));
        this.ime.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.ime()));
        this.mandatorySystemGestures.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.mandatorySystemGestures()));
        this.navigationBars.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.navigationBars()));
        this.statusBars.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.statusBars()));
        this.systemBars.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.systemBars()));
        this.systemGestures.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.systemGestures()));
        this.tappableElement.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.tappableElement()));
    }

    public final AndroidWindowInsets getCaptionBar() {
        return this.captionBar;
    }

    public final AndroidWindowInsets getDisplayCutout() {
        return this.displayCutout;
    }

    public final AndroidWindowInsets getIme() {
        return this.ime;
    }

    public final AndroidWindowInsets getMandatorySystemGestures() {
        return this.mandatorySystemGestures;
    }

    public final AndroidWindowInsets getNavigationBars() {
        return this.navigationBars;
    }

    public final AndroidWindowInsets getStatusBars() {
        return this.statusBars;
    }

    public final AndroidWindowInsets getSystemBars() {
        return this.systemBars;
    }

    public final AndroidWindowInsets getSystemGestures() {
        return this.systemGestures;
    }

    public final AndroidWindowInsets getTappableElement() {
        return this.tappableElement;
    }

    public final ValueInsets getWaterfall() {
        return this.waterfall;
    }

    private final void setCutoutPath(androidx.compose.ui.graphics.Path path) {
        MutableState $this$setValue$iv = this.cutoutPath;
        $this$setValue$iv.setValue(path);
    }

    public final androidx.compose.ui.graphics.Path getCutoutPath() {
        State $this$getValue$iv = this.cutoutPath;
        return (androidx.compose.ui.graphics.Path) $this$getValue$iv.getValue();
    }

    public final WindowInsets getSafeDrawing() {
        return this.safeDrawing;
    }

    public final WindowInsets getSafeGestures() {
        return this.safeGestures;
    }

    public final WindowInsets getSafeContent() {
        return this.safeContent;
    }

    public final ValueInsets getCaptionBarIgnoringVisibility() {
        return this.captionBarIgnoringVisibility;
    }

    public final ValueInsets getNavigationBarsIgnoringVisibility() {
        return this.navigationBarsIgnoringVisibility;
    }

    public final ValueInsets getStatusBarsIgnoringVisibility() {
        return this.statusBarsIgnoringVisibility;
    }

    public final ValueInsets getSystemBarsIgnoringVisibility() {
        return this.systemBarsIgnoringVisibility;
    }

    public final ValueInsets getTappableElementIgnoringVisibility() {
        return this.tappableElementIgnoringVisibility;
    }

    public final ValueInsets getImeAnimationTarget() {
        return this.imeAnimationTarget;
    }

    public final ValueInsets getImeAnimationSource() {
        return this.imeAnimationSource;
    }

    public final boolean getConsumes() {
        return this.consumes;
    }

    public final void incrementAccessors(View view) {
        if (this.accessCount == 0) {
            ViewCompat.setOnApplyWindowInsetsListener(view, this.insetsListener);
            if (view.isAttachedToWindow()) {
                view.requestApplyInsets();
            }
            view.addOnAttachStateChangeListener(this.insetsListener);
            ViewCompat.setWindowInsetsAnimationCallback(view, this.insetsListener);
        }
        this.accessCount++;
    }

    public final void decrementAccessors(View view) {
        this.accessCount--;
        if (this.accessCount == 0) {
            ViewCompat.setOnApplyWindowInsetsListener(view, null);
            ViewCompat.setWindowInsetsAnimationCallback(view, null);
            view.removeOnAttachStateChangeListener(this.insetsListener);
        }
    }

    public static /* synthetic */ void update$default(WindowInsetsHolder windowInsetsHolder, WindowInsetsCompat windowInsetsCompat, int i, int i2, Object obj) throws Throwable {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        windowInsetsHolder.update(windowInsetsCompat, i);
    }

    public final void update(WindowInsetsCompat windowInsets, int types) throws Throwable {
        WindowInsetsCompat insets;
        Insets waterfallInsets;
        Path cutoutPath;
        if (testInsets) {
            android.view.WindowInsets windowInsets2 = windowInsets.toWindowInsets();
            Intrinsics.checkNotNull(windowInsets2);
            insets = WindowInsetsCompat.toWindowInsetsCompat(windowInsets2);
        } else {
            insets = windowInsets;
        }
        this.captionBar.update$foundation_layout(insets, types);
        this.ime.update$foundation_layout(insets, types);
        this.displayCutout.update$foundation_layout(insets, types);
        this.navigationBars.update$foundation_layout(insets, types);
        this.statusBars.update$foundation_layout(insets, types);
        this.systemBars.update$foundation_layout(insets, types);
        this.systemGestures.update$foundation_layout(insets, types);
        this.tappableElement.update$foundation_layout(insets, types);
        this.mandatorySystemGestures.update$foundation_layout(insets, types);
        if (types == 0) {
            this.captionBarIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.captionBar())));
            this.navigationBarsIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.navigationBars())));
            this.statusBarsIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.statusBars())));
            this.systemBarsIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars())));
            this.tappableElementIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.tappableElement())));
            DisplayCutoutCompat cutout = insets.getDisplayCutout();
            ValueInsets valueInsets = this.waterfall;
            if (cutout == null || (waterfallInsets = cutout.getWaterfallInsets()) == null) {
                waterfallInsets = Insets.NONE;
            }
            valueInsets.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(waterfallInsets));
            setCutoutPath((cutout == null || (cutoutPath = cutout.getCutoutPath()) == null) ? null : AndroidPath_androidKt.asComposePath(cutoutPath));
        }
        Snapshot.INSTANCE.sendApplyNotifications();
    }

    public final void updateImeAnimationSource(WindowInsetsCompat windowInsets) {
        this.imeAnimationSource.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(windowInsets.getInsets(WindowInsetsCompat.Type.ime())));
    }

    public final void updateImeAnimationTarget(WindowInsetsCompat windowInsets) {
        this.imeAnimationTarget.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(windowInsets.getInsets(WindowInsetsCompat.Type.ime())));
    }

    /* JADX INFO: compiled from: WindowInsets.android.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0007J\r\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0006J\"\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\"\u0010\u0018\u001a\u00020\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsHolder$Companion;", "", "<init>", "()V", "viewMap", "Ljava/util/WeakHashMap;", "Landroid/view/View;", "Landroidx/compose/foundation/layout/WindowInsetsHolder;", "testInsets", "", "setUseTestInsets", "", "current", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsetsHolder;", "getOrCreateFor", "view", "systemInsets", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "windowInsets", "Landroidx/core/view/WindowInsetsCompat;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "", HintConstants.AUTOFILL_HINT_NAME, "", "valueInsetsIgnoringVisibility", "Landroidx/compose/foundation/layout/ValueInsets;", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void setUseTestInsets(boolean testInsets) {
            WindowInsetsHolder.testInsets = testInsets;
        }

        public final WindowInsetsHolder current(Composer $composer, int $changed) {
            ComposerKt.sourceInformationMarkerStart($composer, -1366542614, "C(current)575@23670L7,578@23762L125,578@23737L150:WindowInsets.android.kt#2w3rfo");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1366542614, $changed, -1, "androidx.compose.foundation.layout.WindowInsetsHolder.Companion.current (WindowInsets.android.kt:574)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer);
            final View view = (View) objConsume;
            final WindowInsetsHolder insets = getOrCreateFor(view);
            ComposerKt.sourceInformationMarkerStart($composer, 1012545799, "CC(remember):WindowInsets.android.kt#9igjgp");
            boolean invalid$iv = $composer.changedInstance(insets) | $composer.changedInstance(view);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsHolder$Companion$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WindowInsetsHolder.Companion.current$lambda$0$0(insets, view, (DisposableEffectScope) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.DisposableEffect(insets, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return insets;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final DisposableEffectResult current$lambda$0$0(final WindowInsetsHolder $insets, final View $view, DisposableEffectScope $this$DisposableEffect) {
            $insets.incrementAccessors($view);
            return new DisposableEffectResult() { // from class: androidx.compose.foundation.layout.WindowInsetsHolder$Companion$current$lambda$0$0$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    $insets.decrementAccessors($view);
                }
            };
        }

        public final WindowInsetsHolder getOrCreateFor(View view) {
            Object answer$iv;
            WindowInsetsHolder windowInsetsHolder;
            synchronized (WindowInsetsHolder.viewMap) {
                Map $this$getOrPut$iv = WindowInsetsHolder.viewMap;
                Object value$iv = $this$getOrPut$iv.get(view);
                if (value$iv == null) {
                    answer$iv = new WindowInsetsHolder(null, view, null);
                    $this$getOrPut$iv.put(view, answer$iv);
                } else {
                    answer$iv = value$iv;
                }
                windowInsetsHolder = (WindowInsetsHolder) answer$iv;
            }
            return windowInsetsHolder;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final AndroidWindowInsets systemInsets(WindowInsetsCompat windowInsets, int type, String name) {
            AndroidWindowInsets $this$systemInsets_u24lambda_u240 = new AndroidWindowInsets(type, name);
            if (windowInsets != null) {
                $this$systemInsets_u24lambda_u240.update$foundation_layout(windowInsets, type);
            }
            return $this$systemInsets_u24lambda_u240;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ValueInsets valueInsetsIgnoringVisibility(WindowInsetsCompat windowInsets, int type, String name) {
            Insets initial;
            if (windowInsets == null || (initial = windowInsets.getInsetsIgnoringVisibility(type)) == null) {
                initial = Insets.NONE;
            }
            return WindowInsets_androidKt.ValueInsets(initial, name);
        }
    }
}
