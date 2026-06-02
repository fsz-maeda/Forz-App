package model;

import java.util.List;

import dao.MediaDAO;

public class MediaLogic {
	public List<Media> execute(){
	MediaDAO dao = new MediaDAO();	
	List<Media> mediaList = dao.findAll();
	return mediaList;
	}
}
