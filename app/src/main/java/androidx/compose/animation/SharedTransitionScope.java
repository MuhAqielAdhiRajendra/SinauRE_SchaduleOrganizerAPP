package androidx.compose.animation;

import androidx.compose.animation.SharedTransitionDefaults;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.ApproachMeasureScope;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.LookaheadScopeKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SharedTransitionScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001:\u0005./#0-J\u001c\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH&J\u001c\u0010\t\u001a\u00020\u0006*\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0016J&\u0010\n\u001a\u00020\u0006*\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\bH&JN\u0010\u000e\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H&Jl\u0010\u001a\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H&JN\u0010!\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%H&J\u0015\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(H\u0017¢\u0006\u0002\u0010)J\u001d\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(2\u0006\u0010*\u001a\u00020+H\u0017¢\u0006\u0002\u0010,J\b\u0010-\u001a\u00020+H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00061À\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope;", "Landroidx/compose/ui/layout/LookaheadScope;", "isTransitionActive", "", "()Z", "skipToLookaheadSize", "Landroidx/compose/ui/Modifier;", "enabled", "Lkotlin/Function0;", "skipToLookaheadPosition", "renderInSharedTransitionScopeOverlay", "zIndexInOverlay", "", "renderInOverlay", "sharedElement", "sharedContentState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "animatedVisibilityScope", "Landroidx/compose/animation/AnimatedVisibilityScope;", "boundsTransform", "Landroidx/compose/animation/BoundsTransform;", "placeholderSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "renderInOverlayDuringTransition", "clipInOverlayDuringTransition", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "sharedBounds", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "resizeMode", "Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "sharedElementWithCallerManagedVisibility", "visible", "OverlayClip", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "rememberSharedContentState", "key", "", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "config", "Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;", "(Ljava/lang/Object;Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "SharedContentConfig", "PlaceholderSize", "ResizeMode", "SharedContentState", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface SharedTransitionScope extends LookaheadScope {

    /* JADX INFO: compiled from: SharedTransitionScope.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "", "getClipPath", "Landroidx/compose/ui/graphics/Path;", "sharedContentState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface OverlayClip {
        Path getClipPath(SharedContentState sharedContentState, Rect bounds, LayoutDirection layoutDirection, Density density);
    }

    OverlayClip OverlayClip(Shape clipShape);

    boolean isTransitionActive();

    Modifier renderInSharedTransitionScopeOverlay(Modifier modifier, float f, Function0<Boolean> function0);

    Modifier sharedBounds(Modifier modifier, SharedContentState sharedContentState, AnimatedVisibilityScope animatedVisibilityScope, EnterTransition enterTransition, ExitTransition exitTransition, BoundsTransform boundsTransform, ResizeMode resizeMode, PlaceholderSize placeholderSize, boolean z, float f, OverlayClip overlayClip);

    Modifier sharedElement(Modifier modifier, SharedContentState sharedContentState, AnimatedVisibilityScope animatedVisibilityScope, BoundsTransform boundsTransform, PlaceholderSize placeholderSize, boolean z, float f, OverlayClip overlayClip);

    Modifier sharedElementWithCallerManagedVisibility(Modifier modifier, SharedContentState sharedContentState, boolean z, BoundsTransform boundsTransform, PlaceholderSize placeholderSize, boolean z2, float f, OverlayClip overlayClip);

    Modifier skipToLookaheadSize(Modifier modifier, Function0<Boolean> function0);

    /* JADX INFO: compiled from: SharedTransitionScope.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bæ\u0080\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H&¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "", "calculateSize", "Landroidx/compose/ui/unit/IntSize;", "contentSize", "animatedSize", "calculateSize-JyjRU_E", "(JJ)J", "Companion", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface PlaceholderSize {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: renamed from: calculateSize-JyjRU_E, reason: not valid java name */
        long mo145calculateSizeJyjRU_E(long contentSize, long animatedSize);

        /* JADX INFO: compiled from: SharedTransitionScope.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize$Companion;", "", "<init>", "()V", "AnimatedSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "getAnimatedSize", "()Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "ContentSize", "getContentSize", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final PlaceholderSize AnimatedSize = new PlaceholderSize() { // from class: androidx.compose.animation.SharedTransitionScope$PlaceholderSize$Companion$AnimatedSize$1
                @Override // androidx.compose.animation.SharedTransitionScope.PlaceholderSize
                /* JADX INFO: renamed from: calculateSize-JyjRU_E */
                public final long mo145calculateSizeJyjRU_E(long j, long animatedSize) {
                    return animatedSize;
                }
            };
            private static final PlaceholderSize ContentSize = new PlaceholderSize() { // from class: androidx.compose.animation.SharedTransitionScope$PlaceholderSize$Companion$ContentSize$1
                @Override // androidx.compose.animation.SharedTransitionScope.PlaceholderSize
                /* JADX INFO: renamed from: calculateSize-JyjRU_E */
                public final long mo145calculateSizeJyjRU_E(long contentSize, long j) {
                    return contentSize;
                }
            };

            private Companion() {
            }

            public final PlaceholderSize getAnimatedSize() {
                return AnimatedSize;
            }

            public final PlaceholderSize getContentSize() {
                return ContentSize;
            }
        }
    }

    /* JADX INFO: compiled from: SharedTransitionScope.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002\u0082\u0001\u0002\u0003\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "", "Companion", "Landroidx/compose/animation/RemeasureImpl;", "Landroidx/compose/animation/ScaleToBoundsImpl;", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface ResizeMode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: SharedTransitionScope.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$ResizeMode$Companion;", "", "<init>", "()V", "RemeasureToBounds", "Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "getRemeasureToBounds", "()Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "scaleToBounds", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alignment", "Landroidx/compose/ui/Alignment;", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final ResizeMode RemeasureToBounds = RemeasureImpl.INSTANCE;

            private Companion() {
            }

            public final ResizeMode getRemeasureToBounds() {
                return RemeasureToBounds;
            }

            public static /* synthetic */ ResizeMode scaleToBounds$default(Companion companion, ContentScale contentScale, Alignment alignment, int i, Object obj) {
                if ((i & 1) != 0) {
                    contentScale = ContentScale.INSTANCE.getFillWidth();
                }
                if ((i & 2) != 0) {
                    alignment = Alignment.INSTANCE.getCenter();
                }
                return companion.scaleToBounds(contentScale, alignment);
            }

            public final ResizeMode scaleToBounds(ContentScale contentScale, Alignment alignment) {
                return SharedTransitionScopeKt.ScaleToBoundsCached(contentScale, alignment);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Modifier skipToLookaheadSize$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: skipToLookaheadSize");
        }
        if ((i & 1) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadSize.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SharedTransitionScope.this.isTransitionActive());
                }
            };
        }
        return sharedTransitionScope.skipToLookaheadSize(modifier, function0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Modifier skipToLookaheadPosition$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: skipToLookaheadPosition");
        }
        if ((i & 1) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadPosition.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SharedTransitionScope.this.isTransitionActive());
                }
            };
        }
        return sharedTransitionScope.skipToLookaheadPosition(modifier, function0);
    }

    default Modifier skipToLookaheadPosition(Modifier $this$skipToLookaheadPosition, final Function0<Boolean> function0) {
        return LookaheadScopeKt.approachLayout($this$skipToLookaheadPosition, new Function1<IntSize, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadPosition.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(IntSize intSize) {
                return m148invokeozmzZPI(intSize.m8325unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
            public final Boolean m148invokeozmzZPI(long it) {
                return false;
            }
        }, new Function2<Placeable.PlacementScope, LayoutCoordinates, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadPosition.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(Placeable.PlacementScope $this$approachLayout, LayoutCoordinates it) {
                return function0.invoke();
            }
        }, new Function3<ApproachMeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.SharedTransitionScope.skipToLookaheadPosition.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(ApproachMeasureScope approachMeasureScope, Measurable measurable, Constraints constraints) {
                return m149invoke3p2s80s(approachMeasureScope, measurable, constraints.getValue());
            }

            /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m149invoke3p2s80s(ApproachMeasureScope $this$approachLayout, Measurable m, long c) {
                final Placeable $this$invoke_3p2s80s_u24lambda_u240 = m.mo6783measureBRTryo0(c);
                final Function0<Boolean> function02 = function0;
                final SharedTransitionScope sharedTransitionScope = this;
                return MeasureScope.layout$default($this$approachLayout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedTransitionScope$skipToLookaheadPosition$4$1$1
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
                        if (function02.invoke().booleanValue()) {
                            LayoutCoordinates it = $this$layout.getCoordinates();
                            if (it != null) {
                                SharedTransitionScope sharedTransitionScope2 = sharedTransitionScope;
                                Placeable placeable = $this$invoke_3p2s80s_u24lambda_u240;
                                long target = LookaheadScope.m6813localLookaheadPositionOfauaQtc$default(sharedTransitionScope2, sharedTransitionScope2.getLookaheadScopeCoordinates($this$layout), it, 0L, false, 6, null);
                                long actual = LayoutCoordinates.m6790localPositionOfS_NoaFU$default(sharedTransitionScope2.getLookaheadScopeCoordinates($this$layout), it, 0L, false, 6, null);
                                long delta = Offset.m5072minusMKHz9U(target, actual);
                                long offset = Offset.m5072minusMKHz9U(it.mo6792localPositionOfR5De75A(sharedTransitionScope2.getLookaheadScopeCoordinates($this$layout), delta), LayoutCoordinates.m6790localPositionOfS_NoaFU$default(it, sharedTransitionScope2.getLookaheadScopeCoordinates($this$layout), 0L, false, 6, null));
                                Placeable.PlacementScope.m6849place70tqf50$default($this$layout, placeable, IntOffsetKt.m8295roundk4lQ0M(offset), 0.0f, 2, null);
                                return;
                            }
                            Placeable.PlacementScope.place$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                            return;
                        }
                        Placeable.PlacementScope.place$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                    }
                }, 4, null);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Modifier renderInSharedTransitionScopeOverlay$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, float f, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: renderInSharedTransitionScopeOverlay");
        }
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScope.renderInSharedTransitionScopeOverlay.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(SharedTransitionScope.this.isTransitionActive());
                }
            };
        }
        return sharedTransitionScope.renderInSharedTransitionScopeOverlay(modifier, f, function0);
    }

    static /* synthetic */ Modifier sharedElement$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, SharedContentState sharedContentState, AnimatedVisibilityScope animatedVisibilityScope, BoundsTransform boundsTransform, PlaceholderSize placeholderSize, boolean z, float f, OverlayClip overlayClip, int i, Object obj) {
        if (obj == null) {
            return sharedTransitionScope.sharedElement(modifier, sharedContentState, animatedVisibilityScope, (i & 4) != 0 ? SharedTransitionDefaults.INSTANCE.getBoundsTransform() : boundsTransform, (i & 8) != 0 ? PlaceholderSize.INSTANCE.getContentSize() : placeholderSize, (i & 16) != 0 ? true : z, (i & 32) != 0 ? 0.0f : f, (i & 64) != 0 ? SharedTransitionScopeKt.ParentClip : overlayClip);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sharedElement");
    }

    static /* synthetic */ Modifier sharedBounds$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, SharedContentState sharedContentState, AnimatedVisibilityScope animatedVisibilityScope, EnterTransition enterTransition, ExitTransition exitTransition, BoundsTransform boundsTransform, ResizeMode resizeMode, PlaceholderSize placeholderSize, boolean z, float f, OverlayClip overlayClip, int i, Object obj) {
        if (obj == null) {
            return sharedTransitionScope.sharedBounds(modifier, sharedContentState, animatedVisibilityScope, (i & 4) != 0 ? EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null) : enterTransition, (i & 8) != 0 ? EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null) : exitTransition, (i & 16) != 0 ? SharedTransitionDefaults.INSTANCE.getBoundsTransform() : boundsTransform, (i & 32) != 0 ? ResizeMode.INSTANCE.scaleToBounds(ContentScale.INSTANCE.getFillWidth(), Alignment.INSTANCE.getCenter()) : resizeMode, (i & 64) != 0 ? PlaceholderSize.INSTANCE.getContentSize() : placeholderSize, (i & 128) != 0 ? true : z, (i & 256) != 0 ? 0.0f : f, (i & 512) != 0 ? SharedTransitionScopeKt.ParentClip : overlayClip);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sharedBounds");
    }

    static /* synthetic */ Modifier sharedElementWithCallerManagedVisibility$default(SharedTransitionScope sharedTransitionScope, Modifier modifier, SharedContentState sharedContentState, boolean z, BoundsTransform boundsTransform, PlaceholderSize placeholderSize, boolean z2, float f, OverlayClip overlayClip, int i, Object obj) {
        if (obj == null) {
            return sharedTransitionScope.sharedElementWithCallerManagedVisibility(modifier, sharedContentState, z, (i & 4) != 0 ? SharedTransitionDefaults.INSTANCE.getBoundsTransform() : boundsTransform, (i & 8) != 0 ? PlaceholderSize.INSTANCE.getContentSize() : placeholderSize, (i & 16) != 0 ? true : z2, (i & 32) != 0 ? 0.0f : f, (i & 64) != 0 ? SharedTransitionScopeKt.ParentClip : overlayClip);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sharedElementWithCallerManagedVisibility");
    }

    default SharedContentState rememberSharedContentState(Object key, Composer $composer, int $changed) {
        $composer.startReplaceGroup(800730162);
        ComposerKt.sourceInformation($composer, "C(rememberSharedContentState)N(key)828@47119L77:SharedTransitionScope.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(800730162, $changed, -1, "androidx.compose.animation.SharedTransitionScope.rememberSharedContentState (SharedTransitionScope.kt:828)");
        }
        SharedContentState sharedContentStateRememberSharedContentState = rememberSharedContentState(key, SharedTransitionDefaults.SharedContentConfig.INSTANCE, $composer, ($changed & 14) | 48 | (($changed << 3) & 896));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return sharedContentStateRememberSharedContentState;
    }

    default SharedContentState rememberSharedContentState(Object key, SharedContentConfig config, Composer $composer, int $changed) {
        $composer.startReplaceGroup(-148945892);
        ComposerKt.sourceInformation($composer, "C(rememberSharedContentState)N(key,config)852@48366L49:SharedTransitionScope.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-148945892, $changed, -1, "androidx.compose.animation.SharedTransitionScope.rememberSharedContentState (SharedTransitionScope.kt:850)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1403074483, "CC(remember):SharedTransitionScope.kt#9igjgp");
        boolean invalid$iv = $composer.changed(key);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new SharedContentState(key, config);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        SharedContentState it = (SharedContentState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        it.setConfig$animation(config);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return it;
    }

    /* JADX INFO: compiled from: SharedTransitionScope.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00048@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\fR\u0011\u0010\u0016\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\fR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0013\u0010!\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b\"\u0010#R/\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\r\u001a\u0004\u0018\u00010$8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010\u0013\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010'¨\u0006-"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "", "key", "config", "Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;", "<init>", "(Ljava/lang/Object;Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;)V", "getKey", "()Ljava/lang/Object;", "isAnimating", "", "isAnimating$animation", "()Z", "<set-?>", "getConfig$animation", "()Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;", "setConfig$animation", "(Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;)V", "config$delegate", "Landroidx/compose/runtime/MutableState;", "isEnabledByUser", "isEnabledByUser$animation", "isMatchFound", "prepareTransitionWithInitialVelocity", "", "initialVelocity", "Landroidx/compose/ui/unit/Velocity;", "prepareTransitionWithInitialVelocity-TH1AsA0", "(J)V", "clipPathInOverlay", "Landroidx/compose/ui/graphics/Path;", "getClipPathInOverlay", "()Landroidx/compose/ui/graphics/Path;", "parentSharedContentState", "getParentSharedContentState", "()Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "Landroidx/compose/animation/SharedElementEntry;", "internalState", "getInternalState$animation", "()Landroidx/compose/animation/SharedElementEntry;", "setInternalState$animation", "(Landroidx/compose/animation/SharedElementEntry;)V", "internalState$delegate", "nonNullInternalState", "getNonNullInternalState", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class SharedContentState {
        public static final int $stable = 8;

        /* JADX INFO: renamed from: config$delegate, reason: from kotlin metadata */
        private final MutableState config;

        /* JADX INFO: renamed from: internalState$delegate, reason: from kotlin metadata */
        private final MutableState internalState;
        private final Object key;

        public SharedContentState(Object key, SharedContentConfig config) {
            this.key = key;
            this.config = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(config, null, 2, null);
            this.internalState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        }

        public /* synthetic */ SharedContentState(Object obj, SharedTransitionDefaults.SharedContentConfig sharedContentConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, (i & 2) != 0 ? SharedTransitionDefaults.SharedContentConfig.INSTANCE : sharedContentConfig);
        }

        public final Object getKey() {
            return this.key;
        }

        public final boolean isAnimating$animation() {
            BoundsAnimation boundsAnimation;
            SharedElementEntry internalState$animation = getInternalState$animation();
            return ((internalState$animation == null || (boundsAnimation = internalState$animation.getBoundsAnimation()) == null) ? null : boundsAnimation.getAnimationState()) != null;
        }

        public final SharedContentConfig getConfig$animation() {
            State $this$getValue$iv = this.config;
            return (SharedContentConfig) $this$getValue$iv.getValue();
        }

        public final void setConfig$animation(SharedContentConfig sharedContentConfig) {
            MutableState $this$setValue$iv = this.config;
            $this$setValue$iv.setValue(sharedContentConfig);
        }

        public final boolean isEnabledByUser$animation() {
            SharedContentConfig $this$_get_isEnabledByUser__u24lambda_u240 = getConfig$animation();
            return $this$_get_isEnabledByUser__u24lambda_u240.isEnabled(this) || (isAnimating$animation() && $this$_get_isEnabledByUser__u24lambda_u240.getShouldKeepEnabledForOngoingAnimation());
        }

        public final boolean isMatchFound() {
            SharedElement sharedElement;
            SharedElementEntry internalState$animation = getInternalState$animation();
            if (internalState$animation == null || (sharedElement = internalState$animation.getSharedElement()) == null) {
                return false;
            }
            return sharedElement.getFoundMatch();
        }

        /* JADX INFO: renamed from: prepareTransitionWithInitialVelocity-TH1AsA0, reason: not valid java name */
        public final void m147prepareTransitionWithInitialVelocityTH1AsA0(long initialVelocity) {
            SharedElementEntry it = getNonNullInternalState();
            SharedContentConfig $this$prepareTransitionWithInitialVelocity_TH1AsA0_u24lambda_u240_u240 = getConfig$animation();
            if ($this$prepareTransitionWithInitialVelocity_TH1AsA0_u24lambda_u240_u240.isEnabled(this)) {
                it.getSharedElement().m142updateExitVelocityTH1AsA0$animation(initialVelocity);
            }
        }

        public final Path getClipPathInOverlay() {
            return getNonNullInternalState().getClipPathInOverlay();
        }

        public final SharedContentState getParentSharedContentState() {
            SharedElementEntry parentState = getNonNullInternalState().getParentState();
            if (parentState != null) {
                return parentState.getUserState();
            }
            return null;
        }

        public final SharedElementEntry getInternalState$animation() {
            State $this$getValue$iv = this.internalState;
            return (SharedElementEntry) $this$getValue$iv.getValue();
        }

        public final void setInternalState$animation(SharedElementEntry sharedElementEntry) {
            MutableState $this$setValue$iv = this.internalState;
            $this$setValue$iv.setValue(sharedElementEntry);
        }

        private final SharedElementEntry getNonNullInternalState() {
            SharedElementEntry internalState$animation = getInternalState$animation();
            if (internalState$animation == null) {
                throw new IllegalArgumentException("Error: SharedContentState has not been added to a sharedElement/sharedBoundsmodifier yet. Therefore the internal state has not been initialized.".toString());
            }
            return internalState$animation;
        }
    }

    /* JADX INFO: compiled from: SharedTransitionScope.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J%\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;", "", "isEnabled", "", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "(Landroidx/compose/animation/SharedTransitionScope$SharedContentState;)Z", "shouldKeepEnabledForOngoingAnimation", "getShouldKeepEnabledForOngoingAnimation", "()Z", "alternativeTargetBoundsInTransitionScopeAfterRemoval", "Landroidx/compose/ui/geometry/Rect;", "targetBoundsBeforeRemoval", "sharedTransitionLayoutSize", "Landroidx/compose/ui/geometry/Size;", "alternativeTargetBoundsInTransitionScopeAfterRemoval-cSwnlzA", "(Landroidx/compose/animation/SharedTransitionScope$SharedContentState;Landroidx/compose/ui/geometry/Rect;J)Landroidx/compose/ui/geometry/Rect;", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface SharedContentConfig {
        default boolean isEnabled(SharedContentState $this$isEnabled) {
            return true;
        }

        default boolean getShouldKeepEnabledForOngoingAnimation() {
            return true;
        }

        /* JADX INFO: renamed from: alternativeTargetBoundsInTransitionScopeAfterRemoval-cSwnlzA, reason: not valid java name */
        default Rect m146alternativeTargetBoundsInTransitionScopeAfterRemovalcSwnlzA(SharedContentState $this$alternativeTargetBoundsInTransitionScopeAfterRemoval_u2dcSwnlzA, Rect targetBoundsBeforeRemoval, long sharedTransitionLayoutSize) {
            return null;
        }
    }

    default SharedContentConfig SharedContentConfig() {
        return CachedSharedContentConfig.INSTANCE;
    }
}
