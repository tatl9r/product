package ru.netology.productmanager;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    Product book1 = new Book("William Blake", "The Little Black Boy", 154, 425);
    Product book2 = new Book("Gabriel Garcia Marquez", "100 Years of Solitude", 1212, 750);
    Product smartphone1 = new Smartphone("Xiaomi", "Redmi Note 8 2021", 54678, 13999);
    Product smartphone2 = new Smartphone("Apple", "iPhone 13 Pro Max", 876589, 179990);
    CartRepository repo = new CartRepository();
    ProductManager manager = new ProductManager(repo);

    @Test
    void addProducts() {
        manager.add(book1);
        manager.add(smartphone1);
        Product[] actual = repo.findAll();
        Product[] expected = {book1, smartphone1};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void searchByOneMatch() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);
        Product[] actual = manager.searchBy("Note");
        Product[] expected = {smartphone1};
        Assertions.assertArrayEquals(actual, expected);

    }

    @Test
    void searchByNoMatch() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);
        Product[] actual = manager.searchBy("frost");
        Product[] expected = {};
        Assertions.assertArrayEquals(actual, expected);

    }

    @Test
    void searchWithoutProducts() {
        Product[] actual = manager.searchBy("Redmi");
        Product[] expected = {};
        Assertions.assertArrayEquals(actual, expected);

    }

    @Test
    void saveTwoProducts() {
        repo.save(book1);
        repo.save(smartphone1);
        Product[] actual = repo.findAll();
        Product[] expected = {book1, smartphone1};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void removeById() {
        repo.save(book1);
        repo.save(smartphone1);
        repo.save(book2);
        repo.save(smartphone2);
        repo.removeById(1212);
        Product[] actual = repo.findAll();
        Product[] expected = {book1, smartphone1, smartphone2};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void removeByIdLastProduct() {
        repo.save(smartphone1);
        repo.removeById(54678);
        Product[] actual = repo.findAll();
        Product[] expected = {};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void removeByIdTwoProducts() {
        repo.save(book1);
        repo.save(smartphone1);
        repo.save(book2);
        repo.save(smartphone2);
        repo.removeById(154);
        repo.removeById(1212);
        Product[] actual = repo.findAll();
        Product[] expected = {smartphone1, smartphone2};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void searchById() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);
        Product[] actual = repo.findById(876589);
        Product[] expected = {smartphone2};
        Assertions.assertArrayEquals(actual, expected);


    }


}