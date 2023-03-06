public class Doctor extends Persons {
    private String soft;

    public Doctor(String position, int salary, String firstName, String secondName, int age, String soft) {
        super(position, salary, firstName, secondName, age);
        this.soft = soft;
    }


    public String getSoft() {
        return soft;
    }

    public void setSoft(String soft) {
        this.soft = soft;
    }
}