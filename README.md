# IP-Project
An individual project to build a **todo list application**. 

## Application
The application has text-based user interface with terminal input and output.

The application allows the user to create new tasks, assign them a title and due date, and choose a project for that task to belong to.


The user should be able to also edit, mark as done or remove tasks while they are using the application.


The user also should be able to quit and save the current task list to file, and then
restart the application with the former state restored.

## User Manual
Open the program by running the main. The welcome message will be printed showing the number of tasks (done and undone) that are saved to file. The following main menu is shown after the welcome message.

Welcome to ToDoList
You have # tasks todo and # tasks are done!

Pick an option (Or 0 to go back to the main menu): 
  1) Show Task List (by date or project)
  2) Add New Task
  3) Edit Task (update, mark as done, remove
  4) Save and Quit


###1) Show Task List(by date or project): 

To print the task list enter 1, then the following submenu will appear:

Pick an option (Or 0 to go back to the main menu): 
  1) Show Task List by date
  2) Show Task List by project


Enter 1 to sort tasks by due date, 2 to filter tasks by the project name or 0 to go back to the main menu. 

####2) Add New Task

TO add a new task enter 2 from the main menue. Then you will be asked to enter the title, project, due date (in yyyy-mm-dd format) and task status (done/undone). A confirmation message "The task has been added to the task list successfully." will appear after entering all the requested fields.

###3) Edit Task (update, mark as done, remove)

To edit an existing task enter 3 from the main menu. Then the following submenu will be shown:

Pick an option (Or 0 to go back to the main menu): 
  1) Update task
  2) Mark as done
  3) Remove task


All the 3 options will print the task list sorted by date to make it easy for the user to pick a task to edit. You should choose a task by id from the task list shown. If you chose an id which does not exist in the list you will get the message "No task was found with this id." and you will be redirected to the main menu.

Enter 1 to update an existing task. Then you will be asked to chose a task by id from the task list shown. Enter the id of the task you want to update. Then you will be asked to enter the new title, project, due date and task status. After entering all the requiered fields the confirmation message will be shown "The task has been updated successfully.".

Enter 2 to mark a task "done". Again you will be asked to choose a task by id from the task list shown. Enter the id of the task you want to mark as done. After chosing a vali id you will get the confirmation message that "The task has been marked as done successfully.". 

Enter 3 to remove a task. If you choose a valid id you will get the confirmation message "The task has been removed successfully.".

###4) Save and Quit

Enter 4 to exit the program and save the your task list. No changes will be saved if you stop the program without entering 4.






