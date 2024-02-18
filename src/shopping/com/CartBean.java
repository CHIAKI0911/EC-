package shopping.com;
/**
 * カートデータを格納するクラス
 * @version 1.0
 * @author fukushima
 *
 */
public class CartBean extends ItemBean{

	private int buyItemCount;	//購入数

	/**
	 * コンストラクタ
	 */
	public CartBean() {
		super();
		this.buyItemCount = 0;
	}

	/**
	 * コンストラクタ
	 * @param itemId		商品ID
	 * @param itemName		商品名
	 * @param itemPrice		価格
	 * @param itemQuantity	在庫数
	 * @param buyItemCount	購入数
	 */
	public CartBean(String itemId, String itemName, int itemPrice, int itemQuantity, int buyItemCount) {
		super(itemId, itemName, itemPrice, itemQuantity);
		this.buyItemCount = buyItemCount;
	}

	/**
	 * アクセサメソッド(getterメソッド)
	 * @return 購入数
	 */
	public int getBuyItemCount() {
		return buyItemCount;
	}
	/**
	 * アクセサメソッド(setterメソッド)
	 * @param buyItemCount 購入数
	 */
	public void setBuyItemCount(int buyItemCount) {
		this.buyItemCount = buyItemCount;
	}
}
