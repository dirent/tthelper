package de.dirent.tthelper.model;


public enum PokalWettbewerb {

	HERREN_KREISLIGA(0),
	HERREN_1KREISKLASSE(1),
	HERREN_2KREISKLASSE(2),
	HERREN_3KREISKLASSE(3),
	DAMEN_KREISLIGA(4),
	DAMEN_1KREISKLASSE(5),
	MAEDCHEN(6),
	JUNGEN(7),
	SCHUELERINNEN_A(8),
	SCHUELER_A(9),
	SCHUELERINNEN_B(10),
	SCHUELER_B(11);
	
	private final int value;
	PokalWettbewerb( int value ) { this.value = value; }
	public int value() { return this.value; }
}
