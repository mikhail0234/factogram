package droidlabs.factogram;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import droidlabs.factogram.adapter.FactListAdapter;
import droidlabs.factogram.dto.FactDTO;
import droidlabs.factogram.favorite.MaterialFavoriteButton;

/**
 * Created by Misha on 05.06.2016.
 */
public class FactActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String id;
    private String title;
    private String text;
    private String tag1;
    private String tag2;
    private String tag3;
    private String favorite;



    private int starID;

    TextView tidFavorite;

    TextView titleView;
    TextView textView;
    TextView idView;
    TextView tag1View;
    TextView tag2View;
    TextView tag3View;
    MaterialFavoriteButton starButton;

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact);

        Intent intent = getIntent();
        id = intent.getStringExtra(FactListAdapter.ID_MESSAGE);
        title = intent.getStringExtra(FactListAdapter.TITLE_MESSAGE);
        text = intent.getStringExtra(FactListAdapter.TEXT_MESSAGE);
        tag1 = intent.getStringExtra(FactListAdapter.TAG1_MESSAGE);
        tag2 = intent.getStringExtra(FactListAdapter.TAG2_MESSAGE);
        tag3 = intent.getStringExtra(FactListAdapter.TAG3_MESSAGE);
        favorite = intent.getStringExtra(FactListAdapter.FAVORITE_MESSAGE);

        initToolbar();

        idView.setText(String.valueOf(starID));
        starButton.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite1) {
                        //TODO
                        DatabaseHelper myDbHelper = new DatabaseHelper(buttonView.getContext(), "factogram");
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



                        if (favorite1) {

                            ContentValues cv = new ContentValues();
                            cv.put("_favorite","TRUE");
                            db.update("facts", cv, "_id" + "=" + id, null);
                        } else {
                            ContentValues cv = new ContentValues();
                            cv.put("_favorite","FALSE");
                            db.update("facts", cv, "_id" + "=" + id, null);
                        }


                    }
                });

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        //toolbar.inflateMenu(R.menu.menu);

        titleView = (TextView) findViewById(R.id.title);
        titleView.setText(title);

        textView = (TextView) findViewById(R.id.text);
        textView.setText(text);

        idView = (TextView) findViewById(R.id.id);
        idView.setText(id);

        tag1View = (TextView) findViewById(R.id.tag1);
        tag1View.setText(tag1);

        tag2View = (TextView) findViewById(R.id.tag2);
        tag2View.setText(tag2);

        tag3View = (TextView) findViewById(R.id.tag3);
        tag3View.setText(tag3);



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

        c = db.rawQuery("select * from facts where _id ='" + id + "'", null);

        if (c.moveToFirst()) {

            favorite = c.getString(6);
        }


        starButton = (MaterialFavoriteButton) findViewById(R.id.favorite_star);
        if (favorite.equals("TRUE"))
            starButton.setFavorite(true, false);
        else
            starButton.setFavorite(false);
    }


    public void onFavoriteChanged(Context context){
        DatabaseHelper myDbHelper = new DatabaseHelper(context, "factogram");
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
        c = db.rawQuery("UPDATE facts SET _favorite =true WHERE _id like '" + this.id + "'", null);


    }
}
