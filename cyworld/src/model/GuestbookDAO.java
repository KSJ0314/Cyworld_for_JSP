package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;

public class GuestbookDAO extends JDBConnect {
	
	// id값이 일치하는 튜플 전체 반환 + 각 튜플에 해당하는 no값을 참조하는 guestbookreply table의 튜플 포함
	public List<GuestbookDTO> getGuestbook_list(String id) {
		List<GuestbookDTO> list = new ArrayList<>();
		
		String sql = "select guestbook.*, imgName from guestbook join member on member.id=guestbook.post_id where guestbook.id=? order by no desc";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				GuestbookDTO dto = new GuestbookDTO();
				int no = rs.getInt(1);
				dto.setNo(no);
				dto.setId(rs.getString(2));
				dto.setPost_id(rs.getString(3));
				String date = rs.getString(4).substring(0, 16);
				dto.setCreated(date);
				dto.setContent(rs.getString(5));
				dto.setImgName(rs.getString(6));
				dto.setReply(getReplyDTO(no));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// no값으로 guestbookreply의 튜플 전체 반환
	public List<ReplyDTO> getReplyDTO(int g_no){
		List<ReplyDTO> list = new ArrayList<>();
		String sql = "select * from guestbookreply where g_no = ? order by r_no desc";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, g_no);
			
			// getGuestbook_list()에서 반복문을 이용해 rs를 사용중에 이 메서드를 실행하는 것이므로 rs를 사용하면 안된다.
			ResultSet rs2 = psmt.executeQuery();
			while(rs2.next()) {
				ReplyDTO dto = new ReplyDTO();
				dto.setR_no(rs2.getString(1));
				dto.setContent(rs2.getString(3));
				String date = rs2.getString(4).substring(0, 16);
				dto.setCreated(date);
				dto.setId(rs2.getString(5));
				list.add(dto);
			}
			rs2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 튜플 추가
	public int insertGuestbook(GuestbookDTO dto) {
		int result = 0;
		
		String sql = "insert into guestbook (id, post_id, content) values(?, ?, ?)";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPost_id());
			psmt.setString(3, dto.getContent());
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 홈피 주인의 최신 방명록 확인 유무를 판단, ture : 확인, false : 미확인
	public boolean isGBCheck(String id) {
		int max_no = 0;
		
		String sql = "select max(no) from guestbook where id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				max_no = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		MemberDAO dao = new MemberDAO();
		int last_view = dao.getLast_view(id);
		dao.close();
		
		return max_no == last_view;
	}
	
	// 튜플 삭제
	public void deleteBook(String no) {
		String sql = "delete from guestbook where no=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
