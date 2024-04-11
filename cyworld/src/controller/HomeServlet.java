package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.GuestbookDAO;
import model.GuestbookDTO;
import model.ImagesDAO;
import model.ImagesDTO;
import model.MusicDAO;
import model.MusicDTO;
import model.ReplyDAO;
import model.ReplyDTO;
import util.JSFunction;

public class HomeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();	// 절대 경로 (현재 파일 이름 포함)
		String Path = request.getContextPath();	// 절대 경로 (현재 파일 이름 미포함)
		String command = uri.substring(Path.length()+1);	// 슬래시 제외 현재 파일 이름
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		if (command.equals("writeBook.home")){
			write(request);
		} else if (command.equals("deleteBook.home")){
			delete(request);
		} else if (command.equals("writeReply.home")){
			writeReply(request);
		} else if (command.equals("deleteRe.home")){
			deleteReply(request);
		} else if (command.equals("writeImages.home")){
			writeImages(request, response);
			return;
		} else if (command.equals("music_play.home")){
			music_play(request);
		} else if (command.equals("music_add.home")){
			music_add(request);
			response.sendRedirect("/cyworld/cyworld/close.jsp");
			return;
		} else if (command.equals("music_dufle.home")){
			music_dufle(request);
			response.sendRedirect("/cyworld/cyworld/home.jsp?page=main&id="+request.getSession().getAttribute("login_id"));
			return;
		}

		String id = request.getParameter("id");
		String page = request.getParameter("page");
		response.sendRedirect("/cyworld/cyworld/home.jsp?page="+page+"&id="+id);
	}
	
	// 방명록 작성
	public int write(HttpServletRequest request) {
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		String post_id = (String)request.getSession().getAttribute("login_id");
		
		GuestbookDTO dto = new GuestbookDTO();
		dto.setId(id);
		dto.setPost_id(post_id);
		dto.setContent(content);
		
		GuestbookDAO dao = new GuestbookDAO();
		int result = dao.insertGuestbook(dto);
		dao.close();
		return result;
	}
	
	// 방명록 삭제
	public void delete(HttpServletRequest request) {
		String no = request.getParameter("no");
		GuestbookDAO dao = new GuestbookDAO();
		dao.deleteBook(no);
		dao.close();
	}
	
	// 방명록의 댓글 작성
	public void writeReply(HttpServletRequest request) {
		String g_no = request.getParameter("g_no");
		String content = request.getParameter("content");
		String login_id = (String)request.getSession().getAttribute("login_id");
		
		ReplyDTO dto = new ReplyDTO();
		dto.setG_no(g_no);
		dto.setContent(content);
		dto.setId(login_id);
		
		ReplyDAO dao = new ReplyDAO();
		dao.insertGuestbookreply(dto);
		dao.close();
	}
	
	// 방명록의 댓글 삭제
	public void deleteReply(HttpServletRequest request) {
		String r_no = request.getParameter("r_no");
		ReplyDAO dao = new ReplyDAO();
		dao.deleteReply(r_no);
		dao.close();
	}
	
	// 사진첩 작성
	public void writeImages(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// MultipartRequest 설정
		String saveDirectory = request.getServletContext().getRealPath("/cyworld/resources/img/images");
		int maxPostSize = 5 * 1024 * 1024; // 5Mb
		String encoding = "utf-8";
		MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		
		String id = mr.getParameter("id");
		String page = mr.getParameter("page");
		String login_id = (String)request.getSession().getAttribute("login_id");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		String fileName = mr.getFilesystemName("file");
		if (fileName != null) {
			File file = new File(saveDirectory+File.separator+fileName);
		} else {
			JSFunction.alert(response, "사진을 등록해주세요.");
			return;
		}
		
		ImagesDTO dto = new ImagesDTO();
		dto.setId(login_id);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setImgName(fileName);
		
		ImagesDAO dao = new ImagesDAO();
		dao.insertImages(dto);
		dao.close();
		response.sendRedirect("/cyworld/cyworld/home.jsp?page="+page+"&id="+id);
	}
	
	// 홈 화면에 재생 목록 변경
	public void music_play(HttpServletRequest request) {
		String[] check_no = request.getParameterValues("check");
		MusicDAO dao = new MusicDAO();
		dao.music_play(check_no);
		dao.close();
	}
	
	// 곡 퍼가기
	public void music_dufle(HttpServletRequest request) {
		String id = request.getParameter("id");
		String login_id = (String)request.getSession().getAttribute("login_id");
		String[] check_no = request.getParameterValues("check");
		MusicDAO dao = new MusicDAO();
		dao.dupleMusic(id, login_id, check_no);
		dao.close();
	}
	
	// 곡 추가
	public void music_add(HttpServletRequest request) {
		String id = (String)request.getSession().getAttribute("login_id");
		String title = request.getParameter("title");
		String artist = request.getParameter("artist");
		
		// 복사한 Youtube주소에서 videoId값(Youtube api에 사용될 변수)을 추출하여 저장
		String path = request.getParameter("path");
		String delimiter = "watch?v=";
		int pathIndex = path.indexOf(delimiter);
        String pathId = path.substring(pathIndex + delimiter.length());
           
		MusicDTO dto = new MusicDTO();
		dto.setId(id);
		dto.setTitle(title);
		dto.setArtist(artist);
		dto.setPath(pathId);
		
		MusicDAO dao = new MusicDAO();
		dao.insertMusic(dto);
		dao.close();
	}

}
