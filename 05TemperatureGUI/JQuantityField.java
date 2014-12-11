import javax.swing.*;
import java.awt.*;

public class JQuantityField extends JComponent {

    private JTextField value;
    private JLabel units;

    public JQuantityField(String units) {
	this.value = new JTextField(10);
	this.value.setHorizontalAlignment(SwingConstants.RIGHT);
	this.units = new JLabel(units);
	this.units.setLabelFor(this.value);

	this.setLayout(new FlowLayout());
	add(this.value);
	add(this.units);
    }

    public String getValue() {
	return value.getText();
    }

    public void setValue(String value) {
	this.value.setText(value);
    }

    public void setValue(double value) {
	this.value.setText(String.valueOf(value));
    }

    public String getUnits() {
	return units.getText();
    }

    public void setUnits(String units) {
	this.units.setText(units);
    }

}
