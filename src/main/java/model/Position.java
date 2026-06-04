package model;

import java.io.Serializable;

public class Position implements Serializable{
	private int positionId;
	private String positionName;
	
	public Position(int positionId, String positionName) {
		this.positionId = positionId;
		this.positionName = positionName;
	}

	public int getPositionId() {
		return positionId;
	}

	public String getPositionName() {
		return positionName;
	}
	
}
