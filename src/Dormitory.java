import java.util.ArrayList;

public class Dormitory {
    private String dormitoryName;
    private String dormitoryType;
    private byte numberOfBlocks;
    private Manager manager;
    private int dormitoryMembersNum;
    private ArrayList<Block> blocks = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    public String getDormitoryName() {
        return dormitoryName;
    }

    public String getDormitoryType() {
        return dormitoryType;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public void setDormitoryType(String dormitoryType) {
        this.dormitoryType = dormitoryType;
    }

    public void setNumberOfBlocks(byte numberOfBlocks) {
        this.numberOfBlocks = numberOfBlocks;
    }

    public byte getNumberOfBlocks() {
        return numberOfBlocks;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int getDormitoryMembersNum() {
        return dormitoryMembersNum;
    }

    public void setDormitoryMembersNum(int dormitoryMembersNum) {
        this.dormitoryMembersNum = dormitoryMembersNum;
    }

    public Room getRoomObject(byte roomNumber) {
        for (int i = 0; i < getRooms().size(); i++) {
            if (getRooms().get(i).getRoomNumber() == roomNumber) {
                return getRooms().get(i);
            }
        }
        return null;
    }

    public void addBlock(char blockCharacter, byte numberOfFloors) {
        blocks.add(new Block(blockCharacter, numberOfFloors, this));
    }

    public void addRoom(byte roomNumber, byte roomCapacity, int roomRentCost, Block block, byte floorNumber) {
        rooms.add(new Room(roomNumber, roomCapacity, roomRentCost, block, floorNumber));
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Block getBlockObject(char blockCharacter) {
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).getBlockCharacter() == blockCharacter) {
                return blocks.get(i);
            }
        }
        return null;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Dormitory(String dormitoryName, String dormitoryType, byte numberOfBlocks, int dormitoryMembersNum, Manager manager) {
        setDormitoryName(dormitoryName);
        setDormitoryType(dormitoryType);
        setNumberOfBlocks(numberOfBlocks);
        setDormitoryMembersNum(dormitoryMembersNum);
        setManager(manager);
    }
}
