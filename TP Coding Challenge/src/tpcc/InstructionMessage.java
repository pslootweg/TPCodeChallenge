package tpcc;

/**
 * Instruction Message class
 * @author paul
 *
 */
public class InstructionMessage extends MessageSequence {

	private Integer instructionType;
	private Integer productCode;
	private Integer quantity;
	private Integer uom;
	private Integer timeStamp;

	public InstructionMessage(int instructionType, int productCode, int quantity, int uom, int timeStamp) {
		super();
		this.instructionType = instructionType;
		this.productCode = productCode;
		this.quantity = quantity;
		this.uom = uom;
		this.timeStamp = timeStamp;
	}

	public Integer getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(Integer instructionType) {
		this.instructionType = instructionType;
	}

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUom() {
		return uom;
	}

	public void setUom(Integer uom) {
		this.uom = uom;
	}

	public Integer getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Integer timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Validate this message
	 */
	void validate() {
		if (instructionType == null || (instructionType < 1 || instructionType > 99)) {
			throw new IllegalArgumentException("0 < InstructionType < 100");
		}
		if (productCode == null || productCode < 1) {
			throw new IllegalArgumentException("0 < ProductCode");
		}
		if (quantity == null || quantity < 1) {
			throw new IllegalArgumentException("0 < Quantity");
		}
		if (uom == null || (uom < 0 || uom > 255)) {
			throw new IllegalArgumentException("0 <= UOM < 256");
		}
		if (timeStamp == null || timeStamp < 1) {
			throw new IllegalArgumentException("0 < TimeStamp");
		}
	}

}
