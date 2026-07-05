package employee;

/** 社員情報を保持するデータクラス **/
public class Employee {
	private String employeeId; // 社員ID
	private String name; // 氏名
	private int age; // 年齢
	private String department; // 部署
	private boolean retired = false; // 退職フラグ

	// コンストラクタ
	public Employee(String employeeId, String name, int age, String department) {
		this.employeeId = employeeId;
		this.name = name;
		this.age = age;
		this.department = department;
	}

	// getter
	public String getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getDepartment() {
		return department;
	}

	public boolean isRetired() {
		return retired;
	}

	// setter
	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setRetired(boolean retired) {
		this.retired = retired;
	}

	@Override
	public String toString() {
		return "employeeId='" + employeeId + '\'' + ", name='" + name + '\'' + ", age=" + age + ", department='"
				+ department + '\'' + ", retired=" + retired;
	}

}
