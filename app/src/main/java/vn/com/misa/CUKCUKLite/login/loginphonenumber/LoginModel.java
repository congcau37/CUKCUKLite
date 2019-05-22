package vn.com.misa.CUKCUKLite.login.loginphonenumber;

/**
 *
 * @created_by tdcong
 * @date 5/20/2019
 */
public class LoginModel implements ILogin {

    /**
     * Hàm lấy ra tài khoản
     * @param
     * @return
     */
    @Override
    public void getAccount(String userName, String passWord, IGetAccountCallback callback) {
        callback.checkAccount(checkAccountDB(userName,passWord));
    }

    /**
     * Hàm kiểm tra tài khoản
     * @param
     * @return
     */
    @Override
    public boolean checkAccountDB(String userName, String passWord) {
        try {
            if (userName.equals("admin") && passWord.equals("admin"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
