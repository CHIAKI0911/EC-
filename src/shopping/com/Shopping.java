package shopping.com;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 商品検索処理
 * @version 1.0
 * @author fukushima
 *
 */
public class Shopping {


	/**
	 * 商品一覧を取得する(ShoppingDaoクラスのselectItemメソッドを実行)
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<ItemBean> getItem() throws ClassNotFoundException, SQLException{

		//1. ShoppingDaoクラスのインスタンスを生成し、ローカル変数sdaoへ格納する
		ShoppingDao sdao = new ShoppingDao();

		//2. ArrayList<ItemBean>型のローカル変数itemListを宣言し、nullを代入する。(戻り値用の変数を宣言する。)
		ArrayList<ItemBean> itemList = null;

		//3. sdaoのselectItemメソッドを実行し、結果をitemListへ格納する。
		itemList = sdao.selectItem();

		//4. itemListを戻り値として返す。
		return itemList;

	}

	/**
	 * 商品一覧を取得する(ShoppingDaoクラスのselectItemメソッド(引数あり)を実行)
	 * @param itemId 商品ID
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ItemBean getItem(String itemId) throws ClassNotFoundException, SQLException {
		//1. ShoppingDaoクラスのインスタンスを生成し、ローカル変数sdaoへ格納する
		ShoppingDao sdao = new ShoppingDao();
		//2. ItemBean型のbeanを宣言し、nullを代入する。(戻り値用の変数を用意する。)
		ItemBean bean = null;
		//3. sdaoのselectItemメソッドを実行し、結果をbeanへ格納する。
		     // selectItemメソッドの引数:itemId
		bean = sdao.selectItem(itemId);
		//4. beanを戻り値として返す。
		return bean;

	}


	/**
	 * カートに商品を追加する
	 * @param item 商品情報
	 * @param quantity 購入数量
	 * @param cartList カートリスト
	 */
	public void addToCart(ItemBean item, int quantity, ArrayList<CartBean> cartList) {
	    // カートに追加する処理を実装
	    // 例えば、CartBean クラスを作成して、それを cartList に追加するといった形になります
	    CartBean cartItem = new CartBean();
	    cartItem.setItemId(item.getItemId());
	    cartItem.setItemName(item.getItemName());
	    cartItem.setItemPrice(item.getItemPrice());
	    cartItem.setItemQuantity(item.getItemQuantity());
	    cartItem.setBuyItemCount(quantity);

	    cartList.add(cartItem);
	}


}
