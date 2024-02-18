package login.com;

public class LoginUserBean {

	private static final long serialVersionUID = 1L;
	private String userId;		//ユーザID
	private String password;	//パスワード
	private String name;		//名前
	/**
	 * @param userId
	 * @param password
	 * @param name
	 */
	public LoginUserBean() {
		this.userId = "";
		this.password = "";
		this.name = "";
	}

	/**
	 *コンストラクタ
	 * @param userId
	 * @param password
	 * @param name
	 */
	public LoginUserBean(String name, String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
	}


	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}



}
