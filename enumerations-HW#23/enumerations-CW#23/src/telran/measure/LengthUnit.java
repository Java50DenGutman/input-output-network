package telran.measure;

public enum LengthUnit {
	MM(1), CM(10), IN(25.4f), FT(304.8F), M(1000), KM(1_000_000);

	private final float value;

	LengthUnit(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	public static Length between(Length length1, Length length2) {
		float difference = length1.getAmount() * length1.getUnit().getValue()
				- length2.getAmount() * length2.getUnit().getValue();
		return new Length(difference, LengthUnit.MM);
	}
}