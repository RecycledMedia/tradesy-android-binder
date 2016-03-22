Binder
=========

RecyclerView provides adapter that provides a binding from an app-specific data set to views.
In a case of different type of data set, adapter requires to specify [unique view type][viewType]
per each data set. This will lead to checking view type using switch/ifelse as well as implementing
multiple [ViewHolders][viewHolder] in the single adapter.

This library's purpose to solve the described issue above by splitting responsibility of the adapter to
create and bind each item view type separately.

The requirement for the library is to be lightweight and to not force any requirements, rather than
implement same [RecyclerView.Adapter][adapter] pattern

```java
public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

public abstract void onBindViewHolder(VH holder, int position);
```

Item binder adapts same pattern as [RecyclerView.Adapter][adapter]. For example item could be:

```java
public class LargeText {
    public String text;

    public LargeText(String text) {
        this.text = text;
    }
}
```

Corresponding item binder would look like this:

```java
public class LargeTextBinder extends ItemBinder<LargeTextBinder.ViewHolder> {

    public LargeTextBinder() {
        super(LargeText.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.large_text, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LargeText item = getItem(position);
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
```

When setting adapter to RecyclerView, it would look like this:

```java
ArrayItemBinderAdapter adapter = new ArrayItemBinderAdapter();
adapter.register(new LargeTextBinder());
adapter.add(new LargeText("Large Text"));

recyclerView.setAdapter(adapter);
```

![](http://imgur.com/Px3XpmB.gif)

For more details see sample app.

Download
--------

```groovy
compile 'com.tradesy.binder:binder-recyclerview-v7:0.1.0'
```

License
-------

    Copyright (C) 2016 Tradesy Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[adapter]: http://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html
[viewHolder]: http://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder.html
[viewType]: http://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html#getItemViewType(int)