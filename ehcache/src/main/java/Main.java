import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import java.util.Scanner;

public class Main {
    static boolean exit = true;
    static Cache cache;
    static Scanner scan = new Scanner(System.in);

    static private void printData() {
        System.out.println("Podaj klucz do wypisania:");
        String keyUser = scan.nextLine();
        System.out.println(cache.get(keyUser));
   }
    static private void printDataAll(){
       for (Object key:cache.getKeys()) {
           System.out.println(" * "+ cache.get((String) key));
       }
   }
    static void printRowByName(){
        System.out.println("Podaj ID Klienta:");
        String name = scan.nextLine();
        for (Object entity: cache.getKeys()) {
           if(((String)entity).contains(name))
               System.out.println(cache.get((String)entity));
        }
    }
    static void addNewRow() {
        System.out.println("Podaj nazwe klucza:");
        String key = scan.nextLine();
        System.out.println("Podaj nazwe value");
        String value = scan.nextLine();
        cache.put(new Element(key, value));

    }
    static void updateRow() {
        System.out.println("Podaj nowy klucz i wartosc");
        System.out.println("Podaj nazwe klucza:");
        String key = scan.nextLine();
        System.out.println("Podaj nazwe value");
        String value = scan.nextLine();
        cache.put(new Element(key, value));
    }
    static void deleteRow() {
        System.out.println("Podaj o jakim kluczu chcesz usunac: ");
        if(scan.hasNextLine()) {
            String key = scan.nextLine();
            cache.remove(key);
            System.out.println("Usunięto rekord o kluczu: "+key);
        }

    }
    static private int getMenuChoice() {
        int choice = -1;
        do {
            System.out.print("Podaj wybor: ");
            try {
                choice = Integer.parseInt(scan.nextLine());
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
        System.out.println("2.Wyswietl dana po kluczu");
        System.out.println("3.Wyswietl po nazwie auta");
        System.out.println("4. Dodaj rekord");
        System.out.println("5. Udpate rekordu");
        System.out.println("6. Usun rekord");
        System.out.println("7. Wyjscie\n");
    }
    static private void performAction(int choice) {
        switch (choice) {
            case 1:
                printDataAll();
                break;
            case 2:
                printData();
                break;
            case 3:
                printRowByName();
                break;
            case 4:
                addNewRow();
                break;
            case 5:
                updateRow();
                break;
            case 6:
                deleteRow();
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

    public static void main(String[] args) {
        CacheManager manager = CacheManager.create();
        cache = new Cache(
                new CacheConfiguration("serwisSamochodowy",100)
        );
        manager.addCache(cache);
        cache = manager.getCache("serwisSamochodowy");
        Data data = new Data(cache);
        data.addEntinty();
        runMenu();
    }
}
