package exception;

public class Fix1to100 {
	public String fix(int errNo) {
		if(errNo == 55) {
			System.out.println("Missing Vehicle Name!!! Load Default Value");
			return new String("Default Vehicle");
		}else if(errNo == 75) {
			System.out.println("Missing BasePrice!! Load Default BasePrice");
			return new String("0");
		}else {
			return new String();
		}
	}

}
