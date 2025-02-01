import java.util.*;

interface ParkingOperations {
    boolean parkVehicle(String vehicleDetails);
    boolean removeVehicle(int spotID);
    void viewParkedVehicles();
    boolean checkAvailability();
}

abstract class ParkingSpot {
    protected int spotID;
    protected boolean isOccupied;
    protected String vehicleDetails;

    public ParkingSpot(int spotID) {
        this.spotID = spotID;
        this.isOccupied = false;
        this.vehicleDetails = "";
    }

    public int getSpotID() {
        return spotID;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void park(String vehicleDetails) {
        this.isOccupied = true;
        this.vehicleDetails = vehicleDetails;
    }

    public void vacate() {
        this.isOccupied = false;
        this.vehicleDetails = "";
    }
}

class ParkingLot implements ParkingOperations {
    private final List<ParkingSpot> spots;

    public ParkingLot(int capacity) {
        spots = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            spots.add(new ParkingSlot(i));
        }
    }

    @Override
    public boolean parkVehicle(String vehicleDetails) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                spot.park(vehicleDetails);
                System.out.println("Vehicle parked at spot ID: " + spot.getSpotID());
                return true;
            }
        }
        System.out.println("Parking lot is full!");
        return false;
    }

    @Override
    public boolean removeVehicle(int spotID) {
        for (ParkingSpot spot : spots) {
            if (spot.getSpotID() == spotID && spot.isOccupied()) {
                spot.vacate();
                System.out.println("Vehicle removed from spot ID: " + spotID);
                return true;
            }
        }
        System.out.println("Spot is already empty or does not exist.");
        return false;
    }

    @Override
    public void viewParkedVehicles() {
        boolean empty = true;
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied()) {
                System.out.println("Spot " + spot.getSpotID() + ": " + spot.getVehicleDetails());
                empty = false;
            }
        }
        if (empty) {
            System.out.println("No vehicles parked.");
        }
    }

    @Override
    public boolean checkAvailability() {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                return true;
            }
        }
        return false;
    }
}

class ParkingSlot extends ParkingSpot {
    public ParkingSlot(int spotID) {
        super(spotID);
    }
}

public class ParkingLotManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot(5); // Example: Parking lot with 5 spots

        while (true) {
            System.out.println("\n--- Parking Lot System ---");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. View Parked Vehicles");
            System.out.println("4. Check Availability");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle details: ");
                    String vehicle = scanner.nextLine();
                    parkingLot.parkVehicle(vehicle);
                    break;
                case 2:
                    System.out.print("Enter spot ID to remove vehicle: ");
                    int spotID = scanner.nextInt();
                    parkingLot.removeVehicle(spotID);
                    break;
                case 3:
                    parkingLot.viewParkedVehicles();
                    break;
                case 4:
                    if (parkingLot.checkAvailability()) {
                        System.out.println("Parking spots available.");
                    } else {
                        System.out.println("Parking lot is full.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}