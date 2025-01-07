package ku.shop;

public class OutOfStockException extends Exception {
    public OutOfStockException() {}
    public OutOfStockException(String reason) { super(reason); }
}


