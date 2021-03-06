package com.kh.semi.payment.model.dao;

import java.sql.Statement;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore.ProtectionParameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.enterprise.model.vo.EnpVO;
import com.kh.semi.payment.model.vo.AdminReservationVo;
import com.kh.semi.payment.model.vo.PaymentHistoryVO;
import com.kh.semi.payment.model.vo.PointVO;
import com.kh.semi.payment.model.vo.ReservationVO;
import static com.kh.semi.common.JDBCTemplate.*;
public class ReservationDao {
	private Properties prop = new Properties();
	
	public ReservationDao() {
		String fileName = ReservationDao.class.getResource("/sql/reservation/reservation-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertReservation(Connection con, ReservationVO insertReservationVO) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertReservation");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setTimestamp(1, insertReservationVO.getrDate());
			pstmt.setString(2, insertReservationVO.getmNo());
			pstmt.setString(3, insertReservationVO.geteNo());
			pstmt.setString(4, insertReservationVO.getcNo());
			pstmt.setString(5, insertReservationVO.getRqMemo());
			pstmt.setInt(6, insertReservationVO.getpAmount());
			pstmt.setInt(7, insertReservationVO.getPeople());
			pstmt.setInt(8, insertReservationVO.getDeposit());
			
			System.out.println("결제 dao : " + insertReservationVO.geteNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertReservationHistory(Connection con, ReservationVO insertReservationVO, int sequence) {
		PreparedStatement pstmt= null;
		int result = 0;
		String query = prop.getProperty("insertReservationHistory");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "RES"+(sequence-1));
			pstmt.setInt(2, insertReservationVO.getPeople());
			pstmt.setTimestamp(3, insertReservationVO.getrDate());
			pstmt.setInt(4, insertReservationVO.getDeposit());
			pstmt.setString(5, insertReservationVO.getmNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<ReservationVO> selectReservation(Connection con, String mNo) {
		ArrayList<ReservationVO> list = new ArrayList<ReservationVO>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectReservationList");
		ReservationVO rvo = null;
		try {
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				rvo = new ReservationVO();
				rvo.setrNo(rset.getString("RESERVATION_NO"));
				rvo.setrDate(rset.getTimestamp("RESERVATION_DATE"));
				rvo.setmNo(mNo);
				rvo.seteNo(rset.getString("ENP_NO"));
				rvo.setcNo(rset.getString("CALC_NO"));
				rvo.setRqMemo(rset.getString("REQUEST_MEMO"));
				rvo.setpAmount(rset.getInt("POINT_AMMOUNT"));
				rvo.setPeople(rset.getInt("PEOPLE"));
				rvo.setSysDate(rset.getTimestamp("RESERVATION_REQUEST_DATE"));
				rvo.setDeposit(rset.getInt("DEPOSIT"));
				
				list.add(rvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

	public int selectReservationSequence(Connection con) {
		
		Statement stmt = null;
		int result = 0;
		String query2 = prop.getProperty("selectSequence");
		ResultSet rset = null;
		System.out.println(query2);
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query2);
			if(rset.next()) {
				System.out.println("LAST_NUMBER : " + rset.getString("LAST_NUMBER"));
				result = Integer.parseInt(rset.getString("LAST_NUMBER"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		return result;
	}

	public ArrayList<String> selectEnpList(Connection con, ArrayList<ReservationVO> list) {
		
		String query = prop.getProperty("selectEnpname");
		ArrayList<String> enpList = new ArrayList<>();
		int count = 0;
		while(true) {
			String ename = "";
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				
				pstmt = con.prepareStatement(query);
				System.out.println("list : " + list.size());
				if(list.size() > 0) {
					pstmt.setString(1, list.get(count).geteNo());
					rset = pstmt.executeQuery();
					if(rset.next()) {
						ename = rset.getString("ENP_NAME");
						
						enpList.add(ename);
						count ++;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rset);
			}
			
			if(list.size() == count || list.size() == 0) break;
		}

		return enpList;
	}

	public ArrayList<String> selectStatusList(Connection con, ArrayList<ReservationVO> list) {
		ArrayList<String> statusList = new ArrayList<String>();
		int count = 0;
		String query = prop.getProperty("selectStatusList");


		while(true) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			if(list.size() > 0) {
			String status = "";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, list.get(count).getrNo());

			rset = pstmt.executeQuery();
			if(rset.next()) {
				status = rset.getString("STATUS_CODE");
				statusList.add(status);
				count++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		if(list.size() == count) break;
		}
		
		return statusList;
	}



	public String selectEnpName(Connection con, String eNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectEnpName");
		String eName = "";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, eNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				eName = rset.getString("ENP_NAME");
				System.out.println(eName);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return eName;
	}

	public int deleteReserInfo(Connection con, String rNo, String mNo, int deposit, int point) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteReserInfo");
		int result = 0;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, rNo);
			pstmt.setInt(2, deposit);
			pstmt.setString(3, mNo);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int insertPaymentHistory(Connection con, PaymentHistoryVO payHistoryVO) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertPaymentHistory");
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, payHistoryVO.getPayPrice());
			pstmt.setString(2, payHistoryVO.getmNo());
			pstmt.setInt(3, payHistoryVO.getpAmount());
			pstmt.setString(4, payHistoryVO.getrNo());
			pstmt.setString(5, payHistoryVO.getpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public PaymentHistoryVO selectPayment(Connection con, String rNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPayment");
		PaymentHistoryVO paymentHistoryVO = null;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, rNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				paymentHistoryVO = new PaymentHistoryVO();
				paymentHistoryVO.setpAmount(rset.getInt("USE_POINT_AMMOUNT"));
				paymentHistoryVO.setPayPrice(rset.getInt("PAY_PRICE"));
				paymentHistoryVO.sethNo(rset.getString("PAY_HISTORY_NO"));
				paymentHistoryVO.setpNo(rset.getString("PAY_UNIQUE_CODE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return paymentHistoryVO;
	}

	public int deletePayInfo(Connection con, String rNo, String mNo, String pNo, int deposit, int point) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deletePayInfo");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, deposit);
			pstmt.setString(2, mNo);
			pstmt.setInt(3, point);
			pstmt.setString(4, rNo);
			pstmt.setString(5, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public EnpVO selectEnpInfo(Connection con, String eNo) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("selectEnpInfo");
		ResultSet rset = null;
		EnpVO evo = null;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, eNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				evo = new EnpVO();
				evo.setEnpName(rset.getString("ENP_NAME"));
				evo.setEnpAddress(rset.getString("ENP_ADDRESS"));
				evo.setIntroduce(rset.getString("INTRODUCE"));
				evo.setEnpPhone(rset.getString("ENP_PHONE"));
				evo.setPartnerName(rset.getString("PARTNER_NAME"));
				evo.setEnpRegisterNo(rset.getString("ENP_REGISTER_NO"));
				evo.setDepositLowerLimit(rset.getInt("DEPOSIT_LOWER_LIMIT"));
				evo.setDepositHigherLimit(rset.getInt("DEPOSIT_HIGHER_LIMIT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return evo;
	}

	public String selectEnpImg(Connection con, String eNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectEnpImg");
		String eimg = "";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, eNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				eimg = rset.getString("CHANGE_NAME");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return eimg;
	}

	public int selectPointAmount(Connection con, String mNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int pAmount = 0;
		String query = prop.getProperty("selectPointAmount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				pAmount = rset.getInt("PAMOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return pAmount;
	}

	public int insertPointUse(Connection con, PointVO pointVO) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertPointUse");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pointVO.getmNo());
			pstmt.setString(2, pointVO.getSaveStatue());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertPointUseHistory(Connection con, PointVO pointVO, int seq) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertPointUseHistory");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, seq);
			pstmt.setString(2, pointVO.getmNo());
			pstmt.setInt(3, -pointVO.getpAmount());
			pstmt.setString(4, pointVO.getSaveStatue());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int selectPointSeq(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int seq = 0;
		String query = prop.getProperty("selectPointSeq");
		try {
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				seq = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return seq;
	}

	public int insertPointGive(Connection con, PointVO pointVO) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertPointGive");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pointVO.getmNo());
			pstmt.setString(2, pointVO.getSaveCode());
			pstmt.setString(3, pointVO.getSaveStatue());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insertPointGiveHistory(Connection con, PointVO pointVO, int seq) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertPointGiveHistory");
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, seq);
			pstmt.setString(2, pointVO.getmNo());
			pstmt.setString(3, pointVO.getSaveStatue());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
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
		} finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}

	public ArrayList<AdminReservationVo> selectList(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AdminReservationVo> list = null;
		AdminReservationVo ar;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
			int endRow = startRow + pi.getLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				ar = new AdminReservationVo();
				ar.setReservationNo(rset.getString("RESERVATION_NO"));
				ar.setReservationDate(rset.getTimestamp("RESERVATION_DATE"));
				ar.setMemberNo(rset.getString("MEMBER_NO"));
				ar.setMemberId(rset.getString("MEMBER_ID"));
				ar.setMemberName(rset.getString("MEMBER_NAME"));
				ar.setEnpNo(rset.getString("ENP_NO"));
				ar.setEnpName(rset.getString("ENP_NAME"));
				ar.setCalcNo(rset.getString("CALC_NO"));
				ar.setRequestMemo(rset.getString("REQUEST_MEMO"));
				ar.setPointAmmount(rset.getInt("POINT_AMMOUNT"));
				ar.setPeople(rset.getInt("PEOPLE"));
				ar.setDeposit(rset.getInt("DEPOSIT"));
				ar.setStatusCode(rset.getString("STATUS_CODE"));
				ar.setStatusName(rset.getString("STATUS_NAME"));
				ar.setPayDate(rset.getDate("PAY_DATE"));
				
				
				list.add(ar);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		
		return list;
	}
}












