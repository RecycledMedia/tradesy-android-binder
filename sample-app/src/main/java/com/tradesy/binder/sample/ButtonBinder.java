package com.tradesy.binder.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tradesy.binder.support.v7.recyclerview.ItemBinder;

public class ButtonBinder extends ItemBinder<ButtonBinder.ViewHolder> {
    Listener listener;

    public ButtonBinder() {
        super(Button.class);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.button, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Button item = getItem(position);
        holder.item = item;
        holder.button.setText(item.text);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        android.widget.Button button;
        Button item;

        public ViewHolder(View itemView) {
            super(itemView);
            button = (android.widget.Button) itemView.findViewById(R.id.button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(item);
            }
        }
    }

    public interface Listener {
        void onClick(Button button);
    }
}
