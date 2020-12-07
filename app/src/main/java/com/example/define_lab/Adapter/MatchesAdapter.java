package com.example.define_lab.Adapter;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.define_lab.Model.Item_Model;
import com.example.define_lab.Model.Save_Model;
import com.example.define_lab.R;
import com.example.define_lab.SQLiteHelper;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder>{
    public Context context;
    List<Item_Model> listdata;
    List<Save_Model> list;
    SQLiteHelper helper;
    SQLiteDatabase sqLiteDatabase;
    SparseBooleanArray mStateButtons = new SparseBooleanArray();

    public MatchesAdapter(Context context, List<Item_Model> listdata, List<Save_Model> list) {
        this.context = context;
        this.listdata = listdata;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        helper = new SQLiteHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item_Model listData =  listdata.get(position);
        holder.tv_name.setText("Name : "+listData.getName());
        holder.tv_id.setText("Id : "+listData.getId());
        holder.tv_contact.setText("Contact No : "+listData.getContact().getPhone());
        holder.tv_location.setText("Location : "+listData.getLocation().getAddress());

        holder.img_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String SQLiteDataBaseQueryHolder = "INSERT INTO users (data_id,name,contact,location,star) VALUES('" +listData.getId() + "','" + listData.getName() + "','" + listData.getContact().getPhone()+  "','"  +  listData.getLocation().getAddress() + "','"  + "Yes" + "');";
                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
                Toast.makeText(context,"Match Saved Sucessfully",Toast.LENGTH_SHORT).show();
                holder.img_save.setImageResource(R.drawable.star_yellow);
                holder.img_save.setEnabled(false);
            }
        });

                for(int i=0;i<list.size();i++) {

                    if (list.get(i).getData_id().equalsIgnoreCase(listData.getId())) {
                        Log.d("data",list.get(i).getData_id()+","+position);
                        holder.img_save.setImageResource(R.drawable.star_yellow);
                        holder.img_save.setEnabled(false);

                    }
                }


        holder.setIsRecyclable(false);

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

