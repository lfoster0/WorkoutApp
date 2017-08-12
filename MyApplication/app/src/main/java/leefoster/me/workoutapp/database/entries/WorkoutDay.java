package leefoster.me.workoutapp.database.entries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import leefoster.me.workoutapp.database.WorkoutAppDbHelper;

/**
 * Created by leefoster on 2017-05-22.
 */

public class WorkoutDay {

    public static final Uri CONTENT_URI = WorkoutAppDbHelper.BASE_CONTENT_URI.buildUpon()
            .appendPath(COLS.TABLE_NAME)
            .build();

    private int day;
    private Workout[] workouts;
    private boolean completed;
    private long id;

    public WorkoutDay(Cursor c, Context context) {
        this.id = c.getLong(c.getColumnIndex(COLS._ID));
        this.day = c.getInt(c.getColumnIndex(COLS.COLUMN_DAY));
        int completedInt = c.getInt(c.getColumnIndex(COLS.COLUMN_COMPLETED));
        this.completed = completedInt > 0;
        Cursor workoutsCursor = context.getContentResolver().query(Workout.CONTENT_URI,
                null,
                Workout.COLS.COLUMN_WORKOUT_DAY_ID + " = ? ",
                new String[]{Long.toString(this.id)},
                null);
        Workout[] workouts = new Workout[workoutsCursor.getCount()];
        for (int i = 0; i < workoutsCursor.getCount(); i++) {
            workoutsCursor.moveToNext();
            workouts[i] = new Workout(workoutsCursor, context, this);
        }
        workoutsCursor.close();
        this.workouts = workouts;
    }

    // Getters
    public int getDay() {
        return day;
    }

    public Workout[] getWorkouts() {
        return workouts;
    }

    public boolean isCompleted() {
        return completed;
    }

    public long getId() {
        return id;
    }

    // Setters
    public void setCompleted(boolean isCompleted, Context context) {
        this.completed = isCompleted;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLS.COLUMN_COMPLETED, completed);
        updateInDB(contentValues, context);
    }

    private void updateInDB(ContentValues contentValues, Context context) {
        context.getContentResolver().update(
                WorkoutDay.CONTENT_URI.buildUpon().appendPath(Long.toString(this.id)).build(),
                contentValues,
                null,
                null);


    }
    public class COLS implements BaseColumns {
        public static final String TABLE_NAME = "workout_day";
        public static final String COLUMN_DAY = "day";
        public static final String COLUMN_COMPLETED = "completed";
    }
}
