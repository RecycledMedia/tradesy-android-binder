package com.tradesy.binder.support.v7.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Item binder of data set for single view type of {@link android.support.v7.widget.RecyclerView.Adapter}
 */
public abstract class ItemBinder<VH extends RecyclerView.ViewHolder> {
    Class<?> itemClass;
    ItemBinderAdapter adapter;

    /**
     * Item binder
     *
     * @param itemClass Item class that binder is responsible to bind
     */
    public ItemBinder(@NonNull Class<?> itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * @see android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder
     */
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * @see android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)
     */
    public void onBindViewHolder(VH holder, int position) {
    }

    final void setAdapter(ItemBinderAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Returns item class the item binder is associated with
     *
     * @return Item class
     */
    public Class<?> getItemClass() {
        return itemClass;
    }

    /**
     * Returns item per adapter position
     *
     * @param position Item adapter position
     * @return Item
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public final <T> T getItem(int position) {
        return (T) adapter.getItem(position);
    }

    /**
     * Returns parent adapter
     *
     * @return Adapter
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public final <T extends ItemBinderAdapter> T getAdapter() {
        return (T) adapter;
    }
}