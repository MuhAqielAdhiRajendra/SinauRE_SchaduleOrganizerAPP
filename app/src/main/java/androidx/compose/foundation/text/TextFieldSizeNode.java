package androidx.compose.foundation.text;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldSize.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\b\u0010\u000e\u001a\u00020\fH\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J#\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u0014H\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\b\u0010 \u001a\u00020\u0014H\u0016J\u000e\u0010!\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u0005J\u0018\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Landroidx/compose/foundation/text/TextFieldSizeNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "style", "Landroidx/compose/ui/text/TextStyle;", "<init>", "(Landroidx/compose/ui/text/TextStyle;)V", "fontResolutionState", "Landroidx/compose/runtime/State;", "", "minSizeState", "Landroidx/compose/foundation/text/TextFieldSize;", "requireFontResolutionState", "requireMinSizeState", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "onAttach", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "onLayoutDirectionChange", "onDensityChange", "onDetach", "update", "updateFontResolutionState", "resolvedStyle", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class TextFieldSizeNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, LayoutModifierNode {
    private State<? extends Object> fontResolutionState;
    private TextFieldSize minSizeState;
    private final boolean shouldAutoInvalidate;
    private final TextStyle style;

    public TextFieldSizeNode(TextStyle style) {
        this.style = style;
    }

    private final State<Object> requireFontResolutionState() {
        State<? extends Object> state = this.fontResolutionState;
        if (state != null) {
            return state;
        }
        InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("Font resolution state is not set.");
        throw new KotlinNothingValueException();
    }

    private final TextFieldSize requireMinSizeState() {
        TextFieldSize textFieldSize = this.minSizeState;
        if (textFieldSize != null) {
            return textFieldSize;
        }
        InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("Min size state is not set.");
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        TextStyle resolvedStyle = TextStyleKt.resolveDefaults(this.style, DelegatableNodeKt.requireLayoutDirection(this));
        FontFamily.Resolver fontFamilyResolver = (FontFamily.Resolver) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFontFamilyResolver());
        updateFontResolutionState(resolvedStyle, fontFamilyResolver);
        this.minSizeState = new TextFieldSize(DelegatableNodeKt.requireLayoutDirection(this), DelegatableNodeKt.requireDensity(this), fontFamilyResolver, resolvedStyle, requireFontResolutionState().getValue());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        long minSize = requireMinSizeState().m1670cachedMinSizeOrComputeMinSizeYEO4UFw(requireFontResolutionState().getValue());
        long childConstraints = ConstraintsKt.Constraints$default((int) (minSize >> 32), 0, (int) (4294967295L & minSize), 0, 10, null);
        final Placeable measured = measurable.mo6783measureBRTryo0(ConstraintsKt.m8118constrainN9IONVI(constraints, childConstraints));
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, measured.getWidth(), measured.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.text.TextFieldSizeNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSizeNode.measure_3p2s80s$lambda$0(measured, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$0(Placeable $measured, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $measured, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.DelegatableNode
    public void onLayoutDirectionChange() {
        TextFieldSize textFieldSize = this.minSizeState;
        if (textFieldSize != null) {
            TextFieldSize.update$default(textFieldSize, DelegatableNodeKt.requireLayoutDirection(this), null, null, null, null, 30, null);
        }
        LayoutModifierNodeKt.invalidateMeasurement(this);
    }

    @Override // androidx.compose.ui.node.DelegatableNode, androidx.compose.ui.node.PointerInputModifierNode
    public void onDensityChange() {
        TextFieldSize textFieldSize = this.minSizeState;
        if (textFieldSize != null) {
            TextFieldSize.update$default(textFieldSize, null, DelegatableNodeKt.requireDensity(this), null, null, null, 29, null);
        }
        LayoutModifierNodeKt.invalidateMeasurement(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.fontResolutionState = null;
        this.minSizeState = null;
    }

    public final void update(TextStyle style) {
        TextStyle resolvedStyle = TextStyleKt.resolveDefaults(style, DelegatableNodeKt.requireLayoutDirection(this));
        updateFontResolutionState(resolvedStyle, (FontFamily.Resolver) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFontFamilyResolver()));
        TextFieldSize.update$default(requireMinSizeState(), null, null, null, resolvedStyle, null, 23, null);
        LayoutModifierNodeKt.invalidateMeasurement(this);
    }

    private final void updateFontResolutionState(TextStyle resolvedStyle, FontFamily.Resolver fontFamilyResolver) {
        FontFamily fontFamily = resolvedStyle.getFontFamily();
        FontWeight fontWeight = resolvedStyle.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        FontStyle fontStyleM7605getFontStyle4Lr2A7w = resolvedStyle.m7605getFontStyle4Lr2A7w();
        int iM7688unboximpl = fontStyleM7605getFontStyle4Lr2A7w != null ? fontStyleM7605getFontStyle4Lr2A7w.m7688unboximpl() : FontStyle.INSTANCE.m7692getNormal_LCdwA();
        FontSynthesis fontSynthesisM7606getFontSynthesisZQGJjVo = resolvedStyle.m7606getFontSynthesisZQGJjVo();
        this.fontResolutionState = fontFamilyResolver.mo7658resolveDPcqOEQ(fontFamily, fontWeight, iM7688unboximpl, fontSynthesisM7606getFontSynthesisZQGJjVo != null ? fontSynthesisM7606getFontSynthesisZQGJjVo.m7701unboximpl() : FontSynthesis.INSTANCE.m7702getAllGVVA2EU());
        LayoutModifierNodeKt.invalidateMeasurement(this);
    }
}
