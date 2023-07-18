package rbac.token;

/**
 * 用户Token
 * @author MiracloW
 */
public class UserToken {

    private Long userId;

    private String userName;

    public UserToken() {
    }

    public UserToken(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "UserToken{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
