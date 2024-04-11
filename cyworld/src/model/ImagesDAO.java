package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.JDBConnect;

public class ImagesDAO extends JDBConnect {
	
	// 튜플 추가
	public int insertImages(ImagesDTO dto) {
		int result = 0;
		
		String sql = "insert into images (id, title, content, imgName) values(?,?,?,?)";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getImgName());
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// id값에 해당하는 튜플 전체 반환
	public List<ImagesDTO> getImagesList(String id){
		List<ImagesDTO> list = new ArrayList<>();
		String sql = "select * from images where id = ? order by no desc";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ImagesDTO dto = new ImagesDTO();
				
				dto.setNo(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				
				String date = rs.getString(5).substring(0, 16);
				dto.setCreated(date);
				dto.setImgName(rs.getString(6));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 오늘 작성된 게시물이 있는지 검사, true : 있음, false : 없음
	public boolean hasNewImages(String id) {
		String sql = "select count(*) from images where id=? and DATE_FORMAT(created, '%Y-%m-%d') = DATE_FORMAT(now(), '%Y-%m-%d')";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			rs.next();
			if (rs.getInt(1) != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// 최신 게시물의 제목 반환
	public String lastTitle(String id) {
		String title = "";
		
		String sql = "select title from images where id=? order by created desc limit 1";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				title = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return title;
	}
	
}
