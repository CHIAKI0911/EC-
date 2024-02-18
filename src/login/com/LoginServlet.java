package login.com;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータ取得
		String strParam = request.getParameter("useradd");
		//リクエストパラメータチェック
		if(strParam != null && strParam.equals("newadd")) {

			//新規ユーザー登録画面を表示
			//1. 引数requestが参照しているHttpServletRequestオブジェクトのgetServletContextメソッドを呼び出し、戻り値であるServletContextオブジェクトをローカル変数con(ServletContext型)へ代入する。
			ServletContext con = request.getServletContext();
			//2.ローカル変数con(ServletContext型)が参照しているServletContext オブジェクトのgetRequestDispatcherメソッドを呼び出し、ページ遷移先を「/WEB-INF/jsp/login.jsp」へ設定する。
			RequestDispatcher rd = con.getRequestDispatcher("/useradd.jsp");
			//3.rdのforwardメソッドを使用して、ページ遷移を実行する。
			rd.forward(request, response);

		}


		//セッションを破棄する
		HttpSession session = request.getSession();
		String sessionFLG = (String)session.getAttribute("login_db");
		if(sessionFLG != null) {
			if(sessionFLG.equals("login")) {
				session.removeAttribute("user_db");
				session.removeAttribute("login_db");
			}
		}

		//1. 引数requestが参照しているHttpServletRequestオブジェクトのgetServletContextメソッドを呼び出し、戻り値であるServletContextオブジェクトをローカル変数con(ServletContext型)へ代入する。
		ServletContext con = request.getServletContext();
		//2.ローカル変数con(ServletContext型)が参照しているServletContext オブジェクトのgetRequestDispatcherメソッドを呼び出し、ページ遷移先を「/WEB-INF/jsp/login.jsp」へ設定する。
		RequestDispatcher rd = con.getRequestDispatcher("/login.jsp");
		//3.rdのforwardメソッドを使用して、ページ遷移を実行する。
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.requestのsetCharacterEncodingメソッドを使用してHTTPリクエストの文字コードを設定する。
		request.setCharacterEncoding("UTF-8");
		//2.requestのgetServletContextを呼び出して、ローカル変数con(ServletContext型)へ代入する
		ServletContext con = request.getServletContext();
		//3.遷移のための変数rd(RequestDispatcher型)を用意する。
		RequestDispatcher rd = null;
		//4.セッションスコープを用意する。
		HttpSession session = request.getSession();
		//5.requestのgetParameterメソッドを利用してsubmitパラメータ情報を取得し、ローカル変数 btn へ格納する。
		String btn = request.getParameter("submit");


		//6.押されたボタン情報によって、処理を分岐する。
		if(btn.equals("ログイン")){
			//6-1.ユーザIdとパスワードを取得
			String userId = request.getParameter("id");
			String password = request.getParameter("pass");
			//6-2.Loginクラスインスタンスを作成する。
			Login login = new Login();

			try {
				//6-3.ログインチェック実施。
				LoginUserBean bean = login.loginCheck(userId, password);
				//6-4.sessionのsetAttributeメソッドを利用し、セッションスコープにユーザ情報を設定する。
				session.setAttribute("user_db", bean);
				session.setAttribute("login_db", "login");

				//6-5.ローカル変数conのgetRequestDispatcherを使用して遷移先を ShoppingServlet に設定する。
				rd =  con.getRequestDispatcher("/shopping");

			}catch(ClassNotFoundException | SQLException e ){
				//6-6.エラーメッセージをリクエストスコープへ格納
				request.setAttribute("error_msg", "メンテナンス中です。しばらくお待ちください。");
				//6-7.ローカル変数conのgetRequestDispatcherを使用して遷移先を loginNg.jsp に設定する
				rd = con.getRequestDispatcher("/loginNG.jsp");

			}catch(Exception e) {
				//6-8.エラーメッセージをリクエストスコープへ格納
				request.setAttribute("error_msg", e.getMessage());
				//6-9.ローカル変数conのgetRequestDispatcherを使用して遷移先を loginNg.jsp に設定する
				rd = con.getRequestDispatcher("/loginNG.jsp");


			}finally {
				//6-10.rd のforwardメソッドを使用して、ページ遷移を実行する。
				rd.forward(request, response);
			}

			return;

		}
		if (btn.equals("新規ユーザー登録確認")){

			//新規登録時の処理
			//名前、ログインID、パスワードを取得
			String userName = request.getParameter("userName");
			String userId = request.getParameter("userId");
			String userPass = request.getParameter("password");



			Login login = new Login();
			try {
				//入力チェック
				LoginUserBean bean = login.userAdd(userName,userId,userPass);
				//リクエストスコープへ登録(正常時)
				request.setAttribute("newUserInfo", bean);
				//新規ユーザ登録 確認画面表示
				rd = request.getRequestDispatcher("/userAddConfrim.jsp");

			} catch (Exception e) {
				//リクエストスコープへ登録(エラー時)
				request.setAttribute("error_msg", e.getMessage());
				//新規ユーザ登録 確認画面表示
				rd = request.getRequestDispatcher("/userAddNg.jsp");
			}finally {
				rd.forward(request, response);
			}

		}if (btn.equals("新規ユーザ登録実行")){

			//名前、ログインID、パスワードを取得
			String userName = request.getParameter("name");
			String userId = request.getParameter("userId");
			String userPass = request.getParameter("password");
			try {
				//DBへ登録
				LoginDao ld = new LoginDao();
				ld.userAdd(userId, userPass, userName);

				//新規ユーザ登録 OK画面表示
				rd = request.getRequestDispatcher("/userAddResult.jsp");

			} catch (Exception e) {
				//リクエストスコープへ登録(エラー時)
				request.setAttribute("error_msg", e.getMessage());
				//新規ユーザ登録 NG画面表示
				rd = request.getRequestDispatcher("/userAddNg.jsp");
			}finally {
				rd.forward(request, response);
			}

		}


	}

}
