package vaadin.grid.examples.ui.component.grid.array;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

import vaadin.grid.examples.ui.component.abstracts.AbstractComponentView;

@UIScope
@SpringView
public class GridObjectArray extends AbstractComponentView {

	private static final long serialVersionUID = 4401818589006499177L;

	@Override
	public Component getViewComponent() {

		Object[][] data = { 
				{ "Sirius", -1.46f }, 
				{ "Canopus", -0.72f }, 
				{ "Arcturus", -0.04f }, 
				{ "Alpha Centauri", -0.01f } 
		};

		Grid<Object[]> grid = new Grid<>("The Brightest Stars");
		grid.setItems(data);
		
		// Define two columns for the built-in container
		grid.addColumn(v -> v[0]).setCaption("Name");
		grid.addColumn(v -> v[1]).setCaption("Mag");

		// Show exactly the currently contained rows (items)
		grid.setHeightMode(HeightMode.ROW);
		grid.setHeightByRows(data.length);

		// Default in Table
		grid.setSelectionMode(Grid.SelectionMode.NONE);

		return grid;
	}

}
