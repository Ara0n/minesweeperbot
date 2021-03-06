package minesweeperbot.engine;

import com.sun.xml.internal.bind.v2.TODO;

public class Engine {
	private Field playground;
	private GameState gameState;
	private int mines;
	private int width;
	private int height;
	private int counter;

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
		counter = 0;
		gameState = GameState.PLAYING;
		this.playground = new Field(width,height);
		Generator.generate(this.playground,mines);
	}

	private void revealImp(int x, int y) {
		if (x<0 || x>=width || y<0 || y>=height || playground.getCell(x,y).isRevealed()) return;
		playground.getCell(x,y).reveal();
		if (playground.getCell(x,y).isMine()) {
			gameState = GameState.LOSS;
		}

		if (playground.getCell(x,y).getProximity()==0) {
			revealImp(x-1,y-1);
			revealImp(x-1,y);
			revealImp(x-1,y+1);
			revealImp(x,y+1);
			revealImp(x,y+1);
			revealImp(x+1,y-1);
			revealImp(x+1,y);
			revealImp(x+1,y+1);
		}
	}

	public void reveal(int x, int y) {
		if (counter==0) {
			while (playground.getCell(x,y).isMine()) {
				newGame();
			}
		}
		revealImp(x,y);
		updateGameState();
	}

	private void updateGameState() {
		boolean victory = true;
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				if (!playground.getCell(i,j).isMine() && !playground.getCell(i,j).isRevealed()) {
					victory = false;
				}
			}
		}
		if (victory) {
			gameState = GameState.WIN;
		}
	}

	public void toggleFlag(int x, int y) {
		playground.getCell(x,y).toggleFlagged();
	}

	public GameState getGameState() {
		return gameState;
	}

	public int getProximity(int x, int y) {
		if (playground.getCell(x,y).isRevealed()) return playground.getCell(x,y).getProximity();
		return -1;
	}

	public boolean isFlagged(int x, int y) {
		return playground.getCell(x,y).isFlagged();
	}

	public boolean isRevealed(int x, int y) {
		return playground.getCell(x,y).isRevealed();
	}
}
