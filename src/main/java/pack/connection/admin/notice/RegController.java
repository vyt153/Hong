package pack.connection.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pack.entity.Notice;
import pack.service.NoticeService;

@MultipartConfig(
		fileSizeThreshold=1024*1024,
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*50*5
		)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp")
		.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		Collection<Part> parts = request.getParts();
		
		StringBuilder builder = new StringBuilder();
		for(Part p:parts) {
			if(!p.getName().equals("file")||p.getSize()==0) continue;
			
			Part filePart = p;
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			InputStream fis = filePart.getInputStream();
			String realPath = request.getServletContext().getRealPath("/upload");
			
			File path = new File(realPath);
			System.out.println(path);
			if(path.exists()) path.mkdir();
			
			String filePath = realPath+File.separator+fileName;
			FileOutputStream fos= new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size=fis.read(buf))!=-1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			fis.close();
		}
		builder.delete(builder.length()-1, builder.length());
		
		boolean pub = false;
		if(isOpen!=null) pub = true;
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriter_ID("Hong");
		notice.setFiles(builder.toString());
		
		NoticeService service = new NoticeService();
		service.insertNotice(notice);
		
		response.sendRedirect("list");
		
	}
}
