package com.example.scheduleorganizer.ui;

import android.content.Context;
import android.net.Uri;
import androidx.autofill.HintConstants;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.example.scheduleorganizer.data.AppRepository;
import com.example.scheduleorganizer.data.entity.Course;
import com.example.scheduleorganizer.data.entity.Schedule;
import com.example.scheduleorganizer.data.entity.Task;
import com.example.scheduleorganizer.data.entity.UserProfile;
import com.example.scheduleorganizer.ui.model.ChatMessage;
import com.example.scheduleorganizer.util.BackupManager;
import com.example.scheduleorganizer.util.ChatHistoryStore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: MainViewModel.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u001fJ\u000e\u00106\u001a\u0002042\u0006\u00105\u001a\u00020\u001fJ\u0006\u00106\u001a\u000204J\u0016\u00107\u001a\u0002042\u0006\u00105\u001a\u00020\u001f2\u0006\u00108\u001a\u00020&J\u0016\u00109\u001a\u0002042\u0006\u00105\u001a\u00020\u001f2\u0006\u0010:\u001a\u00020;J\u0016\u0010<\u001a\u0002042\u0006\u00105\u001a\u00020\u001f2\u0006\u0010:\u001a\u00020;J \u0010=\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u00020\u001f2\u0006\u0010:\u001a\u00020;H\u0086@¢\u0006\u0002\u0010>J\u001e\u0010?\u001a\u0002042\u0006\u00105\u001a\u00020\u001f2\u0006\u0010:\u001a\u00020;H\u0086@¢\u0006\u0002\u0010>J\u000e\u0010\u0015\u001a\u0002042\u0006\u0010@\u001a\u00020\u0010J\u0006\u0010A\u001a\u000204J\u0006\u0010B\u001a\u000204J\u0016\u0010R\u001a\u0002042\u0006\u0010S\u001a\u00020&2\u0006\u0010T\u001a\u00020\u0010J\u000e\u0010U\u001a\u0002042\u0006\u0010V\u001a\u00020FJ.\u0010W\u001a\u00020\n2\u0006\u0010X\u001a\u00020&2\u0006\u0010Y\u001a\u00020&2\u0006\u0010Z\u001a\u00020&2\u0006\u0010[\u001a\u00020&H\u0086@¢\u0006\u0002\u0010\\J\u000e\u0010]\u001a\u0002042\u0006\u0010^\u001a\u00020JJ\u000e\u0010_\u001a\u0002042\u0006\u0010^\u001a\u00020JJ0\u0010`\u001a\u00020\n2\u0006\u0010X\u001a\u00020&2\b\u0010a\u001a\u0004\u0018\u00010\n2\u0006\u0010b\u001a\u00020\n2\u0006\u0010c\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010dJ\u000e\u0010e\u001a\u0002042\u0006\u0010f\u001a\u00020MJ\u000e\u0010g\u001a\u0002042\u0006\u0010f\u001a\u00020MJ\u000e\u0010h\u001a\u0002042\u0006\u0010S\u001a\u00020&J\u0006\u0010i\u001a\u000204J\u000e\u0010j\u001a\u0002042\u0006\u0010:\u001a\u00020;J\u000e\u0010k\u001a\u0002042\u0006\u0010:\u001a\u00020;J\u0006\u0010l\u001a\u000204R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u000eR \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001b\u0010\u000eR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020&0(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,8F¢\u0006\u0006\u001a\u0004\b/\u00100R \u00101\u001a\b\u0012\u0004\u0012\u00020\u001a0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\f\"\u0004\b2\u0010\u000eR\u001d\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0E0D¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u001d\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0E0D¢\u0006\b\n\u0000\u001a\u0004\bK\u0010HR\u001d\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020M0E0D¢\u0006\b\n\u0000\u001a\u0004\bN\u0010HR\u0019\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010P0D¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010H¨\u0006m"}, d2 = {"Lcom/example/scheduleorganizer/ui/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/scheduleorganizer/data/AppRepository;", "backupManager", "Lcom/example/scheduleorganizer/util/BackupManager;", "<init>", "(Lcom/example/scheduleorganizer/data/AppRepository;Lcom/example/scheduleorganizer/util/BackupManager;)V", "selectedCourseId", "Landroidx/compose/runtime/MutableState;", "", "getSelectedCourseId", "()Landroidx/compose/runtime/MutableState;", "setSelectedCourseId", "(Landroidx/compose/runtime/MutableState;)V", "themeMode", "", "getThemeMode", "setThemeMode", "focusDuration", "getFocusDuration", "setFocusDuration", "focusTimeRemaining", "getFocusTimeRemaining", "setFocusTimeRemaining", "isFocusRunning", "", "setFocusRunning", "focusJob", "Lkotlinx/coroutines/Job;", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "_uiEvents", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "uiEvents", "Lkotlinx/coroutines/flow/SharedFlow;", "getUiEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "_messages", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lcom/example/scheduleorganizer/ui/model/ChatMessage;", "messages", "getMessages", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", "isChatLoading", "setChatLoading", "loadChatHistory", "", "context", "clearConversationHistory", "sendMessage", "text", "exportChatHistory", "uri", "Landroid/net/Uri;", "importChatHistory", "exportChatHistoryBlocking", "(Landroid/content/Context;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importChatHistoryBlocking", "minutes", "startFocusSession", "resetFocusSession", "allCourses", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/scheduleorganizer/data/entity/Course;", "getAllCourses", "()Lkotlinx/coroutines/flow/StateFlow;", "allSchedules", "Lcom/example/scheduleorganizer/data/entity/Schedule;", "getAllSchedules", "allTasks", "Lcom/example/scheduleorganizer/data/entity/Task;", "getAllTasks", "userProfile", "Lcom/example/scheduleorganizer/data/entity/UserProfile;", "getUserProfile", "insertCourse", HintConstants.AUTOFILL_HINT_NAME, TypedValues.Custom.S_COLOR, "deleteCourse", "course", "insertSchedule", "title", "category", "time", "days", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSchedule", "schedule", "deleteSchedule", "insertTask", "courseId", "dueDate", "priority", "(Ljava/lang/String;Ljava/lang/Long;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTask", "task", "deleteTask", "insertUserProfile", "checkConsistency", "exportData", "importData", "resetData", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MainViewModel extends ViewModel {
    public static final int $stable = 8;
    private final SnapshotStateList<ChatMessage> _messages;
    private final MutableSharedFlow<String> _uiEvents;
    private final StateFlow<List<Course>> allCourses;
    private final StateFlow<List<Schedule>> allSchedules;
    private final StateFlow<List<Task>> allTasks;
    private Context appContext;
    private final BackupManager backupManager;
    private MutableState<Integer> focusDuration;
    private Job focusJob;
    private MutableState<Integer> focusTimeRemaining;
    private MutableState<Boolean> isChatLoading;
    private MutableState<Boolean> isFocusRunning;
    private final AppRepository repository;
    private MutableState<Long> selectedCourseId;
    private MutableState<Integer> themeMode;
    private final SharedFlow<String> uiEvents;
    private final StateFlow<UserProfile> userProfile;

    public MainViewModel(AppRepository repository, BackupManager backupManager) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(backupManager, "backupManager");
        this.repository = repository;
        this.backupManager = backupManager;
        this.selectedCourseId = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.themeMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
        this.focusDuration = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(25, null, 2, null);
        this.focusTimeRemaining = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Integer.valueOf(ProgressIndicatorKt.CircularAnimationAdditionalRotationDelay), null, 2, null);
        this.isFocusRunning = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this._uiEvents = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this.uiEvents = FlowKt.asSharedFlow(this._uiEvents);
        this._messages = SnapshotStateKt.mutableStateListOf();
        this.isChatLoading = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.allCourses = FlowKt.stateIn(this.repository.getAllCourses(), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0L, 2, null), CollectionsKt.emptyList());
        this.allSchedules = FlowKt.stateIn(this.repository.getAllSchedules(), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0L, 2, null), CollectionsKt.emptyList());
        this.allTasks = FlowKt.stateIn(this.repository.getAllTasks(), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0L, 2, null), CollectionsKt.emptyList());
        this.userProfile = FlowKt.stateIn(this.repository.getUserProfile(), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0L, 2, null), null);
    }

    public final MutableState<Long> getSelectedCourseId() {
        return this.selectedCourseId;
    }

    public final void setSelectedCourseId(MutableState<Long> mutableState) {
        Intrinsics.checkNotNullParameter(mutableState, "<set-?>");
        this.selectedCourseId = mutableState;
    }

    public final MutableState<Integer> getThemeMode() {
        return this.themeMode;
    }

    public final void setThemeMode(MutableState<Integer> mutableState) {
        Intrinsics.checkNotNullParameter(mutableState, "<set-?>");
        this.themeMode = mutableState;
    }

    public final MutableState<Integer> getFocusDuration() {
        return this.focusDuration;
    }

    public final void setFocusDuration(MutableState<Integer> mutableState) {
        Intrinsics.checkNotNullParameter(mutableState, "<set-?>");
        this.focusDuration = mutableState;
    }

    public final MutableState<Integer> getFocusTimeRemaining() {
        return this.focusTimeRemaining;
    }

    public final void setFocusTimeRemaining(MutableState<Integer> mutableState) {
        Intrinsics.checkNotNullParameter(mutableState, "<set-?>");
        this.focusTimeRemaining = mutableState;
    }

    public final MutableState<Boolean> isFocusRunning() {
        return this.isFocusRunning;
    }

    public final void setFocusRunning(MutableState<Boolean> mutableState) {
        Intrinsics.checkNotNullParameter(mutableState, "<set-?>");
        this.isFocusRunning = mutableState;
    }

    public final Context getAppContext() {
        return this.appContext;
    }

    public final void setAppContext(Context context) {
        this.appContext = context;
    }

    public final SharedFlow<String> getUiEvents() {
        return this.uiEvents;
    }

    public final SnapshotStateList<ChatMessage> getMessages() {
        return this._messages;
    }

    public final MutableState<Boolean> isChatLoading() {
        return this.isChatLoading;
    }

    public final void setChatLoading(MutableState<Boolean> mutableState) {
        Intrinsics.checkNotNullParameter(mutableState, "<set-?>");
        this.isChatLoading = mutableState;
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$loadChatHistory$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$loadChatHistory$1", f = "MainViewModel.kt", i = {}, l = {64}, m = "invokeSuspend", n = {}, nl = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT}, s = {}, v = 2)
    static final class C03931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;
        final /* synthetic */ MainViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03931(Context context, MainViewModel mainViewModel, Continuation<? super C03931> continuation) {
            super(2, continuation);
            this.$context = context;
            this.this$0 = mainViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03931(this.$context, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            Object objLoad;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        objLoad = ChatHistoryStore.INSTANCE.load(this.$context, this);
                        if (objLoad == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        objLoad = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Iterable<Pair> history = (List) objLoad;
                this.this$0._messages.clear();
                MainViewModel mainViewModel = this.this$0;
                for (Pair pair : history) {
                    String str = (String) pair.component1();
                    String str2 = (String) pair.component2();
                    mainViewModel._messages.add(new ChatMessage(str, true));
                    mainViewModel._messages.add(new ChatMessage(str2, false));
                }
            } catch (Exception e) {
            }
            return Unit.INSTANCE;
        }
    }

    public final void loadChatHistory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03931(context, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$clearConversationHistory$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$clearConversationHistory$1", f = "MainViewModel.kt", i = {}, l = {80}, m = "invokeSuspend", n = {}, nl = {81}, s = {}, v = 2)
    static final class C03821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03821(Context context, Continuation<? super C03821> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03821(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (ChatHistoryStore.INSTANCE.clear(this.$context, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Exception e) {
            }
            return Unit.INSTANCE;
        }
    }

    public final void clearConversationHistory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this._messages.clear();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03821(context, null), 3, null);
    }

    public final void clearConversationHistory() {
        Context context = this.appContext;
        if (context != null) {
            clearConversationHistory(context);
        }
    }

    public final void sendMessage(Context context, String text) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        String prompt = StringsKt.trim((CharSequence) text).toString();
        if (StringsKt.isBlank(prompt)) {
            return;
        }
        this._messages.add(new ChatMessage(prompt, true));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03951(context, prompt, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$sendMessage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$sendMessage$1", f = "MainViewModel.kt", i = {0, 1, 1, 1}, l = {100, 126}, m = "invokeSuspend", n = {"provider", "provider", "response", "pairs"}, nl = {119, WorkQueueKt.MASK}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 2)
    static final class C03951 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ String $prompt;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03951(Context context, String str, Continuation<? super C03951> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$prompt = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03951(this.$context, this.$prompt, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03951) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x00ad A[Catch: Exception -> 0x0120, all -> 0x0133, TryCatch #0 {Exception -> 0x0120, blocks: (B:21:0x0088, B:22:0x00a7, B:24:0x00ad, B:26:0x00c3), top: B:55:0x0088 }] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0119 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x011a  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r23) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 406
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.MainViewModel.C03951.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$exportChatHistory$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$exportChatHistory$1", f = "MainViewModel.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, nl = {142}, s = {}, v = 2)
    static final class C03861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        int label;
        final /* synthetic */ MainViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03861(Context context, Uri uri, MainViewModel mainViewModel, Continuation<? super C03861> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
            this.this$0 = mainViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03861(this.$context, this.$uri, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objLoad;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        objLoad = ChatHistoryStore.INSTANCE.load(this.$context, this);
                        if (objLoad == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        objLoad = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Iterable history = (List) objLoad;
                Gson gson = new Gson();
                Iterable<Pair> iterable = history;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Pair pair : iterable) {
                    arrayList.add(CollectionsKt.listOf((Object[]) new String[]{pair.getFirst(), pair.getSecond()}));
                }
                String json = gson.toJson((List) arrayList);
                OutputStream outputStreamOpenOutputStream = this.$context.getContentResolver().openOutputStream(this.$uri);
                if (outputStreamOpenOutputStream != null) {
                    OutputStreamWriter outputStreamWriter = outputStreamOpenOutputStream;
                    try {
                        outputStreamWriter = new OutputStreamWriter(outputStreamWriter, Charsets.UTF_8);
                        try {
                            outputStreamWriter.write(json);
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(outputStreamWriter, null);
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(outputStreamWriter, null);
                        } finally {
                        }
                    } finally {
                    }
                }
            } catch (Exception e) {
                this.this$0._uiEvents.tryEmit("Gagal ekspor riwayat chat: " + e.getMessage());
            }
            return Unit.INSTANCE;
        }
    }

    public final void exportChatHistory(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03861(context, uri, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$importChatHistory$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$importChatHistory$1", f = "MainViewModel.kt", i = {0, 0, 0, 0}, l = {169}, m = "invokeSuspend", n = {"json", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "rawList", "pairs"}, nl = {170}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C03881 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MainViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03881(Context context, Uri uri, MainViewModel mainViewModel, Continuation<? super C03881> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
            this.this$0 = mainViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03881(this.$context, this.$uri, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03881) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            List rawList;
            Pair pair;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
            } catch (Exception e) {
                this.this$0._uiEvents.tryEmit("Gagal impor riwayat chat: " + e.getMessage());
            }
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    InputStream inputStreamOpenInputStream = this.$context.getContentResolver().openInputStream(this.$uri);
                    if (inputStreamOpenInputStream != null) {
                        BufferedReader bufferedReader = inputStreamOpenInputStream;
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(bufferedReader, Charsets.UTF_8));
                            try {
                                String json = TextStreamsKt.readText(bufferedReader);
                                CloseableKt.closeFinally(bufferedReader, null);
                                CloseableKt.closeFinally(bufferedReader, null);
                                if (json != null) {
                                    Type type = new TypeToken<List<? extends List<? extends String>>>() { // from class: com.example.scheduleorganizer.ui.MainViewModel$importChatHistory$1$type$1
                                    }.getType();
                                    Object objFromJson = new Gson().fromJson(json, type);
                                    Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                                    List<List> rawList2 = (List) objFromJson;
                                    Collection arrayList = new ArrayList();
                                    for (List list : rawList2) {
                                        if (list.size() >= 2) {
                                            rawList = rawList2;
                                            pair = TuplesKt.to(list.get(0), list.get(1));
                                        } else {
                                            rawList = rawList2;
                                            pair = null;
                                        }
                                        if (pair != null) {
                                            arrayList.add(pair);
                                        }
                                        rawList2 = rawList;
                                    }
                                    List rawList3 = rawList2;
                                    List<Pair> pairs = (List) arrayList;
                                    this.this$0._messages.clear();
                                    MainViewModel mainViewModel = this.this$0;
                                    for (Pair pair2 : pairs) {
                                        String str = (String) pair2.component1();
                                        String str2 = (String) pair2.component2();
                                        mainViewModel._messages.add(new ChatMessage(str, true));
                                        mainViewModel._messages.add(new ChatMessage(str2, false));
                                    }
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(json);
                                    this.L$1 = SpillingKt.nullOutSpilledVariable(type);
                                    this.L$2 = SpillingKt.nullOutSpilledVariable(rawList3);
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(pairs);
                                    this.label = 1;
                                    return ChatHistoryStore.INSTANCE.save(this.$context, pairs, this) == coroutine_suspended ? coroutine_suspended : Unit.INSTANCE;
                                }
                            } finally {
                            }
                        } finally {
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void importChatHistory(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03881(context, uri, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$exportChatHistoryBlocking$2, reason: invalid class name */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$exportChatHistoryBlocking$2", f = "MainViewModel.kt", i = {1}, l = {178, 186}, m = "invokeSuspend", n = {"e"}, nl = {179, 187}, s = {"L$0"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        Object L$0;
        int label;
        final /* synthetic */ MainViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, Uri uri, MainViewModel mainViewModel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
            this.this$0 = mainViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$context, this.$uri, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0060 A[Catch: Exception -> 0x0024, LOOP:0: B:16:0x005a->B:18:0x0060, LOOP_END, TryCatch #4 {Exception -> 0x0024, blocks: (B:7:0x001e, B:15:0x003b, B:16:0x005a, B:18:0x0060, B:19:0x0080, B:21:0x0099, B:25:0x00bb, B:37:0x00cd, B:38:0x00d0, B:12:0x002b, B:22:0x009c, B:24:0x00b5, B:31:0x00c5, B:32:0x00c8, B:23:0x00ab, B:29:0x00c3, B:35:0x00cb), top: B:51:0x000a, inners: #2, #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0099 A[Catch: Exception -> 0x0024, TRY_LEAVE, TryCatch #4 {Exception -> 0x0024, blocks: (B:7:0x001e, B:15:0x003b, B:16:0x005a, B:18:0x0060, B:19:0x0080, B:21:0x0099, B:25:0x00bb, B:37:0x00cd, B:38:0x00d0, B:12:0x002b, B:22:0x009c, B:24:0x00b5, B:31:0x00c5, B:32:0x00c8, B:23:0x00ab, B:29:0x00c3, B:35:0x00cb), top: B:51:0x000a, inners: #2, #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instruction units count: 270
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.MainViewModel.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object exportChatHistoryBlocking(Context context, Uri uri, Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(context, uri, this, null), continuation);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$importChatHistoryBlocking$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$importChatHistoryBlocking$2", f = "MainViewModel.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2}, l = {199, ComposerKt.referenceKey, 208}, m = "invokeSuspend", n = {"json", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "rawList", "pairs", "json", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "rawList", "pairs", "e"}, nl = {ComposerKt.referenceKey, ComposerKt.reuseKey, 210}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0"}, v = 2)
    static final class C03892 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MainViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03892(Context context, Uri uri, MainViewModel mainViewModel, Continuation<? super C03892> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
            this.this$0 = mainViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03892(this.$context, this.$uri, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03892) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:39:0x015b A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instruction units count: 440
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.MainViewModel.C03892.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$importChatHistoryBlocking$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: MainViewModel.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$importChatHistoryBlocking$2$1", f = "MainViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ List<Pair<String, String>> $pairs;
            int label;
            final /* synthetic */ MainViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(MainViewModel mainViewModel, List<Pair<String, String>> list, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = mainViewModel;
                this.$pairs = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$pairs, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.this$0._messages.clear();
                        Iterable<Pair> iterable = this.$pairs;
                        MainViewModel mainViewModel = this.this$0;
                        for (Pair pair : iterable) {
                            String str = (String) pair.component1();
                            String str2 = (String) pair.component2();
                            mainViewModel._messages.add(new ChatMessage(str, true));
                            mainViewModel._messages.add(new ChatMessage(str2, false));
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final Object importChatHistoryBlocking(Context context, Uri uri, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C03892(context, uri, this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final void setFocusDuration(int minutes) {
        this.focusDuration.setValue(Integer.valueOf(RangesKt.coerceIn(minutes, 1, 120)));
        if (!this.isFocusRunning.getValue().booleanValue()) {
            this.focusTimeRemaining.setValue(Integer.valueOf(this.focusDuration.getValue().intValue() * 60));
        }
    }

    public final void startFocusSession() {
        if (this.isFocusRunning.getValue().booleanValue()) {
            Job job = this.focusJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.isFocusRunning.setValue(false);
            return;
        }
        this.isFocusRunning.setValue(true);
        this.focusJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03961(null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$startFocusSession$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$startFocusSession$1", f = "MainViewModel.kt", i = {}, l = {227}, m = "invokeSuspend", n = {}, nl = {228}, s = {}, v = 2)
    static final class C03961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03961(Continuation<? super C03961> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03961(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0038 -> B:13:0x003b). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 1
                switch(r1) {
                    case 0: goto L17;
                    case 1: goto L12;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L12:
                kotlin.ResultKt.throwOnFailure(r7)
                r1 = r6
                goto L3b
            L17:
                kotlin.ResultKt.throwOnFailure(r7)
                r1 = r6
            L1b:
                com.example.scheduleorganizer.ui.MainViewModel r3 = com.example.scheduleorganizer.ui.MainViewModel.this
                androidx.compose.runtime.MutableState r3 = r3.getFocusTimeRemaining()
                java.lang.Object r3 = r3.getValue()
                java.lang.Number r3 = (java.lang.Number) r3
                int r3 = r3.intValue()
                if (r3 <= 0) goto L54
                r3 = r1
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r1.label = r2
                r4 = 1000(0x3e8, double:4.94E-321)
                java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r4, r3)
                if (r3 != r0) goto L3b
                return r0
            L3b:
                com.example.scheduleorganizer.ui.MainViewModel r3 = com.example.scheduleorganizer.ui.MainViewModel.this
                androidx.compose.runtime.MutableState r3 = r3.getFocusTimeRemaining()
                java.lang.Object r4 = r3.getValue()
                java.lang.Number r4 = (java.lang.Number) r4
                int r4 = r4.intValue()
                int r4 = r4 - r2
                java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
                r3.setValue(r4)
                goto L1b
            L54:
                com.example.scheduleorganizer.ui.MainViewModel r0 = com.example.scheduleorganizer.ui.MainViewModel.this
                androidx.compose.runtime.MutableState r0 = r0.isFocusRunning()
                r2 = 0
                java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                r0.setValue(r2)
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.MainViewModel.C03961.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void resetFocusSession() {
        Job job = this.focusJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.isFocusRunning.setValue(false);
        this.focusTimeRemaining.setValue(Integer.valueOf(this.focusDuration.getValue().intValue() * 60));
    }

    public final StateFlow<List<Course>> getAllCourses() {
        return this.allCourses;
    }

    public final StateFlow<List<Schedule>> getAllSchedules() {
        return this.allSchedules;
    }

    public final StateFlow<List<Task>> getAllTasks() {
        return this.allTasks;
    }

    public final StateFlow<UserProfile> getUserProfile() {
        return this.userProfile;
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$insertCourse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$insertCourse$1", f = "MainViewModel.kt", i = {}, l = {261}, m = "invokeSuspend", n = {}, nl = {262}, s = {}, v = 2)
    static final class C03911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $color;
        final /* synthetic */ String $name;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03911(String str, int i, Continuation<? super C03911> continuation) {
            super(2, continuation);
            this.$name = str;
            this.$color = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03911(this.$name, this.$color, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03911) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.repository.insertCourse(new Course(0L, this.$name, this.$color, 1, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void insertCourse(String name, int color) {
        Intrinsics.checkNotNullParameter(name, "name");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03911(name, color, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$deleteCourse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$deleteCourse$1", f = "MainViewModel.kt", i = {}, l = {267}, m = "invokeSuspend", n = {}, nl = {268}, s = {}, v = 2)
    static final class C03831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Course $course;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03831(Course course, Continuation<? super C03831> continuation) {
            super(2, continuation);
            this.$course = course;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03831(this.$course, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.repository.deleteCourse(this.$course, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void deleteCourse(Course course) {
        Intrinsics.checkNotNullParameter(course, "course");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03831(course, null), 3, null);
    }

    public final Object insertSchedule(String title, String category, String time, String days, Continuation<? super Long> continuation) {
        return this.repository.insertSchedule(new Schedule(0L, title, category, time, days, false, 33, null), continuation);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$updateSchedule$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$updateSchedule$1", f = "MainViewModel.kt", i = {}, l = {277}, m = "invokeSuspend", n = {}, nl = {278}, s = {}, v = 2)
    static final class C03971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Schedule $schedule;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03971(Schedule schedule, Continuation<? super C03971> continuation) {
            super(2, continuation);
            this.$schedule = schedule;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03971(this.$schedule, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.repository.updateSchedule(this.$schedule, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateSchedule(Schedule schedule) {
        Intrinsics.checkNotNullParameter(schedule, "schedule");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03971(schedule, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$deleteSchedule$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$deleteSchedule$1", f = "MainViewModel.kt", i = {}, l = {283}, m = "invokeSuspend", n = {}, nl = {284}, s = {}, v = 2)
    static final class C03841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Schedule $schedule;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03841(Schedule schedule, Continuation<? super C03841> continuation) {
            super(2, continuation);
            this.$schedule = schedule;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03841(this.$schedule, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.repository.deleteSchedule(this.$schedule, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void deleteSchedule(Schedule schedule) {
        Intrinsics.checkNotNullParameter(schedule, "schedule");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03841(schedule, null), 3, null);
    }

    public final Object insertTask(String title, Long courseId, long dueDate, int priority, Continuation<? super Long> continuation) {
        return this.repository.insertTask(new Task(0L, title, courseId, dueDate, false, priority, 17, null), continuation);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$updateTask$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$updateTask$1", f = "MainViewModel.kt", i = {}, l = {293}, m = "invokeSuspend", n = {}, nl = {294}, s = {}, v = 2)
    static final class C03981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Task $task;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03981(Task task, Continuation<? super C03981> continuation) {
            super(2, continuation);
            this.$task = task;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03981(this.$task, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.repository.updateTask(this.$task, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateTask(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03981(task, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$deleteTask$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$deleteTask$1", f = "MainViewModel.kt", i = {}, l = {299}, m = "invokeSuspend", n = {}, nl = {300}, s = {}, v = 2)
    static final class C03851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Task $task;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03851(Task task, Continuation<? super C03851> continuation) {
            super(2, continuation);
            this.$task = task;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03851(this.$task, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.repository.deleteTask(this.$task, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void deleteTask(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03851(task, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$insertUserProfile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$insertUserProfile$1", f = "MainViewModel.kt", i = {1}, l = {305, 306}, m = "invokeSuspend", n = {"current"}, nl = {306, 307}, s = {"L$0"}, v = 2)
    static final class C03921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $name;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03921(String str, Continuation<? super C03921> continuation) {
            super(2, continuation);
            this.$name = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03921(this.$name, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0075 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0076  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r12.label
                switch(r1) {
                    case 0: goto L1e;
                    case 1: goto L19;
                    case 2: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L11:
                java.lang.Object r0 = r12.L$0
                com.example.scheduleorganizer.data.entity.UserProfile r0 = (com.example.scheduleorganizer.data.entity.UserProfile) r0
                kotlin.ResultKt.throwOnFailure(r13)
                goto L77
            L19:
                kotlin.ResultKt.throwOnFailure(r13)
                r1 = r13
                goto L38
            L1e:
                kotlin.ResultKt.throwOnFailure(r13)
                com.example.scheduleorganizer.ui.MainViewModel r1 = com.example.scheduleorganizer.ui.MainViewModel.this
                com.example.scheduleorganizer.data.AppRepository r1 = com.example.scheduleorganizer.ui.MainViewModel.access$getRepository$p(r1)
                kotlinx.coroutines.flow.Flow r1 = r1.getUserProfile()
                r2 = r12
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = 1
                r12.label = r3
                java.lang.Object r1 = kotlinx.coroutines.flow.FlowKt.first(r1, r2)
                if (r1 != r0) goto L38
                return r0
            L38:
                com.example.scheduleorganizer.data.entity.UserProfile r1 = (com.example.scheduleorganizer.data.entity.UserProfile) r1
                if (r1 != 0) goto L4d
                com.example.scheduleorganizer.data.entity.UserProfile r2 = new com.example.scheduleorganizer.data.entity.UserProfile
                java.lang.String r4 = r12.$name
                r10 = 61
                r11 = 0
                r3 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r2.<init>(r3, r4, r5, r6, r7, r8, r10, r11)
                r1 = r2
            L4d:
                r2 = r1
                com.example.scheduleorganizer.ui.MainViewModel r1 = com.example.scheduleorganizer.ui.MainViewModel.this
                com.example.scheduleorganizer.data.AppRepository r1 = com.example.scheduleorganizer.ui.MainViewModel.access$getRepository$p(r1)
                java.lang.String r4 = r12.$name
                r10 = 61
                r11 = 0
                r3 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                com.example.scheduleorganizer.data.entity.UserProfile r3 = com.example.scheduleorganizer.data.entity.UserProfile.copy$default(r2, r3, r4, r5, r6, r7, r8, r10, r11)
                r4 = r12
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
                r12.L$0 = r5
                r5 = 2
                r12.label = r5
                java.lang.Object r1 = r1.insertUserProfile(r3, r4)
                if (r1 != r0) goto L76
                return r0
            L76:
                r0 = r2
            L77:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.MainViewModel.C03921.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void insertUserProfile(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03921(name, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$checkConsistency$1, reason: invalid class name */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$checkConsistency$1", f = "MainViewModel.kt", i = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3}, l = {313, 326, 331, 333}, m = "invokeSuspend", n = {"profile", "now", "today", "lastLogin", "oneDayMs", "profile", "now", "today", "lastLogin", "oneDayMs", "newCount", "newBest", "profile", "now", "today", "lastLogin", "oneDayMs"}, nl = {314, 327, 333, 336}, s = {"L$0", "L$1", "J$0", "J$1", "J$2", "L$0", "L$1", "J$0", "J$1", "J$2", "I$0", "I$1", "L$0", "L$1", "J$0", "J$1", "J$2"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        long J$2;
        Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00b9  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0102  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r25) {
            /*
                Method dump skipped, instruction units count: 452
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.MainViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void checkConsistency() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$exportData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$exportData$1", f = "MainViewModel.kt", i = {}, l = {342}, m = "invokeSuspend", n = {}, nl = {343}, s = {}, v = 2)
    static final class C03871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Uri $uri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03871(Uri uri, Continuation<? super C03871> continuation) {
            super(2, continuation);
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03871(this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.backupManager.exportData(this.$uri, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void exportData(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03871(uri, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$importData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$importData$1", f = "MainViewModel.kt", i = {}, l = {348}, m = "invokeSuspend", n = {}, nl = {349}, s = {}, v = 2)
    static final class C03901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Uri $uri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03901(Uri uri, Continuation<? super C03901> continuation) {
            super(2, continuation);
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03901(this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.backupManager.importData(this.$uri, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void importData(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03901(uri, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ui.MainViewModel$resetData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$resetData$1", f = "MainViewModel.kt", i = {}, l = {354}, m = "invokeSuspend", n = {}, nl = {355}, s = {}, v = 2)
    static final class C03941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03941(Continuation<? super C03941> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C03941(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (MainViewModel.this.repository.resetDatabase(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void resetData() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C03941(null), 3, null);
    }
}
