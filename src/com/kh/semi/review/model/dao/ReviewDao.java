package com.kh.semi.review.model.dao;

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
import java.util.List;
import java.util.Properties;

import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.review.model.vo.ReviewAttachment;
import com.kh.semi.review.model.vo.ReviewVO;

public class ReviewDao {
	Properties prop = new Properties();
	
	public ReviewDao() {
		String fileName = ReviewDao.class.getResource("/sql/review/review-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int countReview(Connection con, String enpNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("countReview");
		int count = 0;
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, enpNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public int countTypeReview(Connection con, String enpNo, String type) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("countTypeReview");
		int count = 0;
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, enpNo);
			pstmt.setString(2, type);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public List<ReviewVO> getSelectedEnpVisitReviews(Connection con, String enpNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("getEnpTypeReviews");
		List<ReviewVO> visitReviews = null;
		
		try {
			pstmt = con.prepareStatement(query);
			
			visitReviews = new ArrayList<>();
			
			pstmt.setString(1, enpNo);
			pstmt.setString(2, "방문");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReviewVO r = new ReviewVO();
				r.setReviewNo(rset.getString("REVIEW_NO"));
				r.setReviewContent(rset.getString("REVIEW_CONTENT"));
				r.setMemberNo(rset.getString("MEMBER_NO"));
				r.setReviewDate(rset.getDate("REVIEW_DATE"));
				r.setReviewType(rset.getString("REVIEW_TYPE"));
				r.setEnpNo(rset.getString("ENP_NO"));
				r.setVisitDate(rset.getDate("VISIT_DATE"));
				r.setAverageRating(rset.getDouble("AVERAGE_RATING"));
				r.setReservationHistoryNo(rset.getString("RESERVATION_HISTORY_NO"));
				
				visitReviews.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return visitReviews;
	}

	public List<ReviewVO> getSelectedEnpNormalReviews(Connection con, String enpNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("getEnpTypeReviews");
		List<ReviewVO> normalReviews = null;
		
		try {
			pstmt = con.prepareStatement(query);
			
			normalReviews = new ArrayList<>();
			
			pstmt.setString(1, enpNo);
			pstmt.setString(2, "일반");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReviewVO r = new ReviewVO();
				r.setReviewNo(rset.getString("REVIEW_NO"));
				r.setReviewContent(rset.getString("REVIEW_CONTENT"));
				r.setMemberNo(rset.getString("MEMBER_NO"));
				r.setReviewDate(rset.getDate("REVIEW_DATE"));
				r.setReviewType(rset.getString("REVIEW_TYPE"));
				r.setEnpNo(rset.getString("ENP_NO"));
				r.setVisitDate(rset.getDate("VISIT_DATE"));
				r.setAverageRating(rset.getDouble("AVERAGE_RATING"));
				r.setReservationHistoryNo(rset.getString("RESERVATION_HISTORY_NO"));
				
				normalReviews.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return normalReviews;
	}
	
	public List<ReviewVO> getReviewFilePaths(Connection con, List<ReviewVO> reviews) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("getReviewFiles");
		List<ReviewVO> reviewsWithFiles = null;
		
		try {
			pstmt = con.prepareStatement(query);
			
			reviewsWithFiles = new ArrayList<>();
			
			for(int i = 0; i < reviews.size(); i++) {
				String reviewNo = reviews.get(i).getReviewNo();

				pstmt.setString(1, reviewNo);
				
				rset = pstmt.executeQuery();
				
				String[] filePaths = new String[2];
				int j = 0;
				while(rset.next()) {
					filePaths[j] = rset.getString("FILE_PATH");
					j++;
				}
				
				reviews.get(i).setFilePaths(filePaths);
				
				reviewsWithFiles.add(reviews.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return reviewsWithFiles;
	}

	public String[] checkVisit(Connection con, String mNo, String enpNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("checkVisit");
		String[] datas = new String[2];
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, mNo);
			pstmt.setString(2, enpNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				datas[0] = rset.getString("RESERVATION_HISTORY_NO");
				datas[1] = rset.getString("VISIT_DATE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return datas;
	}

	public int insertReview(Connection con, ReviewVO review) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReview");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, review.getReviewContent());
			pstmt.setString(2, review.getMemberNo());
			pstmt.setString(3, review.getReviewType());
			pstmt.setString(4, review.getEnpNo());
			pstmt.setDate(5, review.getVisitDate());
			pstmt.setDouble(6, review.getAverageRating());
			pstmt.setString(7, review.getReservationHistoryNo());
			
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
		String reviewNo = "";
		
		String query = prop.getProperty("selectCurrval");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				int id = rset.getInt("currval");
				
				reviewNo = "RE"+id;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		
		
		return reviewNo;
	}

	public int insertAttachment(Connection con, ReviewAttachment reviewAttachment) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, reviewAttachment.getOriginName());
			pstmt.setString(2, reviewAttachment.getChangeName());
			pstmt.setString(3, reviewAttachment.getFilePath());
			pstmt.setString(4, reviewAttachment.getReviewNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public ArrayList<ReviewVO> selectList(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewVO> list = null;
		ReviewVO re;
		
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
				re = new ReviewVO();
				re.setReviewNo(rset.getString("REVIEW_NO"));
				re.setReviewContent(rset.getString("REVIEW_CONTENT"));
				re.setMemberId(rset.getString("MEMBER_ID"));
				re.setReviewDate(rset.getDate("REVIEW_DATE"));
				re.setReviewType(rset.getString("REVIEW_TYPE"));
				re.setEnpName(rset.getString("ENP_NAME"));
				re.setOriginName(rset.getString("ORIGIN_NAME"));
				re.setChangeName(rset.getString("CHANGE_NAME"));
				re.setFilePath(rset.getString("FILE_PATH"));
				re.setEnpNo(rset.getString("ENP_NO"));
				
				list.add(re);
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

	public int deleteReview(Connection con, String reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteReview");
		
		System.out.println("query : " + query);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, reviewNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteAttachment(Connection con, String reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteAttachment");
		System.out.println("query : " + query);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, reviewNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int insertPoint(Connection con, ReviewVO review) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertPoint");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, review.getMemberNo());
			pstmt.setString(2, "적립");
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectPointCurrval(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		String pointNo = "";
		
		String query = prop.getProperty("selectPointCurrval");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				int id = rset.getInt("currval");
				
				pointNo = "POINT"+id;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		
		
		return pointNo;
	}

	public int insertPointHistory(Connection con, String pointNo, ReviewVO review) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertPointHistory");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pointNo);
			pstmt.setString(2, review.getMemberNo());
			pstmt.setString(3, "적립");
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String getEnpName(Connection con, String enpNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("getEnpName");
		String enpName = "";
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, enpNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				enpName = rset.getString("ENP_NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return enpName;
	}
}
