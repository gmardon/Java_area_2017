package eu.epitech.area.link;

import eu.epitech.area.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkProcessingThread extends Thread {
    @Autowired
    LinkService linkService;

    @Autowired
    ApplicationContext applicationContext;

    public LinkProcessingThread() {
        this.start();
    }

    @Override
    public void run() {
        List<Link> links = new ArrayList<Link>();
        while (this.isAlive()) {
            if (this.linkService == null)
                continue;
            if (links.size() == 0)
                links = linkService.getAll();
            else {
                List<Link> newLinks = linkService.getAll();
                for (int i = 0; i < newLinks.size(); i++) {
                    Link link = newLinks.get(i);
                    if (links.stream().anyMatch((l) -> l.getId() == link.getId())) {
                        newLinks.remove(newLinks.get(i));
                    }
                }
                links.addAll(newLinks);
            }
            System.out.println("ActionProcessingThread is running");
            for (Link link : links) {
                AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
                factory.autowireBean(link);
                factory.autowireBean(link.getAction());
                factory.autowireBean(link.getReaction());
                System.out.println("Processing link...");
                try {
                    link.apply();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
