package com.skyline.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) {
		DataReader dr = new DataReader("src/data_sets/data_sm.txt");
		ArrayList<DataNode> dataNodes = dr.generateDataNodes();
		
		ForestRanger fr = new ForestRanger(dataNodes);
		println(fr);
		
		DataNode dn = new DataNode();
		dn.insert("price", "$$$");
		dn.insert("dist", "close");
		PriorityQueue<FrequencyDataNode> rankedDataNodes = fr.getForestSkyLine(dn);		
		
		while (!rankedDataNodes.isEmpty()) {
			FrequencyDataNode frequencyDataNode = rankedDataNodes.poll();
			println(frequencyDataNode.dataNode + ": " + frequencyDataNode.frequency);
		}
			
		
		for (FrequencyDataNode frequencyDataNode: rankedDataNodes) {
			println(frequencyDataNode.dataNode + ": " + frequencyDataNode.frequency);
		}
		
	}
	
	private static void println(Object o) {
		System.out.println(o);
	}
	
	private static void print(Object o) {
		System.out.print(o);
	}
	
	private static void print(ArrayList<?> arr) {
		for (Object o: arr)
			println(o);
	}
}
