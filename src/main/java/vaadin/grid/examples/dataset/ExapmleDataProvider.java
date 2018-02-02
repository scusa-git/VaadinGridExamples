package vaadin.grid.examples.dataset;

import java.util.stream.Stream;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;

public class ExapmleDataProvider extends AbstractBackEndDataProvider<Row, Object> {

	@Override
	protected Stream<Row> fetchFromBackEnd(Query<Row, Object> query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int sizeInBackEnd(Query<Row, Object> query) {
		// TODO Auto-generated method stub
		return 0;
	}



}
