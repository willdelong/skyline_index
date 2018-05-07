package skyline_index;

import java.util.ArrayList;
import java.util.HashMap;

public class AttributeTree {
	String name;
	private HashMap<String, ArrayList<DataNode>> branches;
	
	public AttributeTree(String name) {
		this.name = name;
		setBranches(new HashMap<String, ArrayList<DataNode>>());
	}
	
	public void add(DataNode dataNode) {
		String branchValue = dataNode.getDataValue(name);
		ArrayList<DataNode> branch = getBranchDataNodes(branchValue);
		branch.add(dataNode);
	}
	
	public void add(ArrayList<DataNode> dataNodes) {
		for (DataNode dn: dataNodes)
			add(dn);
	}
	
	public ArrayList<DataNode> getBranchDataNodes(String branchKey) {
		if (!getBranches().containsKey(branchKey)) {
			getBranches().put(branchKey, new ArrayList<DataNode>());
		}
		
		return getBranches().get(branchKey);
	}
	
	public ArrayList<DataNode> searchTree(String branchKey) {
		
		return null;
	}
	
	public HashMap<String, ArrayList<DataNode>> getBranches() {
		return branches;
	}

	public void setBranches(HashMap<String, ArrayList<DataNode>> branches) {
		this.branches = branches;
	}

	public String toString() {
		String toString = name + "-tree contains " + getBranches().size() + " branches:\n";
		
		for (String key: getBranches().keySet()) {
			ArrayList<DataNode> branch = getBranches().get(key);
			toString += "\t\t" + key + "-branch contains " + branch.size() + " nodes:\n";
			
			for (DataNode dn: branch)
				toString += "\t\t\t" + dn.toString() + "\n";
		}
		
		return toString;
	}	
}
