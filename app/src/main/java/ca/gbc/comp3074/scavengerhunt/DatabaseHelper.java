package ca.gbc.comp3074.scavengerhunt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes_db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Point.CREATE_TABLE);
        db.execSQL(TeamMember.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Point.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TeamMember.TABLE_NAME);

        onCreate(db);
    }

    public long insertItem(String name, String address, String task, String tags, double rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Point.COLUMN_NAME, name);
        values.put(Point.COLUMN_ADDRESS, address);
        values.put(Point.COLUMN_TASK, task);
        values.put(Point.COLUMN_TAGS, tags);
        values.put(Point.COLUMN_RATING, rating);
        long id = db.insert(Point.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public Point getItem(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                Point.TABLE_NAME,
                new String[]{
                        Point.COLUMN_ID,
                        Point.COLUMN_NAME,
                        Point.COLUMN_ADDRESS,
                        Point.COLUMN_TASK,
                        Point.COLUMN_TAGS,
                        Point.COLUMN_RATING
                },
                Point.COLUMN_ID+"=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        Point point = null;

        if(cursor != null && cursor.moveToFirst()){

            point = new Point(
                    cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(Point.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(Point.COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(Point.COLUMN_TASK)),
                    cursor.getString(cursor.getColumnIndex(Point.COLUMN_TAGS)),
                    cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_RATING))
            );

            cursor.close();
        }
        db.close();
        return point;
    }

    public List<Point> getAllItems(){
        List<Point> points = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+Point.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null && cursor.moveToFirst()){
            do{
                Point point = new Point(
                        cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_TASK)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_TAGS)),
                        cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_RATING))
                );

                points.add(point);
            }
            while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return points;
    }

    public int updateItem(Point point){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Point.COLUMN_NAME, point.getName());
        values.put(Point.COLUMN_ADDRESS, point.getAddress());
        values.put(Point.COLUMN_TASK, point.getTask());
        values.put(Point.COLUMN_TAGS, point.getTags());
        values.put(Point.COLUMN_RATING, point.getRating());
        return db.update(
            Point.TABLE_NAME,
            values,
            Point.COLUMN_ID+"=?",
            new String[]{String.valueOf(point.getId())}
        );
    }

    public int deleteItem(Point point){
        SQLiteDatabase db = this.getWritableDatabase();
        int code = db.delete(Point.TABLE_NAME,
                Point.COLUMN_ID+"=?",
                new String[]{String.valueOf(point.getId())}
        );
        db.close();
        return code;
    }

    public List<Point> getByName(String search){
        List<Point> points = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                Point.TABLE_NAME,
                new String[]{
                        Point.COLUMN_ID,
                        Point.COLUMN_NAME,
                        Point.COLUMN_ADDRESS,
                        Point.COLUMN_TASK,
                        Point.COLUMN_TAGS,
                        Point.COLUMN_RATING
                },
                Point.COLUMN_NAME+" LIKE ?",
                new String[]{"%"+search+"%"},
                null, null, null
        );

        if(cursor != null && cursor.moveToFirst()){
            do{
                Point point = new Point(
                        cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_TASK)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_TAGS)),
                        cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_RATING))
                );

                points.add(point);
            }
            while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return points;
    }

    public List<Point> getByTags(String search){
        List<Point> points = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                Point.TABLE_NAME,
                new String[]{
                        Point.COLUMN_ID,
                        Point.COLUMN_NAME,
                        Point.COLUMN_ADDRESS,
                        Point.COLUMN_TASK,
                        Point.COLUMN_TAGS,
                        Point.COLUMN_RATING
                },
                Point.COLUMN_TAGS+" LIKE ?",
                new String[]{"%"+search+"%"},
                null, null, null
        );

        if(cursor != null && cursor.moveToFirst()){
            do{
                Point point = new Point(
                        cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_TASK)),
                        cursor.getString(cursor.getColumnIndex(Point.COLUMN_TAGS)),
                        cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_RATING))
                );

                points.add(point);
            }
            while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return points;
    }

    ////----------------team methods-------------///
    public long insertItem(String name, String email, String phone, String sms){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TeamMember.COLUMN_NAME, name);
        values.put(TeamMember.COLUMN_EMAIL, email);
        values.put(TeamMember.COLUMN_PHONE, phone);
        values.put(TeamMember.COLUMN_SMS, sms);
        long id = db.insert(TeamMember.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public TeamMember getTeamMember(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TeamMember.TABLE_NAME,
                new String[]{
                        TeamMember.COLUMN_ID,
                        TeamMember.COLUMN_NAME,
                        TeamMember.COLUMN_EMAIL,
                        TeamMember.COLUMN_PHONE,
                        TeamMember.COLUMN_SMS
                },
                TeamMember.COLUMN_ID+"=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        TeamMember teammember = null;

        if(cursor != null && cursor.moveToFirst()){

            teammember = new TeamMember(
                    cursor.getInt(cursor.getColumnIndex(TeamMember.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(TeamMember.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(TeamMember.COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(TeamMember.COLUMN_PHONE)),
                    cursor.getString(cursor.getColumnIndex(TeamMember.COLUMN_SMS))
            );

            cursor.close();
        }
        db.close();
        return teammember;
    }

    public List<TeamMember> getAllTeamMembers(){
        List<TeamMember> teammembers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TeamMember.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null && cursor.moveToFirst()){
            do{
                TeamMember teammember = new TeamMember(
                        cursor.getInt(cursor.getColumnIndex(TeamMember.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(TeamMember.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(TeamMember.COLUMN_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(TeamMember.COLUMN_PHONE)),
                        cursor.getString(cursor.getColumnIndex(TeamMember.COLUMN_SMS))
                );

                teammembers.add(teammember);
            }
            while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return teammembers;
    }

    public int updateItem(TeamMember teammember){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TeamMember.COLUMN_NAME, teammember.getName());
        values.put(TeamMember.COLUMN_EMAIL, teammember.getEmail());
        values.put(TeamMember.COLUMN_PHONE, teammember.getPhone());
        values.put(TeamMember.COLUMN_SMS, teammember.getSms());
        return db.update(
                TeamMember.TABLE_NAME,
                values,
                TeamMember.COLUMN_ID+"=?",
                new String[]{String.valueOf(teammember.getId())}
        );
    }

    public int deleteItem(TeamMember teammember){
        SQLiteDatabase db = this.getWritableDatabase();
        int code = db.delete(TeamMember.TABLE_NAME,
                TeamMember.COLUMN_ID+"=?",
                new String[]{String.valueOf(teammember.getId())}
        );
        db.close();
        return code;
    }


}
