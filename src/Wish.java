import java.time.LocalDate;

public class Wish {
    int wish_ID;
    int wish_Level;
    String wish_Title;
    String wish_Description;
    LocalDate wish_Date;
    State state;

    public Wish(int wish_ID, int wish_Level, String wish_Title, String wish_Description, LocalDate wish_Date, State state) {
        this.wish_ID = wish_ID;
        this.wish_Level = wish_Level;
        this.wish_Title = wish_Title;
        this.wish_Description = wish_Description;
        this.wish_Date = wish_Date;
        this.state = state;
    }


    public void printWish() {
        System.out.println("Wish ID: " + wish_ID +
                " | Title: " + wish_Title +
                " | Description: " + wish_Description +
                " | Date: " + (wish_Date != null ? wish_Date.toString() : "N/A") +
                " | State: " + state +
                " | Required Level: " + wish_Level);
    }
}
