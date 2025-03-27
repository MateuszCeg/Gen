import java.util.Map;

public class Family {
    Map<String, Person> members;
    public void add(Person mem){
        members.put(mem.getFirstName()+" "+mem.getLastName(),mem);
    }
    public Person get(String str){
        return members.get(str);
    }
}
