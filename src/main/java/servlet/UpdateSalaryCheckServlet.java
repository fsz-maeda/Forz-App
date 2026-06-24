package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import dao.SalaryDAO;
import model.Employee;
import model.Salary;

@WebServlet("/updateSalaryCheck")
public class UpdateSalaryCheckServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int salaryId;
        int employeeId;
        int amount;
        int month;
        
        HttpSession session = request.getSession();

        try {
            salaryId = Integer.parseInt(request.getParameter("salaryId"));
            employeeId = Integer.parseInt(request.getParameter("employeeId"));
            amount = Integer.parseInt(request.getParameter("amount"));
            month = Integer.parseInt(request.getParameter("month"));

        } catch (NumberFormatException e) {
            session.setAttribute("updateSalaryMsg", "不正な入力です");
            response.sendRedirect("manageSalary");
            return;
        }

        // 支給額チェック
        if (amount <= 0) {
            session.setAttribute("updateSalaryMsg", "支給額は1円以上を入力してください");
            response.sendRedirect("manageSalary");
            return;
        }

        // 月チェック
        if (month < 1 || month > 12) {
            session.setAttribute("updateSalaryMsg", "月は1～12で入力してください");
            response.sendRedirect("manageSalary");
            return;
        }

        // 社員存在チェック
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee targetEmployee = employeeDAO.findByUserId(employeeId);

        if (targetEmployee == null) {
            session.setAttribute("updateSalaryMsg", "対象社員が存在しません");
            response.sendRedirect("manageSalary");
            return;
        }

        // 給与存在チェック
        SalaryDAO salaryDAO = new SalaryDAO();
        Salary existingSalary = salaryDAO.findBySalaryId(salaryId);

        if (existingSalary == null) {
            session.setAttribute("updateSalaryMsg", "対象給与データが存在しません");
            response.sendRedirect("manageSalary");
            return;
        }

        // 更新用オブジェクト作成
        Salary salary = new Salary(salaryId, employeeId, amount, month);

        // DB更新
        boolean result = salaryDAO.updateSalary(salary);

        if (result) {
            session.setAttribute("updateSalaryMsg", "更新完了");

        } else {
            session.setAttribute("updateSalaryMsg", "更新失敗");
        }

        response.sendRedirect("manageSalary");
    }
}