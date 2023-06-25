package Classes;

public class ProductException extends Exception {
    public ProductException() {
        super("Передан несуществующий товар");
    }
}