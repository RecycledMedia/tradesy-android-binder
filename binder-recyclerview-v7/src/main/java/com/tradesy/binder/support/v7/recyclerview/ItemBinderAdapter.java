package com.tradesy.binder.support.v7.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Adapter provides splitting the responsibility of binding each item view type separately using {@link ItemBinder}
 */
public abstract class ItemBinderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ItemBinderRegistry registry = new ItemBinderRegistry();

    /**
     * Register item binder with the adapter
     *
     * @param itemBinder Item binder
     */
    public void register(@NonNull ItemBinder itemBinder) {
        registry.register(itemBinder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBinder itemBinder = registry.getItemBinder(viewType);
        itemBinder.setAdapter(this);
        RecyclerView.ViewHolder holder = itemBinder.onCreateViewHolder(parent, viewType);
        holder.itemView.setTag(R.id.item_binder, itemBinder);
        return holder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        getItemBinder(holder).onBindViewHolder(holder, position);
    }

    /**
     * Returns item binder responsible for binding provided view holder
     *
     * @param holder ViewHolder
     * @return ItemBinder
     */
    @SuppressWarnings("unchecked")
    @NonNull
    protected final <T> ItemBinder<? super T> getItemBinder(RecyclerView.ViewHolder holder) {
        ItemBinder<? super T> itemBinder = (ItemBinder<? super T>) holder.itemView.getTag(R.id.item_binder);
        if (itemBinder == null) {
            throw new IllegalStateException("ViewHolder is not setup properly, make sure call this only on onBindViewHolder");
        }
        return itemBinder;
    }

    /**
     * Get item per position
     *
     * @param position Item adapter position
     * @return Item
     */
    @NonNull
    public abstract Object getItem(int position);

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getItemViewType(int position) {
        return registry.getItemViewType(getItem(position));
    }
}