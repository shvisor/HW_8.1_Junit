import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordCheckerTest {
    PasswordChecker checker = new PasswordChecker();

    @Test
    @DisplayName("P: Минимальная длина пароля равна 0")
    public void minLengthIsZero() {
        checker.setMinLength("0");
        int expected = 0;
        int actual = checker.getMinLength();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("P: Минимальная длина пароля больше 0")
    public void minLengthAboveZero() {
        checker.setMinLength("7");
        int expected = 7;
        int actual = checker.getMinLength();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("N: Минимальная длина пароля меньше 0")
    public void minLengthExceptionBelowZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checker.setMinLength("-3");
        });
    }

    @Test
    @DisplayName("N: Минимальная длина пароля принимает пустое значение")
    public void minLengthExceptionIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checker.setMinLength("");
        });
    }

    @Test
    @DisplayName("P: Максимальное количество повторов больше 0")
    public void maxRepeatsAboveZero() {
        checker.setMaxRepeats("5");
        int expected = 5;
        int actual = checker.getMaxRepeats();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("N: Максимальное количество повторов равно 0")
    public void maxRepeatsExceptionIsZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checker.setMaxRepeats("0");
        });
    }

    @Test
    @DisplayName("N: Максимальное количество повторов меньше 0")
    public void maxRepeatsExceptionBelowZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checker.setMaxRepeats("-1");
        });
    }

    @Test
    @DisplayName("N: Максимальное количество повторов принимает пустое значение")
    public void maxRepeatsExceptionIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checker.setMaxRepeats("");
        });
    }

    @Test
    @DisplayName("P: Длина пароля равна минимальной")
    public void passIsMinLength() {
        checker.setMinLength("4");
        checker.setMaxRepeats("2");
        boolean actual = checker.verify("pass");
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("P: Длина пароля больше минимальной")
    public void passAboveMinLength() {
        checker.setMinLength("5");
        checker.setMaxRepeats("2");
        boolean actual = checker.verify("password");
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("N: Длина пароля меньше минимальной")
    public void passBelowMinLength() {
        checker.setMinLength("5");
        checker.setMaxRepeats("2");
        boolean actual = checker.verify("pass");
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("N: Не задана минимальная длина пароля")
    public void passLengthIsNotSet() {
        checker.setMaxRepeats("2");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            checker.verify("password");
        });
    }

    @Test
    @DisplayName("P: Повторы равны максимальному количеству")
    public void repeatIsMaxRepeat() {
        checker.setMinLength("5");
        checker.setMaxRepeats("2");
        boolean actual = checker.verify("password");
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("P: Повторов меньше максимального количества")
    public void repeatBelowMaxRepeat() {
        checker.setMinLength("5");
        checker.setMaxRepeats("3");
        boolean actual = checker.verify("password");
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("N: Повторов больше максимального количества")
    public void repeatAboveMaxRepeat() {
        checker.setMinLength("5");
        checker.setMaxRepeats("2");
        boolean actual = checker.verify("password111");
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("N: Не задано максимальное количество повторов")
    public void maxRepeatIsNotSet() {
        checker.setMinLength("5");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            checker.verify("password");
        });
    }
}
