import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class KelvinKonverter extends JFrame implements ActionListener {

    private JQuantityField celsius, fahrenheit;

    private JButton clicky;

    public KelvinKonverter() {
	this.setTitle("Kelvin Konverter");
	this.setSize(400, 100);
	this.setLocation(100, 100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	Container pane = this.getContentPane();
	pane.setLayout(new FlowLayout());

	celsius = new JQuantityField("<html>&deg;C</html>");
	fahrenheit = new JQuantityField("<html>&deg;F</html>");

	clicky = new JButton("Convert!");
	clicky.setActionCommand("CtoF");
	clicky.addActionListener(this);

	pane.add(celsius);
	pane.add(fahrenheit);
	pane.add(clicky);
    }

    public static void main(String[] args) {
	KelvinKonverter window = new KelvinKonverter();
	window.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
	Scanner scan = new Scanner(celsius.getValue());
	if (scan.hasNextDouble()) {
	    double c = scan.nextDouble();
	    double f = c * 1.8 + 32;
	    fahrenheit.setValue(String.valueOf(f));
	}
	else {
	    fahrenheit.setValue("ERROR");
	}
    }

}
