package ch.epfl.cs107.play.signal.logic;

import java.util.List;

public class LogicNumber extends LogicSignal {
	private float nb;
	private List<Logic> e;

	public LogicNumber(float nb, List<Logic> signals) {
		this.nb = nb;
		this.e = signals;
	}

	@Override
	public boolean isOn() {
		float nbSignal = 0;
		for (int i = 0; i < e.size(); ++i) {
			nbSignal += Math.pow(2, i) * (e.get(i).getIntensity());
		}
		if (e.size() > 12 || nb < 0 || nb > Math.pow(2, e.size())) {
			return false;
		} else if (nbSignal == nb)
			return true;
		else
			return false;
	}

}
