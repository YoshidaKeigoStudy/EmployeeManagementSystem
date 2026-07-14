package employee.service;

import java.util.List;

import employee.model.Employee;
import employee.repository.EmployeeRepository;

public class EmployeeService {
	private EmployeeRepository repository;

	/** コンストラクタ **/
	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	/** 登録処理 **/
	public void register(Employee employee) {
		// IDの重複チェック
		if (repository.existsById(employee.getEmployeeId())) {
			throw new IllegalArgumentException("そのIDの社員情報は既に登録済みのため登録できません");
		}
		repository.save(employee);
		System.out.println("登録が完了しました。");
	}

	/** 退職者を除く一覧取得処理 **/
	public List<Employee> findActiveEmployees() {
		return repository.findActiveEmployee();
	}

	/** 検索処理 **/
	public Employee search(String id) {
		return findById(id);
	}

	/** 退職者を除く部署別一覧取得 **/
	public List<Employee> findByDepartment(String dept) {
		List<Employee> activeEmployees = repository.findByDepartmentActive(dept);
		// 空の場合例外処理
		if (activeEmployees.isEmpty()) {
			throw new IllegalArgumentException("その部署名で登録されている社員は存在しません。");
		}
		return activeEmployees;
	}

	/** 更新処理 **/
	public void update(String id, String name, int age, String department) {
		Employee emp = findById(id);
		emp.changeProperty(age, name, department);
	}

	/** 退職処理 **/
	public void retire(String id) {
		// 退職処理。存在チェック後にretiredをtrueにする
		Employee emp = findById(id);
		emp.retire();
	}

	// 対象の社員情報があれば取得、なければ例外処理をするメソッド
	private Employee findById(String id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("そのIDの社員は存在しません。"));
	}

}
