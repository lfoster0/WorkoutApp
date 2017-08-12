package leefoster.me.workoutapp;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import leefoster.me.workoutapp.adapters.WorkoutAdapter;
import leefoster.me.workoutapp.database.entries.Exercise;
import leefoster.me.workoutapp.database.entries.Workout;
import leefoster.me.workoutapp.database.entries.WorkoutDay;
import leefoster.me.workoutapp.utlis.WorkoutAppUtils;

public class WorkoutDetailActivity extends AppCompatActivity implements WorkoutAdapter.ExerciseClickHandler {
    private final double WEIGHT_STEP_LBS = 2.5;
    private WorkoutAdapter mAdapter;
    private RecyclerView mRecyclerview;
    private WorkoutDay mWorkoutDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        // Get the WorkoutDay
        long workoutDayId = getIntent().getExtras().getLong(WorkoutDay.COLS._ID);
        Cursor workoutDayCursor = getContentResolver().query(
                WorkoutDay.CONTENT_URI.buildUpon().appendPath(Long.toString(workoutDayId)).build(),
                null,
                null,
                null,
                null
                );
        workoutDayCursor.moveToFirst();
        mWorkoutDay = new WorkoutDay(workoutDayCursor, this);
        workoutDayCursor.close();

        // get the previous workout for the first workout today
        Cursor previousWorkoutCursor = getContentResolver().query(
                Workout.CONTENT_URI,
                null,
                Workout.COLS.COLUMN_NAME + " = ? AND " + Workout.COLS.COLUMN_WORKOUT_COMPLETED + " = ?",
                new String[]{mWorkoutDay.getWorkouts()[0].getName(), "1"},
                Workout.COLS._ID
        );

        Workout previousPrimaryWorkout = null;
        if (previousWorkoutCursor.getCount() > 0) {
            previousWorkoutCursor.moveToLast();
            previousPrimaryWorkout = new Workout(previousWorkoutCursor, this, null);
        }
        getSupportActionBar().setTitle("Day " + mWorkoutDay.getDay());

        // Setup recycler view/adapter
        mRecyclerview = (RecyclerView) findViewById(R.id.rv_exercises);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new WorkoutAdapter(this, this);
        mRecyclerview.setAdapter(mAdapter);

        ArrayList<Workout> workouts = new ArrayList<>(Arrays.asList(mWorkoutDay.getWorkouts()));
        mAdapter.swapWorkouts(workouts);
        mAdapter.setPreviousPrimaryWorkout(previousPrimaryWorkout);
    }

    @Override
    public void completedClicked(Exercise exercise) {
        exercise.setCompleted(!exercise.isCompleted(), this);
    }

    @Override
    public void weightPlusClicked(Exercise exercise) {
        double newWeight = exercise.getWeight() + WEIGHT_STEP_LBS;
        exercise.setWeight(newWeight, this);
    }

    @Override
    public void weightMinusClicked(Exercise exercise) {
        double newWeight = exercise.getWeight() - WEIGHT_STEP_LBS;
        if (newWeight < 0) {
            newWeight = 0;
        }
        exercise.setWeight(newWeight, this);
    }

    @Override
    public void repClicked(Exercise exercise) {
        exercise.setReps(exercise.getReps()+1, this);
    }

    @Override
    public boolean repLongClicked(Exercise exercise) {
        int newReps = exercise.getReps()-1;
        if (newReps < 0) {
            newReps = 0;
        }
        exercise.setReps(newReps, this);
        return true;
    }

    public void finishedWorkoutClicked(View v) {
        boolean finishedAllExercises = true;
        for (Workout workout : mWorkoutDay.getWorkouts()) {
            for (Exercise exercise : workout.getExercises()) {
                if (!exercise.isCompleted()) {
                    finishedAllExercises = false;
                    break;
                }
            }
            if (!finishedAllExercises) {
                break;
            }
        }

        if (!finishedAllExercises) {
            showConfirmFinishedAlert();
        } else {
            setWorkoutDayFinishedAndGoBack();
        }
    }

    private void showConfirmFinishedAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Not all exercises are completed!");
        alertDialog.setMessage("Are you sure you're finished today?");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Finished", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setWorkoutDayFinishedAndGoBack();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }

    private void setWorkoutDayFinishedAndGoBack() {
        mWorkoutDay.setCompleted(true, this);
        for (Workout workout : mWorkoutDay.getWorkouts()) {
            workout.setCompleted(true, this);
        }
        WorkoutAppUtils.setCurrentWorkoutDay(mWorkoutDay.getDay()+1);
        finish();
    }
}
