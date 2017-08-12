package leefoster.me.workoutapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import leefoster.me.workoutapp.utlis.WorkoutAppUtils;
import leefoster.me.workoutapp.database.entries.Exercise;
import leefoster.me.workoutapp.database.entries.Workout;
import leefoster.me.workoutapp.database.entries.WorkoutDay;

/**
 * Created by leefoster on 2017-05-20.
 */

public class WorkoutAppDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "workoutapp.db";
    public static final int DATABASE_VERSION = 1;

    public static final String CONTENT_AUTHORITY = "leefoster.me.workoutapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    private Context mContext;

    public WorkoutAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WORKOUT_DAY_TABLE =
                "CREATE TABLE " + WorkoutDay.COLS.TABLE_NAME + " (" +
                        WorkoutDay.COLS._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        WorkoutDay.COLS.COLUMN_DAY + " INTEGER NOT NULL, " +
                        WorkoutDay.COLS.COLUMN_COMPLETED + " INTEGER DEFAULT 0 " +
                        ");";


        final String SQL_CREATE_WORKOUT_TABLE =
                "CREATE TABLE " + Workout.COLS.TABLE_NAME + " (" +
                        Workout.COLS._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Workout.COLS.COLUMN_NAME + " STRING NOT NULL, " +
                        Workout.COLS.COLUMN_WORKOUT_DAY_ID + " INTEGER NOT NULL, " +
                        Workout.COLS.COLUMN_DRAWABLE_ID + " INTEGER NOT NULL, " +
                        Workout.COLS.COLUMN_WORKOUT_COMPLETED + " INTEGER DEFAULT 0 " +
                        ");";

        final String SQL_CREATE_EXERCISE_TABLE =
                "CREATE TABLE " + Exercise.COLS.TABLE_NAME + " (" +
                        Exercise.COLS._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Exercise.COLS.COLUMN_NAME + " STRING NOT NULL, " +
                        Exercise.COLS.COLUMN_COMPLETED + " INTEGER DEFAULT 0, " +
                        Exercise.COLS.COLUMN_REPS + " INTEGER NOT NULL, " +
                        Exercise.COLS.COLUMN_WEIGHT + " DOUBLE NOT NULL, " +
                        Exercise.COLS.COLUMN_WORKOUT_ID + " INTEGER NOT NULL, " +
                        Exercise.COLS.COLUMN_SEQUENCE_NUM + " INTEGER NOT NULL" +
                        ");";

        db.execSQL("DROP TABLE IF EXISTS " + Exercise.COLS.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutDay.COLS.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Workout.COLS.TABLE_NAME);
        db.execSQL(SQL_CREATE_WORKOUT_TABLE);
        db.execSQL(SQL_CREATE_WORKOUT_DAY_TABLE);
        db.execSQL(SQL_CREATE_EXERCISE_TABLE);

        WorkoutAppUtils.createWorkoutDays(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutDay.COLS.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Workout.COLS.TABLE_NAME);
        onCreate(db);
    }
}
