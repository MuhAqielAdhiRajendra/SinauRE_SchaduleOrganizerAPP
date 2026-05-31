package com.example.scheduleorganizer.data.entity;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserProfile.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/example/scheduleorganizer/data/entity/UserProfile;", "", "id", "", HintConstants.AUTOFILL_HINT_NAME, "", "title", "consistencyCount", "bestStreak", "lastLoginDate", "", "<init>", "(ILjava/lang/String;Ljava/lang/String;IIJ)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getTitle", "getConsistencyCount", "getBestStreak", "getLastLoginDate", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class UserProfile {
    public static final int $stable = 0;
    private final int bestStreak;
    private final int consistencyCount;
    private final int id;
    private final long lastLoginDate;
    private final String name;
    private final String title;

    public static /* synthetic */ UserProfile copy$default(UserProfile userProfile, int i, String str, String str2, int i2, int i3, long j, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = userProfile.id;
        }
        if ((i4 & 2) != 0) {
            str = userProfile.name;
        }
        if ((i4 & 4) != 0) {
            str2 = userProfile.title;
        }
        if ((i4 & 8) != 0) {
            i2 = userProfile.consistencyCount;
        }
        if ((i4 & 16) != 0) {
            i3 = userProfile.bestStreak;
        }
        if ((i4 & 32) != 0) {
            j = userProfile.lastLoginDate;
        }
        long j2 = j;
        int i5 = i3;
        String str3 = str2;
        return userProfile.copy(i, str, str3, i2, i5, j2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getConsistencyCount() {
        return this.consistencyCount;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getBestStreak() {
        return this.bestStreak;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getLastLoginDate() {
        return this.lastLoginDate;
    }

    public final UserProfile copy(int id, String name, String title, int consistencyCount, int bestStreak, long lastLoginDate) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(title, "title");
        return new UserProfile(id, name, title, consistencyCount, bestStreak, lastLoginDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserProfile)) {
            return false;
        }
        UserProfile userProfile = (UserProfile) other;
        return this.id == userProfile.id && Intrinsics.areEqual(this.name, userProfile.name) && Intrinsics.areEqual(this.title, userProfile.title) && this.consistencyCount == userProfile.consistencyCount && this.bestStreak == userProfile.bestStreak && this.lastLoginDate == userProfile.lastLoginDate;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + this.title.hashCode()) * 31) + Integer.hashCode(this.consistencyCount)) * 31) + Integer.hashCode(this.bestStreak)) * 31) + Long.hashCode(this.lastLoginDate);
    }

    public String toString() {
        return "UserProfile(id=" + this.id + ", name=" + this.name + ", title=" + this.title + ", consistencyCount=" + this.consistencyCount + ", bestStreak=" + this.bestStreak + ", lastLoginDate=" + this.lastLoginDate + ")";
    }

    public UserProfile(int id, String name, String title, int consistencyCount, int bestStreak, long lastLoginDate) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(title, "title");
        this.id = id;
        this.name = name;
        this.title = title;
        this.consistencyCount = consistencyCount;
        this.bestStreak = bestStreak;
        this.lastLoginDate = lastLoginDate;
    }

    public /* synthetic */ UserProfile(int i, String str, String str2, int i2, int i3, long j, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 1 : i, str, (i4 & 4) != 0 ? "Tempo User" : str2, (i4 & 8) != 0 ? 0 : i2, (i4 & 16) != 0 ? 0 : i3, (i4 & 32) != 0 ? 0L : j);
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getConsistencyCount() {
        return this.consistencyCount;
    }

    public final int getBestStreak() {
        return this.bestStreak;
    }

    public final long getLastLoginDate() {
        return this.lastLoginDate;
    }
}
