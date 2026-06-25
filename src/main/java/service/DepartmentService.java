package service;

import dao.DepartmentDAO;
import dao.EmployeeDAO;

public class DepartmentService {
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    /**
     * 部署追加
     */
    public void insertDepartment(String departmentName) {
        if (departmentName == null || departmentName.isBlank()) {
            throw new IllegalArgumentException("部署名を入力してください");
        }

        departmentName = departmentName.trim();

        if (departmentDAO.existsDepartmentName(departmentName)) {
            throw new IllegalArgumentException("同じ部署名が既に存在します");
        }

        boolean result = departmentDAO.insertDepartment(departmentName);

        if (!result) {
            throw new IllegalArgumentException("登録に失敗しました");
        }
    }

    /**
     * 部署更新
     */
    public void updateDepartment(String departmentIdStr, String departmentName) {
        if (departmentIdStr == null || departmentIdStr.isBlank()) {
            throw new IllegalArgumentException("部署IDが不正です");
        }

        int departmentId;

        try {
            departmentId = Integer.parseInt(departmentIdStr);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("部署IDが不正です");
        }

        if (departmentName == null || departmentName.isBlank()) {
            throw new IllegalArgumentException("部署名を入力してください");
        }

        departmentName = departmentName.trim();

        boolean exists = departmentDAO.existsDepartmentNameExceptSelf(departmentId, departmentName);

        if (exists) {
            throw new IllegalArgumentException("同じ部署名が既に存在します");
        }

        boolean result = departmentDAO.updateDepartment(departmentId, departmentName);

        if (!result) {
            throw new IllegalArgumentException("更新に失敗しました");
        }
    }

    /**
     * 部署削除
     */
    public void deleteDepartment(String departmentIdStr) {
        if (departmentIdStr == null || departmentIdStr.isBlank()) {
            throw new IllegalArgumentException("部署IDが不正です");
        }

        int departmentId;

        try {
            departmentId = Integer.parseInt(departmentIdStr);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("部署IDが不正です");
        }

        if (!departmentDAO.existsById(departmentId)) {
            throw new IllegalArgumentException("部署が存在しません");
        }

        int employeeCount = employeeDAO.countByDepartmentId(departmentId);

        if (employeeCount > 0) {
            throw new IllegalArgumentException("対象部署に所属している社員がいるため削除できません");
        }

        boolean result = departmentDAO.deleteDepartment(departmentId);

        if (!result) {
            throw new IllegalArgumentException("削除に失敗しました");
        }
    }
}