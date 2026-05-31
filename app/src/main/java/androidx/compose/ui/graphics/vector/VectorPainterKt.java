package androidx.compose.ui.graphics.vector;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.vector.VectorProperty;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: VectorPainter.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0093\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2@\u0010\u000f\u001a<\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0011\u0012\b\b\n\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0011\u0012\b\b\n\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u009d\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u00182@\u0010\u000f\u001a<\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0011\u0012\b\b\n\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0011\u0012\b\b\n\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0015\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001d\u001a&\u0010\u001e\u001a\u00020\u0012*\u00020\u001f2\u0017\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00120!¢\u0006\u0002\b\"H\u0082\b\u001a#\u0010#\u001a\u00020$*\u00020%2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b&\u0010'\u001a'\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b*\u0010+\u001a!\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0004\b.\u0010/\u001aA\u00100\u001a\u00020\u0003*\u00020\u00032\u0006\u0010)\u001a\u00020$2\u0006\u00101\u001a\u00020$2\b\b\u0002\u0010\n\u001a\u00020\u00012\b\u00102\u001a\u0004\u0018\u00010-2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0004\b3\u00104\u001a \u00105\u001a\u00020\u00032\u0006\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020\u001c2\u0006\u00108\u001a\u000209H\u0000\u001a\u0014\u0010:\u001a\u000209*\u0002092\u0006\u0010;\u001a\u00020<H\u0000\u001a+\u0010=\u001a\u00020\u00122\u0006\u0010>\u001a\u00020<2\u0014\b\u0002\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020A0@H\u0007¢\u0006\u0002\u0010B\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"RootGroupName", "", "rememberVectorPainter", "Landroidx/compose/ui/graphics/vector/VectorPainter;", "defaultWidth", "Landroidx/compose/ui/unit/Dp;", "defaultHeight", "viewportWidth", "", "viewportHeight", HintConstants.AUTOFILL_HINT_NAME, "tintColor", "Landroidx/compose/ui/graphics/Color;", "tintBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "content", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/graphics/vector/VectorComposable;", "rememberVectorPainter-mlNsNFs", "(FFFFLjava/lang/String;JILkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/vector/VectorPainter;", "autoMirror", "", "rememberVectorPainter-vIP8VLU", "(FFFFLjava/lang/String;JIZLkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/vector/VectorPainter;", "image", "Landroidx/compose/ui/graphics/vector/ImageVector;", "(Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/vector/VectorPainter;", "mirror", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "obtainSizePx", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/Density;", "obtainSizePx-VpY3zN4", "(Landroidx/compose/ui/unit/Density;FF)J", "obtainViewportSize", "defaultSize", "obtainViewportSize-Pq9zytI", "(JFF)J", "createColorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "createColorFilter-xETnrds", "(JI)Landroidx/compose/ui/graphics/ColorFilter;", "configureVectorPainter", "viewportSize", "intrinsicColorFilter", "configureVectorPainter-T4PVSW8", "(Landroidx/compose/ui/graphics/vector/VectorPainter;JJLjava/lang/String;Landroidx/compose/ui/graphics/ColorFilter;Z)Landroidx/compose/ui/graphics/vector/VectorPainter;", "createVectorPainterFromImageVector", "density", "imageVector", "root", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "createGroupComponent", "currentGroup", "Landroidx/compose/ui/graphics/vector/VectorGroup;", "RenderVectorGroup", "group", "configs", "", "Landroidx/compose/ui/graphics/vector/VectorConfig;", "(Landroidx/compose/ui/graphics/vector/VectorGroup;Ljava/util/Map;Landroidx/compose/runtime/Composer;II)V", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class VectorPainterKt {
    public static final String RootGroupName = "VectorRootGroup";

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$2 */
    /* JADX INFO: compiled from: VectorPainter.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass2 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Map<String, VectorConfig> $configs;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Map<String, ? extends VectorConfig> map, int i, int i2) {
            super(2);
            map = map;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            VectorPainterKt.RenderVectorGroup(vectorGroup, map, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    @Deprecated(message = "Replace rememberVectorPainter graphicsLayer that consumes the auto mirror flag", replaceWith = @ReplaceWith(expression = "rememberVectorPainter(defaultWidth, defaultHeight, viewportWidth, viewportHeight, name, tintColor, tintBlendMode, false, content)", imports = {"androidx.compose.ui.graphics.vector"}))
    /* JADX INFO: renamed from: rememberVectorPainter-mlNsNFs */
    public static final VectorPainter m6073rememberVectorPaintermlNsNFs(float defaultWidth, float defaultHeight, float viewportWidth, float viewportHeight, String name, long tintColor, int tintBlendMode, Function4<? super Float, ? super Float, ? super Composer, ? super Integer, Unit> function4, Composer $composer, int $changed, int i) {
        float viewportWidth2;
        float viewportHeight2;
        String name2;
        long tintColor2;
        int tintBlendMode2;
        ComposerKt.sourceInformationMarkerStart($composer, 411310745, "C(rememberVectorPainter)N(defaultWidth:c#ui.unit.Dp,defaultHeight:c#ui.unit.Dp,viewportWidth,viewportHeight,name,tintColor:c#ui.graphics.Color,tintBlendMode:c#ui.graphics.BlendMode,content)85@3806L208:VectorPainter.kt#huu6hf");
        if ((i & 4) != 0) {
            viewportWidth2 = Float.NaN;
        } else {
            viewportWidth2 = viewportWidth;
        }
        if ((i & 8) == 0) {
            viewportHeight2 = viewportHeight;
        } else {
            viewportHeight2 = Float.NaN;
        }
        if ((i & 16) == 0) {
            name2 = name;
        } else {
            name2 = RootGroupName;
        }
        if ((i & 32) == 0) {
            tintColor2 = tintColor;
        } else {
            tintColor2 = Color.INSTANCE.m5349getUnspecified0d7_KjU();
        }
        if ((i & 64) == 0) {
            tintBlendMode2 = tintBlendMode;
        } else {
            tintBlendMode2 = BlendMode.INSTANCE.m5251getSrcIn0nO6VwU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(411310745, $changed, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:85)");
        }
        VectorPainter vectorPainterM6074rememberVectorPaintervIP8VLU = m6074rememberVectorPaintervIP8VLU(defaultWidth, defaultHeight, viewportWidth2, viewportHeight2, name2, tintColor2, tintBlendMode2, false, function4, $composer, ($changed & 14) | 12582912 | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed) | (($changed << 3) & 234881024), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return vectorPainterM6074rememberVectorPaintervIP8VLU;
    }

    /* JADX WARN: Removed duplicated region for block: B:199:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0251  */
    /* JADX INFO: renamed from: rememberVectorPainter-vIP8VLU */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.graphics.vector.VectorPainter m6074rememberVectorPaintervIP8VLU(float r24, float r25, float r26, float r27, java.lang.String r28, long r29, int r31, boolean r32, kotlin.jvm.functions.Function4<? super java.lang.Float, ? super java.lang.Float, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, androidx.compose.runtime.Composer r34, int r35, int r36) {
        /*
            Method dump skipped, instruction units count: 600
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.vector.VectorPainterKt.m6074rememberVectorPaintervIP8VLU(float, float, float, float, java.lang.String, long, int, boolean, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int):androidx.compose.ui.graphics.vector.VectorPainter");
    }

    public static final VectorPainter rememberVectorPainter(ImageVector image, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1413834416, "C(rememberVectorPainter)N(image)170@7434L7,172@7518L188:VectorPainter.kt#huu6hf");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1413834416, $changed, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:169)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density density = (Density) objConsume;
        float val1$iv = image.getGenId();
        float val2$iv = density.getDensity();
        long v1$iv = Float.floatToRawIntBits(val1$iv);
        long v2$iv = Float.floatToRawIntBits(val2$iv);
        long key = (v1$iv << 32) | (4294967295L & v2$iv);
        ComposerKt.sourceInformationMarkerStart($composer, 1485214124, "CC(remember):VectorPainter.kt#9igjgp");
        boolean invalid$iv = $composer.changed(key);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            GroupComponent $this$rememberVectorPainter_u24lambda_u240_u240 = new GroupComponent();
            createGroupComponent($this$rememberVectorPainter_u24lambda_u240_u240, image.getRoot());
            Unit unit = Unit.INSTANCE;
            Object value$iv = createVectorPainterFromImageVector(density, image, $this$rememberVectorPainter_u24lambda_u240_u240);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        VectorPainter vectorPainter = (VectorPainter) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return vectorPainter;
    }

    private static final void mirror(DrawScope $this$mirror, Function1<? super DrawScope, Unit> function1) throws Throwable {
        long pivot$iv = $this$mirror.mo5886getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u240$iv$iv = $this$mirror.getDrawContext();
        long previousSize$iv$iv = $this$withTransform_u24lambda_u240$iv$iv.mo5808getSizeNHjbRc();
        $this$withTransform_u24lambda_u240$iv$iv.getCanvas().save();
        try {
            DrawTransform $this$scale_Fgt4K4Q_u24lambda_u240$iv = $this$withTransform_u24lambda_u240$iv$iv.getTransform();
            $this$scale_Fgt4K4Q_u24lambda_u240$iv.mo5815scale0AR0LA0(-1.0f, 1.0f, pivot$iv);
            try {
                function1.invoke($this$mirror);
                $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
            } catch (Throwable th) {
                th = th;
                $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: obtainSizePx-VpY3zN4 */
    private static final long m6071obtainSizePxVpY3zN4(Density $this$obtainSizePx_u2dVpY3zN4, float defaultWidth, float defaultHeight) {
        float width$iv = $this$obtainSizePx_u2dVpY3zN4.mo432toPx0680j_4(defaultWidth);
        float height$iv = $this$obtainSizePx_u2dVpY3zN4.mo432toPx0680j_4(defaultHeight);
        long v1$iv$iv = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv = Float.floatToRawIntBits(height$iv);
        return Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: renamed from: obtainViewportSize-Pq9zytI */
    private static final long m6072obtainViewportSizePq9zytI(long defaultSize, float viewportWidth, float viewportHeight) {
        float width$iv;
        float height$iv;
        if (!Float.isNaN(viewportWidth)) {
            width$iv = viewportWidth;
        } else {
            int bits$iv$iv$iv = (int) (defaultSize >> 32);
            width$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        }
        if (!Float.isNaN(viewportHeight)) {
            height$iv = viewportHeight;
        } else {
            int bits$iv$iv$iv2 = (int) (defaultSize & 4294967295L);
            height$iv = Float.intBitsToFloat(bits$iv$iv$iv2);
        }
        float val2$iv$iv = height$iv;
        float val1$iv$iv = width$iv;
        long v1$iv$iv = Float.floatToRawIntBits(val1$iv$iv);
        long v2$iv$iv = Float.floatToRawIntBits(val2$iv$iv);
        return Size.m5128constructorimpl((v2$iv$iv & 4294967295L) | (v1$iv$iv << 32));
    }

    /* JADX INFO: renamed from: createColorFilter-xETnrds */
    private static final ColorFilter m6070createColorFilterxETnrds(long tintColor, int tintBlendMode) {
        if (tintColor != 16) {
            return ColorFilter.INSTANCE.m5357tintxETnrds(tintColor, tintBlendMode);
        }
        return null;
    }

    /* JADX INFO: renamed from: configureVectorPainter-T4PVSW8$default */
    public static /* synthetic */ VectorPainter m6069configureVectorPainterT4PVSW8$default(VectorPainter vectorPainter, long j, long j2, String str, ColorFilter colorFilter, boolean z, int i, Object obj) {
        String str2;
        boolean z2;
        if ((i & 4) == 0) {
            str2 = str;
        } else {
            str2 = RootGroupName;
        }
        if ((i & 16) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        return m6068configureVectorPainterT4PVSW8(vectorPainter, j, j2, str2, colorFilter, z2);
    }

    /* JADX INFO: renamed from: configureVectorPainter-T4PVSW8 */
    public static final VectorPainter m6068configureVectorPainterT4PVSW8(VectorPainter $this$configureVectorPainter_u2dT4PVSW8, long defaultSize, long viewportSize, String name, ColorFilter intrinsicColorFilter, boolean autoMirror) {
        $this$configureVectorPainter_u2dT4PVSW8.m6066setSizeuvyYCjk$ui(defaultSize);
        $this$configureVectorPainter_u2dT4PVSW8.setAutoMirror$ui(autoMirror);
        $this$configureVectorPainter_u2dT4PVSW8.setIntrinsicColorFilter$ui(intrinsicColorFilter);
        $this$configureVectorPainter_u2dT4PVSW8.m6067setViewportSizeuvyYCjk$ui(viewportSize);
        $this$configureVectorPainter_u2dT4PVSW8.setName$ui(name);
        return $this$configureVectorPainter_u2dT4PVSW8;
    }

    public static final VectorPainter createVectorPainterFromImageVector(Density density, ImageVector imageVector, GroupComponent root) {
        long defaultSize = m6071obtainSizePxVpY3zN4(density, imageVector.getDefaultWidth(), imageVector.getDefaultHeight());
        long viewport = m6072obtainViewportSizePq9zytI(defaultSize, imageVector.getViewportWidth(), imageVector.getViewportHeight());
        return m6068configureVectorPainterT4PVSW8(new VectorPainter(root), defaultSize, viewport, imageVector.getName(), m6070createColorFilterxETnrds(imageVector.getTintColor(), imageVector.getTintBlendMode()), imageVector.getAutoMirror());
    }

    public static final GroupComponent createGroupComponent(GroupComponent $this$createGroupComponent, VectorGroup currentGroup) {
        int size = currentGroup.getSize();
        for (int index = 0; index < size; index++) {
            VectorNode vectorNode = currentGroup.get(index);
            if (vectorNode instanceof VectorPath) {
                PathComponent $this$createGroupComponent_u24lambda_u240 = new PathComponent();
                $this$createGroupComponent_u24lambda_u240.setPathData(((VectorPath) vectorNode).getPathData());
                $this$createGroupComponent_u24lambda_u240.m6051setPathFillTypeoQ8Xj4U(((VectorPath) vectorNode).getPathFillType());
                $this$createGroupComponent_u24lambda_u240.setName(((VectorPath) vectorNode).getName());
                $this$createGroupComponent_u24lambda_u240.setFill(((VectorPath) vectorNode).getFill());
                $this$createGroupComponent_u24lambda_u240.setFillAlpha(((VectorPath) vectorNode).getFillAlpha());
                $this$createGroupComponent_u24lambda_u240.setStroke(((VectorPath) vectorNode).getStroke());
                $this$createGroupComponent_u24lambda_u240.setStrokeAlpha(((VectorPath) vectorNode).getStrokeAlpha());
                $this$createGroupComponent_u24lambda_u240.setStrokeLineWidth(((VectorPath) vectorNode).getStrokeLineWidth());
                $this$createGroupComponent_u24lambda_u240.m6052setStrokeLineCapBeK7IIE(((VectorPath) vectorNode).getStrokeLineCap());
                $this$createGroupComponent_u24lambda_u240.m6053setStrokeLineJoinWw9F2mQ(((VectorPath) vectorNode).getStrokeLineJoin());
                $this$createGroupComponent_u24lambda_u240.setStrokeLineMiter(((VectorPath) vectorNode).getStrokeLineMiter());
                $this$createGroupComponent_u24lambda_u240.setTrimPathStart(((VectorPath) vectorNode).getTrimPathStart());
                $this$createGroupComponent_u24lambda_u240.setTrimPathEnd(((VectorPath) vectorNode).getTrimPathEnd());
                $this$createGroupComponent_u24lambda_u240.setTrimPathOffset(((VectorPath) vectorNode).getTrimPathOffset());
                $this$createGroupComponent.insertAt(index, $this$createGroupComponent_u24lambda_u240);
            } else if (vectorNode instanceof VectorGroup) {
                GroupComponent $this$createGroupComponent_u24lambda_u241 = new GroupComponent();
                $this$createGroupComponent_u24lambda_u241.setName(((VectorGroup) vectorNode).getName());
                $this$createGroupComponent_u24lambda_u241.setRotation(((VectorGroup) vectorNode).getRotation());
                $this$createGroupComponent_u24lambda_u241.setScaleX(((VectorGroup) vectorNode).getScaleX());
                $this$createGroupComponent_u24lambda_u241.setScaleY(((VectorGroup) vectorNode).getScaleY());
                $this$createGroupComponent_u24lambda_u241.setTranslationX(((VectorGroup) vectorNode).getTranslationX());
                $this$createGroupComponent_u24lambda_u241.setTranslationY(((VectorGroup) vectorNode).getTranslationY());
                $this$createGroupComponent_u24lambda_u241.setPivotX(((VectorGroup) vectorNode).getPivotX());
                $this$createGroupComponent_u24lambda_u241.setPivotY(((VectorGroup) vectorNode).getPivotY());
                $this$createGroupComponent_u24lambda_u241.setClipPathData(((VectorGroup) vectorNode).getClipPathData());
                createGroupComponent($this$createGroupComponent_u24lambda_u241, (VectorGroup) vectorNode);
                $this$createGroupComponent.insertAt(index, $this$createGroupComponent_u24lambda_u241);
            }
        }
        return $this$createGroupComponent;
    }

    public static final void RenderVectorGroup(VectorGroup group, Map<String, ? extends VectorConfig> map, Composer $composer, int $changed, int i) {
        Map<String, ? extends VectorConfig> map2;
        Composer $composer2 = $composer.startRestartGroup(-446179233);
        ComposerKt.sourceInformation($composer2, "C(RenderVectorGroup)N(group,configs):VectorPainter.kt#huu6hf");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(group) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            map2 = map;
        } else if (($changed & 48) == 0) {
            map2 = map;
            $dirty |= $composer2.changedInstance(map2) ? 32 : 16;
        } else {
            map2 = map;
        }
        if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            Map<String, ? extends VectorConfig> mapEmptyMap = i2 != 0 ? MapsKt.emptyMap() : map2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-446179233, $dirty, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup (VectorPainter.kt:423)");
            }
            Iterator<VectorNode> it = group.iterator();
            while (it.hasNext()) {
                VectorNode vectorNode = it.next();
                if (vectorNode instanceof VectorPath) {
                    $composer2.startReplaceGroup(798455915);
                    ComposerKt.sourceInformation($composer2, "427@16156L1298");
                    VectorConfig config = mapEmptyMap.get(((VectorPath) vectorNode).getName());
                    if (config == null) {
                        config = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$1
                        };
                    }
                    Composer $composer3 = $composer2;
                    VectorComposeKt.m6057Path9cdaXJ4((List) config.getOrDefault(VectorProperty.PathData.INSTANCE, ((VectorPath) vectorNode).getPathData()), ((VectorPath) vectorNode).getPathFillType(), ((VectorPath) vectorNode).getName(), (Brush) config.getOrDefault(VectorProperty.Fill.INSTANCE, ((VectorPath) vectorNode).getFill()), ((Number) config.getOrDefault(VectorProperty.FillAlpha.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getFillAlpha()))).floatValue(), (Brush) config.getOrDefault(VectorProperty.Stroke.INSTANCE, ((VectorPath) vectorNode).getStroke()), ((Number) config.getOrDefault(VectorProperty.StrokeAlpha.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getStrokeAlpha()))).floatValue(), ((Number) config.getOrDefault(VectorProperty.StrokeLineWidth.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getStrokeLineWidth()))).floatValue(), ((VectorPath) vectorNode).getStrokeLineCap(), ((VectorPath) vectorNode).getStrokeLineJoin(), ((VectorPath) vectorNode).getStrokeLineMiter(), ((Number) config.getOrDefault(VectorProperty.TrimPathStart.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getTrimPathStart()))).floatValue(), ((Number) config.getOrDefault(VectorProperty.TrimPathEnd.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getTrimPathEnd()))).floatValue(), ((Number) config.getOrDefault(VectorProperty.TrimPathOffset.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getTrimPathOffset()))).floatValue(), $composer3, 0, 0, 0);
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                    it = it;
                    mapEmptyMap = mapEmptyMap;
                    $dirty = $dirty;
                } else {
                    Iterator<VectorNode> it2 = it;
                    int $dirty2 = $dirty;
                    Map<String, ? extends VectorConfig> map3 = mapEmptyMap;
                    if (vectorNode instanceof VectorGroup) {
                        $composer2.startReplaceGroup(799870476);
                        ComposerKt.sourceInformation($composer2, "462@18450L88,450@17593L945");
                        VectorConfig config2 = map3.get(((VectorGroup) vectorNode).getName());
                        if (config2 == null) {
                            config2 = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$2
                            };
                        }
                        VectorComposeKt.Group(((VectorGroup) vectorNode).getName(), ((Number) config2.getOrDefault(VectorProperty.Rotation.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getRotation()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.PivotX.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getPivotX()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.PivotY.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getPivotY()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.ScaleX.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getScaleX()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.ScaleY.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getScaleY()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.TranslateX.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getTranslationX()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.TranslateY.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getTranslationY()))).floatValue(), (List) config2.getOrDefault(VectorProperty.PathData.INSTANCE, ((VectorGroup) vectorNode).getClipPathData()), ComposableLambdaKt.rememberComposableLambda(1450046638, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt.RenderVectorGroup.1
                            final /* synthetic */ Map<String, VectorConfig> $configs;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            AnonymousClass1(Map<String, ? extends VectorConfig> map32) {
                                super(2);
                                map = map32;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer4, int $changed2) {
                                ComposerKt.sourceInformation($composer4, "C463@18468L56:VectorPainter.kt#huu6hf");
                                if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1450046638, $changed2, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup.<anonymous> (VectorPainter.kt:463)");
                                }
                                VectorPainterKt.RenderVectorGroup((VectorGroup) vectorNode, map, $composer4, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer2, 54), $composer2, 805306368, 0);
                        $composer2.endReplaceGroup();
                        it = it2;
                        mapEmptyMap = map32;
                        $dirty = $dirty2;
                    } else {
                        $composer2.startReplaceGroup(800876643);
                        $composer2.endReplaceGroup();
                        it = it2;
                        mapEmptyMap = map32;
                        $dirty = $dirty2;
                    }
                }
            }
            Map<String, ? extends VectorConfig> map4 = mapEmptyMap;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            map2 = map4;
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt.RenderVectorGroup.2
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Map<String, VectorConfig> $configs;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass2(Map<String, ? extends VectorConfig> map22, int $changed2, int i3) {
                    super(2);
                    map = map22;
                    i = $changed2;
                    i = i3;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    VectorPainterKt.RenderVectorGroup(vectorGroup, map, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$1 */
    /* JADX INFO: compiled from: VectorPainter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ Map<String, VectorConfig> $configs;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Map<String, ? extends VectorConfig> map32) {
            super(2);
            map = map32;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer4, int $changed2) {
            ComposerKt.sourceInformation($composer4, "C463@18468L56:VectorPainter.kt#huu6hf");
            if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                $composer4.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1450046638, $changed2, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup.<anonymous> (VectorPainter.kt:463)");
            }
            VectorPainterKt.RenderVectorGroup((VectorGroup) vectorNode, map, $composer4, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }
}
