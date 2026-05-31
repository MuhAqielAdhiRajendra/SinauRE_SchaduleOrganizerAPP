package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.profileinstaller.ProfileVerifier;
import androidx.window.core.layout.WindowSizeClass;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006Js\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0012\u0010\u0013J?\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0004\b\"\u0010#JG\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0001¢\u0006\u0004\b&\u0010'J3\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010+J3\u0010(\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010,J\u0093\u0001\u0010(\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000205H\u0007¢\u0006\u0004\b7\u00108J\u009b\u0001\u0010(\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u00109\u001a\u0002052\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000205H\u0001¢\u0006\u0004\b:\u0010;J\u009d\u0001\u0010<\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u0002052\b\b\u0002\u00109\u001a\u000205H\u0001¢\u0006\u0004\b=\u0010>J\u009d\u0001\u0010?\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u00109\u001a\u0002052\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u001f\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12#\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u0010@\u001a\u00020\u001f2\u0006\u0010A\u001a\u00020\u001fH\u0003¢\u0006\u0004\bB\u0010CJ3\u0010(\u001a\u00020\u00192\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010FJ\u0093\u0001\u0010(\u001a\u00020\u00192\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000205H\u0007¢\u0006\u0004\b7\u0010GJ\u009b\u0001\u0010(\u001a\u00020\u00192\u0006\u0010D\u001a\u00020E2\u0006\u00109\u001a\u0002052\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000205H\u0001¢\u0006\u0004\b:\u0010HJ\u008d\u0001\u0010?\u001a\u00020\u00192\u0006\u0010D\u001a\u00020E2\u0006\u00109\u001a\u0002052\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u001f\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12#\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000205H\u0003¢\u0006\u0004\bI\u0010JJï\u0001\u0010K\u001a\u00020\u0019*\u00020/2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020O2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010Q\u001a\u0002052\u0006\u0010R\u001a\u0002052\u0006\u0010S\u001a\u0002052\u0006\u0010T\u001a\u0002052\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u00109\u001a\u0002052\u001f\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12#\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\u0006\u0010U\u001a\u00020\u001f2\b\b\u0002\u0010@\u001a\u00020\u001f2\b\b\u0002\u0010V\u001a\u00020W2\b\b\u0002\u0010A\u001a\u00020\u001fH\u0002¢\u0006\u0004\bX\u0010YJC\u0010Z\u001a\u00020\u0019*\u00020/2\u0006\u0010V\u001a\u00020W2\u0006\u0010[\u001a\u0002002\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020\b2\u0006\u0010_\u001a\u00020O2\u0006\u0010`\u001a\u00020OH\u0002¢\u0006\u0004\ba\u0010bJ)\u0010-\u001a\u00020\u0019*\u00020/2\u0006\u0010[\u001a\u0002002\u0006\u0010\\\u001a\u0002052\u0006\u0010^\u001a\u00020\b¢\u0006\u0004\bc\u0010dR\u0018\u0010\u0014\u001a\u00020\u0005*\u00020\u00158@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010e\u001a\u000205¢\u0006\n\n\u0002\u0010h\u001a\u0004\bf\u0010gR\u0013\u0010i\u001a\u000205¢\u0006\n\n\u0002\u0010h\u001a\u0004\bj\u0010gR\u000e\u0010k\u001a\u00020lX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006m"}, d2 = {"Landroidx/compose/material3/SliderDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/SliderColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SliderColors;", "thumbColor", "Landroidx/compose/ui/graphics/Color;", "activeTrackColor", "activeTickColor", "inactiveTrackColor", "inactiveTickColor", "disabledThumbColor", "disabledActiveTrackColor", "disabledActiveTickColor", "disabledInactiveTrackColor", "disabledInactiveTickColor", "colors-q0g_0yA", "(JJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SliderColors;", "defaultSliderColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultSliderColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SliderColors;", "Thumb", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "thumbSize", "Landroidx/compose/ui/unit/DpSize;", "Thumb-9LiSoMs", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZJLandroidx/compose/runtime/Composer;II)V", "sliderState", "Landroidx/compose/material3/SliderState;", "Thumb-HwbPF3A$material3", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZJLandroidx/compose/runtime/Composer;II)V", "Track", "sliderPositions", "Landroidx/compose/material3/SliderPositions;", "(Landroidx/compose/material3/SliderPositions;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "drawStopIndicator", "Lkotlin/Function2;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ExtensionFunctionType;", "drawTick", "Lkotlin/Function3;", "thumbTrackGapSize", "Landroidx/compose/ui/unit/Dp;", "trackInsideCornerSize", "Track-4EFweAY", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "trackCornerSize", "Track-mnvyFg4$material3", "(Landroidx/compose/material3/SliderState;FLandroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "CenteredTrack", "CenteredTrack-7LSsfP0$material3", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFFLandroidx/compose/runtime/Composer;II)V", "TrackImpl", "enableCornerShrinking", "isCentered", "TrackImpl-VvwgllI", "(Landroidx/compose/material3/SliderState;FLandroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFZZLandroidx/compose/runtime/Composer;II)V", "rangeSliderState", "Landroidx/compose/material3/RangeSliderState;", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/RangeSliderState;FLandroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "TrackImpl-xlyIBlM", "(Landroidx/compose/material3/RangeSliderState;FLandroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;I)V", "drawTrack", "tickFractions", "", "activeRangeStart", "", "activeRangeEnd", "startThumbWidth", "startThumbHeight", "endThumbWidth", "endThumbHeight", "isRangeSlider", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "drawTrack-GVD57ws", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;[FFFJJJJFFFFFFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;ZZLandroidx/compose/foundation/gestures/Orientation;Z)V", "drawTrackPath", TypedValues.CycleType.S_WAVE_OFFSET, "size", "Landroidx/compose/ui/geometry/Size;", TypedValues.Custom.S_COLOR, "startCornerRadius", "endCornerRadius", "drawTrackPath-zXTsYAs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/foundation/gestures/Orientation;JJJFF)V", "drawStopIndicator-x3O1jOs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFJ)V", "TrackStopIndicatorSize", "getTrackStopIndicatorSize-D9Ej5fM", "()F", "F", "TickSize", "getTickSize-D9Ej5fM", "trackPath", "Landroidx/compose/ui/graphics/Path;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SliderDefaults {
    public static final int $stable = 0;
    public static final SliderDefaults INSTANCE = new SliderDefaults();
    private static final float TrackStopIndicatorSize = SliderTokens.INSTANCE.m4175getStopIndicatorSizeD9Ej5fM();
    private static final float TickSize = SliderTokens.INSTANCE.m4175getStopIndicatorSizeD9Ej5fM();
    private static final Path trackPath = AndroidPath_androidKt.Path();

    static final Unit CenteredTrack_7LSsfP0$lambda$25(SliderDefaults sliderDefaults, SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f, float f2, float f3, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m2961CenteredTrack7LSsfP0$material3(sliderState, modifier, z, sliderColors, function2, function3, f, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Thumb_9LiSoMs$lambda$3(SliderDefaults sliderDefaults, MutableInteractionSource mutableInteractionSource, Modifier modifier, SliderColors sliderColors, boolean z, long j, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m2962Thumb9LiSoMs(mutableInteractionSource, modifier, sliderColors, z, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Thumb_HwbPF3A$lambda$6(SliderDefaults sliderDefaults, MutableInteractionSource mutableInteractionSource, SliderState sliderState, Modifier modifier, SliderColors sliderColors, boolean z, long j, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m2963ThumbHwbPF3A$material3(mutableInteractionSource, sliderState, modifier, sliderColors, z, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Track$lambda$12(SliderDefaults sliderDefaults, SliderPositions sliderPositions, Modifier modifier, SliderColors sliderColors, boolean z, int i, int i2, Composer composer, int i3) {
        sliderDefaults.Track(sliderPositions, modifier, sliderColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Track$lambda$13(SliderDefaults sliderDefaults, SliderState sliderState, Modifier modifier, SliderColors sliderColors, boolean z, int i, int i2, Composer composer, int i3) {
        sliderDefaults.Track(sliderState, modifier, sliderColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Track$lambda$33(SliderDefaults sliderDefaults, RangeSliderState rangeSliderState, Modifier modifier, SliderColors sliderColors, boolean z, int i, int i2, Composer composer, int i3) {
        sliderDefaults.Track(rangeSliderState, modifier, sliderColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TrackImpl_VvwgllI$lambda$32(SliderDefaults sliderDefaults, SliderState sliderState, float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f2, float f3, boolean z2, boolean z3, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m2956TrackImplVvwgllI(sliderState, f, modifier, z, sliderColors, function2, function3, f2, f3, z2, z3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    static final Unit TrackImpl_xlyIBlM$lambda$47(SliderDefaults sliderDefaults, RangeSliderState rangeSliderState, float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f2, float f3, int i, Composer composer, int i2) {
        sliderDefaults.m2957TrackImplxlyIBlM(rangeSliderState, f, modifier, z, sliderColors, function2, function3, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Track_4EFweAY$lambda$17(SliderDefaults sliderDefaults, SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f, float f2, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m2965Track4EFweAY(sliderState, modifier, z, sliderColors, (Function2<? super DrawScope, ? super Offset, Unit>) function2, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) function3, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Track_4EFweAY$lambda$37(SliderDefaults sliderDefaults, RangeSliderState rangeSliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f, float f2, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m2964Track4EFweAY(rangeSliderState, modifier, z, sliderColors, (Function2<? super DrawScope, ? super Offset, Unit>) function2, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) function3, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Track_mnvyFg4$lambda$21(SliderDefaults sliderDefaults, SliderState sliderState, float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f2, float f3, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m2967TrackmnvyFg4$material3(sliderState, f, modifier, z, sliderColors, (Function2<? super DrawScope, ? super Offset, Unit>) function2, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) function3, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Track_mnvyFg4$lambda$41(SliderDefaults sliderDefaults, RangeSliderState rangeSliderState, float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f2, float f3, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m2966TrackmnvyFg4$material3(rangeSliderState, f, modifier, z, sliderColors, (Function2<? super DrawScope, ? super Offset, Unit>) function2, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) function3, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private SliderDefaults() {
    }

    public final SliderColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1376295968, "C(colors)1107@48675L11:Slider.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1376295968, $changed, -1, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:1107)");
        }
        SliderColors defaultSliderColors$material3 = getDefaultSliderColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSliderColors$material3;
    }

    /* JADX INFO: renamed from: colors-q0g_0yA, reason: not valid java name */
    public final SliderColors m2968colorsq0g_0yA(long thumbColor, long activeTrackColor, long activeTickColor, long inactiveTrackColor, long inactiveTickColor, long disabledThumbColor, long disabledActiveTrackColor, long disabledActiveTickColor, long disabledInactiveTrackColor, long disabledInactiveTickColor, Composer $composer, int $changed, int $changed1, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 885588574, "C(colors)N(thumbColor:c#ui.graphics.Color,activeTrackColor:c#ui.graphics.Color,activeTickColor:c#ui.graphics.Color,inactiveTrackColor:c#ui.graphics.Color,inactiveTickColor:c#ui.graphics.Color,disabledThumbColor:c#ui.graphics.Color,disabledActiveTrackColor:c#ui.graphics.Color,disabledActiveTickColor:c#ui.graphics.Color,disabledInactiveTrackColor:c#ui.graphics.Color,disabledInactiveTickColor:c#ui.graphics.Color)1149@50999L11:Slider.kt#uh7d8r");
        long thumbColor2 = (i & 1) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : thumbColor;
        long activeTrackColor2 = (i & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : activeTrackColor;
        long activeTickColor2 = (i & 4) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : activeTickColor;
        long inactiveTrackColor2 = (i & 8) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : inactiveTrackColor;
        long inactiveTickColor2 = (i & 16) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : inactiveTickColor;
        long disabledThumbColor2 = (i & 32) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledThumbColor;
        long disabledActiveTrackColor2 = (i & 64) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledActiveTrackColor;
        long disabledActiveTickColor2 = (i & 128) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledActiveTickColor;
        long disabledInactiveTrackColor2 = (i & 256) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledInactiveTrackColor;
        long disabledInactiveTickColor2 = (i & 512) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : disabledInactiveTickColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(885588574, $changed, $changed1, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:1149)");
        }
        SliderColors sliderColorsM2942copyK518z4 = getDefaultSliderColors$material3(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m2942copyK518z4(thumbColor2, activeTrackColor2, activeTickColor2, inactiveTrackColor2, inactiveTickColor2, disabledThumbColor2, disabledActiveTrackColor2, disabledActiveTickColor2, disabledInactiveTrackColor2, disabledInactiveTickColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return sliderColorsM2942copyK518z4;
    }

    public final SliderColors getDefaultSliderColors$material3(ColorScheme $this$defaultSliderColors) {
        SliderColors it = $this$defaultSliderColors.getDefaultSliderColorsCached();
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getHandleColor());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getActiveTrackColor());
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getInactiveTrackColor());
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getInactiveTrackColor());
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getActiveTrackColor());
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledHandleColor());
            long jM5358compositeOverOWjLjI = ColorKt.m5358compositeOverOWjLjI(Color.m5311copywmQWz5c(jFromToken6, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken6) : SliderTokens.INSTANCE.getDisabledHandleOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken6) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken6) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken6) : 0.0f), $this$defaultSliderColors.getSurface());
            long jFromToken7 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledActiveTrackColor());
            long jM5311copywmQWz5c = Color.m5311copywmQWz5c(jFromToken7, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken7) : SliderTokens.INSTANCE.getDisabledActiveTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken7) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken7) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken7) : 0.0f);
            long jFromToken8 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledInactiveTrackColor());
            long jM5311copywmQWz5c2 = Color.m5311copywmQWz5c(jFromToken8, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken8) : SliderTokens.INSTANCE.getDisabledInactiveTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken8) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken8) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken8) : 0.0f);
            long jFromToken9 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledInactiveTrackColor());
            long jM5311copywmQWz5c3 = Color.m5311copywmQWz5c(jFromToken9, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken9) : SliderTokens.INSTANCE.getDisabledInactiveTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken9) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken9) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken9) : 0.0f);
            long jFromToken10 = ColorSchemeKt.fromToken($this$defaultSliderColors, SliderTokens.INSTANCE.getDisabledActiveTrackColor());
            SliderColors it2 = new SliderColors(jFromToken, jFromToken2, jFromToken3, jFromToken4, jFromToken5, jM5358compositeOverOWjLjI, jM5311copywmQWz5c, jM5311copywmQWz5c2, jM5311copywmQWz5c3, Color.m5311copywmQWz5c(jFromToken10, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken10) : SliderTokens.INSTANCE.getDisabledActiveTrackOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken10) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken10) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken10) : 0.0f), null);
            $this$defaultSliderColors.setDefaultSliderColorsCached$material3(it2);
            return it2;
        }
        return it;
    }

    /* JADX INFO: renamed from: Thumb-9LiSoMs, reason: not valid java name */
    public final void m2962Thumb9LiSoMs(final MutableInteractionSource interactionSource, Modifier modifier, SliderColors colors, boolean enabled, long thumbSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors colors2;
        boolean enabled2;
        long j;
        final Modifier modifier3;
        final long thumbSize2;
        final SliderColors colors3;
        final boolean enabled3;
        long thumbSize3;
        long size;
        Composer $composer2 = $composer.startRestartGroup(-290277409);
        ComposerKt.sourceInformation($composer2, "C(Thumb)N(interactionSource,modifier,colors,enabled,thumbSize:c#ui.unit.DpSize)1213@54450L46,1214@54539L658,1214@54505L692,1237@55596L5,1233@55392L220:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                colors2 = colors;
                int i3 = $composer2.changed(colors2) ? 256 : 128;
                $dirty |= i3;
            } else {
                colors2 = colors;
            }
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            j = thumbSize;
        } else if (($changed & 24576) == 0) {
            j = thumbSize;
            $dirty |= $composer2.changed(j) ? 16384 : 8192;
        } else {
            j = thumbSize;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changed(this) ? 131072 : 65536;
        }
        if ($composer2.shouldExecute(($dirty & 74899) != 74898, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "1209@54333L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                thumbSize3 = j;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    SliderColors colors4 = colors($composer2, ($dirty >> 15) & 14);
                    $dirty &= -897;
                    colors2 = colors4;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if (i5 != 0) {
                    thumbSize3 = SliderKt.ThumbSize;
                } else {
                    thumbSize3 = j;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-290277409, $dirty, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 216292781, "CC(remember):Slider.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotStateKt.mutableStateListOf();
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            SnapshotStateList interactions = (SnapshotStateList) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 216296241, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = (Function2) new SliderDefaults$Thumb$1$1(interactionSource, interactions, null);
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer2, $dirty & 14);
            if (!interactions.isEmpty()) {
                float arg0$iv = DpSize.m8248getWidthD9Ej5fM(thumbSize3);
                size = DpSize.m8241copyDwJknco$default(thumbSize3, Dp.m8150constructorimpl(arg0$iv / 2), 0.0f, 2, null);
            } else {
                size = thumbSize3;
            }
            SpacerKt.Spacer(BackgroundKt.m285backgroundbw27NRU(HoverableKt.hoverable$default(SizeKt.m1116size6HolHcs(modifier2, size), interactionSource, false, 2, null), colors2.m2953thumbColorvNxB06k$material3(enabled2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), $composer2, 6)), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            thumbSize2 = thumbSize3;
            colors3 = colors2;
            enabled3 = enabled2;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            thumbSize2 = j;
            colors3 = colors2;
            enabled3 = enabled2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.Thumb_9LiSoMs$lambda$3(this.f$0, interactionSource, modifier3, colors3, enabled3, thumbSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: Thumb-HwbPF3A$material3, reason: not valid java name */
    public final void m2963ThumbHwbPF3A$material3(final MutableInteractionSource interactionSource, final SliderState sliderState, Modifier modifier, SliderColors colors, boolean enabled, long thumbSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors colors2;
        boolean enabled2;
        int i2;
        long j;
        final Modifier modifier3;
        final long thumbSize2;
        final SliderColors colors3;
        final boolean enabled3;
        long thumbSize3;
        long size;
        Composer $composer2 = $composer.startRestartGroup(-889714565);
        ComposerKt.sourceInformation($composer2, "C(Thumb)N(interactionSource,sliderState,modifier,colors,enabled,thumbSize:c#ui.unit.DpSize)1266@56862L46,1267@56951L658,1267@56917L692,1294@58180L5,1290@57976L220:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(sliderState) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i4 = $composer2.changed(colors2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                colors2 = colors;
            }
            $dirty |= i4;
        } else {
            colors2 = colors;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            enabled2 = enabled;
        } else if (($changed & 24576) == 0) {
            enabled2 = enabled;
            $dirty |= $composer2.changed(enabled2) ? 16384 : 8192;
        } else {
            enabled2 = enabled;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i2 = i3;
            j = thumbSize;
        } else if ((196608 & $changed) == 0) {
            i2 = i3;
            j = thumbSize;
            $dirty |= $composer2.changed(j) ? 131072 : 65536;
        } else {
            i2 = i3;
            j = thumbSize;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(this) ? 1048576 : 524288;
        }
        if ($composer2.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "1262@56745L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                thumbSize3 = j;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 8) != 0) {
                    SliderColors colors4 = colors($composer2, ($dirty >> 18) & 14);
                    $dirty &= -7169;
                    colors2 = colors4;
                }
                if (i5 != 0) {
                    enabled2 = true;
                }
                if (i6 != 0) {
                    thumbSize3 = SliderKt.ThumbSize;
                } else {
                    thumbSize3 = j;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-889714565, $dirty, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -317193687, "CC(remember):Slider.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotStateKt.mutableStateListOf();
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            SnapshotStateList interactions = (SnapshotStateList) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -317190227, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = (Function2) new SliderDefaults$Thumb$3$1(interactionSource, interactions, null);
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer2, $dirty & 14);
            if (!interactions.isEmpty()) {
                if (sliderState.getOrientation() == Orientation.Vertical) {
                    float arg0$iv = DpSize.m8246getHeightD9Ej5fM(thumbSize3);
                    size = DpSize.m8241copyDwJknco$default(thumbSize3, 0.0f, Dp.m8150constructorimpl(arg0$iv / 2), 1, null);
                } else {
                    float arg0$iv2 = DpSize.m8248getWidthD9Ej5fM(thumbSize3);
                    size = DpSize.m8241copyDwJknco$default(thumbSize3, Dp.m8150constructorimpl(arg0$iv2 / 2), 0.0f, 2, null);
                }
            } else {
                size = thumbSize3;
            }
            SpacerKt.Spacer(BackgroundKt.m285backgroundbw27NRU(HoverableKt.hoverable$default(SizeKt.m1116size6HolHcs(modifier2, size), interactionSource, false, 2, null), colors2.m2953thumbColorvNxB06k$material3(enabled2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), $composer2, 6)), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            thumbSize2 = thumbSize3;
            colors3 = colors2;
            enabled3 = enabled2;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            thumbSize2 = j;
            colors3 = colors2;
            enabled3 = enabled2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.Thumb_HwbPF3A$lambda$6(this.f$0, interactionSource, sliderState, modifier3, colors3, enabled3, thumbSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(message = "Use version that supports slider state")
    public final void Track(final SliderPositions sliderPositions, Modifier modifier, SliderColors colors, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors sliderColors;
        boolean z;
        final Modifier.Companion modifier3;
        final SliderColors colors2;
        final boolean enabled2;
        SliderColors colors3;
        boolean enabled3;
        Object value$iv;
        Modifier modifier4;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-1546713545);
        ComposerKt.sourceInformation($composer2, "C(Track)N(sliderPositions,modifier,colors,enabled)1323@59516L1838,1323@59464L1890:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(sliderPositions) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sliderColors = colors;
                int i4 = $composer2.changed(sliderColors) ? 256 : 128;
                $dirty |= i4;
            } else {
                sliderColors = colors;
            }
            $dirty |= i4;
        } else {
            sliderColors = colors;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(this) ? 16384 : 8192;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "1316@59111L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
                colors3 = sliderColors;
                enabled3 = z;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 4) == 0) {
                    colors3 = sliderColors;
                } else {
                    colors3 = colors($composer2, ($dirty >> 12) & 14);
                    $dirty &= -897;
                }
                if (i5 == 0) {
                    enabled3 = z;
                } else {
                    enabled3 = true;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1546713545, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1318)");
            }
            final long inactiveTrackColor = colors3.m2955trackColorWaAFU9c$material3(enabled3, false);
            final long activeTrackColor = colors3.m2955trackColorWaAFU9c$material3(enabled3, true);
            final long inactiveTickColor = colors3.m2954tickColorWaAFU9c$material3(enabled3, false);
            final long activeTickColor = colors3.m2954tickColorWaAFU9c$material3(enabled3, true);
            Modifier modifierM1101height3ABfNKs = SizeKt.m1101height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), SliderKt.getTrackHeight());
            ComposerKt.sourceInformationMarkerStart($composer2, -333010299, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(inactiveTrackColor) | (($dirty & 14) == 4) | $composer2.changed(activeTrackColor) | $composer2.changed(inactiveTickColor) | $composer2.changed(activeTickColor);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                modifier4 = modifierM1101height3ABfNKs;
                i2 = 0;
                value$iv = new Function1() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderDefaults.Track$lambda$11$lambda$10(inactiveTrackColor, sliderPositions, activeTrackColor, inactiveTickColor, activeTickColor, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
                modifier4 = modifierM1101height3ABfNKs;
                i2 = 0;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier4, (Function1) value$iv, $composer2, i2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors3;
            enabled2 = enabled3;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = sliderColors;
            enabled2 = z;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.Track$lambda$12(this.f$0, sliderPositions, modifier3, colors2, enabled2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Track$lambda$11$lambda$10(long $inactiveTrackColor, SliderPositions $sliderPositions, long $activeTrackColor, long $inactiveTickColor, long $activeTickColor, DrawScope $this$Canvas) {
        ArrayList answer$iv$iv$iv;
        boolean isRtl = $this$Canvas.getLayoutDirection() == LayoutDirection.Rtl;
        long arg0$iv = $this$Canvas.mo5886getCenterF1C5BW0();
        int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
        float y$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        long v1$iv$iv = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        long arg0$iv2 = $this$Canvas.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv2 = (int) (arg0$iv2 >> 32);
        float x$iv = Float.intBitsToFloat(bits$iv$iv$iv2);
        long arg0$iv3 = $this$Canvas.mo5886getCenterF1C5BW0();
        int bits$iv$iv$iv3 = (int) (arg0$iv3 & 4294967295L);
        float y$iv2 = Float.intBitsToFloat(bits$iv$iv$iv3);
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        long jM5060constructorimpl2 = Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
        long sliderStart = isRtl ? jM5060constructorimpl2 : jM5060constructorimpl;
        long sliderEnd = isRtl ? jM5060constructorimpl : jM5060constructorimpl2;
        float tickSize = $this$Canvas.mo432toPx0680j_4(TickSize);
        float trackStrokeWidth = $this$Canvas.mo432toPx0680j_4(SliderKt.getTrackHeight());
        DrawScope.m5873drawLineNGM6Ib0$default($this$Canvas, $inactiveTrackColor, sliderStart, sliderEnd, trackStrokeWidth, StrokeCap.INSTANCE.m5688getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        long value$iv$iv = sliderStart;
        int bits$iv$iv$iv4 = (int) (value$iv$iv >> 32);
        long value$iv$iv2 = sliderEnd;
        int bits$iv$iv$iv5 = (int) (value$iv$iv2 >> 32);
        long value$iv$iv3 = sliderStart;
        int bits$iv$iv$iv6 = (int) (value$iv$iv3 >> 32);
        float x$iv2 = Float.intBitsToFloat(bits$iv$iv$iv4) + ((Float.intBitsToFloat(bits$iv$iv$iv5) - Float.intBitsToFloat(bits$iv$iv$iv6)) * $sliderPositions.getActiveRange().getEndInclusive().floatValue());
        long arg0$iv4 = $this$Canvas.mo5886getCenterF1C5BW0();
        int bits$iv$iv$iv7 = (int) (arg0$iv4 & 4294967295L);
        float y$iv3 = Float.intBitsToFloat(bits$iv$iv$iv7);
        long v1$iv$iv3 = Float.floatToRawIntBits(x$iv2);
        long v2$iv$iv3 = Float.floatToRawIntBits(y$iv3);
        long sliderValueEnd = Offset.m5060constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv3 & 4294967295L));
        long value$iv$iv4 = sliderStart;
        int bits$iv$iv$iv8 = (int) (value$iv$iv4 >> 32);
        long value$iv$iv5 = sliderEnd;
        int bits$iv$iv$iv9 = (int) (value$iv$iv5 >> 32);
        long value$iv$iv6 = sliderStart;
        int bits$iv$iv$iv10 = (int) (value$iv$iv6 >> 32);
        float x$iv3 = Float.intBitsToFloat(bits$iv$iv$iv8) + ((Float.intBitsToFloat(bits$iv$iv$iv9) - Float.intBitsToFloat(bits$iv$iv$iv10)) * $sliderPositions.getActiveRange().getStart().floatValue());
        long arg0$iv5 = $this$Canvas.mo5886getCenterF1C5BW0();
        long sliderValueEnd2 = arg0$iv5 & 4294967295L;
        int bits$iv$iv$iv11 = (int) sliderValueEnd2;
        float y$iv4 = Float.intBitsToFloat(bits$iv$iv$iv11);
        long v1$iv$iv4 = Float.floatToRawIntBits(x$iv3);
        long v2$iv$iv4 = Float.floatToRawIntBits(y$iv4);
        long sliderValueStart = Offset.m5060constructorimpl((v1$iv$iv4 << 32) | (v2$iv$iv4 & 4294967295L));
        long sliderStart2 = sliderStart;
        long sliderEnd2 = sliderEnd;
        DrawScope.m5873drawLineNGM6Ib0$default($this$Canvas, $activeTrackColor, sliderValueStart, sliderValueEnd, trackStrokeWidth, StrokeCap.INSTANCE.m5688getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        float trackStrokeWidth2 = trackStrokeWidth;
        float[] $this$groupBy$iv = $sliderPositions.getTickFractions();
        Map destination$iv$iv = new LinkedHashMap();
        int length = $this$groupBy$iv.length;
        for (int i = 0; i < length; i++) {
            float element$iv$iv = $this$groupBy$iv[i];
            Boolean boolValueOf = Boolean.valueOf(element$iv$iv > $sliderPositions.getActiveRange().getEndInclusive().floatValue() || element$iv$iv < $sliderPositions.getActiveRange().getStart().floatValue());
            Object value$iv$iv$iv = destination$iv$iv.get(boolValueOf);
            if (value$iv$iv$iv == null) {
                answer$iv$iv$iv = new ArrayList();
                destination$iv$iv.put(boolValueOf, answer$iv$iv$iv);
            } else {
                answer$iv$iv$iv = value$iv$iv$iv;
            }
            List list$iv$iv = (List) answer$iv$iv$iv;
            list$iv$iv.add(Float.valueOf(element$iv$iv));
        }
        for (Map.Entry element$iv : destination$iv$iv.entrySet()) {
            boolean outsideFraction = ((Boolean) element$iv.getKey()).booleanValue();
            List list = (List) element$iv.getValue();
            List $this$fastMap$iv = list;
            int $i$f$fastMap = 0;
            ArrayList target$iv = new ArrayList($this$fastMap$iv.size());
            int index$iv$iv = 0;
            int size = $this$fastMap$iv.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
                float it = ((Number) item$iv$iv).floatValue();
                long sliderStart3 = sliderStart2;
                long sliderEnd3 = sliderEnd2;
                long arg0$iv6 = OffsetKt.m5091lerpWko1d7g(sliderStart3, sliderEnd3, it);
                List $this$fastMap$iv2 = $this$fastMap$iv;
                int bits$iv$iv$iv12 = (int) (arg0$iv6 >> 32);
                float x$iv4 = Float.intBitsToFloat(bits$iv$iv$iv12);
                long arg0$iv7 = $this$Canvas.mo5886getCenterF1C5BW0();
                int bits$iv$iv$iv13 = (int) (arg0$iv7 & 4294967295L);
                float y$iv5 = Float.intBitsToFloat(bits$iv$iv$iv13);
                long v1$iv$iv5 = Float.floatToRawIntBits(x$iv4);
                long v2$iv$iv5 = Float.floatToRawIntBits(y$iv5);
                target$iv.add(Offset.m5057boximpl(Offset.m5060constructorimpl((v1$iv$iv5 << 32) | (v2$iv$iv5 & 4294967295L))));
                index$iv$iv++;
                sliderEnd2 = sliderEnd3;
                trackStrokeWidth2 = trackStrokeWidth2;
                $this$fastMap$iv = $this$fastMap$iv2;
                $i$f$fastMap = $i$f$fastMap;
                sliderStart2 = sliderStart3;
            }
            long sliderStart4 = sliderStart2;
            float trackStrokeWidth3 = trackStrokeWidth2;
            sliderEnd2 = sliderEnd2;
            DrawScope.m5878drawPointsF8ZwMP8$default($this$Canvas, target$iv, PointMode.INSTANCE.m5633getPointsr_lszbg(), outsideFraction ? $inactiveTickColor : $activeTickColor, tickSize, StrokeCap.INSTANCE.m5688getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
            trackStrokeWidth2 = trackStrokeWidth3;
            sliderStart2 = sliderStart4;
        }
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacySliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(sliderState, modifier, enabled, colors, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    public final /* synthetic */ void Track(final SliderState sliderState, Modifier modifier, SliderColors colors, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors sliderColors;
        boolean z;
        final Modifier modifier3;
        final SliderColors colors2;
        final boolean enabled2;
        Modifier.Companion modifier4;
        SliderColors colors3;
        int $dirty;
        Modifier modifier5;
        boolean enabled3;
        Composer $composer2 = $composer.startRestartGroup(593554206);
        ComposerKt.sourceInformation($composer2, "C(Track)N(sliderState,modifier,colors,enabled)1401@62715L214:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(sliderState) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sliderColors = colors;
                int i3 = $composer2.changed(sliderColors) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                sliderColors = colors;
            }
            $dirty2 |= i3;
        } else {
            sliderColors = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(this) ? 16384 : 8192;
        }
        if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = sliderColors;
            enabled2 = z;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "1398@62656L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier5 = modifier2;
                colors2 = sliderColors;
                enabled3 = z;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    colors3 = sliderColors;
                } else {
                    colors3 = colors($composer2, ($dirty2 >> 12) & 14);
                    $dirty2 &= -897;
                }
                if (i4 == 0) {
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                    colors2 = colors3;
                    enabled3 = z;
                } else {
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                    colors2 = colors3;
                    enabled3 = true;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(593554206, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1400)");
            }
            m2965Track4EFweAY(sliderState, modifier5, enabled3, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, $composer2, ($dirty & 14) | 14155776 | ($dirty & 112) | (($dirty >> 3) & 896) | (($dirty << 3) & 7168) | (234881024 & ($dirty << 12)), 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled2 = enabled3;
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.Track$lambda$13(this.f$0, sliderState, modifier3, colors2, enabled2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Track_4EFweAY$lambda$15$lambda$14(SliderColors $colors, boolean $enabled, DrawScope drawScope, Offset it) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM2955trackColorWaAFU9c$material3 = $colors.m2955trackColorWaAFU9c$material3($enabled, true);
        sliderDefaults.m2969drawStopIndicatorx3O1jOs(drawScope, it.m5078unboximpl(), TrackStopIndicatorSize, jM2955trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Track-4EFweAY, reason: not valid java name */
    public final void m2965Track4EFweAY(final SliderState sliderState, Modifier modifier, boolean enabled, SliderColors colors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float thumbTrackGapSize, float trackInsideCornerSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final boolean enabled2;
        final SliderColors colors2;
        Function2<? super DrawScope, ? super Offset, Unit> function22;
        final float thumbTrackGapSize2;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function32;
        final Modifier modifier3;
        final boolean enabled3;
        final SliderColors colors3;
        final Function2<? super DrawScope, ? super Offset, Unit> function23;
        final float trackInsideCornerSize2;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function33;
        int $dirty;
        Composer $composer2 = $composer.startRestartGroup(49984771);
        ComposerKt.sourceInformation($composer2, "C(Track)N(sliderState,modifier,enabled,colors,drawStopIndicator,drawTick,thumbTrackGapSize:c#ui.unit.Dp,trackInsideCornerSize:c#ui.unit.Dp)1447@64678L467:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(sliderState) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer2.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i4 = $composer2.changed(colors2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i4;
        } else {
            colors2 = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                function22 = function2;
                int i5 = $composer2.changedInstance(function22) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                function22 = function2;
            }
            $dirty2 |= i5;
        } else {
            function22 = function2;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            thumbTrackGapSize2 = thumbTrackGapSize;
        } else if (($changed & 1572864) == 0) {
            thumbTrackGapSize2 = thumbTrackGapSize;
            $dirty2 |= $composer2.changed(thumbTrackGapSize2) ? 1048576 : 524288;
        } else {
            thumbTrackGapSize2 = thumbTrackGapSize;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changed(trackInsideCornerSize) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer2.changed(this) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if ($composer2.shouldExecute((38347923 & $dirty2) != 38347922, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "1433@64119L8,1434@64188L199,1441@64443L107");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors4 = colors($composer2, ($dirty2 >> 24) & 14);
                    $dirty2 &= -7169;
                    colors2 = colors4;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, 790733290, "CC(remember):Slider.kt#9igjgp");
                    boolean invalid$iv = (((($dirty2 & 7168) ^ 3072) > 2048 && $composer2.changed(colors2)) || ($dirty2 & 3072) == 2048) | (($dirty2 & 896) == 256);
                    Object it$iv = $composer2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.Track_4EFweAY$lambda$15$lambda$14(colors2, enabled2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty2 &= -57345;
                    function22 = (Function2) it$iv;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, 790741358, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv2 = $composer2.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = (Function3) new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$5$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m2975invokewPWG1Vc(drawScope, offset.m5078unboximpl(), color.m5323unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m2975invokewPWG1Vc(DrawScope drawScope, long offset, long color) {
                                SliderDefaults.INSTANCE.m2969drawStopIndicatorx3O1jOs(drawScope, offset, SliderDefaults.INSTANCE.m2970getTickSizeD9Ej5fM(), color);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    function33 = (Function3) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                } else {
                    function33 = function3;
                }
                if (i7 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                }
                if (i8 != 0) {
                    function32 = function33;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                } else {
                    function32 = function33;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    function32 = function3;
                    $dirty = (-57345) & $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize;
                } else {
                    function32 = function3;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(49984771, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1446)");
            }
            m2956TrackImplVvwgllI(sliderState, Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM(), modifier3, enabled3, colors3, function23, function32, thumbTrackGapSize2, trackInsideCornerSize2, false, false, $composer2, ($dirty & 14) | 805306416 | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128) | (234881024 & ($dirty << 3)), (($dirty >> 21) & 112) | 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            function32 = function3;
            modifier3 = modifier2;
            enabled3 = enabled2;
            colors3 = colors2;
            function23 = function22;
            trackInsideCornerSize2 = trackInsideCornerSize;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.Track_4EFweAY$lambda$17(this.f$0, sliderState, modifier3, enabled3, colors3, function23, function32, thumbTrackGapSize2, trackInsideCornerSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Track_mnvyFg4$lambda$19$lambda$18(SliderColors $colors, boolean $enabled, DrawScope drawScope, Offset it) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM2955trackColorWaAFU9c$material3 = $colors.m2955trackColorWaAFU9c$material3($enabled, true);
        sliderDefaults.m2969drawStopIndicatorx3O1jOs(drawScope, it.m5078unboximpl(), TrackStopIndicatorSize, jM2955trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Track-mnvyFg4$material3, reason: not valid java name */
    public final void m2967TrackmnvyFg4$material3(final SliderState sliderState, final float trackCornerSize, Modifier modifier, boolean enabled, SliderColors colors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float thumbTrackGapSize, float trackInsideCornerSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final boolean enabled2;
        final SliderColors colors2;
        Function2<? super DrawScope, ? super Offset, Unit> function22;
        float thumbTrackGapSize2;
        int i2;
        Composer $composer2;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function32;
        final Modifier modifier3;
        final boolean enabled3;
        final SliderColors colors3;
        final float trackInsideCornerSize2;
        final Function2<? super DrawScope, ? super Offset, Unit> function23;
        final float trackInsideCornerSize3;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function33;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function34;
        int $dirty;
        Modifier modifier4;
        float trackInsideCornerSize4;
        boolean enabled4;
        SliderColors colors4;
        float thumbTrackGapSize3;
        Function2<? super DrawScope, ? super Offset, Unit> function24;
        Composer $composer3 = $composer.startRestartGroup(1691224881);
        ComposerKt.sourceInformation($composer3, "C(Track)N(sliderState,trackCornerSize:c#ui.unit.Dp,modifier,enabled,colors,drawStopIndicator,drawTick,thumbTrackGapSize:c#ui.unit.Dp,trackInsideCornerSize:c#ui.unit.Dp)1503@67150L467:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(sliderState) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(trackCornerSize) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                function22 = function2;
                int i6 = $composer3.changedInstance(function22) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                function22 = function2;
            }
            $dirty2 |= i6;
        } else {
            function22 = function2;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
            thumbTrackGapSize2 = thumbTrackGapSize;
        } else if (($changed & 12582912) == 0) {
            thumbTrackGapSize2 = thumbTrackGapSize;
            $dirty2 |= $composer3.changed(thumbTrackGapSize2) ? 8388608 : 4194304;
        } else {
            thumbTrackGapSize2 = thumbTrackGapSize;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
            i2 = i9;
        } else if (($changed & 100663296) == 0) {
            i2 = i9;
            $dirty2 |= $composer3.changed(trackInsideCornerSize) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i2 = i9;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changed(this) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if ($composer3.shouldExecute((306783379 & $dirty2) != 306783378, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1489@66591L8,1490@66660L199,1497@66915L107");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    SliderColors colors5 = colors($composer3, ($dirty2 >> 27) & 14);
                    $dirty2 &= -57345;
                    colors2 = colors5;
                }
                if ((i & 32) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1769483032, "CC(remember):Slider.kt#9igjgp");
                    boolean invalid$iv = ((((57344 & $dirty2) ^ 24576) > 16384 && $composer3.changed(colors2)) || ($dirty2 & 24576) == 16384) | (($dirty2 & 7168) == 2048);
                    Object it$iv = $composer3.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.Track_mnvyFg4$lambda$19$lambda$18(colors2, enabled2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty2 &= -458753;
                    function22 = (Function2) it$iv;
                }
                if (i7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1769491100, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = (Function3) new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m2976invokewPWG1Vc(drawScope, offset.m5078unboximpl(), color.m5323unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m2976invokewPWG1Vc(DrawScope drawScope, long offset, long color) {
                                SliderDefaults.INSTANCE.m2969drawStopIndicatorx3O1jOs(drawScope, offset, SliderDefaults.INSTANCE.m2970getTickSizeD9Ej5fM(), color);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    function33 = (Function3) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    function33 = function3;
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                }
                if (i2 != 0) {
                    Modifier modifier5 = modifier2;
                    function34 = function33;
                    $dirty = $dirty2;
                    modifier4 = modifier5;
                    trackInsideCornerSize4 = SliderKt.TrackInsideCornerSize;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    function24 = function22;
                } else {
                    Modifier modifier6 = modifier2;
                    function34 = function33;
                    $dirty = $dirty2;
                    modifier4 = modifier6;
                    trackInsideCornerSize4 = trackInsideCornerSize;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    function24 = function22;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    trackInsideCornerSize4 = trackInsideCornerSize;
                    $dirty = (-458753) & $dirty2;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    function24 = function22;
                    function34 = function3;
                } else {
                    trackInsideCornerSize4 = trackInsideCornerSize;
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    function24 = function22;
                    function34 = function3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1691224881, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
            }
            m2956TrackImplVvwgllI(sliderState, trackCornerSize, modifier4, enabled4, colors4, function24, function34, thumbTrackGapSize3, trackInsideCornerSize4, true, false, $composer3, ($dirty & 14) | 805306368 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty), (($dirty >> 24) & 112) | 6);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            trackInsideCornerSize3 = trackInsideCornerSize4;
            trackInsideCornerSize2 = thumbTrackGapSize3;
            function32 = function34;
            function23 = function24;
            colors3 = colors4;
            enabled3 = enabled4;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function32 = function3;
            modifier3 = modifier2;
            enabled3 = enabled2;
            colors3 = colors2;
            trackInsideCornerSize2 = thumbTrackGapSize2;
            function23 = function22;
            trackInsideCornerSize3 = trackInsideCornerSize;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.Track_mnvyFg4$lambda$21(this.f$0, sliderState, trackCornerSize, modifier3, enabled3, colors3, function23, function32, trackInsideCornerSize2, trackInsideCornerSize3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit CenteredTrack_7LSsfP0$lambda$23$lambda$22(SliderColors $colors, boolean $enabled, DrawScope drawScope, Offset it) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM2955trackColorWaAFU9c$material3 = $colors.m2955trackColorWaAFU9c$material3($enabled, true);
        sliderDefaults.m2969drawStopIndicatorx3O1jOs(drawScope, it.m5078unboximpl(), TrackStopIndicatorSize, jM2955trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CenteredTrack-7LSsfP0$material3, reason: not valid java name */
    public final void m2961CenteredTrack7LSsfP0$material3(final SliderState sliderState, Modifier modifier, boolean enabled, SliderColors colors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float thumbTrackGapSize, float trackInsideCornerSize, float trackCornerSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final boolean enabled2;
        final SliderColors colors2;
        Function2<? super DrawScope, ? super Offset, Unit> function22;
        final float thumbTrackGapSize2;
        int i2;
        Composer $composer2;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function32;
        final Modifier modifier3;
        final boolean enabled3;
        final SliderColors colors3;
        final Function2<? super DrawScope, ? super Offset, Unit> function23;
        final float trackInsideCornerSize2;
        final float trackCornerSize2;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function33;
        float trackCornerSize3;
        int $dirty;
        Composer $composer3 = $composer.startRestartGroup(1199441071);
        ComposerKt.sourceInformation($composer3, "C(CenteredTrack)N(sliderState,modifier,enabled,colors,drawStopIndicator,drawTick,thumbTrackGapSize:c#ui.unit.Dp,trackInsideCornerSize:c#ui.unit.Dp,trackCornerSize:c#ui.unit.Dp)1558@69596L466:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(sliderState) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                function22 = function2;
                int i6 = $composer3.changedInstance(function22) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                function22 = function2;
            }
            $dirty2 |= i6;
        } else {
            function22 = function2;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 1572864;
            thumbTrackGapSize2 = thumbTrackGapSize;
        } else if (($changed & 1572864) == 0) {
            thumbTrackGapSize2 = thumbTrackGapSize;
            $dirty2 |= $composer3.changed(thumbTrackGapSize2) ? 1048576 : 524288;
        } else {
            thumbTrackGapSize2 = thumbTrackGapSize;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(trackInsideCornerSize) ? 8388608 : 4194304;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty2 |= 100663296;
            i2 = i10;
        } else if (($changed & 100663296) == 0) {
            i2 = i10;
            $dirty2 |= $composer3.changed(trackCornerSize) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i2 = i10;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changed(this) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if ($composer3.shouldExecute((306783379 & $dirty2) != 306783378, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1543@68991L8,1544@69060L199,1551@69315L107");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors4 = colors($composer3, ($dirty2 >> 27) & 14);
                    $dirty2 &= -7169;
                    colors2 = colors4;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1611643510, "CC(remember):Slider.kt#9igjgp");
                    boolean invalid$iv = (((($dirty2 & 7168) ^ 3072) > 2048 && $composer3.changed(colors2)) || ($dirty2 & 3072) == 2048) | (($dirty2 & 896) == 256);
                    Object it$iv = $composer3.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda22
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.CenteredTrack_7LSsfP0$lambda$23$lambda$22(colors2, enabled2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty2 &= -57345;
                    function22 = (Function2) it$iv;
                }
                if (i7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1611651578, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = (Function3) new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m2972invokewPWG1Vc(drawScope, offset.m5078unboximpl(), color.m5323unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m2972invokewPWG1Vc(DrawScope drawScope, long offset, long color) {
                                SliderDefaults.INSTANCE.m2969drawStopIndicatorx3O1jOs(drawScope, offset, SliderDefaults.INSTANCE.m2970getTickSizeD9Ej5fM(), color);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    function33 = (Function3) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    function33 = function3;
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                }
                float trackInsideCornerSize3 = i9 != 0 ? SliderKt.TrackInsideCornerSize : trackInsideCornerSize;
                if (i2 != 0) {
                    trackCornerSize3 = Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM();
                    function32 = function33;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize3;
                } else {
                    trackCornerSize3 = trackCornerSize;
                    function32 = function33;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    function32 = function3;
                    trackCornerSize3 = trackCornerSize;
                    $dirty = (-57345) & $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize;
                } else {
                    function32 = function3;
                    trackCornerSize3 = trackCornerSize;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1199441071, $dirty, -1, "androidx.compose.material3.SliderDefaults.CenteredTrack (Slider.kt:1557)");
            }
            m2956TrackImplVvwgllI(sliderState, trackCornerSize3, modifier3, enabled3, colors3, function23, function32, thumbTrackGapSize2, trackInsideCornerSize2, true, true, $composer3, ($dirty & 14) | 805306368 | (($dirty >> 21) & 112) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128) | (234881024 & ($dirty << 3)), (($dirty >> 24) & 112) | 6);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            trackCornerSize2 = trackCornerSize3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function32 = function3;
            modifier3 = modifier2;
            enabled3 = enabled2;
            colors3 = colors2;
            function23 = function22;
            trackInsideCornerSize2 = trackInsideCornerSize;
            trackCornerSize2 = trackCornerSize;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.CenteredTrack_7LSsfP0$lambda$25(this.f$0, sliderState, modifier3, enabled3, colors3, function23, function32, thumbTrackGapSize2, trackInsideCornerSize2, trackCornerSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0295  */
    /* JADX INFO: renamed from: TrackImpl-VvwgllI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void m2956TrackImplVvwgllI(final androidx.compose.material3.SliderState r39, final float r40, final androidx.compose.ui.Modifier r41, final boolean r42, final androidx.compose.material3.SliderColors r43, final kotlin.jvm.functions.Function2<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r44, final kotlin.jvm.functions.Function3<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, ? super androidx.compose.ui.graphics.Color, kotlin.Unit> r45, final float r46, final float r47, final boolean r48, final boolean r49, androidx.compose.runtime.Composer r50, final int r51, final int r52) {
        /*
            Method dump skipped, instruction units count: 723
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderDefaults.m2956TrackImplVvwgllI(androidx.compose.material3.SliderState, float, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SliderColors, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, float, float, boolean, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    static final MeasureResult TrackImpl_VvwgllI$lambda$29$lambda$28(float $trackCornerSize, SliderState $sliderState, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        int cornerSize;
        final Placeable placeable = measurable.mo6783measureBRTryo0(constraints.getValue());
        if (Dp.m8155equalsimpl0($trackCornerSize, Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM())) {
            if ($sliderState.getOrientation() == Orientation.Vertical) {
                cornerSize = placeable.getWidth() / 2;
            } else {
                cornerSize = placeable.getHeight() / 2;
            }
        } else {
            cornerSize = $this$layout.mo426roundToPx0680j_4($trackCornerSize);
        }
        return $this$layout.layout(placeable.getWidth(), placeable.getHeight(), MapsKt.mapOf(TuplesKt.to(SliderKt.getCornerSizeAlignmentLine(), Integer.valueOf(cornerSize))), new Function1() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SliderDefaults.TrackImpl_VvwgllI$lambda$29$lambda$28$lambda$27(placeable, (Placeable.PlacementScope) obj);
            }
        });
    }

    static final Unit TrackImpl_VvwgllI$lambda$29$lambda$28$lambda$27(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    static final Unit TrackImpl_VvwgllI$lambda$31$lambda$30(float $trackCornerSize, SliderState $sliderState, long $inactiveTrackColor, long $activeTrackColor, long $inactiveTickColor, long $activeTickColor, float $thumbTrackGapSize, float $trackInsideCornerSize, Function2 $drawStopIndicator, Function3 $drawTick, boolean $enableCornerShrinking, boolean $isCentered, DrawScope $this$Canvas) {
        float cornerSize;
        if (Dp.m8155equalsimpl0($trackCornerSize, Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM())) {
            if ($sliderState.getOrientation() == Orientation.Vertical) {
                long arg0$iv = $this$Canvas.mo5887getSizeNHjbRc();
                int bits$iv$iv$iv = (int) (arg0$iv >> 32);
                cornerSize = Float.intBitsToFloat(bits$iv$iv$iv) / 2.0f;
            } else {
                long arg0$iv2 = $this$Canvas.mo5887getSizeNHjbRc();
                int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
                cornerSize = Float.intBitsToFloat(bits$iv$iv$iv2) / 2.0f;
            }
        } else {
            cornerSize = $this$Canvas.mo432toPx0680j_4($trackCornerSize);
        }
        INSTANCE.m2958drawTrackGVD57ws($this$Canvas, $sliderState.getTickFractions(), 0.0f, $sliderState.getCoercedValueAsFraction(), $inactiveTrackColor, $activeTrackColor, $inactiveTickColor, $activeTickColor, $this$Canvas.mo429toDpu2uoSUM(0), $this$Canvas.mo429toDpu2uoSUM(0), $this$Canvas.mo429toDpu2uoSUM($sliderState.getThumbWidth$material3()), $this$Canvas.mo429toDpu2uoSUM($sliderState.getThumbHeight$material3()), $thumbTrackGapSize, $trackInsideCornerSize, $this$Canvas.mo428toDpu2uoSUM(cornerSize), $drawStopIndicator, $drawTick, false, $enableCornerShrinking, $sliderState.getOrientation(), $isCentered);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacyRangeSliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(rangeSliderState, modifier, colors, enabled, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    public final /* synthetic */ void Track(final RangeSliderState rangeSliderState, Modifier modifier, SliderColors colors, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        SliderColors sliderColors;
        boolean z;
        final Modifier modifier3;
        final SliderColors colors2;
        final boolean enabled2;
        Modifier.Companion modifier4;
        SliderColors colors3;
        int $dirty;
        Modifier modifier5;
        boolean enabled3;
        Composer $composer2 = $composer.startRestartGroup(-1617869097);
        ComposerKt.sourceInformation($composer2, "C(Track)N(rangeSliderState,modifier,colors,enabled)1689@75190L219:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(rangeSliderState) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sliderColors = colors;
                int i3 = $composer2.changed(sliderColors) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                sliderColors = colors;
            }
            $dirty2 |= i3;
        } else {
            sliderColors = colors;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(this) ? 16384 : 8192;
        }
        if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = sliderColors;
            enabled2 = z;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "1686@75131L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier5 = modifier2;
                colors2 = sliderColors;
                enabled3 = z;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    colors3 = sliderColors;
                } else {
                    colors3 = colors($composer2, ($dirty2 >> 12) & 14);
                    $dirty2 &= -897;
                }
                if (i4 == 0) {
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                    colors2 = colors3;
                    enabled3 = z;
                } else {
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                    colors2 = colors3;
                    enabled3 = true;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1617869097, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1688)");
            }
            m2964Track4EFweAY(rangeSliderState, modifier5, enabled3, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, $composer2, ($dirty & 14) | 14155776 | ($dirty & 112) | (($dirty >> 3) & 896) | (($dirty << 3) & 7168) | (234881024 & ($dirty << 12)), 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled2 = enabled3;
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.Track$lambda$33(this.f$0, rangeSliderState, modifier3, colors2, enabled2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Track_4EFweAY$lambda$35$lambda$34(SliderColors $colors, boolean $enabled, DrawScope drawScope, Offset it) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM2955trackColorWaAFU9c$material3 = $colors.m2955trackColorWaAFU9c$material3($enabled, true);
        sliderDefaults.m2969drawStopIndicatorx3O1jOs(drawScope, it.m5078unboximpl(), TrackStopIndicatorSize, jM2955trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Track-4EFweAY, reason: not valid java name */
    public final void m2964Track4EFweAY(final RangeSliderState rangeSliderState, Modifier modifier, boolean enabled, SliderColors colors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float thumbTrackGapSize, float trackInsideCornerSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final boolean enabled2;
        final SliderColors colors2;
        Function2<? super DrawScope, ? super Offset, Unit> function22;
        float thumbTrackGapSize2;
        Composer $composer2;
        final Modifier modifier3;
        final boolean enabled3;
        final SliderColors colors3;
        final Function2<? super DrawScope, ? super Offset, Unit> function23;
        final float trackInsideCornerSize2;
        final float thumbTrackGapSize3;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function32;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function33;
        int $dirty;
        Composer $composer3 = $composer.startRestartGroup(-541824132);
        ComposerKt.sourceInformation($composer3, "C(Track)N(rangeSliderState,modifier,enabled,colors,drawStopIndicator,drawTick,thumbTrackGapSize:c#ui.unit.Dp,trackInsideCornerSize:c#ui.unit.Dp)1735@77205L402:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(rangeSliderState) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i4 = $composer3.changed(colors2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i4;
        } else {
            colors2 = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                function22 = function2;
                int i5 = $composer3.changedInstance(function22) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                function22 = function2;
            }
            $dirty2 |= i5;
        } else {
            function22 = function2;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            thumbTrackGapSize2 = thumbTrackGapSize;
        } else if (($changed & 1572864) == 0) {
            thumbTrackGapSize2 = thumbTrackGapSize;
            $dirty2 |= $composer3.changed(thumbTrackGapSize2) ? 1048576 : 524288;
        } else {
            thumbTrackGapSize2 = thumbTrackGapSize;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(trackInsideCornerSize) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(this) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if ($composer3.shouldExecute((38347923 & $dirty2) != 38347922, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1721@76646L8,1722@76715L199,1729@76970L107");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    SliderColors colors4 = colors($composer3, ($dirty2 >> 24) & 14);
                    $dirty2 &= -7169;
                    colors2 = colors4;
                }
                if ((i & 16) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -1001938653, "CC(remember):Slider.kt#9igjgp");
                    boolean invalid$iv = (((($dirty2 & 7168) ^ 3072) > 2048 && $composer3.changed(colors2)) || ($dirty2 & 3072) == 2048) | (($dirty2 & 896) == 256);
                    Object it$iv = $composer3.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda16
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.Track_4EFweAY$lambda$35$lambda$34(colors2, enabled2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty2 &= -57345;
                    function22 = (Function2) it$iv;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -1001930585, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = (Function3) new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$12$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m2973invokewPWG1Vc(drawScope, offset.m5078unboximpl(), color.m5323unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m2973invokewPWG1Vc(DrawScope drawScope, long offset, long color) {
                                SliderDefaults.INSTANCE.m2969drawStopIndicatorx3O1jOs(drawScope, offset, SliderDefaults.INSTANCE.m2970getTickSizeD9Ej5fM(), color);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    function33 = (Function3) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    function33 = function3;
                }
                if (i7 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                }
                if (i8 != 0) {
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = SliderKt.TrackInsideCornerSize;
                    $dirty = $dirty2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    function32 = function33;
                } else {
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize;
                    $dirty = $dirty2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    function32 = function33;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize;
                    $dirty = (-57345) & $dirty2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    function32 = function3;
                } else {
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    function23 = function22;
                    trackInsideCornerSize2 = trackInsideCornerSize;
                    $dirty = $dirty2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    function32 = function3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-541824132, $dirty, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1734)");
            }
            m2957TrackImplxlyIBlM(rangeSliderState, Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM(), modifier3, enabled3, colors3, function23, function32, thumbTrackGapSize3, trackInsideCornerSize2, $composer3, ($dirty & 14) | 48 | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128) | (($dirty << 3) & 234881024) | (1879048192 & ($dirty << 3)));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enabled3 = enabled2;
            colors3 = colors2;
            function23 = function22;
            trackInsideCornerSize2 = trackInsideCornerSize;
            thumbTrackGapSize3 = thumbTrackGapSize2;
            function32 = function3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.Track_4EFweAY$lambda$37(this.f$0, rangeSliderState, modifier3, enabled3, colors3, function23, function32, thumbTrackGapSize3, trackInsideCornerSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Track_mnvyFg4$lambda$39$lambda$38(SliderColors $colors, boolean $enabled, DrawScope drawScope, Offset it) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM2955trackColorWaAFU9c$material3 = $colors.m2955trackColorWaAFU9c$material3($enabled, true);
        sliderDefaults.m2969drawStopIndicatorx3O1jOs(drawScope, it.m5078unboximpl(), TrackStopIndicatorSize, jM2955trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Track-mnvyFg4$material3, reason: not valid java name */
    public final void m2966TrackmnvyFg4$material3(final RangeSliderState rangeSliderState, final float trackCornerSize, Modifier modifier, boolean enabled, SliderColors colors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float thumbTrackGapSize, float trackInsideCornerSize, Composer $composer, final int $changed, final int i) {
        RangeSliderState rangeSliderState2;
        Modifier modifier2;
        final boolean enabled2;
        final SliderColors colors2;
        Function2<? super DrawScope, ? super Offset, Unit> function22;
        float thumbTrackGapSize2;
        int i2;
        Composer $composer2;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function32;
        final float trackInsideCornerSize2;
        final Modifier modifier3;
        final boolean enabled3;
        final SliderColors colors3;
        final float trackInsideCornerSize3;
        final Function2<? super DrawScope, ? super Offset, Unit> function23;
        int $dirty;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function33;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function34;
        boolean enabled4;
        SliderColors colors4;
        float thumbTrackGapSize3;
        float trackInsideCornerSize4;
        int $dirty2;
        Composer $composer3 = $composer.startRestartGroup(1952945688);
        ComposerKt.sourceInformation($composer3, "C(Track)N(rangeSliderState,trackCornerSize:c#ui.unit.Dp,modifier,enabled,colors,drawStopIndicator,drawTick,thumbTrackGapSize:c#ui.unit.Dp,trackInsideCornerSize:c#ui.unit.Dp)1786@79501L403:Slider.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            rangeSliderState2 = rangeSliderState;
        } else if (($changed & 6) == 0) {
            rangeSliderState2 = rangeSliderState;
            $dirty3 |= $composer3.changedInstance(rangeSliderState2) ? 4 : 2;
        } else {
            rangeSliderState2 = rangeSliderState;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(trackCornerSize) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? 16384 : 8192;
                $dirty3 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty3 |= i5;
        } else {
            colors2 = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                function22 = function2;
                int i6 = $composer3.changedInstance(function22) ? 131072 : 65536;
                $dirty3 |= i6;
            } else {
                function22 = function2;
            }
            $dirty3 |= i6;
        } else {
            function22 = function2;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 12582912;
            thumbTrackGapSize2 = thumbTrackGapSize;
        } else if (($changed & 12582912) == 0) {
            thumbTrackGapSize2 = thumbTrackGapSize;
            $dirty3 |= $composer3.changed(thumbTrackGapSize2) ? 8388608 : 4194304;
        } else {
            thumbTrackGapSize2 = thumbTrackGapSize;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty3 |= 100663296;
            i2 = i9;
        } else if (($changed & 100663296) == 0) {
            i2 = i9;
            $dirty3 |= $composer3.changed(trackInsideCornerSize) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i2 = i9;
        }
        if ((i & 512) != 0) {
            $dirty3 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty3 |= $composer3.changed(this) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int $dirty4 = $dirty3;
        if ($composer3.shouldExecute((306783379 & $dirty3) != 306783378, $dirty4 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1772@78942L8,1773@79011L199,1780@79266L107");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty4 & (-57345);
                    colors2 = colors($composer3, ($dirty4 >> 27) & 14);
                } else {
                    $dirty = $dirty4;
                }
                if ((i & 32) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -119891585, "CC(remember):Slider.kt#9igjgp");
                    boolean invalid$iv = ((((57344 & $dirty) ^ 24576) > 16384 && $composer3.changed(colors2)) || ($dirty & 24576) == 16384) | (($dirty & 7168) == 2048);
                    Object value$iv = $composer3.rememberedValue();
                    if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.Track_mnvyFg4$lambda$39$lambda$38(colors2, enabled2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty &= -458753;
                    function22 = (Function2) value$iv;
                }
                if (i7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -119883517, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = (Function3) new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m2974invokewPWG1Vc(drawScope, offset.m5078unboximpl(), color.m5323unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m2974invokewPWG1Vc(DrawScope drawScope, long offset, long color) {
                                SliderDefaults.INSTANCE.m2969drawStopIndicatorx3O1jOs(drawScope, offset, SliderDefaults.INSTANCE.m2970getTickSizeD9Ej5fM(), color);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                        it$iv = value$iv2;
                    }
                    function33 = (Function3) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    function33 = function3;
                }
                if (i8 != 0) {
                    thumbTrackGapSize2 = SliderKt.ThumbTrackGapSize;
                }
                if (i2 != 0) {
                    function34 = function33;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    trackInsideCornerSize4 = SliderKt.TrackInsideCornerSize;
                    $dirty2 = $dirty;
                } else {
                    function34 = function33;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    trackInsideCornerSize4 = trackInsideCornerSize;
                    $dirty2 = $dirty;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 16) != 0 ? $dirty4 & (-57345) : $dirty4;
                if ((i & 32) != 0) {
                    function34 = function3;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    trackInsideCornerSize4 = trackInsideCornerSize;
                    $dirty2 = (-458753) & $dirty5;
                } else {
                    function34 = function3;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    thumbTrackGapSize3 = thumbTrackGapSize2;
                    trackInsideCornerSize4 = trackInsideCornerSize;
                    $dirty2 = $dirty5;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1952945688, $dirty2, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
            }
            RangeSliderState rangeSliderState3 = rangeSliderState2;
            Modifier modifier4 = modifier2;
            Function2<? super DrawScope, ? super Offset, Unit> function24 = function22;
            m2957TrackImplxlyIBlM(rangeSliderState3, trackCornerSize, modifier4, enabled4, colors4, function24, function34, thumbTrackGapSize3, trackInsideCornerSize4, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            trackInsideCornerSize2 = trackInsideCornerSize4;
            trackInsideCornerSize3 = thumbTrackGapSize3;
            function32 = function34;
            function23 = function24;
            colors3 = colors4;
            enabled3 = enabled4;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function32 = function3;
            trackInsideCornerSize2 = trackInsideCornerSize;
            modifier3 = modifier2;
            enabled3 = enabled2;
            colors3 = colors2;
            trackInsideCornerSize3 = thumbTrackGapSize2;
            function23 = function22;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.Track_mnvyFg4$lambda$41(this.f$0, rangeSliderState, trackCornerSize, modifier3, enabled3, colors3, function23, function32, trackInsideCornerSize3, trackInsideCornerSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TrackImpl-xlyIBlM, reason: not valid java name */
    private final void m2957TrackImplxlyIBlM(final RangeSliderState rangeSliderState, final float trackCornerSize, final Modifier modifier, final boolean enabled, final SliderColors colors, final Function2<? super DrawScope, ? super Offset, Unit> function2, final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, final float thumbTrackGapSize, final float trackInsideCornerSize, Composer $composer, final int $changed) {
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function32;
        Composer $composer2;
        Object it$iv;
        Object value$iv;
        Composer $composer3;
        Modifier modifier2;
        Composer $composer4 = $composer.startRestartGroup(-1719396904);
        ComposerKt.sourceInformation($composer4, "C(TrackImpl)N(rangeSliderState,trackCornerSize:c#ui.unit.Dp,modifier,enabled,colors,drawStopIndicator,drawTick,thumbTrackGapSize:c#ui.unit.Dp,trackInsideCornerSize:c#ui.unit.Dp)1817@80714L449,1828@81174L1310,1816@80643L1841:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer4.changedInstance(rangeSliderState) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer4.changed(trackCornerSize) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer4.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer4.changed(enabled) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer4.changed(colors) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer4.changedInstance(function2) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            function32 = function3;
            $dirty |= $composer4.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer4.changed(thumbTrackGapSize) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer4.changed(trackInsideCornerSize) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (!$composer4.shouldExecute(($dirty & 38347923) != 38347922, $dirty & 1)) {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1719396904, $dirty, -1, "androidx.compose.material3.SliderDefaults.TrackImpl (Slider.kt:1811)");
            }
            final long inactiveTrackColor = colors.m2955trackColorWaAFU9c$material3(enabled, false);
            int $dirty2 = $dirty;
            final long activeTrackColor = colors.m2955trackColorWaAFU9c$material3(enabled, true);
            final long inactiveTickColor = colors.m2954tickColorWaAFU9c$material3(enabled, false);
            final long activeTickColor = colors.m2954tickColorWaAFU9c$material3(enabled, true);
            Modifier modifierM1101height3ABfNKs = SizeKt.m1101height3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), SliderKt.getTrackHeight());
            ComposerKt.sourceInformationMarkerStart($composer4, 1222010201, "CC(remember):Slider.kt#9igjgp");
            Object it$iv2 = $composer4.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function3() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return SliderDefaults.TrackImpl_xlyIBlM$lambda$44$lambda$43((MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                    }
                };
                $composer4.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            } else {
                it$iv = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer4);
            Modifier modifierLayout = LayoutModifierKt.layout(modifierM1101height3ABfNKs, (Function3) it$iv);
            ComposerKt.sourceInformationMarkerStart($composer4, 1222025782, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 112) == 32) | $composer4.changedInstance(rangeSliderState) | $composer4.changed(inactiveTrackColor) | $composer4.changed(activeTrackColor) | $composer4.changed(inactiveTickColor) | $composer4.changed(activeTickColor) | (($dirty2 & 29360128) == 8388608) | (($dirty2 & 234881024) == 67108864) | (($dirty2 & 458752) == 131072) | (($dirty2 & 3670016) == 1048576);
            Object it$iv3 = $composer4.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                $composer3 = $composer4;
                modifier2 = modifierLayout;
                value$iv = new Function1() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderDefaults.TrackImpl_xlyIBlM$lambda$46$lambda$45(trackCornerSize, rangeSliderState, inactiveTrackColor, activeTrackColor, inactiveTickColor, activeTickColor, thumbTrackGapSize, trackInsideCornerSize, function2, function32, (DrawScope) obj);
                    }
                };
                $composer4.updateRememberedValue(value$iv);
            } else {
                modifier2 = modifierLayout;
                $composer3 = $composer4;
                value$iv = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            CanvasKt.Canvas(modifier2, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.TrackImpl_xlyIBlM$lambda$47(this.f$0, rangeSliderState, trackCornerSize, modifier, enabled, colors, function2, function3, thumbTrackGapSize, trackInsideCornerSize, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final MeasureResult TrackImpl_xlyIBlM$lambda$44$lambda$43(MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        final Placeable placeable = measurable.mo6783measureBRTryo0(constraints.getValue());
        int trackCornerSize = placeable.getHeight() / 2;
        return $this$layout.layout(placeable.getWidth(), placeable.getHeight(), MapsKt.mapOf(TuplesKt.to(SliderKt.getCornerSizeAlignmentLine(), Integer.valueOf(trackCornerSize))), new Function1() { // from class: androidx.compose.material3.SliderDefaults$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SliderDefaults.TrackImpl_xlyIBlM$lambda$44$lambda$43$lambda$42(placeable, (Placeable.PlacementScope) obj);
            }
        });
    }

    static final Unit TrackImpl_xlyIBlM$lambda$44$lambda$43$lambda$42(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    static final Unit TrackImpl_xlyIBlM$lambda$46$lambda$45(float $trackCornerSize, RangeSliderState $rangeSliderState, long $inactiveTrackColor, long $activeTrackColor, long $inactiveTickColor, long $activeTickColor, float $thumbTrackGapSize, float $trackInsideCornerSize, Function2 $drawStopIndicator, Function3 $drawTick, DrawScope $this$Canvas) {
        float fIntBitsToFloat;
        if (Dp.m8155equalsimpl0($trackCornerSize, Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM())) {
            long arg0$iv = $this$Canvas.mo5887getSizeNHjbRc();
            int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
            fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv) / 2.0f;
        } else {
            fIntBitsToFloat = $this$Canvas.mo432toPx0680j_4($trackCornerSize);
        }
        float cornerSize = fIntBitsToFloat;
        m2959drawTrackGVD57ws$default(INSTANCE, $this$Canvas, $rangeSliderState.getTickFractions(), $rangeSliderState.getCoercedActiveRangeStartAsFraction$material3(), $rangeSliderState.getCoercedActiveRangeEndAsFraction$material3(), $inactiveTrackColor, $activeTrackColor, $inactiveTickColor, $activeTickColor, $this$Canvas.mo428toDpu2uoSUM($rangeSliderState.getStartThumbWidth$material3()), $this$Canvas.mo428toDpu2uoSUM($rangeSliderState.getStartThumbHeight$material3()), $this$Canvas.mo428toDpu2uoSUM($rangeSliderState.getEndThumbWidth$material3()), $this$Canvas.mo428toDpu2uoSUM($rangeSliderState.getEndThumbHeight$material3()), $thumbTrackGapSize, $trackInsideCornerSize, $this$Canvas.mo428toDpu2uoSUM(cornerSize), $drawStopIndicator, $drawTick, true, false, null, false, 917504, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: drawTrack-GVD57ws$default, reason: not valid java name */
    static /* synthetic */ void m2959drawTrackGVD57ws$default(SliderDefaults sliderDefaults, DrawScope drawScope, float[] fArr, float f, float f2, long j, long j2, long j3, long j4, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Function2 function2, Function3 function3, boolean z, boolean z2, Orientation orientation, boolean z3, int i, Object obj) {
        sliderDefaults.m2958drawTrackGVD57ws(drawScope, fArr, f, f2, j, j2, j3, j4, f3, f4, f5, f6, f7, f8, f9, function2, function3, z, (i & 131072) != 0 ? false : z2, (i & 262144) != 0 ? Orientation.Horizontal : orientation, (i & 524288) != 0 ? false : z3);
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x076c A[PHI: r48
  0x076c: PHI (r48v3 'start' float) = (r48v2 'start' float), (r48v2 'start' float), (r48v4 'start' float) binds: [B:236:0x076a, B:234:0x075e, B:229:0x074d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x018b  */
    /* JADX INFO: renamed from: drawTrack-GVD57ws, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void m2958drawTrackGVD57ws(androidx.compose.ui.graphics.drawscope.DrawScope r64, float[] r65, float r66, float r67, long r68, long r70, long r72, long r74, float r76, float r77, float r78, float r79, float r80, float r81, float r82, kotlin.jvm.functions.Function2<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r83, kotlin.jvm.functions.Function3<? super androidx.compose.ui.graphics.drawscope.DrawScope, ? super androidx.compose.ui.geometry.Offset, ? super androidx.compose.ui.graphics.Color, kotlin.Unit> r84, boolean r85, boolean r86, androidx.compose.foundation.gestures.Orientation r87, boolean r88) {
        /*
            Method dump skipped, instruction units count: 2167
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderDefaults.m2958drawTrackGVD57ws(androidx.compose.ui.graphics.drawscope.DrawScope, float[], float, float, long, long, long, long, float, float, float, float, float, float, float, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, boolean, boolean, androidx.compose.foundation.gestures.Orientation, boolean):void");
    }

    /* JADX INFO: renamed from: drawTrackPath-zXTsYAs, reason: not valid java name */
    private final void m2960drawTrackPathzXTsYAs(DrawScope $this$drawTrackPath_u2dzXTsYAs, Orientation orientation, long offset, long size, long color, float startCornerRadius, float endCornerRadius) {
        RoundRect track;
        long v1$iv$iv = Float.floatToRawIntBits(startCornerRadius);
        long v2$iv$iv = Float.floatToRawIntBits(startCornerRadius);
        long jM5022constructorimpl = CornerRadius.m5022constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        long v1$iv$iv2 = Float.floatToRawIntBits(endCornerRadius);
        long v2$iv$iv2 = Float.floatToRawIntBits(endCornerRadius);
        long endCorner = CornerRadius.m5022constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
        if (orientation == Orientation.Vertical) {
            int bits$iv$iv$iv = (int) (size >> 32);
            float width$iv = Float.intBitsToFloat(bits$iv$iv$iv);
            int bits$iv$iv$iv2 = (int) (size & 4294967295L);
            float height$iv = Float.intBitsToFloat(bits$iv$iv$iv2);
            long v1$iv$iv3 = Float.floatToRawIntBits(width$iv);
            long v2$iv$iv3 = Float.floatToRawIntBits(height$iv);
            track = RoundRectKt.m5120RoundRectZAM2FJo(RectKt.m5108Recttz77jQw(offset, Size.m5128constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv3 & 4294967295L))), jM5022constructorimpl, jM5022constructorimpl, endCorner, endCorner);
        } else {
            int bits$iv$iv$iv3 = (int) (size >> 32);
            float width$iv2 = Float.intBitsToFloat(bits$iv$iv$iv3);
            int bits$iv$iv$iv4 = (int) (size & 4294967295L);
            float height$iv2 = Float.intBitsToFloat(bits$iv$iv$iv4);
            long v1$iv$iv4 = Float.floatToRawIntBits(width$iv2);
            long v2$iv$iv4 = Float.floatToRawIntBits(height$iv2);
            track = RoundRectKt.m5120RoundRectZAM2FJo(RectKt.m5108Recttz77jQw(offset, Size.m5128constructorimpl((v1$iv$iv4 << 32) | (4294967295L & v2$iv$iv4))), jM5022constructorimpl, endCorner, endCorner, jM5022constructorimpl);
        }
        Path.addRoundRect$default(trackPath, track, null, 2, null);
        DrawScope.m5877drawPathLG529CI$default($this$drawTrackPath_u2dzXTsYAs, trackPath, color, 0.0f, null, null, 0, 60, null);
        trackPath.rewind();
    }

    /* JADX INFO: renamed from: drawStopIndicator-x3O1jOs, reason: not valid java name */
    public final void m2969drawStopIndicatorx3O1jOs(DrawScope $this$drawStopIndicator_u2dx3O1jOs, long offset, float size, long color) {
        DrawScope.m5868drawCircleVaOC9Bg$default($this$drawStopIndicator_u2dx3O1jOs, color, $this$drawStopIndicator_u2dx3O1jOs.mo432toPx0680j_4(size) / 2.0f, offset, 0.0f, null, null, 0, 120, null);
    }

    /* JADX INFO: renamed from: getTrackStopIndicatorSize-D9Ej5fM, reason: not valid java name */
    public final float m2971getTrackStopIndicatorSizeD9Ej5fM() {
        return TrackStopIndicatorSize;
    }

    /* JADX INFO: renamed from: getTickSize-D9Ej5fM, reason: not valid java name */
    public final float m2970getTickSizeD9Ej5fM() {
        return TickSize;
    }
}
