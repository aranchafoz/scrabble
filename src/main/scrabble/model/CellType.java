package main.scrabble.model;

public enum CellType {
	PLAIN 			(1),
	DOUBLE_LETTER 	(2),
	TRIPLE_LETTER 	(3),
	DOUBLE_WORD 	(2),
	TRIPLE_WORD 	(3),
	CENTRAL_CELL 	(2);
	
	private final int multiplier;
	CellType(int multiplier){
		this.multiplier = multiplier;
	}

	public static void main(String[] args) {
		for (CellType x : CellType.values()){
			System.out.printf("A %s cell has a multiplier of %d.%n", x, x.multiplier());
		}

	}

	private int multiplier(){
		return multiplier;
	}
}
