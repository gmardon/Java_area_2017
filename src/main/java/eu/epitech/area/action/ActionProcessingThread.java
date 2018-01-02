package eu.epitech.area.action;

import eu.epitech.area.Link;
import eu.epitech.area.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionProcessingThread extends Thread {
    @Autowired
    LinkService linkService;

    @Override
    public void run() {
        List<Link> links = linkService.getAll();
        System.out.println(getName() + " is running");
        System.out.println(getName() + " is sleeping...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
