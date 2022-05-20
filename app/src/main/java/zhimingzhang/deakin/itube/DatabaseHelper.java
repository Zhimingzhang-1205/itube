package zhimingzhang.deakin.itube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "user_db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)";
        String CREATE_VIDEO_TABLE = "CREATE TABLE VIDEO(LINKID INTEGER PRIMARY KEY AUTOINCREMENT, VIDEO TEXT , USERID INTEGER, FOREIGN KEY (USERID) REFERENCES USERS(USERID))";
        sqLiteDatabase.execSQL(CREATE_VIDEO_TABLE);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS USERS";
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", user.getUsername());
        contentValues.put("PASSWORD", user.getPassword());
        long row = db.insert("USERS", null, contentValues);
        db.close();
        return row;
    }

    public long insertVideo(Integer userID, String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("VIDEO", link);
        contentValues.put("USERID", userID);
        long row = db.insert("VIDEO", null, contentValues);
        db.close();
        return row;
    }

    public boolean fetchUser(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("USERS", new String[]{"userid"}, "USERNAME=? and PASSWORD=?", new String[]{username, password}, null,null,null);
        int numrows = cursor.getCount();

        if(numrows > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getUserID(String username)
    {
        Integer userID = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("USERS", new String[]{"userid"}, "USERNAME=?", new String[]{username}, null, null, null);

        cursor.moveToFirst();
        userID = cursor.getInt(0);

        return userID;
    }

    public ArrayList<String> getUserVideos(Integer userID)
    {
        ArrayList<String> videoList = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();

        String SELECT_ALL_VIDEOS_FROM_USERID = "SELECT * FROM VIDEO WHERE USERID=?";

        Cursor cursor = db.rawQuery(SELECT_ALL_VIDEOS_FROM_USERID, new String[]{userID.toString()});

        if(cursor.moveToFirst())
        {
            do
            {
                String item = cursor.getString(1);
                videoList.add(item);
            }while (cursor.moveToNext());
        }

        return videoList;

    }
}
