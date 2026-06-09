package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AttendanceDAO;
import model.Attendance;
import model.AttendanceView;
import model.Employee;
import service.AttendanceLockService;
import service.AttendanceQueryService;
import service.AttendanceService;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private final AttendanceQueryService queryService = new AttendanceQueryService();
	private final AttendanceService service = new AttendanceService();
    private final AttendanceLockService lockService = new AttendanceLockService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
//		今日の日付取得
		LocalDate now = LocalDate.now();

//		年月の取得
		int year = Optional.ofNullable(request.getParameter("year"))
		        .map(Integer::parseInt)
		        .orElse(now.getYear());
		int month = Optional.ofNullable(request.getParameter("month"))
		        .map(Integer::parseInt)
		        .orElse(now.getMonthValue());
	
//		ログインユーザー取得確認
		Employee employee = (Employee) session.getAttribute("loginUser");
		
		
        if (employee == null) {
            response.sendRedirect("Home");
            return;
        }
        
        boolean isAdmin = employee.getManagement();
        int employeeId = employee.getEmployeeId();;

//		ログインユーザーのその月の分のデータを取得
		List<Attendance> list = queryService.findMonthly(employeeId, year, month);
		
//		jspで使いやすくするため
		Map<String, AttendanceView> viewMap = new HashMap<>();

		for (Attendance a : list) {
		    AttendanceView v = service.toView(a);
		    viewMap.put(v.getDate(), v);
		}


//		その月の１日
		LocalDate firstDate = LocalDate.of(year, month, 1);
//		その月の日数
		int days = firstDate.lengthOfMonth();
//		カレンダーの空白の日数（日曜日スタート）
		int startBlank = firstDate.getDayOfWeek().getValue() % 7;
		
//		勤務時間の計算
		double totalHours = service.calcMonthlyTotal(list);
		
		AttendanceDAO dao = new AttendanceDAO();
		boolean approved = dao.isApproved(employeeId, year, month);
		
		Map<String, Boolean> lockMap = new HashMap<>();

		for (int i = 1; i <= days; i++) {

		    LocalDate date = LocalDate.of(year, month, i);

		    boolean locked = lockService.isLocked(date,isAdmin,approved,year,month);

		    lockMap.put(date.toString(), locked);
		}
		
		
		request.setAttribute("approved", approved);
		request.setAttribute("lockMap", lockMap);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("days", days);
        request.setAttribute("attendanceMap", viewMap);
        request.setAttribute("startBlank", startBlank);
        request.setAttribute("today", now.toString());
        request.setAttribute("totalHoursText", service.formatHours(totalHours));

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/attendance.jsp");

		dispatcher.forward(request, response);
	}

}