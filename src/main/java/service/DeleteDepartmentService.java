package service;

import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

public class DeleteDepartmentService {
	public boolean deleteDepartment(int departmentId) {
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
