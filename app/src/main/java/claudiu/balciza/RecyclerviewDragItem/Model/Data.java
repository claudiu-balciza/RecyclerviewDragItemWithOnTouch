package claudiu.balciza.RecyclerviewDragItem.Model;

import java.util.ArrayList;

public class Data {
  private String data;

  public Data(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  private static int lastDataId = 0;

  public static ArrayList<Data> addSomeData(int num) {
    ArrayList<Data> data = new ArrayList<>();

    for (int i = 1; i <= num; i++) {
      data.add(new Data("Data " + lastDataId++));
    }

    return data;
  }
}
