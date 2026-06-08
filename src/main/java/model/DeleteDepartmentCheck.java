package model;

import java.util.List;

import dao.EmployeeDAO;

public class DeleteDepartmentCheck {
	public boolean deleteDepartmentCheck(int departmentId) {
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> employeeList = dao.findAll();
		
		for(Employee employee : employeeList) {
			if(employee.getDepartment() == departmentId) {
				return false;
			}
		}
		
		return true;
	}
}
