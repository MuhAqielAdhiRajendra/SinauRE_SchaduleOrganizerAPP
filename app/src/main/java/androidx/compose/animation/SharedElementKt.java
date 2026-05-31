package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SharedElement.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002\u001a\u0013\u0010\u0007\u001a\u00020\u0006*\u00020\bH\u0000¢\u0006\u0004\b\t\u0010\n\"\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"hasVisibleContent", "", "", "Landroidx/compose/animation/SharedElementEntry;", "DefaultMomentumSpring", "Landroidx/compose/animation/core/SpringSpec;", "Landroidx/compose/ui/geometry/Offset;", "toOffset", "Landroidx/compose/ui/unit/Velocity;", "toOffset-TH1AsA0", "(J)J", "animation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SharedElementKt {
    private static final SpringSpec<Offset> DefaultMomentumSpring;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasVisibleContent(List<SharedElementEntry> list) {
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            SharedElementEntry it = (SharedElementEntry) item$iv$iv;
            if (it.getBoundsAnimation().getTarget()) {
                return true;
            }
        }
        return false;
    }

    static {
        long v1$iv$iv = Float.floatToRawIntBits(3.0f);
        long v2$iv$iv = Float.floatToRawIntBits(3.0f);
        DefaultMomentumSpring = AnimationSpecKt.spring$default(0.0f, 400.0f, Offset.m5057boximpl(Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv))), 1, null);
    }

    /* JADX INFO: renamed from: toOffset-TH1AsA0, reason: not valid java name */
    public static final long m144toOffsetTH1AsA0(long $this$toOffset_u2dTH1AsA0) {
        float x$iv = Velocity.m8388getXimpl($this$toOffset_u2dTH1AsA0);
        float y$iv = Velocity.m8389getYimpl($this$toOffset_u2dTH1AsA0);
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }
}
