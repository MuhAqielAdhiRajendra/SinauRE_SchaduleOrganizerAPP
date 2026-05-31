package androidx.compose.foundation.text;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.os.HandlerCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: BasicSecureTextField.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\r\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0004\u001a\b\u0010\u0010\u001a\u00020\u0011H\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"0\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012²\u0006\n\u0010\u0013\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"TAG", "", "platformAllowsRevealLastTyped", "", "(Landroidx/compose/runtime/Composer;I)Z", "DefaultContentResolverForSecureTextField", "Lkotlin/Function1;", "Landroid/content/Context;", "Landroidx/compose/foundation/text/ContentResolverForSecureTextField;", "contentResolverForSecureTextField", "getContentResolverForSecureTextField$annotations", "()V", "getContentResolverForSecureTextField", "()Lkotlin/jvm/functions/Function1;", "setContentResolverForSecureTextField", "(Lkotlin/jvm/functions/Function1;)V", "resetContentResolverForSecureTextField", "", "foundation", "state"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BasicSecureTextField_androidKt {
    private static final String TAG = "BasicSecureTextField";
    private static final Function1<Context, ContentResolverForSecureTextField> DefaultContentResolverForSecureTextField = new Function1() { // from class: androidx.compose.foundation.text.BasicSecureTextField_androidKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return BasicSecureTextField_androidKt.DefaultContentResolverForSecureTextField$lambda$0((Context) obj);
        }
    };
    private static Function1<? super Context, ? extends ContentResolverForSecureTextField> contentResolverForSecureTextField = DefaultContentResolverForSecureTextField;

    public static /* synthetic */ void getContentResolverForSecureTextField$annotations() {
    }

    public static final boolean platformAllowsRevealLastTyped(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1015272320, "C(platformAllowsRevealLastTyped)39@1443L7,41@1478L119,44@1615L60,46@1711L263,53@2024L242,53@1979L287:BasicSecureTextField.android.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1015272320, $changed, -1, "androidx.compose.foundation.text.platformAllowsRevealLastTyped (BasicSecureTextField.android.kt:38)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) objConsume;
        Object obj = contentResolverForSecureTextField;
        ComposerKt.sourceInformationMarkerStart($composer, -719119593, "CC(remember):BasicSecureTextField.android.kt#9igjgp");
        boolean invalid$iv = $composer.changed(obj) | $composer.changed(context);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = (ContentResolverForSecureTextField) contentResolverForSecureTextField.invoke(context);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final ContentResolverForSecureTextField resolver = (ContentResolverForSecureTextField) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -719115268, "CC(remember):BasicSecureTextField.android.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(resolver);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(resolver.getShowPassword()), null, 2, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        final MutableState state$delegate = (MutableState) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -719111993, "CC(remember):BasicSecureTextField.android.kt#9igjgp");
        boolean invalid$iv3 = $composer.changed(resolver);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            final Handler handlerCreateAsync = HandlerCompat.createAsync(Looper.getMainLooper());
            Object value$iv3 = new ContentObserver(handlerCreateAsync) { // from class: androidx.compose.foundation.text.BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1
                @Override // android.database.ContentObserver
                public void onChange(boolean selfChange) {
                    BasicSecureTextField_androidKt.platformAllowsRevealLastTyped$lambda$3(state$delegate, resolver.getShowPassword());
                }
            };
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        final BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1 settingsObserver = (BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1) it$iv3;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -719101998, "CC(remember):BasicSecureTextField.android.kt#9igjgp");
        boolean invalid$iv4 = $composer.changedInstance(resolver) | $composer.changedInstance(settingsObserver);
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
            Object value$iv4 = new Function1() { // from class: androidx.compose.foundation.text.BasicSecureTextField_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return BasicSecureTextField_androidKt.platformAllowsRevealLastTyped$lambda$5$0(resolver, settingsObserver, (DisposableEffectScope) obj2);
                }
            };
            $composer.updateRememberedValue(value$iv4);
            it$iv4 = value$iv4;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(resolver, settingsObserver, (Function1) it$iv4, $composer, 0);
        boolean zPlatformAllowsRevealLastTyped$lambda$2 = platformAllowsRevealLastTyped$lambda$2(state$delegate);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return zPlatformAllowsRevealLastTyped$lambda$2;
    }

    private static final boolean platformAllowsRevealLastTyped$lambda$2(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void platformAllowsRevealLastTyped$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult platformAllowsRevealLastTyped$lambda$5$0(final ContentResolverForSecureTextField $resolver, final BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$settingsObserver$1$1 $settingsObserver, DisposableEffectScope $this$DisposableEffect) {
        $resolver.registerContentObserver(Settings.System.getUriFor("show_password"), false, $settingsObserver);
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.BasicSecureTextField_androidKt$platformAllowsRevealLastTyped$lambda$5$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $resolver.unregisterContentObserver($settingsObserver);
            }
        };
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.foundation.text.BasicSecureTextField_androidKt$DefaultContentResolverForSecureTextField$1$1] */
    static final BasicSecureTextField_androidKt$DefaultContentResolverForSecureTextField$1$1 DefaultContentResolverForSecureTextField$lambda$0(Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        return new ContentResolverForSecureTextField() { // from class: androidx.compose.foundation.text.BasicSecureTextField_androidKt$DefaultContentResolverForSecureTextField$1$1
            @Override // androidx.compose.foundation.text.ContentResolverForSecureTextField
            public void registerContentObserver(Uri uri, boolean notifyForDescendants, ContentObserver observer) {
                contentResolver.registerContentObserver(uri, notifyForDescendants, observer);
            }

            @Override // androidx.compose.foundation.text.ContentResolverForSecureTextField
            public void unregisterContentObserver(ContentObserver observer) {
                contentResolver.unregisterContentObserver(observer);
            }

            @Override // androidx.compose.foundation.text.ContentResolverForSecureTextField
            public boolean getShowPassword() {
                try {
                    return Settings.System.getInt(contentResolver, "show_password") > 0;
                } catch (Exception e) {
                    Log.w("BasicSecureTextField", "Failed to fetch show password setting, using value: true", e);
                    return true;
                }
            }
        };
    }

    public static final Function1<Context, ContentResolverForSecureTextField> getContentResolverForSecureTextField() {
        return contentResolverForSecureTextField;
    }

    public static final void setContentResolverForSecureTextField(Function1<? super Context, ? extends ContentResolverForSecureTextField> function1) {
        contentResolverForSecureTextField = function1;
    }

    public static final void resetContentResolverForSecureTextField() {
        contentResolverForSecureTextField = DefaultContentResolverForSecureTextField;
    }
}
