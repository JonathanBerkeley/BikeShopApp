package utils;

import ca2.Bicycle;
import ca2.BicycleAccessories;
import ca2.Product;
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
    int desiredBikeAccessories = 10;
    int desiredBikes = 10;
    int desiredProducts = 10;

    //Example obj arrays
    int arrLengths = 5;
    Bicycle arrBicycle[] = new Bicycle[arrLengths];
    BicycleAccessories arrBicycleAccessories[] = new BicycleAccessories[arrLengths];
    Product arrProduct[] = new Product[arrLengths];

    public void populate(Object ob) {
        for (int z = 0; z < arrLengths; ++z) {
            arrBicycleAccessories[z] = (new BicycleAccessories(z + 1,
                     Rangedrandom.genDouble(5, 50, ROBJ),
                     colours[Rangedrandom.genInt(0, colours.length - 1, ROBJ)],
                     baProductName[Rangedrandom.genInt(0, baProductName.length - 1, ROBJ)],
                     productType[Rangedrandom.genInt(0, productType.length - 1, ROBJ)],
                     !(Rangedrandom.genInt(0, 4, ROBJ) == 1)));

            arrBicycle[z] = (new Bicycle(z + 1,
                     Rangedrandom.genDouble(5, 50, ROBJ),
                     colours[Rangedrandom.genInt(0, colours.length - 1, ROBJ)],
                     bProductName[Rangedrandom.genInt(0, bProductName.length - 1, ROBJ)],
                     Rangedrandom.genInt(2, 14, ROBJ),
                     Rangedrandom.genInt(10000, 100000, ROBJ),
                     Rangedrandom.genDouble(5, 35, ROBJ),
                     brands[Rangedrandom.genInt(0, brands.length - 1, ROBJ)]));

            if (Rangedrandom.genInt(0, 2, ROBJ) == 1) {
                arrProduct[z] = arrBicycle[z];
            } else {
                arrProduct[z] = arrBicycleAccessories[z];
            }
        }
    }
}
