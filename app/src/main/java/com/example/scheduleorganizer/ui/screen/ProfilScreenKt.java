package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import android.net.Uri;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.PersonKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.ListItemKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.example.scheduleorganizer.data.entity.Task;
import com.example.scheduleorganizer.data.entity.UserProfile;
import com.example.scheduleorganizer.ui.MainViewModel;
import com.example.scheduleorganizer.util.AISettings;
import com.example.scheduleorganizer.util.AlarmPreferences;
import com.example.scheduleorganizer.util.ConsistencyManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProfilScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b\u001a-\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0007¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\u0013\u001a7\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u001aH\u0007¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d²\u0006\f\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u008a\u0084\u0002²\u0006\u0010\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u008a\u0084\u0002²\u0006\n\u0010#\u001a\u00020$X\u008a\u008e\u0002²\u0006\n\u0010%\u001a\u00020$X\u008a\u008e\u0002²\u0006\n\u0010&\u001a\u00020$X\u008a\u008e\u0002²\u0006\n\u0010'\u001a\u00020$X\u008a\u008e\u0002²\u0006\n\u0010(\u001a\u00020$X\u008a\u008e\u0002²\u0006\n\u0010)\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010*\u001a\u00020+X\u008a\u008e\u0002²\u0006\n\u0010,\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010-\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010.\u001a\u00020/X\u008a\u008e\u0002²\u0006\n\u00100\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u00101\u001a\u00020\u0007X\u008a\u008e\u0002"}, d2 = {"ProfilScreen", "", "viewModel", "Lcom/example/scheduleorganizer/ui/MainViewModel;", "(Lcom/example/scheduleorganizer/ui/MainViewModel;Landroidx/compose/runtime/Composer;I)V", "HeaderSectionProfil", HintConstants.AUTOFILL_HINT_NAME, "", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "ProfilSummarySection", "consistency", "", "totalTasks", "completed", "bestStreak", "(IIIILandroidx/compose/runtime/Composer;I)V", "ProfilSummaryItem", "value", "label", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "ProfileMenuItem", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "onClick", "Lkotlin/Function0;", "ProfileMenuItem-cf5BqRc", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;JLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "app", "userProfile", "Lcom/example/scheduleorganizer/data/entity/UserProfile;", "tasks", "", "Lcom/example/scheduleorganizer/data/entity/Task;", "showNameDialog", "", "showCourseDialog", "showSoundDialog", "showFocusDurationDialog", "showAIConfigDialog", "currentSoundLabel", "selectedSoundUri", "Landroid/net/Uri;", "newName", "durationInput", "selectedProvider", "Lcom/example/scheduleorganizer/util/AISettings$Provider;", "apiKeyInput", "modelInput"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ProfilScreenKt {
    static final Unit HeaderSectionProfil$lambda$1(String str, int i, Composer composer, int i2) {
        HeaderSectionProfil(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$63(MainViewModel mainViewModel, int i, Composer composer, int i2) {
        ProfilScreen(mainViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ProfilSummaryItem$lambda$1(String str, String str2, int i, Composer composer, int i2) {
        ProfilSummaryItem(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ProfilSummarySection$lambda$1(int i, int i2, int i3, int i4, int i5, Composer composer, int i6) {
        ProfilSummarySection(i, i2, i3, i4, composer, RecomposeScopeImplKt.updateChangedFlags(i5 | 1));
        return Unit.INSTANCE;
    }

    static final Unit ProfileMenuItem_cf5BqRc$lambda$1(ImageVector imageVector, String str, long j, Function0 function0, int i, int i2, Composer composer, int i3) {
        m8762ProfileMenuItemcf5BqRc(imageVector, str, j, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0504  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x05a0  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x06a0  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x07ed  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0810  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x081a  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x08b1  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0908  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x038a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void ProfilScreen(final com.example.scheduleorganizer.ui.MainViewModel r52, androidx.compose.runtime.Composer r53, final int r54) {
        /*
            Method dump skipped, instruction units count: 2344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.ProfilScreenKt.ProfilScreen(com.example.scheduleorganizer.ui.MainViewModel, androidx.compose.runtime.Composer, int):void");
    }

    private static final UserProfile ProfilScreen$lambda$0(State<UserProfile> state) {
        return (UserProfile) state.getValue();
    }

    private static final List<Task> ProfilScreen$lambda$1(State<? extends List<Task>> state) {
        return (List) state.getValue();
    }

    private static final boolean ProfilScreen$lambda$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ProfilScreen$lambda$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean ProfilScreen$lambda$6(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ProfilScreen$lambda$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final void ProfilScreen$lambda$10(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean ProfilScreen$lambda$9(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final boolean ProfilScreen$lambda$12(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ProfilScreen$lambda$13(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean ProfilScreen$lambda$15(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ProfilScreen$lambda$16(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String ProfilScreen$lambda$20(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$26$0(MainViewModel $viewModel, Uri uri) {
        if (uri != null) {
            $viewModel.exportData(uri);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$27$0(MainViewModel $viewModel, Uri uri) {
        if (uri != null) {
            $viewModel.importData(uri);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$28$0(MainViewModel $viewModel, Context $context, Uri uri) {
        if (uri != null) {
            $viewModel.exportChatHistory($context, uri);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$29$0(MainViewModel $viewModel, Context $context, Uri uri) {
        if (uri != null) {
            $viewModel.importChatHistory($context, uri);
        }
        return Unit.INSTANCE;
    }

    private static final String ProfilScreen$lambda$31(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$33$0(MutableState $showNameDialog$delegate) {
        ProfilScreen$lambda$4($showNameDialog$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$36(final MutableState $newName$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C97@4103L16,97@4060L91:ProfilScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1993240188, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:97)");
            }
            String strProfilScreen$lambda$31 = ProfilScreen$lambda$31($newName$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -51591380, "CC(remember):ProfilScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda34
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ProfilScreenKt.ProfilScreen$lambda$36$0$0($newName$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextFieldKt.TextField(strProfilScreen$lambda$31, (Function1<? super String, Unit>) objRememberedValue, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfilScreenKt.INSTANCE.getLambda$1430983512$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1572912, 0, 0, 8388540);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$36$0$0(MutableState $newName$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $newName$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$34(final MainViewModel $viewModel, final MutableState $newName$delegate, final MutableState $showNameDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C100@4230L119,100@4213L156:ProfilScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(68402807, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:100)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1310589326, "CC(remember):ProfilScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($viewModel);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$34$0$0($viewModel, $newName$delegate, $showNameDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ProfilScreenKt.INSTANCE.m8726getLambda$1610184089$app(), $composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$34$0$0(MainViewModel $viewModel, MutableState $newName$delegate, MutableState $showNameDialog$delegate) {
        $viewModel.insertUserProfile(ProfilScreen$lambda$31($newName$delegate));
        ProfilScreen$lambda$4($showNameDialog$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$35(final MutableState $showNameDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C106@4452L26,106@4431L68:ProfilScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-879649159, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:106)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 765716787, "CC(remember):ProfilScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$35$0$0($showNameDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ProfilScreenKt.INSTANCE.getLambda$1984258550$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$35$0$0(MutableState $showNameDialog$delegate) {
        ProfilScreen$lambda$4($showNameDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$37$0(MutableState $showCourseDialog$delegate) {
        ProfilScreen$lambda$7($showCourseDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$38$0(MutableState $showSoundDialog$delegate) {
        ProfilScreen$lambda$10($showSoundDialog$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$40(SnapshotStateList $alarmSounds, final Context $context, final MutableState $selectedSoundUri$delegate, final MutableState $currentSoundLabel$delegate, final MutableState $showSoundDialog$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        final Uri uri;
        final String str;
        ComposerKt.sourceInformation($composer, "C120@4830L669:ProfilScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-508469884, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:120)");
            }
            int i = 0;
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = (i2 >> 6) & 14;
            int i4 = 0;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1797268901, "C:ProfilScreen.kt#kl928v");
            $composer.startReplaceGroup(219118620);
            ComposerKt.sourceInformation($composer, "*125@5070L300,131@5418L15,122@4921L538");
            SnapshotStateList<Pair> snapshotStateList = $alarmSounds;
            int i6 = 0;
            for (Pair pair : snapshotStateList) {
                Iterable iterable = snapshotStateList;
                final String str2 = (String) pair.component1();
                int i7 = i6;
                Uri uri2 = (Uri) pair.component2();
                int i8 = i4;
                int i9 = iHashCode;
                CompositionLocalMap compositionLocalMap = currentCompositionLocalMap;
                int i10 = i;
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -1936970017, "CC(remember):ProfilScreen.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance(uri2) | $composer.changedInstance($context) | $composer.changed(str2);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChangedInstance) {
                    uri = uri2;
                } else {
                    uri = uri2;
                    if (objRememberedValue != Composer.INSTANCE.getEmpty()) {
                        str = str2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ListItemKt.m2657ListItemHXNGIdc(ComposableLambdaKt.rememberComposableLambda(1379339893, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return ProfilScreenKt.ProfilScreen$lambda$40$0$0$1(str, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer, 54), ClickableKt.m321clickableoSLSa3U$default(modifierFillMaxWidth$default, false, null, null, null, (Function0) objRememberedValue, 15, null), null, null, null, null, null, 0.0f, 0.0f, $composer, 6, TypedValues.PositionType.TYPE_CURVE_FIT);
                    snapshotStateList = iterable;
                    i6 = i7;
                    i4 = i8;
                    iHashCode = i9;
                    currentCompositionLocalMap = compositionLocalMap;
                    i = i10;
                }
                str = str2;
                objRememberedValue = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$40$0$0$0$0(uri, $context, str2, $selectedSoundUri$delegate, $currentSoundLabel$delegate, $showSoundDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ListItemKt.m2657ListItemHXNGIdc(ComposableLambdaKt.rememberComposableLambda(1379339893, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ProfilScreenKt.ProfilScreen$lambda$40$0$0$1(str, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), ClickableKt.m321clickableoSLSa3U$default(modifierFillMaxWidth$default, false, null, null, null, (Function0) objRememberedValue, 15, null), null, null, null, null, null, 0.0f, 0.0f, $composer, 6, TypedValues.PositionType.TYPE_CURVE_FIT);
                snapshotStateList = iterable;
                i6 = i7;
                i4 = i8;
                iHashCode = i9;
                currentCompositionLocalMap = compositionLocalMap;
                i = i10;
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$40$0$0$0$0(Uri $uri, Context $context, String $title, MutableState $selectedSoundUri$delegate, MutableState $currentSoundLabel$delegate, MutableState $showSoundDialog$delegate) {
        $selectedSoundUri$delegate.setValue($uri);
        AlarmPreferences.INSTANCE.setAlarmSoundUri($context, $uri);
        $currentSoundLabel$delegate.setValue($title);
        ProfilScreen$lambda$10($showSoundDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$40$0$0$1(String $title, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C131@5420L11:ProfilScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1379339893, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfilScreen.kt:131)");
            }
            TextKt.m3157TextNvy7gAk($title, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$39(final MutableState $showSoundDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C137@5582L27,137@5561L105:ProfilScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(853711551, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:137)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 78048250, "CC(remember):ProfilScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda43
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$39$0$0($showSoundDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ProfilScreenKt.INSTANCE.m8730getLambda$5016836$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$39$0$0(MutableState $showSoundDialog$delegate) {
        ProfilScreen$lambda$10($showSoundDialog$delegate, false);
        return Unit.INSTANCE;
    }

    private static final String ProfilScreen$lambda$42(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$44$0(MutableState $showFocusDurationDialog$delegate) {
        ProfilScreen$lambda$13($showFocusDurationDialog$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$47(final MutableState $durationInput$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        ComposerKt.sourceInformation($composer, "C150@6004L695:ProfilScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1441824221, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:150)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1738047358, "C151@6033L46,152@6100L41,155@6260L22,153@6162L519:ProfilScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Atur durasi fokus dalam menit (1-120):", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            String strProfilScreen$lambda$42 = ProfilScreen$lambda$42($durationInput$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, KeyboardType.INSTANCE.m7811getNumberPjHm6EE(), 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 123, (DefaultConstructorMarker) null);
            ComposerKt.sourceInformationMarkerStart($composer, 913771919, "CC(remember):ProfilScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                composer = $composer;
                Object obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda33
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ProfilScreenKt.ProfilScreen$lambda$47$0$1$0($durationInput$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            } else {
                composer = $composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            Composer composer2 = composer;
            TextFieldKt.TextField(strProfilScreen$lambda$42, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfilScreenKt.INSTANCE.m8731getLambda$529295243$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, keyboardOptions, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, 1573296, 0, 0, 8355768);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$47$0$1$0(MutableState $durationInput$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $durationInput$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$45(final MainViewModel $viewModel, final MutableState $durationInput$delegate, final MutableState $showFocusDurationDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C165@6778L197,165@6761L234:ProfilScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-79642786, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:165)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 772695299, "CC(remember):ProfilScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($viewModel);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$45$0$0($viewModel, $durationInput$delegate, $showFocusDurationDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ProfilScreenKt.INSTANCE.getLambda$1706053454$app(), $composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$45$0$0(MainViewModel $viewModel, MutableState $durationInput$delegate, MutableState $showFocusDurationDialog$delegate) {
        Integer intOrNull = StringsKt.toIntOrNull(ProfilScreen$lambda$42($durationInput$delegate));
        int duration = intOrNull != null ? intOrNull.intValue() : 25;
        $viewModel.setFocusDuration(duration);
        ProfilScreen$lambda$13($showFocusDurationDialog$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$46(final MutableState $showFocusDurationDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C172@7078L35,172@7057L77:ProfilScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-624515360, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:172)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1136161763, "CC(remember):ProfilScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda40
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$46$0$0($showFocusDurationDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ProfilScreenKt.INSTANCE.m8725getLambda$1483243747$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$46$0$0(MutableState $showFocusDurationDialog$delegate) {
        ProfilScreen$lambda$13($showFocusDurationDialog$delegate, false);
        return Unit.INSTANCE;
    }

    private static final AISettings.Provider ProfilScreen$lambda$49(MutableState<AISettings.Provider> mutableState) {
        return mutableState.getValue();
    }

    private static final String ProfilScreen$lambda$52(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String ProfilScreen$lambda$55(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$57$0(MutableState $showAIConfigDialog$delegate) {
        ProfilScreen$lambda$16($showAIConfigDialog$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$60(final MutableState $selectedProvider$delegate, final MutableState $apiKeyInput$delegate, final MutableState $modelInput$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        long surface;
        long surface2;
        ComposerKt.sourceInformation($composer, "C186@7639L1850:ProfilScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1919788738, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:186)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1678790816, "C187@7668L24,188@7713L40,189@7774L776,198@8571L41:ProfilScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Pilih provider:", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function02 = constructor2;
                $composer.createNode(function02);
            } else {
                function02 = constructor2;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -581647021, "C190@7821L48,190@7895L154,190@7804L316,193@8145L39,194@8226L49,194@8301L155,194@8209L319:ProfilScreen.kt#kl928v");
            ComposerKt.sourceInformationMarkerStart($composer, -850046932, "CC(remember):ProfilScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$60$0$0$0$0($selectedProvider$delegate);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            Function0 function03 = (Function0) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonDefaults buttonDefaults = ButtonDefaults.INSTANCE;
            if (ProfilScreen$lambda$49($selectedProvider$delegate) == AISettings.Provider.LOCAL) {
                $composer.startReplaceGroup(-850041181);
                ComposerKt.sourceInformation($composer, "190@7990L11");
                surface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
            } else {
                $composer.startReplaceGroup(-850039933);
                ComposerKt.sourceInformation($composer, "190@8029L11");
                surface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface();
            }
            $composer.endReplaceGroup();
            ButtonKt.Button(function03, null, false, null, buttonDefaults.m2208buttonColorsro_MJ88(surface, 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14), null, null, null, null, ComposableSingletons$ProfilScreenKt.INSTANCE.getLambda$1116165516$app(), $composer, 805306374, 494);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            ComposerKt.sourceInformationMarkerStart($composer, -850033971, "CC(remember):ProfilScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$60$0$0$1$0($selectedProvider$delegate);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue2);
            }
            Function0 function04 = (Function0) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonDefaults buttonDefaults2 = ButtonDefaults.INSTANCE;
            if (ProfilScreen$lambda$49($selectedProvider$delegate) == AISettings.Provider.OPENAI) {
                $composer.startReplaceGroup(-850028157);
                ComposerKt.sourceInformation($composer, "194@8397L11");
                surface2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
            } else {
                $composer.startReplaceGroup(-850026909);
                ComposerKt.sourceInformation($composer, "194@8436L11");
                surface2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface();
            }
            $composer.endReplaceGroup();
            ButtonKt.Button(function04, null, false, null, buttonDefaults2.m2208buttonColorsro_MJ88(surface2, 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14), null, null, null, null, ComposableSingletons$ProfilScreenKt.INSTANCE.getLambda$1147992323$app(), $composer, 805306374, 494);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            if (ProfilScreen$lambda$49($selectedProvider$delegate) == AISettings.Provider.OPENAI) {
                $composer.startReplaceGroup(-1677818812);
                ComposerKt.sourceInformation($composer, "200@8711L17,201@8753L40,202@8865L20,202@8818L123,203@8966L40,204@9031L25,205@9081L40,206@9192L19,206@9146L131");
                TextKt.m3157TextNvy7gAk("API Key:", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
                SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
                String strProfilScreen$lambda$52 = ProfilScreen$lambda$52($apiKeyInput$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, 1608449964, "CC(remember):ProfilScreen.kt#9igjgp");
                Object objRememberedValue3 = $composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return ProfilScreenKt.ProfilScreen$lambda$60$0$1$0($apiKeyInput$delegate, (String) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue3 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TextFieldKt.TextField(strProfilScreen$lambda$52, (Function1<? super String, Unit>) objRememberedValue3, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfilScreenKt.INSTANCE.getLambda$1390222319$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1572912, 12582912, 0, 8257468);
                SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
                TextKt.m3157TextNvy7gAk("Model (opsional):", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
                SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
                String strProfilScreen$lambda$55 = ProfilScreen$lambda$55($modelInput$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, 1608460427, "CC(remember):ProfilScreen.kt#9igjgp");
                Object objRememberedValue4 = $composer.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return ProfilScreenKt.ProfilScreen$lambda$60$0$2$0($modelInput$delegate, (String) obj3);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue4 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TextFieldKt.TextField(strProfilScreen$lambda$55, (Function1<? super String, Unit>) objRememberedValue4, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfilScreenKt.INSTANCE.getLambda$2122064870$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1572912, 12582912, 0, 8257468);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1677217660);
                ComposerKt.sourceInformation($composer, "208@9420L11,208@9331L118");
                TextKt.m3157TextNvy7gAk("Local fallback akan digunakan (offline, respons terbatas).", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
                $composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$60$0$0$0$0(MutableState $selectedProvider$delegate) {
        $selectedProvider$delegate.setValue(AISettings.Provider.LOCAL);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$60$0$0$1$0(MutableState $selectedProvider$delegate) {
        $selectedProvider$delegate.setValue(AISettings.Provider.OPENAI);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$60$0$1$0(MutableState $apiKeyInput$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $apiKeyInput$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$60$0$2$0(MutableState $modelInput$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $modelInput$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$58(final Context $context, final MutableState $selectedProvider$delegate, final MutableState $apiKeyInput$delegate, final MutableState $modelInput$delegate, final MutableState $showAIConfigDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C213@9568L392,213@9551L429:ProfilScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1012997123, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:213)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1467342501, "CC(remember):ProfilScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($context);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda56
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$58$0$0($context, $selectedProvider$delegate, $apiKeyInput$delegate, $modelInput$delegate, $showAIConfigDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ProfilScreenKt.INSTANCE.getLambda$772699117$app(), $composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$58$0$0(Context $context, MutableState $selectedProvider$delegate, MutableState $apiKeyInput$delegate, MutableState $modelInput$delegate, MutableState $showAIConfigDialog$delegate) {
        AISettings.INSTANCE.setProvider($context, ProfilScreen$lambda$49($selectedProvider$delegate));
        if (ProfilScreen$lambda$49($selectedProvider$delegate) == AISettings.Provider.OPENAI) {
            AISettings.INSTANCE.setApiKey($context, ProfilScreen$lambda$52($apiKeyInput$delegate));
            AISettings aISettings = AISettings.INSTANCE;
            String strProfilScreen$lambda$55 = ProfilScreen$lambda$55($modelInput$delegate);
            if (StringsKt.isBlank(strProfilScreen$lambda$55)) {
                strProfilScreen$lambda$55 = "gpt-3.5-turbo";
            }
            aISettings.setModel($context, strProfilScreen$lambda$55);
        }
        ProfilScreen$lambda$16($showAIConfigDialog$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit ProfilScreen$lambda$59(final MutableState $showAIConfigDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C223@10063L30,223@10042L72:ProfilScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1557869697, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous> (ProfilScreen.kt:223)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1830808765, "CC(remember):ProfilScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda55
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfilScreenKt.ProfilScreen$lambda$59$0$0($showAIConfigDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ProfilScreenKt.INSTANCE.getLambda$1878369212$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$59$0$0(MutableState $showAIConfigDialog$delegate) {
        ProfilScreen$lambda$16($showAIConfigDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0(final State $userProfile$delegate, final State $tasks$delegate, final SnackbarHostState $snackbarHostState, final MainViewModel $viewModel, final ManagedActivityResultLauncher $exportLauncher, final ManagedActivityResultLauncher $importLauncher, final ManagedActivityResultLauncher $exportChatLauncher, final ManagedActivityResultLauncher $importChatLauncher, final Context $context, final MutableState $showNameDialog$delegate, final MutableState $showCourseDialog$delegate, final MutableState $currentSoundLabel$delegate, final MutableState $showSoundDialog$delegate, final MutableState $showFocusDurationDialog$delegate, final MutableState $showAIConfigDialog$delegate, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-844392587, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ProfilScreenKt.ProfilScreen$lambda$62$0$0($userProfile$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(638436702, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ProfilScreenKt.ProfilScreen$lambda$62$0$1($tasks$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(245437757, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ProfilScreenKt.ProfilScreen$lambda$62$0$2($snackbarHostState, $viewModel, $exportLauncher, $importLauncher, $exportChatLauncher, $importChatLauncher, $context, $showNameDialog$delegate, $showCourseDialog$delegate, $currentSoundLabel$delegate, $showSoundDialog$delegate, $showFocusDurationDialog$delegate, $showAIConfigDialog$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$0(State $userProfile$delegate, LazyItemScope item, Composer $composer, int $changed) {
        String name;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C241@10499L52:ProfilScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-844392587, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous>.<anonymous>.<anonymous> (ProfilScreen.kt:241)");
            }
            UserProfile userProfileProfilScreen$lambda$0 = ProfilScreen$lambda$0($userProfile$delegate);
            if (userProfileProfilScreen$lambda$0 == null || (name = userProfileProfilScreen$lambda$0.getName()) == null) {
                name = "Pengguna";
            }
            HeaderSectionProfil(name, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$1(State $tasks$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C244@10616L7,245@10636L289:ProfilScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(638436702, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous>.<anonymous>.<anonymous> (ProfilScreen.kt:244)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Context context = (Context) objConsume;
            int currentStreak = ConsistencyManager.INSTANCE.getCurrentStreak(context);
            int size = ProfilScreen$lambda$1($tasks$delegate).size();
            Iterable iterableProfilScreen$lambda$1 = ProfilScreen$lambda$1($tasks$delegate);
            Collection arrayList = new ArrayList();
            for (Object obj : iterableProfilScreen$lambda$1) {
                if (((Task) obj).isCompleted()) {
                    arrayList.add(obj);
                }
            }
            ProfilSummarySection(currentStreak, size, ((List) arrayList).size(), ConsistencyManager.INSTANCE.getBestStreak(context), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2(final SnackbarHostState $snackbarHostState, final MainViewModel $viewModel, final ManagedActivityResultLauncher $exportLauncher, final ManagedActivityResultLauncher $importLauncher, final ManagedActivityResultLauncher $exportChatLauncher, final ManagedActivityResultLauncher $importChatLauncher, final Context $context, final MutableState $showNameDialog$delegate, final MutableState $showCourseDialog$delegate, final MutableState $currentSoundLabel$delegate, final MutableState $showSoundDialog$delegate, final MutableState $showFocusDurationDialog$delegate, final MutableState $showAIConfigDialog$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C256@11167L11,256@11125L62,257@11202L2820,253@10963L3059:ProfilScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(245437757, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilScreen.<anonymous>.<anonymous>.<anonymous> (ProfilScreen.kt:253)");
            }
            CardKt.Card(SizeKt.fillMaxWidth$default(PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), 0.0f, 1, null), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(16)), CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14), null, null, ComposableLambdaKt.rememberComposableLambda(-1061420405, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda39
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ProfilScreenKt.ProfilScreen$lambda$62$0$2$0($snackbarHostState, $viewModel, $exportLauncher, $importLauncher, $exportChatLauncher, $importChatLauncher, $context, $showNameDialog$delegate, $showCourseDialog$delegate, $currentSoundLabel$delegate, $showSoundDialog$delegate, $showFocusDurationDialog$delegate, $showAIConfigDialog$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 196614, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x06dd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x071c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0460 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x04bd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x051a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0587  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0675  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0687  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x06d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.Unit ProfilScreen$lambda$62$0$2$0(androidx.compose.material3.SnackbarHostState r44, final com.example.scheduleorganizer.ui.MainViewModel r45, final androidx.activity.compose.ManagedActivityResultLauncher r46, final androidx.activity.compose.ManagedActivityResultLauncher r47, final androidx.activity.compose.ManagedActivityResultLauncher r48, final androidx.activity.compose.ManagedActivityResultLauncher r49, android.content.Context r50, final androidx.compose.runtime.MutableState r51, final androidx.compose.runtime.MutableState r52, androidx.compose.runtime.MutableState r53, final androidx.compose.runtime.MutableState r54, final androidx.compose.runtime.MutableState r55, final androidx.compose.runtime.MutableState r56, androidx.compose.foundation.layout.ColumnScope r57, androidx.compose.runtime.Composer r58, int r59) {
        /*
            Method dump skipped, instruction units count: 1842
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.ProfilScreenKt.ProfilScreen$lambda$62$0$2$0(androidx.compose.material3.SnackbarHostState, com.example.scheduleorganizer.ui.MainViewModel, androidx.activity.compose.ManagedActivityResultLauncher, androidx.activity.compose.ManagedActivityResultLauncher, androidx.activity.compose.ManagedActivityResultLauncher, androidx.activity.compose.ManagedActivityResultLauncher, android.content.Context, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$0$0(MutableState $showNameDialog$delegate) {
        ProfilScreen$lambda$4($showNameDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$1$0(MutableState $showCourseDialog$delegate) {
        ProfilScreen$lambda$7($showCourseDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$2$0(MainViewModel $viewModel) {
        $viewModel.getThemeMode().setValue(Integer.valueOf(($viewModel.getThemeMode().getValue().intValue() + 1) % 3));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$3$0(ManagedActivityResultLauncher $exportLauncher) {
        $exportLauncher.launch("schedule_organizer_backup.json");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$4$0(ManagedActivityResultLauncher $importLauncher) {
        $importLauncher.launch(new String[]{"application/json"});
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$5$0(ManagedActivityResultLauncher $exportChatLauncher) {
        $exportChatLauncher.launch("chat_history.json");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$6$0(ManagedActivityResultLauncher $importChatLauncher) {
        $importChatLauncher.launch(new String[]{"application/json"});
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$7$0(MutableState $showSoundDialog$delegate) {
        ProfilScreen$lambda$10($showSoundDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$8$0(MutableState $showFocusDurationDialog$delegate) {
        ProfilScreen$lambda$13($showFocusDurationDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$9$0(MutableState $showAIConfigDialog$delegate) {
        ProfilScreen$lambda$16($showAIConfigDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProfilScreen$lambda$62$0$2$0$1$10$0(MainViewModel $viewModel) {
        $viewModel.resetData();
        return Unit.INSTANCE;
    }

    public static final void HeaderSectionProfil(String name, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        final String name2 = name;
        Intrinsics.checkNotNullParameter(name2, "name");
        Composer $composer3 = $composer.startRestartGroup(-146319356);
        ComposerKt.sourceInformation($composer3, "C(HeaderSectionProfil)N(name)321@14335L11,322@14394L11,314@14098L1252:ProfilScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(name2) ? 4 : 2;
        }
        if (!$composer3.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-146319356, $dirty, -1, "com.example.scheduleorganizer.ui.screen.HeaderSectionProfil (ProfilScreen.kt:313)");
            }
            Modifier modifierM1101height3ABfNKs = SizeKt.m1101height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8150constructorimpl(250));
            Brush.Companion companion = Brush.INSTANCE;
            long primary = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary();
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(BackgroundKt.background$default(modifierM1101height3ABfNKs, Brush.Companion.m5268verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m5303boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary()), Color.m5303boximpl(Color.m5311copywmQWz5c(primary, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary) : 0.8f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary) : 0.0f))}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m8150constructorimpl(24));
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            int $dirty2 = $dirty;
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1421854779, "C329@14576L768:ProfilScreen.kt#kl928v");
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1841971630, "C330@14649L383,339@15045L41,340@15099L94,341@15206L40,342@15259L75:ProfilScreen.kt#kl928v");
            Modifier modifierClip = ClipKt.clip(SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(100)), RoundedCornerShapeKt.getCircleShape());
            long jM5350getWhite0d7_KjU = Color.INSTANCE.m5350getWhite0d7_KjU();
            Modifier modifierM286backgroundbw27NRU$default = BackgroundKt.m286backgroundbw27NRU$default(modifierClip, Color.m5311copywmQWz5c(jM5350getWhite0d7_KjU, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM5350getWhite0d7_KjU) : 0.3f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM5350getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM5350getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM5350getWhite0d7_KjU) : 0.0f), null, 2, null);
            Alignment center2 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer3, modifierM286backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function02 = constructor3;
                $composer3.createNode(function02);
            } else {
                function02 = constructor3;
                $composer3.useNode();
            }
            Composer composerM4433constructorimpl3 = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl(composerM4433constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 784921623, "C337@14912L106:ProfilScreen.kt#kl928v");
            IconKt.m2605Iconww6aTOc(PersonKt.getPerson(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(64)), Color.INSTANCE.m5350getWhite0d7_KjU(), $composer3, 3504, 0);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), $composer3, 6);
            TextKt.m3157TextNvy7gAk("Preferensi", null, Color.INSTANCE.m5350getWhite0d7_KjU(), null, TextUnitKt.getSp(24), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597830, 0, 262058);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(4)), $composer3, 6);
            long jM5350getWhite0d7_KjU2 = Color.INSTANCE.m5350getWhite0d7_KjU();
            $composer2 = $composer3;
            name2 = name;
            TextKt.m3157TextNvy7gAk(name2, null, Color.m5311copywmQWz5c(jM5350getWhite0d7_KjU2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM5350getWhite0d7_KjU2) : 0.9f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM5350getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM5350getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM5350getWhite0d7_KjU2) : 0.0f), null, TextUnitKt.getSp(18), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, ($dirty2 & 14) | 24960, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProfilScreenKt.HeaderSectionProfil$lambda$1(name2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ProfilSummarySection(final int consistency, final int totalTasks, final int completed, final int bestStreak, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1158117910);
        ComposerKt.sourceInformation($composer3, "C(ProfilSummarySection)N(consistency,totalTasks,completed,bestStreak)355@15705L11,355@15663L62,356@15760L38,357@15805L753,349@15465L1093:ProfilScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(consistency) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(totalTasks) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(completed) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(bestStreak) ? 2048 : 1024;
        }
        if ($composer3.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1158117910, $dirty, -1, "com.example.scheduleorganizer.ui.screen.ProfilSummarySection (ProfilScreen.kt:348)");
            }
            $composer2 = $composer3;
            CardKt.Card(OffsetKt.m1008offsetVpY3zN4$default(SizeKt.fillMaxWidth$default(PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), 0.0f, 1, null), 0.0f, Dp.m8150constructorimpl(-40), 1, null), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(16)), CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer3, CardDefaults.$stable << 12, 14), CardDefaults.INSTANCE.m2229cardElevationaqJV_2Y(Dp.m8150constructorimpl(4), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, (CardDefaults.$stable << 18) | 6, 62), null, ComposableLambdaKt.rememberComposableLambda(-242012744, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ProfilScreenKt.ProfilSummarySection$lambda$0(bestStreak, consistency, totalTasks, completed, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 196614, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda42
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProfilScreenKt.ProfilSummarySection$lambda$1(consistency, totalTasks, completed, bestStreak, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ProfilSummarySection$lambda$0(int $bestStreak, int $consistency, int $totalTasks, int $completed, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C358@15815L737:ProfilScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-242012744, $changed, -1, "com.example.scheduleorganizer.ui.screen.ProfilSummarySection.<anonymous> (ProfilScreen.kt:358)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1205085021, "C359@15872L448,368@16333L41,369@16468L10,369@16513L11,369@16387L155:ProfilScreen.kt#kl928v");
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal spaceAround = Arrangement.INSTANCE.getSpaceAround();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceAround, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function02 = constructor2;
                $composer.createNode(function02);
            } else {
                function02 = constructor2;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i6 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -17152340, "C364@16089L62,365@16168L63,366@16248L58:ProfilScreen.kt#kl928v");
            ProfilSummaryItem(String.valueOf($consistency), "Konsisten", $composer, 48);
            ProfilSummaryItem(String.valueOf($totalTasks), "Total Tugas", $composer, 48);
            ProfilSummaryItem(String.valueOf($completed), "Selesai", $composer, 48);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            TextKt.m3157TextNvy7gAk("Rekor konsistensi terbaik: " + $bestStreak + " hari", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 0, 0, 131066);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static final void ProfilSummaryItem(final String value, String label, Composer $composer, final int $changed) {
        final String str;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(label, "label");
        Composer $composer3 = $composer.startRestartGroup(227635876);
        ComposerKt.sourceInformation($composer3, "C(ProfilSummaryItem)N(value,label)376@16632L281:ProfilScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(label) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            str = label;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(227635876, $dirty, -1, "com.example.scheduleorganizer.ui.screen.ProfilSummaryItem (ProfilScreen.kt:375)");
            }
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1053769381, "C377@16790L11,377@16701L109,378@16878L11,378@16819L88:ProfilScreen.kt#kl928v");
            $composer2 = $composer3;
            TextKt.m3157TextNvy7gAk(value, null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary(), null, TextUnitKt.getSp(24), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, ($dirty & 14) | 1597440, 0, 262058);
            str = label;
            TextKt.m3157TextNvy7gAk(str, null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, (($dirty >> 3) & 14) | 24576, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProfilScreenKt.ProfilSummaryItem$lambda$1(value, str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02c9  */
    /* JADX INFO: renamed from: ProfileMenuItem-cf5BqRc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m8762ProfileMenuItemcf5BqRc(final androidx.compose.ui.graphics.vector.ImageVector r63, final java.lang.String r64, long r65, final kotlin.jvm.functions.Function0<kotlin.Unit> r67, androidx.compose.runtime.Composer r68, final int r69, final int r70) {
        /*
            Method dump skipped, instruction units count: 752
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.ProfilScreenKt.m8762ProfileMenuItemcf5BqRc(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, long, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }
}
