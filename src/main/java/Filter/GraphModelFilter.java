package Filter;

import Channel.DataModel;
import javafx.scene.chart.XYChart;

//Фильтр формирования модели графика
public class GraphModelFilter implements Filter<DataModel> {
    @Override
    public DataModel apply(DataModel dataModel) {
        // логику формирования модели графика
        // создаем модель линейного графика
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < dataModel.getData().size(); i++) {
            series.getData().add(new XYChart.Data<>(i, dataModel.getData().get(i)));
        }
        // устанавливаем модель графика в объект DataModel
        dataModel.setGraphModel(series);
        return dataModel;
    }
}
