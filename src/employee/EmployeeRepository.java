package employee;

import java.util.*;

/** 社員データの保管・取得 **/
public class EmployeeRepository {
	private final List<Employee> employees = new ArrayList<>();
	
	public void save(Employee employee) {
		//リストに社員を追加する
		employees.add(employee);
	}

	public Optional<Employee> findById(String employeeId){
		//IDで社員を1件取得する
		for(Employee emp:employees) {
			if(emp.getEmployeeId().equals(employeeId)) {
				return emp;
			}
		}
		return null;
	}
	
	/*
	private List<Employee> findAll(){
		//全社員を取得する（退職者を含む）
	}
	
	private boolean existsById(String employeeId) {
		//IDが存在するか確認する
	}
	
	private List<Employee> findByDepartment(String department){
		//部署名で社員一覧を取得する
	}
	*/
}
