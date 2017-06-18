package lab.schneppd.sqltest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lab.schneppd.sqltest.Model.SpaceStation;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //only used while debugging
        DatabaseHelper.DeleteDatabase(getApplicationContext());
        dbHelper = new DatabaseHelper(getApplicationContext());

    }

    @Override
    protected void onDestroy() {
        //free the database file, must only be called at application destroy phase to reduce consumption of ressource
        dbHelper.close();
        super.onDestroy();
    }

    public void onClickTestFeature(View v)
    {
        RefreshOutput();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SpaceStation sp2 = new SpaceStation();
        sp2.setName("SS13");
        sp2.setIs_unionized(true);
        SpaceStation.Save(db, sp2);//should be 4

        RefreshOutput();

        sp2.setId_space_station(4);
        sp2.setName("SS133");
        SpaceStation.Save(db, sp2);

        RefreshOutput();

        SpaceStation.Delete(db, sp2);

        RefreshOutput();

    }

    private String GetContentOfDatabase(){
        StringBuilder output = new StringBuilder();

        //get data back
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<SpaceStation> spaceStations = SpaceStation.RestorSpaceStations(db);

        for(SpaceStation spaceStation : spaceStations){
            output.append(spaceStation.GetDescription());
            output.append(System.getProperty("line.separator"));
        }

        return output.toString();
    }

    private void RefreshOutput(){
        String output = GetContentOfDatabase();

        TextView tvOutput = (TextView) findViewById(R.id.tvOutput);
        String message = output.toString();
        tvOutput.setText(message);
    }
}
