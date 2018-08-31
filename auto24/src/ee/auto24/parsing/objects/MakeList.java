package ee.auto24.parsing.objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MakeList {

    private String strKey;
    private String strValue;
    private String url;
    private ArrayList<Car> makeList = new ArrayList<>();
    private List<String> urlForParceMake = new ArrayList<>();

    public MakeList(String url, String strKey, String strValue) throws IOException {
        this.strKey = strKey;
        this.strValue = strValue;
        this.url = url;

        Document doc = Jsoup.connect(url).get();
        Elements selectElements = doc.getElementsByAttributeValue(strKey, strValue);
        selectElements.forEach(selectElement -> {

            for (int i = 1; i < selectElement.childNodeSize(); i++) {
                Element optionElement = selectElement.child(i);
                String optionId = optionElement.val();
                String optionText = optionElement.text();

                urlForParceMake.add(url + "&b=" + optionId);
                makeList.add(new Car(optionId, optionText));

            }
        });

    }

    public ArrayList<Car> getMakeList() {
        return makeList;
    }

    public List<String> getUrlForParceMake() {
        return urlForParceMake;
    }

    @Override
    public String toString() {

        return "MakeList{" +
                "urlForParceMake=" + urlForParceMake +
                '}';

    }
}
