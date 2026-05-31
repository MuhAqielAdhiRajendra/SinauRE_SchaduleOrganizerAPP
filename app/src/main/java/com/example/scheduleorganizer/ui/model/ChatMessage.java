package com.example.scheduleorganizer.ui.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChatMessage.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/example/scheduleorganizer/ui/model/ChatMessage;", "", "text", "", "user", "", "<init>", "(Ljava/lang/String;Z)V", "getText", "()Ljava/lang/String;", "getUser", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ChatMessage {
    public static final int $stable = 0;
    private final String text;
    private final boolean user;

    public static /* synthetic */ ChatMessage copy$default(ChatMessage chatMessage, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = chatMessage.text;
        }
        if ((i & 2) != 0) {
            z = chatMessage.user;
        }
        return chatMessage.copy(str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getUser() {
        return this.user;
    }

    public final ChatMessage copy(String text, boolean user) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new ChatMessage(text, user);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChatMessage)) {
            return false;
        }
        ChatMessage chatMessage = (ChatMessage) other;
        return Intrinsics.areEqual(this.text, chatMessage.text) && this.user == chatMessage.user;
    }

    public int hashCode() {
        return (this.text.hashCode() * 31) + Boolean.hashCode(this.user);
    }

    public String toString() {
        return "ChatMessage(text=" + this.text + ", user=" + this.user + ")";
    }

    public ChatMessage(String text, boolean user) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.user = user;
    }

    public final String getText() {
        return this.text;
    }

    public final boolean getUser() {
        return this.user;
    }
}
