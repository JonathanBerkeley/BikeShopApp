package ca2;

/**
 *
 * @author N00181859
 */
import java.util.ArrayList;
import java.util.List;
//Class is soley for experimenting with generics and does not serve a purpose in program
//I left it in because I think it's interesting

public class TesType<T, N> {

    T myObj;
    N myObj2;

    TesType(T myObj, N myObj2) {
        this.myObj = myObj;
        this.myObj2 = myObj2;
    }

    public List<Object> func() {
        List<Object> oArray = new ArrayList<>();
        oArray.add(myObj);
        oArray.add(myObj2);
        return oArray;
    }

    /* Example use by other class:
    TesType<Boolean, Double> obj = new TesType<>((Rangedrandom.genInt(0,3,ROBJ)==2), (Rangedrandom.genInt(0,3,ROBJ)==2) ? 2.5 : 9.10);
    List<Object> myList = obj.func();
    System.out.println(myList);
     */
}
