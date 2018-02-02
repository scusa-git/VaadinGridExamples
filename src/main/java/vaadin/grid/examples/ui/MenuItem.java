package vaadin.grid.examples.ui;

import org.springframework.util.ClassUtils;

import com.vaadin.spring.internal.Conventions;

public class MenuItem {

    private ViewType type;
    private String label;
    private String viewName;
    private Class<? extends GridView> viewClass;

    public MenuItem(ViewType type, String label, Class<? extends GridView> viewClass) {
        super();
        this.type = type;
        this.label = label;
        this.viewClass = viewClass;
        if (viewClass != null) {
            Class<?> realBeanClass = ClassUtils.getUserClass(viewClass);
            String viewName = realBeanClass.getSimpleName().replaceFirst("View$", "");
            this.viewName = Conventions.upperCamelToLowerHyphen(viewName);
        }
    }

    public String getLabel() {
        return label;
    }

    public ViewType getType() {
        return type;
    }

    public void setType(ViewType type) {
        this.type = type;
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


    @Override
    public String toString() {
        return "MenuItem [type=" + type + ", label=" + label + ", viewName=" + viewName + "]";
    }

    public Class<? extends GridView> getViewClass() {
        return viewClass;
    }
}
