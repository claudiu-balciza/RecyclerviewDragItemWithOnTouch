package claudiu.balciza.RecyclerviewDragItem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import claudiu.balciza.RecyclerviewDragItem.Model.Data;
import claudiu.balciza.RecyclerviewDragItem.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
  private List<Data> mData;

  public DataAdapter(List<Data> data) {
    mData = data;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    View dataView = inflater.inflate(R.layout.recycler_view_item, parent, false);

    return new ViewHolder(dataView);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Data data = mData.get(position);

    TextView textView = holder.dataTextView;
    textView.setText(data.getData());
  }

  @Override
  public int getItemCount() {
    return mData.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView dataTextView;

    public ViewHolder(View itemView) {
      super(itemView);

      dataTextView = itemView.findViewById(R.id.tvData);
    }
  }
}