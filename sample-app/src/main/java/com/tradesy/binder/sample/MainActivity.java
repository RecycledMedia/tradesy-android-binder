package com.tradesy.binder.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tradesy.binder.support.v7.recyclerview.ArrayItemBinderAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create adapter and register item binders
        ArrayItemBinderAdapter adapter = new ArrayItemBinderAdapter();
        adapter.register(new LargeTextBinder());
        adapter.register(new SmallTextBinder());
        adapter.register(new SeparatorBinder());

        ButtonBinder buttonBinder = new ButtonBinder();
        buttonBinder.setListener(new ButtonBinder.Listener() {
            @Override
            public void onClick(Button button) {
                // handle click
            }
        });
        adapter.register(buttonBinder);

        // Create recycler view and set it up
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Creates items and add to adapter
        for (int i = 0; i < 10; i++) {
            adapter.addAll(new SmallText("Small text 1"), new Separator());
            adapter.add(new LargeText("Large Text 1"));
            adapter.add(new Button("Click Me"));
            adapter.addAll(new Separator(), new SmallText("Small Text 2"), new LargeText("Large Text 2"));
        }
    }
}
