package eu.epitech.area.action;

import org.springframework.stereotype.Service;

@Service
public class ActionProcessingThread extends Thread {

    @Override
    public void run() {

        System.out.println(getName() + " is running");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " is running");
    }
}
