package test;

public class Dog {
	private static Dog instance; //null
	
	private Dog() {
		
	}
	
	public static Dog getInstance() {
		//만일 instance 변수가 null이라면,
		//여기서 new하여 인스턴스를 채워주면 됨
		if(instance==null) {
			instance=new Dog();
		}
		
		return instance;
	}
	
	public String getName() {
		return "마루";
	}
}
