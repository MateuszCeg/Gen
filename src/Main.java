import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]){

        List<Person> personList = new ArrayList<>();
        Person kacper = new Person( "Kacper","Kowalski", LocalDate.of(2003,5,12));
        Person tom = new Person( "Tom","Candies", LocalDate.of(1998,10,5));
        Person agata = new Person( "Agata","Kowal", LocalDate.of(2006,11,25));
        personList.add(kacper); personList.add(tom); personList.add(agata);
        for (Person x: personList){
            System.out.println(x);
        }

        System.out.println("Adopt: "+agata.adopt(tom));
        System.out.println("Adopt: "+agata.adopt(kacper));

        System.out.println(agata.getYoungestChild());
    }
}
