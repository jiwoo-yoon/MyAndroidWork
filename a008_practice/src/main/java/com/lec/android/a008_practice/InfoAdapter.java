package com.lec.android.a008_practice;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    List<Info> infos = new ArrayList<Info>();

    static InfoAdapter infoAdapter;

    public InfoAdapter() {this.infoAdapter = this;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View itemView = inf.inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Info item = infos.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {

        return infos.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvAge, tvAddr;
        ImageButton btnDelItem;
        Switch swOnOff;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddr = itemView.findViewById(R.id.tvAddr);
            btnDelItem = itemView.findViewById(R.id.btnDelItem);
            swOnOff = itemView.findViewById(R.id.swOnOff);

            swOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        tvAge.setVisibility(View.INVISIBLE);
                        tvAddr.setVisibility(View.INVISIBLE);
                    }else{
                        tvAge.setVisibility(View.VISIBLE);
                        tvAddr.setVisibility(View.VISIBLE);
                    }
                }
            });

            btnDelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    infoAdapter.removeItem(getAdapterPosition());
                    infoAdapter.notifyDataSetChanged();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), InfoDetail.class);
                    intent.putExtra("info", infoAdapter.getItem(position));
                    v.getContext().startActivity(intent);
                }
            });
         }

         public void setItem(Info item){
            tvName.setText(item.getName());
            tvAddr.setText(item.getAddr());
            tvAge.setText(item.getAge());
         }
    }

    public void addItem(Info item) {  infos.add(item); }
    public void addItem(int position, Info item) {   infos.add(position, item);}
    public void setItems(ArrayList<Info> items) {   this.infos = items;}
    public Info getItem(int position) {   return infos.get(position);}
    public void setItem(int position, Info item) {   infos.set(position, item); }
    public void removeItem(int position){ infos.remove(position); }

}//end class
