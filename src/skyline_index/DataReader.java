package skyline_index;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
	
	Scanner scanner;
	
	public DataReader(String dataFilePath) {
		this.scanner = createDataScanner(dataFilePath);
	}

	public ArrayList<DataNode> generateDataNodes() {
		ArrayList<DataNode> dataNodes = readDataNodesFromFile();
		
        return dataNodes;
	}
	
	public void setDataFilePath(String dataFilePath) {
		this.scanner = createDataScanner(dataFilePath);
	}
	
	private Scanner createDataScanner(String dataFilePath) {
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(dataFilePath));
			scanner.useDelimiter("\n");
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return scanner;
	}
	
	private ArrayList<DataNode> readDataNodesFromFile() {
		ArrayList<DataNode> dataNodes = new ArrayList<DataNode>();
		
		String[] headers = scanner.next().split(", ");
		
        while(scanner.hasNext())
        	dataNodes.add(generateNode(headers, getLineValues(scanner)));
        
        scanner.close();
		
		return dataNodes;
	}

	private DataNode generateNode(String[] headers, String[] values) {
		DataNode dn = new DataNode();
		populateDataNode(dn, headers, values);
		
		return dn;
	}

	private void populateDataNode(DataNode dn, String[] headers, String[] values) {
		for (int i = 0; i < headers.length; i++)
    		dn.insert(headers[i], values[i]);
	}
	
	private String[] getLineValues(Scanner scanner) {
		String[] values = scanner.next().split(", ");
		
		return values;
	}
}
