package ee.auto24.parsing;

public class Main {

    public static void main(String[] args) {

        //
        // logCars.createdMakeList();
        logCars.bmwOnly();

        //select all available car makes

        //end of car make selection

        //select count of available cars for selected make
        logCars.logSelectedMake(logCars.urlForParceMake);
    }
}
