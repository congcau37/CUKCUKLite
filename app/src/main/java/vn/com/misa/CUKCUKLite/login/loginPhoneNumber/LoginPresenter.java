package vn.com.misa.CUKCUKLite.login.loginPhoneNumber;

/**
 * Lớp presenter đăng nhập bằng điện thoại
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class LoginPresenter implements ILoginContract.ILoginPresenter {

    ILogin iLogin;
    ILoginContract.ILoginView iLoginView;

    public LoginPresenter(ILogin iLogin, ILoginContract.ILoginView iLoginView) {
        this.iLogin = iLogin;
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
            iLogin.getAccount(userName, passWord, new ILogin.IGetAccountCallback() {
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

