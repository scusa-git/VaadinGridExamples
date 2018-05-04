package vaadin.grid.examples.ui.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import vaadin.grid.examples.ui.component.abstracts.ComponentView;
import vaadin.grid.examples.utils.Utils;

public class MenuItem implements Serializable {

	private static final long serialVersionUID = -2213262386605211204L;
	
	private List<MenuItem> subMenus = new ArrayList<>();
	private String label;
	private String viewName;
	private boolean selected;
	private Class<? extends ComponentView> viewClass;

	public MenuItem(String label, Class<? extends ComponentView> viewClass) {
		super();
		this.label = label;
		this.viewClass = viewClass;
		this.viewName = Utils.getClassViewName(viewClass);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	public List<MenuItem> getSubMenus() {
		return subMenus;
	}
	
	public void addSubMenu(MenuItem subMenu) {
		this.subMenus.add(subMenu);
	}

	@Override
	public String toString() {
		return "MenuItem [label=" + label + ", viewName=" + viewName + "]";
	}

	public Class<? extends ComponentView> getViewClass() {
		return viewClass;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
