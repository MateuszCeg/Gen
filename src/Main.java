import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]){
        List<Person> personList = new ArrayList<>();
        Person kacper = new Person( "Kacper","Kowalski", LocalDate.of(2003,5,12), null);
        Person tom = new Person( "Tom","Candies", LocalDate.of(1998,10,5), null);
        Person agata = new Person( "Agata","Kowal", LocalDate.of(2006,11,25), null);
        Person ewa = Person.fromCsvLine("Ewa Kowalska,03.11.1901,05.03.1990,,");
        //personList.add(kacper); personList.add(tom); personList.add(agata);



        personList.add(ewa);
        for (Person x: personList){
            System.out.println(x);
        }
        try {
            personList = Person.fromCsv("family.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Person x: personList){
            System.out.println(x);
        }

        //System.out.println("Adopt: "+agata.adopt(tom));
        //System.out.println("Adopt: "+agata.adopt(kacper));

        //System.out.println(agata.getYoungestChild());
    }
}
