

package server;

import Adapter.*;
import model.*;
import java.util.*;
import java.io.*;

public class BuildCarModelOptions extends proxyAutomobile {

	////////// PROPERTIES //////////

	private static final int WAITING = 0;
	private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;
	private int state;

	public Object processRequest(Object obj, int choice) {
		Object toClient = null;
		state = choice;
		
		if (state == REQUEST_BUILD_AUTO) {
			if(obj.getClass().equals(java.util.Properties.class)) {
			Properties props = (Properties)obj;
			OptionSet opSet = new OptionSet();
			Automobile auto = new Automobile();
			Enumeration name = props.propertyNames();
			String modelName = new String(), opSetName = new String();
			ArrayList<Option> arrLst = new ArrayList<Option>();
			
			while(name.hasMoreElements()) {
				String n = name.nextElement().toString();
				if(n.equals("ModelName"))
					modelName = props.getProperty(n);
				else if(n.equals("OptionSet")) {
					opSetName = props.getProperty(n);
					opSet.setName(opSetName);
				}
				else
					arrLst.add(new Option(n, Integer.parseInt(props.getProperty(n))));
			}
			auto = AlMap.get(modelName);
			if(auto != null) {
			opSet.setOpt(arrLst);
			auto.addOptionSet(opSet);
			AlMap.put(modelName, auto);}
			} else {
				Automobile auto = (Automobile)obj;
				AlMap.put(auto.getName(), auto);
			}
			toClient = AlMap;
		}	else if (state == REQUEST_CONFIGURE_AUTO) {
				int size = AlMap.size();
				if(size > 0) {
			    String[] keyArr = new String[AlMap.size()];
			    keyArr = AlMap.keySet().toArray(keyArr);
			    Scanner sc = new Scanner(System.in);
			    for(int i = 0; i < keyArr.length; i++)
			    	System.out.println(Integer.toString(i) + ". " + keyArr[i]);
			    System.out.print("Please choose a option: ");
			    int userChoice = Integer.parseInt(sc.nextLine());
				String modelName = keyArr[userChoice];
				if(AlMap.get(modelName) == null) {
					Automobile newAuto = new Automobile();
					newAuto.setName(modelName);
					toClient = newAuto;
				} else toClient = AlMap.get(modelName);
				AlMap.put(modelName, (Automobile)toClient);
				}else System.out.println("No Automobile in Server to Choose");
		} else
			this.state = WAITING;
		return toClient;
	}

	public String setRequest(int i) {
		String output = null;

		if (i == 1) {
			this.state = REQUEST_BUILD_AUTO;
			output = "Upload a file to create an Automobile";
		}
		else if (i == 2) {
			this.state = REQUEST_CONFIGURE_AUTO;
			output = "Start Adding Configuring an existing Automobile";
		}
		else
			output = "Invalid request";
		return output;
	}
}
