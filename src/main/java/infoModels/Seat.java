package infoModels;

/**
 * Created by Alex Astakhov on 16.10.2016.
 */
public class Seat {
    private int row;
    private int number;
    private boolean occupied;

    public Seat(int row, int number, boolean occupied) {
        this.row = row;
        this.number = number;
        this.occupied = occupied;
    }

    @Override
    public String toString(){
        String occup;
        if (occupied){
            occup = "занято";
        }
        else {
            occup = "свободно";
        }
        return "Ряд: " + row + ", место: " + number + " - " + occup;
    }
}
