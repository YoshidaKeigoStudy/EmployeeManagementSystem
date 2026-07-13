package employee.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import employee.model.Employee;

/** 社員データの保管・取得 **/
public class EmployeeRepository {
	private final List<Employee> employees = new ArrayList<>();

	// リストに社員を追加するメソッド
	public void save(Employee employee) {
		employees.add(employee);
	}

	// IDが一致する社員情報を取得
	public Optional<Employee> findById(String employeeId) {
		// IDで社員を1件取得する
		return employees.stream().filter(emp -> emp.getEmployeeId().equals(employeeId)).findFirst();
	}

	// 全社員情報を取得する（退職者を含む）
	public List<Employee> findAll() {
		return employees;
	}

	// 全社員情報を取得する（退職者を除く）
	public List<Employee> findActiveEmployee() {
		List<Employee> activeEmployee = employees.stream().filter(emp -> !emp.isRetired()).toList();
		return activeEmployee;
	}

	// IDが存在するか確認する
	public boolean existsById(String employeeId) {
		return employees.stream().anyMatch(emp -> emp.getEmployeeId().equals(employeeId));
	}

	// 部署名で社員一覧を取得する（退職者を含む）
	public List<Employee> findByDepartment(String department) {
		return employees.stream().filter(emp -> emp.getDepartment().equals(department)).toList();
	}

	// 部署名で社員一覧を取得する（退職者を除く）
	public List<Employee> findByDepartmentActive(String department) {
		return employees.stream().filter(emp -> emp.getDepartment().equals(department)).filter(emp -> !emp.isRetired())
				.toList();
	}

}
