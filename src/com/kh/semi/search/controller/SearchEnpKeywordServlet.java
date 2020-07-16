package com.kh.semi.search.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.semi.enterprise.model.vo.EnpVO;
import com.kh.semi.search.model.service.SearchService;

@WebServlet("/searchKeyWord.se")
public class SearchEnpKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchEnpKeywordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchWord = request.getParameter("search");
		String keyword = request.getParameter("keyword");
		String[] words = {searchWord, keyword};
		
		List<EnpVO> enpList = new SearchService().searchKeyword(words);
		List<HashMap<String, Integer>> enpMenus = new SearchService().getMenus(enpList);
		
		String page = "views/searchResult/searchResult.jsp";
		request.getSession().setAttribute("search", searchWord);
		request.getSession().setAttribute("enpList", enpList);
		request.getSession().setAttribute("enpMenus", enpMenus);
		response.sendRedirect(page);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
