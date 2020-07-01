package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculator extends JFrame implements ActionListener {

	static JPanel panel_1 = new JPanel();
	static JPanel panel_2 = new JPanel();
	static JPanel panel_3 = new JPanel();
	static JTextField txt = new JTextField("0", 20);
	static JButton press[] = new JButton[24];
	static JRadioButton radio[] = new JRadioButton[5];
	static JButton back = new JButton("<===");
	static int op = 99;
	static double number1 = 0, number2 = 0;
	static int radios = 0;
	static double last = 0;
	static boolean first = true;

	public static void main(String[] args) {

		JFrame jr = new JFrame("Calculator");
		calculator obj = new calculator();
		jr.setSize(300, 300);
		jr.setLocation(550, 300);
		jr.setVisible(true);
		jr.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jr.setLayout(new BorderLayout());

		window_1();
		window_2();
		window_3();
		obj.action();

		jr.add(panel_1, BorderLayout.CENTER);
		jr.add(panel_2, BorderLayout.WEST);
		jr.add(panel_3, BorderLayout.SOUTH);
		jr.pack();

	}

//=======================================================================================================	
	static void window_1() {
		panel_1.setLayout(new BorderLayout());
		panel_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Window"));
		panel_1.add(txt, BorderLayout.NORTH);
		panel_1.add(back, BorderLayout.EAST);
	}

//========================================================================================================	
	static void window_2() {
		ButtonGroup group = new ButtonGroup();
		radio[0] = new JRadioButton("Calculater", true); // calculater radio
		radio[1] = new JRadioButton("Binary to Decimal"); // Binary to Decimal radio
		radio[2] = new JRadioButton("Decimal to Binary"); // Decimal to Binary radio
		radio[3] = new JRadioButton("Degre to Radian");
		radio[4] = new JRadioButton("Radian to Degre");
		panel_2.setLayout(new GridLayout(3, 0));

		for (int x = 0; x < radio.length; x++) {
			group.add(radio[x]);
			panel_2.add(radio[x]);
		}

		panel_2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Type"));
	}

//======================================================================================================	
	static void window_3() {

		panel_3.setLayout(new GridLayout(4, 6, 3, 3));
		panel_3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Work Space"));
		specific();

	}

	static void specific() {
		press[0] = new JButton("M+"); // M+ sign
		press[1] = new JButton("1"); // number 1
		press[2] = new JButton("2"); // number 2
		press[3] = new JButton("3"); // number 3
		press[4] = new JButton("-"); // - sign
		press[5] = new JButton("^"); // ^ sign
		press[6] = new JButton("M-"); // M- sign
		press[7] = new JButton("4"); // number 4
		press[8] = new JButton("5"); // number 5
		press[9] = new JButton("6"); // number 6
		press[10] = new JButton("+"); // + sign
		press[11] = new JButton("%"); // % sign
		press[12] = new JButton("MR"); // MR sign
		press[13] = new JButton("7"); // number 7
		press[14] = new JButton("8"); // number 8
		press[15] = new JButton("9"); // number 9
		press[16] = new JButton("*"); // * sign
		press[17] = new JButton("Clear"); // Clear sign
		press[18] = new JButton("MC"); // MC sign
		press[19] = new JButton("."); // . sign
		press[20] = new JButton("0"); // number 0
		press[21] = new JButton("="); // = sign
		press[22] = new JButton("/"); // / sign
		press[23] = new JButton("Quite"); // Quite

		for (int x = 0; x < press.length; x++) {
			panel_3.add(press[x]);
		}
	}

//=====================================================================================================	
	public void action() {
		back.addActionListener(this);

		for (int x = 0; x < press.length; x++) {
			press[x].addActionListener(this);

			if (x < 5) {
				radio[x].addActionListener(this);
			}
		}
	}

//======================================================================================================
	static String working(String text) {

		String safe = text;
		double number_1 = 0;
		double number_2 = 0;
		if (radios == 0) {

			try {
				operation_test(text); // see what is the operation and save its code in op
				text = code(text); // change the operation to a character so we can split it
				String numbers[] = text.split("k"); // spliting both number

				double dot1 = doting(numbers[0]);
				double dot2 = doting(numbers[1]);

				if (dot1 == 0) {
					number_1 = Integer.parseInt(numbers[0]);
				} else {
					numbers[0] = numbers[0].replace('.', 'k');
					String new_s[] = numbers[0].split("k");
					double n1 = Integer.parseInt(new_s[0]);
					double n2 = Integer.parseInt(new_s[1]);
					int length = len(n2);
					n2 = n2 * Math.pow(10, length);
					number_1 = n1 + n2;
				}

				if (dot2 == 0) {
					number_2 = Integer.parseInt(numbers[1]);
				} else {
					numbers[1] = numbers[1].replace('.', 'k');
					String new_s[] = numbers[1].split("k");
					double n1 = Integer.parseInt(new_s[0]);
					double n2 = Integer.parseInt(new_s[1]);
					int length = len(n2);
					n2 = n2 * Math.pow(10, length);
					number_2 = n1 + n2;
				}

				return anjam(number_1, number_2);
			} catch (Exception e) {
				text = safe;
				JOptionPane.showMessageDialog(null,
						"please enter your data in correct form \nno characters allowed \nand please enter one operation. ",
						"Warning", 0);
			}
		} // close radios == 0

//----------------------------------------------------	
		else if (radios == 1) {
			try {
				int decimal = Integer.parseInt(text, 2);
				return decimal + "";
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"please enter the binary code in proper way \nthen press (=) for convering.", "Warning", 0);
			}
		}
//-----------------------------------------------------	
		else if (radios == 2) {

			try {
				int decimal = (int) Integer.parseInt(text);
				return Integer.toBinaryString(decimal);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Please the integer number in correct form \nthen press (=) for convering.", "Warning", 0);
			}
		}
//-----------------------------------------------------
		else if (radios == 3) {

			try {
				double dots = doting(text);

				if (dots == 0) {
					double degre = Integer.parseInt(text);
					return Math.toRadians(degre) + "";
				}

				else {

					text = text.replace('.', 'k');

					String num[] = text.split("k");
					double n1 = Integer.parseInt(num[0]);
					double n2 = Integer.parseInt(num[1]);

					int length = len(n2);
					n2 = n2 * Math.pow(10, length);
					number_2 = n1 + n2;
					return Math.toRadians(number_2) + "";
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Please the integer number in correct form \nthen press (=) for convering.", "Warning", 0);
			}
		}
//------------------------------------------------------
		else if (radios == 4) {

			try {
				double dots = doting(text);

				if (dots == 0) {
					double radian = Integer.parseInt(text);
					return 180 / 3.14 * radian + "";
				}

				else {

					text = text.replace('.', 'k');

					String num[] = text.split("k");
					double n1 = Integer.parseInt(num[0]);
					double n2 = Integer.parseInt(num[1]);

					int length = len(n2);
					n2 = n2 * Math.pow(10, length);
					number_2 = n1 + n2;
					return 180 / 3.14 * number_2 + "";
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Please the integer number in correct form \nthen press (=) for convering.", "Warning", 0);
			}
		}
		return text;

	}

//======================================================================================================
	static int len(double number) {
		int length = 0;
		while (number >= 1) {
			number = number / 10;
			length--;
		}
		return length;
	}

//======================================================================================================	
	static double doting(String text) {
		int dots = 0;
		for (int x = 0; x < text.length(); x++) {
			if (text.charAt(x) == '.') {
				dots++;
			}
		}
		return dots;

	}

//======================================================================================================	
	static void operation_test(String text) {
		for (int x = 0; x < text.length(); x++) {
			if (text.charAt(x) == '+') {
				op = 1; // adding code is 1
				break;
			} else if (text.charAt(x) == '-') {
				op = 2; // adding code is 1
				break;
			} else if (text.charAt(x) == '*') {
				op = 3; // adding code is 1
				break;
			} else if (text.charAt(x) == '/') {
				op = 4; // adding code is 1
				break;
			} else if (text.charAt(x) == '^') {
				op = 5; // adding code is 1
				break;
			} else if (text.charAt(x) == '%') {
				op = 6; // adding code is 1
				break;
			}
		}
	}

//======================================================================================================
	static String code(String text) {
		if (op == 1) {
			text = text.replace('+', 'k');
			return text;
		} else if (op == 2) {
			text = text.replace('-', 'k');
			return text;
		} else if (op == 3) {
			text = text.replace('*', 'k');
			return text;
		} else if (op == 4) {
			text = text.replace('/', 'k');
			return text;
		} else if (op == 5) {
			text = text.replace('^', 'k');
			return text;
		} else if (op == 6) {
			text = text.replace('%', 'k');
			return text;
		}

		return "oop";
	}

//======================================================================================================
	static String anjam(double x, double y) {
		String returning = "";
		if (op == 1) {
			x = x + y;
			if (first) {
				first = false;
				last = x;
			}
			returning = "" + x;
		} else if (op == 2) {
			x = x - y;
			if (first) {
				first = false;
				last = x;
			}
			returning = "" + x;
		} else if (op == 3) {
			x = x * y;
			if (first) {
				first = false;
				last = x;
			}
			returning = "" + x;
		} else if (op == 4) {
			x = x / y;
			if (first) {
				first = false;
				last = x;
			}
			returning = "" + x;
		} else if (op == 5) {
			x = Math.pow(x, y);
			if (first) {
				first = false;
				last = x;
			}
			returning = "" + x;
		} else if (op == 6) {
			x = x % y;
			if (first) {
				first = false;
				last = x;
			}
			returning = "" + x;
		}

		return returning;
	}

//======================================================================================================
	static double memory(String text) {

		double number = 0;
		try {
			double dots = doting(text);

			if (dots == 0) {
				return number = Integer.parseInt(text);
			} else {
				text = text.replace('.', 'k');
				String num[] = text.split("k");
				double n1 = Integer.parseInt(num[0]);
				double n2 = Integer.parseInt(num[1]);

				int length = len(n2);
				n2 = n2 * Math.pow(10, length);
				return number = n1 + n2;

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please use Memory Buttons in proper way.", "Warning", 0);
		}

		return 0;

	}
//======================================================================================================

	public void actionPerformed(ActionEvent e) {

		String text = "";
		text = txt.getText();

		if (e.getSource() == radio[0]) { // calculater button
			for (int x = 0; x < press.length; x++) {
				press[x].setEnabled(true);

			}
			radios = 0;
		}

		if (e.getSource() == radio[1]) { // Binary to Decimal
			for (int x = 0; x < press.length - 1; x++) {
				press[x].setEnabled(false);
				;
				if (x == 1 || x == 20 || x == 21 || x == 17) { // set all visible off accept 1 , 0 , . and =
					press[x].setEnabled(true);
				}
			}
			radios = 1;
		}

		else if (e.getSource() == radio[2] || e.getSource() == radio[3] || e.getSource() == radio[4]) { // Decimal to Binary||Degre to Radian||Radian to Degre
																										
			for (int x = 0; x < press.length - 1; x++) {

				press[x].setEnabled(false);

				if (x == 1 || x == 2 || x == 3 || x == 7 || x == 8 || x == 9 || x == 13 || x == 14 || x == 15 || x == 17
						|| x == 20 || x == 21) {
					press[x].setEnabled(true);
				}
			}

			if (e.getSource() == radio[2]) {
				radios = 2;
			} else if (e.getSource() == radio[3]) {
				radios = 3;
			} else if (e.getSource() == radio[4]) {
				radios = 4;
			}

			if (radios == 3 || radios == 4) {
				press[19].setEnabled(true);
			}

		}

		if (e.getSource() == press[23]) { // EXIT button
			System.exit(0);
		}

		if (e.getSource() == press[1]) { // number 1

			if (text.equals("0")) {
				txt.setText("1");
			} else {
				txt.setText(text + "1");
			}
		}

		if (e.getSource() == press[2]) { // number 2
			if (text.equals("0")) {
				txt.setText("2");
			} else {
				txt.setText(text + "2");
			}
		}

		if (e.getSource() == press[3]) { // number 3
			if (text.equals("0")) {
				txt.setText("3");
			} else {
				txt.setText(text + "3");
			}
		}

		if (e.getSource() == press[4]) { // - sign
			if (text.equals("0")) {
				txt.setText("-");
			} else {
				txt.setText(text + "-");
			}

		}

		else if (e.getSource() == press[5]) { // ^ sign
			if (text.equals("0")) {
				txt.setText("^");
			} else {
				txt.setText(text + "^");
			}

		}

		else if (e.getSource() == press[7]) { // number 4
			if (text.equals("0")) {
				txt.setText("4");
			} else {
				txt.setText(text + "4");
			}
		}

		else if (e.getSource() == press[8]) { // number 5
			if (text.equals("0")) {
				txt.setText("5");
			} else {
				txt.setText(text + "5");
			}
		}

		else if (e.getSource() == press[9]) { // number 6
			if (text.equals("0")) {
				txt.setText("6");
			} else {
				txt.setText(text + "6");
			}
		}

		else if (e.getSource() == press[10]) { // + sign
			if (text.equals("0")) {
				txt.setText("+");
			} else {
				txt.setText(text + "+");
			}

		}

		else if (e.getSource() == press[11]) { // % sign
			if (text.equals("0")) {
				txt.setText("%");
			} else {
				txt.setText(text + "%");
			}

		}

		else if (e.getSource() == press[13]) { // number 7
			if (text.equals("0")) {
				txt.setText("7");
			} else {
				txt.setText(text + "7");
			}
		}

		else if (e.getSource() == press[14]) { // number 8
			if (text.equals("0")) {
				txt.setText("8");
			} else {
				txt.setText(text + "8");
			}
		}

		else if (e.getSource() == press[15]) { // number 9
			if (text.equals("0")) {
				txt.setText("9");
			} else {
				txt.setText(text + "9");
			}
		}

		else if (e.getSource() == press[16]) { // * sign
			if (text.equals("0")) {
				txt.setText("*");
			} else {
				txt.setText(text + "*");
			}
		}

		else if (e.getSource() == press[19]) { // . sign
			if (text.equals("")) {
				txt.setText("");
			} else {
				txt.setText(text + ".");
			}
		}

		else if (e.getSource() == press[20]) { // number 0
			if (text.equals("0")) {
				txt.setText("0");
			} else {
				txt.setText(text + "0");
			}
		}

		else if (e.getSource() == press[22]) { // / sign
			if (text.equals("0")) {
				txt.setText("/");
			} else {
				txt.setText(text + "/");
			}
		}

		else if (e.getSource() == back) { // Delete button
			if (text.length() > 0) {
				txt.setText(text.substring(0, text.length() - 1));
			}
		}

		if (e.getSource() == press[21]) { // = sign

			txt.setText(working(text));

		}

		if (e.getSource() == press[17]) { // clear Button
			txt.setText("");

		}
		if (e.getSource() == press[0]) { // M+
			last += memory(text);
		}
		if (e.getSource() == press[6]) { // M-
			last -= memory(text);
		}
		if (e.getSource() == press[12]) { // MR
			txt.setText(last + "");
		}
		if (e.getSource() == press[18]) { // MC
			last = 0;
			first = true;
		}

	}
}