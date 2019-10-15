package Adapter;
import model.*;
import exception.*;
import util.*;
import scale.*;
import java.util.*;
import java.io.*;

public abstract class proxyAutomobile implements CreateAuto, UpdateAuto, FixAuto, BuildChoice, Scalable{
	protected static LinkedHashMap<String, Automobile> AlMap = new LinkedHashMap<String, Automobile>();
	
	public LinkedHashMap<String, Automobile> getALMAP() {
		return AlMap;
	}
	
	public void BuildAuto(String filename) {
		if(!new File(filename).exists())
			filename = fixFile();
		Automobile auto = new FileIO().buildAutoObject(filename);
		if(auto.getBaseprice() == 0)
			fix(auto);
		AlMap.put(auto.getName(), auto);
	}
	
	public void BuildAutoWeb(BufferedReader br, int lineCount, String filename) {
		if(!new File(filename).exists())
			filename = fixFile();
		Automobile auto = new FileIO().buildAutoObjectWeb(br, lineCount, filename);
		if(auto.getBaseprice() == 0)
			fix(auto);
		AlMap.put(auto.getName(), auto);
	}
	
	public Automobile ConstructAuto(String filename) {
		if(!new File(filename).exists())
			filename = fixFile();
		Automobile auto = new FileIO().buildAutoObject(filename);
		if(auto.getBaseprice() == 0)
			fix(auto);
		return auto;
	}
	
	public Automobile ConstructAutoWeb(BufferedReader br, int lineCount, String filename) {
		if(!new File(filename).exists())
			filename = fixFile();
		Automobile auto = new FileIO().buildAutoObjectWeb(br, lineCount, filename);
		if(auto.getBaseprice() == 0)
			fix(auto);
		return auto;
	}
	
	@Override
	//Load default price value if the price is 0
	public void fix(Automobile al) {
		al.setBasePrice(20000);
	}
	//Load Perfect File if file not found
	public String fixFile() {
		System.out.println("Loading Perfect File: ");	
		return "PerfectFile.txt";
	}
	
	public void printAuto(String Modelname) {
		if(AlMap.containsKey(Modelname))
			System.out.println(AlMap.get(Modelname));
	}
	public void updateOptionSetName(String Modelname, String OptionSetname, String
			newName) {
		try {
				if(AlMap.containsKey(Modelname)) {
					Automobile auto = AlMap.get(Modelname);
					auto.setOpSetName(OptionSetname, newName);
					AlMap.put(Modelname, auto);
				}else {
					throw new AutoException(13, "Couldn't find automobile");
				}
		}catch(AutoException AE) {
			AE.fix(AE.getErrno());
		}	
	}
	public void updateOptionPrice(String Modelname, String Optionname, String
			Option, float newprice) {
		try {
			if(AlMap.containsKey(Modelname)) {
				Automobile auto = AlMap.get(Modelname);
				auto.	UpdateOptionPrice(Optionname, Option, newprice);
			}else {
				throw new AutoException(15, "Couldn't find automobile");
			}
		}catch(AutoException AE) {
			AE.fix(AE.getErrno());
		}
	}
	
	public void updateOption(String Modelname, String Optionname, String
			Option, float newprice) {
		try {
			if(AlMap.containsKey(Modelname)) {
				Automobile auto = AlMap.get(Modelname);
				auto.	UpdateOptionPrice(Optionname, Option, newprice);
			}else {
				throw new AutoException(15, "Couldn't find automobile");
			}
		}catch(AutoException AE) {
			AE.fix(AE.getErrno());
		}
	}
	
	public static Map<String, Automobile> getAlMap() {
		return AlMap;
	}
	
	public void setOptionChoice(String ModelName, String setName, String optionName) {
		if(AlMap.containsKey(ModelName)) {
			Automobile al = AlMap.get(ModelName);
			al.setOptionChoice(setName, optionName);
			AlMap.put(ModelName, al);
		}
	}

	public void printAll() {
		Iterator it = AlMap.keySet().iterator();
		while(it.hasNext())
			this.printAuto(it.next().toString());
	}
	public void printTotalPrice(String ModelName) {
		Automobile al;
		System.out.print(ModelName + "\t");
		if(AlMap.containsKey(ModelName))
			System.out.print(AlMap.get(ModelName).getTotalPrice());
		else
			System.out.print( "Doesn't Exist!!!");
		System.out.print("\n");
	}
	public String getOptionChoice(String ModelName, String setName) {
		if(AlMap.containsKey(ModelName))
			return AlMap.get(ModelName).getOptionChoice(setName);
		return null;
	}
	public float getOptionChoicePrice(String ModelName, String setName) {
		if(AlMap.containsKey(ModelName))
			return AlMap.get(ModelName).getOptionChoicePrice(setName);
		return 0;
	}
	public Automobile retrieveAuto(String ModelName) {
		return AlMap.get(ModelName);
	}
	
	public void editOptions(String modelname, String opSetName, String oldName, String newName, float price) {
		EditOptions eo = new EditOptions( modelname, opSetName, oldName, newName, price);
		Runnable r = eo;
		Thread t = new Thread(r);
		t.start();
	}
	public void addOptionSet(String modelname, OptionSet opSet) {
		Automobile auto = AlMap.get(modelname);
		if(auto != null) {
			auto.addOptionSet(opSet);
			AlMap.put(modelname, auto);
		}
	}
}
