package checkers;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;


public class win extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	board b;

	public win() throws HeadlessException {
		b = new board();
		b.setBounds(0, 0, 1000, 1000);

		setLayout(new FlowLayout());
		add(b);
	}
}