package tpcc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tpcc.PriorityComparator.Priority;

public class PriorityComparatorTest {

	private PriorityComparator uut;

	@Before
	public void setUp() throws Exception {
		uut = new PriorityComparator();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test that an invalid value throws an exception
	 */
	@Test
	public void testPriorityInvalid() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Can not evaluate: 0");
		
		Priority.evaluate(0);
	}

	/**
	 * Test that HIGH priority us correctly decoded
	 */
	@Test
	public void testPriorityHigh() {
		Priority pri = Priority.evaluate(1);
		assertEquals(Priority.HIGH, pri);

		pri = Priority.evaluate(10);
		assertEquals(Priority.HIGH, pri);
	}

	/**
	 * Test that MEDIUM priority us correctly decoded
	 */
	@Test
	public void testPriorityMedium() {
		Priority pri = Priority.evaluate(11);
		assertEquals(Priority.MEDIUM, pri);

		pri = Priority.evaluate(90);
		assertEquals(Priority.MEDIUM, pri);
	}

	/**
	 * Test that LOW priority us correctly decoded
	 */
	@Test
	public void testPriorityLow() {
		Priority pri = Priority.evaluate(91);
		assertEquals(Priority.LOW, pri);

		pri = Priority.evaluate(99);
		assertEquals(Priority.LOW, pri);
	}

	/**
	 * Check ordering if the priority is different
	 */
	@Test
	public void testHighMedium() {
		InstructionMessage m1 = new InstructionMessage(1, 4, 1, 0, 1);
		InstructionMessage m2 = new InstructionMessage(11, 3, 1, 0, 1);
		m1.setMySeq();
		m2.setMySeq();
		int res = uut.compare(m1, m2);
		assertEquals(-1, res);
	}

	/**
	 * Check ordering if the priority is different, ignores sequence
	 */
	@Test
	public void testHighMediumEarlier() {
		InstructionMessage m1 = new InstructionMessage(1, 4, 1, 0, 1);
		InstructionMessage m2 = new InstructionMessage(11, 3, 1, 0, 1);
		m2.setMySeq();
		m1.setMySeq();
		int res = uut.compare(m1, m2);
		assertEquals(-1, res);
	}

	/**
	 * Same priority, sequence determines order
	 */
	@Test
	public void testHighLower() {
		InstructionMessage m1 = new InstructionMessage(1, 4, 1, 0, 1);
		InstructionMessage m2 = new InstructionMessage(2, 3, 1, 0, 1);
		m1.setMySeq();
		m2.setMySeq();
		int res = uut.compare(m1, m2);
		assertEquals(-1, res);
	}

	/**
	 * Same priority, sequence determines order
	 */
	@Test
	public void testHighLowerEarlier() {
		InstructionMessage m1 = new InstructionMessage(1, 4, 1, 0, 1);
		InstructionMessage m2 = new InstructionMessage(2, 3, 1, 0, 1);
		m2.setMySeq();
		m1.setMySeq();
		int res = uut.compare(m1, m2);
		assertEquals(1, res);
	}

}
