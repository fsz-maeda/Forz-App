package model;

import java.util.List;

import dao.EmployeeDAO;

public class DeletePositionCheck {
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
