package project;

import java.util.ArrayList;
import java.util.List;

public class Child {
	private String name;
	private int points;
	private int level;
	private List<Task> taskList;
	private List<Wish> wishList;

	public Child(String name) {
		this.name = name;
		this.points = 0;
		this.level = 1;
		this.taskList = new ArrayList<>();
		this.wishList = new ArrayList<>();
	}

	// Add points to the child
	public void addPoints(int points) {
		this.points += points;
		updateLevel(); // Update level based on new points
	}

	// Update the child's level based on points
	private void updateLevel() {
		if (points >= 80) {
			level = 4;
		} else if (points >= 60) {
			level = 3;
		} else if (points >= 40) {
			level = 2;
		} else {
			level = 1;
		}
	}

	// Print the child's current points
	public void printBudget() {
		System.out.println("Current Points: " + points);
	}

	// Print the child's current level
	public void printStatus() {
		System.out.println("Current Level: " + level);
	}

	// Getters
	public List<Task> getTaskList() {
		return taskList;
	}

	public List<Wish> getWishList() {
		return wishList;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public int getLevel() {
		return level;
	}
}
