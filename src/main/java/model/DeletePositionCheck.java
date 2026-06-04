package model;

import java.util.List;

import dao.UserDAO;

public class DeletePositionCheck {
	public boolean deletePositionCheck(int positionId) {
		UserDAO dao = new UserDAO();
		List<User> userList = dao.findAll();
		
		for(User user : userList) {
			if(user.getPositionId() == positionId) {
				return false;
			}
		}
		
		return true;
	}
}
