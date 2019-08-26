package com.haritsdeveloper.recyclerview;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MovieAdapter movieAdapter;
    RecyclerView rvMain;
    Toolbar toolbar;
    ArrayList<ModelMovie> arrayList;
    private String[]id = {"1001","1002","1003"};
    private String[]judul = {"seribu satu orang hebat","temukan jati diri","siapa saya ini"};
    private String[]subjudul = {"orang hebat","perjuangan dakwah","petani kode"};
    private int[]gambar = {R.drawable.channel_tv, R.drawable.desain_tanpa_judul,R.drawable.channel_tv};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrayList = new ArrayList<>();

        rvMain = findViewById(R.id.rvMain);

        rvMain = findViewById(R.id.rvMain);


        movieAdapter = new MovieAdapter(arrayList);

        rvMain.setAdapter(movieAdapter);

        rvMain.setLayoutManager(new LinearLayoutManager(this));

    }
    private void setData(){
        int count = 0;
        for (String id : id) {
            arrayList.add(new ModelMovie(id,judul[count],subjudul[count],gambar[count]));
            count++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (movieAdapter != null) movieAdapter.getFilter().filter(s);
                Menu menu = null;
                getMenuInflater().inflate(R.menu.menu_bar,menu);
                MenuItem menuItem = menu.findItem(R.id.spread);
                SearchView searchView1 = (SearchView)menuItem.getActionView();
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.changeLanguage:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                break;





        }
        return super.onOptionsItemSelected(item);
    }
}