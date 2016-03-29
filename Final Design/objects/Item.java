public class Item {

	private String title;
	private String description;
	private String isbn;
	private Double avgRating;
	private String downLoadLink;
	private License license;
	private Date datePublished;
	private Author author;
	private Image image;
	private Publisher publisher;
	private List<Genre> genre;
	private Map<User, Review> reviews;
	private List<User> usersCheckedOut;

	public String toString() {
		// TODO - implement Item.toString
		throw new UnsupportedOperationException();
	}

	public int getNumCheckedOut() {
		// TODO - implement Item.getNumCheckedOut
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 * @param review
	 */
	public void addReview(User user, Review review) {
		// TODO - implement Item.addReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 * @param review
	 */
	public void editReview(User user, Review review) {
		// TODO - implement Item.editReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param review
	 */
	public void removeReview(Review review) {
		// TODO - implement Item.removeReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param rating
	 */
	public void removeRating(Rating rating) {
		// TODO - implement Item.removeRating
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 */
	public boolean hasReview(User user) {
		// TODO - implement Item.hasReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param isbn
	 */
	public void redirectToSocialMediaSite(String isbn) {
		// TODO - implement Item.redirectToSocialMediaSite
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public void updateItemsBorrowed(Item item) {
		// TODO - implement Item.updateItemsBorrowed
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userReserveList
	 */
	public void addItemToReserveList(List userReserveList) {
		// TODO - implement Item.addItemToReserveList
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userReserveList
	 */
	public void updateReserveList(List userReserveList) {
		// TODO - implement Item.updateReserveList
		throw new UnsupportedOperationException();
	}

}