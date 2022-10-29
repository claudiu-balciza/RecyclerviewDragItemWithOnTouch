package claudiu.balciza.RecyclerviewDragItem;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RvItemTouchListener1 implements RecyclerView.OnItemTouchListener {
  private static final int SWIPE_MIN_DISTANCE = 100;
  private static final int SWIPE_THRESHOLD_VELOCITY = 150;
  private static final int SWIPE_MAX_OFF_PATH = 200;

  private final OnTouchActionListener mOnTouchActionListener;
  private final GestureDetectorCompat mGestureDetector;

  public interface OnTouchActionListener {
    void onLeftSwipe(View view, int position);
    void onRightSwipe(View view, int position);
    void onClick(View view, int position, float x, float y);
    void onLongClick(View view, int position, float x, float y);
  }

  public RvItemTouchListener1(Context context,
                              final RecyclerView recyclerView,
                              OnTouchActionListener onTouchActionListener){

    mOnTouchActionListener = onTouchActionListener;
    mGestureDetector = new GestureDetectorCompat(
        context,
        new GestureDetector.SimpleOnGestureListener(){
          @Override
          public boolean onSingleTapConfirmed(MotionEvent e) {
            View rvItem = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (rvItem != null) {
              int childPosition = recyclerView.getChildAdapterPosition(rvItem);
              mOnTouchActionListener.onClick(rvItem, childPosition, e.getX(), e.getY());
            }
            return false;
          }

          @Override
          public void onLongPress(MotionEvent e) {
            View rvItem = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if(rvItem != null) {
              int childPosition = recyclerView.getChildAdapterPosition(rvItem);
              mOnTouchActionListener.onLongClick(rvItem, childPosition, e.getX(), e.getY());
            }
          }

          @Override
          public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            try {
              if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                return false;
              }

              View rvItem = recyclerView.findChildViewUnder(e1.getX(), e1.getY());
              if (rvItem != null) {
                int rvItemPosition = recyclerView.getChildAdapterPosition(rvItem);

                // left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                  if (mOnTouchActionListener != null) {
                    mOnTouchActionListener.onLeftSwipe(rvItem, rvItemPosition);
                  }
                  // right swipe
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                  if (mOnTouchActionListener != null) {
                    mOnTouchActionListener.onRightSwipe(rvItem, rvItemPosition);
                  }
                }
              } else {
                return false;
              }
            } catch(Exception e){
              Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            return false;
          }
        });
  }

  @Override
  public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
    mGestureDetector.onTouchEvent(e);
    return false;
  }

  @Override
  public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
  }

  @Override
  public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
  }

}
