import com.sun.deploy.util.SystemUtils;

/**
 * Created by lstrzalk on 27.04.16.
 */
public class Philosopher extends Thread{
    BinarySemaphore[] forks;
    int id;
    String name;
    public Philosopher(BinarySemaphore[] forks, int id, String name){
        this.forks=forks;
        this.id=id;
        this.name=name;
    }
    public void think(){
        System.out.println(id+" : "+name+" is thinking");
    }
    public void eat(){
        System.out.println(id+" : "+name+" is eating");
    }
    public void run(){
        int leftFork= (this.id == 0 ? 0 : this.id-1);
        int rightFork= (this.id == 0 ? 4 : this.id);
        //System.out.println(this.id+" : " + leftFork+" : "+rightFork);
        while (true) {
            think();
            try {
                forks[leftFork].vb();
                forks[rightFork].vb();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eat();
            forks[rightFork].pb();
            forks[leftFork].pb();
        }
    }

}
