package tpcc;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Message sequence used to ensure FIFO ordering within a priority
 * @author paul
 *
 */
public abstract class MessageSequence {

	// Shared sequence generator
	private static AtomicLong seq = new AtomicLong();
	// Message sequence - assigned when queued
	private Long mySeq = null;
	
	protected void setMySeq() {
		mySeq = seq.getAndIncrement();
	}

	protected Long getMySeq() {
		return mySeq;
	}

}
