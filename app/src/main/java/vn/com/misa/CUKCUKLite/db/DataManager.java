package vn.com.misa.CUKCUKLite.db;
import java.util.ArrayList;
import vn.com.misa.CUKCUKLite.model.Food;

/**
 *
 * @created_by tdcong
 * @date 5/20/2019
 */
public interface DataManager {

    public interface SQLite {
        public ArrayList<Food> getFoodArrayList();
    }
}
