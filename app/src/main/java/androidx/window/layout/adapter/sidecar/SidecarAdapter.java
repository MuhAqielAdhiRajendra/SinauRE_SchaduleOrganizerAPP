package androidx.window.layout.adapter.sidecar;

import android.graphics.Rect;
import androidx.window.core.Bounds;
import androidx.window.core.SpecificationComputer;
import androidx.window.core.VerificationMode;
import androidx.window.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.HardwareFoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarDisplayFeature;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SidecarAdapter.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u0006\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\fJ\u001a\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\fJ\u001a\u0010\u0015\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000fJ(\u0010\u0016\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00072\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007H\u0002J\u001c\u0010\u0017\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\nH\u0002J\u001f\u0010\u0006\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarAdapter;", "", "verificationMode", "Landroidx/window/core/VerificationMode;", "<init>", "(Landroidx/window/core/VerificationMode;)V", "translate", "", "Landroidx/window/layout/DisplayFeature;", "sidecarDisplayFeatures", "Landroidx/window/sidecar/SidecarDisplayFeature;", "deviceState", "Landroidx/window/sidecar/SidecarDeviceState;", "Landroidx/window/layout/WindowLayoutInfo;", "extensionInfo", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "state", "isEqualSidecarDeviceState", "", "first", "second", "isEqualSidecarWindowLayoutInfo", "isEqualSidecarDisplayFeatures", "isEqualSidecarDisplayFeature", "feature", "translate$window_release", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SidecarAdapter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = SidecarAdapter.class.getSimpleName();
    private final VerificationMode verificationMode;

    public SidecarAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public SidecarAdapter(VerificationMode verificationMode) {
        Intrinsics.checkNotNullParameter(verificationMode, "verificationMode");
        this.verificationMode = verificationMode;
    }

    public /* synthetic */ SidecarAdapter(VerificationMode verificationMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? VerificationMode.QUIET : verificationMode);
    }

    public final List<DisplayFeature> translate(List<SidecarDisplayFeature> sidecarDisplayFeatures, SidecarDeviceState deviceState) {
        Intrinsics.checkNotNullParameter(sidecarDisplayFeatures, "sidecarDisplayFeatures");
        Intrinsics.checkNotNullParameter(deviceState, "deviceState");
        List<SidecarDisplayFeature> $this$mapNotNull$iv = sidecarDisplayFeatures;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            SidecarDisplayFeature sidecarFeature = (SidecarDisplayFeature) element$iv$iv$iv;
            DisplayFeature displayFeatureTranslate$window_release = translate$window_release(sidecarFeature, deviceState);
            if (displayFeatureTranslate$window_release != null) {
                destination$iv$iv.add(displayFeatureTranslate$window_release);
            }
        }
        return (List) destination$iv$iv;
    }

    public final WindowLayoutInfo translate(SidecarWindowLayoutInfo extensionInfo, SidecarDeviceState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (extensionInfo == null) {
            return new WindowLayoutInfo(CollectionsKt.emptyList());
        }
        SidecarDeviceState deviceState = new SidecarDeviceState();
        int devicePosture = INSTANCE.getSidecarDevicePosture$window_release(state);
        INSTANCE.setSidecarDevicePosture(deviceState, devicePosture);
        return new WindowLayoutInfo(translate(INSTANCE.getSidecarDisplayFeatures(extensionInfo), deviceState));
    }

    public final boolean isEqualSidecarDeviceState(SidecarDeviceState first, SidecarDeviceState second) {
        if (Intrinsics.areEqual(first, second)) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        int firstPosture = INSTANCE.getSidecarDevicePosture$window_release(first);
        int secondPosture = INSTANCE.getSidecarDevicePosture$window_release(second);
        return firstPosture == secondPosture;
    }

    public final boolean isEqualSidecarWindowLayoutInfo(SidecarWindowLayoutInfo first, SidecarWindowLayoutInfo second) {
        if (Intrinsics.areEqual(first, second)) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        return isEqualSidecarDisplayFeatures(INSTANCE.getSidecarDisplayFeatures(first), INSTANCE.getSidecarDisplayFeatures(second));
    }

    private final boolean isEqualSidecarDisplayFeatures(List<SidecarDisplayFeature> first, List<SidecarDisplayFeature> second) {
        if (first == second) {
            return true;
        }
        if (first == null || second == null || first.size() != second.size()) {
            return false;
        }
        int size = first.size();
        for (int i = 0; i < size; i++) {
            SidecarDisplayFeature firstFeature = first.get(i);
            SidecarDisplayFeature secondFeature = second.get(i);
            if (!isEqualSidecarDisplayFeature(firstFeature, secondFeature)) {
                return false;
            }
        }
        return true;
    }

    private final boolean isEqualSidecarDisplayFeature(SidecarDisplayFeature first, SidecarDisplayFeature second) {
        if (Intrinsics.areEqual(first, second)) {
            return true;
        }
        return first != null && second != null && first.getType() == second.getType() && Intrinsics.areEqual(first.getRect(), second.getRect());
    }

    public final DisplayFeature translate$window_release(SidecarDisplayFeature feature, SidecarDeviceState deviceState) {
        HardwareFoldingFeature.Type type;
        FoldingFeature.State state;
        Intrinsics.checkNotNullParameter(feature, "feature");
        Intrinsics.checkNotNullParameter(deviceState, "deviceState");
        SpecificationComputer.Companion companion = SpecificationComputer.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        SidecarDisplayFeature checkedFeature = (SidecarDisplayFeature) SpecificationComputer.Companion.startSpecification$default(companion, feature, TAG2, this.verificationMode, null, 4, null).require("Type must be either TYPE_FOLD or TYPE_HINGE", new Function1() { // from class: androidx.window.layout.adapter.sidecar.SidecarAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SidecarAdapter.translate$lambda$1((SidecarDisplayFeature) obj));
            }
        }).require("Feature bounds must not be 0", new Function1() { // from class: androidx.window.layout.adapter.sidecar.SidecarAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SidecarAdapter.translate$lambda$2((SidecarDisplayFeature) obj));
            }
        }).require("TYPE_FOLD must have 0 area", new Function1() { // from class: androidx.window.layout.adapter.sidecar.SidecarAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SidecarAdapter.translate$lambda$3((SidecarDisplayFeature) obj));
            }
        }).require("Feature be pinned to either left or top", new Function1() { // from class: androidx.window.layout.adapter.sidecar.SidecarAdapter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SidecarAdapter.translate$lambda$4((SidecarDisplayFeature) obj));
            }
        }).compute();
        if (checkedFeature == null) {
            return null;
        }
        switch (checkedFeature.getType()) {
            case 1:
                type = HardwareFoldingFeature.Type.INSTANCE.getFOLD();
                break;
            case 2:
                type = HardwareFoldingFeature.Type.INSTANCE.getHINGE();
                break;
            default:
                return null;
        }
        switch (INSTANCE.getSidecarDevicePosture$window_release(deviceState)) {
            case 0:
            case 1:
            case 4:
                return null;
            case 2:
                state = FoldingFeature.State.HALF_OPENED;
                break;
            case 3:
                state = FoldingFeature.State.FLAT;
                break;
            default:
                state = FoldingFeature.State.FLAT;
                break;
        }
        Rect rect = feature.getRect();
        Intrinsics.checkNotNullExpressionValue(rect, "getRect(...)");
        return new HardwareFoldingFeature(new Bounds(rect), type, state);
    }

    static final boolean translate$lambda$1(SidecarDisplayFeature require) {
        Intrinsics.checkNotNullParameter(require, "$this$require");
        return require.getType() == 1 || require.getType() == 2;
    }

    static final boolean translate$lambda$2(SidecarDisplayFeature require) {
        Intrinsics.checkNotNullParameter(require, "$this$require");
        return (require.getRect().width() == 0 && require.getRect().height() == 0) ? false : true;
    }

    static final boolean translate$lambda$3(SidecarDisplayFeature require) {
        Intrinsics.checkNotNullParameter(require, "$this$require");
        return require.getType() != 1 || require.getRect().width() == 0 || require.getRect().height() == 0;
    }

    static final boolean translate$lambda$4(SidecarDisplayFeature require) {
        Intrinsics.checkNotNullParameter(require, "$this$require");
        return require.getRect().left == 0 || require.getRect().top == 0;
    }

    /* JADX INFO: compiled from: SidecarAdapter.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0007J\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0010H\u0007R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarAdapter$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getSidecarDisplayFeatures", "", "Landroidx/window/sidecar/SidecarDisplayFeature;", "info", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "setSidecarDisplayFeatures", "", "displayFeatures", "getSidecarDevicePosture", "", "sidecarDeviceState", "Landroidx/window/sidecar/SidecarDeviceState;", "getSidecarDevicePosture$window_release", "getRawSidecarDevicePosture", "setSidecarDevicePosture", "posture", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<SidecarDisplayFeature> getSidecarDisplayFeatures(SidecarWindowLayoutInfo info) {
            Intrinsics.checkNotNullParameter(info, "info");
            try {
                List<SidecarDisplayFeature> list = info.displayFeatures;
                return list == null ? CollectionsKt.emptyList() : list;
            } catch (NoSuchFieldError e) {
                try {
                    Method methodGetFeatures = SidecarWindowLayoutInfo.class.getMethod("getDisplayFeatures", new Class[0]);
                    Object objInvoke = methodGetFeatures.invoke(info, new Object[0]);
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.collections.List<androidx.window.sidecar.SidecarDisplayFeature>");
                    return (List) objInvoke;
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                    return CollectionsKt.emptyList();
                }
            }
        }

        public final void setSidecarDisplayFeatures(SidecarWindowLayoutInfo info, List<SidecarDisplayFeature> displayFeatures) {
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(displayFeatures, "displayFeatures");
            try {
                info.displayFeatures = displayFeatures;
            } catch (NoSuchFieldError e) {
                try {
                    Method methodSetFeatures = SidecarWindowLayoutInfo.class.getMethod("setDisplayFeatures", List.class);
                    methodSetFeatures.invoke(info, displayFeatures);
                } catch (IllegalAccessException e2) {
                    Unit unit = Unit.INSTANCE;
                } catch (NoSuchMethodException e3) {
                    Unit unit2 = Unit.INSTANCE;
                } catch (InvocationTargetException e4) {
                    Unit unit3 = Unit.INSTANCE;
                }
            }
        }

        public final int getSidecarDevicePosture$window_release(SidecarDeviceState sidecarDeviceState) {
            Intrinsics.checkNotNullParameter(sidecarDeviceState, "sidecarDeviceState");
            int rawPosture = getRawSidecarDevicePosture(sidecarDeviceState);
            if (rawPosture < 0 || rawPosture > 4) {
                return 0;
            }
            return rawPosture;
        }

        public final int getRawSidecarDevicePosture(SidecarDeviceState sidecarDeviceState) {
            Intrinsics.checkNotNullParameter(sidecarDeviceState, "sidecarDeviceState");
            try {
                return sidecarDeviceState.posture;
            } catch (NoSuchFieldError e) {
                try {
                    Method methodGetPosture = SidecarDeviceState.class.getMethod("getPosture", new Class[0]);
                    Object objInvoke = methodGetPosture.invoke(sidecarDeviceState, new Object[0]);
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.Int");
                    return ((Integer) objInvoke).intValue();
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                    return 0;
                }
            }
        }

        public final void setSidecarDevicePosture(SidecarDeviceState sidecarDeviceState, int posture) {
            Intrinsics.checkNotNullParameter(sidecarDeviceState, "sidecarDeviceState");
            try {
                sidecarDeviceState.posture = posture;
            } catch (NoSuchFieldError e) {
                try {
                    Method methodSetPosture = SidecarDeviceState.class.getMethod("setPosture", Integer.TYPE);
                    methodSetPosture.invoke(sidecarDeviceState, Integer.valueOf(posture));
                } catch (IllegalAccessException e2) {
                    Unit unit = Unit.INSTANCE;
                } catch (NoSuchMethodException e3) {
                    Unit unit2 = Unit.INSTANCE;
                } catch (InvocationTargetException e4) {
                    Unit unit3 = Unit.INSTANCE;
                }
            }
        }
    }
}
