package hdfc;
import java.util.*;

class Hotel {
    String name;
    int roomsAvailable;
    Hotel(String name, int roomsAvailable) {
        this.name = name;
        this.roomsAvailable = roomsAvailable;
    }
    boolean bookRoom() {
        if (roomsAvailable > 0) {
            roomsAvailable--;
            return true;
        } else {
            return false;
        }
    }
    void displayHotel() {
        System.out.println(name + " (Rooms available: " + roomsAvailable + ")");
    }
}
public class NRI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your destination: ");
        String destination = sc.nextLine();
        Hotel[] hotels = { new Hotel("XYZ", 20),new Hotel("YXZ", 20),new Hotel("ZYX", 20) };
        ArrayList<String> userBookings = new ArrayList<>();
        boolean continueBooking = true;
        while (continueBooking) {
            System.out.println("\nAvailable hotels");
            for (int i = 0; i < hotels.length; i++) {
                System.out.print((i + 1) + ". ");
                hotels[i].displayHotel();
            }
            System.out.print("Select hotel by number: ");
            int hotelChoice = sc.nextInt();
            sc.nextLine();
            if (hotelChoice < 1 || hotelChoice > hotels.length) {
                System.out.println("Invalid choice! Try again.");
                continue;
            }
            Hotel selectedHotel = hotels[hotelChoice - 1];
            if (selectedHotel.bookRoom()) {
                System.out.println("Room booked successfully at " + selectedHotel.name + " for " + name);
                userBookings.add(selectedHotel.name);
            } else {
                System.out.println("Sorry! No rooms available in " + selectedHotel.name);
            }
            System.out.print("Do you want to book another room? (yes/no): ");
            String response = sc.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                continueBooking = false;
            }
        }
        System.out.println("\nBooking summary for " + name + ":");
        if (userBookings.isEmpty()) {
            System.out.println("No bookings were made.");
        } else {
            for (int i = 0; i < userBookings.size(); i++) {
                System.out.println((i + 1) + ". " + userBookings.get(i));
            }
            System.out.println("Total bookings made: " + userBookings.size());
        }
        System.out.println("Thank you for booking! Have a nice day, " + name + "!");
    }
}
