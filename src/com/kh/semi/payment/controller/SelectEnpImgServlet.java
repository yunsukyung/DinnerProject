package com.kh.semi.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.semi.payment.model.service.ReservationService;

@WebServlet("/selectEnpimg.im")
public class SelectEnpImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SelectEnpImgServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eNo = request.getParameter("enpNo");
		String name = new ReservationService().selectEnpImg(eNo);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(name, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
