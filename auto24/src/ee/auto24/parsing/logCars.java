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
    private static final int modifier = 200;
    private static final String makeRowSelector = "#usedVehiclesSearchResult > tbody > tr > td.make_and_model > a";
    public static String urlForTakeMakes = "https://www.auto24.ee/kasutatud/nimekiri.php?bn=2&a=101102&aj=&ae=2&af=" + modifier + "&ag=0&ag=1&otsi=otsi";
    public static String urlForParce = "https://www.auto24.ee/kasutatud/nimekiri.php?bn=2&a=100&aj=&j[]=1&j[]=2&j[]=3&j[]=4&j[]=5&j[]=6&j[]=61&j[]=67&j[]=7&j[]=8&j[]=9&j[]=10&j[]=11&ae=2&af=" + modifier + "&ag=0&ag=1&otsi=otsi";
    private static Element numOfLines;
    private static Elements makeRow;
    private static Document document;

    public static logCars logSelectedMake(List<String> urlForParceMake) {
        urlForParceMake.forEach(urlInList -> {
            String url = urlInList;
            int intNumberOfLines;
            try {
                document = Jsoup.connect(url).get();
                intNumberOfLines = getNumberOfLinesInFirstPage();

                int countOf = intNumberOfLines / modifier;
                if (countOf < 0) countOf = 1;
                for (int m = 0; m <= countOf; m++) {
                    int startingFrom = modifier * m;
                    String modifiedUrl = url + "&ak=" + startingFrom;
                    document = Jsoup.connect(modifiedUrl).get();
                    makeRow = document.select(makeRowSelector);
                    Logger.logRow(makeRow);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return null;
    }

    private static int getNumberOfLinesInFirstPage() {
        numOfLines = document.selectFirst("div.paginator > div.current-range > span.label > strong");
        if (numOfLines == null) return 0;
        return Integer.parseInt(numOfLines.text());
    }

    public static logCars createdMakeList() throws IOException {
        Document doc = Jsoup.connect(urlForTakeMakes).get();
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
