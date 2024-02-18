package login.com;

import java.util.regex.Pattern;

public class Login {

	public LoginUserBean loginCheck(String userId , String password) throws Exception{

		//1. LoginUserBean型のローカル変数beanを宣言し、nullを代入する。（戻り値用の変数を宣言する。）
		LoginUserBean bean = null;
		//2. ログインID及びパスワード文字列をチェックする。
		Pattern p = Pattern.compile("^[a-zA-Z0-9$_]{8,24}$");
		// ユーザIDチェック
		if(!(p.matcher(userId).find())) {
			//2-1. Exceptionオブジェクトを生成し、スローする
			throw new Exception("ログインIDに誤りがあります。再度入力し直して下さい。<br>なおログインIDは、半角英数字(記号$_含む)8桁以上24桁以内の文字列を入力して下さい。");
		}

		// パスワードチェック
		if(!(p.matcher(password).find())) {
			//2-2. Exceptionオブジェクトを生成し、をスローする
			throw new Exception("パスワードに誤りがあります。再度入力し直して下さい。<br>なおログインIDは、半角英数字(記号$_含む)8桁以上24桁以内の文字列を入力して下さい。");
		}

		//3. LoginDaoオブジェクトを生成し、ローカル変数daoへ代入する。
		LoginDao dao = new LoginDao();

		//4. daoのselectUserメソッドを利用してユーザ情報を取得し、beanへ格納する。
		bean =  dao.selectUser(userId, password);

		//5. beanオブジェクトを戻り値として返す。
		return bean;

	}

	/**
	 * 新規ユーザ用チェックメソッド(LoginDaoクラスのUserAddメソッドを実行)
	 * @param userName 	名前
	 * @param userId 	ログインID
	 * @param userPass	パスワード
	 * @return
	 * @throws Exception
	 */
	public LoginUserBean userAdd(String userName , String userId , String userPass) throws Exception {
		//戻り値の初期化
		LoginUserBean  bean = null;

		System.out.print("Login.JavaのuserName→");
		System.out.println(userName);
		System.out.print("Login.JavaのuserId→");
		System.out.println(userId);
		System.out.print("Login.JavaのuserPass→");
		System.out.println(userPass);

		//正規表現を設定
		Pattern p = Pattern.compile("^[a-zA-Z0-9$_.]{8,24}$");

		//名前チェック
		if(userName.length() > 20){
			throw new Exception("名前の入力に誤りがあります。再度入力し直して下さい。<br>なお、名前は20桁以内の文字列を入力して下さい。");
		}

		//ログインID
		if(!(p.matcher(userId).matches())) {
			//2-1. Exceptionオブジェクトを生成し、スローする
			throw new Exception("ログインIDに誤りがあります。再度入力し直して下さい。<br>なおログインIDは、半角英数字(記号$_含む)8桁以上24桁以内の文字列を入力して下さい。");
		}

		// パスワードチェック
		if(!(p.matcher(userPass).matches())) {
			//2-2. Exceptionオブジェクトを生成し、をスローする
			throw new Exception("パスワードに誤りがあります。再度入力し直して下さい。<br>なおログインIDは、半角英数字(記号$_含む)8桁以上24桁以内の文字列を入力して下さい。");
		}
		//LoginUserBeanをインスタンス化
		bean = new LoginUserBean(userName,userId,userPass);
		return  bean;
	}


}
