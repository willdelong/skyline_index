package com.skyline.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DataNode {
	HashMap<String, String> data;
	int matchScore = 0;
	
	public DataNode() {
		data = new HashMap<String, String>(); 
	}
	
	public void insert(String key, String value) {
		data.put(key, value);
	}
	
	public String getDataValue(String key) {
		String dataValue = data.get(key);
		
		return dataValue;
	}
	
	public Set<String> getAttributes() {
		return data.keySet();
	}
	
	public void increaseMatchScore(int matchValue) {
		matchScore += matchValue;
	}
	
	public String toString() {
		ArrayList<String> strList = new ArrayList<String>(); 
		
		for (String key: data.keySet())
			strList.add(key + ": " + data.get(key));			

		String toString = "{ " + String.join(", ", strList) + " }"; 
		
		return toString;
	}
}
