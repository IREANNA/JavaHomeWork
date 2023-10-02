import java.util.*;

public class TelDir {

    public static void main(String[] args) {

//  Реализуйте структуру телефонной книги с помощью HashMap.
//  Программа также должна учитывать, что в во входной структуре будут повторяющиеся имена
//  с разными телефонами, их необходимо считать, как одного человека с разными телефонами.
//  Вывод должен быть отсортирован по убыванию числа телефонов.

        HashMap<String, HashSet<String>> telDir = new HashMap<>();

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Введите имя и номер телефона (например: Иванов Иван=1234567). " +
                     "Чтобы закончить, введите 'конец' или 'end: ");

            String console = in.nextLine();
            if (console.equalsIgnoreCase("конец") || console.equalsIgnoreCase("end")) {
                break;
            }
            String key = console.split("=")[0];
            HashSet<String> record = new HashSet<String>();
            record.add(console.split("=")[1]);

            if (telDir.putIfAbsent(key, record)!=null){
                HashSet<String> temp = telDir.get(key);
                temp.add(console.split("=")[1]);
                telDir.put(key, temp);
            }
        }
        System.out.println("Справочник: ");
        System.out.println(telDir);

        HashMap<String,Integer> telDirCount = new HashMap<String,Integer>();
        for (Map.Entry<String,HashSet<String>> item: telDir.entrySet()) {
            telDirCount.put(item.getKey(),item.getValue().size());
        }

        LinkedHashMap<String, Integer> linkedTelDir = new LinkedHashMap<>();
        telDirCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> linkedTelDir.put(e.getKey(), e.getValue()));

        System.out.println("Отсортированный справочник: ");
        for (Map.Entry<String,Integer> item: linkedTelDir.entrySet()) {
            System.out.println(item.getKey() + " = " + telDir.get(item.getKey()));
        }
    }
}
