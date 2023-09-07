import java.util.ArrayList;

public class Block {
    private char blockCharacter;
    private byte numberOfFloors;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private Dormitory dormitory;

    public void setBlockCharacter(char blockCharacter) {
        this.blockCharacter = blockCharacter;
    }

    public char getBlockCharacter() {
        return blockCharacter;
    }

    public void setNumberOfFloors(byte numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public byte getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Block(char blockCharacter, byte numberOfFloors, Dormitory dormitory) {
        setBlockCharacter(blockCharacter);
        setNumberOfFloors(numberOfFloors);
        setDormitory(dormitory);
    }
}
