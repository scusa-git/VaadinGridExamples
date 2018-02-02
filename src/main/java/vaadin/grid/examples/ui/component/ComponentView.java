package vaadin.grid.examples.ui.component;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface ComponentView extends View {

	@Override
	public Component getViewComponent();
	
	public String getViewName();
}
