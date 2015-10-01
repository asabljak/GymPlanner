package tvz.gymplanner.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tvz.gymplanner.data.entities.TrainingsExercises;

/**
 * Created by Mateo Velenik on 5.5.2015..
 */
public class TrainingsExercisesDao extends DaoBase<TrainingsExercises> {

    public TrainingsExercisesDao(SQLiteDatabase database) {
        super(database, "TrainingsExercises", new String[]{"Id", "TrainingId", "ExerciseId"});
    }

    @Override
    protected TrainingsExercises fromCursor(Cursor c) {
        TrainingsExercises te = new TrainingsExercises();
        te.setId(c.getInt(0));
        te.setTrainingId(c.getInt(1));
        te.setExerciseId(c.getInt(2));
        return te;
    }

    @Override
    protected ContentValues toContentValues(TrainingsExercises e) {
        ContentValues contents = new ContentValues();
        contents.put(columns[1], e.getTrainingId());
        contents.put(columns[2], e.getExerciseId());
        return contents;
    }

    public ArrayList<TrainingsExercises> getAllForTraining(int trainingId){
        String whereClause = String.format("TrainingId = %1$s", trainingId);
        Cursor cursor = db.query(tableName, columns, whereClause, null, null, null, null);
        ArrayList<TrainingsExercises> trainingsExercises = new ArrayList<TrainingsExercises>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            trainingsExercises.add(fromCursor(cursor));
            cursor.moveToNext();
        }
        return trainingsExercises;
    }
}
