package androidx.compose.foundation.text;

import androidx.compose.foundation.text.contextmenu.data.TextContextMenuKeys;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: CommonContextMenuArea.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u0015\u001a\u00020\u0016H\u0007¢\u0006\u0002\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/text/TextContextMenuItems;", "", "key", "", "stringId", "Landroidx/compose/foundation/text/ContextMenuStrings;", "drawableId", "Landroidx/compose/foundation/text/ContextMenuIcons;", "<init>", "(Ljava/lang/String;ILjava/lang/Object;II)V", "getKey", "()Ljava/lang/Object;", "getStringId-9Hzcbyc", "()I", "I", "getDrawableId-3I4p1mQ", "Cut", "Copy", "Paste", "SelectAll", "Autofill", "resolvedString", "", "(Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public enum TextContextMenuItems {
    Cut(TextContextMenuKeys.INSTANCE.getCutKey(), ContextMenuStrings.INSTANCE.m1530getCut9Hzcbyc(), ContextMenuIcons.INSTANCE.m1517getActionModeCutDrawable3I4p1mQ()),
    Copy(TextContextMenuKeys.INSTANCE.getCopyKey(), ContextMenuStrings.INSTANCE.m1529getCopy9Hzcbyc(), ContextMenuIcons.INSTANCE.m1516getActionModeCopyDrawable3I4p1mQ()),
    Paste(TextContextMenuKeys.INSTANCE.getPasteKey(), ContextMenuStrings.INSTANCE.m1531getPaste9Hzcbyc(), ContextMenuIcons.INSTANCE.m1518getActionModePasteDrawable3I4p1mQ()),
    SelectAll(TextContextMenuKeys.INSTANCE.getSelectAllKey(), ContextMenuStrings.INSTANCE.m1532getSelectAll9Hzcbyc(), ContextMenuIcons.INSTANCE.m1519getActionModeSelectAllDrawable3I4p1mQ()),
    Autofill(TextContextMenuKeys.INSTANCE.getAutofillKey(), ContextMenuStrings.INSTANCE.m1528getAutofill9Hzcbyc(), ContextMenuIcons.INSTANCE.m1520getID_NULL3I4p1mQ());

    private final int drawableId;
    private final Object key;
    private final int stringId;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<TextContextMenuItems> getEntries() {
        return $ENTRIES;
    }

    TextContextMenuItems(Object key, int stringId, int drawableId) {
        this.key = key;
        this.stringId = stringId;
        this.drawableId = drawableId;
    }

    public final Object getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: getStringId-9Hzcbyc, reason: not valid java name and from getter */
    public final int getStringId() {
        return this.stringId;
    }

    /* JADX INFO: renamed from: getDrawableId-3I4p1mQ, reason: not valid java name and from getter */
    public final int getDrawableId() {
        return this.drawableId;
    }

    public final String resolvedString(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 479426150, "C(resolvedString)178@7376L19:CommonContextMenuArea.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(479426150, $changed, -1, "androidx.compose.foundation.text.TextContextMenuItems.resolvedString (CommonContextMenuArea.kt:178)");
        }
        String strM1533getStringtk4Tqcs = ContextMenuStrings_androidKt.m1533getStringtk4Tqcs(this.stringId, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return strM1533getStringtk4Tqcs;
    }
}
