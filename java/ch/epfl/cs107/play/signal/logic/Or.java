package ch.epfl.cs107.play.signal.logic;

public class Or extends LogicSignal {
	private Logic s1;
	private Logic s2;

	public Or(Logic s1, Logic s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public boolean isOn() {
		if ((!s1.equals(null) && !s2.equals(null)) && (s1.isOn() || s2.isOn())) {
			return true;
		} else
			return false;
	}

}