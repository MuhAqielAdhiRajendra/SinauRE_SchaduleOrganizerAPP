package androidx.compose.foundation.text.modifiers;

import android.os.Trace;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextStringSimpleNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001VBS\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\f\u0010%\u001a\u00020 *\u00020&H\u0002J\u0017\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020)H\u0002¢\u0006\u0004\b*\u0010+J\r\u0010\"\u001a\u00020 H\u0003¢\u0006\u0002\b,J\u0018\u0010-\u001a\u00020\u000e2\b\u0010.\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010/\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006J=\u00100\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b1\u00102J\u001e\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\u000eJ\u0010\u0010>\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\u0006H\u0002J\b\u0010@\u001a\u000204H\u0002J\f\u0010A\u001a\u000204*\u00020BH\u0016J\b\u0010C\u001a\u000204H\u0002J#\u0010D\u001a\u00020E*\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020JH\u0016¢\u0006\u0004\bK\u0010LJ\u001c\u0010M\u001a\u00020\u0010*\u00020&2\u0006\u0010G\u001a\u00020N2\u0006\u0010O\u001a\u00020\u0010H\u0016J\u001c\u0010P\u001a\u00020\u0010*\u00020&2\u0006\u0010G\u001a\u00020N2\u0006\u0010Q\u001a\u00020\u0010H\u0016J\u001c\u0010R\u001a\u00020\u0010*\u00020&2\u0006\u0010G\u001a\u00020N2\u0006\u0010O\u001a\u00020\u0010H\u0016J\u001c\u0010S\u001a\u00020\u0010*\u00020&2\u0006\u0010G\u001a\u00020N2\u0006\u0010Q\u001a\u00020\u0010H\u0016J\f\u0010T\u001a\u000204*\u00020UH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001bX\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001d\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u00108\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0:\u0012\u0004\u0012\u00020\u000e\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "overrideColor", "Landroidx/compose/ui/graphics/ColorProducer;", "<init>", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZIILandroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "baselineCache", "", "Landroidx/compose/ui/layout/AlignmentLine;", "getBaselineCache$annotations", "()V", "_layoutCache", "Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "layoutCache", "getLayoutCache", "()Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "resolvedInheritedStyle", "getLayoutCacheForMeasure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "resolveInheritedStyle", TypedValues.CycleType.S_WAVE_PHASE, "Landroidx/compose/foundation/text/modifiers/StylePhase;", "resolveInheritedStyle-uwmK9pY", "(I)Z", "getLayoutCacheOrSubstitute", "updateDraw", TypedValues.Custom.S_COLOR, "updateText", "updateLayoutRelatedArgs", "updateLayoutRelatedArgs-HuAbxIM", "(Landroidx/compose/ui/text/TextStyle;IIZLandroidx/compose/ui/text/font/FontFamily$Resolver;I)Z", "doInvalidations", "", "drawChanged", "textChanged", "layoutChanged", "semanticsTextLayoutResult", "Lkotlin/Function1;", "", "Landroidx/compose/ui/text/TextLayoutResult;", "textSubstitution", "Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode$TextSubstitutionValue;", "setSubstitution", "updatedText", "clearSubstitution", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "invalidateForTranslate", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "TextSubstitutionValue", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextStringSimpleNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private ParagraphLayoutCache _layoutCache;
    private Map<AlignmentLine, Integer> baselineCache;
    private FontFamily.Resolver fontFamilyResolver;
    private int maxLines;
    private int minLines;
    private int overflow;
    private ColorProducer overrideColor;
    private TextStyle resolvedInheritedStyle;
    private Function1<? super List<TextLayoutResult>, Boolean> semanticsTextLayoutResult;
    private boolean softWrap;
    private TextStyle style;
    private String text;
    private TextSubstitution textSubstitution;

    public /* synthetic */ TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, ColorProducer colorProducer, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, i, z, i2, i3, colorProducer);
    }

    private static /* synthetic */ void getBaselineCache$annotations() {
    }

    private TextStringSimpleNode(String text, TextStyle style, FontFamily.Resolver fontFamilyResolver, int overflow, boolean softWrap, int maxLines, int minLines, ColorProducer overrideColor) {
        this.text = text;
        this.style = style;
        this.fontFamilyResolver = fontFamilyResolver;
        this.overflow = overflow;
        this.softWrap = softWrap;
        this.maxLines = maxLines;
        this.minLines = minLines;
        this.overrideColor = overrideColor;
    }

    public /* synthetic */ TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, ColorProducer colorProducer, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, (i4 & 8) != 0 ? TextOverflow.INSTANCE.m8060getClipgIe3tQ8() : i, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? Integer.MAX_VALUE : i2, (i4 & 64) != 0 ? 1 : i3, (i4 & 128) != 0 ? null : colorProducer, null);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    private final ParagraphLayoutCache getLayoutCache() {
        TextStyle textStyle;
        if (!ComposeFoundationFlags.isInheritedTextStyleEnabled || (textStyle = this.resolvedInheritedStyle) == null) {
            textStyle = this.style;
        }
        TextStyle style = textStyle;
        if (this._layoutCache == null) {
            this._layoutCache = new ParagraphLayoutCache(this.text, style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, null);
        }
        ParagraphLayoutCache paragraphLayoutCache = this._layoutCache;
        Intrinsics.checkNotNull(paragraphLayoutCache);
        return paragraphLayoutCache;
    }

    private final ParagraphLayoutCache getLayoutCacheForMeasure(IntrinsicMeasureScope $this$getLayoutCacheForMeasure) {
        if (ComposeFoundationFlags.isInheritedTextStyleEnabled && m2018resolveInheritedStyleuwmK9pY(StylePhase.INSTANCE.m2015getLayoutoWBPZag())) {
            TextStyle textStyle = this.resolvedInheritedStyle;
            if (textStyle == null) {
                textStyle = this.style;
            }
            TextStyle style = textStyle;
            getLayoutCache().m2004updateL6sJoHM(this.text, style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
        }
        ParagraphLayoutCache activeCache = getLayoutCacheOrSubstitute();
        activeCache.setDensity$foundation($this$getLayoutCacheForMeasure);
        return activeCache;
    }

    /* JADX INFO: renamed from: resolveInheritedStyle-uwmK9pY, reason: not valid java name */
    private final boolean m2018resolveInheritedStyleuwmK9pY(int phase) {
        TextStyle previousStyle = this.resolvedInheritedStyle;
        TextStyle newInheritedStyle = TextStyleProviderNodeKt.m2020inheritedTextStyleBh5OqGs(this, phase, this.style);
        this.resolvedInheritedStyle = newInheritedStyle;
        if (previousStyle == null) {
            return false;
        }
        return !Intrinsics.areEqual(previousStyle, newInheritedStyle);
    }

    private final ParagraphLayoutCache getLayoutCacheOrSubstitute() {
        ParagraphLayoutCache layoutCache;
        TextSubstitution it = this.textSubstitution;
        if (it != null) {
            if (!it.isShowingSubstitution()) {
                it = null;
            }
            if (it != null && (layoutCache = it.getLayoutCache()) != null) {
                return layoutCache;
            }
        }
        return getLayoutCache();
    }

    public final boolean updateDraw(ColorProducer color, TextStyle style) {
        boolean changed = false;
        if (!Intrinsics.areEqual(color, this.overrideColor)) {
            changed = true;
        }
        this.overrideColor = color;
        return changed || !style.hasSameDrawAffectingAttributes(this.style);
    }

    public final boolean updateText(String text) {
        if (Intrinsics.areEqual(this.text, text)) {
            return false;
        }
        this.text = text;
        clearSubstitution();
        return true;
    }

    /* JADX INFO: renamed from: updateLayoutRelatedArgs-HuAbxIM, reason: not valid java name */
    public final boolean m2019updateLayoutRelatedArgsHuAbxIM(TextStyle style, int minLines, int maxLines, boolean softWrap, FontFamily.Resolver fontFamilyResolver, int overflow) {
        boolean changed = !this.style.hasSameLayoutAffectingAttributes(style);
        this.style = style;
        if (this.minLines != minLines) {
            this.minLines = minLines;
            changed = true;
        }
        if (this.maxLines != maxLines) {
            this.maxLines = maxLines;
            changed = true;
        }
        if (this.softWrap != softWrap) {
            this.softWrap = softWrap;
            changed = true;
        }
        if (!Intrinsics.areEqual(this.fontFamilyResolver, fontFamilyResolver)) {
            this.fontFamilyResolver = fontFamilyResolver;
            changed = true;
        }
        if (!TextOverflow.m8051equalsimpl0(this.overflow, overflow)) {
            this.overflow = overflow;
            return true;
        }
        return changed;
    }

    public final void doInvalidations(boolean drawChanged, boolean textChanged, boolean layoutChanged) {
        if (drawChanged || textChanged || layoutChanged) {
            this.resolvedInheritedStyle = null;
        }
        if (textChanged || layoutChanged) {
            getLayoutCache().m2004updateL6sJoHM(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
        }
        if (!getIsAttached()) {
            return;
        }
        if (textChanged || (drawChanged && this.semanticsTextLayoutResult != null)) {
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if (textChanged || layoutChanged) {
            LayoutModifierNodeKt.invalidateMeasurement(this);
            DrawModifierNodeKt.invalidateDraw(this);
        }
        if (drawChanged) {
            DrawModifierNodeKt.invalidateDraw(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$TextSubstitutionValue, reason: from toString */
    /* JADX INFO: compiled from: TextStringSimpleNode.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\b\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0017\u001a\u00020\u0003H\u0016J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\f\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode$TextSubstitutionValue;", "", "original", "", "substitution", "isShowingSubstitution", "", "layoutCache", "Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLandroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;)V", "getOriginal", "()Ljava/lang/String;", "getSubstitution", "setSubstitution", "(Ljava/lang/String;)V", "()Z", "setShowingSubstitution", "(Z)V", "getLayoutCache", "()Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "setLayoutCache", "(Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;)V", "toString", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final /* data */ class TextSubstitution {
        public static final int $stable = 8;
        private boolean isShowingSubstitution;
        private ParagraphLayoutCache layoutCache;
        private final String original;
        private String substitution;

        public static /* synthetic */ TextSubstitution copy$default(TextSubstitution textSubstitution, String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache, int i, Object obj) {
            if ((i & 1) != 0) {
                str = textSubstitution.original;
            }
            if ((i & 2) != 0) {
                str2 = textSubstitution.substitution;
            }
            if ((i & 4) != 0) {
                z = textSubstitution.isShowingSubstitution;
            }
            if ((i & 8) != 0) {
                paragraphLayoutCache = textSubstitution.layoutCache;
            }
            return textSubstitution.copy(str, str2, z, paragraphLayoutCache);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getOriginal() {
            return this.original;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSubstitution() {
            return this.substitution;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final ParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final TextSubstitution copy(String original, String substitution, boolean isShowingSubstitution, ParagraphLayoutCache layoutCache) {
            return new TextSubstitution(original, substitution, isShowingSubstitution, layoutCache);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TextSubstitution)) {
                return false;
            }
            TextSubstitution textSubstitution = (TextSubstitution) other;
            return Intrinsics.areEqual(this.original, textSubstitution.original) && Intrinsics.areEqual(this.substitution, textSubstitution.substitution) && this.isShowingSubstitution == textSubstitution.isShowingSubstitution && Intrinsics.areEqual(this.layoutCache, textSubstitution.layoutCache);
        }

        public int hashCode() {
            return (((((this.original.hashCode() * 31) + this.substitution.hashCode()) * 31) + Boolean.hashCode(this.isShowingSubstitution)) * 31) + (this.layoutCache == null ? 0 : this.layoutCache.hashCode());
        }

        public TextSubstitution(String original, String substitution, boolean isShowingSubstitution, ParagraphLayoutCache layoutCache) {
            this.original = original;
            this.substitution = substitution;
            this.isShowingSubstitution = isShowingSubstitution;
            this.layoutCache = layoutCache;
        }

        public /* synthetic */ TextSubstitution(String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : paragraphLayoutCache);
        }

        public final String getOriginal() {
            return this.original;
        }

        public final String getSubstitution() {
            return this.substitution;
        }

        public final void setSubstitution(String str) {
            this.substitution = str;
        }

        public final boolean isShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        public final void setShowingSubstitution(boolean z) {
            this.isShowingSubstitution = z;
        }

        public final ParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final void setLayoutCache(ParagraphLayoutCache paragraphLayoutCache) {
            this.layoutCache = paragraphLayoutCache;
        }

        public String toString() {
            return "TextSubstitution(layoutCache=" + this.layoutCache + ", isShowingSubstitution=" + this.isShowingSubstitution + ')';
        }
    }

    private final boolean setSubstitution(String updatedText) {
        TextSubstitution currentTextSubstitution = this.textSubstitution;
        if (currentTextSubstitution != null) {
            if (Intrinsics.areEqual(updatedText, currentTextSubstitution.getSubstitution())) {
                return false;
            }
            currentTextSubstitution.setSubstitution(updatedText);
            ParagraphLayoutCache layoutCache = currentTextSubstitution.getLayoutCache();
            if (layoutCache == null) {
                return false;
            }
            layoutCache.m2004updateL6sJoHM(updatedText, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
            return true;
        }
        TextSubstitution newTextSubstitution = new TextSubstitution(this.text, updatedText, false, null, 12, null);
        ParagraphLayoutCache substitutionLayoutCache = new ParagraphLayoutCache(updatedText, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, null);
        substitutionLayoutCache.setDensity$foundation(getLayoutCache().getDensity());
        newTextSubstitution.setLayoutCache(substitutionLayoutCache);
        this.textSubstitution = newTextSubstitution;
        return true;
    }

    private final void clearSubstitution() {
        this.textSubstitution = null;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        Function1<? super List<TextLayoutResult>, Boolean> function1 = this.semanticsTextLayoutResult;
        if (function1 == null) {
            function1 = new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(TextStringSimpleNode.applySemantics$lambda$0(this.f$0, (List) obj));
                }
            };
            this.semanticsTextLayoutResult = function1;
        }
        SemanticsPropertiesKt.setText($this$applySemantics, new AnnotatedString(this.text, null, 2, null));
        TextSubstitution currentTextSubstitution = this.textSubstitution;
        if (currentTextSubstitution != null) {
            SemanticsPropertiesKt.setShowingTextSubstitution($this$applySemantics, currentTextSubstitution.isShowingSubstitution());
            SemanticsPropertiesKt.setTextSubstitution($this$applySemantics, new AnnotatedString(currentTextSubstitution.getSubstitution(), null, 2, null));
        }
        SemanticsPropertiesKt.setTextSubstitution$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TextStringSimpleNode.applySemantics$lambda$1(this.f$0, (AnnotatedString) obj));
            }
        }, 1, null);
        SemanticsPropertiesKt.showTextSubstitution$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TextStringSimpleNode.applySemantics$lambda$2(this.f$0, ((Boolean) obj).booleanValue()));
            }
        }, 1, null);
        SemanticsPropertiesKt.clearTextSubstitution$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(TextStringSimpleNode.applySemantics$lambda$3(this.f$0));
            }
        }, 1, null);
        SemanticsPropertiesKt.getTextLayoutResult$default($this$applySemantics, null, function1, 1, null);
    }

    static final boolean applySemantics$lambda$0(TextStringSimpleNode this$0, List textLayoutResult) {
        ParagraphLayoutCache layoutCache = this$0.getLayoutCache();
        TextStyle textStyle = this$0.style;
        ColorProducer colorProducer = this$0.overrideColor;
        TextLayoutResult layout = layoutCache.slowCreateTextLayoutResultOrNull(TextStyle.m7593mergedA7vx0o$default(textStyle, colorProducer != null ? colorProducer.mo2472invoke0d7_KjU() : Color.INSTANCE.m5349getUnspecified0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, 0, 0, null, null, 16777214, null));
        if (layout != null) {
            textLayoutResult.add(layout);
        } else {
            layout = null;
        }
        return layout != null;
    }

    static final boolean applySemantics$lambda$1(TextStringSimpleNode this$0, AnnotatedString updatedText) {
        this$0.setSubstitution(updatedText.getText());
        this$0.invalidateForTranslate();
        return true;
    }

    static final boolean applySemantics$lambda$2(TextStringSimpleNode this$0, boolean it) {
        if (this$0.textSubstitution == null) {
            return false;
        }
        TextSubstitution textSubstitution = this$0.textSubstitution;
        if (textSubstitution != null) {
            textSubstitution.setShowingSubstitution(it);
        }
        this$0.invalidateForTranslate();
        return true;
    }

    static final boolean applySemantics$lambda$3(TextStringSimpleNode this$0) {
        this$0.clearSubstitution();
        this$0.invalidateForTranslate();
        return true;
    }

    private final void invalidateForTranslate() {
        SemanticsModifierNodeKt.invalidateSemantics(this);
        LayoutModifierNodeKt.invalidateMeasurement(this);
        DrawModifierNodeKt.invalidateDraw(this);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) throws Throwable {
        Trace.beginSection("TextStringSimpleNode::measure");
        try {
            ParagraphLayoutCache layoutCache = getLayoutCacheForMeasure($this$measure_u2d3p2s80s);
            boolean didChangeLayout = layoutCache.m2002layoutWithConstraintsK40F9xA(constraints, $this$measure_u2d3p2s80s.getLayoutDirection());
            layoutCache.getObserveFontChanges$foundation();
            Paragraph paragraph = layoutCache.getParagraph();
            Intrinsics.checkNotNull(paragraph);
            long layoutSize = layoutCache.getLayoutSize();
            if (didChangeLayout) {
                try {
                    LayoutModifierNodeKt.invalidateLayer(this);
                    Map<AlignmentLine, Integer> map = this.baselineCache;
                    if (map == null) {
                        map = new HashMap(2);
                        this.baselineCache = map;
                    }
                    HorizontalAlignmentLine firstBaseline = AlignmentLineKt.getFirstBaseline();
                    float $this$fastRoundToInt$iv = paragraph.getFirstBaseline();
                    map.put(firstBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv)));
                    HorizontalAlignmentLine lastBaseline = AlignmentLineKt.getLastBaseline();
                    float $this$fastRoundToInt$iv2 = paragraph.getLastBaseline();
                    map.put(lastBaseline, Integer.valueOf(Math.round($this$fastRoundToInt$iv2)));
                    try {
                        final Placeable placeable = measurable.mo6783measureBRTryo0(Constraints.INSTANCE.m8112fitPrioritizingWidthZbe2FdA((int) (layoutSize >> 32), (int) (layoutSize >> 32), (int) (layoutSize & 4294967295L), (int) (layoutSize & 4294967295L)));
                        int $i$f$unpackInt1 = (int) (layoutSize >> 32);
                        Map<AlignmentLine, Integer> map2 = this.baselineCache;
                        Intrinsics.checkNotNull(map2);
                        MeasureResult measureResultLayout = $this$measure_u2d3p2s80s.layout($i$f$unpackInt1, (int) (layoutSize & 4294967295L), map2, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return TextStringSimpleNode.measure_3p2s80s$lambda$0$0(placeable, (Placeable.PlacementScope) obj);
                            }
                        });
                        Trace.endSection();
                        return measureResultLayout;
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                final Placeable placeable2 = measurable.mo6783measureBRTryo0(Constraints.INSTANCE.m8112fitPrioritizingWidthZbe2FdA((int) (layoutSize >> 32), (int) (layoutSize >> 32), (int) (layoutSize & 4294967295L), (int) (layoutSize & 4294967295L)));
                int $i$f$unpackInt12 = (int) (layoutSize >> 32);
                Map<AlignmentLine, Integer> map22 = this.baselineCache;
                Intrinsics.checkNotNull(map22);
                MeasureResult measureResultLayout2 = $this$measure_u2d3p2s80s.layout($i$f$unpackInt12, (int) (layoutSize & 4294967295L), map22, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextStringSimpleNode.measure_3p2s80s$lambda$0$0(placeable2, (Placeable.PlacementScope) obj);
                    }
                });
                Trace.endSection();
                return measureResultLayout2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        Trace.endSection();
        throw th;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure_3p2s80s$lambda$0$0(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        return getLayoutCacheForMeasure($this$minIntrinsicWidth).minIntrinsicWidth($this$minIntrinsicWidth.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        return getLayoutCacheForMeasure($this$minIntrinsicHeight).intrinsicHeight(width, $this$minIntrinsicHeight.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        return getLayoutCacheForMeasure($this$maxIntrinsicWidth).maxIntrinsicWidth($this$maxIntrinsicWidth.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        return getLayoutCacheForMeasure($this$maxIntrinsicHeight).intrinsicHeight(width, $this$maxIntrinsicHeight.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) {
        TextStyle style;
        long color;
        if (!getIsAttached()) {
            return;
        }
        ParagraphLayoutCache layoutCache = getLayoutCacheOrSubstitute();
        Paragraph localParagraph = layoutCache.getParagraph();
        if (localParagraph != null) {
            ContentDrawScope $this$drawIntoCanvas$iv = $this$draw;
            Canvas canvas = $this$drawIntoCanvas$iv.getDrawContext().getCanvas();
            boolean willClip = layoutCache.getDidOverflow();
            if (willClip) {
                long arg0$iv = layoutCache.getLayoutSize();
                float width = (int) (arg0$iv >> 32);
                long arg0$iv2 = layoutCache.getLayoutSize();
                float height = (int) (4294967295L & arg0$iv2);
                canvas.save();
                Canvas.m5285clipRectN_I0leg$default(canvas, 0.0f, 0.0f, width, height, 0, 16, null);
            }
            try {
                if (ComposeFoundationFlags.isInheritedTextStyleEnabled) {
                    m2018resolveInheritedStyleuwmK9pY(StylePhase.INSTANCE.m2014getDrawoWBPZag());
                    style = this.resolvedInheritedStyle;
                    if (style == null) {
                        style = this.style;
                    }
                } else {
                    style = this.style;
                }
                TextDecoration textDecoration = style.getTextDecoration();
                if (textDecoration == null) {
                    textDecoration = TextDecoration.INSTANCE.getNone();
                }
                TextDecoration textDecoration2 = textDecoration;
                Shadow shadow = style.getShadow();
                if (shadow == null) {
                    shadow = Shadow.INSTANCE.getNone();
                }
                Shadow shadow2 = shadow;
                Fill drawStyle = style.getDrawStyle();
                if (drawStyle == null) {
                    drawStyle = Fill.INSTANCE;
                }
                DrawStyle drawStyle2 = drawStyle;
                Brush brush = style.getBrush();
                if (brush == null) {
                    ColorProducer colorProducer = this.overrideColor;
                    long $this$isSpecified$iv = colorProducer != null ? colorProducer.mo2472invoke0d7_KjU() : Color.INSTANCE.m5349getUnspecified0d7_KjU();
                    long overrideColorVal = $this$isSpecified$iv;
                    boolean z = true;
                    if ($this$isSpecified$iv != 16) {
                        color = overrideColorVal;
                    } else {
                        if (style.m7603getColor0d7_KjU() == 16) {
                            z = false;
                        }
                        if (z) {
                            color = style.m7603getColor0d7_KjU();
                        } else {
                            color = Color.INSTANCE.m5339getBlack0d7_KjU();
                        }
                    }
                    Paragraph.m7441paintLG529CI$default(localParagraph, canvas, color, shadow2, textDecoration2, drawStyle2, 0, 32, null);
                } else {
                    float alpha = style.getAlpha();
                    Paragraph.m7443painthn5TExg$default(localParagraph, canvas, brush, alpha, shadow2, textDecoration2, drawStyle2, 0, 64, null);
                }
                if (willClip) {
                    return;
                } else {
                    return;
                }
            } finally {
                if (willClip) {
                    canvas.restore();
                }
            }
        }
        InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("Internal Error: ParagraphLayoutCache could not provide a Paragraph during the draw phase. Please report this bug on the official Issue Tracker with the following diagnostic information: (layoutCache=" + this._layoutCache + ", textSubstitution=" + this.textSubstitution + ')');
        throw new KotlinNothingValueException();
    }
}
