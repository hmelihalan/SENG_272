import java.util.HashMap;

public class WishManager {
    HashMap<Integer, Wish> wishList = new HashMap<>();

    public void listAllWishes() {
        System.out.println("---- All Wishes ----");
        for (Wish w : wishList.values()) {
            w.printWish();
        }
    }


    public void addWish(Wish w) {
        if (wishList.containsKey(w.wish_ID)) { //If wish has same id print error
            System.out.println("Error: Wish ID " + w.wish_ID + " already exists. Not added.");
            return;
        }
        wishList.put(w.wish_ID, w);
        System.out.println("Wish ID " + w.wish_ID + " added.");
    }


    public void wishChecked(int wish_ID, boolean approved, int wish_Level) {
        Wish w = wishList.get(wish_ID);
        if (w != null) {
            if (approved) {
                w.state = State.APPROVED;
                w.wish_Level = wish_Level;
            } else {
                w.state = State.REJECTED;
            }
            System.out.print("Wish " + wish_ID + " state: " + w.state);
            if (approved)
                System.out.print(" | Required level for wish: " + wish_Level);
            System.out.println();
        } else {
            System.out.println("Error: Wish " + wish_ID + " not found.");
        }
    }

    public java.util.List<String> exportWishes() {
        java.util.List<String> lines = new java.util.ArrayList<>();
        for (Wish w : wishList.values()) {
            String line = "W" + w.wish_ID + "," +
                    "\"" + w.wish_Title + "\"," +
                    "\"" + w.wish_Description + "\"," +
                    (w.wish_Date != null ? w.wish_Date.toString() : "") + "," +
                    (w.state) + "," +
                    w.wish_Level;
            lines.add(line);
        }
        return lines;
    }

}
