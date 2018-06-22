package tobacco;

public class Pub {

    public static Table table = new Table();

    public static void main(String[] args) {
        Waitress waitress = new Waitress();
        Thread thread = new Thread(waitress);
        thread.start();
        System.out.println("Smokers enter the Pub");
        for (Utensils  utensil: Utensils.values()) {
            Smoker smoker = new Smoker(utensil);
            Thread smokerThread = new Thread(smoker);
            smokerThread.start();
        }
    }
}
