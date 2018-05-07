package skyline_index;

import java.util.Comparator;

public class FrequencyDataNodeComparator implements Comparator<FrequencyDataNode> {

	@Override
	public int compare(FrequencyDataNode n1, FrequencyDataNode n2) {
		if (n1.frequency > n2.frequency)
			return -1;
		else if (n1.frequency < n2.frequency)
			return 1;
		
		return 0;
	}
}
