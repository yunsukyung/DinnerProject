package com.kh.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.board.model.service.BoardService;
import com.kh.semi.board.model.vo.BoardUpVo;
import com.kh.semi.board.model.vo.History;

/**
 * Servlet implementation class SelectOneBoardServlet
 */
@WebServlet("/selectOneBoard.up")
public class SelectOneBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOneBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardNo = request.getParameter("no");
		
		HttpSession session = request.getSession();
		
		BoardUpVo board = new BoardService().selectOneBoard(boardNo);
		ArrayList<HashMap<String, Object>> list2 = null;
		
		History history = new BoardService().selectHistory(boardNo);
		
			
		list2 = new BoardService().selectThumbnailList(boardNo);
		
		ArrayList<BoardUpVo> replyList = new BoardService().selectReplyList(boardNo);
		
		String page = "";
		if(list2 != null) {
			page="views/admin/reviewConfirm/reviewConfirmDetail.jsp";
			session.setAttribute("list2", list2);
			session.setAttribute("board", board);
			session.setAttribute("boardNo", boardNo);
			request.setAttribute("replyList", replyList);
			request.setAttribute("history", history);
		} else {
			page="views/common/errorPage.jsp";
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
