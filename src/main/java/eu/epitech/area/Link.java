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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", foreignKey = @ForeignKey(name = "ACTION_ID_FK"))
    private Action action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reaction_id", foreignKey = @ForeignKey(name = "REACTION_ID_FK"))
    private Reaction reaction;

    public Link() {

    }

    public Link(Action action, Reaction reaction) {
        this.action = action;
        this.reaction = reaction;
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
}
