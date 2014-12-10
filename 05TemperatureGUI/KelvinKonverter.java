import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KelvinKonverter extends JFrame {

    private JTextField celsius, fahrenheit;

    public KelvinKonverter() {
	this.setTitle("Kelvin Konverter");
	this.setSize(600, 400);
	this.setLocation(100, 100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	Container pane = this.getContentPane();
	pane.setLayout(new FlowLayout());

	celsius = new JTextField(10);
	fahrenheit = new JTextField(10);

	JLabel cl = new JLabel("Celsius", null, JLabel.CENTER);
    }

}