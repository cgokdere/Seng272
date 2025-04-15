package project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
	private List<Task> taskList;

	public TaskManager() {
		this.taskList = new ArrayList<>();
	}

	// Add a task
	public void addTask(Task task) {
		taskList.add(task);
	}

	// List all tasks (with optional filter: daily or weekly)
	public void listAllTasks(String filter) {
		LocalDateTime now = LocalDateTime.now();
		for (Task task : taskList) {
			if (filter.equals("D") && task.getDueDateTime().toLocalDate().equals(now.toLocalDate())) {
				System.out.println(task);
			} else if (filter.equals("W")
					&& task.getDueDateTime().toLocalDate().isBefore(now.plusWeeks(1).toLocalDate())) {
				System.out.println(task);
			} else if (filter.isEmpty()) {
				System.out.println(task);
			}
		}
	}

	// Mark a task as completed
	public void taskDone(int taskId, Child child) {
		for (Task task : taskList) {
			if (task.getTaskId() == taskId) {
				task.setCompleted(true);
				break;
			}
		}
	}

	// Approve a task and update the child's points
	public void taskChecked(int taskId, int rating, Child child) {
		for (Task task : taskList) {
			if (task.getTaskId() == taskId && task.isCompleted()) {
				task.setApproved(true);
				child.addPoints(task.getPoints() * rating); // Update points
				break;
			}
		}
	}
}
