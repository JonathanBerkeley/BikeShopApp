package ca2;
/**
 *
 * @author N00181859
 */
import java.util.ArrayList;
import java.util.List;
public class Store {
    private int storeID;
    private String storeName, storeAddress;
    private List<Product> productList = new ArrayList<>();
    
    //Constructors
    public Store(int id, String sn, String sa, List<Product> sl){
        this.storeID = id;
        this.storeName = sn;
        this.storeAddress = sa;
        this.productList = sl;
    }
    public Store(String sn, String sa, List<Product> sl){
        this.storeName = sn;
        this.storeAddress = sa;
        this.productList = sl;
    }
    public Store(String sn, String sa){
        this.storeName = sn;
        this.storeAddress = sa;
    }
    
    //Functions
    public boolean addProduct(Product pr){
        int initialValue = this.productList.size();
        this.productList.add(pr);
        int postValue = this.productList.size();
        return postValue > initialValue;
    }
    public boolean removeProduct(int id){
        int initialValue2 = this.productList.size();
        this.productList.remove(id);
        int postValue2 = this.productList.size();
        return initialValue2 > postValue2;
    }
    public boolean removeProduct(Product pr){
        int initialValue3 = this.productList.size();
        this.productList.remove(pr);
        int postValue3 = this.productList.size();
        return initialValue3 > postValue3;
    }
    public Product getProduct(int id){
        return this.productList.get(id);
    }
    public void setProduct(int id, Product pr){
        this.productList.set(id, pr);
    }
    
    //Gets
    public int getStoreID(){
        return this.storeID;
    }
    public String getStoreName(){
        return this.storeName;
    }
    public String getStoreAddress(){
        return this.storeAddress;
    }
    public List<Product> getProductList(){
        return this.productList;
    }
    
    //Sets
    public void setStoreID(int id){
        this.storeID = id;
    }
    public void setStoreName(String sn){
        this.storeName = sn;
    }
    public void setStoreAddress(String sa){
        this.storeAddress = sa;
    }
    public void setProductList(List<Product> sl){
        this.productList = sl;
    }
}
