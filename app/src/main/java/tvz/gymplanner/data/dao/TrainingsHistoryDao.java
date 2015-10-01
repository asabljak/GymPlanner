package tvz.gymplanner.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import tvz.gymplanner.data.entities.TrainingsHistory;

/**
 * Created by Mateo Velenik on 9.5.2015..
 */
public class TrainingsHistoryDao extends DaoBase<TrainingsHistory> {

    public TrainingsHistoryDao(SQLiteDatabase database) {
        super(database, "TrainingsHistory", new String[]{"Id", "TrainingId", "TrainingDate"});
    }

    @Override
    protected TrainingsHistory fromCursor(Cursor c) {
        TrainingsHistory th = new TrainingsHistory();
        th.setId(c.getInt(0));
        th.setTrainingId(c.getInt(1));
        th.setDateTrained(new Date(c.getLong(2)));
        return th;
    }

    @Override
    protected ContentValues toContentValues(TrainingsHistory e) {
        ContentValues contents = new ContentValues();
        contents.put(columns[1], e.getTrainingId());
        contents.put(columns[2], e.getDateTrained().getTime());
        return contents;
    }
}
