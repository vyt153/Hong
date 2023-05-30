package pack.connection.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.entity.NoticeView;
import pack.service.NoticeService;

@WebServlet("/html/notice/list")
public class ListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field= "title";
		if(field_!=null&&!field_.equals("")) {
			field = field_;
		}
		
		String query= "";
		if(query_!=null&&!query_.equals("")) {
			query = query_;
		}
		int page = 1;
		if(page_!=null&&!page_.equals("")) {
			page = Integer.parseInt(page_);
		}
		
		NoticeService service = new NoticeService();
		int count = service.getNoticeCount(field, query);
		List<NoticeView> list = service.getPubNoticeList(field, query, page);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
		.forward(request, response);
	}
}
