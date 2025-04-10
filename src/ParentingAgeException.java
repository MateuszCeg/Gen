public class ParentingAgeException extends Exception{
    public ParentingAgeException(Person per){
        super("Rodzic " + per.getFirstName() + " " + per.getLastName() + " jest za młoda.");
    }
}
