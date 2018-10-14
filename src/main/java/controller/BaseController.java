package controller;

import entity.GameMap;
import utility.GameStateChangeListener;

/**
 * This is the abstract class for controller. All the controllers should extend this class.
 *
 * @param <T> this is the type of View
 */
public abstract class BaseController<T> {

    public T view;
    public GameMap model;
    protected GameStateChangeListener stateChangeListener;

    /**
     * This is the constructor for the Controller
     *
     * @param view View associated with the Controller
     */
    public BaseController(T view) {
        this.view = view;
        this.model = GameMap.getInstance();
    }

    /**
     * Getter method for the view
     *
     * @return Returns the associated View
     */
    public T getView() {
        return view;
    }

    /**
     * setter method for state change listener
     */
    public void setStateChangeListener(GameStateChangeListener stateChangeListener) {
        this.stateChangeListener = stateChangeListener;
    }
}
