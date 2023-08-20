package test;

public class ExceptionTest {
	
	//test메서드 호출부에게 예외처리를 전가함 -> throws
	public void test() throws NumberFormatException{
		String msg="1교시";
		
		//개발자에게 try~catch 처리를 강요하지 않는 예외를 가리켜 RuntimeException이라하며
		//RuntimeException의 처리 여부는 오직 개발자의 몫
		int x=Integer.parseInt(msg);
		System.out.println(x);
		/*
		try {
		}catch(NumberFormatException e) {
			System.out.println("알맞은 숫자형 문자를 사용하십시오");
		}
		*/
	}
	
	public static void main(String[] args) {
		ExceptionTest et=new ExceptionTest();
		try {
			et.test();
		}catch(NumberFormatException e) {
			System.out.println("알맞은 숫자형 문자를 사용하십시오");
		}
		
	}
}
