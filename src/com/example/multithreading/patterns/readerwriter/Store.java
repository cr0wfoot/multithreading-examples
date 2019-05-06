package patterns.readerwriter;

public class Store {
    
    private static String message = "Message number 0";
    
    public static void changeMessage(String message) {
        Store.message = message;
    }
    
    public static String getMessage() {
        return message;
    }
    
}
