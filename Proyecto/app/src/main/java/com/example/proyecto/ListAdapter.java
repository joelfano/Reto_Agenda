package com.example.proyecto;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInfalter;
    private Context context;
    final ListAdapter.OnItemClickListener listener;
    private int position;

    public interface OnItemClickListener {
        void onItemClick(ListElement item);
    }

    public ListAdapter(List<ListElement> itemList, Context context, ListAdapter.OnItemClickListener listener) {
        this.mInfalter = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypes) {
        View view = mInfalter.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListElement> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView iconImgage;
        TextView name, status;

        ViewHolder(View ItemView) {
            super(ItemView);
            iconImgage = itemView.findViewById(R.id.task_image);
            name = itemView.findViewById(R.id.task_name);
            status = itemView.findViewById(R.id.task_status);
        }

        void bindData(final ListElement item) {
            iconImgage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getName());
            status.setText(item.getStatus());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemView.showContextMenu();
                    return false;
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select The Action");
            menu.add(0, v.getId(), 0, "Call");
            menu.add(0, v.getId(), 0, "SMS");
        }
    }
}

