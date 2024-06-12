package Filter;

import Channel.DataModel;

import java.util.ArrayList;
import java.util.List;

//фильтр проверки данных
public class DataValidationFilter implements Filter<DataModel> {
    @Override
    public DataModel apply(DataModel dataModel) {
        List<Double> validData = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
        // логику проверки данных
        // проверяем, что все значения положительные
        for (Double value : dataModel.getData()) {
            if (value <= 0) {
                errorMessages.add("Value must be positive: " + value);
            } else {
                validData.add(value);
            }
        }
        dataModel.setData(validData);
        dataModel.setErrorMessages(errorMessages);
        return dataModel;
    }
}
