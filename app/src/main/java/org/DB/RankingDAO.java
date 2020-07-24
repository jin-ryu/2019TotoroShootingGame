package org.DB;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.framework.AppManager;

import java.util.ArrayList;
import java.util.List;

public class RankingDAO extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static RankingDAO s_instance = null;

    public static RankingDAO getInstance(Context context){
        if(s_instance == null) {
            s_instance = new RankingDAO(context.getApplicationContext(),"Rank.db",null,1);
        }
        return s_instance;
    }

    private RankingDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table rank (_id integer primary key autoincrement, point integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db = this.getWritableDatabase();
        String sql="drop table if exists rank";
        db.execSQL(sql);
        onCreate(db);
    }

    public List<String> RankData() {

        db = this.getWritableDatabase();
        List<String> dataResultList = new ArrayList<String>();
        String sql = "select * from rank ORDER BY point DESC;";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            do{
                String result = results.getInt(0)  + "번째 여행 \n 점수는 " + results.getInt(1) + "점";
                dataResultList.add(result);
            }while(results.moveToNext());
        }

        db.close();

        return dataResultList;
    }

    public void insert(int point)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("point", point);
        db.insert("rank", null, contentValues);
        db.close();

    }
}
