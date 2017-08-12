package leefoster.me.workoutapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import leefoster.me.workoutapp.database.entries.Exercise;
import leefoster.me.workoutapp.database.entries.Workout;
import leefoster.me.workoutapp.databinding.ListItemExerciseBinding;
import leefoster.me.workoutapp.databinding.ListItemWorkoutBinding;

/**
 * Created by leefoster on 2017-06-04.
 */

public class WorkoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Object> mWorkoutsAndExercises;
    Context mContext;
    ExerciseClickHandler mExerciseClickDelegate;
    Workout mPreviousPrimaryWorkout;

    public WorkoutAdapter(Context context, ExerciseClickHandler clickDelegate) {
        mContext = context;
        mWorkoutsAndExercises = new ArrayList<>();
        mExerciseClickDelegate = clickDelegate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ExerciseViewHolder.VIEW_TYPE:
                ListItemExerciseBinding exerciseBinding = ListItemExerciseBinding.inflate(LayoutInflater.from(mContext),parent,false);
                return new ExerciseViewHolder(exerciseBinding);
            case WorkoutViewHolder.VIEW_TYPE:
                ListItemWorkoutBinding workoutBinding = ListItemWorkoutBinding.inflate(LayoutInflater.from(mContext), parent, false);
                return new WorkoutViewHolder(workoutBinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExerciseViewHolder) {
            Exercise exercise = (Exercise) mWorkoutsAndExercises.get(position);
            Exercise previousExercise = null;
            if (mPreviousPrimaryWorkout != null &&
                    position < mPreviousPrimaryWorkout.getExercises().length) {
                previousExercise = mPreviousPrimaryWorkout.getExercises()[position-1];
                exercise.setWeight(previousExercise.getWeight(), mContext);
            }
            ExerciseViewHolder exerciseViewHolder = (ExerciseViewHolder) holder;
            exerciseViewHolder.bind(exercise,
                    previousExercise,
                    mExerciseClickDelegate);
        } else if (holder instanceof WorkoutViewHolder) {
            WorkoutViewHolder workoutViewHolder = (WorkoutViewHolder) holder;
            workoutViewHolder.bind((Workout) mWorkoutsAndExercises.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mWorkoutsAndExercises.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (mWorkoutsAndExercises.get(position) instanceof Workout) {
            return WorkoutViewHolder.VIEW_TYPE;
        }
        return ExerciseViewHolder.VIEW_TYPE;
    }

    public void swapWorkouts(ArrayList<Workout> workouts) {
        mWorkoutsAndExercises = new ArrayList<>();
        for (Workout workout: workouts) {
            mWorkoutsAndExercises.add(workout);
            mWorkoutsAndExercises.addAll(Arrays.asList(workout.getExercises()));
        }
        notifyDataSetChanged();
    }

    public void setPreviousPrimaryWorkout(Workout workout) {
        mPreviousPrimaryWorkout = workout;
        notifyDataSetChanged();
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        static final int VIEW_TYPE = 1;

        private ListItemExerciseBinding mBinding;

        public ExerciseViewHolder(ListItemExerciseBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Exercise exercise, Exercise previousExercise, ExerciseClickHandler clickHandler) {
            mBinding.setExercise(exercise);
            mBinding.setPreviousExercise(previousExercise);
            mBinding.setClickHandler(clickHandler);
        }
    }

    class WorkoutViewHolder extends RecyclerView.ViewHolder {
        static final int VIEW_TYPE = 2;

        private ListItemWorkoutBinding mBinding;

        public WorkoutViewHolder(ListItemWorkoutBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Workout workout) {
            mBinding.setWorkout(workout);
        }
    }

    public interface ExerciseClickHandler {
        void completedClicked(Exercise exercise);
        void weightPlusClicked(Exercise exercise);
        void weightMinusClicked(Exercise exercise);
        void repClicked(Exercise exercise);
        boolean repLongClicked(Exercise exercise);
    }
}
