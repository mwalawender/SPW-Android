package pl.project;

public class EmployeeTask {

	String taskID;
	String taskName;
	String taskDesc;
	String taskStartD;
	String taskEndD;
	String workerEmail;
	String workerName;
	String workerSurname;
	
	@Override
	public String toString() {
		return "EmployeeTask [taskID=" + taskID + ", taskName=" + taskName
				+ ", taskDesc=" + taskDesc + ", taskStartD=" + taskStartD
				+ ", taskEndD=" + taskEndD + ", workerEmail=" + workerEmail
				+ ", workerName=" + workerName + ", workerSurname="
				+ workerSurname + "]";
	}

	public EmployeeTask(String taskID, String taskName, String taskDesc,
			String taskStartD, String taskEndD, String workerEmail,
			String workerName, String workerSurname) {

		this.taskID = taskID;
		this.taskName = taskName;
		this.taskDesc = taskDesc;
		this.taskStartD = taskStartD;
		this.taskEndD = taskEndD;
		this.workerEmail = workerEmail;
		this.workerName = workerName;
		this.workerSurname = workerSurname;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskStartD() {
		return taskStartD;
	}

	public void setTaskStartD(String taskStartD) {
		this.taskStartD = taskStartD;
	}

	public String getTaskEndD() {
		return taskEndD;
	}

	public void setTaskEndD(String taskEndD) {
		this.taskEndD = taskEndD;
	}

	public String getWorkerEmail() {
		return workerEmail;
	}

	public void setWorkerEmail(String workerEmail) {
		this.workerEmail = workerEmail;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerSurname() {
		return workerSurname;
	}

	public void setWorkerSurname(String workerSurname) {
		this.workerSurname = workerSurname;
	}

}
