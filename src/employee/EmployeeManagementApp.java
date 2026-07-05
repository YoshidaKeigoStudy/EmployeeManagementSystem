package employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeManagementApp {
	public static void main(String[] args) {
		EmployeeRepository repository = new EmployeeRepository();
		EmployeeService service = new EmployeeService(repository);
		Scanner scanner = new Scanner(System.in);

		while (true) {
			/** 一覧表示 **/
			System.out.println("==以下のメニューから操作したい機能番号を選択してください==");
			System.out.println("1.社員登録");
			System.out.println("2.社員一覧表示");
			System.out.println("3.社員検索");
			System.out.println("4.部署別一覧");
			System.out.println("5.社員情報変更");
			System.out.println("6.退職処理");
			System.out.println("0.終了");
			System.out.println("===========================================");

			/** 機能選択 **/
			System.out.print("機能番号を入力：");
			int functionNum = scanner.nextInt();

			/** 機能分岐 **/
			switch (functionNum) {
			case 1: {
				/** 登録する社員情報の入力 **/
				System.out.println("==登録する社員情報を入力してください==");
				System.out.print("ID：");
				String employeeId = scanner.next();

				System.out.print("氏名：");
				String employeeName = scanner.next();

				System.out.print("年齢：");
				int employeeAge = scanner.nextInt();

				System.out.print("部署：");
				String employeeDepartment = scanner.next();
				System.out.println();

				Employee emp = new Employee(employeeId, employeeName, employeeAge, employeeDepartment);

				/** 登録メソッドの呼び出し **/
				try {
					service.register(emp);
				} catch (IllegalArgumentException e) {
					System.out.println("そのIDの社員情報は既に登録済みのため登録できません");
				}

				break;
			}

			case 2: {
				/** 社員一覧表示 **/
				List<Employee> allEmp = service.findAll();
				for (Employee emp : allEmp) {
					System.out.println(emp);
				}
				break;
			}

			case 3: {
				/** 社員検索 **/
				System.out.println("==検索したい社員IDを入力してください==");
				System.out.print("ID：");
				String employeeId = scanner.next();
				try {
					Employee emp = service.search(employeeId);
					System.out.println(emp);
				} catch (IllegalArgumentException e) {
					System.out.println("そのIDの社員は存在しません。別のIDで検索してください。");
				}

				break;
			}

			case 4: {
				/** 部署別一覧 **/
				System.out.println("==検索したい部署名を入力してください==");
				System.out.print("部署：");
				String employeeDepartment = scanner.next();
				List<Employee> depEmp = service.findByDepartment(employeeDepartment);
				for (Employee emp : depEmp) {
					System.out.println(emp);
				}
				break;
			}

			case 5: {
				/** 社員情報変更 **/
				System.out.println("==変更したい社員IDを入力してください==");
				System.out.print("ID：");
				String employeeId = scanner.next();
				try {
					service.search(employeeId);
					System.out.println("==IDが" + employeeId + "の社員情報を更新します。入力してください。==");
				} catch (IllegalArgumentException e) {
					System.out.println("そのIDの社員は存在しません。");
					break;
				}

				System.out.print("氏名：");
				String employeeName = scanner.next();

				System.out.print("年齢：");
				int employeeAge = scanner.nextInt();

				System.out.print("部署：");
				String employeeDepartment = scanner.next();

				/** 更新メソッドの呼び出し **/
				service.update(employeeId, employeeName, employeeAge, employeeDepartment);
				break;
			}

			case 6: {
				/** 退職処理 **/
				System.out.println("==退職処理したい社員IDを入力してください==");
				System.out.print("ID：");
				String employeeId = scanner.next();
				try {
					service.retire(employeeId);
				} catch (IllegalArgumentException e) {
					System.out.println("そのIDの社員情報は存在しません。");
				}

				break;
			}

			case 0: {
				System.out.println("システムを終了します");
				scanner.close();
				return;
			}
			default: {
				System.out.println("入力した番号は無効です。");
			}

			}
			System.out.println("===========================================");
		}
	}
}
