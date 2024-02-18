package shopping.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import setting.
com.DbSetting;

/**
 * 購入処理用DB処理
 * @version 1.0
 * @author yamamoto
 *
 */
public class ShoppingDao {

	private Connection 			con 	= null;		//DB接続用
	private PreparedStatement 	ps 		= null;		//SQL実行用
	private ResultSet			rs		= null;		//SQL実行結果用

	/**
	 * 全ての商品一覧を取得する
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<ItemBean> selectItem() throws ClassNotFoundException , SQLException{

		//1. ArrayListクラスのインスタンスを生成し、ローカル変数beanListへ代入する。
		ArrayList<ItemBean> beanList = new ArrayList<ItemBean>();

		//2. ClassクラスのforNameメソッドを使用し、JDBCドライバをロードする。
		Class.forName("com.mysql.jdbc.Driver");

		//3. DriverManagerクラスのgetConnectionメソッドを使用し、Connectionオブジェクトを生成し、メンバ変数conへ代入する。
		con = DriverManager.getConnection(DbSetting.URL,DbSetting.USER,DbSetting.PASS);

		//4.conのprepareStatementメソッドを利用し、SQL文を生成する。そしてメンバ変数psへ代入する。
		ps = con.prepareStatement("select b.item_id, b.item_name ,b.price,a.quantity from stock_info a, item_info b where a.item_id = b.item_id");

		//5. psのexecuteQueryメソッドを使用し、SQLを実行する。実行結果をメンバ変数rsへ代入する。
		rs = ps.executeQuery();

		//6. rsから実行結果を取り出す。（rs.next()を実行し、falseになるまで繰り返す。）
		//繰り返し条件：[rs.next()の実行結果がfalseになるまで ]
		while(rs.next()) {
			//--- rs.next()の実行結果がtrueの場合の処理 ---
			//6-1. ItemBeanクラスのインスタンスを生成し、ローカル変数beanへ代入する。
			ItemBean bean = new ItemBean();
			//6-2. rsのgetStringメソッドを利用し、商品IDを取得する。そしてItemBeanオブジェクトのitemIdへ格納する。
			bean.setItemId(rs.getString("item_id"));
			//6-3. rsのgetStringメソッドを利用し、商品名を取得する。そしてItemBeanオブジェクトのitemNameへ格納する。
			bean.setItemName(rs.getString("item_name"));
			//6-4. rsのgetIntメソッドを利用し、価格を取得する。そしてItemBeanオブジェクトのitemPriceへ格納する。
			bean.setItemPrice(rs.getInt("price"));
			//6-5. rsのgetIntメソッドを利用し、商品数を取得する。そしてItemBeanオブジェクトのitemQuantityへ格納する。
			bean.setItemQuantity(rs.getInt("quantity"));
			//6-6. beanListのaddメソッドを利用し、beanを追加する。
			beanList.add(bean);
		}



		return beanList;
	}

	/**
	 * 商品IDから商品情報を取得する
	 * @param itemId 商品ID
	 * @return       商品情報
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ItemBean selectItem(String itemId) throws ClassNotFoundException , SQLException {

		//1. ItemBeanクラスのインスタンスを生成し、ローカル変数beanへ代入する。
		ItemBean bean = new ItemBean();
		//2. ClassクラスのforNameメソッドを使用し、JDBCドライバをロードする。
		Class.forName("com.mysql.jdbc.Driver");
		//3. DriverManagerクラスのgetConnectionメソッドを使用し、Connectionオブジェクトを生成し、メンバ変数conへ代入する。
		con = DriverManager.getConnection(DbSetting.URL,DbSetting.USER,DbSetting.PASS);
		//4. conのprepareStatementメソッドを利用し、SQL文を生成する。そしてメンバ変数psへ代入する。
		ps = con.prepareStatement("select b.item_name, b.price ,a.quantity from stock_info a, item_info b where a.item_id = b.item_id and a.item_id = ?");
		//5. psのsetStringメソッドを利用してSQL文の「？」の部分に商品IDを設定する。
		ps.setString(1, itemId);
		//6. psのexecuteQueryメソッドを使用し、SQLを実行する。実行結果をメンバ変数rsへ代入する。
		rs = ps.executeQuery();
		//7. rsから実行結果を取り出す。（rs.next()を実行し、falseになるまで繰り返す。）
		//繰り返し条件：[rs.next()の実行結果がfalseになるまで ]
		while(rs.next()) {
			//7-1. beanのsetItemIdメソッドを利用して商品IDを設定する。
			bean.setItemId(itemId);
			//7-2. rsのgetStringメソッドを利用し、商品名を取得する。そしてItemBeanオブジェクトのitemNameへ格納する。
			bean.setItemName(rs.getString("item_name"));
			//7-3. rsのgetIntメソッドを利用し、価格を取得する。そしてItemBeanオブジェクトのitemPriceへ格納する。
			bean.setItemPrice(rs.getInt("price"));
			//7-4. rsのgetIntメソッドを利用し、商品数を取得する。そしてItemBeanオブジェクトのitemQuantityへ格納する。
			bean.setItemQuantity(rs.getInt("quantity"));

		}
		//8.データベースとの接続を解除する。（closeメソッドを実行させる。）
		con.close();
		ps.close();
		rs.close();

		//9.商品情報（bean）を返す。
		return bean;

	}

	/**
	 *
	 * @param itemId 商品ID
	 * @param itemQuantity 注文数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateItem(String itemId , int itemQuantity) throws ClassNotFoundException, SQLException {
		//1. ClassクラスのforNameメソッドを使用し、JDBCドライバをロードする。
		Class.forName("com.mysql.jdbc.Driver");

		//2. DriverManagerクラスのgetConnectionメソッドを使用し、Connectionオブジェクトを生成し、メンバ変数conへ代入する。
		con = DriverManager.getConnection(DbSetting.URL,DbSetting.USER,DbSetting.PASS);

		//3. conのprepareStatementメソッドを利用し、SQL文を生成する。そしてメンバ変数psへ代入する。
		ps = con.prepareStatement("update stock_info set quantity = quantity - ? where item_id = ?");

		//4. psのsetIntメソッドを利用して在庫数を設定する。
		ps.setInt(1, itemQuantity);

		//5. psのsetStringメソッドを利用して商品IDを設定する。
		ps.setString(2, itemId);

		//6. psのexecuteUpdateメソッドを使用し、SQLを実行する。
		ps.executeUpdate();

		//7. データベースとの接続を解除する。
		con.close();
		ps.close();

	}

	/**
	 * 購入履歴テーブルを更新
	 * @param userId ログインID
	 * @param itemId 商品ID
	 * @param itemQuantity 在庫数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateHistory(String userId , String itemId , int itemQuantity) throws ClassNotFoundException, SQLException {

		//1. ClassクラスのforNameメソッドを使用し、JDBCドライバをロードする。
		Class.forName("com.mysql.jdbc.Driver");
		//2. DriverManagerクラスのgetConnectionメソッドを使用し、Connectionオブジェクトを生成し、メンバ変数conへ代入する。
		con = DriverManager.getConnection(DbSetting.URL,DbSetting.USER,DbSetting.PASS);
		//3. conのprepareStatementメソッドを利用し、SQL文を生成する。そしてメンバ変数psへ代入する。
		ps = con.prepareStatement("insert into history_info (id, item_id, quantity) values (?, ?, ?)");
		//4. psのsetStringメソッドを利用してユーザIDを設定する。
		ps.setString(1, userId);
		//5. psのsetStringメソッドを利用して商品IDを設定する。
		ps.setString(2, itemId);
		//6. psのsetIntメソッドを利用して在庫数を設定する。
		ps.setInt(3, itemQuantity);
		//7. psのexecuteUpdateメソッドを使用し、SQLを実行する。
		ps.executeUpdate();
		//8. データベースとの接続を解除する。
		con.close();
		ps.close();

	}

}
