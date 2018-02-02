package vaadin.grid.examples.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontIcon;

public enum ViewType {
    
		GRID(VaadinIcons.GRID),
		GRID_ARRAY(VaadinIcons.BELL),
		GRID_DATASET(VaadinIcons.GRID);
    
    FontIcon icon;
    
    ViewType(FontIcon icon) {
        this.icon = icon;
    }

    public FontIcon getIcon() {
        return icon;
    }
    
}
