package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.JDBConnect;

public class ReplyDAO extends JDBConnect {
	
	// 튜플 추가
	public int insertGuestbookreply(ReplyDTO dto) {
		int result = 0;
		
		String sql = "insert into guestbookreply (g_no, content, id) values(?,?,?)";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getG_no());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 튜플 삭제
	public int deleteReply(String r_no) {
		int result = 0;
		
		String sql = "delete from guestbookreply where r_no = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, r_no);
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
