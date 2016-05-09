package tpcc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class QueueTest {
	
	private Queue uut;

	@Before
	public void setUpBefore() throws Exception {
		uut = new Queue();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testEnqueue_Invalid_IT_Lower() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < InstructionType < 100");
		
		uut.enqueue(new InstructionMessage(-1, 1, 1, 0, 1));
	}

	@Test
	public void testEnqueue_Invalid_IT_Upper() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < InstructionType < 100");
		
		uut.enqueue(new InstructionMessage(100, 1, 1, 0, 1));
	}

	@Test
	public void testEnqueue_Invalid_PC() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < ProductCode");
		
		uut.enqueue(new InstructionMessage(1, -1, 1, 0, 1));
	}

	@Test
	public void testEnqueue_Invalid_Q() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < Quantity");
		
		uut.enqueue(new InstructionMessage(1, 1, -1, 0, 1));
	}

	@Test
	public void testEnqueue_Invalid_U_Lower() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 <= UOM < 256");
		
		uut.enqueue(new InstructionMessage(1, 1, 1, -1, 1));
	}

	@Test
	public void testEnqueue_Invalid_U_Upper() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 <= UOM < 256");
		
		uut.enqueue(new InstructionMessage(1, 1, 1, 256, 1));
	}

	@Test
	public void testEnqueue_Invalid_T() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < TimeStamp");
		
		uut.enqueue(new InstructionMessage(1, 1, 1, 0, -1));
	}

	@Test
	public void testEnqueue_Valid() {
		uut.enqueue(new InstructionMessage(1, 1, 1, 0, 1));
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(1, uut.count());
	}

	@Test
	public void testDequeue_Basic() {
		final InstructionMessage msg0 = new InstructionMessage(1, 1, 1, 0, 1);
		uut.enqueue(msg0);
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(1, uut.count());
		
		InstructionMessage msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(msg0, msg);
		
		assertTrue("Should be empty", uut.isEmpty());
		assertEquals(0, uut.count());
	}

	@Test
	public void testPeek_Basic() {
		final InstructionMessage msg0 = new InstructionMessage(1, 1, 1, 0, 1);
		uut.enqueue(msg0);
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(1, uut.count());
		
		InstructionMessage msg = uut.peek();
		assertNotNull(msg);
		assertEquals(msg0, msg);
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(1, uut.count());
	}

	@Test
	public void testCount_Basic() {
		assertEquals(0, uut.count());
	}

	@Test
	public void testIsEmpty_Basic() {
		assertTrue("Should be empty", uut.isEmpty());
	}

	@Test
	public void testEnqueue_MultipleSamePriority() {
		uut.enqueue(new InstructionMessage(1, 1, 1, 0, 1));
		uut.enqueue(new InstructionMessage(1, 2, 1, 0, 1));
		uut.enqueue(new InstructionMessage(1, 3, 1, 0, 1));
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(3, uut.count());
	}

	/**
	 * Simple same priority - retrieved in order added
	 */
	@Test
	public void testDequeue_MultipleSamePriority() {
		uut.enqueue(new InstructionMessage(1, 1, 1, 0, 1));
		uut.enqueue(new InstructionMessage(1, 2, 1, 0, 1));
		uut.enqueue(new InstructionMessage(1, 3, 1, 0, 1));
		uut.enqueue(new InstructionMessage(1, 4, 1, 0, 1));
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(4, uut.count());
		
		InstructionMessage msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(1), msg.getProductCode());
		
		assertEquals(3, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(2), msg.getProductCode());
		
		assertEquals(2, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(3), msg.getProductCode());
		
		assertEquals(1, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(4), msg.getProductCode());
		
		assertTrue("Should be empty", uut.isEmpty());
		assertEquals(0, uut.count());
	}

	/**
	 * Mixed priority - 3 levels
	 */
	@Test
	public void testDequeue_MultipleArbitraryPriority() {
		uut.enqueue(new InstructionMessage(95, 1, 1, 0, 1));
		uut.enqueue(new InstructionMessage(1, 2, 1, 0, 1));
		uut.enqueue(new InstructionMessage(15, 3, 1, 0, 1));
		uut.enqueue(new InstructionMessage(2, 4, 1, 0, 1));
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(4, uut.count());
		
		InstructionMessage msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(2), msg.getProductCode());
		
		assertEquals(3, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(4), msg.getProductCode());
		
		assertEquals(2, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(3), msg.getProductCode());
		
		assertEquals(1, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(1), msg.getProductCode());
		
		assertTrue("Should be empty", uut.isEmpty());
		assertEquals(0, uut.count());
	}

	/**
	 * High priority messages preserved in order added
	 */
	@Test
	public void testDequeue_MultipleOrderPriority() {
		uut.enqueue(new InstructionMessage(95, 1, 1, 0, 1));
		uut.enqueue(new InstructionMessage(3, 2, 1, 0, 1));
		uut.enqueue(new InstructionMessage(2, 3, 1, 0, 1));
		uut.enqueue(new InstructionMessage(1, 4, 1, 0, 1));
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(4, uut.count());
		
		InstructionMessage msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(2), msg.getProductCode());
		
		assertEquals(3, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(3), msg.getProductCode());
		
		assertEquals(2, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(4), msg.getProductCode());
		
		assertEquals(1, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(1), msg.getProductCode());
		
		assertTrue("Should be empty", uut.isEmpty());
		assertEquals(0, uut.count());
	}
	
	/**
	 * Mixed en/de-queue operations
	 */
	@Test
	public void testEnqueDequeue_Mixed() {
		uut.enqueue(new InstructionMessage(95, 1, 1, 0, 1));
		uut.enqueue(new InstructionMessage(1, 2, 1, 0, 1));
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(2, uut.count());
		
		InstructionMessage msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(2), msg.getProductCode());
		
		assertEquals(1, uut.count());
		
		uut.enqueue(new InstructionMessage(15, 3, 1, 0, 1));
		uut.enqueue(new InstructionMessage(2, 4, 1, 0, 1));
		
		assertEquals(3, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(4), msg.getProductCode());
		
		assertEquals(2, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(3), msg.getProductCode());
		
		assertEquals(1, uut.count());
		
		msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(Integer.valueOf(1), msg.getProductCode());
		
		assertTrue("Should be empty", uut.isEmpty());
		assertEquals(0, uut.count());
	}

	@Test
	public void testDequeue_Empty() {
		InstructionMessage msg = uut.dequeue();
		assertNull(msg);
	}

	@Test
	public void testPeek_Empty() {
		InstructionMessage msg = uut.peek();
		assertNull(msg);
	}

}
