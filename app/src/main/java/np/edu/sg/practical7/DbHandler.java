package np.edu.sg.practical7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    private static final String TAG = "MyDBHandler";
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountDB.db";
    public static final String ACCOUNTS = "Accounts";
    public static final String COLUMN_USERNAME = "UserName";
    public static final String COLUMN_PASSWORD = "Password";

    public DbHandler(Context context,
                     String name,
                     SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS +
                "(" + COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT" + ")" ;

        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        onCreate(db);
    }

    public void addUser(UserData userData){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,userData.getMyUserName());
        values.put(COLUMN_PASSWORD, userData.getMyPassword());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(ACCOUNTS, null,values);
        db.close();
    }

    public UserData findUser(String username){
        String query = "SELECT * FROM " + ACCOUNTS + " WHERE " + COLUMN_USERNAME
                + "= \"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        UserData querydata = new UserData();

        if(cursor.moveToFirst()){
            querydata.setMyUserName(cursor.getString(0));
            querydata.setMyPassword(cursor.getString(1));
            cursor.close();
        }
        else{
            querydata =  null;

        }
        db.close();
        return querydata;
    }
}

