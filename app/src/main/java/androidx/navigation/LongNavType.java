package androidx.navigation;

import android.os.Bundle;
import androidx.autofill.HintConstants;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NavType.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J$\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\"\u0010\u0010\u001a\u00020\u00022\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0002\u0010\u0013R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Landroidx/navigation/LongNavType;", "Landroidx/navigation/NavType;", "", "<init>", "()V", HintConstants.AUTOFILL_HINT_NAME, "", "getName", "()Ljava/lang/String;", "put", "", "bundle", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "key", "value", "get", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Long;", "parseValue", "(Ljava/lang/String;)Ljava/lang/Long;", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LongNavType extends NavType<Long> {
    public LongNavType() {
        super(false);
    }

    @Override // androidx.navigation.NavType
    public /* bridge */ /* synthetic */ void put(Bundle bundle, String key, Long l) {
        put(bundle, key, l.longValue());
    }

    @Override // androidx.navigation.NavType
    public String getName() {
        return "long";
    }

    public void put(Bundle bundle, String key, long value) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle $this$put_u24lambda_u240 = SavedStateWriter.m8608constructorimpl(bundle);
        SavedStateWriter.m8629putLongimpl($this$put_u24lambda_u240, key, value);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.navigation.NavType
    public Long get(Bundle bundle, String key) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle $this$get_u24lambda_u241 = SavedStateReader.m8522constructorimpl(bundle);
        return Long.valueOf(SavedStateReader.m8563getLongimpl($this$get_u24lambda_u241, key));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.navigation.NavType
    public Long parseValue(String value) {
        long j;
        Intrinsics.checkNotNullParameter(value, "value");
        String localValue = value;
        if (StringsKt.endsWith$default(value, "L", false, 2, (Object) null)) {
            String strSubstring = localValue.substring(0, value.length() - 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            localValue = strSubstring;
        }
        if (StringsKt.startsWith$default(value, "0x", false, 2, (Object) null)) {
            String strSubstring2 = localValue.substring(2);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            j = Long.parseLong(strSubstring2, CharsKt.checkRadix(16));
        } else {
            j = Long.parseLong(localValue);
        }
        return Long.valueOf(j);
    }
}
