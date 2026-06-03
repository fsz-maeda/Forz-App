package model;

public class Chat {
	private int chatId;
	private int senderId;
	private int receiverId;
	private String message;
	private String sendTime;
	
	public int getChatId() {return chatId;}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public int getSenderId() {return senderId;}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {return receiverId;}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getMessage() {return message;}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSendTime() {return sendTime;}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

}
