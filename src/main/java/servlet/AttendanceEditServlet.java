package servlet;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AttendanceDAO;
import model.Attendance;
import model.Employee;
import service.AttendanceClockService;
import service.AttendanceLockService;
import service.AttendanceQueryService;

@WebServlet("/AttendanceEditServlet")
public class AttendanceEditServlet extends HttpServlet {

    private final AttendanceLockService lockService = new AttendanceLockService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

//    	ログイン確認
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("loginUser");

        if (employee == null) {
            response.sendRedirect("Home");
            return;
        }

//      日付取得からnullチェック
        String dateStr = request.getParameter("date");

        if (dateStr == null) {
            response.sendError(400);
            return;
        }
        
        LocalDate date = LocalDate.parse(dateStr);

//      年月を分解
        int year = date.getYear();
        int month = date.getMonthValue();
        
        AttendanceDAO dao = new AttendanceDAO();

//      月が承認済みか
        boolean approved = dao.isMonthApproved(
                employee.getEmployeeId(),
                year,
                month
        );
        
//      管理者判定
        boolean isAdmin = employee.getManagement();

//      ロック判定
        if (lockService.isLocked(date, isAdmin, approved, year, month)) {
            response.sendError(403);
            return;
        }

//      編集の対象データ取得
        AttendanceQueryService queryService = new AttendanceQueryService();
        Attendance a = queryService.findByDate(employee.getEmployeeId(), dateStr);

        request.setAttribute("attendance", a);
        request.setAttribute("date", dateStr);

        request.getRequestDispatcher("/WEB-INF/jsp/attendanceEdit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

//      ログイン確認
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("loginUser");

        if (employee == null) {
            response.sendRedirect("Home");
            return;
        }

//      日付取得
        String dateStr = request.getParameter("date");
        LocalDate date = LocalDate.parse(dateStr);
//      管理者チェック
        boolean isAdmin = employee.getManagement();

//      年月の分解
        int year = date.getYear();
        int month = date.getMonthValue();
        
        AttendanceDAO dao = new AttendanceDAO();

//      月の承認チェック
        boolean approved = dao.isMonthApproved(
                employee.getEmployeeId(),
                year,
                month
        );

//      ロックチェック
        if (lockService.isLocked(date, isAdmin, approved, year, month)) {
            response.sendError(403);
            return;
        }

        String clockInStr = request.getParameter("clockIn");
        String clockOutStr = request.getParameter("clockOut");
        int breakMinutes = Integer.parseInt(request.getParameter("breakMinutes"));

        AttendanceClockService clockService = new AttendanceClockService();

//      保存処理
        clockService.saveAttendance(
                employee.getEmployeeId(),
                dateStr,
                clockInStr,
                clockOutStr,
                breakMinutes
        );
        

        response.sendRedirect("AttendanceServlet?year=" +
                dateStr.substring(0, 4) + "&month=" + dateStr.substring(5, 7));
    }
}