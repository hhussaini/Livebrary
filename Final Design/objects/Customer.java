public class Customer extends User {

	private int libraryId;
	private Library localLibrary;
	private String barcode;
	private Map<String, Integer> reviewedItems;
	private List<Item> currentItems;
	private List<Item> wishList;
	private List<Item> ownedItems;

	/**
	 * 
	 * @param isbn
	 */
	public void addToWishlist(String isbn) {
		// TODO - implement Customer.addToWishlist
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param isbn
	 */
	public void removeFromWishList(String isbn) {
		// TODO - implement Customer.removeFromWishList
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public void addFavorite(Item item) {
		// TODO - implement Customer.addFavorite
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public boolean isFavorited(Item item) {
		// TODO - implement Customer.isFavorited
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public void updateItemsOwned(Item item) {
		// TODO - implement Customer.updateItemsOwned
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public void addToCheckedOut(Item item) {
		// TODO - implement Customer.addToCheckedOut
		throw new UnsupportedOperationException();
	}

}