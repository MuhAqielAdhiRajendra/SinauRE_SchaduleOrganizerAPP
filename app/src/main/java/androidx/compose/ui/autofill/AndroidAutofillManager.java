package androidx.compose.ui.autofill;

import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectListKt;
import androidx.compose.ui.focus.FocusListener;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.platform.coreshims.AutofillIdCompat;
import androidx.compose.ui.platform.coreshims.ViewCompatShims;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.semantics.SemanticsListener;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidAutofillManager.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u001c\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u000e\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&J\u0014\u0010'\u001a\u00020\u00192\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)J\u0015\u0010-\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b.J\u0015\u0010/\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b0J\u001d\u00101\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!2\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0002\b4J\u0015\u00105\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b6J\u0015\u00107\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b8J\r\u0010;\u001a\u00020\u0019H\u0000¢\u0006\u0002\b<R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Landroidx/compose/ui/autofill/AndroidAutofillManager;", "Landroidx/compose/ui/autofill/AutofillManager;", "Landroidx/compose/ui/semantics/SemanticsListener;", "Landroidx/compose/ui/focus/FocusListener;", "platformAutofillManager", "Landroidx/compose/ui/autofill/PlatformAutofillManager;", "semanticsOwner", "Landroidx/compose/ui/semantics/SemanticsOwner;", "view", "Landroid/view/View;", "rectManager", "Landroidx/compose/ui/spatial/RectManager;", "packageName", "", "<init>", "(Landroidx/compose/ui/autofill/PlatformAutofillManager;Landroidx/compose/ui/semantics/SemanticsOwner;Landroid/view/View;Landroidx/compose/ui/spatial/RectManager;Ljava/lang/String;)V", "getPlatformAutofillManager", "()Landroidx/compose/ui/autofill/PlatformAutofillManager;", "setPlatformAutofillManager", "(Landroidx/compose/ui/autofill/PlatformAutofillManager;)V", "reusableRect", "Landroid/graphics/Rect;", "rootAutofillId", "Landroid/view/autofill/AutofillId;", "commit", "", "cancel", "onFocusChanged", "previous", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "current", "onSemanticsChanged", "semanticsInfo", "Landroidx/compose/ui/semantics/SemanticsInfo;", "previousSemanticsConfiguration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "populateViewStructure", "rootViewStructure", "Landroid/view/ViewStructure;", "performAutofill", "values", "Landroid/util/SparseArray;", "Landroid/view/autofill/AutofillValue;", "currentlyDisplayedIDs", "Landroidx/collection/MutableIntSet;", "requestAutofill", "requestAutofill$ui", "onPostAttach", "onPostAttach$ui", "onPostLayoutNodeReused", "previousSemanticsId", "", "onPostLayoutNodeReused$ui", "onLayoutNodeDeactivated", "onLayoutNodeDeactivated$ui", "onDetach", "onDetach$ui", "pendingAutofillCommit", "", "onEndApplyChanges", "onEndApplyChanges$ui", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidAutofillManager extends AutofillManager implements SemanticsListener, FocusListener {
    public static final int $stable = 8;
    private MutableIntSet currentlyDisplayedIDs;
    private final String packageName;
    private boolean pendingAutofillCommit;
    private PlatformAutofillManager platformAutofillManager;
    private final RectManager rectManager;
    private Rect reusableRect = new Rect();
    private AutofillId rootAutofillId;
    private final SemanticsOwner semanticsOwner;
    private final View view;

    /* JADX INFO: compiled from: AndroidAutofillManager.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AndroidAutofillManager(PlatformAutofillManager platformAutofillManager, SemanticsOwner semanticsOwner, View view, RectManager rectManager, String packageName) {
        this.platformAutofillManager = platformAutofillManager;
        this.semanticsOwner = semanticsOwner;
        this.view = view;
        this.rectManager = rectManager;
        this.packageName = packageName;
        int i = 1;
        this.view.setImportantForAutofill(1);
        AutofillIdCompat autofillId = ViewCompatShims.getAutofillId(this.view);
        DefaultConstructorMarker defaultConstructorMarker = null;
        AutofillId autofillId2 = autofillId != null ? autofillId.toAutofillId() : null;
        if (autofillId2 != null) {
            this.rootAutofillId = autofillId2;
            this.currentlyDisplayedIDs = new MutableIntSet(0, i, defaultConstructorMarker);
        } else {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
            throw new KotlinNothingValueException();
        }
    }

    public final PlatformAutofillManager getPlatformAutofillManager() {
        return this.platformAutofillManager;
    }

    public final void setPlatformAutofillManager(PlatformAutofillManager platformAutofillManager) {
        this.platformAutofillManager = platformAutofillManager;
    }

    @Override // androidx.compose.ui.autofill.AutofillManager
    public void commit() {
        this.platformAutofillManager.commit();
    }

    @Override // androidx.compose.ui.autofill.AutofillManager
    public void cancel() {
        this.platformAutofillManager.cancel();
    }

    @Override // androidx.compose.ui.focus.FocusListener
    public void onFocusChanged(FocusTargetModifierNode previous, FocusTargetModifierNode current) {
        SemanticsInfo it;
        SemanticsInfo it2;
        if (previous != null && (it2 = DelegatableNodeKt.requireSemanticsInfo(previous)) != null) {
            SemanticsConfiguration semanticsConfiguration = it2.getSemanticsConfiguration();
            if (semanticsConfiguration != null && AndroidAutofillManager_androidKt.isAutofillable(semanticsConfiguration)) {
                this.platformAutofillManager.notifyViewExited(this.view, it2.getSemanticsId());
            }
        }
        if (current != null && (it = DelegatableNodeKt.requireSemanticsInfo(current)) != null) {
            SemanticsConfiguration semanticsConfiguration2 = it.getSemanticsConfiguration();
            if (semanticsConfiguration2 != null && AndroidAutofillManager_androidKt.isAutofillable(semanticsConfiguration2)) {
                final int semanticsId = it.getSemanticsId();
                this.rectManager.getRects().withRect(semanticsId, new Function4<Integer, Integer, Integer, Integer, Unit>() { // from class: androidx.compose.ui.autofill.AndroidAutofillManager$onFocusChanged$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, Integer num3, Integer num4) {
                        invoke(num.intValue(), num2.intValue(), num3.intValue(), num4.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int l, int t, int r, int b) {
                        this.this$0.getPlatformAutofillManager().notifyViewEntered(this.this$0.view, semanticsId, new Rect(l, t, r, b));
                    }
                });
            }
        }
    }

    @Override // androidx.compose.ui.semantics.SemanticsListener
    public void onSemanticsChanged(SemanticsInfo semanticsInfo, SemanticsConfiguration previousSemanticsConfiguration) {
        Boolean isToggled;
        AnnotatedString annotatedString;
        AnnotatedString annotatedString2;
        SemanticsConfiguration config = semanticsInfo.getSemanticsConfiguration();
        int semanticsId = semanticsInfo.getSemanticsId();
        String previousText = (previousSemanticsConfiguration == null || (annotatedString2 = (AnnotatedString) SemanticsConfigurationKt.getOrNull(previousSemanticsConfiguration, SemanticsProperties.INSTANCE.getInputText())) == null) ? null : annotatedString2.getText();
        String newText = (config == null || (annotatedString = (AnnotatedString) SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getInputText())) == null) ? null : annotatedString.getText();
        boolean currRelatedToAutoCommit = false;
        if (previousText != newText) {
            if (previousText == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, true);
            } else if (newText == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, false);
            } else {
                ContentDataType contentDataType = (ContentDataType) SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getContentDataType());
                if (Intrinsics.areEqual(contentDataType, ContentDataType.INSTANCE.getText())) {
                    this.platformAutofillManager.notifyValueChanged(this.view, semanticsId, AutofillApi26Helper.INSTANCE.getAutofillTextValue(newText));
                }
            }
        }
        ToggleableState previousToggleValue = previousSemanticsConfiguration != null ? (ToggleableState) SemanticsConfigurationKt.getOrNull(previousSemanticsConfiguration, SemanticsProperties.INSTANCE.getToggleableState()) : null;
        ToggleableState newToggleValue = config != null ? (ToggleableState) SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getToggleableState()) : null;
        if (previousToggleValue != newToggleValue) {
            if (previousToggleValue == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, true);
            } else if (newToggleValue == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, false);
            } else {
                ContentDataType contentDataType2 = (ContentDataType) SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getContentDataType());
                if (Intrinsics.areEqual(contentDataType2, ContentDataType.INSTANCE.getToggle())) {
                    switch (WhenMappings.$EnumSwitchMapping$0[newToggleValue.ordinal()]) {
                        case 1:
                            isToggled = true;
                            break;
                        case 2:
                            isToggled = false;
                            break;
                        default:
                            isToggled = null;
                            break;
                    }
                    if (isToggled != null) {
                        this.platformAutofillManager.notifyValueChanged(this.view, semanticsId, AutofillApi26Helper.INSTANCE.getAutofillToggleValue(isToggled.booleanValue()));
                    }
                }
            }
        }
        FillableData previousFillableData = previousSemanticsConfiguration != null ? (FillableData) SemanticsConfigurationKt.getOrNull(previousSemanticsConfiguration, SemanticsProperties.INSTANCE.getFillableData()) : null;
        FillableData newFillableData = config != null ? (FillableData) SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getFillableData()) : null;
        if (!Intrinsics.areEqual(previousFillableData, newFillableData)) {
            if (previousFillableData == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, true);
            } else {
                PlatformAutofillManager platformAutofillManager = this.platformAutofillManager;
                if (newFillableData == null) {
                    platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, false);
                } else {
                    platformAutofillManager.notifyValueChanged(this.view, semanticsId, ((AndroidFillableData) newFillableData).getAutofillValue());
                }
            }
        }
        boolean prevRelatedToAutoCommit = previousSemanticsConfiguration != null && AndroidAutofillManager_androidKt.isRelatedToAutoCommit(previousSemanticsConfiguration);
        if (config != null && AndroidAutofillManager_androidKt.isRelatedToAutoCommit(config)) {
            currRelatedToAutoCommit = true;
        }
        if (prevRelatedToAutoCommit != currRelatedToAutoCommit) {
            MutableIntSet mutableIntSet = this.currentlyDisplayedIDs;
            if (currRelatedToAutoCommit) {
                mutableIntSet.add(semanticsId);
            } else {
                mutableIntSet.remove(semanticsId);
            }
        }
    }

    public final void populateViewStructure(ViewStructure rootViewStructure) {
        AutofillApi26Helper autofillApi;
        SemanticsInfo rootSemanticInfo;
        AutofillApi26Helper autofillApi2 = AutofillApi26Helper.INSTANCE;
        SemanticsInfo rootSemanticInfo2 = this.semanticsOwner.getRootInfo$ui();
        PopulateViewStructure_androidKt.populate(rootViewStructure, rootSemanticInfo2, this.rootAutofillId, this.packageName, this.rectManager);
        MutableObjectList populateChildren = ObjectListKt.mutableObjectListOf(rootSemanticInfo2, rootViewStructure);
        while (populateChildren.isNotEmpty()) {
            MutableObjectList this_$iv = populateChildren;
            int i = 1;
            Object objRemoveAt = populateChildren.removeAt(this_$iv._size - 1);
            Intrinsics.checkNotNull(objRemoveAt, "null cannot be cast to non-null type android.view.ViewStructure");
            ViewStructure parentStructure = (ViewStructure) objRemoveAt;
            MutableObjectList this_$iv2 = populateChildren;
            Object objRemoveAt2 = populateChildren.removeAt(this_$iv2._size - 1);
            Intrinsics.checkNotNull(objRemoveAt2, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsInfo");
            SemanticsInfo parentInfo = (SemanticsInfo) objRemoveAt2;
            List<SemanticsInfo> childrenInfo = parentInfo.getChildrenInfo();
            int index$iv = 0;
            int size = childrenInfo.size();
            while (index$iv < size) {
                Object item$iv = childrenInfo.get(index$iv);
                SemanticsInfo childInfo = (SemanticsInfo) item$iv;
                if (childInfo.getIsDeactivated() || !childInfo.isAttached()) {
                    autofillApi = autofillApi2;
                    rootSemanticInfo = rootSemanticInfo2;
                } else if (childInfo.isPlaced()) {
                    SemanticsConfiguration semanticsConfigurationChild = childInfo.getSemanticsConfiguration();
                    int i2 = 0;
                    if (semanticsConfigurationChild != null && AndroidAutofillManager_androidKt.isRelatedToAutofill(semanticsConfigurationChild) == i) {
                        i2 = i;
                    }
                    if (i2 == 0) {
                        populateChildren.add(childInfo);
                        populateChildren.add(parentStructure);
                        autofillApi = autofillApi2;
                        rootSemanticInfo = rootSemanticInfo2;
                    } else {
                        int childIndex = autofillApi2.addChildCount(parentStructure, i);
                        ViewStructure childStructure = autofillApi2.newChild(parentStructure, childIndex);
                        autofillApi = autofillApi2;
                        rootSemanticInfo = rootSemanticInfo2;
                        PopulateViewStructure_androidKt.populate(childStructure, childInfo, this.rootAutofillId, this.packageName, this.rectManager);
                        populateChildren.add(childInfo);
                        populateChildren.add(childStructure);
                    }
                } else {
                    autofillApi = autofillApi2;
                    rootSemanticInfo = rootSemanticInfo2;
                }
                index$iv++;
                autofillApi2 = autofillApi;
                rootSemanticInfo2 = rootSemanticInfo;
                i = 1;
            }
        }
    }

    public final void performAutofill(SparseArray<AutofillValue> values) {
        SemanticsConfiguration semanticsConfiguration;
        Function1 function1;
        Function1 function12;
        int size = values.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = values.keyAt(i);
            AutofillValue autofillValue = values.get(iKeyAt);
            SemanticsInfo semanticsInfo = this.semanticsOwner.get$ui(iKeyAt);
            if (semanticsInfo != null && (semanticsConfiguration = semanticsInfo.getSemanticsConfiguration()) != null) {
                AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.INSTANCE.getOnAutofillText());
                if (accessibilityAction != null && (function12 = (Function1) accessibilityAction.getAction()) != null) {
                }
                AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.INSTANCE.getOnFillData());
                if (accessibilityAction2 != null && (function1 = (Function1) accessibilityAction2.getAction()) != null) {
                }
            }
        }
    }

    public final void requestAutofill$ui(final SemanticsInfo semanticsInfo) {
        this.rectManager.getRects().withRect(semanticsInfo.getSemanticsId(), new Function4<Integer, Integer, Integer, Integer, Unit>() { // from class: androidx.compose.ui.autofill.AndroidAutofillManager$requestAutofill$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, Integer num3, Integer num4) {
                invoke(num.intValue(), num2.intValue(), num3.intValue(), num4.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int left, int top, int right, int bottom) {
                this.this$0.reusableRect.set(left, top, right, bottom);
                this.this$0.getPlatformAutofillManager().requestAutofill(this.this$0.view, semanticsInfo.getSemanticsId(), this.this$0.reusableRect);
            }
        });
    }

    public final void onPostAttach$ui(SemanticsInfo semanticsInfo) {
        SemanticsConfiguration semanticsConfiguration = semanticsInfo.getSemanticsConfiguration();
        boolean z = false;
        if (semanticsConfiguration != null && AndroidAutofillManager_androidKt.isRelatedToAutoCommit(semanticsConfiguration)) {
            z = true;
        }
        if (z) {
            this.currentlyDisplayedIDs.add(semanticsInfo.getSemanticsId());
            this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsInfo.getSemanticsId(), true);
        }
    }

    public final void onPostLayoutNodeReused$ui(SemanticsInfo semanticsInfo, int previousSemanticsId) {
        boolean z = false;
        if (this.currentlyDisplayedIDs.remove(previousSemanticsId)) {
            this.platformAutofillManager.notifyViewVisibilityChanged(this.view, previousSemanticsId, false);
        }
        SemanticsConfiguration semanticsConfiguration = semanticsInfo.getSemanticsConfiguration();
        if (semanticsConfiguration != null && AndroidAutofillManager_androidKt.isRelatedToAutoCommit(semanticsConfiguration)) {
            z = true;
        }
        if (z) {
            this.currentlyDisplayedIDs.add(semanticsInfo.getSemanticsId());
            this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsInfo.getSemanticsId(), true);
        }
    }

    public final void onLayoutNodeDeactivated$ui(SemanticsInfo semanticsInfo) {
        if (this.currentlyDisplayedIDs.remove(semanticsInfo.getSemanticsId())) {
            this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsInfo.getSemanticsId(), false);
        }
    }

    public final void onDetach$ui(SemanticsInfo semanticsInfo) {
        if (this.currentlyDisplayedIDs.remove(semanticsInfo.getSemanticsId())) {
            this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsInfo.getSemanticsId(), false);
        }
    }

    public final void onEndApplyChanges$ui() {
        if (this.currentlyDisplayedIDs.isEmpty() && this.pendingAutofillCommit) {
            this.platformAutofillManager.commit();
            this.pendingAutofillCommit = false;
        }
        if (this.currentlyDisplayedIDs.isNotEmpty()) {
            this.pendingAutofillCommit = true;
        }
    }
}
