package ca2;
/**
 *
 * @author N00181859
 */
public class Meta{
    public static boolean debug = false; //Controls whether println debug statements appear throughout the program
    
    public static void toggleDebug(){
        debug = !debug; //Flips the booleans current value
    }
    
    //No real reason for get/set considering it's a class variable
    public static boolean getDebugValue(){
        return debug;
    } 
    public static void setDebugValue(boolean dbg){
        debug = dbg;
    }
}
