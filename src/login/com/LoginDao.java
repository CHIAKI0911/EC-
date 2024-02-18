package login.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import setting.com.DbSetting;

public class LoginDao {

	private Connection 			con 	= null;		//DB接続用
	private PreparedStatement 	ps 		= null;		//SQL実行用
	private ResultSet			rs		= null;		//SQL実行結果用
	private LoginUserBean		bean 	= null;		//ユーザ情報用

	/**
	 * ユーザ情報を取得する
	 * @param userId		ログインID
	 * @param password		パスワード
	 * @return				ユーザ情報
	 */
	public LoginUserBean selectUser(String userId,String password)throws Exception {
		//1. ClassクラスのforNameメソッドを使用し、JDBCドライバをロードする。
		Class.forName("com.mysql.jdbc.Driver");
		//2. DriverManagerクラスのgetConnectionメソッドを使用し、Connectionオブジェクトを生成し、メンバ変数conへ代入する。
		con = DriverManager.getConnection(DbSetting.URL,DbSetting.USER,DbSetting.PASS);
		//3. conのprepareStatementメソッドを利用し、SQL文を生成する。そしてメンバ変数psへ代入する。
		ps = con.prepareStatement("select * from user_info where id = ?");
		//4 psのsetStringメソッドを利用してユーザIDを設定する。
		ps.setString(1,userId);
		//5. psのexecuteQueryメソッドを使用し、SQLを実行する。実行結果をメンバ変数rsへ代入する。
		rs = ps.executeQuery();

		//6. rsから実行結果を取り出す。（rs.next()を実行し、結果を条件分岐で判定する）
		if(rs.next()) {
			//  6-1. ResultSetオブジェクト(rs)に格納されているパスワードと引数(password))が同じかどうか
			if(rs.getString("pass").equals(password) ) {
				//6-1-1. LoginUserBeanクラスのインスタンスを生成し、メンバ変数beanへ代入する。
				bean = new LoginUserBean();
				//6-1-2. beanのsetUserIdメソッドを使用し、ユーザIDを設定する。
				bean.setUserId(userId);
				//6-1-3. beanのsetNameメソッドを使用し、ユーザ名を設定する。
				bean.setName(rs.getString("name"));
			}else {
				// 6-1-4. Exceptionオブジェクトを生成し、をスローする
				throw new Exception("パスワードに誤りがあります。再度確認し入力し直して下さい。");

			}

			//8. データベースとの接続を解除する。
			con.close();
			ps.close();
			rs.close();

		}
		//9. ユーザ情報（bean）を返す。
		return bean;
	}

	public void userAdd(String userId, String userPass , String userName) throws Exception{

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DbSetting.URL,DbSetting.USER,DbSetting.PASS);
			ps = con.prepareStatement("INSERT INTO user_info(id , pass , name ) VALUES( ? , ? , ? )");
			ps.setString(1, userId);
			ps.setString(2, userPass);
			ps.setString(3, userName);



			int addCount =  ps.executeUpdate();

			if(addCount != 1) {
				throw new Exception("新規ユーザー登録でエラーが発生しました。");
			}

			con.close();
			ps.close();
	}
}
