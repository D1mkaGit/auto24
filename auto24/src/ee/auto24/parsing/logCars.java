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


public class logCars {

    public static List<Car> makeList = new ArrayList<>();
    public static List<String> urlForParceMake = new ArrayList<>();
    public static String urlForParce = "http://www.auto24.ee/kasutatud/nimekiri.php?bn=2&a=101&aj=&ae=2&af=200&ag=0&ag=1&otsi=otsi";

    public static logCars logSelectedMake(List<String> urlForParceMake) {
        urlForParceMake.forEach(urlInList -> {
            String url = urlInList;
            int modifier = 200;
            try {
                Document document = Jsoup.connect(url).get();
                Element numOfLines = document.selectFirst("div.paginator > div.current-range > span.label > strong");

                int intNumberOfLines = Integer.parseInt(numOfLines.text());
                String makeRowSelecotor = "#usedVehiclesSearchResult > tbody > tr > td.make_and_model > a";
                Elements makeRow = document.select(makeRowSelecotor);
                Logger.logRow(makeRow); //first 200 rows logged there

                if (intNumberOfLines > modifier) {
                    int countOf = intNumberOfLines / modifier;
                    for (int m = 1; m <= countOf; m++) {
                        int startingFrom = modifier * m;
                        String modifiedUrl = url + "&ak=" + startingFrom;
                        document = Jsoup.connect(modifiedUrl).get();
                        makeRow = document.select(makeRowSelecotor);
                        Logger.logRow(makeRow); // starting from 201 car loging here
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return null;
    }

    public static logCars createdMakeList() throws IOException {
        Document doc = Jsoup.connect(urlForParce).get();
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
        return null;
    }

    public static logCars bmwOnly() {
        String bmwId = "4";
        urlForParceMake.add(urlForParce + "&b=" + bmwId);
        makeList.add(new Car(bmwId, "BMW"));

        return null;
    }
}
