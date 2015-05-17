package persistance.tweets.entity;

public enum Emotion {
    SUPER_POSITIVE("Super positive"),
    POSITIVE("Positive"),
    NEUTRAL("Neutral"),
    NEGATIVE("Negative"),
    SUPER_NEGATIVE("Super negative"),
    UNRECOGNIZED("Unrecognized");
    
    private String value;
    
    private Emotion(String value) {
    	this.value = value;
    }
    
    @Override
    public String toString() {
    	return value;
    }
}
