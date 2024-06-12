package org.example.fandcproject;

import Channel.Channel;
import Database.DataFetcher;
import Filter.*;
import Strategy.MinMaxNormalizationStrategy;
import Strategy.ZScoreNormalizationStrategy;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import Channel.DataModel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class FilterController {
    @FXML
    private ListView<String> filterList;

    @FXML
    private GridPane filterSettings;

    @FXML
    private Button addFilterButton, removeFilterButton, runButton;

    private Channel channel = new Channel();
    private List<Filter<DataModel>> filters = new ArrayList<>();
    private DataModel dataModel;

    @FXML
    public void initialize() {
        filters.add(new DataValidationFilter());
        filters.add(new StatisticalAnalysisFilter());
        filters.add(new ActiveDataFilter(5.0));
        filters.add(new DataNormalizationFilter(new ZScoreNormalizationStrategy()));
        filters.add(new DataNormalizationFilter(new MinMaxNormalizationStrategy()));
        filters.add(new GraphModelFilter());
        filters.add(new GraphDisplayFilter());
        filters.add(new LoggingErrorFilter());

        filterList.getItems().addAll(filters.stream().map(Filter::toString).collect(Collectors.toList()));

        addFilterButton.setOnAction(event -> addFilter());
        removeFilterButton.setOnAction(event -> removeFilter());
        runButton.setOnAction(event -> run());
    }
    public void setFilters(List<Filter<DataModel>> filters) {
        this.filters = filters;
    }
    @FXML
    private void addFilter() {
        Filter<DataModel> filter = new DataValidationFilter();
        filters.add(filter);
        filterList.getItems().add(filter.toString());
    }
    @FXML
    private void removeFilter() {
        int index = filterList.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            filterList.getItems().remove(index);
            filters.remove(index);
        }
    }
    @FXML
    private void run() {
        DataFetcher dataFetcher = new DataFetcher();
        List<Double> data = dataFetcher.getDataFromDatabase();
        dataModel = new DataModel(data);
        channel.setFilters(filters);// Set the filters in the channel
        channel.processData(dataModel); // Process the data with the channel
    }
    @FXML
    private void upload() {
// создаем новый документ Word
        XWPFDocument doc = new XWPFDocument();

        // создаем заголовок отчета
        XWPFParagraph titleParagraph = doc.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setText("Отчет по обработке данных");
        titleRun.setFontSize(20);
        titleRun.setBold(true);

        // создаем раздел с последовательностью фильтров
        XWPFParagraph filtersParagraph = doc.createParagraph();
        filtersParagraph.setAlignment(ParagraphAlignment.LEFT);
        filtersParagraph.createRun().setText("Последовательность фильтров:");
        filtersParagraph.createRun().setText("\n");
        for (Filter<DataModel> filter : filters) {
            filtersParagraph.createRun().setText("- " + filter.toString() + "\n");
        }

        // добавляем график в документ
        try (FileInputStream fis = new FileInputStream("src/main/resources/line_chart.png")) {
            XWPFParagraph chartParagraph = doc.createParagraph();
            chartParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun chartRun = chartParagraph.createRun();
            chartRun.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "График", Units.toEMU(500), Units.toEMU(300));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

        // сохраняем документ в файл
        try (FileOutputStream fos = new FileOutputStream("src/main/resources/report.docx")) {
            doc.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // выводим сообщение об успешном создании отчета
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText("Отчет успешно создан!");
        alert.showAndWait();
    }
}
