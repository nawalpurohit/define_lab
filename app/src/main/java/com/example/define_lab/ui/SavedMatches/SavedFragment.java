package com.example.define_lab.ui.SavedMatches;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.define_lab.Adapter.MatchesAdapter;
import com.example.define_lab.Adapter.SaveAdapter;
import com.example.define_lab.Model.Item_Model;
import com.example.define_lab.Model.Save_Model;
import com.example.define_lab.R;
import com.example.define_lab.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.define_lab.SQLiteHelper.Key_data_id;

public class SavedFragment extends Fragment {
    SQLiteHelper helper;
    SQLiteDatabase sqLiteDatabase;
    SaveAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Save_Model> data=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        showdb();
        RecyclerView recyclerView= root.findViewById(R.id.recy_save);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SaveAdapter(getContext(),data);
        recyclerView.setAdapter(adapter);

        return root;
    }
    public void showdb(){

        helper = new SQLiteHelper(getActivity());
        sqLiteDatabase = helper.getWritableDatabase();

       Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

        if (cursor.moveToFirst()) {
            do {

                String data_id = cursor.getString(cursor.getColumnIndex("data_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String contact  = cursor.getString(cursor.getColumnIndex("contact"));
                String  location  = cursor.getString(cursor.getColumnIndex("location"));
                Log.e("name" , cursor.getString(cursor.getColumnIndex("name")));
                Log.e("location" , cursor.getString(cursor.getColumnIndex("location")));

                data.add(new Save_Model(data_id,name,contact,location,"Yes"));



            } while (cursor.moveToNext());
        }

    }
}