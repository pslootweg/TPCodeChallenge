package tpcc;

import java.util.PriorityQueue;

/**
 * Queue for {@link InstructionMessage}
 * @author paul
 *
 */
public class Queue {
	
	private PriorityQueue<InstructionMessage> theQueue = new PriorityQueue<>(new PriorityComparator());
	
	/**
	 * Enqueue a message if it is valid
	 * @param message
	 * @throws IllegalArgumentException
	 */
	public void enqueue(InstructionMessage message) throws IllegalArgumentException {
		// Validate the message - will throw exception if invalid
		message.validate();
		// Set the message sequence
		message.setMySeq();
		// Enqueue the message
		theQueue.add(message);
	}

	/**
	 * Get and remove the next highest priority message
	 * @return null if the queue is empty
	 */
	public InstructionMessage dequeue() {
		return theQueue.poll();
	}

	/**
	 * Get the next highest priority message without removing it
	 * @return null if the queue is empty
	 */
	public InstructionMessage peek() {
		return theQueue.peek();
	}
	
	/**
	 * Get the queue size
	 * @return number of messages in the queue
	 */
	public int count() {
		return theQueue.size();
	}
	
	/**
	 * Check if the queue is empty
	 * @return true if the queue is empty
	 */
	public boolean isEmpty() {
		return theQueue.isEmpty();
	}
	
}
