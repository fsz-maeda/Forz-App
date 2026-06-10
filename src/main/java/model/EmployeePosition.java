package model;

public class EmployeePosition {
	private int employeeId;
	private String name;
	private String pass;
	private String mail;
	private String positionName;
	private String departmentName;
	private String enter;
	private boolean management;
	private int remainPaidHoliday;
	
	public EmployeePosition(int employeeId, String name, String pass, String mail, String positionName,
			String departmentName, String enter, boolean management, int remainPaidHoliday) {
		this.employeeId = employeeId;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.positionName = positionName;
		this.departmentName = departmentName;
		this.enter = enter;
		this.management = management;
		this.remainPaidHoliday = remainPaidHoliday;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

	public String getMail() {
		return mail;
	}

	public String getPositionName() {
		return positionName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getEnter() {
		return enter;
	}

	public boolean isManagement() {
		return management;
	}
	
	public int getRemainPaidHoliday() {
		return remainPaidHoliday;
	}
}
