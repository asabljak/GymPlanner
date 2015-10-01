package tvz.gymplanner.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import tvz.gymplanner.data.entities.Training;

/**
 * Created by Mateo Velenik on 12.4.2015..
 */
public class TrainingDao extends DaoBase<Training> {

    public TrainingDao(SQLiteDatabase database) {
        super(database, "Trainings", new String[]{"Id", "Name", "Description", "CreationDate"});
    }

    @Override
    protected Training fromCursor(Cursor c) {
        Training t = new Training();
        t.setId(c.getInt(0));
        t.setName(c.getString(1));
        t.setDescription(c.getString(2));
        t.setDateCreated(new Date(c.getLong(3)));
        return t;
    }

    @Override
    protected ContentValues toContentValues(Training t) {
        ContentValues contents = new ContentValues();
        contents.put(columns[1], t.getName());
        contents.put(columns[2], t.getDescription());
        contents.put(columns[3], t.getDateCreated().getTime());
        return contents;
    }

    public Training getByName(String name){
        String whereClause = String.format("Name = '%1$s'", name);
        Cursor cursor = db.query(tableName, columns, whereClause, null, null, null, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            return fromCursor(cursor);
        }
        return null;
    }
}
