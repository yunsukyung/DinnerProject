package com.kh.semi.enterprise.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.enterprise.model.service.EnpService;

/**
 * Servlet implementation class DeleteConfirmRequestServlet
 */
@WebServlet("/deleteRequest.en")
public class DeleteConfirmRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteConfirmRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String enp = request.getParameter("enpId");
		String rno = request.getParameter("rno");
		
		
		int result = new EnpService().deleteCrList(rno);
		
		
		String page = "";
		if(result > 0 ) {
			page = "selectConfirmRequestList.en";
			request.setAttribute("enp", enp);
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("msg", "예약 삭제 실패");
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
