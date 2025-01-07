package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) throws OutOfStockException {
        Product prod = catalog.getProduct(name);
        order.addItem(prod, quantity);

    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @When("I buy {string} with quantity more than stock, {int}")
    public void i_buy_with_quantity_more_then_stock(String name, int quantity) throws OutOfStockException {
        Product prod = catalog.getProduct(name);
        assertThrows(OutOfStockException.class,
                () -> order.addItem(prod, quantity));
    }

    @Then("stock of {int} {string} should exists")
    public void stock_of_should_exists(double quantity, String name) {
        Product prod = catalog.getProduct(name);
        assertEquals(quantity, prod.getStock());
    }



}

