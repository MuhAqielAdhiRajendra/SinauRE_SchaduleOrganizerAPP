package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.FlexAlignItems;
import androidx.compose.foundation.layout.FlexAlignSelf;
import androidx.compose.foundation.layout.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Measured;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.VerticalAlignmentLine;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b*\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0015\u001a\u00020\u0016*\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u0017*\u00020\u0016H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010'\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+J\u0010\u0010'\u001a\u00020(2\u0006\u0010,\u001a\u00020\u0005H\u0016J\u001c\u0010'\u001a\u00020(2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u001d0.H\u0016J\u0010\u00100\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u001dH\u0016J\u0010\u00101\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u0010H\u0016J\u0010\u00102\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u0010H\u0016J\u0017\u00103\u001a\u00020(2\u0006\u0010\u0004\u001a\u000204H\u0016¢\u0006\u0004\b5\u00106J\u0017\u00103\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u0017H\u0016¢\u0006\u0004\bE\u0010>J\u0012\u00103\u001a\u00020(2\b\b\u0001\u0010\u0004\u001a\u00020\u0010H\u0016J\u0016\u0010F\u001a\u00020\u001d2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020JJ\u001d\u0010O\u001a\u00020(2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010P\u001a\u00020Q¢\u0006\u0004\bR\u0010SJ\u0015\u0010\\\u001a\u00020\u001d2\u0006\u0010]\u001a\u00020LH\u0000¢\u0006\u0002\b^J\u0015\u0010_\u001a\u00020\u001d2\u0006\u0010]\u001a\u00020LH\u0000¢\u0006\u0002\b`R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u001e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u001d@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u001d@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u001e\u0010#\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u001d@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u001e\u0010%\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u001d@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u001c\u0010'\u001a\u00020)X\u0080\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b7\u0010 \"\u0004\b8\u0010+R\u001a\u00100\u001a\u00020\u001dX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010 \"\u0004\b;\u0010+R\u001a\u00101\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u0012\"\u0004\b=\u0010>R\u001a\u00102\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u0012\"\u0004\b@\u0010>R\u001c\u00103\u001a\u000204X\u0080\u000e¢\u0006\u0010\n\u0002\u0010D\u001a\u0004\bA\u0010B\"\u0004\bC\u00106R\u0011\u0010K\u001a\u00020L8F¢\u0006\u0006\u001a\u0004\bM\u0010NR\u001c\u0010T\u001a\u0004\u0018\u00010UX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u000e\u0010Z\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010a\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010 \"\u0004\bc\u0010+R\u001a\u0010d\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010 \"\u0004\bf\u0010+R\u001a\u0010g\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010 \"\u0004\bi\u0010+R\u001a\u0010j\u001a\u00020LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010N\"\u0004\bk\u0010lR\u001a\u0010m\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010 \"\u0004\bo\u0010+R\u001a\u0010p\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010 \"\u0004\br\u0010+R\u001c\u0010G\u001a\u0004\u0018\u00010HX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u001a\u0010w\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010 \"\u0004\bx\u0010+R\u001a\u0010y\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010 \"\u0004\b{\u0010+R\u001a\u0010|\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b}\u0010 \"\u0004\b~\u0010+¨\u0006\u007f"}, d2 = {"Landroidx/compose/foundation/layout/ResolvedFlexItemInfo;", "Landroidx/compose/foundation/layout/FlexConfigScope;", "<init>", "()V", "value", "Landroidx/compose/ui/layout/AlignmentLine;", "baselineAlignmentLine", "getBaselineAlignmentLine", "()Landroidx/compose/ui/layout/AlignmentLine;", "Landroidx/compose/foundation/layout/AlignmentLineProviderBlock;", "baselineAlignmentBlock", "getBaselineAlignmentBlock", "()Landroidx/compose/foundation/layout/AlignmentLineProviderBlock;", "_density", "Landroidx/compose/ui/unit/Density;", "density", "", "getDensity", "()F", "fontScale", "getFontScale", "toSp", "Landroidx/compose/ui/unit/TextUnit;", "Landroidx/compose/ui/unit/Dp;", "toSp-0xMU5do", "(F)J", "toDp", "toDp-GaN1DYA", "(J)F", "", "flexBoxMainAxisMax", "getFlexBoxMainAxisMax", "()I", "flexBoxMainAxisMin", "getFlexBoxMainAxisMin", "flexBoxCrossAxisMax", "getFlexBoxCrossAxisMax", "flexBoxCrossAxisMin", "getFlexBoxCrossAxisMin", "alignSelf", "", "Landroidx/compose/foundation/layout/FlexAlignSelf;", "alignSelf-aKVMlHY", "(I)V", "alignmentLine", "alignmentLineBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Measured;", "order", "grow", "shrink", "basis", "Landroidx/compose/foundation/layout/FlexBasis;", "basis-MFoeH6Y", "(J)V", "getAlignSelf-_ov7Qcc$foundation_layout", "setAlignSelf-aKVMlHY$foundation_layout", "I", "getOrder$foundation_layout", "setOrder$foundation_layout", "getGrow$foundation_layout", "setGrow$foundation_layout", "(F)V", "getShrink$foundation_layout", "setShrink$foundation_layout", "getBasis-d-lZNVs$foundation_layout", "()J", "setBasis-MFoeH6Y$foundation_layout", "J", "basis-0680j_4", "getBaseline", "placeable", "Landroidx/compose/ui/layout/Placeable;", "fallback", "Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;", "hasBaseline", "", "getHasBaseline", "()Z", "prepare", "constraints", "Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "prepare-RMq0m1M", "(Landroidx/compose/ui/unit/Density;J)V", "measurable", "Landroidx/compose/ui/layout/Measurable;", "getMeasurable", "()Landroidx/compose/ui/layout/Measurable;", "setMeasurable", "(Landroidx/compose/ui/layout/Measurable;)V", "_minMainAxisSize", "_maxContentSize", "getMinMainAxisSize", "isHorizontal", "getMinMainAxisSize$foundation_layout", "getMaxContentSize", "getMaxContentSize$foundation_layout", "flexBaseSize", "getFlexBaseSize", "setFlexBaseSize", "hypotheticalMainSize", "getHypotheticalMainSize", "setHypotheticalMainSize", "targetMainSize", "getTargetMainSize", "setTargetMainSize", "isFrozen", "setFrozen", "(Z)V", "mainPosition", "getMainPosition", "setMainPosition", "crossPosition", "getCrossPosition", "setCrossPosition", "getPlaceable", "()Landroidx/compose/ui/layout/Placeable;", "setPlaceable", "(Landroidx/compose/ui/layout/Placeable;)V", "baseline", "setBaseline", "mainAxisSize", "getMainAxisSize", "setMainAxisSize", "crossAxisSize", "getCrossAxisSize", "setCrossAxisSize", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ResolvedFlexItemInfo implements FlexConfigScope {
    public static final int $stable = 0;
    private Density _density = FlexBoxKt.getDefaultDensity();
    private int _maxContentSize;
    private int _minMainAxisSize;
    private int alignSelf;
    private int baseline;
    private AlignmentLineProviderBlock baselineAlignmentBlock;
    private AlignmentLine baselineAlignmentLine;
    private long basis;
    private int crossAxisSize;
    private int crossPosition;
    private int flexBaseSize;
    private int flexBoxCrossAxisMax;
    private int flexBoxCrossAxisMin;
    private int flexBoxMainAxisMax;
    private int flexBoxMainAxisMin;
    private float grow;
    private int hypotheticalMainSize;
    private boolean isFrozen;
    private int mainAxisSize;
    private int mainPosition;
    private Measurable measurable;
    private int order;
    private Placeable placeable;
    private float shrink;
    private int targetMainSize;

    public ResolvedFlexItemInfo() {
        FlexAlignSelf.Companion companion = FlexAlignSelf.INSTANCE;
        this.alignSelf = FlexAlignSelf.m809constructorimpl(0);
        this.shrink = 1.0f;
        this.basis = FlexBasis.INSTANCE.m834getAutodlZNVs();
        this._minMainAxisSize = -1;
        this._maxContentSize = -1;
        this.baseline = Integer.MIN_VALUE;
    }

    public final AlignmentLine getBaselineAlignmentLine() {
        return this.baselineAlignmentLine;
    }

    public final AlignmentLineProviderBlock getBaselineAlignmentBlock() {
        return this.baselineAlignmentBlock;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this._density.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this._density.getFontScale();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public long mo434toSp0xMU5do(float $this$toSp_u2d0xMU5do) {
        Density $this$toSp_0xMU5do_u24lambda_u240 = this._density;
        return $this$toSp_0xMU5do_u24lambda_u240.mo434toSp0xMU5do($this$toSp_u2d0xMU5do);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public float mo427toDpGaN1DYA(long $this$toDp_u2dGaN1DYA) {
        Density $this$toDp_GaN1DYA_u24lambda_u240 = this._density;
        return $this$toDp_GaN1DYA_u24lambda_u240.mo427toDpGaN1DYA($this$toDp_u2dGaN1DYA);
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public int getFlexBoxMainAxisMax() {
        return this.flexBoxMainAxisMax;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public int getFlexBoxMainAxisMin() {
        return this.flexBoxMainAxisMin;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public int getFlexBoxCrossAxisMax() {
        return this.flexBoxCrossAxisMax;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public int getFlexBoxCrossAxisMin() {
        return this.flexBoxCrossAxisMin;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    /* JADX INFO: renamed from: alignSelf-aKVMlHY */
    public void mo854alignSelfaKVMlHY(int value) {
        this.alignSelf = value;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public void alignSelf(AlignmentLine alignmentLine) {
        FlexAlignSelf.Companion companion = FlexAlignSelf.INSTANCE;
        this.alignSelf = FlexAlignSelf.m809constructorimpl(5);
        this.baselineAlignmentLine = alignmentLine;
        this.baselineAlignmentBlock = null;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public void alignSelf(final Function1<? super Measured, Integer> alignmentLineBlock) {
        FlexAlignSelf.Companion companion = FlexAlignSelf.INSTANCE;
        this.alignSelf = FlexAlignSelf.m809constructorimpl(5);
        this.baselineAlignmentLine = null;
        this.baselineAlignmentBlock = new AlignmentLineProviderBlock() { // from class: androidx.compose.foundation.layout.ResolvedFlexItemInfo$$ExternalSyntheticLambda0
            @Override // androidx.compose.foundation.layout.AlignmentLineProviderBlock
            public final int calculateAlignmentLinePosition(Measured measured) {
                return ((Number) alignmentLineBlock.invoke(measured)).intValue();
            }
        };
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public void order(int value) {
        this.order = value;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public void grow(float value) {
        boolean value$iv = value >= 0.0f;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Flex grow cannot be negative: " + value);
        }
        this.grow = value;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public void shrink(float value) {
        boolean value$iv = value >= 0.0f;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Flex shrink cannot be negative: " + value);
        }
        this.shrink = value;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    /* JADX INFO: renamed from: basis-MFoeH6Y */
    public void mo856basisMFoeH6Y(long value) {
        this.basis = value;
    }

    /* JADX INFO: renamed from: getAlignSelf-_ov7Qcc$foundation_layout, reason: not valid java name and from getter */
    public final int getAlignSelf() {
        return this.alignSelf;
    }

    /* JADX INFO: renamed from: setAlignSelf-aKVMlHY$foundation_layout, reason: not valid java name */
    public final void m1091setAlignSelfaKVMlHY$foundation_layout(int i) {
        this.alignSelf = i;
    }

    /* JADX INFO: renamed from: getOrder$foundation_layout, reason: from getter */
    public final int getOrder() {
        return this.order;
    }

    public final void setOrder$foundation_layout(int i) {
        this.order = i;
    }

    /* JADX INFO: renamed from: getGrow$foundation_layout, reason: from getter */
    public final float getGrow() {
        return this.grow;
    }

    public final void setGrow$foundation_layout(float f) {
        this.grow = f;
    }

    /* JADX INFO: renamed from: getShrink$foundation_layout, reason: from getter */
    public final float getShrink() {
        return this.shrink;
    }

    public final void setShrink$foundation_layout(float f) {
        this.shrink = f;
    }

    /* JADX INFO: renamed from: getBasis-d-lZNVs$foundation_layout, reason: not valid java name and from getter */
    public final long getBasis() {
        return this.basis;
    }

    /* JADX INFO: renamed from: setBasis-MFoeH6Y$foundation_layout, reason: not valid java name */
    public final void m1092setBasisMFoeH6Y$foundation_layout(long j) {
        this.basis = j;
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    /* JADX INFO: renamed from: basis-0680j_4 */
    public void mo855basis0680j_4(float value) {
        this.basis = FlexBasis.INSTANCE.m832DpcHuBJEI(value);
    }

    @Override // androidx.compose.foundation.layout.FlexConfigScope
    public void basis(float value) {
        this.basis = FlexBasis.INSTANCE.m833Percentuoj9tHE(value);
    }

    public final int getBaseline(Placeable placeable, ResolvedFlexBoxConfig fallback) {
        if (this.baselineAlignmentBlock != null) {
            AlignmentLineProviderBlock alignmentLineProviderBlock = this.baselineAlignmentBlock;
            Intrinsics.checkNotNull(alignmentLineProviderBlock);
            return alignmentLineProviderBlock.calculateAlignmentLinePosition(placeable);
        }
        if (this.baselineAlignmentLine != null) {
            AlignmentLine alignmentLine = this.baselineAlignmentLine;
            Intrinsics.checkNotNull(alignmentLine);
            int value = placeable.get(alignmentLine);
            if (value != Integer.MIN_VALUE) {
                return value;
            }
            AlignmentLine alignmentLine2 = this.baselineAlignmentLine;
            Intrinsics.checkNotNull(alignmentLine2);
            if (alignmentLine2 instanceof VerticalAlignmentLine) {
                return placeable.getWidth();
            }
            return placeable.getHeight();
        }
        int i = this.alignSelf;
        FlexAlignSelf.Companion companion = FlexAlignSelf.INSTANCE;
        if (FlexAlignSelf.m811equalsimpl0(i, FlexAlignSelf.m809constructorimpl(5))) {
            return fallback.getBaseline$foundation_layout(placeable);
        }
        int alignItems = fallback.getAlignItems();
        FlexAlignItems.Companion companion2 = FlexAlignItems.INSTANCE;
        if (FlexAlignItems.m799equalsimpl0(alignItems, FlexAlignItems.m797constructorimpl(4))) {
            int i2 = this.alignSelf;
            FlexAlignSelf.Companion companion3 = FlexAlignSelf.INSTANCE;
            if (FlexAlignSelf.m811equalsimpl0(i2, FlexAlignSelf.m809constructorimpl(0))) {
                return fallback.getBaseline$foundation_layout(placeable);
            }
        }
        return Integer.MIN_VALUE;
    }

    public final boolean getHasBaseline() {
        int i = this.alignSelf;
        FlexAlignSelf.Companion companion = FlexAlignSelf.INSTANCE;
        return FlexAlignSelf.m811equalsimpl0(i, FlexAlignSelf.m809constructorimpl(5));
    }

    /* JADX INFO: renamed from: prepare-RMq0m1M, reason: not valid java name */
    public final void m1090prepareRMq0m1M(Density density, long constraints) {
        this._density = density;
        this.flexBoxMainAxisMax = Constraints.m8103getMaxWidthimpl(constraints);
        this.flexBoxMainAxisMin = Constraints.m8105getMinWidthimpl(constraints);
        this.flexBoxCrossAxisMax = Constraints.m8102getMaxHeightimpl(constraints);
        this.flexBoxCrossAxisMin = Constraints.m8104getMinHeightimpl(constraints);
    }

    public final Measurable getMeasurable() {
        return this.measurable;
    }

    public final void setMeasurable(Measurable measurable) {
        this.measurable = measurable;
    }

    public final int getMinMainAxisSize$foundation_layout(boolean isHorizontal) {
        if (this._minMainAxisSize == -1) {
            Measurable measurable = this.measurable;
            int iMinIntrinsicHeight = 0;
            if (isHorizontal) {
                if (measurable != null) {
                    iMinIntrinsicHeight = measurable.minIntrinsicWidth(Integer.MAX_VALUE);
                }
            } else if (measurable != null) {
                iMinIntrinsicHeight = measurable.minIntrinsicHeight(Integer.MAX_VALUE);
            }
            this._minMainAxisSize = iMinIntrinsicHeight;
        }
        return this._minMainAxisSize;
    }

    public final int getMaxContentSize$foundation_layout(boolean isHorizontal) {
        if (this._maxContentSize == -1) {
            Measurable measurable = this.measurable;
            int iMaxIntrinsicHeight = 0;
            if (isHorizontal) {
                if (measurable != null) {
                    iMaxIntrinsicHeight = measurable.maxIntrinsicWidth(Integer.MAX_VALUE);
                }
            } else if (measurable != null) {
                iMaxIntrinsicHeight = measurable.maxIntrinsicHeight(Integer.MAX_VALUE);
            }
            this._maxContentSize = iMaxIntrinsicHeight;
        }
        return this._maxContentSize;
    }

    public final int getFlexBaseSize() {
        return this.flexBaseSize;
    }

    public final void setFlexBaseSize(int i) {
        this.flexBaseSize = i;
    }

    public final int getHypotheticalMainSize() {
        return this.hypotheticalMainSize;
    }

    public final void setHypotheticalMainSize(int i) {
        this.hypotheticalMainSize = i;
    }

    public final int getTargetMainSize() {
        return this.targetMainSize;
    }

    public final void setTargetMainSize(int i) {
        this.targetMainSize = i;
    }

    /* JADX INFO: renamed from: isFrozen, reason: from getter */
    public final boolean getIsFrozen() {
        return this.isFrozen;
    }

    public final void setFrozen(boolean z) {
        this.isFrozen = z;
    }

    public final int getMainPosition() {
        return this.mainPosition;
    }

    public final void setMainPosition(int i) {
        this.mainPosition = i;
    }

    public final int getCrossPosition() {
        return this.crossPosition;
    }

    public final void setCrossPosition(int i) {
        this.crossPosition = i;
    }

    public final Placeable getPlaceable() {
        return this.placeable;
    }

    public final void setPlaceable(Placeable placeable) {
        this.placeable = placeable;
    }

    public final int getBaseline() {
        return this.baseline;
    }

    public final void setBaseline(int i) {
        this.baseline = i;
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    public final void setMainAxisSize(int i) {
        this.mainAxisSize = i;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    public final void setCrossAxisSize(int i) {
        this.crossAxisSize = i;
    }
}
