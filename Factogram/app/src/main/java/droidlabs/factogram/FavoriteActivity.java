package droidlabs.factogram;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import droidlabs.factogram.adapter.CategoryListAdapter;

//import droidlabs.factogram.adapter.TabsFragmentAdapter;
import droidlabs.factogram.adapter.FactFragmentAdapter;
//import droidlabs.factogram.adapter.FactListAdapter;
import droidlabs.factogram.dto.CategoryDTO;
import droidlabs.factogram.dto.FactDTO;


public class FavoriteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private String message;
    FactFragmentAdapter adapter;
    private ViewPager viewPager;

    private List<FactDTO> data;
    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initToolbar();
        initTabs();

    }



    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new FactFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        onPostExecuteFacts();
        //onPostExecuteCategories();


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Избранное");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        //toolbar.inflateMenu(R.menu.menu);
    }


    private void onPostExecuteFacts(){
        DatabaseHelper myDbHelper = new DatabaseHelper(this, "factogram");
        List<FactDTO> list = new ArrayList<>();

        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        SQLiteDatabase db = myDbHelper.getReadableDatabase();

        c = db.rawQuery("select * from facts where _favorite='" + "TRUE" + "'", null);

        if (c.moveToFirst()) {
            do {
                list.add(new FactDTO(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), (c.getString(6)=="TRUE")));
            } while (c.moveToNext());
        }
        adapter.setData(list);


    }



}