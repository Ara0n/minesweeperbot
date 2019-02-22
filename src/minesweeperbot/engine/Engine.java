package minesweeperbot.engine;

public class Engine {
	private Field playground;
	private int mines;
	private int width;
	private int height;

	public Engine(int mines, int width, int height) {
		this.mines = mines;
		this.width = width;
		this.height = height;
		this.newGame();
	}

	//default WinMine expert settings
	public Engine() {
		this(99,30,16);
	}

	public void newGame() {
		this.playground = new Field(width,height);
		Generator.generate(this.playground,mines);
	}
}
