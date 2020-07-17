package com.kh.semi.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.notice.model.dao.ClientNoticeDao;
import com.kh.semi.notice.model.vo.NoticeVO;

import static com.kh.semi.common.JDBCTemplate.*;

public class ClientNoticeService {

	public int insertClientNotice(NoticeVO newNotice) {
		
		Connection con = getConnection();
		
		int result = new ClientNoticeDao().insertClientNotice(con, newNotice);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	
	public int getListCount() {
		
		Connection con = getConnection();
		
		int listCount = new ClientNoticeDao().getListCount(con);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<NoticeVO> selectList(PageInfo pi) {
	Connection con = getConnection();
		
		ArrayList<NoticeVO> list = new ClientNoticeDao().selectList(con, pi);
		
		close(con);
		
		return list;
	}
	
	//개인회원 공지 관리자 상세보기용 
	public NoticeVO selectOne(int cnno) {

		Connection con = getConnection();

		int result = 0;

		NoticeVO notice = new ClientNoticeDao().selectOne(con, cnno);

		if(notice != null) {

			result = new ClientNoticeDao().updateCount(con, cnno);

			if(result > 0) {
				commit(con);
			} else {
				rollback(con);
				notice = null;
			}
		}

		close(con);

		return notice;

	}
}
