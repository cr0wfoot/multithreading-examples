package committee;

public class Document {

	private int number;
	private Type type;
	
	public enum Type {
		
		MATHEMATICIAN, BIOLOGIST
		
	}
	
	public Document(Type t, int n) {
		this.type = t;
		this.number = n;
	}
	
	public int getNum() {
		return number;
	}
	
	public Type getType() {
		return type;
	}
	
}
