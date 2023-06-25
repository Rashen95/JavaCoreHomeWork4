/**
 * 1 часть
 * 1. Написать классы покупатель (ФИО, возраст, телефон), товар (название, цена) и
 * заказ (объект покупатель, объект товар, целочисленное количество).
 * 2. Создать массив покупателей (инициализировать 2 элемента), массив товаров
 * (инициализировать 5 элементов) и массив заказов (пустой на 5 элементов).
 * 3. Создать статический метод «совершить покупку» со строковыми параметрами,
 * соответствующими полям объекта заказа. Метод должен вернуть объект заказа.
 * 4. Если в метод передан несуществующий покупатель – метод должен выбросить
 * исключение CustomerException, если передан несуществующий товар, метод
 * должен выбросить исключение ProductException, если было передано отри-
 * цательное или слишком больше значение количества (например, 100), метод
 * должен выбросить исключение AmountException.
 * Вызвать метод совершения покупки несколько раз таким образом, чтобы запол-
 * нить массив покупок возвращаемыми значениями. Обработать исключения сле-
 * дующим образом (в заданном порядке):
 * – если был передан неверный товар – вывести в консоль сообщение об ошиб-
 * ке, не совершать данную покупку;
 * – если было передано неверное количество – купить товар в количестве 1;
 * – если был передан неверный пользователь – завершить работу приложения
 * с исключением.
 * Вывести в консоль итоговое количество совершённых покупок после выполне-
 * ния основного кода приложения.
 * 2 часть
 * Добавить перечисление с гендерами. В класс покупателя добавить свойство «пол»
 * со значением созданного перечисления. Добавить геттеры, сеттеры.
 * Добавить в приложение Магазин учет цены товара - в Заказ добавить поле стоимость.
 * Добавить перечисление с размерами скидок - 0, 5, 10, 15, 20%.
 * Написать метод, при вызове которого на переданный тип товара незначается рандомная скидка из перечисления (меняем значение поля price)
 */

package Main;

import Classes.*;

public class Main {
    static Product[] products;
    static Buyer[] buyers;

    public static void main(String[] args) {
        // Инициализация покупателей
        buyers = new Buyer[]{
                new Buyer("Artem", "Privalov", (short) 28, "+79381134495", Gender.male),
                new Buyer("Alex", "Sergeev", (short) 35, "+79381134455", Gender.female)
        };

        // Инициализация продуктов
        products = new Product[]{
                new Product("Milk", 70),
                new Product("Meat", 300),
                new Product("IceCream", 55),
                new Product("Orange", 150),
                new Product("Cola", 110)
        };

        // Массивы с передаваемыми в функцию аргументами
        String[] productNames = {"Milk", "Meat", "Clock", "Orange", "Cola"};
        String[] phoneNumbers = {
                "+79381134495",
                "+79381134495",
                "+79381134455",
                "+79381134459",
                "+79381134455"
        };
        short[] amountProduct = {
                -5, 25, 10, 10, 50
        };

        // Инициализация пустого массива заказов на 5 элементов
        Order[] orders = new Order[5];

        // Наполнение массива заказов (с учетом скидки)
        for (int i = 0; i < orders.length; i++) {
            try {
                orders[i] = makeAPurchase(phoneNumbers[i], productNames[i], amountProduct[i]);
                orders[i].makeDiscount();
            } catch (ProductException | CustomerException e) {
                System.out.println(e.getMessage());
            } catch (AmountException e) {
                System.out.println(e.getMessage());
                try {
                    orders[i] = makeAPurchase(phoneNumbers[i], productNames[i], (short) 1);
                    orders[i].makeDiscount();
                } catch (ProductException | AmountException | CustomerException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        // Вывод заказов
        int count = 0;
        for (Order o : orders) {
            if (o != null) {
                count++;
                System.out.println(o);
            }
        }

        // Вывод количества совершенных покупок
        System.out.println("Количество удачно совершенных покупок " + count);
    }

    /**
     * Совершение покупки
     *
     * @param buyersPhoneNumber        телефон покупателя
     * @param productName              имя продукта
     * @param quantityOfGoodsPurchased количество приобретаемого продукта
     * @return экземпляр класса Order
     * @throws CustomerException введенного покупателя нет
     * @throws ProductException  введенного продукта нет
     * @throws AmountException   количество товара неверное
     */
    public static Order makeAPurchase(String buyersPhoneNumber, String productName, short quantityOfGoodsPurchased) throws CustomerException, ProductException, AmountException {
        Buyer buyer = null;
        Product product = null;
        for (Buyer b : buyers) {
            if (buyersPhoneNumber.equals(b.getPhoneNumber())) {
                buyer = b;
            }
        }
        if (buyer == null) {
            throw new CustomerException(String.format("Покупатель с номером %s не найден. Заказ отменен.\n", buyersPhoneNumber));
        }
        for (Product p : products) {
            if (productName.equals(p.getNameProduct())) {
                product = p;
            }
        }
        if (product == null) {
            throw new ProductException(String.format("Товара %s нет в данном магазине. Заказ отменен.\n", productName));
        }
        if (quantityOfGoodsPurchased <= 0 || quantityOfGoodsPurchased > 100) {
            throw new AmountException(String.format("Вы не можете заказать %s в количестве %d. " +
                    "Количество изменено на 1.\n", productName, quantityOfGoodsPurchased));
        }
        return new Order(buyer, product, quantityOfGoodsPurchased);
    }
}