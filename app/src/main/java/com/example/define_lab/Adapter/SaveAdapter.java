package com.example.define_lab.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.define_lab.Model.Item_Model;
import com.example.define_lab.Model.Save_Model;
import com.example.define_lab.R;
import com.example.define_lab.SQLiteHelper;

import java.util.List;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.ViewHolder>{
    public Context context;
    List<Save_Model> listdata;
    SQLiteHelper helper;
    SQLiteDatabase sqLiteDatabase;
    public SaveAdapter(Context context, List<Save_Model> listdata) {
        this.context = context;
        this.listdata = listdata;

    }
    @Override
    public SaveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        helper = new SQLiteHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item, parent, false);
        SaveAdapter.ViewHolder viewHolder = new SaveAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SaveAdapter.ViewHolder holder, final int position) {
        final Save_Model listData =  listdata.get(position);
        holder.tv_name.setText("Name : "+listData.getName());
        final String date_id=listData.getData_id();
        holder.tv_id.setText("Id : "+listData.getData_id());
        holder.tv_contact.setText("Contact No : "+listData.getContact());
        holder.tv_location.setText("Location : "+listData.getLocation());
        holder.img_save.setImageResource(R.drawable.star_yellow);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whereClause = "data_id=?";
                String whereArgs[] = {date_id.toString()};
                sqLiteDatabase.delete("users", whereClause, whereArgs);
                listdata.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listdata.size());
                Toast.makeText(context,"Match Delete Sucessfully",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_name,tv_id,tv_contact,tv_location;
        ImageView img_save;


        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.name);
            this.tv_id = itemView.findViewById(R.id.id);
            this.tv_contact = itemView.findViewById(R.id.contact);
            this.tv_location = itemView.findViewById(R.id.location);
            this.img_save = itemView.findViewById(R.id.star);


        }
    }
}


