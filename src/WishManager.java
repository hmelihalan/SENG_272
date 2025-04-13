import java.util.HashMap;

public class WishManager {
    HashMap<Integer, Wish> wishList = new HashMap<>();

    void listAllWishes(){
        for(Wish w : wishList.values()) {
            w.printWish();
        }
    }
    void addWish(Wish w) {
        wishList.put(w.wish_ID,w);
    }
    void wishChecked(int wish_ID,boolean checked,int wish_Level) {
        Wish w = wishList.get(wish_ID);
        w.wish_checked = checked;
        w.wish_Level = wish_Level;

    }
}

