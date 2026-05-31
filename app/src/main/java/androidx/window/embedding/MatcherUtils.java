package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.autofill.HintConstants;
import androidx.window.core.ActivityComponentInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MatcherUtils.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\tJ\u001d\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\rJ\u001d\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0011J\u0018\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J\u001d\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u001aR\u000e\u0010\u001b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0014X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/window/embedding/MatcherUtils;", "", "<init>", "()V", "areComponentsMatching", "", "activityComponent", "Landroidx/window/core/ActivityComponentInfo;", "ruleComponent", "areComponentsMatching$window_release", "isActivityMatching", "activity", "Landroid/app/Activity;", "isActivityMatching$window_release", "isIntentMatching", "intent", "Landroid/content/Intent;", "isIntentMatching$window_release", "wildcardMatch", HintConstants.AUTOFILL_HINT_NAME, "", "pattern", "validateComponentName", "", "packageName", "className", "validateComponentName$window_release", "sDebugMatchers", "sMatchersTag", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MatcherUtils {
    public static final MatcherUtils INSTANCE = new MatcherUtils();
    public static final boolean sDebugMatchers = false;
    public static final String sMatchersTag = "SplitRuleResolution";

    private MatcherUtils() {
    }

    public final boolean areComponentsMatching$window_release(ActivityComponentInfo activityComponent, ActivityComponentInfo ruleComponent) {
        Intrinsics.checkNotNullParameter(ruleComponent, "ruleComponent");
        if (activityComponent == null) {
            return Intrinsics.areEqual(ruleComponent.getPackageName(), "*") && Intrinsics.areEqual(ruleComponent.getClassName(), "*");
        }
        if (StringsKt.contains$default((CharSequence) activityComponent.toString(), (CharSequence) "*", false, 2, (Object) null)) {
            throw new IllegalArgumentException("Wildcard can only be part of the rule.".toString());
        }
        boolean packagesMatch = Intrinsics.areEqual(activityComponent.getPackageName(), ruleComponent.getPackageName()) || wildcardMatch(activityComponent.getPackageName(), ruleComponent.getPackageName());
        boolean classesMatch = Intrinsics.areEqual(activityComponent.getClassName(), ruleComponent.getClassName()) || wildcardMatch(activityComponent.getClassName(), ruleComponent.getClassName());
        return packagesMatch && classesMatch;
    }

    public final boolean isActivityMatching$window_release(Activity activity, ActivityComponentInfo ruleComponent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(ruleComponent, "ruleComponent");
        ComponentName componentName = activity.getComponentName();
        Intrinsics.checkNotNullExpressionValue(componentName, "getComponentName(...)");
        if (areComponentsMatching$window_release(new ActivityComponentInfo(componentName), ruleComponent)) {
            return true;
        }
        Intent intent = activity.getIntent();
        if (intent != null) {
            return INSTANCE.isIntentMatching$window_release(intent, ruleComponent);
        }
        return false;
    }

    public final boolean isIntentMatching$window_release(Intent intent, ActivityComponentInfo ruleComponent) {
        String packageName;
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intrinsics.checkNotNullParameter(ruleComponent, "ruleComponent");
        ComponentName p0 = intent.getComponent();
        if (areComponentsMatching$window_release(p0 != null ? new ActivityComponentInfo(p0) : null, ruleComponent)) {
            return true;
        }
        if (intent.getComponent() == null && (packageName = intent.getPackage()) != null) {
            return (Intrinsics.areEqual(packageName, ruleComponent.getPackageName()) || wildcardMatch(packageName, ruleComponent.getPackageName())) && Intrinsics.areEqual(ruleComponent.getClassName(), "*");
        }
        return false;
    }

    private final boolean wildcardMatch(String name, String pattern) {
        if (!StringsKt.contains$default((CharSequence) pattern, (CharSequence) "*", false, 2, (Object) null)) {
            return false;
        }
        if (Intrinsics.areEqual(pattern, "*")) {
            return true;
        }
        if (!(StringsKt.indexOf$default((CharSequence) pattern, "*", 0, false, 6, (Object) null) == StringsKt.lastIndexOf$default((CharSequence) pattern, "*", 0, false, 6, (Object) null) && StringsKt.endsWith$default(pattern, "*", false, 2, (Object) null))) {
            throw new IllegalArgumentException("Name pattern with a wildcard must only contain a single wildcard in the end".toString());
        }
        String strSubstring = pattern.substring(0, pattern.length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return StringsKt.startsWith$default(name, strSubstring, false, 2, (Object) null);
    }

    public final void validateComponentName$window_release(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        boolean z = true;
        if (!(packageName.length() > 0)) {
            throw new IllegalArgumentException("Package name must not be empty".toString());
        }
        if (!(className.length() > 0)) {
            throw new IllegalArgumentException("Activity class name must not be empty".toString());
        }
        if (!(!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "*", false, 2, (Object) null) || StringsKt.indexOf$default((CharSequence) packageName, "*", 0, false, 6, (Object) null) == packageName.length() - 1)) {
            throw new IllegalArgumentException("Wildcard in package name is only allowed at the end.".toString());
        }
        if (StringsKt.contains$default((CharSequence) className, (CharSequence) "*", false, 2, (Object) null) && StringsKt.indexOf$default((CharSequence) className, "*", 0, false, 6, (Object) null) != className.length() - 1) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.".toString());
        }
    }
}
