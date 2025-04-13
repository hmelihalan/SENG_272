import java.time.LocalDate;

public class Wish {
    int wish_ID;
    int wish_Level;
    String wish_Title;
    String wish_Description;
    boolean wish_checked;
    LocalDate wish_Date;

    public Wish(int wish_ID,int wish_Level,String wish_Title,String wish_Description,LocalDate wish_Date,boolean wish_checked) {
        this.wish_ID = wish_ID;
        this.wish_Level = wish_Level;
        this.wish_Title = wish_Title;
        this.wish_Description = wish_Description;
        this.wish_Date = wish_Date;
        this.wish_checked = wish_checked;
    }

    public void printWish(){
        System.out.println("Wish ID: "+wish_ID);
    }

}
