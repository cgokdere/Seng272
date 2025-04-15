package project;

import java.time.LocalDateTime;

public class Wish {
	private int wishId;
	private String title;
	private String description;
	private String status; // "Pending", "Approved", "Rejected"
	private LocalDateTime activityDateTime; // For Wish2 (activity date and time)
	private int levelRequirement; // Level required to fulfill the wish

	// Constructor for Wish1 (product wish)
	public Wish(int wishId, String title, String description) {
		this.wishId = wishId;
		this.title = title;
		this.description = description;
		this.status = "Pending"; // Default: pending approval
		this.activityDateTime = null; // No activity time for product wishes
	}

	// Constructor for Wish2 (activity wish)
	public Wish(int wishId, String title, String description, LocalDateTime activityDateTime) {
		this(wishId, title, description);
		this.activityDateTime = activityDateTime; // Set activity time for activity wishes
	}

	// Getters and Setters
	public int getWishId() {
		return wishId;
	}

	public String getStatus() {
		return status;
	}

	public void approveWish(int levelRequirement) {
		this.status = "Approved";
		this.levelRequirement = levelRequirement;
	}

	public void rejectWish() {
		this.status = "Rejected";
	}

	@Override
	public String toString() {
		return "Wish ID: " + wishId + ", Title: " + title + ", Status: " + status
				+ (activityDateTime != null ? ", Activity Date: " + activityDateTime : "");
	}

}
