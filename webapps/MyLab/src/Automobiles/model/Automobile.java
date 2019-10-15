package model;
import exception.AutoException;
import java.util.*;

public class Automobile implements java.io.Serializable{
	private String make;
	private String model;
	private String name;
	private float baseprice;
	private int currentIndex = -1 ;
	private ArrayList<OptionSet> opset;
	
	public void setOpSetName(String oldSetName, String newSetName) {
		for(int i = 0; i < opset.size(); i++) {
			if(opset.get(i).getName().equals(oldSetName)) {
				OptionSet os = opset.get(i);
				os.setName(newSetName);
				opset.set(i, os);
			}
		}
	}
	
	public void setOptionChoice(String setName, String optionName) {
		int index = this.findOptionSet(setName);
		if(index != -1) {
			OptionSet opSet = opset.get(index);
			opSet.setOptionChoice(optionName);
			opset.set(index, opSet);
		}else 
			System.out.println(setName + " Not Found!!!");
	}
	
	public String getOptionChoice(String setName) {
		int index = this.findOptionSet(setName);
		if(index != -1)
			return opset.get(index).getOptionChoice();
		else
			return null;
	}
	
	public float getOptionChoicePrice(String setName) {
		int index = this.findOptionSet(setName);
		if(index != -1) {
			return opset.get(index).getOptionChoicePrice();
		}
		else
			return 0;
	}
	
	public float getTotalPrice() {
		float sum = baseprice;
		for(int i = 0; i < opset.size(); i++)
			sum += getOptionChoicePrice(opset.get(i).getName());
		return sum;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Automobile(){
		this.name = new String();
		this.opset = new ArrayList<OptionSet>();
		this.baseprice = 0;
	}
	public Automobile(String name, float baseprice, int OptionSetsize){
		this.name = name;
		this.make = name.split("\\s+", 2)[0];
		this.model = name.split("\\s+", 2)[1];
		this.baseprice = baseprice;
		opset = new ArrayList<OptionSet>();
		
		for(int i = 0; i < OptionSetsize; i++)
			opset.add(new OptionSet());
	}
	
	public Automobile(String name, ArrayList<OptionSet> opset, float baseprice, int currentIndex) {
		super();
		this.name = name;
		this.make = name.split("\\s+", 2)[0];
		this.model = name.split("\\s+", 2)[1];
		this.opset = opset;
		this.baseprice = baseprice;
		this.currentIndex = currentIndex;
	}
	
	public String getName() {return this.name;}
	public double getBaseprice(){return this.baseprice;}
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public void addOptionSet(OptionSet opSet) {
		for(int i = 0; i<opset.size(); i++)
			if(opset.get(i).getName().contentEquals(opSet.getName())) {
				OptionSet someOpSet = opset.get(i);
				for(Option opt : opSet.getOpt())
					someOpSet.addOption(opt);
				opset.set(i, someOpSet);
				return;
			}		
		opset.add(opSet);
	}
	
	public void setOptionSet(String[] SomeSet, String name) {
		if(this.findOptionSet(name) == -1) {
			currentIndex += 1;
		}
		opset.set(currentIndex, new OptionSet(SomeSet.length, name));
		for(int i = 0 ; i < SomeSet.length; i++) {
			String [] OptionStrAndPrice = SomeSet[i].strip().split("\\$");
			OptionSet os = opset.get(currentIndex);
			os.SetOption(i, OptionStrAndPrice[0].strip(), Float.parseFloat(OptionStrAndPrice[1]));
			opset.set(currentIndex, os);
		}
	}
	public void setBasePrice(float baseprice) {
		this.baseprice = baseprice;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void UpdateOptionSetName(String oldOptionSetName, String newOptionSetName) throws AutoException{
		int index = this.findOptionSet(oldOptionSetName);
		if(index != -1)
			opset.get(index).setName(newOptionSetName);
		else
			throw new AutoException(35, "OptionSet " + oldOptionSetName + " Not Found");
	}
	
	public void UpdateOptionPrice(String OpSetName, String
			opName, float newprice) throws AutoException{
		int opset_index = this.findOptionSet(OpSetName);
		int op_index = 0;
		if(opset_index != -1) {
			OptionSet ops = opset.get(opset_index);
			op_index = ops.findOption(opName);
			if(op_index != -1) {
				ops.SetOption(ops.findOption(opName), newprice);	
			    this.opset.set(opset_index, ops);
			}
		}
		else
			throw new AutoException(33, "Option " + opName + " Not Found");
			
	}
	
	public void  UpdateOptionName(String opSetName, String opName, String newName)
	throws AutoException{
		int opset_index = this.findOptionSet(opSetName);
		int op_index = 0;
		if(opset_index != -1) {
			OptionSet ops = opset.get(opset_index);
			op_index = ops.findOption(opName);
			if(op_index != -1) {
				ops.SetOption(ops.findOption(opName), newName);	
			    this.opset.set(opset_index, ops);
			}
		}
		else
			throw new AutoException(33, "Option " + opName + " Not Found");
		
	}
	
	public void addOption(String opSetName, String name, float price) throws
	AutoException {
		int opset_index = this.findOptionSet(opSetName);
		if(opset_index != -1) {
				opset.get(opset_index).addOption(name, price);
		}
		else
			throw new AutoException(79, "OptionSet " + opSetName + " Not Found");
		
	}
	
	public int findOptionSet(String name) {
		for(int i = 0; i < opset.size(); i++) {
			if(opset.get(i).getName().toLowerCase().equals(name.toLowerCase()))
				return i;
		}
		return -1;
	}
	
	public ArrayList<OptionSet> getOpset() {
		return opset;
	}
	public void setOpset(ArrayList<OptionSet> opset) {
		this.opset = opset;
	}
	
	public void deleteOptionSet(int index) {
		opset.remove(index);
	}
	
	public void deleteOption(String opSetName, String optName) {
		int index = findOptionSet(opSetName);
		OptionSet opSet = opset.get(index);
		opSet.deleteOption(optName);
		opset.set(index, opSet);
	}
	
	@Override
	public String toString() {
		String str = new String();
		str += name + "\n" + Float.toString(baseprice) + '\n';
		for(int i = 0 ; i < opset.size(); i++) 
			str += opset.get(i).toString();
		return str;
	}
	
}
