package com.google.android.material.resources;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import com.google.android.material.R;

/* JADX INFO: loaded from: classes13.dex */
public class MaterialAttributes {
    public static TypedValue resolve(Context context, int attributeResId) {
        return resolve(context.getTheme(), attributeResId);
    }

    public static TypedValue resolve(Resources.Theme theme, int attributeResId) {
        TypedValue typedValue = new TypedValue();
        if (theme.resolveAttribute(attributeResId, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static TypedValue resolveTypedValueOrThrow(View componentView, int attributeResId) {
        return resolveTypedValueOrThrow(componentView.getContext(), attributeResId, componentView.getClass().getCanonicalName());
    }

    public static TypedValue resolveTypedValueOrThrow(Context context, int attributeResId, String errorMessageComponent) {
        TypedValue typedValue = resolve(context, attributeResId);
        if (typedValue == null) {
            throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", errorMessageComponent, context.getResources().getResourceName(attributeResId)));
        }
        return typedValue;
    }

    public static int resolveOrThrow(Context context, int attributeResId, String errorMessageComponent) {
        return resolveTypedValueOrThrow(context, attributeResId, errorMessageComponent).data;
    }

    public static int resolveOrThrow(View componentView, int attributeResId) {
        return resolveTypedValueOrThrow(componentView, attributeResId).data;
    }

    public static boolean resolveBooleanOrThrow(Context context, int attributeResId, String errorMessageComponent) {
        return resolveOrThrow(context, attributeResId, errorMessageComponent) != 0;
    }

    public static boolean resolveBoolean(Context context, int attributeResId, boolean defaultValue) {
        return resolveBoolean(context.getTheme(), attributeResId, defaultValue);
    }

    public static boolean resolveBoolean(Resources.Theme theme, int attributeResId, boolean defaultValue) {
        TypedValue typedValue = resolve(theme, attributeResId);
        if (typedValue == null || typedValue.type != 18) {
            return defaultValue;
        }
        return typedValue.data != 0;
    }

    public static int resolveInteger(Context context, int attributeResId, int defaultValue) {
        return resolveInteger(context.getTheme(), attributeResId, defaultValue);
    }

    public static int resolveInteger(Resources.Theme theme, int attributeResId, int defaultValue) {
        TypedValue typedValue = resolve(theme, attributeResId);
        if (typedValue != null && typedValue.type == 16) {
            return typedValue.data;
        }
        return defaultValue;
    }

    public static int resolveMinimumAccessibleTouchTarget(Context context) {
        return resolveDimension(context, R.attr.minTouchTargetSize, R.dimen.mtrl_min_touch_target_size);
    }

    public static int resolveDimension(Context context, int attributeResId, int defaultDimenResId) {
        float dimensionValue = resolveDimension(context.getTheme(), attributeResId, Float.NaN);
        if (Float.isNaN(dimensionValue)) {
            return (int) context.getResources().getDimension(defaultDimenResId);
        }
        return (int) dimensionValue;
    }

    public static float resolveDimension(Resources.Theme theme, int attributeResId, float defaultValue) {
        TypedValue dimensionValue = resolve(theme, attributeResId);
        if (dimensionValue == null || dimensionValue.type != 5) {
            return defaultValue;
        }
        return dimensionValue.getDimension(theme.getResources().getDisplayMetrics());
    }
}
