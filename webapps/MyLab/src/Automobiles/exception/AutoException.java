package exception;
import java.io.*;
import java.util.*;
import java.text.*;

enum errEnum{
	model(1, 100), util(101, 200), exception(201, 300), adapter(301, 400), 
	thread(401, 500), socket(501, 600), web(701, 700), DEFAULT(-1, -1);
	
	private int errMin;
	private int errMax;
	private errEnum(int errMin, int errMax) {
		this.errMin = errMin;
		this.errMax = errMax;
	}
	public int getMin() {
		return this.errMin;
	}
	public int getMax() {
		return this.errMax;
	}
	public static errEnum generate(int num){
		for(errEnum E:errEnum.values()) {
			if(num <= E.getMax() && num >= E.getMin())
				return E;
		}
		return errEnum.DEFAULT;
	}
	@Override
	public String toString() {
		return "  AutoException: " + " (" +  this.name() + ") ";
	}
}

public class AutoException extends Exception{
	private int errno;
	public AutoException(int errno) {
		super("Error Code: " + Integer.toString(errno) + '\t' + errEnum.generate(errno).toString());
		this.errno = errno;
	}
	public AutoException(int errno, String errMsg) {
		super("Error Code: " + Integer.toString(errno) + '\t' + errMsg + errEnum.generate(errno).toString());
		this.errno = errno;
	}
	
	public int getErrno() {
		return errno;
	}
	public void setErrno(int errno) {
		this.errno = errno;
	}
	
	public String fix(int errNo) {
		switch(errEnum.generate(errno)) {
			case model:
				System.out.println(this.getMessage());
				log();
				return new Fix1to100().fix(errNo);
			case util:
			case exception:
			case adapter:
			case thread:
			case socket:
			case web:
				System.out.println(this.getMessage());
				log();
				return new String();
			case DEFAULT:
			default:
				return new String();
		}		
	}
	public void log() {
		try {
		BufferedWriter bw = new BufferedWriter(new FileWriter("Log.txt", true));
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
		bw.write(formatter.format(d) + '\n');
		bw.write("Error Code: " + Integer.toString(errno) + '\t' + errEnum.generate(this.errno).toString() + '\n');
		bw.write(this.toString() + '\n');
		bw.write('\n');
		bw.close();
		}catch(IOException IE) {
			System.out.println(IE);
		}
	}
}
