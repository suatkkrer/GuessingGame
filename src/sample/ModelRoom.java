package sample;

public class ModelRoom {
    int roomNumb;
    int isEmpty;

    public ModelRoom(int roomNumb, int isEmpty) {
        this.roomNumb = roomNumb;
        this.isEmpty = isEmpty;

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
