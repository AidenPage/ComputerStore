package computerstore.com.computerstore.respository.employees.Impl;

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
import computerstore.com.computerstore.domain.employees.Employees;
import computerstore.com.computerstore.respository.employees.EmployeesRepository;


public class EmployeesRepositoryImpl extends SQLiteOpenHelper implements EmployeesRepository {
    public static final String TABLE_NAME = "emp";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_JOBDESCRIPTION = "stock";
    private static final int DATABASE_VERSION = DBConstants.DATABASE_VERSION+13;

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_SURNAME + " TEXT NOT NULL , "
            + COLUMN_JOBDESCRIPTION + " TEXT  NOT NULL );";


    public EmployeesRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Employees findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_SURNAME,
                        COLUMN_JOBDESCRIPTION},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Employees emp = new Employees.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .empName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .empSurname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .empJob(cursor.getString(cursor.getColumnIndex(COLUMN_JOBDESCRIPTION)))
                    .build();

            return emp;
        } else {
            return null;
        }
    }

    @Override
    public Employees save(Employees entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getEmpName());
        values.put(COLUMN_SURNAME, entity.getEmpSurname());
        values.put(COLUMN_JOBDESCRIPTION, entity.getEmpJob());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Employees insertedEntity = new Employees.Builder()
                .Employees(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Employees update(Employees entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getEmpName());
        values.put(COLUMN_SURNAME, entity.getEmpSurname());
        values.put(COLUMN_JOBDESCRIPTION, entity.getEmpJob());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Employees delete(Employees entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Employees> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Employees> emp = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Employees personAddress = new Employees.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .empName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .empSurname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .empJob(cursor.getString(cursor.getColumnIndex(COLUMN_JOBDESCRIPTION)))
                        .build();
                emp.add(personAddress);
            } while (cursor.moveToNext());
        }
        return emp;
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
