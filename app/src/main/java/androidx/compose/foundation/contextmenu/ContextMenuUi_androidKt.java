package androidx.compose.foundation.contextmenu;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContextMenuUi.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\b\b\u0001\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\u00020\b*\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u00042\b\b\u0001\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\"\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u00042\b\b\u0001\u0010\u000b\u001a\u00020\u0004H\u0002\u001a\u001d\u0010\u0011\u001a\u00020\b*\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001d\u0010\u0014\u001a\u00020\b*\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0015\u0010\u0013¨\u0006\u0016"}, d2 = {"computeContextMenuColors", "Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "backgroundStyleId", "", "foregroundStyleId", "(IILandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "resolveColor", "Landroidx/compose/ui/graphics/Color;", "Landroid/content/Context;", "resId", "attrId", "defaultColor", "resolveColor-g2O1Hgs", "(Landroid/content/Context;IIJ)J", "resolveColorStateList", "Landroid/content/res/ColorStateList;", "enabledColor", "enabledColor-4WTKRHQ", "(Landroid/content/res/ColorStateList;J)J", "disabledColor", "disabledColor-4WTKRHQ", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ContextMenuUi_androidKt {
    public static final ContextMenuColors computeContextMenuColors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1428061410, "C(computeContextMenuColors)32@1185L160:ContextMenuUi.android.kt#3xeu6s");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1428061410, $changed, -1, "androidx.compose.foundation.contextmenu.computeContextMenuColors (ContextMenuUi.android.kt:32)");
        }
        ContextMenuColors contextMenuColorsComputeContextMenuColors = computeContextMenuColors(R.style.Widget.PopupMenu, R.style.TextAppearance.Widget.PopupMenu.Large, $composer, 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return contextMenuColorsComputeContextMenuColors;
    }

    public static final ContextMenuColors computeContextMenuColors(int backgroundStyleId, int foregroundStyleId, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1689505294, "C(computeContextMenuColors)N(backgroundStyleId,foregroundStyleId)42@1528L7,43@1584L7,43@1547L851:ContextMenuUi.android.kt#3xeu6s");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1689505294, $changed, -1, "androidx.compose.foundation.contextmenu.computeContextMenuColors (ContextMenuUi.android.kt:41)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) objConsume;
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 116956225, "CC(remember):ContextMenuUi.android.kt#9igjgp");
        boolean invalid$iv = $composer.changed(context) | $composer.changed((Configuration) objConsume2);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            long backgroundColor = m418resolveColorg2O1Hgs(context, backgroundStyleId, R.attr.colorBackground, ContextMenuUiKt.getDefaultContextMenuColors().getBackgroundColor());
            ColorStateList textColorStateList = resolveColorStateList(context, foregroundStyleId, R.attr.textColorPrimary);
            long enabledColor = m417enabledColor4WTKRHQ(textColorStateList, ContextMenuUiKt.getDefaultContextMenuColors().getTextColor());
            long disabledColor = m416disabledColor4WTKRHQ(textColorStateList, ContextMenuUiKt.getDefaultContextMenuColors().getDisabledTextColor());
            Object value$iv = new ContextMenuColors(backgroundColor, enabledColor, enabledColor, disabledColor, disabledColor, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ContextMenuColors contextMenuColors = (ContextMenuColors) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return contextMenuColors;
    }

    /* JADX INFO: renamed from: resolveColor-g2O1Hgs, reason: not valid java name */
    private static final long m418resolveColorg2O1Hgs(Context $this$resolveColor_u2dg2O1Hgs, int resId, int attrId, long defaultColor) {
        TypedArray typedArray = $this$resolveColor_u2dg2O1Hgs.obtainStyledAttributes(resId, new int[]{attrId});
        int defaultColorAndroid = ColorKt.m5367toArgb8_81llA(defaultColor);
        int colorInt = typedArray.getColor(0, defaultColorAndroid);
        typedArray.recycle();
        return colorInt == defaultColorAndroid ? defaultColor : ColorKt.Color(colorInt);
    }

    private static final ColorStateList resolveColorStateList(Context $this$resolveColorStateList, int resId, int attrId) {
        TypedArray typedArray = $this$resolveColorStateList.obtainStyledAttributes(resId, new int[]{attrId});
        ColorStateList colorStateList = typedArray.getColorStateList(0);
        typedArray.recycle();
        return colorStateList;
    }

    /* JADX INFO: renamed from: enabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m417enabledColor4WTKRHQ(ColorStateList $this$enabledColor_u2d4WTKRHQ, long defaultColor) {
        int defaultColorArgb = ColorKt.m5367toArgb8_81llA(defaultColor);
        Integer color = $this$enabledColor_u2d4WTKRHQ != null ? Integer.valueOf($this$enabledColor_u2d4WTKRHQ.getColorForState(new int[]{R.attr.state_enabled}, defaultColorArgb)) : null;
        return (color == null || color.intValue() == defaultColorArgb) ? defaultColor : ColorKt.Color(color.intValue());
    }

    /* JADX INFO: renamed from: disabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m416disabledColor4WTKRHQ(ColorStateList $this$disabledColor_u2d4WTKRHQ, long defaultColor) {
        int defaultColorArgb = ColorKt.m5367toArgb8_81llA(defaultColor);
        Integer color = $this$disabledColor_u2d4WTKRHQ != null ? Integer.valueOf($this$disabledColor_u2d4WTKRHQ.getColorForState(new int[]{-16842910}, defaultColorArgb)) : null;
        return (color == null || color.intValue() == defaultColorArgb) ? defaultColor : ColorKt.Color(color.intValue());
    }
}
