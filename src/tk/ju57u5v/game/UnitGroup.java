package tk.ju57u5v.game;

import java.util.ArrayList;

public class UnitGroup {

	ArrayList<Unit> units = new ArrayList<Unit>();

	public void addUnit(Unit u) {
		units.add(u);
	}

	public void moveTo(int x, int y) {
		for (int c = 0; c < units.size(); c++) {
			units.get(c).moveTo(x, y);
		}
	}
	
	public void clear () {
		units = new ArrayList<Unit>();
	}
}
