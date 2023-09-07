import java.util.ArrayList;

public class Manager {
    private String firstName;
    private String lastName;
    private String gender;
    private String username;
    private String password;
    private Dormitory dormitory;
    static ArrayList<Manager> managers = new ArrayList<Manager>();
    private ArrayList<Student> students = new ArrayList<Student>();

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDormitory(String dormitoryName, String dormitoryType, byte numberOfBlocks, int dormitoryMembersNum) {
        dormitory = new Dormitory(dormitoryName, dormitoryType, numberOfBlocks, dormitoryMembersNum, this);
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    //add new Student
    public void addStudent(String firstName, String lastName, String studentNumber, String studentSubject, int entranceYear, int debt) {
        getStudents().add(new Student(firstName, lastName, studentNumber, studentSubject, entranceYear, debt, null));
    }

    //get all dormitory students
    public ArrayList<Student> getStudents() {
        return students;
    }

    //remove student
    public void removeStudent(Student st) {
        st.getRoom().getRoomMembers().remove(st);
        students.remove(st);
    }

    //remove all dormitory students
    public void removeAllStudents() {
        for (int i = 0; i < students.size(); i++) {
            removeStudent(students.get(i));
        }
    }

    //choose student's room
    public void chooseStudentRoom(Student st, Room room) {
        st.setRoom(room);
    }

    //edit student
    public void editStudentFirstName(Student student, String firstName) {
        student.editStudentFirstName(firstName);
    }

    public void editStudentLastName(Student student, String lastName) {
        student.editStudentLastName(lastName);
    }

    public void editStudentNum(Student student, String studentNumber) {
        student.editStudentNum(studentNumber);
    }

    public void editStudentSub(Student student, String studentSubject) {
        student.editStudentSub(studentSubject);
    }

    public void editStudentEntranceYear(Student student, int entranceYear) {
        student.editStudentEntranceYear(entranceYear);
    }

    public void editStudentDebt(Student student, int debt) {
        student.editStudentDebt(debt);
    }

    public void editStudentRoom(Student student, Room room) {
        student.getRoom().getRoomMembers().remove(student);
        student.editStudentRoom(room);
    }

    //show student's roommates
    public void showStudentRoommates(String studentNum) {
        Student st = searchStudentByStudentNumber(studentNum);
        Room room = st.getRoom();
        if (room.getRoomMembers().size() == 1) {
            System.out.println("There Is No Roommate!");
        }
        for (int i = 0, s = 1; i < room.getRoomMembers().size(); i++) {
            if (room.getRoomMembers().get(i) != st) {
                System.out.println("\n" + s + ")" + room.getRoomMembers().get(i).getFirstName() + " " + room.getRoomMembers().get(i).getLastName());
                s++;
            }
        }
    }

    //show all room members
    public void showRoomMembers(byte roomNum) {
        Room room = dormitory.getRoomObject(roomNum);
        if (room.getRoomMembers().size() == 0) {
            System.out.println("The Room Is Empty!");
        }
        for (int i = 0; i < room.getRoomMembers().size(); i++) {
            System.out.println((i + 1) + ")" + room.getRoomMembers().get(i).getFirstName() + " " + room.getRoomMembers().get(i).getLastName());
        }
    }

    //get student by student number
    public Student searchStudentByStudentNumber(String studentNumber) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentNumber().equals(studentNumber)) {
                return students.get(i);
            }
        }
        return null;
    }

    //remove all room members
    public void removeAllRoomMembers(byte roomNum) {
        Room room = dormitory.getRoomObject(roomNum);
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRoom() == room) {
                students.get(i).setRoom(null);
            }
        }
        room.getRoomMembers().clear();
    }

    //show rooms with empty space
    public void showRoomsWithEmptySpace() {
        int r = 0;
        for (int i = 0; i < dormitory.getRooms().size(); i++) {
            if (dormitory.getRooms().get(i).getRoomMembers().size() < dormitory.getRooms().get(i).getRoomCapacity()) {
                r++;
                System.out.println(r + ")" + dormitory.getRooms().get(i).getRoomNumber() + "  ");
            }
        }
        if (r == 0) {
            System.out.println("There Is No Empty Room!");
        }
    }

    //add new manager
    public static void addManager(String firstName, String lastName, String gender, String username, String password) {
        managers.add(new Manager(firstName, lastName, gender, username, password));
    }

    //get manager by username for sign in
    public static Manager getManagerObject(String username) {
        for (int i = 0; i < managers.size(); i++) {
            if (managers.get(i).getUsername().equalsIgnoreCase(username)) {
                return managers.get(i);
            }
        }
        return null;
    }

    public Manager(String firstName, String lastName, String gender, String username, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setUsername(username);
        setPassword(password);
    }
}
