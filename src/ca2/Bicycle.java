package ca2;
/**
 *
 * @author N00181859
 */
public class Bicycle extends Product{
    private int gearCount, modelNo;
    private double weight;
    private String brand;
    
    //Constructor
    public Bicycle(int id, double pr, String co, String pn, int gc, int mn, double wt, String br){
        super(id, pr, co, pn);
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
    @Override
    public String displayAll(){
        String formattedReturn = String.format("ID: " 
                + this.getId() 
                + " | Price: " + this.getPrice() 
                + " | Colour: " + this.getColour() 
                + " | Product name: " + this.getProductName()
                + " | Gear count: " + this.getGearCount()
                + " | Model number: " + this.getModelNo()
                + " | Weight: " + this.getWeight() +"kg"
                + " | Brand: " + this.getBrand());
        return formattedReturn;
    }
    @Override
    public String toString(){
        return ("Bike ID: " + super.id);
    }
}
