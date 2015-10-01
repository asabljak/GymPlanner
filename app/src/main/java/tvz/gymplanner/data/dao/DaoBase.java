package tvz.gymplanner.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import tvz.gymplanner.data.entities.EntityBase;

/**
 * Created by Mateo Velenik on 5.5.2015..
 */
public abstract class DaoBase<TEntity extends EntityBase> {
    protected String tableName;
    protected String[] columns;
    protected SQLiteDatabase db;

    /**
     * Creates a new base data access object
     * @param database The database
     * @param tableName The name of the SQLite table
     * @param columns The ordered array of columns from the given table
     */
    protected DaoBase(SQLiteDatabase database, String tableName, String[] columns) {
        this.db = database;
        this.tableName = tableName;
        this.columns = columns;
    }

    /**
     * Gets all the entities of type TEntity
     * @return List of entities
     */
    public ArrayList<TEntity> getAll(){
        ArrayList<TEntity> list = new ArrayList<TEntity>();

        Cursor cursor = db.query(tableName, columns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            list.add(fromCursor(cursor));
            cursor.moveToNext();
        }

        return list;
    }

    /**
     * Gets the entity with the given id
     * @param id The entity id
     * @return The found entity
     */
    public TEntity getById(int id){
        String whereClause = String.format("Id = %1$s", id);
        Cursor cursor = db.query(tableName, columns, whereClause, null, null, null, null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            return fromCursor(cursor);
        }
        return null;
    }

    /**
     * Saves the given entity to the database
     * @param entity The entity to save
     * @return The saved entity
     */
    public TEntity save(TEntity entity){
        ContentValues content = toContentValues(entity);
        TEntity oldEntity = getById(entity.getId());

        int newKey;

        if (oldEntity != null) {
            db.update(tableName, content, String.format("Id = %1$s", oldEntity.getId()), null);
            newKey = oldEntity.getId();
        } else {
            newKey = (int)db.insert(tableName, null, content);
        }
        return getById(newKey);
    }

    /**
     * Deletes the entity with the given id from the database
     * @param id Id of the entity to delete
     * @return True if the delete succeeded; false if not found or failed
     */
    public boolean delete(int id){
        int deletedCount = db.delete(tableName, String.format("Id = %1$s", id), null);
        return deletedCount > 0;
    }

    protected abstract TEntity fromCursor(Cursor c);

    protected abstract ContentValues toContentValues(TEntity e);
}
