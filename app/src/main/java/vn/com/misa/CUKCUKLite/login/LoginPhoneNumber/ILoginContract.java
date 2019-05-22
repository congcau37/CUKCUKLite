package vn.com.misa.CUKCUKLite.login.LoginPhoneNumber;

/**
 *
 * @created_by tdcong
 * @date 5/20/2019
 */
public interface ILoginContract {
    interface ILoginView {

        void showOnSuccess();

        void showOnFail();
    }

    interface ILoginPresenter {

        void loadAccount(String userName, String passWord);
    }
}
