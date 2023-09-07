public class Student {
    private String firstName;
    private String lastName;
    private String studentNumber;
    private String studentSubject;
    private int entranceYear;
    private int debt;
    private Room room;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentSubject(String studentSubject) {
        this.studentSubject = studentSubject;
    }

    public void setEntranceYear(int entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getStudentSubject() {
        return studentSubject;
    }

    public int getEntranceYear() {
        return entranceYear;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public void setRoom(Room room) {
        if (room != null) {
            room.setRoomMembers(this);
        }
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void editStudentFirstName(String firstName) {
        this.setFirstName(firstName);
    }

    public void editStudentLastName(String lastName) {
        this.setLastName(lastName);
    }

    public void editStudentNum(String studentNumber) {
        this.setStudentNumber(studentNumber);
    }

    public void editStudentSub(String studentSubject) {
        this.setStudentSubject(studentSubject);
    }

    public void editStudentEntranceYear(int entranceYear) {
        this.setEntranceYear(entranceYear);
    }

    public void editStudentDebt(int debt) {
        this.setEntranceYear(debt);
    }

    public void editStudentRoom(Room room) {
        this.setRoom(room);
    }

    public String toString() {
        if (room == null) {
            return "First Name: " + getFirstName() + "  Last Name: " + getLastName() + "  Student Number: " + studentNumber + "\nStudent Subject: " + getStudentSubject() + "  Entrance Year: " + getEntranceYear() + "  Debt: " + getDebt() + "  Room: Unsigned";
        } else {
            return "First Name: " + getFirstName() + "  Last Name: " + getLastName() + "  Student Number: " + studentNumber + "\nStudent Subject: " + getStudentSubject() + "  Entrance Year: " + getEntranceYear() + "  Debt: " + getDebt() + "  Room: " + room.getRoomNumber();
        }
    }

    public Student(String firstName, String lastName, String studentNumber, String studentSubject, int entranceYear, int debt, Room room) {
        setFirstName(firstName);
        setLastName(lastName);
        setStudentNumber(studentNumber);
        setStudentSubject(studentSubject);
        setEntranceYear(entranceYear);
        setDebt(debt);
        setRoom(room);
    }
}
