import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Person implements Comparable<Person>{
    private String firstName, lastName;
    private LocalDate birthDay, deathDay;
    private Set<Person> children = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public static Person fromCsvLine(String str){
        String[] arr = str.split(",");
        String[] name = arr[0].split(" ");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthD = LocalDate.parse(arr[1], format);
        LocalDate deathD;
        if(arr[2] == "") {
            deathD = null;
        }
        else {
            deathD = LocalDate.parse(arr[2], format);
        }


        return new Person(name[0], name[1], birthD, deathD);
    }
    public static List<Person> fromCsv(String filePath) throws IOException {
        FileReader file = new FileReader(filePath);
        BufferedReader fopen = new BufferedReader(file);
        List<Person> personList = new ArrayList<>();
        String line = fopen.readLine();
        String[] parentName = new String[2];
        while(line!=null){
            line = fopen.readLine();

            if (line == null) break;

            Person osob = Person.fromCsvLine(line);
            String[] temp = line.split(",");
            
            if(temp.length >=5){
                if (!(temp[3] == "" || temp[3] == null)) {
                    parentName[0] = temp[3];
                    String[] name = parentName[0].split(" ");
                    for (Person x : personList) {
                        if (Objects.equals(x.getFirstName(), name[0]) && Objects.equals(x.getLastName(), name[1])) {
                            x.adopt(osob);
                        }
                    }
                }

                if (!(temp[4] == "" || temp[4] == null)) {
                    parentName[1] = temp[4];
                    String[] name = parentName[1].split(" ");
                    for (Person x : personList) {
                        if (Objects.equals(x.getFirstName(), name[0]) && Objects.equals(x.getLastName(), name[1])) {
                            x.adopt(osob);
                        }
                    }
                }
            }
            try {

                osob.sameLastFirstName(personList);
                osob.checkLifeSpan();
                personList.add(osob);
            } catch (NegativeLifespanException | AmbiguousPersonException e) {
                System.err.println("Error: "+e);
            }
        }
        return personList;
    }

    private void sameLastFirstName(List<Person> personList) throws AmbiguousPersonException{
        for (Person x: personList){
        if(Objects.equals(x.getLastName(), this.getLastName()) && Objects.equals(x.getFirstName(), this.getFirstName())) {
            throw new AmbiguousPersonException(this);
        }
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, LocalDate birthDay, LocalDate deathDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.deathDay = deathDay;
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

    public List<Person> getChildren(){
        List<Person> childs = new ArrayList<>();
        childs.addAll(children);
        Collections.sort(childs);

        return childs;
    }
    public void parentAgeCheck(Person b) throws ParentingAgeException{
        if((this.deathDay.getYear()-this.birthDay.getYear())<15 || (this.deathDay.isBefore(b.birthDay))){
            throw new ParentingAgeException(this);
        }
    }
    private void checkLifeSpan() throws NegativeLifespanException {
        if (this.deathDay==null) {
            //throw new NegativeLifespanException(this);
            return;
        }
        if ((this.birthDay.isAfter(this.deathDay))){
            throw new NegativeLifespanException(this);
        }
    }
    @Override
    public String toString() {
        String str;
        if(deathDay == null) {
            str = firstName + " " + lastName + ", urodzony: " + birthDay;
        }
        else{
            str = firstName + " " + lastName + ", urodzony: " + birthDay + ", zgon:" + deathDay;
        }
        if (this.children.size() != 0){
            str += " Dzieci: ";
            for (Person x : children){
                str += x.getLastName() + ", ";
            }
        }
        return str;
    }
    @Override
    public int compareTo(Person o) {

        return this.birthDay.compareTo(o.birthDay);
    }
}
