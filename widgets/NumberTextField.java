package widgets;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.function.UnaryOperator;

import javafx.beans.property.Property;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

class NumberStringConverter extends StringConverter<Number> {
	
	NumberFormat format;

	public NumberStringConverter(NumberFormat format) {
		super();
		this.format = format;
	}

	@Override
	public Number fromString(String string) {
		Number d = 0;
		try {
			d = format.parse(string);
		} catch (Exception e) {
			d = 0;
		}
		return d;
	}

	@Override
	public String toString(Number object) {
		return format.format(object);
	}
}

class NumberFilter implements UnaryOperator<TextFormatter.Change> {
	NumberFormat format;

	public NumberFilter(NumberFormat format) {
		super();
		this.format = format;
	}

	@Override
	public TextFormatter.Change apply(TextFormatter.Change change) {
		@SuppressWarnings("unused")
		Number d = 0;
		try {
			if (change.getControlNewText().length() == 0)
				d = 0;
			else
				d = format.parse(change.getControlNewText());
		} catch (Exception e) {
			change = null;
		}
		return change;
	}
}

public class NumberTextField extends TextField {
	NumberFormat format;
	NumberStringConverter nsc;
	NumberFilter fi;
	TextFormatter<Number> formatter;

	public NumberTextField() {
		this("");
	}

	public NumberTextField(String text) {
		super(text);
		format = new DecimalFormat();
		nsc = new NumberStringConverter(format);
		fi = new NumberFilter(format);
		formatter = new TextFormatter<Number>(nsc, 0, fi);
		setTextFormatter(formatter);
	}

	public void bindBidirectional(Property<Number> b) {
		this.textProperty().bindBidirectional(b, nsc);
	}

	public void unbindBidirectional(Property<Number> b) {
		this.textProperty().unbindBidirectional(b);
	}
}
