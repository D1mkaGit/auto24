package ee.auto24.parsing;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //
        logCars.createdMakeList();
        //logCars.bmwOnly();

        //select all available car makes

        //end of car make selection

        //select count of available cars for selected make
        logCars.logSelectedMake(logCars.urlForParceMake);
    }
}
