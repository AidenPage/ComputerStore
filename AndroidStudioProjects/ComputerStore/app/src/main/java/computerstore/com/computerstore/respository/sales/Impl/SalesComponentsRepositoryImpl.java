package computerstore.com.computerstore.respository.sales.Impl;

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
import computerstore.com.computerstore.domain.sales.SalesComponents;
import computerstore.com.computerstore.respository.sales.SalesComponentsRepository;


public class SalesComponentsRepositoryImpl extends SQLiteOpenHelper implements SalesComponentsRepository {
    public static final String TABLE_NAME = "sales_components";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT_ID = "productID";
    public static final String COLUMN_SALE_ID = "saleID";
    public static final String COLUMN_AMOUNT = "amount";
    private static final int DATABASE_VERSION = DBConstants.DATABASE_VERSION+14;

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PRODUCT_ID + " TEXT NOT NULL , "
            + COLUMN_SALE_ID + " TEXT NOT NULL , "
            + COLUMN_AMOUNT + " TEXT  NOT NULL );";


    public SalesComponentsRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public SalesComponents findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_PRODUCT_ID,
                        COLUMN_SALE_ID,
                        COLUMN_AMOUNT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final SalesComponents salesCom = new SalesComponents.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .productNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID)))
                    .saleID(cursor.getString(cursor.getColumnIndex(COLUMN_SALE_ID)))
                    .amount(cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT)))
                    .build();

            return salesCom;
        } else {
            return null;
        }
    }

    @Override
    public SalesComponents save(SalesComponents entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_PRODUCT_ID, entity.getProductNumber());
        values.put(COLUMN_SALE_ID, entity.getSaleID());
        values.put(COLUMN_AMOUNT, entity.getAmount());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        SalesComponents insertedEntity = new SalesComponents.Builder()
                .SalesComponents(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public SalesComponents update(SalesComponents entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_PRODUCT_ID, entity.getProductNumber());
        values.put(COLUMN_SALE_ID, entity.getSaleID());
        values.put(COLUMN_AMOUNT, entity.getAmount());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public SalesComponents delete(SalesComponents entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<SalesComponents> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<SalesComponents> salesCom = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final SalesComponents personAddress = new SalesComponents.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .productNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID)))
                        .saleID(cursor.getString(cursor.getColumnIndex(COLUMN_SALE_ID)))
                        .amount(cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT)))
                        .build();
                salesCom.add(personAddress);
            } while (cursor.moveToNext());
        }
        return salesCom;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
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
}
