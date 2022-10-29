package claudiu.balciza.RecyclerviewDragItem;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class RecyclerViewDragHelper extends ItemTouchHelper.Callback {
  private static final String TAG = "RecyclerViewDragItem:RecyclerViewDragHelper:";
  private AnimationListener animationListener;

  public RecyclerViewDragHelper(AnimationListener animationListener) {
    this.animationListener = animationListener;
  }

  @Override
  public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
    int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
    int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
    return makeMovementFlags(dragFlags, swipeFlags);
  }

  @Override
  public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
    Log.d(TAG, "onMove");
    if(animationListener != null) {
      animationListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }
    return true;
  }

  @Override
  public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    // direction ItemTouchHelper.UP, ItemTouchHelper.DOWN, ItemTouchHelper.START, ItemTouchHelper.END
    Log.d(TAG, "onSwiped");
    if(animationListener != null) {
      animationListener.onSwiped(direction, viewHolder.getAdapterPosition());
    }
  }

  interface AnimationListener {
    void onMove(int fromPosition, int toPosition);
    void onSwiped(int direction, int position);
  }
}
