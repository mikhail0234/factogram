package droidlabs.factogram;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import droidlabs.factogram.adapter.TabsFragmentAdapter;
import droidlabs.factogram.dto.CategoryDTO;
import droidlabs.factogram.dto.FactDTO;


public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    TabsFragmentAdapter adapter;
    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabs();

    }

    private void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);

    }


    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        onPostExecuteFacts();
        //onPostExecuteCategories();




        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }


    private void initNavigationView() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.actionVideo:
                        showNotificationTab();

                }
                return true;
            }
        });
    }

    private void showNotificationTab(){
        viewPager.setCurrentItem(Constants.TAB_TWO);
    }


    private void onPostExecuteFacts(){
        DatabaseHelper myDbHelper = new DatabaseHelper(this);
        List<FactDTO> list = new ArrayList<>();
        List<CategoryDTO> listCategory = new ArrayList<>();

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
        c = myDbHelper.query("facts", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                list.add(new FactDTO(c.getString(1), c.getString(2)));
            } while (c.moveToNext());
        }
        adapter.setData(list);

        /*
        c = myDbHelper.query("categories", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                listCategory.add(new CategoryDTO(c.getString(1), c.getInt(2)));
            } while (c.moveToNext());
        }
        adapter.setDataCategory(listCategory);
        */

    }

    private void onPostExecuteCategories(){
        DatabaseHelper myDbHelper = new DatabaseHelper(this);
        List<CategoryDTO> listCategory = new ArrayList<>();

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

        c = myDbHelper.query("categories", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                listCategory.add(new CategoryDTO(c.getString(1), c.getInt(2)));
            } while (c.moveToNext());
        }
        adapter.setDataCategory(listCategory);


    }


}
