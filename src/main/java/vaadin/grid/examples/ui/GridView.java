package vaadin.grid.examples.ui;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface GridView extends View {
    
    Component getGrid();
    
    String getSource();
    
    String getViewName();
    

}
