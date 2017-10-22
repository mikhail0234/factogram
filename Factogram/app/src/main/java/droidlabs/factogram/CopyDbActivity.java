
/*package droidlabs.factogram;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLDataException;

public class CopyDbActivity extends AppCompatActivity {

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        DatabaseHelper myDbHelper = new DatabaseHelper(CopyDbActivity.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        } catch (SQLDataException e) {
            e.printStackTrace();
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        } catch (SQLDataException e) {
            e.printStackTrace();
        }
        Toast.makeText(CopyDbActivity.this, "Success", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("EMP_TABLE", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        Toast.makeText(CopyDbActivity.this,
                                "_id: " + c.getString(0) + "\n" +
                                        "E_HEADER: " + c.getString(1) + "\n" +
                                        "E_ARTICLE: " + c.getString(2) + "\n" +
                                        "E_TAG1:  " + c.getString(3),
                                Toast.LENGTH_LONG).show();
                    } while (c.moveToNext());
                }
    }

}
*/