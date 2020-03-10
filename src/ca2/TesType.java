package ca2;
/**
 *
 * @author N00181859
 */
import java.util.ArrayList;
import java.util.List;
//Class is soley for testing generics and does not serve a purpose in program
public class TesType<T, N>{
    T myObj;
    N myObj2;
    TesType(T myObj, N myObj2){
        this.myObj = myObj;
        this.myObj2 = myObj2;
    }
    public List<Object> func(){
        System.out.println(myObj + " " + myObj2);
        List<Object> oArray = new ArrayList<>();
        oArray.add(myObj);
        oArray.add(myObj2);
        return oArray;
    }
}
