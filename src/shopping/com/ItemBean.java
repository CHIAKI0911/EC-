package shopping.com;

import java.io.Serializable;
/**
 * 商品データを格納するクラス
 * @version 1.0
 * @author fukushima
 *
 */
public class ItemBean implements Serializable{

	private	static final long serialVersionUID = 1L;	//シリアライズ
	private	String itemId;		//商品ID
	private String itemName;	//商品名
	private int itemPrice;		//価格
	private int itemQuantity;	//数量

	/**
	 * コンストラクタ
	 */
	public ItemBean() {
		this.itemId = "";
		this.itemName = "";
		this.itemPrice = 0;
		this.itemQuantity = 0;
	}


	/**
	 * コンストラクタ2
	 */
//	おそらくこのコンストラクタはカート機能で渡される引数を使う
	public ItemBean(String itemId, String itemName, int itemPrice, int itemQuantity) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
	}


	//アクセサメソッド
	public String getItemId() {
		return itemId;
	}

	//アクセサメソッド
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	//アクセサメソッド
	public String getItemName() {
		return itemName;
	}

	//アクセサメソッド
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	//アクセサメソッド
	public int getItemPrice() {
		return itemPrice;
	}

	//アクセサメソッド
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	//アクセサメソッド
	public int getItemQuantity() {
		return itemQuantity;
	}

	//アクセサメソッド
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}


}
