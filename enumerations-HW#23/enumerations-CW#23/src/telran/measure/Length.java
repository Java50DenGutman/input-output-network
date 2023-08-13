package telran.measure;

public class Length implements Comparable<Length> {
	private final float amount;
	private final LengthUnit unit;

	public Length(float amount, LengthUnit unit) {
		this.amount = amount;
		this.unit = unit;
	}

	public float getAmount() {
		return amount;
	}

	public LengthUnit getUnit() {
		return unit;
	}

	public Length convert(LengthUnit newUnit) {
		float newValue = (amount * unit.getValue()) / newUnit.getValue();
		return new Length(newValue, newUnit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Length length = (Length) obj;
		float thisValueInMM = amount * unit.getValue();
		float otherValueInMM = length.amount * length.unit.getValue();
		return Float.compare(thisValueInMM, otherValueInMM) == 0;
	}

	@Override
	public String toString() {
		return amount + " " + unit;
	}

	@Override
	public int compareTo(Length other) {
		float thisValueInMM = amount * unit.getValue();
		float otherValueInMM = other.amount * other.unit.getValue();
		return Float.compare(thisValueInMM, otherValueInMM);
	}
}