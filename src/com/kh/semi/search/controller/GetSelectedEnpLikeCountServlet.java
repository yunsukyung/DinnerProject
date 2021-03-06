package com.kh.semi.search.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.semi.search.model.service.SearchService;

@WebServlet("/getLikeCount.se")
public class GetSelectedEnpLikeCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSelectedEnpLikeCountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String enpNo = request.getParameter("enpNo");
		
		int likeCount = new SearchService().getLikeCount(enpNo);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		new Gson().toJson(likeCount, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
