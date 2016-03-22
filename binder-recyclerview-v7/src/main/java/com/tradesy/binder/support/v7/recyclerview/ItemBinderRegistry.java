package com.tradesy.binder.support.v7.recyclerview;

import android.support.annotation.NonNull;
import android.util.SparseArray;

class ItemBinderRegistry {
    SparseArray<ItemBinder> itemBinders = new SparseArray<>();

    public void register(@NonNull ItemBinder itemBinder) {
        itemBinders.put(itemBinders.size(), itemBinder);
    }

    public int getItemViewType(@NonNull Object item) {
        Class<?> itemClass = item.getClass();
        for (int i = 0, count = itemBinders.size(); i < count; i++) {
            if (itemBinders.valueAt(i).itemClass == itemClass) {
                return itemBinders.keyAt(i);
            }
        }
        throw new IllegalArgumentException("Item with class " + itemClass + " cannot be bound to this adapter. No suitable ItemBinder found");
    }

    @NonNull
    public ItemBinder getItemBinder(int itemViewType) {
        ItemBinder itemBinder = itemBinders.get(itemViewType);
        if (itemBinder == null) {
            throw new IllegalArgumentException("There is no registered item binder for view type " + itemViewType);
        }
        return itemBinder;
    }
}
