package tobacco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Waitress extends Agent {

    private Utensils[] serve (){
        Utensils[] result  = new Utensils[2];
        List<Utensils> utensils = new ArrayList<>(Arrays.asList(Utensils.values()));
        for (int i = 0; i < 2 ; i++){
            int rand = ThreadLocalRandom.current().nextInt(0, utensils.size());
            result[i] = utensils.get(rand);
            utensils.remove(rand);
        }
        return result;
    }

    @Override
    public void run() {
        while (true){
            try {
                Pub.table.acquire();
                try {
                    if (check(Pub.table.availableUtensils)){
                        Pub.table.availableUtensils = serve();
                        System.out.println("Waitress served:" + Pub.table.availableUtensils[0] + " and " + Pub.table.availableUtensils[1]);
                    }
                }finally {
                    Pub.table.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    boolean check(Utensils[] tableUtensils) {
        return tableUtensils[0] == null;
    }
}
