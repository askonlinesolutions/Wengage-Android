package com.askonlinesolutions.wengage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.askonlinesolutions.wengage.Model.CategoryList;
import com.askonlinesolutions.wengage.Model.EventCategoryBean;
import com.askonlinesolutions.wengage.Model.EventCategoryModal;
import com.askonlinesolutions.wengage.Model.VenueCategoryBean;
import com.askonlinesolutions.wengage.Model.VenueSubCategoryListBean;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper dbInstance;

    public static DatabaseHelper getInstance(Context context) {
        if (dbInstance == null)
            return new DatabaseHelper(context, DATABASE_NAME, VERSION);
        return dbInstance;
    }


    private static final String DATABASE_NAME = "wengage_db";
    private static final int VERSION = 8;
    private String MAIN_CATEGORY_TABLE = "main_category";
    private String VENUE_CATEGORY_TABLE = "venue_category";
    private String EVENT_CATEGORY_TABLE = "event_category";
    private String VENUE_SUB_CATEGORY_TABLE = "event_sub_category";
    private String EVENT_SUB_CATEGORY_TABLE = "event_sub_category";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMainCategoryTable = "CREATE TABLE " + MAIN_CATEGORY_TABLE + "(id TEXT, name TEXT,image TEXT, PRIMARY KEY(id,name))";
        String createVenueCategoryTable = "CREATE TABLE " + VENUE_CATEGORY_TABLE + "(id TEXT, name TEXT,status TEXT,categoryId TEXT, PRIMARY KEY(id,name))";
        String createEventCategoryTable = "CREATE TABLE " + EVENT_CATEGORY_TABLE + "(id TEXT, name TEXT,status TEXT,categoryId TEXT,PRIMARY KEY(id,name))";
        String createEventSubCategoryTable = "CREATE TABLE " + EVENT_SUB_CATEGORY_TABLE + "(id TEXT, name TEXT,status TEXT,categoryId TEXT,PRIMARY KEY(id,name))";
        String createVenueSubCategoryTable = "CREATE TABLE " + VENUE_SUB_CATEGORY_TABLE + "(id TEXT, name TEXT,status TEXT,categoryId TEXT,PRIMARY KEY(id,name))";
        db.execSQL(createMainCategoryTable);
        db.execSQL(createVenueCategoryTable);
        db.execSQL(createEventCategoryTable);
//        db.execSQL(createEventSubCategoryTable);
//        db.execSQL(createVenueSubCategoryTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MAIN_CATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VENUE_CATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_CATEGORY_TABLE);
    }


    public void insertVenueMainCategory(List<VenueCategoryBean> list) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (VenueCategoryBean response : list) {
            values.put("id", response.getCategoryId());
            values.put("name", response.getCategoryName());
            values.put("image", response.getIcon());
            database.insert(MAIN_CATEGORY_TABLE, null, values);
        }
    }

    public List<VenueCategoryBean> getVenueMainCategory() {
        List<VenueCategoryBean> categoryList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + MAIN_CATEGORY_TABLE;
        String[] columns = {"id", "name", "image"};
        Cursor cursor = database.query(MAIN_CATEGORY_TABLE, columns, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                categoryList.add(new VenueCategoryBean(id, name, image));
            } while (cursor.moveToNext());
        }
        return categoryList;
    }

    public void getVenueMainCategory(List<VenueCategoryBean> list) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (VenueCategoryBean response : list) {
            values.put("id", response.getCategoryId());
            values.put("name", response.getCategoryName());
            values.put("image", response.getIcon());
            database.insert(MAIN_CATEGORY_TABLE, null, values);
        }
    }

    public void insertVenueCategory(List<VenueCategoryBean.SubCategoryBeanX> list) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (VenueCategoryBean.SubCategoryBeanX response : list) {
            values.put("id", response.getSubCategoryId());
            values.put("name", response.getSubCategoryName());
            values.put("image", response.getIcon());
            values.put("select", response.getSelected());
            database.insert(VENUE_SUB_CATEGORY_TABLE, null, values);
        }
    }

    public void insertEventCategory(List<EventCategoryBean.SubCategoryBean> list) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (EventCategoryBean.SubCategoryBean response : list) {
            values.put("id", response.getSubCategoryId());
            values.put("name", response.getSubCategoryName());
            values.put("image", response.getIcon());
            values.put("select", response.getSelected());
            database.insert(EVENT_SUB_CATEGORY_TABLE, null, values);
        }
    }

    public void insertCategory(List<CategoryList> list) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (CategoryList response : list) {
            values.put("id", response.getCategoryId());
            values.put("name", response.getCategoryName());
            values.put("image", response.getIcon());
            database.insert(MAIN_CATEGORY_TABLE, null, values);
        }
    }

    public List<CategoryList> getCategoryList() {
        List<CategoryList> categoryList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + MAIN_CATEGORY_TABLE;
        String[] columns = {"id", "name", "image"};
        Cursor cursor = database.query(MAIN_CATEGORY_TABLE, columns, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                categoryList.add(new CategoryList(id, name, image));
            } while (cursor.moveToNext());
        }
        return categoryList;
    }

    public void insertVenue(List<VenueSubCategoryListBean> venueModal, int categoryId) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (VenueSubCategoryListBean venue : venueModal) {
            values.put("id", venue.getSubCategoryId());
            values.put("name", venue.getSubCategoryName());
            values.put("status", venue.getIsSelected());
            values.put("categoryId", categoryId);
            database.insert(VENUE_CATEGORY_TABLE, null, values);
        }

    }


    public List<VenueSubCategoryListBean> getVenueList(String categoryIds) {
        List<VenueSubCategoryListBean> venueList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + VENUE_CATEGORY_TABLE;
        String[] columns = {"id", "name", "status", "categoryId"};
        Cursor cursor = database.query(VENUE_CATEGORY_TABLE, columns, "categoryId=?",
                new String[]{categoryIds}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int status = Integer.parseInt(cursor.getString(cursor.getColumnIndex("status")));
                int categoryId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("categoryId")));
                venueList.add(new VenueSubCategoryListBean(id, name, status, categoryId));
            } while (cursor.moveToNext());
        }
        return venueList;
    }

    public void insetEvent(List<EventCategoryModal> eventModal, int categoryId) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (EventCategoryModal event : eventModal) {
            values.put("id", event.getSubCategoryId());
            values.put("name", event.getSubCategoryName());
            values.put("status", event.getIsSelected());
            values.put("categoryId", categoryId);
            database.insert(EVENT_CATEGORY_TABLE, null, values);
        }

//        return
    }

    public List<EventCategoryModal> getEventList(String categoryIds) {
        List<EventCategoryModal> eventList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + EVENT_CATEGORY_TABLE;
        String[] columns = {"id", "name", "status", "categoryId"};
        Cursor cursor = database.query(EVENT_CATEGORY_TABLE, columns, "categoryId=?",
                new String[]{categoryIds}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int status = Integer.parseInt(cursor.getString(cursor.getColumnIndex("status")));
                int categoryId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("categoryId")));
                eventList.add(new EventCategoryModal(id, name, status, categoryId));
            } while (cursor.moveToNext());
        }
        return eventList;
    }


    public void updateEvent(int status, int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        String query = "UPDATE " + EVENT_CATEGORY_TABLE + " SET status = '" + status + "' WHERE id = '" + id + "'";
        database.execSQL(query);
    }

    public void updateVenues(int status, int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        String query = "UPDATE " + VENUE_CATEGORY_TABLE + " SET status = '" + status + "' WHERE id = '" + id + "'";
        database.execSQL(query);
//        database.update(VENUE_CATEGORY_TABLE, values, "id=" + "= ?", new String[] {String.valueOf(id)});

    }

}
