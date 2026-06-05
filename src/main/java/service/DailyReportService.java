package service;

import java.util.List;
import java.util.Set;

import dao.DailyReportDAO;
import model.DailyReport;

public class DailyReportService {

    private DailyReportDAO dao = new DailyReportDAO();

    
    public List<DailyReport> getPagedReports(int offset, int limit, Set<Integer> likedSet) {

//    	日報を１件づつとってくる
        List<DailyReport> list = dao.findPagedReports(offset, limit);
        
//      日報にあるコメントの取得といいねしているかの判定
        for (DailyReport r : list) {
            r.setCommentList(
                dao.findCommentsByReportId(r.getDailyReportId())
            );
            
//          いいねした日報のID一覧からそのユーザーがいいねを押しているのかの取得
            r.setLiked(
                likedSet != null && likedSet.contains(r.getDailyReportId())
            );
        }

        return list;
    }
}