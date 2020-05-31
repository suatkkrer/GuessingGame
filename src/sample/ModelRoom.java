package sample;

public class ModelRoom {
    int roomNumb;
    int isEmpty;
    String user1Name = null;
    String user2Name = null;

    public ModelRoom(int roomNumb, int isEmpty,String user1Name,String user2Name) {
        this.roomNumb = roomNumb;
        this.isEmpty = isEmpty;
        this.user1Name = user1Name;
        this.user2Name = user2Name;

    }

    public String getUser1Name() {
        return user1Name;
    }

    public void setUser1Name(String user1Name) {
        this.user1Name = user1Name;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public void setUser2Name(String user2Name) {
        this.user2Name = user2Name;
    }

    public int getRoomNumb() {
        return roomNumb;
    }

    public void setRoomNumb(int roomNumb) {
        this.roomNumb = roomNumb;
    }

    public int getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(int isEmpty) {
        this.isEmpty = isEmpty;
    }
}
