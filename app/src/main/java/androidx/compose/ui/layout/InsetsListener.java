package androidx.compose.ui.layout;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.collection.IntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.core.graphics.Insets;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowInsetsRulers.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0016J\u0018\u0010*\u001a\u00020+2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020+H\u0016J\u0018\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020\u00142\u0006\u0010(\u001a\u00020)H\u0002J\u001e\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u00102\f\u00101\u001a\b\u0012\u0004\u0012\u00020)02H\u0016J\u0010\u00103\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u00104\u001a\u00020'2\u0006\u0010.\u001a\u00020\u0014H\u0002J\u0018\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u0002072\u0006\u00100\u001a\u00020\u0010H\u0016J\u0010\u00108\u001a\u00020'2\u0006\u00100\u001a\u00020\u0010H\u0002J\b\u00109\u001a\u00020'H\u0016J\u0010\u0010:\u001a\u00020'2\u0006\u00106\u001a\u000207H\u0016J\u0010\u0010;\u001a\u00020'2\u0006\u00106\u001a\u000207H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006<"}, d2 = {"Landroidx/compose/ui/layout/InsetsListener;", "Landroidx/core/view/WindowInsetsAnimationCompat$Callback;", "Ljava/lang/Runnable;", "Landroidx/core/view/OnApplyWindowInsetsListener;", "Landroid/view/View$OnAttachStateChangeListener;", "composeView", "Landroidx/compose/ui/platform/AndroidComposeView;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "getComposeView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "prepared", "", "runningAnimationMask", "", "savedInsets", "Landroidx/core/view/WindowInsetsCompat;", "insetsValues", "Landroidx/collection/ScatterMap;", "", "Landroidx/compose/ui/layout/WindowWindowInsetsAnimationValues;", "getInsetsValues", "()Landroidx/collection/ScatterMap;", "generation", "Landroidx/compose/runtime/MutableIntState;", "getGeneration", "()Landroidx/compose/runtime/MutableIntState;", "displayCutouts", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/runtime/MutableState;", "Landroid/graphics/Rect;", "getDisplayCutouts", "()Landroidx/collection/MutableObjectList;", "displayCutoutRulers", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Landroidx/compose/ui/layout/RectRulers;", "getDisplayCutoutRulers", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", "onPrepare", "", "animation", "Landroidx/core/view/WindowInsetsAnimationCompat;", "onStart", "Landroidx/core/view/WindowInsetsAnimationCompat$BoundsCompat;", "bounds", "updateInsetAnimationInfo", "insetsValue", "onProgress", "insets", "runningAnimations", "", "onEnd", "stopAnimationForRuler", "onApplyWindowInsets", "view", "Landroid/view/View;", "updateInsets", "run", "onViewAttachedToWindow", "onViewDetachedFromWindow", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InsetsListener extends WindowInsetsAnimationCompat.Callback implements Runnable, OnApplyWindowInsetsListener, View.OnAttachStateChangeListener {
    public static final int $stable = 8;
    private final AndroidComposeView composeView;
    private final SnapshotStateList<RectRulers> displayCutoutRulers;
    private final MutableObjectList<MutableState<Rect>> displayCutouts;
    private final MutableIntState generation;
    private final ScatterMap<Object, WindowWindowInsetsAnimationValues> insetsValues;
    private boolean prepared;
    private int runningAnimationMask;
    private WindowInsetsCompat savedInsets;

    public InsetsListener(AndroidComposeView composeView) {
        super(1);
        this.composeView = composeView;
        MutableScatterMap it = new MutableScatterMap(9);
        it.set(WindowInsetsRulers.INSTANCE.getCaptionBar(), new WindowWindowInsetsAnimationValues("caption bar"));
        it.set(WindowInsetsRulers.INSTANCE.getDisplayCutout(), new WindowWindowInsetsAnimationValues("display cutout"));
        it.set(WindowInsetsRulers.INSTANCE.getIme(), new WindowWindowInsetsAnimationValues("ime"));
        it.set(WindowInsetsRulers.INSTANCE.getMandatorySystemGestures(), new WindowWindowInsetsAnimationValues("mandatory system gestures"));
        it.set(WindowInsetsRulers.INSTANCE.getNavigationBars(), new WindowWindowInsetsAnimationValues("navigation bars"));
        it.set(WindowInsetsRulers.INSTANCE.getStatusBars(), new WindowWindowInsetsAnimationValues("status bars"));
        it.set(WindowInsetsRulers.INSTANCE.getSystemGestures(), new WindowWindowInsetsAnimationValues("system gestures"));
        it.set(WindowInsetsRulers.INSTANCE.getTappableElement(), new WindowWindowInsetsAnimationValues("tappable element"));
        it.set(WindowInsetsRulers.INSTANCE.getWaterfall(), new WindowWindowInsetsAnimationValues("waterfall"));
        this.insetsValues = it;
        this.generation = SnapshotIntStateKt.mutableIntStateOf(0);
        this.displayCutouts = new MutableObjectList<>(4);
        this.displayCutoutRulers = SnapshotStateKt.mutableStateListOf();
    }

    public final AndroidComposeView getComposeView() {
        return this.composeView;
    }

    public final ScatterMap<Object, WindowWindowInsetsAnimationValues> getInsetsValues() {
        return this.insetsValues;
    }

    public final MutableIntState getGeneration() {
        return this.generation;
    }

    public final MutableObjectList<MutableState<Rect>> getDisplayCutouts() {
        return this.displayCutouts;
    }

    public final SnapshotStateList<RectRulers> getDisplayCutoutRulers() {
        return this.displayCutoutRulers;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void onPrepare(WindowInsetsAnimationCompat animation) {
        this.prepared = true;
        super.onPrepare(animation);
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat animation, WindowInsetsAnimationCompat.BoundsCompat bounds) throws Throwable {
        WindowInsetsCompat insets = this.savedInsets;
        this.prepared = false;
        this.savedInsets = null;
        if (animation.getDurationMillis() > 0 && insets != null) {
            int type = animation.getTypeMask();
            this.runningAnimationMask |= type;
            WindowInsetsRulers rulers = (WindowInsetsRulers) WindowInsetsRulers_androidKt.WindowInsetsTypeMap.get(type);
            if (rulers != null) {
                WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = this.insetsValues.get(rulers);
                Intrinsics.checkNotNull(windowWindowInsetsAnimationValues);
                WindowWindowInsetsAnimationValues insetsValue = windowWindowInsetsAnimationValues;
                Insets insets$iv = insets.getInsets(type);
                long target = ValueInsets.m6917constructorimpl((((long) insets$iv.left) << 48) | (((long) insets$iv.top) << 32) | (((long) insets$iv.right) << 16) | ((long) insets$iv.bottom));
                long current = insetsValue.getCurrent();
                if (!ValueInsets.m6919equalsimpl0(target, current)) {
                    insetsValue.m6934setSourceValueInsetsYnlvx88(current);
                    insetsValue.m6935setTargetValueInsetsYnlvx88(target);
                    insetsValue.setAnimating(true);
                    updateInsetAnimationInfo(insetsValue, animation);
                    MutableIntState mutableIntState = this.generation;
                    mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
                    Snapshot.INSTANCE.sendApplyNotifications();
                }
            }
        }
        return super.onStart(animation, bounds);
    }

    private final void updateInsetAnimationInfo(WindowWindowInsetsAnimationValues insetsValue, WindowInsetsAnimationCompat animation) {
        insetsValue.setFraction(animation.getInterpolatedFraction());
        insetsValue.setAlpha(animation.getAlpha());
        insetsValue.setDurationMillis(animation.getDurationMillis());
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsCompat onProgress(WindowInsetsCompat insets, List<WindowInsetsAnimationCompat> runningAnimations) throws Throwable {
        int size = runningAnimations.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = runningAnimations.get(index$iv);
            WindowInsetsAnimationCompat animation = (WindowInsetsAnimationCompat) item$iv;
            int typeMask = animation.getTypeMask();
            WindowInsetsRulers rulers = (WindowInsetsRulers) WindowInsetsRulers_androidKt.WindowInsetsTypeMap.get(typeMask);
            if (rulers != null) {
                WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = this.insetsValues.get(rulers);
                Intrinsics.checkNotNull(windowWindowInsetsAnimationValues);
                WindowWindowInsetsAnimationValues insetsValue = windowWindowInsetsAnimationValues;
                if (insetsValue.isAnimating()) {
                    updateInsetAnimationInfo(insetsValue, animation);
                }
            }
        }
        updateInsets(insets);
        return insets;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void onEnd(WindowInsetsAnimationCompat animation) throws Throwable {
        this.prepared = false;
        int type = animation.getTypeMask();
        this.runningAnimationMask &= ~type;
        this.savedInsets = null;
        WindowInsetsRulers rulers = (WindowInsetsRulers) WindowInsetsRulers_androidKt.WindowInsetsTypeMap.get(type);
        if (rulers != null) {
            WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = this.insetsValues.get(rulers);
            Intrinsics.checkNotNull(windowWindowInsetsAnimationValues);
            WindowWindowInsetsAnimationValues insetsValue = windowWindowInsetsAnimationValues;
            insetsValue.setFraction(0.0f);
            insetsValue.setAlpha(1.0f);
            insetsValue.setDurationMillis(0L);
            insetsValue.setFraction(0.0f);
            stopAnimationForRuler(insetsValue);
            MutableIntState mutableIntState = this.generation;
            mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
            Snapshot.INSTANCE.sendApplyNotifications();
        }
        super.onEnd(animation);
    }

    private final void stopAnimationForRuler(WindowWindowInsetsAnimationValues insetsValue) {
        insetsValue.setAnimating(false);
        insetsValue.m6934setSourceValueInsetsYnlvx88(ValueInsets_androidKt.getUnsetValueInsets());
        insetsValue.m6935setTargetValueInsetsYnlvx88(ValueInsets_androidKt.getUnsetValueInsets());
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets) throws Throwable {
        if (this.prepared) {
            this.savedInsets = insets;
            if (Build.VERSION.SDK_INT == 30) {
                view.post(this);
            }
        } else if (this.runningAnimationMask == 0) {
            updateInsets(insets);
        }
        return insets;
    }

    private final void updateInsets(WindowInsetsCompat insets) throws Throwable {
        char c;
        long waterfall;
        IntObjectMap this_$iv;
        int $i$f$forEach;
        int[] k$iv;
        Object[] v$iv;
        IntObjectMap this_$iv2;
        int $i$f$forEach2;
        int[] k$iv2;
        Object[] v$iv2;
        int i;
        boolean changed = false;
        boolean hasInsets = false;
        IntObjectMap this_$iv3 = WindowInsetsRulers_androidKt.WindowInsetsTypeMap;
        int $i$f$forEach3 = 0;
        int[] k$iv3 = this_$iv3.keys;
        Object[] v$iv3 = this_$iv3.values;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                c = 16;
                int i$iv$iv2 = i$iv$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv & 255;
                        int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                        if ($i$f$isFull != 0) {
                            int index$iv$iv = (i$iv$iv2 << 3) + j$iv$iv;
                            int type = k$iv3[index$iv$iv];
                            i = i2;
                            WindowInsetsRulers rulers = (WindowInsetsRulers) v$iv3[index$iv$iv];
                            boolean changed2 = changed;
                            boolean hasInsets2 = hasInsets;
                            Insets insets$iv = insets.getInsets(type);
                            this_$iv2 = this_$iv3;
                            $i$f$forEach2 = $i$f$forEach3;
                            long insetsValue = ValueInsets.m6917constructorimpl((((long) insets$iv.left) << 48) | (((long) insets$iv.top) << 32) | (((long) insets$iv.right) << 16) | ((long) insets$iv.bottom));
                            WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = this.insetsValues.get(rulers);
                            Intrinsics.checkNotNull(windowWindowInsetsAnimationValues);
                            WindowWindowInsetsAnimationValues values = windowWindowInsetsAnimationValues;
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                            if (!ValueInsets.m6919equalsimpl0(insetsValue, values.getCurrent())) {
                                values.m6932setCurrentYnlvx88(insetsValue);
                                changed2 = true;
                                if (!ValueInsets.m6919equalsimpl0(insetsValue, ValueInsets_androidKt.getZeroValueInsets())) {
                                    hasInsets2 = true;
                                }
                            }
                            if (type != WindowInsetsCompat.Type.ime()) {
                                Insets insets$iv2 = insets.getInsetsIgnoringVisibility(type);
                                long insetsValue2 = ValueInsets.m6917constructorimpl((((long) insets$iv2.left) << 48) | (((long) insets$iv2.top) << 32) | (((long) insets$iv2.right) << 16) | ((long) insets$iv2.bottom));
                                if (!ValueInsets.m6919equalsimpl0(values.getMaximum(), insetsValue2)) {
                                    values.m6933setMaximumYnlvx88(insetsValue2);
                                    changed2 = true;
                                    if (!ValueInsets.m6919equalsimpl0(insetsValue2, ValueInsets_androidKt.getZeroValueInsets())) {
                                        hasInsets2 = true;
                                    }
                                }
                            }
                            values.setVisible(insets.isVisible(type));
                            hasInsets = hasInsets2;
                            changed = changed2;
                        } else {
                            this_$iv2 = this_$iv3;
                            $i$f$forEach2 = $i$f$forEach3;
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                            i = i2;
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        k$iv3 = k$iv2;
                        this_$iv3 = this_$iv2;
                        $i$f$forEach3 = $i$f$forEach2;
                        v$iv3 = v$iv2;
                    }
                    boolean changed3 = changed;
                    this_$iv = this_$iv3;
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    if (bitCount$iv$iv != i2) {
                        changed = changed3;
                        break;
                    }
                    changed = changed3;
                } else {
                    this_$iv = this_$iv3;
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                }
                if (i$iv$iv2 == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv = i$iv$iv2 + 1;
                k$iv3 = k$iv;
                this_$iv3 = this_$iv;
                $i$f$forEach3 = $i$f$forEach;
                v$iv3 = v$iv;
            }
        } else {
            c = 16;
        }
        DisplayCutoutCompat cutout = insets.getDisplayCutout();
        if (cutout == null) {
            waterfall = ValueInsets_androidKt.getZeroValueInsets();
        } else {
            Insets insets$iv3 = cutout.getWaterfallInsets();
            waterfall = ValueInsets.m6917constructorimpl((((long) insets$iv3.left) << 48) | (((long) insets$iv3.top) << 32) | (((long) insets$iv3.right) << c) | ((long) insets$iv3.bottom));
        }
        WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues2 = this.insetsValues.get(WindowInsetsRulers.INSTANCE.getWaterfall());
        Intrinsics.checkNotNull(windowWindowInsetsAnimationValues2);
        WindowWindowInsetsAnimationValues waterfallInsets = windowWindowInsetsAnimationValues2;
        waterfallInsets.setVisible(!ValueInsets.m6919equalsimpl0(waterfall, ValueInsets_androidKt.getZeroValueInsets()));
        if (!ValueInsets.m6919equalsimpl0(waterfallInsets.getCurrent(), waterfall)) {
            waterfallInsets.m6932setCurrentYnlvx88(waterfall);
            waterfallInsets.m6933setMaximumYnlvx88(waterfall);
            changed = true;
            if (!ValueInsets.m6919equalsimpl0(waterfall, ValueInsets_androidKt.getZeroValueInsets())) {
                hasInsets = true;
            }
        }
        if (cutout != null) {
            List<Rect> boundingRects = cutout.getBoundingRects();
            if (boundingRects.size() < this.displayCutouts.getSize()) {
                this.displayCutouts.removeRange(boundingRects.size(), this.displayCutouts.getSize());
                this.displayCutoutRulers.removeRange(boundingRects.size(), this.displayCutoutRulers.size());
                changed = true;
            } else {
                int size = boundingRects.size() - this.displayCutouts.getSize();
                for (int i3 = 0; i3 < size; i3++) {
                    this.displayCutouts.add(SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(boundingRects.get(this.displayCutouts.getSize()), null, 2, null));
                    this.displayCutoutRulers.add(RectRulersKt.RectRulers("display cutout rect " + this.displayCutouts.getSize()));
                    changed = true;
                }
            }
            List<Rect> list = boundingRects;
            int index$iv = 0;
            int size2 = list.size();
            while (index$iv < size2) {
                Object item$iv = list.get(index$iv);
                Rect rect = (Rect) item$iv;
                int index = index$iv;
                List<Rect> list2 = list;
                MutableState<Rect> mutableState = this.displayCutouts.get(index);
                boolean changed4 = changed;
                if (Intrinsics.areEqual(mutableState.getValue(), rect)) {
                    changed = changed4;
                } else {
                    mutableState.setValue(rect);
                    changed = true;
                }
                index$iv++;
                list = list2;
            }
            boolean changed5 = changed;
            if (boundingRects.isEmpty()) {
                changed = changed5;
            } else {
                hasInsets = true;
                changed = changed5;
            }
        } else if (this.displayCutouts.getSize() > 0) {
            this.displayCutouts.clear();
            this.displayCutoutRulers.clear();
            changed = true;
        }
        if ((hasInsets || this.generation.getIntValue() != 0) && changed) {
            MutableIntState mutableIntState = this.generation;
            mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
            Snapshot.INSTANCE.sendApplyNotifications();
        }
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        if (this.prepared) {
            this.runningAnimationMask = 0;
            this.prepared = false;
            WindowInsetsCompat it = this.savedInsets;
            if (it != null) {
                updateInsets(it);
                this.savedInsets = null;
            }
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        Object parent = view.getParent();
        View listenerView = parent instanceof View ? (View) parent : null;
        if (listenerView == null) {
            listenerView = view;
        }
        ViewCompat.setOnApplyWindowInsetsListener(listenerView, this);
        ViewCompat.setWindowInsetsAnimationCallback(listenerView, this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        Object parent = view.getParent();
        View listenerView = parent instanceof View ? (View) parent : null;
        if (listenerView == null) {
            listenerView = view;
        }
        ViewCompat.setOnApplyWindowInsetsListener(listenerView, null);
        ViewCompat.setWindowInsetsAnimationCallback(listenerView, null);
    }
}
