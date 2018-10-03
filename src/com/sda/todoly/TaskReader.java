package com.sda.todoly;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class is a part of "ToDoList" project.
 *
 * This TaskReader reads from the input file.
 */

public class TaskReader {

	private int totalNumberOfTasks;
	private int numberOfDoneTasks;


	public ArrayList<Task> loadTasks() throws FileNotFoundException {
		ArrayList<Task> tasksToShow = new ArrayList<>();

		File file = new File("tasks.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		Task currentTask;
		try {

			while ((line = bufferedReader.readLine()) != null) {

				String title = line;
				line = bufferedReader.readLine();
				LocalDate dueDate = LocalDate.parse(line);
				line = bufferedReader.readLine();
				String project = line;
				line = bufferedReader.readLine();
				boolean isDone = Boolean.valueOf(line);



				currentTask = new Task(title, dueDate, project, isDone);
				tasksToShow.add(currentTask);
			}
			fileReader.close();

		} catch (IOException ex) {
			System.err.println("Caught IOException: " + ex.getMessage());
		}
		return tasksToShow;
	}
}
