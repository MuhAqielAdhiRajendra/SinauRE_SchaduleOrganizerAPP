package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ChipKt;
import androidx.compose.material3.tokens.AssistChipTokens;
import androidx.compose.material3.tokens.FilterChipTokens;
import androidx.compose.material3.tokens.InputChipTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.SuggestionChipTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a¨\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a¦\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0018\u001a¨\u0001\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a¦\u0001\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0018\u001a°\u0001\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u001c2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u001e\u001a°\u0001\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u001c2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u001e\u001aÇ\u0001\u0010 \u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u001c2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\"\u001a\u0091\u0001\u0010#\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010%\u001a\u008f\u0001\u0010#\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010&\u001a\u0091\u0001\u0010'\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010%\u001a\u008f\u0001\u0010'\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010&\u001a¸\u0001\u0010(\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\b\u001a\u00020\t2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0003¢\u0006\u0004\b1\u00102\u001aÍ\u0001\u00103\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\b\u001a\u00020\t2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010)\u001a\u00020*2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001c2\b\u0010\u0010\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0003¢\u0006\u0004\b4\u00105\u001a\u0091\u0001\u00106\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u00107\u001a\u00020,2\u0006\u00108\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0003¢\u0006\u0004\b9\u0010:\u001a\u0091\u0001\u0010;\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u00107\u001a\u00020,2\u0006\u00108\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0003¢\u0006\u0004\b<\u0010:\u001aN\u0010=\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u00107\u001a\u00020,H\u0003¢\u0006\u0004\b>\u0010?\u001a9\u0010@\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u00108\u001a\u00020,H\u0003¢\u0006\u0004\bA\u0010B\u001a%\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001HE0D\"\u0004\b\u0000\u0010E2\b\u0010F\u001a\u0004\u0018\u0001HEH\u0003¢\u0006\u0002\u0010G\u001a&\u0010L\u001a\u0002002\b\b\u0002\u0010M\u001a\u00020\t2\b\b\u0002\u0010N\u001a\u00020\t2\b\b\u0002\u0010O\u001a\u00020\tH\u0002\"\u0018\u0010H\u001a\u00020\u000f*\u00020I8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010K\"\u0010\u0010P\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010Q\"\u000e\u0010R\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010S\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010T\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010U\u001a\u00020VX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010W\u001a\u00020VX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010X\u001a\u00020VX\u0082T¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"AssistChip", "", "onClick", "Lkotlin/Function0;", ChipKt.LabelLayoutId, "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", ChipKt.LeadingIconLayoutId, ChipKt.TrailingIconLayoutId, "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/ChipColors;", "elevation", "Landroidx/compose/material3/ChipElevation;", "border", "Landroidx/compose/foundation/BorderStroke;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/material3/ChipBorder;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/material3/ChipBorder;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "ElevatedAssistChip", "FilterChip", "selected", "Landroidx/compose/material3/SelectableChipColors;", "Landroidx/compose/material3/SelectableChipElevation;", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "ElevatedFilterChip", "InputChip", "avatar", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "SuggestionChip", "icon", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/material3/ChipBorder;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "ElevatedSuggestionChip", "Chip", "labelTextStyle", "Landroidx/compose/ui/text/TextStyle;", "labelColor", "Landroidx/compose/ui/graphics/Color;", "minHeight", "Landroidx/compose/ui/unit/Dp;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "Chip-nkUnTEs", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SelectableChip", "SelectableChip-u0RnIRE", "(ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "ChipContent", "leadingIconColor", "trailingIconColor", "ChipContent-fe0OD_I", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)V", "AnimatingChipContent", "AnimatingChipContent-fe0OD_I", "leadingContent", "leadingContent-XO-JAsU", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JLandroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "trailingContent", "trailingContent-RPmYEkk", "(Lkotlin/jvm/functions/Function2;JLandroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "rememberRetainedState", "Landroidx/compose/runtime/State;", "T", "targetValue", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "defaultSuggestionChipColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultSuggestionChipColors", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ChipColors;", "inputChipPadding", "hasAvatar", "hasLeadingIcon", "hasTrailingIcon", "HorizontalElementsPadding", "F", "AssistChipPadding", "FilterChipPadding", "SuggestionChipPadding", "LeadingIconLayoutId", "", "LabelLayoutId", "TrailingIconLayoutId", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ChipKt {
    private static final String LabelLayoutId = "label";
    private static final String LeadingIconLayoutId = "leadingIcon";
    private static final String TrailingIconLayoutId = "trailingIcon";
    private static final float HorizontalElementsPadding = Dp.m8150constructorimpl(8);
    private static final PaddingValues AssistChipPadding = PaddingKt.m1043PaddingValuesYgX7TsA$default(HorizontalElementsPadding, 0.0f, 2, null);
    private static final PaddingValues FilterChipPadding = PaddingKt.m1043PaddingValuesYgX7TsA$default(HorizontalElementsPadding, 0.0f, 2, null);
    private static final PaddingValues SuggestionChipPadding = PaddingKt.m1043PaddingValuesYgX7TsA$default(HorizontalElementsPadding, 0.0f, 2, null);

    static final Unit AnimatingChipContent_fe0OD_I$lambda$24(Function2 function2, TextStyle textStyle, long j, Function2 function22, Function2 function23, Function2 function24, long j2, long j3, float f, PaddingValues paddingValues, int i, Composer composer, int i2) {
        m2272AnimatingChipContentfe0OD_I(function2, textStyle, j, function22, function23, function24, j2, j3, f, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit AssistChip$lambda$0(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function22, Function2 function23, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        AssistChip((Function0<Unit>) function0, (Function2<? super Composer, ? super Integer, Unit>) function2, modifier, z, (Function2<? super Composer, ? super Integer, Unit>) function22, (Function2<? super Composer, ? super Integer, Unit>) function23, shape, chipColors, chipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit AssistChip$lambda$2(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function22, Function2 function23, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        AssistChip(function0, function2, modifier, z, function22, function23, shape, chipColors, chipElevation, chipBorder, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit ChipContent_fe0OD_I$lambda$23(Function2 function2, TextStyle textStyle, long j, Function2 function22, Function2 function23, Function2 function24, long j2, long j3, float f, PaddingValues paddingValues, int i, Composer composer, int i2) {
        m2274ChipContentfe0OD_I(function2, textStyle, j, function22, function23, function24, j2, j3, f, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Chip_nkUnTEs$lambda$18(Modifier modifier, Function0 function0, boolean z, Function2 function2, TextStyle textStyle, long j, Function2 function22, Function2 function23, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, float f, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        m2273ChipnkUnTEs(modifier, function0, z, function2, textStyle, j, function22, function23, shape, chipColors, chipElevation, borderStroke, f, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    static final Unit ElevatedAssistChip$lambda$3(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function22, Function2 function23, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        ElevatedAssistChip((Function0<Unit>) function0, (Function2<? super Composer, ? super Integer, Unit>) function2, modifier, z, (Function2<? super Composer, ? super Integer, Unit>) function22, (Function2<? super Composer, ? super Integer, Unit>) function23, shape, chipColors, chipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedAssistChip$lambda$5(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function22, Function2 function23, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        ElevatedAssistChip(function0, function2, modifier, z, function22, function23, shape, chipColors, chipElevation, chipBorder, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedFilterChip$lambda$7(boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function22, Function2 function23, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        ElevatedFilterChip(z, function0, function2, modifier, z2, function22, function23, shape, selectableChipColors, selectableChipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedSuggestionChip$lambda$12(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function22, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        ElevatedSuggestionChip((Function0<Unit>) function0, (Function2<? super Composer, ? super Integer, Unit>) function2, modifier, z, (Function2<? super Composer, ? super Integer, Unit>) function22, shape, chipColors, chipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedSuggestionChip$lambda$14(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function22, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        ElevatedSuggestionChip(function0, function2, modifier, z, function22, shape, chipColors, chipElevation, chipBorder, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit FilterChip$lambda$6(boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function22, Function2 function23, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        FilterChip(z, function0, function2, modifier, z2, function22, function23, shape, selectableChipColors, selectableChipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit InputChip$lambda$8(boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function22, Function2 function23, Function2 function24, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        InputChip(z, function0, function2, modifier, z2, function22, function23, function24, shape, selectableChipColors, selectableChipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit SelectableChip_u0RnIRE$lambda$22(boolean z, Modifier modifier, Function0 function0, boolean z2, Function2 function2, TextStyle textStyle, Function2 function22, Function2 function23, Function2 function24, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, float f, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        m2275SelectableChipu0RnIRE(z, modifier, function0, z2, function2, textStyle, function22, function23, function24, shape, selectableChipColors, selectableChipElevation, borderStroke, f, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    static final Unit SuggestionChip$lambda$11(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function22, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        SuggestionChip(function0, function2, modifier, z, function22, shape, chipColors, chipElevation, chipBorder, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SuggestionChip$lambda$9(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function22, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        SuggestionChip((Function0<Unit>) function0, (Function2<? super Composer, ? super Integer, Unit>) function2, modifier, z, (Function2<? super Composer, ? super Integer, Unit>) function22, shape, chipColors, chipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void AssistChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        ChipColors colors2;
        int i2;
        int $dirty;
        Composer $composer2;
        final Shape shape2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final ChipColors colors3;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function27;
        int $dirty2;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final boolean enabled2;
        final ChipElevation elevation2;
        Shape shape3;
        int $dirty3;
        ChipColors colors4;
        int $dirty4;
        Modifier modifier4;
        int i3;
        ChipElevation elevation3;
        boolean enabled3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        BorderStroke border4;
        Function2<? super Composer, ? super Integer, Unit> function29;
        ChipElevation elevation4;
        int $dirty5;
        Modifier modifier5;
        Function2<? super Composer, ? super Integer, Unit> function210;
        Shape shape4;
        Composer $composer3 = $composer.startRestartGroup(1192083339);
        ComposerKt.sourceInformation($composer3, "C(AssistChip)N(onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)151@7455L5,146@7287L542:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty6 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function24 = function2;
        } else if (($changed & 48) == 0) {
            function24 = function2;
            $dirty6 |= $composer3.changedInstance(function24) ? 32 : 16;
        } else {
            function24 = function2;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty6 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty6 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty6 |= 24576;
            function25 = function22;
        } else if (($changed & 24576) == 0) {
            function25 = function22;
            $dirty6 |= $composer3.changedInstance(function25) ? 16384 : 8192;
        } else {
            function25 = function22;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function26 = function23;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function26 = function23;
            $dirty6 |= $composer3.changedInstance(function26) ? 131072 : 65536;
        } else {
            function26 = function23;
        }
        if (($changed & 1572864) == 0) {
            $dirty6 |= ((i & 64) == 0 && $composer3.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                int i8 = $composer3.changed(colors2) ? 8388608 : 4194304;
                $dirty6 |= i8;
            } else {
                colors2 = colors;
            }
            $dirty6 |= i8;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            $dirty6 |= ((i & 256) == 0 && $composer3.changed(elevation)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty6 |= ((i & 512) == 0 && $composer3.changed(border)) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int $dirty1 = $changed1;
        int $dirty12 = i & 1024;
        if ($dirty12 != 0) {
            $dirty1 |= 6;
            i2 = $dirty12;
        } else if (($changed1 & 6) == 0) {
            i2 = $dirty12;
            $dirty1 |= $composer3.changed(interactionSource) ? 4 : 2;
        } else {
            i2 = $dirty12;
        }
        int $dirty13 = $dirty1;
        int $dirty7 = $dirty6;
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty13 & 3) == 2) ? false : true, $dirty7 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "140@7003L5,141@7054L18,142@7125L21,143@7195L25");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i5 != 0 ? true : z;
                if (i6 != 0) {
                    function25 = null;
                }
                Function2<? super Composer, ? super Integer, Unit> function211 = i7 != 0 ? null : function26;
                if ((i & 64) != 0) {
                    $dirty3 = $dirty7 & (-3670017);
                    shape3 = AssistChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty7;
                }
                if ((i & 128) != 0) {
                    colors4 = AssistChipDefaults.INSTANCE.assistChipColors($composer3, 6);
                    $dirty4 = $dirty3 & (-29360129);
                } else {
                    colors4 = colors2;
                    $dirty4 = $dirty3;
                }
                if ((i & 256) != 0) {
                    modifier4 = modifier6;
                    $dirty = $dirty13;
                    i3 = 6;
                    $dirty4 &= -234881025;
                    elevation3 = AssistChipDefaults.INSTANCE.m2183assistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier4 = modifier6;
                    i3 = 6;
                    $dirty = $dirty13;
                    elevation3 = elevation;
                }
                if ((i & 512) != 0) {
                    enabled3 = enabled4;
                    border3 = AssistChipDefaults.INSTANCE.m2181assistChipBorderh1eTWw(enabled3, 0L, 0L, 0.0f, $composer3, (($dirty4 >> 9) & 14) | 24576, 14);
                    $dirty4 &= -1879048193;
                } else {
                    enabled3 = enabled4;
                    border3 = border;
                }
                if (i2 != 0) {
                    border4 = border3;
                    interactionSource3 = null;
                    function29 = function25;
                    elevation4 = elevation3;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier5 = modifier4;
                    function210 = function211;
                    shape4 = shape3;
                } else {
                    interactionSource3 = interactionSource;
                    border4 = border3;
                    function29 = function25;
                    elevation4 = elevation3;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier5 = modifier4;
                    function210 = function211;
                    shape4 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty8 = (i & 64) != 0 ? $dirty7 & (-3670017) : $dirty7;
                if ((i & 128) != 0) {
                    $dirty8 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty8 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty8 &= -1879048193;
                }
                shape4 = shape;
                elevation4 = elevation;
                border4 = border;
                interactionSource3 = interactionSource;
                $dirty5 = $dirty8;
                i3 = 6;
                function210 = function26;
                enabled3 = z;
                function29 = function25;
                $dirty = $dirty13;
                modifier5 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1192083339, $dirty5, $dirty, "androidx.compose.material3.AssistChip (Chip.kt:146)");
            }
            boolean enabled5 = enabled3;
            ChipColors colors5 = colors2;
            $composer2 = $composer3;
            m2273ChipnkUnTEs(modifier5, function0, enabled5, function24, TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer3, i3), colors2.m2263labelColorvNxB06k$material3(enabled3), function29, function210, shape4, colors5, elevation4, border4, AssistChipDefaults.INSTANCE.m2186getHeightD9Ej5fM(), AssistChipPadding, interactionSource3, $composer2, (($dirty5 >> 6) & 14) | (($dirty5 << 3) & 112) | (($dirty5 >> 3) & 896) | (($dirty5 << 6) & 7168) | (($dirty5 << 6) & 3670016) | (($dirty5 << 6) & 29360128) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 3456 | (($dirty5 >> 24) & 112) | (($dirty << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            enabled2 = enabled5;
            function27 = function29;
            function28 = function210;
            shape2 = shape4;
            colors3 = colors5;
            elevation2 = elevation4;
            border2 = border4;
            interactionSource2 = interactionSource3;
            $dirty2 = $dirty5;
        } else {
            $dirty = $dirty13;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            border2 = border;
            interactionSource2 = interactionSource;
            colors3 = colors2;
            modifier3 = modifier2;
            function27 = function25;
            $dirty2 = $dirty7;
            function28 = function26;
            enabled2 = z;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.AssistChip$lambda$0(function0, function2, modifier3, enabled2, function27, function28, shape2, colors3, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with AssistChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "AssistChip(onClick, label, modifier, enabled,leadingIcon, trailingIcon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void AssistChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 leadingIcon, Function2 trailingIcon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2 function2;
        Modifier modifier2;
        boolean enabled2;
        Function2 leadingIcon2;
        Function2 function22;
        ChipColors colors2;
        int i2;
        int $dirty;
        Composer $composer2;
        final Shape shape2;
        final ChipBorder border2;
        final MutableInteractionSource interactionSource2;
        final ChipColors colors3;
        final Modifier modifier3;
        final Function2 leadingIcon3;
        int $dirty2;
        final Function2 trailingIcon2;
        final boolean enabled3;
        final ChipElevation elevation2;
        Shape shape3;
        int $dirty3;
        ChipColors colors4;
        int $dirty4;
        Modifier modifier4;
        int i3;
        Composer $composer3;
        ChipElevation elevation3;
        ChipBorder border3;
        MutableInteractionSource interactionSource3;
        ChipElevation elevation4;
        int $dirty5;
        Function2 leadingIcon4;
        Function2 trailingIcon3;
        Shape shape4;
        Modifier modifier5;
        State<BorderStroke> stateBorderStroke$material3;
        Composer $composer4 = $composer.startRestartGroup(1930061919);
        ComposerKt.sourceInformation($composer4, "C(AssistChip)N(onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)236@11549L5,231@11381L572:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty6 |= $composer4.changedInstance(onClick) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty6 |= $composer4.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer4.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty6 |= 24576;
            leadingIcon2 = leadingIcon;
        } else if (($changed & 24576) == 0) {
            leadingIcon2 = leadingIcon;
            $dirty6 |= $composer4.changedInstance(leadingIcon2) ? 16384 : 8192;
        } else {
            leadingIcon2 = leadingIcon;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function22 = trailingIcon;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function22 = trailingIcon;
            $dirty6 |= $composer4.changedInstance(function22) ? 131072 : 65536;
        } else {
            function22 = trailingIcon;
        }
        if (($changed & 1572864) == 0) {
            $dirty6 |= ((i & 64) == 0 && $composer4.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                int i8 = $composer4.changed(colors2) ? 8388608 : 4194304;
                $dirty6 |= i8;
            } else {
                colors2 = colors;
            }
            $dirty6 |= i8;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            $dirty6 |= ((i & 256) == 0 && $composer4.changed(elevation)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty6 |= ((i & 512) == 0 && $composer4.changed(border)) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int $dirty1 = $changed1;
        int $dirty12 = i & 1024;
        if ($dirty12 != 0) {
            $dirty1 |= 6;
            i2 = $dirty12;
        } else if (($changed1 & 6) == 0) {
            i2 = $dirty12;
            $dirty1 |= $composer4.changed(interactionSource) ? 4 : 2;
        } else {
            i2 = $dirty12;
        }
        int $dirty13 = $dirty1;
        int $dirty7 = $dirty6;
        if ($composer4.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty13 & 3) == 2) ? false : true, $dirty7 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "225@11072L5,226@11123L18,227@11194L21,228@11262L18,229@11332L39");
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i5 != 0 ? true : enabled2;
                if (i6 != 0) {
                    leadingIcon2 = null;
                }
                Function2 trailingIcon4 = i7 != 0 ? null : function22;
                if ((i & 64) != 0) {
                    $dirty3 = $dirty7 & (-3670017);
                    shape3 = AssistChipDefaults.INSTANCE.getShape($composer4, 6);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty7;
                }
                if ((i & 128) != 0) {
                    colors4 = AssistChipDefaults.INSTANCE.assistChipColors($composer4, 6);
                    $dirty4 = $dirty3 & (-29360129);
                } else {
                    colors4 = colors2;
                    $dirty4 = $dirty3;
                }
                if ((i & 256) != 0) {
                    $composer3 = $composer4;
                    modifier4 = modifier6;
                    i3 = 6;
                    $dirty = $dirty13;
                    $dirty4 &= -234881025;
                    elevation3 = AssistChipDefaults.INSTANCE.m2183assistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier4 = modifier6;
                    i3 = 6;
                    $composer3 = $composer4;
                    $dirty = $dirty13;
                    elevation3 = elevation;
                }
                if ((i & 512) != 0) {
                    $composer4 = $composer3;
                    border3 = AssistChipDefaults.INSTANCE.m2180assistChipBorderd_3_b6Q(0L, 0L, 0.0f, $composer4, 3072, 7);
                    $dirty4 &= -1879048193;
                } else {
                    $composer4 = $composer3;
                    border3 = border;
                }
                if (i2 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer4, -636337050, "CC(remember):Chip.kt#9igjgp");
                    Composer $this$cache$iv = $composer4;
                    Object it$iv = $this$cache$iv.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $this$cache$iv.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    elevation4 = elevation3;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    leadingIcon4 = leadingIcon2;
                    trailingIcon3 = trailingIcon4;
                    shape4 = shape3;
                    modifier5 = modifier4;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    leadingIcon4 = leadingIcon2;
                    trailingIcon3 = trailingIcon4;
                    shape4 = shape3;
                    modifier5 = modifier4;
                }
            } else {
                $composer4.skipToGroupEnd();
                int $dirty8 = (i & 64) != 0 ? $dirty7 & (-3670017) : $dirty7;
                if ((i & 128) != 0) {
                    $dirty8 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty8 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty8 &= -1879048193;
                }
                shape4 = shape;
                elevation4 = elevation;
                border3 = border;
                interactionSource3 = interactionSource;
                i3 = 6;
                trailingIcon3 = function22;
                leadingIcon4 = leadingIcon2;
                $dirty5 = $dirty8;
                modifier5 = modifier2;
                $dirty = $dirty13;
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1930061919, $dirty5, $dirty, "androidx.compose.material3.AssistChip (Chip.kt:231)");
            }
            TextStyle value = TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer4, i3);
            long jM2263labelColorvNxB06k$material3 = colors2.m2263labelColorvNxB06k$material3(enabled2);
            if (border3 == null) {
                $composer4.startReplaceGroup(1748832781);
                $composer4.endReplaceGroup();
                stateBorderStroke$material3 = null;
            } else {
                $composer4.startReplaceGroup(-636322700);
                ComposerKt.sourceInformation($composer4, "243@11781L21");
                stateBorderStroke$material3 = border3.borderStroke$material3(enabled2, $composer4, (($dirty5 >> 9) & 14) | (($dirty5 >> 24) & 112));
                $composer4.endReplaceGroup();
            }
            ChipColors colors5 = colors2;
            $composer2 = $composer4;
            boolean enabled5 = enabled2;
            m2273ChipnkUnTEs(modifier5, onClick, enabled5, function2, value, jM2263labelColorvNxB06k$material3, leadingIcon4, trailingIcon3, shape4, colors5, elevation4, stateBorderStroke$material3 != null ? stateBorderStroke$material3.getValue() : null, AssistChipDefaults.INSTANCE.m2186getHeightD9Ej5fM(), AssistChipPadding, interactionSource3, $composer2, (($dirty5 >> 6) & 14) | (($dirty5 << 3) & 112) | (($dirty5 >> 3) & 896) | (($dirty5 << 6) & 7168) | (($dirty5 << 6) & 3670016) | (($dirty5 << 6) & 29360128) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 3456 | (($dirty << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            modifier3 = modifier5;
            enabled3 = enabled5;
            leadingIcon3 = leadingIcon4;
            trailingIcon2 = trailingIcon3;
            shape2 = shape4;
            colors3 = colors5;
            elevation2 = elevation4;
            interactionSource2 = interactionSource3;
            $dirty2 = $dirty5;
        } else {
            $dirty = $dirty13;
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            border2 = border;
            interactionSource2 = interactionSource;
            colors3 = colors2;
            modifier3 = modifier2;
            leadingIcon3 = leadingIcon2;
            $dirty2 = $dirty7;
            trailingIcon2 = function22;
            enabled3 = enabled2;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.AssistChip$lambda$2(onClick, label, modifier3, enabled3, leadingIcon3, trailingIcon2, shape2, colors3, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ElevatedAssistChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean enabled2;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        ChipColors colors2;
        int i2;
        int i3;
        int $dirty;
        Composer $composer2;
        final Shape shape2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final ChipColors colors3;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function27;
        int $dirty2;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final boolean enabled3;
        final ChipElevation elevation2;
        Shape shape3;
        int $dirty3;
        ChipColors colors4;
        int $dirty4;
        Modifier modifier4;
        int i4;
        ChipElevation elevation3;
        MutableInteractionSource interactionSource3;
        ChipElevation elevation4;
        BorderStroke border3;
        Function2<? super Composer, ? super Integer, Unit> function29;
        int $dirty5;
        Modifier modifier5;
        Function2<? super Composer, ? super Integer, Unit> function210;
        Shape shape4;
        Composer $composer3 = $composer.startRestartGroup(2028863105);
        ComposerKt.sourceInformation($composer3, "C(ElevatedAssistChip)N(onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)310@15241L5,305@15073L542:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty6 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function24 = function2;
        } else if (($changed & 48) == 0) {
            function24 = function2;
            $dirty6 |= $composer3.changedInstance(function24) ? 32 : 16;
        } else {
            function24 = function2;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty6 |= 24576;
            function25 = function22;
        } else if (($changed & 24576) == 0) {
            function25 = function22;
            $dirty6 |= $composer3.changedInstance(function25) ? 16384 : 8192;
        } else {
            function25 = function22;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function26 = function23;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function26 = function23;
            $dirty6 |= $composer3.changedInstance(function26) ? 131072 : 65536;
        } else {
            function26 = function23;
        }
        if (($changed & 1572864) == 0) {
            $dirty6 |= ((i & 64) == 0 && $composer3.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                int i9 = $composer3.changed(colors2) ? 8388608 : 4194304;
                $dirty6 |= i9;
            } else {
                colors2 = colors;
            }
            $dirty6 |= i9;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            $dirty6 |= ((i & 256) == 0 && $composer3.changed(elevation)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int $dirty1 = $changed1;
        int $dirty12 = i & 512;
        if ($dirty12 != 0) {
            $dirty6 |= 805306368;
            i2 = $dirty12;
        } else if (($changed & 805306368) == 0) {
            i2 = $dirty12;
            $dirty6 |= $composer3.changed(border) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = $dirty12;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty1 |= 6;
            i3 = i10;
        } else if (($changed1 & 6) == 0) {
            i3 = i10;
            $dirty1 |= $composer3.changed(interactionSource) ? 4 : 2;
        } else {
            i3 = i10;
        }
        int $dirty13 = $dirty1;
        int $dirty7 = $dirty6;
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty13 & 3) == 2) ? false : true, $dirty7 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "299@14813L5,300@14864L26,301@14943L29");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i6 != 0 ? true : enabled2;
                if (i7 != 0) {
                    function25 = null;
                }
                Function2<? super Composer, ? super Integer, Unit> function211 = i8 != 0 ? null : function26;
                if ((i & 64) != 0) {
                    $dirty3 = $dirty7 & (-3670017);
                    shape3 = AssistChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty7;
                }
                if ((i & 128) != 0) {
                    colors4 = AssistChipDefaults.INSTANCE.elevatedAssistChipColors($composer3, 6);
                    $dirty4 = $dirty3 & (-29360129);
                } else {
                    colors4 = colors2;
                    $dirty4 = $dirty3;
                }
                if ((i & 256) != 0) {
                    modifier4 = modifier6;
                    $dirty = $dirty13;
                    i4 = 6;
                    elevation3 = AssistChipDefaults.INSTANCE.m2185elevatedAssistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty4 &= -234881025;
                } else {
                    modifier4 = modifier6;
                    i4 = 6;
                    $dirty = $dirty13;
                    elevation3 = elevation;
                }
                BorderStroke border4 = i2 != 0 ? null : border;
                if (i3 != 0) {
                    elevation4 = elevation3;
                    border3 = border4;
                    interactionSource3 = null;
                    function29 = function25;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier5 = modifier4;
                    function210 = function211;
                    shape4 = shape3;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    border3 = border4;
                    function29 = function25;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier5 = modifier4;
                    function210 = function211;
                    shape4 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty8 = (i & 64) != 0 ? $dirty7 & (-3670017) : $dirty7;
                if ((i & 128) != 0) {
                    $dirty8 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty8 &= -234881025;
                }
                shape4 = shape;
                elevation4 = elevation;
                border3 = border;
                interactionSource3 = interactionSource;
                $dirty5 = $dirty8;
                i4 = 6;
                function210 = function26;
                $dirty = $dirty13;
                function29 = function25;
                modifier5 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2028863105, $dirty5, $dirty, "androidx.compose.material3.ElevatedAssistChip (Chip.kt:305)");
            }
            ChipColors colors5 = colors2;
            $composer2 = $composer3;
            boolean enabled5 = enabled2;
            m2273ChipnkUnTEs(modifier5, function0, enabled5, function24, TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer3, i4), colors2.m2263labelColorvNxB06k$material3(enabled2), function29, function210, shape4, colors5, elevation4, border3, AssistChipDefaults.INSTANCE.m2186getHeightD9Ej5fM(), AssistChipPadding, interactionSource3, $composer2, (($dirty5 >> 6) & 14) | (($dirty5 << 3) & 112) | (($dirty5 >> 3) & 896) | (($dirty5 << 6) & 7168) | (($dirty5 << 6) & 3670016) | (($dirty5 << 6) & 29360128) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 3456 | (($dirty5 >> 24) & 112) | (($dirty << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            enabled3 = enabled5;
            function27 = function29;
            function28 = function210;
            shape2 = shape4;
            colors3 = colors5;
            elevation2 = elevation4;
            border2 = border3;
            interactionSource2 = interactionSource3;
            $dirty2 = $dirty5;
        } else {
            $dirty = $dirty13;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            border2 = border;
            interactionSource2 = interactionSource;
            colors3 = colors2;
            modifier3 = modifier2;
            function27 = function25;
            $dirty2 = $dirty7;
            function28 = function26;
            enabled3 = enabled2;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedAssistChip$lambda$3(function0, function2, modifier3, enabled3, function27, function28, shape2, colors3, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with ElevatedAssistChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "ElevatedAssistChip(onClick, label, modifier, enabled,leadingIcon, trailingIcon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void ElevatedAssistChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 leadingIcon, Function2 trailingIcon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2 function2;
        Modifier modifier2;
        boolean enabled2;
        Function2 leadingIcon2;
        Function2 function22;
        ChipColors colors2;
        int i2;
        int i3;
        int $dirty;
        Composer $composer2;
        final Shape shape2;
        final ChipBorder border2;
        final MutableInteractionSource interactionSource2;
        final ChipColors colors3;
        final Modifier modifier3;
        final Function2 leadingIcon3;
        int $dirty2;
        final Function2 trailingIcon2;
        final boolean enabled3;
        final ChipElevation elevation2;
        Shape shape3;
        int $dirty3;
        ChipColors colors4;
        int $dirty4;
        Modifier modifier4;
        int i4;
        ChipElevation elevation3;
        ChipBorder border3;
        MutableInteractionSource interactionSource3;
        ChipElevation elevation4;
        Function2 leadingIcon4;
        int $dirty5;
        Modifier modifier5;
        Function2 trailingIcon3;
        Shape shape4;
        State<BorderStroke> stateBorderStroke$material3;
        Composer $composer3 = $composer.startRestartGroup(-759567147);
        ComposerKt.sourceInformation($composer3, "C(ElevatedAssistChip)N(onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)394@19326L5,389@19158L572:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty6 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty6 |= $composer3.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty6 |= 24576;
            leadingIcon2 = leadingIcon;
        } else if (($changed & 24576) == 0) {
            leadingIcon2 = leadingIcon;
            $dirty6 |= $composer3.changedInstance(leadingIcon2) ? 16384 : 8192;
        } else {
            leadingIcon2 = leadingIcon;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function22 = trailingIcon;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function22 = trailingIcon;
            $dirty6 |= $composer3.changedInstance(function22) ? 131072 : 65536;
        } else {
            function22 = trailingIcon;
        }
        if (($changed & 1572864) == 0) {
            $dirty6 |= ((i & 64) == 0 && $composer3.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                int i9 = $composer3.changed(colors2) ? 8388608 : 4194304;
                $dirty6 |= i9;
            } else {
                colors2 = colors;
            }
            $dirty6 |= i9;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            $dirty6 |= ((i & 256) == 0 && $composer3.changed(elevation)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int $dirty1 = $changed1;
        int $dirty12 = i & 512;
        if ($dirty12 != 0) {
            $dirty6 |= 805306368;
            i2 = $dirty12;
        } else if (($changed & 805306368) == 0) {
            i2 = $dirty12;
            $dirty6 |= $composer3.changed(border) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = $dirty12;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty1 |= 6;
            i3 = i10;
        } else if (($changed1 & 6) == 0) {
            i3 = i10;
            $dirty1 |= $composer3.changed(interactionSource) ? 4 : 2;
        } else {
            i3 = i10;
        }
        int $dirty13 = $dirty1;
        int $dirty7 = $dirty6;
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty13 & 3) == 2) ? false : true, $dirty7 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "383@18866L5,384@18917L26,385@18996L29,387@19109L39");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i6 != 0 ? true : enabled2;
                if (i7 != 0) {
                    leadingIcon2 = null;
                }
                Function2 trailingIcon4 = i8 != 0 ? null : function22;
                if ((i & 64) != 0) {
                    $dirty3 = $dirty7 & (-3670017);
                    shape3 = AssistChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty7;
                }
                if ((i & 128) != 0) {
                    colors4 = AssistChipDefaults.INSTANCE.elevatedAssistChipColors($composer3, 6);
                    $dirty4 = $dirty3 & (-29360129);
                } else {
                    colors4 = colors2;
                    $dirty4 = $dirty3;
                }
                if ((i & 256) != 0) {
                    modifier4 = modifier6;
                    $dirty = $dirty13;
                    i4 = 6;
                    elevation3 = AssistChipDefaults.INSTANCE.m2185elevatedAssistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty4 &= -234881025;
                } else {
                    modifier4 = modifier6;
                    i4 = 6;
                    $dirty = $dirty13;
                    elevation3 = elevation;
                }
                border3 = i2 != 0 ? null : border;
                if (i3 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 200442940, "CC(remember):Chip.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    elevation4 = elevation3;
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    leadingIcon4 = leadingIcon2;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier5 = modifier4;
                    trailingIcon3 = trailingIcon4;
                    shape4 = shape3;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    leadingIcon4 = leadingIcon2;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier5 = modifier4;
                    trailingIcon3 = trailingIcon4;
                    shape4 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty8 = (i & 64) != 0 ? $dirty7 & (-3670017) : $dirty7;
                if ((i & 128) != 0) {
                    $dirty8 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty8 &= -234881025;
                }
                shape4 = shape;
                elevation4 = elevation;
                interactionSource3 = interactionSource;
                $dirty5 = $dirty8;
                i4 = 6;
                trailingIcon3 = function22;
                $dirty = $dirty13;
                border3 = border;
                leadingIcon4 = leadingIcon2;
                modifier5 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-759567147, $dirty5, $dirty, "androidx.compose.material3.ElevatedAssistChip (Chip.kt:389)");
            }
            TextStyle value = TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer3, i4);
            long jM2263labelColorvNxB06k$material3 = colors2.m2263labelColorvNxB06k$material3(enabled2);
            float fM2186getHeightD9Ej5fM = AssistChipDefaults.INSTANCE.m2186getHeightD9Ej5fM();
            PaddingValues paddingValues = AssistChipPadding;
            if (border3 == null) {
                $composer3.startReplaceGroup(1919297975);
                $composer3.endReplaceGroup();
                stateBorderStroke$material3 = null;
            } else {
                $composer3.startReplaceGroup(200460170);
                ComposerKt.sourceInformation($composer3, "403@19648L21");
                stateBorderStroke$material3 = border3.borderStroke$material3(enabled2, $composer3, (($dirty5 >> 9) & 14) | (($dirty5 >> 24) & 112));
                $composer3.endReplaceGroup();
            }
            ChipColors colors5 = colors2;
            $composer2 = $composer3;
            boolean enabled5 = enabled2;
            m2273ChipnkUnTEs(modifier5, onClick, enabled5, function2, value, jM2263labelColorvNxB06k$material3, leadingIcon4, trailingIcon3, shape4, colors5, elevation4, stateBorderStroke$material3 != null ? stateBorderStroke$material3.getValue() : null, fM2186getHeightD9Ej5fM, paddingValues, interactionSource3, $composer2, (($dirty5 >> 6) & 14) | (($dirty5 << 3) & 112) | (($dirty5 >> 3) & 896) | (($dirty5 << 6) & 7168) | (($dirty5 << 6) & 3670016) | (($dirty5 << 6) & 29360128) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 3456 | (($dirty << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            modifier3 = modifier5;
            enabled3 = enabled5;
            leadingIcon3 = leadingIcon4;
            trailingIcon2 = trailingIcon3;
            shape2 = shape4;
            colors3 = colors5;
            elevation2 = elevation4;
            interactionSource2 = interactionSource3;
            $dirty2 = $dirty5;
        } else {
            $dirty = $dirty13;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            border2 = border;
            interactionSource2 = interactionSource;
            colors3 = colors2;
            modifier3 = modifier2;
            leadingIcon3 = leadingIcon2;
            $dirty2 = $dirty7;
            trailingIcon2 = function22;
            enabled3 = enabled2;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedAssistChip$lambda$5(onClick, label, modifier3, enabled3, leadingIcon3, trailingIcon2, shape2, colors3, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void FilterChip(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, SelectableChipColors colors, SelectableChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean z2;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Shape shape2;
        int i2;
        MutableInteractionSource mutableInteractionSource;
        int $dirty1;
        Composer $composer2;
        final SelectableChipElevation elevation2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final Shape shape3;
        final Function2<? super Composer, ? super Integer, Unit> function27;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final boolean enabled2;
        final Modifier modifier3;
        final SelectableChipColors colors2;
        int i3;
        boolean enabled3;
        int $dirty;
        Shape shape4;
        SelectableChipColors colors3;
        int $dirty2;
        Modifier modifier4;
        int $dirty3;
        int i4;
        SelectableChipElevation elevation3;
        int $dirty4;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        SelectableChipElevation elevation4;
        int $dirty5;
        BorderStroke border4;
        boolean enabled4;
        int $dirty12;
        Function2<? super Composer, ? super Integer, Unit> function29;
        Function2<? super Composer, ? super Integer, Unit> function210;
        Shape shape5;
        SelectableChipColors colors4;
        Modifier modifier5;
        Composer $composer3 = $composer.startRestartGroup(-1385473344);
        ComposerKt.sourceInformation($composer3, "C(FilterChip)N(selected,onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)480@23562L5,474@23355L555:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        int $dirty13 = $changed1;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty6 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty6 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty6 |= 384;
            function24 = function2;
        } else if (($changed & 384) == 0) {
            function24 = function2;
            $dirty6 |= $composer3.changedInstance(function24) ? 256 : 128;
        } else {
            function24 = function2;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty6 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty6 |= 24576;
            z2 = enabled;
        } else if (($changed & 24576) == 0) {
            z2 = enabled;
            $dirty6 |= $composer3.changed(z2) ? 16384 : 8192;
        } else {
            z2 = enabled;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function25 = function22;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function25 = function22;
            $dirty6 |= $composer3.changedInstance(function25) ? 131072 : 65536;
        } else {
            function25 = function22;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty6 |= 1572864;
            function26 = function23;
        } else if (($changed & 1572864) == 0) {
            function26 = function23;
            $dirty6 |= $composer3.changedInstance(function26) ? 1048576 : 524288;
        } else {
            function26 = function23;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                shape2 = shape;
                int i9 = $composer3.changed(shape2) ? 8388608 : 4194304;
                $dirty6 |= i9;
            } else {
                shape2 = shape;
            }
            $dirty6 |= i9;
        } else {
            shape2 = shape;
        }
        if (($changed & 100663296) == 0) {
            $dirty6 |= ((i & 256) == 0 && $composer3.changed(colors)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty6 |= ((i & 512) == 0 && $composer3.changed(elevation)) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($changed1 & 6) == 0) {
            $dirty13 |= ((i & 1024) == 0 && $composer3.changed(border)) ? 4 : 2;
        }
        int $dirty14 = $dirty13;
        int $dirty15 = i & 2048;
        if ($dirty15 != 0) {
            $dirty1 = $dirty14 | 48;
            i2 = $dirty15;
            mutableInteractionSource = interactionSource;
        } else if (($changed1 & 48) == 0) {
            i2 = $dirty15;
            mutableInteractionSource = interactionSource;
            $dirty1 = $dirty14 | ($composer3.changed(mutableInteractionSource) ? 32 : 16);
        } else {
            i2 = $dirty15;
            mutableInteractionSource = interactionSource;
            $dirty1 = $dirty14;
        }
        int $dirty7 = $dirty6;
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty1 & 19) == 18) ? false : true, $dirty7 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "468@23041L5,469@23102L18,470@23183L21,471@23253L35");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if (i6 != 0) {
                    int i10 = i2;
                    enabled3 = true;
                    i3 = i10;
                } else {
                    i3 = i2;
                    enabled3 = z2;
                }
                Function2<? super Composer, ? super Integer, Unit> function211 = i7 != 0 ? null : function25;
                Function2<? super Composer, ? super Integer, Unit> function212 = i8 != 0 ? null : function26;
                if ((i & 128) != 0) {
                    $dirty = $dirty7 & (-29360129);
                    shape4 = FilterChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty7;
                    shape4 = shape2;
                }
                if ((i & 256) != 0) {
                    colors3 = FilterChipDefaults.INSTANCE.filterChipColors($composer3, 6);
                    $dirty2 = $dirty & (-234881025);
                } else {
                    colors3 = colors;
                    $dirty2 = $dirty;
                }
                if ((i & 512) != 0) {
                    modifier4 = modifier6;
                    $dirty3 = 6;
                    i4 = i3;
                    elevation3 = FilterChipDefaults.INSTANCE.m2545filterChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty4 = $dirty2 & (-1879048193);
                } else {
                    modifier4 = modifier6;
                    $dirty3 = 6;
                    i4 = i3;
                    elevation3 = elevation;
                    $dirty4 = $dirty2;
                }
                if ((i & 1024) != 0) {
                    border3 = FilterChipDefaults.INSTANCE.m2543filterChipBorder_7El2pE(enabled3, z, 0L, 0L, 0L, 0L, 0.0f, 0.0f, $composer3, (($dirty4 >> 12) & 14) | 100663296 | (($dirty4 << 3) & 112), 252);
                    $dirty1 &= -15;
                } else {
                    border3 = border;
                }
                if (i4 != 0) {
                    elevation4 = elevation3;
                    $dirty5 = $dirty4;
                    border4 = border3;
                    interactionSource3 = null;
                    enabled4 = enabled3;
                    $dirty12 = $dirty1;
                    function29 = function211;
                    function210 = function212;
                    shape5 = shape4;
                    colors4 = colors3;
                    modifier5 = modifier4;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    $dirty5 = $dirty4;
                    border4 = border3;
                    enabled4 = enabled3;
                    $dirty12 = $dirty1;
                    function29 = function211;
                    function210 = function212;
                    shape5 = shape4;
                    colors4 = colors3;
                    modifier5 = modifier4;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty8 = (i & 128) != 0 ? $dirty7 & (-29360129) : $dirty7;
                if ((i & 256) != 0) {
                    $dirty8 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty8 &= -1879048193;
                }
                if ((i & 1024) != 0) {
                    $dirty12 = $dirty1 & (-15);
                    $dirty5 = $dirty8;
                    $dirty3 = 6;
                    colors4 = colors;
                    elevation4 = elevation;
                    border4 = border;
                    interactionSource3 = mutableInteractionSource;
                    enabled4 = z2;
                    function29 = function25;
                    function210 = function26;
                    shape5 = shape2;
                    modifier5 = modifier2;
                } else {
                    $dirty5 = $dirty8;
                    $dirty3 = 6;
                    colors4 = colors;
                    elevation4 = elevation;
                    border4 = border;
                    interactionSource3 = mutableInteractionSource;
                    enabled4 = z2;
                    function29 = function25;
                    function210 = function26;
                    shape5 = shape2;
                    modifier5 = modifier2;
                    $dirty12 = $dirty1;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1385473344, $dirty5, $dirty12, "androidx.compose.material3.FilterChip (Chip.kt:474)");
            }
            $composer2 = $composer3;
            m2275SelectableChipu0RnIRE(selected, modifier5, function02, enabled4, function24, TypographyKt.getValue(FilterChipTokens.INSTANCE.getLabelTextFont(), $composer3, $dirty3), function29, null, function210, shape5, colors4, elevation4, border4, FilterChipDefaults.INSTANCE.m2546getHeightD9Ej5fM(), FilterChipPadding, interactionSource3, $composer2, ($dirty5 & 14) | 12582912 | (($dirty5 >> 6) & 112) | (($dirty5 << 3) & 896) | (($dirty5 >> 3) & 7168) | (($dirty5 << 6) & 57344) | (($dirty5 << 3) & 3670016) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 27648 | (($dirty5 >> 24) & 112) | (($dirty12 << 6) & 896) | (($dirty12 << 12) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            enabled2 = enabled4;
            function28 = function29;
            function27 = function210;
            shape3 = shape5;
            colors2 = colors4;
            elevation2 = elevation4;
            border2 = border4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            elevation2 = elevation;
            border2 = border;
            interactionSource2 = interactionSource;
            shape3 = shape2;
            function27 = function26;
            function28 = function25;
            enabled2 = z2;
            modifier3 = modifier2;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.FilterChip$lambda$6(selected, function0, function2, modifier3, enabled2, function28, function27, shape3, colors2, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ElevatedFilterChip(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, SelectableChipColors colors, SelectableChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Shape shape2;
        int i2;
        int $dirty1;
        int i3;
        int $dirty;
        Composer $composer2;
        final SelectableChipElevation elevation2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final Shape shape3;
        final Function2<? super Composer, ? super Integer, Unit> function27;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final boolean enabled2;
        final Modifier modifier3;
        final SelectableChipColors colors2;
        int $dirty2;
        Shape shape4;
        SelectableChipColors colors3;
        int $dirty3;
        Modifier modifier4;
        int i4;
        SelectableChipElevation elevation3;
        MutableInteractionSource interactionSource3;
        SelectableChipElevation elevation4;
        BorderStroke border3;
        Function2<? super Composer, ? super Integer, Unit> function29;
        Function2<? super Composer, ? super Integer, Unit> function210;
        boolean enabled3;
        int $dirty4;
        int i5;
        SelectableChipColors colors4;
        Shape shape5;
        Modifier modifier5;
        Composer $composer3 = $composer.startRestartGroup(1533553846);
        ComposerKt.sourceInformation($composer3, "C(ElevatedFilterChip)N(selected,onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)562@27590L5,556@27383L555:Chip.kt#uh7d8r");
        int $dirty5 = $changed;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty5 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty5 |= 384;
            function24 = function2;
        } else if (($changed & 384) == 0) {
            function24 = function2;
            $dirty5 |= $composer3.changedInstance(function24) ? 256 : 128;
        } else {
            function24 = function2;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty5 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty5 |= 24576;
            z = enabled;
        } else if (($changed & 24576) == 0) {
            z = enabled;
            $dirty5 |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = enabled;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function25 = function22;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function25 = function22;
            $dirty5 |= $composer3.changedInstance(function25) ? 131072 : 65536;
        } else {
            function25 = function22;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty5 |= 1572864;
            function26 = function23;
        } else if (($changed & 1572864) == 0) {
            function26 = function23;
            $dirty5 |= $composer3.changedInstance(function26) ? 1048576 : 524288;
        } else {
            function26 = function23;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                shape2 = shape;
                int i10 = $composer3.changed(shape2) ? 8388608 : 4194304;
                $dirty5 |= i10;
            } else {
                shape2 = shape;
            }
            $dirty5 |= i10;
        } else {
            shape2 = shape;
        }
        if (($changed & 100663296) == 0) {
            $dirty5 |= ((i & 256) == 0 && $composer3.changed(colors)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty5 |= ((i & 512) == 0 && $composer3.changed(elevation)) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int $dirty12 = i & 1024;
        if ($dirty12 != 0) {
            $dirty1 = $changed1 | 6;
            i2 = $dirty12;
        } else if (($changed1 & 6) == 0) {
            i2 = $dirty12;
            $dirty1 = $changed1 | ($composer3.changed(border) ? 4 : 2);
        } else {
            i2 = $dirty12;
            $dirty1 = $changed1;
        }
        int i11 = i & 2048;
        if (i11 != 0) {
            $dirty1 |= 48;
            i3 = i11;
        } else if (($changed1 & 48) == 0) {
            i3 = i11;
            $dirty1 |= $composer3.changed(interactionSource) ? 32 : 16;
        } else {
            i3 = i11;
        }
        int $dirty13 = $dirty1;
        int $dirty6 = $dirty5;
        if ($composer3.shouldExecute((($dirty5 & 306783379) == 306783378 && ($dirty13 & 19) == 18) ? false : true, $dirty6 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "550@27103L5,551@27164L26,552@27253L29");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i7 != 0 ? true : z;
                Function2<? super Composer, ? super Integer, Unit> function211 = i8 != 0 ? null : function25;
                Function2<? super Composer, ? super Integer, Unit> function212 = i9 != 0 ? null : function26;
                if ((i & 128) != 0) {
                    $dirty2 = $dirty6 & (-29360129);
                    shape4 = FilterChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty2 = $dirty6;
                    shape4 = shape2;
                }
                if ((i & 256) != 0) {
                    colors3 = FilterChipDefaults.INSTANCE.elevatedFilterChipColors($composer3, 6);
                    $dirty3 = $dirty2 & (-234881025);
                } else {
                    colors3 = colors;
                    $dirty3 = $dirty2;
                }
                if ((i & 512) != 0) {
                    modifier4 = modifier6;
                    $dirty = $dirty13;
                    i4 = 6;
                    elevation3 = FilterChipDefaults.INSTANCE.m2542elevatedFilterChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty3 &= -1879048193;
                } else {
                    modifier4 = modifier6;
                    i4 = 6;
                    $dirty = $dirty13;
                    elevation3 = elevation;
                }
                BorderStroke border4 = i2 != 0 ? null : border;
                if (i3 != 0) {
                    elevation4 = elevation3;
                    border3 = border4;
                    interactionSource3 = null;
                    function29 = function211;
                    function210 = function212;
                    enabled3 = enabled4;
                    $dirty4 = $dirty3;
                    i5 = 1533553846;
                    colors4 = colors3;
                    shape5 = shape4;
                    modifier5 = modifier4;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    border3 = border4;
                    function29 = function211;
                    function210 = function212;
                    enabled3 = enabled4;
                    $dirty4 = $dirty3;
                    i5 = 1533553846;
                    colors4 = colors3;
                    shape5 = shape4;
                    modifier5 = modifier4;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty7 = (i & 128) != 0 ? $dirty6 & (-29360129) : $dirty6;
                if ((i & 256) != 0) {
                    $dirty7 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty7 &= -1879048193;
                }
                colors4 = colors;
                elevation4 = elevation;
                border3 = border;
                interactionSource3 = interactionSource;
                $dirty4 = $dirty7;
                i4 = 6;
                function29 = function25;
                function210 = function26;
                shape5 = shape2;
                modifier5 = modifier2;
                $dirty = $dirty13;
                i5 = 1533553846;
                enabled3 = z;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i5, $dirty4, $dirty, "androidx.compose.material3.ElevatedFilterChip (Chip.kt:556)");
            }
            $composer2 = $composer3;
            m2275SelectableChipu0RnIRE(selected, modifier5, function02, enabled3, function24, TypographyKt.getValue(FilterChipTokens.INSTANCE.getLabelTextFont(), $composer3, i4), function29, null, function210, shape5, colors4, elevation4, border3, FilterChipDefaults.INSTANCE.m2546getHeightD9Ej5fM(), FilterChipPadding, interactionSource3, $composer2, ($dirty4 & 14) | 12582912 | (($dirty4 >> 6) & 112) | (($dirty4 << 3) & 896) | (($dirty4 >> 3) & 7168) | (($dirty4 << 6) & 57344) | (3670016 & ($dirty4 << 3)) | (($dirty4 << 6) & 234881024) | (($dirty4 << 6) & 1879048192), (($dirty4 >> 24) & 14) | 27648 | (($dirty4 >> 24) & 112) | (($dirty << 6) & 896) | (($dirty << 12) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            enabled2 = enabled3;
            function28 = function29;
            function27 = function210;
            shape3 = shape5;
            colors2 = colors4;
            elevation2 = elevation4;
            border2 = border3;
            interactionSource2 = interactionSource3;
        } else {
            $dirty = $dirty13;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            elevation2 = elevation;
            border2 = border;
            interactionSource2 = interactionSource;
            shape3 = shape2;
            function27 = function26;
            function28 = function25;
            enabled2 = z;
            modifier3 = modifier2;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedFilterChip$lambda$7(selected, function0, function2, modifier3, enabled2, function28, function27, shape3, colors2, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void InputChip(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Shape shape, SelectableChipColors colors, SelectableChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function02;
        Modifier modifier2;
        boolean z2;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Composer $composer2;
        final Shape shape2;
        final SelectableChipColors colors2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final Modifier modifier3;
        final boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        final Function2<? super Composer, ? super Integer, Unit> function210;
        final SelectableChipElevation elevation2;
        Modifier.Companion modifier4;
        boolean enabled3;
        Function2<? super Composer, ? super Integer, Unit> function211;
        Function2<? super Composer, ? super Integer, Unit> function212;
        Function2<? super Composer, ? super Integer, Unit> function213;
        Shape shape3;
        int $dirty;
        SelectableChipColors colors3;
        int $dirty2;
        Modifier modifier5;
        int $dirty3;
        int i2;
        SelectableChipElevation elevation3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        SelectableChipElevation elevation4;
        BorderStroke border4;
        boolean enabled4;
        Function2<? super Composer, ? super Integer, Unit> function214;
        Function2<? super Composer, ? super Integer, Unit> function215;
        Shape shape4;
        SelectableChipColors colors4;
        int $dirty4;
        Modifier modifier6;
        boolean z3;
        Function2 shapedAvatar;
        Composer $composer3 = $composer.startRestartGroup(-1975409271);
        ComposerKt.sourceInformation($composer3, "C(InputChip)N(selected,onClick,label,modifier,enabled,leadingIcon,avatar,trailingIcon,shape,colors,elevation,border,interactionSource)673@32653L5,667@32447L747:Chip.kt#uh7d8r");
        int $dirty5 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty5 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty5 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty5 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty5 |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty5 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty5 |= 24576;
            z2 = enabled;
        } else if (($changed & 24576) == 0) {
            z2 = enabled;
            $dirty5 |= $composer3.changed(z2) ? 16384 : 8192;
        } else {
            z2 = enabled;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function25 = function22;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function25 = function22;
            $dirty5 |= $composer3.changedInstance(function25) ? 131072 : 65536;
        } else {
            function25 = function22;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty5 |= 1572864;
            function26 = function23;
        } else if (($changed & 1572864) == 0) {
            function26 = function23;
            $dirty5 |= $composer3.changedInstance(function26) ? 1048576 : 524288;
        } else {
            function26 = function23;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty5 |= 12582912;
            function27 = function24;
        } else if (($changed & 12582912) == 0) {
            function27 = function24;
            $dirty5 |= $composer3.changedInstance(function27) ? 8388608 : 4194304;
        } else {
            function27 = function24;
        }
        if (($changed & 100663296) == 0) {
            $dirty5 |= ((i & 256) == 0 && $composer3.changed(shape)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty5 |= ((i & 512) == 0 && $composer3.changed(colors)) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= ((i & 1024) == 0 && $composer3.changed(elevation)) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= ((i & 2048) == 0 && $composer3.changed(border)) ? 32 : 16;
        }
        int i8 = i & 4096;
        if (i8 != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 256 : 128;
        }
        int $dirty6 = $dirty5;
        if ($composer3.shouldExecute((($dirty5 & 306783379) == 306783378 && ($dirty1 & 147) == 146) ? false : true, $dirty6 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "640@31314L5,641@31374L17,642@31453L20,643@31521L34");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty7 = (i & 256) != 0 ? $dirty6 & (-234881025) : $dirty6;
                if ((i & 512) != 0) {
                    $dirty7 &= -1879048193;
                }
                if ((i & 1024) != 0) {
                    $dirty1 &= -15;
                }
                if ((i & 2048) != 0) {
                    $dirty1 &= -113;
                }
                $dirty4 = $dirty7;
                $dirty3 = 6;
                shape4 = shape;
                colors4 = colors;
                elevation4 = elevation;
                border4 = border;
                interactionSource3 = interactionSource;
                modifier6 = modifier2;
                enabled4 = z2;
                function214 = function25;
                function215 = function27;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 == 0) {
                    enabled3 = z2;
                } else {
                    enabled3 = true;
                }
                if (i5 == 0) {
                    function211 = function25;
                } else {
                    function211 = null;
                }
                if (i6 == 0) {
                    function212 = function26;
                } else {
                    function212 = null;
                }
                if (i7 == 0) {
                    function213 = function27;
                } else {
                    function213 = null;
                }
                if ((i & 256) == 0) {
                    shape3 = shape;
                    $dirty = $dirty6;
                } else {
                    $dirty = $dirty6 & (-234881025);
                    shape3 = InputChipDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 512) == 0) {
                    colors3 = colors;
                    $dirty2 = $dirty;
                } else {
                    colors3 = InputChipDefaults.INSTANCE.inputChipColors($composer3, 6);
                    $dirty2 = $dirty & (-1879048193);
                }
                if ((i & 1024) == 0) {
                    modifier5 = modifier4;
                    $dirty3 = 6;
                    i2 = i8;
                    elevation3 = elevation;
                } else {
                    modifier5 = modifier4;
                    i2 = i8;
                    $dirty3 = 6;
                    elevation3 = InputChipDefaults.INSTANCE.m2627inputChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty1 &= -15;
                }
                if ((i & 2048) == 0) {
                    border3 = border;
                } else {
                    border3 = InputChipDefaults.INSTANCE.m2625inputChipBorder_7El2pE(enabled3, z, 0L, 0L, 0L, 0L, 0.0f, 0.0f, $composer3, (($dirty2 >> 12) & 14) | 100663296 | (($dirty2 << 3) & 112), 252);
                    $dirty1 &= -113;
                }
                if (i2 == 0) {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    border4 = border3;
                    enabled4 = enabled3;
                    function214 = function211;
                    function26 = function212;
                    function215 = function213;
                    shape4 = shape3;
                    colors4 = colors3;
                    $dirty4 = $dirty2;
                    modifier6 = modifier5;
                } else {
                    elevation4 = elevation3;
                    border4 = border3;
                    interactionSource3 = null;
                    enabled4 = enabled3;
                    function214 = function211;
                    function26 = function212;
                    function215 = function213;
                    shape4 = shape3;
                    colors4 = colors3;
                    $dirty4 = $dirty2;
                    modifier6 = modifier5;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1975409271, $dirty4, $dirty1, "androidx.compose.material3.InputChip (Chip.kt:645)");
            }
            if (function26 != null) {
                $composer3.startReplaceGroup(225175787);
                ComposerKt.sourceInformation($composer3, "651@31963L5,653@32016L420");
                float avatarOpacity = enabled4 ? 1.0f : InputChipTokens.INSTANCE.getDisabledAvatarOpacity();
                Shape avatarShape = ShapesKt.getValue(InputChipTokens.INSTANCE.getAvatarShape(), $composer3, $dirty3);
                z3 = true;
                Function2 shapedAvatar2 = ComposableLambdaKt.rememberComposableLambda(-570452295, true, new AnonymousClass1(avatarOpacity, avatarShape, function26), $composer3, 54);
                $composer3.endReplaceGroup();
                shapedAvatar = shapedAvatar2;
            } else {
                z3 = true;
                $composer3.startReplaceGroup(225773529);
                $composer3.endReplaceGroup();
                shapedAvatar = null;
            }
            TextStyle value = TypographyKt.getValue(InputChipTokens.INSTANCE.getLabelTextFont(), $composer3, $dirty3);
            float fM2623getHeightD9Ej5fM = InputChipDefaults.INSTANCE.m2623getHeightD9Ej5fM();
            boolean z4 = shapedAvatar != null ? z3 : false;
            boolean z5 = function214 != null ? z3 : false;
            if (function215 == null) {
                z3 = false;
            }
            $composer2 = $composer3;
            m2275SelectableChipu0RnIRE(selected, modifier6, function02, enabled4, function2, value, function214, shapedAvatar, function215, shape4, colors4, elevation4, border4, fM2623getHeightD9Ej5fM, inputChipPadding(z4, z5, z3), interactionSource3, $composer2, ($dirty4 & 14) | (($dirty4 >> 6) & 112) | (($dirty4 << 3) & 896) | (($dirty4 >> 3) & 7168) | (($dirty4 << 6) & 57344) | (($dirty4 << 3) & 3670016) | (($dirty4 << 3) & 234881024) | (($dirty4 << 3) & 1879048192), (($dirty4 >> 27) & 14) | 3072 | (($dirty1 << 3) & 112) | (($dirty1 << 3) & 896) | (($dirty1 << 9) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function29 = function26;
            modifier3 = modifier6;
            enabled2 = enabled4;
            function28 = function214;
            function210 = function215;
            shape2 = shape4;
            colors2 = colors4;
            elevation2 = elevation4;
            border2 = border4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            colors2 = colors;
            border2 = border;
            interactionSource2 = interactionSource;
            modifier3 = modifier2;
            enabled2 = z2;
            function28 = function25;
            function29 = function26;
            function210 = function27;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.InputChip$lambda$8(selected, function0, function2, modifier3, enabled2, function28, function29, function210, shape2, colors2, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$InputChip$1 */
    /* JADX INFO: compiled from: Chip.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ Function2<Composer, Integer, Unit> $avatar;
        final /* synthetic */ float $avatarOpacity;
        final /* synthetic */ Shape $avatarShape;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(float f, Shape shape, Function2<? super Composer, ? super Integer, Unit> function2) {
            this.$avatarOpacity = f;
            this.$avatarShape = shape;
            this.$avatar = function2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C656@32117L180,654@32034L388:Chip.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-570452295, $changed, -1, "androidx.compose.material3.InputChip.<anonymous> (Chip.kt:654)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1551161805, "CC(remember):Chip.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$avatarOpacity) | $composer.changed(this.$avatarShape);
            final float f = this.$avatarOpacity;
            final Shape shape = this.$avatarShape;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.ChipKt$InputChip$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ChipKt.AnonymousClass1.invoke$lambda$1$lambda$0(f, shape, (GraphicsLayerScope) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) it$iv);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            Function2<Composer, Integer, Unit> function2 = this.$avatar;
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
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
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1351092324, "C663@32396L8:Chip.kt#uh7d8r");
            function2.invoke($composer, 0);
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

        static final Unit invoke$lambda$1$lambda$0(float $avatarOpacity, Shape $avatarShape, GraphicsLayerScope $this$graphicsLayer) {
            $this$graphicsLayer.setAlpha($avatarOpacity);
            $this$graphicsLayer.setShape($avatarShape);
            $this$graphicsLayer.setClip(true);
            return Unit.INSTANCE;
        }
    }

    public static final void SuggestionChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function23;
        final Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Shape shape2;
        ChipColors colors2;
        int i2;
        ChipElevation elevation2;
        int i3;
        Composer $composer2;
        final boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function25;
        final Shape shape3;
        final ChipColors colors3;
        final ChipElevation elevation3;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        Shape shape4;
        ChipColors colors4;
        int $dirty2;
        Modifier modifier3;
        int i4;
        boolean enabled3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        BorderStroke border4;
        Function2<? super Composer, ? super Integer, Unit> function26;
        int $dirty3;
        ChipElevation elevation4;
        Shape shape5;
        boolean enabled4;
        Composer $composer3 = $composer.startRestartGroup(-252243183);
        ComposerKt.sourceInformation($composer3, "C(SuggestionChip)N(onClick,label,modifier,enabled,icon,shape,colors,elevation,border,interactionSource)751@36361L5,746@36189L539:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty4 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function23 = function2;
        } else if (($changed & 48) == 0) {
            function23 = function2;
            $dirty4 |= $composer3.changedInstance(function23) ? 32 : 16;
        } else {
            function23 = function2;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty4 |= 24576;
            function24 = function22;
        } else if (($changed & 24576) == 0) {
            function24 = function22;
            $dirty4 |= $composer3.changedInstance(function24) ? 16384 : 8192;
        } else {
            function24 = function22;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i8 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty4 |= i8;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i8;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i9 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty4 |= i9;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i9;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            i2 = 12582912;
            if ((i & 128) == 0) {
                elevation2 = elevation;
                int i10 = $composer3.changed(elevation2) ? 8388608 : 4194304;
                $dirty4 |= i10;
            } else {
                elevation2 = elevation;
            }
            $dirty4 |= i10;
        } else {
            i2 = 12582912;
            elevation2 = elevation;
        }
        if (($changed & 100663296) == 0) {
            $dirty4 |= ((i & 256) == 0 && $composer3.changed(border)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i11 = i & 512;
        if (i11 != 0) {
            $dirty4 |= 805306368;
            i3 = i11;
        } else if (($changed & 805306368) == 0) {
            i3 = i11;
            $dirty4 |= $composer3.changed(interactionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i3 = i11;
        }
        int $dirty5 = $dirty4;
        if ($composer3.shouldExecute(($dirty4 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "740@35881L5,741@35936L22,742@36015L25,743@36093L29");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled5 = i6 != 0 ? true : z;
                Function2<? super Composer, ? super Integer, Unit> function27 = i7 != 0 ? null : function24;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape4 = SuggestionChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape4 = shape2;
                }
                if ((i & 64) != 0) {
                    colors4 = SuggestionChipDefaults.INSTANCE.suggestionChipColors($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors4 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    modifier3 = modifier4;
                    i4 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m3013suggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier3 = modifier4;
                    i4 = 6;
                }
                if ((i & 256) != 0) {
                    enabled3 = enabled5;
                    border3 = SuggestionChipDefaults.INSTANCE.m3011suggestionChipBorderh1eTWw(enabled3, 0L, 0L, 0.0f, $composer3, (($dirty2 >> 9) & 14) | 24576, 14);
                    $dirty2 &= -234881025;
                } else {
                    enabled3 = enabled5;
                    border3 = border;
                }
                if (i3 != 0) {
                    border4 = border3;
                    interactionSource3 = null;
                    colors2 = colors4;
                    function26 = function27;
                    $dirty3 = $dirty2;
                    modifier2 = modifier3;
                    elevation4 = elevation2;
                    shape5 = shape4;
                    enabled4 = enabled3;
                } else {
                    modifier2 = modifier3;
                    interactionSource3 = interactionSource;
                    border4 = border3;
                    colors2 = colors4;
                    function26 = function27;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    shape5 = shape4;
                    enabled4 = enabled3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                if ((i & 256) != 0) {
                    border4 = border;
                    interactionSource3 = interactionSource;
                    $dirty3 = $dirty5 & (-234881025);
                    i4 = 6;
                    function26 = function24;
                    shape5 = shape2;
                    elevation4 = elevation2;
                    enabled4 = z;
                } else {
                    border4 = border;
                    interactionSource3 = interactionSource;
                    i4 = 6;
                    function26 = function24;
                    shape5 = shape2;
                    elevation4 = elevation2;
                    $dirty3 = $dirty5;
                    enabled4 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-252243183, $dirty3, -1, "androidx.compose.material3.SuggestionChip (Chip.kt:746)");
            }
            $composer2 = $composer3;
            ChipColors colors5 = colors2;
            Function2<? super Composer, ? super Integer, Unit> function28 = function23;
            Modifier modifier5 = modifier2;
            m2273ChipnkUnTEs(modifier5, function02, enabled4, function28, TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer3, i4), colors2.m2263labelColorvNxB06k$material3(enabled4), function26, null, shape5, colors5, elevation4, border4, SuggestionChipDefaults.INSTANCE.m3008getHeightD9Ej5fM(), SuggestionChipPadding, interactionSource3, $composer2, (($dirty3 >> 6) & 14) | i2 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (($dirty3 << 9) & 1879048192), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 21) & 112) | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier5;
            enabled2 = enabled4;
            function25 = function26;
            shape3 = shape5;
            colors3 = colors5;
            elevation3 = elevation4;
            border2 = border4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            enabled2 = z;
            function25 = function24;
            shape3 = shape2;
            colors3 = colors2;
            elevation3 = elevation2;
            border2 = border;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.SuggestionChip$lambda$9(function0, function2, modifier2, enabled2, function25, shape3, colors3, elevation3, border2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with SuggestionChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "SuggestionChip(onClick, label, modifier, enabled, icon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void SuggestionChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 icon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Function2 function2;
        final Modifier modifier2;
        boolean z;
        Function2 function22;
        Shape shape2;
        ChipColors colors2;
        int i2;
        ChipElevation elevation2;
        int i3;
        Composer $composer2;
        final boolean enabled2;
        final Function2 icon2;
        final Shape shape3;
        final ChipColors colors3;
        final ChipElevation elevation3;
        final ChipBorder border2;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        Shape shape4;
        ChipColors colors4;
        int $dirty2;
        Modifier modifier3;
        int i4;
        Composer $composer3;
        ChipBorder border3;
        MutableInteractionSource interactionSource3;
        Function2 icon3;
        int $dirty3;
        ChipElevation elevation4;
        boolean enabled3;
        Shape shape5;
        State<BorderStroke> stateBorderStroke$material3;
        Composer $composer4 = $composer.startRestartGroup(270460261);
        ComposerKt.sourceInformation($composer4, "C(SuggestionChip)N(onClick,label,modifier,enabled,icon,shape,colors,elevation,border,interactionSource)833@40281L5,828@40109L569:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function0 = onClick;
        } else if (($changed & 6) == 0) {
            function0 = onClick;
            $dirty4 |= $composer4.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = onClick;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty4 |= $composer4.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer4.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty4 |= 24576;
            function22 = icon;
        } else if (($changed & 24576) == 0) {
            function22 = icon;
            $dirty4 |= $composer4.changedInstance(function22) ? 16384 : 8192;
        } else {
            function22 = icon;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i8 = $composer4.changed(shape2) ? 131072 : 65536;
                $dirty4 |= i8;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i8;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i9 = $composer4.changed(colors2) ? 1048576 : 524288;
                $dirty4 |= i9;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i9;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            i2 = 12582912;
            if ((i & 128) == 0) {
                elevation2 = elevation;
                int i10 = $composer4.changed(elevation2) ? 8388608 : 4194304;
                $dirty4 |= i10;
            } else {
                elevation2 = elevation;
            }
            $dirty4 |= i10;
        } else {
            i2 = 12582912;
            elevation2 = elevation;
        }
        if (($changed & 100663296) == 0) {
            $dirty4 |= ((i & 256) == 0 && $composer4.changed(border)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i11 = i & 512;
        if (i11 != 0) {
            $dirty4 |= 805306368;
            i3 = i11;
        } else if (($changed & 805306368) == 0) {
            i3 = i11;
            $dirty4 |= $composer4.changed(interactionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i3 = i11;
        }
        int $dirty5 = $dirty4;
        if ($composer4.shouldExecute(($dirty4 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "822@39776L5,823@39831L22,824@39910L25,825@39986L22,826@40060L39");
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i6 != 0 ? true : z;
                Function2 icon4 = i7 != 0 ? null : function22;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape4 = SuggestionChipDefaults.INSTANCE.getShape($composer4, 6);
                } else {
                    $dirty = $dirty5;
                    shape4 = shape2;
                }
                if ((i & 64) != 0) {
                    colors4 = SuggestionChipDefaults.INSTANCE.suggestionChipColors($composer4, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors4 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    $composer3 = $composer4;
                    modifier3 = modifier4;
                    i4 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m3013suggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier3 = modifier4;
                    i4 = 6;
                    $composer3 = $composer4;
                }
                if ((i & 256) != 0) {
                    $composer4 = $composer3;
                    border3 = SuggestionChipDefaults.INSTANCE.m3010suggestionChipBorderd_3_b6Q(0L, 0L, 0.0f, $composer4, 3072, 7);
                    $dirty2 &= -234881025;
                } else {
                    $composer4 = $composer3;
                    border3 = border;
                }
                if (i3 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer4, -2080664820, "CC(remember):Chip.kt#9igjgp");
                    Composer $this$cache$iv = $composer4;
                    Object it$iv = $this$cache$iv.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $this$cache$iv.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    modifier2 = modifier3;
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    colors2 = colors4;
                    icon3 = icon4;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                } else {
                    modifier2 = modifier3;
                    interactionSource3 = interactionSource;
                    colors2 = colors4;
                    icon3 = icon4;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty3 = $dirty5 & (-234881025);
                    i4 = 6;
                    border3 = border;
                    interactionSource3 = interactionSource;
                    icon3 = function22;
                    shape5 = shape2;
                    elevation4 = elevation2;
                    enabled3 = z;
                } else {
                    border3 = border;
                    interactionSource3 = interactionSource;
                    i4 = 6;
                    icon3 = function22;
                    shape5 = shape2;
                    elevation4 = elevation2;
                    $dirty3 = $dirty5;
                    enabled3 = z;
                }
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(270460261, $dirty3, -1, "androidx.compose.material3.SuggestionChip (Chip.kt:828)");
            }
            TextStyle value = TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer4, i4);
            long jM2263labelColorvNxB06k$material3 = colors2.m2263labelColorvNxB06k$material3(enabled3);
            if (border3 == null) {
                $composer4.startReplaceGroup(-75666041);
                $composer4.endReplaceGroup();
                stateBorderStroke$material3 = null;
            } else {
                $composer4.startReplaceGroup(-2080650822);
                ComposerKt.sourceInformation($composer4, "840@40498L21");
                stateBorderStroke$material3 = border3.borderStroke$material3(enabled3, $composer4, (($dirty3 >> 9) & 14) | (($dirty3 >> 21) & 112));
                $composer4.endReplaceGroup();
            }
            $composer2 = $composer4;
            ChipColors colors5 = colors2;
            Function2 function23 = function2;
            Modifier modifier5 = modifier2;
            m2273ChipnkUnTEs(modifier5, function0, enabled3, function23, value, jM2263labelColorvNxB06k$material3, icon3, null, shape5, colors5, elevation4, stateBorderStroke$material3 != null ? stateBorderStroke$material3.getValue() : null, SuggestionChipDefaults.INSTANCE.m3008getHeightD9Ej5fM(), SuggestionChipPadding, interactionSource3, $composer2, (($dirty3 >> 6) & 14) | i2 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (1879048192 & ($dirty3 << 9)), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            modifier2 = modifier5;
            enabled2 = enabled3;
            icon2 = icon3;
            shape3 = shape5;
            colors3 = colors5;
            elevation3 = elevation4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            enabled2 = z;
            icon2 = function22;
            shape3 = shape2;
            colors3 = colors2;
            elevation3 = elevation2;
            border2 = border;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.SuggestionChip$lambda$11(onClick, label, modifier2, enabled2, icon2, shape3, colors3, elevation3, border2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ElevatedSuggestionChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function23;
        final Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Shape shape2;
        ChipColors colors2;
        int i2;
        ChipElevation elevation2;
        int i3;
        int i4;
        Composer $composer2;
        final boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function25;
        final Shape shape3;
        final ChipColors colors3;
        final ChipElevation elevation3;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        Shape shape4;
        ChipColors colors4;
        int $dirty2;
        Modifier modifier3;
        int i5;
        MutableInteractionSource interactionSource3;
        BorderStroke border3;
        Function2<? super Composer, ? super Integer, Unit> function26;
        int $dirty3;
        ChipElevation elevation4;
        boolean enabled3;
        Shape shape5;
        Composer $composer3 = $composer.startRestartGroup(-894435833);
        ComposerKt.sourceInformation($composer3, "C(ElevatedSuggestionChip)N(onClick,label,modifier,enabled,icon,shape,colors,elevation,border,interactionSource)904@43788L5,899@43616L539:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty4 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function23 = function2;
        } else if (($changed & 48) == 0) {
            function23 = function2;
            $dirty4 |= $composer3.changedInstance(function23) ? 32 : 16;
        } else {
            function23 = function2;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty4 |= 24576;
            function24 = function22;
        } else if (($changed & 24576) == 0) {
            function24 = function22;
            $dirty4 |= $composer3.changedInstance(function24) ? 16384 : 8192;
        } else {
            function24 = function22;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i9 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty4 |= i9;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i9;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i10 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty4 |= i10;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i10;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            i2 = 12582912;
            if ((i & 128) == 0) {
                elevation2 = elevation;
                int i11 = $composer3.changed(elevation2) ? 8388608 : 4194304;
                $dirty4 |= i11;
            } else {
                elevation2 = elevation;
            }
            $dirty4 |= i11;
        } else {
            i2 = 12582912;
            elevation2 = elevation;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty4 |= 100663296;
            i3 = i6;
        } else if (($changed & 100663296) == 0) {
            i3 = i6;
            $dirty4 |= $composer3.changed(border) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i3 = i6;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty4 |= 805306368;
            i4 = i13;
        } else if (($changed & 805306368) == 0) {
            i4 = i13;
            $dirty4 |= $composer3.changed(interactionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i4 = i13;
        }
        int $dirty5 = $dirty4;
        if ($composer3.shouldExecute(($dirty4 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "893@43340L5,894@43395L30,895@43482L33");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i7 != 0 ? true : z;
                Function2<? super Composer, ? super Integer, Unit> function27 = i8 != 0 ? null : function24;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape4 = SuggestionChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape4 = shape2;
                }
                if ((i & 64) != 0) {
                    colors4 = SuggestionChipDefaults.INSTANCE.elevatedSuggestionChipColors($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors4 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    modifier3 = modifier4;
                    i5 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m3007elevatedSuggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier3 = modifier4;
                    i5 = 6;
                }
                BorderStroke border4 = i12 != 0 ? null : border;
                if (i4 != 0) {
                    modifier2 = modifier3;
                    border3 = border4;
                    interactionSource3 = null;
                    colors2 = colors4;
                    function26 = function27;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                } else {
                    modifier2 = modifier3;
                    interactionSource3 = interactionSource;
                    border3 = border4;
                    colors2 = colors4;
                    function26 = function27;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    border3 = border;
                    interactionSource3 = interactionSource;
                    $dirty3 = $dirty5 & (-29360129);
                    i5 = 6;
                    function26 = function24;
                    shape5 = shape2;
                    elevation4 = elevation2;
                    enabled3 = z;
                } else {
                    border3 = border;
                    interactionSource3 = interactionSource;
                    i5 = 6;
                    function26 = function24;
                    shape5 = shape2;
                    elevation4 = elevation2;
                    $dirty3 = $dirty5;
                    enabled3 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-894435833, $dirty3, -1, "androidx.compose.material3.ElevatedSuggestionChip (Chip.kt:899)");
            }
            $composer2 = $composer3;
            ChipColors colors5 = colors2;
            Function2<? super Composer, ? super Integer, Unit> function28 = function23;
            Modifier modifier5 = modifier2;
            m2273ChipnkUnTEs(modifier5, function02, enabled3, function28, TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer3, i5), colors2.m2263labelColorvNxB06k$material3(enabled3), function26, null, shape5, colors5, elevation4, border3, SuggestionChipDefaults.INSTANCE.m3008getHeightD9Ej5fM(), SuggestionChipPadding, interactionSource3, $composer2, (($dirty3 >> 6) & 14) | i2 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (($dirty3 << 9) & 1879048192), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 21) & 112) | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier5;
            enabled2 = enabled3;
            function25 = function26;
            shape3 = shape5;
            colors3 = colors5;
            elevation3 = elevation4;
            border2 = border3;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            enabled2 = z;
            function25 = function24;
            shape3 = shape2;
            colors3 = colors2;
            elevation3 = elevation2;
            border2 = border;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedSuggestionChip$lambda$12(function0, function2, modifier2, enabled2, function25, shape3, colors3, elevation3, border2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with ElevatedSuggestionChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "ElevatedSuggestionChip(onClick, label, modifier, enabled, icon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void ElevatedSuggestionChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 icon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Function2 function2;
        final Modifier modifier2;
        boolean z;
        Function2 function22;
        Shape shape2;
        ChipColors colors2;
        int i2;
        ChipElevation elevation2;
        int i3;
        int i4;
        Composer $composer2;
        final boolean enabled2;
        final Function2 icon2;
        final Shape shape3;
        final ChipColors colors3;
        final ChipElevation elevation3;
        final ChipBorder border2;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        Shape shape4;
        ChipColors colors4;
        int $dirty2;
        Modifier modifier3;
        int i5;
        ChipBorder border3;
        MutableInteractionSource interactionSource3;
        Function2 icon3;
        int $dirty3;
        ChipElevation elevation4;
        boolean enabled3;
        Shape shape5;
        State<BorderStroke> stateBorderStroke$material3;
        Composer $composer3 = $composer.startRestartGroup(1306662363);
        ComposerKt.sourceInformation($composer3, "C(ElevatedSuggestionChip)N(onClick,label,modifier,enabled,icon,shape,colors,elevation,border,interactionSource)985@47676L5,980@47504L569:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function0 = onClick;
        } else if (($changed & 6) == 0) {
            function0 = onClick;
            $dirty4 |= $composer3.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = onClick;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty4 |= $composer3.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty4 |= 24576;
            function22 = icon;
        } else if (($changed & 24576) == 0) {
            function22 = icon;
            $dirty4 |= $composer3.changedInstance(function22) ? 16384 : 8192;
        } else {
            function22 = icon;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i9 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty4 |= i9;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i9;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i10 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty4 |= i10;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i10;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            i2 = 12582912;
            if ((i & 128) == 0) {
                elevation2 = elevation;
                int i11 = $composer3.changed(elevation2) ? 8388608 : 4194304;
                $dirty4 |= i11;
            } else {
                elevation2 = elevation;
            }
            $dirty4 |= i11;
        } else {
            i2 = 12582912;
            elevation2 = elevation;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty4 |= 100663296;
            i3 = i6;
        } else if (($changed & 100663296) == 0) {
            i3 = i6;
            $dirty4 |= $composer3.changed(border) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i3 = i6;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty4 |= 805306368;
            i4 = i13;
        } else if (($changed & 805306368) == 0) {
            i4 = i13;
            $dirty4 |= $composer3.changed(interactionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i4 = i13;
        }
        int $dirty5 = $dirty4;
        if ($composer3.shouldExecute(($dirty4 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "974@47196L5,975@47251L30,976@47338L33,978@47455L39");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i7 != 0 ? true : z;
                Function2 icon4 = i8 != 0 ? null : function22;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape4 = SuggestionChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape4 = shape2;
                }
                if ((i & 64) != 0) {
                    colors4 = SuggestionChipDefaults.INSTANCE.elevatedSuggestionChipColors($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors4 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    modifier3 = modifier4;
                    i5 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m3007elevatedSuggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier3 = modifier4;
                    i5 = 6;
                }
                border3 = i12 != 0 ? null : border;
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1572109794, "CC(remember):Chip.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    modifier2 = modifier3;
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    colors2 = colors4;
                    icon3 = icon4;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                } else {
                    modifier2 = modifier3;
                    interactionSource3 = interactionSource;
                    colors2 = colors4;
                    icon3 = icon4;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty3 = $dirty5 & (-29360129);
                    i5 = 6;
                    border3 = border;
                    interactionSource3 = interactionSource;
                    icon3 = function22;
                    shape5 = shape2;
                    elevation4 = elevation2;
                    enabled3 = z;
                } else {
                    border3 = border;
                    interactionSource3 = interactionSource;
                    i5 = 6;
                    icon3 = function22;
                    shape5 = shape2;
                    elevation4 = elevation2;
                    $dirty3 = $dirty5;
                    enabled3 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1306662363, $dirty3, -1, "androidx.compose.material3.ElevatedSuggestionChip (Chip.kt:980)");
            }
            TextStyle value = TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer3, i5);
            long jM2263labelColorvNxB06k$material3 = colors2.m2263labelColorvNxB06k$material3(enabled3);
            float fM3008getHeightD9Ej5fM = SuggestionChipDefaults.INSTANCE.m3008getHeightD9Ej5fM();
            PaddingValues paddingValues = SuggestionChipPadding;
            if (border3 == null) {
                $composer3.startReplaceGroup(1491294513);
                $composer3.endReplaceGroup();
                stateBorderStroke$material3 = null;
            } else {
                $composer3.startReplaceGroup(1572126928);
                ComposerKt.sourceInformation($composer3, "994@47991L21");
                stateBorderStroke$material3 = border3.borderStroke$material3(enabled3, $composer3, (($dirty3 >> 9) & 14) | (($dirty3 >> 21) & 112));
                $composer3.endReplaceGroup();
            }
            $composer2 = $composer3;
            ChipColors colors5 = colors2;
            Function2 function23 = function2;
            Modifier modifier5 = modifier2;
            m2273ChipnkUnTEs(modifier5, function0, enabled3, function23, value, jM2263labelColorvNxB06k$material3, icon3, null, shape5, colors5, elevation4, stateBorderStroke$material3 != null ? stateBorderStroke$material3.getValue() : null, fM3008getHeightD9Ej5fM, paddingValues, interactionSource3, $composer2, (($dirty3 >> 6) & 14) | i2 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (1879048192 & ($dirty3 << 9)), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            modifier2 = modifier5;
            enabled2 = enabled3;
            icon2 = icon3;
            shape3 = shape5;
            colors3 = colors5;
            elevation3 = elevation4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            enabled2 = z;
            icon2 = function22;
            shape3 = shape2;
            colors3 = colors2;
            elevation3 = elevation2;
            border2 = border;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedSuggestionChip$lambda$14(onClick, label, modifier2, enabled2, icon2, shape3, colors3, elevation3, border2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: Chip-nkUnTEs */
    private static final void m2273ChipnkUnTEs(final Modifier modifier, final Function0<Unit> function0, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, final long labelColor, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Shape shape, final ChipColors colors, final ChipElevation elevation, final BorderStroke border, final float minHeight, final PaddingValues paddingValues, final MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1) {
        TextStyle textStyle;
        long j;
        BorderStroke borderStroke;
        Composer $composer2;
        int $dirty;
        int $dirty1;
        int $dirty2;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer3 = $composer.startRestartGroup(892465622);
        ComposerKt.sourceInformation($composer3, "C(Chip)N(modifier,onClick,enabled,label,labelTextStyle,labelColor:c#ui.graphics.Color,leadingIcon,trailingIcon,shape,colors,elevation,border,minHeight:c#ui.unit.Dp,paddingValues,interactionSource)1962@97212L22,1969@97509L478,1960@97138L849:Chip.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty12 = $changed1;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changed(enabled) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            textStyle = labelTextStyle;
            $dirty3 |= $composer3.changed(textStyle) ? 16384 : 8192;
        } else {
            textStyle = labelTextStyle;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            j = labelColor;
            $dirty3 |= $composer3.changed(j) ? 131072 : 65536;
        } else {
            j = labelColor;
        }
        if (($changed & 1572864) == 0) {
            $dirty3 |= $composer3.changedInstance(function22) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function23) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changed(shape) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty3 |= $composer3.changed(colors) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($changed1 & 6) == 0) {
            $dirty12 |= $composer3.changed(elevation) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            borderStroke = border;
            $dirty12 |= $composer3.changed(borderStroke) ? 32 : 16;
        } else {
            borderStroke = border;
        }
        if (($changed1 & 384) == 0) {
            $dirty12 |= $composer3.changed(minHeight) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty12 |= $composer3.changed(paddingValues) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty12 |= $composer3.changed(interactionSource) ? 16384 : 8192;
        }
        int $dirty13 = $dirty12;
        if ($composer3.shouldExecute(((306783379 & $dirty3) == 306783378 && ($dirty13 & 9363) == 9362) ? false : true, $dirty3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(892465622, $dirty3, $dirty13, "androidx.compose.material3.Chip (Chip.kt:1957)");
            }
            if (interactionSource == null) {
                $composer3.startReplaceGroup(1596346437);
                ComposerKt.sourceInformation($composer3, "1959@97094L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -1333978275, "CC(remember):Chip.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                $dirty1 = $dirty13;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $dirty2 = $dirty3;
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                } else {
                    $dirty2 = $dirty3;
                }
                mutableInteractionSource = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $dirty1 = $dirty13;
                $dirty2 = $dirty3;
                $composer3.startReplaceGroup(-1333978926);
                $composer3.endReplaceGroup();
                mutableInteractionSource = interactionSource;
            }
            MutableInteractionSource interactionSource2 = mutableInteractionSource;
            ComposerKt.sourceInformationMarkerStart($composer3, -1333974516, "CC(remember):Chip.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ChipKt.Chip_nkUnTEs$lambda$17$lambda$16((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            State<Dp> stateShadowElevation$material3 = null;
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, (Function1) it$iv2, 1, null);
            long jM2253containerColorvNxB06k$material3 = colors.m2253containerColorvNxB06k$material3(enabled);
            if (elevation == null) {
                $composer3.startReplaceGroup(1596621344);
            } else {
                $composer3.startReplaceGroup(-1333969407);
                ComposerKt.sourceInformation($composer3, "1966@97371L43");
                stateShadowElevation$material3 = elevation.shadowElevation$material3(enabled, interactionSource2, $composer3, (($dirty2 >> 6) & 14) | (($dirty1 << 6) & 896));
            }
            $composer3.endReplaceGroup();
            final TextStyle textStyle2 = textStyle;
            final long j2 = j;
            $dirty = $dirty2;
            SurfaceKt.m3017Surfaceo_FOJdg(function0, modifierSemantics$default, enabled, shape, jM2253containerColorvNxB06k$material3, 0L, 0.0f, stateShadowElevation$material3 != null ? stateShadowElevation$material3.getValue().m8164unboximpl() : Dp.m8150constructorimpl(0), borderStroke, interactionSource2, ComposableLambdaKt.rememberComposableLambda(-70915349, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$Chip$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C1970@97519L462:Chip.kt#uh7d8r");
                    if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-70915349, $changed2, -1, "androidx.compose.material3.Chip.<anonymous> (Chip.kt:1970)");
                        }
                        ChipKt.m2274ChipContentfe0OD_I(function2, textStyle2, j2, function22, null, function23, colors.m2264leadingIconContentColorvNxB06k$material3(enabled), colors.m2265trailingIconContentColorvNxB06k$material3(enabled), minHeight, paddingValues, $composer4, 24576);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | ($dirty & 896) | (($dirty >> 15) & 7168) | (($dirty1 << 21) & 234881024), 6, 96);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $dirty = $dirty3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.Chip_nkUnTEs$lambda$18(modifier, function0, enabled, function2, labelTextStyle, labelColor, function22, function23, shape, colors, elevation, border, minHeight, paddingValues, interactionSource, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Chip_nkUnTEs$lambda$17$lambda$16(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7362setRolekuIjeqM($this$semantics, Role.INSTANCE.m7343getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: SelectableChip-u0RnIRE */
    private static final void m2275SelectableChipu0RnIRE(final boolean selected, final Modifier modifier, final Function0<Unit> function0, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Shape shape, final SelectableChipColors colors, final SelectableChipElevation elevation, final BorderStroke border, final float minHeight, final PaddingValues paddingValues, final MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1) {
        TextStyle textStyle;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Composer $composer2;
        int $dirty;
        int $dirty1;
        MutableInteractionSource mutableInteractionSource;
        State<Dp> stateShadowElevation$material3;
        Composer $composer3 = $composer.startRestartGroup(1786844928);
        ComposerKt.sourceInformation($composer3, "C(SelectableChip)N(selected,modifier,onClick,enabled,label,labelTextStyle,leadingIcon,avatar,trailingIcon,shape,colors,elevation,border,minHeight:c#ui.unit.Dp,paddingValues,interactionSource)2009@98774L24,2016@99083L670,2006@98671L1082:Chip.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty12 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selected) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle = labelTextStyle;
            $dirty2 |= $composer3.changed(textStyle) ? 131072 : 65536;
        } else {
            textStyle = labelTextStyle;
        }
        if (($changed & 1572864) == 0) {
            function25 = function22;
            $dirty2 |= $composer3.changedInstance(function25) ? 1048576 : 524288;
        } else {
            function25 = function22;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function23) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(function24) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changed(shape) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($changed1 & 6) == 0) {
            $dirty12 |= $composer3.changed(colors) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty12 |= $composer3.changed(elevation) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty12 |= $composer3.changed(border) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty12 |= $composer3.changed(minHeight) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty12 |= $composer3.changed(paddingValues) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty12 |= $composer3.changed(interactionSource) ? 131072 : 65536;
        }
        if (!$composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && (74899 & $dirty12) == 74898) ? false : true, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1786844928, $dirty2, $dirty12, "androidx.compose.material3.SelectableChip (Chip.kt:2003)");
            }
            if (interactionSource == null) {
                $composer3.startReplaceGroup(73215547);
                ComposerKt.sourceInformation($composer3, "2005@98627L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -828922201, "CC(remember):Chip.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                $dirty = $dirty2;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $dirty1 = $dirty12;
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                } else {
                    $dirty1 = $dirty12;
                }
                mutableInteractionSource = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $dirty = $dirty2;
                $dirty1 = $dirty12;
                $composer3.startReplaceGroup(-828922852);
                $composer3.endReplaceGroup();
                mutableInteractionSource = interactionSource;
            }
            MutableInteractionSource interactionSource2 = mutableInteractionSource;
            ComposerKt.sourceInformationMarkerStart($composer3, -828917512, "CC(remember):Chip.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ChipKt.SelectableChip_u0RnIRE$lambda$21$lambda$20((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, (Function1) it$iv2, 1, null);
            long jM2910containerColorWaAFU9c$material3 = colors.m2910containerColorWaAFU9c$material3(enabled, selected);
            if (elevation == null) {
                $composer3.startReplaceGroup(73531126);
                $composer3.endReplaceGroup();
                stateShadowElevation$material3 = null;
            } else {
                $composer3.startReplaceGroup(-828912021);
                ComposerKt.sourceInformation($composer3, "2013@98945L43");
                stateShadowElevation$material3 = elevation.shadowElevation$material3(enabled, interactionSource2, $composer3, (($dirty >> 9) & 14) | (($dirty1 << 3) & 896));
                $composer3.endReplaceGroup();
            }
            final TextStyle textStyle2 = textStyle;
            final Function2<? super Composer, ? super Integer, Unit> function26 = function25;
            int $dirty3 = $dirty;
            SurfaceKt.m3015Surfaced85dljk(selected, function0, modifierSemantics$default, enabled, shape, jM2910containerColorWaAFU9c$material3, 0L, 0.0f, stateShadowElevation$material3 != null ? stateShadowElevation$material3.getValue().m8164unboximpl() : Dp.m8150constructorimpl(0), border, interactionSource2, ComposableLambdaKt.rememberComposableLambda(-990050154, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$SelectableChip$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C2019@99228L519:Chip.kt#uh7d8r");
                    if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-990050154, $changed2, -1, "androidx.compose.material3.SelectableChip.<anonymous> (Chip.kt:2019)");
                        }
                        ChipKt.m2272AnimatingChipContentfe0OD_I(function2, textStyle2, colors.m2912labelColorWaAFU9c$material3(enabled, selected), function26, function23, function24, colors.m2913leadingIconContentColorWaAFU9c$material3(enabled, selected), colors.m2914trailingIconContentColorWaAFU9c$material3(enabled, selected), minHeight, paddingValues, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, ($dirty3 & 14) | (($dirty3 >> 3) & 112) | ($dirty3 & 7168) | (($dirty3 >> 15) & 57344) | (($dirty1 << 21) & 1879048192), 48, 192);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.SelectableChip_u0RnIRE$lambda$22(selected, modifier, function0, enabled, function2, labelTextStyle, function22, function23, function24, shape, colors, elevation, border, minHeight, paddingValues, interactionSource, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit SelectableChip_u0RnIRE$lambda$21$lambda$20(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7362setRolekuIjeqM($this$semantics, Role.INSTANCE.m7345getCheckboxo7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: ChipContent-fe0OD_I */
    public static final void m2274ChipContentfe0OD_I(final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, long labelColor, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final long leadingIconColor, final long trailingIconColor, final float minHeight, final PaddingValues paddingValues, Composer $composer, final int $changed) {
        long j;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        long j2;
        long j3;
        char c;
        Composer $composer2 = $composer.startRestartGroup(1105630840);
        ComposerKt.sourceInformation($composer2, "C(ChipContent)N(label,labelTextStyle,labelColor:c#ui.graphics.Color,leadingIcon,avatar,trailingIcon,leadingIconColor:c#ui.graphics.Color,trailingIconColor:c#ui.graphics.Color,minHeight:c#ui.unit.Dp,paddingValues)2056@100480L1721,2053@100353L1848:Chip.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(labelTextStyle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            j = labelColor;
            $dirty |= $composer2.changed(j) ? 256 : 128;
        } else {
            j = labelColor;
        }
        if (($changed & 3072) == 0) {
            function25 = function22;
            $dirty |= $composer2.changedInstance(function25) ? 2048 : 1024;
        } else {
            function25 = function22;
        }
        if (($changed & 24576) == 0) {
            function26 = function23;
            $dirty |= $composer2.changedInstance(function26) ? 16384 : 8192;
        } else {
            function26 = function23;
        }
        if ((196608 & $changed) == 0) {
            function27 = function24;
            $dirty |= $composer2.changedInstance(function27) ? 131072 : 65536;
        } else {
            function27 = function24;
        }
        if ((1572864 & $changed) == 0) {
            j2 = leadingIconColor;
            $dirty |= $composer2.changed(j2) ? 1048576 : 524288;
        } else {
            j2 = leadingIconColor;
        }
        if ((12582912 & $changed) == 0) {
            j3 = trailingIconColor;
            $dirty |= $composer2.changed(j3) ? 8388608 : 4194304;
        } else {
            j3 = trailingIconColor;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(minHeight) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty |= $composer2.changed(paddingValues) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (!$composer2.shouldExecute(($dirty & 306783379) != 306783378, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                c = 1;
                ComposerKt.traceEventStart(1105630840, $dirty, -1, "androidx.compose.material3.ChipContent (Chip.kt:2052)");
            } else {
                c = 1;
            }
            ProvidedValue[] providedValueArr = new ProvidedValue[2];
            providedValueArr[0] = ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(j));
            providedValueArr[c] = TextKt.getLocalTextStyle().provides(labelTextStyle);
            final Function2<? super Composer, ? super Integer, Unit> function28 = function25;
            final Function2<? super Composer, ? super Integer, Unit> function29 = function26;
            final Function2<? super Composer, ? super Integer, Unit> function210 = function27;
            final long j4 = j2;
            final long j5 = j3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.rememberComposableLambda(-2130105544, c, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$ChipContent$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:52:0x0294  */
                /* JADX WARN: Removed duplicated region for block: B:53:0x02a6  */
                /* JADX WARN: Removed duplicated region for block: B:72:0x040d  */
                /* JADX WARN: Removed duplicated region for block: B:92:0x0559  */
                /* JADX WARN: Removed duplicated region for block: B:95:0x0578  */
                /* JADX WARN: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void invoke(androidx.compose.runtime.Composer r57, int r58) {
                    /*
                        Method dump skipped, instruction units count: 1408
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt$ChipContent$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final long j6 = j;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ChipContent_fe0OD_I$lambda$23(function2, labelTextStyle, j6, function22, function23, function24, leadingIconColor, trailingIconColor, minHeight, paddingValues, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: AnimatingChipContent-fe0OD_I */
    public static final void m2272AnimatingChipContentfe0OD_I(final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, long labelColor, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final long leadingIconColor, final long trailingIconColor, final float minHeight, final PaddingValues paddingValues, Composer $composer, final int $changed) {
        long j;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        long j2;
        long j3;
        char c;
        Composer $composer2 = $composer.startRestartGroup(-2070754602);
        ComposerKt.sourceInformation($composer2, "C(AnimatingChipContent)N(label,labelTextStyle,labelColor:c#ui.graphics.Color,leadingIcon,avatar,trailingIcon,leadingIconColor:c#ui.graphics.Color,trailingIconColor:c#ui.graphics.Color,minHeight:c#ui.unit.Dp,paddingValues)2118@102851L4086,2115@102724L4213:Chip.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(labelTextStyle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            j = labelColor;
            $dirty |= $composer2.changed(j) ? 256 : 128;
        } else {
            j = labelColor;
        }
        if (($changed & 3072) == 0) {
            function25 = function22;
            $dirty |= $composer2.changedInstance(function25) ? 2048 : 1024;
        } else {
            function25 = function22;
        }
        if (($changed & 24576) == 0) {
            function26 = function23;
            $dirty |= $composer2.changedInstance(function26) ? 16384 : 8192;
        } else {
            function26 = function23;
        }
        if ((196608 & $changed) == 0) {
            function27 = function24;
            $dirty |= $composer2.changedInstance(function27) ? 131072 : 65536;
        } else {
            function27 = function24;
        }
        if ((1572864 & $changed) == 0) {
            j2 = leadingIconColor;
            $dirty |= $composer2.changed(j2) ? 1048576 : 524288;
        } else {
            j2 = leadingIconColor;
        }
        if ((12582912 & $changed) == 0) {
            j3 = trailingIconColor;
            $dirty |= $composer2.changed(j3) ? 8388608 : 4194304;
        } else {
            j3 = trailingIconColor;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(minHeight) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty |= $composer2.changed(paddingValues) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (!$composer2.shouldExecute(($dirty & 306783379) != 306783378, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                c = 1;
                ComposerKt.traceEventStart(-2070754602, $dirty, -1, "androidx.compose.material3.AnimatingChipContent (Chip.kt:2114)");
            } else {
                c = 1;
            }
            ProvidedValue[] providedValueArr = new ProvidedValue[2];
            providedValueArr[0] = ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(j));
            providedValueArr[c] = TextKt.getLocalTextStyle().provides(labelTextStyle);
            final Function2<? super Composer, ? super Integer, Unit> function28 = function25;
            final Function2<? super Composer, ? super Integer, Unit> function29 = function26;
            final Function2<? super Composer, ? super Integer, Unit> function210 = function27;
            final long j4 = j2;
            final long j5 = j3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.rememberComposableLambda(-668234218, c, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$AnimatingChipContent$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer3, "C2120@102988L14,2121@103063L14,2122@103137L16,2123@103216L16,2197@106882L38,2124@103241L3690:Chip.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-668234218, $changed2, -1, "androidx.compose.material3.AnimatingChipContent.<anonymous> (Chip.kt:2120)");
                    }
                    FiniteAnimationSpec fadeInSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.SlowEffects, $composer3, 6);
                    FiniteAnimationSpec fadeOutSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer3, 6);
                    FiniteAnimationSpec expandSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer3, 6);
                    FiniteAnimationSpec shrinkSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer3, 6);
                    Modifier modifierPadding = PaddingKt.padding(SizeKt.m1100defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, minHeight, 1, null), paddingValues);
                    ComposerKt.sourceInformationMarkerStart($composer3, 2072139932, "CC(remember):Chip.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new ChipLayoutMeasurePolicy();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ChipLayoutMeasurePolicy chipLayoutMeasurePolicy = (ChipLayoutMeasurePolicy) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final Function2<Composer, Integer, Unit> function211 = function29;
                    final Function2<Composer, Integer, Unit> function212 = function28;
                    final Function2<Composer, Integer, Unit> function213 = function210;
                    final long j6 = j4;
                    Function2<Composer, Integer, Unit> function214 = function2;
                    final long j7 = j5;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifierPadding);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv = ((384 << 6) & 896) | 6;
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
                    Composer $this$Layout_u24lambda_u240$iv = Updater.m4433constructorimpl($composer3);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, chipLayoutMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                    }
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -564817408, "C2141@104124L826,2128@103442L1508,2158@104967L362,2179@106014L824,2168@105407L1431:Chip.kt#uh7d8r");
                    AnimatedVisibilityKt.AnimatedVisibility((function211 == null && function212 == null) ? false : true, LayoutIdKt.layoutId(Modifier.INSTANCE, "leadingIcon"), EnterExitTransitionKt.expandHorizontally$default(expandSpec, Alignment.INSTANCE.getStart(), false, null, 12, null).plus(EnterExitTransitionKt.fadeIn$default(fadeInSpec, 0.0f, 2, null)), EnterExitTransitionKt.shrinkHorizontally$default(shrinkSpec, Alignment.INSTANCE.getStart(), false, null, 12, null).plus(EnterExitTransitionKt.fadeOut$default(fadeOutSpec, 0.0f, 2, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(687705959, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$AnimatingChipContent$1$2$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                            invoke(animatedVisibilityScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Removed duplicated region for block: B:23:0x013c  */
                        /* JADX WARN: Removed duplicated region for block: B:24:0x0143  */
                        /* JADX WARN: Removed duplicated region for block: B:27:0x0175  */
                        /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r30, androidx.compose.runtime.Composer r31, int r32) {
                            /*
                                Method dump skipped, instruction units count: 377
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt$AnimatingChipContent$1$2$1.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                        }
                    }, $composer3, 54), $composer3, 196656, 16);
                    Modifier modifier$iv = PaddingKt.m1050paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "label"), ChipKt.HorizontalElementsPadding, 0.0f, 2, null);
                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    int $changed$iv$iv2 = (438 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
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
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer3);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                    }
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i3 = ((438 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 75607137, "C2164@105301L7:Chip.kt#uh7d8r");
                    function214.invoke($composer3, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    AnimatedVisibilityKt.AnimatedVisibility(function213 != null, LayoutIdKt.layoutId(Modifier.INSTANCE, "trailingIcon"), EnterExitTransitionKt.expandHorizontally$default(expandSpec, Alignment.INSTANCE.getEnd(), false, null, 12, null).plus(EnterExitTransitionKt.fadeIn$default(fadeInSpec, 0.0f, 2, null)), EnterExitTransitionKt.shrinkHorizontally$default(shrinkSpec, Alignment.INSTANCE.getEnd(), false, null, 12, null).plus(EnterExitTransitionKt.fadeOut$default(fadeOutSpec, 0.0f, 2, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1905252304, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$AnimatingChipContent$1$2$3
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                            invoke(animatedVisibilityScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer4, int $changed3) {
                            Function0<ComposeUiNode> function02;
                            ComposerKt.sourceInformation($composer4, "C2185@106445L48,2184@106380L139,2188@106541L279:Chip.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1905252304, $changed3, -1, "androidx.compose.material3.AnimatingChipContent.<anonymous>.<anonymous>.<anonymous> (Chip.kt:2183)");
                            }
                            State trailingContentRetainedState = ChipKt.rememberRetainedState(ChipKt.m2281trailingContentRPmYEkk(function213, j7, $composer4, 0), $composer4, 0);
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Modifier modifier$iv2 = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv3 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                function02 = constructor3;
                                $composer4.createNode(function02);
                            } else {
                                function02 = constructor3;
                                $composer4.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4433constructorimpl($composer4);
                            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                            }
                            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                            int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i5 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -2101817907, "C:Chip.kt#uh7d8r");
                            Function2 function215 = (Function2) trailingContentRetainedState.getValue();
                            if (function215 == null) {
                                $composer4.startReplaceGroup(-2101783313);
                            } else {
                                $composer4.startReplaceGroup(-344894126);
                                ComposerKt.sourceInformation($composer4, "2192@106763L8");
                                function215.invoke($composer4, 0);
                            }
                            $composer4.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54), $composer3, 196656, 16);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
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
            final long j6 = j;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.AnimatingChipContent_fe0OD_I$lambda$24(function2, labelTextStyle, j6, function22, function23, function24, leadingIconColor, trailingIconColor, minHeight, paddingValues, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: leadingContent-XO-JAsU */
    public static final Function2<Composer, Integer, Unit> m2280leadingContentXOJAsU(Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final long leadingIconColor, Composer $composer, int $changed) {
        Function2 function2RememberComposableLambda;
        ComposerKt.sourceInformationMarkerStart($composer, 1330309098, "C(leadingContent)N(avatar,leadingIcon,leadingIconColor:c#ui.graphics.Color):Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1330309098, $changed, -1, "androidx.compose.material3.leadingContent (Chip.kt:2212)");
        }
        if (function2 != null) {
            $composer.startReplaceGroup(-1473203984);
            $composer.endReplaceGroup();
            function2RememberComposableLambda = function2;
        } else if (function22 != null) {
            $composer.startReplaceGroup(1575390813);
            ComposerKt.sourceInformation($composer, "2215@107394L183");
            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-237350650, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$leadingContent$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C2216@107412L151:Chip.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-237350650, $changed2, -1, "androidx.compose.material3.leadingContent.<anonymous> (Chip.kt:2216)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(leadingIconColor)), function22, $composer2, ProvidedValue.$stable);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(1575618259);
            $composer.endReplaceGroup();
            function2RememberComposableLambda = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return function2RememberComposableLambda;
    }

    /* JADX INFO: renamed from: trailingContent-RPmYEkk */
    public static final Function2<Composer, Integer, Unit> m2281trailingContentRPmYEkk(final Function2<? super Composer, ? super Integer, Unit> function2, final long trailingIconColor, Composer $composer, int $changed) {
        ComposableLambda composableLambdaRememberComposableLambda;
        ComposerKt.sourceInformationMarkerStart($composer, -165980551, "C(trailingContent)N(trailingIcon,trailingIconColor:c#ui.graphics.Color):Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-165980551, $changed, -1, "androidx.compose.material3.trailingContent (Chip.kt:2231)");
        }
        if (function2 != null) {
            $composer.startReplaceGroup(-1219055576);
            ComposerKt.sourceInformation($composer, "2232@107914L165");
            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-566924201, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$trailingContent$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C2233@107928L141:Chip.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-566924201, $changed2, -1, "androidx.compose.material3.trailingContent.<anonymous> (Chip.kt:2233)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(trailingIconColor)), function2, $composer2, ProvidedValue.$stable);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-1218863531);
            $composer.endReplaceGroup();
            composableLambdaRememberComposableLambda = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return composableLambdaRememberComposableLambda;
    }

    public static final <T> State<T> rememberRetainedState(T t, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -618198444, "C(rememberRetainedState)N(targetValue)2249@108434L40:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-618198444, $changed, -1, "androidx.compose.material3.rememberRetainedState (Chip.kt:2248)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1383214468, "CC(remember):Chip.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        MutableState retainedState = (MutableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (t != null) {
            retainedState.setValue(t);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return retainedState;
    }

    public static final ChipColors getDefaultSuggestionChipColors(ColorScheme $this$defaultSuggestionChipColors) {
        ChipColors it = $this$defaultSuggestionChipColors.getDefaultSuggestionChipColorsCached();
        if (it != null) {
            return it;
        }
        long jM5348getTransparent0d7_KjU = Color.INSTANCE.m5348getTransparent0d7_KjU();
        long jFromToken = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getLabelTextColor());
        long jFromToken2 = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getLeadingIconColor());
        long jM5349getUnspecified0d7_KjU = Color.INSTANCE.m5349getUnspecified0d7_KjU();
        long jM5348getTransparent0d7_KjU2 = Color.INSTANCE.m5348getTransparent0d7_KjU();
        long jFromToken3 = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getDisabledLabelTextColor());
        long jM5311copywmQWz5c = Color.m5311copywmQWz5c(jFromToken3, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken3) : SuggestionChipTokens.INSTANCE.getDisabledLabelTextOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken3) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken3) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken3) : 0.0f);
        long jFromToken4 = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getDisabledLeadingIconColor());
        ChipColors it2 = new ChipColors(jM5348getTransparent0d7_KjU, jFromToken, jFromToken2, jM5349getUnspecified0d7_KjU, jM5348getTransparent0d7_KjU2, jM5311copywmQWz5c, Color.m5311copywmQWz5c(jFromToken4, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jFromToken4) : SuggestionChipTokens.INSTANCE.getDisabledLeadingIconOpacity(), (14 & 2) != 0 ? Color.m5319getRedimpl(jFromToken4) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jFromToken4) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jFromToken4) : 0.0f), Color.INSTANCE.m5349getUnspecified0d7_KjU(), null);
        $this$defaultSuggestionChipColors.setDefaultSuggestionChipColorsCached$material3(it2);
        return it2;
    }

    static /* synthetic */ PaddingValues inputChipPadding$default(boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        return inputChipPadding(z, z2, z3);
    }

    private static final PaddingValues inputChipPadding(boolean hasAvatar, boolean hasLeadingIcon, boolean hasTrailingIcon) {
        float start = (hasAvatar || !hasLeadingIcon) ? Dp.m8150constructorimpl(4) : Dp.m8150constructorimpl(8);
        float end = hasTrailingIcon ? Dp.m8150constructorimpl(8) : Dp.m8150constructorimpl(4);
        return PaddingKt.m1045PaddingValuesa9UjIt4$default(start, 0.0f, end, 0.0f, 10, null);
    }
}
