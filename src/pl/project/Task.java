package pl.project;

public class Task {

	private int taskId;
	private String taskName;
	private String taskDescription;
	private String startTimeDate;
	private String endTimeDate;

	public Task(int taskId, String taskName, String taskDescription,
			String startTimeDate, String endTimeDate) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.startTimeDate = startTimeDate;
		this.endTimeDate = endTimeDate;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName
				+ ", taskDescription=" + taskDescription + ", startTimeDate="
				+ startTimeDate + ", endTimeDate=" + endTimeDate + "]";
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getStartTimeDate() {
		return startTimeDate;
	}

	public void setStartTimeDate(String startTimeDate) {
		this.startTimeDate = startTimeDate;
	}

	public String getEndTimeDate() {
		return endTimeDate;
	}

	public void setEndTimeDate(String endTimeDate) {
		this.endTimeDate = endTimeDate;
	}

}
