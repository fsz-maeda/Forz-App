package model;

import dao.MediaDAO;

public class MediaRegistLogic {

    public boolean execute(Media media, int departmentId, int employeeId) {

        MediaDAO dao = new MediaDAO();
        return dao.insert(media, departmentId, employeeId);
    }
}