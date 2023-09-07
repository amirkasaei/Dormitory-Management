import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DormitoryManagement {
    static Scanner scanner = new Scanner(System.in);
    static Manager manager;
    static byte select;

    //sign up
    static void signUp() {
        //manager inputs
        System.out.print("\nPlease Enter Your First name: ");
        String firstName = (scanner.next());
        System.out.print("Please Enter Your last name: ");
        String lastName = (scanner.next());
        System.out.println("1)Male\t2)Female");
        System.out.print("Please select Your Gender: ");
        String gender = null;
        select = scanner.nextByte();
        switch (select) {
            case 1:
                gender = "Male";
                break;
            case 2:
                gender = "Female";
                break;
        }
        String username;
        do {
            System.out.print("Please Enter Your username: ");
            username = (scanner.next());
            if (Manager.getManagerObject(username) != null) {
                System.out.println("This Username Is Not Available!! Please Try Again!!");
            }
        } while (Manager.getManagerObject(username) != null);
        System.out.print("Please Enter password: ");
        String password = (scanner.next());
        //manager creation
        Manager.addManager(firstName, lastName, gender, username, password);
        manager = Manager.getManagerObject(username);
        //dormitory inputs
        System.out.print("\nPlease Enter The Dormitory Name: ");
        String dormitoryName = scanner.next();
        System.out.println("1)Male\t2)Female");
        System.out.print("Please select The Dormitory Type: ");
        String dormitoryType = null;
        select = scanner.nextByte();
        switch (select) {
            case 1:
                dormitoryType = "Male";
                break;
            case 2:
                dormitoryType = "Female";
                break;
        }
        System.out.print("Please Enter The Number Of Dormitory Blocks: ");
        byte numberOfBlocks = scanner.nextByte();
        System.out.print("Please Enter The Number Of Dormitory Members: ");
        int dormitoryMembersNum = scanner.nextInt();
        //dormitory creation
        manager.setDormitory(dormitoryName, dormitoryType, numberOfBlocks, dormitoryMembersNum);
    }

    //sign in
    static void signIn() {
        //username check
        System.out.print("Please Enter Your Username: ");
        do {
            String username = scanner.next();
            manager = Manager.getManagerObject(username);
            if (manager == null) {
                System.out.println("Wrong Username!! Please Try Again!");
            }
        } while (manager == null);
        //password check
        System.out.print("Please Enter password: ");
        boolean wrongPass = true;
        do {
            String password = scanner.next();
            if (manager.getPassword().equals(password)) {
                wrongPass = false;
                break;
            }
            if (wrongPass) {
                System.out.println("Wrong Password!! Please Try Again!");
            }
        } while (wrongPass);
    }

    //add new student
    static void addStudent() {
        //dormitory capacity check
        if (manager.getDormitory().getDormitoryMembersNum() == manager.getStudents().size()) {
            System.out.println("You Can't Add Another Student!\nThe Dormitory Is Full!!");
            return;
        }
        //inputs
        System.out.print("Please Enter Student's First Name: ");
        String firstName = scanner.next();
        System.out.print("Please Enter Student's Last Name: ");
        String lastName = scanner.next();
        String studentNumber;
        do {
            System.out.print("Please Enter Student Number: ");
            studentNumber = scanner.next();
            if (manager.searchStudentByStudentNumber(studentNumber) != null) {
                System.out.println("A Student With This Number Already Exists! Please Try Again!!");
            }
        } while (manager.searchStudentByStudentNumber(studentNumber) != null);
        System.out.print("Please Enter Student's Studying Subject: ");
        String studentSubject = scanner.next();
        System.out.print("Please Enter Student's Entrance Year: ");
        int entranceYear = scanner.nextInt();
        System.out.print("Please Enter Student's Debt: ");
        int debt = scanner.nextInt();
        //student creation
        manager.addStudent(firstName, lastName, studentNumber, studentSubject, entranceYear, debt);
        chooseStudentRoom(manager.searchStudentByStudentNumber(studentNumber));
    }

    //add new block
    static void addBlock() {
        if (manager.getDormitory().getBlocks().size() == manager.getDormitory().getNumberOfBlocks()) {
            System.out.println("You Can't Add Another Block!");
            return;
        }
        //inputs
        char blockCharacter;
        do {
            System.out.print("Please Enter Block Character: ");
            blockCharacter = scanner.next().charAt(0);
            if (manager.getDormitory().getBlockObject(blockCharacter) != null) {
                System.out.println("A Block With This Character Already Exists! Please Try Again!!");
            }
        } while (manager.getDormitory().getBlockObject(blockCharacter) != null);
        System.out.print("Please Enter Number Of Floors: ");
        byte numberOfFloors = scanner.nextByte();
        manager.getDormitory().addBlock(blockCharacter, numberOfFloors);
    }

    //add new room
    static void addRoom() {
        //inputs
        byte roomNumber;
        do {
            System.out.print("Please Enter Room Number: ");
            roomNumber = scanner.nextByte();
            if (manager.getDormitory().getRoomObject(roomNumber) != null) {
                System.out.println("A Room With This Number Already Exists! Please Try Again!!");
            }
        } while (manager.getDormitory().getRoomObject(roomNumber) != null);
        System.out.print("Please Enter Room Capacity: ");
        byte roomCapacity = scanner.nextByte();
        System.out.print("Please Enter Room Rent Cost: ");
        int roomRentCost = scanner.nextInt();
        char blockCharacter;
        //block character check
        Block block;
        do {
            System.out.print("Please Enter Block Character: ");
            blockCharacter = scanner.next().charAt(0);
            block = manager.getDormitory().getBlockObject(blockCharacter);
            if (block == null) {
                System.out.println("Wrong Block Character!");
            }
        } while (block == null);
        byte floorNumber;
        //floor number check
        do {
            System.out.print("Please Enter Floor Number: ");
            floorNumber = scanner.nextByte();
            if (floorNumber > block.getNumberOfFloors()) {
                System.out.println("Wrong Floor Number!");
            }
        } while (floorNumber > block.getNumberOfFloors());
        //room creation
        manager.getDormitory().addRoom(roomNumber, roomCapacity, roomRentCost, block, floorNumber);
    }

    //edit student's profile
    static void editStudent() {
        String studentNumber;
        Student student;
        do {
            System.out.print("Please Enter Student's Student Number: ");
            studentNumber = scanner.next();
            student = manager.searchStudentByStudentNumber(studentNumber);
            if (student == null) {
                System.out.println("Wrong Student Number!");
            }
        } while (student == null);
        System.out.println(student);
        System.out.println("\n1)First Name  2)Last Name  3)Student Number  4)Student's Subject\n5)Entrance Year  6)Student's Debt  7)Student's Room  8)Set As Room Manager");
        System.out.print("Please Choose What You Want To Edit: ");
        select = scanner.nextByte();
        switch (select) {
            case 1:
                System.out.print("Please Enter Student's New First Name:");
                manager.editStudentFirstName(student, scanner.next());
                break;
            case 2:
                System.out.print("Please Enter Student's New Last Name: ");
                manager.editStudentLastName(student, scanner.next());
                break;
            case 3:
                System.out.print("Please Enter New Student Number: ");
                manager.editStudentNum(student, scanner.next());
                break;
            case 4:
                System.out.print("Please Enter Student's New Studying Subject: ");
                manager.editStudentSub(student, scanner.next());
                break;
            case 5:
                System.out.print("Please Enter Student's New Entrance Year: ");
                manager.editStudentEntranceYear(student, scanner.nextInt());
                break;
            case 6:
                System.out.print("Please Enter Student's New Debt: ");
                manager.editStudentDebt(student, scanner.nextInt());
                break;
            case 7:
                //room number check
                byte roomNumber;
                Room room;
                do {
                    System.out.print("Please Enter Student's New Room Number: ");
                    roomNumber = scanner.nextByte();
                    room = manager.getDormitory().getRoomObject(roomNumber);
                    if (room == null) {
                        System.out.println("Wrong Room Number!");
                    } else if (room.getRoomCapacity() == room.getRoomMembers().size()) {
                        System.out.println("The Room Is Full!");
                    }
                } while (room == null || room.getRoomCapacity() == room.getRoomMembers().size());
                manager.editStudentRoom(student, room);
                break;
            case 8:
                student.getRoom().setRoomManager(student);
                break;
        }
    }

    //remove a student
    static void removeStudent() {
        System.out.print("Please Enter Student Number: ");
        manager.removeStudent(manager.searchStudentByStudentNumber(scanner.next()));
    }

    //remove all room members
    static void emptyRoom() {
        System.out.print("Please Enter The Room Number: ");
        manager.removeAllRoomMembers(scanner.nextByte());
    }

    //show student's roommates
    static void showStudentRoommates() {
        System.out.print("Please Enter Student Number: ");
        manager.showStudentRoommates(scanner.next());
    }

    //show all room members
    static void showRoomMembers() {
        System.out.print("Please Enter Room Number: ");
        manager.showRoomMembers(scanner.nextByte());
    }

    //show rooms with empty space
    static void showEmptyRooms() {
        manager.showRoomsWithEmptySpace();
    }

    //remove all dormitory members
    static void removeAllStudents() {
        manager.removeAllStudents();
    }

    //choose student's room
    static void chooseStudentRoom(Student student) {
        //room number check
        byte roomNumber;
        Room room;
        do {
            System.out.print("Please Enter Room Number: ");
            roomNumber = scanner.nextByte();
            room = manager.getDormitory().getRoomObject(roomNumber);
            if (room == null || room.getRoomCapacity() == room.getRoomMembers().size()) {
                System.out.println("Wrong Room Number!");
            } else if (room.getRoomCapacity() == room.getRoomMembers().size()) {
                System.out.println("The Room Is Full!");
            }
        } while (room == null || room.getRoomCapacity() == room.getRoomMembers().size());
        //adding room
        manager.chooseStudentRoom(student, room);
    }

    //manger menu
    static void subMenu() throws IOException {
        System.out.println("\n1)New Student  2)Register New Block  3)Register New Room  4)Edit Student  5)Remove Student\n6)Empty Room  7)Show Student's Roommates  8)Show Room Members  9)Show Empty Rooms  10)Remove All Students\nEnter 0 To SignOut");
        select = scanner.nextByte();
        boolean signOut = false;
        switch (select) {
            case 0:
                menu();
                signOut = true;
                break;
            case 1:
                addStudent();
                break;
            case 2:
                addBlock();
                break;
            case 3:
                addRoom();
                break;
            case 4:
                editStudent();
                break;
            case 5:
                removeStudent();
                break;
            case 6:
                emptyRoom();
                break;
            case 7:
                showStudentRoommates();
                break;
            case 8:
                showRoomMembers();
                break;
            case 9:
                showEmptyRooms();
                break;
            case 10:
                removeAllStudents();
                break;
        }
        if (!signOut) {
            subMenu();
        }
    }

    //main menu
    static void menu() throws IOException {
        System.out.println("1)Sign UP\t2)Sign In\t3)Exit");
        select = scanner.nextByte();
        switch (select) {
            case 1:
                signUp();
                break;
            case 2:
                signIn();
                break;
            case 3:
                return;
        }
        if (manager.getGender().equals("Male")) {
            System.out.println("\nWelcome Mr." + manager.getLastName() + "!");
        } else {
            System.out.println("\nWelcome Ms." + manager.getLastName() + "!");
        }
        subMenu();
    }

    public static void main(String[] args) throws IOException {
        //mangers reader
        try (FileReader fileReader = new FileReader("managers.txt");) {
            Scanner sc = new Scanner(fileReader);
            String firstName, lastName, gender, username, password, dormitoryName, dormitoryType;
            byte numberOfBlocks;
            int dormitoryMembersNum;
            String tmp;
            String[] temp;
            while (sc.hasNextLine()) {
                tmp = sc.nextLine();
                temp = tmp.split("/");
                firstName = temp[0];
                lastName = temp[1];
                gender = temp[2];
                username = temp[3];
                password = temp[4];
                dormitoryName = temp[5];
                dormitoryType = temp[6];
                numberOfBlocks = Byte.parseByte(temp[7]);
                dormitoryMembersNum = Integer.parseInt(temp[8]);
                Manager.addManager(firstName, lastName, gender, username, password);
                Manager.getManagerObject(username).setDormitory(dormitoryName, dormitoryType, numberOfBlocks, dormitoryMembersNum);
            }
        } catch (FileNotFoundException e) {
            System.out.println("NO PREVIOUS DATA FOUND!!");
        }
        //blocks reader
        try (FileReader fileReader = new FileReader("blocks.txt");) {
            Scanner sc = new Scanner(fileReader);
            String username;
            char blockCharacter;
            byte numberOfFloors;
            String tmp;
            String[] temp;
            while (sc.hasNextLine()) {
                tmp = sc.nextLine();
                temp = tmp.split("/");
                username = temp[0];
                blockCharacter = temp[1].charAt(0);
                numberOfFloors = Byte.parseByte(temp[2]);
                Manager.getManagerObject(username).getDormitory().addBlock(blockCharacter, numberOfFloors);
            }
        } catch (FileNotFoundException e) {
            System.out.println("NO PREVIOUS DATA FOUND!!");
        }
        //rooms reader
        try (FileReader fileReader = new FileReader("rooms.txt");) {
            Scanner sc = new Scanner(fileReader);
            String username;
            byte roomNumber;
            byte roomCapacity;
            int roomRentCost;
            char blockCharacter;
            byte floorNumber;
            String tmp;
            String[] temp;
            while (sc.hasNextLine()) {
                tmp = sc.nextLine();
                temp = tmp.split("/");
                username = temp[0];
                roomNumber = Byte.parseByte(temp[1]);
                roomCapacity = Byte.parseByte(temp[2]);
                roomRentCost = Integer.parseInt(temp[3]);
                blockCharacter = temp[4].charAt(0);
                floorNumber = Byte.parseByte(temp[5]);
                Manager.getManagerObject(username).getDormitory().addRoom(roomNumber, roomCapacity, roomRentCost, Manager.getManagerObject(username).getDormitory().getBlockObject(blockCharacter), floorNumber);
            }
        } catch (FileNotFoundException e) {
            System.out.println("NO PREVIOUS DATA FOUND!!");
        }
        //students reader
        try (FileReader fileReader = new FileReader("students.txt");) {
            Scanner sc = new Scanner(fileReader);
            String username, firstName, lastName, studentNumber, studentSubject;
            int entranceYear, debt;
            byte roomNumber;
            String tmp;
            String[] temp;
            while (sc.hasNextLine()) {
                tmp = sc.nextLine();
                temp = tmp.split("/");
                username = temp[0];
                firstName = temp[1];
                lastName = temp[2];
                studentNumber = temp[3];
                studentSubject = temp[4];
                entranceYear = Integer.parseInt(temp[5]);
                debt = Integer.parseInt(temp[6]);
                roomNumber = Byte.parseByte(temp[7]);
                Manager.getManagerObject(username).addStudent(firstName, lastName, studentNumber, studentSubject, entranceYear, debt);
                Manager.getManagerObject(username).searchStudentByStudentNumber(studentNumber).setRoom(Manager.getManagerObject(username).getDormitory().getRoomObject(roomNumber));
            }
        } catch (FileNotFoundException e) {
            System.out.println("NO PREVIOUS DATA FOUND!!");
        }

        menu();

        //add data to file
        for (Manager m: Manager.managers) {
            //manager writer
            try (FileWriter fileWriter = new FileWriter("managers.txt")) {
                fileWriter.write(m.getFirstName() + "/" + m.getLastName() + "/" + m.getGender() + "/" + m.getUsername() + "/" +
                                m.getPassword() + "/" + m.getDormitory().getDormitoryName() + "/" + m.getDormitory().getDormitoryType() +
                                "/" + m.getDormitory().getNumberOfBlocks() + "/" + m.getDormitory().getDormitoryMembersNum() + "\n");
            } catch (IOException e) {
                e.getStackTrace();
            }
            //block writer
            for (Block b: m.getDormitory().getBlocks()) {
                try (FileWriter fileWriter = new FileWriter("blocks.txt")) {
                    fileWriter.write(m.getUsername() + "/" + b.getBlockCharacter() + "/" + b.getNumberOfFloors() + "\n");
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
            //room writer
            for (Room r: m.getDormitory().getRooms()) {
                try (FileWriter fileWriter = new FileWriter("rooms.txt")) {
                    fileWriter.write(m.getUsername() + "/" + r.getRoomNumber() + "/" + r.getRoomCapacity() + "/" + r.getRoomRentCost() + "/" + r.getBlock().getBlockCharacter() + "/" + r.getFloorNumber() + "\n");
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
            //student writer
            for (Student s: m.getStudents()) {
                try (FileWriter fileWriter = new FileWriter("students.txt")) {
                    fileWriter.write(m.getUsername() + "/" + s.getFirstName() + "/" + s.getLastName() + "/" + s.getStudentNumber() + "/" + s.getStudentSubject() + "/" + s.getEntranceYear() + "/" + s.getDebt() + "/" + s.getRoom().getRoomNumber() + "\n");
                } catch (IOException e) {

                } catch (NullPointerException e) {
                    e.getStackTrace();
                }
            }
        }
    }
}
