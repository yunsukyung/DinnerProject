package com.kh.semi.enterprise.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.enterprise.model.dao.EnpDao;
import com.kh.semi.enterprise.model.vo.EnpAttachment;
import com.kh.semi.enterprise.model.vo.EnpUpVo;
import com.kh.semi.enterprise.model.vo.EnpVO;
import com.kh.semi.enterprise.model.vo.ForCmVO;
import com.kh.semi.enterprise.model.vo.ForCrInfoVO;
import com.kh.semi.enterprise.model.vo.ForEntCrVO;
import com.kh.semi.enterprise.model.vo.ForPhVO;
import com.kh.semi.enterprise.model.vo.ForSdVO;
import com.kh.semi.enterprise.model.vo.PageInfo;
import com.kh.semi.enterprise.model.vo.PartnerEnpVO;
import com.kh.semi.notice.model.dao.NoticeDao;
import com.kh.semi.notice.model.vo.NoticeVO;
import com.kh.semi.payment.model.vo.ReservationVO;

public class EnpService {

	public int insertPartnerEnp(EnpVO requestEnp) {
		Connection con = getConnection();

		int result = new EnpDao().insertPartnerEnp1(con, requestEnp);

		int result2 = new EnpDao().insertPartnerEnp2(con, requestEnp);

		result += result2;

		if (result > 1) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	public int insertEnterprise(EnpUpVo enpUp, ArrayList<EnpAttachment> fileList) {
		Connection con = getConnection();
		int result = 0;

		int result1 = 0;

		int result2 = 0;

		int result3 = 0;

		result1 = new EnpDao().insertEnterprise(con, enpUp);
		if (result1 > 0) {
			String enpNo = new EnpDao().selectCurrval(con);

			for (int i = 0; i < fileList.size(); i++) {
				fileList.get(i).setEnpNo(enpNo);
				enpUp.setEnpNo(enpNo);

				result2 += new EnpDao().insertAttachment(con, fileList.get(i));

			}
			result3 = new EnpDao().insertMenu(con, enpUp);
		}

		if (result1 > 0 && result3 > 0 && result2 == fileList.size()) {
			commit(con);
			result = 1;
		} else {
			rollback(con);
		}

		return result;
	}

	public EnpVO loginCheck(EnpVO requestEnp) {
		Connection con = getConnection();

		EnpVO loginEnp = new EnpDao().loginCheck(con, requestEnp);

		close(con);

		return loginEnp;
	}

	public ArrayList<ReservationVO> selectCRList(PageInfo pi, String enp) {
		Connection con = getConnection();

		ArrayList<ReservationVO> requestReserve = new EnpDao().selectCRList(con, pi, enp);

		close(con);

		return requestReserve;
	}

	public int getListCount(String enp) {
		Connection con = getConnection();

		int listCount = new EnpDao().getListCount(con,enp);

		close(con);

		return listCount;
	}

	public ArrayList<ForEntCrVO> selectCRModalList(String memId) {
		Connection con = getConnection();

		ArrayList<ForEntCrVO> modalList = new EnpDao().selectCRModalList(con, memId);

		close(con);

		return modalList;
	}

	public String selectCRMemId(String enp) {
		Connection con = getConnection();

		String modalId = new EnpDao().selectCRMemId(con, enp);

		close(con);

		return modalId;
	}

	public ArrayList<Integer> selectCRCount(String countId, String enp, ArrayList<ReservationVO> checkCountList) {
		Connection con = getConnection();
		ArrayList<Integer> count = new ArrayList<Integer>();
		for (ReservationVO v : checkCountList) {

			String mNo = v.getmNo();

			int a = new EnpDao().selectCRCount(con, countId, enp, mNo);

			count.add(a);
		}

		close(con);

		return count;
	}

	public EnpVO selectEnp(String enpNo) {
		Connection con = getConnection();
		EnpVO selectedEnp = new EnpDao().selectEnp(con, enpNo);

		close(con);

		return selectedEnp;
	}

	public ArrayList<ForCmVO> selectCmList(String enp) {
		Connection con = getConnection();

		ArrayList<ForCmVO> list = null;
		ArrayList<ForCmVO> cmList = null;

		list = new EnpDao().selectCmMemberVal(con, enp);
		cmList = new EnpDao().selectCmFilePath(con, list);

		close(con);

		return cmList;
	}

	public int insertComment(String reviewNum, String comment, String enpNo) {
		Connection con = getConnection();

		int result = new EnpDao().insertComment(con, reviewNum, comment, enpNo);

		int result2 = new EnpDao().updateReplyStatus(con, reviewNum);

		if (result > 0 && result2 > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	public String getEnpFile(String enpNo) {
		Connection con = getConnection();
		String filePath = new EnpDao().getEnpFile(con, enpNo);

		close(con);

		return filePath;
	}

	public int selectCRRownum(String enp) {

		return 0;
	}

	public String checkId(String id) {
		Connection con = getConnection();
		String checkEnp = new EnpDao().checkId(con, id);

		close(con);

		return checkEnp;
	}

	public ArrayList<ReservationVO> selectRDList(PageInfo pi, String enp, String requestDay) {
		Connection con = getConnection();

		ArrayList<ReservationVO> requestReserve = new EnpDao().selectRDList(con, pi, enp, requestDay);

		close(con);

		return requestReserve;
	}

	public ArrayList<ForEntCrVO> selectRDModalList(String memId, String requestDay) {
		Connection con = getConnection();

		ArrayList<ForEntCrVO> modalList = new EnpDao().selectRDModalList(con, memId, requestDay);

		close(con);

		return modalList;
	}

	public int uploadEnp(EnpUpVo enp) {
		Connection con = getConnection();
		int result = 0;
		result = new EnpDao().uploadEnp(con, enp);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return result;
	}

	public int getSDListCount(String enp) {
		Connection con = getConnection();

		int listCount = new EnpDao().getSDListCount(con, enp);

		close(con);

		return listCount;
	}

	public ArrayList<ForSdVO> selectSDList(PageInfo pi, String enp) {
		Connection con = getConnection();

		ArrayList<ForSdVO> requestReserve = new EnpDao().selectSDList(con, pi, enp);

		close(con);

		return requestReserve;
	}

	public int updateCrList(String rno) {
		Connection con = getConnection();

		int result = new EnpDao().updateCrList(con, rno);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return result;
	}

	public int deleteCrList(String rno) {
		Connection con = getConnection();

		int result = new EnpDao().deleteCrList(con, rno); 

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return result;
	}

	public ArrayList<ForCrInfoVO> selectCrInfoModalList(String enp) {
		Connection con = getConnection();

		ArrayList<ForCrInfoVO> modalList = new EnpDao().selectCrInfoModalList(con, enp);

		close(con);

		return modalList;
	}

	public int getRDListCount(String enp,String requestDay) {
		Connection con = getConnection();
		int result = new EnpDao().getRDListCount(con,enp, requestDay);
		return result;
	}

	public ArrayList<ForPhVO> selectPHList(PageInfo pi, String enp) {
		Connection con = getConnection();
		ArrayList<ForPhVO> resList = new EnpDao().selectPHList(con,pi,enp);
		
		close(con);
		
		return resList;
	}

	public int getPHListCount(String enp) {
		Connection con = getConnection();
		
		int result = new EnpDao().getPHListCount(con,enp);
		
		close(con);
		
		return result;
	}

	public ArrayList<ForPhVO> selectSum(ArrayList<ForPhVO> resList, String enp) {
		Connection con = getConnection();
		
		ArrayList<ForPhVO> list = new EnpDao().selectSum(con,resList,enp);
		
		close(con);
		
		return list;
	}

	public ArrayList<ForPhVO> getCalcList(String enp) {
		Connection con = getConnection();
		ArrayList<ForPhVO> calcList = new EnpDao().getCalcList(con,enp);
		
		close(con);
		
		
		return calcList;
	}

	public ArrayList<ForPhVO> selectPhInfoList(ArrayList<ForPhVO> calcList) {
		Connection con = getConnection();
		ArrayList<ForPhVO> list = new EnpDao().selectPhInfoList(con,calcList);
		
		close(con);

		return list;
	}

	public int getPartnerListCount() {
		
		Connection con = getConnection();
		
		int listCount = new EnpDao().getPartnerListCount(con);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<PartnerEnpVO> selectPartnerList(PageInfo pi) {
		
		Connection con = getConnection();
		
		ArrayList<PartnerEnpVO> list = new EnpDao().selectPartnerList(con, pi);
		
		close(con);
		
		return list;
	}

	public PartnerEnpVO selectPartnerOne(int pacNo) {

		Connection con = getConnection();

		PartnerEnpVO partnerEnp = new EnpDao().selecPartnertOne(con, pacNo);

		close(con);

		return partnerEnp;
	}

	public int deletepartner(String enpNo) {
	
		Connection con = getConnection();

		int result = new EnpDao().deletePartner(con, enpNo);

		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);

		return result;
	}

	public int insertException(String enpNo, String enpId, String reason, String enpName, String tel, String email) {
		Connection con = getConnection();
		
		int result = new EnpDao().insertException(con,enpNo,enpId,reason,enpName,tel,email);
		
		if(result >0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}


	
}