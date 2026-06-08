package model;

public class MediaComment {
	private int id;
	private String name;
	private String comment;
	
	public MediaComment(int id,String name,String comment) {
		this.id = id;
		this.name = name;
		this.comment = comment;
	}
	
	public MediaComment(int i) {
		this.id=i;
	}

	public MediaComment(int id2, String comment2) {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public int getId(){return id;}
	public String getName() {return name;}
	public String getComment() {return comment;}

}
