package vaadin.grid.examples.utils;

import org.springframework.util.ClassUtils;

import com.vaadin.spring.internal.Conventions;

import vaadin.grid.examples.ui.component.abstracts.ComponentView;

/**
 * This util class provides a few shortcuts.
 */
public abstract class Utils {

	public static String getPathToClass(Class<?> clazz) {
		return clazz.getCanonicalName().replaceAll("\\.", "/") + ".java";
	}

	public static String getClassViewName(Class<? extends ComponentView> viewClass) {
		if (viewClass != null) {
			Class<?> realBeanClass = ClassUtils.getUserClass(viewClass);
			String viewName = realBeanClass.getSimpleName().replaceFirst("View$", "");
			return Conventions.upperCamelToLowerHyphen(viewName);
		}

		return null;
	}
}
