package tvz.gymplanner.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tvz.gymplanner.data.entities.ExerciseSets;

/**
 * Created by Mateo Velenik on 9.5.2015..
 */
public class ExerciseSetsDao extends DaoBase<ExerciseSets> {

    public ExerciseSetsDao(SQLiteDatabase database) {
        super(database, "ExerciseSets", new String[]{"Id", "TrainingsExercisesId", "SetOrder", "NumberOfRepeats"});
    }

    @Override
    protected ExerciseSets fromCursor(Cursor c) {
        ExerciseSets es = new ExerciseSets();
        es.setId(c.getInt(0));
        es.setTrainingsExercisesId(c.getInt(1));
        es.setOrder(c.getInt(2));
        es.setNumberOfRepeats(c.getInt(3));
        return es;
    }

    @Override
    protected ContentValues toContentValues(ExerciseSets e) {
        ContentValues contents = new ContentValues();
        contents.put(columns[1], e.getTrainingsExercisesId());
        contents.put(columns[2], e.getOrder());
        contents.put(columns[3], e.getNumberOfRepeats());
        return contents;
    }

    public ArrayList<ExerciseSets> getAllForExercise(int trainingsExercisesId) {
        String whereClause = String.format("TrainingsExercisesId = %1$s", trainingsExercisesId);
        Cursor cursor = db.query(tableName, columns, whereClause, null, null, null, null);
        ArrayList<ExerciseSets> exerciseSets = new ArrayList<ExerciseSets>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            exerciseSets.add(fromCursor(cursor));
            cursor.moveToNext();
        }
        return exerciseSets;
    }
}
