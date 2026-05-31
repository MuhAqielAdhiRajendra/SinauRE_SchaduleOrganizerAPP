package androidx.compose.ui.contentcapture;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.LongSparseArray;
import android.view.View;
import android.view.autofill.AutofillId;
import android.view.translation.TranslationRequestValue;
import android.view.translation.TranslationResponseValue;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.AndroidComposeUiFlags;
import androidx.compose.ui.contentcapture.AndroidContentCaptureManager;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.compose.ui.platform.SemanticsNodeCopy;
import androidx.compose.ui.platform.SemanticsUtils_androidKt;
import androidx.compose.ui.platform.coreshims.AutofillIdCompat;
import androidx.compose.ui.platform.coreshims.ViewCompatShims;
import androidx.compose.ui.platform.coreshims.ViewStructureCompat;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeWithAdjustedBounds;
import androidx.compose.ui.semantics.SemanticsNode_androidKt;
import androidx.compose.ui.semantics.SemanticsOwnerKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: compiled from: AndroidContentCaptureManager.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \u0086\u00012\u00020\u00012\u00020\u0002:\u0006\u0084\u0001\u0085\u0001\u0086\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u00108\u001a\u00020\"2\u0006\u00109\u001a\u00020:H\u0016J\u0010\u0010;\u001a\u00020\"2\u0006\u00109\u001a\u00020:H\u0016J\u0010\u0010?\u001a\u00020\"2\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010B\u001a\u00020\"2\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010C\u001a\u00020\"H\u0080@¢\u0006\u0004\bD\u0010EJ\r\u0010F\u001a\u00020\"H\u0000¢\u0006\u0002\bGJ\r\u0010H\u001a\u00020\"H\u0000¢\u0006\u0002\bIJ\b\u0010J\u001a\u00020\"H\u0002J\u0018\u0010K\u001a\u00020\"2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u000203H\u0002J\u0016\u0010O\u001a\u00020\"2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002J\u0018\u0010Q\u001a\u00020\"2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020UH\u0002J\b\u0010V\u001a\u00020\"H\u0002J\b\u0010W\u001a\u00020\"H\u0002J\u0016\u0010X\u001a\u0004\u0018\u00010Y*\u00020M2\u0006\u0010Z\u001a\u00020SH\u0002J&\u0010[\u001a\u00020\"*\u00020M2\u0018\u0010\\\u001a\u0014\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\"0]H\u0002JG\u0010^\u001a\u00020\"\"\u0004\b\u0000\u0010_*\b\u0012\u0004\u0012\u0002H_0`2\u0018\u0010\\\u001a\u0014\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u0002H_\u0012\u0004\u0012\u00020\"0]2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u0002H_\u0012\u0004\u0012\u00020\u001f0bH\u0082\bJ\u001a\u0010c\u001a\u00020\"2\u0006\u0010d\u001a\u00020S2\b\u0010e\u001a\u0004\u0018\u00010YH\u0002J\u0010\u0010f\u001a\u00020\"2\u0006\u0010d\u001a\u00020SH\u0002J\b\u0010g\u001a\u00020\"H\u0002J\u0018\u0010h\u001a\u00020\"2\u0006\u0010Z\u001a\u00020S2\u0006\u0010i\u001a\u00020MH\u0002J\u0010\u0010j\u001a\u00020\"2\u0006\u0010i\u001a\u00020MH\u0002J\u0010\u0010k\u001a\u00020\"2\u0006\u0010i\u001a\u00020MH\u0002J\r\u0010l\u001a\u00020\"H\u0000¢\u0006\u0002\bmJ\r\u0010n\u001a\u00020\"H\u0000¢\u0006\u0002\boJ\r\u0010p\u001a\u00020\"H\u0000¢\u0006\u0002\bqJ\b\u0010r\u001a\u00020\"H\u0002J\b\u0010s\u001a\u00020\"H\u0002J\b\u0010t\u001a\u00020\"H\u0002J-\u0010u\u001a\u00020\"2\u0006\u0010v\u001a\u00020w2\u0006\u0010x\u001a\u00020y2\u000e\u0010z\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010|0{H\u0001¢\u0006\u0002\b}J)\u0010~\u001a\u00020\"2\u0006\u0010\u007f\u001a\u00020\u00002\u0011\u0010\u0080\u0001\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010\u0082\u00010\u0081\u0001H\u0001¢\u0006\u0003\b\u0083\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\"\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR&\u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010%\u001a\u0004\u0018\u00010$8@X\u0080\u0004¢\u0006\f\u0012\u0004\b&\u0010\u0012\u001a\u0004\b'\u0010(R\"\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*8@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020302X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010<\u001a\u00020\u001f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>¨\u0006\u0087\u0001"}, d2 = {"Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroid/view/View$OnAttachStateChangeListener;", "view", "Landroidx/compose/ui/platform/AndroidComposeView;", "onContentCaptureSession", "Lkotlin/Function0;", "Landroidx/compose/ui/contentcapture/ContentCaptureSessionWrapper;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;Lkotlin/jvm/functions/Function0;)V", "getView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "getOnContentCaptureSession", "()Lkotlin/jvm/functions/Function0;", "setOnContentCaptureSession", "(Lkotlin/jvm/functions/Function0;)V", "contentCaptureSession", "getContentCaptureSession$ui$annotations", "()V", "getContentCaptureSession$ui", "()Landroidx/compose/ui/contentcapture/ContentCaptureSessionWrapper;", "setContentCaptureSession$ui", "(Landroidx/compose/ui/contentcapture/ContentCaptureSessionWrapper;)V", "bufferedEvents", "", "Landroidx/compose/ui/contentcapture/ContentCaptureEvent;", "SendRecurringContentCaptureEventsIntervalMillis", "", "translateStatus", "Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager$TranslateStatus;", "currentSemanticsNodesInvalidated", "", "boundsUpdateChannel", "Lkotlinx/coroutines/channels/Channel;", "", "legacyMainHandler", "Landroid/os/Handler;", "handler", "getHandler$ui$annotations", "getHandler$ui", "()Landroid/os/Handler;", "currentSemanticsNodes", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/semantics/SemanticsNodeWithAdjustedBounds;", "getCurrentSemanticsNodes$ui", "()Landroidx/collection/IntObjectMap;", "setCurrentSemanticsNodes$ui", "(Landroidx/collection/IntObjectMap;)V", "currentSemanticsNodesSnapshotTimestampMillis", "previousSemanticsNodes", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/ui/platform/SemanticsNodeCopy;", "previousSemanticsRoot", "checkingForSemanticsChanges", "contentCaptureChangeChecker", "Ljava/lang/Runnable;", "onViewAttachedToWindow", "v", "Landroid/view/View;", "onViewDetachedFromWindow", "isEnabled", "isEnabled$ui", "()Z", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "boundsUpdatesEventLoop", "boundsUpdatesEventLoop$ui", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSemanticsChange", "onSemanticsChange$ui", "onLayoutChange", "onLayoutChange$ui", "sendContentCaptureDisappearEvents", "sendContentCaptureAppearEvents", "newNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "oldNode", "checkForContentCapturePropertyChanges", "newSemanticsNodes", "sendContentCaptureTextUpdateEvent", "id", "", "newText", "", "updateSemanticsCopy", "notifySubtreeStateChangeIfNeeded", "toViewStructure", "Landroidx/compose/ui/platform/coreshims/ViewStructureCompat;", "index", "fastForEachReplacedVisibleChildren", "action", "Lkotlin/Function2;", "fastForEachIndexedWithFilter", "T", "", "predicate", "Lkotlin/Function1;", "bufferContentCaptureViewAppeared", "virtualId", "viewStructure", "bufferContentCaptureViewDisappeared", "notifyContentCaptureChanges", "updateBuffersOnAppeared", "node", "updateBuffersOnDisappeared", "updateTranslationOnAppeared", "onShowTranslation", "onShowTranslation$ui", "onHideTranslation", "onHideTranslation$ui", "onClearTranslation", "onClearTranslation$ui", "showTranslatedText", "hideTranslatedText", "clearTranslatedText", "onCreateVirtualViewTranslationRequests", "virtualIds", "", "supportedFormats", "", "requestsCollector", "Ljava/util/function/Consumer;", "Landroid/view/translation/ViewTranslationRequest;", "onCreateVirtualViewTranslationRequests$ui", "onVirtualViewTranslationResponses", "contentCaptureManager", "response", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "onVirtualViewTranslationResponses$ui", "TranslateStatus", "ViewTranslationHelperMethods", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidContentCaptureManager implements DefaultLifecycleObserver, View.OnAttachStateChangeListener {
    public static final String VIEW_STRUCTURE_BUNDLE_KEY_ADDITIONAL_INDEX = "android.view.ViewStructure.extra.EXTRA_VIEW_NODE_INDEX";
    public static final String VIEW_STRUCTURE_BUNDLE_KEY_TIMESTAMP = "android.view.contentcapture.EventTimestamp";
    private boolean checkingForSemanticsChanges;
    private ContentCaptureSessionWrapper contentCaptureSession;
    private long currentSemanticsNodesSnapshotTimestampMillis;
    private Function0<? extends ContentCaptureSessionWrapper> onContentCaptureSession;
    private SemanticsNodeCopy previousSemanticsRoot;
    private final AndroidComposeView view;
    public static final int $stable = 8;
    private final List<ContentCaptureEvent> bufferedEvents = new ArrayList();
    private long SendRecurringContentCaptureEventsIntervalMillis = 100;
    private TranslateStatus translateStatus = TranslateStatus.SHOW_ORIGINAL;
    private boolean currentSemanticsNodesInvalidated = true;
    private final Channel<Unit> boundsUpdateChannel = ChannelKt.Channel$default(1, null, null, 6, null);
    private final Handler legacyMainHandler = new Handler(Looper.getMainLooper());
    private IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes = IntObjectMapKt.intObjectMapOf();
    private MutableIntObjectMap<SemanticsNodeCopy> previousSemanticsNodes = IntObjectMapKt.mutableIntObjectMapOf();
    private final Runnable contentCaptureChangeChecker = new Runnable() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            AndroidContentCaptureManager.contentCaptureChangeChecker$lambda$0(this.f$0);
        }
    };

    /* JADX INFO: compiled from: AndroidContentCaptureManager.android.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager$TranslateStatus;", "", "<init>", "(Ljava/lang/String;I)V", "SHOW_ORIGINAL", "SHOW_TRANSLATED", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private enum TranslateStatus {
        SHOW_ORIGINAL,
        SHOW_TRANSLATED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<TranslateStatus> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: AndroidContentCaptureManager.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContentCaptureEventType.values().length];
            try {
                iArr[ContentCaptureEventType.VIEW_APPEAR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ContentCaptureEventType.VIEW_DISAPPEAR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void getContentCaptureSession$ui$annotations() {
    }

    public static /* synthetic */ void getHandler$ui$annotations() {
    }

    public AndroidContentCaptureManager(AndroidComposeView view, Function0<? extends ContentCaptureSessionWrapper> function0) {
        this.view = view;
        this.onContentCaptureSession = function0;
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), IntObjectMapKt.intObjectMapOf());
    }

    public final AndroidComposeView getView() {
        return this.view;
    }

    public final Function0<ContentCaptureSessionWrapper> getOnContentCaptureSession() {
        return this.onContentCaptureSession;
    }

    public final void setOnContentCaptureSession(Function0<? extends ContentCaptureSessionWrapper> function0) {
        this.onContentCaptureSession = function0;
    }

    /* JADX INFO: renamed from: getContentCaptureSession$ui, reason: from getter */
    public final ContentCaptureSessionWrapper getContentCaptureSession() {
        return this.contentCaptureSession;
    }

    public final void setContentCaptureSession$ui(ContentCaptureSessionWrapper contentCaptureSessionWrapper) {
        this.contentCaptureSession = contentCaptureSessionWrapper;
    }

    public final Handler getHandler$ui() {
        if (AndroidComposeUiFlags.isViewBasedSemanticsHandlerEnabled) {
            return this.view.getHandler();
        }
        return this.legacyMainHandler;
    }

    public final void setCurrentSemanticsNodes$ui(IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap) {
        this.currentSemanticsNodes = intObjectMap;
    }

    public final IntObjectMap<SemanticsNodeWithAdjustedBounds> getCurrentSemanticsNodes$ui() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = SemanticsOwnerKt.getAllUncoveredSemanticsNodesToIntObjectMap(this.view.getSemanticsOwner(), -1, new Function1<SemanticsNode, Boolean>() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager$currentSemanticsNodes$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(SemanticsNode it) {
                    return Boolean.valueOf(SemanticsNode_androidKt.isAccessibilityIgnoredLink(it));
                }
            });
            this.currentSemanticsNodesSnapshotTimestampMillis = System.currentTimeMillis();
        }
        return this.currentSemanticsNodes;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0056, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
    
        throw r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final void contentCaptureChangeChecker$lambda$0(androidx.compose.ui.contentcapture.AndroidContentCaptureManager r9) {
        /*
            boolean r0 = r9.isEnabled$ui()
            if (r0 != 0) goto L7
            return
        L7:
            java.lang.String r0 = "ContentCapture:changeChecker"
            r1 = 0
            android.os.Trace.beginSection(r0)
            r2 = 0
            androidx.compose.ui.platform.AndroidComposeView r3 = r9.view     // Catch: java.lang.Throwable -> L56
            androidx.compose.ui.node.Owner r3 = (androidx.compose.ui.node.Owner) r3     // Catch: java.lang.Throwable -> L56
            r4 = 1
            r5 = 0
            r6 = 0
            androidx.compose.ui.node.Owner.measureAndLayout$default(r3, r6, r4, r5)     // Catch: java.lang.Throwable -> L56
            r9.sendContentCaptureDisappearEvents()     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = "ContentCapture:sendAppearEvents"
            r4 = 0
            android.os.Trace.beginSection(r3)     // Catch: java.lang.Throwable -> L56
            r5 = 0
            androidx.compose.ui.platform.AndroidComposeView r7 = r9.view     // Catch: java.lang.Throwable -> L51
            androidx.compose.ui.semantics.SemanticsOwner r7 = r7.getSemanticsOwner()     // Catch: java.lang.Throwable -> L51
            androidx.compose.ui.semantics.SemanticsNode r7 = r7.getUnmergedRootSemanticsNode()     // Catch: java.lang.Throwable -> L51
            androidx.compose.ui.platform.SemanticsNodeCopy r8 = r9.previousSemanticsRoot     // Catch: java.lang.Throwable -> L51
            r9.sendContentCaptureAppearEvents(r7, r8)     // Catch: java.lang.Throwable -> L51
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L51
            android.os.Trace.endSection()     // Catch: java.lang.Throwable -> L56
            androidx.collection.IntObjectMap r3 = r9.getCurrentSemanticsNodes$ui()     // Catch: java.lang.Throwable -> L56
            r9.checkForContentCapturePropertyChanges(r3)     // Catch: java.lang.Throwable -> L56
            r9.updateSemanticsCopy()     // Catch: java.lang.Throwable -> L56
            r9.checkingForSemanticsChanges = r6     // Catch: java.lang.Throwable -> L56
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L56
            android.os.Trace.endSection()
            return
        L51:
            r5 = move-exception
            android.os.Trace.endSection()     // Catch: java.lang.Throwable -> L56
            throw r5     // Catch: java.lang.Throwable -> L56
        L56:
            r2 = move-exception
            android.os.Trace.endSection()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.contentCaptureChangeChecker$lambda$0(androidx.compose.ui.contentcapture.AndroidContentCaptureManager):void");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View v) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View v) {
        Handler handler$ui = getHandler$ui();
        Intrinsics.checkNotNull(handler$ui);
        handler$ui.removeCallbacks(this.contentCaptureChangeChecker);
        this.contentCaptureSession = null;
    }

    public final boolean isEnabled$ui() {
        return ContentCaptureManager.INSTANCE.isEnabled() && this.contentCaptureSession != null;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(LifecycleOwner owner) {
        this.contentCaptureSession = this.onContentCaptureSession.invoke();
        updateBuffersOnAppeared(-1, this.view.getSemanticsOwner().getUnmergedRootSemanticsNode());
        notifyContentCaptureChanges();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        updateBuffersOnDisappeared(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode());
        notifyContentCaptureChanges();
        this.contentCaptureSession = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0092 -> B:15:0x004e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object boundsUpdatesEventLoop$ui(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1 r0 = (androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1 r0 = new androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1
            r0.<init>(r10, r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            switch(r3) {
                case 0: goto L44;
                case 1: goto L37;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2d:
            r3 = r10
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            kotlin.ResultKt.throwOnFailure(r1)
            goto L96
        L37:
            r3 = r10
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            kotlin.ResultKt.throwOnFailure(r1)
            r6 = r5
            r5 = r3
            r3 = r2
            r2 = r1
            goto L5f
        L44:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r10
            kotlinx.coroutines.channels.Channel<kotlin.Unit> r5 = r3.boundsUpdateChannel
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
        L4e:
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.hasNext(r0)
            if (r6 != r2) goto L59
            return r2
        L59:
            r9 = r2
            r2 = r1
            r1 = r6
            r6 = r5
            r5 = r3
            r3 = r9
        L5f:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L97
            r6.next()
            boolean r1 = r5.isEnabled$ui()
            if (r1 == 0) goto L73
            r5.notifyContentCaptureChanges()
        L73:
            android.os.Handler r1 = r5.getHandler$ui()
            boolean r7 = r5.checkingForSemanticsChanges
            if (r7 != 0) goto L84
            if (r1 == 0) goto L84
            r5.checkingForSemanticsChanges = r4
            java.lang.Runnable r7 = r5.contentCaptureChangeChecker
            r1.post(r7)
        L84:
            long r7 = r5.SendRecurringContentCaptureEventsIntervalMillis
            r0.L$0 = r6
            r1 = 2
            r0.label = r1
            java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r7, r0)
            if (r1 != r3) goto L92
            return r3
        L92:
            r1 = r2
            r2 = r3
            r3 = r5
            r5 = r6
        L96:
            goto L4e
        L97:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.boundsUpdatesEventLoop$ui(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onSemanticsChange$ui() {
        this.currentSemanticsNodesInvalidated = true;
        Handler localHandler = getHandler$ui();
        if (isEnabled$ui() && !this.checkingForSemanticsChanges && localHandler != null) {
            this.checkingForSemanticsChanges = true;
            localHandler.post(this.contentCaptureChangeChecker);
        }
    }

    public final void onLayoutChange$ui() {
        this.currentSemanticsNodesInvalidated = true;
        if (isEnabled$ui()) {
            notifySubtreeStateChangeIfNeeded();
        }
    }

    private final void sendContentCaptureDisappearEvents() {
        IntObjectMap this_$iv;
        int $i$f$forEachKey;
        IntObjectMap this_$iv2;
        int $i$f$forEachKey2;
        int i;
        IntObjectMap this_$iv3 = this.previousSemanticsNodes;
        int $i$f$forEachKey3 = 0;
        int[] k$iv = this_$iv3.keys;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                this_$iv = this_$iv3;
                $i$f$forEachKey = $i$f$forEachKey3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        this_$iv2 = this_$iv3;
                        $i$f$forEachKey2 = $i$f$forEachKey3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        int key = k$iv[index$iv$iv];
                        this_$iv2 = this_$iv3;
                        $i$f$forEachKey2 = $i$f$forEachKey3;
                        if (!getCurrentSemanticsNodes$ui().containsKey(key)) {
                            bufferContentCaptureViewDisappeared(key);
                            notifySubtreeStateChangeIfNeeded();
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    this_$iv3 = this_$iv2;
                    $i$f$forEachKey3 = $i$f$forEachKey2;
                }
                this_$iv = this_$iv3;
                $i$f$forEachKey = $i$f$forEachKey3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv3 = this_$iv;
            $i$f$forEachKey3 = $i$f$forEachKey;
        }
    }

    private final void sendContentCaptureAppearEvents(SemanticsNode newNode, final SemanticsNodeCopy oldNode) {
        fastForEachReplacedVisibleChildren(newNode, new Function2<Integer, SemanticsNode, Unit>() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.sendContentCaptureAppearEvents.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, SemanticsNode semanticsNode) {
                invoke(num.intValue(), semanticsNode);
                return Unit.INSTANCE;
            }

            public final void invoke(int index, SemanticsNode child) {
                if (!oldNode.getChildren().contains(child.getId())) {
                    this.updateBuffersOnAppeared(index, child);
                    this.notifySubtreeStateChangeIfNeeded();
                }
            }
        });
        List<SemanticsNode> replacedChildren$ui = newNode.getReplacedChildren$ui();
        int size = replacedChildren$ui.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = replacedChildren$ui.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
            int key$iv = child.getId();
            if (currentSemanticsNodes$ui.containsKey(key$iv)) {
                IntObjectMap this_$iv = this.previousSemanticsNodes;
                int key$iv2 = child.getId();
                if (this_$iv.containsKey(key$iv2)) {
                    Object value$iv = this.previousSemanticsNodes.get(child.getId());
                    if (value$iv != null) {
                        SemanticsNodeCopy prevNodeCopy = (SemanticsNodeCopy) value$iv;
                        sendContentCaptureAppearEvents(child, prevNodeCopy);
                    } else {
                        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("node not present in pruned tree before this change");
                        throw new KotlinNothingValueException();
                    }
                } else {
                    continue;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0132 A[EDGE_INSN: B:44:0x0132->B:107:0x026b BREAK  A[LOOP:2: B:22:0x00a4->B:45:0x0134]] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0236 A[EDGE_INSN: B:80:0x0236->B:110:0x026b BREAK  A[LOOP:4: B:51:0x0173->B:81:0x0238]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void checkForContentCapturePropertyChanges(androidx.collection.IntObjectMap<androidx.compose.ui.semantics.SemanticsNodeWithAdjustedBounds> r53) {
        /*
            Method dump skipped, instruction units count: 700
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.checkForContentCapturePropertyChanges(androidx.collection.IntObjectMap):void");
    }

    private final void sendContentCaptureTextUpdateEvent(int id, String newText) {
        ContentCaptureSessionWrapper session;
        if (Build.VERSION.SDK_INT >= 29 && (session = this.contentCaptureSession) != null) {
            AutofillId autofillId = session.newAutofillId(id);
            if (autofillId != null) {
                session.notifyViewTextChanged(autofillId, newText);
            } else {
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid content capture ID");
                throw new KotlinNothingValueException();
            }
        }
    }

    private final void updateSemanticsCopy() {
        int[] k$iv;
        Object[] v$iv;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap;
        int $i$f$forEachIndexed;
        int i;
        int[] k$iv2;
        Object[] v$iv2;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap2;
        int $i$f$forEachIndexed2;
        this.previousSemanticsNodes.clear();
        IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
        int $i$f$forEach = 0;
        int[] k$iv3 = currentSemanticsNodes$ui.keys;
        Object[] v$iv3 = currentSemanticsNodes$ui.values;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap3 = currentSemanticsNodes$ui;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv = intObjectMap3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap4 = currentSemanticsNodes$ui;
                int $i$f$forEach2 = $i$f$forEach;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    intObjectMap = intObjectMap3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            i = i2;
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                            intObjectMap2 = intObjectMap3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            int key = k$iv3[index$iv$iv];
                            SemanticsNodeWithAdjustedBounds value = (SemanticsNodeWithAdjustedBounds) v$iv3[index$iv$iv];
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                            intObjectMap2 = intObjectMap3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            this.previousSemanticsNodes.set(key, new SemanticsNodeCopy(value.getSemanticsNode(), getCurrentSemanticsNodes$ui()));
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        k$iv3 = k$iv2;
                        v$iv3 = v$iv2;
                        intObjectMap3 = intObjectMap2;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                    }
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    intObjectMap = intObjectMap3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                currentSemanticsNodes$ui = intObjectMap4;
                $i$f$forEach = $i$f$forEach2;
                k$iv3 = k$iv;
                v$iv3 = v$iv;
                intObjectMap3 = intObjectMap;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
            }
        }
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), getCurrentSemanticsNodes$ui());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySubtreeStateChangeIfNeeded() {
        this.boundsUpdateChannel.mo10436trySendJP2dKIU(Unit.INSTANCE);
    }

    private final ViewStructureCompat toViewStructure(SemanticsNode $this$toViewStructure, int index) {
        AutofillIdCompat rootAutofillId;
        AutofillId parentAutofillId;
        String it;
        ContentCaptureSessionWrapper session = this.contentCaptureSession;
        if (session == null || Build.VERSION.SDK_INT < 29 || (rootAutofillId = ViewCompatShims.getAutofillId(this.view)) == null) {
            return null;
        }
        SemanticsNode parentNode = $this$toViewStructure.getParent();
        if (parentNode != null) {
            parentAutofillId = session.newAutofillId(parentNode.getId());
            if (parentAutofillId == null) {
                return null;
            }
        } else {
            parentAutofillId = rootAutofillId.toAutofillId();
        }
        ViewStructureCompat structure = session.newVirtualViewStructure(parentAutofillId, $this$toViewStructure.getId());
        if (structure == null) {
            return null;
        }
        SemanticsConfiguration configuration = $this$toViewStructure.getUnmergedConfig();
        if (configuration.contains(SemanticsProperties.INSTANCE.getPassword())) {
            return null;
        }
        Bundle it2 = structure.getExtras();
        if (it2 != null) {
            it2.putLong(VIEW_STRUCTURE_BUNDLE_KEY_TIMESTAMP, this.currentSemanticsNodesSnapshotTimestampMillis);
            it2.putInt(VIEW_STRUCTURE_BUNDLE_KEY_ADDITIONAL_INDEX, index);
        }
        String it3 = (String) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getTestTag());
        if (it3 != null) {
            structure.setId($this$toViewStructure.getId(), null, null, it3);
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getIsTraversalGroup());
        if (bool != null) {
            bool.booleanValue();
            structure.setClassName("android.widget.ViewGroup");
        }
        List it4 = (List) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getText());
        if (it4 != null) {
            structure.setClassName(AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
            structure.setText(ListUtilsKt.fastJoinToString$default(it4, "\n", null, null, 0, null, null, 62, null));
        }
        AnnotatedString it5 = (AnnotatedString) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getEditableText());
        if (it5 != null) {
            structure.setClassName(AndroidComposeViewAccessibilityDelegateCompat.TextFieldClassName);
            structure.setText(it5);
        }
        List it6 = (List) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getContentDescription());
        if (it6 != null) {
            structure.setContentDescription(ListUtilsKt.fastJoinToString$default(it6, "\n", null, null, 0, null, null, 62, null));
        }
        Role role = (Role) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getRole());
        if (role != null && (it = SemanticsUtils_androidKt.m7319toLegacyClassNameV4PA4sw(role.getValue())) != null) {
            structure.setClassName(it);
        }
        TextLayoutResult it7 = SemanticsUtils_androidKt.getTextLayoutResult(configuration);
        if (it7 != null) {
            TextLayoutInput input = it7.getLayoutInput();
            float px = TextUnit.m8344getValueimpl(input.getStyle().m7604getFontSizeXSAIIZE()) * input.getDensity().getDensity() * input.getDensity().getFontScale();
            structure.setTextStyle(px, 0, 0, 0);
        }
        Rect $this$toViewStructure_u24lambda_u248 = $this$toViewStructure.getBoundsInParent$ui();
        structure.setDimens((int) $this$toViewStructure_u24lambda_u248.getLeft(), (int) $this$toViewStructure_u24lambda_u248.getTop(), 0, 0, (int) ($this$toViewStructure_u24lambda_u248.getRight() - $this$toViewStructure_u24lambda_u248.getLeft()), (int) ($this$toViewStructure_u24lambda_u248.getBottom() - $this$toViewStructure_u24lambda_u248.getTop()));
        return structure;
    }

    private final void fastForEachReplacedVisibleChildren(SemanticsNode $this$fastForEachReplacedVisibleChildren, Function2<? super Integer, ? super SemanticsNode, Unit> function2) {
        List<SemanticsNode> replacedChildren$ui = $this$fastForEachReplacedVisibleChildren.getReplacedChildren$ui();
        int i$iv = 0;
        int size = replacedChildren$ui.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = replacedChildren$ui.get(index$iv);
            SemanticsNode it = (SemanticsNode) item$iv;
            IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
            int key$iv = it.getId();
            if (currentSemanticsNodes$ui.containsKey(key$iv)) {
                function2.invoke(Integer.valueOf(i$iv), item$iv);
                i$iv++;
            }
        }
    }

    private final <T> void fastForEachIndexedWithFilter(List<? extends T> list, Function2<? super Integer, ? super T, Unit> function2, Function1<? super T, Boolean> function1) {
        int i = 0;
        int size = list.size();
        for (int index = 0; index < size; index++) {
            Object item = list.get(index);
            if (function1.invoke(item).booleanValue()) {
                function2.invoke(Integer.valueOf(i), item);
                i++;
            }
        }
    }

    private final void bufferContentCaptureViewAppeared(int virtualId, ViewStructureCompat viewStructure) {
        if (viewStructure == null) {
            return;
        }
        this.bufferedEvents.add(new ContentCaptureEvent(virtualId, this.currentSemanticsNodesSnapshotTimestampMillis, ContentCaptureEventType.VIEW_APPEAR, viewStructure));
    }

    private final void bufferContentCaptureViewDisappeared(int virtualId) {
        this.bufferedEvents.add(new ContentCaptureEvent(virtualId, this.currentSemanticsNodesSnapshotTimestampMillis, ContentCaptureEventType.VIEW_DISAPPEAR, null));
    }

    private final void notifyContentCaptureChanges() {
        ContentCaptureSessionWrapper session = this.contentCaptureSession;
        if (session != null && Build.VERSION.SDK_INT >= 29 && !this.bufferedEvents.isEmpty()) {
            List<ContentCaptureEvent> list = this.bufferedEvents;
            int size = list.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = list.get(index$iv);
                ContentCaptureEvent event = (ContentCaptureEvent) item$iv;
                switch (WhenMappings.$EnumSwitchMapping$0[event.getType().ordinal()]) {
                    case 1:
                        ViewStructureCompat node = event.getStructureCompat();
                        if (node != null) {
                            session.notifyViewAppeared(node.toViewStructure());
                        }
                        break;
                    case 2:
                        AutofillId autofillId = session.newAutofillId(event.getId());
                        if (autofillId != null) {
                            session.notifyViewDisappeared(autofillId);
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
            session.flush();
            this.bufferedEvents.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateBuffersOnAppeared(int index, SemanticsNode node) {
        if (!isEnabled$ui()) {
            return;
        }
        updateTranslationOnAppeared(node);
        bufferContentCaptureViewAppeared(node.getId(), toViewStructure(node, index));
        fastForEachReplacedVisibleChildren(node, new Function2<Integer, SemanticsNode, Unit>() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.updateBuffersOnAppeared.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, SemanticsNode semanticsNode) {
                invoke(num.intValue(), semanticsNode);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, SemanticsNode child) {
                AndroidContentCaptureManager.this.updateBuffersOnAppeared(i, child);
            }
        });
    }

    private final void updateBuffersOnDisappeared(SemanticsNode node) {
        if (!isEnabled$ui()) {
            return;
        }
        bufferContentCaptureViewDisappeared(node.getId());
        List<SemanticsNode> replacedChildren$ui = node.getReplacedChildren$ui();
        int size = replacedChildren$ui.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = replacedChildren$ui.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            updateBuffersOnDisappeared(child);
        }
    }

    private final void updateTranslationOnAppeared(SemanticsNode node) {
        AccessibilityAction accessibilityAction;
        Function1 function1;
        Function1 function12;
        SemanticsConfiguration config = node.getUnmergedConfig();
        Boolean isShowingTextSubstitution = (Boolean) SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution());
        if (this.translateStatus == TranslateStatus.SHOW_ORIGINAL && Intrinsics.areEqual((Object) isShowingTextSubstitution, (Object) true)) {
            AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getShowTextSubstitution());
            if (accessibilityAction2 != null && (function12 = (Function1) accessibilityAction2.getAction()) != null) {
                return;
            }
            return;
        }
        if (this.translateStatus == TranslateStatus.SHOW_TRANSLATED && Intrinsics.areEqual((Object) isShowingTextSubstitution, (Object) false) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (function1 = (Function1) accessibilityAction.getAction()) != null) {
        }
    }

    public final void onShowTranslation$ui() {
        this.translateStatus = TranslateStatus.SHOW_TRANSLATED;
        showTranslatedText();
    }

    public final void onHideTranslation$ui() {
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        hideTranslatedText();
    }

    public final void onClearTranslation$ui() {
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        clearTranslatedText();
    }

    private final void showTranslatedText() {
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap;
        int $i$f$forEachValue;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap2;
        int $i$f$forEachValue2;
        int i;
        AccessibilityAction accessibilityAction;
        Function1 function1;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
        int $i$f$forEachValue3 = 0;
        Object[] v$iv = currentSemanticsNodes$ui.values;
        long[] m$iv$iv = currentSemanticsNodes$ui.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                intObjectMap = currentSemanticsNodes$ui;
                $i$f$forEachValue = $i$f$forEachValue3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        intObjectMap2 = currentSemanticsNodes$ui;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        SemanticsNodeWithAdjustedBounds node = (SemanticsNodeWithAdjustedBounds) v$iv[index$iv$iv];
                        i = i2;
                        SemanticsConfiguration config = node.getSemanticsNode().getUnmergedConfig();
                        intObjectMap2 = currentSemanticsNodes$ui;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()), (Object) false) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (function1 = (Function1) accessibilityAction.getAction()) != null) {
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    $i$f$forEachValue3 = $i$f$forEachValue2;
                    i2 = i;
                    currentSemanticsNodes$ui = intObjectMap2;
                }
                intObjectMap = currentSemanticsNodes$ui;
                $i$f$forEachValue = $i$f$forEachValue3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            $i$f$forEachValue3 = $i$f$forEachValue;
            currentSemanticsNodes$ui = intObjectMap;
        }
    }

    private final void hideTranslatedText() {
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap;
        int $i$f$forEachValue;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap2;
        int $i$f$forEachValue2;
        int i;
        AccessibilityAction accessibilityAction;
        Function1 function1;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
        int $i$f$forEachValue3 = 0;
        Object[] v$iv = currentSemanticsNodes$ui.values;
        long[] m$iv$iv = currentSemanticsNodes$ui.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                intObjectMap = currentSemanticsNodes$ui;
                $i$f$forEachValue = $i$f$forEachValue3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        intObjectMap2 = currentSemanticsNodes$ui;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        SemanticsNodeWithAdjustedBounds node = (SemanticsNodeWithAdjustedBounds) v$iv[index$iv$iv];
                        i = i2;
                        SemanticsConfiguration config = node.getSemanticsNode().getUnmergedConfig();
                        intObjectMap2 = currentSemanticsNodes$ui;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()), (Object) true) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (function1 = (Function1) accessibilityAction.getAction()) != null) {
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    $i$f$forEachValue3 = $i$f$forEachValue2;
                    i2 = i;
                    currentSemanticsNodes$ui = intObjectMap2;
                }
                intObjectMap = currentSemanticsNodes$ui;
                $i$f$forEachValue = $i$f$forEachValue3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            $i$f$forEachValue3 = $i$f$forEachValue;
            currentSemanticsNodes$ui = intObjectMap;
        }
    }

    private final void clearTranslatedText() {
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap2;
        int i;
        AccessibilityAction accessibilityAction;
        Function0 function0;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
        Object[] v$iv = currentSemanticsNodes$ui.values;
        long[] m$iv$iv = currentSemanticsNodes$ui.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                intObjectMap = currentSemanticsNodes$ui;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        intObjectMap2 = currentSemanticsNodes$ui;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        SemanticsNodeWithAdjustedBounds node = (SemanticsNodeWithAdjustedBounds) v$iv[index$iv$iv];
                        i = i2;
                        SemanticsConfiguration config = node.getSemanticsNode().getUnmergedConfig();
                        intObjectMap2 = currentSemanticsNodes$ui;
                        if (SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()) != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getClearTextSubstitution())) != null && (function0 = (Function0) accessibilityAction.getAction()) != null) {
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    currentSemanticsNodes$ui = intObjectMap2;
                }
                intObjectMap = currentSemanticsNodes$ui;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            currentSemanticsNodes$ui = intObjectMap;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AndroidContentCaptureManager.android.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0007J \u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011H\u0007J \u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011H\u0002¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager$ViewTranslationHelperMethods;", "", "<init>", "()V", "onCreateVirtualViewTranslationRequests", "", "contentCaptureManager", "Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "virtualIds", "", "supportedFormats", "", "requestsCollector", "Ljava/util/function/Consumer;", "Landroid/view/translation/ViewTranslationRequest;", "onVirtualViewTranslationResponses", "response", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "doTranslation", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class ViewTranslationHelperMethods {
        public static final ViewTranslationHelperMethods INSTANCE = new ViewTranslationHelperMethods();

        private ViewTranslationHelperMethods() {
        }

        public final void onCreateVirtualViewTranslationRequests(AndroidContentCaptureManager contentCaptureManager, long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
            SemanticsNode semanticsNode;
            String strFastJoinToString$default;
            for (long j : virtualIds) {
                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = contentCaptureManager.getCurrentSemanticsNodes$ui().get((int) j);
                if (semanticsNodeWithAdjustedBounds != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null) {
                    ViewTranslationRequest.Builder builder = new ViewTranslationRequest.Builder(contentCaptureManager.getView().getAutofillId(), semanticsNode.getId());
                    List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
                    if (list != null && (strFastJoinToString$default = ListUtilsKt.fastJoinToString$default(list, "\n", null, null, 0, null, null, 62, null)) != null) {
                        builder.setValue("android:text", TranslationRequestValue.forText(new AnnotatedString(strFastJoinToString$default, null, 2, 0 == true ? 1 : 0)));
                        requestsCollector.accept(builder.build());
                    }
                }
            }
        }

        public final void onVirtualViewTranslationResponses(final AndroidContentCaptureManager contentCaptureManager, final LongSparseArray<ViewTranslationResponse> response) {
            if (Build.VERSION.SDK_INT < 31) {
                return;
            }
            if (Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
                doTranslation(contentCaptureManager, response);
            } else {
                contentCaptureManager.getView().post(new Runnable() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager$ViewTranslationHelperMethods$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AndroidContentCaptureManager.ViewTranslationHelperMethods.INSTANCE.doTranslation(contentCaptureManager, response);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void doTranslation(AndroidContentCaptureManager contentCaptureManager, LongSparseArray<ViewTranslationResponse> response) {
            TranslationResponseValue value;
            CharSequence text;
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds;
            SemanticsNode semanticsNode;
            AccessibilityAction accessibilityAction;
            Function1 function1;
            int size = response.size();
            for (int i = 0; i < size; i++) {
                long jKeyAt = response.keyAt(i);
                ViewTranslationResponse viewTranslationResponse = response.get(jKeyAt);
                if (viewTranslationResponse != null && (value = viewTranslationResponse.getValue("android:text")) != null && (text = value.getText()) != null && (semanticsNodeWithAdjustedBounds = contentCaptureManager.getCurrentSemanticsNodes$ui().get((int) jKeyAt)) != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetTextSubstitution())) != null && (function1 = (Function1) accessibilityAction.getAction()) != null) {
                }
            }
        }
    }

    public final void onCreateVirtualViewTranslationRequests$ui(long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
        ViewTranslationHelperMethods.INSTANCE.onCreateVirtualViewTranslationRequests(this, virtualIds, supportedFormats, requestsCollector);
    }

    public final void onVirtualViewTranslationResponses$ui(AndroidContentCaptureManager contentCaptureManager, LongSparseArray<ViewTranslationResponse> response) {
        ViewTranslationHelperMethods.INSTANCE.onVirtualViewTranslationResponses(contentCaptureManager, response);
    }
}
