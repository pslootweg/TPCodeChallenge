package tpcc;

import java.util.Comparator;

/**
 * Comparator for the PriorityQueue
 * @author paul
 *
 */
public class PriorityComparator implements Comparator<InstructionMessage> {

	@Override
	public int compare(InstructionMessage m1, InstructionMessage m2) {
		// If the priority is the same, compare sequence numbers
		if (m1.getInstructionType().compareTo(m2.getInstructionType()) == 0) {
			return m1.getMySeq().compareTo(m2.getMySeq());
		} else {
			return m1.getInstructionType().compareTo(m2.getInstructionType());
		}
	}

}
