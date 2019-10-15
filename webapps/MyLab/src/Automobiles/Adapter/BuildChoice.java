package Adapter;

public interface BuildChoice {
	public void setOptionChoice(String ModelName, String setName, String optionName);
	public String getOptionChoice(String ModelName, String setName);
	public float getOptionChoicePrice(String ModelName, String setName);
}
