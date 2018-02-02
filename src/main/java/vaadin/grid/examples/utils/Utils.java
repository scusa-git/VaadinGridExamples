package vaadin.grid.examples.utils;

/**
 * This util class provides a few shortcuts.
 */
public abstract class Utils {

	public static String getPathToClass(Class<?> clazz) {
		return clazz.getCanonicalName().replaceAll("\\.", "/") + ".java";
	}
}
