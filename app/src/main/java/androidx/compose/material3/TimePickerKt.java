package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.collection.IntList;
import androidx.collection.IntListKt;
import androidx.collection.MutableIntList;
import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.TimePickerKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.TimeInputTokens;
import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a)\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\r\u001a+\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001a \u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u001a3\u0010\u001b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0002¢\u0006\u0004\b\"\u0010#\u001aJ\u0010$\u001a\u00020\u0001*\u00020%2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001d0(H\u0082@¢\u0006\u0004\b)\u0010*\u001a1\u0010/\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020%2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0013H\u0001¢\u0006\u0002\u00100\u001a1\u00101\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020%2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0013H\u0001¢\u0006\u0002\u00100\u001a%\u00102\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u00103\u001a\u001d\u00104\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00105\u001a\u001d\u00106\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00105\u001a\u001d\u00107\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00105\u001a%\u00108\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00109\u001a%\u0010:\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00109\u001a=\u0010;\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?H\u0003¢\u0006\u0002\u0010A\u001aQ\u0010B\u001a\u00020\u00012\u0006\u0010C\u001a\u00020\u00132\u0006\u0010D\u001a\u00020?2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00010F2\u0006\u0010\u0006\u001a\u00020\u00072\u001c\u0010G\u001a\u0018\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u00010H¢\u0006\u0002\bJ¢\u0006\u0002\bKH\u0003¢\u0006\u0002\u0010L\u001a\u0015\u0010M\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010N\u001a7\u0010O\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0004\bS\u0010T\u001a-\u0010U\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0013H\u0001¢\u0006\u0002\u0010V\u001a\u001c\u0010W\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a-\u0010X\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020%2\u0006\u0010P\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010Y\u001ah\u0010Z\u001a\u00020\u00012\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010P\u001a\u00020[2\u0006\u0010\\\u001a\u00020[2\u0006\u0010]\u001a\u00020\u00102\f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00130_2!\u0010`\u001a\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\ba\u0012\b\bb\u0012\u0004\b\b(P\u0012\u0004\u0012\u00020\u00010HH\u0002¢\u0006\u0004\bc\u0010d\u001a_\u0010e\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010P\u001a\u00020[2\u0012\u0010f\u001a\u000e\u0012\u0004\u0012\u00020[\u0012\u0004\u0012\u00020\u00010H2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020R2\b\b\u0002\u0010g\u001a\u00020h2\b\b\u0002\u0010i\u001a\u00020j2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0004\bk\u0010l\u001a4\u0010m\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010n\u001a\u00020\u001d2\u0011\u0010G\u001a\r\u0012\u0004\u0012\u00020\u00010F¢\u0006\u0002\bJH\u0003¢\u0006\u0002\u0010o\u001a'\u0010p\u001a\u00020q2\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010r\u001a\u00020\u0010H\u0001¢\u0006\u0004\bs\u0010t\u001a(\u0010u\u001a\u00020\u001d2\u0006\u0010v\u001a\u00020\u001d2\u0006\u0010w\u001a\u00020\u001d2\u0006\u0010x\u001a\u00020\u00102\u0006\u0010y\u001a\u00020\u0010H\u0002\u001a\u0018\u0010z\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002\u001a\u0016\u0010\u009d\u0001\u001a\u00020\u0005*\u00020\u00052\u0007\u0010\u009d\u0001\u001a\u00020\u0013H\u0003\"\u0015\u0010\u0015\u001a\u00020\u0013*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\"\u0018\u0010\u0018\u001a\u00020\u0010*\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\"\u0018\u0010+\u001a\u00020,*\u00020%8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.\"\u001a\u0010{\u001a\u00020\t8AX\u0080\u0004¢\u0006\f\u0012\u0004\b|\u0010}\u001a\u0004\b~\u0010\u007f\"\u000f\u0010\u0080\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0081\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0082\u0001\u001a\u00030\u0083\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0084\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0085\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0086\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0087\u0001\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000f\u0010\u0088\u0001\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008c\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008d\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008e\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008f\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0090\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0091\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0010\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0094\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0095\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0096\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0097\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0098\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0099\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u001b\u0010\u009a\u0001\u001a\u00030\u008a\u0001X\u0080\u0004¢\u0006\r\n\u0003\u0010\u008b\u0001\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001¨\u0006\u009e\u0001²\u0006\u000b\u0010\u009f\u0001\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\u000b\u0010 \u0001\u001a\u00020[X\u008a\u008e\u0002²\u0006\u000b\u0010¡\u0001\u001a\u00020[X\u008a\u008e\u0002²\u0006\u000b\u0010 \u001a\u00030¢\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010£\u0001\u001a\u00020!X\u008a\u008e\u0002²\u0006\f\u0010¤\u0001\u001a\u00030¥\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010¦\u0001\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"TimePicker", "", "state", "Landroidx/compose/material3/TimePickerState;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "Landroidx/compose/material3/TimePickerColors;", "layoutType", "Landroidx/compose/material3/TimePickerLayoutType;", "TimePicker-mT9BvqQ", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ILandroidx/compose/runtime/Composer;II)V", "TimeInput", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "rememberTimePickerState", "initialHour", "", "initialMinute", "is24Hour", "", "(IIZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TimePickerState;", "isPm", "(Landroidx/compose/material3/TimePickerState;)Z", "TimePickerState", "hourForDisplay", "getHourForDisplay", "(Landroidx/compose/material3/TimePickerState;)I", "moveSelector", "x", "", "y", "maxDist", "center", "Landroidx/compose/ui/unit/IntOffset;", "moveSelector-d3b8Pxo", "(Landroidx/compose/material3/TimePickerState;FFFJ)V", "onTap", "Landroidx/compose/material3/AnalogTimePickerState;", "autoSwitchToMinute", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "onTap-uYHVD98", "(Landroidx/compose/material3/AnalogTimePickerState;FFFZJLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectorPos", "Landroidx/compose/ui/unit/DpOffset;", "getSelectorPos", "(Landroidx/compose/material3/AnalogTimePickerState;)J", "VerticalTimePicker", "(Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;II)V", "HorizontalTimePicker", "TimeInputImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/material3/TimePickerState;Landroidx/compose/runtime/Composer;I)V", "HorizontalClockDisplay", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "VerticalClockDisplay", "ClockDisplayNumbers", "HorizontalPeriodToggle", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "VerticalPeriodToggle", "PeriodToggleImpl", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "startShape", "Landroidx/compose/ui/graphics/Shape;", "endShape", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "ToggleItem", "checked", "shape", "onClick", "Lkotlin/Function0;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/TimePickerColors;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "DisplaySeparator", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "TimeSelector", "value", "selection", "Landroidx/compose/material3/TimePickerSelectionMode;", "TimeSelector-SAnMeKU", "(Landroidx/compose/ui/Modifier;ILandroidx/compose/material3/TimePickerState;ILandroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "ClockFace", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;I)V", "drawSelector", "ClockText", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/AnalogTimePickerState;IZLandroidx/compose/runtime/Composer;I)V", "timeInputOnChange", "Landroidx/compose/ui/text/input/TextFieldValue;", "prevValue", "max", "userOverride", "Landroidx/compose/ui/node/Ref;", "onNewValue", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "timeInputOnChange-_K77t-0", "(ILandroidx/compose/material3/TimePickerState;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/TextFieldValue;ILandroidx/compose/ui/node/Ref;Lkotlin/jvm/functions/Function1;)V", "TimePickerTextField", "onValueChange", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "TimePickerTextField-1vLObsk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/TimePickerState;ILandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "CircularLayout", "radiusToSizeRatio", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "numberContentDescription", "", "number", "numberContentDescription-dSwYdS4", "(IZILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "dist", "x1", "y1", "x2", "y2", "atan", "defaultTimePickerLayoutType", "getDefaultTimePickerLayoutType$annotations", "()V", "getDefaultTimePickerLayoutType", "(Landroidx/compose/runtime/Composer;I)I", "FullCircle", "HalfCircle", "QuarterCircle", "", "RadiansPerMinute", "RadiansPerHour", "SeparatorZIndex", "OuterCircleToSizeRatio", "InnerCircleToSizeRatio", "ClockDisplayBottomMargin", "Landroidx/compose/ui/unit/Dp;", "F", "ClockFaceBottomMargin", "DisplaySeparatorWidth", "SupportLabelTop", "TimeInputBottomPadding", "MaxDistance", "MinimumInteractiveSize", "Minutes", "Landroidx/collection/IntList;", "Hours", "ExtraHours", "PeriodToggleMargin", "TimePickerMaxHeight", "TimePickerMidHeight", "ClockDialMidContainerSize", "ClockDialMinContainerSize", "getClockDialMinContainerSize", "()F", "visible", "material3", "a11yServicesEnabled", "hourValue", "minuteValue", "Landroidx/compose/ui/geometry/Offset;", "parentCenter", "boundsInParent", "Landroidx/compose/ui/geometry/Rect;", "selected"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TimePickerKt {
    private static final float ClockDialMidContainerSize;
    private static final float ClockDialMinContainerSize;
    private static final float ClockDisplayBottomMargin;
    private static final float ClockFaceBottomMargin;
    private static final float DisplaySeparatorWidth;
    private static final IntList ExtraHours;
    private static final float FullCircle = 6.2831855f;
    private static final float HalfCircle = 3.1415927f;
    private static final IntList Hours;
    private static final float InnerCircleToSizeRatio;
    private static final float MaxDistance;
    private static final float MinimumInteractiveSize;
    private static final IntList Minutes;
    private static final float OuterCircleToSizeRatio;
    private static final float PeriodToggleMargin;
    private static final double QuarterCircle = 1.5707963267948966d;
    private static final float RadiansPerHour = 0.5235988f;
    private static final float RadiansPerMinute = 0.10471976f;
    private static final float SeparatorZIndex = 2.0f;
    private static final float SupportLabelTop;
    private static final float TimeInputBottomPadding;
    private static final float TimePickerMaxHeight;
    private static final float TimePickerMidHeight;

    static final Unit CircularLayout$lambda$96(Modifier modifier, float f, Function2 function2, int i, int i2, Composer composer, int i3) {
        CircularLayout(modifier, f, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ClockDisplayNumbers$lambda$35(TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        ClockDisplayNumbers(timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ClockFace$lambda$60(Modifier modifier, AnalogTimePickerState analogTimePickerState, TimePickerColors timePickerColors, boolean z, int i, Composer composer, int i2) {
        ClockFace(modifier, analogTimePickerState, timePickerColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ClockText$lambda$84(Modifier modifier, AnalogTimePickerState analogTimePickerState, int i, boolean z, int i2, Composer composer, int i3) {
        ClockText(modifier, analogTimePickerState, i, z, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    static final Unit DisplaySeparator$lambda$54(Modifier modifier, int i, Composer composer, int i2) {
        DisplaySeparator(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HorizontalClockDisplay$lambda$31(TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        HorizontalClockDisplay(timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HorizontalPeriodToggle$lambda$37(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        HorizontalPeriodToggle(modifier, timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HorizontalTimePicker$lambda$15(AnalogTimePickerState analogTimePickerState, Modifier modifier, TimePickerColors timePickerColors, boolean z, int i, int i2, Composer composer, int i3) {
        HorizontalTimePicker(analogTimePickerState, modifier, timePickerColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit PeriodToggleImpl$lambda$47(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, MeasurePolicy measurePolicy, Shape shape, Shape shape2, int i, Composer composer, int i2) {
        PeriodToggleImpl(modifier, timePickerState, timePickerColors, measurePolicy, shape, shape2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TimeInput$lambda$5(TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, int i, int i2, Composer composer, int i3) {
        TimeInput(timePickerState, modifier, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TimeInputImpl$lambda$28(Modifier modifier, TimePickerColors timePickerColors, TimePickerState timePickerState, int i, Composer composer, int i2) {
        TimeInputImpl(modifier, timePickerColors, timePickerState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TimePickerTextField_1vLObsk$lambda$94(Modifier modifier, TextFieldValue textFieldValue, Function1 function1, TimePickerState timePickerState, int i, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, TimePickerColors timePickerColors, int i2, int i3, Composer composer, int i4) {
        m3198TimePickerTextField1vLObsk(modifier, textFieldValue, function1, timePickerState, i, keyboardOptions, keyboardActions, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit TimePicker_mT9BvqQ$lambda$4(TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, int i, int i2, int i3, Composer composer, int i4) {
        m3197TimePickermT9BvqQ(timePickerState, modifier, timePickerColors, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit TimeSelector_SAnMeKU$lambda$59(Modifier modifier, int i, TimePickerState timePickerState, int i2, TimePickerColors timePickerColors, int i3, Composer composer, int i4) {
        m3199TimeSelectorSAnMeKU(modifier, i, timePickerState, i2, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1));
        return Unit.INSTANCE;
    }

    static final Unit ToggleItem$lambda$50(boolean z, Shape shape, Function0 function0, TimePickerColors timePickerColors, Function3 function3, int i, Composer composer, int i2) {
        ToggleItem(z, shape, function0, timePickerColors, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit VerticalClockDisplay$lambda$34(TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        VerticalClockDisplay(timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit VerticalPeriodToggle$lambda$39(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        VerticalPeriodToggle(modifier, timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit VerticalTimePicker$lambda$11(AnalogTimePickerState analogTimePickerState, Modifier modifier, TimePickerColors timePickerColors, boolean z, int i, int i2, Composer composer, int i3) {
        VerticalTimePicker(analogTimePickerState, modifier, timePickerColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void getDefaultTimePickerLayoutType$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:250:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x023c  */
    /* JADX INFO: renamed from: TimePicker-mT9BvqQ */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m3197TimePickermT9BvqQ(final androidx.compose.material3.TimePickerState r18, androidx.compose.ui.Modifier r19, androidx.compose.material3.TimePickerColors r20, int r21, androidx.compose.runtime.Composer r22, final int r23, final int r24) {
        /*
            Method dump skipped, instruction units count: 604
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m3197TimePickermT9BvqQ(androidx.compose.material3.TimePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerColors, int, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean TimePicker_mT9BvqQ$lambda$0(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    public static final void TimeInput(final TimePickerState state, Modifier modifier, TimePickerColors colors, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        final TimePickerColors colors2;
        Composer $composer2 = $composer.startRestartGroup(-760850373);
        ComposerKt.sourceInformation($composer2, "C(TimeInput)N(state,modifier,colors)275@13528L38:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ((i & 4) == 0 && $composer2.changed(colors)) ? 256 : 128;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "273@13510L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
            } else {
                if (i2 != 0) {
                    modifier = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    colors = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                    $dirty &= -897;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-760850373, $dirty, -1, "androidx.compose.material3.TimeInput (TimePicker.kt:274)");
            }
            TimeInputImpl(modifier, colors, state, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty << 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier;
            colors2 = colors;
        } else {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.TimeInput$lambda$5(state, modifier2, colors2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final TimePickerState rememberTimePickerState(final int initialHour, final int initialMinute, final boolean is24Hour, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1237715277, "C(rememberTimePickerState)N(initialHour,initialMinute,is24Hour)586@29390L14,589@29526L185,589@29472L239:TimePicker.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialHour = 0;
        }
        if ((i & 2) != 0) {
            initialMinute = 0;
        }
        if ((i & 4) != 0) {
            is24Hour = TimeFormat_androidKt.is24HourFormat($composer, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1237715277, $changed, -1, "androidx.compose.material3.rememberTimePickerState (TimePicker.kt:587)");
        }
        Object[] objArr = new Object[0];
        Saver<TimePickerStateImpl, ?> Saver = TimePickerStateImpl.INSTANCE.Saver();
        ComposerKt.sourceInformationMarkerStart($composer, -261550618, "CC(remember):TimePicker.kt#9igjgp");
        boolean z = true;
        boolean z2 = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialHour)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialMinute)) || ($changed & 48) == 32);
        if (((($changed & 896) ^ 384) <= 256 || !$composer.changed(is24Hour)) && ($changed & 384) != 256) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TimePickerKt.rememberTimePickerState$lambda$7$lambda$6(initialHour, initialMinute, is24Hour);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TimePickerStateImpl state = (TimePickerStateImpl) RememberSaveableKt.m4704rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return state;
    }

    static final TimePickerStateImpl rememberTimePickerState$lambda$7$lambda$6(int $initialHour, int $initialMinute, boolean $is24Hour) {
        return new TimePickerStateImpl($initialHour, $initialMinute, $is24Hour);
    }

    public static final boolean isPm(TimePickerState $this$isPm) {
        return $this$isPm.getHour() >= 12;
    }

    public static final TimePickerState TimePickerState(int initialHour, int initialMinute, boolean is24Hour) {
        return new TimePickerStateImpl(initialHour, initialMinute, is24Hour);
    }

    public static final int getHourForDisplay(TimePickerState $this$hourForDisplay) {
        if ($this$hourForDisplay.getIs24hour()) {
            return $this$hourForDisplay.getHour() % 24;
        }
        if ($this$hourForDisplay.getHour() % 12 == 0) {
            return 12;
        }
        return isPm($this$hourForDisplay) ? $this$hourForDisplay.getHour() - 12 : $this$hourForDisplay.getHour();
    }

    /* JADX INFO: renamed from: moveSelector-d3b8Pxo */
    public static final void m3205moveSelectord3b8Pxo(TimePickerState $this$moveSelector_u2dd3b8Pxo, float x, float y, float maxDist, long center) {
        if (TimePickerSelectionMode.m3223equalsimpl0($this$moveSelector_u2dd3b8Pxo.mo2147getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI()) && $this$moveSelector_u2dd3b8Pxo.getIs24hour()) {
            float currentDist = dist(x, y, IntOffset.m8278getXimpl(center), IntOffset.m8279getYimpl(center));
            if (isPm($this$moveSelector_u2dd3b8Pxo)) {
                $this$moveSelector_u2dd3b8Pxo.setHour($this$moveSelector_u2dd3b8Pxo.getHour() - (currentDist < maxDist ? 0 : 12));
            } else {
                $this$moveSelector_u2dd3b8Pxo.setHour($this$moveSelector_u2dd3b8Pxo.getHour() + (currentDist >= maxDist ? 0 : 12));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00df  */
    /* JADX INFO: renamed from: onTap-uYHVD98 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object m3207onTapuYHVD98(androidx.compose.material3.AnalogTimePickerState r16, float r17, float r18, float r19, boolean r20, long r21, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r23, kotlin.coroutines.Continuation<? super kotlin.Unit> r24) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m3207onTapuYHVD98(androidx.compose.material3.AnalogTimePickerState, float, float, float, boolean, long, androidx.compose.animation.core.AnimationSpec, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final long getSelectorPos(AnalogTimePickerState $this$selectorPos) {
        float arg0$iv;
        float arg0$iv2 = $this$selectorPos.m2146getCurrentDiameterD9Ej5fM();
        float other$iv = TimePickerTokens.INSTANCE.m4257getClockDialContainerSizeD9Ej5fM();
        float scale = arg0$iv2 / other$iv;
        float arg0$iv3 = TimePickerTokens.INSTANCE.m4259getClockDialSelectorHandleContainerSizeD9Ej5fM();
        float arg0$iv4 = Dp.m8150constructorimpl(Dp.m8150constructorimpl(arg0$iv3 / SeparatorZIndex) * scale);
        if ($this$selectorPos.getIs24hour() && isPm($this$selectorPos) && TimePickerSelectionMode.m3223equalsimpl0($this$selectorPos.mo2147getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI())) {
            float arg0$iv5 = $this$selectorPos.m2146getCurrentDiameterD9Ej5fM();
            float other$iv2 = InnerCircleToSizeRatio;
            arg0$iv = Dp.m8150constructorimpl(arg0$iv5 * other$iv2);
        } else {
            float arg0$iv6 = $this$selectorPos.m2146getCurrentDiameterD9Ej5fM();
            float other$iv3 = OuterCircleToSizeRatio;
            arg0$iv = Dp.m8150constructorimpl(arg0$iv6 * other$iv3);
        }
        float selectorLength = ((Dp) RangesKt.coerceAtLeast(Dp.m8148boximpl(Dp.m8150constructorimpl(arg0$iv - arg0$iv4)), Dp.m8148boximpl(Dp.m8150constructorimpl(0)))).m8164unboximpl();
        float length = Dp.m8150constructorimpl(selectorLength + arg0$iv4);
        float other$iv4 = (float) Math.cos($this$selectorPos.getCurrentAngle());
        float other$iv5 = Dp.m8150constructorimpl(length * other$iv4);
        float arg0$iv7 = $this$selectorPos.m2146getCurrentDiameterD9Ej5fM();
        float arg0$iv8 = Dp.m8150constructorimpl(other$iv5 + Dp.m8150constructorimpl(arg0$iv7 / 2));
        float other$iv6 = (float) Math.sin($this$selectorPos.getCurrentAngle());
        float other$iv7 = Dp.m8150constructorimpl(length * other$iv6);
        float arg0$iv9 = $this$selectorPos.m2146getCurrentDiameterD9Ej5fM();
        float arg0$iv10 = Dp.m8150constructorimpl(other$iv7 + Dp.m8150constructorimpl(arg0$iv9 / 2));
        long v1$iv$iv = Float.floatToRawIntBits(arg0$iv8);
        long v2$iv$iv = Float.floatToRawIntBits(arg0$iv10);
        return DpOffset.m8206constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    public static final void VerticalTimePicker(final AnalogTimePickerState state, Modifier modifier, TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors timePickerColors;
        boolean z;
        final Modifier modifier3;
        final TimePickerColors colors2;
        Modifier.Companion modifier4;
        int $dirty;
        Modifier modifier5;
        TimePickerColors colors3;
        Composer $composer2 = $composer.startRestartGroup(1249591487);
        ComposerKt.sourceInformation($composer2, "C(VerticalTimePicker)N(state,modifier,colors,autoSwitchToMinute)959@41242L27,958@41196L544:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(state) ? 4 : 2;
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
                timePickerColors = colors;
                int i3 = $composer2.changed(timePickerColors) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                timePickerColors = colors;
            }
            $dirty2 |= i3;
        } else {
            timePickerColors = colors;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
            z = autoSwitchToMinute;
        } else if (($changed & 3072) == 0) {
            z = autoSwitchToMinute;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = autoSwitchToMinute;
        }
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "955@41145L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier5 = modifier2;
                colors3 = timePickerColors;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                    colors3 = timePickerColors;
                } else {
                    $dirty = $dirty2 & (-897);
                    modifier5 = modifier4;
                    colors3 = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1249591487, $dirty, -1, "androidx.compose.material3.VerticalTimePicker (TimePicker.kt:957)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1733414662, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.VerticalTimePicker$lambda$9$lambda$8((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) it$iv, 1, null);
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1720573003, "C962@41347L52,963@41408L60,964@41477L191,970@41677L57:TimePicker.kt#uh7d8r");
            VerticalClockDisplay(state, colors3, $composer2, ($dirty & 14) | (($dirty >> 3) & 112));
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, ClockDisplayBottomMargin), $composer2, 6);
            ClockFace(SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4257getClockDialContainerSizeD9Ej5fM()), state, colors3, z, $composer2, ($dirty & 896) | (($dirty << 3) & 112) | 6 | ($dirty & 7168));
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, ClockFaceBottomMargin), $composer2, 6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors3;
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = timePickerColors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.VerticalTimePicker$lambda$11(state, modifier3, colors2, autoSwitchToMinute, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit VerticalTimePicker$lambda$9$lambda$8(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
        return Unit.INSTANCE;
    }

    public static final void HorizontalTimePicker(final AnalogTimePickerState state, Modifier modifier, TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors timePickerColors;
        boolean z;
        final Modifier modifier3;
        final TimePickerColors colors2;
        Modifier.Companion modifier4;
        int $dirty;
        Modifier modifier5;
        TimePickerColors colors3;
        Composer $composer2 = $composer.startRestartGroup(1432307537);
        ComposerKt.sourceInformation($composer2, "C(HorizontalTimePicker)N(state,modifier,colors,autoSwitchToMinute)982@42004L27,981@41961L418:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(state) ? 4 : 2;
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
                timePickerColors = colors;
                int i3 = $composer2.changed(timePickerColors) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                timePickerColors = colors;
            }
            $dirty2 |= i3;
        } else {
            timePickerColors = colors;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
            z = autoSwitchToMinute;
        } else if (($changed & 3072) == 0) {
            z = autoSwitchToMinute;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = autoSwitchToMinute;
        }
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "978@41910L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier5 = modifier2;
                colors3 = timePickerColors;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                    colors3 = timePickerColors;
                } else {
                    $dirty = $dirty2 & (-897);
                    modifier5 = modifier4;
                    colors3 = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1432307537, $dirty, -1, "androidx.compose.material3.HorizontalTimePicker (TimePicker.kt:980)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 2058016684, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.HorizontalTimePicker$lambda$13$lambda$12((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) it$iv, 1, null);
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1443384930, "C985@42105L37,986@42151L59,987@42219L154:TimePicker.kt#uh7d8r");
            HorizontalClockDisplay(state, colors3, $composer2, ($dirty & 14) | (($dirty >> 3) & 112));
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, ClockDisplayBottomMargin), $composer2, 6);
            ClockFace(Modifier.INSTANCE.then(new ClockFaceSizeModifier()), state, colors3, z, $composer2, ($dirty & 896) | (($dirty << 3) & 112) | ($dirty & 7168));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors3;
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = timePickerColors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.HorizontalTimePicker$lambda$15(state, modifier3, colors2, autoSwitchToMinute, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit HorizontalTimePicker$lambda$13$lambda$12(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
        return Unit.INSTANCE;
    }

    private static final void TimeInputImpl(Modifier modifier, TimePickerColors colors, TimePickerState state, Composer $composer, final int $changed) {
        final Modifier modifier2;
        final TimePickerColors timePickerColors;
        Composer $composer2;
        Ref userOverride;
        MutableState hourValue$delegate;
        MutableState minuteValue$delegate;
        Function0<ComposeUiNode> function0;
        final TimePickerState timePickerState = state;
        Composer $composer3 = $composer.startRestartGroup(-475657989);
        ComposerKt.sourceInformation($composer3, "C(TimeInputImpl)N(modifier,colors,state)1002@42753L35,1002@42701L87,1005@42873L37,1005@42821L89,1007@42935L27,1009@43044L177,1009@43003L218,1017@43227L4621:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(colors) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer3.changed(timePickerState) : $composer3.changedInstance(timePickerState) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-475657989, $dirty2, -1, "androidx.compose.material3.TimeInputImpl (TimePicker.kt:997)");
            }
            Object[] objArr = new Object[0];
            Saver<TextFieldValue, Object> saver = TextFieldValue.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart($composer3, -1840782178, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(timePickerState));
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TimePickerKt.TimeInputImpl$hourTextValue(timePickerState), null, 2, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MutableState hourValue$delegate2 = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (Function0) it$iv, $composer3, 0);
            Object[] objArr2 = new Object[0];
            Saver<TextFieldValue, Object> saver2 = TextFieldValue.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart($composer3, -1840778336, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = ($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(timePickerState));
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TimePickerKt.TimeInputImpl$minuteTextValue(timePickerState), null, 2, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MutableState minuteValue$delegate2 = RememberSaveableKt.rememberSaveable(objArr2, (Saver) saver2, (Function0) it$iv2, $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, -1840776362, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Ref();
                $composer3.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            Ref userOverride2 = (Ref) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Integer numValueOf = Integer.valueOf(timePickerState.getHour());
            Integer numValueOf2 = Integer.valueOf(timePickerState.getMinute());
            ComposerKt.sourceInformationMarkerStart($composer3, -1840772724, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv3 = $composer3.changedInstance(userOverride2) | $composer3.changed(hourValue$delegate2) | (($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(timePickerState))) | $composer3.changed(minuteValue$delegate2);
            Object it$iv4 = $composer3.rememberedValue();
            if (invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                userOverride = userOverride2;
                hourValue$delegate = hourValue$delegate2;
                minuteValue$delegate = minuteValue$delegate2;
                Object value$iv4 = (Function2) new TimePickerKt$TimeInputImpl$1$1(userOverride2, timePickerState, hourValue$delegate2, minuteValue$delegate2, null);
                $composer3.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
            } else {
                hourValue$delegate = hourValue$delegate2;
                userOverride = userOverride2;
                minuteValue$delegate = minuteValue$delegate2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(numValueOf, numValueOf2, (Function2) it$iv4, $composer3, 0);
            Modifier modifier$iv = PaddingKt.m1052paddingqDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, TimeInputBottomPadding, 7, null);
            modifier2 = modifier;
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1385914133, "C1022@43428L5,1031@43794L3669,1027@43572L3891:TimePicker.kt#uh7d8r");
            TextStyle textStyle = TextStyle.m7586copyp1EtxEg$default(TypographyKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldLabelTextFont(), $composer3, 6), colors.m3180timeSelectorContentColorvNxB06k$material3(true), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m8003getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744446, null);
            timePickerColors = colors;
            MutableState hourValue$delegate3 = hourValue$delegate;
            $composer2 = $composer3;
            timePickerState = state;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(textStyle), CompositionLocalsKt.getLocalLayoutDirection().provides(LayoutDirection.Ltr)}, ComposableLambdaKt.rememberComposableLambda(1306700887, true, new TimePickerKt$TimeInputImpl$2$1(hourValue$delegate3, timePickerState, userOverride, timePickerColors, minuteValue$delegate), $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (timePickerState.getIs24hour()) {
                $composer3.startReplaceGroup(-1381607893);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1381942321);
                ComposerKt.sourceInformation($composer3, "1120@47508L324");
                Modifier modifier$iv2 = PaddingKt.m1052paddingqDBjuR0$default(Modifier.INSTANCE, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor2;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor2;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4433constructorimpl($composer3);
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1377011131, "C1121@47576L242:TimePicker.kt#uh7d8r");
                VerticalPeriodToggle(SizeKt.m1117sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m4252getPeriodSelectorContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m4251getPeriodSelectorContainerHeightD9Ej5fM()), timePickerState, timePickerColors, $composer3, (($dirty2 >> 3) & 112) | 6 | (($dirty2 << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            modifier2 = modifier;
            timePickerColors = colors;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.TimeInputImpl$lambda$28(modifier2, timePickerColors, timePickerState, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final TextFieldValue TimeInputImpl$hourTextValue(TimePickerState $state) {
        return new TextFieldValue(CalendarLocale_jvmKt.toLocalString$default(getHourForDisplay($state), 2, 0, false, null, 14, null), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null);
    }

    public static final TextFieldValue TimeInputImpl$minuteTextValue(TimePickerState $state) {
        return new TextFieldValue(CalendarLocale_jvmKt.toLocalString$default($state.getMinute(), 2, 0, false, null, 14, null), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null);
    }

    public static final TextFieldValue TimeInputImpl$lambda$18(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final TextFieldValue TimeInputImpl$lambda$22(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    private static final void HorizontalClockDisplay(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        Composer $composer3 = $composer.startRestartGroup(755539561);
        ComposerKt.sourceInformation($composer3, "C(HorizontalClockDisplay)N(state,colors)1134@47955L591:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(colors) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(755539561, $dirty, -1, "androidx.compose.material3.HorizontalClockDisplay (TimePicker.kt:1133)");
            }
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $dirty2 = $dirty;
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            $composer2 = $composer3;
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 998514658, "C1135@48014L34:TimePicker.kt#uh7d8r");
            ClockDisplayNumbers(state, colors, $composer2, ($dirty2 & 14) | ($dirty2 & 112));
            if (state.getIs24hour()) {
                $composer2.startReplaceGroup(999020143);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(998576161);
                ComposerKt.sourceInformation($composer2, "1137@48092L438");
                Modifier modifier$iv2 = PaddingKt.m1052paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, PeriodToggleMargin, 0.0f, 0.0f, 13, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function02 = constructor2;
                    $composer2.createNode(function02);
                } else {
                    function02 = constructor2;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4433constructorimpl($composer2);
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1710314390, "C1138@48169L347:TimePicker.kt#uh7d8r");
                HorizontalPeriodToggle(SizeKt.m1117sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4263getPeriodSelectorHorizontalContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4262getPeriodSelectorHorizontalContainerHeightD9Ej5fM()), state, colors, $composer2, (($dirty2 << 3) & 112) | 6 | (($dirty2 << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.HorizontalClockDisplay$lambda$31(state, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void VerticalClockDisplay(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        Composer $composer3 = $composer.startRestartGroup(2054675515);
        ComposerKt.sourceInformation($composer3, "C(VerticalClockDisplay)N(state,colors)1154@48651L586:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(colors) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2054675515, $dirty, -1, "androidx.compose.material3.VerticalClockDisplay (TimePicker.kt:1153)");
            }
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $dirty2 = $dirty;
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            $composer2 = $composer3;
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1364225858, "C1155@48709L34:TimePicker.kt#uh7d8r");
            ClockDisplayNumbers(state, colors, $composer2, ($dirty2 & 14) | ($dirty2 & 112));
            if (state.getIs24hour()) {
                $composer2.startReplaceGroup(1364727499);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1364287361);
                ComposerKt.sourceInformation($composer2, "1157@48787L434");
                Modifier modifier$iv2 = PaddingKt.m1052paddingqDBjuR0$default(Modifier.INSTANCE, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function02 = constructor2;
                    $composer2.createNode(function02);
                } else {
                    function02 = constructor2;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4433constructorimpl($composer2);
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -2145741896, "C1158@48866L341:TimePicker.kt#uh7d8r");
                VerticalPeriodToggle(SizeKt.m1117sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4266getPeriodSelectorVerticalContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4265getPeriodSelectorVerticalContainerHeightD9Ej5fM()), state, colors, $composer2, (($dirty2 << 3) & 112) | 6 | (($dirty2 << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.VerticalClockDisplay$lambda$34(state, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ClockDisplayNumbers(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-934561141);
        ComposerKt.sourceInformation($composer2, "C(ClockDisplayNumbers)N(state,colors)1175@49425L5,1178@49561L775,1174@49341L995:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-934561141, $dirty, -1, "androidx.compose.material3.ClockDisplayNumbers (TimePicker.kt:1173)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(TypographyKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorLabelTextFont(), $composer2, 6)), CompositionLocalsKt.getLocalLayoutDirection().provides(LayoutDirection.Ltr)}, ComposableLambdaKt.rememberComposableLambda(-477913269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockDisplayNumbers.1
                final /* synthetic */ TimePickerColors $colors;

                AnonymousClass1(final TimePickerColors colors2) {
                    timePickerColors = colors2;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer3, "C1179@49571L759:TimePicker.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-477913269, $changed2, -1, "androidx.compose.material3.ClockDisplayNumbers.<anonymous> (TimePicker.kt:1179)");
                    }
                    TimePickerState timePickerState = timePickerState;
                    TimePickerColors timePickerColors = timePickerColors;
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function0 = constructor;
                        $composer3.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer3.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer3);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i2 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2065726649, "C1180@49589L294,1187@49896L123,1190@50032L288:TimePicker.kt#uh7d8r");
                    TimePickerKt.m3199TimeSelectorSAnMeKU(SizeKt.m1117sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4269getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4268getTimeSelectorContainerHeightD9Ej5fM()), TimePickerKt.getHourForDisplay(timePickerState), timePickerState, TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI(), timePickerColors, $composer3, 3078);
                    TimePickerKt.DisplaySeparator(SizeKt.m1117sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimePickerTokens.INSTANCE.m4265getPeriodSelectorVerticalContainerHeightD9Ej5fM()), $composer3, 6);
                    TimePickerKt.m3199TimeSelectorSAnMeKU(SizeKt.m1117sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4269getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4268getTimeSelectorContainerHeightD9Ej5fM()), timePickerState.getMinute(), timePickerState, TimePickerSelectionMode.INSTANCE.m3228getMinuteyecRtBI(), timePickerColors, $composer3, 3078);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.ClockDisplayNumbers$lambda$35(state, colors2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockDisplayNumbers$1 */
    /* JADX INFO: compiled from: TimePicker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ TimePickerColors $colors;

        AnonymousClass1(final TimePickerColors colors2) {
            timePickerColors = colors2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer3, int $changed2) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer3, "C1179@49571L759:TimePicker.kt#uh7d8r");
            if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                $composer3.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-477913269, $changed2, -1, "androidx.compose.material3.ClockDisplayNumbers.<anonymous> (TimePicker.kt:1179)");
            }
            TimePickerState timePickerState = timePickerState;
            TimePickerColors timePickerColors = timePickerColors;
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 2065726649, "C1180@49589L294,1187@49896L123,1190@50032L288:TimePicker.kt#uh7d8r");
            TimePickerKt.m3199TimeSelectorSAnMeKU(SizeKt.m1117sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4269getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4268getTimeSelectorContainerHeightD9Ej5fM()), TimePickerKt.getHourForDisplay(timePickerState), timePickerState, TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI(), timePickerColors, $composer3, 3078);
            TimePickerKt.DisplaySeparator(SizeKt.m1117sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimePickerTokens.INSTANCE.m4265getPeriodSelectorVerticalContainerHeightD9Ej5fM()), $composer3, 6);
            TimePickerKt.m3199TimeSelectorSAnMeKU(SizeKt.m1117sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4269getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4268getTimeSelectorContainerHeightD9Ej5fM()), timePickerState.getMinute(), timePickerState, TimePickerSelectionMode.INSTANCE.m3228getMinuteyecRtBI(), timePickerColors, $composer3, 3078);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    private static final void HorizontalPeriodToggle(Modifier modifier, TimePickerState state, TimePickerColors colors, Composer $composer, final int $changed) {
        final Modifier modifier2;
        final TimePickerState state2;
        final TimePickerColors colors2;
        Composer $composer2 = $composer.startRestartGroup(1261215927);
        ComposerKt.sourceInformation($composer2, "C(HorizontalPeriodToggle)N(modifier,state,colors)1207@50498L1014,1235@51559L5,1237@51590L207:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1261215927, $dirty, -1, "androidx.compose.material3.HorizontalPeriodToggle (TimePicker.kt:1206)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 847734445, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (MeasurePolicy) TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1.INSTANCE;
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape = (CornerBasedShape) value;
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            PeriodToggleImpl(modifier2, state2, colors2, measurePolicy, ShapesKt.start$default(shape, null, 1, null), ShapesKt.end$default(shape, null, 1, null), $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.HorizontalPeriodToggle$lambda$37(modifier2, state2, colors2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void VerticalPeriodToggle(Modifier modifier, TimePickerState state, TimePickerColors colors, Composer $composer, final int $changed) {
        final Modifier modifier2;
        final TimePickerState state2;
        final TimePickerColors colors2;
        Composer $composer2 = $composer.startRestartGroup(-1898918107);
        ComposerKt.sourceInformation($composer2, "C(VerticalPeriodToggle)N(modifier,state,colors)1253@51957L1022,1281@53026L5,1283@53057L208:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1898918107, $dirty, -1, "androidx.compose.material3.VerticalPeriodToggle (TimePicker.kt:1252)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 500805987, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (MeasurePolicy) TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1.INSTANCE;
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape = (CornerBasedShape) value;
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            PeriodToggleImpl(modifier2, state2, colors2, measurePolicy, ShapesKt.top$default(shape, null, 1, null), ShapesKt.bottom$default(shape, null, 1, null), $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda34
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.VerticalPeriodToggle$lambda$39(modifier2, state2, colors2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:212:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void PeriodToggleImpl(final androidx.compose.ui.Modifier r38, final androidx.compose.material3.TimePickerState r39, final androidx.compose.material3.TimePickerColors r40, final androidx.compose.ui.layout.MeasurePolicy r41, final androidx.compose.ui.graphics.Shape r42, final androidx.compose.ui.graphics.Shape r43, androidx.compose.runtime.Composer r44, final int r45) {
        /*
            Method dump skipped, instruction units count: 803
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.PeriodToggleImpl(androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerState, androidx.compose.material3.TimePickerColors, androidx.compose.ui.layout.MeasurePolicy, androidx.compose.ui.graphics.Shape, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int):void");
    }

    static final Unit PeriodToggleImpl$lambda$41$lambda$40(String $contentDescription, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
        SemanticsPropertiesKt.setContentDescription($this$semantics, $contentDescription);
        return Unit.INSTANCE;
    }

    static final Unit PeriodToggleImpl$lambda$46$lambda$43$lambda$42(TimePickerState $state) {
        if (isPm($state)) {
            $state.setHour($state.getHour() - 12);
        }
        return Unit.INSTANCE;
    }

    static final Unit PeriodToggleImpl$lambda$46$lambda$45$lambda$44(TimePickerState $state) {
        if (!isPm($state)) {
            $state.setHour($state.getHour() + 12);
        }
        return Unit.INSTANCE;
    }

    private static final void ToggleItem(final boolean checked, final Shape shape, final Function0<Unit> function0, final TimePickerColors colors, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Shape shape2;
        Composer $composer2;
        Composer $composer3;
        Composer $composer4 = $composer.startRestartGroup(1523811083);
        ComposerKt.sourceInformation($composer4, "C(ToggleItem)N(checked,shape,onClick,colors,content)1365@55539L22,1371@55730L125,1363@55431L431:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer4.changed(checked) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            shape2 = shape;
            $dirty |= $composer4.changed(shape2) ? 32 : 16;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer4.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer4.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? 16384 : 8192;
        }
        if ($composer4.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1523811083, $dirty, -1, "androidx.compose.material3.ToggleItem (TimePicker.kt:1359)");
            }
            long contentColor = colors.m3178periodSelectorContentColorvNxB06k$material3(checked);
            long containerColor = colors.m3177periodSelectorContainerColorvNxB06k$material3(checked);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(Modifier.INSTANCE, checked ? 0.0f : 1.0f), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer4, -201383391, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv = $composer4.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                $composer3 = $composer4;
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.ToggleItem$lambda$49$lambda$48(checked, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer4.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                $composer3 = $composer4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Composer $composer5 = $composer3;
            ButtonKt.TextButton(function0, SemanticsModifierKt.semantics$default(modifierFillMaxSize$default, false, (Function1) it$iv, 1, null), false, shape2, ButtonDefaults.INSTANCE.m2219textButtonColorsro_MJ88(containerColor, contentColor, 0L, 0L, $composer5, 24576, 12), null, null, PaddingKt.m1041PaddingValues0680j_4(Dp.m8150constructorimpl(0)), null, function3, $composer5, (($dirty >> 6) & 14) | 12582912 | (($dirty << 6) & 7168) | (($dirty << 15) & 1879048192), 356);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.ToggleItem$lambda$50(checked, shape, function0, colors, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ToggleItem$lambda$49$lambda$48(boolean $checked, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setSelected($this$semantics, $checked);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x0249  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void DisplaySeparator(final androidx.compose.ui.Modifier r53, androidx.compose.runtime.Composer r54, final int r55) {
        /*
            Method dump skipped, instruction units count: 611
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.DisplaySeparator(androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: renamed from: TimeSelector-SAnMeKU */
    public static final void m3199TimeSelectorSAnMeKU(final Modifier modifier, final int value, final TimePickerState state, final int selection, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2;
        int iM3454constructorimpl;
        Composer $composer3 = $composer.startRestartGroup(-1148055889);
        ComposerKt.sourceInformation($composer3, "C(TimeSelector)N(modifier,value,state,selection:c#material3.TimePickerSelectionMode,colors)1406@56730L214,1418@57168L124,1428@57503L5,1422@57312L117,1430@57548L498,1416@57084L962:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(selection) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(colors) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1148055889, $dirty2, -1, "androidx.compose.material3.TimeSelector (TimePicker.kt:1403)");
            }
            boolean selected = TimePickerSelectionMode.m3223equalsimpl0(state.mo2147getSelectionyecRtBI(), selection);
            if (TimePickerSelectionMode.m3223equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI())) {
                Strings.Companion companion = Strings.INSTANCE;
                iM3454constructorimpl = Strings.m3454constructorimpl(R.string.m3c_time_picker_hour_selection);
            } else {
                Strings.Companion companion2 = Strings.INSTANCE;
                iM3454constructorimpl = Strings.m3454constructorimpl(R.string.m3c_time_picker_minute_selection);
            }
            final String selectorContentDescription = Strings_androidKt.m3533getString2EP1pXo(iM3454constructorimpl, $composer3, 0);
            long containerColor = colors.m3179timeSelectorContainerColorvNxB06k$material3(selected);
            long contentColor = colors.m3180timeSelectorContentColorvNxB06k$material3(selected);
            ComposerKt.sourceInformationMarkerStart($composer3, 524909899, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(selectorContentDescription);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.TimeSelector_SAnMeKU$lambda$56$lambda$55(selectorContentDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (Function1) it$iv);
            Shape value2 = ShapesKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorContainerShape(), $composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, 524914500, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 7168) == 2048) | (($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(state)));
            Object value$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TimePickerKt.TimeSelector_SAnMeKU$lambda$58$lambda$57(selection, state);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            SurfaceKt.m3015Surfaced85dljk(selected, (Function0<Unit>) value$iv2, modifierSemantics, false, value2, containerColor, 0L, 0.0f, 0.0f, (BorderStroke) null, (MutableInteractionSource) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1477282471, true, new TimePickerKt$TimeSelector$3(selection, state, value, contentColor), $composer3, 54), $composer2, 0, 48, 1992);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.TimeSelector_SAnMeKU$lambda$59(modifier, value, state, selection, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TimeSelector_SAnMeKU$lambda$56$lambda$55(String $selectorContentDescription, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7362setRolekuIjeqM($this$semantics, Role.INSTANCE.m7348getRadioButtono7Vup1c());
        SemanticsPropertiesKt.setContentDescription($this$semantics, $selectorContentDescription);
        return Unit.INSTANCE;
    }

    static final Unit TimeSelector_SAnMeKU$lambda$58$lambda$57(int $selection, TimePickerState $state) {
        if (!TimePickerSelectionMode.m3223equalsimpl0($selection, $state.mo2147getSelectionyecRtBI())) {
            $state.mo2149setSelection6_8s6DQ($selection);
        }
        return Unit.INSTANCE;
    }

    public static final void ClockFace(final Modifier modifier, AnalogTimePickerState state, final TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed) {
        final AnalogTimePickerState analogTimePickerState = state;
        Composer $composer2 = $composer.startRestartGroup(-478841003);
        ComposerKt.sourceInformation($composer2, "C(ClockFace)N(modifier,state,colors,autoSwitchToMinute)1602@63170L7,1607@63371L7,1608@63386L2054,1593@62800L2640:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(analogTimePickerState) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-478841003, $dirty2, -1, "androidx.compose.material3.ClockFace (TimePicker.kt:1591)");
            }
            analogTimePickerState = state;
            CrossfadeKt.Crossfade(analogTimePickerState.getClockFaceValues(), drawSelector(BackgroundKt.m285backgroundbw27NRU(modifier, colors.getClockDialColor(), RoundedCornerShapeKt.getCircleShape()).then(new ClockDialModifier(state, autoSwitchToMinute, state.mo2147getSelectionyecRtBI(), MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6), null)), analogTimePickerState, colors), (FiniteAnimationSpec<Float>) MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(747010833, true, new C02771(colors, analogTimePickerState, autoSwitchToMinute), $composer2, 54), $composer2, 24576, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.ClockFace$lambda$60(modifier, analogTimePickerState, colors, autoSwitchToMinute, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1 */
    /* JADX INFO: compiled from: TimePicker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02771 implements Function3<IntList, Composer, Integer, Unit> {
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ AnalogTimePickerState $state;

        C02771(TimePickerColors timePickerColors, AnalogTimePickerState analogTimePickerState, boolean z) {
            this.$colors = timePickerColors;
            this.$state = analogTimePickerState;
            this.$autoSwitchToMinute = z;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(IntList intList, Composer composer, Integer num) {
            invoke(intList, composer, num.intValue());
            return Unit.INSTANCE;
        }

        static final Unit invoke$lambda$1$lambda$0(SemanticsPropertyReceiver $this$semantics) {
            SemanticsPropertiesKt.selectableGroup($this$semantics);
            return Unit.INSTANCE;
        }

        public final void invoke(IntList screen, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "CN(screen)1610@63493L21,1612@63582L1852,1609@63406L2028:TimePicker.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(747010833, $changed, -1, "androidx.compose.material3.ClockFace.<anonymous> (TimePicker.kt:1609)");
            }
            Modifier modifierM1115size3ABfNKs = SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4257getClockDialContainerSizeD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer, 617875526, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.C02771.invoke$lambda$1$lambda$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TimePickerKt.CircularLayout(SemanticsModifierKt.semantics$default(modifierM1115size3ABfNKs, false, (Function1) it$iv, 1, null), TimePickerKt.OuterCircleToSizeRatio, ComposableLambdaKt.rememberComposableLambda(-99063847, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1.2
                final /* synthetic */ boolean $autoSwitchToMinute;
                final /* synthetic */ IntList $screen;
                final /* synthetic */ AnalogTimePickerState $state;

                AnonymousClass2(IntList screen2, AnalogTimePickerState analogTimePickerState, boolean z) {
                    intList = screen2;
                    analogTimePickerState = analogTimePickerState;
                    z = z;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1 */
                /* JADX INFO: compiled from: TimePicker.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                static final class C00521 implements Function2<Composer, Integer, Unit> {
                    final /* synthetic */ boolean $autoSwitchToMinute;
                    final /* synthetic */ IntList $screen;
                    final /* synthetic */ AnalogTimePickerState $state;

                    C00521(IntList intList, AnalogTimePickerState analogTimePickerState, boolean z) {
                        this.$screen = intList;
                        this.$state = analogTimePickerState;
                        this.$autoSwitchToMinute = z;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer, int $changed) {
                        int outerValue;
                        boolean z;
                        Composer composer = $composer;
                        ComposerKt.sourceInformation(composer, "C:TimePicker.kt#uh7d8r");
                        if (!composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                            composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-596940007, $changed, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1616)");
                        }
                        composer.startReplaceGroup(1866272144);
                        ComposerKt.sourceInformation(composer, "*1624@64134L41,1623@64069L277");
                        IntList this_$iv = this.$screen;
                        int i = this_$iv._size;
                        AnalogTimePickerState analogTimePickerState = this.$state;
                        IntList intList = this.$screen;
                        boolean z2 = this.$autoSwitchToMinute;
                        int i2 = 0;
                        while (i2 < i) {
                            final int index = i2;
                            if (!analogTimePickerState.getIs24hour() || TimePickerSelectionMode.m3223equalsimpl0(analogTimePickerState.mo2147getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3228getMinuteyecRtBI())) {
                                outerValue = intList.get(index);
                            } else {
                                outerValue = intList.get(index) % 12;
                            }
                            Modifier.Companion companion = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer, -121641742, "CC(remember):TimePicker.kt#9igjgp");
                            boolean invalid$iv = composer.changed(index);
                            Object it$iv = $composer.rememberedValue();
                            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                z = z2;
                                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return TimePickerKt.C02771.AnonymousClass2.C00521.invoke$lambda$2$lambda$1$lambda$0(index, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                $composer.updateRememberedValue(value$iv);
                                it$iv = value$iv;
                            } else {
                                z = z2;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null);
                            Composer composer2 = composer;
                            TimePickerKt.ClockText(modifierSemantics$default, analogTimePickerState, outerValue, z, composer2, 0);
                            composer = composer2;
                            i2++;
                            z2 = z;
                        }
                        composer.endReplaceGroup();
                        if (TimePickerSelectionMode.m3223equalsimpl0(this.$state.mo2147getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI()) && this.$state.getIs24hour()) {
                            composer.startReplaceGroup(2020585964);
                            ComposerKt.sourceInformation(composer, "1638@64839L553,1632@64475L917");
                            TimePickerKt.CircularLayout(BackgroundKt.m285backgroundbw27NRU(SizeKt.m1115size3ABfNKs(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutId.InnerCircle), TimePickerTokens.INSTANCE.m4257getClockDialContainerSizeD9Ej5fM()), Color.INSTANCE.m5348getTransparent0d7_KjU(), RoundedCornerShapeKt.getCircleShape()), TimePickerKt.InnerCircleToSizeRatio, ComposableLambdaKt.rememberComposableLambda(-1385767514, true, new C00532(this.$state, this.$autoSwitchToMinute), composer, 54), composer, 432, 0);
                            composer.endReplaceGroup();
                        } else {
                            composer.startReplaceGroup(2021505641);
                            composer.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    static final Unit invoke$lambda$2$lambda$1$lambda$0(int $index, SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setTraversalIndex($this$semantics, $index + 1.0f);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$2 */
                    /* JADX INFO: compiled from: TimePicker.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                    static final class C00532 implements Function2<Composer, Integer, Unit> {
                        final /* synthetic */ boolean $autoSwitchToMinute;
                        final /* synthetic */ AnalogTimePickerState $state;

                        C00532(AnalogTimePickerState analogTimePickerState, boolean z) {
                            this.$state = analogTimePickerState;
                            this.$autoSwitchToMinute = z;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer, int $changed) {
                            Composer composer = $composer;
                            ComposerKt.sourceInformation(composer, "C*1643@65100L41,1641@64991L353:TimePicker.kt#uh7d8r");
                            if (composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1385767514, $changed, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1639)");
                                }
                                IntList this_$iv = TimePickerKt.ExtraHours;
                                int i = this_$iv._size;
                                AnalogTimePickerState analogTimePickerState = this.$state;
                                boolean z = this.$autoSwitchToMinute;
                                int i2 = 0;
                                while (i2 < i) {
                                    final int index = i2;
                                    int innerValue = TimePickerKt.ExtraHours.get(index);
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer, -1870016769, "CC(remember):TimePicker.kt#9igjgp");
                                    boolean invalid$iv = composer.changed(index);
                                    Object it$iv = $composer.rememberedValue();
                                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                        Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$2$$ExternalSyntheticLambda0
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                return TimePickerKt.C02771.AnonymousClass2.C00521.C00532.invoke$lambda$2$lambda$1$lambda$0(index, (SemanticsPropertyReceiver) obj);
                                            }
                                        };
                                        $composer.updateRememberedValue(value$iv);
                                        it$iv = value$iv;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null), analogTimePickerState, innerValue, z, composer, 0);
                                    i2++;
                                    composer = $composer;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer.skipToGroupEnd();
                        }

                        static final Unit invoke$lambda$2$lambda$1$lambda$0(int $index, SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.setTraversalIndex($this$semantics, 12.0f + $index);
                            return Unit.INSTANCE;
                        }
                    }
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C1615@63715L1709,1613@63596L1828:TimePicker.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-99063847, $changed2, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous> (TimePicker.kt:1613)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(timePickerColors.m3161clockDialContentColorvNxB06k$material3(false))), ComposableLambdaKt.rememberComposableLambda(-596940007, true, new C00521(intList, analogTimePickerState, z), $composer2, 54), $composer2, ProvidedValue.$stable | 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54), $composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1$2 */
        /* JADX INFO: compiled from: TimePicker.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
            final /* synthetic */ boolean $autoSwitchToMinute;
            final /* synthetic */ IntList $screen;
            final /* synthetic */ AnalogTimePickerState $state;

            AnonymousClass2(IntList screen2, AnalogTimePickerState analogTimePickerState, boolean z) {
                intList = screen2;
                analogTimePickerState = analogTimePickerState;
                z = z;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1 */
            /* JADX INFO: compiled from: TimePicker.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            static final class C00521 implements Function2<Composer, Integer, Unit> {
                final /* synthetic */ boolean $autoSwitchToMinute;
                final /* synthetic */ IntList $screen;
                final /* synthetic */ AnalogTimePickerState $state;

                C00521(IntList intList, AnalogTimePickerState analogTimePickerState, boolean z) {
                    this.$screen = intList;
                    this.$state = analogTimePickerState;
                    this.$autoSwitchToMinute = z;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer, int $changed) {
                    int outerValue;
                    boolean z;
                    Composer composer = $composer;
                    ComposerKt.sourceInformation(composer, "C:TimePicker.kt#uh7d8r");
                    if (!composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-596940007, $changed, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1616)");
                    }
                    composer.startReplaceGroup(1866272144);
                    ComposerKt.sourceInformation(composer, "*1624@64134L41,1623@64069L277");
                    IntList this_$iv = this.$screen;
                    int i = this_$iv._size;
                    AnalogTimePickerState analogTimePickerState = this.$state;
                    IntList intList = this.$screen;
                    boolean z2 = this.$autoSwitchToMinute;
                    int i2 = 0;
                    while (i2 < i) {
                        final int index = i2;
                        if (!analogTimePickerState.getIs24hour() || TimePickerSelectionMode.m3223equalsimpl0(analogTimePickerState.mo2147getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3228getMinuteyecRtBI())) {
                            outerValue = intList.get(index);
                        } else {
                            outerValue = intList.get(index) % 12;
                        }
                        Modifier.Companion companion = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer, -121641742, "CC(remember):TimePicker.kt#9igjgp");
                        boolean invalid$iv = composer.changed(index);
                        Object it$iv = $composer.rememberedValue();
                        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                            z = z2;
                            Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return TimePickerKt.C02771.AnonymousClass2.C00521.invoke$lambda$2$lambda$1$lambda$0(index, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                            it$iv = value$iv;
                        } else {
                            z = z2;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null);
                        Composer composer2 = composer;
                        TimePickerKt.ClockText(modifierSemantics$default, analogTimePickerState, outerValue, z, composer2, 0);
                        composer = composer2;
                        i2++;
                        z2 = z;
                    }
                    composer.endReplaceGroup();
                    if (TimePickerSelectionMode.m3223equalsimpl0(this.$state.mo2147getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI()) && this.$state.getIs24hour()) {
                        composer.startReplaceGroup(2020585964);
                        ComposerKt.sourceInformation(composer, "1638@64839L553,1632@64475L917");
                        TimePickerKt.CircularLayout(BackgroundKt.m285backgroundbw27NRU(SizeKt.m1115size3ABfNKs(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutId.InnerCircle), TimePickerTokens.INSTANCE.m4257getClockDialContainerSizeD9Ej5fM()), Color.INSTANCE.m5348getTransparent0d7_KjU(), RoundedCornerShapeKt.getCircleShape()), TimePickerKt.InnerCircleToSizeRatio, ComposableLambdaKt.rememberComposableLambda(-1385767514, true, new C00532(this.$state, this.$autoSwitchToMinute), composer, 54), composer, 432, 0);
                        composer.endReplaceGroup();
                    } else {
                        composer.startReplaceGroup(2021505641);
                        composer.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                static final Unit invoke$lambda$2$lambda$1$lambda$0(int $index, SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setTraversalIndex($this$semantics, $index + 1.0f);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$2 */
                /* JADX INFO: compiled from: TimePicker.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                static final class C00532 implements Function2<Composer, Integer, Unit> {
                    final /* synthetic */ boolean $autoSwitchToMinute;
                    final /* synthetic */ AnalogTimePickerState $state;

                    C00532(AnalogTimePickerState analogTimePickerState, boolean z) {
                        this.$state = analogTimePickerState;
                        this.$autoSwitchToMinute = z;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer, int $changed) {
                        Composer composer = $composer;
                        ComposerKt.sourceInformation(composer, "C*1643@65100L41,1641@64991L353:TimePicker.kt#uh7d8r");
                        if (composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1385767514, $changed, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1639)");
                            }
                            IntList this_$iv = TimePickerKt.ExtraHours;
                            int i = this_$iv._size;
                            AnalogTimePickerState analogTimePickerState = this.$state;
                            boolean z = this.$autoSwitchToMinute;
                            int i2 = 0;
                            while (i2 < i) {
                                final int index = i2;
                                int innerValue = TimePickerKt.ExtraHours.get(index);
                                Modifier.Companion companion = Modifier.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer, -1870016769, "CC(remember):TimePicker.kt#9igjgp");
                                boolean invalid$iv = composer.changed(index);
                                Object it$iv = $composer.rememberedValue();
                                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                    Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$2$$ExternalSyntheticLambda0
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return TimePickerKt.C02771.AnonymousClass2.C00521.C00532.invoke$lambda$2$lambda$1$lambda$0(index, (SemanticsPropertyReceiver) obj);
                                        }
                                    };
                                    $composer.updateRememberedValue(value$iv);
                                    it$iv = value$iv;
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null), analogTimePickerState, innerValue, z, composer, 0);
                                i2++;
                                composer = $composer;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer.skipToGroupEnd();
                    }

                    static final Unit invoke$lambda$2$lambda$1$lambda$0(int $index, SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setTraversalIndex($this$semantics, 12.0f + $index);
                        return Unit.INSTANCE;
                    }
                }
            }

            public final void invoke(Composer $composer2, int $changed2) {
                ComposerKt.sourceInformation($composer2, "C1615@63715L1709,1613@63596L1828:TimePicker.kt#uh7d8r");
                if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                    $composer2.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-99063847, $changed2, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous> (TimePicker.kt:1613)");
                }
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(timePickerColors.m3161clockDialContentColorvNxB06k$material3(false))), ComposableLambdaKt.rememberComposableLambda(-596940007, true, new C00521(intList, analogTimePickerState, z), $composer2, 54), $composer2, ProvidedValue.$stable | 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }
    }

    private static final Modifier drawSelector(Modifier $this$drawSelector, final AnalogTimePickerState state, final TimePickerColors colors) {
        return DrawModifierKt.drawWithContent($this$drawSelector, new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TimePickerKt.drawSelector$lambda$61(state, colors, (ContentDrawScope) obj);
            }
        });
    }

    static final Unit drawSelector$lambda$61(AnalogTimePickerState $state, TimePickerColors $colors, ContentDrawScope $this$drawWithContent) {
        float x$iv = $this$drawWithContent.mo432toPx0680j_4(DpOffset.m8211getXD9Ej5fM(getSelectorPos($state)));
        float y$iv = $this$drawWithContent.mo432toPx0680j_4(DpOffset.m8213getYD9Ej5fM(getSelectorPos($state)));
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        float selectorRadius = (($this$drawWithContent.mo432toPx0680j_4(TimePickerTokens.INSTANCE.m4259getClockDialSelectorHandleContainerSizeD9Ej5fM()) / SeparatorZIndex) * $this$drawWithContent.mo426roundToPx0680j_4($state.m2146getCurrentDiameterD9Ej5fM())) / $this$drawWithContent.mo426roundToPx0680j_4(TimePickerTokens.INSTANCE.m4257getClockDialContainerSizeD9Ej5fM());
        long selectorColor = $colors.getSelectorColor();
        DrawScope.m5868drawCircleVaOC9Bg$default($this$drawWithContent, Color.INSTANCE.m5339getBlack0d7_KjU(), selectorRadius, jM5060constructorimpl, 0.0f, null, null, BlendMode.INSTANCE.m5226getClear0nO6VwU(), 56, null);
        $this$drawWithContent.drawContent();
        DrawScope.m5868drawCircleVaOC9Bg$default($this$drawWithContent, selectorColor, selectorRadius, jM5060constructorimpl, 0.0f, null, null, BlendMode.INSTANCE.m5254getXor0nO6VwU(), 56, null);
        float strokeWidth = $this$drawWithContent.mo432toPx0680j_4(TimePickerTokens.INSTANCE.m4260getClockDialSelectorTrackContainerWidthD9Ej5fM());
        float x$iv2 = ((float) Math.cos($state.getCurrentAngle())) * selectorRadius;
        float y$iv2 = ((float) Math.sin($state.getCurrentAngle())) * selectorRadius;
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        long lineLength = Offset.m5072minusMKHz9U(jM5060constructorimpl, Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2)));
        DrawScope.m5873drawLineNGM6Ib0$default($this$drawWithContent, selectorColor, androidx.compose.ui.geometry.SizeKt.m5147getCenteruvyYCjk($this$drawWithContent.mo5887getSizeNHjbRc()), lineLength, strokeWidth, 0, null, 0.0f, null, BlendMode.INSTANCE.m5253getSrcOver0nO6VwU(), 240, null);
        DrawScope.m5868drawCircleVaOC9Bg$default($this$drawWithContent, selectorColor, $this$drawWithContent.mo432toPx0680j_4(TimePickerTokens.INSTANCE.m4258getClockDialSelectorCenterContainerSizeD9Ej5fM()) / SeparatorZIndex, androidx.compose.ui.geometry.SizeKt.m5147getCenteruvyYCjk($this$drawWithContent.mo5887getSizeNHjbRc()), 0.0f, null, null, 0, 120, null);
        DrawScope.m5868drawCircleVaOC9Bg$default($this$drawWithContent, $colors.m3161clockDialContentColorvNxB06k$material3(true), selectorRadius, jM5060constructorimpl, 0.0f, null, null, BlendMode.INSTANCE.m5236getDstOver0nO6VwU(), 56, null);
        return Unit.INSTANCE;
    }

    public static final void ClockText(final Modifier modifier, final AnalogTimePickerState state, final int value, final boolean autoSwitchToMinute, Composer $composer, final int $changed) {
        Density density;
        float maxDist;
        Object value$iv;
        Alignment alignment;
        Modifier modifier2;
        final CoroutineScope scope;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(-206784607);
        ComposerKt.sourceInformation($composer2, "C(ClockText)N(modifier,state,value,autoSwitchToMinute)1728@67678L5,1729@67724L7,1731@67805L40,1732@67870L43,1733@67940L38,1734@67995L24,1736@68057L143,1744@68267L268,1757@68741L215,1765@69135L612,1753@68613L1344:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(value) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-206784607, $dirty2, -1, "androidx.compose.material3.ClockText (TimePicker.kt:1727)");
            }
            TextStyle style = TypographyKt.getValue(TimePickerTokens.INSTANCE.getClockDialLabelTextFont(), $composer2, 6);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density2 = (Density) objConsume;
            float maxDist2 = density2.mo432toPx0680j_4(MaxDistance);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151501687, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m5057boximpl(Offset.INSTANCE.m5084getZeroF1C5BW0()), null, 2, null);
                $composer2.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            }
            final MutableState center$delegate = (MutableState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151499604, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                density = density2;
                maxDist = maxDist2;
                Object value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntOffset.m8269boximpl(IntOffset.INSTANCE.m8289getZeronOccac()), null, 2, null);
                $composer2.updateRememberedValue(value$iv3);
                it$iv2 = value$iv3;
            } else {
                density = density2;
                maxDist = maxDist2;
            }
            final MutableState parentCenter$delegate = (MutableState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151497369, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Rect.INSTANCE.getZero(), null, 2, null);
                $composer2.updateRememberedValue(value$iv4);
                it$iv3 = value$iv4;
            }
            final MutableState boundsInParent$delegate = (MutableState) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, 683737348, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                $composer2.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            CoroutineScope scope2 = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final String contentDescription = m3206numberContentDescriptiondSwYdS4(state.mo2147getSelectionyecRtBI(), state.getIs24hour(), value, $composer2, $dirty2 & 896);
            final Density density3 = density;
            String text = CalendarLocale_jvmKt.toLocalString$default(value, 0, 0, false, null, 15, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151486675, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(state);
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv || it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(TimePickerKt.ClockText$lambda$74$lambda$73(state, density3, boundsInParent$delegate));
                    }
                });
                $composer2.updateRememberedValue(value$iv5);
                it$iv4 = value$iv5;
            }
            final State selected$delegate = (State) it$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, -1151471560, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv5 = $composer2.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.ClockText$lambda$77$lambda$76(parentCenter$delegate, boundsInParent$delegate, center$delegate, (LayoutCoordinates) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv6);
                it$iv5 = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierFocusable$default = FocusableKt.focusable$default(SizeKt.m1115size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(OnGloballyPositionedModifierKt.onGloballyPositioned(modifier, (Function1) it$iv5)), MinimumInteractiveSize), false, null, 3, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151458555, "CC(remember):TimePicker.kt#9igjgp");
            final float maxDist3 = maxDist;
            boolean invalid$iv2 = $composer2.changedInstance(scope2) | $composer2.changedInstance(state) | $composer2.changed(maxDist3) | (($dirty2 & 7168) == 2048) | $composer2.changed(selected$delegate);
            Object it$iv6 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                alignment = center;
                modifier2 = modifierFocusable$default;
                scope = scope2;
                value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.ClockText$lambda$80$lambda$79(scope, state, maxDist3, autoSwitchToMinute, center$delegate, parentCenter$delegate, selected$delegate, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                modifier2 = modifierFocusable$default;
                scope = scope2;
                value$iv = it$iv6;
                alignment = center;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv$iv = SemanticsModifierKt.semantics(modifier2, true, (Function1) value$iv);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(alignment, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -866292798, "C1784@69840L48,1782@69765L186:TimePicker.kt#uh7d8r");
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -2106152649, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changed(contentDescription);
            Object value$iv7 = $composer2.rememberedValue();
            if (invalid$iv3 || value$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.ClockText$lambda$83$lambda$82$lambda$81(contentDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv7);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextKt.m3157TextNvy7gAk(text, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) value$iv7), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, style, $composer2, 0, 0, 131068);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.ClockText$lambda$84(modifier, state, value, autoSwitchToMinute, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final long ClockText$lambda$64(MutableState<Offset> mutableState) {
        MutableState<Offset> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().m5078unboximpl();
    }

    private static final void ClockText$lambda$65(MutableState<Offset> mutableState, long j) {
        mutableState.setValue(Offset.m5057boximpl(j));
    }

    public static final long ClockText$lambda$67(MutableState<IntOffset> mutableState) {
        MutableState<IntOffset> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().m8287unboximpl();
    }

    private static final void ClockText$lambda$68(MutableState<IntOffset> mutableState, long j) {
        mutableState.setValue(IntOffset.m8269boximpl(j));
    }

    private static final Rect ClockText$lambda$70(MutableState<Rect> mutableState) {
        MutableState<Rect> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    private static final boolean ClockText$lambda$75(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final boolean ClockText$lambda$74$lambda$73(AnalogTimePickerState $state, Density $density, MutableState $boundsInParent$delegate) {
        long selectorPos = getSelectorPos($state);
        float x$iv = $density.mo432toPx0680j_4(DpOffset.m8211getXD9Ej5fM(selectorPos));
        float y$iv = $density.mo432toPx0680j_4(DpOffset.m8213getYD9Ej5fM(selectorPos));
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long offset = Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        return ClockText$lambda$70($boundsInParent$delegate).m5094containsk4lQ0M(offset);
    }

    static final Unit ClockText$lambda$77$lambda$76(MutableState $parentCenter$delegate, MutableState $boundsInParent$delegate, MutableState $center$delegate, LayoutCoordinates it) {
        LayoutCoordinates parentCoordinates = it.getParentCoordinates();
        ClockText$lambda$68($parentCenter$delegate, parentCoordinates != null ? IntSizeKt.m8327getCenterozmzZPI(parentCoordinates.mo6791getSizeYbymL2g()) : IntOffset.INSTANCE.m8289getZeronOccac());
        $boundsInParent$delegate.setValue(LayoutCoordinatesKt.boundsInParent(it));
        ClockText$lambda$65($center$delegate, ClockText$lambda$70($boundsInParent$delegate).m5098getCenterF1C5BW0());
        return Unit.INSTANCE;
    }

    static final Unit ClockText$lambda$80$lambda$79(final CoroutineScope $scope, final AnalogTimePickerState $state, final float $maxDist, final boolean $autoSwitchToMinute, final MutableState $center$delegate, final MutableState $parentCenter$delegate, State $selected$delegate, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(TimePickerKt.ClockText$lambda$80$lambda$79$lambda$78($scope, $state, $maxDist, $autoSwitchToMinute, $center$delegate, $parentCenter$delegate));
            }
        }, 1, null);
        SemanticsPropertiesKt.setSelected($this$semantics, ClockText$lambda$75($selected$delegate));
        return Unit.INSTANCE;
    }

    static final boolean ClockText$lambda$80$lambda$79$lambda$78(CoroutineScope $scope, AnalogTimePickerState $state, float $maxDist, boolean $autoSwitchToMinute, MutableState $center$delegate, MutableState $parentCenter$delegate) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new TimePickerKt$ClockText$2$1$1$1($state, $maxDist, $autoSwitchToMinute, $center$delegate, $parentCenter$delegate, null), 3, null);
        return true;
    }

    static final Unit ClockText$lambda$83$lambda$82$lambda$81(String $contentDescription, SemanticsPropertyReceiver $this$clearAndSetSemantics) {
        SemanticsPropertiesKt.setContentDescription($this$clearAndSetSemantics, $contentDescription);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: timeInputOnChange-_K77t-0 */
    public static final void m3208timeInputOnChange_K77t0(int selection, TimePickerState state, TextFieldValue value, TextFieldValue prevValue, int max, Ref<Boolean> ref, Function1<? super TextFieldValue, Unit> function1) {
        int newValue;
        TextFieldValue textFieldValueM7819copy3r_uNRQ$default;
        int i = 0;
        ref.setValue(false);
        if (Intrinsics.areEqual(value.getText(), prevValue.getText())) {
            function1.invoke(value);
            return;
        }
        int i2 = 12;
        if (value.getText().length() == 0) {
            if (TimePickerSelectionMode.m3223equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI())) {
                if (isPm(state) && !state.getIs24hour()) {
                    i = 12;
                }
                state.setHour(i);
            } else {
                state.setMinute(0);
            }
            function1.invoke(TextFieldValue.m7819copy3r_uNRQ$default(value, "", 0L, (TextRange) null, 6, (Object) null));
            return;
        }
        try {
            if (value.getText().length() == 3 && TextRange.m7573getStartimpl(value.getSelection()) == 1) {
                newValue = CharsKt.digitToInt(value.getText().charAt(0));
            } else {
                newValue = Integer.parseInt(value.getText());
            }
            if (newValue <= max) {
                if (TimePickerSelectionMode.m3223equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3227getHouryecRtBI())) {
                    if (newValue != 12 || !isPm(state)) {
                        if (newValue == 12 && !isPm(state) && !state.getIs24hour()) {
                            i2 = 0;
                        } else {
                            if (!isPm(state) || state.getIs24hour()) {
                                i2 = 0;
                            }
                            i2 += newValue;
                        }
                    }
                    state.setHour(i2);
                    if (newValue > 1 && !state.getIs24hour()) {
                        state.mo2149setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3228getMinuteyecRtBI());
                    }
                } else {
                    state.setMinute(newValue);
                }
                if (value.getText().length() > 2) {
                    textFieldValueM7819copy3r_uNRQ$default = TextFieldValue.m7819copy3r_uNRQ$default(value, String.valueOf(value.getText().charAt(0)), 0L, (TextRange) null, 6, (Object) null);
                } else {
                    textFieldValueM7819copy3r_uNRQ$default = value;
                }
                function1.invoke(textFieldValueM7819copy3r_uNRQ$default);
            }
        } catch (NumberFormatException e) {
        } catch (IllegalArgumentException e2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:292:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0439  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x048d  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0505  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x06be  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x06e2  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x06ec  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x076c  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x077d  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0789  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0795  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x07a3  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x07c4  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x07c8  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x07d6  */
    /* JADX WARN: Removed duplicated region for block: B:401:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: TimePickerTextField-1vLObsk */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m3198TimePickerTextField1vLObsk(final androidx.compose.ui.Modifier r114, final androidx.compose.ui.text.input.TextFieldValue r115, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r116, androidx.compose.material3.TimePickerState r117, int r118, androidx.compose.foundation.text.KeyboardOptions r119, androidx.compose.foundation.text.KeyboardActions r120, final androidx.compose.material3.TimePickerColors r121, androidx.compose.runtime.Composer r122, final int r123, final int r124) {
        /*
            Method dump skipped, instruction units count: 2027
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m3198TimePickerTextField1vLObsk(androidx.compose.ui.Modifier, androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.material3.TimePickerState, int, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int, int):void");
    }

    static final Unit TimePickerTextField_1vLObsk$lambda$92$lambda$89$lambda$88$lambda$87(String $contentDescription, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $contentDescription);
        return Unit.INSTANCE;
    }

    public static final void CircularLayout(Modifier modifier, final float radiusToSizeRatio, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Modifier modifier3;
        Modifier.Companion modifier4;
        Composer $composer2 = $composer.startRestartGroup(-1041042571);
        ComposerKt.sourceInformation($composer2, "C(CircularLayout)N(modifier,radiusToSizeRatio,content)1979@76688L1660,1979@76641L1707:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(radiusToSizeRatio) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1041042571, $dirty2, -1, "androidx.compose.material3.CircularLayout (TimePicker.kt:1978)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 285478289, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 112) == 32;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (MeasurePolicy) new TimePickerKt$CircularLayout$1$1(radiusToSizeRatio);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv = (($dirty2 >> 6) & 14) | (($dirty2 << 3) & 112);
            Modifier modifier$iv = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
            Modifier modifier5 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            function2.invoke($composer2, Integer.valueOf(($changed$iv$iv >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.CircularLayout$lambda$96(modifier3, radiusToSizeRatio, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: numberContentDescription-dSwYdS4 */
    public static final String m3206numberContentDescriptiondSwYdS4(int selection, boolean is24Hour, int number, Composer $composer, int $changed) {
        int id;
        ComposerKt.sourceInformationMarkerStart($composer, 194237364, "C(numberContentDescription)N(selection:c#material3.TimePickerSelectionMode,is24Hour,number)2029@78784L21:TimePicker.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(194237364, $changed, -1, "androidx.compose.material3.numberContentDescription (TimePicker.kt:2019)");
        }
        if (TimePickerSelectionMode.m3223equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3228getMinuteyecRtBI())) {
            Strings.Companion companion = Strings.INSTANCE;
            id = Strings.m3454constructorimpl(R.string.m3c_time_picker_minute_suffix);
        } else if (is24Hour) {
            Strings.Companion companion2 = Strings.INSTANCE;
            id = Strings.m3454constructorimpl(R.string.m3c_time_picker_hour_24h_suffix);
        } else {
            Strings.Companion companion3 = Strings.INSTANCE;
            id = Strings.m3454constructorimpl(R.string.m3c_time_picker_hour_suffix);
        }
        String strM3534getStringqBjtwXw = Strings_androidKt.m3534getStringqBjtwXw(id, new Object[]{Integer.valueOf(number)}, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return strM3534getStringqBjtwXw;
    }

    private static final float dist(float x1, float y1, int x2, int y2) {
        float x = x2 - x1;
        float y = y2 - y1;
        return (float) Math.hypot(x, y);
    }

    public static final float atan(float y, float x) {
        float ret = ((float) Math.atan2(y, x)) - 1.5707964f;
        return ret < 0.0f ? FullCircle + ret : ret;
    }

    public static final int getDefaultTimePickerLayoutType(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 435687004, "C(<get-defaultTimePickerLayoutType>)2051@79436L29:TimePicker.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(435687004, $changed, -1, "androidx.compose.material3.<get-defaultTimePickerLayoutType> (TimePicker.kt:2051)");
        }
        int iDefaultTimePickerLayoutType = TimePicker_androidKt.defaultTimePickerLayoutType($composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return iDefaultTimePickerLayoutType;
    }

    static {
        float arg0$iv = Dp.m8150constructorimpl(TypedValues.TYPE_TARGET);
        float other$iv = TimePickerTokens.INSTANCE.m4257getClockDialContainerSizeD9Ej5fM();
        OuterCircleToSizeRatio = arg0$iv / other$iv;
        float arg0$iv2 = Dp.m8150constructorimpl(69);
        float other$iv2 = TimePickerTokens.INSTANCE.m4257getClockDialContainerSizeD9Ej5fM();
        InnerCircleToSizeRatio = arg0$iv2 / other$iv2;
        ClockDisplayBottomMargin = Dp.m8150constructorimpl(36);
        ClockFaceBottomMargin = Dp.m8150constructorimpl(24);
        DisplaySeparatorWidth = Dp.m8150constructorimpl(24);
        SupportLabelTop = Dp.m8150constructorimpl(7);
        TimeInputBottomPadding = Dp.m8150constructorimpl(24);
        MaxDistance = Dp.m8150constructorimpl(74);
        MinimumInteractiveSize = Dp.m8150constructorimpl(48);
        Minutes = IntListKt.intListOf(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);
        Hours = IntListKt.intListOf(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        MutableIntList $this$ExtraHours_u24lambda_u2498 = new MutableIntList(Hours._size);
        IntList this_$iv = Hours;
        int[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            int it = content$iv[i$iv];
            $this$ExtraHours_u24lambda_u2498.add((it % 12) + 12);
        }
        ExtraHours = $this$ExtraHours_u24lambda_u2498;
        PeriodToggleMargin = Dp.m8150constructorimpl(12);
        TimePickerMaxHeight = Dp.m8150constructorimpl(384);
        TimePickerMidHeight = Dp.m8150constructorimpl(330);
        ClockDialMidContainerSize = Dp.m8150constructorimpl(238);
        ClockDialMinContainerSize = Dp.m8150constructorimpl(200);
    }

    public static final float getClockDialMinContainerSize() {
        return ClockDialMinContainerSize;
    }

    private static final Modifier visible(Modifier $this$visible, final boolean visible) {
        return $this$visible.then(new VisibleModifier(visible, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TimePickerKt$visible$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("visible");
                $this$null.getProperties().set("visible", Boolean.valueOf(visible));
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }
}
