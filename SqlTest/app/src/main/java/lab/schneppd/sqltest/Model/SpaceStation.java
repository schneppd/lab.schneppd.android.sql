package lab.schneppd.sqltest.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by schneppd on 6/18/17.
 */

public class SpaceStation {
    private int id_space_station;
    private String name;
    private Date tcreation;
    private boolean is_unionized;

    public SpaceStation(){
        setId_space_station(0);
        setTcreation(new Date());
        setName("");
        is_unionized = false;
    }

    public SpaceStation(Cursor c){
        this.setId_space_station(c.getInt(c.getColumnIndexOrThrow(SpaceStationEntry.COLUMN_NAME_ID_SPACE_STATION)));
        this.setName(c.getString(c.getColumnIndexOrThrow(SpaceStationEntry.COLUMN_NAME_NAME)));
        String rawDate = c.getString(c.getColumnIndexOrThrow(SpaceStationEntry.COLUMN_NAME_TCREATION));
        this.setTcreation(new Date());
        try{
            this.setTcreation(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(rawDate));
        }
        catch(ParseException e){
            Log.d("Error parsing date: ", e.toString());
        }
        int rawIs_unionized = c.getInt(c.getColumnIndexOrThrow(SpaceStationEntry.COLUMN_NAME_IS_UNIONIZED));
        this.setIs_unionized((rawIs_unionized > 0)? true : false);
    }

    public static List<SpaceStation> RestorSpaceStations(SQLiteDatabase db){
        // select list of SpaceStations
        String[] projection = {SpaceStationEntry.COLUMN_NAME_ID_SPACE_STATION, SpaceStationEntry.COLUMN_NAME_NAME, SpaceStationEntry.COLUMN_NAME_TCREATION, SpaceStationEntry.COLUMN_NAME_IS_UNIONIZED};
        Cursor cursor = db.query(
                SpaceStationEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        List<SpaceStation> res = new ArrayList<>();
        while(cursor.moveToNext()) {
            SpaceStation sp = new SpaceStation(cursor);
            res.add(sp);
        }
        cursor.close();
        return res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_space_station() {
        return id_space_station;
    }

    public void setId_space_station(int id_space_station) {
        this.id_space_station = id_space_station;
    }

    public Date getTcreation() {
        return tcreation;
    }

    public void setTcreation(Date tcreation) {
        this.tcreation = tcreation;
    }

    public boolean is_unionized() {
        return is_unionized;
    }

    public void setIs_unionized(boolean is_unionized) {
        this.is_unionized = is_unionized;
    }

    /* Inner class that defines the table contents */
    public static class SpaceStationEntry implements BaseColumns {
        public static final String TABLE_NAME = "SpaceStation";
        public static final String COLUMN_NAME_ID_SPACE_STATION = "id_space_station";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TCREATION = "tcreation";
        public static final String COLUMN_NAME_IS_UNIONIZED = "is_unionized";
    }

    public String GetDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append(SpaceStationEntry.TABLE_NAME + " infos: ");
        sb.append(System.getProperty("line.separator"));

        sb.append(SpaceStationEntry.COLUMN_NAME_ID_SPACE_STATION + ": ");
        sb.append(getId_space_station());
        sb.append(System.getProperty("line.separator"));

        sb.append(SpaceStationEntry.COLUMN_NAME_NAME + ": ");
        sb.append(getName());
        sb.append(System.getProperty("line.separator"));

        sb.append(SpaceStationEntry.COLUMN_NAME_TCREATION + ": ");
        sb.append(getTcreation());
        sb.append(System.getProperty("line.separator"));

        sb.append(SpaceStationEntry.COLUMN_NAME_IS_UNIONIZED + ": ");
        sb.append(is_unionized());
        sb.append(System.getProperty("line.separator"));

        return sb.toString();
    }

    public static void Save(SQLiteDatabase db, SpaceStation sp){
        if(sp.getId_space_station() > 0){
            // update command
            // New value for one column
            ContentValues values = new ContentValues();
            values.put(SpaceStationEntry.COLUMN_NAME_NAME, sp.getName());

            // Define 'where' part of query.
            String selection = SpaceStationEntry.COLUMN_NAME_ID_SPACE_STATION + " = ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { Integer.toString(sp.getId_space_station()) };

            int count = db.update(
                    SpaceStationEntry.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);

        }
        else{
            // insert command
            ContentValues values = new ContentValues();
            values.put(SpaceStationEntry.COLUMN_NAME_NAME, sp.getName());
            int val_is_unionized = (sp.is_unionized())? 1 : 0;
            values.put(SpaceStationEntry.COLUMN_NAME_IS_UNIONIZED, val_is_unionized);

            long rowId = db.insert(SpaceStationEntry.TABLE_NAME, null, values);
        }

    }

    public static void Delete(SQLiteDatabase db, SpaceStation sp){
        // Define 'where' part of query.
        String selection = SpaceStationEntry.COLUMN_NAME_ID_SPACE_STATION + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { Integer.toString(sp.getId_space_station()) };

        db.delete(SpaceStationEntry.TABLE_NAME, selection, selectionArgs);
    }
}
