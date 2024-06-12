package Channel;

import Filter.Filter;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private List<Filter<DataModel>> filters = new ArrayList<>();

    public void addFilter(Filter<DataModel> filter) {
        filters.add(filter);
    }

    public void processData(DataModel dataModel) {
        for (Filter<DataModel> filter : filters) {
            dataModel = filter.apply(dataModel);
        }
    }
    public void setFilters(List<Filter<DataModel>> filters) {
        this.filters = filters;
    }
}
