package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.internal.ClipboardUtils_androidKt;
import androidx.compose.foundation.text.ClipboardEventsHandler_jvmAndAndroidKt;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: SelectionContainer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a \u0010\b\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\t\u001aJ\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u000f¨\u0006\u0010²\u0006\f\u0010\n\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002"}, d2 = {"SelectionContainer", "", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DisableSelection", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "selection", "Landroidx/compose/foundation/text/selection/Selection;", "onSelectionChange", "Lkotlin/Function1;", "children", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/Selection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SelectionContainerKt {
    static final Unit DisableSelection$lambda$0(Function2 function2, int i, Composer composer, int i2) {
        DisableSelection(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SelectionContainer$lambda$11(Modifier modifier, Selection selection, Function1 function1, Function2 function2, int i, int i2, Composer composer, int i3) {
        SelectionContainer(modifier, selection, function1, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SelectionContainer$lambda$4(Modifier modifier, Function2 function2, int i, int i2, Composer composer, int i3) {
        SelectionContainer(modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void SelectionContainer(final Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Composer $composer2 = $composer.startRestartGroup(1949207773);
        ComposerKt.sourceInformation($composer2, "C(SelectionContainer)N(modifier,content)57@2606L45,61@2764L18,58@2656L161:SelectionContainer.kt#eksfi3");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            function22 = function2;
            $composer2.skipToGroupEnd();
        } else {
            if (i2 != 0) {
                modifier = Modifier.INSTANCE;
            }
            Modifier modifier2 = modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1949207773, $dirty, -1, "androidx.compose.foundation.text.selection.SelectionContainer (SelectionContainer.kt:56)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 582932362, "CC(remember):SelectionContainer.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final MutableState selection$delegate = (MutableState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Selection selectionSelectionContainer$lambda$1 = SelectionContainer$lambda$1(selection$delegate);
            ComposerKt.sourceInformationMarkerStart($composer2, 582937391, "CC(remember):SelectionContainer.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SelectionContainerKt.SelectionContainer$lambda$3$0(selection$delegate, (Selection) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            function22 = function2;
            SelectionContainer(modifier2, selectionSelectionContainer$lambda$1, (Function1) it$iv2, function22, $composer2, ($dirty & 14) | 384 | (($dirty << 6) & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.SelectionContainer$lambda$4(modifier, function22, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final Selection SelectionContainer$lambda$1(MutableState<Selection> mutableState) {
        MutableState<Selection> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$3$0(MutableState $selection$delegate, Selection it) {
        $selection$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    public static final void DisableSelection(final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1162635549);
        ComposerKt.sourceInformation($composer2, "C(DisableSelection)N(content)74@3108L82:SelectionContainer.kt#eksfi3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1162635549, $dirty, -1, "androidx.compose.foundation.text.selection.DisableSelection (SelectionContainer.kt:73)");
            }
            CompositionLocalKt.CompositionLocalProvider(SelectionRegistrarKt.getLocalSelectionRegistrar().provides(null), function2, $composer2, ProvidedValue.$stable | (($dirty << 3) & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.DisableSelection$lambda$0(function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void SelectionContainer(Modifier modifier, final Selection selection, final Function1<? super Selection, Unit> function1, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        final Modifier.Companion companion;
        boolean z;
        Composer composerStartRestartGroup = composer.startRestartGroup(-917932944);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SelectionContainer)N(modifier,selection,onSelectionChange,children)95@3856L28,95@3801L83,97@3904L44,99@3985L7,100@4018L24,101@4092L7,103@4136L345,112@4525L7,123@4949L148,135@5475L2468,135@5418L2525,191@7975L106,191@7949L132:SelectionContainer.kt#eksfi3");
        int i3 = i;
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 |= 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 |= composerStartRestartGroup.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(selection) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        int i5 = i3;
        if (composerStartRestartGroup.shouldExecute((i5 & 1171) != 1170, i5 & 1)) {
            companion = i4 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-917932944, i5, -1, "androidx.compose.foundation.text.selection.SelectionContainer (SelectionContainer.kt:93)");
            }
            Object[] objArr = new Object[0];
            Saver<SelectionRegistrarImpl, Long> saver = SelectionRegistrarImpl.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1665249940, "CC(remember):SelectionContainer.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SelectionContainerKt.SelectionContainer$lambda$5$0();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final SelectionRegistrarImpl selectionRegistrarImpl = (SelectionRegistrarImpl) RememberSaveableKt.m4704rememberSaveable(objArr, (Saver) saver, (Function0) objRememberedValue, composerStartRestartGroup, 384);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1665248388, "CC(remember):SelectionContainer.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                z = false;
                Object selectionManager = new SelectionManager(selectionRegistrarImpl);
                composerStartRestartGroup.updateRememberedValue(selectionManager);
                objRememberedValue2 = selectionManager;
            } else {
                z = false;
            }
            final SelectionManager selectionManager2 = (SelectionManager) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<Clipboard> localClipboard = CompositionLocalsKt.getLocalClipboard();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localClipboard);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Clipboard clipboard = (Clipboard) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<HapticFeedback> localHapticFeedback = CompositionLocalsKt.getLocalHapticFeedback();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localHapticFeedback);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            selectionManager2.setHapticFeedBack((HapticFeedback) objConsume2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1665240663, "CC(remember):SelectionContainer.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(coroutineScope) | composerStartRestartGroup.changed(clipboard);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = ClipboardUtils_androidKt.isWriteSupported(clipboard) ? new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return SelectionContainerKt.SelectionContainer$lambda$7$0(coroutineScope, clipboard, (AnnotatedString) obj3);
                    }
                } : null;
                composerStartRestartGroup.updateRememberedValue(obj2);
                objRememberedValue4 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            selectionManager2.setOnCopyHandler((Function1) objRememberedValue4);
            ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(localTextToolbar);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            selectionManager2.setTextToolbar((TextToolbar) objConsume3);
            selectionManager2.setOnSelectionChange(function1);
            selectionManager2.setSelection(selection);
            if (ComposeFoundationFlags.isSmartSelectionEnabled) {
                composerStartRestartGroup.startReplaceGroup(-82280708);
                ComposerKt.sourceInformation(composerStartRestartGroup, "118@4777L69");
                selectionManager2.setPlatformSelectionBehaviors$foundation(PlatformSelectionBehaviors_androidKt.rememberPlatformSelectionBehaviors(SelectedTextType.StaticText, null, composerStartRestartGroup, 54));
                selectionManager2.setCoroutineScope$foundation(coroutineScope);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-82105806);
                composerStartRestartGroup.endReplaceGroup();
            }
            new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return selectionManager2.getSelectedText$foundation();
                }
            };
            selectionManager2.isNonEmptySelection$foundation();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1264411026, "CC(rememberClipboardEventsHandler)N(onPaste,onCopy,onCut,isEnabled):ClipboardEventsHandler.jvmAndAndroid.kt#423gt5");
            ClipboardEventsHandler_jvmAndAndroidKt.AnonymousClass1 anonymousClass1 = ClipboardEventsHandler_jvmAndAndroidKt.AnonymousClass1.INSTANCE;
            ClipboardEventsHandler_jvmAndAndroidKt.AnonymousClass3 anonymousClass3 = ClipboardEventsHandler_jvmAndAndroidKt.AnonymousClass3.INSTANCE;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            boolean z2 = z;
            selectionManager2.setShouldIgnoreCopyKeyEvent$foundation(z2);
            SimpleLayoutKt.SimpleLayout(companion.then(selectionManager2.getModifier()), ComposableLambdaKt.rememberComposableLambda(-1799563674, true, new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return SelectionContainerKt.SelectionContainer$lambda$9(selectionManager2, selectionRegistrarImpl, function2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 48, z2 ? 1 : 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1665118054, "CC(remember):SelectionContainer.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(selectionManager2);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return SelectionContainerKt.SelectionContainer$lambda$10$0(selectionManager2, (DisposableEffectScope) obj4);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj3);
                objRememberedValue5 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.DisposableEffect(selectionManager2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue5, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            companion = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return SelectionContainerKt.SelectionContainer$lambda$11(companion, selection, function1, function2, i, i2, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SelectionRegistrarImpl SelectionContainer$lambda$5$0() {
        return new SelectionRegistrarImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$7$0(CoroutineScope $coroutineScope, Clipboard $clipboard, AnnotatedString textToCopy) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, CoroutineStart.UNDISPATCHED, new SelectionContainerKt$SelectionContainer$3$1$1($clipboard, textToCopy, null), 1, null);
        return Unit.INSTANCE;
    }

    static final Unit SelectionContainer$lambda$9(final SelectionManager $manager, final SelectionRegistrarImpl $registrarImpl, final Function2 $children, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C136@5510L2427,136@5485L2452:SelectionContainer.kt#eksfi3");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1799563674, $changed, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous> (SelectionContainer.kt:136)");
            }
            ContextMenu_androidKt.ContextMenuArea($manager, ComposableLambdaKt.rememberComposableLambda(-284825865, true, new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.SelectionContainer$lambda$9$0($registrarImpl, $children, $manager, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$9$0(SelectionRegistrarImpl $registrarImpl, final Function2 $children, final SelectionManager $manager, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C137@5597L2330,137@5524L2403:SelectionContainer.kt#eksfi3");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-284825865, $changed, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous>.<anonymous> (SelectionContainer.kt:137)");
            }
            CompositionLocalKt.CompositionLocalProvider(SelectionRegistrarKt.getLocalSelectionRegistrar().provides($registrarImpl), ComposableLambdaKt.rememberComposableLambda(610483127, true, new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.SelectionContainer$lambda$9$0$0($children, $manager, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0177  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.Unit SelectionContainer$lambda$9$0$0(kotlin.jvm.functions.Function2 r26, androidx.compose.foundation.text.selection.SelectionManager r27, androidx.compose.runtime.Composer r28, int r29) {
        /*
            Method dump skipped, instruction units count: 478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionContainerKt.SelectionContainer$lambda$9$0$0(kotlin.jvm.functions.Function2, androidx.compose.foundation.text.selection.SelectionManager, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset SelectionContainer$lambda$9$0$0$0$0$1$0(SelectionManager $manager) {
        Offset offsetM2071getStartHandlePosition_m7T9E = $manager.m2071getStartHandlePosition_m7T9E();
        return Offset.m5057boximpl(offsetM2071getStartHandlePosition_m7T9E != null ? offsetM2071getStartHandlePosition_m7T9E.m5078unboximpl() : Offset.INSTANCE.m5083getUnspecifiedF1C5BW0());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset SelectionContainer$lambda$9$0$0$0$0$1$1(SelectionManager $manager) {
        Offset offsetM2070getEndHandlePosition_m7T9E = $manager.m2070getEndHandlePosition_m7T9E();
        return Offset.m5057boximpl(offsetM2070getEndHandlePosition_m7T9E != null ? offsetM2070getEndHandlePosition_m7T9E.m5078unboximpl() : Offset.INSTANCE.m5083getUnspecifiedF1C5BW0());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult SelectionContainer$lambda$10$0(final SelectionManager $manager, DisposableEffectScope $this$DisposableEffect) {
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$lambda$10$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $manager.onRelease();
                $manager.setHasFocus(false);
            }
        };
    }
}
