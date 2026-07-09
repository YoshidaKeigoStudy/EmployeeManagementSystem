package employee.model;

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
		// バリデーションチェック
		// 年齢が0以上かどうか
		if (age < 0) {
			throw new IllegalArgumentException("年齢が0未満です。登録できません。");
		} else {
			this.age = age;
		}
		// 氏名が入力されているかどうか
		if (name.isBlank()) {
			throw new IllegalArgumentException("氏名が入力されていないため登録できません。");
		} else {
			this.name = name;
		}
		// 部署が入力されているかどうか
		if (department.isBlank()) {
			throw new IllegalArgumentException("部署が入力されていないため登録できません。");
		} else {
			this.department = department;
		}

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

	// プロパティの更新
	public void changeProperty(int age, String name, String department) {
		// バリデーションチェック
		// 年齢が0以上かどうか
		if (age < 0) {
			throw new IllegalArgumentException("年齢が0未満です。登録できません。");
		}
		// 氏名が入力されているかどうか
		if (name.isBlank()) {
			throw new IllegalArgumentException("氏名が入力されていないため登録できません。");
		}
		// 部署が入力されているかどうかのバリデーションチェック
		if (department.isBlank()) {
			throw new IllegalArgumentException("部署が入力されていないため登録できません。");
		}
		this.age = age;
		this.name = name;
		this.department = department;
	}

	// 退職フラグをオンにする
	public void retire() {
		this.retired = true;
	}

	@Override
	public String toString() {
		return "employeeId='" + employeeId + '\'' + ", name='" + name + '\'' + ", age=" + age + ", department='"
				+ department + '\'' + ", retired=" + retired;
	}
}
