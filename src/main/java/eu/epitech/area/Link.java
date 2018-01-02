package eu.epitech.area;

import eu.epitech.area.action.Action;
import eu.epitech.area.reaction.Reaction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "link")
public class Link implements Serializable {
    @Id
    private Long id;

    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", foreignKey = @ForeignKey(name = "ACTION_ID_FK"))
    private Action action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reaction_id", foreignKey = @ForeignKey(name = "REACTION_ID_FK"))
    private Reaction reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "AUTHOR_ID_FK"))
    private User author;

    public Link() {

    }

    public Link(Action action, Reaction reaction, User author) {
        this.action = action;
        this.reaction = reaction;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
