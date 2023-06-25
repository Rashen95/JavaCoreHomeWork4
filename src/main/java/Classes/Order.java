package Classes;

import java.util.Random;

public class Order {
    private Buyer buyer;
    private Product product;
    private short amountOfProducts;
    private int price;
    private int discont = 0;

    public Order(Buyer buyer, Product product, short amountOfProducts) {
        this.buyer = buyer;
        this.product = product;
        this.amountOfProducts = amountOfProducts;
        this.price = (product.getPrice() * amountOfProducts);
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public short getAmountOfProducts() {
        return amountOfProducts;
    }

    public void setAmountOfProducts(short amountOfProducts) {
        this.amountOfProducts = amountOfProducts;
    }

    /**
     * Сделать скидку (рандомно)
     */
    public void makeDiscount() {
        Random r = new Random();
        Discont[] disconts = Discont.values();
        this.discont = disconts[r.nextInt(0, disconts.length)].getCode();
        this.price = (product.getPrice() * amountOfProducts) - (product.getPrice() * amountOfProducts) * discont / 100;
    }

    @Override
    public String toString() {
        return String.format("""
                Покупатель: %s
                Продукт: %s
                Количество продукта: %d
                Скидка: %s
                Итоговая стоимость: %s
                """, buyer.getFirstName(), product.getNameProduct(), amountOfProducts, discont, price);
    }
}