/**
 * 購入処理用DB処理
 */
package history.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import setting.com.DbSetting;

public class HistoryDao {

	//メンバ変数
	private Connection 			con 	= null;		//DB接続用
	private PreparedStatement 	ps 		= null;		//SQL実行用
	private ResultSet			rs		= null;		//SQL実行結果用


	public ArrayList<HistoryBean> getHistoryQuery(String userId) throws ClassNotFoundException , SQLException{

		//1.ArrayListクラスのインスタンスを生成し、ローカル変数hbListへ代入する。
		ArrayList<HistoryBean> hbList = new ArrayList<>();
		//2.ClassクラスのforNameメソッドを使用し、JDBCドライバをロードする。
		Class.forName("com.mysql.jdbc.Driver");
		//3.DriverManagerクラスのgetConnectionメソッドを使用し、Connectionオブジェクトを生成し、メンバ変数conへ代入する。
		con = DriverManager.getConnection(DbSetting.URL,DbSetting.USER,DbSetting.PASS);
		//4.conのprepareStatementメソッドを利用し、SQL文を生成する。そしてメンバ変数psへ代入する。
		ps = con.prepareStatement("select a.item_id, b.item_name, a.quantity from history_info a , item_info b where a.id = ? and a.item_id = b.item_id");
		//5.psのsetStringメソッドを利用してSQL文の「？」の部分にユーザIDを設定する。
		ps.setString(1, userId);
		//6.psのexecuteQueryメソッドを使用し、SQLを実行する。実行結果をメンバ変数rsへ代入する。
		rs = ps.executeQuery();
		//7.rsから実行結果を取り出す。（rs.next()を実行し、falseになるまで繰り返す。）
		while(rs.next()) {
			//7-1. HistoryBeanクラスのインスタンスを生成し、ローカル変数hbへ代入する。
			HistoryBean hb = new HistoryBean();
			//7-2. rsのgetStringメソッドを利用し、商品IDを取得する。そしてHistoryBeanオブジェクトのitemIdへ格納する。
			hb.setItemId(rs.getString("item_id"));
			//7-3. rsのgetStringメソッドを利用し、商品名を取得する。そしてHistoryBeanオブジェクトのitemNameへ格納する。
			hb.setItemName(rs.getString("item_name"));
			//7-4. rsのgetIntメソッドを利用し、商品購入数を取得する。そしてHistoryBeanオブジェクトのutemByQuantityへ格納する。
			hb.setItemByQuantity(rs.getInt("quantity"));
			//7-5. hbListのaddメソッドを利用し、hbを追加する。
			hbList.add(hb);

		}

		//8.データベースとの接続を解除する。（closeメソッドを実行させる。）
		con.close();
		ps.close();
		rs.close();

		//.9. 商品購入履歴リスト（hbList）を返す。
		return hbList;

	}

}
