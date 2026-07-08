package employee.service;

import java.util.List;
import java.util.Optional;

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
		if (!repository.existsById(employee.getEmployeeId())) {
			// バリデーションチェック
			// 年齢が0以上かどうか
			if (employee.getAge() < 0) {
				System.out.println("年齢が0未満です。登録できません。");
				return;
			}
			// 氏名が入力されているかどうか
			if (employee.getName().isBlank()) {
				System.out.println("氏名が入力されていないため登録できません。");
				return;
			}
			// 部署が入力されているかどうかのバリデーションチェック
			if (employee.getDepartment().isBlank()) {
				System.out.println("部署が入力されていないため登録できません。");
				return;
			}
		} else {
			throw new IllegalArgumentException();
		}
		repository.save(employee);
		System.out.println("登録が完了しました。");
	}

	/** 退職者を除く一覧取得処理 **/
	public List<Employee> findActiveEmployees() {
		List<Employee> activeEmployees = repository.findActiveEmployee();
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

	/** 退職者を除く部署別一覧取得 **/
	public List<Employee> findByDepartment(String dept) {
		List<Employee> activeEmployees = repository.findByDepartmentActive(dept);
		// 空の場合例外処理
		if (activeEmployees.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return activeEmployees;
	}

	/** 更新処理 **/
	public void update(String id, String name, int age, String department) {
		// 更新処理。存在チェック後に氏名・年齢・部署を変更
		Optional<Employee> employee = repository.findById(id);
		// 対象の社員情報があるかどうかのチェック
		if (employee.isPresent()) {
			/** 残作業：バリデーションチェックを入れる **/
			Employee emp = employee.get();
			// バリデーションチェック
			// 年齢が0以上かどうか
			if (age < 0) {
				System.out.println("年齢が0未満です。登録できません。");
				return;
			} else {
				emp.setAge(age);
			}
			// 氏名が入力されているかどうか
			if (name.isBlank()) {
				System.out.println("氏名が入力されていないため登録できません。");
				return;
			} else {
				emp.setName(name);
			}
			// 部署が入力されているかどうかのバリデーションチェック
			if (department.isBlank()) {
				System.out.println("部署が入力されていないため登録できません。");
				return;
			} else {
				emp.setDepartment(department);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	/** 退職処理 **/
	public void retire(String id) {
		// 退職処理。存在チェック後にretiredをtrueにする
		Optional<Employee> employee = repository.findById(id);
		// 対象の社員情報があるかどうかのチェック
		if (employee.isPresent()) {
			Employee emp = employee.get();
			emp.setRetired(true);
		} else {
			throw new IllegalArgumentException();
		}
	}

}
