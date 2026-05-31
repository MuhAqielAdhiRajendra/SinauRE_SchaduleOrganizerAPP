package androidx.compose.animation;

import androidx.compose.animation.AnimatedContentTransitionScopeImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: AnimatedContent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\b\u001a\u00020\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\"\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J\"\u0010\u0017\u001a\u00020\u0013*\u00020\u00142\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\u0006\u0010\u0018\u001a\u00020\u0013H\u0016J\"\u0010\u0019\u001a\u00020\u0013*\u00020\u00142\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J\"\u0010\u001a\u001a\u00020\u0013*\u00020\u00142\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\u0006\u0010\u0018\u001a\u00020\u0013H\u0016R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001b"}, d2 = {"Landroidx/compose/animation/AnimatedContentMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "rootScope", "Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;", "<init>", "(Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;)V", "getRootScope", "()Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class AnimatedContentMeasurePolicy implements MeasurePolicy {
    private final AnimatedContentTransitionScopeImpl<?> rootScope;

    public AnimatedContentMeasurePolicy(AnimatedContentTransitionScopeImpl<?> animatedContentTransitionScopeImpl) {
        this.rootScope = animatedContentTransitionScopeImpl;
    }

    public final AnimatedContentTransitionScopeImpl<?> getRootScope() {
        return this.rootScope;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult mo39measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        Placeable placeable;
        final int maxWidth;
        Placeable placeable2;
        int height;
        List<? extends Measurable> list2;
        final Placeable[] placeables = new Placeable[list.size()];
        long targetSize = IntSize.INSTANCE.m8326getZeroYbymL2g();
        List<? extends Measurable> list3 = list;
        int index$iv = 0;
        int size = list3.size();
        while (true) {
            if (index$iv >= size) {
                break;
            }
            Object item$iv = list3.get(index$iv);
            Measurable measurable = (Measurable) item$iv;
            int index = index$iv;
            Object parentData = measurable.getParentData();
            AnimatedContentTransitionScopeImpl.ChildData childData = parentData instanceof AnimatedContentTransitionScopeImpl.ChildData ? (AnimatedContentTransitionScopeImpl.ChildData) parentData : null;
            if (childData != null && childData.isTarget()) {
                Placeable it = measurable.mo6783measureBRTryo0(constraints);
                int width$iv = it.getWidth();
                int height$iv = it.getHeight();
                list2 = list3;
                targetSize = IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
                Unit unit = Unit.INSTANCE;
                placeables[index] = it;
            } else {
                list2 = list3;
            }
            index$iv++;
            list3 = list2;
        }
        long targetSize2 = targetSize;
        int size2 = list.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = list.get(index$iv2);
            Measurable measurable2 = (Measurable) item$iv2;
            int index2 = index$iv2;
            if (placeables[index2] == null) {
                placeables[index2] = measurable2.mo6783measureBRTryo0(constraints);
            }
        }
        if ($this$measure_u2d3p2s80s.isLookingAhead()) {
            maxWidth = (int) (targetSize2 >> 32);
        } else {
            if (placeables.length == 0) {
                placeable = null;
            } else {
                placeable = placeables[0];
                int lastIndex$iv = ArraysKt.getLastIndex(placeables);
                if (lastIndex$iv != 0) {
                    int maxValue$iv = placeable != null ? placeable.getWidth() : 0;
                    int i$iv = 1;
                    if (1 <= lastIndex$iv) {
                        while (true) {
                            Placeable placeable3 = placeables[i$iv];
                            int v$iv = placeable3 != null ? placeable3.getWidth() : 0;
                            if (maxValue$iv < v$iv) {
                                placeable = placeable3;
                                maxValue$iv = v$iv;
                            }
                            if (i$iv == lastIndex$iv) {
                                break;
                            }
                            i$iv++;
                        }
                    }
                }
            }
            maxWidth = placeable != null ? placeable.getWidth() : 0;
        }
        if ($this$measure_u2d3p2s80s.isLookingAhead()) {
            height = (int) (targetSize2 & 4294967295L);
        } else {
            if (placeables.length == 0) {
                placeable2 = null;
            } else {
                placeable2 = placeables[0];
                int lastIndex$iv2 = ArraysKt.getLastIndex(placeables);
                if (lastIndex$iv2 != 0) {
                    int maxValue$iv2 = placeable2 != null ? placeable2.getHeight() : 0;
                    int i$iv2 = 1;
                    if (1 <= lastIndex$iv2) {
                        while (true) {
                            Placeable placeable4 = placeables[i$iv2];
                            int v$iv2 = placeable4 != null ? placeable4.getHeight() : 0;
                            if (maxValue$iv2 < v$iv2) {
                                maxValue$iv2 = v$iv2;
                                placeable2 = placeable4;
                            }
                            if (i$iv2 == lastIndex$iv2) {
                                break;
                            }
                            i$iv2++;
                        }
                    }
                }
            }
            height = placeable2 != null ? placeable2.getHeight() : 0;
        }
        final int maxHeight = height;
        if (!$this$measure_u2d3p2s80s.isLookingAhead()) {
            int width$iv2 = maxWidth;
            this.rootScope.m64setMeasuredSizeozmzZPI$animation(IntSize.m8316constructorimpl((((long) maxHeight) & 4294967295L) | (((long) width$iv2) << 32)));
        }
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, maxWidth, maxHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentMeasurePolicy$measure$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                Placeable[] placeableArr;
                AnimatedContentMeasurePolicy animatedContentMeasurePolicy;
                Placeable[] placeableArr2 = placeables;
                AnimatedContentMeasurePolicy animatedContentMeasurePolicy2 = this;
                int width$iv3 = maxWidth;
                int height$iv2 = maxHeight;
                int length = placeableArr2.length;
                int i = 0;
                while (i < length) {
                    Placeable placeable5 = placeableArr2[i];
                    if (placeable5 == null) {
                        placeableArr = placeableArr2;
                        animatedContentMeasurePolicy = animatedContentMeasurePolicy2;
                    } else {
                        Alignment contentAlignment = animatedContentMeasurePolicy2.getRootScope().getContentAlignment();
                        int width$iv4 = placeable5.getWidth();
                        int height$iv3 = placeable5.getHeight();
                        placeableArr = placeableArr2;
                        animatedContentMeasurePolicy = animatedContentMeasurePolicy2;
                        long offset = contentAlignment.mo4736alignKFBX0sM(IntSize.m8316constructorimpl((((long) width$iv4) << 32) | (((long) height$iv3) & 4294967295L)), IntSize.m8316constructorimpl((((long) width$iv3) << 32) | (((long) height$iv2) & 4294967295L)), LayoutDirection.Ltr);
                        Placeable.PlacementScope.place$default($this$layout, placeable5, IntOffset.m8278getXimpl(offset), IntOffset.m8279getYimpl(offset), 0.0f, 4, null);
                    }
                    i++;
                    placeableArr2 = placeableArr;
                    animatedContentMeasurePolicy2 = animatedContentMeasurePolicy;
                }
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            IntrinsicMeasurable it = list.get(0);
            numValueOf = Integer.valueOf(it.minIntrinsicWidth(height));
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    IntrinsicMeasurable it2 = list.get(i$iv);
                    Integer numValueOf2 = Integer.valueOf(it2.minIntrinsicWidth(height));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        Integer num = numValueOf;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            IntrinsicMeasurable it = list.get(0);
            numValueOf = Integer.valueOf(it.minIntrinsicHeight(width));
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    IntrinsicMeasurable it2 = list.get(i$iv);
                    Integer numValueOf2 = Integer.valueOf(it2.minIntrinsicHeight(width));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        Integer num = numValueOf;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            IntrinsicMeasurable it = list.get(0);
            numValueOf = Integer.valueOf(it.maxIntrinsicWidth(height));
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    IntrinsicMeasurable it2 = list.get(i$iv);
                    Integer numValueOf2 = Integer.valueOf(it2.maxIntrinsicWidth(height));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        Integer num = numValueOf;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            IntrinsicMeasurable it = list.get(0);
            numValueOf = Integer.valueOf(it.maxIntrinsicHeight(width));
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                while (true) {
                    IntrinsicMeasurable it2 = list.get(i$iv);
                    Integer numValueOf2 = Integer.valueOf(it2.maxIntrinsicHeight(width));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        }
        Integer num = numValueOf;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }
}
