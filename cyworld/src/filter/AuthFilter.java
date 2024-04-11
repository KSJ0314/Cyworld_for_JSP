package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GuestbookDAO;
import model.ImagesDAO;
import model.MemberDAO;
import model.MusicDAO;
import model.ReplyDAO;
import util.JSFunction;

public class AuthFilter implements Filter {
	
	private MemberDAO dao;
	private GuestbookDAO dao_gB;
	private ReplyDAO dao_re;
	private ImagesDAO dao_im;
	private MusicDAO dao_mu;

	@Override
	public void destroy() {
		dao.close();
		dao_gB.close();
		dao_re.close();
		dao_im.close();
		dao_mu.close();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		dao = new MemberDAO();
		dao_gB = new GuestbookDAO();
		dao_re = new ReplyDAO();
		dao_im = new ImagesDAO();
		dao_mu = new MusicDAO();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		// 로그인한 아이디
		String login_id = (String)req.getSession().getAttribute("login_id");
		// 홈페이지 주인의 아이디
		String id = request.getParameter("id");
		// 페이지 구분
		String page = request.getParameter("page");
		
		// 로그인체크
		if (login_id == null) {
			JSFunction.alertLocation(res ,"회원만 접근 가능합니다.", "/cyworld/cyworld/index.jsp");
			return;
		}
		
		// 홈페이지 주인이 방명록페이지를 접속 시 실행
		if (id.equals(login_id) && page.equals("guestBook")) {
			dao.updateLastView(id);
		}
		
		// 홈페이지 주인의 정보
		request.setAttribute("dto", dao.getMember(id));
		// 로그인한 유저의 정보
		request.setAttribute("login_dto", dao.getMember(login_id));
		// 최신 방명록의 제목
		request.setAttribute("lastTitle", dao_im.lastTitle(id));
		// 오늘 날짜로 작성된 사진첩의 유무, true : 존재 | false : 미존재
		request.setAttribute("dateNew", dao_im.hasNewImages(id));
		// 홈페이지 주인의 최신 방명록 확인 유무, treu : 확인 | false : 미확인
		request.setAttribute("isGBCheck", dao_gB.isGBCheck(id));
		// 방명록 출력을 위한 List
		request.setAttribute("guestbook_list", dao_gB.getGuestbook_list(id));
		// 사진첩 출력을 위한 List
		request.setAttribute("images_list", dao_im.getImagesList(id));
		// 주크박스 출력을 위한 List
		request.setAttribute("music_list", dao_mu.getMusicList(id));
		// 활성화중인 곡 List
		request.setAttribute("music_list_true", dao_mu.getMusicListTrue(id));
		// 활성화중인 곡의 제목 List
		request.setAttribute("music_list_title", dao_mu.getMusicList_title(id));
		// 활성화중인 곡의 경로 List
		request.setAttribute("music_list_path", dao_mu.getMusicList_path(id));
		
		chain.doFilter(request, response);
		
	}
	
}
