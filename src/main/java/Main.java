import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PasswordChecker checker = new PasswordChecker();
        Scanner scan = new Scanner(System.in);

        System.out.print("\nВведите мин. длину пароля: ");
        checker.setMinLength(scan.nextLine());
        System.out.print("\nВведите макс. допустимое количество повторений символа подряд: ");
        checker.setMaxRepeats(scan.nextLine());

        while (true) {
            System.out.println("Введите пароль или end: ");
            String input = scan.nextLine();
            if (input.equals("end")) {
                break;
            } else {
                System.out.println(checker.verify(input) ? "Подходит!" : "Не подходит!");
            }
        }
        scan.close();
    }
}
