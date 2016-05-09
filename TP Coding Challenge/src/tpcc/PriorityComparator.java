package tpcc;

import java.util.Comparator;

public class PriorityComparator implements Comparator<InstructionMessage> {

	@Override
	public int compare(InstructionMessage m1, InstructionMessage m2) {
		return m1.getInstructionType().compareTo(m2.getInstructionType());
	}

}
