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
import computerstore.com.computerstore.domain.sales.Sales;
import computerstore.com.computerstore.respository.sales.SalesRepository;


public class SalesRepositoryImpl extends SQLiteOpenHelper implements SalesRepository {
    public static final String TABLE_NAME = "sales";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMPLOYEE_ID = "empID";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TOTALSALES = "totalSales";
    public static final String COLUMN_DISCOUNT = "discount";
    private static final int DATABASE_VERSION = DBConstants.DATABASE_VERSION+15;

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EMPLOYEE_ID + " TEXT NOT NULL , "
            + COLUMN_DATE + " TEXT NOT NULL , "
            + COLUMN_TOTALSALES + " TEXT  NOT NULL , "
            + COLUMN_DISCOUNT + " TEXT  NOT NULL );";

    public SalesRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Sales findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_EMPLOYEE_ID,
                        COLUMN_DATE,
                        COLUMN_TOTALSALES,
                        COLUMN_DISCOUNT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Sales sales = new Sales.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .empID(cursor.getInt(cursor.getColumnIndex(COLUMN_EMPLOYEE_ID)))
                    .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .totalSales(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALSALES)))
                    .discount(cursor.getDouble(cursor.getColumnIndex(COLUMN_DISCOUNT)))
                    .build();

            return sales;
        } else {
            return null;
        }
    }

    @Override
    public Sales save(Sales entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_EMPLOYEE_ID, entity.getEmpID());
        values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_TOTALSALES, entity.getTotalSales());
        values.put(COLUMN_DISCOUNT, entity.getDiscount());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Sales insertedEntity = new Sales.Builder()
                .Sales(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Sales update(Sales entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_EMPLOYEE_ID, entity.getEmpID());
        values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_TOTALSALES, entity.getTotalSales());
        values.put(COLUMN_DISCOUNT, entity.getDiscount());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Sales delete(Sales entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Sales> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Sales> sales = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Sales personAddress = new Sales.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .empID(cursor.getInt(cursor.getColumnIndex(COLUMN_EMPLOYEE_ID)))
                        .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .totalSales(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALSALES)))
                        .discount(cursor.getDouble(cursor.getColumnIndex(COLUMN_DISCOUNT)))
                        .build();
                sales.add(personAddress);
            } while (cursor.moveToNext());
        }
        return sales;
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
