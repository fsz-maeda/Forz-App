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
import dao.EmployeeDAO;
import dao.SystemSettingDAO;
import model.Attendance;
import model.AttendanceView;
import model.Employee;
import service.AttendanceLockService;
import service.AttendanceQueryService;
import service.AttendanceService;


@WebServlet("/manageAttendance")
public class ManageAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("loginUser");
		
		if(!((employee != null && employee.getManagement() == true) || employee.getEmployeeId() == 1)){
			response.sendRedirect("Home");
			return;
		}
		
        EmployeeDAO dao = new EmployeeDAO();
        
        List<Employee> employeeList = dao.findAll();
        
        request.setAttribute("attendanceEmployee", employeeList);
        
        
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/manageAttendance.jsp");

		dispatcher.forward(request, response);
	}
	
    private final AttendanceQueryService queryService = new AttendanceQueryService();
	private final AttendanceService service = new AttendanceService();
    private final AttendanceLockService lockService = new AttendanceLockService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("loginUser");
		
		
		if(!((employee != null && employee.getManagement() == true) || employee.getEmployeeId() == 1)){
			response.sendRedirect("Home");
			return;
		}
        
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        
		
//		今日の日付取得
		LocalDate now = LocalDate.now();

//		年月の取得
		int year = Optional.ofNullable(request.getParameter("year"))
		        .map(Integer::parseInt)
		        .orElse(now.getYear());
		int month = Optional.ofNullable(request.getParameter("month"))
		        .map(Integer::parseInt)
		        .orElse(now.getMonthValue());
	
        boolean isAdmin = employee.getManagement();

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
		boolean approved = dao.isMonthApproved(employeeId, year, month);
		
		SystemSettingDAO settingDAO = new SystemSettingDAO();
		int closeDay = settingDAO.getCloseDay();
		request.setAttribute("closeDay", closeDay);
		
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
