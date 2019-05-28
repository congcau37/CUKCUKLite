package vn.com.misa.CUKCUKLite.order.editFood;

import vn.com.misa.CUKCUKLite.model.Food;

/**
 * Lớp model món ăn
 * @Create_by: trand
 * @Date: 5/29/2019
 */
public interface IFoodModel {
    boolean updateFood(Food food);
    boolean saveNewFood(Food newFood);
}
