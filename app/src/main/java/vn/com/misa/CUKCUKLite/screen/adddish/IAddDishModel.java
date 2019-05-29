package vn.com.misa.CUKCUKLite.screen.adddish;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 * Lớp model món ăn
 * @Create_by: trand
 * @Date: 5/29/2019
 */
public interface IAddDishModel {
    boolean updateDish(Dish dish);
    boolean saveNewDish(Dish newDish);
}
