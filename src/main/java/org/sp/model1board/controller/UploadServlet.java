package org.sp.model1board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

//평상시와는 달리 클라이언트가 전송한 파라미터에 바이너리 파일도 포함되어 있으므로
//기존에 사용하던 리퀘스트 내장객체만으로는 파라미터 처리를 해결할 수 없음
//해결책? 개발자가 개발해야함..
//따라서 이미 개발이 완료된 외부의 컴포넌트를 사용하면 됨(유/무료)
public class UploadServlet extends HttpServlet{
	
	//바이너리 파일이 포함된 경우 POST 방식만 이용가능
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 multipart/form-data 속성으로 요청을 시도하면 서버측에서는 기존의 request객체로는 파라미터를 받을 수 없음
		//해결책) 유/무료 컴포넌트 이용
		//아래 객체가 바로 업로드를 수행하는 객체이며, 업로드는 생성자 호출 시 동작함
		String path="D:/morning/javaee_workspace/Model1_board/src/main/webapp/data"; 
		MultipartRequest multi=new MultipartRequest(request, path);
		System.out.println("업로드 완료");
		//String title=request.getParameter("title");
		//String photo=request.getParameter("photo");
		
		//System.out.println("제목은 "+title);
		//System.out.println("파일은 "+photo);
		
		
	}
}
