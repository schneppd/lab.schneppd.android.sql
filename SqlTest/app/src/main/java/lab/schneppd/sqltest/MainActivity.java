package lab.schneppd.sqltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //only used while debugging
        //DatabaseHelper.DeleteDatabase(getApplicationContext());
        db = new DatabaseHelper(getApplicationContext());
        db.getWritableDatabase();
    }

    @Override
    protected void onDestroy() {
        //free the database file, must only be called at application destroy phase to reduce consumption of ressource
        db.close();
        super.onDestroy();
    }
}
