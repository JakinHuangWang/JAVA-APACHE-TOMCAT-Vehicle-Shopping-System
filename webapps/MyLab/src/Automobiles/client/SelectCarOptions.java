
package client;

import model.*;
import Adapter.*;
import java.io.*;
import java.util.*;

public class SelectCarOptions extends proxyAutomobile{
	
	public void configureAuto(Object obj) {
		Automobile auto = (Automobile)obj;
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		String modelName = auto.getName(), OpSetName, OpName;
		float OpPrice;
		
		System.out.print("Please Input an OptionSet name to Add(Press q to quit): ");
		while((OpSetName = br.readLine()) != null && !OpSetName.equals("q")) {
			System.out.print("Please Input an Option Name(Press q to quit): ");
			ArrayList<Option> arrLst = new ArrayList<Option>();
			while((OpName = br.readLine()) != null && !OpName.equals("q")) {
				System.out.print("Please Input a number for price: ");
				OpPrice = Float.parseFloat(br.readLine());
				arrLst.add(new Option(OpName, OpPrice));
				System.out.print("Please Input an Option Name(Press q to quit): ");
			}
			auto.addOptionSet(new OptionSet(OpSetName, arrLst));
			System.out.print("Please Input an OptionSet name(Press q to quit): ");
		}
		AlMap.put(modelName, auto);
		}catch(IOException IOE) {
			System.err.println(IOE);
		}
	}

	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;

		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
		}
		catch (ClassCastException e) {
			isAutomobile = false;
		}
		return isAutomobile;
	}

}
