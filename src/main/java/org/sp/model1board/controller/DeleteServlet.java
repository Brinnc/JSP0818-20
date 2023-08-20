package org.sp.model1board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sp.model1board.model.BoardDAO;

//파라미터를 받아와 오라클에 레코드 1건 삭제->DAO
public class DeleteServlet extends HttpServlet{
	BoardDAO boardDAO;
	
	@Override
	public void init() throws ServletException {
		boardDAO=new BoardDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//파라미터 받기
		int board_idx=Integer.parseInt(request.getParameter("board_idx"));
		
		int result=boardDAO.delete(board_idx);
		
		response.setContentType("text/html;charset=utf-8"); //JSP에서의 페이지 지시영역과 동일한 효과
		PrintWriter out=response.getWriter();
		
		out.print("<script>");
		if(result>0) {
			out.print("alert('성공');");
			out.print("location.href='/board/list.jsp';");
		}else {
			out.print("alert('실패');");
			out.print("history.back();");
		}
		out.print("</script>");
		
		
	}
}
