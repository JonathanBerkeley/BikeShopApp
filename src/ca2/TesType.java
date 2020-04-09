package ca2;
/**
 *
 * @author N00181859
 */
import java.util.ArrayList;
import java.util.List;
//Class is soley for experimenting with generics and does not serve a purpose in program
public class TesType<T, N, Y>{
    T myObj;
    N myObj2;
    Y myObj3;
    TesType(T myObj, N myObj2, Y myObj3){
        this.myObj = myObj;
        this.myObj2 = myObj2;
        this.myObj3 = myObj3;
    }
    
    public List<Object> func(){
        List<Object> oArray = new ArrayList<>();
        oArray.add(myObj);
        oArray.add(myObj2);
        oArray.add(myObj3);
        return oArray;
    }
}
