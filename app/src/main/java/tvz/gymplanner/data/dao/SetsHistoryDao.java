package tvz.gymplanner.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import tvz.gymplanner.data.entities.SetsHistory;

/**
 * Created by Mateo Velenik on 9.5.2015..
 */
public class SetsHistoryDao extends DaoBase<SetsHistory> {

    public SetsHistoryDao(SQLiteDatabase database){
        super(database, "SetsHistory", new String[]{"Id", "ExerciseHistoryId", "SetOrder", "NumberOfRepeats"});
    }

    @Override
    protected SetsHistory fromCursor(Cursor c) {
        SetsHistory sh = new SetsHistory();
        sh.setId(c.getInt(0));
        sh.setExerciseHistoryId(c.getInt(1));
        sh.setOrder(c.getInt(2));
        sh.setNumberOfRepeats(c.getInt(3));
        return sh;
    }

    @Override
    protected ContentValues toContentValues(SetsHistory e) {
        ContentValues contents = new ContentValues();
        contents.put(columns[1], e.getExerciseHistoryId());
        contents.put(columns[2], e.getOrder());
        contents.put(columns[3], e.getNumberOfRepeats());
        return contents;
    }
}
