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
		int size = playground.getHeight()*playground.getWidth();
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
				playground.getCell(i%playground.getWidth(), i/playground.getWidth()).setMine(true);
			} else {
				playground.getCell(i%playground.getWidth(), i/playground.getWidth()).setMine(false);
			}
		}
	}

}
