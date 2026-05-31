package com.google.android.material.listitem;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes13.dex */
public class ListItemViewHolder extends RecyclerView.ViewHolder {
    private final ListItemLayout listItemLayout;

    public ListItemViewHolder(View itemView) {
        super(itemView);
        this.listItemLayout = findListItemLayout();
    }

    private ListItemLayout findListItemLayout() {
        if (this.itemView instanceof ListItemLayout) {
            return (ListItemLayout) this.itemView;
        }
        if (this.itemView instanceof ViewGroup) {
            int childCount = ((ViewGroup) this.itemView).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = ((ViewGroup) this.itemView).getChildAt(i);
                if (child instanceof ListItemLayout) {
                    return (ListItemLayout) child;
                }
            }
        }
        throw new IllegalStateException("Didn't find ListItemLayout in root itemView or among itemView's children.");
    }

    public void bind() {
        int position = getBindingAdapterPosition();
        int itemCount = getBindingAdapter().getItemCount();
        bind(position, itemCount);
    }

    public void bind(int position, int itemCount) {
        if (position == -1 || itemCount == 0) {
            return;
        }
        this.listItemLayout.updateAppearance(position, itemCount);
    }

    public void bind(int position) {
        this.listItemLayout.updateAppearance(position);
    }
}
