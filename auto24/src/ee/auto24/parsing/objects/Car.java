package ee.auto24.parsing.objects;

public class Car {
    private String make;
    private String makeId;
    private String makeValue;
    private String makeCountBySearch;

    public Car(String make) {
        this.make = make;
    }

    public Car(String makeId, String makeValue) {
        this.makeId = makeId;
        this.makeValue = makeValue;
    }

    public String getMakeId() {
        return makeId;
    }

    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }

    public String getMakeValue() {
        return makeValue;
    }

    public void setMakeValue(String makeValue) {
        this.makeValue = makeValue;
    }

    public String getMakeCountBySearch() {
        return makeCountBySearch;
    }

    public void setMakeCountBySearch(String makeCountBySearch) {
        this.makeCountBySearch = makeCountBySearch;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return make;
    }
}
