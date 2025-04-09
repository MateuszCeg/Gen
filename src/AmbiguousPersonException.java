public class AmbiguousPersonException extends Exception{
    public AmbiguousPersonException(Person a) {
        super("Imię " + a.getFirstName() +  " i nazwisko " + a.getLastName() + " powtarza się w pliku.");
    }
}
