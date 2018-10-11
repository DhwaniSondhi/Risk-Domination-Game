package utility;

/**
 * The listener interface for receiving phase change events.
 * The class that is interested in processing an phase change event
 * implements this interface.
 */
public interface GameStateChangeListener {

    /**
     * Invoked when an game map is loaded
     */
    void onMapLoaded();

    /**
     * Invoked when an startup is completed is loaded
     */
    void onStartUpCompleted();

    /**
     * Invoked when reinforcement is done
     */
    void onReinforcementCompleted();

    /**
     * Invoked when attack is done
     */
    void onAttackCompleted();

    /**
     * Invoked when fortification is done
     */
    void onFortificationCompleted();
}
