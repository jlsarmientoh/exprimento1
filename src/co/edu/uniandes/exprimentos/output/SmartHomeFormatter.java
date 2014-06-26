package co.edu.uniandes.exprimentos.output;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SmartHomeFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		 return record.getMessage()+"\n";
	}

}
