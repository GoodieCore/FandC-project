package Channel;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private List<Double> data;
    private XYChart.Series<Number, Number> graphModel;
    private List<String> errorMessages = new ArrayList<>();

    public DataModel(List<Double> data) {
        this.data = data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public List<Double> getData() {
        return data;
    }

    public XYChart.Series<Number, Number> getGraphModel() {
        return graphModel;
    }

    public void setGraphModel(XYChart.Series<Number, Number> graphModel) {
        this.graphModel = graphModel;
    }
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(String errorMessage) {
        this.errorMessages.add(errorMessage);
    }
}
