package ee.auto24.parsing.objects;

public class Car {
    private String make;
    private String makeId;
    private String makeValue;
    private String makeCountBySearch;

    private String carMake;
    private String makeModel;
    private String carColor;
    private String privod;
    private String korobka;
    private String carYear;
    private String tipTopliva;
    private String objom;
    private String probeg;
    private String cena;
    private String cena2;

    private String carUrl;

    public Car() {
    }

    public Car(String make) {
        this.make = make;
    }

    public Car(String makeId, String makeValue) {
        this.makeId = makeId;
        this.makeValue = makeValue;
    }

    public String getCarUrl() {
        return carUrl;
    }

    public void setCarUrl(String carUrl) {
        this.carUrl = carUrl;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getPrivod() {
        return privod;
    }

    public void setPrivod(String privod) {
        this.privod = privod;
    }

    public String getKorobka() {
        return korobka;
    }

    public void setKorobka(String korobka) {
        this.korobka = korobka;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public String getTipTopliva() {
        return tipTopliva;
    }

    public void setTipTopliva(String tipTopliva) {
        this.tipTopliva = tipTopliva;
    }

    public String getObjom() {
        return objom;
    }

    public void setObjom(String objom) {
        this.objom = objom;
    }

    public String getProbeg() {
        return probeg;
    }

    public void setProbeg(String probeg) {
        this.probeg = probeg;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getCena2() {
        return cena2;
    }

    public void setCena2(String cena2) {
        this.cena2 = cena2;
    }

    @Override
    public String toString() {
        return make;
    }


}
