package Filter;
import Channel.DataModel;

import java.util.List;
import java.util.stream.Collectors;

//активный фильтр проверки порогового значения данных
public class ActiveDataFilter implements Filter<DataModel> {
    private double threshold;

    public ActiveDataFilter(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public DataModel apply(DataModel dataModel) {
        // логика фильтрации данных
        // оставляем только те значения, которые превышают порог
        List<Double> filteredData = dataModel.getData().stream()
                .filter(value -> value > threshold)
                .collect(Collectors.toList());
        dataModel.setData(filteredData);
        return dataModel;
    }
}
