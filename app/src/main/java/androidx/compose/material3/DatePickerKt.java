package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.grid.GridCells;
import androidx.compose.foundation.lazy.grid.LazyGridDslKt;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.foundation.lazy.grid.LazyGridStateKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.DatePickerKt;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.CalendarMonth;
import androidx.compose.material3.internal.Icons;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: DatePicker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\u001aw\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001aE\u0010\u0013\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001aO\u0010\u001f\u001a\u00020\u00032\n\u0010 \u001a\u00060!j\u0002`\"2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c¢\u0006\u0004\b#\u0010$\u001a\u0081\u0001\u0010%\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0013\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0013\u0010&\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0001¢\u0006\u0004\b,\u0010-\u001a;\u0010.\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u001a2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u0001012\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0004\b2\u00103\u001a£\u0001\u00104\u001a\u00020\u00012\b\u00105\u001a\u0004\u0018\u00010\u00152\u0006\u00106\u001a\u00020\u00152\u0006\u0010/\u001a\u00020\u001a2#\u00107\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\u0001012!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u0001012\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0003¢\u0006\u0004\b?\u0010@\u001a\u008d\u0001\u0010A\u001a\u00020\u00012\b\u00105\u001a\u0004\u0018\u00010\u00152\u0006\u00106\u001a\u00020\u00152!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\u0001012!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u0001012\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010B\u001aW\u0010C\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0001¢\u0006\u0004\bH\u0010I\u001a\u008d\u0001\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020L2\b\u00105\u001a\u0004\u0018\u00010\u00152!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\u0001012!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u0001012\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010M\u001aI\u0010N\u001a\u00020\u00012\u0006\u0010K\u001a\u00020L2!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u0001012\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u0018H\u0080@¢\u0006\u0002\u0010O\u001a\u001d\u0010P\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010=\u001a\u00020>H\u0001¢\u0006\u0002\u0010Q\u001a\u0082\u0001\u0010R\u001a\u00020\u00012\u0006\u0010S\u001a\u00020T2!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\u0001012\u0006\u0010U\u001a\u00020\u00152\b\u0010V\u001a\u0004\u0018\u00010\u00152\b\u0010W\u001a\u0004\u0018\u00010\u00152\b\u0010X\u001a\u0004\u0018\u00010Y2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\n\u0010 \u001a\u00060!j\u0002`\"H\u0001¢\u0006\u0002\u0010Z\u001a\u0010\u0010[\u001a\u00020\\2\u0006\u0010\u0017\u001a\u00020\u0018H\u0000\u001a7\u0010]\u001a\u0004\u0018\u00010^2\u0006\u0010_\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020\u000f2\u0006\u0010a\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020\u000f2\u0006\u0010c\u001a\u00020\u000fH\u0003¢\u0006\u0002\u0010d\u001ac\u0010e\u001a\u00020\u00012\u0006\u0010f\u001a\u00020^2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010g\u001a\u00020\u000f2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010i\u001a\u00020\u000f2\u0006\u0010j\u001a\u00020\u000f2\u0006\u0010k\u001a\u00020\u000f2\u0006\u0010l\u001a\u00020\u000f2\u0006\u0010m\u001a\u00020^2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010n\u001a`\u0010o\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u00152!\u0010p\u001a\u001d\u0012\u0013\u0012\u00110\\¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(q\u0012\u0004\u0012\u00020\u0001012\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010r\u001aS\u0010s\u001a\u00020\u00012\u0006\u0010f\u001a\u00020^2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010g\u001a\u00020\u000f2\u0006\u0010t\u001a\u00020\u000f2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010j\u001a\u00020\u000f2\u0006\u0010m\u001a\u00020^2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010u\u001ag\u0010v\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010w\u001a\u00020\u000f2\u0006\u0010x\u001a\u00020\u000f2\u0006\u0010y\u001a\u00020\u000f2\u0006\u0010z\u001a\u00020^2\f\u0010{\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010}\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010~\u001aB\u0010\u007f\u001a\u00020\u00012\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0007\u0010\u0080\u0001\u001a\u00020\u000f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0003¢\u0006\u0003\u0010\u0081\u0001\u001aD\u0010\u0082\u0001\u001a\u00020\u00012\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0085\u0001\u001a\u00020^2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010j\u001a\u00020\u000fH\u0003¢\u0006\u0003\u0010\u0086\u0001\"\u001a\u0010\u0087\u0001\u001a\u00020*X\u0080\u0004¢\u0006\r\n\u0003\u0010\u008a\u0001\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u001a\u0010\u008b\u0001\u001a\u00020*X\u0080\u0004¢\u0006\r\n\u0003\u0010\u008a\u0001\u001a\u0006\b\u008c\u0001\u0010\u0089\u0001\"\u001a\u0010\u008d\u0001\u001a\u00020*X\u0080\u0004¢\u0006\r\n\u0003\u0010\u008a\u0001\u001a\u0006\b\u008e\u0001\u0010\u0089\u0001\"\u0018\u0010\u008f\u0001\u001a\u00030\u0090\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001\"\u0010\u0010\u0093\u0001\u001a\u00030\u0090\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0094\u0001\u001a\u00030\u0090\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0012\u0010\u0095\u0001\u001a\u00020*X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008a\u0001\"\u000f\u0010\u0096\u0001\u001a\u00020\\X\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0097\u0001\u001a\u00020\\X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0098\u0001²\u0006\n\u0010y\u001a\u00020\u000fX\u008a\u008e\u0002"}, d2 = {"DatePicker", "", "state", "Landroidx/compose/material3/DatePickerState;", "modifier", "Landroidx/compose/ui/Modifier;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "colors", "Landroidx/compose/material3/DatePickerColors;", "title", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "headline", "showModeToggle", "", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "(Landroidx/compose/material3/DatePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/DatePickerColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "rememberDatePickerState", "initialSelectedDateMillis", "", "initialDisplayedMonthMillis", "yearRange", "Lkotlin/ranges/IntRange;", "initialDisplayMode", "Landroidx/compose/material3/DisplayMode;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "rememberDatePickerState-EU0dCGE", "(Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DatePickerState;", "DatePickerState", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DatePickerState-sHin3Bw", "(Ljava/util/Locale;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DatePickerState;", "DateEntryContainer", "modeToggleButton", "headlineTextStyle", "Landroidx/compose/ui/text/TextStyle;", "headerMinHeight", "Landroidx/compose/ui/unit/Dp;", "content", "DateEntryContainer-au3_HiA", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/text/TextStyle;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "DisplayModeToggleButton", "displayMode", "onDisplayModeChange", "Lkotlin/Function1;", "DisplayModeToggleButton-iUJLfQg", "(Landroidx/compose/ui/Modifier;ILkotlin/jvm/functions/Function1;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "SwitchableDateEntryContent", "selectedDateMillis", "displayedMonthMillis", "onDateSelectionChange", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dateInMillis", "onDisplayedMonthChange", "monthInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "SwitchableDateEntryContent-KaiTk9E", "(Ljava/lang/Long;JILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "DatePickerContent", "(Ljava/lang/Long;JLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "DatePickerHeader", "titleContentColor", "Landroidx/compose/ui/graphics/Color;", "headlineContentColor", "minHeight", "DatePickerHeader-pc5RIQQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;JJFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "HorizontalMonthsList", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "(Landroidx/compose/foundation/lazy/LazyListState;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "updateDisplayedMonth", "(Landroidx/compose/foundation/lazy/LazyListState;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "WeekDays", "(Landroidx/compose/material3/DatePickerColors;Landroidx/compose/material3/internal/CalendarModel;Landroidx/compose/runtime/Composer;I)V", "Month", "month", "Landroidx/compose/material3/internal/CalendarMonth;", "todayMillis", "startDateMillis", "endDateMillis", "rangeSelectionInfo", "Landroidx/compose/material3/SelectedRangeInfo;", "(Landroidx/compose/material3/internal/CalendarMonth;Lkotlin/jvm/functions/Function1;JLjava/lang/Long;Ljava/lang/Long;Landroidx/compose/material3/SelectedRangeInfo;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Ljava/util/Locale;Landroidx/compose/runtime/Composer;I)V", "numberOfMonthsInRange", "", "dayContentDescription", "", "rangeSelectionEnabled", "isToday", "isStartDate", "isEndDate", "isInRange", "(ZZZZZLandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "Day", "text", "selected", "onClick", "animateChecked", "enabled", "today", "inRange", "description", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;ZZZZLjava/lang/String;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "YearPicker", "onYearSelected", "year", "(Landroidx/compose/ui/Modifier;JLkotlin/jvm/functions/Function1;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "Year", "currentYear", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;ZZLkotlin/jvm/functions/Function0;ZLjava/lang/String;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "MonthsNavigation", "nextAvailable", "previousAvailable", "yearPickerVisible", "yearPickerText", "onNextClicked", "onPreviousClicked", "onYearPickerButtonClicked", "(Landroidx/compose/ui/Modifier;ZZZLjava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "YearPickerMenuButton", "expanded", "(Lkotlin/jvm/functions/Function0;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "IconButtonWithTooltip", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "contentDescription", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "RecommendedSizeForAccessibility", "getRecommendedSizeForAccessibility", "()F", "F", "MonthYearHeight", "getMonthYearHeight", "DatePickerHorizontalPadding", "getDatePickerHorizontalPadding", "DatePickerModeTogglePadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getDatePickerModeTogglePadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "DatePickerTitlePadding", "DatePickerHeadlinePadding", "YearsVerticalPadding", "MaxCalendarRows", "YearsInRow", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DatePickerKt {
    private static final int MaxCalendarRows = 6;
    private static final int YearsInRow = 3;
    private static final float RecommendedSizeForAccessibility = Dp.m8150constructorimpl(48);
    private static final float MonthYearHeight = Dp.m8150constructorimpl(56);
    private static final float DatePickerHorizontalPadding = Dp.m8150constructorimpl(12);
    private static final PaddingValues DatePickerModeTogglePadding = PaddingKt.m1045PaddingValuesa9UjIt4$default(0.0f, 0.0f, Dp.m8150constructorimpl(12), Dp.m8150constructorimpl(12), 3, null);
    private static final PaddingValues DatePickerTitlePadding = PaddingKt.m1045PaddingValuesa9UjIt4$default(Dp.m8150constructorimpl(24), Dp.m8150constructorimpl(16), Dp.m8150constructorimpl(12), 0.0f, 8, null);
    private static final PaddingValues DatePickerHeadlinePadding = PaddingKt.m1045PaddingValuesa9UjIt4$default(Dp.m8150constructorimpl(24), 0.0f, Dp.m8150constructorimpl(12), Dp.m8150constructorimpl(12), 2, null);
    private static final float YearsVerticalPadding = Dp.m8150constructorimpl(16);

    static final Unit DateEntryContainer_au3_HiA$lambda$10(Modifier modifier, Function2 function2, Function2 function22, Function2 function23, DatePickerColors datePickerColors, TextStyle textStyle, float f, Function2 function24, int i, Composer composer, int i2) {
        m2432DateEntryContainerau3_HiA(modifier, function2, function22, function23, datePickerColors, textStyle, f, function24, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DatePicker$lambda$3(DatePickerState datePickerState, Modifier modifier, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, Function2 function2, Function2 function22, boolean z, FocusRequester focusRequester, int i, int i2, Composer composer, int i3) {
        DatePicker(datePickerState, modifier, datePickerFormatter, datePickerColors, function2, function22, z, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DatePickerContent$lambda$37(Long l, long j, Function1 function1, Function1 function12, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        DatePickerContent(l, j, function1, function12, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DatePickerHeader_pc5RIQQ$lambda$39(Modifier modifier, Function2 function2, long j, long j2, float f, Function2 function22, int i, Composer composer, int i2) {
        m2433DatePickerHeaderpc5RIQQ(modifier, function2, j, j2, f, function22, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Day$lambda$62(String str, Modifier modifier, boolean z, Function0 function0, boolean z2, boolean z3, boolean z4, boolean z5, String str2, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        Day(str, modifier, z, function0, z2, z3, z4, z5, str2, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DisplayModeToggleButton_iUJLfQg$lambda$11(Modifier modifier, int i, Function1 function1, DatePickerColors datePickerColors, int i2, Composer composer, int i3) {
        m2436DisplayModeToggleButtoniUJLfQg(modifier, i, function1, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    static final Unit HorizontalMonthsList$lambda$42(LazyListState lazyListState, Long l, Function1 function1, Function1 function12, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        HorizontalMonthsList(lazyListState, l, function1, function12, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit IconButtonWithTooltip$lambda$71(Function0 function0, ImageVector imageVector, String str, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        IconButtonWithTooltip(function0, imageVector, str, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Month$lambda$59(CalendarMonth calendarMonth, Function1 function1, long j, Long l, Long l2, SelectedRangeInfo selectedRangeInfo, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Locale locale, int i, Composer composer, int i2) {
        Month(calendarMonth, function1, j, l, l2, selectedRangeInfo, datePickerFormatter, selectableDates, datePickerColors, locale, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MonthsNavigation$lambda$69(Modifier modifier, boolean z, boolean z2, boolean z3, String str, Function0 function0, Function0 function02, Function0 function03, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        MonthsNavigation(modifier, z, z2, z3, str, function0, function02, function03, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SwitchableDateEntryContent_KaiTk9E$lambda$22(Long l, long j, int i, Function1 function1, Function1 function12, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, FocusRequester focusRequester, int i2, int i3, Composer composer, int i4) throws Throwable {
        m2437SwitchableDateEntryContentKaiTk9E(l, j, i, function1, function12, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    static final Unit WeekDays$lambda$49(DatePickerColors datePickerColors, CalendarModel calendarModel, int i, Composer composer, int i2) {
        WeekDays(datePickerColors, calendarModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Year$lambda$67(String str, Modifier modifier, boolean z, boolean z2, Function0 function0, boolean z3, String str2, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        Year(str, modifier, z, z2, function0, z3, str2, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit YearPicker$lambda$63(Modifier modifier, long j, Function1 function1, SelectableDates selectableDates, CalendarModel calendarModel, IntRange intRange, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        YearPicker(modifier, j, function1, selectableDates, calendarModel, intRange, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit YearPickerMenuButton$lambda$70(Function0 function0, boolean z, Modifier modifier, Function2 function2, int i, int i2, Composer composer, int i3) {
        YearPickerMenuButton(function0, z, modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void DatePicker(final androidx.compose.material3.DatePickerState r30, androidx.compose.ui.Modifier r31, androidx.compose.material3.DatePickerFormatter r32, androidx.compose.material3.DatePickerColors r33, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r34, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r35, boolean r36, androidx.compose.ui.focus.FocusRequester r37, androidx.compose.runtime.Composer r38, final int r39, final int r40) {
        /*
            Method dump skipped, instruction units count: 834
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.DatePicker(androidx.compose.material3.DatePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.DatePickerFormatter, androidx.compose.material3.DatePickerColors, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.ui.focus.FocusRequester, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePicker$5, reason: invalid class name */
    /* JADX INFO: compiled from: DatePicker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass5 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerState $state;

        AnonymousClass5(DatePickerState datePickerState, DatePickerColors datePickerColors) {
            this.$state = datePickerState;
            this.$colors = datePickerColors;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C209@9650L50,206@9440L324:DatePicker.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1483431603, $changed, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:206)");
            }
            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
            int iMo2441getDisplayModejFl4v0 = this.$state.mo2441getDisplayModejFl4v0();
            ComposerKt.sourceInformationMarkerStart($composer, 351379263, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$state);
            final DatePickerState datePickerState = this.$state;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DatePickerKt$DatePicker$5$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DatePickerKt.AnonymousClass5.invoke$lambda$1$lambda$0(datePickerState, (DisplayMode) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            DatePickerKt.m2436DisplayModeToggleButtoniUJLfQg(modifierPadding, iMo2441getDisplayModejFl4v0, (Function1) it$iv, this.$colors, $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        static final Unit invoke$lambda$1$lambda$0(DatePickerState $state, DisplayMode displayMode) {
            $state.mo2442setDisplayModevCnGnXg(displayMode.getValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePicker$6, reason: invalid class name */
    /* JADX INFO: compiled from: DatePicker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass6 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ FocusRequester $focusRequester;
        final /* synthetic */ DatePickerState $state;

        AnonymousClass6(DatePickerState datePickerState, CalendarModel calendarModel, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, FocusRequester focusRequester) {
            this.$state = datePickerState;
            this.$calendarModel = calendarModel;
            this.$dateFormatter = datePickerFormatter;
            this.$colors = datePickerColors;
            this.$focusRequester = focusRequester;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) throws Throwable {
            ComposerKt.sourceInformation($composer, "C224@10259L59,225@10357L91,220@10028L685:DatePicker.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1346903698, $changed, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:220)");
            }
            Long selectedDateMillis = this.$state.getSelectedDateMillis();
            long displayedMonthMillis = this.$state.getDisplayedMonthMillis();
            int iMo2441getDisplayModejFl4v0 = this.$state.mo2441getDisplayModejFl4v0();
            ComposerKt.sourceInformationMarkerStart($composer, -1589289911, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$state);
            final DatePickerState datePickerState = this.$state;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DatePickerKt$DatePicker$6$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DatePickerKt.AnonymousClass6.invoke$lambda$1$lambda$0(datePickerState, (Long) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            Function1 function1 = (Function1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1589286743, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed(this.$state);
            final DatePickerState datePickerState2 = this.$state;
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.DatePickerKt$DatePicker$6$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DatePickerKt.AnonymousClass6.invoke$lambda$3$lambda$2(datePickerState2, ((Long) obj).longValue());
                    }
                };
                $composer.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            DatePickerKt.m2437SwitchableDateEntryContentKaiTk9E(selectedDateMillis, displayedMonthMillis, iMo2441getDisplayModejFl4v0, function1, (Function1) it$iv2, this.$calendarModel, this.$state.getYearRange(), this.$dateFormatter, this.$state.getSelectableDates(), this.$colors, this.$focusRequester, $composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        static final Unit invoke$lambda$1$lambda$0(DatePickerState $state, Long dateInMillis) {
            $state.setSelectedDateMillis(dateInMillis);
            return Unit.INSTANCE;
        }

        static final Unit invoke$lambda$3$lambda$2(DatePickerState $state, long monthInMillis) {
            $state.setDisplayedMonthMillis(monthInMillis);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: rememberDatePickerState-EU0dCGE, reason: not valid java name */
    public static final DatePickerState m2439rememberDatePickerStateEU0dCGE(Long initialSelectedDateMillis, Long initialDisplayedMonthMillis, IntRange yearRange, int initialDisplayMode, SelectableDates selectableDates, Composer $composer, int $changed, int i) {
        final Long initialSelectedDateMillis2;
        final Long initialDisplayedMonthMillis2;
        final IntRange yearRange2;
        final int initialDisplayMode2;
        final SelectableDates selectableDates2;
        ComposerKt.sourceInformationMarkerStart($composer, 2065763010, "C(rememberDatePickerState)N(initialSelectedDateMillis,initialDisplayedMonthMillis,yearRange,initialDisplayMode:c#material3.DisplayMode,selectableDates)374@15968L15,375@16072L384,375@15995L461:DatePicker.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialSelectedDateMillis2 = null;
        } else {
            initialSelectedDateMillis2 = initialSelectedDateMillis;
        }
        if ((i & 2) == 0) {
            initialDisplayedMonthMillis2 = initialDisplayedMonthMillis;
        } else {
            initialDisplayedMonthMillis2 = initialSelectedDateMillis2;
        }
        if ((i & 4) == 0) {
            yearRange2 = yearRange;
        } else {
            yearRange2 = DatePickerDefaults.INSTANCE.getYearRange();
        }
        if ((i & 8) == 0) {
            initialDisplayMode2 = initialDisplayMode;
        } else {
            initialDisplayMode2 = DisplayMode.INSTANCE.m2481getPickerjFl4v0();
        }
        if ((i & 16) == 0) {
            selectableDates2 = selectableDates;
        } else {
            selectableDates2 = DatePickerDefaults.INSTANCE.getAllDates();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2065763010, $changed, -1, "androidx.compose.material3.rememberDatePickerState (DatePicker.kt:373)");
        }
        final Locale locale = CalendarLocale_androidKt.defaultLocale($composer, 0);
        Object[] objArr = new Object[0];
        Saver<DatePickerStateImpl, Object> Saver = DatePickerStateImpl.INSTANCE.Saver(selectableDates2, locale);
        ComposerKt.sourceInformationMarkerStart($composer, 923379074, "CC(remember):DatePicker.kt#9igjgp");
        boolean z = true;
        boolean zChangedInstance = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialSelectedDateMillis2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialDisplayedMonthMillis2)) || ($changed & 48) == 32) | $composer.changedInstance(yearRange2) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(initialDisplayMode2)) || ($changed & 3072) == 2048);
        if ((((57344 & $changed) ^ 24576) <= 16384 || !$composer.changed(selectableDates2)) && ($changed & 24576) != 16384) {
            z = false;
        }
        boolean invalid$iv = $composer.changedInstance(locale) | zChangedInstance | z;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DatePickerKt.rememberDatePickerState_EU0dCGE$lambda$5$lambda$4(initialSelectedDateMillis2, initialDisplayedMonthMillis2, yearRange2, initialDisplayMode2, selectableDates2, locale);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object objM4704rememberSaveable = RememberSaveableKt.m4704rememberSaveable(objArr, Saver, (Function0<? extends Object>) it$iv, $composer, 0);
        DatePickerStateImpl $this$rememberDatePickerState_EU0dCGE_u24lambda_u246 = (DatePickerStateImpl) objM4704rememberSaveable;
        $this$rememberDatePickerState_EU0dCGE_u24lambda_u246.setSelectableDates(selectableDates2);
        DatePickerStateImpl datePickerStateImpl = (DatePickerStateImpl) objM4704rememberSaveable;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return datePickerStateImpl;
    }

    static final DatePickerStateImpl rememberDatePickerState_EU0dCGE$lambda$5$lambda$4(Long $initialSelectedDateMillis, Long $initialDisplayedMonthMillis, IntRange $yearRange, int $initialDisplayMode, SelectableDates $selectableDates, Locale $locale) {
        return new DatePickerStateImpl($initialSelectedDateMillis, $initialDisplayedMonthMillis, $yearRange, $initialDisplayMode, $selectableDates, $locale, null);
    }

    /* JADX INFO: renamed from: DatePickerState-sHin3Bw$default, reason: not valid java name */
    public static /* synthetic */ DatePickerState m2435DatePickerStatesHin3Bw$default(Locale locale, Long l, Long l2, IntRange intRange, int i, SelectableDates selectableDates, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            l = null;
        }
        if ((i2 & 4) != 0) {
            l2 = l;
        }
        if ((i2 & 8) != 0) {
            intRange = DatePickerDefaults.INSTANCE.getYearRange();
        }
        if ((i2 & 16) != 0) {
            i = DisplayMode.INSTANCE.m2481getPickerjFl4v0();
        }
        return m2434DatePickerStatesHin3Bw(locale, l, l2, intRange, i, (i2 & 32) != 0 ? DatePickerDefaults.INSTANCE.getAllDates() : selectableDates);
    }

    /* JADX INFO: renamed from: DatePickerState-sHin3Bw, reason: not valid java name */
    public static final DatePickerState m2434DatePickerStatesHin3Bw(Locale locale, Long initialSelectedDateMillis, Long initialDisplayedMonthMillis, IntRange yearRange, int initialDisplayMode, SelectableDates selectableDates) {
        return new DatePickerStateImpl(initialSelectedDateMillis, initialDisplayedMonthMillis, yearRange, initialDisplayMode, selectableDates, locale, null);
    }

    /* JADX INFO: renamed from: DateEntryContainer-au3_HiA, reason: not valid java name */
    public static final void m2432DateEntryContainerau3_HiA(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final DatePickerColors colors, final TextStyle headlineTextStyle, final float headerMinHeight, final Function2<? super Composer, ? super Integer, Unit> function24, Composer $composer, final int $changed) {
        Modifier modifier2;
        DatePickerColors datePickerColors;
        TextStyle textStyle;
        float f;
        int $dirty;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(1539132883);
        ComposerKt.sourceInformation($composer2, "C(DateEntryContainer)N(modifier,title,headline,modeToggleButton,colors,headlineTextStyle,headerMinHeight:c#ui.unit.Dp,content)1356@63459L236,1352@63311L1910:DatePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changedInstance(function22) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changedInstance(function23) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            datePickerColors = colors;
            $dirty2 |= $composer2.changed(datePickerColors) ? 16384 : 8192;
        } else {
            datePickerColors = colors;
        }
        if ((196608 & $changed) == 0) {
            textStyle = headlineTextStyle;
            $dirty2 |= $composer2.changed(textStyle) ? 131072 : 65536;
        } else {
            textStyle = headlineTextStyle;
        }
        if ((1572864 & $changed) == 0) {
            f = headerMinHeight;
            $dirty2 |= $composer2.changed(f) ? 1048576 : 524288;
        } else {
            f = headerMinHeight;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function24) ? 8388608 : 4194304;
        }
        if (!$composer2.shouldExecute((4793491 & $dirty2) != 4793490, $dirty2 & 1)) {
            $dirty = $dirty2;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1539132883, $dirty2, -1, "androidx.compose.material3.DateEntryContainer (DatePicker.kt:1351)");
            }
            $dirty = $dirty2;
            Modifier modifierM1119sizeInqDBjuR0$default = SizeKt.m1119sizeInqDBjuR0$default(modifier2, DatePickerModalTokens.INSTANCE.m3759getContainerWidthD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1637966303, "CC(remember):DatePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DatePickerKt.DateEntryContainer_au3_HiA$lambda$8$lambda$7((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv = BackgroundKt.m286backgroundbw27NRU$default(SemanticsModifierKt.semantics$default(modifierM1119sizeInqDBjuR0$default, false, (Function1) it$iv, 1, null), datePickerColors.getContainerColor(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
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
            ComposerKt.sourceInformationMarkerStart($composer2, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i2 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1831145178, "C1370@64014L1183,1364@63763L1434,1396@65206L9:DatePicker.kt#uh7d8r");
            final TextStyle textStyle2 = textStyle;
            final DatePickerColors datePickerColors2 = datePickerColors;
            m2433DatePickerHeaderpc5RIQQ(Modifier.INSTANCE, function2, datePickerColors.getTitleContentColor(), datePickerColors.getHeadlineContentColor(), f, ComposableLambdaKt.rememberComposableLambda(-1658370654, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$2$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function02;
                    Arrangement.HorizontalOrVertical horizontalArrangement;
                    Function0<ComposeUiNode> function03;
                    Composer $composer4;
                    ComposerKt.sourceInformation($composer3, "C1371@64028L1159:DatePicker.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1658370654, $changed2, -1, "androidx.compose.material3.DateEntryContainer.<anonymous>.<anonymous> (DatePicker.kt:1371)");
                    }
                    Modifier modifier$iv2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    final Function2<Composer, Integer, Unit> function25 = function22;
                    Function2<Composer, Integer, Unit> function26 = function23;
                    Function2<Composer, Integer, Unit> function27 = function2;
                    DatePickerColors datePickerColors3 = datePickerColors2;
                    TextStyle textStyle3 = textStyle2;
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
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
                        function02 = constructor2;
                        $composer3.createNode(function02);
                    } else {
                        function02 = constructor2;
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
                    ComposerKt.sourceInformationMarkerStart($composer3, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    int i4 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -251319653, "C1378@64389L521:DatePicker.kt#uh7d8r");
                    if (function25 == null || function26 == null) {
                        horizontalArrangement = function25 != null ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getEnd();
                    } else {
                        horizontalArrangement = Arrangement.INSTANCE.getSpaceBetween();
                    }
                    Modifier modifier$iv3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement, verticalAlignment$iv, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv3 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function03 = constructor3;
                        $composer3.createNode(function03);
                    } else {
                        function03 = constructor3;
                        $composer3.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4433constructorimpl($composer3);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                    }
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    int i6 = ((390 >> 6) & 112) | 6;
                    final RowScope $this$invoke_u24lambda_u241_u24lambda_u240 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -516047984, "C:DatePicker.kt#uh7d8r");
                    if (function25 != null) {
                        $composer3.startReplaceGroup(-516028300);
                        ComposerKt.sourceInformation($composer3, "1384@64717L106,1384@64673L150");
                        TextKt.ProvideTextStyle(textStyle3, ComposableLambdaKt.rememberComposableLambda(-738208900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$2$1$1$1$1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer5, int $changed3) {
                                Function0<ComposeUiNode> function04;
                                ComposerKt.sourceInformation($composer5, "C1385@64747L50:DatePicker.kt#uh7d8r");
                                if (!$composer5.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-738208900, $changed3, -1, "androidx.compose.material3.DateEntryContainer.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1385)");
                                }
                                Modifier modifier$iv4 = RowScope.weight$default($this$invoke_u24lambda_u241_u24lambda_u240, Modifier.INSTANCE, 1.0f, false, 2, null);
                                Function2<Composer, Integer, Unit> function28 = function25;
                                ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                int $changed$iv$iv4 = (0 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv4 = $composer5.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer5, modifier$iv4);
                                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                if (!($composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer5.startReusableNode();
                                if ($composer5.getInserting()) {
                                    function04 = constructor4;
                                    $composer5.createNode(function04);
                                } else {
                                    function04 = constructor4;
                                    $composer5.useNode();
                                }
                                Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4433constructorimpl($composer5);
                                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                                }
                                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                                int i7 = ($changed$iv$iv$iv4 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                int i8 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, -1330662525, "C1385@64785L10:DatePicker.kt#uh7d8r");
                                function28.invoke($composer5, 0);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer3, 54), $composer3, 48);
                        $composer3.endReplaceGroup();
                    } else {
                        $composer3.startReplaceGroup(-515838022);
                        $composer3.endReplaceGroup();
                    }
                    if (function26 == null) {
                        $composer3.startReplaceGroup(-515799087);
                    } else {
                        $composer3.startReplaceGroup(260455984);
                        ComposerKt.sourceInformation($composer3, "1388@64884L8");
                        function26.invoke($composer3, 0);
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (function27 != null || function25 != null || function26 != null) {
                        $composer3.startReplaceGroup(-250360576);
                        ComposerKt.sourceInformation($composer3, "1392@65109L46");
                        DividerKt.m2484HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors3.getDividerColor(), $composer3, 0, 3);
                        $composer4 = $composer3;
                        $composer4.endReplaceGroup();
                    } else {
                        $composer3.startReplaceGroup(-250277930);
                        $composer3.endReplaceGroup();
                        $composer4 = $composer3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ($dirty & 112) | 196614 | (($dirty >> 6) & 57344));
            function24.invoke($composer2, Integer.valueOf(($dirty >> 21) & 14));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.DateEntryContainer_au3_HiA$lambda$10(modifier, function2, function22, function23, colors, headlineTextStyle, headerMinHeight, function24, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit DateEntryContainer_au3_HiA$lambda$8$lambda$7(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContainer($this$semantics, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DisplayModeToggleButton-iUJLfQg, reason: not valid java name */
    public static final void m2436DisplayModeToggleButtoniUJLfQg(final Modifier modifier, final int displayMode, final Function1<? super DisplayMode, Unit> function1, final DatePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1461252485);
        ComposerKt.sourceInformation($composer2, "C(DisplayModeToggleButton)N(modifier,displayMode:c#material3.DisplayMode,onDisplayModeChange,colors)1407@65496L658,1407@65415L739:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(displayMode) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1461252485, $dirty, -1, "androidx.compose.material3.DisplayModeToggleButton (DatePicker.kt:1406)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(colors.getHeadlineContentColor())), ComposableLambdaKt.rememberComposableLambda(-1734512197, true, new DatePickerKt$DisplayModeToggleButton$1(displayMode, function1, modifier), $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.DisplayModeToggleButton_iUJLfQg$lambda$11(modifier, displayMode, function1, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchableDateEntryContent-KaiTk9E, reason: not valid java name */
    public static final void m2437SwitchableDateEntryContentKaiTk9E(final Long selectedDateMillis, final long displayedMonthMillis, int displayMode, final Function1<? super Long, Unit> function1, final Function1<? super Long, Unit> function12, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, final FocusRequester focusRequester, Composer $composer, final int $changed, final int $changed1) throws Throwable {
        int i;
        CalendarModel calendarModel2;
        IntRange intRange;
        Composer $composer2;
        Object it$iv;
        Composer $composer3 = $composer.startRestartGroup(-2053685029);
        ComposerKt.sourceInformation($composer3, "C(SwitchableDateEntryContent)N(selectedDateMillis,displayedMonthMillis,displayMode:c#material3.DisplayMode,onDateSelectionChange,onDisplayedMonthChange,calendarModel,yearRange,dateFormatter,selectableDates,colors,focusRequester)1446@66942L7,1449@67152L7,1451@67264L7,1453@67385L7,1455@67503L7,1459@67617L216,1465@67860L1708,1497@69626L1136,1456@67515L3247:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(selectedDateMillis) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(displayedMonthMillis) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            i = displayMode;
            $dirty |= $composer3.changed(i) ? 256 : 128;
        } else {
            i = displayMode;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function12) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            calendarModel2 = calendarModel;
            $dirty |= $composer3.changedInstance(calendarModel2) ? 131072 : 65536;
        } else {
            calendarModel2 = calendarModel;
        }
        if ((1572864 & $changed) == 0) {
            intRange = yearRange;
            $dirty |= $composer3.changedInstance(intRange) ? 1048576 : 524288;
        } else {
            intRange = yearRange;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= ($changed & 16777216) == 0 ? $composer3.changed(dateFormatter) : $composer3.changedInstance(dateFormatter) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(selectableDates) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changed(colors) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(focusRequester) ? 4 : 2;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2053685029, $dirty, $dirty1, "androidx.compose.material3.SwitchableDateEntryContent (DatePicker.kt:1443)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$SwitchableDateEntryContent_KaiTk9E_u24lambda_u2412 = (Density) objConsume;
            final int parallaxTarget = -$this$SwitchableDateEntryContent_KaiTk9E_u24lambda_u2412.mo426roundToPx0680j_4(Dp.m8150constructorimpl(48));
            final FiniteAnimationSpec effectsInAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer3, 6);
            final FiniteAnimationSpec effectsOutAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer3, 6);
            final FiniteAnimationSpec spatialInOutAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer3, 6);
            final FiniteAnimationSpec spatialSizeAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer3, 6);
            DisplayMode displayModeM2473boximpl = DisplayMode.m2473boximpl(i);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093685971, "CC(remember):DatePicker.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DatePickerKt.SwitchableDateEntryContent_KaiTk9E$lambda$14$lambda$13((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                it$iv = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 2093695239, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = $composer3.changedInstance(spatialInOutAnimationSpec) | $composer3.changedInstance(effectsInAnimationSpec) | $composer3.changedInstance(effectsOutAnimationSpec) | $composer3.changed(parallaxTarget) | $composer3.changedInstance(spatialSizeAnimationSpec);
            Object it$iv3 = $composer3.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DatePickerKt.SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20(spatialInOutAnimationSpec, effectsInAnimationSpec, effectsOutAnimationSpec, parallaxTarget, spatialSizeAnimationSpec, (AnimatedContentTransitionScope) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv3 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final CalendarModel calendarModel3 = calendarModel2;
            final IntRange intRange2 = intRange;
            AnimatedContentKt.AnimatedContent(displayModeM2473boximpl, modifierSemantics$default, (Function1) it$iv3, null, "DatePickerDisplayModeAnimation", null, ComposableLambdaKt.rememberComposableLambda(1838500091, true, new Function4<AnimatedContentScope, DisplayMode, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$3
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, DisplayMode displayMode2, Composer composer, Integer num) {
                    m2440invokefYndouo(animatedContentScope, displayMode2.getValue(), composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-fYndouo, reason: not valid java name */
                public final void m2440invokefYndouo(AnimatedContentScope $this$AnimatedContent, int mode, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "CN(mode:c#material3.DisplayMode):DatePicker.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1838500091, $changed2, -1, "androidx.compose.material3.SwitchableDateEntryContent.<anonymous> (DatePicker.kt:1498)");
                    }
                    if (DisplayMode.m2476equalsimpl0(mode, DisplayMode.INSTANCE.m2481getPickerjFl4v0())) {
                        $composer4.startReplaceGroup(1567031954);
                        ComposerKt.sourceInformation($composer4, "1500@69708L535");
                        DatePickerKt.DatePickerContent(selectedDateMillis, displayedMonthMillis, function1, function12, calendarModel3, intRange2, dateFormatter, selectableDates, colors, $composer4, 0);
                        $composer4.endReplaceGroup();
                    } else if (DisplayMode.m2476equalsimpl0(mode, DisplayMode.INSTANCE.m2480getInputjFl4v0())) {
                        $composer4.startReplaceGroup(1567050592);
                        ComposerKt.sourceInformation($composer4, "1512@70293L453");
                        DateInputKt.DateInputContent(selectedDateMillis, function1, calendarModel3, intRange2, dateFormatter, selectableDates, colors, focusRequester, $composer4, 0);
                        $composer4.endReplaceGroup();
                    } else {
                        $composer4.startReplaceGroup(1334373351);
                        $composer4.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer3, (($dirty2 >> 6) & 14) | 1597440, 40);
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
            final int i2 = i;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.SwitchableDateEntryContent_KaiTk9E$lambda$22(selectedDateMillis, displayedMonthMillis, i2, function1, function12, calendarModel, yearRange, dateFormatter, selectableDates, colors, focusRequester, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit SwitchableDateEntryContent_KaiTk9E$lambda$14$lambda$13(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContainer($this$semantics, true);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final ContentTransform SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20(FiniteAnimationSpec $spatialInOutAnimationSpec, FiniteAnimationSpec $effectsInAnimationSpec, FiniteAnimationSpec $effectsOutAnimationSpec, final int $parallaxTarget, final FiniteAnimationSpec $spatialSizeAnimationSpec, AnimatedContentTransitionScope $this$AnimatedContent) {
        ContentTransform contentTransform;
        if (DisplayMode.m2476equalsimpl0(((DisplayMode) $this$AnimatedContent.getTargetState()).getValue(), DisplayMode.INSTANCE.m2480getInputjFl4v0())) {
            contentTransform = AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInVertically($spatialInOutAnimationSpec, new Function1() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(DatePickerKt.SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$15(((Integer) obj).intValue()));
                }
            }).plus(EnterExitTransitionKt.fadeIn$default($effectsInAnimationSpec, 0.0f, 2, null)), EnterExitTransitionKt.fadeOut$default($effectsOutAnimationSpec, 0.0f, 2, null).plus(EnterExitTransitionKt.slideOutVertically($spatialInOutAnimationSpec, new Function1() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(DatePickerKt.SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$16($parallaxTarget, ((Integer) obj).intValue()));
                }
            })));
        } else {
            contentTransform = AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInVertically($spatialInOutAnimationSpec, new Function1() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(DatePickerKt.SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$17($parallaxTarget, ((Integer) obj).intValue()));
                }
            }).plus(EnterExitTransitionKt.fadeIn$default($effectsInAnimationSpec, 0.0f, 2, null)), EnterExitTransitionKt.slideOutVertically($spatialInOutAnimationSpec, new Function1() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(DatePickerKt.SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$18(((Integer) obj).intValue()));
                }
            }).plus(EnterExitTransitionKt.fadeOut$default($effectsOutAnimationSpec, 0.0f, 2, null)));
        }
        return $this$AnimatedContent.using(contentTransform, AnimatedContentKt.SizeTransform(true, new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return DatePickerKt.SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$19($spatialSizeAnimationSpec, (IntSize) obj, (IntSize) obj2);
            }
        }));
    }

    static final int SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$15(int height) {
        return height;
    }

    static final int SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$16(int $parallaxTarget, int i) {
        return $parallaxTarget;
    }

    static final int SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$17(int $parallaxTarget, int i) {
        return $parallaxTarget;
    }

    static final int SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$18(int fullHeight) {
        return fullHeight;
    }

    static final FiniteAnimationSpec SwitchableDateEntryContent_KaiTk9E$lambda$21$lambda$20$lambda$19(FiniteAnimationSpec $spatialSizeAnimationSpec, IntSize intSize, IntSize intSize2) {
        return $spatialSizeAnimationSpec;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0394 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03cb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0467  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0473  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x04ac  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x04c2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x058d  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x05bf  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x05d5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0753  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void DatePickerContent(final java.lang.Long r84, final long r85, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r87, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r88, final androidx.compose.material3.internal.CalendarModel r89, final kotlin.ranges.IntRange r90, final androidx.compose.material3.DatePickerFormatter r91, final androidx.compose.material3.SelectableDates r92, final androidx.compose.material3.DatePickerColors r93, androidx.compose.runtime.Composer r94, final int r95) {
        /*
            Method dump skipped, instruction units count: 1921
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.DatePickerContent(java.lang.Long, long, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.material3.internal.CalendarModel, kotlin.ranges.IntRange, androidx.compose.material3.DatePickerFormatter, androidx.compose.material3.SelectableDates, androidx.compose.material3.DatePickerColors, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DatePickerContent$lambda$26(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DatePickerContent$lambda$27(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit DatePickerContent$lambda$36$lambda$29$lambda$28(CoroutineScope $coroutineScope, LazyListState $monthsListState) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new DatePickerKt$DatePickerContent$2$1$1$1($monthsListState, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit DatePickerContent$lambda$36$lambda$31$lambda$30(CoroutineScope $coroutineScope, LazyListState $monthsListState) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new DatePickerKt$DatePickerContent$2$2$1$1($monthsListState, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit DatePickerContent$lambda$36$lambda$33$lambda$32(MutableState $yearPickerVisible$delegate) {
        DatePickerContent$lambda$27($yearPickerVisible$delegate, !DatePickerContent$lambda$26($yearPickerVisible$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DatePickerHeader-pc5RIQQ, reason: not valid java name */
    public static final void m2433DatePickerHeaderpc5RIQQ(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final long titleContentColor, final long headlineContentColor, final float minHeight, final Function2<? super Composer, ? super Integer, Unit> function22, Composer $composer, final int $changed) {
        long j;
        Modifier.Companion heightModifier;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(2020490761);
        ComposerKt.sourceInformation($composer2, "C(DatePickerHeader)N(modifier,title,titleContentColor:c#ui.graphics.Color,headlineContentColor:c#ui.graphics.Color,minHeight:c#ui.unit.Dp,content)1685@78288L540:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            j = titleContentColor;
            $dirty |= $composer2.changed(j) ? 256 : 128;
        } else {
            j = titleContentColor;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(headlineContentColor) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(minHeight) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function22) ? 131072 : 65536;
        }
        if ($composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2020490761, $dirty, -1, "androidx.compose.material3.DatePickerHeader (DatePicker.kt:1677)");
            }
            if (function2 != null) {
                heightModifier = SizeKt.m1100defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, minHeight, 1, null);
            } else {
                heightModifier = Modifier.INSTANCE;
            }
            Modifier modifier$iv = SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null).then(heightModifier);
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            int $dirty2 = $dirty;
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
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
            ComposerKt.sourceInformationMarkerStart($composer2, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 396879060, "C1695@78730L92:DatePicker.kt#uh7d8r");
            if (function2 != null) {
                $composer2.startReplaceGroup(396894187);
                ComposerKt.sourceInformation($composer2, "1690@78518L5,1691@78622L89,1691@78536L175");
                TextStyle textStyle = TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getHeaderSupportingTextFont(), $composer2, 6);
                ProvideContentColorTextStyleKt.m3452ProvideContentColorTextStyle3JVO9M(j, textStyle, ComposableLambdaKt.rememberComposableLambda(1344395458, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerHeader$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed2) {
                        Function0<ComposeUiNode> function02;
                        ComposerKt.sourceInformation($composer3, "C1692@78640L57:DatePicker.kt#uh7d8r");
                        if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1344395458, $changed2, -1, "androidx.compose.material3.DatePickerHeader.<anonymous>.<anonymous> (DatePicker.kt:1692)");
                        }
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getBottomStart();
                        Function2<Composer, Integer, Unit> function23 = function2;
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Modifier modifier$iv2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv2 = (48 << 3) & 112;
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
                            function02 = constructor2;
                            $composer3.createNode(function02);
                        } else {
                            function02 = constructor2;
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
                        int i4 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 562743380, "C1692@78688L7:DatePicker.kt#uh7d8r");
                        function23.invoke($composer3, 0);
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
                }, $composer2, 54), $composer2, (($dirty2 >> 6) & 14) | 384);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(397163267);
                $composer2.endReplaceGroup();
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(headlineContentColor)), function22, $composer2, ProvidedValue.$stable | (($dirty2 >> 12) & 112));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.DatePickerHeader_pc5RIQQ$lambda$39(modifier, function2, titleContentColor, headlineContentColor, minHeight, function22, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0180  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void HorizontalMonthsList(androidx.compose.foundation.lazy.LazyListState r22, final java.lang.Long r23, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r24, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r25, final androidx.compose.material3.internal.CalendarModel r26, final kotlin.ranges.IntRange r27, final androidx.compose.material3.DatePickerFormatter r28, final androidx.compose.material3.SelectableDates r29, final androidx.compose.material3.DatePickerColors r30, androidx.compose.runtime.Composer r31, final int r32) {
        /*
            Method dump skipped, instruction units count: 497
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.HorizontalMonthsList(androidx.compose.foundation.lazy.LazyListState, java.lang.Long, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.material3.internal.CalendarModel, kotlin.ranges.IntRange, androidx.compose.material3.DatePickerFormatter, androidx.compose.material3.SelectableDates, androidx.compose.material3.DatePickerColors, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1, reason: invalid class name */
    /* JADX INFO: compiled from: DatePicker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ CalendarMonth $firstMonth;
        final /* synthetic */ LazyListState $lazyListState;
        final /* synthetic */ Function1<Long, Unit> $onDateSelectionChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedDateMillis;
        final /* synthetic */ CalendarDate $today;
        final /* synthetic */ IntRange $yearRange;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(LazyListState lazyListState, IntRange intRange, CalendarModel calendarModel, CalendarMonth calendarMonth, Function1<? super Long, Unit> function1, CalendarDate calendarDate, Long l, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors) {
            this.$lazyListState = lazyListState;
            this.$yearRange = intRange;
            this.$calendarModel = calendarModel;
            this.$firstMonth = calendarMonth;
            this.$onDateSelectionChange = function1;
            this.$today = calendarDate;
            this.$selectedDateMillis = l;
            this.$dateFormatter = datePickerFormatter;
            this.$selectableDates = selectableDates;
            this.$colors = datePickerColors;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x011e  */
        /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void invoke(androidx.compose.runtime.Composer r30, int r31) {
            /*
                Method dump skipped, instruction units count: 294
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.AnonymousClass1.invoke(androidx.compose.runtime.Composer, int):void");
        }

        static final Unit invoke$lambda$3$lambda$2(SemanticsPropertyReceiver $this$semantics) {
            SemanticsPropertiesKt.setHorizontalScrollAxisRange($this$semantics, new ScrollAxisRange(new Function0() { // from class: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(DatePickerKt.AnonymousClass1.invoke$lambda$3$lambda$2$lambda$0());
                }
            }, new Function0() { // from class: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(DatePickerKt.AnonymousClass1.invoke$lambda$3$lambda$2$lambda$1());
                }
            }, false, 4, null));
            return Unit.INSTANCE;
        }

        static final float invoke$lambda$3$lambda$2$lambda$0() {
            return 0.0f;
        }

        static final float invoke$lambda$3$lambda$2$lambda$1() {
            return 0.0f;
        }

        static final Unit invoke$lambda$5$lambda$4(IntRange $yearRange, final CalendarModel $calendarModel, final CalendarMonth $firstMonth, final Function1 $onDateSelectionChange, final CalendarDate $today, final Long $selectedDateMillis, final DatePickerFormatter $dateFormatter, final SelectableDates $selectableDates, final DatePickerColors $colors, LazyListScope $this$LazyRow) {
            LazyListScope.items$default($this$LazyRow, DatePickerKt.numberOfMonthsInRange($yearRange), null, null, ComposableLambdaKt.composableLambdaInstance(72599078, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1$2$1$1
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer, "CN(it)1734@80317L652:DatePicker.kt#uh7d8r");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer.changed($this$items) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer.changed(it) ? 32 : 16;
                    }
                    if ($composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(72599078, $dirty, -1, "androidx.compose.material3.HorizontalMonthsList.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1733)");
                        }
                        CalendarMonth month = $calendarModel.plusMonths($firstMonth, it);
                        Modifier modifier$iv = LazyItemScope.fillParentMaxWidth$default($this$items, Modifier.INSTANCE, 0.0f, 1, null);
                        Function1<Long, Unit> function1 = $onDateSelectionChange;
                        CalendarDate calendarDate = $today;
                        Long l = $selectedDateMillis;
                        DatePickerFormatter datePickerFormatter = $dateFormatter;
                        SelectableDates selectableDates = $selectableDates;
                        DatePickerColors datePickerColors = $colors;
                        CalendarModel calendarModel = $calendarModel;
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function0 = constructor;
                            $composer.createNode(function0);
                        } else {
                            function0 = constructor;
                            $composer.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer);
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i2 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -438536543, "C1735@80385L566:DatePicker.kt#uh7d8r");
                        DatePickerKt.Month(month, function1, calendarDate.getUtcTimeMillis(), l, null, null, datePickerFormatter, selectableDates, datePickerColors, calendarModel.getLocale(), $composer, 221184);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        boolean propagateMinConstraints$iv = ComposerKt.isTraceInProgress();
                        if (propagateMinConstraints$iv) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer.skipToGroupEnd();
                }
            }), 6, null);
            return Unit.INSTANCE;
        }
    }

    public static final Object updateDisplayedMonth(final LazyListState lazyListState, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange yearRange, Continuation<? super Unit> continuation) {
        Object objCollect = SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(lazyListState.getFirstVisibleItemIndex());
            }
        }).collect(new FlowCollector() { // from class: androidx.compose.material3.DatePickerKt.updateDisplayedMonth.3
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit(((Number) value).intValue(), (Continuation<? super Unit>) $completion);
            }

            public final Object emit(int it, Continuation<? super Unit> continuation2) {
                int yearOffset = lazyListState.getFirstVisibleItemIndex() / 12;
                int month = (lazyListState.getFirstVisibleItemIndex() % 12) + 1;
                function1.invoke(Boxing.boxLong(calendarModel.getMonth(yearRange.getFirst() + yearOffset, month).getStartUtcTimeMillis()));
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0477  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void WeekDays(final androidx.compose.material3.DatePickerColors r90, final androidx.compose.material3.internal.CalendarModel r91, androidx.compose.runtime.Composer r92, final int r93) {
        /*
            Method dump skipped, instruction units count: 1169
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.WeekDays(androidx.compose.material3.DatePickerColors, androidx.compose.material3.internal.CalendarModel, androidx.compose.runtime.Composer, int):void");
    }

    static final Unit WeekDays$lambda$48$lambda$47$lambda$45$lambda$44(Pair $it, SemanticsPropertyReceiver $this$clearAndSetSemantics) {
        SemanticsPropertiesKt.setContentDescription($this$clearAndSetSemantics, (String) $it.getFirst());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:187:0x04f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Month(final androidx.compose.material3.internal.CalendarMonth r88, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r89, final long r90, final java.lang.Long r92, final java.lang.Long r93, final androidx.compose.material3.SelectedRangeInfo r94, final androidx.compose.material3.DatePickerFormatter r95, final androidx.compose.material3.SelectableDates r96, final androidx.compose.material3.DatePickerColors r97, final java.util.Locale r98, androidx.compose.runtime.Composer r99, final int r100) {
        /*
            Method dump skipped, instruction units count: 2005
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.Month(androidx.compose.material3.internal.CalendarMonth, kotlin.jvm.functions.Function1, long, java.lang.Long, java.lang.Long, androidx.compose.material3.SelectedRangeInfo, androidx.compose.material3.DatePickerFormatter, androidx.compose.material3.SelectableDates, androidx.compose.material3.DatePickerColors, java.util.Locale, androidx.compose.runtime.Composer, int):void");
    }

    static final Unit Month$lambda$51$lambda$50(SelectedRangeInfo $rangeSelectionInfo, DatePickerColors $colors, ContentDrawScope $this$drawWithContent) {
        DateRangePickerKt.m2456drawRangeBackgroundmxwnekA($this$drawWithContent, $rangeSelectionInfo, $colors.getDayInSelectionRangeContainerColor());
        $this$drawWithContent.drawContent();
        return Unit.INSTANCE;
    }

    static final Unit Month$lambda$58$lambda$57$lambda$54$lambda$53(Function1 $onDateSelectionChange, long $dateInMillis) {
        $onDateSelectionChange.invoke(Long.valueOf($dateInMillis));
        return Unit.INSTANCE;
    }

    public static final int numberOfMonthsInRange(IntRange yearRange) {
        return ((yearRange.getLast() - yearRange.getFirst()) + 1) * 12;
    }

    private static final String dayContentDescription(boolean rangeSelectionEnabled, boolean isToday, boolean isStartDate, boolean isEndDate, boolean isInRange, Composer $composer, int $changed) {
        boolean z;
        ComposerKt.sourceInformationMarkerStart($composer, 502032503, "C(dayContentDescription)N(rangeSelectionEnabled,isToday,isStartDate,isEndDate,isInRange):DatePicker.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(502032503, $changed, -1, "androidx.compose.material3.dayContentDescription (DatePicker.kt:1972)");
        }
        StringBuilder descriptionBuilder = new StringBuilder();
        boolean z2 = false;
        if (rangeSelectionEnabled) {
            $composer.startReplaceGroup(974450583);
            ComposerKt.sourceInformation($composer, "");
            if (isStartDate) {
                $composer.startReplaceGroup(1416909399);
                ComposerKt.sourceInformation($composer, "1977@90977L56");
                Strings.Companion companion = Strings.INSTANCE;
                descriptionBuilder.append(Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_picker_start_headline), $composer, 0));
                $composer.endReplaceGroup();
            } else if (isEndDate) {
                $composer.startReplaceGroup(1416913397);
                ComposerKt.sourceInformation($composer, "1979@91102L54");
                Strings.Companion companion2 = Strings.INSTANCE;
                descriptionBuilder.append(Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_picker_end_headline), $composer, 0));
                $composer.endReplaceGroup();
            } else if (isInRange) {
                $composer.startReplaceGroup(1416917332);
                ComposerKt.sourceInformation($composer, "1981@91225L53");
                Strings.Companion companion3 = Strings.INSTANCE;
                descriptionBuilder.append(Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_picker_day_in_range), $composer, 0));
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(974832875);
                $composer.endReplaceGroup();
            }
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(974838827);
            $composer.endReplaceGroup();
        }
        if (isToday) {
            $composer.startReplaceGroup(1416920485);
            ComposerKt.sourceInformation($composer, "1986@91426L54");
            if (descriptionBuilder.length() <= 0) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                descriptionBuilder.append(", ");
            }
            Strings.Companion companion4 = Strings.INSTANCE;
            descriptionBuilder.append(Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_picker_today_description), $composer, 0));
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(975029291);
            $composer.endReplaceGroup();
        }
        if (descriptionBuilder.length() == 0) {
            z2 = true;
        }
        String string = z2 ? null : descriptionBuilder.toString();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return string;
    }

    private static final void Day(final String text, final Modifier modifier, final boolean selected, final Function0<Unit> function0, final boolean animateChecked, final boolean enabled, final boolean today, final boolean inRange, final String description, final DatePickerColors colors, Composer $composer, final int $changed) {
        String str;
        boolean z;
        boolean z2;
        boolean z3;
        DatePickerColors datePickerColors;
        Composer $composer2;
        BorderStroke borderStrokeM312BorderStrokecXLIe8U;
        Composer $composer3 = $composer.startRestartGroup(-945355136);
        ComposerKt.sourceInformation($composer3, "C(Day)N(text,modifier,selected,onClick,animateChecked,enabled,today,inRange,description,colors)2012@92261L124,2017@92471L5,2020@92530L83,2031@92927L867,2004@91851L1943:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            str = text;
            $dirty |= $composer3.changed(str) ? 4 : 2;
        } else {
            str = text;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(selected) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            z = animateChecked;
            $dirty |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = animateChecked;
        }
        if ((196608 & $changed) == 0) {
            z2 = enabled;
            $dirty |= $composer3.changed(z2) ? 131072 : 65536;
        } else {
            z2 = enabled;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changed(today) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            z3 = inRange;
            $dirty |= $composer3.changed(z3) ? 8388608 : 4194304;
        } else {
            z3 = inRange;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer3.changed(description) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if ((805306368 & $changed) == 0) {
            datePickerColors = colors;
            $dirty |= $composer3.changed(datePickerColors) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            datePickerColors = colors;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute((306783379 & $dirty2) != 306783378, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-945355136, $dirty2, -1, "androidx.compose.material3.Day (DatePicker.kt:2003)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 2046803100, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = (234881024 & $dirty2) == 67108864;
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DatePickerKt.Day$lambda$61$lambda$60(description, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (Function1) it$iv);
            Shape value = ShapesKt.getValue(DatePickerModalTokens.INSTANCE.getDateContainerShape(), $composer3, 6);
            long jM5323unboximpl = datePickerColors.dayContainerColor$material3(selected, z2, z, $composer3, (($dirty2 >> 6) & 14) | (($dirty2 >> 12) & 112) | (($dirty2 >> 6) & 896) | (($dirty2 >> 18) & 7168)).getValue().m5323unboximpl();
            if (today && !selected) {
                borderStrokeM312BorderStrokecXLIe8U = BorderStrokeKt.m312BorderStrokecXLIe8U(DatePickerModalTokens.INSTANCE.m3764getDateTodayContainerOutlineWidthD9Ej5fM(), colors.getTodayDateBorderColor());
            } else {
                borderStrokeM312BorderStrokecXLIe8U = null;
            }
            SurfaceKt.m3015Surfaced85dljk(selected, function0, modifierSemantics, enabled, value, jM5323unboximpl, 0L, 0.0f, 0.0f, borderStrokeM312BorderStrokecXLIe8U, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(1126347158, true, new C02532(str, colors, today, selected, z3, enabled), $composer3, 54), $composer3, (($dirty2 >> 6) & 14) | (($dirty2 >> 6) & 112) | (($dirty2 >> 6) & 7168), 48, 1472);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.Day$lambda$62(text, modifier, selected, function0, animateChecked, enabled, today, inRange, description, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Day$lambda$61$lambda$60(String $description, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setText($this$semantics, new AnnotatedString($description, null, 2, null));
        SemanticsPropertiesKt.m7362setRolekuIjeqM($this$semantics, Role.INSTANCE.m7343getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$Day$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DatePicker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02532 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ boolean $inRange;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ String $text;
        final /* synthetic */ boolean $today;

        C02532(String str, DatePickerColors datePickerColors, boolean z, boolean z2, boolean z3, boolean z4) {
            this.$text = str;
            this.$colors = datePickerColors;
            this.$today = z;
            this.$selected = z2;
            this.$inRange = z3;
            this.$enabled = z4;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C2032@92937L851:DatePicker.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1126347158, $changed, -1, "androidx.compose.material3.Day.<anonymous> (DatePicker.kt:2032)");
            }
            Modifier modifier$iv = SizeKt.m1109requiredSizeVpY3zN4(Modifier.INSTANCE, DatePickerModalTokens.INSTANCE.m3761getDateContainerWidthD9Ej5fM(), DatePickerModalTokens.INSTANCE.m3760getDateContainerHeightD9Ej5fM());
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            String str = this.$text;
            DatePickerColors datePickerColors = this.$colors;
            boolean z = this.$today;
            boolean z2 = this.$selected;
            boolean z3 = this.$inRange;
            boolean z4 = this.$enabled;
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (54 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 781235302, "C2043@93376L2,2046@93456L230,2040@93221L557:DatePicker.kt#uh7d8r");
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1775909774, "CC(remember):DatePicker.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DatePickerKt$Day$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.m3157TextNvy7gAk(str, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) it$iv), datePickerColors.dayContentColor$material3(z, z2, z3, z4, $composer, 0).getValue().m5323unboximpl(), null, 0L, null, null, null, 0L, null, TextAlign.m7996boximpl(TextAlign.INSTANCE.m8003getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 261112);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DatePicker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02571 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ long $displayedMonthMillis;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Function1<Integer, Unit> $onYearSelected;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ IntRange $yearRange;

        /* JADX WARN: Multi-variable type inference failed */
        C02571(CalendarModel calendarModel, long j, IntRange intRange, Modifier modifier, DatePickerColors datePickerColors, Function1<? super Integer, Unit> function1, SelectableDates selectableDates) {
            this.$calendarModel = calendarModel;
            this.$displayedMonthMillis = j;
            this.$yearRange = intRange;
            this.$modifier = modifier;
            this.$colors = datePickerColors;
            this.$onYearSelected = function1;
            this.$selectableDates = selectableDates;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C2073@94342L281,2086@95071L1131,2078@94632L1570:DatePicker.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1301915789, $changed, -1, "androidx.compose.material3.YearPicker.<anonymous> (DatePicker.kt:2070)");
            }
            final int currentYear = this.$calendarModel.getMonth(this.$calendarModel.getToday()).getYear();
            final int displayedYear = this.$calendarModel.getMonth(this.$displayedMonthMillis).getYear();
            LazyGridState lazyGridState = LazyGridStateKt.rememberLazyGridState(Math.max(0, (displayedYear - this.$yearRange.getFirst()) - 3), 0, $composer, 0, 2);
            GridCells.Fixed fixed = new GridCells.Fixed(3);
            Modifier modifierM286backgroundbw27NRU$default = BackgroundKt.m286backgroundbw27NRU$default(this.$modifier, this.$colors.getContainerColor(), null, 2, null);
            Arrangement.HorizontalOrVertical spaceEvenly = Arrangement.INSTANCE.getSpaceEvenly();
            GridCells.Fixed fixed2 = fixed;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM740spacedBy0680j_4 = Arrangement.INSTANCE.m740spacedBy0680j_4(DatePickerKt.YearsVerticalPadding);
            Arrangement.HorizontalOrVertical horizontalOrVertical = spaceEvenly;
            ComposerKt.sourceInformationMarkerStart($composer, 1305620792, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = $composer.changedInstance(this.$yearRange) | $composer.changedInstance(this.$calendarModel) | $composer.changed(displayedYear) | $composer.changed(currentYear) | $composer.changed(this.$onYearSelected) | $composer.changed(this.$selectableDates) | $composer.changed(this.$colors);
            final IntRange intRange = this.$yearRange;
            final CalendarModel calendarModel = this.$calendarModel;
            final Function1<Integer, Unit> function1 = this.$onYearSelected;
            final SelectableDates selectableDates = this.$selectableDates;
            final DatePickerColors datePickerColors = this.$colors;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DatePickerKt$YearPicker$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DatePickerKt.C02571.invoke$lambda$1$lambda$0(intRange, calendarModel, displayedYear, currentYear, function1, selectableDates, datePickerColors, (LazyGridScope) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            LazyGridDslKt.LazyVerticalGrid(fixed2, modifierM286backgroundbw27NRU$default, lazyGridState, null, false, horizontalOrVerticalM740spacedBy0680j_4, horizontalOrVertical, null, false, null, (Function1) it$iv, $composer, 1769472, 0, 920);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        static final Unit invoke$lambda$1$lambda$0(IntRange $yearRange, CalendarModel $calendarModel, int $displayedYear, int $currentYear, Function1 $onYearSelected, SelectableDates $selectableDates, DatePickerColors $colors, LazyGridScope $this$LazyVerticalGrid) {
            LazyGridScope.items$default($this$LazyVerticalGrid, CollectionsKt.count($yearRange), null, null, null, ComposableLambdaKt.composableLambdaInstance(674613074, true, new DatePickerKt$YearPicker$1$1$1$1($yearRange, $calendarModel, $displayedYear, $currentYear, $onYearSelected, $selectableDates, $colors)), 14, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void YearPicker(final Modifier modifier, final long displayedMonthMillis, final Function1<? super Integer, Unit> function1, final SelectableDates selectableDates, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerColors colors, Composer $composer, final int $changed) {
        Modifier modifier2;
        long j;
        Function1<? super Integer, Unit> function12;
        SelectableDates selectableDates2;
        CalendarModel calendarModel2;
        IntRange intRange;
        DatePickerColors datePickerColors;
        Composer $composer2 = $composer.startRestartGroup(-1286899812);
        ComposerKt.sourceInformation($composer2, "C(YearPicker)N(modifier,displayedMonthMillis,onYearSelected,selectableDates,calendarModel,yearRange,colors)2069@94140L5,2069@94147L2061,2069@94066L2142:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            j = displayedMonthMillis;
            $dirty |= $composer2.changed(j) ? 32 : 16;
        } else {
            j = displayedMonthMillis;
        }
        if (($changed & 384) == 0) {
            function12 = function1;
            $dirty |= $composer2.changedInstance(function12) ? 256 : 128;
        } else {
            function12 = function1;
        }
        if (($changed & 3072) == 0) {
            selectableDates2 = selectableDates;
            $dirty |= $composer2.changed(selectableDates2) ? 2048 : 1024;
        } else {
            selectableDates2 = selectableDates;
        }
        if (($changed & 24576) == 0) {
            calendarModel2 = calendarModel;
            $dirty |= $composer2.changedInstance(calendarModel2) ? 16384 : 8192;
        } else {
            calendarModel2 = calendarModel;
        }
        if ((196608 & $changed) == 0) {
            intRange = yearRange;
            $dirty |= $composer2.changedInstance(intRange) ? 131072 : 65536;
        } else {
            intRange = yearRange;
        }
        if ((1572864 & $changed) == 0) {
            datePickerColors = colors;
            $dirty |= $composer2.changed(datePickerColors) ? 1048576 : 524288;
        } else {
            datePickerColors = colors;
        }
        if (!$composer2.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1286899812, $dirty, -1, "androidx.compose.material3.YearPicker (DatePicker.kt:2068)");
            }
            TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getSelectionYearLabelTextFont(), $composer2, 6), ComposableLambdaKt.rememberComposableLambda(1301915789, true, new C02571(calendarModel2, j, intRange, modifier2, datePickerColors, function12, selectableDates2), $composer2, 54), $composer2, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.YearPicker$lambda$63(modifier, displayedMonthMillis, function1, selectableDates, calendarModel, yearRange, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Year(final String text, final Modifier modifier, final boolean selected, final boolean currentYear, final Function0<Unit> function0, final boolean enabled, final String description, final DatePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2;
        Object value$iv;
        Composer $composer3 = $composer.startRestartGroup(-1153850597);
        ComposerKt.sourceInformation($composer3, "C(Year)N(text,modifier,selected,currentYear,onClick,enabled,description,colors)2130@96589L394,2148@97358L112,2153@97566L5,2154@97596L58,2156@97693L638,2141@96988L1343:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(selected) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(currentYear) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(enabled) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changed(description) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changed(colors) ? 8388608 : 4194304;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute((4793491 & $dirty2) != 4793490, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1153850597, $dirty2, -1, "androidx.compose.material3.Year (DatePicker.kt:2128)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -748852891, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 7168) == 2048) | (($dirty2 & 896) == 256);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                if (currentYear && !selected) {
                    value$iv = BorderStrokeKt.m312BorderStrokecXLIe8U(DatePickerModalTokens.INSTANCE.m3764getDateTodayContainerOutlineWidthD9Ej5fM(), colors.getTodayDateBorderColor());
                } else {
                    value$iv = null;
                }
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            BorderStroke border = (BorderStroke) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -748828565, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv2 = (3670016 & $dirty2) == 1048576;
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DatePickerKt.Year$lambda$66$lambda$65(description, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            SurfaceKt.m3015Surfaced85dljk(selected, function0, SemanticsModifierKt.semantics(modifier, true, (Function1) it$iv2), enabled, ShapesKt.getValue(DatePickerModalTokens.INSTANCE.getSelectionYearStateLayerShape(), $composer3, 6), colors.yearContainerColor$material3(selected, enabled, $composer3, (($dirty2 >> 6) & 14) | (($dirty2 >> 12) & 112) | (($dirty2 >> 15) & 896)).getValue().m5323unboximpl(), 0L, 0.0f, 0.0f, border, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-564400443, true, new C02562(text, colors, currentYear, selected, enabled), $composer3, 54), $composer2, (($dirty2 >> 6) & 14) | (($dirty2 >> 9) & 112) | (($dirty2 >> 6) & 7168), 48, 1472);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.Year$lambda$67(text, modifier, selected, currentYear, function0, enabled, description, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Year$lambda$66$lambda$65(String $description, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setText($this$semantics, new AnnotatedString($description, null, 2, null));
        SemanticsPropertiesKt.m7362setRolekuIjeqM($this$semantics, Role.INSTANCE.m7343getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$Year$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DatePicker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02562 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ boolean $currentYear;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ String $text;

        C02562(String str, DatePickerColors datePickerColors, boolean z, boolean z2, boolean z3) {
            this.$text = str;
            this.$colors = datePickerColors;
            this.$currentYear = z;
            this.$selected = z2;
            this.$enabled = z3;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C2157@97703L622:DatePicker.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-564400443, $changed, -1, "androidx.compose.material3.Year.<anonymous> (DatePicker.kt:2157)");
            }
            Modifier modifier$iv = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            String str = this.$text;
            DatePickerColors datePickerColors = this.$colors;
            boolean z = this.$currentYear;
            boolean z2 = this.$selected;
            boolean z3 = this.$enabled;
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (54 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1181166895, "C2161@97949L2,2164@98029L194,2158@97794L521:DatePicker.kt#uh7d8r");
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1901556051, "CC(remember):DatePicker.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DatePickerKt$Year$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.m3157TextNvy7gAk(str, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) it$iv), datePickerColors.yearContentColor$material3(z, z2, z3, $composer, 0).getValue().m5323unboximpl(), null, 0L, null, null, null, 0L, null, TextAlign.m7996boximpl(TextAlign.INSTANCE.m8003getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 261112);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0277  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void MonthsNavigation(final androidx.compose.ui.Modifier r37, final boolean r38, final boolean r39, final boolean r40, final java.lang.String r41, final kotlin.jvm.functions.Function0<kotlin.Unit> r42, final kotlin.jvm.functions.Function0<kotlin.Unit> r43, final kotlin.jvm.functions.Function0<kotlin.Unit> r44, final androidx.compose.material3.DatePickerColors r45, androidx.compose.runtime.Composer r46, final int r47) {
        /*
            Method dump skipped, instruction units count: 707
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.MonthsNavigation(androidx.compose.ui.Modifier, boolean, boolean, boolean, java.lang.String, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.material3.DatePickerColors, androidx.compose.runtime.Composer, int):void");
    }

    private static final void YearPickerMenuButton(final Function0<Unit> function0, final boolean expanded, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        final Modifier modifier3;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(-709923073);
        ComposerKt.sourceInformation($composer2, "C(YearPickerMenuButton)N(onClick,expanded,modifier,content)2252@101224L7,2252@101174L58,2255@101289L454,2248@101045L698:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(expanded) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-709923073, $dirty, -1, "androidx.compose.material3.YearPickerMenuButton (DatePicker.kt:2247)");
            }
            RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
            ButtonDefaults buttonDefaults = ButtonDefaults.INSTANCE;
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier5 = modifier4;
            ButtonKt.TextButton(function02, modifier5, false, circleShape, buttonDefaults.m2219textButtonColorsro_MJ88(0L, ((Color) objConsume).m5323unboximpl(), 0L, 0L, $composer2, 24576, 13), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1899489890, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPickerMenuButton.1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                    invoke(rowScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RowScope $this$TextButton, Composer $composer3, int $changed2) {
                    String str;
                    ComposerKt.sourceInformation($composer3, "C2256@101299L9,2257@101317L49,2258@101375L362:DatePicker.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 17) != 16, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1899489890, $changed2, -1, "androidx.compose.material3.YearPickerMenuButton.<anonymous> (DatePicker.kt:2256)");
                    }
                    function2.invoke($composer3, 0);
                    SpacerKt.Spacer(SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, ButtonDefaults.INSTANCE.m2215getIconSpacingD9Ej5fM()), $composer3, 6);
                    ImageVector arrowDropDown$material3 = Icons.Filled.INSTANCE.getArrowDropDown$material3();
                    if (expanded) {
                        $composer3.startReplaceGroup(1509384391);
                        ComposerKt.sourceInformation($composer3, "2262@101506L49");
                        Strings.Companion companion = Strings.INSTANCE;
                        String strM3533getString2EP1pXo = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_picker_switch_to_day_selection), $composer3, 0);
                        $composer3.endReplaceGroup();
                        str = strM3533getString2EP1pXo;
                    } else {
                        $composer3.startReplaceGroup(1509478662);
                        ComposerKt.sourceInformation($composer3, "2264@101601L50");
                        Strings.Companion companion2 = Strings.INSTANCE;
                        String strM3533getString2EP1pXo2 = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_picker_switch_to_year_selection), $composer3, 0);
                        $composer3.endReplaceGroup();
                        str = strM3533getString2EP1pXo2;
                    }
                    IconKt.m2605Iconww6aTOc(arrowDropDown$material3, str, RotateKt.rotate(Modifier.INSTANCE, expanded ? 180.0f : 0.0f), 0L, $composer3, 0, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 807075840 | (($dirty >> 3) & 112), 388);
            $composer2 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.YearPickerMenuButton$lambda$70(function0, expanded, modifier3, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void IconButtonWithTooltip(final Function0<Unit> function0, final ImageVector icon, final String contentDescription, Modifier modifier, boolean enabled, Composer $composer, final int $changed, final int i) {
        final ImageVector imageVector;
        Modifier modifier2;
        boolean z;
        final Modifier modifier3;
        final boolean enabled2;
        final Modifier modifier4;
        boolean enabled3;
        Composer $composer2 = $composer.startRestartGroup(-368059805);
        ComposerKt.sourceInformation($composer2, "C(IconButtonWithTooltip)N(onClick,icon,contentDescription,modifier,enabled)2282@102053L60,2283@102133L45,2284@102196L22,2285@102226L175,2280@101986L415:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            imageVector = icon;
        } else if (($changed & 48) == 0) {
            imageVector = icon;
            $dirty |= $composer2.changed(imageVector) ? 32 : 16;
        } else {
            imageVector = icon;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(contentDescription) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            z = enabled;
        } else if (($changed & 24576) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 16384 : 8192;
        } else {
            z = enabled;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 == 0) {
                enabled3 = z;
            } else {
                enabled3 = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-368059805, $dirty2, -1, "androidx.compose.material3.IconButtonWithTooltip (DatePicker.kt:2279)");
            }
            final boolean enabled4 = enabled3;
            TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m3336rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m3323getAbovelOKsHw4(), 0.0f, $composer2, 390, 2), ComposableLambdaKt.rememberComposableLambda(-456272562, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer, Integer num) {
                    invoke(tooltipScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(TooltipScope $this$TooltipBox, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C2283@102148L28,2283@102135L41:DatePicker.kt#uh7d8r");
                    int $dirty3 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty3 |= ($changed2 & 8) == 0 ? $composer3.changed($this$TooltipBox) : $composer3.changedInstance($this$TooltipBox) ? 4 : 2;
                    }
                    int $dirty4 = $dirty3;
                    if (!$composer3.shouldExecute(($dirty4 & 19) != 18, $dirty4 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-456272562, $dirty4, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2283)");
                    }
                    final String str = contentDescription;
                    TooltipKt.m3339PlainTooltipgv3ox5I($this$TooltipBox, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(1905952188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.1.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed3) {
                            ComposerKt.sourceInformation($composer4, "C2283@102150L24:DatePicker.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1905952188, $changed3, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2283)");
                            }
                            TextKt.m3157TextNvy7gAk(str, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer4, 0, 0, 262142);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54), $composer3, ($dirty4 & 14) | 805306368, 255);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), TooltipKt.rememberTooltipState(false, false, null, $composer2, 0, 7), null, null, false, false, false, ComposableLambdaKt.rememberComposableLambda(-1124908186, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C2286@102306L89,2286@102236L159:DatePicker.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1124908186, $changed2, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous> (DatePicker.kt:2286)");
                    }
                    Function0<Unit> function02 = function0;
                    Modifier modifier5 = modifier4;
                    boolean z2 = enabled4;
                    final ImageVector imageVector2 = imageVector;
                    final String str = contentDescription;
                    IconButtonKt.IconButton(function02, modifier5, z2, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1301085432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.IconButtonWithTooltip.2.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed3) {
                            ComposerKt.sourceInformation($composer4, "C2287@102320L65:DatePicker.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1301085432, $changed3, -1, "androidx.compose.material3.IconButtonWithTooltip.<anonymous>.<anonymous> (DatePicker.kt:2287)");
                            }
                            IconKt.m2605Iconww6aTOc(imageVector2, str, (Modifier) null, 0L, $composer4, 0, 12);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54), $composer3, 1572864, 56);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, 100663344, 248);
            $composer2 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled2 = enabled4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enabled2 = z;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerKt.IconButtonWithTooltip$lambda$71(function0, icon, contentDescription, modifier3, enabled2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final float getRecommendedSizeForAccessibility() {
        return RecommendedSizeForAccessibility;
    }

    public static final float getMonthYearHeight() {
        return MonthYearHeight;
    }

    public static final float getDatePickerHorizontalPadding() {
        return DatePickerHorizontalPadding;
    }

    public static final PaddingValues getDatePickerModeTogglePadding() {
        return DatePickerModeTogglePadding;
    }
}
