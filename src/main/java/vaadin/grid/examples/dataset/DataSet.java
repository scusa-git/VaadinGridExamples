package vaadin.grid.examples.dataset;

import java.util.List;

public class DataSet {
	private List<String> columns;
	private List<Row> rows;

	public DataSet(List<String> columns, List<Row> rows) {
		this.columns = columns;
		this.rows = rows;
	}

	public List<String> getColumns() {
		return columns;
	}

	public List<Row> getRows() {
		return rows;
	}
}
