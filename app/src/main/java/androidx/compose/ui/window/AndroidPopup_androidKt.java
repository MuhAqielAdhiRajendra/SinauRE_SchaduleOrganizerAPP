package androidx.compose.ui.window;

import android.graphics.Rect;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: AndroidPopup.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aR\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\u0010\u001a \u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0002\u001a(\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u001b2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0001¢\u0006\u0002\u0010 \u001a+\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%2\u0013\b\b\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bH\u0083\b¢\u0006\u0002\u0010&\u001a\f\u0010'\u001a\u00020\u0015*\u00020(H\u0000\u001a\u0014\u0010)\u001a\u00020\u0012*\u00020\t2\u0006\u0010*\u001a\u00020\u0015H\u0002\u001a\f\u0010+\u001a\u00020,*\u00020-H\u0002\u001a\u001c\u0010.\u001a\u00020\u00152\u0006\u0010/\u001a\u00020(2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u001bH\u0007\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\u001aX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001d¨\u00061²\u0006\u0015\u00102\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u000bX\u008a\u0084\u0002"}, d2 = {"Popup", "", "alignment", "Landroidx/compose/ui/Alignment;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/unit/IntOffset;", "onDismissRequest", "Lkotlin/Function0;", "properties", "Landroidx/compose/ui/window/PopupProperties;", "content", "Landroidx/compose/runtime/Composable;", "Popup-K5zGePQ", "(Landroidx/compose/ui/Alignment;JLkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "popupPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "PopupPropertiesBaseFlags", "", "createFlags", "focusable", "", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "clippingEnabled", "LocalPopupTestTag", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalPopupTestTag", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "PopupTestTag", "tag", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "LocalIsInPopupLayout", "getLocalIsInPopupLayout", "SimpleStack", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "isFlagSecureEnabled", "Landroid/view/View;", "flagsWithSecureFlagInherited", "isParentFlagSecureEnabled", "toIntBounds", "Landroidx/compose/ui/unit/IntRect;", "Landroid/graphics/Rect;", "isPopupLayout", "view", "testTag", "ui", "currentContent"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidPopup_androidKt {
    private static final int PopupPropertiesBaseFlags = 262144;
    private static final ProvidableCompositionLocal<String> LocalPopupTestTag = CompositionLocalKt.compositionLocalOf$default(null, new Function0<String>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$LocalPopupTestTag$1
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return "DEFAULT_TEST_TAG";
        }
    }, 1, null);
    private static final ProvidableCompositionLocal<Boolean> LocalIsInPopupLayout = CompositionLocalKt.compositionLocalOf$default(null, new Function0<Boolean>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$LocalIsInPopupLayout$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return false;
        }
    }, 1, null);

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$1 */
    /* JADX INFO: compiled from: AndroidPopup.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;
        final /* synthetic */ long $offset;
        final /* synthetic */ Function0<Unit> $onDismissRequest;
        final /* synthetic */ PopupProperties $properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(long j, Function0<Unit> function0, PopupProperties popupProperties, Function2<? super Composer, ? super Integer, Unit> function2, int i, int i2) {
            super(2);
            j = j;
            function0 = function0;
            popupProperties = popupProperties;
            function2 = function2;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            AndroidPopup_androidKt.m8402PopupK5zGePQ(alignment, j, function0, popupProperties, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$9 */
    /* JADX INFO: compiled from: AndroidPopup.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass9 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;
        final /* synthetic */ Function0<Unit> $onDismissRequest;
        final /* synthetic */ PopupProperties $properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass9(Function0<Unit> function0, PopupProperties popupProperties, Function2<? super Composer, ? super Integer, Unit> function2, int i, int i2) {
            super(2);
            function0 = function0;
            popupProperties = popupProperties;
            function2 = function2;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            AndroidPopup_androidKt.Popup(popupPositionProvider, function0, popupProperties, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$PopupTestTag$1 */
    /* JADX INFO: compiled from: AndroidPopup.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class C03271 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;
        final /* synthetic */ String $tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03271(String str, Function2<? super Composer, ? super Integer, Unit> function2, int i) {
            super(2);
            str = str;
            function2 = function2;
            i = i;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            AndroidPopup_androidKt.PopupTestTag(str, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        }
    }

    /* JADX INFO: renamed from: Popup-K5zGePQ */
    public static final void m8402PopupK5zGePQ(Alignment alignment, long offset, Function0<Unit> function0, PopupProperties properties, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed, int i) {
        Alignment alignment2;
        long offset2;
        Function0<Unit> function02;
        PopupProperties popupProperties;
        Function2<? super Composer, ? super Integer, Unit> function22;
        Alignment alignment3;
        Function0<Unit> function03;
        PopupProperties properties2;
        Composer $composer2 = $composer.startRestartGroup(71005054);
        ComposerKt.sourceInformation($composer2, "C(Popup)N(alignment,offset:c#ui.unit.IntOffset,onDismissRequest,properties,content)388@19091L149,392@19246L166:AndroidPopup.android.kt#2oxthz");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            alignment2 = alignment;
        } else if (($changed & 6) == 0) {
            alignment2 = alignment;
            $dirty |= $composer2.changed(alignment2) ? 4 : 2;
        } else {
            alignment2 = alignment;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            offset2 = offset;
        } else if (($changed & 48) == 0) {
            offset2 = offset;
            $dirty |= $composer2.changed(offset2) ? 32 : 16;
        } else {
            offset2 = offset;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            function02 = function0;
        } else if (($changed & 384) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 256 : 128;
        } else {
            function02 = function0;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            popupProperties = properties;
        } else if (($changed & 3072) == 0) {
            popupProperties = properties;
            $dirty |= $composer2.changed(popupProperties) ? 2048 : 1024;
        } else {
            popupProperties = properties;
        }
        if (($changed & 24576) == 0) {
            function22 = function2;
            $dirty |= $composer2.changedInstance(function22) ? 16384 : 8192;
        } else {
            function22 = function2;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            alignment3 = i2 != 0 ? Alignment.INSTANCE.getTopStart() : alignment2;
            long offset3 = i3 != 0 ? IntOffset.m8272constructorimpl((((long) 0) << 32) | (((long) 0) & 4294967295L)) : offset2;
            Function0<Unit> function04 = i4 != 0 ? null : function02;
            PopupProperties properties3 = i5 != 0 ? new PopupProperties(false, false, false, false, false, 31, (DefaultConstructorMarker) null) : popupProperties;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(71005054, $dirty, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:386)");
            }
            int windowType = properties3.getWindowType();
            IBinder windowToken = properties3.getWindowToken();
            ComposerKt.sourceInformationMarkerStart($composer2, -483613133, "CC(remember):AndroidPopup.android.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(windowType) | (($dirty & 14) == 4) | (($dirty & 112) == 32) | $composer2.changed(windowToken);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new AlignmentOffsetPositionProvider(alignment3, offset3, null);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            AlignmentOffsetPositionProvider popupPositioner = (AlignmentOffsetPositionProvider) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Popup(popupPositioner, function04, properties3, function22, $composer2, (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function03 = function04;
            offset2 = offset3;
            properties2 = properties3;
        } else {
            $composer2.skipToGroupEnd();
            alignment3 = alignment2;
            function03 = function02;
            properties2 = popupProperties;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.Popup.1
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Function2<Composer, Integer, Unit> $content;
                final /* synthetic */ long $offset;
                final /* synthetic */ Function0<Unit> $onDismissRequest;
                final /* synthetic */ PopupProperties $properties;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass1(long offset22, Function0<Unit> function032, PopupProperties properties22, Function2<? super Composer, ? super Integer, Unit> function23, int $changed2, int i6) {
                    super(2);
                    j = offset22;
                    function0 = function032;
                    popupProperties = properties22;
                    function2 = function23;
                    i = $changed2;
                    i = i6;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    AndroidPopup_androidKt.m8402PopupK5zGePQ(alignment, j, function0, popupProperties, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:238:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0489  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Popup(androidx.compose.ui.window.PopupPositionProvider r31, kotlin.jvm.functions.Function0<kotlin.Unit> r32, androidx.compose.ui.window.PopupProperties r33, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r34, androidx.compose.runtime.Composer r35, int r36, int r37) {
        /*
            Method dump skipped, instruction units count: 1200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.AndroidPopup_androidKt.Popup(androidx.compose.ui.window.PopupPositionProvider, kotlin.jvm.functions.Function0, androidx.compose.ui.window.PopupProperties, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final Function2<Composer, Integer, Unit> Popup$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    public static final int createFlags(boolean focusable, SecureFlagPolicy securePolicy, boolean clippingEnabled) {
        int flags = 262144;
        if (!focusable) {
            flags = 262144 | 8;
        }
        if (securePolicy == SecureFlagPolicy.SecureOn) {
            flags |= 8192;
        }
        if (!clippingEnabled) {
            return flags | 512;
        }
        return flags;
    }

    public static final ProvidableCompositionLocal<String> getLocalPopupTestTag() {
        return LocalPopupTestTag;
    }

    public static final void PopupTestTag(String tag, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1357513789);
        ComposerKt.sourceInformation($composer2, "C(PopupTestTag)N(tag,content)548@25328L75:AndroidPopup.android.kt#2oxthz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(tag) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1357513789, $dirty, -1, "androidx.compose.ui.window.PopupTestTag (AndroidPopup.android.kt:547)");
            }
            CompositionLocalKt.CompositionLocalProvider(LocalPopupTestTag.provides(tag), function2, $composer2, ProvidedValue.$stable | ($dirty & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt.PopupTestTag.1
                final /* synthetic */ int $$changed;
                final /* synthetic */ Function2<Composer, Integer, Unit> $content;
                final /* synthetic */ String $tag;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C03271(String tag2, Function2<? super Composer, ? super Integer, Unit> function22, int $changed2) {
                    super(2);
                    str = tag2;
                    function2 = function22;
                    i = $changed2;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    AndroidPopup_androidKt.PopupTestTag(str, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalIsInPopupLayout() {
        return LocalIsInPopupLayout;
    }

    private static final void SimpleStack(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 26279861, "CC(SimpleStack)N(modifier,content)568@26353L899,568@26306L946:AndroidPopup.android.kt#2oxthz");
        ComposerKt.sourceInformationMarkerStart($composer, -514852264, "CC(remember):AndroidPopup.android.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = (MeasurePolicy) AndroidPopup_androidKt$SimpleStack$1$1.INSTANCE;
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        MeasurePolicy measurePolicy$iv = (MeasurePolicy) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        int $changed$iv = (($changed >> 3) & 14) | 384 | (($changed << 3) & 112);
        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
        int compositeKeyHash$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
        CompositionLocalMap localMap$iv = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer, modifier);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            $composer.createNode(constructor);
        } else {
            $composer.useNode();
        }
        Composer $this$Layout_u24lambda_u240$iv = Updater.m4433constructorimpl($composer);
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, Integer.valueOf(compositeKeyHash$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
        Updater.m4439reconcileimpl($this$Layout_u24lambda_u240$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
        function2.invoke($composer, Integer.valueOf(($changed$iv$iv >> 6) & 14));
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    public static final boolean isFlagSecureEnabled(View $this$isFlagSecureEnabled) {
        ViewGroup.LayoutParams layoutParams = $this$isFlagSecureEnabled.getRootView().getLayoutParams();
        WindowManager.LayoutParams windowParams = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (windowParams == null || (windowParams.flags & 8192) == 0) ? false : true;
    }

    public static final int flagsWithSecureFlagInherited(PopupProperties $this$flagsWithSecureFlagInherited, boolean isParentFlagSecureEnabled) {
        if ($this$flagsWithSecureFlagInherited.getInheritSecurePolicy() && isParentFlagSecureEnabled) {
            return $this$flagsWithSecureFlagInherited.getFlags() | 8192;
        }
        if ($this$flagsWithSecureFlagInherited.getInheritSecurePolicy() && !isParentFlagSecureEnabled) {
            return $this$flagsWithSecureFlagInherited.getFlags() & (-8193);
        }
        return $this$flagsWithSecureFlagInherited.getFlags();
    }

    public static final IntRect toIntBounds(Rect $this$toIntBounds) {
        return new IntRect($this$toIntBounds.left, $this$toIntBounds.top, $this$toIntBounds.right, $this$toIntBounds.bottom);
    }

    public static /* synthetic */ boolean isPopupLayout$default(View view, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return isPopupLayout(view, str);
    }

    public static final boolean isPopupLayout(View view, String testTag) {
        return (view instanceof PopupLayout) && (testTag == null || Intrinsics.areEqual(testTag, ((PopupLayout) view).getTestTag()));
    }
}
