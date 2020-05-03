import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.hibernate.instance.IHazelcastInstanceFactory;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Program {
    static  HazelcastInstance hazelcastInstance;
    static Scanner scanner;
    static Random r;
    static boolean exit = true;

    static void fillData(){
        Map<Long,SerwisSamochodowy> serwisSamochodowyMap = hazelcastInstance.getMap("serwisSamochodowy");
        serwisSamochodowyMap.put(r.nextLong(),new SerwisSamochodowy("Antoni Nowak",4,5600,"Uszczelka pod glowica"));
        serwisSamochodowyMap.put(r.nextLong(),new SerwisSamochodowy("Jacek Kowalski",24,60,"Wymiana hamulcow"));
        serwisSamochodowyMap.put(r.nextLong(),new SerwisSamochodowy("Dariusz Barek",15,21,"Zmania oleju"));
        serwisSamochodowyMap.put(r.nextLong(),new SerwisSamochodowy("Kamil Kamil",44,38,"Wymiana filtru"));
        serwisSamochodowyMap.put(r.nextLong(),new SerwisSamochodowy("Tomasz Kaminski",4,60,"Wymiana kol"));
        serwisSamochodowyMap.put(r.nextLong(),new SerwisSamochodowy("Zache Brylski",5,6,"Totalny remont"));
    }
    static void getOneByDescribe(){
        System.out.println("Podaj opis:");
        Map<Long,SerwisSamochodowy> serwisSamochodowyMap = hazelcastInstance.getMap("serwisSamochodowy");
        String choose = scanner.nextLine();
        for (Map.Entry<Long,SerwisSamochodowy> e: serwisSamochodowyMap.entrySet()) {
            if((e.getValue().getDescribe()).contains(choose))
            {
                System.out.println(e.getKey() + " => " + e.getValue().toString());
            }

        }
    }
    static void addNew(){
        Map<Long,SerwisSamochodowy> serwisSamochodowyMap = hazelcastInstance.getMap("serwisSamochodowy");
        SerwisSamochodowy serwisSamochodowy = new SerwisSamochodowy();
        System.out.println("Podaj nazwe wykonwcy: ");
        serwisSamochodowy.setRepairManager(scanner.nextLine());

        System.out.println("Podaj szacowany czas naprawy: ");
        serwisSamochodowy.setTimeRepair(scanner.nextInt());

        System.out.println("Podaj liczbę osob potrzebnych do naprawy: ");
        serwisSamochodowy.setNumberTeam(scanner.nextInt());

        System.out.println("Podaj wlasciciela pojazdu: ");
        serwisSamochodowy.setDescribe(scanner.nextLine());

        Long key = r.nextLong();
        serwisSamochodowyMap.put(key,serwisSamochodowy);
    }
    static void getAll(){
        Map<Long,SerwisSamochodowy> serwisSamochodowyMap = hazelcastInstance.getMap("serwisSamochodowy");
        System.out.println("Wszystkie naprawy serwisu: ");
        for(Map.Entry<Long,SerwisSamochodowy> e: serwisSamochodowyMap.entrySet()){
            System.out.println(e.getKey() + " => " + e.getValue().toString());
        }
    }
    static void getOneByRepairManager(){
        Map<Long,SerwisSamochodowy> serwisSamochodowyMap = hazelcastInstance.getMap("serwisSamochodowy");
        System.out.println("Podaj kto jest zlecony do naprawy (lider): ");
        String nazwa = scanner.nextLine();

        for (Map.Entry<Long,SerwisSamochodowy> e: serwisSamochodowyMap.entrySet()){
            if (e.getValue().getRepairManager().equals(nazwa)==true){
                System.out.println(e.getKey() + " => " + e.getValue().toString());
            }
        }
    }
    static void update(){
        Map<Long,SerwisSamochodowy> serwisSamochodowyMap = hazelcastInstance.getMap("serwisSamochodowy");
        System.out.println("Podaj klucz: ");
        Long key = scanner.nextLong();
        for (Map.Entry<Long,SerwisSamochodowy> e: serwisSamochodowyMap.entrySet()){
            if (key == e.getKey().longValue()){
                System.out.println("Wpisz Q aby pominąć!!!");
                System.out.println(e.getKey() + " => " + e.getValue().toString());

                System.out.println("Zmien nazwe wykonawcy: ");

                String nameManager = scanner.nextLine();
                if(nameManager.equals("Q")==false){
                    e.getValue().setRepairManager(nameManager);
                }

                System.out.println("Zmień czas naprawy: ");
                String timeRepair = scanner.nextLine();
                if(timeRepair.equals("Q")==false){
                    e.getValue().setTimeRepair(Integer.parseInt(timeRepair));
                }
                System.out.println("Zmień liczbe wymaganych osob: ");
                String teamAmount = scanner.nextLine();
                if(teamAmount.equals("Q")==false){
                    e.getValue().setNumberTeam(Integer.parseInt(teamAmount));
                }

                System.out.println("Zmień nazwę wlasciciela: ");
                String describe = scanner.nextLine();
                if(describe.equals("Q")==false){
                    e.getValue().setDescribe(describe);
                }
            }
        }
    }
    static void remove(){
        Map<Long,SerwisSamochodowy> serwisSamochodowyMap = hazelcastInstance.getMap("serwisSamochodowy");
        System.out.println("Podaj klucz: ");
        Long key = scanner.nextLong();
        serwisSamochodowyMap.remove(key);
    }
    static private int getMenuChoice() {
        int choice = -1;
        do {
            System.out.print("Podaj wybor: ");
            try {
                choice = Integer.parseInt(scanner   .nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Podawaj tylko liczby!");
            }
            if (choice < 1 || choice > 7) {
                System.out.println("Wybor nie istnieje");
            }

        } while (choice < 1 || choice > 7);

        return choice;
    }
    static private void printMenu() {
        System.out.println("\n Witaj w programie co chciałbys zrobic: ");
        System.out.println("1. Wyswietl wszystkie dane");
        System.out.println("2. Wyswietl dana po kierowniku naprawy");
        System.out.println("3. Wyswietl po okreslonym opisie");
        System.out.println("4. Dodaj rekord");
        System.out.println("5. Udpate rekordu");
        System.out.println("6. Usun rekord");
        System.out.println("7. Wyjscie\n");
    }
    static private void performAction(int choice) {
        switch (choice) {
            case 1:
                getAll();
                break;
            case 2:
                getOneByRepairManager();
                break;
            case 3:
                getOneByDescribe();
                break;
            case 4:
                addNew();
                break;
            case 5:
                update();
                break;
            case 6:
                remove();
                break;
            case 7:
                exit = false;
                break;
            default:
                System.out.println("Brak obsługi.");
        }
    }
    static public void runMenu() {
        while (exit) {
            printMenu();
            int choice = getMenuChoice();
            performAction(choice);
        }
    }

    public static void main(String[] args) throws UnknownHostException{
        hazelcastInstance = Hazelcast.newHazelcastInstance(HConfig.getConfig());
        scanner = new Scanner(System.in);
        r = new Random();
        fillData();
        runMenu();
        scanner.nextInt();

    }
}
