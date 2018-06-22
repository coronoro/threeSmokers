package tobacco;

import java.util.Arrays;
import java.util.HashSet;

public class Smoker extends Agent{

    private Utensils utensil;

    public Smoker(Utensils utensil){
        super();
        this.utensil = utensil;
    }

    private void smoke() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(" a,  8a\n" +
                " `8, `8)                            ,adPPRg,\n" +
                "  8)  ]8                        ,ad888888888b\n" +
                " ,8' ,8'                    ,gPPR888888888888\n" +
                ",8' ,8'                 ,ad8\"\"   `Y888888888P\n" +
                "8)  8)              ,ad8\"\"        (8888888\"\"\n" +
                "8,  8,          ,ad8\"\"            d888\"\"\n" +
                "`8, `8,     ,ad8\"\"            ,ad8\"\"\n" +
                " `8, `\" ,ad8\"\"            ,ad8\"\"\n" +
                "    ,gPPR8b           ,ad8\"\"\n" +
                "   dP:::::Yb      ,ad8\"\"\n" +
                "   8):::::(8  ,ad8\"\"\n" +
                "   Yb:;;;:d888\"\"  Smoker\n" +
                "    \"8ggg8P\"      "+this.utensil.name());
    }

    @Override
    public boolean check(Utensils[] tableUtensils){
        HashSet<Utensils> utensilSet = new HashSet<>();
        utensilSet.add(this.utensil);
        for (Utensils u:
             tableUtensils) {
            utensilSet.add(u);
        }
        boolean result = utensilSet.size() == 3;
        return result;
    }

    public void run() {
        while (true){
            try {
                Pub.table.acquire();
                try {
                    //System.out.println("Smoker "+ utensil.name() + " checks the table");
                    if (check(Pub.table.availableUtensils)){
                        //take
                        Arrays.fill(Pub.table.availableUtensils, null);
                        smoke();
                    }
                }finally {
                    Pub.table.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
