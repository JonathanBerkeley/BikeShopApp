package ca2;
/**
 *
 * @author N00181859
 */
interface Display {
    //Must be implemented by subclasses of implementing superclass:
    public String displayAll();
    
    //Gets
    public int getId();
    public double getPrice();
    public String getColour();
    public String getProductName();
    
    //Sets
    public void setId(int id);
    public void setPrice(int pr);
    public void setColour(String cr);
    public void setProductName(String pn);
}
