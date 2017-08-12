package leefoster.me.workoutapp.database.entries;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.provider.BaseColumns;

import leefoster.me.workoutapp.BR;
import leefoster.me.workoutapp.database.WorkoutAppDbHelper;

/**
 * Created by leefoster on 2017-06-04.
 */

public class Exercise extends BaseObservable {

    public static final Uri CONTENT_URI = WorkoutAppDbHelper.BASE_CONTENT_URI.buildUpon()
            .appendPath(COLS.TABLE_NAME)
            .build();

    private String name;
    private int reps;
    private double weight;
    private boolean completed;
    private long id;
    private int sequenceNumber;
    private Workout workout;

    public Exercise(Cursor c, Workout workout) {
        this.id = c.getLong(c.getColumnIndex(COLS._ID));
        this.name = c.getString(c.getColumnIndex(COLS.COLUMN_NAME));
        this.reps = c.getInt(c.getColumnIndex(COLS.COLUMN_REPS));
        this.weight = c.getDouble(c.getColumnIndex(COLS.COLUMN_WEIGHT));
        int completedInt = c.getInt(c.getColumnIndex(COLS.COLUMN_COMPLETED));
        this.completed = completedInt > 0;
        this.workout = workout;
        this.sequenceNumber = c.getInt(c.getColumnIndex(COLS.COLUMN_SEQUENCE_NUM));
    }

    // Getters
    public String getName() {
        return name;
    }

    @Bindable
    public int getReps() {
        return reps;
    }

    @Bindable
    public double getWeight() {
        return weight;
    }

    @Bindable
    public boolean isCompleted() {
        return completed;
    }

    public long getId() {
        return id;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public Workout getWorkout() {
        return workout;
    }

    // Setters
    public void setReps(int reps, Context context) {
        this.reps = reps;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLS.COLUMN_REPS, reps);
        updateInDB(contentValues, context);
        notifyPropertyChanged(BR.reps);
    }

    public void setWeight(double weight, Context context) {
        this.weight = weight;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLS.COLUMN_WEIGHT, weight);
        updateInDB(contentValues, context);
        notifyPropertyChanged(BR.weight);
    }

    public void setCompleted(boolean completed, Context context) {
        this.completed = completed;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLS.COLUMN_COMPLETED, completed);
        updateInDB(contentValues, context);
        notifyPropertyChanged(BR.completed);
    }

    private void updateInDB(ContentValues contentValues, Context context) {
        context.getContentResolver().update(
                Exercise.CONTENT_URI.buildUpon().appendPath(Long.toString(this.id)).build(),
                contentValues,
                null,
                null);


    }
    public class COLS implements BaseColumns {
        public static final String TABLE_NAME = "exercise";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_REPS = "reps";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_COMPLETED = "completed";
        public static final String COLUMN_WORKOUT_ID = "workout_id";
        public static final String COLUMN_SEQUENCE_NUM = "sequence_num";
    }
}
