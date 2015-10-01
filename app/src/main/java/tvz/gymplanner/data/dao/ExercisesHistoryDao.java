package tvz.gymplanner.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import tvz.gymplanner.data.entities.ExercisesHistory;

/**
 * Created by Mateo Velenik on 9.5.2015..
 */
public class ExercisesHistoryDao extends DaoBase<ExercisesHistory> {

    public ExercisesHistoryDao(SQLiteDatabase database){
        super(database, "ExercisesHistory", new String[]{"Id", "TrainingHistoryId", "ExerciseId"});
    }

    @Override
    protected ExercisesHistory fromCursor(Cursor c) {
        ExercisesHistory eh = new ExercisesHistory();
        eh.setId(c.getInt(0));
        eh.setTrainingHistoryId(c.getInt(1));
        eh.setExerciseId(c.getInt(2));
        return eh;
    }

    @Override
    protected ContentValues toContentValues(ExercisesHistory e) {
        ContentValues contents = new ContentValues();
        contents.put(columns[1], e.getTrainingHistoryId());
        contents.put(columns[2], e.getExerciseId());
        return contents;
    }
}
