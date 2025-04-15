package project;

import java.io.File;
import java.io.FileNotFoundException;
<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class FileHandler {
	private TaskManagement taskManagement = new TaskManagement();
	private WishManagement wishManagement = new WishManagement();
	private Child child = new Child();

	public void processCommands() {
		try (Scanner scanner = new Scanner(new File("Commands.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (line.isEmpty())
					continue;

				try {
					String[] parts = line.split("\\s+");
					String command = parts[0];

					switch (command) {
					case "ADD_TASK1":
						processAddTask1();
						break;
					case "ADD_TASK2":
						processAddTask2();
						break;
					case "LIST_ALL_TASKS":
						String filter = parts.length > 1 ? parts[1] : "";
						taskManagement.listAllTasks(filter);
						break;
					case "TASK_DONE":
					    if (parts.length > 1) {
					        taskManagement.taskDone(Integer.parseInt(parts[1]), child);
					    } else {
					        System.out.println("Missing task ID for TASK_DONE");
					    }
					    break;
					case "TASK_CHECKED":
					    if (parts.length > 2) {
					        taskManagement.taskChecked(
					            Integer.parseInt(parts[1]), 
					            Integer.parseInt(parts[2]), 
					            child
					        );
					    } else {
					        System.out.println("Missing parameters for TASK_CHECKED (need task ID and rating)");
					    }
					    break;
					
					case "ADD_WISH1":
						processAddWish1();
						break;
					case "ADD_WISH2":
						processAddWish2();
						break;
					case "LIST_ALL_WISHES":
						wishManagement.listAllWishes();
						break;
					case "ADD_BUDGET_COIN":
					    if (parts.length > 1) {
					        try {
					            int pointsToAdd = Integer.parseInt(parts[1]);
					            child.addPoints(pointsToAdd);
					        } catch (NumberFormatException e) {
					            System.out.println("Invalid points value: " + parts[1]);
					        }
					    } else {
					        System.out.println("Missing points value for ADD_BUDGET_COIN");
					    }
					    break;
					case "WISH_CHECKED":
						if (child.getWishList().isEmpty()) {
							System.out.println("No wishes available to check");
						} else {
							Wish firstWish = child.getWishList().get(0);
							wishManagement.wishChecked(firstWish.getWishId(), "APPROVED", 2);
						}
						break;
					case "PRINT_BUDGET":
						child.printBudget();
						break;
					case "PRINT_STATUS":
						child.printStatus();
						break;
					default:
						System.out.println("Unknown command: " + command);
					}
				} catch (Exception e) {
					System.out.println("Error processing: " + line);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Commands.txt not found");
		}
	}

	private void processAddTask1() {
        try (Scanner scanner = new Scanner(new File("Tasks.txt"))) {
            LocalDate today = LocalDate.now();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("ADD_TASK1")) {
                    String[] parts = line.split("\"");

                    String[] firstParts = parts[0].trim().split("\\s+");
                    if (firstParts.length < 3) continue;
                    
                    String assignedBy = firstParts[1];
                    int taskId = Integer.parseInt(firstParts[2]);
                    String title = parts[1];
                    String description = parts.length > 3 ? parts[3] : "";

                    String[] lastParts = parts[parts.length - 1].trim().split("\\s+");
                    if (lastParts.length < 3) continue;
                    
                    LocalDate date = LocalDate.parse(lastParts[0]);
                    LocalTime time = LocalTime.parse(lastParts[1]);
                    int points = Integer.parseInt(lastParts[2]);

                    if (date.isBefore(today)) {
                        System.out.printf("Warning: Task %d \"%s\" has a past date (%s)%n", 
                                taskId, title, date);
                    }

                    Task task = new Task(assignedBy, taskId, title, description, 
                            LocalDateTime.of(date, time), points);
                    taskManagement.addTask(task);
                    child.getTaskList().add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private void processAddTask2() {
        try (Scanner scanner = new Scanner(new File("Tasks.txt"))) {
            LocalDate currentDate = LocalDate.now();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("ADD_TASK2")) {
                    String[] parts = line.split("\"");

                    String[] firstParts = parts[0].trim().split("\\s+");
                    if (firstParts.length < 3) continue;
                    
                    String assignedBy = firstParts[1];
                    int taskId = Integer.parseInt(firstParts[2]);
                    String title = parts[1];
                    String description = parts.length > 3 ? parts[3] : "";

                    String[] lastParts = parts[parts.length - 1].trim().split("\\s+");
                    if (lastParts.length < 5) continue;
                    
                    LocalDate startDate = LocalDate.parse(lastParts[0]);
                    LocalTime startTime = LocalTime.parse(lastParts[1]);
                    LocalDate endDate = LocalDate.parse(lastParts[2]);
                    LocalTime endTime = LocalTime.parse(lastParts[3]);
                    int points = Integer.parseInt(lastParts[4]);

                    if (endDate.isBefore(currentDate)) {
                        System.out.printf("Warning: Task %d \"%s\" has already ended (%s)%n", 
                                taskId, title, endDate);
                    } else if (startDate.isBefore(currentDate)) {
                        System.out.printf("Note: Task %d \"%s\" has already started (%s)%n", 
                                taskId, title, startDate);
                    }

                    Task task = new Task(assignedBy, taskId, title, description, 
                            LocalDateTime.of(startDate, startTime),
                            LocalDateTime.of(endDate, endTime), points);
                    taskManagement.addTask(task);
                    child.getTaskList().add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private void processAddWish1() {
        try (Scanner scanner = new Scanner(new File("Wishes.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("ADD_WISH1")) {
                    String[] parts = line.split("\"");
                    if (parts.length < 3) continue;

                    String[] firstParts = parts[0].trim().split("\\s+");
                    if (firstParts.length < 2) continue;
                    
                    String wishId = firstParts[1];
                    String title = parts[1].trim();
                    String description = parts.length > 3 ? parts[3].trim() : "";

                    Wish wish = new Wish(wishId, title, description);
                    wishManagement.addWish(wish);
                    child.getWishList().add(wish);
                   // System.out.println("Added wish: " + title);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading wishes file: " + e.getMessage());
        }
    }

    private void processAddWish2() {
        try (Scanner scanner = new Scanner(new File("Wishes.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("ADD_WISH2")) {
                    String[] parts = line.split("\"");
                    if (parts.length < 3) continue;

                    String[] firstParts = parts[0].trim().split("\\s+");
                    if (firstParts.length < 2) continue;
                    
                    String wishId = firstParts[1];
                    String title = parts[1].trim();
                    String description = parts.length > 3 ? parts[3].trim() : "";

                    String[] lastParts = parts[parts.length - 1].trim().split("\\s+");
                    if (lastParts.length < 5) continue;
                    
                    LocalDate startDate = LocalDate.parse(lastParts[0]);
                    LocalTime startTime = LocalTime.parse(lastParts[1]);
                    LocalDate endDate = LocalDate.parse(lastParts[2]);
                    LocalTime endTime = LocalTime.parse(lastParts[3]);

                    Wish wish = new Wish(wishId, title, description, 
                            LocalDateTime.of(startDate, startTime),
                            LocalDateTime.of(endDate, endTime));
                    wishManagement.addWish(wish);
                    child.getWishList().add(wish);
                   // System.out.println("Added wish: " + title);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading wishes file: " + e.getMessage());
        }
    }
    
	public static void main(String[] args) {
		FileHandler fileHandler = new FileHandler();
		fileHandler.processCommands();
=======
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
>>>>>>> 09f170eb2bdd3c2d694a876b3a96ff9445c132d3
	}
}
