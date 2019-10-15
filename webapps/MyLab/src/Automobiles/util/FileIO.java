package util;
import java.io.*;
import java.util.*;
import exception.AutoException;
import model.Automobile;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class FileIO {
	public Automobile buildAutoObject(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			boolean eof = false; 
			int lineCount = 0;
			while(!eof) {
				lineCount++;
				if(br.readLine() == null) {
					eof = true;
				}
			}
			eof = false;
			br.close();
			
			br = new BufferedReader(new FileReader(filename));
			String name, floatString;
			try {
				if((name = br.readLine()).isEmpty())throw new AutoException(55, "Missing Model Name");
			}catch(AutoException AE) {
				name = AE.fix(AE.getErrno());
			}
			float baseprice = 0;
			try {
				if((floatString = br.readLine()).isEmpty()) throw new AutoException(75, "Missing Float Value for Price");
			}catch(AutoException AE) {
				floatString = AE.fix(AE.getErrno());
			}
			baseprice = Float.parseFloat(floatString);
			Automobile auto = new Automobile(name, baseprice, lineCount - 2);
			while(!eof) {
				String line = br.readLine();
				if(line == null) {
					eof = true;
				}
				else {
					String[] NameAndOption = line.split(":");					
					String[] AllOptions = NameAndOption[1].strip().split(",");
					try {
						if(AllOptions == null) throw new AutoException(255, "Null OptionSet Exception");
					}catch(AutoException AE) {
						AllOptions = AE.fix(AE.getErrno()).split("");
					}
					auto.setOptionSet(AllOptions, NameAndOption[0]);
				}
			}
			br.close();
			return auto;
		}catch(IOException IE) {
			System.out.println(IE.toString());
		}
		return new Automobile();
	}
	public Automobile buildAutoObjectWeb(BufferedReader br, int lineCount, String filename) {
		try {
			String name, floatString;
			try {
				if((name = br.readLine()).isEmpty())throw new AutoException(55, "Missing Model Name");
			}catch(AutoException AE) {
				name = AE.fix(AE.getErrno());
			}
			float baseprice = 0;
			try {
				if((floatString = br.readLine()).isEmpty()) throw new AutoException(75, "Missing Float Value for Price");
			}catch(AutoException AE) {
				floatString = AE.fix(AE.getErrno());
			}
			baseprice = Float.parseFloat(floatString);
			Automobile auto = new Automobile(name, baseprice, lineCount - 2);
			boolean eof = false;
			while(!eof) {
				String line = br.readLine();
				if(line == null) {
					eof = true;
				}
				else {
					String[] NameAndOption = line.split(":");					
					String[] AllOptions = NameAndOption[1].strip().split(",");
					try {
						if(AllOptions == null) throw new AutoException(255, "Null OptionSet Exception");
					}catch(AutoException AE) {
						AllOptions = AE.fix(AE.getErrno()).split("");
					}
					auto.setOptionSet(AllOptions, NameAndOption[0]);
				}
			}
			br.close();
			return auto;
		}catch(IOException IE) {
			System.out.println(IE.toString());
		}
		return new Automobile();
	}
	public void serializeAuto(Automobile auto) {
		try {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("auto.ser"));
		oos.writeObject(auto);
		oos.close();
		}catch(IOException e) {
			System.out.println(e.toString());
		}
	}
	public Automobile deSerializeAuto() {
		Automobile auto = new Automobile();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("auto.ser"));
			auto = (Automobile)ois.readObject();
			ois.close();
			}catch(IOException e) {
				System.out.println(e.toString());
			}catch(ClassNotFoundException ce) {
				System.out.println("Class Not Found: " + ce.toString());
			}
		return auto;	
	}
}