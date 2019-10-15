package driver;

import Adapter.*;
import scale.*;
import java.io.*;
import java.util.*;
import server.*;
import client.*;
import client.DefaultSocketClient;

import java.net.*;

//I implement the synchronization on the functions that include CRUD operation since a lot of other
//functions use them, I think it's the best to synchronize it there.

public class Driver {

	public static void main(String[] args) {
		
		BuildAuto ca = new BuildAuto();
		
		ca.BuildAuto("TeslaModel3.txt");
		ca.BuildAuto("FordWagonZTW.txt");
		ca.BuildAuto("ToyotaCorolla.txt");		//File WIthout Name and Price
		ca.BuildAuto("xxXXx.txt");					//File Not Existing

	
//		ca.printAuto("Tesla Model 3");
//		ca.printAuto("Ford Wagon ZTW");
//		ca.printAuto("Toyota Corolla SE");		//No Object named Toyota Corolla SE	
		
		ca.setOptionChoice("Tesla Model 3", "Battery", "Dual Motor All-Wheel Drive(Performance)");
		ca.setOptionChoice("Tesla Model 3", "Battery", "Dual Motor All-Wheel Drive(Long Range)");
		ca.setOptionChoice("Ford Wagon ZTW", "Transmission", "manual");
		ca.setOptionChoice("Ford Wagon ZTW", "Brakes/Traction Control", "ABS with Advance Trac");
		
//		ca.printAll();	//Print All the Automobile stored in BuildAuto
//		ca.printTotalPrice("Tesla Model 3");
//		ca.printTotalPrice("Toyota Corolla SE");
//		ca.printTotalPrice("Ford Wagon ZTW");
		
		Scalable s = ca;
		
		for(int i = 0; i < 10; i++) {
		s.editOptions("Tesla Model 3", "Color", "Solid Black", "Midnight Black", 11000 );
		//ca.printAuto("Tesla Model 3");
		s.editOptions("Tesla Model 3", "Color", "Midnight Black", "Solid Black", 11000 );
		//ca.printAuto("Tesla Model 3");
		}
		
		InetAddress local = null;
		
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.err.println("Identity Crisis!!!");
			System.exit(0);
		}
		
		//Creating a Property file
		writePropertiesFiles("Features1.txt");
		writePropertiesFiles("Features2.txt");
		writePropertiesFiles("Features3.txt");
		//Creating a Property File
		
	}
	
	public static void writePropertiesFiles(String txtFname) {
		try {
			String line;
			Properties p = new Properties();
			BufferedReader br = new BufferedReader(new FileReader(txtFname));
			while((line = br.readLine()) != null)
				p.put(line.split(",")[0].strip(), line.split(",")[1].strip());
			String propFname = txtFname.replace(".txt", ".prop");
			p.store(new FileWriter(propFname), "Storing properties from " + txtFname);
			br.close();
		}catch(IOException IOE) {
			System.exit(0);
		}
	}
}

