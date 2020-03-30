package ca2;
/**
 *
 * @author N00181859
 */
public abstract class Product implements Display{
    protected int id;
    private double price;
    private String colour, productName;
    
    //Instance variable used for representing one to many relationship
    private int storeId;
    
    //Super constructor
    public Product(int id, double pr, String co, String pn, int sid){
        this.id = id;
        this.price = pr;
        this.colour = co;
        this.productName = pn;
        this.storeId = sid;
    }
    
    //Gets
    @Override
    public int getId(){
        return this.id;
    }
    @Override
    public double getPrice(){
        return this.price;
    }
    @Override
    public String getColour(){
        return this.colour;
    }
    @Override
    public String getProductName(){
        return this.productName;
    }
    
    //Sets
    @Override
    public void setId(int id){
        this.id = id;
    }
    @Override
    public void setPrice(int pr){
        this.price = pr;
    }
    @Override
    public void setColour(String cr){
        this.colour = cr;
    }
    @Override
    public void setProductName(String pn){
        this.productName = pn;
    }
}
