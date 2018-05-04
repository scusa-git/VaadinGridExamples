package vaadin.grid.examples.ui.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.vaadin.annotations.Theme;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import vaadin.grid.examples.ui.ViewType;
import vaadin.grid.examples.ui.component.abstracts.ComponentView;
import vaadin.grid.examples.ui.component.grid.GridNextTest;
import vaadin.grid.examples.ui.component.grid.GridOtherTest;
import vaadin.grid.examples.ui.component.grid.GridTest;
import vaadin.grid.examples.ui.component.grid.array.GridObjectArray;
import vaadin.grid.examples.ui.component.grid.dataset.GridDataSet;
import vaadin.grid.examples.ui.dashboard.DashBoard;
import vaadin.grid.examples.ui.menu.MenuItem;

@Theme("grid")
@SpringUI
public class MainViewUI extends UI {

	private static final long serialVersionUID = -33887281222947647L;

	boolean expanded = false;

	protected static List<MenuItem> MENU_ITEMS;
	static {
		MENU_ITEMS = new ArrayList<>();

		MenuItem dashBoard = new MenuItem("Dashboard", DashBoard.class);
		MENU_ITEMS.add(dashBoard);

		MenuItem gridArray = new MenuItem("Grid Array", null);
		gridArray.addSubMenu(new MenuItem("Simple Example", GridObjectArray.class));
		MENU_ITEMS.add(gridArray);

		MenuItem gridArray2 = new MenuItem("Grid Array 2", null);
		gridArray2.addSubMenu(new MenuItem("Simple Example 2", GridObjectArray.class));
		MENU_ITEMS.add(gridArray2);

	}

	@Autowired
	private SpringViewProvider viewProvider;

	@Autowired
	private Environment env;

	@SuppressWarnings("serial")
	@Override
	protected void init(VaadinRequest request) {
		String title = env.getProperty("title");
		getPage().setTitle(title);
		Responsive.makeResponsive(this);

		Panel content = buildContent();

		Navigator navigator = new Navigator(this, content);
		navigator.addProvider(viewProvider);
		navigator.setErrorProvider(viewProvider);

		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(false);
		vl.setSizeFull();

		Label info = new Label("<strong>" + title + "</strong> " + "| Vaadin: <strong>" + env.getProperty("vaadin.version") + "</strong> "
				+ "| Spring Boot: <strong>" + env.getProperty("spring.boot.version") + "</strong> ");
		info.setContentMode(ContentMode.HTML);

		CssLayout infoBar = new CssLayout(info);
		infoBar.setWidth(100, Unit.PERCENTAGE);
		infoBar.addStyleName("addon-info-bar");
		vl.addComponent(infoBar);

		HorizontalSplitPanel splitMenuContent = new HorizontalSplitPanel();
		splitMenuContent.setSizeFull();
		splitMenuContent.setFirstComponent(buildMenuTree());
		splitMenuContent.setSecondComponent(content);
		splitMenuContent.setSplitPosition(15);
		vl.addComponent(splitMenuContent);
		vl.setExpandRatio(splitMenuContent, 1);

		// vl.addComponent(content);

		// navigator.addViewChangeListener(new ViewChangeListener() {
		// @Override
		// public boolean beforeViewChange(ViewChangeEvent event) {
		// ComponentView view = (ComponentView) event.getNewView();
		//
		// return true;
		// }
		//
		// @Override
		// public void afterViewChange(ViewChangeEvent event) {
		//
		// }
		// });

		setContent(vl);

		String fragment = Page.getCurrent().getUriFragment();
		if (fragment == null || fragment.equals("")) {
			String viewName = MENU_ITEMS.get(0).getViewName();
			navigator.navigateTo(viewName);
		}

	}

	private Component buildMenuTree() {
		Panel treePanel = new Panel();

		treePanel.setResponsive(true);

		treePanel.setSizeFull();
		treePanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		treePanel.addStyleName("addon-menu");

		Tree<MenuItem> tree = new Tree<>();
		tree.setSizeFull();
		tree.setResponsive(true);

		TreeData<MenuItem> treeData = new TreeData<>();

		TreeDataProvider<MenuItem> provider = new TreeDataProvider<>(treeData);
		tree.setDataProvider(provider);
		
		treeData.addItems(MENU_ITEMS, MenuItem::getSubMenus);

		tree.addItemClickListener(e -> {
			MenuItem menuItem = e.getItem();
			menuItem.setSelected(true);
			if (menuItem.getViewName() != null) {
				getUI().getNavigator().navigateTo(menuItem.getViewName());
			} else {
				
				tree.collapse(MENU_ITEMS);
				
//				for (MenuItem menuRootItem : treeData.getRootItems()) {
//					
//					if (!menuRootItem.equals(menuItem)) {
//						tree.collapse(menuRootItem);
//						
//					}
//				}

				tree.expand(menuItem);
			}
		});
		
	// Show all leaf nodes as disabled
//		tree.setStyleGenerator(styleGenerator);
//		tree.setStyleGenerator(item -> {
//			item.
//		        if (!tree.getDataProvider().hasChildren(item))
//		            return "leaf";
//		        return null;
//		    }
//		);
		
		tree.setItemCaptionGenerator(MenuItem::getLabel);
		tree.setItemIconGenerator(item -> {
			if (item.getViewName() == null) {
				//return item.getType().getIcon();
				return VaadinIcons.ALARM;
			}
			return null;
		});
		treePanel.setContent(tree);
		return treePanel;
	}

	private Panel buildContent() {
		Panel contentPanel = new Panel();
		contentPanel.setSizeFull();
		contentPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		return contentPanel;
	}

}
