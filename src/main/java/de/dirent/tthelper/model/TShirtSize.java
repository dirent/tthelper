package de.dirent.tthelper.model;


public enum TShirtSize {

	S(0),
	L(1),
	XL(2),
	XXL(3),
	XXXL(4);
	
	private final int value;
	TShirtSize( int value ) { this.value = value; }
	public int value() { return this.value; }
}
