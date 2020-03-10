package ca2;
/**
 *
 * @author N00181859
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import utils.Rangedrandom;
public class CA2 {
    public static void main(String[] args) {
        final Random ROBJ = new Random();
        int desiredBikeAccessories = 10;
        int desiredBikes = 10;
        int desiredProducts = 10;
        
        //This is for testing generics and does not serve a purpose in the program:
        TesType<Boolean, Double> obj = new TesType<>((Rangedrandom.genInt(0,3,ROBJ)==2), (Rangedrandom.genInt(0,3,ROBJ)==2) ? 2.5 : 9.10);
        List<Object> myList = obj.func();
        System.out.println(myList);
        
        //Create selection arrays for pseudorandom object generation:
        String colours[] = {"Red","Blue","Green","Black","White","Brown","Yellow","Pink"};
        String baProductName[] = {"Spray paint","Bike light","Grips","Bell","Hi-vis vest","Wheel","Handlebars","Toolbox"};
        String bProductName[] = {"Sports", "Heavy", "Mountain bike", "Offroad"};
        String productType[] = {"Customizable","Bike sticker","Parts","Tools","Misc"};
        String brands[] = {"Zoltec", "Pinarello", "BMC", "Trek", "Specialized", "Raleigh", "GT", "Focus", "Cannondale", "Cervelo", "Diamondback", "Kona",};
        
        //Example obj arrays
        int arrLengths = 5;
        Bicycle arrBicycle[] = new Bicycle[arrLengths];
        BicycleAccessories arrBicycleAccessories[] = new BicycleAccessories[arrLengths];
        Product arrProduct[] = new Product[arrLengths];
        
        for(int z = 0; z < arrLengths; ++z){
            arrBicycleAccessories[z] = (new BicycleAccessories(z+1
                    , Rangedrandom.genDouble(5, 50, ROBJ)
                    , colours[Rangedrandom.genInt(0, colours.length-1, ROBJ)]
                    , baProductName[Rangedrandom.genInt(0, baProductName.length-1, ROBJ)]
                    , productType[Rangedrandom.genInt(0, productType.length-1, ROBJ)]
                    , !(Rangedrandom.genInt(0,4, ROBJ) == 1)));
            
            arrBicycle[z] = (new Bicycle(z+1
                    , Rangedrandom.genDouble(5, 50, ROBJ)
                    , colours[Rangedrandom.genInt(0, colours.length-1, ROBJ)]
                    , bProductName[Rangedrandom.genInt(0, bProductName.length-1, ROBJ)]
                    , Rangedrandom.genInt(2, 14, ROBJ)
                    , Rangedrandom.genInt(10000, 100000, ROBJ)
                    , Rangedrandom.genDouble(5, 35, ROBJ)
                    , brands[Rangedrandom.genInt(0, brands.length-1, ROBJ)]));
            
            if(Rangedrandom.genInt(0, 2, ROBJ) == 1){
                arrProduct[z] = arrBicycle[z];
            }
            else{
                arrProduct[z] = arrBicycleAccessories[z];
            }
        }
        
        System.out.print("\nBicycleAccessories array:\n");
        for(BicycleAccessories aba : arrBicycleAccessories){
            System.out.println(aba.displayAll());
        }
        System.out.print("\nBicycle array:\n");
        for(Bicycle ab : arrBicycle){
            System.out.println(ab.displayAll());
        }
        System.out.print("\nSuperclass array:\n");
        for(Product ap : arrProduct){
            System.out.println(ap.displayAll());
        }
        System.out.print("\n");
        
        //Create ArrayLists and add desired amount of entries to it
        List<BicycleAccessories> baObjArray = new ArrayList<>();
        for(int i = 1; i < desiredBikeAccessories+1; ++i){
            baObjArray.add(new BicycleAccessories(i
                    , Rangedrandom.genDouble(5, 50, ROBJ)
                    , colours[Rangedrandom.genInt(0, colours.length-1, ROBJ)]
                    , baProductName[Rangedrandom.genInt(0, baProductName.length-1, ROBJ)]
                    , productType[Rangedrandom.genInt(0, productType.length-1, ROBJ)]
                    , !(Rangedrandom.genInt(0,4, ROBJ) == 1))); //Condensed random object generator
        }
        
        List<Bicycle> bicycleObjArray = new ArrayList<>();
        for(int y = 1; y < desiredBikes+1; ++y){
            bicycleObjArray.add(new Bicycle(y
                    , Rangedrandom.genDouble(5, 50, ROBJ)
                    , colours[Rangedrandom.genInt(0, colours.length-1, ROBJ)]
                    , bProductName[Rangedrandom.genInt(0, bProductName.length-1, ROBJ)]
                    , Rangedrandom.genInt(2, 14, ROBJ)
                    , Rangedrandom.genInt(10000, 100000, ROBJ)
                    , Rangedrandom.genDouble(5, 35, ROBJ)
                    , brands[Rangedrandom.genInt(0, brands.length-1, ROBJ)]));
        }
        
        List<Product> productObjArray = new ArrayList<>();
        for(int x = 1; x < desiredProducts+1; ++x){
            if(Rangedrandom.genInt(0, 2, ROBJ) == 1)
                productObjArray.add(arrBicycle[Rangedrandom.genInt(0, arrBicycle.length-1, ROBJ)]);
            else
                productObjArray.add(arrBicycleAccessories[Rangedrandom.genInt(0, arrBicycleAccessories.length-1, ROBJ)]);
        }
        
        //Print info of all generated objects:
        System.out.print("Bicycle ArrayList:\n");
        for (Bicycle b : bicycleObjArray){
            System.out.println(b.displayAll());
        }
        System.out.print("\nBicycleAccessories ArrayList:\n");
        for (BicycleAccessories ba : baObjArray){
            System.out.println(ba.displayAll());
        }
        System.out.print("\nProduct ArrayList:\n");
        for (Product poa : productObjArray){
            System.out.println(poa.displayAll());
        }
        
        //Store, one to many testing:
        List<Product> pl = new ArrayList<>();
        new Store(0,"","",pl);
    }
}
