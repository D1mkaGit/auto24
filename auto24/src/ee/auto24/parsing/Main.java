package ee.auto24.parsing;

import ee.auto24.parsing.objects.Car;
import ee.auto24.parsing.objects.CarFromDocument;
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

        List<Car> carList = new ArrayList<>();

        urlForParceMake.forEach(urlInList -> {
            String url = urlInList;
            int i = 0;
            //System.out.println(url);

            try {
                Document document = Jsoup.connect(url).get();
                Elements numOfLines = document.select("body > div.tpl-body > div.tpl-content.have-sidebar.have-bannerbar > div:nth-child(5) > div.current-range > span.label > Strong");
                Elements seldMake = document.select("#searchParam-cmm-1-make option[selected]");

                //Elements makeRow = doc.getElementsByAttributeValue("class", "make_and_model");

                String numberOfLines = numOfLines.text();
                int intNumberOfLines = Integer.parseInt(numberOfLines);


                String selectedMake = seldMake.text();

                Elements makeRow = document.select("#usedVehiclesSearchResult > tbody > tr:has(td.make_and_model) > td.make_and_model > a");
                Elements makePriceRow = document.select("#usedVehiclesSearchResult > tbody > tr.result-row.item-odd > td.price");

                makeRow.forEach(Element -> {

                    String make = Element.text();
                    String makeUrl = Element.attr("abs:href");
                    //System.out.println(make+";"+makeUrl);

                    new CarFromDocument(makeUrl);
                    //System.out.println(new CarFromDocument(makeUrl).getTextToWrite());
                    try {
                        Logger.log(new CarFromDocument(makeUrl).getTextToWrite());
                        Logger.log(System.getProperty("line.separator"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                });

                if (intNumberOfLines > 200) {

                    int countOf = intNumberOfLines / 200;
                    //System.out.println(countOf);

                    int modifier = 200;

                    for (int m = 1; m <= countOf; m++) {
                        int startingFrom = modifier * m;
                        String modifiedUrl = url + "&ak=" + startingFrom;
                        //System.out.println(modifiedUrl);
                        Document docInLoop = Jsoup.connect(modifiedUrl).get();
                        Elements makeRow2 = docInLoop.select("#usedVehiclesSearchResult > tbody > tr:has(td.make_and_model) > td.make_and_model > a");
                        Elements makePriceRow2 = document.select("#usedVehiclesSearchResult > tbody > tr:nth-child(1) > td.price");
                        makeRow2.forEach(Element -> {

                            String make = Element.text();
                            String makeUrl = Element.attr("abs:href");
                            //System.out.println(make+";"+makeUrl);
                            new CarFromDocument(makeUrl);
                            try {
                                Logger.log(new CarFromDocument(makeUrl).getTextToWrite());
                                Logger.log(System.getProperty("line.separator"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }


                }


//
                //String makeUrl = makeRow.attr("href");
                //System.out.println(selectedMake +" - "+numberOfLines);


                //carList.forEach(System.out::println);
               /* FileWriter writer = new FileWriter("output.txt");
                for(Car str: carList) {
                    writer.write(String.valueOf(str+ "\n"));
                }
                writer.close();*/

            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        //end of select count of available cars for selected make

        //select all cars from web

        //List<Car> carList = new ArrayList<>();

        //Elements tdElements = doc.getElementsByAttributeValue("class", "make_and_model");

        /*tdElements.forEach(tdElement ->{
            Element aElement = tdElement.child(0);
            String makeAndModel = aElement.text();

            carList.add(new Car(makeAndModel));
        });*/

        // carList.forEach(System.out::println);

        //end of select all cars from list

    }
}
