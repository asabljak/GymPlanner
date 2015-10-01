package tvz.gymplanner.data.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import tvz.gymplanner.data.sql.TrainingAppSQLiteHelper;

/**
 * Created by Mateo Velenik on 5.5.2015..
 */
public abstract class RepositoryBase {
    protected TrainingAppSQLiteHelper dbHelper;
    protected SQLiteDatabase db;

    protected RepositoryBase(Context ctx){
        this.dbHelper = new TrainingAppSQLiteHelper(ctx);
        this.db = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getUnderlyingDatabase(){
        return db;
    }
}
