import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hospital implements Iterable<Persons> {
    private List<Persons> listPersonal = new ArrayList<>();

    public void listAdd(Persons person) {
        listPersonal.add(person);
    }

    public void getList() {
        for (Persons persons : listPersonal) {
            System.out.println(persons.getInfo());
        }
    }

    public void removeStuff(int id) {
        System.out.println("Сотрудник: " + listPersonal.get(id - 1).getInfo() + " был уволен");
        listPersonal.remove(checkPerson(id));
    }

    @Override
    public Iterator<Persons> iterator() {
        return listPersonal.iterator();
    }

    public Persons checkPerson(int id) {
        for (Persons persons : listPersonal) {
            if (id == persons.getId()) {
                persons.getInfo();
                return persons;
            }
        }
        System.out.println("Нет такого сотрудника");
        return null;
    }
    public boolean haveOrNot(int id) {
        for (Persons persons : listPersonal) {
            if (id == persons.getId()) {
                persons.getInfo();
                return true;
            }
        }
        return false;
    }

}