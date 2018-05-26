package com.skyline.index;

public class FrequencyDataNode {
	int frequency;
	DataNode dataNode;
	
	public FrequencyDataNode(DataNode dataNode, int frequency) {
		this.dataNode = dataNode;
		this.frequency = frequency;
	}
}
