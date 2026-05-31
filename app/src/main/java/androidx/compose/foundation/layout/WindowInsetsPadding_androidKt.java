package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: WindowInsetsPadding.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\r\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0013\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0015\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0017\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0019\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u001b\u001a\u00020\u0001*\u00020\u0001\u001a>\u0010\u001d\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u0003¢\u0006\u0002\b\u00062\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0003\"\u001f\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"safeDrawingPadding", "Landroidx/compose/ui/Modifier;", "safeDrawingLambda", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/WindowInsetsHolder;", "Landroidx/compose/foundation/layout/WindowInsets;", "Lkotlin/ExtensionFunctionType;", "safeGesturesPadding", "safeGesturesLambda", "safeContentPadding", "safeContentLambda", "systemBarsPadding", "systemBarsLambda", "displayCutoutPadding", "displayCutoutLambda", "statusBarsPadding", "statusBarsLambda", "imePadding", "imeLambda", "navigationBarsPadding", "navigationBarsLambda", "captionBarPadding", "captionBarLambda", "waterfallPadding", "waterfallLambda", "systemGesturesPadding", "systemGesturesLambda", "mandatorySystemGesturesPadding", "mandatorySystemGesturesLambda", "windowInsetsPadding", "inspectorInfo", "Landroidx/compose/ui/platform/InspectorInfo;", "", "insetsCalculation", "foundation-layout"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class WindowInsetsPadding_androidKt {
    private static final Function1<WindowInsetsHolder, WindowInsets> safeDrawingLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getSafeDrawing();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> safeGesturesLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getSafeGestures();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> safeContentLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getSafeContent();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> systemBarsLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getSystemBars();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> displayCutoutLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getDisplayCutout();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> statusBarsLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getStatusBars();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> imeLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getIme();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> navigationBarsLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getNavigationBars();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> captionBarLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getCaptionBar();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> waterfallLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getWaterfall();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> systemGesturesLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getSystemGestures();
        }
    };
    private static final Function1<WindowInsetsHolder, WindowInsets> mandatorySystemGesturesLambda = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((WindowInsetsHolder) obj).getMandatorySystemGestures();
        }
    };

    public static final Modifier safeDrawingPadding(Modifier $this$safeDrawingPadding) {
        return windowInsetsPadding($this$safeDrawingPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$safeDrawingPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("safeDrawingPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), safeDrawingLambda);
    }

    public static final Modifier safeGesturesPadding(Modifier $this$safeGesturesPadding) {
        return windowInsetsPadding($this$safeGesturesPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$safeGesturesPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("safeGesturesPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), safeGesturesLambda);
    }

    public static final Modifier safeContentPadding(Modifier $this$safeContentPadding) {
        return windowInsetsPadding($this$safeContentPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$safeContentPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("safeContentPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), safeContentLambda);
    }

    public static final Modifier systemBarsPadding(Modifier $this$systemBarsPadding) {
        return windowInsetsPadding($this$systemBarsPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$systemBarsPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("systemBarsPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), systemBarsLambda);
    }

    public static final Modifier displayCutoutPadding(Modifier $this$displayCutoutPadding) {
        return windowInsetsPadding($this$displayCutoutPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$displayCutoutPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("displayCutoutPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), displayCutoutLambda);
    }

    public static final Modifier statusBarsPadding(Modifier $this$statusBarsPadding) {
        return windowInsetsPadding($this$statusBarsPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$statusBarsPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("statusBarsPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), statusBarsLambda);
    }

    public static final Modifier imePadding(Modifier $this$imePadding) {
        return windowInsetsPadding($this$imePadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$imePadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("imePadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), imeLambda);
    }

    public static final Modifier navigationBarsPadding(Modifier $this$navigationBarsPadding) {
        return windowInsetsPadding($this$navigationBarsPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$navigationBarsPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("navigationBarsPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), navigationBarsLambda);
    }

    public static final Modifier captionBarPadding(Modifier $this$captionBarPadding) {
        return windowInsetsPadding($this$captionBarPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$captionBarPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("captionBarPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), captionBarLambda);
    }

    public static final Modifier waterfallPadding(Modifier $this$waterfallPadding) {
        return windowInsetsPadding($this$waterfallPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$waterfallPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("waterfallPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), waterfallLambda);
    }

    public static final Modifier systemGesturesPadding(Modifier $this$systemGesturesPadding) {
        return windowInsetsPadding($this$systemGesturesPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$systemGesturesPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("systemGesturesPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), systemGesturesLambda);
    }

    public static final Modifier mandatorySystemGesturesPadding(Modifier $this$mandatorySystemGesturesPadding) {
        return windowInsetsPadding($this$mandatorySystemGesturesPadding, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$mandatorySystemGesturesPadding$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("mandatorySystemGesturesPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), mandatorySystemGesturesLambda);
    }

    private static final Modifier windowInsetsPadding(Modifier $this$windowInsetsPadding, Function1<? super InspectorInfo, Unit> function1, Function1<? super WindowInsetsHolder, ? extends WindowInsets> function12) {
        return $this$windowInsetsPadding.then(new SystemInsetsPaddingModifierElement(function1, function12));
    }
}
