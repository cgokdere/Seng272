package project;

import java.time.LocalDateTime;

public class Task {
	private int taskId;
	private String title;
	private String description;
	private LocalDateTime dueDateTime;
	private int points;
	private String assignedBy; // "T" for Teacher, "F" for Parent
	private boolean isCompleted; // Tracks if the task is completed
	private boolean isApproved; // Tracks if the task is approved
	private LocalDateTime activityStartTime; // For Task2 (activity start time)
	private LocalDateTime activityEndTime; // For Task2 (activity end time)

	// Constructor for Task1 (no activity time)
	public Task(int taskId, String title, String description, LocalDateTime dueDateTime, int points,
			String assignedBy) {
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.dueDateTime = dueDateTime;
		this.points = points;
		this.assignedBy = assignedBy;
		this.isCompleted = false; // Default: not completed
		this.isApproved = false; // Default: not approved
	}

	// Constructor for Task2 (with activity time)
	public Task(int taskId, String title, String description, LocalDateTime dueDateTime, int points, String assignedBy,
			LocalDateTime activityStartTime, LocalDateTime activityEndTime) {
		this(taskId, title, description, dueDateTime, points, assignedBy);
		this.activityStartTime = activityStartTime;
		this.activityEndTime = activityEndTime;
	}

	// Getters and Setters
	public int getTaskId() {
		return taskId;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean completed) {
		isCompleted = completed;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean approved) {
		isApproved = approved;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return "Task ID: " + taskId + ", Title: " + title + ", Due: " + dueDateTime + ", Points: " + points
				+ ", Assigned By: " + assignedBy + ", Completed: " + isCompleted + ", Approved: " + isApproved;
	}
}
