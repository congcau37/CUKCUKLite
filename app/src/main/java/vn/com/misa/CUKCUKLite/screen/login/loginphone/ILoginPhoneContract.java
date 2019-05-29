package vn.com.misa.CUKCUKLite.screen.login.loginphone;

/**
 *
 * @created_by tdcong
 * @date 5/20/2019
 */
public interface ILoginPhoneContract {
    interface ILoginView {

        void showOnSuccess();

        void showOnFail();
    }

    interface ILoginPresenter {

        void loadAccount(String userName, String passWord);
    }
}
