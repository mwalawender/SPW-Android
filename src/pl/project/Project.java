package pl.project;

public class Project {

	private int projectId;
	private String projectName;
	private String projectDescription;
	private String startTimeDate;
	private String endTimeDate;

	public Project(int mId, String projectName, String projectDescription,
			String startTimeDate, String endTimeDate) {

		this.projectId = mId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.startTimeDate = startTimeDate;
		this.endTimeDate = endTimeDate;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
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

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName="
				+ projectName + ", projectDescription=" + projectDescription
				+ ", startTimeDate=" + startTimeDate + ", endTimeDate="
				+ endTimeDate + "]";
	}

}
