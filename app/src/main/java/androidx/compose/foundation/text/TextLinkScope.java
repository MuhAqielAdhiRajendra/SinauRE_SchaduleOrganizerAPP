package androidx.compose.foundation.text;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.UriHandler;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.LinkInteractionListener;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextLinkStyles;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextLinkScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u001f\u001a\u00020 *\u00020 2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$H\u0002J\u001e\u0010%\u001a\u00020 *\u00020 2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$H\u0002J\u001c\u0010&\u001a\u0004\u0018\u00010'2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$H\u0002J\u001c\u0010(\u001a\u0004\u0018\u00010)2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$H\u0002J0\u0010*\u001a\u0010\u0012\u0004\u0012\u00020#\u0018\u00010\"j\u0004\u0018\u0001`$2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$2\u0006\u0010\n\u001a\u00020\tH\u0002J\r\u0010+\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010,J\u001a\u0010-\u001a\u0004\u0018\u00010.*\u0004\u0018\u00010.2\b\u0010/\u001a\u0004\u0018\u00010.H\u0002J\u0018\u00100\u001a\u00020\u00182\u0006\u0010!\u001a\u00020#2\u0006\u00101\u001a\u000202H\u0002J\r\u00103\u001a\u00020\u0003H\u0000¢\u0006\u0002\b4J>\u00105\u001a\u00020\u00182\u0016\u00106\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000107\"\u0004\u0018\u00010\u00012\u0017\u00108\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\u0019H\u0003¢\u0006\u0002\u00109R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R/\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0011\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0007\"\u0004\b\u0013\u0010\u0005R%\u0010\u0014\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\u00190\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006:"}, d2 = {"Landroidx/compose/foundation/text/TextLinkScope;", "", "initialText", "Landroidx/compose/ui/text/AnnotatedString;", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;)V", "getInitialText$foundation", "()Landroidx/compose/ui/text/AnnotatedString;", "<set-?>", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "getTextLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "setTextLayoutResult", "(Landroidx/compose/ui/text/TextLayoutResult;)V", "textLayoutResult$delegate", "Landroidx/compose/runtime/MutableState;", "text", "getText$foundation", "setText$foundation", "annotators", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/TextAnnotatorScope;", "", "Lkotlin/ExtensionFunctionType;", "shouldMeasureLinks", "Lkotlin/Function0;", "", "getShouldMeasureLinks", "()Lkotlin/jvm/functions/Function0;", "textRange", "Landroidx/compose/ui/Modifier;", "link", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/LinkAnnotation;", "Landroidx/compose/foundation/text/LinkRange;", "clipLink", "shapeForRange", "Landroidx/compose/ui/graphics/Shape;", "pathForRangeInRangeCoordinates", "Landroidx/compose/ui/graphics/Path;", "calculateVisibleLinkRange", "LinksComposables", "(Landroidx/compose/runtime/Composer;I)V", "mergeOrUse", "Landroidx/compose/ui/text/SpanStyle;", "other", "handleLink", "uriHandler", "Landroidx/compose/ui/platform/UriHandler;", "applyAnnotators", "applyAnnotators$foundation", "StyleAnnotation", "keys", "", "block", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextLinkScope {
    public static final int $stable = 8;
    private final AnnotatedString initialText;
    private AnnotatedString text;

    /* JADX INFO: renamed from: textLayoutResult$delegate, reason: from kotlin metadata */
    private final MutableState textLayoutResult = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    private final SnapshotStateList<Function1<TextAnnotatorScope, Unit>> annotators = SnapshotStateKt.mutableStateListOf();

    static final Unit LinksComposables$lambda$1(TextLinkScope textLinkScope, int i, Composer composer, int i2) {
        textLinkScope.LinksComposables(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit StyleAnnotation$lambda$1(TextLinkScope textLinkScope, Object[] objArr, Function1 function1, int i, Composer composer, int i2) {
        textLinkScope.StyleAnnotation(objArr, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public TextLinkScope(AnnotatedString initialText) {
        this.initialText = initialText;
        this.text = this.initialText.flatMapAnnotations(new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextLinkScope._init_$lambda$0((AnnotatedString.Range) obj);
            }
        });
    }

    /* JADX INFO: renamed from: getInitialText$foundation, reason: from getter */
    public final AnnotatedString getInitialText() {
        return this.initialText;
    }

    public final TextLayoutResult getTextLayoutResult() {
        State $this$getValue$iv = this.textLayoutResult;
        return (TextLayoutResult) $this$getValue$iv.getValue();
    }

    public final void setTextLayoutResult(TextLayoutResult textLayoutResult) {
        MutableState $this$setValue$iv = this.textLayoutResult;
        $this$setValue$iv.setValue(textLayoutResult);
    }

    /* JADX INFO: renamed from: getText$foundation, reason: from getter */
    public final AnnotatedString getText() {
        return this.text;
    }

    public final void setText$foundation(AnnotatedString annotatedString) {
        this.text = annotatedString;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final java.util.List _init_$lambda$0(androidx.compose.ui.text.AnnotatedString.Range r27) {
        /*
            java.lang.Object r0 = r27.getItem()
            boolean r0 = r0 instanceof androidx.compose.ui.text.LinkAnnotation
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L76
            java.lang.Object r0 = r27.getItem()
            java.lang.String r3 = "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r3)
            androidx.compose.ui.text.LinkAnnotation r0 = (androidx.compose.ui.text.LinkAnnotation) r0
            androidx.compose.ui.text.TextLinkStyles r0 = r0.getStyles()
            boolean r0 = androidx.compose.foundation.text.TextLinkScopeKt.access$isNullOrEmpty(r0)
            if (r0 != 0) goto L76
            r0 = 2
            androidx.compose.ui.text.AnnotatedString$Range[] r0 = new androidx.compose.ui.text.AnnotatedString.Range[r0]
            r0[r1] = r27
            androidx.compose.ui.text.AnnotatedString$Range r1 = new androidx.compose.ui.text.AnnotatedString$Range
            java.lang.Object r4 = r27.getItem()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r3)
            androidx.compose.ui.text.LinkAnnotation r4 = (androidx.compose.ui.text.LinkAnnotation) r4
            androidx.compose.ui.text.TextLinkStyles r3 = r4.getStyles()
            if (r3 == 0) goto L3d
            androidx.compose.ui.text.SpanStyle r3 = r3.getStyle()
            if (r3 != 0) goto L63
        L3d:
            androidx.compose.ui.text.SpanStyle r4 = new androidx.compose.ui.text.SpanStyle
            r25 = 65535(0xffff, float:9.1834E-41)
            r26 = 0
            r5 = 0
            r7 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r4.<init>(r5, r7, r9, r10, r11, r12, r13, r14, r16, r17, r18, r19, r21, r22, r23, r24, r25, r26)
            r3 = r4
        L63:
            int r4 = r27.getStart()
            int r5 = r27.getEnd()
            r1.<init>(r3, r4, r5)
            r0[r2] = r1
            java.util.ArrayList r0 = kotlin.collections.CollectionsKt.arrayListOf(r0)
            goto L7e
        L76:
            androidx.compose.ui.text.AnnotatedString$Range[] r0 = new androidx.compose.ui.text.AnnotatedString.Range[r2]
            r0[r1] = r27
            java.util.ArrayList r0 = kotlin.collections.CollectionsKt.arrayListOf(r0)
        L7e:
            java.util.List r0 = (java.util.List) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextLinkScope._init_$lambda$0(androidx.compose.ui.text.AnnotatedString$Range):java.util.List");
    }

    static final boolean _get_shouldMeasureLinks_$lambda$0(TextLinkScope this$0) {
        TextLayoutInput layoutInput;
        AnnotatedString annotatedString = this$0.text;
        TextLayoutResult textLayoutResult = this$0.getTextLayoutResult();
        return Intrinsics.areEqual(annotatedString, (textLayoutResult == null || (layoutInput = textLayoutResult.getLayoutInput()) == null) ? null : layoutInput.getText());
    }

    public final Function0<Boolean> getShouldMeasureLinks() {
        return new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(TextLinkScope._get_shouldMeasureLinks_$lambda$0(this.f$0));
            }
        };
    }

    private final Modifier textRange(Modifier $this$textRange, final AnnotatedString.Range<LinkAnnotation> range) {
        return $this$textRange.then(new TextRangeLayoutModifier(new TextRangeScopeMeasurePolicy() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda12
            @Override // androidx.compose.foundation.text.TextRangeScopeMeasurePolicy
            public final TextRangeLayoutMeasureResult measure(TextRangeLayoutMeasureScope textRangeLayoutMeasureScope) {
                return TextLinkScope.textRange$lambda$0(this.f$0, range, textRangeLayoutMeasureScope);
            }
        }));
    }

    static final TextRangeLayoutMeasureResult textRange$lambda$0(TextLinkScope this$0, AnnotatedString.Range $link, TextRangeLayoutMeasureScope $this$TextRangeLayoutModifier) {
        TextLayoutResult layoutResult = this$0.getTextLayoutResult();
        if (layoutResult == null) {
            return $this$TextRangeLayoutModifier.layout(0, 0, new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TextLinkScope.textRange$lambda$0$0();
                }
            });
        }
        AnnotatedString.Range<LinkAnnotation> rangeCalculateVisibleLinkRange = this$0.calculateVisibleLinkRange($link, layoutResult);
        if (rangeCalculateVisibleLinkRange == null) {
            return $this$TextRangeLayoutModifier.layout(0, 0, new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TextLinkScope.textRange$lambda$0$1();
                }
            });
        }
        final IntRect bounds = IntRectKt.roundToIntRect(layoutResult.getPathForRange(rangeCalculateVisibleLinkRange.getStart(), rangeCalculateVisibleLinkRange.getEnd()).getBounds());
        return $this$TextRangeLayoutModifier.layout(bounds.getWidth(), bounds.getHeight(), new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextLinkScope.textRange$lambda$0$2(bounds);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset textRange$lambda$0$0() {
        return IntOffset.m8269boximpl(IntOffset.INSTANCE.m8289getZeronOccac());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset textRange$lambda$0$1() {
        return IntOffset.m8269boximpl(IntOffset.INSTANCE.m8289getZeronOccac());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset textRange$lambda$0$2(IntRect $bounds) {
        return IntOffset.m8269boximpl($bounds.m8307getTopLeftnOccac());
    }

    private final Modifier clipLink(Modifier $this$clipLink, final AnnotatedString.Range<LinkAnnotation> range) {
        return GraphicsLayerModifierKt.graphicsLayer($this$clipLink, new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextLinkScope.clipLink$lambda$0(this.f$0, range, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit clipLink$lambda$0(TextLinkScope this$0, AnnotatedString.Range $link, GraphicsLayerScope $this$graphicsLayer) {
        Shape linkShape = this$0.shapeForRange($link);
        if (linkShape != null) {
            $this$graphicsLayer.setShape(linkShape);
            $this$graphicsLayer.setClip(true);
        }
        return Unit.INSTANCE;
    }

    private final Shape shapeForRange(AnnotatedString.Range<LinkAnnotation> link) {
        final Path it = pathForRangeInRangeCoordinates(link);
        return it != null ? new Shape() { // from class: androidx.compose.foundation.text.TextLinkScope$shapeForRange$1$1
            @Override // androidx.compose.ui.graphics.Shape
            /* JADX INFO: renamed from: createOutline-Pq9zytI */
            public Outline mo342createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                return new Outline.Generic(it);
            }
        } : null;
    }

    private final Path pathForRangeInRangeCoordinates(AnnotatedString.Range<LinkAnnotation> link) {
        TextLayoutResult it;
        AnnotatedString.Range<LinkAnnotation> rangeCalculateVisibleLinkRange;
        float xOffset;
        if (!getShouldMeasureLinks().invoke().booleanValue() || (it = getTextLayoutResult()) == null || (rangeCalculateVisibleLinkRange = calculateVisibleLinkRange(link, it)) == null) {
            return null;
        }
        Path path = it.getPathForRange(rangeCalculateVisibleLinkRange.getStart(), rangeCalculateVisibleLinkRange.getEnd());
        Rect firstCharBoundingBox = it.getBoundingBox(rangeCalculateVisibleLinkRange.getStart());
        Rect lastCharBoundingBox = it.getBoundingBox(rangeCalculateVisibleLinkRange.getEnd() - 1);
        int rangeStartLine = it.getLineForOffset(rangeCalculateVisibleLinkRange.getStart());
        int rangeEndLine = it.getLineForOffset(rangeCalculateVisibleLinkRange.getEnd() - 1);
        if (rangeStartLine == rangeEndLine) {
            xOffset = Math.min(lastCharBoundingBox.getLeft(), firstCharBoundingBox.getLeft());
        } else {
            xOffset = 0.0f;
        }
        float yOffset = firstCharBoundingBox.getTop();
        float x$iv = xOffset;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v1$iv$iv2 = Float.floatToRawIntBits(yOffset);
        long v2$iv$iv = (v1$iv$iv << 32) | (v1$iv$iv2 & 4294967295L);
        long arg0$iv = Offset.m5060constructorimpl(v2$iv$iv);
        path.mo5205translatek4lQ0M(Offset.m5060constructorimpl((-9223372034707292160L) ^ arg0$iv));
        return path;
    }

    private final AnnotatedString.Range<LinkAnnotation> calculateVisibleLinkRange(AnnotatedString.Range<LinkAnnotation> link, TextLayoutResult textLayoutResult) {
        int lastOffset = TextLayoutResult.getLineEnd$default(textLayoutResult, textLayoutResult.getLineCount() - 1, false, 2, null);
        if (link.getStart() < lastOffset) {
            return AnnotatedString.Range.copy$default(link, null, 0, Math.min(link.getEnd(), lastOffset), null, 11, null);
        }
        return null;
    }

    public final void LinksComposables(Composer $composer, final int $changed) {
        UriHandler uriHandler;
        int $dirty;
        List<AnnotatedString.Range<LinkAnnotation>> list;
        List<AnnotatedString.Range<LinkAnnotation>> list2;
        int $i$f$fastForEach;
        MutableInteractionSource interactionSource;
        SpanStyle pressedStyle;
        Composer $composer2 = $composer.startRestartGroup(1154651354);
        ComposerKt.sourceInformation($composer2, "C(LinksComposables)215@9182L7:TextLinkScope.kt#423gt5");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(this) ? 4 : 2;
        }
        if ($composer2.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1154651354, $dirty2, -1, "androidx.compose.foundation.text.TextLinkScope.LinksComposables (TextLinkScope.kt:214)");
            }
            ProvidableCompositionLocal<UriHandler> localUriHandler = CompositionLocalsKt.getLocalUriHandler();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localUriHandler);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final UriHandler uriHandler2 = (UriHandler) objConsume;
            List<AnnotatedString.Range<LinkAnnotation>> linkAnnotations = this.text.getLinkAnnotations(0, this.text.length());
            List<AnnotatedString.Range<LinkAnnotation>> list3 = linkAnnotations;
            int $i$f$fastForEach2 = 0;
            int index$iv = 0;
            int size = list3.size();
            while (index$iv < size) {
                AnnotatedString.Range<LinkAnnotation> item$iv = list3.get(index$iv);
                final AnnotatedString.Range<LinkAnnotation> range = item$iv;
                if (range.getStart() != range.getEnd()) {
                    $composer2.startReplaceGroup(725478935);
                    ComposerKt.sourceInformation($composer2, "220@9372L39,224@9514L180,234@10047L38,222@9429L701");
                    ComposerKt.sourceInformationMarkerStart($composer2, 854684939, "CC(remember):TextLinkScope.kt#9igjgp");
                    Object it$iv = $composer2.rememberedValue();
                    $dirty = $dirty2;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        list = linkAnnotations;
                        $composer2.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    } else {
                        list = linkAnnotations;
                    }
                    MutableInteractionSource interactionSource2 = (MutableInteractionSource) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierClipLink = clipLink(Modifier.INSTANCE, range);
                    ComposerKt.sourceInformationMarkerStart($composer2, 854689624, "CC(remember):TextLinkScope.kt#9igjgp");
                    Object it$iv2 = $composer2.rememberedValue();
                    list2 = list3;
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return TextLinkScope.LinksComposables$lambda$0$1$0((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $i$f$fastForEach = $i$f$fastForEach2;
                    Modifier modifierPointerHoverIcon$default = PointerIconKt.pointerHoverIcon$default(HoverableKt.hoverable$default(textRange(SemanticsModifierKt.semantics$default(modifierClipLink, false, (Function1) it$iv2, 1, null), range), interactionSource2, false, 2, null), PointerIcon.INSTANCE.getHand(), false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 854706538, "CC(remember):TextLinkScope.kt#9igjgp");
                    boolean invalid$iv = $composer2.changedInstance(this) | $composer2.changed(range) | $composer2.changedInstance(uriHandler2);
                    Object it$iv3 = $composer2.rememberedValue();
                    if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                        interactionSource = interactionSource2;
                        Object value$iv3 = new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return TextLinkScope.LinksComposables$lambda$0$2$0(this.f$0, range, uriHandler2);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                        it$iv3 = value$iv3;
                    } else {
                        interactionSource = interactionSource2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    BoxKt.Box(ClickableKt.m325combinedClickableauXiCPI$default(modifierPointerHoverIcon$default, interactionSource, null, false, null, null, null, null, null, false, (Function0) it$iv3, TypedValues.PositionType.TYPE_CURVE_FIT, null), $composer2, 0);
                    if (TextLinkScopeKt.isNullOrEmpty(range.getItem().getStyles())) {
                        uriHandler = uriHandler2;
                        $composer2.startReplaceGroup(728331710);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(726303039);
                        ComposerKt.sourceInformation($composer2, "243@10493L110,246@10645L51,246@10624L72,256@11137L1144,248@10718L1563");
                        ComposerKt.sourceInformationMarkerStart($composer2, 854720882, "CC(remember):TextLinkScope.kt#9igjgp");
                        Object it$iv4 = $composer2.rememberedValue();
                        if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                            uriHandler = uriHandler2;
                            Object value$iv4 = new LinkStateInteractionSourceObserver(interactionSource);
                            $composer2.updateRememberedValue(value$iv4);
                            it$iv4 = value$iv4;
                        } else {
                            uriHandler = uriHandler2;
                        }
                        final LinkStateInteractionSourceObserver linkStateObserver = (LinkStateInteractionSourceObserver) it$iv4;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        Unit unit = Unit.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer2, 854725687, "CC(remember):TextLinkScope.kt#9igjgp");
                        Object it$iv5 = $composer2.rememberedValue();
                        if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                            pressedStyle = null;
                            Object value$iv5 = (Function2) new TextLinkScope$LinksComposables$1$3$1(linkStateObserver, null);
                            $composer2.updateRememberedValue(value$iv5);
                            it$iv5 = value$iv5;
                        } else {
                            pressedStyle = null;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv5, $composer2, 6);
                        Boolean boolValueOf = Boolean.valueOf(linkStateObserver.isHovered());
                        Boolean boolValueOf2 = Boolean.valueOf(linkStateObserver.isFocused());
                        Boolean boolValueOf3 = Boolean.valueOf(linkStateObserver.isPressed());
                        TextLinkStyles styles = range.getItem().getStyles();
                        SpanStyle style = styles != null ? styles.getStyle() : pressedStyle;
                        TextLinkStyles styles2 = range.getItem().getStyles();
                        SpanStyle focusedStyle = styles2 != null ? styles2.getFocusedStyle() : pressedStyle;
                        TextLinkStyles styles3 = range.getItem().getStyles();
                        SpanStyle hoveredStyle = styles3 != null ? styles3.getHoveredStyle() : pressedStyle;
                        TextLinkStyles styles4 = range.getItem().getStyles();
                        if (styles4 != null) {
                            pressedStyle = styles4.getPressedStyle();
                        }
                        Object[] objArr = {boolValueOf, boolValueOf2, boolValueOf3, style, focusedStyle, hoveredStyle, pressedStyle};
                        ComposerKt.sourceInformationMarkerStart($composer2, 854742524, "CC(remember):TextLinkScope.kt#9igjgp");
                        boolean invalid$iv2 = $composer2.changedInstance(this) | $composer2.changed(range);
                        Object it$iv6 = $composer2.rememberedValue();
                        if (invalid$iv2 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv6 = new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda9
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return TextLinkScope.LinksComposables$lambda$0$5$0(this.f$0, range, linkStateObserver, (TextAnnotatorScope) obj);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv6);
                            it$iv6 = value$iv6;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        StyleAnnotation(objArr, (Function1) it$iv6, $composer2, ($dirty << 6) & 896);
                        $composer2.endReplaceGroup();
                    }
                    $composer2.endReplaceGroup();
                } else {
                    uriHandler = uriHandler2;
                    $dirty = $dirty2;
                    list = linkAnnotations;
                    list2 = list3;
                    $i$f$fastForEach = $i$f$fastForEach2;
                    $composer2.startReplaceGroup(728345598);
                    $composer2.endReplaceGroup();
                }
                index$iv++;
                $i$f$fastForEach2 = $i$f$fastForEach;
                uriHandler2 = uriHandler;
                linkAnnotations = list;
                $dirty2 = $dirty;
                list3 = list2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextLinkScope.LinksComposables$lambda$1(this.f$0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinksComposables$lambda$0$1$0(SemanticsPropertyReceiver $this$semantics) {
        $this$semantics.set(SemanticsProperties.INSTANCE.getLinkTestMarker(), Unit.INSTANCE);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinksComposables$lambda$0$2$0(TextLinkScope this$0, AnnotatedString.Range $range, UriHandler $uriHandler) {
        this$0.handleLink((LinkAnnotation) $range.getItem(), $uriHandler);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinksComposables$lambda$0$5$0(TextLinkScope this$0, AnnotatedString.Range $range, LinkStateInteractionSourceObserver $linkStateObserver, TextAnnotatorScope $this$StyleAnnotation) {
        TextLinkStyles styles;
        TextLinkStyles styles2;
        TextLinkStyles styles3;
        TextLinkStyles styles4 = ((LinkAnnotation) $range.getItem()).getStyles();
        SpanStyle pressedStyle = null;
        SpanStyle style = styles4 != null ? styles4.getStyle() : null;
        SpanStyle focusedStyle = (!$linkStateObserver.isFocused() || (styles3 = ((LinkAnnotation) $range.getItem()).getStyles()) == null) ? null : styles3.getFocusedStyle();
        SpanStyle spanStyleMergeOrUse = this$0.mergeOrUse(style, focusedStyle);
        SpanStyle hoveredStyle = (!$linkStateObserver.isHovered() || (styles2 = ((LinkAnnotation) $range.getItem()).getStyles()) == null) ? null : styles2.getHoveredStyle();
        SpanStyle spanStyleMergeOrUse2 = this$0.mergeOrUse(spanStyleMergeOrUse, hoveredStyle);
        if ($linkStateObserver.isPressed() && (styles = ((LinkAnnotation) $range.getItem()).getStyles()) != null) {
            pressedStyle = styles.getPressedStyle();
        }
        SpanStyle mergedStyle = this$0.mergeOrUse(spanStyleMergeOrUse2, pressedStyle);
        $this$StyleAnnotation.replaceStyle($range, mergedStyle);
        return Unit.INSTANCE;
    }

    private final SpanStyle mergeOrUse(SpanStyle $this$mergeOrUse, SpanStyle other) {
        SpanStyle spanStyleMerge;
        return ($this$mergeOrUse == null || (spanStyleMerge = $this$mergeOrUse.merge(other)) == null) ? other : spanStyleMerge;
    }

    private final void handleLink(LinkAnnotation link, UriHandler uriHandler) {
        LinkInteractionListener linkInteractionListener;
        if (link instanceof LinkAnnotation.Url) {
            LinkInteractionListener linkInteractionListener2 = ((LinkAnnotation.Url) link).getLinkInteractionListener();
            if (linkInteractionListener2 == null) {
                try {
                    uriHandler.openUri(((LinkAnnotation.Url) link).getUrl());
                    return;
                } catch (IllegalArgumentException e) {
                    return;
                }
            } else {
                linkInteractionListener2.onClick(link);
                return;
            }
        }
        if (!(link instanceof LinkAnnotation.Clickable) || (linkInteractionListener = ((LinkAnnotation.Clickable) link).getLinkInteractionListener()) == null) {
            return;
        }
        linkInteractionListener.onClick(link);
    }

    public final AnnotatedString applyAnnotators$foundation() {
        AnnotatedString styledText;
        if (this.annotators.isEmpty()) {
            styledText = this.text;
        } else {
            TextAnnotatorScope scope = new TextAnnotatorScope(this.text);
            List $this$fastForEach$iv = this.annotators;
            int size = $this$fastForEach$iv.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                ((Function1) item$iv).invoke(scope);
            }
            styledText = scope.getStyledText();
        }
        this.text = styledText;
        return styledText;
    }

    private final void StyleAnnotation(final Object[] keys, final Function1<? super TextAnnotatorScope, Unit> function1, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-2083052099);
        ComposerKt.sourceInformation($composer2, "C(StyleAnnotation)N(keys,block)316@13856L89,316@13825L120:TextLinkScope.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(this) ? 256 : 128;
        }
        $composer2.startMovableGroup(-358306546, Integer.valueOf(keys.length));
        int $dirty2 = $dirty | ($composer2.changed(keys.length) ? 4 : 0);
        for (Object value : keys) {
            $dirty2 |= $composer2.changedInstance(value) ? 4 : 0;
        }
        $composer2.endMovableGroup();
        if (($dirty2 & 14) == 0) {
            $dirty2 |= 2;
        }
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2083052099, $dirty2, -1, "androidx.compose.foundation.text.TextLinkScope.StyleAnnotation (TextLinkScope.kt:315)");
            }
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(function1);
            spreadBuilder.addSpread(keys);
            Object[] array = spreadBuilder.toArray(new Object[spreadBuilder.size()]);
            ComposerKt.sourceInformationMarkerStart($composer2, -358303338, "CC(remember):TextLinkScope.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(this) | (($dirty2 & 112) == 32);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextLinkScope.StyleAnnotation$lambda$0$0(this.f$0, function1, (DisposableEffectScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(array, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextLinkScope.StyleAnnotation$lambda$1(this.f$0, keys, function1, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult StyleAnnotation$lambda$0$0(final TextLinkScope this$0, final Function1 $block, DisposableEffectScope $this$DisposableEffect) {
        this$0.annotators.add($block);
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.TextLinkScope$StyleAnnotation$lambda$0$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                this.this$0.annotators.remove($block);
            }
        };
    }
}
