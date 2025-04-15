package project;

<<<<<<< HEAD
import java.util.List;
import java.util.ArrayList;

public class Child {
	private int level;
	private int points;
	private List<Task> taskList;
	private List<Wish> wishList;

	public Child() {
		this.level = 1;
		this.points = 0;
		this.taskList = new ArrayList<>();
		this.wishList = new ArrayList<>();
	}
	public void addPoints(int points) {
	    if (points > 0) {
	        this.points += points;
	        updateLevel();
	        System.out.println("\n=== POINTS&LEVEL ===");
	        System.out.println("- Points added: " + points);
        System.out.println("- Total points: " + this.points);
	    }
	}

	private void updateLevel() {
	    int newLevel;
	    if (points >= 80) {
	        newLevel = 4;
	    } else if (points >= 60) {
	        newLevel = 3;
	    } else if (points >= 40) {
	        newLevel = 2;
	    } else {
	        newLevel = 1;
	    }
	    
	    if (newLevel != level) {
	        System.out.println("Level up! New level: " + newLevel);
	        level = newLevel;
	    }
	}

	
	public void printBudget() {
		 System.out.println("\n=== BUDGET SUMMARY ===");
		System.out.println("Current Points: " + points);
	}

	public void printStatus() {
		System.out.println("\n=== CHILD STATUS ===");
		System.out.println("Current Status:");
		System.out.println("Level: " + level);
		System.out.println("Points: " + points);
	}


=======
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
>>>>>>> 09f170eb2bdd3c2d694a876b3a96ff9445c132d3
	public List<Task> getTaskList() {
		return taskList;
	}

	public List<Wish> getWishList() {
		return wishList;
	}
<<<<<<< HEAD
	public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }
=======

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public int getLevel() {
		return level;
	}
>>>>>>> 09f170eb2bdd3c2d694a876b3a96ff9445c132d3
}
