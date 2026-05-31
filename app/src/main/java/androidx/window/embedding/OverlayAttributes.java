package androidx.window.embedding;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverlayAttributes.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u000fB\u0013\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/window/embedding/OverlayAttributes;", "", "bounds", "Landroidx/window/embedding/EmbeddingBounds;", "<init>", "(Landroidx/window/embedding/EmbeddingBounds;)V", "getBounds", "()Landroidx/window/embedding/EmbeddingBounds;", "toString", "", "equals", "", "other", "hashCode", "", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OverlayAttributes {
    private final EmbeddingBounds bounds;

    public OverlayAttributes() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public OverlayAttributes(EmbeddingBounds bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        this.bounds = bounds;
    }

    public /* synthetic */ OverlayAttributes(EmbeddingBounds embeddingBounds, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? EmbeddingBounds.BOUNDS_EXPANDED : embeddingBounds);
    }

    public final EmbeddingBounds getBounds() {
        return this.bounds;
    }

    public String toString() {
        return OverlayAttributes.class.getSimpleName() + ": {bounds=" + this.bounds + '}';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof OverlayAttributes) {
            return Intrinsics.areEqual(this.bounds, ((OverlayAttributes) other).bounds);
        }
        return false;
    }

    public int hashCode() {
        return this.bounds.hashCode();
    }

    /* JADX INFO: compiled from: OverlayAttributes.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/window/embedding/OverlayAttributes$Builder;", "", "<init>", "()V", "bounds", "Landroidx/window/embedding/EmbeddingBounds;", "setBounds", "build", "Landroidx/window/embedding/OverlayAttributes;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Builder {
        private EmbeddingBounds bounds = EmbeddingBounds.BOUNDS_EXPANDED;

        public final Builder setBounds(EmbeddingBounds bounds) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            Builder $this$setBounds_u24lambda_u240 = this;
            $this$setBounds_u24lambda_u240.bounds = bounds;
            return this;
        }

        public final OverlayAttributes build() {
            return new OverlayAttributes(this.bounds);
        }
    }
}
