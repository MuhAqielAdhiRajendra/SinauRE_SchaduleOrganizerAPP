package androidx.compose.ui.graphics.vector;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.constraintlayout.motion.widget.Key;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: VectorCompose.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0085\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0002\u0010\u0013\u001a£\u0001\u0010\u0014\u001a\u00020\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020\u0005H\u0007¢\u0006\u0004\b&\u0010'¨\u0006("}, d2 = {"Group", "", HintConstants.AUTOFILL_HINT_NAME, "", Key.ROTATION, "", "pivotX", "pivotY", "scaleX", "scaleY", "translationX", "translationY", "clipPathData", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/graphics/vector/VectorComposable;", "(Ljava/lang/String;FFFFFFFLjava/util/List;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Path", "pathData", "pathFillType", "Landroidx/compose/ui/graphics/PathFillType;", "fill", "Landroidx/compose/ui/graphics/Brush;", "fillAlpha", "stroke", "strokeAlpha", "strokeLineWidth", "strokeLineCap", "Landroidx/compose/ui/graphics/StrokeCap;", "strokeLineJoin", "Landroidx/compose/ui/graphics/StrokeJoin;", "strokeLineMiter", "trimPathStart", "trimPathEnd", "trimPathOffset", "Path-9cdaXJ4", "(Ljava/util/List;ILjava/lang/String;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Brush;FFIIFFFFLandroidx/compose/runtime/Composer;III)V", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class VectorComposeKt {
    public static final void Group(String name, float rotation, float pivotX, float pivotY, float scaleX, float scaleY, float translationX, float translationY, List<? extends PathNode> list, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        String name2;
        float rotation2;
        float pivotX2;
        float pivotY2;
        float scaleX2;
        int i2;
        final float scaleY2;
        final float translationY2;
        final String name3;
        final float pivotX3;
        final float pivotY3;
        final float pivotX4;
        final List<? extends PathNode> list2;
        final float rotation3;
        final float rotation4;
        float scaleY3;
        float translationX2;
        float translationY3;
        List<? extends PathNode> emptyPath;
        int $dirty;
        float scaleY4;
        Composer $composer2 = $composer.startRestartGroup(-1851426205);
        ComposerKt.sourceInformation($composer2, "C(Group)N(name,rotation,pivotX,pivotY,scaleX,scaleY,translationX,translationY,clipPathData,content)59@2563L20,58@2501L586:VectorCompose.kt#huu6hf");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty2 |= 6;
            name2 = name;
        } else if (($changed & 6) == 0) {
            name2 = name;
            $dirty2 |= $composer2.changed(name2) ? 4 : 2;
        } else {
            name2 = name;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty2 |= 48;
            rotation2 = rotation;
        } else if (($changed & 48) == 0) {
            rotation2 = rotation;
            $dirty2 |= $composer2.changed(rotation2) ? 32 : 16;
        } else {
            rotation2 = rotation;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty2 |= 384;
            pivotX2 = pivotX;
        } else if (($changed & 384) == 0) {
            pivotX2 = pivotX;
            $dirty2 |= $composer2.changed(pivotX2) ? 256 : 128;
        } else {
            pivotX2 = pivotX;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty2 |= 3072;
            pivotY2 = pivotY;
        } else if (($changed & 3072) == 0) {
            pivotY2 = pivotY;
            $dirty2 |= $composer2.changed(pivotY2) ? 2048 : 1024;
        } else {
            pivotY2 = pivotY;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            scaleX2 = scaleX;
        } else if (($changed & 24576) == 0) {
            scaleX2 = scaleX;
            $dirty2 |= $composer2.changed(scaleX2) ? 16384 : 8192;
        } else {
            scaleX2 = scaleX;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer2.changed(scaleY) ? 131072 : 65536;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changed(translationX) ? 1048576 : 524288;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty2 |= 12582912;
            i2 = i10;
        } else if (($changed & 12582912) == 0) {
            i2 = i10;
            $dirty2 |= $composer2.changed(translationY) ? 8388608 : 4194304;
        } else {
            i2 = i10;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer2.changedInstance(list)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int $dirty3 = $dirty2;
        if ($composer2.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer2.startDefaults();
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 256) != 0) {
                    $dirty = $dirty3 & (-234881025);
                    scaleY3 = scaleY;
                    translationX2 = translationX;
                    translationY3 = translationY;
                    emptyPath = list;
                } else {
                    scaleY3 = scaleY;
                    translationX2 = translationX;
                    translationY3 = translationY;
                    emptyPath = list;
                    $dirty = $dirty3;
                }
            } else {
                if (i3 != 0) {
                    name2 = "";
                }
                if (i4 != 0) {
                    rotation2 = 0.0f;
                }
                if (i5 != 0) {
                    pivotX2 = 0.0f;
                }
                if (i6 != 0) {
                    pivotY2 = 0.0f;
                }
                if (i7 != 0) {
                    scaleX2 = 1.0f;
                }
                if (i8 == 0) {
                    scaleY3 = scaleY;
                } else {
                    scaleY3 = 1.0f;
                }
                if (i9 == 0) {
                    translationX2 = translationX;
                } else {
                    translationX2 = 0.0f;
                }
                if (i2 == 0) {
                    translationY3 = translationY;
                } else {
                    translationY3 = 0.0f;
                }
                if ((i & 256) == 0) {
                    emptyPath = list;
                    $dirty = $dirty3;
                } else {
                    emptyPath = VectorKt.getEmptyPath();
                    $dirty = $dirty3 & (-234881025);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                scaleY4 = scaleY3;
                ComposerKt.traceEventStart(-1851426205, $dirty, -1, "androidx.compose.ui.graphics.vector.Group (VectorCompose.kt:57)");
            } else {
                scaleY4 = scaleY3;
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1104106697, "CC(remember):VectorCompose.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            int $dirty4 = $dirty;
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function0) new Function0<GroupComponent>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$1$1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final GroupComponent invoke() {
                        return new GroupComponent();
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            Function0 factory$iv = (Function0) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float translationX3 = translationX2;
            float translationY4 = translationY3;
            ComposerKt.sourceInformationMarkerStart($composer2, -1291542257, "CC(ComposeNode)N(factory,update,content)372@14715L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof VectorApplier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(factory$iv);
            } else {
                $composer2.useNode();
            }
            Composer $this$Group_u24lambda_u241 = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Group_u24lambda_u241, name2, new Function2<GroupComponent, String, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, String str) {
                    invoke2(groupComponent, str);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(GroupComponent $this$set, String it) {
                    $this$set.setName(it);
                }
            });
            Updater.m4441setimpl($this$Group_u24lambda_u241, Float.valueOf(rotation2), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f) {
                    invoke(groupComponent, f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(GroupComponent $this$set, float it) {
                    $this$set.setRotation(it);
                }
            });
            Updater.m4441setimpl($this$Group_u24lambda_u241, Float.valueOf(pivotX2), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f) {
                    invoke(groupComponent, f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(GroupComponent $this$set, float it) {
                    $this$set.setPivotX(it);
                }
            });
            Updater.m4441setimpl($this$Group_u24lambda_u241, Float.valueOf(pivotY2), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f) {
                    invoke(groupComponent, f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(GroupComponent $this$set, float it) {
                    $this$set.setPivotY(it);
                }
            });
            Updater.m4441setimpl($this$Group_u24lambda_u241, Float.valueOf(scaleX2), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$5
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f) {
                    invoke(groupComponent, f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(GroupComponent $this$set, float it) {
                    $this$set.setScaleX(it);
                }
            });
            Updater.m4441setimpl($this$Group_u24lambda_u241, Float.valueOf(scaleY4), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$6
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f) {
                    invoke(groupComponent, f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(GroupComponent $this$set, float it) {
                    $this$set.setScaleY(it);
                }
            });
            Updater.m4441setimpl($this$Group_u24lambda_u241, Float.valueOf(translationX3), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$7
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f) {
                    invoke(groupComponent, f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(GroupComponent $this$set, float it) {
                    $this$set.setTranslationX(it);
                }
            });
            Updater.m4441setimpl($this$Group_u24lambda_u241, Float.valueOf(translationY4), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$8
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f) {
                    invoke(groupComponent, f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(GroupComponent $this$set, float it) {
                    $this$set.setTranslationY(it);
                }
            });
            Updater.m4441setimpl($this$Group_u24lambda_u241, emptyPath, new Function2<GroupComponent, List<? extends PathNode>, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$9
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, List<? extends PathNode> list3) {
                    invoke2(groupComponent, list3);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(GroupComponent $this$set, List<? extends PathNode> list3) {
                    $this$set.setClipPathData(list3);
                }
            });
            int i11 = (6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1167503970, "C72@3072L9:VectorCompose.kt#huu6hf");
            function2.invoke($composer2, Integer.valueOf(($dirty4 >> 27) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            translationY2 = translationY4;
            name3 = name2;
            pivotX3 = pivotX2;
            pivotY3 = pivotY2;
            pivotX4 = translationX3;
            list2 = emptyPath;
            scaleY2 = scaleY4;
            rotation3 = rotation2;
            rotation4 = scaleX2;
        } else {
            $composer2.skipToGroupEnd();
            scaleY2 = scaleY;
            translationY2 = translationY;
            name3 = name2;
            pivotX3 = pivotX2;
            pivotY3 = pivotY2;
            pivotX4 = translationX;
            list2 = list;
            rotation3 = rotation2;
            rotation4 = scaleX2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt.Group.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i12) {
                    VectorComposeKt.Group(name3, rotation3, pivotX3, pivotY3, rotation4, scaleY2, pivotX4, translationY2, list2, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: Path-9cdaXJ4, reason: not valid java name */
    public static final void m6057Path9cdaXJ4(final List<? extends PathNode> list, int pathFillType, String name, Brush fill, float fillAlpha, Brush stroke, float strokeAlpha, float strokeLineWidth, int strokeLineCap, int strokeLineJoin, float strokeLineMiter, float trimPathStart, float trimPathEnd, float trimPathOffset, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        String str;
        Brush brush;
        float f;
        Brush brush2;
        float strokeAlpha2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        Composer $composer2;
        int $dirty;
        final float strokeLineMiter2;
        int $dirty1;
        final String name2;
        final Brush fill2;
        final float fillAlpha2;
        final Brush stroke2;
        final int pathFillType2;
        final float strokeAlpha3;
        final float fillAlpha3;
        final int strokeLineCap2;
        final int strokeLineJoin2;
        final float strokeLineMiter3;
        final float trimPathStart2;
        final float strokeAlpha4;
        int pathFillType3;
        String name3;
        Brush fill3;
        float fillAlpha4;
        Brush stroke3;
        float strokeLineWidth2;
        int strokeLineCap3;
        int strokeLineJoin3;
        float strokeLineMiter4;
        float trimPathStart3;
        float trimPathEnd2;
        float trimPathOffset2;
        int pathFillType4;
        float trimPathOffset3;
        Composer $composer3 = $composer.startRestartGroup(-1478270750);
        ComposerKt.sourceInformation($composer3, "C(Path)N(pathData,pathFillType:c#ui.graphics.PathFillType,name,fill,fillAlpha,stroke,strokeAlpha,strokeLineWidth,strokeLineCap:c#ui.graphics.StrokeCap,strokeLineJoin:c#ui.graphics.StrokeJoin,strokeLineMiter,trimPathStart,trimPathEnd,trimPathOffset)116@5136L19,115@5075L877:VectorCompose.kt#huu6hf");
        int $dirty2 = $changed;
        int $dirty12 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(list) ? 4 : 2;
        }
        int i9 = i & 2;
        if (i9 != 0) {
            $dirty2 |= 48;
            i2 = pathFillType;
        } else if (($changed & 48) == 0) {
            i2 = pathFillType;
            $dirty2 |= $composer3.changed(i2) ? 32 : 16;
        } else {
            i2 = pathFillType;
        }
        int i10 = i & 4;
        if (i10 != 0) {
            $dirty2 |= 384;
            str = name;
        } else if (($changed & 384) == 0) {
            str = name;
            $dirty2 |= $composer3.changed(str) ? 256 : 128;
        } else {
            str = name;
        }
        int i11 = i & 8;
        if (i11 != 0) {
            $dirty2 |= 3072;
            brush = fill;
        } else if (($changed & 3072) == 0) {
            brush = fill;
            $dirty2 |= $composer3.changed(brush) ? 2048 : 1024;
        } else {
            brush = fill;
        }
        int i12 = i & 16;
        if (i12 != 0) {
            $dirty2 |= 24576;
            f = fillAlpha;
        } else if (($changed & 24576) == 0) {
            f = fillAlpha;
            $dirty2 |= $composer3.changed(f) ? 16384 : 8192;
        } else {
            f = fillAlpha;
        }
        int i13 = i & 32;
        if (i13 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            brush2 = stroke;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            brush2 = stroke;
            $dirty2 |= $composer3.changed(brush2) ? 131072 : 65536;
        } else {
            brush2 = stroke;
        }
        int i14 = i & 64;
        if (i14 != 0) {
            $dirty2 |= 1572864;
            strokeAlpha2 = strokeAlpha;
        } else if (($changed & 1572864) == 0) {
            strokeAlpha2 = strokeAlpha;
            $dirty2 |= $composer3.changed(strokeAlpha2) ? 1048576 : 524288;
        } else {
            strokeAlpha2 = strokeAlpha;
        }
        int i15 = i & 128;
        if (i15 != 0) {
            $dirty2 |= 12582912;
            i3 = i15;
        } else if (($changed & 12582912) == 0) {
            i3 = i15;
            $dirty2 |= $composer3.changed(strokeLineWidth) ? 8388608 : 4194304;
        } else {
            i3 = i15;
        }
        int i16 = i & 256;
        if (i16 != 0) {
            $dirty2 |= 100663296;
            i4 = i16;
        } else if (($changed & 100663296) == 0) {
            i4 = i16;
            $dirty2 |= $composer3.changed(strokeLineCap) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i4 = i16;
        }
        int i17 = i & 512;
        if (i17 != 0) {
            $dirty2 |= 805306368;
            i5 = i17;
        } else if (($changed & 805306368) == 0) {
            i5 = i17;
            $dirty2 |= $composer3.changed(strokeLineJoin) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i5 = i17;
        }
        int i18 = i & 1024;
        if (i18 != 0) {
            $dirty12 |= 6;
            i6 = i18;
        } else if (($changed1 & 6) == 0) {
            i6 = i18;
            $dirty12 |= $composer3.changed(strokeLineMiter) ? 4 : 2;
        } else {
            i6 = i18;
        }
        int i19 = i & 2048;
        if (i19 != 0) {
            $dirty12 |= 48;
            i7 = i19;
        } else if (($changed1 & 48) == 0) {
            i7 = i19;
            $dirty12 |= $composer3.changed(trimPathStart) ? 32 : 16;
        } else {
            i7 = i19;
        }
        int i20 = i & 4096;
        if (i20 != 0) {
            $dirty12 |= 384;
            i8 = i20;
        } else {
            i8 = i20;
            if (($changed1 & 384) == 0) {
                $dirty12 |= $composer3.changed(trimPathEnd) ? 256 : 128;
            }
        }
        int i21 = i & 8192;
        if (i21 != 0) {
            $dirty12 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty12 |= $composer3.changed(trimPathOffset) ? 2048 : 1024;
        }
        if (!$composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty12 & 1171) == 1170) ? false : true, $dirty2 & 1)) {
            $composer2 = $composer3;
            $dirty = $dirty2;
            $composer2.skipToGroupEnd();
            strokeLineMiter2 = trimPathEnd;
            $dirty1 = $dirty12;
            name2 = str;
            fill2 = brush;
            fillAlpha2 = f;
            stroke2 = brush2;
            pathFillType2 = i2;
            strokeAlpha3 = strokeAlpha2;
            fillAlpha3 = strokeLineWidth;
            strokeLineCap2 = strokeLineCap;
            strokeLineJoin2 = strokeLineJoin;
            strokeLineMiter3 = strokeLineMiter;
            trimPathStart2 = trimPathStart;
            strokeAlpha4 = trimPathOffset;
        } else {
            if (i9 != 0) {
                pathFillType3 = VectorKt.getDefaultFillType();
            } else {
                pathFillType3 = i2;
            }
            if (i10 == 0) {
                name3 = str;
            } else {
                name3 = "";
            }
            if (i11 == 0) {
                fill3 = brush;
            } else {
                fill3 = null;
            }
            if (i12 == 0) {
                fillAlpha4 = f;
            } else {
                fillAlpha4 = 1.0f;
            }
            if (i13 == 0) {
                stroke3 = brush2;
            } else {
                stroke3 = null;
            }
            if (i14 != 0) {
                strokeAlpha2 = 1.0f;
            }
            if (i3 == 0) {
                strokeLineWidth2 = strokeLineWidth;
            } else {
                strokeLineWidth2 = 0.0f;
            }
            if (i4 == 0) {
                strokeLineCap3 = strokeLineCap;
            } else {
                strokeLineCap3 = VectorKt.getDefaultStrokeLineCap();
            }
            if (i5 == 0) {
                strokeLineJoin3 = strokeLineJoin;
            } else {
                strokeLineJoin3 = VectorKt.getDefaultStrokeLineJoin();
            }
            if (i6 == 0) {
                strokeLineMiter4 = strokeLineMiter;
            } else {
                strokeLineMiter4 = 4.0f;
            }
            if (i7 == 0) {
                trimPathStart3 = trimPathStart;
            } else {
                trimPathStart3 = 0.0f;
            }
            if (i8 == 0) {
                trimPathEnd2 = trimPathEnd;
            } else {
                trimPathEnd2 = 1.0f;
            }
            if (i21 == 0) {
                trimPathOffset2 = trimPathOffset;
            } else {
                trimPathOffset2 = 0.0f;
            }
            if (ComposerKt.isTraceInProgress()) {
                pathFillType4 = pathFillType3;
                trimPathOffset3 = trimPathOffset2;
                ComposerKt.traceEventStart(-1478270750, $dirty2, $dirty12, "androidx.compose.ui.graphics.vector.Path (VectorCompose.kt:114)");
            } else {
                pathFillType4 = pathFillType3;
                trimPathOffset3 = trimPathOffset2;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1016005589, "CC(remember):VectorCompose.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            $composer2 = $composer3;
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function0) new Function0<PathComponent>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$1$1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final PathComponent invoke() {
                        return new PathComponent();
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            Function0 factory$iv = (Function0) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $dirty = $dirty2;
            ComposerKt.sourceInformationMarkerStart($composer2, 1886828752, "CC(ComposeNode)N(factory,update):Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof VectorApplier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(factory$iv);
            } else {
                $composer2.useNode();
            }
            Composer $this$Path_9cdaXJ4_u24lambda_u241 = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, name3, new Function2<PathComponent, String, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, String str2) {
                    invoke2(pathComponent, str2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PathComponent $this$set, String it) {
                    $this$set.setName(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, list, new Function2<PathComponent, List<? extends PathNode>, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, List<? extends PathNode> list2) {
                    invoke2(pathComponent, list2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PathComponent $this$set, List<? extends PathNode> list2) {
                    $this$set.setPathData(list2);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, PathFillType.m5602boximpl(pathFillType4), new Function2<PathComponent, PathFillType, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, PathFillType pathFillType5) {
                    m6059invokepweu1eQ(pathComponent, pathFillType5.getValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-pweu1eQ, reason: not valid java name */
                public final void m6059invokepweu1eQ(PathComponent $this$set, int it) {
                    $this$set.m6051setPathFillTypeoQ8Xj4U(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, fill3, new Function2<PathComponent, Brush, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Brush brush3) {
                    invoke2(pathComponent, brush3);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PathComponent $this$set, Brush it) {
                    $this$set.setFill(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, Float.valueOf(fillAlpha4), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$5
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f2) {
                    invoke(pathComponent, f2.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PathComponent $this$set, float it) {
                    $this$set.setFillAlpha(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, stroke3, new Function2<PathComponent, Brush, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$6
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Brush brush3) {
                    invoke2(pathComponent, brush3);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PathComponent $this$set, Brush it) {
                    $this$set.setStroke(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, Float.valueOf(strokeAlpha2), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$7
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f2) {
                    invoke(pathComponent, f2.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PathComponent $this$set, float it) {
                    $this$set.setStrokeAlpha(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, Float.valueOf(strokeLineWidth2), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$8
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f2) {
                    invoke(pathComponent, f2.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PathComponent $this$set, float it) {
                    $this$set.setStrokeLineWidth(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, StrokeJoin.m5690boximpl(strokeLineJoin3), new Function2<PathComponent, StrokeJoin, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$9
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, StrokeJoin strokeJoin) {
                    m6060invokekLtJ_vA(pathComponent, strokeJoin.getValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-kLtJ_vA, reason: not valid java name */
                public final void m6060invokekLtJ_vA(PathComponent $this$set, int it) {
                    $this$set.m6053setStrokeLineJoinWw9F2mQ(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, StrokeCap.m5680boximpl(strokeLineCap3), new Function2<PathComponent, StrokeCap, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$10
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, StrokeCap strokeCap) {
                    m6058invokeCSYIeUk(pathComponent, strokeCap.getValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-CSYIeUk, reason: not valid java name */
                public final void m6058invokeCSYIeUk(PathComponent $this$set, int it) {
                    $this$set.m6052setStrokeLineCapBeK7IIE(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, Float.valueOf(strokeLineMiter4), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$11
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f2) {
                    invoke(pathComponent, f2.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PathComponent $this$set, float it) {
                    $this$set.setStrokeLineMiter(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, Float.valueOf(trimPathStart3), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$12
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f2) {
                    invoke(pathComponent, f2.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PathComponent $this$set, float it) {
                    $this$set.setTrimPathStart(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, Float.valueOf(trimPathEnd2), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$13
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f2) {
                    invoke(pathComponent, f2.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PathComponent $this$set, float it) {
                    $this$set.setTrimPathEnd(it);
                }
            });
            Updater.m4441setimpl($this$Path_9cdaXJ4_u24lambda_u241, Float.valueOf(trimPathOffset3), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$14
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f2) {
                    invoke(pathComponent, f2.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PathComponent $this$set, float it) {
                    $this$set.setTrimPathOffset(it);
                }
            });
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            name2 = name3;
            $dirty1 = $dirty12;
            fill2 = fill3;
            fillAlpha2 = fillAlpha4;
            stroke2 = stroke3;
            fillAlpha3 = strokeLineWidth2;
            strokeLineCap2 = strokeLineCap3;
            strokeLineJoin2 = strokeLineJoin3;
            strokeLineMiter3 = strokeLineMiter4;
            strokeAlpha3 = strokeAlpha2;
            trimPathStart2 = trimPathStart3;
            strokeLineMiter2 = trimPathEnd2;
            pathFillType2 = pathFillType4;
            strokeAlpha4 = trimPathOffset3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i22) {
                    VectorComposeKt.m6057Path9cdaXJ4(list, pathFillType2, name2, fill2, fillAlpha2, stroke2, strokeAlpha3, fillAlpha3, strokeLineCap2, strokeLineJoin2, strokeLineMiter3, trimPathStart2, strokeLineMiter2, strokeAlpha4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }
}
