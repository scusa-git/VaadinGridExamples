package vaadin.grid.examples.ui.component.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

import vaadin.grid.examples.ui.component.abstracts.AbstractComponentView;

@UIScope
@SpringView
public class GridTest extends AbstractComponentView {

	private static final long serialVersionUID = 6767631954171581895L;

	@Override
	public Component getViewComponent() {
		
		
		
//		Grid<List<String>> grid = new Grid<>();
		Grid<List<MyBean>> grid = new Grid<>();
	  grid.setSizeFull();
	  
	  List<List<String>> myBeans = new ArrayList<>();
	  myBeans.add(Arrays.asList("1", "2", "3"));
//	  List<MyBean> myBeans = Arrays.asList(new MyBean(1));
//	  List<MyBean> myBeans = Arrays.asList(new MyBean(23));
//	  List<MyBean> myBeans = Arrays.asList(new MyBean(13));
//	  for (String bean : myBeans) {
//	  	grid.addColumn(b->bean).setCaption("Value:"+bean).setWidth(100);
//		}
	  
	  
//	  grid.setItems(Arrays.asList(new MyBean(8)));
//	  grid.addColumn(MyBean::getValue).setCaption("Value:1").setWidth(100);
//	  grid.setItems(Arrays.asList(new MyBean(25)));
//	  grid.addColumn(MyBean::getValue).setCaption("Value:1").setWidth(100);
//	  grid.setItems(myBeans);
//	  
//	  
//	  grid.addColumn(String::trim).setCaption("Value:1").setWidth(100);
//	  grid.addColumn(String::trim).setCaption("Value:2").setWidth(100);
//	  grid.addColumn(String::trim).setCaption("Value:3").setWidth(100);
//	  grid.addColumn(String::trim).setCaption("Value:4").setWidth(100);
//	  grid.addColumn(String::trim).setCaption("Value:5").setWidth(100);
	  
		return grid;
	}

}
