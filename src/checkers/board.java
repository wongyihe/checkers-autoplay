package checkers;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;

public class board extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HashSet<int[][]> map_his = new HashSet<int[][]>();

	boolean Gameover = false;
	int[][] A = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
			{ 0, 0, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
			{ 0, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	int[][][] goal = { {}, {}, {} };
	double l = 40;
	double dx = l * Math.cos(60 * Math.PI / 180);
	double dy = l * Math.sin(60 * Math.PI / 180);
	int r = 5;
	Point p0 = new Point();
	int mark = 0;
	int i_old, j_old;
	board b;
	int[] target_c_red = { 80, 200 };
	int[] target_c_yellow = { 200, 80 };

	int[][] move_jump = { { -2, 0 }, { 2, 0 }, { 0, -2 }, { 0, 2 }, { -2, 2 }, { 2, -2 } };
	int[][] move_step = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 1 }, { 1, -1 } };

	ArrayList<float[]> h = new ArrayList<float[]>();

	int myColor = 3;
	int[][] tempA;
	int[][] M = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 25, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 11, 35, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 8, 12, 45, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 2, 4, 6, 9, 9, 9, 15, 55, 57, 58, 59, 60 },
			{ 0, 0, 0, 0, 2, 3, 6, 7, 9, 9, 9, 15, 55, 57, 58, 59, 0 },
			{ 0, 0, 0, 0, 3, 5, 6, 8, 9, 9, 9, 15, 55, 57, 58, 0, 0 },
			{ 0, 0, 0, 0, 4, 5, 6, 8, 9, 9, 9, 15, 55, 57, 0, 0, 0 },
			{ 0, 0, 0, 0, 5, 6, 7, 8, 9, 9, 9, 15, 55, 0, 0, 0, 0 },
			{ 0, 0, 0, 4, 5, 6, 7, 8, 9, 9, 9, 15, 45, 0, 0, 0, 0 },
			{ 0, 0, 3, 4, 5, 6, 7, 8, 9, 9, 9, 12, 35, 0, 0, 0, 0 },
			{ 0, 2, 3, 4, 5, 6, 6, 8, 9, 9, 8, 11, 25, 0, 0, 0, 0 },
			{ 1, 2, 3, 4, 5, 5, 6, 7, 9, 5, 7, 10, 15, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 4, 5, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 3, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	void clearmark() {
		int i, j;
		for (i = 0; i < 17; i++)
			for (j = 0; j < 17; j++) {
				if (A[i][j] > 4)
					A[i][j] = 1;
			}
		repaint();
	}

	void possiblemove(int i, int j) {
		int k, l;
		for (int m = 0; m < 6; m++) {
			k = i + move_jump[m][0];
			l = j + move_jump[m][1];
			if (k >= 0 && l >= 0 && k < 17 && l < 17)
				if (ismovable(i, j, k, l)) {
					A[k][l] = 5;
					possiblemove(k, l);
				}
			k = i + move_step[m][0];
			l = j + move_step[m][1];
			if (k >= 0 && l >= 0 && k < 17 && l < 17)
				if (ismovable(i, j, k, l) && A[i][j] != 5) {
					A[k][l] = 5;
				}
		}
		repaint();
	}

	boolean ismovable(int i, int j, int k, int l) {

		if (i >= 0 && j >= 0 && k >= 0 && l >= 0 && i < A.length && k < A.length && j < A[0].length
				&& l < A[0].length) {
			if (A[i][j] > 1 && A[k][l] == 1) {
				if ((k == i - 1 && l == j) || (k == i + 1 && l == j) || (k == i && l == j - 1) || (k == i && l == j + 1)
						|| (k == i - 1 && l == j + 1) || (k == i + 1 && l == j - 1))
					return true;
			}
			if (A[i][j] > 1 && A[k][l] == 1) {
				if ((k == i - 1 && l == j) || (k == i + 1 && l == j) || (k == i && l == j - 1) || (k == i && l == j + 1)
						|| (k == i - 1 && l == j + 1) || (k == i + 1 && l == j - 1))
					return true;
				if (((k == i - 2 && l == j) && A[i - 1][l] > 1) || ((k == i + 2 && l == j) && A[i + 1][j] > 1)
						|| ((k == i && l == j - 2) && A[i][j - 1] > 1) || ((k == i && l == j + 2) && A[i][j + 1] > 1)
						|| ((k == i - 2 && l == j + 2) && A[i - 1][j + 1] > 1)
						|| ((k == i + 2 && l == j - 2) && A[i + 1][j - 1] > 1))
					return true;
			}
		}
		return false;
	}

	boolean check(int i, int j, int k, int l) {

		if (i >= 0 && j >= 0 && k >= 0 && l >= 0 && i < A.length && k < A.length && j < A[0].length
				&& l < A[0].length) {
			if (A[i][j] > 1 && A[k][l] == 1) {
				if ((k == i - 1 && l == j) || (k == i + 1 && l == j) || (k == i && l == j - 1) || (k == i && l == j + 1)
						|| (k == i - 1 && l == j + 1) || (k == i + 1 && l == j - 1))
					return true;
			}
			if (A[i][j] > 1 && A[k][l] == 1) {
				if ((k == i - 1 && l == j) || (k == i + 1 && l == j) || (k == i && l == j - 1) || (k == i && l == j + 1)
						|| (k == i - 1 && l == j + 1) || (k == i + 1 && l == j - 1))
					return true;
				if (((k == i - 2 && l == j) && A[i - 1][l] > 1) || ((k == i + 2 && l == j) && A[i + 1][j] > 1)
						|| ((k == i && l == j - 2) && A[i][j - 1] > 1) || ((k == i && l == j + 2) && A[i][j + 1] > 1)
						|| ((k == i - 2 && l == j + 2) && A[i - 1][j + 1] > 1)
						|| ((k == i + 2 && l == j - 2) && A[i + 1][j - 1] > 1))
					return true;
			}
		}
		return false;
	}

	public board() {
		p0.x = 10;
		p0.y = 100;
		b = this;
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

				if (!Gameover) {
					int x = e.getX();
					int y = e.getY();
					int i = (int) Math.round((x - p0.x - (y - p0.y) * dx / dy) / 2 / dx);
					int j = (int) Math.round((y - p0.y) / dy);

					if (A[i_old][j_old] == 2) {
						jump(i_old, j_old, i, j);
					}

					if (iswin(A[i][j])) {
						System.out.println(A[i][j] + " win!!");
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (!Gameover) {
					int x = e.getX();
					int y = e.getY();
					int i = (int) Math.round((x - p0.x - (y - p0.y) * dx / dy) / 2 / dx);
					int j = (int) Math.round((y - p0.y) / dy);
					mark = A[i][j];
					i_old = i;
					j_old = j;
					if (A[i_old][j_old] == 2) {
						possiblemove(i, j);
						b.repaint();
					}

				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		final int N = 17;
		int i, j;
		cell c = new cell();
		int x, y;
		for (i = 0; i < N; i++)
			for (j = 0; j < N; j++) {
				if (A[i][j] > 0) {
					x = (int) ((2 * i + j) * dx);
					y = (int) (j * dy);
					c.set(p0.x + x, p0.y + y, A[i][j] - 1);
					c.draw(g);
					g.drawString("(" + i + "," + j + ")", p0.x + x, p0.y + y + r + r + r);
				}
			}
	}

//---------------------------
	public void move(int i_old, int j_old, int i, int j) {

		// possible movement
		System.out.println(A[i_old][j_old] + "move: <" + i_old + "," + j_old + ">to<" + i + "," + j + ">");
		A[i][j] = mark;
		A[i_old][j_old] = 1;

		b.repaint();

		clearmark();
	}

	boolean set(int i, int j, int player) {
		if (A[i][j] < 1) {
			System.out.println("wrong position");
			return false;
		} else
			A[i][j] = player;
		return true;
	}

	public void jump(int i_old, int j_old, int i, int j) {
		if (ismovable(i_old, j_old, i, j)) {
			A[i][j] = mark;
			A[i_old][j_old] = 1;
			b.repaint();
		}
		if (A[i][j] == 5) {
			// possible movement
			System.out.println(A[i_old][j_old] + "jump: <" + i_old + "," + j_old + ">to<" + i + "," + j + ">");
			A[i][j] = mark;
			A[i_old][j_old] = 1;

			b.repaint();
			clearmark();
			Go();
		}
		clearmark();

	}

	// ----------check if win----------
	boolean iswin(int color) {
		int[] c = center(color);
		if (color == 2) {
			if (c[0] == target_c_yellow[0] && c[1] == target_c_yellow[1])
				if (A[12][4] == 2 && A[12][5] == 2 && A[12][6] == 2 && A[12][7] == 2 && A[12][8] == 2) {
					Gameover = true;
					return true;
				}
		} else if (color == 3) {
			if (c[0] == target_c_red[0] && c[1] == target_c_red[1])
				if (A[4][12] == 3 && A[5][12] == 3 && A[6][12] == 3 && A[7][12] == 3 && A[8][12] == 3) {
					Gameover = true;
					return true;
				}
		}
		return false;

	}

	int[] center(int color) {
		int X = 0;
		int Y = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == color) {
					X += i;
					Y += j;
				}
			}
		}
		int[] c = { X, Y };
		return c;
	}

	// ---------agent------------

	void Go() {

		h.clear();
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == myColor) {
					mark = A[i][j];
					i_old = i;
					j_old = j;
					possiblemove_c(i, j, i, j);

				}

			}
		}
		int mini_index = miniDelta_c();
		move((int) h.get(mini_index)[0], (int) h.get(mini_index)[1], (int) h.get(mini_index)[2],
				(int) h.get(mini_index)[3]);
		if (iswin(myColor)) {
			System.out.println("computer win!!");
		}

	}

	float Delta_d(int[] current_c) {
		float delta_d = (float) Math
				.sqrt(Math.pow(target_c_red[0] - current_c[0], 2) + Math.pow(target_c_red[1] - current_c[1], 2));
		return delta_d;
	}

	int Vertical_d(int[] current_c) {
		int vertical_d = target_c_red[1] - current_c[1];
		return vertical_d;

	}

	void possiblemove_c(int origin_i, int origin_j, int i_, int j_) {
		possiblestep_c(origin_i, origin_j, i_, j_);
		possiblejump_c(origin_i, origin_j, i_, j_);
	}

	void possiblestep_c(int origin_i, int origin_j, int i_, int j_) {
		int k, l;
		for (int m = 0; m < 6; m++) {
			k = i_ + move_step[m][0];
			l = j_ + move_step[m][1];
			if (k >= 0 && l >= 0 && k < 17 && l < 17)
				if (check(i_, j_, k, l) && A[i_][j_] != 5) {
					A[k][l] = 5;
					int[] current_c = { center(myColor)[0] - origin_i + k, center(myColor)[1] - origin_j + l };
					float[] c = { origin_i, origin_j, k, l, Delta_d(current_c) };
					h.add(c);
				}
		}
		clearmark();
	}

	void possiblejump_c(int origin_i, int origin_j, int i_, int j_) {
		int k, l;
		for (int m = 0; m < 6; m++) {
			k = i_ + move_jump[m][0];
			l = j_ + move_jump[m][1];
			if (k >= 0 && l >= 0 && k < 17 && l < 17)
				if (check(i_, j_, k, l)) {
					A[k][l] = 5;
					int[] current_c = { center(myColor)[0] - origin_i + k, center(myColor)[1] - origin_j + l };
					float[] c = { origin_i, origin_j, k, l, Delta_d(current_c) };
					h.add(c);
					possiblejump_c(origin_i, origin_j, k, l);
				}
		}

	}

	int miniDelta_c() {

		float temp = 200;
		int index = 0;
		for (int i = 0; i < h.size(); i++) {

			System.out.println("-------------------");
			System.out.println(
					h.get(i)[0] + "," + h.get(i)[1] + "to" + h.get(i)[2] + "," + h.get(i)[3] + " : " + h.get(i)[4]);
			System.out.println("temp: " + temp);
			// 如果temp距離比i近 而且 i原始位置和目標位置的y座標不一樣 而且還有一段距離才到終點
			if (temp > h.get(i)[4] && (int) h.get(i)[1] != (int) h.get(i)[3] && temp >= 10) {
				System.out.println("如果temp距離比i近 而且 i原始位置和目標位置的y座標不一樣 而且還有一段距離才到終點");
				temp = h.get(i)[4];
				index = i;
			}
			// 隨機成分 i的周圍比index的周圍夥伴多 而他沒有在終點範圍內
			else if (Math.random() > 0.8 && isSurroundbyred((int) h.get(i)[2],
					(int) h.get(i)[3]) > isSurroundbyred((int) h.get(index)[2], (int) h.get(index)[3])
					&& M[(int) h.get(i)[0]][(int) h.get(i)[1]] < 55) {
				System.out.println("隨機成分 i的周圍比index的周圍夥伴多 而他沒有在終點範圍內");
				temp = h.get(i)[4];
				index = i;
			}
			// 如果totalM（i）比totalM（index）大 且移動之後M前比M後小
			else if (M[(int) h.get(i)[2]][(int) h.get(i)[3]] > M[(int) h.get(i)[0]][(int) h.get(i)[1]]
					&& totalM((int) h.get(i)[0], (int) h.get(i)[1], (int) h.get(i)[2], (int) h.get(i)[3]) > totalM(
							(int) h.get(index)[0], (int) h.get(index)[1], (int) h.get(index)[2],
							(int) h.get(index)[3])) {
				System.out.println("M[i]: " + M[(int) h.get(i)[2]][(int) h.get(i)[3]]);
				System.out.println("M[index]: " + M[(int) h.get(index)[2]][(int) h.get(index)[3]]);
				// 還沒到終點
				if (M[(int) h.get(i)[0]][(int) h.get(i)[1]] < 55) {
					System.out.println("如果totalM（i）比totalM（index）大 而且還沒到終點");
					temp = h.get(i)[4];
					index = i;
				}
				// 到終點了 且不在同一排變動
				else if ((int) h.get(i)[1] != (int) h.get(i)[3]) {
					System.out.println("如果totalM（i）比totalM（index）大 到終點了 且不在同一排變動");
					temp = h.get(i)[4];
					index = i;
				}
			}

		}
		System.out.println("done");
		return index;

	}

	int isSurroundbyred(int i, int j) {
		int times = 0;
		int k, l;
		for (int m = 0; m < 6; m++) {
			k = i + move_step[m][0];
			l = j + move_step[m][1];
			if (k >= 0 && k < 17 && l >= 0 && l < 17 && A[k][l] == myColor) {
				times++;
			}
		}
		return times;
	}

	int totalM(int original_i, int original_j, int temp_i, int temp_j) {
		int M_ = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == myColor) {
					M_ += M[i][j];
				}
			}
		}
		M_ = M_ - M[original_i][original_j] + M[temp_i][temp_j];
		return M_;
	}

}

class cell {
	Point p = new Point();
	int r = 10;
	int player = 0;
	final Color[] c = { Color.WHITE, Color.YELLOW, Color.RED, Color.GREEN, Color.GRAY };

	void set(int x, int y, int player) {
		p.x = x;
		p.y = y;
		this.player = player;
	}

	void draw(Graphics g) {
		g.setColor(c[player]);
		g.fillOval(p.x - r, p.y - r, r + r, r + r);
		g.setColor(Color.BLACK);
		g.drawOval(p.x - r, p.y - r, r + r, r + r);
	}

}