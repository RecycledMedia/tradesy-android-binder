package com.tradesy.binder.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tradesy.binder.support.v7.recyclerview.ItemBinder;

public class SeparatorBinder extends ItemBinder<SeparatorBinder.ViewHolder> {

    public SeparatorBinder() {
        super(Separator.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.separator, parent, false));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
