package ee.auto24.parsing.objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class CarFromDocument {
    private String textToWrite;

    public CarFromDocument(String carUrl) {
        Car car = new Car();
        car.setCarUrl(carUrl);
        try {
            Document document = Jsoup.connect(carUrl).get();
            /*TODO: if auction add extra child for setCarMake and setMakeModel*/
            car.setCarMake(document.select("#navi-links > a:nth-child(4)").text());
            car.setMakeModel(document.select("#navi-links > a:nth-child(5)").text());
            car.setCarColor(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-varvus > td.field > span").text());
            car.setPrivod(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-vedavsild > td.field > span").text());
            car.setKorobka(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-kaigukast_kaikudega > td.field > span").text());
            car.setCarYear(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-month_and_year > td.field > span").text());
            car.setTipTopliva(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-kytus > td.field > span").text());
            car.setObjom(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-mootorvoimsus > td.field > span").text());
            car.setProbeg(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-labisoit > td.field > span").text());
            car.setCena(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-hind > td.field > span.value").text());
            car.setCena2(document.select("body > div.tpl-body > div.tpl-content.have-alt_sidebar > div.data-container > table > tbody > tr.field-soodushind > td.field > span.value").text());

            textToWrite = car.getCarMake() + ";" + car.getMakeModel() + ";" + car.getCarColor() + ";" + car.getPrivod() + ";" + car.getKorobka() + ";" + car.getCarYear() + ";" + car.getTipTopliva() + ";" + car.getObjom() + ";" + car.getProbeg() + ";" + car.getCena() + ";" + car.getCena2() + ";" + carUrl;
            System.out.println(textToWrite);

        } catch (IOException e) {
            e.printStackTrace();
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
