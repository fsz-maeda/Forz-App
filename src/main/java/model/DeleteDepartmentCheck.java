package model;

import java.util.List;

import dao.UserDAO;

public class DeleteDepartmentCheck {
	public boolean deleteDepartmentCheck(int departmentId) {
		UserDAO dao = new UserDAO();
		List<User> userList = dao.findAll();
		
		for(User user : userList) {
			if(user.getDepartmentId() == departmentId) {
				return false;
			}
		}
		
		return true;
	}
}
