package shopping.com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 商品一覧の処理を受け付けるクラス
 * @version 1.0
 * @author fukushima
 */
@WebServlet("/shopping")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	//リクエストパラメータ取得
    			String strParam = request.getParameter("cart");
    			String btn = request.getParameter("submit");


    			//リクエストパラメータチェック
    			if(strParam != null && strParam.equals("select")) {

    				//カート一覧画面を表示
    				//1. 引数requestが参照しているHttpServletRequestオブジェクトのgetServletContextメソッドを呼び出し、戻り値であるServletContextオブジェクトをローカル変数con(ServletContext型)へ代入する。
    				ServletContext con = request.getServletContext();
    				//2.ローカル変数con(ServletContext型)が参照しているServletContext オブジェクトのgetRequestDispatcherメソッドを呼び出し、ページ遷移先を「/WEB-INF/jsp/cart.jsp」へ設定する。
    				RequestDispatcher rd = con.getRequestDispatcher("/itemCart.jsp");
    				//3.rdのforwardメソッドを使用して、ページ遷移を実行する。
    				rd.forward(request, response);

    			}else if (strParam != null && strParam.equals("input")){

    				//セッションスコープの準備
    				HttpSession session = request.getSession();


    				//商品IDと購入数を取得
    				String itemId = request.getParameter("itemId");
    				String itemName = request.getParameter("itemName");
    				int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
    				int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
    				int ordersNumber = Integer.parseInt(request.getParameter("ordersNumber"));

    				//CartBeanオブジェクト生成
    				CartBean cb = new CartBean(itemId, itemName, itemPrice, itemQuantity, ordersNumber);

    				//セッションスコープからデータ取得
    				List<CartBean> cartlist = (ArrayList<CartBean>)session.getAttribute("cartlist");

    				//cartlistへ追加
    				if(cartlist == null) {
    					cartlist = new ArrayList<CartBean>();
    				}
    				cartlist.add(cb);

    				//セッションスコープへ保存
    				session.setAttribute("cartlist", cartlist);

    				//カート一覧画面を表示
    				//1. 引数requestが参照しているHttpServletRequestオブジェクトのgetServletContextメソッドを呼び出し、戻り値であるServletContextオブジェクトをローカル変数con(ServletContext型)へ代入する。
    				ServletContext con = request.getServletContext();
    				//2.ローカル変数con(ServletContext型)が参照しているServletContext オブジェクトのgetRequestDispatcherメソッドを呼び出し、ページ遷移先を「/WEB-INF/jsp/cart.jsp」へ設定する。
    				RequestDispatcher rd = con.getRequestDispatcher("/itemCart.jsp");
    				//3.rdのforwardメソッドを使用して、ページ遷移を実行する。
    				rd.forward(request, response);

    			}else if(btn != null && btn.equals("一覧に戻る")) {
    				//1.Shoppingクラスのインスタンスを作成する。
    		    	Shopping shopping = new Shopping();

    		    	//2.画面遷移のための変数rd(RequestDispatcher型)を用意する。
    		    	RequestDispatcher rd = null;

    		    	//3.itembeanList(ArrayList<ItemBean>型)を用意する。
    		    	ArrayList<ItemBean> itembeanList = null;

    		    	try {

    			    	//4.Shoppingオブジェクト(ローカル変数shopping)のgetItemメソッドを実行し、ローカル変数 itembeanList(ArrayList<ItemBean>型) へ格納する。
    			    	itembeanList = shopping.getItem();


    		    	}catch(ClassNotFoundException | SQLException e) {
    		    		e.getStackTrace();
    		    		//5.エラーメッセージをリクエストスコープへ格納
    		    		request.setAttribute("error_msg", "メンテナンス中です。しばらくお待ちください。");
    		    		//6.requestのgetRequestDispatcherを使用して遷移先を loginNg.jsp に設定する。
    		    		rd = request.getRequestDispatcher("/loginNG.jsp");
    		    		//7 rd のforwardメソッドを使用して、ページ遷移を実行する。
    		    		rd.forward(request, response);

    		    	}

    		    	//8.requestのsetAttributeメソッドを利用し、リクエストスコープに商品情報を設定する。
    		    	request.setAttribute("itembeanList",itembeanList);
    		    	//9.requestのgetRequestDispatcherを使用して遷移先を itemList.jsp に設定する。
    		    	rd = request.getRequestDispatcher("/itemList.jsp");
    		    	//10.rd のforwardメソッドを使用して、ページ遷移を実行する。
    		    	rd.forward(request, response);



    			}else {
    		    	//1.doPostメソッドを呼び出す
    		    	doPost(request, response);
    			}

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	//1.Shoppingクラスのインスタンスを作成する。
    	Shopping shopping = new Shopping();

    	//2.画面遷移のための変数rd(RequestDispatcher型)を用意する。
    	RequestDispatcher rd = null;

    	//3.itembeanList(ArrayList<ItemBean>型)を用意する。
    	ArrayList<ItemBean> itembeanList = null;

    	try {

	    	//4.Shoppingオブジェクト(ローカル変数shopping)のgetItemメソッドを実行し、ローカル変数 itembeanList(ArrayList<ItemBean>型) へ格納する。
	    	itembeanList = shopping.getItem();


    	}catch(ClassNotFoundException | SQLException e) {
    		e.getStackTrace();
    		//5.エラーメッセージをリクエストスコープへ格納
    		request.setAttribute("error_msg", "メンテナンス中です。しばらくお待ちください。");
    		//6.requestのgetRequestDispatcherを使用して遷移先を loginNg.jsp に設定する。
    		rd = request.getRequestDispatcher("/loginNG.jsp");
    		//7 rd のforwardメソッドを使用して、ページ遷移を実行する。
    		rd.forward(request, response);

    	}

    	//8.requestのsetAttributeメソッドを利用し、リクエストスコープに商品情報を設定する。
    	request.setAttribute("itembeanList",itembeanList);
    	//9.requestのgetRequestDispatcherを使用して遷移先を itemList.jsp に設定する。
    	rd = request.getRequestDispatcher("/itemList.jsp");
    	//10.rd のforwardメソッドを使用して、ページ遷移を実行する。
    	rd.forward(request, response);

    }

}
