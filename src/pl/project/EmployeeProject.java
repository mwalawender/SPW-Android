package pl.project;

public class EmployeeProject {

	@Override
	public String toString() {
		return "EmployeeProject [projectID=" + projectID + ", projectName="
				+ projectName + ", projectDesc=" + projectDesc
				+ ", projectStartD=" + projectStartD + ", projectEndD="
				+ projectEndD + ", workerEmail=" + workerEmail
				+ ", workerName=" + workerName + ", workerSurname="
				+ workerSurname + "]";
	}

	public EmployeeProject(String projectID, String projectName,
			String projectDesc, String projectStartD, String projectEndD,
			String workerEmail, String workerName, String workerSurname) {

		this.projectID = projectID;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.projectStartD = projectStartD;
		this.projectEndD = projectEndD;
		this.workerEmail = workerEmail;
		this.workerName = workerName;
		this.workerSurname = workerSurname;
	}

	String projectID;
	String projectName;
	String projectDesc;
	String projectStartD;
	String projectEndD;
	String workerEmail;
	String workerName;
	String workerSurname;

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectStartD() {
		return projectStartD;
	}

	public void setProjectStartD(String projectStartD) {
		this.projectStartD = projectStartD;
	}

	public String getProjectEndD() {
		return projectEndD;
	}

	public void setProjectEndD(String projectEndD) {
		this.projectEndD = projectEndD;
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
