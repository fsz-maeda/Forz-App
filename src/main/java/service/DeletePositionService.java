package service;

import dao.EmployeeDAO;

public class DeletePositionService {

    // 削除可能ならtrue
    public boolean canDeletePosition(int positionId) {

        EmployeeDAO employeeDao = new EmployeeDAO();

        return !employeeDao.existsByPositionId(positionId);
    }
}