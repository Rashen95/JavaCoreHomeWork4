package Classes;

public class CustomerException extends Exception {
    public CustomerException() {
        super("Передан несуществующий покупатель");
    }
}