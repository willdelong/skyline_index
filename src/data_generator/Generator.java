package data_generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class Generator {
	public static void main(String[] args) throws IOException {
		String[] attributes = {"price", "distance"};
		
		HashMap<String, String[]> attributesRanges = new HashMap<String, String[]>();
		attributesRanges.put("price", new String[] {"$", "$$", "$$$"});
		attributesRanges.put("distance", new String[] {"close", "far"});
		
		int numberOfDataNodes = 100;
		
		ArrayList<String> lines = generateData(attributes, attributesRanges, numberOfDataNodes);
		
		String filePath = "src/data_sets/data.txt";
		writeToFile(filePath, lines);
	}

	private static void writeToFile(String filePath, ArrayList<String> lines) throws IOException {
		System.out.printf("Writing %d lines to %s\n", lines.size(), filePath);
		Files.write(Paths.get(filePath), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
	}

	private static ArrayList<String> generateData(String[] attributes, HashMap<String, String[]> attributesRanges,
			int numberOfDataNodes) {
		ArrayList<String> lines = new ArrayList<String>();
		
		lines.add(getHeader(attributes));
		lines.addAll(getDataNodes(attributes, attributesRanges, numberOfDataNodes));
		
		return lines;
	}

	private static ArrayList<String> getDataNodes(String[] attributes, HashMap<String, String[]> attributesRanges,
			int numberOfDataNodes) {
		ArrayList<String> dataNodes = new ArrayList<String>();
		
		for (int i = 0; i < numberOfDataNodes; i++) {
			dataNodes.add(getDataNode(attributes, attributesRanges));
		}
		
		return dataNodes;
	}

	private static String getDataNode(String[] attributes, HashMap<String, String[]> attributesRanges) {
		ArrayList<String> nodeAttributeValues = new ArrayList<String>();
		
		for (String attribute: attributes) {
			String attributeValue = getAttributeValue(attributesRanges.get(attribute));
			nodeAttributeValues.add(attributeValue);
		}
		
		return String.join(", ", nodeAttributeValues);	
	}
	
	private static String getAttributeValue(String[] possibleAttributeValues) {
		int randomIndex = (int) (Math.random() * possibleAttributeValues.length);
		return possibleAttributeValues[randomIndex]; 
	}

	private static String getHeader(String[] attributes) {
		return String.join(", ", attributes);
	}

}
