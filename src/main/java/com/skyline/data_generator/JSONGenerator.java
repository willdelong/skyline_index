package com.skyline.data_generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONGenerator {
	public static void main(String[] args) throws IOException {
		String[] attributes = { "price", "distance", "color", "type" };

		HashMap<String, String[]> attributesRanges = new HashMap<String, String[]>();
		attributesRanges.put("price", new String[] { "$", "$$", "$$$", "out-of-range" });
		attributesRanges.put("distance", new String[] { "close", "far", "very far", "very close" });
		attributesRanges.put("color", new String[] { "red", "blue", "orange", "green", "yellow" });
		attributesRanges.put("type", new String[] { "car", "restaurant", "furniture", "book","t-shirt"  });

		int numberOfDataNodes = 100;

		ArrayList<String> lines = generateData(attributes, attributesRanges, numberOfDataNodes);

		String filePath = "src/main/java/com/skyline/data_sets/json_data.txt";
		writeToFile(filePath, lines);
	}

	private static void writeToFile(String filePath, ArrayList<String> lines) throws IOException {
		System.out.printf("Writing %d lines to %s\n", lines.size(), filePath);
		Files.write(Paths.get(filePath), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
	}

	private static ArrayList<String> generateData(String[] attributes, HashMap<String, String[]> attributesRanges,
			int numberOfDataNodes) {
		ArrayList<String> lines = new ArrayList<String>();

		for (int i = 0; i < numberOfDataNodes; i++) {
			String item = "{";
			for (int j = 0; j < attributes.length; j++) {
				String key = attributes[j];
				String[] options = attributesRanges.get(key);
				String value = options[getRandomValue(options.length)];
				item = item + "\"" + key + "\":\"" + value + "\",";
			}
			item = item.substring(0, item.length()-1);
			item = item + "}";
			lines.add(item);
		}

		return lines;
	}

	private static int getRandomValue(int length) {
		int randomIndex = (int) (Math.random() * length);
		return randomIndex;
	}

}
