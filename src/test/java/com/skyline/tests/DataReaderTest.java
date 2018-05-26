package com.skyline.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import com.skyline.index.DataNode;
import com.skyline.index.DataReader;

public class DataReaderTest {

	DataReader dr;
	
	@Before
	public void setUp() throws Exception {
		dr = new DataReader("src/test/java/com/skyline/tests/test_data_1.txt");
	}

	@Test
	public void testGenerateDataNodes() {
		ArrayList<DataNode> dataNodes = dr.generateDataNodes();
		
		String toString = "{ price: $$$, dist: close }"
				+ "{ price: $$$, dist: far }"
				+ "{ price: $$, dist: far }"; 
				
		assertEquals(3, dataNodes.size());
		assertEquals(toString, getToString(dataNodes));
	}
	
	@Test
	public void testSetDataFilePath() {
		dr.setDataFilePath("src/test/java/com/skyline/tests/test_data_2.txt");
		
		ArrayList<DataNode> dataNodes = dr.generateDataNodes();
		
		String toString = "{ price: $$$, dist: close }"
				+ "{ price: $$$, dist: far }"
				+ "{ price: $$, dist: far }"
				+ "{ price: $$, dist: close }"
				+ "{ price: $, dist: far }"; 
				
		assertEquals(5, dataNodes.size());
		assertEquals(toString, getToString(dataNodes));
	}

	private String getToString(ArrayList<DataNode> dataNodes) {
		String toString = "";
		
		for (DataNode dn: dataNodes)
			toString += dn.toString();
		
		return toString;
	}

}
