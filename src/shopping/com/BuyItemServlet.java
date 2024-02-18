package shopping.com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuyItemServlet
 */
@WebServlet("/shopping/buy")
public class BuyItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//1.requestのsetCharacterEncodingメソッドを使用してHTTPリスエストの文字コードを設定する。
		request.setCharacterEncoding("UTF-8");
		//2.requestのgetParameterNamesメソッドを使用してリクエストに含まれるパラメータ名をEnumeration<String>型のnamesへ代入する。
		Enumeration<String> names = request.getParameterNames();
		//3.現在のパラメータ名、商品ID、購入数用のローカル変数を宣言する。(初期値は空白("")を代入しておく。)
		String name = "";		//パラメータ名
		String itemId = "";		//商品ID
		String quantity = "";	//購入数

		//4.購入ボタンに付属している商品IDを取得するまで繰り返す。
		while(names.hasMoreElements()) {
			//4-1.namesのnextElementメソッドを実行し、ローカル変数nameへ代入する。
			name = names.nextElement();
			//4-2.requestのgetParameterメソッドを使用し、nameパラメータの値が"購入確認"かどうか確認する。(条件分岐)
			if("カートに入れる".equals(request.getParameter(name))) {
				//4-2-1.nameをitemIdへ代入する(購入ボタンに付属している値（value）が商品IDになる)
				itemId = name;
			}
		}
		//5.requestのgetParameterメソッドを使用してドロップダウンリストから購入数を取得し、quantityへ代入する。
		quantity = request.getParameter(itemId + "list");


		//6.Shoppingクラスのインスタンスを生成し、ローカル変数shoppingへ代入する。
		Shopping shopping = new Shopping();

		//7.遷移のための変数rd(RequestDispatcher型)を用意する。
		RequestDispatcher rd = null;

		//8.bean(ItemBean型)を用意する。
		ItemBean bean = null;

		try {
			//9.shoppingのgetItemメソッドを実行し、ローカル変数beanへ代入する。
			bean = shopping.getItem(itemId);

			// カートに商品を追加する
            ArrayList<CartBean> cartList = (ArrayList<CartBean>) request.getSession().getAttribute("cartlist");
            if (cartList == null) {
                cartList = new ArrayList<>();
            }
            shopping.addToCart(bean, Integer.parseInt(quantity), cartList);
            request.getSession().setAttribute("cartlist", cartList);

		} catch (ClassNotFoundException | SQLException e) {
			//10.エラーメッセージをスコープへ格納
    		request.setAttribute("error_msg", "メンテナンス中です。しばらくお待ちください。");
    		//11.requestのgetRequestDispatcherを使用して遷移先を loginNg.jsp に設定する。
    		rd = request.getRequestDispatcher("/loginNG.jsp");
    		//12.rd のforwardメソッドを使用して、ページ遷移を実行する。
    		rd.forward(request, response);

		}

		//13. requestのsetAttributeメソッドを利用し、リクエストスコープに商品一覧情報を設定する。
		request.setAttribute("item", bean);
		request.setAttribute("quantity", quantity);

		//14. requestのgetRequestDispatcherを使用して遷移先を confirm.jsp に設定する。
		rd = request.getRequestDispatcher("/itemCart.jsp");

		//15. rd のforwardメソッドを使用して、ページ遷移を実行する。
		rd.forward(request, response);


	}

}