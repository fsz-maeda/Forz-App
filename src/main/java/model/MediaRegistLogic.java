package model;

import dao.MediaDAO;

public class MediaRegistLogic {
	public boolean execute(Media media, int departmentId, Employee em) {
		MediaDAO dao = new MediaDAO();
		return dao.mediaRegist(media,departmentId,em);
	}

}
