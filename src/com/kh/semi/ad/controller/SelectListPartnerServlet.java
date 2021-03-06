package com.kh.semi.ad.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.ad.model.service.PartnerService;
import com.kh.semi.ad.model.vo.PartnerVO;
import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.notice.model.service.NoticeService;
import com.kh.semi.notice.model.vo.NoticeVO;

/**
 * Servlet implementation class SelectListPartnerServlet
 */
@WebServlet("/selectList.pa")
public class SelectListPartnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectListPartnerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;
		
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		limit = 10;
		
		int listCount = new PartnerService().getListCount();
		
		maxPage = (int)((double) listCount / limit + 0.9);
		
		startPage = (((int) ((double) currentPage / 10 + 0.9)) - 1) * 10 + 1;
		
		endPage = startPage + 10 - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		ArrayList<PartnerVO> list = new PartnerService().selectList(pi);
		
		String page = "";
		
		if(list != null) {
			page = "/views/admin/partnerQuestion/partnerQuestionList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회 실패!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
