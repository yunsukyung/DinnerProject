package com.kh.semi.enterprise.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semi.enterprise.model.vo.EnpAttachment;
import com.kh.semi.enterprise.model.vo.EnpUpVo;
import com.kh.semi.enterprise.model.vo.EnpVO;
import com.kh.semi.enterprise.model.vo.PageInfo;
import com.kh.semi.payment.model.vo.ReservationVO;

public class EnpDao {
Properties prop = new Properties();
   
   public EnpDao() {
      String fileName = EnpDao.class.getResource("/sql/enp/enp-query.properties").getPath();
      try {
         prop.load(new FileReader(fileName));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   //제휴업체 회원가입 일반enterprise에 정보 기입
   public int insertPartnerEnp1(Connection con, EnpVO requestEnp) {
      PreparedStatement pstmt = null;
      int result = 0;
      String query = prop.getProperty("insertPartnerEnp1");
      
      try {
         pstmt = con.prepareStatement(query);
//         아래 입력 내용은 쿼리에 따라 달라질 수 있음
         /*
         ENP_NO, ENP_NAME, ENP_PHONE, ENP_ADDRESS, ENP_HOUR, ENP_TYPE, 
         ENP_STATUS, ENP_PARTNER_TYPE,PRICE_RANGE,CLOSED_DAY,WEBSITE,INTRODUCE,PARKING_POSSIBLE
         */
         pstmt.setString(1, requestEnp.getEnpName());
         pstmt.setString(2, requestEnp.getEnpPhone());
         pstmt.setString(3, requestEnp.getEnpAddress());
         pstmt.setString(4, requestEnp.getEnpHour());
         pstmt.setString(5, requestEnp.getEnpType());
         pstmt.setString(6, requestEnp.getPriceRange());
         pstmt.setString(7, requestEnp.getClosedDay());
         pstmt.setString(8, requestEnp.getWebsite());
         pstmt.setString(9, requestEnp.getIntroduce());
         pstmt.setString(10, requestEnp.getParkingPossible());
         
         
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }

   //제휴업체 회원가입 partnerEnp에 값넣기
   public int insertPartnerEnp2(Connection con, EnpVO requestEnp) {
	   PreparedStatement pstmt = null;
	      int result = 0;
	      String query = prop.getProperty("insertPartnerEnp2");
	      
	      try {
	         pstmt = con.prepareStatement(query);
//	         아래 입력 내용은 쿼리에 따라 달라질 수 있음
	         /*--ENP_NO	VARCHAR2(20 BYTE)
	         --ENP_REGISTER_NO	VARCHAR2(10 BYTE)
	         --PARTNER_CODE	VARCHAR2(20 BYTE)
	         --PENALTY_COUNT	NUMBER(3,0)
	         --PARTNER_ID	VARCHAR2(20 BYTE)
	         --PARTNER_PWD	VARCHAR2(128 BYTE)
	         --PARTNER_EMAIL	VARCHAR2(30 BYTE)
	         --PARTNER_NAME	VARCHAR2(15 BYTE)
	         --ACCOUNT_HOLDER	VARCHAR2(15 BYTE)
	         --BANK	VARCHAR2(30 BYTE)
	         --BANK_ACCOUNT	NUMBER(14,0)
	         --DEPOSIT_LOWER_LIMIT	NUMBER(7,0)
	         --DEPOSIT_HIGHER_LIMIT	NUMBER(7,0)
	         --SIGNUP_APPROVAL	VARCHAR2(1 BYTE)
	         --JUMIN_NO	VARCHAR2(14 BYTE)
	         --ENP_LICENCE	VARCHAR2(6 BYTE)*/
	         pstmt.setString(1, requestEnp.getEnpRegisterNo());
	         pstmt.setString(2, requestEnp.getPartnerId());
	         pstmt.setString(3, requestEnp.getPartnerPwd());
	         pstmt.setString(4, requestEnp.getPartnerEmail());
	         pstmt.setString(5, requestEnp.getPartnerName());
	         pstmt.setString(6, requestEnp.getPartnerName());
	         pstmt.setString(7, requestEnp.getBank());
	         pstmt.setString(8,requestEnp.getBankAccount());
	         pstmt.setInt(9, 0);
	         pstmt.setInt(10, 1000000);
	         pstmt.setString(11, requestEnp.getJuminNo());
	         pstmt.setString(12, requestEnp.getEnpLicense());
	         
	         
	         result = pstmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      return result;
   }
   
   
//가게정보업로드
public int insertEnterprise(Connection con, EnpUpVo enpUp) {
	PreparedStatement pstmt = null;
	int result = 0;
	String query = prop.getProperty("insertEnterprise");
	
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, enpUp.getEnpName());
		pstmt.setString(2, enpUp.getEnpPhone());
		pstmt.setString(3, enpUp.getEnpAddress());
		pstmt.setString(4, enpUp.getEnpHour());
		pstmt.setString(5, enpUp.getEnpType());
		pstmt.setString(6, enpUp.getHashTags());
		pstmt.setString(7, enpUp.getPriceRange());
		pstmt.setString(8, enpUp.getClosedDay());
		pstmt.setString(9, enpUp.getWebsite());
		pstmt.setString(10, enpUp.getIntroduce());
		pstmt.setString(11, enpUp.getParkingPossible());
		
		result = pstmt.executeUpdate();
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	
	return result;
}

public String selectCurrval(Connection con) {
	Statement stmt = null;
	ResultSet rset = null;
	String enpNo = "";
	
	String query = prop.getProperty("selectCurrval");

	
	try {
		stmt = con.createStatement();
		rset = stmt.executeQuery(query);
		
		if(rset.next()) {
			int id= rset.getInt("currval");
			
			enpNo = "ENP" + id;
			
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		close(stmt);
		close(rset);
	}
	
	
	return enpNo;
}

public int insertAttachment(Connection con, EnpAttachment enpAttachment) {
	PreparedStatement pstmt = null;
	int result = 0;
	
	String query = prop.getProperty("insertAttachment");

	
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, enpAttachment.getOriginName());
		pstmt.setString(2, enpAttachment.getChangeName());
		pstmt.setString(3, enpAttachment.getFilePath());
		pstmt.setString(4, enpAttachment.getEnpNo());
		
		result = pstmt.executeUpdate();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	
	
	
	return result;
}

public int insertMenu(Connection con, EnpUpVo enpUp) {
	PreparedStatement pstmt = null;
	int result = 0;
	
	String query = prop.getProperty("insertMenu");
	
	
	
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, enpUp.getMenuName());
		pstmt.setInt(2, enpUp.getMenuPrice());
		pstmt.setString(3, enpUp.getEnpNo());
		
		
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	
	return result;
}

public EnpVO loginCheck(Connection con, EnpVO requestEnp) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	EnpVO loginEnp = null;
	
	String query = prop.getProperty("loginCheck");
	
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, requestEnp.getPartnerId());
		pstmt.setString(2, requestEnp.getPartnerPwd());
		
		rset = pstmt.executeQuery();
		if(rset.next()) {
			loginEnp = new EnpVO();
			loginEnp.setEnpNo(rset.getString("ENP_NO"));
			loginEnp.setEnpRegisterNo(rset.getString("ENP_REGISTER_NO"));
			loginEnp.setPartnerCode(rset.getString("PARTNER_CODE"));
			loginEnp.setPenaltyCount(rset.getInt("PENALTY_COUNT"));
			loginEnp.setPartnerId(rset.getString("PARTNER_ID"));
			loginEnp.setPartnerPwd(rset.getString("PARTNER_PWD"));
			loginEnp.setPartnerEmail(rset.getString("PARTNER_EMAIL"));
			loginEnp.setPartnerName(rset.getString("PARTNER_NAME"));
			loginEnp.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
			loginEnp.setBank(rset.getString("BANK"));
			loginEnp.setBankAccount(rset.getString("BANK_ACCOUNT"));
			loginEnp.setDepositLowerLimit(rset.getInt("DEPOSIT_LOWER_LIMIT"));
			loginEnp.setDepositHigherLimit(rset.getInt("DEPOSIT_HIGHER_LIMIT"));
			loginEnp.setSignupApproval(rset.getString("SIGNUP_APPROVAL"));
			loginEnp.setJuminNo(rset.getString("JUMIN_NO"));
			loginEnp.setEnpLicense(rset.getString("ENP_LICENCE"));
			loginEnp.setEnpName(rset.getString("ENP_NAME"));
			loginEnp.setEnpPhone(rset.getString("ENP_PHONE"));
			loginEnp.setEnpAddress(rset.getString("ENP_ADDRESS"));
			loginEnp.setEnpHour(rset.getString("ENP_HOUR"));
			loginEnp.setEnpType(rset.getString("ENP_TYPE"));
			loginEnp.setEnpStatus(rset.getString("ENP_STATUS"));
			loginEnp.setEnpPartnerType(rset.getString("ENP_PARTNER_TYPE"));
			loginEnp.setHashTags(rset.getString("HASH_TAGS"));
			loginEnp.setPriceRange(rset.getString("PRICE_RANGE"));
			loginEnp.setClosedDay(rset.getString("CLOSED_DAY"));
			loginEnp.setWebsite(rset.getString("WEBSITE"));
			loginEnp.setIntroduce(rset.getString("INTRODUCE"));
			loginEnp.setParkingPossible(rset.getString("PARKING_POSSIBLE"));
			loginEnp.setUploadApproval(rset.getString("UPLOAD_APPROVAL"));
			
			/*ENP_NO ENP_REGISTER_NO PARTNER_CODE PENALTY_COUNT PARTNER_ID PARTNER_PWD PARTNER_EMAIL PARTNER_NAME ACCOUNT_HOLDER BANK
			BANK_ACCOUNT DEPOSIT_LOWER_LIMIT DEPOSIT_HIGHER_LIMIT SIGNUP_APPROVAL JUMIN_NO ENP_LICENCE ENP_NAME ENP_PHONE ENP_ADDRESS ENP_HOUR
			ENP_TYPE ENP_STATUS ENP_PARTNER_TYPE HASH_TAGS PRICE_RANGE CLOSED_DAY
			WEBSITE INTRODUCE PARKING_POSSIBLE UPLOAD_APPROVAL*/
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		close(pstmt);
		close(rset);
	}
	
	
	return loginEnp;
}

public ArrayList<ReservationVO> selectCRList(Connection con, PageInfo pi, String enp) {
	ArrayList<ReservationVO> requestReserve = null;
	PreparedStatement pstmt = null;
	ResultSet rset= null;
	
	String query = prop.getProperty("selectList");
	try {
		pstmt = con.prepareStatement(query);
		
		int startRow = (pi.getCurrentPage() -1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);
		pstmt.setString(3, enp);
		
		rset = pstmt.executeQuery();
		
		requestReserve = new ArrayList<>();
		
		while(rset.next()) {
			/*Board b = new Board();
			b.setBid(rset.getInt("BID"));
			b.setbType(rset.getInt("BTYPE"));
			b.setBno(rset.getInt("BNO"));
			b.setcName(rset.getString("CNAME"));
			b.setbTitle(rset.getString("BTITLE"));
			b.setbContent(rset.getString("BCONTENT"));
			b.setNickName(rset.getString("NICK_NAME"));
			b.setbCount(rset.getInt("BCOUNT"));
			b.setRefBid(rset.getInt("REF_BID"));
			b.setReplyLevel(rset.getInt("REPLY_LEVEL"));
			b.setbDate(rset.getDate("BDATE"));
			b.setStatus(rset.getString("STATUS"));
			
			list.add(b);*/
			
			ReservationVO r = new ReservationVO();
			r.setcNo(rset.getString("CALC_NO"));
			r.seteNo(rset.getString("ENP_NO"));
			r.setmNo(rset.getString("MEMBER_NO"));
			r.setpAmount(rset.getInt("POINT_AMMOUNT"));
			r.setPeople(rset.getInt("PEOPLE"));
			r.setrDate(rset.getDate("TO_CHAR(RESERVATION_REQUEST_DATE,'RRRR/MM/DD/HH24/MI/SS')"));
			r.setrNo(rset.getString("RESERVATION_NO"));
			r.setRqMemo(rset.getString("REQUEST_MEMO"));
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
		close(rset);
	}
	
	
	return requestReserve;
}

public int getListCount(Connection con) {
	Statement stmt = null;
	int listCount = 0;
	ResultSet rset = null;
	
	String query = prop.getProperty("listCount");
	try {
		stmt = con.createStatement();
		rset = stmt.executeQuery(query);
		if(rset.next()) {
			listCount = rset.getInt(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(stmt);
		close(rset);
	}
	
	return listCount;
}



}