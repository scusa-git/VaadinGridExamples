package vaadin.grid.examples.ui.component.grid.dataset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

import vaadin.grid.examples.dataset.DataSet;
import vaadin.grid.examples.dataset.Row;
import vaadin.grid.examples.ui.component.abstracts.AbstractComponentView;

@UIScope
@SpringView
public class GridDataSet extends AbstractComponentView {
	
	private static final long serialVersionUID = 7684427328297235539L;

	@Override
	public Component getViewComponent() {
DataSet dataSet = createDataSet();
		
		Grid<Row> grid = new Grid<>("Data Set");
		grid.setItems(dataSet.getRows());
		
		for (String column : dataSet.getColumns()) {
			grid.addColumn(row -> row.getValue(column)).setCaption(column).setHidable(true);
		}
		
		grid.setHeightMode(HeightMode.ROW);
		grid.setHeightByRows(dataSet.getRows().size());
		
		return grid;
	}
	
	private DataSet createDataSet() {
		
		List<String> columns = Arrays.asList("A", "B", "C", "D", "E");
		List<Row> rows = new ArrayList<>();
//		rows.add(new Row()
//				.setRowValue("A", 111)
//				.setRowValue("B", 121)
//				.setRowValue("C", 211)
//				.setRowValue("D", "Purum")
//				.setRowValue("E", "Turum")
//				);
		
		for (int i = 1; i < 17; i++) {
			Row row = new Row();
			for (String columnName : columns) {
				row.setValue(columnName, columnName+i);
			}
			rows.add(row);
		}
		
		return new DataSet(columns, rows);
		
	}
}
