package com.skyline.tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.skyline.index.DataNode;

import org.junit.Before;

public class DataNodeTest  {
	
	DataNode dataNode;
	
	@Before
	public void setUp() throws Exception {
		dataNode = new DataNode();
		dataNode.insert("price", "$");
		dataNode.insert("dist", "close");
	}
	
	@Test
	public void testGetDataValue() {
		String data = dataNode.getDataValue("price");
		assertEquals("$", data);
		
		data = dataNode.getDataValue("dist");
		assertEquals("close", data);
		
		data = dataNode.getDataValue("error");
		assertEquals(null, data);
	}
	
	@Test
	public void testGetAttributes() {
		Set<String> attributes = dataNode.getAttributes();
		assertEquals(2, attributes.size());
		assertEquals(true, attributes.contains("price"));
		assertEquals(true, attributes.contains("dist"));
		assertEquals(false, attributes.contains("error"));
	}
	
	@Test
	public void testToString() {
		String toString = dataNode.toString();		
		assertEquals("{ price: $, dist: close }", toString);
	}
}
