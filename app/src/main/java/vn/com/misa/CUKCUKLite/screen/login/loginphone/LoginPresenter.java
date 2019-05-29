package vn.com.misa.CUKCUKLite.screen.login.loginphone;

/**
 * Lớp presenter đăng nhập bằng điện thoại
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class LoginPresenter implements ILoginPhoneContract.ILoginPresenter {

    ILoginPhoneModel iLoginPhoneModel;
    ILoginPhoneContract.ILoginView iLoginView;

    public LoginPresenter(ILoginPhoneModel iLoginPhoneModel, ILoginPhoneContract.ILoginView iLoginView) {
        this.iLoginPhoneModel = iLoginPhoneModel;
        this.iLoginView = iLoginView;
    }

    /**
     * Hàm
     * @param
     * @return
     */
    @Override
    public void loadAccount(String userName, String passWord) {
        try {
            iLoginPhoneModel.getAccount(userName, passWord, new ILoginPhoneModel.IGetAccountCallback() {
                @Override
                public void checkAccount(boolean check) {
                    if (check) {
                        iLoginView.showOnSuccess();
                    } else {
                        iLoginView.showOnFail();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

