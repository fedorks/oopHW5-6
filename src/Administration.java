public class Administration extends Persons {
    private int parkingNumber;

    public Administration(String position, int salary, String firstName, String secondName, int age, int parkingNumber) {
        super(position, salary, firstName, secondName, age);
        this.parkingNumber = parkingNumber;
    }

    public int getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(int parkingNumber) {
        this.parkingNumber = parkingNumber;
    }
}