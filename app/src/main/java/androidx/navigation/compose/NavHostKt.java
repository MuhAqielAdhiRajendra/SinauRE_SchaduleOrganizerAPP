package androidx.navigation.compose;

import androidx.collection.MutableObjectFloatMap;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.SizeTransform;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHostController;
import androidx.navigation.NavType;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.compose.ComposeNavGraphNavigator;
import androidx.navigation.compose.ComposeNavigator;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* JADX INFO: compiled from: NavHost.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001aL\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\r\u001aÚ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\u001f\b\u0002\u0010\u0010\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\f2\u001f\b\u0002\u0010\u0014\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\f2\u001f\b\u0002\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\f2\u001f\b\u0002\u0010\u0017\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\f2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\u0018\u001a\u0098\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052$\b\u0002\u0010\u0010\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0016\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0017\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2(\b\u0002\u0010\u001a\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\u001c\u001a¿\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u001d2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d2\u001d\b\u0002\u0010\u001e\u001a\u0017\u0012\u0004\u0012\u00020 \u0012\r\u0012\u000b\u0012\u0002\b\u00030!¢\u0006\u0002\b\u00190\u001f2$\b\u0002\u0010\u0010\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0016\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0017\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2(\b\u0002\u0010\u001a\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\"\u001a»\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020#2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d2\u001d\b\u0002\u0010\u001e\u001a\u0017\u0012\u0004\u0012\u00020 \u0012\r\u0012\u000b\u0012\u0002\b\u00030!¢\u0006\u0002\b\u00190\u001f2$\b\u0002\u0010\u0010\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0016\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0017\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2(\b\u0002\u0010\u001a\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010$\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010'\u001aµ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u001f\b\u0002\u0010\u0010\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\f2\u001f\b\u0002\u0010\u0014\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\f2\u001f\b\u0002\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\f2\u001f\b\u0002\u0010\u0017\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010(\u001aó\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2$\b\u0002\u0010\u0010\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0016\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00130\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2$\b\u0002\u0010\u0017\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00150\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\f2(\b\u0002\u0010\u001a\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010\n¢\u0006\u0002\b\u0019¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010)\u001a\u001c\u0010*\u001a\u0004\u0018\u00010\u0013*\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u001a\u001c\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u001a\u001c\u0010.\u001a\u0004\u0018\u00010\u0013*\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u001a\u001c\u0010/\u001a\u0004\u0018\u00010\u0015*\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u001a\u001c\u00100\u001a\u0004\u0018\u00010\u001b*\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002¨\u00061²\u0006\u0010\u00102\u001a\b\u0012\u0004\u0012\u00020\u001203X\u008a\u0084\u0002²\u0006\n\u00104\u001a\u000205X\u008a\u008e\u0002²\u0006\n\u00106\u001a\u000207X\u008a\u008e\u0002²\u0006\u0010\u00108\u001a\b\u0012\u0004\u0012\u00020\u001203X\u008a\u0084\u0002²\u0006\u0010\u00109\u001a\b\u0012\u0004\u0012\u00020\u001203X\u008a\u0084\u0002"}, d2 = {"NavHost", "", "navController", "Landroidx/navigation/NavHostController;", "startDestination", "", "modifier", "Landroidx/compose/ui/Modifier;", "route", "builder", "Lkotlin/Function1;", "Landroidx/navigation/NavGraphBuilder;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/navigation/NavHostController;Ljava/lang/String;Landroidx/compose/ui/Modifier;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "contentAlignment", "Landroidx/compose/ui/Alignment;", "enterTransition", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/navigation/NavBackStackEntry;", "Landroidx/compose/animation/EnterTransition;", "exitTransition", "Landroidx/compose/animation/ExitTransition;", "popEnterTransition", "popExitTransition", "(Landroidx/navigation/NavHostController;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "Lkotlin/jvm/JvmSuppressWildcards;", "sizeTransform", "Landroidx/compose/animation/SizeTransform;", "(Landroidx/navigation/NavHostController;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "Lkotlin/reflect/KClass;", "typeMap", "", "Lkotlin/reflect/KType;", "Landroidx/navigation/NavType;", "(Landroidx/navigation/NavHostController;Lkotlin/reflect/KClass;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Lkotlin/reflect/KClass;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "", "(Landroidx/navigation/NavHostController;Ljava/lang/Object;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Lkotlin/reflect/KClass;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "graph", "Landroidx/navigation/NavGraph;", "(Landroidx/navigation/NavHostController;Landroidx/navigation/NavGraph;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/navigation/NavHostController;Landroidx/navigation/NavGraph;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/navigation/NavHostController;Landroidx/navigation/NavGraph;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "createEnterTransition", "Landroidx/navigation/NavDestination;", "scope", "createExitTransition", "createPopEnterTransition", "createPopExitTransition", "createSizeTransform", "navigation-compose_release", "currentBackStack", "", "progress", "", "inPredictiveBack", "", "allVisibleEntries", "visibleEntries"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavHostKt {
    static final Unit NavHost$lambda$1(NavHostController navHostController, String str, Modifier modifier, String str2, Function1 function1, int i, int i2, Composer composer, int i3) throws Throwable {
        NavHost(navHostController, str, modifier, str2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavHost$lambda$13(NavHostController navHostController, String str, Modifier modifier, Alignment alignment, String str2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, int i, int i2, int i3, Composer composer, int i4) throws Throwable {
        NavHost(navHostController, str, modifier, alignment, str2, function1, function12, function13, function14, function15, function16, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit NavHost$lambda$19(NavHostController navHostController, KClass kClass, Modifier modifier, Alignment alignment, KClass kClass2, Map map, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, int i, int i2, int i3, Composer composer, int i4) throws Throwable {
        NavHost(navHostController, (KClass<?>) kClass, modifier, alignment, (KClass<?>) kClass2, (Map<KType, NavType<?>>) map, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function1, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function12, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function13, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function14, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) function15, (Function1<? super NavGraphBuilder, Unit>) function16, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit NavHost$lambda$25(NavHostController navHostController, Object obj, Modifier modifier, Alignment alignment, KClass kClass, Map map, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, int i, int i2, int i3, Composer composer, int i4) throws Throwable {
        NavHost(navHostController, obj, modifier, alignment, (KClass<?>) kClass, (Map<KType, NavType<?>>) map, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function1, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function12, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function13, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function14, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) function15, (Function1<? super NavGraphBuilder, Unit>) function16, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit NavHost$lambda$26(NavHostController navHostController, NavGraph navGraph, Modifier modifier, int i, int i2, Composer composer, int i3) throws Throwable {
        NavHost(navHostController, navGraph, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavHost$lambda$31(NavHostController navHostController, NavGraph navGraph, Modifier modifier, Alignment alignment, Function1 function1, Function1 function12, Function1 function13, Function1 function14, int i, int i2, Composer composer, int i3) throws Throwable {
        NavHost(navHostController, navGraph, modifier, alignment, function1, function12, function13, function14, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavHost$lambda$37(NavHostController navHostController, NavGraph navGraph, Modifier modifier, Alignment alignment, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, int i2, Composer composer, int i3) throws Throwable {
        NavHost(navHostController, navGraph, modifier, alignment, function1, function12, function13, function14, function15, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavHost$lambda$7(NavHostController navHostController, String str, Modifier modifier, Alignment alignment, String str2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, int i2, Composer composer, int i3) throws Throwable {
        NavHost(navHostController, str, modifier, alignment, str2, function1, function12, function13, function14, function15, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavHost$lambda$79(NavHostController navHostController, NavGraph navGraph, Modifier modifier, Alignment alignment, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, int i2, Composer composer, int i3) throws Throwable {
        NavHost(navHostController, navGraph, modifier, alignment, function1, function12, function13, function14, function15, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavHost$lambda$80(NavHostController navHostController, NavGraph navGraph, Modifier modifier, Alignment alignment, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, int i2, Composer composer, int i3) throws Throwable {
        NavHost(navHostController, navGraph, modifier, alignment, function1, function12, function13, function14, function15, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of NavHost that supports AnimatedContent")
    public static final /* synthetic */ void NavHost(final NavHostController navController, final String startDestination, Modifier modifier, String route, final Function1 builder, Composer $composer, final int $changed, final int i) throws Throwable {
        Modifier modifier2;
        String str;
        Modifier.Companion modifier3;
        String route2;
        Object value$iv;
        final Modifier modifier4;
        final String route3;
        Composer $composer2 = $composer.startRestartGroup(141827520);
        ComposerKt.sourceInformation($composer2, "C(NavHost)P(2,4,1,3)93@3782L126,91@3742L190:NavHost.kt#opm8kd");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(navController) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(startDestination) ? 32 : 16;
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
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            str = route;
        } else if (($changed & 3072) == 0) {
            str = route;
            $dirty |= $composer2.changed(str) ? 2048 : 1024;
        } else {
            str = route;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(builder) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) != 9362 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i3 == 0) {
                route2 = str;
            } else {
                route2 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(141827520, $dirty2, -1, "androidx.navigation.compose.NavHost (NavHost.kt:90)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1142694498, "CC(remember):NavHost.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 7168) == 2048) | (($dirty2 & 112) == 32) | ((57344 & $dirty2) == 16384);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                NavHostController $this$createGraph$iv = navController;
                NavigatorProvider $this$navigation$iv$iv = $this$createGraph$iv.getNavigatorProvider();
                NavGraphBuilder navGraphBuilder = new NavGraphBuilder($this$navigation$iv$iv, startDestination, route2);
                builder.invoke(navGraphBuilder);
                value$iv = navGraphBuilder.build();
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            String route4 = route2;
            NavHost(navController, (NavGraph) value$iv, modifier3, null, null, null, null, null, null, $composer2, ($dirty2 & 14) | ($dirty2 & 896), TypedValues.PositionType.TYPE_PERCENT_HEIGHT);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            route3 = route4;
        } else {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            route3 = str;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavHostKt.NavHost$lambda$1(navController, startDestination, modifier4, route3, builder, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of NavHost that supports sizeTransform")
    public static final /* synthetic */ void NavHost(final NavHostController navController, final String startDestination, Modifier modifier, Alignment contentAlignment, String route, Function1 enterTransition, Function1 exitTransition, Function1 popEnterTransition, Function1 popExitTransition, final Function1 builder, Composer $composer, final int $changed, final int i) throws Throwable {
        Modifier modifier2;
        Alignment contentAlignment2;
        String route2;
        Function1 enterTransition2;
        Function1 exitTransition2;
        Function1 popEnterTransition2;
        Function1 popEnterTransition3;
        int $dirty;
        Modifier modifier3;
        Alignment contentAlignment3;
        Function1 exitTransition3;
        String route3;
        Function1 popExitTransition2;
        Object value$iv;
        Object value$iv2;
        Modifier modifier4;
        Alignment contentAlignment4;
        Object value$iv3;
        Composer $composer2;
        final Function1 popEnterTransition4;
        final Function1 popExitTransition3;
        final Function1 popEnterTransition5;
        final Function1 popExitTransition4;
        final String route4;
        final Alignment contentAlignment5;
        final Modifier modifier5;
        Composer $composer3 = $composer.startRestartGroup(410432995);
        ComposerKt.sourceInformation($composer3, "C(NavHost)P(5,9,4,1,8,2,3,6,7)145@6007L126,143@5967L320:NavHost.kt#opm8kd");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(navController) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(startDestination) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            contentAlignment2 = contentAlignment;
        } else if (($changed & 3072) == 0) {
            contentAlignment2 = contentAlignment;
            $dirty2 |= $composer3.changed(contentAlignment2) ? 2048 : 1024;
        } else {
            contentAlignment2 = contentAlignment;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            route2 = route;
        } else if (($changed & 24576) == 0) {
            route2 = route;
            $dirty2 |= $composer3.changed(route2) ? 16384 : 8192;
        } else {
            route2 = route;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enterTransition2 = enterTransition;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            enterTransition2 = enterTransition;
            $dirty2 |= $composer3.changedInstance(enterTransition2) ? 131072 : 65536;
        } else {
            enterTransition2 = enterTransition;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            exitTransition2 = exitTransition;
        } else if (($changed & 1572864) == 0) {
            exitTransition2 = exitTransition;
            $dirty2 |= $composer3.changedInstance(exitTransition2) ? 1048576 : 524288;
        } else {
            exitTransition2 = exitTransition;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer3.changedInstance(popEnterTransition)) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer3.changedInstance(popExitTransition)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changedInstance(builder) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($dirty2 & 306783379) != 306783378 || !$composer3.getSkipping()) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "131@5472L50,134@5619L51");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                if ((i & 256) != 0) {
                    popEnterTransition3 = popEnterTransition;
                    $dirty = (-234881025) & $dirty2;
                    modifier3 = modifier2;
                    contentAlignment3 = contentAlignment2;
                    exitTransition3 = exitTransition2;
                    route3 = route2;
                    popExitTransition2 = popExitTransition;
                } else {
                    popEnterTransition3 = popEnterTransition;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    contentAlignment3 = contentAlignment2;
                    exitTransition3 = exitTransition2;
                    route3 = route2;
                    popExitTransition2 = popExitTransition;
                }
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    contentAlignment2 = Alignment.INSTANCE.getTopStart();
                }
                if (i4 != 0) {
                    route2 = null;
                }
                if (i5 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -710868907, "CC(remember):NavHost.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    enterTransition2 = (Function1) value$iv2;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -710864202, "CC(remember):NavHost.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    exitTransition2 = (Function1) value$iv;
                }
                if ((i & 128) == 0) {
                    popEnterTransition2 = popEnterTransition;
                } else {
                    popEnterTransition2 = enterTransition2;
                    $dirty2 &= -29360129;
                }
                if ((i & 256) == 0) {
                    popEnterTransition3 = popEnterTransition2;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    contentAlignment3 = contentAlignment2;
                    exitTransition3 = exitTransition2;
                    route3 = route2;
                    popExitTransition2 = popExitTransition;
                } else {
                    Alignment alignment = contentAlignment2;
                    popExitTransition2 = exitTransition2;
                    contentAlignment3 = alignment;
                    popEnterTransition3 = popEnterTransition2;
                    $dirty = $dirty2 & (-234881025);
                    modifier3 = modifier2;
                    exitTransition3 = exitTransition2;
                    route3 = route2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                modifier4 = modifier3;
                ComposerKt.traceEventStart(410432995, $dirty, -1, "androidx.navigation.compose.NavHost (NavHost.kt:142)");
            } else {
                modifier4 = modifier3;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -710851711, "CC(remember):NavHost.kt#9igjgp");
            boolean invalid$iv = ((57344 & $dirty) == 16384) | (($dirty & 112) == 32) | ((1879048192 & $dirty) == 536870912);
            Object it$iv3 = $composer3.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                NavHostController $this$createGraph$iv = navController;
                NavigatorProvider $this$navigation$iv$iv = $this$createGraph$iv.getNavigatorProvider();
                contentAlignment4 = contentAlignment3;
                NavGraphBuilder navGraphBuilder = new NavGraphBuilder($this$navigation$iv$iv, startDestination, route3);
                builder.invoke(navGraphBuilder);
                value$iv3 = navGraphBuilder.build();
                $composer3.updateRememberedValue(value$iv3);
            } else {
                contentAlignment4 = contentAlignment3;
                value$iv3 = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Alignment contentAlignment6 = contentAlignment4;
            String route5 = route3;
            Modifier modifier6 = modifier4;
            NavHost(navController, (NavGraph) value$iv3, modifier6, contentAlignment6, enterTransition2, exitTransition3, popEnterTransition3, popExitTransition2, null, $composer3, ($dirty & 14) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752) | (($dirty >> 3) & 3670016) | (29360128 & ($dirty >> 3)), 256);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            popEnterTransition4 = popEnterTransition3;
            popExitTransition3 = popExitTransition2;
            popEnterTransition5 = enterTransition2;
            popExitTransition4 = exitTransition3;
            route4 = route5;
            contentAlignment5 = contentAlignment6;
            modifier5 = modifier6;
        } else {
            $composer3.skipToGroupEnd();
            popEnterTransition4 = popEnterTransition;
            popEnterTransition5 = enterTransition2;
            modifier5 = modifier2;
            contentAlignment5 = contentAlignment2;
            $composer2 = $composer3;
            popExitTransition4 = exitTransition2;
            route4 = route2;
            popExitTransition3 = popExitTransition;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavHostKt.NavHost$lambda$7(navController, startDestination, modifier5, contentAlignment5, route4, popEnterTransition5, popExitTransition4, popEnterTransition4, popExitTransition3, builder, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void NavHost(final NavHostController navController, final String startDestination, Modifier modifier, Alignment contentAlignment, String route, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, final Function1<? super NavGraphBuilder, Unit> function16, Composer $composer, final int $changed, final int $changed1, final int i) throws Throwable {
        Modifier modifier2;
        Alignment alignment;
        String route2;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function17;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function18;
        int i2;
        Alignment contentAlignment2;
        Modifier modifier3;
        int $dirty;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function19;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function110;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function111;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function112;
        int $dirty2;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function113;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function114;
        int $dirty3;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function115;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function116;
        String route3;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function117;
        Modifier modifier4;
        Object value$iv;
        Object value$iv2;
        Modifier modifier5;
        Alignment contentAlignment3;
        Composer $composer2;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function118;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function119;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function120;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function121;
        final String route4;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function122;
        final Alignment contentAlignment4;
        final Modifier modifier6;
        Composer $composer3 = $composer.startRestartGroup(1840250294);
        ComposerKt.sourceInformation($composer3, "C(NavHost)P(5,10,4,1,8,2,3,6,7,9)213@8658L126,211@8618L343:NavHost.kt#opm8kd");
        int $dirty4 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(navController) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer3.changed(startDestination) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            alignment = contentAlignment;
        } else if (($changed & 3072) == 0) {
            alignment = contentAlignment;
            $dirty4 |= $composer3.changed(alignment) ? 2048 : 1024;
        } else {
            alignment = contentAlignment;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
            route2 = route;
        } else if (($changed & 24576) == 0) {
            route2 = route;
            $dirty4 |= $composer3.changed(route2) ? 16384 : 8192;
        } else {
            route2 = route;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function17 = function1;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function17 = function1;
            $dirty4 |= $composer3.changedInstance(function17) ? 131072 : 65536;
        } else {
            function17 = function1;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty4 |= $composer3.changedInstance(function12) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                function18 = function13;
                int i8 = $composer3.changedInstance(function18) ? 8388608 : 4194304;
                $dirty4 |= i8;
            } else {
                function18 = function13;
            }
            $dirty4 |= i8;
        } else {
            function18 = function13;
        }
        if (($changed & 100663296) == 0) {
            $dirty4 |= ((i & 256) == 0 && $composer3.changedInstance(function14)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty4 |= 805306368;
            i2 = i9;
        } else if (($changed & 805306368) == 0) {
            i2 = i9;
            $dirty4 |= $composer3.changedInstance(function15) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = i9;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(function16) ? 4 : 2;
        }
        int $dirty12 = $dirty1;
        if ((306783379 & $dirty4) == 306783378 && ($dirty12 & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            function119 = function15;
            modifier6 = modifier2;
            $composer2 = $composer3;
            contentAlignment4 = alignment;
            function118 = function14;
            function121 = function18;
            function122 = function17;
            route4 = route2;
            function120 = function12;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "188@7838L58,194@8039L59");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier7 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                contentAlignment2 = i4 != 0 ? Alignment.INSTANCE.getTopStart() : alignment;
                if (i5 != 0) {
                    route2 = null;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -1045981872, "CC(remember):NavHost.kt#9igjgp");
                    modifier3 = modifier7;
                    Object it$iv = $composer3.rememberedValue();
                    $dirty = $dirty4;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda22
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    function19 = (Function1) value$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    modifier3 = modifier7;
                    $dirty = $dirty4;
                    function19 = function17;
                }
                if (i7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -1045975439, "CC(remember):NavHost.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    function110 = function19;
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda23
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv2;
                    }
                    function111 = (Function1) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    function110 = function19;
                    function111 = function12;
                }
                if ((i & 128) != 0) {
                    function112 = function110;
                    $dirty2 = $dirty & (-29360129);
                } else {
                    function112 = function18;
                    $dirty2 = $dirty;
                }
                if ((i & 256) != 0) {
                    function113 = function111;
                    $dirty2 &= -234881025;
                } else {
                    function113 = function14;
                }
                if (i2 != 0) {
                    $dirty3 = $dirty2;
                    function114 = null;
                    function17 = function110;
                    function115 = function113;
                    function116 = function111;
                    route3 = route2;
                    function117 = function112;
                    modifier4 = modifier3;
                } else {
                    function114 = function15;
                    $dirty3 = $dirty2;
                    function115 = function113;
                    function17 = function110;
                    function116 = function111;
                    route3 = route2;
                    function117 = function112;
                    modifier4 = modifier3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 128) != 0) {
                    $dirty4 &= -29360129;
                }
                if ((i & 256) != 0) {
                    int i10 = (-234881025) & $dirty4;
                    function114 = function15;
                    modifier4 = modifier2;
                    contentAlignment2 = alignment;
                    function115 = function14;
                    $dirty3 = i10;
                    route3 = route2;
                    function117 = function18;
                    function116 = function12;
                } else {
                    function114 = function15;
                    route3 = route2;
                    contentAlignment2 = alignment;
                    $dirty3 = $dirty4;
                    function117 = function18;
                    modifier4 = modifier2;
                    function116 = function12;
                    function115 = function14;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                modifier5 = modifier4;
                contentAlignment3 = contentAlignment2;
                ComposerKt.traceEventStart(1840250294, $dirty3, $dirty12, "androidx.navigation.compose.NavHost (NavHost.kt:210)");
            } else {
                modifier5 = modifier4;
                contentAlignment3 = contentAlignment2;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1045955564, "CC(remember):NavHost.kt#9igjgp");
            boolean invalid$iv = ((57344 & $dirty3) == 16384) | (($dirty3 & 112) == 32) | (($dirty12 & 14) == 4);
            Object value$iv3 = $composer3.rememberedValue();
            if (invalid$iv || value$iv3 == Composer.INSTANCE.getEmpty()) {
                NavHostController $this$createGraph$iv = navController;
                NavigatorProvider $this$navigation$iv$iv = $this$createGraph$iv.getNavigatorProvider();
                NavGraphBuilder navGraphBuilder = new NavGraphBuilder($this$navigation$iv$iv, startDestination, route3);
                function16.invoke(navGraphBuilder);
                value$iv3 = navGraphBuilder.build();
                $composer3.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String route5 = route3;
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function123 = function17;
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function124 = function116;
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function125 = function117;
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function126 = function115;
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function127 = function114;
            Alignment contentAlignment5 = contentAlignment3;
            Modifier modifier8 = modifier5;
            NavHost(navController, (NavGraph) value$iv3, modifier8, contentAlignment5, function123, function124, function125, function126, function127, $composer3, ($dirty3 & 14) | ($dirty3 & 896) | ($dirty3 & 7168) | (($dirty3 >> 3) & 57344) | (($dirty3 >> 3) & 458752) | (($dirty3 >> 3) & 3670016) | (($dirty3 >> 3) & 29360128) | (234881024 & ($dirty3 >> 3)), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function118 = function126;
            function119 = function127;
            function120 = function124;
            function121 = function125;
            route4 = route5;
            function122 = function123;
            contentAlignment4 = contentAlignment5;
            modifier6 = modifier8;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavHostKt.NavHost$lambda$13(navController, startDestination, modifier6, contentAlignment4, route4, function122, function120, function121, function118, function119, function16, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void NavHost(final NavHostController navController, final KClass<?> kClass, Modifier modifier, Alignment contentAlignment, KClass<?> kClass2, Map<KType, NavType<?>> map, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, final Function1<? super NavGraphBuilder, Unit> function16, Composer $composer, final int $changed, final int $changed1, final int i) throws Throwable {
        Modifier modifier2;
        Alignment alignment;
        KClass<?> kClass3;
        Map<KType, NavType<?>> map2;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function17;
        int i2;
        Alignment contentAlignment2;
        Modifier modifier3;
        int $dirty;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function18;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function19;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function110;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function111;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function112;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function113;
        KClass<?> kClass4;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function114;
        Map<KType, NavType<?>> map3;
        int $dirty2;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function115;
        Modifier modifier4;
        Object value$iv;
        Object value$iv2;
        Modifier modifier5;
        Alignment contentAlignment3;
        Composer $composer2;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function116;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function117;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function118;
        final Map<KType, NavType<?>> map4;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function119;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function120;
        final KClass<?> kClass5;
        final Alignment contentAlignment4;
        final Modifier modifier6;
        Composer $composer3 = $composer.startRestartGroup(750467758);
        ComposerKt.sourceInformation($composer3, "C(NavHost)P(5,10,4,1,8,11,2,3,6,7,9)285@11605L135,283@11565L352:NavHost.kt#opm8kd");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(navController) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(kClass) ? 32 : 16;
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
            alignment = contentAlignment;
        } else if (($changed & 3072) == 0) {
            alignment = contentAlignment;
            $dirty3 |= $composer3.changed(alignment) ? 2048 : 1024;
        } else {
            alignment = contentAlignment;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
            kClass3 = kClass2;
        } else if (($changed & 24576) == 0) {
            kClass3 = kClass2;
            $dirty3 |= $composer3.changedInstance(kClass3) ? 16384 : 8192;
        } else {
            kClass3 = kClass2;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            map2 = map;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            map2 = map;
            $dirty3 |= $composer3.changedInstance(map2) ? 131072 : 65536;
        } else {
            map2 = map;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            function17 = function1;
        } else if (($changed & 1572864) == 0) {
            function17 = function1;
            $dirty3 |= $composer3.changedInstance(function17) ? 1048576 : 524288;
        } else {
            function17 = function1;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function12) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty3 |= ((i & 256) == 0 && $composer3.changedInstance(function13)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty3 |= ((i & 512) == 0 && $composer3.changedInstance(function14)) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int i9 = i & 1024;
        if (i9 != 0) {
            $dirty1 |= 6;
            i2 = i9;
        } else if (($changed1 & 6) == 0) {
            i2 = i9;
            $dirty1 |= $composer3.changedInstance(function15) ? 4 : 2;
        } else {
            i2 = i9;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(function16) ? 32 : 16;
        }
        int $dirty12 = $dirty1;
        if ((306783379 & $dirty3) == 306783378 && ($dirty12 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            function118 = function15;
            contentAlignment4 = alignment;
            kClass5 = kClass3;
            map4 = map2;
            function119 = function17;
            $composer2 = $composer3;
            modifier6 = modifier2;
            function120 = function12;
            function116 = function13;
            function117 = function14;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "260@10785L58,266@10986L59");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier7 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                contentAlignment2 = i4 != 0 ? Alignment.INSTANCE.getTopStart() : alignment;
                KClass<?> kClass6 = i5 != 0 ? null : kClass3;
                Map<KType, NavType<?>> mapEmptyMap = i6 != 0 ? MapsKt.emptyMap() : map2;
                if (i7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -354976248, "CC(remember):NavHost.kt#9igjgp");
                    modifier3 = modifier7;
                    Object it$iv = $composer3.rememberedValue();
                    $dirty = $dirty3;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda27
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    function18 = (Function1) value$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    modifier3 = modifier7;
                    $dirty = $dirty3;
                    function18 = function17;
                }
                if (i8 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -354969815, "CC(remember):NavHost.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    function19 = function18;
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda28
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv2;
                    }
                    function110 = (Function1) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    function19 = function18;
                    function110 = function12;
                }
                if ((i & 256) != 0) {
                    function111 = function19;
                    $dirty &= -234881025;
                } else {
                    function111 = function13;
                }
                if ((i & 512) != 0) {
                    function112 = function110;
                    $dirty &= -1879048193;
                } else {
                    function112 = function14;
                }
                if (i2 != 0) {
                    KClass<?> kClass7 = kClass6;
                    function113 = function110;
                    kClass4 = kClass7;
                    map3 = mapEmptyMap;
                    function114 = null;
                    $dirty2 = $dirty;
                    function17 = function19;
                    function115 = function111;
                    modifier4 = modifier3;
                } else {
                    KClass<?> kClass8 = kClass6;
                    function113 = function110;
                    kClass4 = kClass8;
                    function17 = function19;
                    function114 = function15;
                    map3 = mapEmptyMap;
                    $dirty2 = $dirty;
                    function115 = function111;
                    modifier4 = modifier3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 256) != 0) {
                    $dirty3 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty2 = $dirty3 & (-1879048193);
                    contentAlignment2 = alignment;
                    kClass4 = kClass3;
                    map3 = map2;
                    modifier4 = modifier2;
                    function113 = function12;
                    function115 = function13;
                    function112 = function14;
                    function114 = function15;
                } else {
                    $dirty2 = $dirty3;
                    contentAlignment2 = alignment;
                    kClass4 = kClass3;
                    map3 = map2;
                    modifier4 = modifier2;
                    function113 = function12;
                    function115 = function13;
                    function112 = function14;
                    function114 = function15;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                modifier5 = modifier4;
                contentAlignment3 = contentAlignment2;
                ComposerKt.traceEventStart(750467758, $dirty2, $dirty12, "androidx.navigation.compose.NavHost (NavHost.kt:282)");
            } else {
                modifier5 = modifier4;
                contentAlignment3 = contentAlignment2;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -354949931, "CC(remember):NavHost.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(kClass4) | $composer3.changed(kClass) | (($dirty12 & 112) == 32);
            Object value$iv3 = $composer3.rememberedValue();
            if (invalid$iv || value$iv3 == Composer.INSTANCE.getEmpty()) {
                NavHostController $this$createGraph$iv = navController;
                NavigatorProvider $this$navigation$iv$iv = $this$createGraph$iv.getNavigatorProvider();
                NavGraphBuilder navGraphBuilder = new NavGraphBuilder($this$navigation$iv$iv, kClass, kClass4, map3);
                function16.invoke(navGraphBuilder);
                value$iv3 = navGraphBuilder.build();
                $composer3.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Alignment contentAlignment5 = contentAlignment3;
            KClass<?> kClass9 = kClass4;
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function121 = function17;
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function122 = function114;
            Modifier modifier8 = modifier5;
            NavHost(navController, (NavGraph) value$iv3, modifier8, contentAlignment5, function121, function113, function115, function112, function122, $composer3, ($dirty2 & 14) | ($dirty2 & 896) | ($dirty2 & 7168) | (($dirty2 >> 6) & 57344) | (($dirty2 >> 6) & 458752) | (($dirty2 >> 6) & 3670016) | (29360128 & ($dirty2 >> 6)) | (($dirty12 << 24) & 234881024), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function116 = function115;
            function117 = function112;
            function118 = function122;
            map4 = map3;
            function119 = function121;
            function120 = function113;
            kClass5 = kClass9;
            contentAlignment4 = contentAlignment5;
            modifier6 = modifier8;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavHostKt.NavHost$lambda$19(navController, kClass, modifier6, contentAlignment4, kClass5, map4, function119, function120, function116, function117, function118, function16, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void NavHost(final NavHostController navController, final Object startDestination, Modifier modifier, Alignment contentAlignment, KClass<?> kClass, Map<KType, NavType<?>> map, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, final Function1<? super NavGraphBuilder, Unit> function16, Composer $composer, final int $changed, final int $changed1, final int i) throws Throwable {
        Modifier modifier2;
        Alignment alignment;
        KClass<?> kClass2;
        Map<KType, NavType<?>> map2;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function17;
        int i2;
        Alignment contentAlignment2;
        Modifier modifier3;
        int $dirty;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function18;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function19;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function110;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function111;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function112;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function113;
        KClass<?> kClass3;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function114;
        Map<KType, NavType<?>> map3;
        int $dirty2;
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function115;
        Modifier modifier4;
        Object value$iv;
        Object value$iv2;
        Modifier modifier5;
        Alignment contentAlignment3;
        Composer $composer2;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function116;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function117;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function118;
        final Map<KType, NavType<?>> map4;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function119;
        final Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function120;
        final KClass<?> kClass4;
        final Alignment contentAlignment4;
        final Modifier modifier6;
        Composer $composer3 = $composer.startRestartGroup(-1476019057);
        ComposerKt.sourceInformation($composer3, "C(NavHost)P(5,10,4,1,8,11,2,3,6,7,9)357@14557L135,355@14517L352:NavHost.kt#opm8kd");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(navController) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(startDestination) ? 32 : 16;
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
            alignment = contentAlignment;
        } else if (($changed & 3072) == 0) {
            alignment = contentAlignment;
            $dirty3 |= $composer3.changed(alignment) ? 2048 : 1024;
        } else {
            alignment = contentAlignment;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
            kClass2 = kClass;
        } else if (($changed & 24576) == 0) {
            kClass2 = kClass;
            $dirty3 |= $composer3.changedInstance(kClass2) ? 16384 : 8192;
        } else {
            kClass2 = kClass;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            map2 = map;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            map2 = map;
            $dirty3 |= $composer3.changedInstance(map2) ? 131072 : 65536;
        } else {
            map2 = map;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            function17 = function1;
        } else if (($changed & 1572864) == 0) {
            function17 = function1;
            $dirty3 |= $composer3.changedInstance(function17) ? 1048576 : 524288;
        } else {
            function17 = function1;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function12) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty3 |= ((i & 256) == 0 && $composer3.changedInstance(function13)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty3 |= ((i & 512) == 0 && $composer3.changedInstance(function14)) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int i9 = i & 1024;
        if (i9 != 0) {
            $dirty1 |= 6;
            i2 = i9;
        } else if (($changed1 & 6) == 0) {
            i2 = i9;
            $dirty1 |= $composer3.changedInstance(function15) ? 4 : 2;
        } else {
            i2 = i9;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(function16) ? 32 : 16;
        }
        int $dirty12 = $dirty1;
        if ((306783379 & $dirty3) == 306783378 && ($dirty12 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            function118 = function15;
            contentAlignment4 = alignment;
            kClass4 = kClass2;
            map4 = map2;
            function119 = function17;
            $composer2 = $composer3;
            modifier6 = modifier2;
            function120 = function12;
            function116 = function13;
            function117 = function14;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "332@13737L58,338@13938L59");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier7 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                contentAlignment2 = i4 != 0 ? Alignment.INSTANCE.getTopStart() : alignment;
                KClass<?> kClass5 = i5 != 0 ? null : kClass2;
                Map<KType, NavType<?>> mapEmptyMap = i6 != 0 ? MapsKt.emptyMap() : map2;
                if (i7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -1115092247, "CC(remember):NavHost.kt#9igjgp");
                    modifier3 = modifier7;
                    Object it$iv = $composer3.rememberedValue();
                    $dirty = $dirty3;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda16
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    function18 = (Function1) value$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    modifier3 = modifier7;
                    $dirty = $dirty3;
                    function18 = function17;
                }
                if (i8 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -1115085814, "CC(remember):NavHost.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    function19 = function18;
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda17
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv2;
                    }
                    function110 = (Function1) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    function19 = function18;
                    function110 = function12;
                }
                if ((i & 256) != 0) {
                    function111 = function19;
                    $dirty &= -234881025;
                } else {
                    function111 = function13;
                }
                if ((i & 512) != 0) {
                    function112 = function110;
                    $dirty &= -1879048193;
                } else {
                    function112 = function14;
                }
                if (i2 != 0) {
                    KClass<?> kClass6 = kClass5;
                    function113 = function110;
                    kClass3 = kClass6;
                    map3 = mapEmptyMap;
                    function114 = null;
                    $dirty2 = $dirty;
                    function17 = function19;
                    function115 = function111;
                    modifier4 = modifier3;
                } else {
                    KClass<?> kClass7 = kClass5;
                    function113 = function110;
                    kClass3 = kClass7;
                    function17 = function19;
                    function114 = function15;
                    map3 = mapEmptyMap;
                    $dirty2 = $dirty;
                    function115 = function111;
                    modifier4 = modifier3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 256) != 0) {
                    $dirty3 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty2 = $dirty3 & (-1879048193);
                    contentAlignment2 = alignment;
                    kClass3 = kClass2;
                    map3 = map2;
                    modifier4 = modifier2;
                    function113 = function12;
                    function115 = function13;
                    function112 = function14;
                    function114 = function15;
                } else {
                    $dirty2 = $dirty3;
                    contentAlignment2 = alignment;
                    kClass3 = kClass2;
                    map3 = map2;
                    modifier4 = modifier2;
                    function113 = function12;
                    function115 = function13;
                    function112 = function14;
                    function114 = function15;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                modifier5 = modifier4;
                contentAlignment3 = contentAlignment2;
                ComposerKt.traceEventStart(-1476019057, $dirty2, $dirty12, "androidx.navigation.compose.NavHost (NavHost.kt:354)");
            } else {
                modifier5 = modifier4;
                contentAlignment3 = contentAlignment2;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1115065930, "CC(remember):NavHost.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(kClass3) | $composer3.changed(startDestination) | (($dirty12 & 112) == 32);
            Object value$iv3 = $composer3.rememberedValue();
            if (invalid$iv || value$iv3 == Composer.INSTANCE.getEmpty()) {
                NavHostController $this$createGraph$iv = navController;
                NavigatorProvider $this$navigation$iv$iv = $this$createGraph$iv.getNavigatorProvider();
                NavGraphBuilder navGraphBuilder = new NavGraphBuilder($this$navigation$iv$iv, startDestination, kClass3, map3);
                function16.invoke(navGraphBuilder);
                value$iv3 = navGraphBuilder.build();
                $composer3.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Alignment contentAlignment5 = contentAlignment3;
            KClass<?> kClass8 = kClass3;
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function121 = function17;
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function122 = function114;
            Modifier modifier8 = modifier5;
            NavHost(navController, (NavGraph) value$iv3, modifier8, contentAlignment5, function121, function113, function115, function112, function122, $composer3, ($dirty2 & 14) | ($dirty2 & 896) | ($dirty2 & 7168) | (($dirty2 >> 6) & 57344) | (($dirty2 >> 6) & 458752) | (($dirty2 >> 6) & 3670016) | (29360128 & ($dirty2 >> 6)) | (($dirty12 << 24) & 234881024), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function116 = function115;
            function117 = function112;
            function118 = function122;
            map4 = map3;
            function119 = function121;
            function120 = function113;
            kClass4 = kClass8;
            contentAlignment4 = contentAlignment5;
            modifier6 = modifier8;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavHostKt.NavHost$lambda$25(navController, startDestination, modifier6, contentAlignment4, kClass4, map4, function119, function120, function116, function117, function118, function16, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of NavHost that supports AnimatedContent")
    public static final /* synthetic */ void NavHost(final NavHostController navController, final NavGraph graph, Modifier modifier, Composer $composer, final int $changed, final int i) throws Throwable {
        NavHostController navHostController;
        NavGraph navGraph;
        Modifier modifier2;
        Modifier modifier3;
        final Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(-957014592);
        ComposerKt.sourceInformation($composer2, "C(NavHost)P(2)392@15649L39:NavHost.kt#opm8kd");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            navHostController = navController;
        } else if (($changed & 6) == 0) {
            navHostController = navController;
            $dirty |= $composer2.changedInstance(navHostController) ? 4 : 2;
        } else {
            navHostController = navController;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            navGraph = graph;
        } else if (($changed & 48) == 0) {
            navGraph = graph;
            $dirty |= $composer2.changedInstance(navGraph) ? 32 : 16;
        } else {
            navGraph = graph;
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
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-957014592, $dirty, -1, "androidx.navigation.compose.NavHost (NavHost.kt:392)");
            }
            NavHost(navHostController, navGraph, modifier3, null, null, null, null, null, null, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896), TypedValues.PositionType.TYPE_PERCENT_HEIGHT);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavHostKt.NavHost$lambda$26(navController, graph, modifier4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of NavHost that supports sizeTransform")
    public static final /* synthetic */ void NavHost(final NavHostController navController, final NavGraph graph, Modifier modifier, Alignment contentAlignment, Function1 enterTransition, Function1 exitTransition, Function1 popEnterTransition, Function1 popExitTransition, Composer $composer, final int $changed, final int i) throws Throwable {
        NavHostController navHostController;
        NavGraph navGraph;
        Modifier modifier2;
        Alignment contentAlignment2;
        Function1 enterTransition2;
        Function1 exitTransition2;
        Function1 popEnterTransition2;
        Modifier modifier3;
        int i2;
        int $dirty;
        Modifier modifier4;
        Function1 popExitTransition2;
        Function1 exitTransition3;
        Function1 popEnterTransition3;
        int $dirty2;
        Alignment contentAlignment3;
        Function1 popEnterTransition4;
        Object value$iv;
        Object value$iv2;
        Composer $composer2;
        final Modifier modifier5;
        final Alignment contentAlignment4;
        final Function1 enterTransition3;
        final Function1 exitTransition4;
        final Function1 popEnterTransition5;
        final Function1 popExitTransition3;
        Composer $composer3 = $composer.startRestartGroup(-1818191915);
        ComposerKt.sourceInformation($composer3, "C(NavHost)P(5,3,4)430@17381L199:NavHost.kt#opm8kd");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            navHostController = navController;
        } else if (($changed & 6) == 0) {
            navHostController = navController;
            $dirty3 |= $composer3.changedInstance(navHostController) ? 4 : 2;
        } else {
            navHostController = navController;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
            navGraph = graph;
        } else if (($changed & 48) == 0) {
            navGraph = graph;
            $dirty3 |= $composer3.changedInstance(navGraph) ? 32 : 16;
        } else {
            navGraph = graph;
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
            contentAlignment2 = contentAlignment;
        } else if (($changed & 3072) == 0) {
            contentAlignment2 = contentAlignment;
            $dirty3 |= $composer3.changed(contentAlignment2) ? 2048 : 1024;
        } else {
            contentAlignment2 = contentAlignment;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
            enterTransition2 = enterTransition;
        } else if (($changed & 24576) == 0) {
            enterTransition2 = enterTransition;
            $dirty3 |= $composer3.changedInstance(enterTransition2) ? 16384 : 8192;
        } else {
            enterTransition2 = enterTransition;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            exitTransition2 = exitTransition;
        } else if ((196608 & $changed) == 0) {
            exitTransition2 = exitTransition;
            $dirty3 |= $composer3.changedInstance(exitTransition2) ? 131072 : 65536;
        } else {
            exitTransition2 = exitTransition;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                popEnterTransition2 = popEnterTransition;
                int i7 = $composer3.changedInstance(popEnterTransition2) ? 1048576 : 524288;
                $dirty3 |= i7;
            } else {
                popEnterTransition2 = popEnterTransition;
            }
            $dirty3 |= i7;
        } else {
            popEnterTransition2 = popEnterTransition;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= ((i & 128) == 0 && $composer3.changedInstance(popExitTransition)) ? 8388608 : 4194304;
        }
        int $dirty4 = $dirty3;
        if (($dirty3 & 4793491) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier5 = modifier2;
            contentAlignment4 = contentAlignment2;
            enterTransition3 = enterTransition2;
            exitTransition4 = exitTransition2;
            popEnterTransition5 = popEnterTransition2;
            popExitTransition3 = popExitTransition;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "419@16926L50,422@17073L51");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if (i4 != 0) {
                    contentAlignment2 = Alignment.INSTANCE.getTopStart();
                }
                if (i5 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 769272135, "CC(remember):NavHost.kt#9igjgp");
                    i2 = -29360129;
                    Object it$iv = $composer3.rememberedValue();
                    modifier3 = modifier6;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda19
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    enterTransition2 = (Function1) value$iv2;
                } else {
                    modifier3 = modifier6;
                    i2 = -29360129;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 769276840, "CC(remember):NavHost.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv = new Function1() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda20
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(TypedValues.TransitionType.TYPE_DURATION, 0, null, 6, null), 0.0f, 2, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    exitTransition2 = (Function1) value$iv;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty4 & (-3670017);
                    popEnterTransition2 = enterTransition2;
                } else {
                    $dirty = $dirty4;
                }
                if ((i & 128) != 0) {
                    $dirty &= i2;
                    modifier4 = modifier3;
                    popExitTransition2 = exitTransition2;
                    exitTransition3 = exitTransition2;
                    popEnterTransition3 = popEnterTransition2;
                    $dirty2 = -1818191915;
                    contentAlignment3 = contentAlignment2;
                    popEnterTransition4 = enterTransition2;
                } else {
                    modifier4 = modifier3;
                    popExitTransition2 = popExitTransition;
                    exitTransition3 = exitTransition2;
                    popEnterTransition3 = popEnterTransition2;
                    $dirty2 = -1818191915;
                    contentAlignment3 = contentAlignment2;
                    popEnterTransition4 = enterTransition2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 64) != 0 ? $dirty4 & (-3670017) : $dirty4;
                if ((i & 128) != 0) {
                    popExitTransition2 = popExitTransition;
                    $dirty = $dirty5 & (-29360129);
                    modifier4 = modifier2;
                    exitTransition3 = exitTransition2;
                    popEnterTransition3 = popEnterTransition2;
                    $dirty2 = -1818191915;
                    contentAlignment3 = contentAlignment2;
                    popEnterTransition4 = enterTransition2;
                } else {
                    popExitTransition2 = popExitTransition;
                    $dirty = $dirty5;
                    modifier4 = modifier2;
                    exitTransition3 = exitTransition2;
                    popEnterTransition3 = popEnterTransition2;
                    $dirty2 = -1818191915;
                    contentAlignment3 = contentAlignment2;
                    popEnterTransition4 = enterTransition2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart($dirty2, $dirty, -1, "androidx.navigation.compose.NavHost (NavHost.kt:429)");
            }
            $composer2 = $composer3;
            NavHost(navHostController, navGraph, modifier4, contentAlignment3, popEnterTransition4, exitTransition3, popEnterTransition3, popExitTransition2, null, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty), 256);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
            contentAlignment4 = contentAlignment3;
            enterTransition3 = popEnterTransition4;
            exitTransition4 = exitTransition3;
            popEnterTransition5 = popEnterTransition3;
            popExitTransition3 = popExitTransition2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.navigation.compose.NavHostKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavHostKt.NavHost$lambda$31(navController, graph, modifier5, contentAlignment4, enterTransition3, exitTransition4, popEnterTransition5, popExitTransition3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:230:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x052b  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0539  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x056b  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x056d  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x05cc  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x05fc  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x060a  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0628  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x068f  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0716  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x072f  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0775  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0782  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0800  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0817  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void NavHost(final androidx.navigation.NavHostController r37, final androidx.navigation.NavGraph r38, androidx.compose.ui.Modifier r39, androidx.compose.ui.Alignment r40, kotlin.jvm.functions.Function1<androidx.compose.animation.AnimatedContentTransitionScope<androidx.navigation.NavBackStackEntry>, androidx.compose.animation.EnterTransition> r41, kotlin.jvm.functions.Function1<androidx.compose.animation.AnimatedContentTransitionScope<androidx.navigation.NavBackStackEntry>, androidx.compose.animation.ExitTransition> r42, kotlin.jvm.functions.Function1<androidx.compose.animation.AnimatedContentTransitionScope<androidx.navigation.NavBackStackEntry>, androidx.compose.animation.EnterTransition> r43, kotlin.jvm.functions.Function1<androidx.compose.animation.AnimatedContentTransitionScope<androidx.navigation.NavBackStackEntry>, androidx.compose.animation.ExitTransition> r44, kotlin.jvm.functions.Function1<androidx.compose.animation.AnimatedContentTransitionScope<androidx.navigation.NavBackStackEntry>, androidx.compose.animation.SizeTransform> r45, androidx.compose.runtime.Composer r46, final int r47, final int r48) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 2313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.compose.NavHostKt.NavHost(androidx.navigation.NavHostController, androidx.navigation.NavGraph, androidx.compose.ui.Modifier, androidx.compose.ui.Alignment, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<NavBackStackEntry> NavHost$lambda$38(State<? extends List<NavBackStackEntry>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float NavHost$lambda$40(MutableFloatState $progress$delegate) {
        MutableFloatState $this$getValue$iv = $progress$delegate;
        return $this$getValue$iv.getFloatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean NavHost$lambda$43(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NavHost$lambda$44(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final DisposableEffectResult NavHost$lambda$48$lambda$47(NavHostController $navController, LifecycleOwner $lifecycleOwner, DisposableEffectScope $this$DisposableEffect) {
        $navController.setLifecycleOwner($lifecycleOwner);
        return new DisposableEffectResult() { // from class: androidx.navigation.compose.NavHostKt$NavHost$lambda$48$lambda$47$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
            }
        };
    }

    private static final List<NavBackStackEntry> NavHost$lambda$49(State<? extends List<NavBackStackEntry>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<NavBackStackEntry> NavHost$lambda$53(State<? extends List<NavBackStackEntry>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    static final List NavHost$lambda$52$lambda$51(State $allVisibleEntries$delegate) {
        Iterable $this$filter$iv = NavHost$lambda$49($allVisibleEntries$delegate);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            NavBackStackEntry entry = (NavBackStackEntry) element$iv$iv;
            if (Intrinsics.areEqual(entry.getDestination().getNavigatorName(), ComposeNavigator.NAME)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final EnterTransition NavHost$lambda$58$lambda$57(ComposeNavigator $composeNavigator, Function1 $popEnterTransition, Function1 $enterTransition, MutableState $inPredictiveBack$delegate, AnimatedContentTransitionScope animatedContentTransitionScope) {
        NavDestination destination = ((NavBackStackEntry) animatedContentTransitionScope.getTargetState()).getDestination();
        Intrinsics.checkNotNull(destination, "null cannot be cast to non-null type androidx.navigation.compose.ComposeNavigator.Destination");
        ComposeNavigator.Destination targetDestination = (ComposeNavigator.Destination) destination;
        EnterTransition enterTransition = null;
        if ($composeNavigator.isPop$navigation_compose_release().getValue().booleanValue() || NavHost$lambda$43($inPredictiveBack$delegate)) {
            Iterator<NavDestination> it = NavDestination.INSTANCE.getHierarchy(targetDestination).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NavDestination destination2 = it.next();
                EnterTransition enterTransitionCreatePopEnterTransition = createPopEnterTransition(destination2, animatedContentTransitionScope);
                if (enterTransitionCreatePopEnterTransition != null) {
                    enterTransition = enterTransitionCreatePopEnterTransition;
                    break;
                }
            }
            if (enterTransition == null) {
                return (EnterTransition) $popEnterTransition.invoke(animatedContentTransitionScope);
            }
            return enterTransition;
        }
        Iterator<NavDestination> it2 = NavDestination.INSTANCE.getHierarchy(targetDestination).iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            NavDestination destination3 = it2.next();
            EnterTransition enterTransitionCreateEnterTransition = createEnterTransition(destination3, animatedContentTransitionScope);
            if (enterTransitionCreateEnterTransition != null) {
                enterTransition = enterTransitionCreateEnterTransition;
                break;
            }
        }
        if (enterTransition == null) {
            return (EnterTransition) $enterTransition.invoke(animatedContentTransitionScope);
        }
        return enterTransition;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final ExitTransition NavHost$lambda$62$lambda$61(ComposeNavigator $composeNavigator, Function1 $popExitTransition, Function1 $exitTransition, MutableState $inPredictiveBack$delegate, AnimatedContentTransitionScope animatedContentTransitionScope) {
        NavDestination destination = ((NavBackStackEntry) animatedContentTransitionScope.getInitialState()).getDestination();
        Intrinsics.checkNotNull(destination, "null cannot be cast to non-null type androidx.navigation.compose.ComposeNavigator.Destination");
        ComposeNavigator.Destination initialDestination = (ComposeNavigator.Destination) destination;
        ExitTransition exitTransition = null;
        if ($composeNavigator.isPop$navigation_compose_release().getValue().booleanValue() || NavHost$lambda$43($inPredictiveBack$delegate)) {
            Iterator<NavDestination> it = NavDestination.INSTANCE.getHierarchy(initialDestination).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NavDestination destination2 = it.next();
                ExitTransition exitTransitionCreatePopExitTransition = createPopExitTransition(destination2, animatedContentTransitionScope);
                if (exitTransitionCreatePopExitTransition != null) {
                    exitTransition = exitTransitionCreatePopExitTransition;
                    break;
                }
            }
            if (exitTransition == null) {
                return (ExitTransition) $popExitTransition.invoke(animatedContentTransitionScope);
            }
            return exitTransition;
        }
        Iterator<NavDestination> it2 = NavDestination.INSTANCE.getHierarchy(initialDestination).iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            NavDestination destination3 = it2.next();
            ExitTransition exitTransitionCreateExitTransition = createExitTransition(destination3, animatedContentTransitionScope);
            if (exitTransitionCreateExitTransition != null) {
                exitTransition = exitTransitionCreateExitTransition;
                break;
            }
        }
        if (exitTransition == null) {
            return (ExitTransition) $exitTransition.invoke(animatedContentTransitionScope);
        }
        return exitTransition;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final SizeTransform NavHost$lambda$65$lambda$64(Function1 $sizeTransform, AnimatedContentTransitionScope animatedContentTransitionScope) {
        SizeTransform sizeTransformCreateSizeTransform;
        NavDestination destination = ((NavBackStackEntry) animatedContentTransitionScope.getTargetState()).getDestination();
        Intrinsics.checkNotNull(destination, "null cannot be cast to non-null type androidx.navigation.compose.ComposeNavigator.Destination");
        ComposeNavigator.Destination targetDestination = (ComposeNavigator.Destination) destination;
        Iterator<NavDestination> it = NavDestination.INSTANCE.getHierarchy(targetDestination).iterator();
        while (true) {
            if (!it.hasNext()) {
                sizeTransformCreateSizeTransform = null;
                break;
            }
            NavDestination destination2 = it.next();
            sizeTransformCreateSizeTransform = createSizeTransform(destination2, animatedContentTransitionScope);
            if (sizeTransformCreateSizeTransform != null) {
                break;
            }
        }
        if (sizeTransformCreateSizeTransform != null) {
            return sizeTransformCreateSizeTransform;
        }
        if ($sizeTransform != null) {
            return (SizeTransform) $sizeTransform.invoke(animatedContentTransitionScope);
        }
        return null;
    }

    static final DisposableEffectResult NavHost$lambda$69$lambda$68(final State $visibleEntries$delegate, final ComposeNavigator $composeNavigator, DisposableEffectScope $this$DisposableEffect) {
        return new DisposableEffectResult() { // from class: androidx.navigation.compose.NavHostKt$NavHost$lambda$69$lambda$68$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Iterable $this$forEach$iv = NavHostKt.NavHost$lambda$53($visibleEntries$delegate);
                for (Object element$iv : $this$forEach$iv) {
                    NavBackStackEntry entry = (NavBackStackEntry) element$iv;
                    $composeNavigator.onTransitionComplete(entry);
                }
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final ContentTransform NavHost$lambda$75$lambda$74(MutableObjectFloatMap $zIndices, ComposeNavigator $composeNavigator, Function1 $finalEnter, Function1 $finalExit, Function1 $finalSizeTransform, State $visibleEntries$delegate, MutableState $inPredictiveBack$delegate, AnimatedContentTransitionScope $this$AnimatedContent) {
        float value$iv;
        float targetZIndex;
        if (NavHost$lambda$53($visibleEntries$delegate).contains($this$AnimatedContent.getInitialState())) {
            String id = ((NavBackStackEntry) $this$AnimatedContent.getInitialState()).getId();
            int index$iv = $zIndices.findKeyIndex(id);
            if (index$iv >= 0) {
                value$iv = $zIndices.values[index$iv];
            } else {
                value$iv = 0.0f;
                $zIndices.set(id, 0.0f);
            }
            if (Intrinsics.areEqual(((NavBackStackEntry) $this$AnimatedContent.getTargetState()).getId(), ((NavBackStackEntry) $this$AnimatedContent.getInitialState()).getId())) {
                targetZIndex = value$iv;
            } else if ($composeNavigator.isPop$navigation_compose_release().getValue().booleanValue() || NavHost$lambda$43($inPredictiveBack$delegate)) {
                targetZIndex = value$iv - 1.0f;
            } else {
                targetZIndex = 1.0f + value$iv;
            }
            $zIndices.set(((NavBackStackEntry) $this$AnimatedContent.getTargetState()).getId(), targetZIndex);
            return new ContentTransform((EnterTransition) $finalEnter.invoke($this$AnimatedContent), (ExitTransition) $finalExit.invoke($this$AnimatedContent), targetZIndex, (SizeTransform) $finalSizeTransform.invoke($this$AnimatedContent));
        }
        return AnimatedContentKt.togetherWith(EnterTransition.INSTANCE.getNone(), ExitTransition.INSTANCE.getNone());
    }

    private static final EnterTransition createEnterTransition(NavDestination $this$createEnterTransition, AnimatedContentTransitionScope<NavBackStackEntry> animatedContentTransitionScope) {
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> enterTransition$navigation_compose_release;
        if ($this$createEnterTransition instanceof ComposeNavigator.Destination) {
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> enterTransition$navigation_compose_release2 = ((ComposeNavigator.Destination) $this$createEnterTransition).getEnterTransition$navigation_compose_release();
            if (enterTransition$navigation_compose_release2 != null) {
                return enterTransition$navigation_compose_release2.invoke(animatedContentTransitionScope);
            }
            return null;
        }
        if (!($this$createEnterTransition instanceof ComposeNavGraphNavigator.ComposeNavGraph) || (enterTransition$navigation_compose_release = ((ComposeNavGraphNavigator.ComposeNavGraph) $this$createEnterTransition).getEnterTransition$navigation_compose_release()) == null) {
            return null;
        }
        return enterTransition$navigation_compose_release.invoke(animatedContentTransitionScope);
    }

    private static final ExitTransition createExitTransition(NavDestination $this$createExitTransition, AnimatedContentTransitionScope<NavBackStackEntry> animatedContentTransitionScope) {
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> exitTransition$navigation_compose_release;
        if ($this$createExitTransition instanceof ComposeNavigator.Destination) {
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> exitTransition$navigation_compose_release2 = ((ComposeNavigator.Destination) $this$createExitTransition).getExitTransition$navigation_compose_release();
            if (exitTransition$navigation_compose_release2 != null) {
                return exitTransition$navigation_compose_release2.invoke(animatedContentTransitionScope);
            }
            return null;
        }
        if (!($this$createExitTransition instanceof ComposeNavGraphNavigator.ComposeNavGraph) || (exitTransition$navigation_compose_release = ((ComposeNavGraphNavigator.ComposeNavGraph) $this$createExitTransition).getExitTransition$navigation_compose_release()) == null) {
            return null;
        }
        return exitTransition$navigation_compose_release.invoke(animatedContentTransitionScope);
    }

    private static final EnterTransition createPopEnterTransition(NavDestination $this$createPopEnterTransition, AnimatedContentTransitionScope<NavBackStackEntry> animatedContentTransitionScope) {
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> popEnterTransition$navigation_compose_release;
        if ($this$createPopEnterTransition instanceof ComposeNavigator.Destination) {
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> popEnterTransition$navigation_compose_release2 = ((ComposeNavigator.Destination) $this$createPopEnterTransition).getPopEnterTransition$navigation_compose_release();
            if (popEnterTransition$navigation_compose_release2 != null) {
                return popEnterTransition$navigation_compose_release2.invoke(animatedContentTransitionScope);
            }
            return null;
        }
        if (!($this$createPopEnterTransition instanceof ComposeNavGraphNavigator.ComposeNavGraph) || (popEnterTransition$navigation_compose_release = ((ComposeNavGraphNavigator.ComposeNavGraph) $this$createPopEnterTransition).getPopEnterTransition$navigation_compose_release()) == null) {
            return null;
        }
        return popEnterTransition$navigation_compose_release.invoke(animatedContentTransitionScope);
    }

    private static final ExitTransition createPopExitTransition(NavDestination $this$createPopExitTransition, AnimatedContentTransitionScope<NavBackStackEntry> animatedContentTransitionScope) {
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> popExitTransition$navigation_compose_release;
        if ($this$createPopExitTransition instanceof ComposeNavigator.Destination) {
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> popExitTransition$navigation_compose_release2 = ((ComposeNavigator.Destination) $this$createPopExitTransition).getPopExitTransition$navigation_compose_release();
            if (popExitTransition$navigation_compose_release2 != null) {
                return popExitTransition$navigation_compose_release2.invoke(animatedContentTransitionScope);
            }
            return null;
        }
        if (!($this$createPopExitTransition instanceof ComposeNavGraphNavigator.ComposeNavGraph) || (popExitTransition$navigation_compose_release = ((ComposeNavGraphNavigator.ComposeNavGraph) $this$createPopExitTransition).getPopExitTransition$navigation_compose_release()) == null) {
            return null;
        }
        return popExitTransition$navigation_compose_release.invoke(animatedContentTransitionScope);
    }

    private static final SizeTransform createSizeTransform(NavDestination $this$createSizeTransform, AnimatedContentTransitionScope<NavBackStackEntry> animatedContentTransitionScope) {
        Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> sizeTransform$navigation_compose_release;
        if ($this$createSizeTransform instanceof ComposeNavigator.Destination) {
            Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> sizeTransform$navigation_compose_release2 = ((ComposeNavigator.Destination) $this$createSizeTransform).getSizeTransform$navigation_compose_release();
            if (sizeTransform$navigation_compose_release2 != null) {
                return sizeTransform$navigation_compose_release2.invoke(animatedContentTransitionScope);
            }
            return null;
        }
        if (!($this$createSizeTransform instanceof ComposeNavGraphNavigator.ComposeNavGraph) || (sizeTransform$navigation_compose_release = ((ComposeNavGraphNavigator.ComposeNavGraph) $this$createSizeTransform).getSizeTransform$navigation_compose_release()) == null) {
            return null;
        }
        return sizeTransform$navigation_compose_release.invoke(animatedContentTransitionScope);
    }
}
