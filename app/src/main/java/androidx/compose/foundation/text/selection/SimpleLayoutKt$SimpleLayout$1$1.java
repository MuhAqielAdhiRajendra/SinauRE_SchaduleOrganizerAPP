package androidx.compose.foundation.text.selection;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SimpleLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
final class SimpleLayoutKt$SimpleLayout$1$1 implements MeasurePolicy {
    public static final SimpleLayoutKt$SimpleLayout$1$1 INSTANCE = new SimpleLayoutKt$SimpleLayout$1$1();

    SimpleLayoutKt$SimpleLayout$1$1() {
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo39measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
        List target$iv = new ArrayList(list.size());
        int size = list.size();
        int width = 0;
        int height = 0;
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable measurable = (Measurable) item$iv$iv;
            Placeable placeable = measurable.mo6783measureBRTryo0(constraints);
            width = Math.max(width, placeable.getWidth());
            height = Math.max(height, placeable.getHeight());
            target$iv.add(placeable);
        }
        final List placeables = target$iv;
        return MeasureScope.layout$default($this$Layout, width, height, null, new Function1() { // from class: androidx.compose.foundation.text.selection.SimpleLayoutKt$SimpleLayout$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SimpleLayoutKt$SimpleLayout$1$1.measure_3p2s80s$lambda$1(placeables, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$1(List $placeables, Placeable.PlacementScope $this$layout) {
        int size = $placeables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $placeables.get(index$iv);
            Placeable placeable = (Placeable) item$iv;
            Placeable.PlacementScope.place$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
