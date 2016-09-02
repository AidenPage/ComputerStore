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
import computerstore.com.computerstore.domain.components.OpticalDevices;
import computerstore.com.computerstore.respository.components.OpticalDevicesRepository;


public class OpticalDevicesRepositoryImpl extends SQLiteOpenHelper implements OpticalDevicesRepository {
    public static final String TABLE_NAME = "optical_devices";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_STOCK = "stock";
    private static final int DATABASE_VERSION = DBConstants.DATABASE_VERSION+8;

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL , "
            + COLUMN_PRICE + " TEXT NOT NULL , "
            + COLUMN_STOCK + " TEXT  NOT NULL );";


    public OpticalDevicesRepositoryImpl(Context context) {
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
    public OpticalDevices findById(Long id) {

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
            final OpticalDevices opticalDevices = new OpticalDevices.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .price(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .build();

            return opticalDevices;
        } else {
            return null;
        }
    }

    @Override
    public OpticalDevices save(OpticalDevices entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_STOCK, entity.getStock());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        OpticalDevices insertedEntity = new OpticalDevices.Builder()
                .OpticalDevices(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public OpticalDevices update(OpticalDevices entity) {
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
    public OpticalDevices delete(OpticalDevices entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<OpticalDevices> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<OpticalDevices> opticalDevices = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final OpticalDevices personAddress = new OpticalDevices.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .price(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .build();
                opticalDevices.add(personAddress);
            } while (cursor.moveToNext());
        }
        return opticalDevices;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

}
