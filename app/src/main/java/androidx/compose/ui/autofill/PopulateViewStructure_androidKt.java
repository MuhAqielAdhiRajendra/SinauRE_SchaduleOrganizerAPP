package androidx.compose.ui.autofill;

import android.view.ViewStructure;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: PopulateViewStructure.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0001¨\u0006\u000b"}, d2 = {"populate", "", "Landroid/view/ViewStructure;", "semanticsInfo", "Landroidx/compose/ui/semantics/SemanticsInfo;", "rootAutofillId", "Landroid/view/autofill/AutofillId;", "packageName", "", "rectManager", "Landroidx/compose/ui/spatial/RectManager;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PopulateViewStructure_androidKt {
    /* JADX WARN: Removed duplicated region for block: B:412:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0451  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x04c3  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:466:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void populate(android.view.ViewStructure r50, androidx.compose.ui.semantics.SemanticsInfo r51, android.view.autofill.AutofillId r52, java.lang.String r53, androidx.compose.ui.spatial.RectManager r54) {
        /*
            Method dump skipped, instruction units count: 1289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.autofill.PopulateViewStructure_androidKt.populate(android.view.ViewStructure, androidx.compose.ui.semantics.SemanticsInfo, android.view.autofill.AutofillId, java.lang.String, androidx.compose.ui.spatial.RectManager):void");
    }

    /* JADX INFO: renamed from: androidx.compose.ui.autofill.PopulateViewStructure_androidKt$populate$7 */
    /* JADX INFO: compiled from: PopulateViewStructure.android.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "left", "", "top", "right", "bottom", "invoke"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass7 extends Lambda implements Function4<Integer, Integer, Integer, Integer, Unit> {
        final /* synthetic */ ViewStructure $this_populate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass7(ViewStructure viewStructure) {
            super(4);
            viewStructure = viewStructure;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, Integer num3, Integer num4) {
            invoke(num.intValue(), num2.intValue(), num3.intValue(), num4.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int left, int top, int right, int bottom) {
            autofillApi26Helper.setDimens(viewStructure, left, top, 0, 0, right - left, bottom - top);
        }
    }
}
