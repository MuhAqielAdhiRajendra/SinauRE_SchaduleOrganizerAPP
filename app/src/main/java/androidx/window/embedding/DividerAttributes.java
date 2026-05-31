package androidx.window.embedding;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DividerAttributes.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u0000 \u000f2\u00020\u0001:\u0004\f\r\u000e\u000fB\u001d\b\u0002\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0010"}, d2 = {"Landroidx/window/embedding/DividerAttributes;", "", "widthDp", "", TypedValues.Custom.S_COLOR, "<init>", "(II)V", "getWidthDp", "()I", "getColor", "toString", "", "FixedDividerAttributes", "DraggableDividerAttributes", "DragRange", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class DividerAttributes {
    public static final int COLOR_SYSTEM_DEFAULT = -16777216;
    public static final float DRAG_RANGE_VALUE_UNSPECIFIED = -1.0f;
    public static final int TYPE_VALUE_DRAGGABLE = 1;
    public static final int TYPE_VALUE_FIXED = 0;
    public static final int WIDTH_SYSTEM_DEFAULT = -1;
    private final int color;
    private final int widthDp;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final DividerAttributes NO_DIVIDER = new DividerAttributes() { // from class: androidx.window.embedding.DividerAttributes$Companion$NO_DIVIDER$1
        @Override // androidx.window.embedding.DividerAttributes
        public String toString() {
            return "NO_DIVIDER";
        }

        public int hashCode() {
            return toString().hashCode();
        }
    };

    public /* synthetic */ DividerAttributes(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2);
    }

    private DividerAttributes(int widthDp, int color) {
        this.widthDp = widthDp;
        this.color = color;
    }

    /* synthetic */ DividerAttributes(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? -1 : i, (i3 & 2) != 0 ? -16777216 : i2);
    }

    public final int getWidthDp() {
        return this.widthDp;
    }

    public final int getColor() {
        return this.color;
    }

    public String toString() {
        return DividerAttributes.class.getSimpleName() + "{width=" + this.widthDp + ", color=" + this.color + '}';
    }

    /* JADX INFO: compiled from: DividerAttributes.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\fB\u001d\b\u0003\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\u0003H\u0016¨\u0006\r"}, d2 = {"Landroidx/window/embedding/DividerAttributes$FixedDividerAttributes;", "Landroidx/window/embedding/DividerAttributes;", "widthDp", "", TypedValues.Custom.S_COLOR, "<init>", "(II)V", "equals", "", "other", "", "hashCode", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class FixedDividerAttributes extends DividerAttributes {
        public /* synthetic */ FixedDividerAttributes(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2);
        }

        /* synthetic */ FixedDividerAttributes(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? -1 : i, (i3 & 2) != 0 ? -16777216 : i2);
        }

        private FixedDividerAttributes(int widthDp, int color) {
            super(widthDp, color, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof FixedDividerAttributes) {
                return getWidthDp() == ((FixedDividerAttributes) other).getWidthDp() && getColor() == ((FixedDividerAttributes) other).getColor();
            }
            return false;
        }

        public int hashCode() {
            return (getWidthDp() * 31) + getColor();
        }

        /* JADX INFO: compiled from: DividerAttributes.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0012\u0010\n\u001a\u00020\u00002\b\b\u0001\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\u000b\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\bH\u0007J\b\u0010\f\u001a\u00020\u0005H\u0007R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/embedding/DividerAttributes$FixedDividerAttributes$Builder;", "", "<init>", "()V", "original", "Landroidx/window/embedding/DividerAttributes$FixedDividerAttributes;", "(Landroidx/window/embedding/DividerAttributes$FixedDividerAttributes;)V", "widthDp", "", TypedValues.Custom.S_COLOR, "setWidthDp", "setColor", "build", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Builder {
            private int color;
            private int widthDp;

            public Builder() {
                this.widthDp = -1;
                this.color = -16777216;
            }

            /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
            public Builder(FixedDividerAttributes original) {
                this();
                Intrinsics.checkNotNullParameter(original, "original");
                this.widthDp = original.getWidthDp();
                this.color = original.getColor();
            }

            public final Builder setWidthDp(int widthDp) {
                Builder $this$setWidthDp_u24lambda_u240 = this;
                DividerAttributes.INSTANCE.validateWidth(widthDp);
                $this$setWidthDp_u24lambda_u240.widthDp = widthDp;
                return this;
            }

            public final Builder setColor(int color) {
                Builder $this$setColor_u24lambda_u241 = this;
                DividerAttributes.INSTANCE.validateColor(color);
                $this$setColor_u24lambda_u241.color = color;
                return this;
            }

            public final FixedDividerAttributes build() {
                return new FixedDividerAttributes(this.widthDp, this.color, null);
            }
        }
    }

    /* JADX INFO: compiled from: DividerAttributes.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B1\b\u0003\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\r¨\u0006\u0015"}, d2 = {"Landroidx/window/embedding/DividerAttributes$DraggableDividerAttributes;", "Landroidx/window/embedding/DividerAttributes;", "widthDp", "", TypedValues.Custom.S_COLOR, "dragRange", "Landroidx/window/embedding/DividerAttributes$DragRange;", "isDraggingToFullscreenAllowed", "", "<init>", "(IILandroidx/window/embedding/DividerAttributes$DragRange;Z)V", "getDragRange", "()Landroidx/window/embedding/DividerAttributes$DragRange;", "()Z", "equals", "other", "", "hashCode", "toString", "", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class DraggableDividerAttributes extends DividerAttributes {

        /* JADX INFO: renamed from: dragRange, reason: from kotlin metadata and from toString */
        private final DragRange primaryContainerDragRange;
        private final boolean isDraggingToFullscreenAllowed;

        public /* synthetic */ DraggableDividerAttributes(int i, int i2, DragRange dragRange, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, dragRange, z);
        }

        /* synthetic */ DraggableDividerAttributes(int i, int i2, DragRange dragRange, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? -1 : i, (i3 & 2) != 0 ? -16777216 : i2, (i3 & 4) != 0 ? DragRange.DRAG_RANGE_SYSTEM_DEFAULT : dragRange, (i3 & 8) != 0 ? false : z);
        }

        /* JADX INFO: renamed from: getDragRange, reason: from getter */
        public final DragRange getPrimaryContainerDragRange() {
            return this.primaryContainerDragRange;
        }

        /* JADX INFO: renamed from: isDraggingToFullscreenAllowed, reason: from getter */
        public final boolean getIsDraggingToFullscreenAllowed() {
            return this.isDraggingToFullscreenAllowed;
        }

        private DraggableDividerAttributes(int widthDp, int color, DragRange dragRange, boolean isDraggingToFullscreenAllowed) {
            super(widthDp, color, null);
            this.primaryContainerDragRange = dragRange;
            this.isDraggingToFullscreenAllowed = isDraggingToFullscreenAllowed;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof DraggableDividerAttributes) {
                return getWidthDp() == ((DraggableDividerAttributes) other).getWidthDp() && getColor() == ((DraggableDividerAttributes) other).getColor() && Intrinsics.areEqual(this.primaryContainerDragRange, ((DraggableDividerAttributes) other).primaryContainerDragRange) && this.isDraggingToFullscreenAllowed == ((DraggableDividerAttributes) other).isDraggingToFullscreenAllowed;
            }
            return false;
        }

        public int hashCode() {
            return (((((getWidthDp() * 31) + getColor()) * 31) + this.primaryContainerDragRange.hashCode()) * 31) + Boolean.hashCode(this.isDraggingToFullscreenAllowed);
        }

        @Override // androidx.window.embedding.DividerAttributes
        public String toString() {
            return DraggableDividerAttributes.class.getSimpleName() + "{width=" + getWidthDp() + ", color=" + getColor() + ", primaryContainerDragRange=" + this.primaryContainerDragRange + ", isDraggingToFullscreenAllowed=" + this.isDraggingToFullscreenAllowed + '}';
        }

        /* JADX INFO: compiled from: DividerAttributes.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0012\u0010\u000e\u001a\u00020\u00002\b\b\u0001\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\u000f\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\bH\u0007J\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\rH\u0007J\b\u0010\u0013\u001a\u00020\u0005H\u0007R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/window/embedding/DividerAttributes$DraggableDividerAttributes$Builder;", "", "<init>", "()V", "original", "Landroidx/window/embedding/DividerAttributes$DraggableDividerAttributes;", "(Landroidx/window/embedding/DividerAttributes$DraggableDividerAttributes;)V", "widthDp", "", TypedValues.Custom.S_COLOR, "dragRange", "Landroidx/window/embedding/DividerAttributes$DragRange;", "isDraggingToFullscreenAllowed", "", "setWidthDp", "setColor", "setDragRange", "setDraggingToFullscreenAllowed", "allowed", "build", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Builder {
            private int color;
            private DragRange dragRange;
            private boolean isDraggingToFullscreenAllowed;
            private int widthDp;

            public Builder() {
                this.widthDp = -1;
                this.color = -16777216;
                this.dragRange = DragRange.DRAG_RANGE_SYSTEM_DEFAULT;
            }

            /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
            public Builder(DraggableDividerAttributes original) {
                this();
                Intrinsics.checkNotNullParameter(original, "original");
                this.widthDp = original.getWidthDp();
                this.dragRange = original.getPrimaryContainerDragRange();
                this.color = original.getColor();
                this.isDraggingToFullscreenAllowed = original.getIsDraggingToFullscreenAllowed();
            }

            public final Builder setWidthDp(int widthDp) {
                Builder $this$setWidthDp_u24lambda_u240 = this;
                DividerAttributes.INSTANCE.validateWidth(widthDp);
                $this$setWidthDp_u24lambda_u240.widthDp = widthDp;
                return this;
            }

            public final Builder setColor(int color) {
                Builder $this$setColor_u24lambda_u241 = this;
                DividerAttributes.INSTANCE.validateColor(color);
                $this$setColor_u24lambda_u241.color = color;
                return this;
            }

            public final Builder setDragRange(DragRange dragRange) {
                Intrinsics.checkNotNullParameter(dragRange, "dragRange");
                Builder $this$setDragRange_u24lambda_u242 = this;
                $this$setDragRange_u24lambda_u242.dragRange = dragRange;
                return this;
            }

            public final Builder setDraggingToFullscreenAllowed(boolean allowed) {
                Builder $this$setDraggingToFullscreenAllowed_u24lambda_u243 = this;
                $this$setDraggingToFullscreenAllowed_u24lambda_u243.isDraggingToFullscreenAllowed = allowed;
                return this;
            }

            public final DraggableDividerAttributes build() {
                return new DraggableDividerAttributes(this.widthDp, this.color, this.dragRange, this.isDraggingToFullscreenAllowed, null);
            }
        }
    }

    /* JADX INFO: compiled from: DividerAttributes.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b&\u0018\u0000 \u00052\u00020\u0001:\u0002\u0004\u0005B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Landroidx/window/embedding/DividerAttributes$DragRange;", "", "<init>", "()V", "SplitRatioDragRange", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static abstract class DragRange {
        public static final DragRange DRAG_RANGE_SYSTEM_DEFAULT = new DragRange() { // from class: androidx.window.embedding.DividerAttributes$DragRange$Companion$DRAG_RANGE_SYSTEM_DEFAULT$1
            public String toString() {
                return "DRAG_RANGE_SYSTEM_DEFAULT";
            }
        };

        public /* synthetic */ DragRange(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private DragRange() {
        }

        /* JADX INFO: compiled from: DividerAttributes.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0012"}, d2 = {"Landroidx/window/embedding/DividerAttributes$DragRange$SplitRatioDragRange;", "Landroidx/window/embedding/DividerAttributes$DragRange;", "minRatio", "", "maxRatio", "<init>", "(FF)V", "getMinRatio", "()F", "getMaxRatio", "toString", "", "equals", "", "other", "", "hashCode", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class SplitRatioDragRange extends DragRange {
            private final float maxRatio;
            private final float minRatio;

            public final float getMinRatio() {
                return this.minRatio;
            }

            public final float getMaxRatio() {
                return this.maxRatio;
            }

            public SplitRatioDragRange(float minRatio, float maxRatio) {
                super(null);
                this.minRatio = minRatio;
                this.maxRatio = maxRatio;
                if (this.minRatio <= 0.0d || this.minRatio >= 1.0d) {
                    throw new IllegalArgumentException("minRatio must be in the interval (0.0, 1.0)");
                }
                if (this.maxRatio <= 0.0d || this.maxRatio >= 1.0d) {
                    throw new IllegalArgumentException("maxRatio must be in the interval (0.0, 1.0)");
                }
                if (this.minRatio <= this.maxRatio) {
                } else {
                    throw new IllegalArgumentException("minRatio must be less than or equal to maxRatio");
                }
            }

            public String toString() {
                return "SplitRatioDragRange[" + this.minRatio + ", " + this.maxRatio + ']';
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SplitRatioDragRange)) {
                    return false;
                }
                if (this.minRatio == ((SplitRatioDragRange) other).minRatio) {
                    if (this.maxRatio == ((SplitRatioDragRange) other).maxRatio) {
                        return true;
                    }
                }
                return false;
            }

            public int hashCode() {
                return (Float.hashCode(this.minRatio) * 31) + Float.hashCode(this.maxRatio);
            }
        }
    }

    /* JADX INFO: compiled from: DividerAttributes.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J=\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J-\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u001bJ\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0012\u0010\u001d\u001a\u00020\u00172\b\b\u0001\u0010\u0010\u001a\u00020\u0005H\u0002J\f\u0010\u001e\u001a\u00020\u0005*\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/window/embedding/DividerAttributes$Companion;", "", "<init>", "()V", "WIDTH_SYSTEM_DEFAULT", "", "NO_DIVIDER", "Landroidx/window/embedding/DividerAttributes;", "TYPE_VALUE_FIXED", "TYPE_VALUE_DRAGGABLE", "DRAG_RANGE_VALUE_UNSPECIFIED", "", "COLOR_SYSTEM_DEFAULT", "createDividerAttributes", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "widthDp", TypedValues.Custom.S_COLOR, "dragRangeMinRatio", "dragRangeMaxRatio", "isDraggingToFullscreenAllowed", "", "createDividerAttributes$window_release", "validateXmlDividerAttributes", "", "hasDragRangeMinRatio", "hasDragRangeMaxRatio", "hasIsDraggingToFullscreenAllowed", "validateXmlDividerAttributes$window_release", "validateWidth", "validateColor", "alpha", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0057  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final androidx.window.embedding.DividerAttributes createDividerAttributes$window_release(int r6, int r7, int r8, float r9, float r10, boolean r11) {
            /*
                r5 = this;
                switch(r6) {
                    case 0: goto L63;
                    case 1: goto L22;
                    default: goto L3;
                }
            L3:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Got unknown divider type "
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r1 = r1.append(r6)
                r2 = 33
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L22:
                androidx.window.embedding.DividerAttributes$DraggableDividerAttributes$Builder r0 = new androidx.window.embedding.DividerAttributes$DraggableDividerAttributes$Builder
                r0.<init>()
                androidx.window.embedding.DividerAttributes$DraggableDividerAttributes$Builder r0 = r0.setWidthDp(r7)
                androidx.window.embedding.DividerAttributes$DraggableDividerAttributes$Builder r0 = r0.setColor(r8)
                androidx.window.embedding.DividerAttributes$DraggableDividerAttributes$Builder r0 = r0.setDraggingToFullscreenAllowed(r11)
                r1 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r2 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
                r3 = 1
                r4 = 0
                if (r2 != 0) goto L3f
                r2 = r3
                goto L40
            L3f:
                r2 = r4
            L40:
                if (r2 != 0) goto L57
                int r1 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
                if (r1 != 0) goto L47
                goto L48
            L47:
                r3 = r4
            L48:
                if (r3 == 0) goto L4b
                goto L57
            L4b:
                androidx.window.embedding.DividerAttributes$DragRange$SplitRatioDragRange r1 = new androidx.window.embedding.DividerAttributes$DragRange$SplitRatioDragRange
                r1.<init>(r9, r10)
                androidx.window.embedding.DividerAttributes$DragRange r1 = (androidx.window.embedding.DividerAttributes.DragRange) r1
                r0.setDragRange(r1)
                goto L5c
            L57:
                androidx.window.embedding.DividerAttributes$DragRange r1 = androidx.window.embedding.DividerAttributes.DragRange.DRAG_RANGE_SYSTEM_DEFAULT
                r0.setDragRange(r1)
            L5c:
                androidx.window.embedding.DividerAttributes$DraggableDividerAttributes r0 = r0.build()
                androidx.window.embedding.DividerAttributes r0 = (androidx.window.embedding.DividerAttributes) r0
                goto L76
            L63:
                androidx.window.embedding.DividerAttributes$FixedDividerAttributes$Builder r0 = new androidx.window.embedding.DividerAttributes$FixedDividerAttributes$Builder
                r0.<init>()
                androidx.window.embedding.DividerAttributes$FixedDividerAttributes$Builder r0 = r0.setWidthDp(r7)
                androidx.window.embedding.DividerAttributes$FixedDividerAttributes$Builder r0 = r0.setColor(r8)
                androidx.window.embedding.DividerAttributes$FixedDividerAttributes r0 = r0.build()
                androidx.window.embedding.DividerAttributes r0 = (androidx.window.embedding.DividerAttributes) r0
            L76:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.DividerAttributes.Companion.createDividerAttributes$window_release(int, int, int, float, float, boolean):androidx.window.embedding.DividerAttributes");
        }

        public final void validateXmlDividerAttributes$window_release(int type, boolean hasDragRangeMinRatio, boolean hasDragRangeMaxRatio, boolean hasIsDraggingToFullscreenAllowed) {
            if (type == 1) {
                return;
            }
            if (hasDragRangeMinRatio) {
                throw new IllegalArgumentException("Fixed divider does not allow attribute dragRangeMinRatio!");
            }
            if (hasDragRangeMaxRatio) {
                throw new IllegalArgumentException("Fixed divider does not allow attribute dragRangeMaxRatio!");
            }
            if (hasIsDraggingToFullscreenAllowed) {
                throw new IllegalArgumentException("Fixed divider does not allow attribute isDraggingToFullscreenAllowed!");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void validateWidth(int widthDp) {
            if (!(widthDp == -1 || widthDp >= 0)) {
                throw new IllegalArgumentException(("widthDp must be greater than or equal to 0 or WIDTH_SYSTEM_DEFAULT. Got: " + widthDp).toString());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void validateColor(int color) {
            Companion $this$validateColor_u24lambda_u243 = this;
            if (!($this$validateColor_u24lambda_u243.alpha(color) == 255)) {
                throw new IllegalArgumentException(("Divider color must be opaque. Got: " + Integer.toHexString(color)).toString());
            }
        }

        private final int alpha(int $this$alpha) {
            return $this$alpha >>> 24;
        }
    }
}
