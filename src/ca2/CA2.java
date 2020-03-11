package ca2;
/**
 *
 * @author N00181859
 */
import utils.DatabasePopulator;
public class CA2 {
    public static void main(String[] args) {
        DatabasePopulator dbPop = new DatabasePopulator();
        dbPop.populate(10,10);
    }
}
