package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import skyline_index.AttributeTree;
import skyline_index.DataNode;
import skyline_index.ForestRanger;

public class ForestRangerTest {

	ArrayList<DataNode> dataNodes;

	@Before
	public void setUp() throws Exception {
		dataNodes = generateDataNodes();
	}
	
	@Test
	public void testAdd() {
		ForestRanger fr = new ForestRanger();
		
		for (DataNode dn: dataNodes)
			fr.add(dn);

		assertEquals(2, fr.getForest().size());
		for (AttributeTree at: fr.getForest().values())
			assertEquals(2, at.getBranches().size());
	}
	
	@Test
	public void testBulkAdd() {
		ForestRanger fr = new ForestRanger();
		fr.add(dataNodes);

		assertEquals(2, fr.getForest().size());
		for (AttributeTree at: fr.getForest().values())
			assertEquals(2, at.getBranches().size());
	}
	
	@Test
	public void testToString() {
		ForestRanger fr = new ForestRanger();
		fr.add(dataNodes);

		String toString = "Forest contains 2 trees:\n"
				+ "\tprice-tree contains 2 branches:\n"
				+ "\t\t$$-branch contains 1 nodes:\n"
				+ "\t\t\t{ price: $$, dist: far }\n"
				+ "\t\t$-branch contains 1 nodes:\n"
				+ "\t\t\t{ price: $, dist: close }\n\n"
				+ "\tdist-tree contains 2 branches:\n"
				+ "\t\tfar-branch contains 1 nodes:\n"
				+ "\t\t\t{ price: $$, dist: far }\n"
				+ "\t\tclose-branch contains 1 nodes:\n"
				+ "\t\t\t{ price: $, dist: close }\n\n";
		
		assertEquals(toString, fr.toString());
	}
	
	private ArrayList<DataNode> generateDataNodes() {
		ArrayList<DataNode> dataNodes = new ArrayList<DataNode>();
		
		DataNode dn1 = new DataNode();
		dn1.insert("price", "$");
		dn1.insert("dist", "close");
		
		DataNode dn2 = new DataNode();
		dn2.insert("price", "$$");
		dn2.insert("dist", "far");
		
		dataNodes.add(dn1);
		dataNodes.add(dn2);		
		
		return dataNodes;	
	}
}
