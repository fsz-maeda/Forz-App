package model;

import java.io.Serializable;

public class Questionnaire implements Serializable{
	private int questionnaireId;
	private int employeeId;
	private String content;
	
	public Questionnaire(int questionnaireId, int employeeId, String content) {
		this.questionnaireId = questionnaireId;
		this.employeeId = employeeId;
		this.content = content;
	}

	public int getQuestionnaireId() {
		return questionnaireId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getContent() {
		return content;
	}
}
