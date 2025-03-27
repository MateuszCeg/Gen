import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Person implements Comparable<Person>{
    private String firstName, lastName;
    private LocalDate birthDay;
    private Set<Person> children = new HashSet<>();

    public Person(String firstName, String lastName, LocalDate birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
    }
    public boolean adopt(Person child){
        return children.add(child);
    }

    public Person getYoungestChild(){
        if (children.isEmpty()) return null;
        Person youngest = null;
        for (Person x: children){
                if (youngest==null)
                    youngest = x;
                if(x.compareTo(youngest)>0) youngest = x;
        }
        return youngest;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName  + ", urodzony: " + birthDay;
    }

    @Override
    public int compareTo(Person o) {

        return this.birthDay.compareTo(o.birthDay);
    }
}
