package de.dirent.tthelper.model;


public enum SweatShirtSize {

	XS(0),
	S(1),
	M(2),
	L(3),
	XL(4),
	XXL(5),
	XXXL(6);
	
	private final int value;
	SweatShirtSize( int value ) { this.value = value; }
	public int value() { return this.value; }
}
