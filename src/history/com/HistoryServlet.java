package history.com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.com.LoginUserBean;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	//1.セッションスコープを用意する。
    	HttpSession  session = request.getSession();

    	//2.sessionのgetAttributeメソッドを利用し、セッションスコープからユーザ情報を取得し、ローカル変数 lubへ格納する。
    	LoginUserBean lub = (LoginUserBean)session.getAttribute("user_db");

    	//3.ローカル変数 lubのgetUserIdメソッドを利用し、ログインID情報を取得し、ローカル変数userIdへ格納する。
    	String userId = lub.getUserId();

    	//4.Historyクラスインスタンスを作成する。
    	History history = new History();

    	//5.遷移のための変数rd(RequestDispatcher型)を用意する。
    	RequestDispatcher rd = null;

    	//6.ArrayList<HistoryBean>型のローカル変数 historyListBean を宣言し、nullを代入する。(戻り値用の変数を宣言する。)
    	ArrayList<HistoryBean> historyListBean = null;

    	try {
    		//7. historyのgetHistoryメソッドを利用し、購入履歴取得処理を実行する。
    		historyListBean = history.getHistory(userId);

    	}catch(ClassNotFoundException | SQLException e) {
    		//8.エラーメッセージをリクエストスコープへ格納
    		request.setAttribute("error_msg", "メンテナンス中です。しばらくお待ちください。");
    		//9.requestのgetRequestDispatcherを使用して遷移先を loginNg.jsp に設定する。
    		rd = request.getRequestDispatcher("/loginNG.jsp");
    		//10 rd のforwardメソッドを使用して、ページ遷移を実行する。
    		rd.forward(request, response);

    	}
    	//11.requestのsetAttributeメソッドを利用し、リスエストスコープに購入履歴情報を設定する。
    	request.setAttribute("historyList", historyListBean);
    	//12.requestのgetRequestDispatcherを使用して遷移先を history.jsp に設定する。
    	rd = request.getRequestDispatcher("/history.jsp");
    	//13.rd のforwardメソッドを使用して、ページ遷移を実行する。
    	rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
