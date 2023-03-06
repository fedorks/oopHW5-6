import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    BasicText text = new BasicText();

    public static void main(String[] args) throws IOException {
        Persons persons01 = new Administration("Заместитель главного врача", 175000, "Денис", "Боткин", 47);
        Persons persons02 = new Doctor("Фтизиатр участковый", 120000, "Олег", "Зубань", 40);
        Persons persons03 = new MedNurse("Сестра палатная хирургическая", 115000, "Светлана", "Кандыбина", 38);
        Hospital businessCo = new Hospital();
        businessCo.listAdd(persons01);
        businessCo.listAdd(persons02);
        businessCo.listAdd(persons03);
        Server serverInfo = new Server();
        try (ServerSocket serverPart = new ServerSocket(555)) {
            System.out.println("Сервер запущен... Ожидаем подключение клиента...");
            Socket serverInterface = serverPart.accept();
            System.out.println("Клиент произвёл подключение...");
            DataOutputStream infoOut = new DataOutputStream(serverInterface.getOutputStream());
            DataInputStream infoIn = new DataInputStream(serverInterface.getInputStream());
            while (true) {
                String clientRequest = infoIn.readUTF();
                if (clientRequest.equals("Стоп")) break;
                if (Integer.parseInt(clientRequest) == 1) {
                    infoOut.writeUTF("Введите ID сотрудника... ");
                    int idNum = Integer.parseInt(infoIn.readUTF());
                    if(businessCo.haveOrNot(idNum)){
                        for (Persons id : businessCo) {
                            if (idNum == id.getId()) {
                                infoOut.writeUTF(id.getInfo()+"\n" + serverInfo.text.basicPrint());
                            }
                        }
                    }
                    else infoOut.writeUTF("Сотрудника с ID "+idNum+" нет в Базе данных!\n" + serverInfo.text.basicPrint());

                } else if (Integer.parseInt(clientRequest) == 2) {
                    infoOut.writeUTF("Введите ID сотрудника для удаления... ");
                    int idNum = Integer.parseInt(infoIn.readUTF());
                    if(businessCo.haveOrNot(idNum)){
                        for (Persons it : businessCo) {
                            if (idNum == it.getId()) {
                                infoOut.writeUTF("Сотрудник: " + it.getFirstName() + " " + it.getSecondName() + " удалён.\n" + serverInfo.text.basicPrint());
                            }
                        }
                    }
                    else infoOut.writeUTF("Сотрудника с ID "+idNum+" нет в Базе данных!\n" + serverInfo.text.basicPrint());

                    businessCo.removeStuff(idNum);
                } else if (Integer.parseInt(clientRequest) == 3) {
                    infoOut.writeUTF("Кого нужно добавить?\n[1] Административного сотрудника; [2] Врача; [3] Медицинскую сестру\n");
                    serverInfo.addPersonal(infoOut, infoIn, businessCo, serverInfo);
                } else if (Integer.parseInt(clientRequest) == 4) {
                    String listPer = "";
                    for (Persons it : businessCo) {
                        listPer += it.getInfo() + "\n";
                    }
                    infoOut.writeUTF(listPer + serverInfo.text.basicPrint());
                } else if (Integer.parseInt(clientRequest) == 5) {
                    infoOut.writeUTF("Введите ID сотрудника... ");
                    int idNum = Integer.parseInt(infoIn.readUTF());
                    if(businessCo.haveOrNot(idNum)){
                        serverInfo.changePersonal(idNum,infoOut,infoIn,businessCo,serverInfo);
                    }
                    else infoOut.writeUTF("Сотрудника с ID "+idNum+" нет в Базе данных!\n" + serverInfo.text.basicPrint());


                } else {
                    infoOut.writeUTF("Некорректный ввод!!!" + serverInfo.text.basicPrint());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPersonal(DataOutputStream infoOut, DataInputStream infoIn, Hospital businessCo, Server serverInfo) throws IOException {
        switch (Integer.parseInt(infoIn.readUTF())) {
            case (1):
                infoOut.writeUTF("Введите должность сотрудника");
                String scanPosition = infoIn.readUTF();
                infoOut.writeUTF("Введите имя сотрудника");
                String scanFirstName = infoIn.readUTF();
                infoOut.writeUTF("Введите фамилию сотрудника");
                String scanSecondName = infoIn.readUTF();
                infoOut.writeUTF("Введите зарплату сотрудника");
                int scanSalary = Integer.parseInt(infoIn.readUTF());
                infoOut.writeUTF("Введите возраст сотрудника");
                int scanAge = Integer.parseInt(infoIn.readUTF());
                businessCo.listAdd(new Administration(scanPosition, scanSalary, scanFirstName, scanSecondName, scanAge));
                infoOut.writeUTF("Добавлен сотрудник: " + scanFirstName + " " + scanSecondName + serverInfo.text.basicPrint());
                break;
            case (2):
                infoOut.writeUTF("Введите должность сотрудника");
                scanPosition = infoIn.readUTF();
                infoOut.writeUTF("Введите имя сотрудника");
                scanFirstName = infoIn.readUTF();
                infoOut.writeUTF("Введите фамилию сотрудника");
                scanSecondName = infoIn.readUTF();
                infoOut.writeUTF("Введите зарплату сотрудника");
                scanSalary = Integer.parseInt(infoIn.readUTF());
                infoOut.writeUTF("Введите возраст сотрудника");
                scanAge = Integer.parseInt(infoIn.readUTF());
                businessCo.listAdd(new Doctor(scanPosition, scanSalary, scanFirstName, scanSecondName, scanAge));
                infoOut.writeUTF("Добавлен сотрудник: " + scanFirstName + " " + scanSecondName + serverInfo.text.basicPrint());
                break;
            case (3):
                infoOut.writeUTF("Введите должность сотрудника");
                scanPosition = infoIn.readUTF();
                infoOut.writeUTF("Введите имя сотрудника");
                scanFirstName = infoIn.readUTF();
                infoOut.writeUTF("Введите фамилию сотрудника");
                scanSecondName = infoIn.readUTF();
                infoOut.writeUTF("Введите зарплату сотрудника");
                scanSalary = Integer.parseInt(infoIn.readUTF());
                infoOut.writeUTF("Введите возраст сотрудника");
                scanAge = Integer.parseInt(infoIn.readUTF());
                businessCo.listAdd(new Persons(scanPosition, scanSalary, scanFirstName, scanSecondName, scanAge));
                infoOut.writeUTF("Добавлен сотрудник: " + scanFirstName + " " + scanSecondName + serverInfo.text.basicPrint());
                break;
        }
    }

    public void changePersonal(int idNum, DataOutputStream infoOut, DataInputStream infoIn, Hospital businessCo, Server serverInfo) throws IOException {
        for (Persons id : businessCo) {
            if (idNum == id.getId()) {
                infoOut.writeUTF(id.getInfo() + "\nКакие данные изменить?\n[1] Имя; [2] Фамилия" +
                        "; [3] Должность; [4] Зарплата; [5] Возраст;");
                switch (Integer.parseInt(infoIn.readUTF())) {
                    case (1):
                        infoOut.writeUTF("Введите данные... ");
                        id.setFirstName(infoIn.readUTF());
                        infoOut.writeUTF("Теперь сотрудника зовут " + id.getFirstName() + serverInfo.text.basicPrint());
                        break;
                    case (2):
                        infoOut.writeUTF("Введите данные... ");
                        id.setSecondName(infoIn.readUTF());
                        infoOut.writeUTF("Теперь фамилия сотрудника " + id.getFirstName() + ": " + id.getSecondName() + serverInfo.text.basicPrint());
                        break;
                    case (3):
                        infoOut.writeUTF("Введите данные... ");
                        id.setPosition(infoIn.readUTF());
                        infoOut.writeUTF("Теперь должность сотрудника " + id.getFirstName() + ": " + id.getPosition() + serverInfo.text.basicPrint());
                        break;
                    case (4):
                        infoOut.writeUTF("Введите данные... ");
                        id.setSalary(Integer.parseInt(infoIn.readUTF()));
                        infoOut.writeUTF("Теперь зарплата сотрудника " + id.getFirstName() + ": " + id.getSalary() + serverInfo.text.basicPrint());
                        break;
                    case (5):
                        infoOut.writeUTF("Введите данные... ");
                        id.setAge(Integer.parseInt(infoIn.readUTF()));
                        infoOut.writeUTF("Теперь возраст сотрудника " + id.getFirstName() + ": " + id.getAge() + serverInfo.text.basicPrint());
                        break;
                }
                break;
            }
        }
    }
}