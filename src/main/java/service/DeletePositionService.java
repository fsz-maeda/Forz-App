package service;

import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

public class DeletePositionService {
	//指定した役職IDをもつ従業員がいるか確認
	public boolean deletePositionCheck(int positionId) {
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> employeeList = dao.findAll();
		
		for(Employee employee : employeeList) {
			if(employee.getPosition() == positionId) {
				return false;
			}
		}
		
		return true;
	}
}
