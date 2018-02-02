package vaadin.grid.examples.ui.grid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.vaadin.data.ValueProvider;
import com.vaadin.event.Action;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.GridContextClickEvent;
import com.vaadin.ui.Grid.SelectionMode;

import vaadin.grid.examples.dataset.DataSet;
import vaadin.grid.examples.dataset.Row;
import vaadin.grid.examples.ui.AbstractGridView;

import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView
public class GridNextTest extends AbstractGridView {
	private static final long serialVersionUID = -4401970044116538553L;
	
	AtomicInteger count = new AtomicInteger(0);

	@Override
	public Component getGrid() {
		
		VerticalLayout layout = new VerticalLayout();
		layout.setResponsive(true);
		
		TextField zero = new TextField("Test");
		zero.addValueChangeListener(event -> {
			Notification.show(event.getValue());
		});
		

		// basic setup
		Grid<Row> grid = new Grid<>();
		
		createGrid(grid);
		
//		regen(grid);
		
//		grid.getColumns().stream().forEach(column -> column.setHidable(true));
//		layout.addComponents(grid, new Button("Regen grid", event -> regen(grid)), new Button("Other grid", event -> otherRegen(grid)));

		return grid;
//		return layout;
	}

	private void createGrid(Grid<Row> grid) {
		// TODO Auto-generated method stub
		DataSet dataSet = generateDataSet();
		for (String column : dataSet.getColumns()) {
			grid.addColumn(row -> row.getValue(column)).setCaption(column).setHidable(true);
		}
		
		grid.addItemClickListener(event -> {
			Row row = event.getItem();
//			grid.deselect(row);
			grid.select(row);
			Collection<Object> values = row.getValues();
			
			String vb = values.stream().map(value->value.toString()).collect(Collectors.joining(":"));
			Notification.show(vb);
			
		});
		
//		GridContextClickEvent<T>
		
	}
	
	private void otherRegen(Grid<Row> grid) {
		
		DataSet dataSet = generateOtherDataSet();
		
		grid.setItems(dataSet.getRows());
		
		
	}

	// utility method to regenerate grid columns and data
	private void regen(Grid<Row> grid) {
		// generate some random data
		DataSet dataSet = generateDataSet();
		
//		ValueProvider

		//########
		// setup columns
		//grid.removeAllColumns();
		
//		for (String column : dataSet.getColumns()) {
//			grid.addColumn(row -> row.getValue(column)).setCaption(column).setHidable(true);
//		}
		
//		grid.addComponentColumn(row -> {
//      Button button = new Button("click me :)", e ->
//      Notification.show("Do it!!!"+grid.addColumn(ValueProvider))
//      );
//      return button;
//		});
		
		
		
//		grid.setSelectionMode(SelectionMode.MULTI);
//		grid.addSelectionListener(event -> {
//			Set<Row> items = event.getAllSelectedItems();
//			String v = "";
//			for (Row row : items) {
//				v = String.join(":", row.columnValue.toString());
//			}
//			Notification.show(v);
//		});

		// add items
		grid.setItems(dataSet.getRows());
	}
	
	private DataSet generateOtherDataSet() {
		// TODO Auto-generated method stub
		List<String> columns = Arrays.asList("A", "B", "C", "D", "E");
		List<Row> rows = new ArrayList<>();
		
		
		for (int i = 1; i < 9; i++) {
			Row row = new Row();
			for (String columnName : columns) {
				row.setValue(columnName, columnName+i+":"+i*2);
			}
			rows.add(row);
		}
		
		return new DataSet(columns, rows);
	}

	private DataSet generateDataSet() {
		// TODO Auto-generated method stub
		List<String> columns = Arrays.asList("A", "B", "C", "D", "E");
		List<Row> rows = new ArrayList<>();
		
		for (int i = 1; i < 7; i++) {
			Row row = new Row();
			for (String columnName : columns) {
				row.setValue(columnName, columnName+i);
			}
			rows.add(row);
		}
		
		return new DataSet(columns, rows);
	}

	// simulation of data set object


	// simulation of row object


}
