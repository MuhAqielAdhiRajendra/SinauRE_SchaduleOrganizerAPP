package androidx.core.view;

import android.graphics.Matrix;
import android.graphics.Path;
import android.view.DisplayShape;
import androidx.core.graphics.PathParser;
import java.util.Objects;

/* JADX INFO: loaded from: classes12.dex */
public final class DisplayShapeCompat {
    static final DisplayShapeCompat EMPTY = new DisplayShapeCompat("", 0, 0, 1.0f, 0, 0, 0, 1.0f);
    private static final String TAG = "DisplayShapeCompat";
    private final Impl mImpl;

    private interface Impl {
        Path getPath();

        DisplayShape getPlatformDisplayShape();
    }

    private DisplayShapeCompat(DisplayShape platformDisplayShape) {
        this.mImpl = new Impl34(platformDisplayShape);
    }

    private DisplayShapeCompat(String spec, int displayWidth, int displayHeight, float physicalPixelDisplaySizeRatio, int rotation, int offsetX, int offsetY, float scale) {
        this.mImpl = new ImplBase(spec, displayWidth, displayHeight, physicalPixelDisplaySizeRatio, rotation, offsetX, offsetY, scale);
    }

    static DisplayShapeCompat toDisplayShapeCompat(DisplayShape ds) {
        if (ds == null) {
            return null;
        }
        return new DisplayShapeCompat(ds);
    }

    static DisplayShape toPlatformDisplayShape(DisplayShapeCompat dsc) {
        if (dsc == null) {
            return null;
        }
        return dsc.mImpl.getPlatformDisplayShape();
    }

    public static DisplayShapeCompat create(String spec, float physicalPixelDisplaySizeRatio, int displayWidth, int displayHeight) {
        return new DisplayShapeCompat(spec, displayWidth, displayHeight, physicalPixelDisplaySizeRatio, 0, 0, 0, 1.0f);
    }

    public static DisplayShapeCompat create(int displayWidth, int displayHeight, boolean isCircular, int topLeftRadius, int topRightRadius, int bottomRightRadius, int bottomLeftRadius) {
        String spec = createSpecString(displayWidth, displayHeight, isCircular, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
        return new DisplayShapeCompat(spec, displayWidth, displayHeight, 1.0f, 0, 0, 0, 1.0f);
    }

    private static String createSpecString(int displayWidth, int displayHeight, boolean isCircular, int topLeftRadius, int topRightRadius, int bottomRightRadius, int bottomLeftRadius) {
        if (isCircular) {
            int xRadius = displayWidth / 2;
            int yRadius = displayHeight / 2;
            return "M0," + yRadius + " A" + xRadius + "," + yRadius + " 0 1,1 " + displayWidth + "," + yRadius + " A" + xRadius + "," + yRadius + " 0 1,1 0," + yRadius + " Z";
        }
        StringBuilder spec = new StringBuilder();
        int maxRadius = Math.min(displayWidth / 2, displayHeight / 2);
        int rTL = Math.min(maxRadius, topLeftRadius);
        int rTR = Math.min(maxRadius, topRightRadius);
        int rBR = Math.min(maxRadius, bottomRightRadius);
        int rBL = Math.min(maxRadius, bottomLeftRadius);
        spec.append("M ").append(rTL).append(",0");
        spec.append(" L ").append(displayWidth - rTR).append(",0");
        if (rTR > 0) {
            spec.append(" A ").append(rTR).append(",").append(rTR).append(" 0 0,1 ").append(displayWidth).append(",").append(rTR);
        }
        spec.append(" L ").append(displayWidth).append(",").append(displayHeight - rBR);
        if (rBR > 0) {
            spec.append(" A ").append(rBR).append(",").append(rBR).append(" 0 0,1 ").append(displayWidth - rBR).append(",").append(displayHeight);
        }
        spec.append(" L ").append(rBL).append(",").append(displayHeight);
        if (rBL > 0) {
            spec.append(" A ").append(rBL).append(",").append(rBL).append(" 0 0,1 ").append(0).append(",").append(displayHeight - rBL);
        }
        if (rTL > 0) {
            spec.append(" L ").append(0).append(",").append(rTL);
            spec.append(" A ").append(rTL).append(",").append(rTL).append(" 0 0,1 ").append(rTL).append(",").append(0);
        }
        spec.append(" Z");
        return spec.toString();
    }

    public Path getPath() {
        return this.mImpl.getPath();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DisplayShapeCompat)) {
            return false;
        }
        DisplayShapeCompat that = (DisplayShapeCompat) o;
        return Objects.equals(this.mImpl, that.mImpl);
    }

    public int hashCode() {
        return Objects.hashCode(this.mImpl);
    }

    public String toString() {
        return this.mImpl.toString();
    }

    private static class ImplBase implements Impl {
        private Path mCachedPath;
        private final int mDisplayHeight;
        private final String mDisplayShapeSpec;
        private final int mDisplayWidth;
        private final int mOffsetX;
        private final int mOffsetY;
        private final float mPhysicalPixelDisplaySizeRatio;
        private final int mRotation;
        private final float mScale;

        ImplBase(String spec, int displayWidth, int displayHeight, float physicalPixelDisplaySizeRatio, int rotation, int offsetX, int offsetY, float scale) {
            this.mDisplayShapeSpec = spec;
            this.mDisplayWidth = displayWidth;
            this.mDisplayHeight = displayHeight;
            this.mPhysicalPixelDisplaySizeRatio = physicalPixelDisplaySizeRatio;
            this.mRotation = rotation;
            this.mOffsetX = offsetX;
            this.mOffsetY = offsetY;
            this.mScale = scale;
        }

        @Override // androidx.core.view.DisplayShapeCompat.Impl
        public Path getPath() {
            if (this.mCachedPath != null) {
                return this.mCachedPath;
            }
            if (this.mDisplayShapeSpec == null || this.mDisplayShapeSpec.isEmpty()) {
                return new Path();
            }
            try {
                Path path = PathParser.createPathFromPathData(this.mDisplayShapeSpec);
                if (!path.isEmpty()) {
                    Matrix matrix = new Matrix();
                    if (this.mRotation != 0) {
                        float rotateDegrees = 0.0f;
                        float pivotX = 0.0f;
                        float pivotY = 0.0f;
                        switch (this.mRotation) {
                            case 1:
                                rotateDegrees = 90.0f;
                                pivotX = this.mDisplayWidth;
                                pivotY = 0.0f;
                                break;
                            case 2:
                                rotateDegrees = 180.0f;
                                pivotX = this.mDisplayWidth;
                                pivotY = this.mDisplayHeight;
                                break;
                            case 3:
                                rotateDegrees = 270.0f;
                                pivotX = 0.0f;
                                pivotY = this.mDisplayHeight;
                                break;
                        }
                        matrix.preRotate(rotateDegrees, pivotX, pivotY);
                    }
                    float rotateDegrees2 = this.mPhysicalPixelDisplaySizeRatio;
                    if (rotateDegrees2 != 1.0f) {
                        matrix.preScale(this.mPhysicalPixelDisplaySizeRatio, this.mPhysicalPixelDisplaySizeRatio);
                    }
                    if (this.mOffsetX != 0 || this.mOffsetY != 0) {
                        matrix.postTranslate(this.mOffsetX, this.mOffsetY);
                    }
                    if (this.mScale != 1.0f) {
                        matrix.postScale(this.mScale, this.mScale);
                    }
                    path.transform(matrix);
                }
                this.mCachedPath = path;
                return path;
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Failed to parse DisplayShapeCompat path data: " + this.mDisplayShapeSpec, e);
            }
        }

        @Override // androidx.core.view.DisplayShapeCompat.Impl
        public DisplayShape getPlatformDisplayShape() {
            return null;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ImplBase)) {
                return false;
            }
            ImplBase that = (ImplBase) o;
            return Objects.equals(this.mDisplayShapeSpec, that.mDisplayShapeSpec) && this.mDisplayWidth == that.mDisplayWidth && this.mDisplayHeight == that.mDisplayHeight && this.mPhysicalPixelDisplaySizeRatio == that.mPhysicalPixelDisplaySizeRatio && this.mRotation == that.mRotation && this.mOffsetX == that.mOffsetX && this.mOffsetY == that.mOffsetY && this.mScale == that.mScale;
        }

        public int hashCode() {
            return Objects.hash(this.mDisplayShapeSpec, Integer.valueOf(this.mDisplayWidth), Integer.valueOf(this.mDisplayHeight), Float.valueOf(this.mPhysicalPixelDisplaySizeRatio), Integer.valueOf(this.mRotation), Integer.valueOf(this.mOffsetX), Integer.valueOf(this.mOffsetY), Float.valueOf(this.mScale));
        }

        public String toString() {
            return "DisplayShapeCompat{ spec=" + (this.mDisplayShapeSpec != null ? Integer.valueOf(this.mDisplayShapeSpec.hashCode()) : "null") + " displayWidth=" + this.mDisplayWidth + " displayHeight=" + this.mDisplayHeight + " physicalPixelDisplaySizeRatio=" + this.mPhysicalPixelDisplaySizeRatio + " rotation=" + this.mRotation + " offsetX=" + this.mOffsetX + " offsetY=" + this.mOffsetY + " scale=" + this.mScale + "}";
        }
    }

    private static class Impl34 implements Impl {
        private final DisplayShape mPlatformDisplayShape;

        Impl34(DisplayShape platformDisplayShape) {
            this.mPlatformDisplayShape = platformDisplayShape;
        }

        @Override // androidx.core.view.DisplayShapeCompat.Impl
        public Path getPath() {
            return this.mPlatformDisplayShape.getPath();
        }

        @Override // androidx.core.view.DisplayShapeCompat.Impl
        public DisplayShape getPlatformDisplayShape() {
            return this.mPlatformDisplayShape;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Impl34)) {
                return false;
            }
            Impl34 that = (Impl34) o;
            return Objects.equals(this.mPlatformDisplayShape, that.mPlatformDisplayShape);
        }

        public int hashCode() {
            return Objects.hashCode(this.mPlatformDisplayShape);
        }

        public String toString() {
            return "DisplayShapeCompat{mPlatformDisplayShape=" + this.mPlatformDisplayShape + '}';
        }
    }
}
