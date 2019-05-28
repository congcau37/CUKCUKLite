package vn.com.misa.CUKCUKLite.login.loginPhoneNumber;

/**
 * Lớp model đăng nhập bằng điện thoại
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class LoginModel implements ILogin {

    /**
     * Hàm lấy ra tài khoản
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: userName, passWord, callback
     * @Return:
     */
    @Override
    public void getAccount(String userName, String passWord, IGetAccountCallback callback) {
        callback.checkAccount(checkAccountDB(userName,passWord));
    }

    /**
     * Hàm kiểm tra tài khoản
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: userName, passWord
     * @Return: boolean
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
