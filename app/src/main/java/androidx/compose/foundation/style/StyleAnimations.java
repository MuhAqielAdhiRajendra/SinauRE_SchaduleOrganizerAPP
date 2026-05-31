package androidx.compose.foundation.style;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.ui.unit.Density;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: StyleAnimations.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001:\u00013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\b\u0018\u00010\fR\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0002J2\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ&\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u000fJ&\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u000fJ\u0006\u0010%\u001a\u00020\u0015J\u001e\u0010&\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010'\u001a\u00020\u0011J\b\u0010(\u001a\u00020\u0015H\u0002J0\u0010)\u001a\u00020\u00152%\u0010*\u001a!\u0012\u0017\u0012\u00150\fR\u00020\u0000¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00150+H\u0082\bJ\u0017\u00100\u001a\u00020\u00152\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001502H\u0082\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0018\u00010\fR\u00020\u00000\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Landroidx/compose/foundation/style/StyleAnimations;", "", "node", "Landroidx/compose/foundation/style/StyleOuterNode;", "<init>", "(Landroidx/compose/foundation/style/StyleOuterNode;)V", "getNode", "()Landroidx/compose/foundation/style/StyleOuterNode;", "currentStyle", "Landroidx/compose/foundation/style/ResolvedStyle;", "values", "", "Landroidx/compose/foundation/style/StyleAnimations$Entry;", "[Landroidx/compose/foundation/style/StyleAnimations$Entry;", "size", "", "isNotEmpty", "", "find", "key", "ensureCapacity", "", "requiredCapacity", "record", "style", "Landroidx/compose/foundation/style/Style;", "toSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "fromSpec", "withAnimations", "density", "Landroidx/compose/ui/unit/Density;", "staticStyle", "forChanges", "applyAnimationsTo", TypedValues.AttributesType.S_TARGET, "preResolve", "postResolve", "triggerAnimations", "cleanupAnimations", "forEach", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "value", "inGuard", "recursionGuard", "block", "Lkotlin/Function0;", "Entry", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class StyleAnimations {
    public static final int $stable = 8;
    private boolean inGuard;
    private final StyleOuterNode node;
    private int size;
    private final ResolvedStyle currentStyle = new ResolvedStyle();
    private Entry[] values = new Entry[2];

    public StyleAnimations(StyleOuterNode node) {
        this.node = node;
    }

    public final StyleOuterNode getNode() {
        return this.node;
    }

    /* JADX INFO: compiled from: StyleAnimations.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u000e\u0010/\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u000e\u00100\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u000e\u00101\u001a\u00020,2\u0006\u0010-\u001a\u00020.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001d\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\r\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u00062"}, d2 = {"Landroidx/compose/foundation/style/StyleAnimations$Entry;", "", "key", "", "style", "Landroidx/compose/foundation/style/Style;", "toSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "fromSpec", "<init>", "(Landroidx/compose/foundation/style/StyleAnimations;ILandroidx/compose/foundation/style/Style;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/AnimationSpec;)V", "getKey", "()I", "getStyle", "()Landroidx/compose/foundation/style/Style;", "setStyle", "(Landroidx/compose/foundation/style/Style;)V", "getToSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "setToSpec", "(Landroidx/compose/animation/core/AnimationSpec;)V", "getFromSpec", "setFromSpec", "anim", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "getAnim", "()Landroidx/compose/animation/core/Animatable;", "styleScope", "Landroidx/compose/foundation/style/ResolvedStyle;", "getStyleScope", "()Landroidx/compose/foundation/style/ResolvedStyle;", "state", "getState", "setState", "(I)V", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "animateIn", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "snapIn", "animateOut", "snapOut", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class Entry {
        private AnimationSpec<Float> fromSpec;
        private Job job;
        private final int key;
        private Style style;
        private AnimationSpec<Float> toSpec;
        private final Animatable<Float, AnimationVector1D> anim = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
        private final ResolvedStyle styleScope = new ResolvedStyle();
        private int state = 3;

        public Entry(int key, Style style, AnimationSpec<Float> animationSpec, AnimationSpec<Float> animationSpec2) {
            this.key = key;
            this.style = style;
            this.toSpec = animationSpec;
            this.fromSpec = animationSpec2;
        }

        public final int getKey() {
            return this.key;
        }

        public final Style getStyle() {
            return this.style;
        }

        public final void setStyle(Style style) {
            this.style = style;
        }

        public final AnimationSpec<Float> getToSpec() {
            return this.toSpec;
        }

        public final void setToSpec(AnimationSpec<Float> animationSpec) {
            this.toSpec = animationSpec;
        }

        public final AnimationSpec<Float> getFromSpec() {
            return this.fromSpec;
        }

        public final void setFromSpec(AnimationSpec<Float> animationSpec) {
            this.fromSpec = animationSpec;
        }

        public final Animatable<Float, AnimationVector1D> getAnim() {
            return this.anim;
        }

        public final ResolvedStyle getStyleScope() {
            return this.styleScope;
        }

        public final int getState() {
            return this.state;
        }

        public final void setState(int i) {
            this.state = i;
        }

        public final Job getJob() {
            return this.job;
        }

        public final void setJob(Job job) {
            this.job = job;
        }

        public final void animateIn(CoroutineScope coroutineScope) {
            Job job = this.job;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.job = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new StyleAnimations$Entry$animateIn$1(this, null), 3, null);
        }

        public final void snapIn(CoroutineScope coroutineScope) {
            Job job = this.job;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.job = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new StyleAnimations$Entry$snapIn$1(this, null), 3, null);
        }

        public final void animateOut(CoroutineScope coroutineScope) {
            Job job = this.job;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.job = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new StyleAnimations$Entry$animateOut$1(this, StyleAnimations.this, null), 3, null);
        }

        public final void snapOut(CoroutineScope coroutineScope) {
            Job job = this.job;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.job = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new StyleAnimations$Entry$snapOut$1(this, StyleAnimations.this, null), 3, null);
        }
    }

    public final boolean isNotEmpty() {
        return this.size > 0;
    }

    private final Entry find(int key) {
        Entry[] values = this.values;
        int size = this.size;
        for (int i = 0; i < size; i++) {
            Entry entry = values[i];
            boolean z = false;
            if (entry != null && entry.getKey() == key) {
                z = true;
            }
            if (z) {
                return entry;
            }
        }
        return null;
    }

    private final void ensureCapacity(int requiredCapacity) {
        int current = this.values.length;
        if (requiredCapacity > current) {
            Object[] objArrCopyOf = Arrays.copyOf(this.values, Math.max(current * 2, 2));
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
            this.values = (Entry[]) objArrCopyOf;
        }
    }

    public final void record(int key, Style style, AnimationSpec<Float> toSpec, AnimationSpec<Float> fromSpec) {
        Entry animation = find(key);
        if (animation != null) {
            animation.setStyle(style);
            int state = animation.getState();
            switch (state) {
                case 1:
                    animation.setState(2);
                    return;
                case 4:
                    animation.setState(3);
                    break;
            }
            return;
        }
        ensureCapacity(this.size + 1);
        int insertIndex = this.size;
        this.values[insertIndex] = new Entry(key, style, toSpec, fromSpec);
        this.size++;
    }

    public final ResolvedStyle withAnimations(Density density, ResolvedStyle staticStyle, StyleOuterNode node, int forChanges) {
        ResolvedStyle result = this.currentStyle;
        staticStyle.copyInto$foundation(result);
        applyAnimationsTo(result, density, node, forChanges);
        return result;
    }

    public final void applyAnimationsTo(ResolvedStyle target, Density density, StyleOuterNode node, int forChanges) {
        Entry[] values$iv = this.values;
        int size$iv = this.size;
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            Entry entry$iv = values$iv[i$iv];
            if (entry$iv != null) {
                target.copyInto$foundation(entry$iv.getStyleScope());
                entry$iv.getStyleScope().resolve$foundation(entry$iv.getStyle(), node, density, true);
                int changed = target.diff$foundation(entry$iv.getStyleScope(), forChanges);
                if ((changed & forChanges) != 0) {
                    ResolvedStyleKt.lerp(target, entry$iv.getStyleScope(), entry$iv.getAnim().getValue().floatValue(), forChanges, target);
                }
            }
        }
    }

    public final void preResolve() {
        Entry[] values$iv = this.values;
        int size$iv = this.size;
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            Entry entry$iv = values$iv[i$iv];
            if (entry$iv != null) {
                int state = 1;
                switch (entry$iv.getState()) {
                    case 2:
                    case 3:
                        break;
                    default:
                        state = entry$iv.getState();
                        break;
                }
                entry$iv.setState(state);
            }
        }
    }

    public final int postResolve(StyleOuterNode node, Density density, boolean triggerAnimations) {
        int changedFlags = 0;
        Entry[] values$iv = this.values;
        int size$iv = this.size;
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            Entry entry$iv = values$iv[i$iv];
            if (entry$iv != null) {
                if (entry$iv.getState() == 3) {
                    entry$iv.getStyleScope().resolve$foundation(entry$iv.getStyle(), node, density, true);
                    changedFlags |= entry$iv.getStyleScope().flags;
                    if (triggerAnimations) {
                        entry$iv.animateIn(node.getNode().getCoroutineScope());
                    } else {
                        entry$iv.snapIn(node.getNode().getCoroutineScope());
                    }
                } else if (entry$iv.getState() == 1) {
                    entry$iv.setState(4);
                    entry$iv.getStyleScope().clear$foundation();
                    entry$iv.getStyleScope().resolve$foundation(entry$iv.getStyle(), node, density, true);
                    changedFlags |= entry$iv.getStyleScope().flags;
                    if (triggerAnimations) {
                        entry$iv.animateOut(node.getNode().getCoroutineScope());
                    } else {
                        entry$iv.snapOut(node.getNode().getCoroutineScope());
                    }
                }
            }
        }
        return changedFlags;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cleanupAnimations() {
        Entry value;
        Entry[] values = this.values;
        int size = this.size;
        int i = 0;
        int j = 0;
        while (j < size && (value = values[j]) != null) {
            if (value.getState() == 4 && !value.getAnim().isRunning()) {
                values[j] = null;
                j++;
            } else {
                if (i != j) {
                    values[i] = value;
                    values[j] = null;
                }
                i++;
                j++;
            }
        }
        this.size = i;
        if (size != i && !this.inGuard) {
            this.inGuard = true;
            try {
                StyleOuterNode.resolveStyleAndInvalidate$default(this.node, false, 1, null);
            } finally {
                this.inGuard = false;
            }
        }
    }

    private final void forEach(Function1<? super Entry, Unit> action) {
        Entry[] values = this.values;
        int size = this.size;
        for (int i = 0; i < size; i++) {
            Entry entry = values[i];
            if (entry != null) {
                action.invoke(entry);
            }
        }
    }

    private final void recursionGuard(Function0<Unit> block) {
        if (this.inGuard) {
            return;
        }
        this.inGuard = true;
        try {
            block.invoke();
        } finally {
            this.inGuard = false;
        }
    }
}
