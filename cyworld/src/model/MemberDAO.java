package model;

import java.sql.SQLException;

import common.JDBConnect;

public class MemberDAO extends JDBConnect {
	
	// 튜플 추가
	public int addMember(MemberDTO dto) {
		int result = 0;
		
		String sql = "insert into member values(?,?,?,?";
		if (dto.getIsAdmin().equals("admin")) {
			sql += ", true";
		} else {
			sql += ", default";
		}
		sql += ",?,0)";
		
		System.out.println(dto.getImgName());
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getTel());
			psmt.setString(5, dto.getImgName());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// id값에 해당하는 튜플이 있는지 검사, 없음 : 0 | 있음 : 1
	public int hasMember(String id) {
		int result = 0;
		
		String sql = "select count(*) from member where id = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 로그인 체크, 0 : 로그인 성공, 1 : 아이디 오류, 2 : 비번 오류
	public int login(String id, String pw) {
		int result = 2;
		
		String sql = "select pw from member where id = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(pw)) {
					result = 0;
				}
			} else {
				result = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// id값으로 튜플 반환, (id, imgName 컬럼만 반환중)
	public MemberDTO getMember(String id) {
		MemberDTO dto = new MemberDTO();
		
		String sql = "select * from member where id = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString(1));
				// dto.setPw(rs.getString(2));
				// dto.setEmail(rs.getString(3));
				// dto.setTel(rs.getString(4));
				// dto.setIsAdmin(rs.getString(5));
				dto.setImgName(rs.getString(6));
				// dto.setLast_view(rs.getInt(7));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// 홈피 주인의 최신 방명록 확인 유무를 판단하기 위한 last_view값 반환
	public int getLast_view(String id) {
		int last_view = 0;
		
		String sql = "select last_view from member where id = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				last_view = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return last_view;
	}
	
	// id에 해당하는 튜플의 last_view의 값을 guestbook의 최신 번호(no)로 업데이트
	public void updateLastView(String id) {
		
		String sql = "update member set last_view = (select max(no) from guestbook where id=?) where id=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, id);
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
