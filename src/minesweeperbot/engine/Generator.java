package minesweeperbot.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generator {
	//default WinMine expert settings is 99 mines
	public static void generate(Field playground) {
		generate(playground, 99);
	}

	public static void generate(Field playground, int mines) {
		int height = playground.getHeight();
		int width = playground.getWidth();
		int size = height*width;
		if (mines>=size) throw new IllegalArgumentException("Too much mines !");
		List<Boolean> shuffled = new ArrayList<Boolean>(size);

		//initializing the list before shuffling
		for (int i=0; i<mines; i++) {
			shuffled.set(i, true);
		}
		for (int i=mines; i<size; i++) {
			shuffled.set(i, false);
		}

		//shuffling the list
		Collections.shuffle(shuffled);

		//porting the shuffling to the playground
		for (int i=0; i<size; i++) {
			if (shuffled.get(i)) {
				playground.getCell(i%width, i/width).setMine(true);
			} else {
				playground.getCell(i%width, i/width).setMine(false);
			}
		}

		//setting the proximity
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (!playground.getCell(x, y).isMine()) {
					int proximity = 0;
					if (x-1>=0 && y-1>=0 && playground.getCell(x-1,y-1).isMine()) proximity++;
					if (x-1>=0 && playground.getCell(x-1,y).isMine()) proximity++;
					if (x-1>=0 && y+1<height && playground.getCell(x-1,y+1).isMine()) proximity++;
					if (y-1>=0 && playground.getCell(x,y+1).isMine()) proximity++;
					if (y+1<height && playground.getCell(x,y+1).isMine()) proximity++;
					if (x+1<width && y-1>=0 && playground.getCell(x+1,y-1).isMine()) proximity++;
					if (x+1<width && playground.getCell(x+1,y).isMine()) proximity++;
					if (x+1<width && y+1<height && playground.getCell(x+1,y+1).isMine()) proximity++;

					playground.getCell(x, y).setProximity(proximity);
				}
			}
		}
	}

}
