package computerstore.com.computerstore.respository.components.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import computerstore.com.computerstore.conf.databases.DBConstants;
import computerstore.com.computerstore.domain.components.Notebook;
import computerstore.com.computerstore.respository.components.NotebookRepository;


public class NotebookRepositoryImpl extends SQLiteOpenHelper implements NotebookRepository {
    public static final String TABLE_NAME = "notebook";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_STOCK = "stock";
    private static final int DATABASE_VERSION = DBConstants.DATABASE_VERSION+7;

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL , "
            + COLUMN_PRICE + " TEXT NOT NULL , "
            + COLUMN_STOCK + " TEXT  NOT NULL );";


    public NotebookRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public Notebook findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_DESCRIPTION,
                        COLUMN_PRICE,
                        COLUMN_STOCK},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Notebook notebook = new Notebook.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .price(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .build();

            return notebook;
        } else {
            return null;
        }
    }

    @Override
    public Notebook save(Notebook entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_STOCK, entity.getStock());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Notebook insertedEntity = new Notebook.Builder()
                .Notebook(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Notebook update(Notebook entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_STOCK, entity.getStock());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Notebook delete(Notebook entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Notebook> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Notebook> notebook = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Notebook personAddress = new Notebook.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .price(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .build();
                notebook.add(personAddress);
            } while (cursor.moveToNext());
        }
        return notebook;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

}
