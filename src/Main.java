import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\thuph\\Downloads\\779names.txt");
        Scanner myReader = new Scanner(myFile);
        ArrayList arrayList = new ArrayList();
        while(myReader.hasNextLine()) {
            String data = myReader.nextLine();
            arrayList.add(data);
        }
        int size = arrayList.size();
        String[] names = new String[size];
        names = (String[]) arrayList.toArray(names);
        HashSimulator simulator = new HashSimulator();
        simulator.runHashSimulator(names, 77900);
    }
}