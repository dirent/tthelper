package de.dirent.tthelper.model;


public enum RanglistenKonkurrenz {

	HERREN(0),
	DAMEN(1),
	SENIOREN(2),
	SENIORINNEN(3),
	MAEDCHEN(6),
	JUNGEN(7),
	SCHUELERINNEN_A(8),
	SCHUELER_A(9),
	SCHUELERINNEN_B(10),
	SCHUELER_B(11),
	SCHUELERINNEN_C(12),
	SCHUELER_C(13);
	
	private final int value;
	RanglistenKonkurrenz( int value ) { this.value = value; }
	public int value() { return this.value; }
}
