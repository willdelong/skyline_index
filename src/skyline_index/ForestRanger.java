package skyline_index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class ForestRanger {
	HashMap<String, AttributeTree> forest;
	
	public ForestRanger() {
		forest = new HashMap<String, AttributeTree>();
	}
	
	public ForestRanger(ArrayList<DataNode> dataNodes) {
		forest = new HashMap<String, AttributeTree>() ;
		add(dataNodes);
	}
	
	public void add(DataNode dn) {
		ensureAttributeTreesInForest(dn.getAttributes());
		for (AttributeTree tree: forest.values())
			tree.add(dn);
	}
	
	public void add(ArrayList<DataNode> dataNodes) {
		for (DataNode dn: dataNodes)
			add(dn);
	}
	
	public PriorityQueue<FrequencyDataNode> getForestSkyLine(DataNode queryDataNode) {
		HashMap<DataNode, Integer> rankedDataNodes = mapDataNodesFrequency(getDataNodesFromForest(queryDataNode));
		PriorityQueue<FrequencyDataNode> dataNodeQueue = new PriorityQueue<FrequencyDataNode>(new FrequencyDataNodeComparator());
		
		for (DataNode dn: rankedDataNodes.keySet())
			dataNodeQueue.add(new FrequencyDataNode(dn, rankedDataNodes.get(dn)));
		
		return dataNodeQueue;
	}

	private HashMap<DataNode, Integer> mapDataNodesFrequency(ArrayList<DataNode> dataNodes) {
		HashMap<DataNode, Integer> rankedDataNodes = new HashMap<DataNode, Integer>();
		
		for (DataNode dn: dataNodes)
			rankedDataNodes.put(dn, rankedDataNodes.getOrDefault(dn, 0) + 1);
		
		return rankedDataNodes;
	}
	
	private ArrayList<DataNode> getDataNodesFromForest(DataNode queryDataNode) {
		ArrayList<DataNode> dataNodes = new ArrayList<DataNode>();
		
		for (String attributeTreeKey: queryDataNode.getAttributes()) {
			String dataValue = queryDataNode.getDataValue(attributeTreeKey);
			dataNodes.addAll(getDataNodesFromAttributeTree(attributeTreeKey, dataValue));
		}
		
		return dataNodes;
	}
	

	private ArrayList<DataNode> getDataNodesFromAttributeTree(String attributeTreeKey, String branchKey) {
		ArrayList<DataNode> dataNodes = new ArrayList<DataNode>();
		
		if (forest.containsKey(attributeTreeKey))
			dataNodes = forest.get(attributeTreeKey).getBranchDataNodes(branchKey);
		
		return dataNodes;
	}

	private void ensureAttributeTreesInForest(Set<String> attributes) {
		for (String attribute: attributes) {
			if (!forest.containsKey(attribute))
				forest.put(attribute, new AttributeTree(attribute));
		}
	}
	
	public String toString() {
		String toString = "Forest contains " + forest.size() + " trees:\n";
		
		for (AttributeTree tree: forest.values())
			toString += "\t" + tree.toString() + "\n";
		
		return toString;
	}

	public HashMap<String, AttributeTree> getForest() {
		return forest;
	}
}
