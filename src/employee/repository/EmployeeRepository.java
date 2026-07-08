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
		for (Employee emp : employees) {
			if (emp.getEmployeeId().equals(employeeId)) {
				return Optional.of(emp);
			}
		}
		return Optional.empty();
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
		for (Employee emp : employees) {
			if (emp.getEmployeeId().equals(employeeId)) {
				return true;
			}
		}
		return false;
	}

	// 部署名で社員一覧を取得する（退職者を含む）
	public List<Employee> findByDepartment(String department) {
		List<Employee> departmentList = new ArrayList<>();
		for (Employee emp : employees) {
			if (emp.getDepartment().equals(department)) {
				departmentList.add(emp);
			}
		}
		return departmentList;
	}

	// 部署名で社員一覧を取得する（退職者を除く）
	public List<Employee> findByDepartmentActive(String department) {
		List<Employee> activeDepartmentList = new ArrayList<>();
		for (Employee emp : employees) {
			if (emp.getDepartment().equals(department)) {
				activeDepartmentList.add(emp);
			}
		}
		return activeDepartmentList.stream().filter(emp -> !emp.isRetired()).toList();
	}

}
