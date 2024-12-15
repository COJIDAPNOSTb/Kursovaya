package org.example.parcer;
import org.example.train.TrainP;
import org.example.db.database;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jsoup.Jsoup;


public class Parcer {
    static List<String> trainNumbers = new ArrayList<>();
    static List<String> trainDurations = new ArrayList<>();
    static List<String> trainDates = new ArrayList<>();

    public static void runParsing() throws InterruptedException, SQLException {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.setProperty("webdriver.edge.driver", "D:\\WebDriver\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        WebDriver driver = new EdgeDriver(options);

        try {
            for (int i = 0; i < 3; i++) {
                String formattedDate = currentDate.format(formatter);
                String url = "https://xn----btbhgbpv1d7d.xn--80aswg/kupit-zhd-bilety/#/sochi/krasnodar?date=" + formattedDate;
                driver.get(url);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Устанавливаем таймаут 20 секунд
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className("wg-train-container"))); // Указываем класс элемента

                String pageSource = driver.getPageSource();
                Document document = Jsoup.parse(pageSource);
                ParsingData.parseTrainData(document);
                currentDate = currentDate.plusDays(1);
            }

            printAndWriteTrains();
        } finally {
            driver.quit();
        }
    }


    private static void printAndWriteTrains() throws SQLException {
        int j = 0;
        for (int i = 0; i < trainNumbers.size(); i++) {
            TrainP trainP = new TrainP();
            trainP.setNumber(trainNumbers.get(i));
            trainP.setDuration(trainDurations.get(i));
            trainP.setStartDate(trainDates.get(j));
            trainP.setEndDate(trainDates.get(j + 1));

            System.out.println("Номер поезда: " + trainP.getNumber());
            System.out.println(trainP.getStartDate() + "------" + trainP.getDuration() + "------" + trainP.getEndDate());
            System.out.println();

            j += 2;
            DatabaseWrite.dbWrite(trainP);
        }
    }


}
