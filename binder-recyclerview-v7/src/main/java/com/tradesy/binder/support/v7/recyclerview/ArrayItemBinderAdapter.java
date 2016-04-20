package com.tradesy.binder.support.v7.recyclerview;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Simple array item binder adapter. It manages items, that allows to avoid creation of adapter in most of the cases
 */
public class ArrayItemBinderAdapter extends ItemBinderAdapter {
    final Object lock = new Object();
    ArrayList<Object> items = new ArrayList<>();

    /**
     * Set collection of items
     *
     * @param items Items
     */
    public void set(Collection<?> items) {
        synchronized (lock) {
            int count = this.items.size();
            int newCount = items.size();
            this.items = new ArrayList<>(items);
            if (count > newCount) {
                notifyItemRangeChanged(0, newCount);
                notifyItemRangeRemoved(newCount, count - newCount);
            } else {
                notifyItemRangeChanged(0, count);
                notifyItemRangeInserted(count, newCount - count);
            }
        }
    }

    /**
     * Replace item at position
     *
     * @param position Item position
     * @param item     Item
     */
    public void set(int position, Object item) {
        synchronized (lock) {
            items.set(position, item);
            notifyItemChanged(position);
        }
    }

    /**
     * Insert item at position
     *
     * @param position Item position
     * @param item     Item
     */
    public void add(int position, Object item) {
        synchronized (lock) {
            items.add(position, item);
            notifyItemInserted(position);
        }
    }

    /**
     * Add item in the end
     *
     * @param item Item
     */
    public void add(Object item) {
        synchronized (lock) {
            items.add(item);
            notifyItemInserted(items.size() - 1);
        }
    }

    /**
     * Add collection of items in the end
     *
     * @param items Items
     */
    public void addAll(Collection<?> items) {
        synchronized (lock) {
            int count = this.items.size();
            if (this.items.addAll(items)) {
                notifyItemRangeInserted(count, this.items.size() - count);
            }
        }
    }

    /**
     * Add items in the end
     *
     * @param items Items
     */
    public void addAll(Object... items) {
        synchronized (lock) {
            int count = this.items.size();
            if (Collections.addAll(this.items, items)) {
                notifyItemRangeInserted(count, this.items.size() - count);
            }
        }
    }

    /**
     * Remove item if exists
     *
     * @param item Item
     */
    public void remove(Object item) {
        synchronized (lock) {
            int position = items.indexOf(item);
            if (position >= 0) {
                items.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    /**
     * Remove all items if any
     */
    public void clear() {
        synchronized (lock) {
            int count = items.size();
            if (count > 0) {
                items.clear();
                notifyItemRangeRemoved(0, count);
            }
        }
    }

    /**
     * Sort items by supplying comparator
     *
     * @param comparator Comparator
     */
    public void sort(Comparator<? super Object> comparator) {
        synchronized (lock) {
            Collections.sort(items, comparator);
            notifyItemRangeChanged(0, items.size());
        }
    }

    /**
     * Return if adapter is empty
     *
     * @return True if adapter is empty, otherwise false
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Get item at position
     *
     * @param position Item position
     * @return Item
     */
    @NonNull
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    /**
     * Get position of the item
     *
     * @param item Item
     * @return Position of item if found, otherwise -1
     */
    public int getPosition(Object item) {
        return items.indexOf(item);
    }

    /**
     * Get unique item id by position of the item
     *
     * @param position Item position
     * @return Item id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get item count
     *
     * @return Item count
     */
    @Override
    public int getItemCount() {
        return items.size();
    }
}
