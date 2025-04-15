package project;

import java.util.ArrayList;
import java.util.List;

public class WishManager {
	private List<Wish> wishList;

	public WishManager() {
		this.wishList = new ArrayList<>();
	}

	// Add a wish
	public void addWish(Wish wish) {
		wishList.add(wish);
	}

	// Approve or reject a wish
	public void wishChecked(int wishId, String status, int levelRequirement) {
		for (Wish wish : wishList) {
			if (wish.getWishId() == wishId) {
				if (status.equals("APPROVED")) {
					wish.approveWish(levelRequirement);
				} else if (status.equals("REJECTED")) {
					wish.rejectWish();
				}
				break;
			}
		}
	}

	// List all wishes
	public void listAllWishes() {
		for (Wish wish : wishList) {
			System.out.println(wish);
		}
	}
}
