public class NegativeLifespanException extends Exception{
    public NegativeLifespanException(Person a) {
        super(a.getFirstName()+" "+a.getLastName()+" \nBłąd daty. ");
    }
}
