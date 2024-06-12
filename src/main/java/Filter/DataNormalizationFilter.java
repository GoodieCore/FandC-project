package Filter;

import Channel.DataModel;
import Strategy.NormalizationStrategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//активный фильтр нормализации данных
public class DataNormalizationFilter implements Filter<DataModel> {
    private NormalizationStrategy strategy;

    public DataNormalizationFilter(NormalizationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public DataModel apply(DataModel dataModel) {
        List<Double> normalizedData = strategy.normalize(dataModel.getData());
        dataModel.setData(normalizedData);
        return dataModel;
    }
}
