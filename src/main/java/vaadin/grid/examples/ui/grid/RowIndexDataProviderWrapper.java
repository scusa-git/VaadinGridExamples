package vaadin.grid.examples.ui.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.DataProviderListener;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.Registration;

public class RowIndexDataProviderWrapper<T extends RowIndexAware, F> implements DataProvider<T, F> {

	private static final long serialVersionUID = 2368246311080911126L;

	private DataProvider<T, F> wrapped;

	public RowIndexDataProviderWrapper(DataProvider<T, F> wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public boolean isInMemory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size(Query<T, F> query) {
		// TODO Auto-generated method stub
		return wrapped.size(query);
	}

	@Override
	public Stream<T> fetch(Query<T, F> query) {
		List<T> result = wrapped.fetch(query).collect(Collectors.toCollection(ArrayList::new));
		for (int i = 0; i < result.size(); i++) {
			result.get(i).setRowIndex(query.getOffset() + i);
		}
		return result.stream();
	}

	@Override
	public void refreshItem(T item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Registration addDataProviderListener(DataProviderListener<T> listener) {
		// TODO Auto-generated method stub
		return null;
	}

}
