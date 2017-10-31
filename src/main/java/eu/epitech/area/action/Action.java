package eu.epitech.area.action;

public abstract class Action {
    protected String name;
    protected String[] params;
    abstract boolean check();

    public Action(String name, String[] params) {
        this.name = name;
        this.params = params;
    }
}
