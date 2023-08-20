package org.sp.model1board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sp.model1board.domain.Board;
import org.sp.model1board.model.BoardDAO;

//클라이언트가 전송한 수정폼의 내용을 넘겨받아 오라클에 업데이트문 수행->DAO
public class EditServlet extends HttpServlet{
	BoardDAO boardDAO;
	
	@Override
	public void init() throws ServletException {
		boardDAO=new BoardDAO();
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기 (총 4개)
		//update board set title=넘겨받은값, writer=, content=넘값
		//where board_idx=사용자가 읽은 바로 그글의 idx
		request.setCharacterEncoding("utf-8"); //파라미터에 대한 인코딩
		
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		int board_idx=Integer.parseInt(request.getParameter("board_idx"));
		
		System.out.println(title);
		System.out.println(writer);
		System.out.println(content);
		System.out.println(board_idx);
		
		Board board=new Board(); //빈 DTO
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setBoard_idx(board_idx);
		
		//DAO에게 수정 시키기
		int result=boardDAO.update(board);
		
		response.setContentType("text/html;charset=utf-8"); //JSP에서의 페이지 지시영역과 동일한 효과
		PrintWriter out=response.getWriter();
		
		out.print("<script>");
		if(result>0) {
			out.print("alert('성공');");
			out.print("location.href='/board/content.jsp?board_idx="+board_idx+"';");
		}else {
			out.print("alert('실패');");
			out.print("history.back();");
		}
		out.print("</script>");
	}
}
