package com.example.scheduleorganizer;

import android.app.AlarmManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import com.example.scheduleorganizer.data.AppDatabase;
import com.example.scheduleorganizer.data.AppRepository;
import com.example.scheduleorganizer.ui.MainViewModel;
import com.example.scheduleorganizer.ui.MainViewModelFactory;
import com.example.scheduleorganizer.util.BackupManager;
import com.example.scheduleorganizer.util.ConsistencyManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J\b\u0010\b\u001a\u00020\u0005H\u0002¨\u0006\t"}, d2 = {"Lcom/example/scheduleorganizer/MainActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "requestStartupPermissions", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MainActivity extends ComponentActivity {
    public static final int $stable = 8;

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConsistencyManager.INSTANCE.onAppOpened(this);
        requestStartupPermissions();
        AppDatabase database = AppDatabase.INSTANCE.getDatabase(this);
        AppRepository repository = new AppRepository(database.appDao());
        BackupManager backupManager = new BackupManager(this);
        final MainViewModelFactory factory = new MainViewModelFactory(repository, backupManager);
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-664613967, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.onCreate$lambda$0(factory, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$0(MainViewModelFactory $factory, Composer $composer, int $changed) {
        CreationExtras defaultViewModelCreationExtras;
        ComposerKt.sourceInformation($composer, "C60@2625L28,62@2700L60,62@2679L81,66@2774L31:MainActivity.kt#342o8p");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-664613967, $changed, -1, "com.example.scheduleorganizer.MainActivity.onCreate.<anonymous> (MainActivity.kt:60)");
            }
            MainViewModelFactory mainViewModelFactory = $factory;
            int i = MainViewModelFactory.$stable << 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1729797275, "CC(viewModel)N(viewModelStoreOwner,key,factory,extras)56@2573L7,67@2981L63:ViewModel.kt#3tja67");
            ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent($composer, 6);
            if (current == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            if (current instanceof HasDefaultViewModelProviderFactory) {
                defaultViewModelCreationExtras = ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras();
            } else {
                defaultViewModelCreationExtras = CreationExtras.Empty.INSTANCE;
            }
            ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(MainViewModel.class), current, (String) null, mainViewModelFactory, defaultViewModelCreationExtras, $composer, ((i << 3) & 112) | ((i << 3) & 896) | ((i << 3) & 7168) | (57344 & (i << 3)), 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            MainViewModel viewModel2 = (MainViewModel) viewModel;
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1256108717, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance(viewModel2);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new MainActivity$onCreate$1$1$1(viewModel2, null);
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue, $composer, 6);
            MainActivityKt.ScheduleOrganizerApp(viewModel2, $composer, MainViewModel.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private final void requestStartupPermissions() {
        boolean shouldExplain;
        SharedPreferences prefs = getSharedPreferences("app_setup_prefs", 0);
        if (prefs.getBoolean("requested_permissions", false)) {
            return;
        }
        prefs.edit().putBoolean("requested_permissions", true).apply();
        Iterable requiredPermissions = (List) new ArrayList();
        if (Build.VERSION.SDK_INT >= 33) {
            ((Collection) requiredPermissions).add("android.permission.POST_NOTIFICATIONS");
            ((Collection) requiredPermissions).add("android.permission.READ_MEDIA_AUDIO");
        } else {
            ((Collection) requiredPermissions).add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (!((Collection) requiredPermissions).isEmpty()) {
            Collection arrayList = new ArrayList();
            for (Object obj : requiredPermissions) {
                if (ContextCompat.checkSelfPermission(this, (String) obj) != 0) {
                    arrayList.add(obj);
                }
            }
            final List permissionsToRequest = (List) arrayList;
            if (!permissionsToRequest.isEmpty()) {
                final ActivityResultLauncher requestLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.example.scheduleorganizer.MainActivity$$ExternalSyntheticLambda1
                    @Override // androidx.activity.result.ActivityResultCallback
                    public final void onActivityResult(Object obj2) {
                        MainActivity.requestStartupPermissions$lambda$1((Map) obj2);
                    }
                });
                Intrinsics.checkNotNullExpressionValue(requestLauncher, "registerForActivityResult(...)");
                List list = permissionsToRequest;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (shouldShowRequestPermissionRationale((String) it.next())) {
                                shouldExplain = true;
                                break;
                            }
                        } else {
                            shouldExplain = false;
                            break;
                        }
                    }
                } else {
                    shouldExplain = false;
                }
                if (shouldExplain) {
                    new AlertDialog.Builder(this).setTitle("Izin Akses Penting").setMessage("Agar pengingat dan suara alarm bekerja maksimal, aplikasi perlu izin notifikasi dan audio.").setPositiveButton("Izinkan", new DialogInterface.OnClickListener() { // from class: com.example.scheduleorganizer.MainActivity$$ExternalSyntheticLambda2
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            requestLauncher.launch(permissionsToRequest.toArray(new String[0]));
                        }
                    }).setNegativeButton("Nanti", (DialogInterface.OnClickListener) null).show();
                } else {
                    requestLauncher.launch(permissionsToRequest.toArray(new String[0]));
                }
            }
        }
        try {
            Object systemService = getSystemService(NotificationCompat.CATEGORY_ALARM);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
            AlarmManager alarmManager = (AlarmManager) systemService;
            if (Build.VERSION.SDK_INT >= 31 && !alarmManager.canScheduleExactAlarms()) {
                new AlertDialog.Builder(this).setTitle("Izin Alarm Tepat Waktu").setMessage("Agar semua pengingat muncul tepat waktu, izinkan aplikasi menjadwalkan alarm persis.").setPositiveButton("Buka Pengaturan", new DialogInterface.OnClickListener() { // from class: com.example.scheduleorganizer.MainActivity$$ExternalSyntheticLambda3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.requestStartupPermissions$lambda$4(this.f$0, dialogInterface, i);
                    }
                }).setNegativeButton("Nanti", (DialogInterface.OnClickListener) null).show();
            }
        } catch (Exception e) {
        }
        try {
            Object systemService2 = getSystemService("power");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.os.PowerManager");
            PowerManager pm = (PowerManager) systemService2;
            if (!pm.isIgnoringBatteryOptimizations(getPackageName())) {
                new AlertDialog.Builder(this).setTitle("Pengaturan Baterai").setMessage("Agar pengingat terus muncul meski perangkat menghemat baterai, izinkan pengabaian optimisasi baterai.").setPositiveButton("Buka Pengaturan", new DialogInterface.OnClickListener() { // from class: com.example.scheduleorganizer.MainActivity$$ExternalSyntheticLambda4
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.requestStartupPermissions$lambda$5(this.f$0, dialogInterface, i);
                    }
                }).setNegativeButton("Nanti", (DialogInterface.OnClickListener) null).show();
            }
        } catch (Exception e2) {
        }
    }

    static final void requestStartupPermissions$lambda$1(Map map) {
    }

    static final void requestStartupPermissions$lambda$4(MainActivity this$0, DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.settings.REQUEST_SCHEDULE_EXACT_ALARM");
        this$0.startActivity(intent);
    }

    static final void requestStartupPermissions$lambda$5(MainActivity this$0, DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", Uri.parse("package:" + this$0.getPackageName()));
        this$0.startActivity(intent);
    }
}
