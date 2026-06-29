package employee;

import java.util.Optional;

public class EmployeeService {
	private EmployeeRepository repository;
	public EmployeeService(EmployeeRepository repository){
		this.repository = repository;
	}
	
	public void register(Employee employee) {
		//登録処理。IDの重複チェックを行い問題なければ保存メソッドを呼び出し
		if(!repository.existsById(employee.getEmployeeId())) {
			repository.save(employee);
		}else {
			/** エラー処理 **/
		}
	}
	
	public void update(String id, String name, int age, String department) {
		//更新処理。存在チェック後に氏名・年齢・部署を変更
		Optional<Employee> employee = repository.findById(id);
		if(employee.isPresent()) {
			/** 残作業：バリデーションチェックを入れる **/
			Employee emp = employee.get();
			emp.setName(name);
			emp.setAge(age);
			emp.setDepartment(department);
		}else {
			/** エラー処理 **/
		}
	}
	
	public void retire(String id) {
		//退職処理。存在チェック後にretiredをtrueにする
		Optional<Employee> employee = repository.findById(id);
		if(employee.isPresent()) {
			Employee emp = employee.get();
			emp.setRetired(true);
		}else {
			/** エラー処理 **/
		}
	}
	
	public Employee search(String id) {
		Optional<Employee> employee = repository.findById(id);
		//検索処理。ID検索または氏名部分一致検索を行う
		if(employee.isPresent()) {
			Employee emp = employee.get();
			return emp;
		}else {
			/** エラー処理　**/
		}
	}
	
	/*
	private List findAll() {
		//一覧取得処理。退職者を除外して返す
	}
	
	private List findByDepartment(String dept) {
		//部署別一覧取得処理。退職者を除外して返す
	}
	*/
}
