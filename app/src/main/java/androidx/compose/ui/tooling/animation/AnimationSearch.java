package androidx.compose.ui.tooling.animation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.core.DecayAnimation;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.TargetBasedAnimation;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.tooling.ComposeAnimation;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ModifierInfo;
import androidx.compose.ui.tooling.PreviewUtils_androidKt;
import androidx.compose.ui.tooling.animation.AnimationSearch;
import androidx.compose.ui.tooling.animation.clock.ComposeAnimationClock;
import androidx.compose.ui.tooling.animation.search.AnimateXAsStateSearchInfo;
import androidx.compose.ui.tooling.animation.search.AnimatedContentSearchInfo;
import androidx.compose.ui.tooling.animation.search.AnimatedVisibilitySearchInfo;
import androidx.compose.ui.tooling.animation.search.InfiniteTransitionSearchInfo;
import androidx.compose.ui.tooling.animation.search.SearchInfo;
import androidx.compose.ui.tooling.animation.search.TransitionSearchInfo;
import androidx.compose.ui.tooling.animation.search.UnsupportedSearchInfo;
import androidx.compose.ui.tooling.data.CallGroup;
import androidx.compose.ui.tooling.data.Group;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;

/* JADX INFO: compiled from: AnimationSearch.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0001\u0018\u00002\u00020\u0001:\n!\"#$%&'()*B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J.\u0010\u0013\u001a(\u0012$\u0012\"\u0012\u001e\b\u0001\u0012\u001a\u0012\u0006\b\u0001\u0012\u00020\u0016\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00170\u00150\u00140\u0011H\u0002J\u0016\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00140\u000eH\u0002J\u0014\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u000eJ\u0014\u0010\u001f\u001a\u00020 2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u000eR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R4\u0010\u0013\u001a(\u0012$\u0012\"\u0012\u001e\b\u0001\u0012\u001a\u0012\u0006\b\u0001\u0012\u00020\u0016\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00170\u00150\u00140\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00140\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00140\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch;", "", "clock", "Lkotlin/Function0;", "Landroidx/compose/ui/tooling/animation/PreviewAnimationClock;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "transitionSearch", "Landroidx/compose/ui/tooling/animation/AnimationSearch$TransitionSearch;", "animatedContentSearch", "Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimatedContentSearch;", "animatedVisibilitySearch", "Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimatedVisibilitySearch;", "animateXAsStateSearch", "", "Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimateXAsStateSearch;", "infiniteTransitionSearch", "", "Landroidx/compose/ui/tooling/animation/AnimationSearch$InfiniteTransitionSearch;", "supportedSearch", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/ui/tooling/animation/search/SearchInfo;", "Landroidx/compose/animation/tooling/ComposeAnimation;", "Landroidx/compose/ui/tooling/animation/clock/ComposeAnimationClock;", "unsupportedSearch", "setToTrack", "setToSearch", "searchAny", "", "slotTrees", "Landroidx/compose/ui/tooling/data/Group;", "attachAllAnimations", "", "Search", "RememberSearch", "TargetBasedSearch", "DecaySearch", "InfiniteTransitionSearch", "AnimateXAsStateSearch", "AnimateContentSizeSearch", "TransitionSearch", "AnimatedVisibilitySearch", "AnimatedContentSearch", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AnimationSearch {
    public static final int $stable = 8;
    private final Function0<PreviewAnimationClock> clock;
    private final TransitionSearch transitionSearch = new TransitionSearch(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return AnimationSearch.transitionSearch$lambda$0(this.f$0, (TransitionSearchInfo) obj);
        }
    });
    private final AnimatedContentSearch animatedContentSearch = new AnimatedContentSearch(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return AnimationSearch.animatedContentSearch$lambda$0(this.f$0, (AnimatedContentSearchInfo) obj);
        }
    });
    private final AnimatedVisibilitySearch animatedVisibilitySearch = new AnimatedVisibilitySearch(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return AnimationSearch.animatedVisibilitySearch$lambda$0(this.f$0, (AnimatedVisibilitySearchInfo) obj);
        }
    });
    private final Set<Search<? extends SearchInfo<? extends ComposeAnimation, ? extends ComposeAnimationClock<?, ?>>>> supportedSearch = supportedSearch();
    private final Set<Search<? extends Object>> setToTrack = SetsKt.plus((Set) this.supportedSearch, (Iterable) unsupportedSearch());
    private final Set<Search<? extends Object>> setToSearch = SetsKt.plus((Set) this.setToTrack, (Iterable) SetsKt.setOf(this.animatedContentSearch));

    /* JADX WARN: Multi-variable type inference failed */
    public AnimationSearch(Function0<? extends PreviewAnimationClock> function0) {
        this.clock = function0;
    }

    static final Unit transitionSearch$lambda$0(AnimationSearch this$0, TransitionSearchInfo it) {
        this$0.clock.invoke().trackComposeAnimation(it);
        return Unit.INSTANCE;
    }

    static final Unit animatedContentSearch$lambda$0(AnimationSearch this$0, AnimatedContentSearchInfo it) {
        this$0.clock.invoke().trackComposeAnimation(it);
        return Unit.INSTANCE;
    }

    static final Unit animatedVisibilitySearch$lambda$0(AnimationSearch this$0, AnimatedVisibilitySearchInfo it) {
        this$0.clock.invoke().trackComposeAnimation(it);
        return Unit.INSTANCE;
    }

    private final Collection<AnimateXAsStateSearch> animateXAsStateSearch() {
        if (AnimateXAsStateComposeAnimation.INSTANCE.getApiAvailable()) {
            return SetsKt.setOf(new AnimateXAsStateSearch(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AnimationSearch.animateXAsStateSearch$lambda$0(this.f$0, (AnimateXAsStateSearchInfo) obj);
                }
            }));
        }
        return CollectionsKt.emptyList();
    }

    static final Unit animateXAsStateSearch$lambda$0(AnimationSearch this$0, AnimateXAsStateSearchInfo it) {
        this$0.clock.invoke().trackComposeAnimation(it);
        return Unit.INSTANCE;
    }

    private final Set<InfiniteTransitionSearch> infiniteTransitionSearch() {
        if (InfiniteTransitionComposeAnimation.INSTANCE.getApiAvailable()) {
            return SetsKt.setOf(new InfiniteTransitionSearch(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AnimationSearch.infiniteTransitionSearch$lambda$0(this.f$0, (InfiniteTransitionSearchInfo) obj);
                }
            }));
        }
        return SetsKt.emptySet();
    }

    static final Unit infiniteTransitionSearch$lambda$0(AnimationSearch this$0, InfiniteTransitionSearchInfo it) {
        this$0.clock.invoke().trackComposeAnimation(it);
        return Unit.INSTANCE;
    }

    private final Set<Search<? extends SearchInfo<? extends ComposeAnimation, ? extends ComposeAnimationClock<?, ?>>>> supportedSearch() {
        return SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.setOf((Object[]) new Search[]{this.transitionSearch, this.animatedVisibilitySearch}), (Iterable) animateXAsStateSearch()), (Iterable) infiniteTransitionSearch()), (Iterable) (AnimatedContentComposeAnimation.INSTANCE.getApiAvailable() ? SetsKt.setOf(this.animatedContentSearch) : SetsKt.emptySet()));
    }

    private final Collection<Search<? extends Object>> unsupportedSearch() {
        return UnsupportedComposeAnimation.INSTANCE.getApiAvailable() ? SetsKt.setOf((Object[]) new Search[]{new AnimateContentSizeSearch(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnimationSearch.unsupportedSearch$lambda$0(this.f$0, obj);
            }
        }), new TargetBasedSearch(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnimationSearch.unsupportedSearch$lambda$1(this.f$0, (TargetBasedAnimation) obj);
            }
        }), new DecaySearch(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnimationSearch.unsupportedSearch$lambda$2(this.f$0, (DecayAnimation) obj);
            }
        })}) : CollectionsKt.emptyList();
    }

    static final Unit unsupportedSearch$lambda$0(AnimationSearch this$0, Object it) {
        this$0.clock.invoke().trackComposeAnimation(new UnsupportedSearchInfo(it, "animateContentSize"));
        return Unit.INSTANCE;
    }

    static final Unit unsupportedSearch$lambda$1(AnimationSearch this$0, TargetBasedAnimation it) {
        this$0.clock.invoke().trackComposeAnimation(new UnsupportedSearchInfo(it, "TargetBasedAnimation"));
        return Unit.INSTANCE;
    }

    static final Unit unsupportedSearch$lambda$2(AnimationSearch this$0, DecayAnimation it) {
        this$0.clock.invoke().trackComposeAnimation(new UnsupportedSearchInfo(it, "DecayAnimation"));
        return Unit.INSTANCE;
    }

    public final boolean searchAny(Collection<? extends Group> slotTrees) {
        boolean z;
        Collection<? extends Group> $this$any$iv = slotTrees;
        if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            Group tree = (Group) element$iv;
            List<Group> listFindAll = PreviewUtils_androidKt.findAll(tree, new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(AnimationSearch.searchAny$lambda$0$0((Group) obj));
                }
            });
            Iterable $this$any$iv2 = this.supportedSearch;
            if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                Iterator it = $this$any$iv2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv2 = it.next();
                        Search search = (Search) element$iv2;
                        if (search.hasAnimations(listFindAll)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static final boolean searchAny$lambda$0$0(Group it) {
        return true;
    }

    public final void attachAllAnimations(Collection<? extends Group> slotTrees) {
        Collection<? extends Group> $this$forEach$iv = slotTrees;
        for (Object element$iv : $this$forEach$iv) {
            Group tree = (Group) element$iv;
            List<Group> listFindAll = PreviewUtils_androidKt.findAll(tree, new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(AnimationSearch.attachAllAnimations$lambda$0$0((Group) obj));
                }
            });
            for (Object element$iv2 : this.setToSearch) {
                Search it = (Search) element$iv2;
                it.addAnimations(listFindAll);
            }
            Iterable $this$map$iv = this.animatedVisibilitySearch.getAnimations();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                AnimatedVisibilitySearchInfo it2 = (AnimatedVisibilitySearchInfo) item$iv$iv;
                destination$iv$iv.add(it2.getTransition());
            }
            Set set = CollectionsKt.toSet((List) destination$iv$iv);
            Iterable $this$map$iv2 = this.animatedContentSearch.getAnimations();
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (Object item$iv$iv2 : $this$map$iv2) {
                AnimatedContentSearchInfo it3 = (AnimatedContentSearchInfo) item$iv$iv2;
                destination$iv$iv2.add(it3.getTransition());
                $this$forEach$iv = $this$forEach$iv;
            }
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            final Set transitionsToExclude = SetsKt.plus(set, (Iterable) CollectionsKt.toSet((List) destination$iv$iv2));
            CollectionsKt.removeAll(this.transitionSearch.getAnimations(), new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(AnimationSearch.attachAllAnimations$lambda$0$4(transitionsToExclude, (TransitionSearchInfo) obj));
                }
            });
            $this$forEach$iv = $this$forEach$iv2;
        }
        for (Object element$iv3 : this.setToTrack) {
            Search it4 = (Search) element$iv3;
            it4.track();
        }
    }

    public static final boolean attachAllAnimations$lambda$0$0(Group it) {
        return true;
    }

    public static final boolean attachAllAnimations$lambda$0$4(Set $transitionsToExclude, TransitionSearchInfo it) {
        return $transitionsToExclude.contains(it.getTransition());
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0005\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\fH&J\u0016\u0010\u0013\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u0006\u0010\u0014\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "T", "", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "hasAnimations", "", "groups", "", "Landroidx/compose/ui/tooling/data/Group;", "hasAnimation", "group", "animations", "", "getAnimations", "()Ljava/util/Set;", "addAnimations", "track", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Search<T> {
        public static final int $stable = 8;
        private final Set<T> animations = new LinkedHashSet();
        private final Function1<T, Unit> trackAnimation;

        public abstract boolean hasAnimation(Group group);

        /* JADX WARN: Multi-variable type inference failed */
        public Search(Function1<? super T, Unit> function1) {
            this.trackAnimation = function1;
        }

        public final boolean hasAnimations(Collection<? extends Group> groups) {
            Collection<? extends Group> $this$any$iv = groups;
            if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                Group it = (Group) element$iv;
                if (hasAnimation(it)) {
                    return true;
                }
            }
            return false;
        }

        public final Set<T> getAnimations() {
            return this.animations;
        }

        public void addAnimations(Collection<? extends Group> groups) {
        }

        public final void track() {
            Iterable $this$forEach$iv = CollectionsKt.reversed(this.animations);
            Function1<T, Unit> function1 = this.trackAnimation;
            Iterator<T> it = $this$forEach$iv.iterator();
            while (it.hasNext()) {
                function1.invoke(it.next());
            }
        }
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J0\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0013\"\b\b\u0001\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0002J+\u0010\u0012\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0001\u0010\u0001*\u00020\u0002*\u00020\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0002¢\u0006\u0002\u0010\u0014R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$RememberSearch;", "T", "", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "clazz", "Lkotlin/reflect/KClass;", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function1;)V", "addAnimations", "groups", "", "Landroidx/compose/ui/tooling/data/Group;", "hasAnimation", "", "group", "findRememberCallWithType", "", "(Landroidx/compose/ui/tooling/data/Group;Lkotlin/reflect/KClass;)Ljava/lang/Object;", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static class RememberSearch<T> extends Search<T> {
        public static final int $stable = 8;
        private final KClass<T> clazz;

        public RememberSearch(KClass<T> kClass, Function1<? super T, Unit> function1) {
            super(function1);
            this.clazz = kClass;
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groups) {
            Collection<? extends Group> $this$filter$iv = groups;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                Group it = (Group) element$iv$iv;
                if (it.getLocation() != null) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List groupsWithLocation = (List) destination$iv$iv;
            getAnimations().addAll(CollectionsKt.toSet(findRememberCallWithType(groupsWithLocation, this.clazz)));
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public boolean hasAnimation(Group group) {
            return (group.getLocation() == null || findRememberCallWithType(group, this.clazz) == null) ? false : true;
        }

        private final <T> List<T> findRememberCallWithType(Collection<? extends Group> collection, KClass<T> kClass) {
            Collection<? extends Group> $this$mapNotNull$iv = collection;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Group it = (Group) element$iv$iv$iv;
                T tFindRememberCallWithType = findRememberCallWithType(it, kClass);
                if (tFindRememberCallWithType != null) {
                    destination$iv$iv.add(tFindRememberCallWithType);
                }
            }
            return (List) destination$iv$iv;
        }

        private final <T> T findRememberCallWithType(Group group, KClass<T> kClass) {
            Object kotlinClass;
            Class<?> cls;
            Iterator<T> it = group.getData().iterator();
            while (true) {
                kotlinClass = null;
                if (!it.hasNext()) {
                    break;
                }
                T next = it.next();
                if (next != null && (cls = next.getClass()) != null) {
                    kotlinClass = JvmClassMappingKt.getKotlinClass(cls);
                }
                if (Intrinsics.areEqual(kotlinClass, kClass)) {
                    kotlinClass = next;
                    break;
                }
            }
            return (T) KClasses.safeCast(kClass, kotlinClass);
        }
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00020\u0001B#\u0012\u001a\u0010\u0003\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$TargetBasedSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$RememberSearch;", "Landroidx/compose/animation/core/TargetBasedAnimation;", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TargetBasedSearch extends RememberSearch<TargetBasedAnimation<?, ?>> {
        public static final int $stable = 8;

        public TargetBasedSearch(Function1<? super TargetBasedAnimation<?, ?>, Unit> function1) {
            super(Reflection.getOrCreateKotlinClass(TargetBasedAnimation.class), function1);
        }
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00020\u0001B#\u0012\u001a\u0010\u0003\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$DecaySearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$RememberSearch;", "Landroidx/compose/animation/core/DecayAnimation;", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DecaySearch extends RememberSearch<DecayAnimation<?, ?>> {
        public static final int $stable = 8;

        public DecaySearch(Function1<? super DecayAnimation<?, ?>, Unit> function1) {
            super(Reflection.getOrCreateKotlinClass(DecayAnimation.class), function1);
        }
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00122\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0002J \u0010\u0013\u001a\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0018\u00010\u00142\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$InfiniteTransitionSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/ui/tooling/animation/search/InfiniteTransitionSearchInfo;", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "hasAnimation", "", "group", "Landroidx/compose/ui/tooling/data/Group;", "addAnimations", "groups", "", "toAnimationGroup", "Landroidx/compose/ui/tooling/data/CallGroup;", "findAnimations", "", "findToolingOverride", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/runtime/State;", "", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InfiniteTransitionSearch extends Search<InfiniteTransitionSearchInfo> {
        public static final int $stable = 8;

        public InfiniteTransitionSearch(Function1<? super InfiniteTransitionSearchInfo, Unit> function1) {
            super(function1);
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public boolean hasAnimation(Group group) {
            Object element$iv$iv;
            if (toAnimationGroup(group) == null) {
                return false;
            }
            Collection<Object> data = group.getData();
            Iterable $this$flatMap$iv$iv = group.getChildren();
            Collection destination$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$flatMap$iv$iv) {
                Group it$iv = (Group) element$iv$iv$iv;
                Iterable list$iv$iv$iv = it$iv.getData();
                CollectionsKt.addAll(destination$iv$iv$iv, list$iv$iv$iv);
            }
            Iterable dataToSearch$iv = CollectionsKt.plus((Collection) data, destination$iv$iv$iv);
            Iterable $this$firstOrNull$iv$iv = dataToSearch$iv;
            Iterator it = $this$firstOrNull$iv$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    element$iv$iv = it.next();
                    if (element$iv$iv instanceof InfiniteTransition) {
                        break;
                    }
                } else {
                    element$iv$iv = null;
                    break;
                }
            }
            return (((InfiniteTransition) (element$iv$iv instanceof InfiniteTransition ? element$iv$iv : null)) == null || findToolingOverride(group) == null) ? false : true;
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groups) {
            getAnimations().addAll(findAnimations(groups));
        }

        private final CallGroup toAnimationGroup(Group group) {
            Group it = group.getLocation() != null && Intrinsics.areEqual(group.getName(), "rememberInfiniteTransition") ? group : null;
            if (it == null || !(it instanceof CallGroup)) {
                return null;
            }
            return (CallGroup) it;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final List<InfiniteTransitionSearchInfo> findAnimations(Collection<? extends Group> groups) {
            Object toolingState;
            Object obj;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : groups) {
                CallGroup animationGroup = toAnimationGroup((Group) element$iv$iv$iv);
                if (animationGroup != null) {
                    destination$iv$iv.add(animationGroup);
                }
            }
            Iterable $this$mapNotNull$iv = (List) destination$iv$iv;
            int $i$f$mapNotNull = 0;
            Collection destination$iv$iv2 = new ArrayList();
            Iterable list$iv$iv$iv = $this$mapNotNull$iv;
            for (Object element$iv$iv$iv2 : list$iv$iv$iv) {
                Group it = (CallGroup) element$iv$iv$iv2;
                Group $this$findData_u24default$iv = it;
                int $i$f$mapNotNull2 = $i$f$mapNotNull;
                Collection<Object> data = $this$findData_u24default$iv.getData();
                Iterable $this$flatMap$iv$iv = $this$findData_u24default$iv.getChildren();
                Iterable $this$mapNotNull$iv2 = $this$mapNotNull$iv;
                Collection destination$iv$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv3 : $this$flatMap$iv$iv) {
                    Group it$iv = (Group) element$iv$iv$iv3;
                    Iterable $this$mapNotNullTo$iv$iv = list$iv$iv$iv;
                    CollectionsKt.addAll(destination$iv$iv$iv, it$iv.getData());
                    list$iv$iv$iv = $this$mapNotNullTo$iv$iv;
                }
                Iterable $this$mapNotNullTo$iv$iv2 = list$iv$iv$iv;
                List dataToSearch$iv = CollectionsKt.plus((Collection) data, destination$iv$iv$iv);
                List $this$firstOrNull$iv$iv = dataToSearch$iv;
                Iterator it2 = $this$firstOrNull$iv$iv.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj = null;
                        break;
                    }
                    Object element$iv$iv = it2.next();
                    List dataToSearch$iv2 = dataToSearch$iv;
                    if (element$iv$iv instanceof InfiniteTransition) {
                        obj = element$iv$iv;
                        break;
                    }
                    dataToSearch$iv = dataToSearch$iv2;
                }
                if (!(obj instanceof InfiniteTransition)) {
                    obj = null;
                }
                InfiniteTransition infiniteTransition = (InfiniteTransition) obj;
                MutableState<State<Long>> mutableStateFindToolingOverride = findToolingOverride(it);
                if (infiniteTransition != null && mutableStateFindToolingOverride != null) {
                    Object value = mutableStateFindToolingOverride.getValue();
                    toolingState = value instanceof ToolingState ? (ToolingState) value : null;
                    if (toolingState == null) {
                        toolingState = new ToolingState(0L);
                    }
                    InfiniteTransitionSearchInfo $this$findAnimations_u24lambda_u241_u240 = new InfiniteTransitionSearchInfo(infiniteTransition, new ToolingOverride(mutableStateFindToolingOverride, toolingState));
                    $this$findAnimations_u24lambda_u241_u240.attach();
                    toolingState = $this$findAnimations_u24lambda_u241_u240;
                }
                if (toolingState != null) {
                    Object it$iv$iv = toolingState;
                    destination$iv$iv2.add(it$iv$iv);
                }
                $i$f$mapNotNull = $i$f$mapNotNull2;
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                list$iv$iv$iv = $this$mapNotNullTo$iv$iv2;
            }
            return (List) destination$iv$iv2;
        }

        private final MutableState<State<Long>> findToolingOverride(Group group) {
            Object element$iv$iv;
            Collection<Object> data = group.getData();
            Collection<Group> children = group.getChildren();
            Collection<Group> $this$flatMap$iv$iv = children;
            Collection destination$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$flatMap$iv$iv) {
                Group child$iv = (Group) element$iv$iv$iv;
                Iterable list$iv$iv$iv = child$iv.getChildren();
                CollectionsKt.addAll(destination$iv$iv$iv, list$iv$iv$iv);
            }
            Iterable $this$flatMap$iv$iv2 = CollectionsKt.plus((Collection) children, destination$iv$iv$iv);
            Collection destination$iv$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv2 : $this$flatMap$iv$iv2) {
                Group it$iv = (Group) element$iv$iv$iv2;
                Iterable list$iv$iv$iv2 = it$iv.getData();
                CollectionsKt.addAll(destination$iv$iv$iv2, list$iv$iv$iv2);
            }
            Iterable dataToSearch$iv = CollectionsKt.plus((Collection) data, destination$iv$iv$iv2);
            Iterable $this$firstOrNull$iv$iv = dataToSearch$iv;
            Iterator it = $this$firstOrNull$iv$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    element$iv$iv = null;
                    break;
                }
                element$iv$iv = it.next();
                if (element$iv$iv instanceof MutableState) {
                    break;
                }
            }
            return (MutableState) (element$iv$iv instanceof MutableState ? element$iv$iv : null);
        }
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00020\u0001B#\u0012\u001a\u0010\u0003\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0002J.\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u00020\u00140\u00020\u0012\"\u0004\b\u0000\u0010\u00132\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0002J&\u0010\u0015\u001a\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0013\u0018\u00010\u0017\u0018\u00010\u0016\"\u0004\b\u0000\u0010\u00132\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001e\u0010\u0018\u001a\n\u0012\u0004\u0012\u0002H\u0013\u0018\u00010\u0019\"\u0004\b\u0000\u0010\u00132\u0006\u0010\n\u001a\u00020\u0010H\u0002J$\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001b\"\u0004\b\u0000\u0010\u00132\u0006\u0010\n\u001a\u00020\u0010H\u0002¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimateXAsStateSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/ui/tooling/animation/search/AnimateXAsStateSearchInfo;", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "hasAnimation", "", "group", "Landroidx/compose/ui/tooling/data/Group;", "addAnimations", "groups", "", "toAnimationGroup", "Landroidx/compose/ui/tooling/data/CallGroup;", "findAnimations", "", "T", "Landroidx/compose/animation/core/AnimationVector;", "findToolingOverride", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/runtime/State;", "findAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "findAnimatable", "Landroidx/compose/animation/core/Animatable;", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnimateXAsStateSearch extends Search<AnimateXAsStateSearchInfo<?, ?>> {
        public static final int $stable = 8;

        public AnimateXAsStateSearch(Function1<? super AnimateXAsStateSearchInfo<?, ?>, Unit> function1) {
            super(function1);
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public boolean hasAnimation(Group group) {
            CallGroup it = toAnimationGroup(group);
            return (it == null || findAnimatable(it) == null || findAnimationSpec(it) == null || findToolingOverride(it) == null) ? false : true;
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groups) {
            getAnimations().addAll(findAnimations(groups));
        }

        private final CallGroup toAnimationGroup(Group group) {
            Group it = group.getLocation() != null && Intrinsics.areEqual(group.getName(), "animateValueAsState") ? group : null;
            if (it == null || !(it instanceof CallGroup)) {
                return null;
            }
            return (CallGroup) it;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final <T> List<AnimateXAsStateSearchInfo<T, AnimationVector>> findAnimations(Collection<? extends Group> groups) {
            Iterable $this$mapNotNull$iv;
            Iterable $this$mapNotNullTo$iv$iv;
            AnimateXAsStateSearch animateXAsStateSearch = this;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : groups) {
                CallGroup animationGroup = animateXAsStateSearch.toAnimationGroup((Group) element$iv$iv$iv);
                if (animationGroup != null) {
                    destination$iv$iv.add(animationGroup);
                }
            }
            Iterable $this$mapNotNull$iv2 = (List) destination$iv$iv;
            int $i$f$mapNotNull = 0;
            Collection destination$iv$iv2 = new ArrayList();
            Iterable $this$mapNotNullTo$iv$iv2 = $this$mapNotNull$iv2;
            for (Object element$iv$iv$iv2 : $this$mapNotNullTo$iv$iv2) {
                CallGroup it = (CallGroup) element$iv$iv$iv2;
                Animatable<T, AnimationVector> animatableFindAnimatable = animateXAsStateSearch.findAnimatable(it);
                AnimationSpec<T> animationSpecFindAnimationSpec = animateXAsStateSearch.findAnimationSpec(it);
                int $i$f$mapNotNull2 = $i$f$mapNotNull;
                MutableState<State<T>> mutableStateFindToolingOverride = animateXAsStateSearch.findToolingOverride(it);
                if (animatableFindAnimatable == null || animationSpecFindAnimationSpec == null || mutableStateFindToolingOverride == null) {
                    $this$mapNotNull$iv = $this$mapNotNull$iv2;
                    $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                } else {
                    Object value = mutableStateFindToolingOverride.getValue();
                    $this$mapNotNull$iv = $this$mapNotNull$iv2;
                    toolingState = value instanceof ToolingState ? (ToolingState) value : null;
                    if (toolingState == null) {
                        toolingState = new ToolingState(animatableFindAnimatable.getValue());
                    }
                    $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                    AnimateXAsStateSearchInfo $this$findAnimations_u24lambda_u241_u240 = new AnimateXAsStateSearchInfo(animatableFindAnimatable, animationSpecFindAnimationSpec, new ToolingOverride(mutableStateFindToolingOverride, toolingState));
                    $this$findAnimations_u24lambda_u241_u240.attach();
                    toolingState = $this$findAnimations_u24lambda_u241_u240;
                }
                if (toolingState != null) {
                    Object it$iv$iv = toolingState;
                    destination$iv$iv2.add(it$iv$iv);
                }
                animateXAsStateSearch = this;
                $i$f$mapNotNull = $i$f$mapNotNull2;
                $this$mapNotNull$iv2 = $this$mapNotNull$iv;
                $this$mapNotNullTo$iv$iv2 = $this$mapNotNullTo$iv$iv;
            }
            return (List) destination$iv$iv2;
        }

        private final <T> MutableState<State<T>> findToolingOverride(Group group) {
            Object element$iv$iv;
            Object it$iv;
            List rememberCalls$iv$iv;
            Object obj;
            Object obj2;
            Group $this$findRememberedData$iv = group;
            int $i$f$findRememberedData = 0;
            Iterable $this$firstOrNull$iv$iv = $this$findRememberedData$iv.getData();
            Iterator<T> it = $this$firstOrNull$iv$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    element$iv$iv = null;
                    break;
                }
                element$iv$iv = it.next();
                if (element$iv$iv instanceof MutableState) {
                    break;
                }
            }
            if (!(element$iv$iv instanceof MutableState)) {
                element$iv$iv = null;
            }
            Object thisData$iv = (MutableState) element$iv$iv;
            if (thisData$iv == null || (it$iv = CollectionsKt.listOf(thisData$iv)) == null) {
                it$iv = CollectionsKt.emptyList();
            }
            List list = (Collection) it$iv;
            Collection $this$findRememberedData$iv$iv = $this$findRememberedData$iv.getChildren();
            Collection $this$mapNotNull$iv$iv$iv = $this$findRememberedData$iv$iv;
            Collection destination$iv$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv$iv$iv : $this$mapNotNull$iv$iv$iv) {
                Group it$iv$iv = (Group) element$iv$iv$iv$iv$iv;
                Iterable $this$firstOrNull$iv$iv$iv = it$iv$iv.getData();
                Iterator<T> it2 = $this$firstOrNull$iv$iv$iv.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    Object element$iv$iv$iv = it2.next();
                    if (element$iv$iv$iv instanceof MutableState) {
                        obj2 = element$iv$iv$iv;
                        break;
                    }
                }
                Group $this$findRememberedData$iv2 = $this$findRememberedData$iv;
                if (!(obj2 instanceof MutableState)) {
                    obj2 = null;
                }
                MutableState mutableState = (MutableState) obj2;
                if (mutableState != null) {
                    destination$iv$iv$iv$iv.add(mutableState);
                }
                $this$findRememberedData$iv = $this$findRememberedData$iv2;
            }
            Object it$iv$iv$iv$iv = (List) destination$iv$iv$iv$iv;
            Collection $this$mapNotNull$iv$iv$iv2 = $this$findRememberedData$iv$iv;
            Collection destination$iv$iv$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv$iv$iv2 : $this$mapNotNull$iv$iv$iv2) {
                Object selfData$iv$iv = it$iv$iv$iv$iv;
                Group it$iv$iv2 = (Group) element$iv$iv$iv$iv$iv2;
                int $i$f$findRememberedData2 = $i$f$findRememberedData;
                Group it$iv$iv3 = PreviewUtils_androidKt.firstOrNull(it$iv$iv2, AnimationSearch_androidKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                if (it$iv$iv3 != null) {
                    destination$iv$iv$iv$iv2.add(it$iv$iv3);
                }
                it$iv$iv$iv$iv = selfData$iv$iv;
                $i$f$findRememberedData = $i$f$findRememberedData2;
            }
            Object selfData$iv$iv2 = it$iv$iv$iv$iv;
            List rememberCalls$iv$iv2 = (List) destination$iv$iv$iv$iv2;
            ArrayList arrayList = (Collection) selfData$iv$iv2;
            List $this$mapNotNull$iv$iv$iv3 = rememberCalls$iv$iv2;
            Collection destination$iv$iv$iv$iv3 = new ArrayList();
            for (Object element$iv$iv$iv$iv$iv3 : $this$mapNotNull$iv$iv$iv3) {
                Group it$iv$iv4 = (Group) element$iv$iv$iv$iv$iv3;
                Iterable $this$firstOrNull$iv$iv$iv2 = it$iv$iv4.getData();
                Iterator<T> it3 = $this$firstOrNull$iv$iv$iv2.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        rememberCalls$iv$iv = rememberCalls$iv$iv2;
                        obj = null;
                        break;
                    }
                    Object element$iv$iv$iv2 = it3.next();
                    rememberCalls$iv$iv = rememberCalls$iv$iv2;
                    if (element$iv$iv$iv2 instanceof MutableState) {
                        obj = element$iv$iv$iv2;
                        break;
                    }
                    rememberCalls$iv$iv2 = rememberCalls$iv$iv;
                }
                Collection $this$firstOrNull$iv$iv$iv3 = $this$findRememberedData$iv$iv;
                if (!(obj instanceof MutableState)) {
                    obj = null;
                }
                MutableState mutableState2 = (MutableState) obj;
                if (mutableState2 != null) {
                    destination$iv$iv$iv$iv3.add(mutableState2);
                }
                $this$findRememberedData$iv$iv = $this$firstOrNull$iv$iv$iv3;
                rememberCalls$iv$iv2 = rememberCalls$iv$iv;
            }
            return (MutableState) CollectionsKt.firstOrNull(CollectionsKt.plus((Collection) list, (Iterable) CollectionsKt.plus((Collection) arrayList, destination$iv$iv$iv$iv3)));
        }

        private final <T> AnimationSpec<T> findAnimationSpec(CallGroup group) {
            Iterable $this$filter$iv = group.getChildren();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                Group it = (Group) element$iv$iv;
                if (Intrinsics.areEqual(it.getName(), "rememberUpdatedState")) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable rememberStates = (List) destination$iv$iv;
            ArrayList arrayList = (Collection) rememberStates;
            Iterable $this$flatMap$iv = rememberStates;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv2 : $this$flatMap$iv) {
                Group it2 = (Group) element$iv$iv2;
                Iterable list$iv$iv = it2.getChildren();
                CollectionsKt.addAll(destination$iv$iv2, list$iv$iv);
            }
            Iterable $this$flatMap$iv2 = CollectionsKt.plus((Collection) arrayList, destination$iv$iv2);
            Collection destination$iv$iv3 = new ArrayList();
            for (Object element$iv$iv3 : $this$flatMap$iv2) {
                Group it3 = (Group) element$iv$iv3;
                Iterable list$iv$iv2 = it3.getData();
                CollectionsKt.addAll(destination$iv$iv3, list$iv$iv2);
            }
            Iterable $this$filterIsInstance$iv = (List) destination$iv$iv3;
            Collection destination$iv$iv4 = new ArrayList();
            for (T t : $this$filterIsInstance$iv) {
                if (t instanceof State) {
                    destination$iv$iv4.add(t);
                }
            }
            Iterable $this$map$iv = (List) destination$iv$iv4;
            Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                State it4 = (State) item$iv$iv;
                destination$iv$iv5.add(it4.getValue());
            }
            Iterable $this$filterIsInstance$iv2 = (List) destination$iv$iv5;
            Collection destination$iv$iv6 = new ArrayList();
            for (T t2 : $this$filterIsInstance$iv2) {
                if (t2 instanceof AnimationSpec) {
                    destination$iv$iv6.add(t2);
                }
            }
            return (AnimationSpec) CollectionsKt.firstOrNull((List) destination$iv$iv6);
        }

        private final <T> Animatable<T, AnimationVector> findAnimatable(CallGroup group) {
            Object element$iv$iv;
            Object it$iv;
            List rememberCalls$iv$iv;
            Object obj;
            Object obj2;
            CallGroup $this$findRememberedData$iv = group;
            int $i$f$findRememberedData = 0;
            Iterable $this$firstOrNull$iv$iv = $this$findRememberedData$iv.getData();
            Iterator<T> it = $this$firstOrNull$iv$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    element$iv$iv = null;
                    break;
                }
                element$iv$iv = it.next();
                if (element$iv$iv instanceof Animatable) {
                    break;
                }
            }
            if (!(element$iv$iv instanceof Animatable)) {
                element$iv$iv = null;
            }
            Object thisData$iv = (Animatable) element$iv$iv;
            if (thisData$iv == null || (it$iv = CollectionsKt.listOf(thisData$iv)) == null) {
                it$iv = CollectionsKt.emptyList();
            }
            List list = (Collection) it$iv;
            Collection $this$findRememberedData$iv$iv = $this$findRememberedData$iv.getChildren();
            Collection $this$mapNotNull$iv$iv$iv = $this$findRememberedData$iv$iv;
            Collection destination$iv$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv$iv$iv : $this$mapNotNull$iv$iv$iv) {
                Group it$iv$iv = (Group) element$iv$iv$iv$iv$iv;
                Iterable $this$firstOrNull$iv$iv$iv = it$iv$iv.getData();
                Iterator<T> it2 = $this$firstOrNull$iv$iv$iv.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    Object element$iv$iv$iv = it2.next();
                    if (element$iv$iv$iv instanceof Animatable) {
                        obj2 = element$iv$iv$iv;
                        break;
                    }
                }
                Group $this$findRememberedData$iv2 = $this$findRememberedData$iv;
                if (!(obj2 instanceof Animatable)) {
                    obj2 = null;
                }
                Animatable animatable = (Animatable) obj2;
                if (animatable != null) {
                    destination$iv$iv$iv$iv.add(animatable);
                }
                $this$findRememberedData$iv = $this$findRememberedData$iv2;
            }
            Object it$iv$iv$iv$iv = (List) destination$iv$iv$iv$iv;
            Collection $this$mapNotNull$iv$iv$iv2 = $this$findRememberedData$iv$iv;
            Collection destination$iv$iv$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv$iv$iv2 : $this$mapNotNull$iv$iv$iv2) {
                Object selfData$iv$iv = it$iv$iv$iv$iv;
                Group it$iv$iv2 = (Group) element$iv$iv$iv$iv$iv2;
                int $i$f$findRememberedData2 = $i$f$findRememberedData;
                Group it$iv$iv3 = PreviewUtils_androidKt.firstOrNull(it$iv$iv2, AnimationSearch_androidKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                if (it$iv$iv3 != null) {
                    destination$iv$iv$iv$iv2.add(it$iv$iv3);
                }
                it$iv$iv$iv$iv = selfData$iv$iv;
                $i$f$findRememberedData = $i$f$findRememberedData2;
            }
            Object selfData$iv$iv2 = it$iv$iv$iv$iv;
            List rememberCalls$iv$iv2 = (List) destination$iv$iv$iv$iv2;
            ArrayList arrayList = (Collection) selfData$iv$iv2;
            List $this$mapNotNull$iv$iv$iv3 = rememberCalls$iv$iv2;
            Collection destination$iv$iv$iv$iv3 = new ArrayList();
            for (Object element$iv$iv$iv$iv$iv3 : $this$mapNotNull$iv$iv$iv3) {
                Group it$iv$iv4 = (Group) element$iv$iv$iv$iv$iv3;
                Iterable $this$firstOrNull$iv$iv$iv2 = it$iv$iv4.getData();
                Iterator<T> it3 = $this$firstOrNull$iv$iv$iv2.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        rememberCalls$iv$iv = rememberCalls$iv$iv2;
                        obj = null;
                        break;
                    }
                    Object element$iv$iv$iv2 = it3.next();
                    rememberCalls$iv$iv = rememberCalls$iv$iv2;
                    if (element$iv$iv$iv2 instanceof Animatable) {
                        obj = element$iv$iv$iv2;
                        break;
                    }
                    rememberCalls$iv$iv2 = rememberCalls$iv$iv;
                }
                Collection $this$firstOrNull$iv$iv$iv3 = $this$findRememberedData$iv$iv;
                if (!(obj instanceof Animatable)) {
                    obj = null;
                }
                Animatable animatable2 = (Animatable) obj;
                if (animatable2 != null) {
                    destination$iv$iv$iv$iv3.add(animatable2);
                }
                $this$findRememberedData$iv$iv = $this$firstOrNull$iv$iv$iv3;
                rememberCalls$iv$iv2 = rememberCalls$iv$iv;
            }
            return (Animatable) CollectionsKt.firstOrNull(CollectionsKt.plus((Collection) list, (Iterable) CollectionsKt.plus((Collection) arrayList, destination$iv$iv$iv$iv3)));
        }
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0016¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimateContentSizeSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "hasAnimation", "", "group", "Landroidx/compose/ui/tooling/data/Group;", "addAnimations", "groups", "", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnimateContentSizeSearch extends Search<Object> {
        public static final int $stable = 8;

        public AnimateContentSizeSearch(Function1<Object, Unit> function1) {
            super(function1);
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public boolean hasAnimation(Group group) {
            boolean z;
            if (group.getModifierInfo().isEmpty()) {
                return false;
            }
            Iterable $this$any$iv = group.getModifierInfo();
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        ModifierInfo it2 = (ModifierInfo) element$iv;
                        if (it2.getModifier().any(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$AnimateContentSizeSearch$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Boolean.valueOf(AnimationSearch.AnimateContentSizeSearch.hasAnimation$lambda$0$0((Modifier.Element) obj));
                            }
                        })) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            } else {
                z = false;
            }
            return z;
        }

        public static final boolean hasAnimation$lambda$0$0(Modifier.Element mod) {
            return Intrinsics.areEqual(mod.getClass().getName(), "androidx.compose.animation.SizeAnimationModifierElement");
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groups) {
            Collection<? extends Group> $this$filter$iv = groups;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                Group it = (Group) element$iv$iv;
                if (!it.getModifierInfo().isEmpty()) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable $this$forEach$iv = (List) destination$iv$iv;
            for (Object element$iv : $this$forEach$iv) {
                Group group = (Group) element$iv;
                Iterable $this$forEach$iv2 = group.getModifierInfo();
                for (Object element$iv2 : $this$forEach$iv2) {
                    ModifierInfo it2 = (ModifierInfo) element$iv2;
                    it2.getModifier().any(new Function1() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$AnimateContentSizeSearch$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(AnimationSearch.AnimateContentSizeSearch.addAnimations$lambda$1$0$0(this.f$0, (Modifier.Element) obj));
                        }
                    });
                }
            }
        }

        public static final boolean addAnimations$lambda$1$0$0(AnimateContentSizeSearch this$0, Modifier.Element mod) {
            if (Intrinsics.areEqual(mod.getClass().getName(), "androidx.compose.animation.SizeAnimationModifierElement")) {
                this$0.getAnimations().add(mod);
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$TransitionSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/ui/tooling/animation/search/TransitionSearchInfo;", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "hasAnimation", "", "group", "Landroidx/compose/ui/tooling/data/Group;", "addAnimations", "groups", "", "toAnimationGroup", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TransitionSearch extends Search<TransitionSearchInfo> {
        public static final int $stable = 8;

        public TransitionSearch(Function1<? super TransitionSearchInfo, Unit> function1) {
            super(function1);
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public boolean hasAnimation(Group group) {
            return toAnimationGroup(group) != null;
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groups) {
            List rememberCalls$iv;
            Object obj;
            Object obj2;
            Set<TransitionSearchInfo> animations = getAnimations();
            Collection<? extends Group> $this$mapNotNull$iv = groups;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Group it = (Group) element$iv$iv$iv;
                Group it2 = toAnimationGroup(it);
                if (it2 != null) {
                    destination$iv$iv.add(it2);
                }
            }
            Collection $this$findRememberedData$iv = (List) destination$iv$iv;
            int $i$f$findRememberedData = 0;
            Collection $this$mapNotNull$iv$iv = $this$findRememberedData$iv;
            Collection destination$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv$iv : $this$mapNotNull$iv$iv) {
                Group it$iv = (Group) element$iv$iv$iv$iv;
                Iterable $this$firstOrNull$iv$iv = it$iv.getData();
                Iterator it3 = $this$firstOrNull$iv$iv.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    Object element$iv$iv = it3.next();
                    if (element$iv$iv instanceof Transition) {
                        obj2 = element$iv$iv;
                        break;
                    }
                }
                int $i$f$findRememberedData2 = $i$f$findRememberedData;
                if (!(obj2 instanceof Transition)) {
                    obj2 = null;
                }
                Transition transition = (Transition) obj2;
                if (transition != null) {
                    destination$iv$iv$iv.add(transition);
                }
                $i$f$findRememberedData = $i$f$findRememberedData2;
            }
            Object it$iv$iv$iv = (List) destination$iv$iv$iv;
            Collection $this$mapNotNull$iv$iv2 = $this$findRememberedData$iv;
            Collection destination$iv$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv$iv2 : $this$mapNotNull$iv$iv2) {
                Group it$iv2 = (Group) element$iv$iv$iv$iv2;
                Object selfData$iv = it$iv$iv$iv;
                Group groupFirstOrNull = PreviewUtils_androidKt.firstOrNull(it$iv2, AnimationSearch_androidKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                if (groupFirstOrNull != null) {
                    destination$iv$iv$iv2.add(groupFirstOrNull);
                }
                it$iv$iv$iv = selfData$iv;
            }
            Object selfData$iv2 = it$iv$iv$iv;
            List rememberCalls$iv2 = (List) destination$iv$iv$iv2;
            ArrayList arrayList = (Collection) selfData$iv2;
            List $this$mapNotNull$iv$iv3 = rememberCalls$iv2;
            Collection destination$iv$iv$iv3 = new ArrayList();
            for (Object element$iv$iv$iv$iv3 : $this$mapNotNull$iv$iv3) {
                Group it$iv3 = (Group) element$iv$iv$iv$iv3;
                Iterable $this$firstOrNull$iv$iv2 = it$iv3.getData();
                Iterator it4 = $this$firstOrNull$iv$iv2.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        rememberCalls$iv = rememberCalls$iv2;
                        obj = null;
                        break;
                    }
                    Object element$iv$iv2 = it4.next();
                    rememberCalls$iv = rememberCalls$iv2;
                    if (element$iv$iv2 instanceof Transition) {
                        obj = element$iv$iv2;
                        break;
                    }
                    rememberCalls$iv2 = rememberCalls$iv;
                }
                Collection $this$firstOrNull$iv$iv3 = $this$findRememberedData$iv;
                if (!(obj instanceof Transition)) {
                    obj = null;
                }
                Transition transition2 = (Transition) obj;
                if (transition2 != null) {
                    destination$iv$iv$iv3.add(transition2);
                }
                $this$findRememberedData$iv = $this$firstOrNull$iv$iv3;
                rememberCalls$iv2 = rememberCalls$iv;
            }
            Iterable $this$map$iv = CollectionsKt.plus((Collection) arrayList, destination$iv$iv$iv3);
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Transition it5 = (Transition) item$iv$iv;
                destination$iv$iv2.add(new TransitionSearchInfo(it5));
            }
            animations.addAll((List) destination$iv$iv2);
        }

        private final Group toAnimationGroup(Group group) {
            if (group.getLocation() != null && Intrinsics.areEqual(group.getName(), "updateTransition")) {
                return group;
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimatedVisibilitySearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/ui/tooling/animation/search/AnimatedVisibilitySearchInfo;", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "hasAnimation", "", "group", "Landroidx/compose/ui/tooling/data/Group;", "addAnimations", "groups", "", "toAnimationGroup", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnimatedVisibilitySearch extends Search<AnimatedVisibilitySearchInfo> {
        public static final int $stable = 8;

        public AnimatedVisibilitySearch(Function1<? super AnimatedVisibilitySearchInfo, Unit> function1) {
            super(function1);
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public boolean hasAnimation(Group group) {
            return toAnimationGroup(group) != null;
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groups) {
            List rememberCalls$iv;
            Object obj;
            Object obj2;
            Set<AnimatedVisibilitySearchInfo> animations = getAnimations();
            Collection<? extends Group> $this$mapNotNull$iv = groups;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Group it = (Group) element$iv$iv$iv;
                Group it2 = toAnimationGroup(it);
                if (it2 != null) {
                    destination$iv$iv.add(it2);
                }
            }
            Collection $this$findRememberedData$iv = (List) destination$iv$iv;
            int $i$f$findRememberedData = 0;
            Collection $this$mapNotNull$iv$iv = $this$findRememberedData$iv;
            Collection destination$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv$iv : $this$mapNotNull$iv$iv) {
                Group it$iv = (Group) element$iv$iv$iv$iv;
                Iterable $this$firstOrNull$iv$iv = it$iv.getData();
                Iterator it3 = $this$firstOrNull$iv$iv.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    Object element$iv$iv = it3.next();
                    if (element$iv$iv instanceof Transition) {
                        obj2 = element$iv$iv;
                        break;
                    }
                }
                int $i$f$findRememberedData2 = $i$f$findRememberedData;
                if (!(obj2 instanceof Transition)) {
                    obj2 = null;
                }
                Transition transition = (Transition) obj2;
                if (transition != null) {
                    destination$iv$iv$iv.add(transition);
                }
                $i$f$findRememberedData = $i$f$findRememberedData2;
            }
            Object it$iv$iv$iv = (List) destination$iv$iv$iv;
            Collection $this$mapNotNull$iv$iv2 = $this$findRememberedData$iv;
            Collection destination$iv$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv$iv2 : $this$mapNotNull$iv$iv2) {
                Group it$iv2 = (Group) element$iv$iv$iv$iv2;
                Object selfData$iv = it$iv$iv$iv;
                Group groupFirstOrNull = PreviewUtils_androidKt.firstOrNull(it$iv2, AnimationSearch_androidKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                if (groupFirstOrNull != null) {
                    destination$iv$iv$iv2.add(groupFirstOrNull);
                }
                it$iv$iv$iv = selfData$iv;
            }
            Object selfData$iv2 = it$iv$iv$iv;
            List rememberCalls$iv2 = (List) destination$iv$iv$iv2;
            ArrayList arrayList = (Collection) selfData$iv2;
            List $this$mapNotNull$iv$iv3 = rememberCalls$iv2;
            Collection destination$iv$iv$iv3 = new ArrayList();
            for (Object element$iv$iv$iv$iv3 : $this$mapNotNull$iv$iv3) {
                Group it$iv3 = (Group) element$iv$iv$iv$iv3;
                Iterable $this$firstOrNull$iv$iv2 = it$iv3.getData();
                Iterator it4 = $this$firstOrNull$iv$iv2.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        rememberCalls$iv = rememberCalls$iv2;
                        obj = null;
                        break;
                    }
                    Object element$iv$iv2 = it4.next();
                    rememberCalls$iv = rememberCalls$iv2;
                    if (element$iv$iv2 instanceof Transition) {
                        obj = element$iv$iv2;
                        break;
                    }
                    rememberCalls$iv2 = rememberCalls$iv;
                }
                Collection $this$firstOrNull$iv$iv3 = $this$findRememberedData$iv;
                if (!(obj instanceof Transition)) {
                    obj = null;
                }
                Transition transition2 = (Transition) obj;
                if (transition2 != null) {
                    destination$iv$iv$iv3.add(transition2);
                }
                $this$findRememberedData$iv = $this$firstOrNull$iv$iv3;
                rememberCalls$iv2 = rememberCalls$iv;
            }
            Iterable $this$map$iv = CollectionsKt.plus((Collection) arrayList, destination$iv$iv$iv3);
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Transition it5 = (Transition) item$iv$iv;
                destination$iv$iv2.add(new AnimatedVisibilitySearchInfo(it5));
            }
            animations.addAll((List) destination$iv$iv2);
        }

        private final Group toAnimationGroup(Group group) {
            Object obj = null;
            Group it = group.getLocation() != null && Intrinsics.areEqual(group.getName(), "AnimatedVisibility") ? group : null;
            if (it == null) {
                return null;
            }
            Iterable $this$firstOrNull$iv = it.getChildren();
            Iterator it2 = $this$firstOrNull$iv.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object element$iv = it2.next();
                Group updateTransitionCall = (Group) element$iv;
                if (Intrinsics.areEqual(updateTransitionCall.getName(), "updateTransition")) {
                    obj = element$iv;
                    break;
                }
            }
            return (Group) obj;
        }
    }

    /* JADX INFO: compiled from: AnimationSearch.android.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimatedContentSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/ui/tooling/animation/search/AnimatedContentSearchInfo;", "trackAnimation", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "hasAnimation", "", "group", "Landroidx/compose/ui/tooling/data/Group;", "addAnimations", "groups", "", "toAnimationGroup", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnimatedContentSearch extends Search<AnimatedContentSearchInfo> {
        public static final int $stable = 8;

        public AnimatedContentSearch(Function1<? super AnimatedContentSearchInfo, Unit> function1) {
            super(function1);
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public boolean hasAnimation(Group group) {
            return toAnimationGroup(group) != null;
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groups) {
            List rememberCalls$iv;
            Object obj;
            Object obj2;
            Set<AnimatedContentSearchInfo> animations = getAnimations();
            Collection<? extends Group> $this$mapNotNull$iv = groups;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Group it = (Group) element$iv$iv$iv;
                Group it2 = toAnimationGroup(it);
                if (it2 != null) {
                    destination$iv$iv.add(it2);
                }
            }
            Collection $this$findRememberedData$iv = (List) destination$iv$iv;
            int $i$f$findRememberedData = 0;
            Collection $this$mapNotNull$iv$iv = $this$findRememberedData$iv;
            Collection destination$iv$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv$iv : $this$mapNotNull$iv$iv) {
                Group it$iv = (Group) element$iv$iv$iv$iv;
                Iterable $this$firstOrNull$iv$iv = it$iv.getData();
                Iterator it3 = $this$firstOrNull$iv$iv.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    Object element$iv$iv = it3.next();
                    if (element$iv$iv instanceof Transition) {
                        obj2 = element$iv$iv;
                        break;
                    }
                }
                int $i$f$findRememberedData2 = $i$f$findRememberedData;
                if (!(obj2 instanceof Transition)) {
                    obj2 = null;
                }
                Transition transition = (Transition) obj2;
                if (transition != null) {
                    destination$iv$iv$iv.add(transition);
                }
                $i$f$findRememberedData = $i$f$findRememberedData2;
            }
            Object it$iv$iv$iv = (List) destination$iv$iv$iv;
            Collection $this$mapNotNull$iv$iv2 = $this$findRememberedData$iv;
            Collection destination$iv$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv$iv2 : $this$mapNotNull$iv$iv2) {
                Group it$iv2 = (Group) element$iv$iv$iv$iv2;
                Object selfData$iv = it$iv$iv$iv;
                Group groupFirstOrNull = PreviewUtils_androidKt.firstOrNull(it$iv2, AnimationSearch_androidKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                if (groupFirstOrNull != null) {
                    destination$iv$iv$iv2.add(groupFirstOrNull);
                }
                it$iv$iv$iv = selfData$iv;
            }
            Object selfData$iv2 = it$iv$iv$iv;
            List rememberCalls$iv2 = (List) destination$iv$iv$iv2;
            ArrayList arrayList = (Collection) selfData$iv2;
            List $this$mapNotNull$iv$iv3 = rememberCalls$iv2;
            Collection destination$iv$iv$iv3 = new ArrayList();
            for (Object element$iv$iv$iv$iv3 : $this$mapNotNull$iv$iv3) {
                Group it$iv3 = (Group) element$iv$iv$iv$iv3;
                Iterable $this$firstOrNull$iv$iv2 = it$iv3.getData();
                Iterator it4 = $this$firstOrNull$iv$iv2.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        rememberCalls$iv = rememberCalls$iv2;
                        obj = null;
                        break;
                    }
                    Object element$iv$iv2 = it4.next();
                    rememberCalls$iv = rememberCalls$iv2;
                    if (element$iv$iv2 instanceof Transition) {
                        obj = element$iv$iv2;
                        break;
                    }
                    rememberCalls$iv2 = rememberCalls$iv;
                }
                Collection $this$firstOrNull$iv$iv3 = $this$findRememberedData$iv;
                if (!(obj instanceof Transition)) {
                    obj = null;
                }
                Transition transition2 = (Transition) obj;
                if (transition2 != null) {
                    destination$iv$iv$iv3.add(transition2);
                }
                $this$findRememberedData$iv = $this$firstOrNull$iv$iv3;
                rememberCalls$iv2 = rememberCalls$iv;
            }
            Iterable $this$map$iv = CollectionsKt.plus((Collection) arrayList, destination$iv$iv$iv3);
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Transition it5 = (Transition) item$iv$iv;
                destination$iv$iv2.add(new AnimatedContentSearchInfo(it5));
            }
            animations.addAll((List) destination$iv$iv2);
        }

        private final Group toAnimationGroup(Group group) {
            Object obj = null;
            Group it = group.getLocation() != null && Intrinsics.areEqual(group.getName(), "AnimatedContent") ? group : null;
            if (it == null) {
                return null;
            }
            Iterable $this$firstOrNull$iv = it.getChildren();
            Iterator it2 = $this$firstOrNull$iv.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object element$iv = it2.next();
                Group updateTransitionCall = (Group) element$iv;
                if (Intrinsics.areEqual(updateTransitionCall.getName(), "updateTransition")) {
                    obj = element$iv;
                    break;
                }
            }
            return (Group) obj;
        }
    }
}
