package shopping.com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.com.LoginUserBean;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.セッションスコープを用意する。
		HttpSession session = request.getSession();

		//2.sessonのgetAttributeメソッドを利用して、セッションスコープからユーザ情報を取得し、ローカル変数 bean(LoginUserBean型)へ格納する。
		LoginUserBean bean = (LoginUserBean)session.getAttribute("user_db");

		//3.beanのgetUserIdメソッドを利用して、ユーザIDを取得し、ローカル変数userId(String型)へ格納する。
		String userId = bean.getUserId();

		//4
		List<CartBean> cartBeanArray = (ArrayList<CartBean>)session.getAttribute("cartlist");

		//5.遷移のための変数rd(RequestDispatcher型)を用意する。
		RequestDispatcher rd = null;

		//6.ShoppingDaoクラスのインスタンスを生成し、ローカル変数shopdaoへ格納する。
		ShoppingDao shopdao = new ShoppingDao();

		try {
			for(CartBean cb : cartBeanArray) {
				String itemId = cb.getItemId();
				int itemQuantity = cb.getBuyItemCount();
				shopdao.updateHistory(userId, itemId, itemQuantity);
				shopdao.updateItem(itemId, itemQuantity);
			}

			//カートを空にする
			session.removeAttribute("cartlist");


		}catch(ClassNotFoundException | SQLException e) {

			//10.エラーメッセージをスコープへ格納
			request.setAttribute("error_msg", "メンテナンス中です。しばらくお待ちください。");
			//11.requestのgetRequestDispatcherを使用して遷移先を loginNg.jsp に設定する。
			rd = request.getRequestDispatcher("/loginNG.jsp");
			//12. rd のforwardメソッドを使用して、ページ遷移を実行する。
			rd.forward(request, response);

		}


		//13. requestのgetRequestDispatcherを使用して遷移先を result.jsp に設定する。
		rd = request.getRequestDispatcher("/result.jsp");

		//14. rd のforwardメソッドを使用して、ページ遷移を実行する。
		rd.forward(request, response);

	}

}
