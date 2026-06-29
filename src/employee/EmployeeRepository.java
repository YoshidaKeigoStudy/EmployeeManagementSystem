package employee;

import java.util.*;

/** 社員データの保管・取得 **/
public class EmployeeRepository {
	private final List<Employee> employees = new ArrayList<>();
	
	//リストに社員を追加するメソッド
	public void save(Employee employee) {
		employees.add(employee);
	}

	//IDが一致する社員情報を取得
	public Optional<Employee> findById(String employeeId){
		//IDで社員を1件取得する
		for(Employee emp:employees) {
			if(emp.getEmployeeId().equals(employeeId)) {
				return Optional.of(emp);
			}
		}
		return Optional.empty();
	}
	
	//全社員情報を取得する（退職者を含む）
	public List<Employee> findAll(){
		return employees;
	}
	
	//IDが存在するか確認する
	public boolean existsById(String employeeId) {
		for(Employee emp:employees) {
			if(emp.getEmployeeId().equals(employeeId)) {
				return true;
			} 
		}
		return false;
	}
	
	//部署名で社員一覧を取得する
	public List<Employee> findByDepartment(String department){
		List<Employee> departmentList = new ArrayList<>();
		for(Employee emp:employees) {
			if(emp.getDepartment().equals(department)) {
				departmentList.add(emp);
			}
		}
		return departmentList;
	}
	
}
