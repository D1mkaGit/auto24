package ee.auto24.parsing.objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.SocketTimeoutException;


public class CarFromDocument {
    private String textToWrite;
    private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36";

    public CarFromDocument(String carUrl) {
        Car car = new Car();
        car.setCarUrl(carUrl);

        Document document = null;
        for (int i = 0; i < 3; i++) {


            try {
                document = Jsoup.connect(carUrl)
                        .userAgent(userAgent)
                        .referrer("http://www.google.com")
                        .followRedirects(true)
                        .get();
                break;

            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (document != null) {
            String properCarMakeChildElelement = "4";
            String prperMakeModelChildElement = "5";
            String oksjon = "";
            String oksjonText = "Oksjon";
            if (document.select("tr.field-auction").text().equals(oksjonText)) {
                properCarMakeChildElelement = "5";
                prperMakeModelChildElement = "6";
                oksjon = oksjonText;
            }
            car.setCarMake(document.select("#navi-links > a:nth-child(" + properCarMakeChildElelement + ")").text());
            car.setMakeModel(document.select("#navi-links > a:nth-child(" + prperMakeModelChildElement + ")").text());
            car.setCarColor(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-varvus > td.field > span").text());
            car.setPrivod(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-vedavsild > td.field > span").text());
            car.setKorobka(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-kaigukast_kaikudega > td.field > span").text());
            car.setCarYear(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-month_and_year > td.field > span").text());
            car.setTipTopliva(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-kytus > td.field > span").text());
            car.setObjom(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-mootorvoimsus > td.field > span").text());
            car.setProbeg(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-labisoit > td.field > span").text());
            car.setCena(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-hind > td.field > span.value").text());
            car.setCena2(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-soodushind > td.field > span.value").text());

            textToWrite = car.getCarMake() + ";" + car.getMakeModel() + ";" + car.getCarColor() + ";" + car.getPrivod() + ";" + car.getKorobka() + ";" + car.getCarYear() + ";" + car.getTipTopliva() + ";" + car.getObjom() + ";" + car.getProbeg() + ";" + car.getCena() + ";" + car.getCena2() + oksjon + ";" + carUrl;
            System.out.println(textToWrite);

        } else {
            System.err.println("Ошибка соединения, док пустой");
        }
    }

    public String getTextToWrite() {
        return textToWrite;
    }

    @Override
    public String toString() {
        return "CarFromDocument{" +
                "textToWrite='" + textToWrite + '\'' +
                '}';
    }

    //Car make: #navi-links > a:nth-child(4)
    //Car model: #navi-links > a:nth-child(5)
    //cvet: body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-varvus > td.field > span
    //tip kuzova: body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-keretyyp > td.field > span
    //privod: body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-vedavsild > td.field > span
    //korobka: body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-kaigukast_kaikudega > td.field > span
    //god: body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-month_and_year > td.field > span
    //tip topliva: body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-kytus > td.field > span
    //objom(kw): body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-mootorvoimsus > td.field > span
    //probeg: body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-labisoit > td.field > span
    //cena: body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-hind > td.field > span.value
    //cena so skidkoj: body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-soodushind > td.field > span.value

}
