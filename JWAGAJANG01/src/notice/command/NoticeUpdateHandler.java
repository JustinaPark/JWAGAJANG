package notice.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.dto.NoticeVO;

public class NoticeUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
			String url = "/noticeUpdate.do";
			int num = Integer.parseInt(req.getParameter("num"));
			NoticeDAO bDao = NoticeDAO.getInstance();
			bDao.updateReadCount(num);
			NoticeVO bVo = bDao.selectOneBoardByNum(num);
			req.setAttribute("board", bVo);
			return url;
			}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		NoticeVO bVo = new NoticeVO();
		bVo.setNotice_label(req.getParameter("notice_label"));
		bVo.setNotice_title(req.getParameter("notice_title"));
		bVo.setNotice_content(req.getParameter("notice_content"));
		NoticeDAO bDao = NoticeDAO.getInstance();
		bDao.updateBoard(bVo);
		res.sendRedirect("noticeList.do");
		return null;
	}
}