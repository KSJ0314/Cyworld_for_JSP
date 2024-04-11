package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;

public class MusicDAO extends JDBConnect {
	// 튜플 추가
	public int insertMusic(MusicDTO dto) {
		int result = 0;
		String sql = "insert into music (id, path, title, artist) values(?,?,?,?)";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPath());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getArtist());
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 곡 퍼가기
	public void dupleMusic(String id, String login_id, String[] noArr) {
		List<MusicDTO> list = new ArrayList<>();
		for (String no : noArr) {
			list.add(getMusic(no));
		}
		
		for (MusicDTO dto : list) {
			dto.setId(login_id);
			String sql = "insert into music (id, path, title, artist) values(?,?,?,?)";
			
			try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPath());
				psmt.setString(3, dto.getTitle());
				psmt.setString(4, dto.getArtist());
				psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// no값에 해당하는 튜플 반환
	public MusicDTO getMusic(String no){
		MusicDTO dto = new MusicDTO();
		String sql = "select * from music where no = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setPath(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setArtist(rs.getString(5));
				dto.setIsPlay(rs.getBoolean(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// id값에 해당하는 튜플 전체 반환
	public List<MusicDTO> getMusicList(String id){
		List<MusicDTO> list = new ArrayList<>();
		String sql = "select * from music where id = ? order by no desc";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MusicDTO dto = new MusicDTO();
				dto.setNo(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setPath(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setArtist(rs.getString(5));
				dto.setIsPlay(rs.getBoolean(6));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 활성화중인 곡 전체 반환
	public List<MusicDTO> getMusicListTrue(String id){
		List<MusicDTO> list = new ArrayList<>();
		String sql = "select * from music where id = ? and isPlay=true order by no desc";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MusicDTO dto = new MusicDTO();
				dto.setNo(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setPath(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setArtist(rs.getString(5));
				dto.setIsPlay(rs.getBoolean(6));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 활성화중인 곡의 제목 반환
	public List<String> getMusicList_title(String id){
		List<String> list = new ArrayList<>();
		String sql = "select title from music where id = ? and isPlay = true order by no desc";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 활성화중인 곡의 경로 반환
	public List<String> getMusicList_path(String id){
		List<String> list = new ArrayList<>();
		String sql = "select path from music where id = ? and isPlay = true order by no desc";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 홈화면에 재생 목록 변경
	public void music_play(String[] no) {
		// 전체 비활성화
		String sql = "update music set isPlay = false";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 선택 항목 활성화
		if (no == null) {
			return;
		}
		for (String str : no) {
			String sql2 = "update music set isPlay = true where no = ?";
			
			try {
				psmt = con.prepareStatement(sql2);
				psmt.setString(1, str);
				psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
