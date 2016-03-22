package com.tradesy.binder.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tradesy.binder.support.v7.recyclerview.ItemBinder;

public class SmallTextBinder extends ItemBinder<SmallTextBinder.ViewHolder> {

    public SmallTextBinder() {
        super(SmallText.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.small_text, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SmallText item = getItem(position);
        holder.textView.setText(item.text);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
