package tvz.gymplanner.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import tvz.gymplanner.data.entities.Exercise;

/**
 * Created by Mateo Velenik on 6.5.2015..
 */
public class ExerciseDao extends DaoBase<Exercise> {

    public ExerciseDao(SQLiteDatabase database) {
        super(database, "Exercises", new String[]{"Id", "Name", "Description", "Image"});
    }

    @Override
    protected Exercise fromCursor(Cursor c) {
        Exercise e = new Exercise();
        e.setId(c.getInt(0));
        e.setName(c.getString(1));
        e.setDescription(c.getString(2));
        e.setImage(c.getString(3));
        return e;
    }

    @Override
    protected ContentValues toContentValues(Exercise e) {
        ContentValues contents = new ContentValues();
        contents.put(columns[1], e.getName());
        contents.put(columns[2], e.getDescription());
        contents.put(columns[3], e.getImage());
        return contents;
    }

    public Exercise getByName(String name) {
        String whereClause = String.format("Name = '%1$s'", name);
        Cursor cursor = db.query(tableName, columns, whereClause, null, null, null, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            return fromCursor(cursor);
        }
        return null;
    }
}
