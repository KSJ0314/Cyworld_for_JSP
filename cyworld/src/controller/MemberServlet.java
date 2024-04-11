package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.MemberDAO;
import model.MemberDTO;

public class MemberServlet extends HttpServlet {
	
	private MemberDAO dao;
	
	public void init() throws ServletException {
		dao = new MemberDAO();
	}
	
	public void destroy() {
		dao.close();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();	// 절대 경로 (현재 파일 이름 포함)
		String Path = request.getContextPath();	// 절대 경로 (현재 파일 이름 미포함)
		String command = uri.substring(Path.length()+1);	// 슬래시 제외 현재 파일 이름
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(command.equals("login.member")){	// idCheck.jsp에서 아이디 중복확인 버튼 클릭
			String id = request.getParameter("id");
			int code = dao.hasMember(id);
			response.sendRedirect("/cyworld/cyworld/idCheck.jsp?code="+code+"&idCheck="+id);
		} else if(command.equals("logout.member")){	// index.jsp에서 로그아웃 a태그 클릭
			request.getSession().removeAttribute("login_id");
			response.sendRedirect("/cyworld/cyworld/index.jsp");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();	// 절대 경로 (현재 파일 이름 포함)
		String Path = request.getContextPath();	// 절대 경로 (현재 파일 이름 미포함)
		String command = uri.substring(Path.length()+1);	// 슬래시 제외 현재 파일 이름
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(command.equals("addMember.member")){	// addMember.jsp에서 회원가입 버튼 클릭
			addMember(request, response);
		} else if(command.equals("login.member")){	// login.jsp에서 로그인 버튼 클릭
			login(request, response);
		} 
		
	}
	
	private void addMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// MultipartRequest 설정
		String saveDirectory = request.getServletContext().getRealPath("/cyworld/resources/img");
		int maxPostSize = 5 * 1024 * 1024; // 5Mb
		String encoding = "utf-8";
		MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		String fileName = mr.getFilesystemName("file");
		if (fileName != null) {
			File file = new File(saveDirectory+File.separator+fileName);
		} else {	// 이미지 미등록시 기본이미지로 설정
			fileName = "default.jpg";
		}
		
		// 회원가입을 위한 member 객체 생성
		MemberDTO dto = new MemberDTO();
		String id = mr.getParameter("id");
		dto.setId(id);
		dto.setPw(mr.getParameter("pw"));
		String email = mr.getParameter("email")+mr.getParameter("domain");
		dto.setEmail(email);
		String tel = mr.getParameter("tel1")+"-"+mr.getParameter("tel2")+"-"+mr.getParameter("tel3");
		dto.setTel(tel);
		dto.setIsAdmin(mr.getParameter("rank"));
		dto.setImgName(fileName);
		
		if (dao.addMember(dto) == 1) {
			response.sendRedirect("/cyworld/cyworld/addMemberSuccess.jsp?id="+id);
		} else {
			response.sendRedirect("/cyworld/cyworld/error.jsp");
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		int result = dao.login(id, pw);	// 0 : 로그인 성공, 1 : 아이디 오류, 2 : 비번 오류
		
		try {
			if (result == 1) {
				response.sendRedirect("/cyworld/cyworld/login.jsp?error=1");
			} else if (result == 2) {
				response.sendRedirect("/cyworld/cyworld/login.jsp?error=2");
			} else {
				request.getSession().setAttribute("login_id", id);
				response.sendRedirect("/cyworld/cyworld/home.jsp?page=main&id="+id);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
