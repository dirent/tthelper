package de.dirent.tthelper.model;


public enum Verein {

	BTG(0),
	CVJM_HEEPEN(1),
	CVJM_DORNBERG(2),
	DJK(3),
	ESV(4),
	GTB(5),
	SC_BABENHAUSEN(6),
	SCB(7),
	SC_HALLE(8),
	SF_SENNESTADT(9),
	SG_ALTSTADT(10),
	SPVG_HEEPEN(11),
	SPVG_STEINHAGEN(12),
	SVB(13),
	SVG(14),
	SG_HESSELN(15),
	SV_UBBEDISSEN(16),
	TSVE(17),
	TTC_ALTENHAGEN(18),
	TTG_VERSMOLD(19),
	TURA(20),
	TUS_08_SENNE(21),
	TUS_BRAKE(22),
	TUS_EINTRACHT(23),
	TUS_HILLEGOSSEN(24),
	TUS_JOELLENBECK(25),
	TUS_QUELLE(26),
	TUS_VILSENDORF(27),
	TV_WERTHER(28),
	FICHTE(29),
	VFL_THEESEN(30),
	VFL_UMMELN(31),
	VFL_OLDENTRUP(32);
	
	private final int value;
	Verein( int value ) { this.value = value; }
	public int value() { return this.value; }
}
