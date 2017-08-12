package leefoster.me.workoutapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import leefoster.me.workoutapp.utlis.WorkoutAppUtils;
import leefoster.me.workoutapp.database.entries.WorkoutDay;
import leefoster.me.workoutapp.databinding.ListItemWorkoutDayBinding;

/**
 * Created by leefoster on 2017-05-21.
 */

public class WorkoutDayAdapter extends RecyclerView.Adapter<WorkoutDayAdapter.WorkoutDayAdapterViewHolder> {

    private final Context mContext;
    private ArrayList<WorkoutDay> mWorkoutDays;
    private WorkoutDayClickHandler mClickDelegate;

    public WorkoutDayAdapter(Context context, WorkoutDayClickHandler clickDelegate) {
        mContext = context;
        mWorkoutDays = new ArrayList<>();
        mClickDelegate = clickDelegate;
    }

    @Override
    public WorkoutDayAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemWorkoutDayBinding binding = ListItemWorkoutDayBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new WorkoutDayAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(WorkoutDayAdapterViewHolder holder, int position) {
        holder.bind(mWorkoutDays.get(position), mClickDelegate);
    }

    @Override
    public int getItemCount() {
        return mWorkoutDays.size();
    }

    public void swapWorkoutDays(ArrayList<WorkoutDay> newWorkoutDays) {
        mWorkoutDays = newWorkoutDays;
        notifyDataSetChanged();
    }

    class WorkoutDayAdapterViewHolder extends RecyclerView.ViewHolder {
        ListItemWorkoutDayBinding mBinding;

        public WorkoutDayAdapterViewHolder(ListItemWorkoutDayBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(WorkoutDay workoutDayObject, WorkoutDayClickHandler clickHandler) {
            mBinding.setWorkoutDay(workoutDayObject);
            mBinding.setCurrentWorkoutDay(workoutDayObject.getDay() == WorkoutAppUtils.getCurrentWorkoutDay());
            mBinding.setWorkoutImage(mContext.getDrawable(workoutDayObject.getWorkouts()[0].getDrawableId()));
            mBinding.setClickHandler(clickHandler);
        }
    }

    public interface WorkoutDayClickHandler {
        void workoutDayClicked(WorkoutDay workoutDay);
    }
}
