package vn.com.misa.CUKCUKLite.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Lớp khởi tạo và sao chép database
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class DBOpenHeplper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/vn.com.misa.cukcuklite/databases/";
    private static String DB_NAME = "cukcuk.sqlite";
    private static final int DATABASE_VERSION = 6;

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DBOpenHeplper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    /**
     * Hàm mở database
     */
    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    /**
     * Hàm mở database
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void deleteDataBase() {
        String myPath = DB_PATH + DB_NAME;
        SQLiteDatabase.deleteDatabase(new File(myPath));
    }

    /**
     * Hàm đóng database
     */
    @Override
    public synchronized void close() {
        if (myDataBase != null) myDataBase.close();
        super.close();

    }

    /**
     * Hàm kiểm tra database
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //database chua ton tai
        }

        if (checkDB != null) checkDB.close();
        return checkDB != null ? true : false;
    }

    /**
     * Hàm coppy database
     */
    private void copyDataBase() {
        try {
            InputStream myInput = myContext.getAssets().open(DB_NAME);          //mo db trong thu muc assets nhu mot input stream
            String outFileName = DB_PATH + DB_NAME;                             //duong dan den db se tao
            OutputStream myOutput = new FileOutputStream(outFileName);          //mo db giong nhu mot output stream
            byte[] buffer = new byte[1024];                                     //truyen du lieu tu inputfile sang outputfile
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            //Dong luon
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm tạo database
     */
    public void createDataBase() {
        try {
            boolean dbExist = checkDataBase(); //kiem tra db
            if (dbExist) {
                //khong lam gi ca, database da co roi
            } else {
                this.getReadableDatabase();
                copyDataBase(); //chep du lieu
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


