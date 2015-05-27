package pl.edu.agh.iosr.twitter.model;

public enum Emotion {
	SUPER_POSITIVE("Super positive"), POSITIVE("Positive"), NEUTRAL("Neutral"), NEGATIVE(
			"Negative"), SUPER_NEGATIVE("Super negative"), UNRECOGNIZED(
			"Unrecognized");

	private String value;

	private Emotion(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public static Emotion getValue(int rank) {
		switch (rank) {
		case 0:
			return SUPER_NEGATIVE;
		case 1:
			return NEGATIVE;
		case 2:
			return NEUTRAL;
		case 3:
			return POSITIVE;
		case 4:
			return SUPER_POSITIVE;
		default:
			return UNRECOGNIZED;
		}
	}
	
}
