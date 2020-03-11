package utils;

import ca2.Bicycle;
import ca2.BicycleAccessories;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author N00181859
 */
public class DatabasePopulator {
    
    //todo fix this
    
    //Random object
    private final Random ROBJ = new Random();

    //Create selection arrays for pseudorandom object generation:
    private final String colours[] = {"Red", "Blue", "Green", "Black", "White", "Brown", "Yellow", "Pink"};
    private final String baProductName[] = {"Spray paint", "Bike light", "Grips", "Bell", "Hi-vis vest", "Wheel", "Handlebars", "Toolbox"};
    private final String bProductName[] = {"Sports", "Heavy", "Mountain bike", "Offroad"};
    private final String productType[] = {"Customizable", "Bike sticker", "Parts", "Tools", "Misc"};
    private final String brands[] = {"Zoltec", "Pinarello", "BMC", "Trek", "Specialized", "Raleigh", "GT", "Focus", "Cannondale", "Cervelo", "Diamondback", "Kona",};

    //Condensed random object generator
    public void populate(int desBA, int desB){
        //Create ArrayLists and add desired amount of entries to it
        List<BicycleAccessories> baObjArray = new ArrayList<>();
        for(int i = 1; i < desBA+1; ++i){
            baObjArray.add(new BicycleAccessories(i
                    , Rangedrandom.genDouble(5, 50, ROBJ)
                    , colours[Rangedrandom.genInt(0, colours.length-1, ROBJ)]
                    , baProductName[Rangedrandom.genInt(0, baProductName.length-1, ROBJ)]
                    , productType[Rangedrandom.genInt(0, productType.length-1, ROBJ)]
                    , !(Rangedrandom.genInt(0,4, ROBJ) == 1))); 
        }
        
        List<Bicycle> bicycleObjArray = new ArrayList<>();
        for(int y = 1; y < desB+1; ++y){
            bicycleObjArray.add(new Bicycle(y
                    , Rangedrandom.genDouble(5, 50, ROBJ)
                    , colours[Rangedrandom.genInt(0, colours.length-1, ROBJ)]
                    , bProductName[Rangedrandom.genInt(0, bProductName.length-1, ROBJ)]
                    , Rangedrandom.genInt(2, 14, ROBJ)
                    , Rangedrandom.genInt(10000, 100000, ROBJ)
                    , Rangedrandom.genDouble(5, 35, ROBJ)
                    , brands[Rangedrandom.genInt(0, brands.length-1, ROBJ)]));
        }
        
        for(BicycleAccessories ba : baObjArray){
            //Commit to db here
            System.out.println(ba.displayAll());
        }
        
        for(Bicycle b : bicycleObjArray){
            //Commit to db here
            System.out.println(b.displayAll());
        }
    }
}
