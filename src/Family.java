import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Family {

    Map<String, List<Person>> members;
    public void add(Person mem){
        List<Person> test = new ArrayList<Person>();
        test.add(mem);
        members.put(mem.getFirstName()+" "+mem.getLastName(),test);
    }
    public void add(List<Person> mem){
        for(Person a: mem){
        members.put(a.getFirstName()+" "+a.getLastName(),mem);
        }
    }
    public List<Person> get(String str){
        List<Person> test = members.get(str);
        test.sort(Person::compareTo);
        return test;
    }
}
