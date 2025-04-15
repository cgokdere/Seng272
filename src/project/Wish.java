package project;

import java.time.LocalDateTime;

public class Wish {
<<<<<<< HEAD
	private String Wish_id;
	private String title;
	private String description;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String status; // pending, approved,rejected
	private int requiredLevel;

	// Constructor for wish1(product)
	public Wish(String Wish_id, String title, String description) {
		this.Wish_id = Wish_id;
		this.title = title;
		this.description = description;
		this.status = "Pending";
	}

	// Constructor for wish2(activity)
	public Wish(String Wish_ID, String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
		this.Wish_id = Wish_ID;
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = "Pending";
	}

	public String getWishId() {
		return Wish_id;
=======
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
>>>>>>> 09f170eb2bdd3c2d694a876b3a96ff9445c132d3
	}

	public String getStatus() {
		return status;
	}

<<<<<<< HEAD
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public void approveWish(int requiredLevel) {
		this.status = "Approved";
		this.requiredLevel = requiredLevel;
=======
	public void approveWish(int levelRequirement) {
		this.status = "Approved";
		this.levelRequirement = levelRequirement;
>>>>>>> 09f170eb2bdd3c2d694a876b3a96ff9445c132d3
	}

	public void rejectWish() {
		this.status = "Rejected";
	}

<<<<<<< HEAD
	public String getWishDetails() {
	String idStr = String.format("ID:%-5s", Wish_id);
    String titleStr = String.format("Title:%-25s", title);
    String descStr = String.format("Desc:%-30s", description);
    String statusStr = String.format("Status:%-10s", status);
    String levelStr = String.format("Level:%-2d", requiredLevel);

    if (startTime != null) {
        String startStr = String.format("Start:%-16s", 
            startTime.toLocalDate() + " " + startTime.toLocalTime());
        String endStr = String.format("End:%-16s", 
            endTime.toLocalDate() + " " + endTime.toLocalTime());
        
        return String.format("%s | %s | %s | %s | %s | %s | %s", 
            idStr, titleStr, descStr, statusStr, levelStr, startStr, endStr);
    } else {
        return String.format("%s | %s | %s | %s | %s", 
            idStr, titleStr, descStr, statusStr, levelStr);
    }
	}
=======
	@Override
	public String toString() {
		return "Wish ID: " + wishId + ", Title: " + title + ", Status: " + status
				+ (activityDateTime != null ? ", Activity Date: " + activityDateTime : "");
	}

>>>>>>> 09f170eb2bdd3c2d694a876b3a96ff9445c132d3
}
