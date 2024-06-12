package Filter;

import Channel.DataModel;

//Фильтр расчета статистических характеристик
public class StatisticalAnalysisFilter implements Filter<DataModel> {
    @Override
    public DataModel apply(DataModel dataModel) {
        // логика расчета статистических характеристик
        // рассчитываем среднее значение
        double sum = 0;
        for (Double value : dataModel.getData()) {
            sum += value;
        }
        double mean = sum / dataModel.getData().size();
        // добавляем среднее значение в конец списка
        dataModel.getData().add(mean);
        return dataModel;
    }
}
