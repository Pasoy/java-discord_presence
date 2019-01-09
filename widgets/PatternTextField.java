package widgets;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.beans.property.Property;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DefaultStringConverter;

class PatternFilter implements UnaryOperator<TextFormatter.Change> {
	
	Pattern pattern;

	public PatternFilter(Pattern pattern) {
		super();
		this.pattern = pattern;
	}

	@Override
	public TextFormatter.Change apply(TextFormatter.Change change) {
		
		/*Matcher m = pattern.matcher(change.getControlNewText());
		if (!m.matches())
			change = null;*/
		return change;
	}
}

public class PatternTextField extends TextField {
	PatternFilter fi;
	Pattern pat;
	TextFormatter<String> formatter;

	public PatternTextField(String pattern) {
		this("", pattern);
	}

	public PatternTextField(String text, String pattern) {
		super(text);
		pat = Pattern.compile(pattern);
		fi = new PatternFilter(pat);
		formatter = new TextFormatter<String>(new DefaultStringConverter(), "", fi);
		setTextFormatter(formatter);
	}

	public void bindBidirectional(Property<String> b) {
		this.textProperty().bindBidirectional(b);
	}

	public void unbindBidirectional(Property<String> b) {
		this.textProperty().unbindBidirectional(b);
	}

}
