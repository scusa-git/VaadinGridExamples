package vaadin.grid.examples.ui.component.grid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

import vaadin.grid.examples.ui.component.abstracts.AbstractComponentView;

@UIScope
@SpringView
public class GridOtherTest extends AbstractComponentView {
	private static final long serialVersionUID = -4401970044116538553L;

	private static final Random RANDOM = new Random();
	private static final String[] NAMES = { "Alex", "Jay", "John", "Mary", "Joan", "Corrine" };
	private static final String[] SURNNAMES = { "Carlisle", "Dunn", "Albert", "Crow", "Picket", "Valden" };
	private static final String[] NICKNAMES = { "The one", "The second", "The third", "The fourth", "The fifth", "The sixth" };
	private static final String[] NATIONALITIES = { "American", "Algerian", "Italian", "Japanese", "Australian", "Romanian" };
	private static final LocalDate[] BIRTYHDAYS = { 
			LocalDate.parse("1970-01-01"), LocalDate.parse("1980-08-15"), LocalDate.parse("1990-05-02"),
			LocalDate.parse("2000-10-26"), LocalDate.parse("2010-06-06"), LocalDate.parse("2015-12-12"),
			LocalDate.parse("2017-12-12")};

	@Override
	public Component getViewComponent() {

		// basic setup
		Grid<Row> grid = new Grid<>();
		regen(grid);
		// addComponents(grid, new Button("Regen grid", event -> regen(grid)));

		return grid;
	}

	// utility method to regenerate grid columns and data
	private void regen(Grid<Row> grid) {
		// generate some random data
		DataSet dataSet = generateRandomDataSet();

		// setup columns
		grid.removeAllColumns();
		for (String column : dataSet.getColumns()) {
			grid.addColumn(row -> row.getValue(column)).setCaption(column);
		}

		// add items
		grid.setItems(dataSet.getRows());
	}

	// utility method to generate some random data
	private DataSet generateRandomDataSet() {
		// randomly select some columns
		int numberOfColumns = RANDOM.nextInt(ColumnGenerator.values().length) + 1;
		Set<ColumnGenerator> generators = new HashSet<>(numberOfColumns);
		while (generators.size() < numberOfColumns) {
			generators.add(ColumnGenerator.values()[RANDOM.nextInt(ColumnGenerator.values().length)]);
		}

		// randomly generate rows with the selected columns
		List<Row> rows = new ArrayList<>();
		for (int i = 0; i < RANDOM.nextInt(10) + 1; i++) {
			Row row = new Row();
			for (ColumnGenerator generator : generators) {
				row.setValue(generator.name(), generator.randomize());
			}
			rows.add(row);
		}

		return new DataSet(generators.stream().map(Enum::name).collect(Collectors.toList()), rows);
	}

	// column generator
	private enum ColumnGenerator {
		DATE_OF_BIRTH {
			@Override
			public Object randomize() {
				return BIRTYHDAYS[RANDOM.nextInt(BIRTYHDAYS.length)];
			}
		},
		NAME {
			@Override
			public Object randomize() {
				return NAMES[RANDOM.nextInt(NAMES.length)];
			}
		},
		SURNAME {
			@Override
			public Object randomize() {
				return SURNNAMES[RANDOM.nextInt(SURNNAMES.length)];
			}
		},
		NICK_NAME {
			@Override
			public Object randomize() {
				return NICKNAMES[RANDOM.nextInt(NICKNAMES.length)];
			}
		},
		SEX {
			@Override
			public Object randomize() {
				return RANDOM.nextBoolean() ? "F" : "M";
			}
		},
		NATIONALITY {
			@Override
			public Object randomize() {
				return NATIONALITIES[RANDOM.nextInt(NATIONALITIES.length)];
			}
		},
		STATUS {
			@Override
			public Object randomize() {
				return RANDOM.nextBoolean() ? "ONLINE" : "OFFLINE";
			}
		},
		LICENSE_ACTIVE {
			@Override
			public Object randomize() {
				return RANDOM.nextBoolean();
			}
		},
		REPUTATION {
			@Override
			public Object randomize() {
				return RANDOM.nextInt(1000);
			}
		};

		public abstract Object randomize();

	}

	// simulation of data set object
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

	// simulation of row object
	public class Row {
		private Map<String, Object> values = new HashMap<>();

		public Object getValue(String column) {
			return values.get(column);
		}

		public void setValue(String column, Object value) {
			values.put(column, value);
		}
	}

}
