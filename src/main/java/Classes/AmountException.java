package Classes;

public class AmountException extends Exception {
    public AmountException() {
        super("Количество товара не может быть меньше или равным нулю, или быть больше 100. Количество изменено на 1");
    }
}