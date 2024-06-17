package org.example;
import org.jsoup.*;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;

import java.sql.*;
import java.sql.Connection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static List<String>trainNumbers = new ArrayList<>();
    static List<String>trainDurations = new ArrayList<>();
    static List<String>trainDates = new ArrayList<>();
    private static Connection connect;
    private static Statement statement;
    private static PreparedStatement prepare;
    private static ResultSet result;


    public static void main(String[] args) throws InterruptedException, SQLException {


        System.setProperty("webdriver.edge.driver", "D:\\WebDriver\\msedgedriver.exe");
        String url = "https://xn----btbhgbpv1d7d.xn--80aswg/kupit-zhd-bilety/#/sochi/krasnodar";

        WebDriver driver = new EdgeDriver();
        driver.get(url);
        Duration duration = Duration.ofSeconds(10);
        Thread.sleep(1200);
        String pageSource = driver.getPageSource();
        Document document = Jsoup.parse(pageSource);
        Elements trainNumElem = document.select("span.wg-train-info__number-link");
        Elements startDateElem = document.select("span.wg-track-info__date");
        Elements durationElem = document.select("span.wg-track-info__duration-time");
        for (Element element : trainNumElem) {
            String trainNumber = element.text();
            if(trainNumber.length()>7){
                continue;}
            trainNumbers.add(trainNumber);

        }
        for(Element element : startDateElem)
        {
            String trainDate = element.text();
            trainDates.add(trainDate);
        }
        for(Element element : durationElem)
        {
            String trainDuration = element.text();
            trainDurations.add(trainDuration);
        }
        printTrain();
    }



}
