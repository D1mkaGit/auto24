package ee.auto24.parsing;

import ee.auto24.parsing.objects.Car;
import ee.auto24.parsing.objects.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String urlForParce = "http://www.auto24.ee/kasutatud/nimekiri.php?bn=2&a=101&aj=&ae=2&af=200&ag=0&ag=1&otsi=otsi";
        Document doc = Jsoup.connect(urlForParce).get();

        //select all available car makes
        List<Car> makeList = new ArrayList<>();
        List<String> urlForParceMake = new ArrayList<>();
        Elements selectElements = doc.getElementsByAttributeValue("name", "b");

        selectElements.forEach(selectElement -> {

            for (int i = 1; i < selectElement.childNodeSize(); i++) {
                Element optionElement = selectElement.child(i);
                String optionId = optionElement.val();
                String optionText = optionElement.text();

                urlForParceMake.add(urlForParce + "&b=" + optionId);
                makeList.add(new Car(optionId, optionText));
            }
        });
        //end of car make selection

        //select count of available cars for selected make
        urlForParceMake.forEach(urlInList -> {
            String url = urlInList;
            int modifier = 200;
            try {
                Document document = Jsoup.connect(url).get();
                Elements numOfLines = document.select("div:nth-child(3) > div.current-range > span.label > strong");
                int intNumberOfLines = Integer.parseInt(numOfLines.text());
                String makeRowSelecotor = "#usedVehiclesSearchResult > tbody > tr:has(td.make_and_model) > td.make_and_model > a";

                if (intNumberOfLines > modifier) {
                    int countOf = intNumberOfLines / modifier;
                    for (int m = 1; m <= countOf; m++) {
                        int startingFrom = modifier * m;
                        String modifiedUrl = url + "&ak=" + startingFrom;
                        document = Jsoup.connect(modifiedUrl).get();
                    }
                }
                Elements makeRow = document.select(makeRowSelecotor);
                Logger.logRow(makeRow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
