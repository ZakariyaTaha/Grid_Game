package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;

public class MultipleAnd extends LogicSignal {
	private List<Logic> signals = new LinkedList<Logic>();

	public MultipleAnd(List<Logic> list) {
		signals = list;
	}

	@Override
	public boolean isOn() {
		boolean temp = true;
		for (int i = 0; i < signals.size() && temp; ++i) {
			if (signals.get(i).isOn() == false) {
				temp = false;
			}

		}
		return temp;
	}

}
