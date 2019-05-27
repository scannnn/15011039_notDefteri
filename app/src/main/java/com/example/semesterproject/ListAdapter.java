package com.example.semesterproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {

    Context con;
    private List<not> mNotes;
    public ListAdapter(List<not> mNotes,Context con){
        this.mNotes = sortArrayList(mNotes);
        this.con = con;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mItemHeader;
        private ImageButton mDuzenle;
        private ImageView mOncelik;
        private LinearLayout mRecycle;
        private ImageButton mSil;

        public ListViewHolder(View itemView){
            super(itemView);
            mItemHeader = itemView.findViewById(R.id.itemHeader);
            mDuzenle = itemView.findViewById(R.id.duzenle);
            mOncelik = itemView.findViewById(R.id.yildiz);
            mSil = itemView.findViewById(R.id.sil);
            mRecycle = itemView.findViewById(R.id.recycle_layout);
            itemView.setOnClickListener(this);
        }

        public void bindView(final int position){
            mItemHeader.setText(mNotes.get(position).getBaslik());
            mRecycle.setBackgroundColor(mNotes.get(position).getRenk());
            if(mNotes.get(position).getOncelik() == 1) mOncelik.setVisibility(View.VISIBLE);
            else mOncelik.setVisibility(View.INVISIBLE);
            mDuzenle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(con,not_goruntule.class);
                    intent.putExtra("baslik",mNotes.get(position).getBaslik());
                    intent.putExtra("not",mNotes.get(position).getNot());
                    intent.putExtra("tarih",mNotes.get(position).getTarih());
                    intent.putExtra("renk",""+mNotes.get(position).getRenk());
                    intent.putExtra("oncelik",""+mNotes.get(position).getOncelik());
                    intent.putExtra("galeri_path",mNotes.get(position).getGaleri_path());
                    con.startActivity(intent);
                }
            });
            mSil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    veri_kaynagi vk = new veri_kaynagi(con);
                    vk.ac();
                    vk.not_sil(mNotes.get(position));
                    vk.kapat();
                    mNotes.remove(position);
                    notlar.recyclerView.removeViewAt(position);
                    notlar.listAdapter.notifyItemRemoved(position);
                    notlar.listAdapter.notifyItemRangeChanged(position,mNotes.size());
                    Toast.makeText(con,"Notunuz başarıyla silinmiştir.",Toast.LENGTH_SHORT).show();
                }
            });
        }
        public void onClick(View view){

        }
    }
    public List<not> sortArrayList(List<not> array){
        not temp;
        for(int i=0;i<array.size();i++){
            if (array.get(i).getOncelik() == 1){
                temp = array.get(i);
                array.remove(i);
                array.add(0,temp);
            }
        }
        return array;
    }
}
