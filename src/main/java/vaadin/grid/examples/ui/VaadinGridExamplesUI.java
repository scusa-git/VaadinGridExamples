package vaadin.grid.examples.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.options.JavaSourceConversionOptions;
import de.java2html.util.IllegalConfigurationException;
import vaadin.grid.examples.ui.grid.GridNextTest;
import vaadin.grid.examples.ui.grid.GridOtherTest;
import vaadin.grid.examples.ui.grid.GridTest;
import vaadin.grid.examples.ui.grid.dataset.GridDataSet;
import vaadin.grid.examples.ui.grid.objectarray.GridObjectArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Theme("grid")
@SpringUI
public class VaadinGridExamplesUI extends UI {

    private static final long serialVersionUID = -33887281222947647L;
    
    boolean expanded = false;

    protected static List<MenuItem> MENU_ITEMS;
    static {
        MENU_ITEMS = new ArrayList<>();
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "NextTest", GridNextTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "OtherTest", GridOtherTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID, "TEST", GridTest.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID_ARRAY, "Grid Array", GridObjectArray.class));
        MENU_ITEMS.add(new MenuItem(ViewType.GRID_DATASET, "Grid Data Set", GridDataSet.class));
    }

    @Autowired
    private SpringViewProvider viewProvider;
    
    @Autowired
    private Environment env;

    private Label codeLabel;

    private Link codeLink;

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
        
        Label info = new Label("<strong>" + title + "</strong> "
            + "| Vaadin: <strong>" + env.getProperty("vaadin.version") + "</strong> "
            + "| Spring Boot: <strong>" + env.getProperty("spring.boot.version") + "</strong> ");
        info.setContentMode(ContentMode.HTML);

        CssLayout infoBar = new CssLayout(info);
        infoBar.setWidth(100, Unit.PERCENTAGE);
        infoBar.addStyleName("addon-info-bar");
        vl.addComponent(infoBar);

        HorizontalSplitPanel splitContentCode = new HorizontalSplitPanel();
        splitContentCode.setSizeFull();
        splitContentCode.setFirstComponent(content);
        splitContentCode.setSecondComponent(buildCode());
        splitContentCode.setSplitPosition(50);

        HorizontalSplitPanel splitMenuContent = new HorizontalSplitPanel();
        splitMenuContent.setSizeFull();
        splitMenuContent.setFirstComponent(buildMenuTree());
        splitMenuContent.setSecondComponent(splitContentCode);
        splitMenuContent.setSplitPosition(15);
        vl.addComponent(splitMenuContent);
        vl.setExpandRatio(splitMenuContent, 1);

        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                GridView view = (GridView) event.getNewView();
//                codeLink.setResource(new ExternalResource(DemoUtils.getGithubPath(view.getClass())));
//                codeLink.setTargetName("_blank");

                String formattedSourceCode = getFormattedSourceCode(view.getSource());
                codeLabel.setValue(formattedSourceCode);
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
        setContent(vl);

        String fragment = Page.getCurrent().getUriFragment();
        if (fragment == null || fragment.equals("")) {
            String viewName = MENU_ITEMS.get(0).getViewName();
            navigator.navigateTo(viewName);
        }
    }

    private Panel buildContent() {
        Panel contentPanel = new Panel();
        contentPanel.setSizeFull();
        contentPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        return contentPanel;
    }

    private Component buildCode() {
        codeLink = new Link();
        codeLink.setCaption("code");

        codeLabel = new Label();
        codeLabel.setContentMode(ContentMode.HTML);

        Panel codePanel = new Panel(codeLabel);
        codePanel.setSizeFull();
        codePanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        codePanel.addStyleName("addon-code");

        VerticalLayout codeVl = new VerticalLayout(codePanel, codeLink);
        codeVl.setMargin(false);
        codeVl.setSizeFull();
        codeVl.setExpandRatio(codePanel, 1);
        codeVl.setComponentAlignment(codeLink, Alignment.MIDDLE_CENTER);
        return codeVl;
    }

    private Component buildMenuTree() {
        Panel treePanel = new Panel();
        
        treePanel.setResponsive(true);
        
        treePanel.setSizeFull();
        treePanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        treePanel.addStyleName("addon-menu");

        Tree<MenuItem> tree = new Tree<>();
        tree.setResponsive(true);
        TreeData<MenuItem> treeData = new TreeData<>();

        TreeDataProvider<MenuItem> provider = new TreeDataProvider<>(treeData);
        tree.setDataProvider(provider);


        for (ViewType viewType : ViewType.values()) {
            List<MenuItem> children = new ArrayList<>();
            for (MenuItem i : MENU_ITEMS) {
                if (i.getType() == viewType) {
                    children.add(i);
                }
            }

            MenuItem parentItem = new MenuItem(viewType, viewType.name(), null);
            treeData.addItem(null, parentItem);

            for (MenuItem i : children) {
                treeData.addItem(parentItem, i);
               // tree.expand(parentItem);
                tree.collapse(parentItem);
            }
        }
        
//        tree.addExpandListener(event -> {
//        	MenuItem i = event.getExpandedItem();
//        	for (MenuItem menuItem : treeData.getRootItems()) {
//        		if (!i.equals(menuItem)) {
//        			tree.collapse(menuItem);
//        		}
//        	}
//        });
        
				tree.addItemClickListener(e -> {
					MenuItem i = e.getItem();
					if (i.getViewName() != null) {
						getUI().getNavigator().navigateTo(i.getViewName());
					} else {
		
						for (MenuItem menuItem : treeData.getRootItems()) {
							if (!i.equals(menuItem)) {
								tree.collapse(menuItem);
							}
						}
		
						tree.expand(i);
					}
				});
				
        tree.setItemCaptionGenerator(MenuItem::getLabel);
        tree.setItemIconGenerator(item -> {
            if (item.getViewName() == null) {
                return item.getType().getIcon();
            }
            return null;
        });
        treePanel.setContent(tree);
        return treePanel;
    }

    public String getFormattedSourceCode(String sourceCode) {
        if (sourceCode != null) {
            try {
                JavaSource source = new JavaSourceParser().parse(new StringReader(sourceCode));
                JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter();
                StringWriter writer = new StringWriter();
                JavaSourceConversionOptions options = JavaSourceConversionOptions.getDefault();
                options.setShowLineNumbers(false);
                options.setAddLineAnchors(false);
                converter.convert(source, options, writer);
                return writer.toString();
            } catch (IllegalConfigurationException | IOException ignore) {

            }
        }
        return sourceCode;
    }

}
