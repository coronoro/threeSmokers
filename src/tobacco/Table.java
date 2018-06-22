package tobacco;

import java.util.concurrent.Semaphore;

public class Table extends Semaphore {

    Utensils [] availableUtensils = new Utensils[2];

    public Table() {
        super(1);

    }
}