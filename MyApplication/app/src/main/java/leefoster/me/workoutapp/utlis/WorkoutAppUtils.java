package leefoster.me.workoutapp.utlis;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.preference.PreferenceManager;

import leefoster.me.workoutapp.App;
import leefoster.me.workoutapp.R;
import leefoster.me.workoutapp.database.entries.Exercise;
import leefoster.me.workoutapp.database.entries.Workout;
import leefoster.me.workoutapp.database.entries.WorkoutDay;

/**
 * Created by leefoster on 2017-05-20.
 */

public class WorkoutAppUtils {
    public static int getCurrentWorkoutDay() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        String currentWorkoutDayKey = App.getContext().getString(R.string.pref_current_workout_day);
        int currentWorkoutDay = preferences.getInt(currentWorkoutDayKey,1);
        return currentWorkoutDay;
    }

    public static void setCurrentWorkoutDay(int currentWorkoutDay) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        String currentWorkoutDayKey = App.getContext().getString(R.string.pref_current_workout_day);
        preferences.edit().putInt(currentWorkoutDayKey, currentWorkoutDay).apply();
    }

    public static void createWorkoutDays(SQLiteDatabase db) {
        // PHASE 1
        // Weeks 1-3
        int day = 1;
        long workoutDayId = 0;
        long workoutId = 0;
        for (int i = 0; i < 3; i++) {
            // chest and back, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.chest_and_back, workoutDayId, R.drawable.chest_and_back);
            insertChestAndBackExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // plyo
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.plyo, workoutDayId, R.drawable.plyo);
            insertPlyoExercises(db, workoutId);
            day++;

            // shoulders & arms, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.shoulders_and_arms, workoutDayId, R.drawable.shoulders_and_arms);
            insertShoulderAndArmExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // yoga x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
            insertYogaExercises(db, workoutId);
            day++;

            // legs & back, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.legs_and_back, workoutDayId, R.drawable.legs_and_back);
            insertLegsAndBackExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // kenpo x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.kenpo, workoutDayId, R.drawable.kenpo);
            insertKenpoXExercises(db, workoutId);
            day++;

            // x stretch
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
            insertXStretchExercises(db, workoutId);
            day++;
        }
        // Week 4
        // yoga x
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
        insertYogaExercises(db, workoutId);
        day++;

        // core synergistics
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.core_synergistics, workoutDayId, R.drawable.core_synergistics);
        insertCoreSynergisticExercises(db, workoutId);
        day++;

        // kenpo x
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.kenpo, workoutDayId, R.drawable.kenpo);
        insertKenpoXExercises(db, workoutId);
        day++;

        // x stretch
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
        insertXStretchExercises(db, workoutId);
        day++;

        // core synergistics
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.core_synergistics, workoutDayId, R.drawable.core_synergistics);
        insertCoreSynergisticExercises(db, workoutId);
        day++;

        // yoga x
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
        insertYogaExercises(db, workoutId);
        day++;

        // x stretch
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
        insertXStretchExercises(db, workoutId);
        day++;

        // PHASE 2
        // week 5-7
        for (int i = 0; i < 3; i++) {
            // chest shoulders triceps, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.chest_shoulders_triceps, workoutDayId, R.drawable.chest_shoulder_tri);
            insertChestShouldersTricepExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // plyo
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.plyo, workoutDayId, R.drawable.plyo);
            insertPlyoExercises(db, workoutId);
            day++;

            // back & biceps, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.back_and_biceps, workoutDayId, R.drawable.back_and_biceps);
            insertBackAndBicepExercies(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // yoga x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
            insertYogaExercises(db, workoutId);
            day++;

            // legs & back, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.legs_and_back, workoutDayId, R.drawable.legs_and_back);
            insertLegsAndBackExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // kenpo x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.kenpo, workoutDayId, R.drawable.kenpo);
            insertKenpoXExercises(db, workoutId);
            day++;

            // x stretch
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
            insertXStretchExercises(db, workoutId);
            day++;
        }

        // week 8
        // yoga x
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
        insertYogaExercises(db, workoutId);
        day++;

        // core synergistics
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.core_synergistics, workoutDayId, R.drawable.core_synergistics);
        insertCoreSynergisticExercises(db, workoutId);
        day++;

        // kenpo x
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.kenpo, workoutDayId, R.drawable.kenpo);
        insertKenpoXExercises(db, workoutId);
        day++;

        // x stretch
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
        insertXStretchExercises(db, workoutId);
        day++;

        // core synergistics
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.core_synergistics, workoutDayId, R.drawable.core_synergistics);
        insertCoreSynergisticExercises(db, workoutId);
        day++;

        // yoga x
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
        insertYogaExercises(db, workoutId);
        day++;

        // x stretch
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
        insertXStretchExercises(db, workoutId);
        day++;

        // PHASE 3
        // week 9 - 12
        for (int i = 0; i < 2; i++) {
            // chest & back, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.chest_and_back, workoutDayId, R.drawable.chest_and_back);
            insertChestAndBackExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // plyo
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.plyo, workoutDayId, R.drawable.plyo);
            insertPlyoExercises(db, workoutId);
            day++;

            // shoulders & arms, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.shoulders_and_arms, workoutDayId, R.drawable.shoulders_and_arms);
            insertShoulderAndArmExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // yoga x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
            insertYogaExercises(db, workoutId);
            day++;

            // legs & back, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.legs_and_back, workoutDayId, R.drawable.legs_and_back);
            insertLegsAndBackExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // kenpo x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.kenpo, workoutDayId, R.drawable.kenpo);
            insertKenpoXExercises(db, workoutId);
            day++;

            // x stretch
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
            insertXStretchExercises(db, workoutId);
            day++;

            // chest shoulders triceps, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.chest_shoulders_triceps, workoutDayId, R.drawable.chest_shoulder_tri);
            insertChestShouldersTricepExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // plyo
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.plyo, workoutDayId, R.drawable.plyo);
            insertPlyoExercises(db, workoutId);
            day++;

            // back & biceps, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.back_and_biceps, workoutDayId, R.drawable.back_and_biceps);
            insertBackAndBicepExercies(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // yoga x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
            insertYogaExercises(db, workoutId);
            day++;

            // legs & back, ab ripper x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.legs_and_back, workoutDayId, R.drawable.legs_and_back);
            insertLegsAndBackExercises(db, workoutId);
            workoutId = insertNewWorkout(db, R.string.abs, workoutDayId, -1);
            insertAbRipperXExercises(db, workoutId);
            day++;

            // kenpo x
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.kenpo, workoutDayId, R.drawable.kenpo);
            insertKenpoXExercises(db, workoutId);
            day++;

            // x stretch
            workoutDayId = insertNewWorkoutDay(db, day, false);
            workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
            insertXStretchExercises(db, workoutId);
            day++;
        }

        // week 13
        // yoga x
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
        insertYogaExercises(db, workoutId);
        day++;

        // core synergistics
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.core_synergistics, workoutDayId, R.drawable.core_synergistics);
        insertCoreSynergisticExercises(db, workoutId);
        day++;

        // kenpo x
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.kenpo, workoutDayId, R.drawable.kenpo);
        insertKenpoXExercises(db, workoutId);
        day++;

        // x stretch
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
        insertXStretchExercises(db, workoutId);
        day++;

        // core synergistics
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.core_synergistics, workoutDayId, R.drawable.core_synergistics);
        insertCoreSynergisticExercises(db, workoutId);
        day++;

        // yoga x
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.yoga, workoutDayId, R.drawable.yoga);
        insertYogaExercises(db, workoutId);
        day++;

        // x stretch
        workoutDayId = insertNewWorkoutDay(db, day, false);
        workoutId = insertNewWorkout(db, R.string.stretch, workoutDayId, R.drawable.stretch);
        insertXStretchExercises(db, workoutId);
        day++;
    }



    private static long insertNewWorkoutDay(SQLiteDatabase db, int day, boolean completed) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WorkoutDay.COLS.COLUMN_DAY, day);
        contentValues.put(WorkoutDay.COLS.COLUMN_COMPLETED, completed);
        return db.insert(WorkoutDay.COLS.TABLE_NAME,null, contentValues);
    }

    private static long insertNewWorkout(SQLiteDatabase db, int workoutNameId, long workoutDayId, int drawableId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Workout.COLS.COLUMN_NAME, App.getContext().getString(workoutNameId));
        contentValues.put(Workout.COLS.COLUMN_WORKOUT_DAY_ID, workoutDayId);
        contentValues.put(Workout.COLS.COLUMN_DRAWABLE_ID, drawableId);
        return db.insert(Workout.COLS.TABLE_NAME, null, contentValues);
    }

    private static long insertExercise(SQLiteDatabase db, long workoutId, int nameId, int reps, int weight, boolean completed, int sequenceNum) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Exercise.COLS.COLUMN_WORKOUT_ID, workoutId);
        contentValues.put(Exercise.COLS.COLUMN_NAME, App.getContext().getString(nameId));
        contentValues.put(Exercise.COLS.COLUMN_REPS, reps);
        contentValues.put(Exercise.COLS.COLUMN_WEIGHT, weight);
        contentValues.put(Exercise.COLS.COLUMN_COMPLETED, completed);
        contentValues.put(Exercise.COLS.COLUMN_SEQUENCE_NUM, sequenceNum);
        return db.insert(Exercise.COLS.TABLE_NAME, null, contentValues);
    }

    // EXERCISES
    private static void insertChestAndBackExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.standard_pushups, 0, -1, false, 1);
        insertExercise(db, workoutId, R.string.wide_front_pullups, 0, -1, false, 2);
        insertExercise(db, workoutId, R.string.military_pushups, 0, -1, false, 3);
        insertExercise(db, workoutId, R.string.reverse_grip_chinups, 0, -1, false, 4);

        insertExercise(db, workoutId, R.string.wide_fly_pushups, 0, -1, false, 5);
        insertExercise(db, workoutId, R.string.close_grip_overhand_pullups, 0, -1, false, 6);
        insertExercise(db, workoutId, R.string.decline_pushups, 0, -1, false, 7);
        insertExercise(db, workoutId, R.string.heavy_pants, 0, 0, false, 8);

        insertExercise(db, workoutId, R.string.diamond_pushups, 0, -1, false, 9);
        insertExercise(db, workoutId, R.string.lawnmowers, 0, 0, false, 10);
        insertExercise(db, workoutId, R.string.divebomber_pushups, 0, -1, false, 11);
        insertExercise(db, workoutId, R.string.back_flys, 0, 0, false, 12);

        insertExercise(db, workoutId, R.string.wide_front_pullups, 0, -1, false, 13);
        insertExercise(db, workoutId, R.string.standard_pushups, 0, -1, false, 14);
        insertExercise(db, workoutId, R.string.reverse_grip_chinups, 0, -1, false, 15);
        insertExercise(db, workoutId, R.string.military_pushups, 0, -1, false, 16);

        insertExercise(db, workoutId, R.string.close_grip_overhand_pullups, 0, -1, false, 17);
        insertExercise(db, workoutId, R.string.wide_fly_pushups, 0, -1, false, 18);
        insertExercise(db, workoutId, R.string.heavy_pants, 0, 0, false, 19);
        insertExercise(db, workoutId, R.string.decline_pushups, 0, -1, false, 20);

        insertExercise(db, workoutId, R.string.lawnmowers, 0, 0, false, 21);
        insertExercise(db, workoutId, R.string.diamond_pushups, 0, -1, false, 22);
        insertExercise(db, workoutId, R.string.back_flys, 0, 0, false, 23);
        insertExercise(db, workoutId, R.string.divebomber_pushups, 0, -1, false, 24);
    }

    private static void insertAbRipperXExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.abs, -1, -1, false, 1);
    }

    private static void insertPlyoExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.plyo, -1, -1, false, 1);
    }

    private static void insertShoulderAndArmExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.alternating_shoulder_press, 0, 0, false, 1);
        insertExercise(db, workoutId, R.string.in_out_bicep_curl, 0, 0, false, 2);
        insertExercise(db, workoutId, R.string.two_arm_tricep_kickback, 0, 0, false, 3);
        insertExercise(db, workoutId, R.string.alternating_shoulder_press, 0, 0, false, 4);
        insertExercise(db, workoutId, R.string.in_out_bicep_curl, 0, 0, false, 5);
        insertExercise(db, workoutId, R.string.two_arm_tricep_kickback, 0, 0, false, 6);

        insertExercise(db, workoutId, R.string.deep_swimmer_press, 0, 0, false, 7);
        insertExercise(db, workoutId, R.string.full_sup_concentration_curls, 0, 0, false, 8);
        insertExercise(db, workoutId, R.string.chair_dips, 0, -1, false, 9);
        insertExercise(db, workoutId, R.string.deep_swimmer_press, 0, 0, false, 10);
        insertExercise(db, workoutId, R.string.full_sup_concentration_curls, 0, 0, false, 11);
        insertExercise(db, workoutId, R.string.chair_dips, 0, -1, false, 12);

        insertExercise(db, workoutId, R.string.upright_rows, 0, 0, false, 13);
        insertExercise(db, workoutId, R.string.static_arm_curls, 0, 0, false, 14);
        insertExercise(db, workoutId, R.string.flip_grip_kickbacks, 0, 0, false, 15);
        insertExercise(db, workoutId, R.string.upright_rows, 0, 0, false, 16);
        insertExercise(db, workoutId, R.string.static_arm_curls, 0, 0, false, 17);
        insertExercise(db, workoutId, R.string.flip_grip_kickbacks, 0, 0, false, 18);

        insertExercise(db, workoutId, R.string.seated_two_angle_shoulder_flys, 0, 0, false, 19);
        insertExercise(db, workoutId, R.string.crouching_cohen_curls, 0, 0, false, 20);
        insertExercise(db, workoutId, R.string.lying_down_tricep_extensions, 0, 0, false, 21);
        insertExercise(db, workoutId, R.string.seated_two_angle_shoulder_flys, 0, 0, false, 22);
        insertExercise(db, workoutId, R.string.crouching_cohen_curls, 0, 0, false, 23);
        insertExercise(db, workoutId, R.string.lying_down_tricep_extensions, 0, 0, false, 24);

        insertExercise(db, workoutId, R.string.in_and_out_shoulder_flys, 0, 0, false, 25);
        insertExercise(db, workoutId, R.string.congdon_curls, 0, 0, false, 26);
        insertExercise(db, workoutId, R.string.side_tri_rise, 0, 0, false, 27);
        insertExercise(db, workoutId, R.string.in_and_out_shoulder_flys, 0, 0, false, 28);
        insertExercise(db, workoutId, R.string.congdon_curls, 0, 0, false, 29);
        insertExercise(db, workoutId, R.string.side_tri_rise, 0, 0, false, 30);
    }

    private static void insertYogaExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.yoga, -1, -1, false, 1);
    }

    private static void insertKenpoXExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.kenpo, -1, -1, false, 1);
    }

    private static void insertXStretchExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.stretch, -1, -1, false, 1);
    }

    private static void insertLegsAndBackExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.balanced_lunges, 0, -1, false, 1);
        insertExercise(db, workoutId, R.string.calf_raise_squats, 0, 0, false, 2);
        insertExercise(db, workoutId, R.string.reverse_grip_chinups, 0, -1, false, 3);
        insertExercise(db, workoutId, R.string.super_skaters, 0, -1, false, 4);
        insertExercise(db, workoutId, R.string.wall_squats, 0, -1, false, 5);
        insertExercise(db, workoutId, R.string.wide_front_pullups, 0, -1, false, 6);
        insertExercise(db, workoutId, R.string.step_back_lunge, 0, 0 ,false, 7);
        insertExercise(db, workoutId, R.string.alternating_side_lunge, 0, 0, false, 8);
        insertExercise(db, workoutId, R.string.close_grip_overhand_pullups, 0, -1, false, 9);
        insertExercise(db, workoutId, R.string.single_leg_wall_squat, 0, -1, false, 10);
        insertExercise(db, workoutId, R.string.deadlife_squats, 0, -1, false, 11);
        insertExercise(db, workoutId, R.string.switch_grip_pull_up, 0, -1, false, 12);

        insertExercise(db, workoutId, R.string.three_way_lunge, 0, -1, false, 13);
        insertExercise(db, workoutId, R.string.sneaky_lunge, 0, -1, false, 14);
        insertExercise(db, workoutId, R.string.reverse_grip_chinups, 0, -1, false, 15);
        insertExercise(db, workoutId, R.string.chair_salutations, 0, -1, false, 16);
        insertExercise(db, workoutId, R.string.toe_roll_iso_lunge, 0, 0, false, 17);
        insertExercise(db, workoutId, R.string.wide_front_pullups, 0, -1, false, 18);
        insertExercise(db, workoutId, R.string.groucho_walk, 0, -1, false, 19);
        insertExercise(db, workoutId, R.string.calf_raises_toes_out, 0, 0, false, 20);
        insertExercise(db, workoutId, R.string.calf_raises_feet_parallel, 0, 0, false, 21);
        insertExercise(db, workoutId, R.string.calf_raises_toes_in, 0, 0, false, 22);
        insertExercise(db, workoutId, R.string.close_grip_overhand_pullups, 0, -1, false, 23);
        insertExercise(db, workoutId, R.string.eighty_twenty_siber_speed_squats, 0, -1, false, 24);
        insertExercise(db, workoutId, R.string.switch_grip_pull_up, 0, -1, false, 25);

    }

    private static void insertCoreSynergisticExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.stacked_foot_staggered_hands_push_ups, 0, -1, false, 1);
        insertExercise(db, workoutId, R.string.banana_rolls, 0, -1, false, 2);
        insertExercise(db, workoutId, R.string.leaning_crescent_lunges, 0, 0, false, 3);
        insertExercise(db, workoutId, R.string.squat_runs, 0, 0, false, 4);
        insertExercise(db, workoutId, R.string.sphinx_push_ups, 0, -1, false, 5);
        insertExercise(db, workoutId, R.string.bow_to_boat, 0, -1, false, 6);
        insertExercise(db, workoutId, R.string.low_lateral_skaters, 0, -1, false, 7);
        insertExercise(db, workoutId, R.string.lunge_and_reach, 0, 0, false, 8);

        insertExercise(db, workoutId, R.string.prison_cell_pushups, 0, -1, false, 9);
        insertExercise(db, workoutId, R.string.side_hip_raise, 0, -1, false, 10);
        insertExercise(db, workoutId, R.string.squat_x_press, 0, 0, false, 11);
        insertExercise(db, workoutId, R.string.plank_to_chaturanga_run, 0, -1, false, 12);
        insertExercise(db, workoutId, R.string.walking_push_ups, 0, -1, false, 13);
        insertExercise(db, workoutId, R.string.superman_banana, 0, 0, false, 14);
        insertExercise(db, workoutId, R.string.lunge_kickback_curl_press, 0, 0, false, 15);
        insertExercise(db, workoutId, R.string.towel_hoppers, 0, -1, false, 16);

        insertExercise(db, workoutId, R.string.reach_high_and_under_push_ups, 0, -1, false, 17);
        insertExercise(db, workoutId, R.string.steam_engine, 0, -1, false, 18);
        insertExercise(db, workoutId, R.string.dreya_rolls, 0, -1, false, 19);
        insertExercise(db, workoutId, R.string.plank_to_chaturanga_iso, 0, -1, false, 20);
        insertExercise(db, workoutId, R.string.halfback, 0, -1, false, 21);
        insertExercise(db, workoutId, R.string.table_dip_leg_raises, 0, -1, false, 22);
    }

    private static void insertChestShouldersTricepExercises(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.slow_motion_3_in_1_push_up, 0, -1, false, 1);
        insertExercise(db, workoutId, R.string.in_and_out_shoulder_flys, 0, 0, false, 2);
        insertExercise(db, workoutId, R.string.chair_dips, 0, -1, false, 3);
        insertExercise(db, workoutId, R.string.plange_push_ups, 0, -1, false, 4);
        insertExercise(db, workoutId, R.string.pike_presses, 0, -1, false, 5);
        insertExercise(db, workoutId, R.string.side_tri_rise, 0, -1, false, 6);
        insertExercise(db, workoutId, R.string.floor_flys, 0, -1, false, 7);
        insertExercise(db, workoutId, R.string.scarecrows, 0, 0, false, 8);
        insertExercise(db, workoutId, R.string.overhead_tricep_extensions, 0, 0, false, 9);
        insertExercise(db, workoutId, R.string.two_twitch_speed_push_ups, 0 , -1, false, 10);
        insertExercise(db, workoutId, R.string.y_presses, 0, 0, false, 11);
        insertExercise(db, workoutId, R.string.lying_down_tricep_extensions, 0, 0, false, 12);

        insertExercise(db, workoutId, R.string.side_to_side_push_ups, 0, -1, false, 13);
        insertExercise(db, workoutId, R.string.pour_flys, 0, 0, false, 14);
        insertExercise(db, workoutId, R.string.side_leaning_triceps_extensions, 0, 0, false, 15);
        insertExercise(db, workoutId, R.string.one_arm_push_ups, 0, -1, false, 16);
        insertExercise(db, workoutId, R.string.weighted_cirlces, 0, 0, false, 17);
        insertExercise(db, workoutId, R.string.throw_the_bomb, 0, 0, false, 18);
        insertExercise(db, workoutId, R.string.clap_or_plyo_push_ups, 0, -1, false, 19);
        insertExercise(db, workoutId, R.string.slow_mo_throws, 0, 0, false, 20);
        insertExercise(db, workoutId, R.string.front_to_back_tricep_extensions, 0, 0, false, 21);
        insertExercise(db, workoutId, R.string.one_arm_balance_push_ups, 0, -1, false, 22);
        insertExercise(db, workoutId, R.string.fly_row_presses, 0, 0, false, 23);
        insertExercise(db, workoutId, R.string.dumbell_cross_body_blows, 0, 0, false, 24);
    }

    private static void insertBackAndBicepExercies(SQLiteDatabase db, long workoutId) {
        insertExercise(db, workoutId, R.string.wide_front_pullups, 0, -1, false, 1);
        insertExercise(db, workoutId, R.string.lawnmowers, 0, 0, false, 2);
        insertExercise(db, workoutId, R.string.twenty_ones, 0, 0, false, 3);
        insertExercise(db, workoutId, R.string.one_arm_cross_body_curls, 0, 0, false, 4);
        insertExercise(db, workoutId, R.string.switch_grip_pull_up, 0, -1, false, 5);
        insertExercise(db, workoutId, R.string.elbows_out_lawnmowers, 0, 0, false, 6);
        insertExercise(db, workoutId, R.string.standing_bicep_curls, 0, 0, false, 7);
        insertExercise(db, workoutId, R.string.one_arm_concentration_curls, 0, 0, false, 8);
        insertExercise(db, workoutId, R.string.corn_cob_pull_ups, 0, -1, false, 9);
        insertExercise(db, workoutId, R.string.reverse_grip_bent_over_rows, 0, 0, false, 10);
        insertExercise(db, workoutId, R.string.open_arm_curls, 0, 0, false, 11);
        insertExercise(db, workoutId, R.string.static_arm_curls, 0, 0, false, 12);

        insertExercise(db, workoutId, R.string.towel_pull_ups, 0, -1, false, 13);
        insertExercise(db, workoutId, R.string.congdon_locomotives, 0, 0, false, 14);
        insertExercise(db, workoutId, R.string.crouching_cohen_curls, 0, 0, false, 15);
        insertExercise(db, workoutId, R.string.one_arm_corkscrew_curls, 0, 0, false, 16);
        insertExercise(db, workoutId, R.string.chin_ups, 0, -1, false, 17);
        insertExercise(db, workoutId, R.string.seated_bent_over_back_flys, 0, 0, false, 18);
        insertExercise(db, workoutId, R.string.curl_up_hammer_downs, 0, 0, false, 19);
        insertExercise(db, workoutId, R.string.max_rep_pull_ups, 0, -1, false, 20);
        insertExercise(db, workoutId, R.string.superman, 0, -1, false, 21);
        insertExercise(db, workoutId, R.string.in_out_hammer_curls, 0, 0, false, 22);
        insertExercise(db, workoutId, R.string.strip_set_curls, 0, 0, false, 23);
        insertExercise(db, workoutId, R.string.strip_set_curls, 0, 0, false, 24);
        insertExercise(db, workoutId, R.string.strip_set_curls, 0, 0, false, 25);
        insertExercise(db, workoutId, R.string.strip_set_curls, 0, 0, false, 26);
    }


























}
