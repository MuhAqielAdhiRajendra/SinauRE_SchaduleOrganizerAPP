package androidx.compose.foundation.text;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: AnnotatedStringResolveInlineContent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a_\u0010\b\u001a?\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u000b0\n\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00070\u0001j\u0002`\f0\n0\t*\u00020\r2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\rH\u0000\u001a>\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\r2'\u0010\u0015\u001a#\u0012\u001f\u0012\u001d\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00070\u0001j\u0002`\f0\nH\u0001¢\u0006\u0002\u0010\u0016\"K\u0010\u0017\u001a?\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u000b0\n\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00070\u0001j\u0002`\f0\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000*\u0018\b\u0000\u0010\u0000\"\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0001*:\b\u0000\u0010\u0003\"\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00070\u00012\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00070\u0001¨\u0006\u0018"}, d2 = {"PlaceholderRange", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "InlineContentRange", "Lkotlin/Function1;", "", "", "Landroidx/compose/runtime/Composable;", "resolveInlineContent", "Lkotlin/Pair;", "", "Landroidx/compose/foundation/text/PlaceholderRange;", "Landroidx/compose/foundation/text/InlineContentRange;", "Landroidx/compose/ui/text/AnnotatedString;", "inlineContent", "", "Landroidx/compose/foundation/text/InlineTextContent;", "hasInlineContent", "", "InlineChildren", "text", "inlineContents", "(Landroidx/compose/ui/text/AnnotatedString;Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "EmptyInlineContent", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AnnotatedStringResolveInlineContentKt {
    private static final Pair<List<AnnotatedString.Range<Placeholder>>, List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>>> EmptyInlineContent = new Pair<>(CollectionsKt.emptyList(), CollectionsKt.emptyList());

    static final Unit InlineChildren$lambda$1(AnnotatedString annotatedString, List list, int i, Composer composer, int i2) {
        InlineChildren(annotatedString, list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final Pair<List<AnnotatedString.Range<Placeholder>>, List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>>> resolveInlineContent(AnnotatedString $this$resolveInlineContent, Map<String, InlineTextContent> map) {
        List<AnnotatedString.Range<String>> list;
        Map<String, InlineTextContent> map2 = map;
        if (map2 == null || map2.isEmpty()) {
            return EmptyInlineContent;
        }
        List<AnnotatedString.Range<String>> stringAnnotations = $this$resolveInlineContent.getStringAnnotations(InlineTextContentKt.INLINE_CONTENT_TAG, 0, $this$resolveInlineContent.getText().length());
        List placeholders = new ArrayList();
        List inlineComposables = new ArrayList();
        int index$iv = 0;
        int size = stringAnnotations.size();
        while (index$iv < size) {
            Object item$iv = stringAnnotations.get(index$iv);
            AnnotatedString.Range<String> range = (AnnotatedString.Range) item$iv;
            InlineTextContent inlineTextContent = map2.get(range.getItem());
            if (inlineTextContent == null) {
                list = stringAnnotations;
            } else {
                list = stringAnnotations;
                placeholders.add(new AnnotatedString.Range(inlineTextContent.getPlaceholder(), range.getStart(), range.getEnd()));
                inlineComposables.add(new AnnotatedString.Range(inlineTextContent.getChildren(), range.getStart(), range.getEnd()));
            }
            index$iv++;
            map2 = map;
            stringAnnotations = list;
        }
        return new Pair<>(placeholders, inlineComposables);
    }

    public static final boolean hasInlineContent(AnnotatedString $this$hasInlineContent) {
        return $this$hasInlineContent.hasStringAnnotations(InlineTextContentKt.INLINE_CONTENT_TAG, 0, $this$hasInlineContent.getText().length());
    }

    public static final void InlineChildren(final AnnotatedString text, final List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> list, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(-1794596951);
        ComposerKt.sourceInformation($composer3, "C(InlineChildren)N(text,inlineContents)*69@2714L269,69@2649L334:AnnotatedStringResolveInlineContent.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(list) ? 32 : 16;
        }
        if ($composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1794596951, $dirty, -1, "androidx.compose.foundation.text.InlineChildren (AnnotatedStringResolveInlineContent.kt:67)");
            }
            List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> list2 = list;
            int $i$f$fastForEach = 0;
            int index$iv = 0;
            int size = list2.size();
            while (index$iv < size) {
                Object item$iv = list2.get(index$iv);
                AnnotatedString.Range<Function3<String, Composer, Integer, Unit>> range = (AnnotatedString.Range) item$iv;
                Function3<String, Composer, Integer, Unit> function3Component1 = range.component1();
                int start = range.getStart();
                int end = range.getEnd();
                ComposerKt.sourceInformationMarkerStart($composer3, -557969248, "CC(remember):AnnotatedStringResolveInlineContent.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> list3 = list2;
                Object it$iv = $this$cache$iv.rememberedValue();
                Composer $composer4 = $composer3;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (MeasurePolicy) AnnotatedStringResolveInlineContentKt$InlineChildren$1$2$1.INSTANCE;
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                MeasurePolicy measurePolicy$iv = (MeasurePolicy) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                int $dirty2 = $dirty;
                ComposerKt.sourceInformationMarkerStart($composer4, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                Modifier modifier$iv = Modifier.INSTANCE;
                int compositeKeyHash$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer4, 0));
                CompositionLocalMap localMap$iv = $composer4.getCurrentCompositionLocalMap();
                int $i$f$fastForEach2 = $i$f$fastForEach;
                Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv = ((384 << 6) & 896) | 6;
                int index$iv2 = index$iv;
                ComposerKt.sourceInformationMarkerStart($composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer4.startReusableNode();
                if ($composer4.getInserting()) {
                    function0 = constructor;
                    $composer4.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer4.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv = Updater.m4433constructorimpl($composer4);
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, Integer.valueOf(compositeKeyHash$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4439reconcileimpl($this$Layout_u24lambda_u240$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, 374106383, "C69@2668L42:AnnotatedStringResolveInlineContent.kt#423gt5");
                function3Component1.invoke(text.subSequence(start, end).getText(), $composer4, 0);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                index$iv = index$iv2 + 1;
                list2 = list3;
                $dirty = $dirty2;
                $composer3 = $composer4;
                $i$f$fastForEach = $i$f$fastForEach2;
            }
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.AnnotatedStringResolveInlineContentKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AnnotatedStringResolveInlineContentKt.InlineChildren$lambda$1(text, list, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
