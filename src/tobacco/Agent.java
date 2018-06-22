package tobacco;

public abstract class Agent implements Runnable{

    abstract boolean check(Utensils[] tableUtensils);

}
