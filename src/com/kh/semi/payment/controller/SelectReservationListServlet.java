package com.kh.semi.payment.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.vo.MemberVO;
import com.kh.semi.payment.model.dao.ReservationDao;
import com.kh.semi.payment.model.service.ReservationService;
import com.kh.semi.payment.model.vo.ReservationVO;

@WebServlet("/selectList.py")
public class SelectReservationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectReservationListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		
		String mNo = member.getmNo();
		ArrayList<ReservationVO> list = new ReservationService().selectReservationList(mNo);
		ArrayList<String> enpList = new ReservationService().selectEnpList(list);
		ArrayList<String> statusList = new ReservationService().selectStatusList(list);
		
		System.out.println(statusList);
		String page = "";
		if(list != null) {
			page = request.getContextPath() + "/views/myPage/detailsOfPayandReservation.jsp";
			session.setAttribute("list", list);
			session.setAttribute("enpList", enpList);
			session.setAttribute("statusList", statusList);
		} else {
			
		}
		response.sendRedirect(page);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
