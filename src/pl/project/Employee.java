package pl.project;

public class Employee {

	private int employeeId;
	private String firstName;
	private String lastName;
	private String position;
	private String section;
	private String phoneNumber;
	private String email;
	private String password;

	public Employee(int mId, String mFirstName, String mLastName,
			String mPosition, String mSection,
			String mPhoneNumber, String mEmail, String mPassword) {

		this.employeeId = mId;
		this.firstName = mFirstName;
		this.lastName = mLastName;
		this.position = mPosition;
		this.section = mSection;
		this.email = mEmail;
		this.phoneNumber = mPhoneNumber;
		this.password = mPassword;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", position="
				+ position + ", section=" + section + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", password=" + password
				+ "]";
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int empoyeeId) {
		this.employeeId = empoyeeId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
