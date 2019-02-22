package minesweeperbot.engine;

public class Field {
	private Cell[][] field;
	private int width;
	private int height;

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Cell getCell(int x, int y) {
		return this.field[x][y];
	}

	//default expert WinMine size
	public Field() {
		this(30, 16);
	}

	public Field(int width, int height) {
		if (width <=0 || height <=0) throw new IllegalArgumentException("tables must have a size");

		this.field = new Cell[width][height];

		//adding cells
		for (int i = 0; i< width; i++) {
			for (int j = 0; j< height; j++) {
				this.field[i][j] = new Cell();
			}
		}

		this.width = width;
		this.height = height;
	}
}
