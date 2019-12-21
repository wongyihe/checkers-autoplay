package checkers;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Checkers extends Thread {
	win w;

	public Checkers() {
	}

	public static void main(String[] args) {
		Checkers c = new Checkers();
		c.w = new win();
		c.w.setSize(1000, 1000);
		c.w.setLayout(new FlowLayout());
		c.w.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				super.windowClosing(e);
			}
		});
		c.w.setVisible(true);
		c.start();

	}

}