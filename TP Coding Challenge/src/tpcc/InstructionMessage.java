package tpcc;

public class InstructionMessage {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instructionType == null) ? 0 : instructionType.hashCode());
		result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((uom == null) ? 0 : uom.hashCode());
		result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstructionMessage other = (InstructionMessage) obj;
		if (instructionType == null) {
			if (other.instructionType != null)
				return false;
		} else if (!instructionType.equals(other.instructionType))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		if (uom == null) {
			if (other.uom != null)
				return false;
		} else if (!uom.equals(other.uom))
			return false;
		return true;
	}

}