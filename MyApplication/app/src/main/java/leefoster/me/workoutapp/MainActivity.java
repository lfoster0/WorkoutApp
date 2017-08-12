package leefoster.me.workoutapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import leefoster.me.workoutapp.adapters.WorkoutDayAdapter;
import leefoster.me.workoutapp.database.entries.WorkoutDay;

public class MainActivity extends AppCompatActivity implements WorkoutDayAdapter.WorkoutDayClickHandler {

    private WorkoutDayAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_workout_days);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new GridDivider());

        mAdapter = new WorkoutDayAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor c = getContentResolver().query(WorkoutDay.CONTENT_URI,
                null,
                null,
                null,
                null);

        ArrayList<WorkoutDay> workoutDays = new ArrayList<>();
        while (c.moveToNext()) {
            workoutDays.add(new WorkoutDay(c, this));
        }
        c.close();
        mAdapter.swapWorkoutDays(workoutDays);
    }

    @Override
    public void workoutDayClicked(WorkoutDay workoutDay) {
        Intent detailIntent = new Intent(this, WorkoutDetailActivity.class);
        detailIntent.putExtra(WorkoutDay.COLS._ID, workoutDay.getId());
        startActivity(detailIntent);
    }

    public class GridDivider extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            outRect.bottom = 16;
            if (position == 0 || position == 1) {
                outRect.top = 16;
            } else {
                outRect.top = 16;
            }
            if (position % 2 == 0) {
                outRect.left = 32;
                outRect.right = 16;
            } else {
                outRect.right = 32;
                outRect.left = 16;
            }
        }

    }
}
