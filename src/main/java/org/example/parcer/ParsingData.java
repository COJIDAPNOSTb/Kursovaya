package org.example.parcer;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static org.example.parcer.Parcer.*;

public class ParsingData {

    static void parseTrainData(Document document) {
        Elements trainNumElem = document.select("span.wg-train-info__number-link");
        Elements startDateElem = document.select("span.wg-track-info__date");
        Elements durationElem = document.select("span.wg-track-info__duration-time");

        for (var element : trainNumElem) {
            String trainNumber = element.text();
            if (trainNumber.length() <= 7) {
                trainNumbers.add(trainNumber);
            }
        }

        for (var element : startDateElem) {
            trainDates.add(element.text());
        }

        for (var element : durationElem) {
            trainDurations.add(element.text());
        }
    }
}
