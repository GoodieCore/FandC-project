package Filter;

import Channel.DataModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

//Фильтр отображения графика
public class GraphDisplayFilter implements Filter<DataModel> {
    @Override
    public DataModel apply(DataModel dataModel) {
        // логику отображения графика
        // отображаем линейный график с помощью JavaFX
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.getData().add(dataModel.getGraphModel());

        Stage chartStage = new Stage();
        chartStage.setTitle("Line Chart");
        chartStage.setScene(new Scene(lineChart, 800, 600));
        chartStage.show();

        // Преобразуем график в изображение
        File outputFile = new File("src/main/resources/line_chart.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(lineChart.snapshot(null, null), null), "png", outputFile);
        } catch (IOException e) {
            System.out.println("Error occurred while saving the chart as an image: " + e.getMessage());
        }
        return dataModel;
    }
}
