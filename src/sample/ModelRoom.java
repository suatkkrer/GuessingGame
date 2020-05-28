package sample;

public class ModelRoom {
    int roomNumb;
    int isEmpty;
    int user_fk;
    int user_fk2;

    public ModelRoom(int roomNumb, int isEmpty,int user_fk,int user_fk2) {
        this.roomNumb = roomNumb;
        this.isEmpty = isEmpty;
        this.user_fk = user_fk;
        this.user_fk = user_fk2;

    }

    public int getUser_fk() {
        return user_fk;
    }

    public void setUser_fk(int user_fk) {
        this.user_fk = user_fk;
    }

    public int getUser_fk2() {
        return user_fk2;
    }

    public void setUser_fk2(int user_fk2) {
        this.user_fk2 = user_fk2;
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
