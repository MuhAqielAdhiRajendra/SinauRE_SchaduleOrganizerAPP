package androidx.compose.ui.graphics.vector;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: VectorPainter.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010@\u001a\u000204*\u00020AH\u0014J\u0010\u0010B\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020<H\u0014J\u0012\u0010D\u001a\u00020\u000f2\b\u0010E\u001a\u0004\u0018\u00010\u0017H\u0014R+\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR+\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000f8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R(\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u00178@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00078@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\n\"\u0004\b\u001f\u0010\fR$\u0010!\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020 8@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020'X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020+8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u001c\u0010.\u001a\u0004\u0018\u00010/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R+\u00105\u001a\u0002042\u0006\u0010\u0006\u001a\u0002048B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010\u000e\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010;\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010\n¨\u0006F"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorPainter;", "Landroidx/compose/ui/graphics/painter/Painter;", "root", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "<init>", "(Landroidx/compose/ui/graphics/vector/GroupComponent;)V", "<set-?>", "Landroidx/compose/ui/geometry/Size;", "size", "getSize-NH-jbRc$ui", "()J", "setSize-uvyYCjk$ui", "(J)V", "size$delegate", "Landroidx/compose/runtime/MutableState;", "", "autoMirror", "getAutoMirror$ui", "()Z", "setAutoMirror$ui", "(Z)V", "autoMirror$delegate", "value", "Landroidx/compose/ui/graphics/ColorFilter;", "intrinsicColorFilter", "getIntrinsicColorFilter$ui", "()Landroidx/compose/ui/graphics/ColorFilter;", "setIntrinsicColorFilter$ui", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "viewportSize", "getViewportSize-NH-jbRc$ui", "setViewportSize-uvyYCjk$ui", "", HintConstants.AUTOFILL_HINT_NAME, "getName$ui", "()Ljava/lang/String;", "setName$ui", "(Ljava/lang/String;)V", "vector", "Landroidx/compose/ui/graphics/vector/VectorComponent;", "getVector$ui", "()Landroidx/compose/ui/graphics/vector/VectorComponent;", "bitmapConfig", "Landroidx/compose/ui/graphics/ImageBitmapConfig;", "getBitmapConfig-_sVssgQ$ui", "()I", "composition", "Landroidx/compose/runtime/Composition;", "getComposition$ui", "()Landroidx/compose/runtime/Composition;", "setComposition$ui", "(Landroidx/compose/runtime/Composition;)V", "", "drawInvalidation", "getDrawInvalidation", "()Lkotlin/Unit;", "setDrawInvalidation", "(Lkotlin/Unit;)V", "drawInvalidation$delegate", "currentAlpha", "", "currentColorFilter", "intrinsicSize", "getIntrinsicSize-NH-jbRc", "onDraw", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "applyAlpha", "alpha", "applyColorFilter", "colorFilter", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VectorPainter extends Painter {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: autoMirror$delegate, reason: from kotlin metadata */
    private final MutableState autoMirror;
    private Composition composition;
    private float currentAlpha;
    private ColorFilter currentColorFilter;

    /* JADX INFO: renamed from: drawInvalidation$delegate, reason: from kotlin metadata */
    private final MutableState drawInvalidation;

    /* JADX INFO: renamed from: size$delegate, reason: from kotlin metadata */
    private final MutableState size;
    private final VectorComponent vector;

    public VectorPainter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public VectorPainter(GroupComponent root) {
        this.size = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m5125boximpl(Size.INSTANCE.m5146getZeroNHjbRc()), null, 2, null);
        this.autoMirror = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        VectorComponent $this$vector_u24lambda_u240 = new VectorComponent(root);
        $this$vector_u24lambda_u240.setInvalidateCallback$ui(new Function0<Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainter$vector$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.setDrawInvalidation(Unit.INSTANCE);
            }
        });
        this.vector = $this$vector_u24lambda_u240;
        this.drawInvalidation = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.currentAlpha = 1.0f;
    }

    public /* synthetic */ VectorPainter(GroupComponent groupComponent, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new GroupComponent() : groupComponent);
    }

    /* JADX INFO: renamed from: getSize-NH-jbRc$ui, reason: not valid java name */
    public final long m6064getSizeNHjbRc$ui() {
        State $this$getValue$iv = this.size;
        return ((Size) $this$getValue$iv.getValue()).m5142unboximpl();
    }

    /* JADX INFO: renamed from: setSize-uvyYCjk$ui, reason: not valid java name */
    public final void m6066setSizeuvyYCjk$ui(long j) {
        MutableState $this$setValue$iv = this.size;
        $this$setValue$iv.setValue(Size.m5125boximpl(j));
    }

    public final boolean getAutoMirror$ui() {
        State $this$getValue$iv = this.autoMirror;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setAutoMirror$ui(boolean z) {
        MutableState $this$setValue$iv = this.autoMirror;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final ColorFilter getIntrinsicColorFilter$ui() {
        return this.vector.getIntrinsicColorFilter$ui();
    }

    public final void setIntrinsicColorFilter$ui(ColorFilter value) {
        this.vector.setIntrinsicColorFilter$ui(value);
    }

    /* JADX INFO: renamed from: getViewportSize-NH-jbRc$ui, reason: not valid java name */
    public final long m6065getViewportSizeNHjbRc$ui() {
        return this.vector.m6055getViewportSizeNHjbRc$ui();
    }

    /* JADX INFO: renamed from: setViewportSize-uvyYCjk$ui, reason: not valid java name */
    public final void m6067setViewportSizeuvyYCjk$ui(long value) {
        this.vector.m6056setViewportSizeuvyYCjk$ui(value);
    }

    public final String getName$ui() {
        return this.vector.getName();
    }

    public final void setName$ui(String value) {
        this.vector.setName(value);
    }

    /* JADX INFO: renamed from: getVector$ui, reason: from getter */
    public final VectorComponent getVector() {
        return this.vector;
    }

    /* JADX INFO: renamed from: getBitmapConfig-_sVssgQ$ui, reason: not valid java name */
    public final int m6063getBitmapConfig_sVssgQ$ui() {
        return this.vector.m6054getCacheBitmapConfig_sVssgQ$ui();
    }

    /* JADX INFO: renamed from: getComposition$ui, reason: from getter */
    public final Composition getComposition() {
        return this.composition;
    }

    public final void setComposition$ui(Composition composition) {
        this.composition = composition;
    }

    private final Unit getDrawInvalidation() {
        State $this$getValue$iv = this.drawInvalidation;
        $this$getValue$iv.getValue();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDrawInvalidation(Unit unit) {
        MutableState $this$setValue$iv = this.drawInvalidation;
        $this$setValue$iv.setValue(unit);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long getIntrinsicSize() {
        return m6064getSizeNHjbRc$ui();
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected void onDraw(DrawScope $this$onDraw) throws Throwable {
        VectorComponent $this$onDraw_u24lambda_u240 = this.vector;
        ColorFilter intrinsicColorFilter$ui = this.currentColorFilter;
        if (intrinsicColorFilter$ui == null) {
            intrinsicColorFilter$ui = $this$onDraw_u24lambda_u240.getIntrinsicColorFilter$ui();
        }
        ColorFilter filter = intrinsicColorFilter$ui;
        if (!getAutoMirror$ui() || $this$onDraw.getLayoutDirection() != LayoutDirection.Rtl) {
            $this$onDraw_u24lambda_u240.draw($this$onDraw, this.currentAlpha, filter);
        } else {
            long pivot$iv$iv = $this$onDraw.mo5886getCenterF1C5BW0();
            DrawContext $this$withTransform_u24lambda_u240$iv$iv$iv = $this$onDraw.getDrawContext();
            long previousSize$iv$iv$iv = $this$withTransform_u24lambda_u240$iv$iv$iv.mo5808getSizeNHjbRc();
            $this$withTransform_u24lambda_u240$iv$iv$iv.getCanvas().save();
            try {
                DrawTransform $this$scale_Fgt4K4Q_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv$iv.getTransform();
                $this$scale_Fgt4K4Q_u24lambda_u240$iv$iv.mo5815scale0AR0LA0(-1.0f, 1.0f, pivot$iv$iv);
            } catch (Throwable th) {
                th = th;
            }
            try {
                $this$onDraw_u24lambda_u240.draw($this$onDraw, this.currentAlpha, filter);
                $this$withTransform_u24lambda_u240$iv$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u240$iv$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv$iv);
            } catch (Throwable th2) {
                th = th2;
                $this$withTransform_u24lambda_u240$iv$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u240$iv$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv$iv);
                throw th;
            }
        }
        getDrawInvalidation();
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyAlpha(float alpha) {
        this.currentAlpha = alpha;
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyColorFilter(ColorFilter colorFilter) {
        this.currentColorFilter = colorFilter;
        return true;
    }
}
