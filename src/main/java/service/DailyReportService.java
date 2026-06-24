package service;

import java.util.List;
import java.util.Set;

import dao.DailyReportCommentDAO;
import dao.DailyReportDAO;
import model.DailyReport;

public class DailyReportService {

    private DailyReportDAO reportDao = new DailyReportDAO();
    private DailyReportCommentDAO commentDao = new DailyReportCommentDAO();

    public List<DailyReport> getPagedReports(int offset, int limit, Set<Integer> likedSet) {

        List<DailyReport> reports = reportDao.findPagedReports(offset, limit);

        for (DailyReport report : reports) {
            enrichReport(report, likedSet);
        }

        return reports;
    }

    private void enrichReport(DailyReport report, Set<Integer> likedSet) {

        report.setCommentList(
                commentDao.findByReportId(report.getDailyReportId())
        );

        report.setLiked(
                likedSet != null && likedSet.contains(report.getDailyReportId())
        );
    }
}