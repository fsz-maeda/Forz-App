package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import dao.PaidHolidayDAO;
import model.Employee;
import model.PaidHoliday;
import service.PaidHolidayService;

@WebServlet("/updatePaidHolidayCheck")
public class UpdatePaidHolidayCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int paidHolidayId =
                Integer.parseInt(request.getParameter("paidHolidayId"));

        int employeeId =
                Integer.parseInt(request.getParameter("employeeId"));

        Date startDate =
                Date.valueOf(request.getParameter("startDate"));

        Date finishDate =
                Date.valueOf(request.getParameter("finishDate"));

        String holidayType =
                request.getParameter("holidayType");

        PaidHolidayService phs = new PaidHolidayService();

        double newUsedDays =
                phs.calcUsedDays(startDate, finishDate, holidayType);

        if (newUsedDays == -1) {

            request.getSession().setAttribute(
                    "updatePaidHolidayMsg",
                    "入力内容に誤りがあります");

            response.sendRedirect("insertPaidHoliday");
            return;
        }

        PaidHolidayDAO dao = new PaidHolidayDAO();

        // 更新前データ取得
        PaidHoliday oldHoliday =
                dao.findByPaidHolidayId(paidHolidayId);

        if (oldHoliday == null) {

            request.getSession().setAttribute(
                    "updatePaidHolidayMsg",
                    "対象データが存在しません");

            response.sendRedirect("insertPaidHoliday");
            return;
        }

        double oldUsedDays =
                oldHoliday.getUsedDays();

        // 差分計算
        double diff =
                newUsedDays - oldUsedDays;

        EmployeeDAO edao = new EmployeeDAO();

        Employee employee =
                edao.findByUserId(employeeId);

        boolean check = true;

        // 増える場合のみ残有給チェック
        if (diff > 0) {
            check = phs.checkUsedDays(
                    employeeId,
                    diff,
                    employee);
        }

        if (!check) {

            request.getSession().setAttribute(
                    "updatePaidHolidayMsg",
                    "有給日数が超過しています");

            response.sendRedirect("insertPaidHoliday");
            return;
        }

        boolean updateHoliday =
                dao.updatePaidHoliday(
                        paidHolidayId,
                        newUsedDays,
                        startDate,
                        finishDate,
                        holidayType);

        boolean updateRemain =
                edao.decreaseRemainPaidHoliday(
                        employeeId,
                        diff);

        if (updateHoliday && updateRemain) {

            request.getSession().setAttribute(
                    "updatePaidHolidayMsg",
                    "更新完了");

        } else {

            request.getSession().setAttribute(
                    "updatePaidHolidayMsg",
                    "更新失敗");
        }

        response.sendRedirect("insertPaidHoliday");
    }
}