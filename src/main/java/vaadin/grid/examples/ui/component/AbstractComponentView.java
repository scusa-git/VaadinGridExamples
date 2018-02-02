package vaadin.grid.examples.ui.component;

import javax.annotation.PostConstruct;

import org.springframework.util.ClassUtils;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractComponentView extends VerticalLayout implements ComponentView {

	private static final long serialVersionUID = -4515260397169455248L;

		@PostConstruct
    public void postConstruct() {
        setSizeFull();
        setMargin(true);
        Component layout = this.getViewComponent();
        layout.setWidth(100, Unit.PERCENTAGE);
        addComponent(layout);
        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

    @Override
    public String getViewName() {
        Class<?> realBeanClass = ClassUtils.getUserClass(getClass());
        return realBeanClass.getSimpleName();
    }

}
