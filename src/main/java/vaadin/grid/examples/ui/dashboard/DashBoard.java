package vaadin.grid.examples.ui.dashboard;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import vaadin.grid.examples.ui.component.abstracts.AbstractComponentView;

@UIScope
@SpringView
public class DashBoard extends AbstractComponentView {

	private static final long serialVersionUID = 4401818589006499177L;

	@Override
	public Component getViewComponent() {

		Label label = new Label("Hallo ich bin Dashboard");

		return label;
	}

}
