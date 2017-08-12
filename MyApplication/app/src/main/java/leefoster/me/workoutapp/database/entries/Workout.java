package leefoster.me.workoutapp.database.entries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import leefoster.me.workoutapp.BR;
import leefoster.me.workoutapp.database.WorkoutAppDbHelper;

/**
 * Created by leefoster on 2017-05-22.
 */

public class Workout {
    public static final Uri CONTENT_URI = WorkoutAppDbHelper.BASE_CONTENT_URI.buildUpon()
            .appendPath(COLS.TABLE_NAME)
            .build();

    private String name;
    private WorkoutDay workoutDay;
    private int drawableId;
    private boolean completed;
    private long id;
    private Exercise[] exercises;


    public Workout(Cursor c, Context context, WorkoutDay day) {
        this.id = c.getLong(c.getColumnIndex(COLS._ID));
        this.name = c.getString(c.getColumnIndex(COLS.COLUMN_NAME));
        this.drawableId = c.getInt(c.getColumnIndex(COLS.COLUMN_DRAWABLE_ID));
        this.workoutDay = day;
        int completedInt = c.getInt(c.getColumnIndex(COLS.COLUMN_WORKOUT_COMPLETED));
        this.completed = completedInt > 0;

        Cursor exerciseCursor = context.getContentResolver().query(Exercise.CONTENT_URI,
                null,
                Exercise.COLS.COLUMN_WORKOUT_ID + "= ?",
                new String[]{Long.toString(this.id)},
                Exercise.COLS.COLUMN_SEQUENCE_NUM);
        Exercise[] exercises = new Exercise[exerciseCursor.getCount()];
        for (int i = 0; i < exerciseCursor.getCount(); i++) {
            exerciseCursor.moveToNext();
            exercises[i] = new Exercise(exerciseCursor, this);
        }
        exerciseCursor.close();
        this.exercises = exercises;
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public WorkoutDay getWorkoutDay() {
        return workoutDay;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public long getId() {
        return id;
    }

    public Exercise[] getExercises() {
        return exercises;
    }

    // SETTERS
    public void setCompleted(boolean completed, Context context) {
        this.completed = completed;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLS.COLUMN_WORKOUT_COMPLETED, completed);
        updateInDB(contentValues, context);
    }

    private void updateInDB(ContentValues contentValues, Context context) {
        context.getContentResolver().update(
                Workout.CONTENT_URI.buildUpon().appendPath(Long.toString(this.id)).build(),
                contentValues,
                null,
                null);
    }

    public class COLS implements BaseColumns {
        public static final String TABLE_NAME = "workout";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DRAWABLE_ID = "drawable_id";
        public static final String COLUMN_WORKOUT_DAY_ID = "workout_day_id";
        public static final String COLUMN_WORKOUT_COMPLETED = "completed";
    }
}
