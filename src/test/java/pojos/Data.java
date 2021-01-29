package pojos;

public class Data {

	private Integer id;
	private String employee_name;
	private Integer employee_salary;
	private Integer employee_age;
	private String profile_image;

	public Data() {
	}

	public Data(Integer id, String employeeName, Integer employeeSalary, Integer employeeAge, String profileImage) {
		this.id = id;
		this.employee_name = employeeName;
		this.employee_salary = employeeSalary;
		this.employee_age = employeeAge;
		this.profile_image = profileImage;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employee_name;
	}
	public void setEmployeeName(String employeeName) {
		this.employee_name = employeeName;
	}
	public Integer getEmployeeSalary() {
		return employee_salary;
	}
	public void setEmployeeSalary(Integer employeeSalary) {
		this.employee_salary = employeeSalary;
	}
	public Integer getEmployeeAge() {
		return employee_age;
	}
	public void setEmployeeAge(Integer employeeAge) {
		this.employee_age = employeeAge;
	}
	public String getProfileImage() {
		return profile_image;
	}
	public void setProfileImage(String profileImage) {
		this.profile_image = profileImage;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", employeeName=" + employee_name + ", employeeSalary=" + employee_salary
				+ ", employeeAge=" + employee_age + ", profileImage=" + profile_image + "]";
	}

}
