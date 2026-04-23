/**
 * Represent a player character arriving at the whizard
 */
public class PlayerCharacter implements Comparable<PlayerCharacter> {
    private int priority;
    private int arrivalTime;
    private int requestTime;
    private int departureTime;
    private int characterNumber;

    /**
     * instantiating the playerCharacter class
     * 
     * @param arrivalTime     the arrival time of the character
     * @param requestTime     the amount of time to learn a skill from the whizard
     * @param characterNumber the id number of the character
     */
    public PlayerCharacter(int arrivalTime, int requestTime, int characterNumber) {
        this.arrivalTime = arrivalTime;
        this.requestTime = requestTime;
        this.characterNumber = characterNumber;
    }

    public int getCharacterNumber() {
        return characterNumber;
    }

    public void setCharacterNumber(int characterNumber) {
        this.characterNumber = characterNumber;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public int getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(int requestTime) {
        this.requestTime = requestTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public int compareTo(PlayerCharacter c) {
        return Integer.compare(this.getPriority(), c.getPriority());
    }

    public String toString() {
        return "Character " + characterNumber +
                " [arrival=" + arrivalTime +
                ", request=" + requestTime +
                ", priority=" + priority + "]";
    }
}