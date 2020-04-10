package ca2;
/**
 *
 * @author N00181859
 */
public class Bicycle extends Product{
    private int gearCount, modelNo;
    private double weight;
    private String brand;
    
    //Constructor with id
    public Bicycle(int id, double pr, String co, String pn, int gc, int mn, double wt, String br, int strID){
        super(id, pr, co, pn, strID);
        this.gearCount = gc;
        this.modelNo = mn;
        this.weight = wt;
        this.brand = br;
    }
    
    //Constructor without id
    public Bicycle(double pr, String co, String pn, int gc, int mn, double wt, String br, int strId){
        super(-1, pr, co, pn, strId);
        this.gearCount = gc;
        this.modelNo = mn;
        this.weight = wt;
        this.brand = br;
    }
    
    //Gets
    public int getGearCount(){
        return this.gearCount;
    }
    public int getModelNo(){
        return this.modelNo;
    }
    public double getWeight(){
        return this.weight;
    }
    public String getBrand(){
        return this.brand;
    }
    
    //Sets
    public void setGearCount(int gc){
        this.gearCount = gc;
    }
    public void setModelNo(int mn){
        this.modelNo = mn;
    }
    public void setWeight(double wt){
        this.weight = wt;
    }
    public void setBrand(String bd){
        this.brand = bd;
    }
    
    //Overrides:
    
    //Polymorphic functions here change behavior depending 
    //on objects implementation. For example, this displayAll function
    //displays all data relevant to this object.
    //BicycleAccessories class has an alternative implementation of this function
    //which displays all data relevant to that object.
    @Override
    public String displayAll(){
        String formattedReturn = String.format("ID: " 
                + this.getId() 
                + " | Price: €" + this.getPrice() 
                + " | Colour: " + this.getColour() 
                + " | Product name: " + this.getProductName()
                + " | Gear count: " + this.getGearCount()
                + " | Model number: " + this.getModelNo()
                + " | Weight: " + this.getWeight() + "kg"
                + " | Brand: " + this.getBrand());
        return formattedReturn;
    }
    @Override
    public String toString(){
        return ("ID: " + super.id + " " + this.displayAll());
    }
}
