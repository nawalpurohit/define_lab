package com.example.define_lab.ui.AllMatches;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.define_lab.Adapter.MatchesAdapter;
import com.example.define_lab.Api.Api_Client;
import com.example.define_lab.Api.Api_Interface;
import com.example.define_lab.Model.Item_Model;
import com.example.define_lab.Model.Message_Model;
import com.example.define_lab.Model.Save_Model;
import com.example.define_lab.R;
import com.example.define_lab.SQLiteHelper;
import com.example.define_lab.ui.AllMatches.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class All_MatchesFragment extends Fragment {

    String header="application/json";
    String ll="40.7484,-73.9857";
    String v="20180616";
    String token="NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ";
    MatchesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper helper;
    List<Item_Model> data=new ArrayList<>();
    List<Save_Model> list=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        helper = new SQLiteHelper(getContext());
        sqLiteDatabase = helper.getWritableDatabase();
        RecyclerView recyclerView= root.findViewById(R.id.recy_matches);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MatchesAdapter(getContext(),data,list);
        recyclerView.setAdapter(adapter);

        matches();
        showdb();


        return root;
    }

    public void showdb(){

        helper = new SQLiteHelper(getActivity());
        sqLiteDatabase = helper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

        if (cursor.moveToFirst()) {
            do {

                String data_id = cursor.getString(cursor.getColumnIndex("data_id"));


                list.add(new Save_Model(data_id,"","","","Yes"));



            } while (cursor.moveToNext());
        }

    }

    private void matches() {
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        Api_Interface service = Api_Client.getClient().create(Api_Interface.class);
        Call<Message_Model> call = service.getmatches(header,ll,token,v);
        Log.i("url",call.request().url().toString());
        call.enqueue(new Callback<Message_Model>() {
            @Override
            public void onResponse(Call<Message_Model> call, Response<Message_Model> response) {
                Log.d("response_code", String.valueOf(response.code()));
                progressDialog.dismiss();
                if (response.code()==200){

                    data.addAll(response.body().getResponse().getVenues());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Message_Model> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}