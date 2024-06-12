package org.example.fandcproject;

import Channel.Channel;
import Database.DataFetcher;
import Filter.*;
import Strategy.MinMaxNormalizationStrategy;
import Strategy.NormalizationStrategy;
import Strategy.ZScoreNormalizationStrategy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import Channel.DataModel;
import java.util.List;

public class HelloController {
    public void onStartBtnClick(ActionEvent event) {
// создаем объект DataFetcher
        DataFetcher dataFetcher = new DataFetcher();

        // создаем объект DataModel
        List<Double> data = dataFetcher.getDataFromDatabase();
        DataModel dataModel = new DataModel(data);

        // создаем канал
        Channel channel = new Channel();
        // добавляем в него нужные фильтры
        channel.addFilter(new DataValidationFilter());
        channel.addFilter(new StatisticalAnalysisFilter());
        channel.addFilter(new ActiveDataFilter(5.0));// добавляем активный фильтр с порогом 5.0
        //channel.addFilter(new DataNormalizationFilter());// добавляем активный фильтр нормализации от 0 до 1
        // Создайте экземпляр стратегии нормализации и передайте его в фильтр нормализации данных
        NormalizationStrategy normalizationStrategy = new ZScoreNormalizationStrategy(); // или новый ZScoreNormalizationStrategy()
        channel.addFilter(new DataNormalizationFilter(normalizationStrategy));
        channel.addFilter(new GraphModelFilter());
        channel.addFilter(new GraphDisplayFilter());

        channel.addFilter(new LoggingErrorFilter());

        // обрабатываем данные в канале
        channel.processData(dataModel);
    }
}