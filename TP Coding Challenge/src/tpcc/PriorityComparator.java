package tpcc;

import java.util.Comparator;

/**
 * Comparator for the PriorityQueue
 * @author paul
 *
 */
public class PriorityComparator implements Comparator<InstructionMessage> {

	public enum Priority {
		HIGH(1, 10),
		MEDIUM(11, 90),
		LOW(91, 99);
		
		private int min;
		private int max;

		private Priority(int min, int max) {
			this.min = min;
			this.max = max;
		}
		
		static Priority evaluate(int it) {
			for (Priority v : Priority.values()) {
				if (v.min <= it && v.max >= it) {
					return v;
				}
			}
			throw new IllegalArgumentException("Can not evaluate: "+ it);
		}
	}

	@Override
	public int compare(InstructionMessage m1, InstructionMessage m2) {
		// If the priority is the same, compare sequence numbers
		if (Priority.evaluate(m1.getInstructionType()) == Priority.evaluate(m2.getInstructionType())) {
			return m1.getMySeq().compareTo(m2.getMySeq());
		} else {
			return m1.getInstructionType().compareTo(m2.getInstructionType());
		}
	}

}
