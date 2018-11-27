/**
 * Turboable is an interface describing all objects that have the "Turbo"-function which is a way to increase a vehicle's
 * engine power.
 */
public interface Turboable {
    /**
     * This method turns the turbo on, meaning that the vehicle will gain increased effect when gasing.
     */
    void setTurboOn();

    /**
     * This method turns off the turbo, meaning it will gain speed as if not enhanced when gasing.
     */
    void setTurboOff();
}
