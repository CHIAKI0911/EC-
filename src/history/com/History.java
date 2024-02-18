/**
 * 購入履歴検索処理
 */
package history.com;

import java.sql.SQLException;
import java.util.ArrayList;

public class History {
	/**
	 * 購入履歴を取得する(HistoryDaoクラスのgetHistoryQueryメソッドを実行)
	 * @param userId ユーザID
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<HistoryBean> getHistory(String userId) throws ClassNotFoundException , SQLException{

		//1. HistoryDaoクラスのインスタンスを生成し、ローカル変数historydaoへ格納する
		HistoryDao historydao = new HistoryDao();
		//2. ArrayList<HistoryBean>型のローカル変数hbByListを宣言し、nullを代入する。(戻り値用の変数を宣言する。)
		ArrayList<HistoryBean> hbByList = null;
		//3. historydaoのgetHistoryQueryメソッドを実行し、結果をhbByListへ格納する。getHistoryQueryメソッドの引数:userId
		hbByList = historydao.getHistoryQuery(userId);
		//4. hbByListを戻り値として返す。
		return hbByList;

	}


}
