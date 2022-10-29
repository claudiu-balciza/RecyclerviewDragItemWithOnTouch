package claudiu.balciza.RecyclerviewDragItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import claudiu.balciza.RecyclerviewDragItem.Adapters.DataAdapter;
import claudiu.balciza.RecyclerviewDragItem.Model.Data;

/**
 * you will notice that the onSwiped at times is followed by the onLongClick
 * especially when swiping left
 *
 * the RvItemTouchListener onLeftSwipe and onRightSwipe don't trigger any more
**/

public class MainActivity extends AppCompatActivity implements RecyclerViewDragHelper.AnimationListener {
  private static final String TAG = "RecyclerViewDragItem:activityMain:";
  private ArrayList<Data> data;
  private Context context;
  private DataAdapter dataAdapter;
  private RecyclerView rvData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    context = getApplicationContext();

    rvData = (RecyclerView) findViewById(R.id.rvData);
    data = Data.addSomeData(20);

    dataAdapter = new DataAdapter(data);
    rvData.setAdapter(dataAdapter);
    rvData.setLayoutManager(new LinearLayoutManager(context));

    new ItemTouchHelper(new RecyclerViewDragHelper(this)).attachToRecyclerView(rvData);

    rvData.addOnItemTouchListener(new RvItemTouchListener1(
        context,
        rvData,
        new RvItemTouchListener1.OnTouchActionListener() {
          @Override
          public void onLeftSwipe(View view, int position) {
            Log.d(TAG, "RvItemTouchListener1:onLeftSwipe");
          }

          @Override
          public void onRightSwipe(View view, int position) {
            Log.d(TAG, "RvItemTouchListener1:onRightSwipe");
          }

          @Override
          public void onClick(View view, int position, float x, float y) {
            Log.d(TAG, "RvItemTouchListener1:onClick");
          }

          @Override
          public void onLongClick(View view, int position, float x, float y) {
            Log.d(TAG, "RvItemTouchListener1:onLongClick");
          }
        }
    ));

  }

  @Override
  public void onMove(int fromPosition, int toPosition) {
    // to move you need to click (like long click) and then drag
    // do whatever you want when the item is moved
    // like move it in the adapter
    data.add(toPosition, data.remove(fromPosition));
    dataAdapter.notifyItemMoved(fromPosition, toPosition);
  }

  @Override
  public void onSwiped(int direction, int position) {
    // do whatever you want when the item is swiped
    // like delete it or flag it or change it's appearance
    // dataAdapter.makeTextBold((DataAdapter.ViewHolder) rvData.findViewHolderForAdapterPosition(position), position);
    dataAdapter.notifyItemChanged(position);
  }
}