package com.example.bilalqureshi.buysellproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bilalqureshi on 19/04/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ModelItem> mList;
    ItemAdapter(Context context, ArrayList<ModelItem> list){
        mContext=context;
        mList=list;
    }
CustomItemClickListener listener;

    public ItemAdapter(Context context, ArrayList<ModelItem> itemList, CustomItemClickListener listener) {
        this.mList = itemList;
        this.mContext = context;
        this.listener = listener;
    }
    public void removeAt(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View view =layoutInflater.inflate(R.layout.rv_items,parent,false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ModelItem modelItem=mList.get(position);
ImageView image=holder.item_image;
        TextView name,place,price;
        name=holder.item_name;
        price=holder.item_price;
        place=holder.item_place;
        image.setImageResource(modelItem.getImage());
        name.setText(modelItem.getName());
        price.setText(modelItem.getPrice());
        place.setText(modelItem.getPlace());


    }
    public void ItemsListAdapter(Context mContext, ArrayList<ModelItem> mList, CustomItemClickListener listener) {
        this.mList = mList;
        this.mContext = mContext;
        this.listener = listener;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item_image;
        TextView item_name,item_place,item_price;
        public ViewHolder(View itemView){
            super(itemView);
            item_image=itemView.findViewById(R.id.item_image);
            item_place=itemView.findViewById(R.id.item_place);
            item_name=itemView.findViewById(R.id.item_name) ;
            item_price=itemView.findViewById(R.id.item_price);


        }


    }



}
