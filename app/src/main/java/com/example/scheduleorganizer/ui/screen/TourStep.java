package com.example.scheduleorganizer.ui.screen;

import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TourGuideScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/example/scheduleorganizer/ui/screen/TourStep;", "", "title", "", "description", "hintTitle", "hintText", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", TypedValues.AttributesType.S_TARGET, "Lcom/example/scheduleorganizer/ui/screen/TourTarget;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;Lcom/example/scheduleorganizer/ui/screen/TourTarget;)V", "getTitle", "()Ljava/lang/String;", "getDescription", "getHintTitle", "getHintText", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "getTarget", "()Lcom/example/scheduleorganizer/ui/screen/TourTarget;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class TourStep {
    private final String description;
    private final String hintText;
    private final String hintTitle;
    private final ImageVector icon;
    private final TourTarget target;
    private final String title;

    public static /* synthetic */ TourStep copy$default(TourStep tourStep, String str, String str2, String str3, String str4, ImageVector imageVector, TourTarget tourTarget, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tourStep.title;
        }
        if ((i & 2) != 0) {
            str2 = tourStep.description;
        }
        if ((i & 4) != 0) {
            str3 = tourStep.hintTitle;
        }
        if ((i & 8) != 0) {
            str4 = tourStep.hintText;
        }
        if ((i & 16) != 0) {
            imageVector = tourStep.icon;
        }
        if ((i & 32) != 0) {
            tourTarget = tourStep.target;
        }
        ImageVector imageVector2 = imageVector;
        TourTarget tourTarget2 = tourTarget;
        return tourStep.copy(str, str2, str3, str4, imageVector2, tourTarget2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getHintTitle() {
        return this.hintTitle;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getHintText() {
        return this.hintText;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ImageVector getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TourTarget getTarget() {
        return this.target;
    }

    public final TourStep copy(String title, String description, String hintTitle, String hintText, ImageVector icon, TourTarget target) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(hintTitle, "hintTitle");
        Intrinsics.checkNotNullParameter(hintText, "hintText");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(target, "target");
        return new TourStep(title, description, hintTitle, hintText, icon, target);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TourStep)) {
            return false;
        }
        TourStep tourStep = (TourStep) other;
        return Intrinsics.areEqual(this.title, tourStep.title) && Intrinsics.areEqual(this.description, tourStep.description) && Intrinsics.areEqual(this.hintTitle, tourStep.hintTitle) && Intrinsics.areEqual(this.hintText, tourStep.hintText) && Intrinsics.areEqual(this.icon, tourStep.icon) && this.target == tourStep.target;
    }

    public int hashCode() {
        return (((((((((this.title.hashCode() * 31) + this.description.hashCode()) * 31) + this.hintTitle.hashCode()) * 31) + this.hintText.hashCode()) * 31) + this.icon.hashCode()) * 31) + this.target.hashCode();
    }

    public String toString() {
        return "TourStep(title=" + this.title + ", description=" + this.description + ", hintTitle=" + this.hintTitle + ", hintText=" + this.hintText + ", icon=" + this.icon + ", target=" + this.target + ")";
    }

    public TourStep(String title, String description, String hintTitle, String hintText, ImageVector icon, TourTarget target) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(hintTitle, "hintTitle");
        Intrinsics.checkNotNullParameter(hintText, "hintText");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(target, "target");
        this.title = title;
        this.description = description;
        this.hintTitle = hintTitle;
        this.hintText = hintText;
        this.icon = icon;
        this.target = target;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getHintTitle() {
        return this.hintTitle;
    }

    public final String getHintText() {
        return this.hintText;
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final TourTarget getTarget() {
        return this.target;
    }
}
