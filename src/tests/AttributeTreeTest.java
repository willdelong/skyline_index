package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skyline_index.AttributeTree;
import skyline_index.DataNode;

public class AttributeTreeTest {

	ArrayList<DataNode> dataNodes;

	@Before
	public void setUp() throws Exception {
		dataNodes = generateDataNodes();
	}

	@Test
	public void testAdd() {
		AttributeTree at = new AttributeTree("price");
		
		for (DataNode dn: dataNodes)
			at.add(dn);
		
		assertEquals(3, at.getBranches().size());
	
		assertEquals(2, at.getBranchDataNodes("$").size());
		assertEquals(2, at.getBranchDataNodes("$$").size());
		assertEquals(1, at.getBranchDataNodes("$$$").size());
		assertEquals(0, at.getBranchDataNodes("millions").size());
	}
	
	@Test
	public void testBulkAdd() {
		AttributeTree at = new AttributeTree("price");
		at.add(dataNodes);
		
		assertEquals(3, at.getBranches().size());
		
		assertEquals(2, at.getBranchDataNodes("$").size());
		assertEquals(2, at.getBranchDataNodes("$$").size());
		assertEquals(1, at.getBranchDataNodes("$$$").size());
		assertEquals(0, at.getBranchDataNodes("millions").size());
	}
	
	@Test
	public void testGetBranchList() {
		AttributeTree at = new AttributeTree("price");
		at.add(dataNodes);
		
		ArrayList<DataNode> branchDataNodes = at.getBranchDataNodes("$$");
			
		assertEquals(2, at.getBranchDataNodes("$$").size());
		for (DataNode dn: branchDataNodes)
			assertEquals("$$", dn.getDataValue("price"));
	}
	
	@Test
	public void testToString() {
		AttributeTree at = new AttributeTree("price");
		at.add(dataNodes);
		
		String toString = "price-tree contains 3 branches:\n"
				+ "\t\t$$-branch contains 2 nodes:\n"
				+ "\t\t\t{ price: $$, dist: close }\n"
				+ "\t\t\t{ price: $$, dist: far }\n"
				+ "\t\t$-branch contains 2 nodes:\n"
				+ "\t\t\t{ price: $, dist: close }\n"
				+ "\t\t\t{ price: $, dist: far }\n"
				+ "\t\t$$$-branch contains 1 nodes:\n"
				+ "\t\t\t{ price: $$$, dist: close }\n";
		
		assertEquals(toString, at.toString());
	}
	
	private ArrayList<DataNode> generateDataNodes() {
		ArrayList<DataNode> dataNodes = new ArrayList<DataNode>();
		
		DataNode dn1 = new DataNode();
		dn1.insert("price", "$");
		dn1.insert("dist", "close");
		
		DataNode dn2 = new DataNode();
		dn2.insert("price", "$$");
		dn2.insert("dist", "close");
		
		DataNode dn3 = new DataNode();
		dn3.insert("price", "$$$");
		dn3.insert("dist", "close");
		
		DataNode dn4 = new DataNode();
		dn4.insert("price", "$");
		dn4.insert("dist", "far");
		
		DataNode dn5 = new DataNode();
		dn5.insert("price", "$$");
		dn5.insert("dist", "far");
		
		dataNodes.add(dn1);
		dataNodes.add(dn2);
		dataNodes.add(dn3);
		dataNodes.add(dn4);
		dataNodes.add(dn5);
		
		return dataNodes;	
	}

}
