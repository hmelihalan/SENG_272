# **Users**:  
	Child - Parent - Teacher

# **Functional Requirements:

	   Task Management
		Adding - viewing - completeng - approving tasks
		
		Tasks can have deadlines (only deadline or specified time range)
		
		Tasks can be filtered on daily or weekly basis
		
		Only deadlined tasks sshould appear on child's calendar
		
		Tasks truly completes when parent or teacher approves it then child gets 
		the points
		
		After tasks finished teacher parent gives rating(1-5) for leveling
		
	
	  Wish Management:
		 Wish could be activity or product after requsted it should pend
		 
		 If it's an activity then should include date and time details
		 
		 Parent approves wish and sets required level
		 
	 
	   Points and Level System
		   Child earns point for each task
		   
		   An average point earned for each task calculated then level determines
		   
		   0–40 average points: Level 1  
		   40–60 average points: Level 2  
		   60–80 average points: Level 3 
		   80+ average points: Level 4

# **File management**:

	Holding data via text files:
		
		Commands.txt - -Wishes.txt - Tasks.txt

# **Commands:

	ADD_TASK:
		-Adding a task by a parent or teacher.
		
		Example Usage:
			ADD_TASK1 T 101 "Math Homework" "Solve pages 10 to 20" 2025-03-01 
			15:00 10
		
		-asiignedBy(T),ID(101),title(Math Homework),description(solve 10 -      
		20),deadline(March 1 2025 15:00) or time range.
		
		-(Char) assingedBy : T , P
		-(Int)  ID 
		-(String) Title
		-(String) Description
		-(?)Deadline / Date
		-(Int) Task points
		
		 Note:if activity requires specific time include these parameters
		 [start_date, end_date, start_time, end_time]
		
	LIST_ALL_TASKS:
		
		-Listing all tasks in the system or child can filter them.
		
		Example Usage:
			LIST_ALL_TASKS
			LIST_ALL_TASKS D (daily)
			LIST_ALL_TASKS W (weekly)
		
	LIST_ALL_WISHES:
		
		-Listing all wishes in the system (approved, pending, or rejected).
		
		Example Usage:
			LIST_ALL_WISHES
			
			
	TASK_DONE:
		
		-The child indicates that a specific task is completed.
		
		Example Usage:
			TASK_DONE 101
			
		
		Note:Task have to pend to approval of teacher or parent
		
		
	TASK_CHECKED:
		
		-Parent or teacher approves task and give rating between 1-5
		
		Example Usage:
			TASK_CHECKED 101 5
			
		
		-(Int) Rating
		
		Note:Rating between 1-5 adds up to the tasks total score
		
		
	ADD_WISH:
		-The child submits a new wish.(product or activity)
		
		Example Usage:
			ADD_WISH1 W102 "Lego Set" "Price:150TL, Brand:LEGO"
			ADD_WISH2 W103 "Go to the Cinema" "Price:100TL" 2025-03-07 14:00 
			2025-03-07 16:00
		
		-(Int) Wish_ID
		-(String) Wish_Title
		-(String) Description
		-(?) Date/Time
		
		
	ADD_BUDGET_COIN:
		
		-Parent or teacher adds extra points/coins to the child’s account.
		
		Example Usage:
			 ADD_BUDGET_COIN 50
			 
		
		-(Int) Child-Id
		-(Int) Task Points
		
	WISH_CHECKED:
		
		The parent approves or rejects the child’s wishes
		
		Example Usage:
			WISH_CHECKED W102 APPROVED 3
			WISH_CHECKED W103 REJECTED
			
		
		-(Int) Wish_ID
		-(Boolean) Approval_Stat
		-(Int) Activates at level
		
		
	PRINT_BUDGET:
		
		-Prints the child’s current level or overall performance status.
		
		Example Usage: 
			PRINT_BUDGET
			
			
	PRINT_STATUS:
		
		-Prints the child’s current level or overall performance status.
		
		Example Usage: 
			PRINT_STATUS
			
			
# **Additional Notes:

	Parameter Format: 
		Text fields should be enclosed in quotes ("...") in the input
		yyyy-MM-dd for dates and HH:mm for times.
	
	Data Persistence:
		Task and wish data should be read from and written to a file so that the 
		application maintains state across sessions.
