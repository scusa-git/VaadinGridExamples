package vaadin.grid.examples.dataset;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Row {
	
	private String columnName;
	private Object columnValue;
	private Map<String, Object> values = new HashMap<>();

	public Collection<Object> getValues() {
		return values.values();
	}

	public Object getValue(String column) {
		return values.get(column);
	}

	public void setValue(String column, Object value) {
		values.put(column, value);
		this.columnName = column;
		this.columnValue = value;
	}
	
	public Row setRowValue(String column, Object value) {
		values.put(column, value);
		return this;
	}
	
}
