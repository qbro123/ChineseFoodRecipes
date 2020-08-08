package acrux.com.chinesefoodrecipes.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import acrux.com.chinesefoodrecipes.items.Food;

/**
 * Created by Mr_Hai on 1/11/2016.
 */
public class DatabaseManager {
    private static final String DB_PATH =
            Environment.getDataDirectory().getPath() + "/data/acrux.com.chinesefoodrecipes/databases";

    private static final String DB_NAME = "ChineseFood.sqlite";
    private static final String TAG = "DBManager";
    private Context mContext;

    private SQLiteDatabase sqlDB;

    private ArrayList<Food> arr=new ArrayList<>();
    private String REQUEST;

    public DatabaseManager(Context mContext) {
        this.mContext = mContext;
        copyDB();

    }

    public DatabaseManager(Context context, String REQUEST) {
        mContext=context;
        this.REQUEST=REQUEST;
        copyDB();

    }

    private void copyDB() {

        try {
            new File(DB_PATH).mkdir();

            File file = new File(DB_PATH + "/" + DB_NAME);
            if (file.exists())
                return;

            file.createNewFile();
            InputStream input=mContext.getAssets().open(DB_NAME);
            FileOutputStream output=new FileOutputStream(file);

            int len;
            byte buff[]=new byte[1024];
            len=input.read(buff);
            while(len>0){
                output.write(buff,0,len);
                len=input.read(buff);
            }
            input.close();
            output.close();
            Log.i(TAG, "DB is copied to internal path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDB(){
        if(sqlDB==null||!sqlDB.isOpen())
            sqlDB= SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDB(){
        if(sqlDB!=null && sqlDB.isOpen()) {
            sqlDB.close();
            sqlDB=null;
        }
    }
    public ArrayList<Food> getData(){
        arr.clear();
        //Mo DB
        openDB();
        Cursor cursor=sqlDB.rawQuery(REQUEST, null);
        if(cursor==null)
            return null;

        Food food;

        int indexID, indexName, indexIngredients, indexSauce , indexMethod , indexNutrition , indexImage;
        indexID=cursor.getColumnIndex("ID");
        indexName=cursor.getColumnIndex("Name");
        indexIngredients = cursor.getColumnIndex("Ingredients");
        indexSauce = cursor.getColumnIndex("Sauce");
        indexMethod = cursor.getColumnIndex("Method");
        indexNutrition = cursor.getColumnIndex("Nutrition");
        indexImage=cursor.getColumnIndex("Image");

        int id ;
        String name, ingredients,sauce , method , nutrition ;
        byte [] image;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            id=cursor.getInt(indexID);
            name = cursor.getString(indexName);
            ingredients = cursor.getString(indexIngredients);
            sauce= cursor.getString(indexSauce);
            method= cursor.getString(indexMethod);
            nutrition= cursor.getString(indexNutrition);
            image = cursor.getBlob(indexImage);
            food = new Food(id , name , ingredients, sauce, method , nutrition, image);
            arr.add(food);
            cursor.moveToNext();
        }
        return arr;
    }

}
