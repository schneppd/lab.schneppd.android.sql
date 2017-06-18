package lab.schneppd.sqltest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.List;

/**
 * Created by schneppd on 6/17/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //current version of database, change to change the databases data
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SqlTest.sqlite";
    private Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }
    public void onCreate(SQLiteDatabase db) {
        List<String> sqlEntries = SqliteParser.GetSimpleSqlCommand(this.myContext, "sql/table-v0.sql");
        for(String sqlEntry : sqlEntries){
            db.execSQL(sqlEntry);
        }
        sqlEntries = SqliteParser.GetSimpleSqlCommand(this.myContext, "sql/view-v0.sql");
        for(String sqlEntry : sqlEntries){
            db.execSQL(sqlEntry);
        }
        sqlEntries = SqliteParser.GetSimpleSqlCommand(this.myContext, "sql/index-v0.sql");
        for(String sqlEntry : sqlEntries){
            db.execSQL(sqlEntry);
        }
        sqlEntries = SqliteParser.GetSimpleSqlCommand(this.myContext, "sql/trigger-v0.sql");
        for(String sqlEntry : sqlEntries){
            db.execSQL(sqlEntry);
        }
        sqlEntries = SqliteParser.GetSimpleSqlCommand(this.myContext, "sql/data-v0.sql");
        for(String sqlEntry : sqlEntries){
            db.execSQL(sqlEntry);
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // used when database version is increased
        //simple delete old and create new
        DatabaseHelper.DeleteDatabase(myContext);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // used when database version is decreased
        DatabaseHelper.DeleteDatabase(myContext);
        onUpgrade(db, oldVersion, newVersion);
    }

    static void DeleteDatabase(Context context){
        File oldDb = context.getDatabasePath(DatabaseHelper.DATABASE_NAME);
        oldDb.delete();
    }
}
