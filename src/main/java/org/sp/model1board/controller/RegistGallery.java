package org.sp.model1board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.sp.model1board.domain.Gallery;
import org.sp.model1board.model.GalleryDAO;

public class RegistGallery extends HttpServlet{
	DiskFileItemFactory factory; //업로드에 관련된 설정을 담당함
	int maxSize=1024*1024*2; //2MB
	
	String path;
	String filename; //새롭게 생성된 파일명
	GalleryDAO galleryDAO;
	Gallery gallery;
	
	String encoding="utf-8";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		galleryDAO=new GalleryDAO();
		factory=new DiskFileItemFactory();
		
		//용량 제한
		factory.setSizeThreshold(maxSize);
		//서버의 어디에 저장할지 : 경로(파일 자료형) appication.getRealPath()
		//application은 JSP의 내장객체이므로, 서블릿으로 개발 시는 사용할 수 없음
		//따라서 application 내장 객체의 자료형을 직접 다룰 줄 알아야 함
		//ServletContext 자료형이며, javaEE api
		//ServletContext context를 얻어오는 방법
		//1) requst 객체를 통해 얻기 -> 단, init()메서드에서는 얻을 수 없음. service()메서드에서 
		//2) ServletConfig 객체를 통해 얻기
		ServletContext context=config.getServletContext();
		path=context.getRealPath("/data");
		System.out.println(path);
		
		File file=new File(path);
		factory.setRepository(file);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트에게 전송할 응답정보
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		//클라이언트가 전송한 파라미터들을 받기
		//만약 복합된 형식인 경우(multipart), 기존의 request만으로는 처리불가 -> 컴포넌트 이용
		
		//팩토리를 이용해 설정이 끝났다면, ServletFileUpload를 이용해 파일 업로드를 실행
		ServletFileUpload upload=new ServletFileUpload(factory);
		boolean flag=false; //업로드 성공 여부를 판단할 수 있는 논리값
		try {
			List<FileItem> itemList=upload.parseRequest(request); //요청분석
			
			gallery=new Gallery(); //빈 DTO
			
			//improved loop 개선된 반복문
			for(FileItem item : itemList) {
				if(item.isFormField()){ //input type="text" 형태의 파라미터
					String param=item.getString();
					
					if(item.getFieldName().equals("title")) { //제목 채우기
						gallery.setTitle(item.getString(encoding));
					}else if(item.getFieldName().equals("writer")) { //작성자 채우기
						gallery.setWriter(item.getString(encoding));
					}else if(item.getFieldName().equals("content")) { //내용 채우기
						gallery.setContent(item.getString(encoding));
					}
					
				}else { //input type="file" 형태의 파라미터
					//지정한 경로에 파일 저장하기
					
					long time=System.currentTimeMillis();
					//String ext=전송된 파일의 이름에서 확장자 추출;
					String ext=item.getName().substring(item.getName().lastIndexOf(".")+1, item.getName().length());
					filename=time+"."+ext; //a12345.jpg
					
					gallery.setFilename(filename); //DTO에 파일명 채우기
					
					File uploadFile=new File(path, filename); //(디렉토리-파일경로, 파일명)
					
					item.write(uploadFile); //디스트에 내려쓰기
					System.out.println("서버에 저장된 파일은 "+uploadFile);
				}
			}
			flag=true;
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		//메세지 출력 후 list페이지로 돌아감
		out.print("<script>");
		if(flag) { //차일 저장이 성공되었다면 DB insert 진행
			int result=galleryDAO.insert(gallery);
			
			if(result>0) {
				out.print("alert('등록 성공');");
				out.print("location.href='/gallery/list.jsp';"); //재접속을 유도
			}else {
				out.print("alert('등록 실패');");
				out.print("history.back();"); //재접속을 유도
			}
			
		}else {
			out.print("alert('파일이 저장되지 않음');");
			out.print("history.back();");
		}
		out.print("</script>");
	}
}
