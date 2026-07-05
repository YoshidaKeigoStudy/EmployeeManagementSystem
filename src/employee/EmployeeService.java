package employee;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
	private EmployeeRepository repository;

	/** コンストラクタ **/
	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	/** 登録処理 **/
	public void register(Employee employee) {
		// IDの重複チェック
		if (!repository.existsById(employee.getEmployeeId())) {
			repository.save(employee);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/** 一覧取得処理 **/
	public List<Employee> findAll() {
		// 一覧取得処理。退職者を除外して返す
		List<Employee> employees = repository.findAll();
		List<Employee> activeEmployees = employees.stream().filter(emp -> !emp.isRetired()).toList();
		return activeEmployees;
	}

	/** 検索処理 **/
	public Employee search(String id) {
		Optional<Employee> employee = repository.findById(id);
		// 検索処理。ID検索または氏名部分一致検索を行う
		if (employee.isPresent()) {
			Employee emp = employee.get();
			return emp;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/** 部署別一覧取得 **/
	public List<Employee> findByDepartment(String dept) {
		// 部署別一覧取得処理。退職者を除外して返す
		List<Employee> employees = repository.findByDepartment(dept);
		List<Employee> activeEmployees = employees.stream().filter(emp -> !emp.isRetired()).toList();
		return activeEmployees;
	}

	/** 更新処理 **/
	public void update(String id, String name, int age, String department) {
		// 更新処理。存在チェック後に氏名・年齢・部署を変更
		Optional<Employee> employee = repository.findById(id);
		if (employee.isPresent()) {
			/** 残作業：バリデーションチェックを入れる **/
			Employee emp = employee.get();
			emp.setName(name);
			emp.setAge(age);
			emp.setDepartment(department);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/** 退職処理 **/
	public void retire(String id) {
		// 退職処理。存在チェック後にretiredをtrueにする
		Optional<Employee> employee = repository.findById(id);
		if (employee.isPresent()) {
			Employee emp = employee.get();
			emp.setRetired(true);
		} else {
			throw new IllegalArgumentException();
		}
	}

}
