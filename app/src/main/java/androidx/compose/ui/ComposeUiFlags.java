package androidx.compose.ui;

import kotlin.Metadata;

/* JADX INFO: compiled from: ComposeUiFlags.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/ComposeUiFlags;", "", "<init>", "()V", "isViewFocusFixEnabled", "", "isBypassUnfocusableComposeViewEnabled", "isInitialFocusOnFocusableAvailable", "isFocusRestorationEnabled", "isIndirectPointerNavigationGestureDetectorEnabled", "isOptimizedFocusEventDispatchEnabled", "isGraphicsLayerShapeSemanticsEnabled", "isTraversableDelegatesFixEnabled", "isAccessibilityShouldIncludeOffscreenChildrenEnabled", "isTrackpadGestureHandlingEnabled", "isMediaQueryIntegrationEnabled", "isSkipNonImportantSemanticsNodesHitTestEnabled", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ComposeUiFlags {
    public static boolean isFocusRestorationEnabled;
    public static boolean isInitialFocusOnFocusableAvailable;
    public static boolean isMediaQueryIntegrationEnabled;
    public static boolean isViewFocusFixEnabled;
    public static final ComposeUiFlags INSTANCE = new ComposeUiFlags();
    public static boolean isBypassUnfocusableComposeViewEnabled = true;
    public static boolean isIndirectPointerNavigationGestureDetectorEnabled = true;
    public static boolean isOptimizedFocusEventDispatchEnabled = true;
    public static boolean isGraphicsLayerShapeSemanticsEnabled = true;
    public static boolean isTraversableDelegatesFixEnabled = true;
    public static boolean isAccessibilityShouldIncludeOffscreenChildrenEnabled = true;
    public static boolean isTrackpadGestureHandlingEnabled = true;
    public static boolean isSkipNonImportantSemanticsNodesHitTestEnabled = true;
    public static final int $stable = 8;

    private ComposeUiFlags() {
    }
}
