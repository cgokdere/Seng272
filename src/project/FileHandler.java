package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FileHandler {
	private TaskManager taskManager;
	private WishManager wishManager;
	private Child child;

	public FileHandler(TaskManager taskManager, WishManager wishManager, Child child) {
		this.taskManager = taskManager;
		this.wishManager = wishManager;
		this.child = child;
	}

	// Read and process commands from Commands.txt
	public void readCommands(String filename) {
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine()) {
				String command = scanner.nextLine().trim();
				processCommand(command);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Commands.txt file not found!");
		}
	}

	// Process each command
	private void processCommand(String command) {
		String[] parts = command.split(" ");
		String action = parts[0];

		switch (action) {
		case "ADD_TASK1":
			addTask1(parts);
			break;
		case "ADD_TASK2":
			addTask2(parts);
			break;
		case "LIST_ALL_TASKS":
			listAllTasks(parts);
			break;
		case "TASK_DONE":
			taskDone(parts);
			break;
		case "TASK_CHECKED":
			taskChecked(parts);
			break;
		case "ADD_WISH1":
			addWish1(parts);
			break;
		case "ADD_WISH2":
			addWish2(parts);
			break;
		case "WISH_CHECKED":
			wishChecked(parts);
			break;
		case "PRINT_BUDGET":
			child.printBudget();
			break;
		case "PRINT_STATUS":
			child.printStatus();
			break;
		default:
			System.out.println("Invalid command: " + command);
		}
	}

	// Process ADD_TASK1 command
	private void addTask1(String[] parts) {
		String assignedBy = parts[1]; // "T" (Teacher) or "F" (Parent)
		int taskId = Integer.parseInt(parts[2]);
		String title = parts[3].replace("\"", ""); // Remove quotes
		String description = parts[4].replace("\"", ""); // Remove quotes
		String dueDate = parts[5]; // yyyy-MM-dd format
		String dueTime = parts[6]; // HH:mm format
		int points = Integer.parseInt(parts[7]);

		// Combine date and time
		LocalDateTime dueDateTime = LocalDateTime.parse(dueDate + "T" + dueTime);

		// Create Task1 and add to TaskManager
		Task task = new Task(taskId, title, description, dueDateTime, points, assignedBy);
		taskManager.addTask(task);
	}

	// Process ADD_TASK2 command
	private void addTask2(String[] parts) {
		String assignedBy = parts[1]; // "T" (Teacher) or "F" (Parent)
		int taskId = Integer.parseInt(parts[2]);
		String title = parts[3].replace("\"", ""); // Remove quotes
		String description = parts[4].replace("\"", ""); // Remove quotes
		String dueDate = parts[5]; // yyyy-MM-dd format
		String dueTime = parts[6]; // HH:mm format
		String activityStartDate = parts[7]; // yyyy-MM-dd format
		String activityStartTime = parts[8]; // HH:mm format
		String activityEndDate = parts[9]; // yyyy-MM-dd format
		String activityEndTime = parts[10]; // HH:mm format
		int points = Integer.parseInt(parts[11]);

		// Combine date and time
		LocalDateTime dueDateTime = LocalDateTime.parse(dueDate + "T" + dueTime);
		LocalDateTime activityStartDateTime = LocalDateTime.parse(activityStartDate + "T" + activityStartTime);
		LocalDateTime activityEndDateTime = LocalDateTime.parse(activityEndDate + "T" + activityEndTime);

		// Create Task2 and add to TaskManager
		Task task = new Task(taskId, title, description, dueDateTime, points, assignedBy, activityStartDateTime,
				activityEndDateTime);
		taskManager.addTask(task);
	}

	// Process LIST_ALL_TASKS command
	private void listAllTasks(String[] parts) {
		String filter = parts.length > 1 ? parts[1] : "";
		taskManager.listAllTasks(filter);
	}

	// Process TASK_DONE command
	private void taskDone(String[] parts) {
		int taskId = Integer.parseInt(parts[1]);
		taskManager.taskDone(taskId, child);
	}

	// Process TASK_CHECKED command
	private void taskChecked(String[] parts) {
		int taskId = Integer.parseInt(parts[1]);
		int rating = Integer.parseInt(parts[2]);
		taskManager.taskChecked(taskId, rating, child);
	}

	// Process ADD_WISH1 command
	private void addWish1(String[] parts) {
		int wishId = Integer.parseInt(parts[1]);
		String title = parts[2].replace("\"", ""); // Remove quotes
		String description = parts[3].replace("\"", ""); // Remove quotes

		// Create Wish1 and add to WishManager
		Wish wish = new Wish(wishId, title, description);
		wishManager.addWish(wish);
	}

	// Process ADD_WISH2 command
	private void addWish2(String[] parts) {
		int wishId = Integer.parseInt(parts[1]);
		String title = parts[2].replace("\"", ""); // Remove quotes
		String description = parts[3].replace("\"", ""); // Remove quotes
		String activityDate = parts[4]; // yyyy-MM-dd format
		String activityTime = parts[5]; // HH:mm format

		// Combine date and time
		LocalDateTime activityDateTime = LocalDateTime.parse(activityDate + "T" + activityTime);

		// Create Wish2 and add to WishManager
		Wish wish = new Wish(wishId, title, description, activityDateTime);
		wishManager.addWish(wish);
	}

	// Process WISH_CHECKED command
	private void wishChecked(String[] parts) {
		int wishId = Integer.parseInt(parts[1]);
		String status = parts[2];
		int levelRequirement = status.equals("APPROVED") ? Integer.parseInt(parts[3]) : 0;

		wishManager.wishChecked(wishId, status, levelRequirement);
	}
}

public class Main {
	public static void main(String[] args) {
		// Create manager classes and child
		TaskManager taskManager = new TaskManager();
		WishManager wishManager = new WishManager();
		Child child = new Child("Ali");

		// Create file handler and process commands
		FileHandler fileHandler = new FileHandler(taskManager, wishManager, child);
		fileHandler.readCommands("Commands.txt");
	}
}
