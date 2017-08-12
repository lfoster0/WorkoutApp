package leefoster.me.workoutapp.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import leefoster.me.workoutapp.database.entries.Exercise;
import leefoster.me.workoutapp.database.entries.Workout;
import leefoster.me.workoutapp.database.entries.WorkoutDay;

/**
 * Created by leefoster on 2017-05-20.
 */

public class WorkoutAppProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final int WORKOUT_DAYS   = 100;
    public static final int WORKOUT_DAY_WITH_ID = 101;
    public static final int WORKOUTS = 200;
    public static final int WORKOUT_WITH_ID = 201;
    public static final int EXERCISES = 300;
    public static final int EXERCISES_WITH_ID = 301;

    private WorkoutAppDbHelper mDbHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = WorkoutAppDbHelper.CONTENT_AUTHORITY;

        // workout day
        matcher.addURI(authority, WorkoutDay.COLS.TABLE_NAME, WORKOUT_DAYS);
        matcher.addURI(authority, WorkoutDay.COLS.TABLE_NAME + "/#", WORKOUT_DAY_WITH_ID);
        // workout
        matcher.addURI(authority, Workout.COLS.TABLE_NAME, WORKOUTS);
        matcher.addURI(authority, Workout.COLS.TABLE_NAME + "/#", WORKOUT_WITH_ID);
        // exercise
        matcher.addURI(authority, Exercise.COLS.TABLE_NAME, EXERCISES);
        matcher.addURI(authority, Exercise.COLS.TABLE_NAME + "/#", EXERCISES_WITH_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new WorkoutAppDbHelper(getContext());
        return true;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor c;
        switch (match) {
            case WORKOUT_DAYS:
                c = db.query(WorkoutDay.COLS.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case WORKOUT_DAY_WITH_ID:
                c = db.query(WorkoutDay.COLS.TABLE_NAME,
                        projection,
                        WorkoutDay.COLS._ID + "= ?",
                        new String[]{uri.getLastPathSegment()},
                        null,
                        null,
                        sortOrder);
                break;
            case WORKOUTS:
                c = db.query(Workout.COLS.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case EXERCISES:
                c = db.query(Exercise.COLS.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case EXERCISES_WITH_ID:
                c = db.query(Exercise.COLS.TABLE_NAME,
                        projection,
                        Exercise.COLS._ID + "= ?",
                        new String[]{uri.getLastPathSegment()},
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri returnUri;
        long id = -1;
        switch (match) {
            case WORKOUT_DAYS:
                id = db.insert(WorkoutDay.COLS.TABLE_NAME, null, values);
                returnUri = ContentUris.withAppendedId(WorkoutDay.CONTENT_URI, id);
                break;
            case WORKOUTS:
                id = db.insert(Workout.COLS.TABLE_NAME, null, values);
                returnUri = ContentUris.withAppendedId(Workout.CONTENT_URI, id);
                break;
            case EXERCISES:
                id = db.insert(Exercise.COLS.TABLE_NAME, null, values);
                returnUri = ContentUris.withAppendedId(Exercise.CONTENT_URI, id);
                break;
            default:
                throw new UnsupportedOperationException("unknown uri: " + uri);
        }
        if (id < 0) {
            throw new android.database.SQLException("failed to insert row into " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        int numEffected = 0;
        switch (match) {
            case EXERCISES_WITH_ID:
                numEffected = db.update(Exercise.COLS.TABLE_NAME, values, Exercise.COLS._ID + "= ?", new String[]{uri.getLastPathSegment()});
                break;
            case WORKOUT_DAY_WITH_ID:
                numEffected = db.update(WorkoutDay.COLS.TABLE_NAME, values, WorkoutDay.COLS._ID + "= ?", new String[]{uri.getLastPathSegment()});
                break;
            case WORKOUT_WITH_ID:
                numEffected = db.update(Workout.COLS.TABLE_NAME, values, Workout.COLS._ID + "= ?", new String[]{uri.getLastPathSegment()});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        if (numEffected == 0) {
            throw new android.database.SQLException("failed to update " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return numEffected;
    }
}
