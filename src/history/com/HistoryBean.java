/**
 * 購入履歴情報を格納
 */
package history.com;

import java.io.Serializable;

public class HistoryBean implements Serializable{

	//メンバ変数(フィールド変数)
	private static final long serialVersionUID = 1L;	//シリアライズ
	private String itemId;								//商品ID
	private String itemName;							//商品名
	private int		itemByQuantity;						//購入数

	//コンストラクタ
	public HistoryBean() {
		super();
		this.itemId = "";
		this.itemName = "";
		this.itemByQuantity = 0;
	}

	//メソッド
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemByQuantity() {
		return itemByQuantity;
	}

	public void setItemByQuantity(int itemByQuantity) {
		this.itemByQuantity = itemByQuantity;
	}



}
