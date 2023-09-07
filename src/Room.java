import java.util.ArrayList;

public class Room {
    private byte roomNumber;
    private byte roomCapacity;
    private ArrayList<Student> roomMembers = new ArrayList<Student>();
    private int roomRentCost;
    private Block block;
    private byte floorNumber;
    private Student roomManager;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(byte roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public int getRoomRentCost() {
        return roomRentCost;
    }

    public Block getBlock() {
        return block;
    }

    public byte getFloorNumber() {
        return floorNumber;
    }

    public void setRoomCapacity(byte roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public ArrayList<Student> getRoomMembers() {
        return roomMembers;
    }

    public void setRoomMembers(Student roomMember) {
        this.roomMembers.add(roomMember);
    }

    public void setRoomRentCost(int roomRentCost) {
        this.roomRentCost = roomRentCost;
    }

    public void setFloorNumber(byte floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setBlock(Block block) {
        block.addRoom(this);
        this.block = block;
    }

    public void setRoomManager(Student roomManager) {
        this.roomManager = roomManager;
    }

    public Room(byte roomNumber, byte roomCapacity, int roomRentCost, Block block, byte floorNumber) {
        setRoomNumber(roomNumber);
        setRoomCapacity(roomCapacity);
        setRoomRentCost(roomRentCost);
        setBlock(block);
        setFloorNumber(floorNumber);
    }

}
