package ca2;
/**
 *
 * @author N00181859
 */
public class BicycleAccessories extends Product{
    private String type;
    private boolean inStock;
    
    
    //Constructor
    public BicycleAccessories(int id, double pr, String co, String pn, String ty, boolean is, int strId){
        super(id, pr, co, pn, strId);
        this.type = ty;
        this.inStock = is;
    }
    
    //Gets
    public String getType(){
        return this.type;
    }
    public Boolean getInStock(){
        return this.inStock;
    }
    
    //Sets
    public void setType(String tp){
        this.type = tp;
    }
    public void setInStock(boolean is){
        this.inStock = is;
    }
    
    //Overrides:
    @Override
    public String displayAll(){
        String formattedReturn = String.format("ID: " 
                + this.getId() 
                + " | Price: " + this.getPrice() 
                + " | Colour: " + this.getColour() 
                + " | Product name: " + this.getProductName()
                + " | Product type: " + this.getType()
                + " | In stock: " + (this.getInStock() ? "Yes" : "No"));
        return formattedReturn;
    }
    @Override
    public String toString(){
        return ("Bicycle accessories ID: " + super.id);
    }
}
