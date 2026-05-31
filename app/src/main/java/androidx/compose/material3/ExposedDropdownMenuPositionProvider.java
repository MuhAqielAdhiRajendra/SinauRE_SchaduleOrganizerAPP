package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.material3.internal.MenuPosition;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ExposedDropdownMenu.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u00128\b\u0002\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\u000b¢\u0006\u0004\b\u0011\u0010\u0012J/\u0010&\u001a\u00020'2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020)H\u0016¢\u0006\u0004\b-\u0010.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016RA\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Landroidx/compose/material3/ExposedDropdownMenuPositionProvider;", "Landroidx/compose/ui/window/PopupPositionProvider;", "density", "Landroidx/compose/ui/unit/Density;", "topWindowInsets", "", "keyboardSignalState", "Landroidx/compose/runtime/State;", "", "verticalMargin", "onPositionCalculated", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntRect;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "anchorBounds", "menuBounds", "<init>", "(Landroidx/compose/ui/unit/Density;ILandroidx/compose/runtime/State;ILkotlin/jvm/functions/Function2;)V", "getDensity", "()Landroidx/compose/ui/unit/Density;", "getTopWindowInsets", "()I", "getKeyboardSignalState", "()Landroidx/compose/runtime/State;", "getVerticalMargin", "getOnPositionCalculated", "()Lkotlin/jvm/functions/Function2;", "startToAnchorStart", "Landroidx/compose/material3/internal/MenuPosition$Horizontal;", "endToAnchorEnd", "leftToWindowLeft", "rightToWindowRight", "topToAnchorBottom", "Landroidx/compose/material3/internal/MenuPosition$Vertical;", "bottomToAnchorTop", "topToWindowTop", "bottomToWindowBottom", "calculatePosition", "Landroidx/compose/ui/unit/IntOffset;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "popupContentSize", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ExposedDropdownMenuPositionProvider implements PopupPositionProvider {
    public static final int $stable = 0;
    private final MenuPosition.Vertical bottomToAnchorTop;
    private final MenuPosition.Vertical bottomToWindowBottom;
    private final Density density;
    private final MenuPosition.Horizontal endToAnchorEnd;
    private final State<Unit> keyboardSignalState;
    private final MenuPosition.Horizontal leftToWindowLeft;
    private final Function2<IntRect, IntRect, Unit> onPositionCalculated;
    private final MenuPosition.Horizontal rightToWindowRight;
    private final MenuPosition.Horizontal startToAnchorStart;
    private final MenuPosition.Vertical topToAnchorBottom;
    private final MenuPosition.Vertical topToWindowTop;
    private final int topWindowInsets;
    private final int verticalMargin;

    /* JADX WARN: Multi-variable type inference failed */
    public ExposedDropdownMenuPositionProvider(Density density, int topWindowInsets, State<Unit> state, int verticalMargin, Function2<? super IntRect, ? super IntRect, Unit> function2) {
        this.density = density;
        this.topWindowInsets = topWindowInsets;
        this.keyboardSignalState = state;
        this.verticalMargin = verticalMargin;
        this.onPositionCalculated = function2;
        this.startToAnchorStart = MenuPosition.startToAnchorStart$default(MenuPosition.INSTANCE, 0, 1, null);
        this.endToAnchorEnd = MenuPosition.endToAnchorEnd$default(MenuPosition.INSTANCE, 0, 1, null);
        this.leftToWindowLeft = MenuPosition.leftToWindowLeft$default(MenuPosition.INSTANCE, 0, 1, null);
        this.rightToWindowRight = MenuPosition.rightToWindowRight$default(MenuPosition.INSTANCE, 0, 1, null);
        this.topToAnchorBottom = MenuPosition.topToAnchorBottom$default(MenuPosition.INSTANCE, 0, 1, null);
        this.bottomToAnchorTop = MenuPosition.bottomToAnchorTop$default(MenuPosition.INSTANCE, 0, 1, null);
        this.topToWindowTop = MenuPosition.INSTANCE.topToWindowTop(this.verticalMargin);
        this.bottomToWindowBottom = MenuPosition.INSTANCE.bottomToWindowBottom(this.verticalMargin);
    }

    public /* synthetic */ ExposedDropdownMenuPositionProvider(Density $this$_init__u24lambda_u240, int i, State state, int i2, Function2 function2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this($this$_init__u24lambda_u240, i, (i3 & 4) != 0 ? null : state, (i3 & 8) != 0 ? $this$_init__u24lambda_u240.mo426roundToPx0680j_4(MenuKt.getMenuVerticalMargin()) : i2, (i3 & 16) != 0 ? new Function2() { // from class: androidx.compose.material3.ExposedDropdownMenuPositionProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Unit.INSTANCE;
            }
        } : function2);
    }

    public final Density getDensity() {
        return this.density;
    }

    public final int getTopWindowInsets() {
        return this.topWindowInsets;
    }

    public final State<Unit> getKeyboardSignalState() {
        return this.keyboardSignalState;
    }

    public final int getVerticalMargin() {
        return this.verticalMargin;
    }

    public final Function2<IntRect, IntRect, Unit> getOnPositionCalculated() {
        return this.onPositionCalculated;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
    public long mo399calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        IntRect intRect;
        char c;
        char c2;
        char c3;
        long windowSize2;
        State<Unit> state = this.keyboardSignalState;
        if (state != null) {
            state.getValue();
        }
        int width$iv = (int) (windowSize >> 32);
        int height$iv = ((int) (windowSize & 4294967295L)) + this.topWindowInsets;
        long windowSize3 = IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
        MenuPosition.Horizontal[] horizontalArr = new MenuPosition.Horizontal[3];
        char c4 = 0;
        horizontalArr[0] = this.startToAnchorStart;
        char c5 = 1;
        horizontalArr[1] = this.endToAnchorEnd;
        int $i$f$unpackInt1 = (int) (windowSize3 >> 32);
        char c6 = 2;
        horizontalArr[2] = IntOffset.m8278getXimpl(anchorBounds.m8302getCenternOccac()) < $i$f$unpackInt1 / 2 ? this.leftToWindowLeft : this.rightToWindowRight;
        List xCandidates = CollectionsKt.listOf((Object[]) horizontalArr);
        int x = 0;
        int size = xCandidates.size();
        int index = 0;
        while (index < size) {
            c = c6;
            List xCandidates2 = xCandidates;
            intRect = anchorBounds;
            int xCandidate = ((MenuPosition.Horizontal) xCandidates.get(index)).mo3427position95KtPRI(intRect, windowSize3, (int) (popupContentSize >> 32), layoutDirection);
            if (index != CollectionsKt.getLastIndex(xCandidates2)) {
                if (xCandidate >= 0) {
                    c2 = c4;
                    c3 = c5;
                    if (((int) (popupContentSize >> 32)) + xCandidate <= ((int) (windowSize3 >> 32))) {
                    }
                } else {
                    c2 = c4;
                    c3 = c5;
                }
                index++;
                c4 = c2;
                c6 = c;
                xCandidates = xCandidates2;
                c5 = c3;
            } else {
                c2 = c4;
                c3 = c5;
            }
            x = xCandidate;
            break;
        }
        intRect = anchorBounds;
        c = c6;
        c2 = c4;
        c3 = c5;
        MenuPosition.Vertical[] verticalArr = new MenuPosition.Vertical[3];
        verticalArr[c2] = this.topToAnchorBottom;
        verticalArr[c3] = this.bottomToAnchorTop;
        verticalArr[c] = IntOffset.m8279getYimpl(intRect.m8302getCenternOccac()) < ((int) (windowSize3 & 4294967295L)) / 2 ? this.topToWindowTop : this.bottomToWindowBottom;
        List yCandidates = CollectionsKt.listOf((Object[]) verticalArr);
        int y = 0;
        int index2 = 0;
        int size2 = yCandidates.size();
        while (index2 < size2) {
            int yCandidate = ((MenuPosition.Vertical) yCandidates.get(index2)).mo3428positionJVtK1S4(intRect, windowSize3, (int) (popupContentSize & 4294967295L));
            if (index2 != CollectionsKt.getLastIndex(yCandidates)) {
                if (yCandidate >= 0) {
                    windowSize2 = windowSize3;
                    if (((int) (popupContentSize & 4294967295L)) + yCandidate <= ((int) (windowSize2 & 4294967295L))) {
                    }
                } else {
                    windowSize2 = windowSize3;
                }
                index2++;
                windowSize3 = windowSize2;
            }
            y = yCandidate;
            break;
        }
        long menuOffset = IntOffset.m8272constructorimpl((((long) x) << 32) | (((long) y) & 4294967295L));
        this.onPositionCalculated.invoke(intRect, IntRectKt.m8311IntRectVbeCjmY(menuOffset, popupContentSize));
        return menuOffset;
    }
}
