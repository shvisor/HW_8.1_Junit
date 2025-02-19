public class PasswordChecker {
    private int minLength = -1;
    private int maxRepeats = -1;

    public void setMinLength(String minLength) {
        if (minLength.isEmpty() || Integer.parseInt(minLength) < 0) {
            throw new IllegalArgumentException();
        }
        this.minLength = Integer.parseInt(minLength);
    }

    public void setMaxRepeats(String maxRepeats) {
        if (maxRepeats.isEmpty() || Integer.parseInt(maxRepeats) <= 0) {
            throw new IllegalArgumentException();
        }
        this.maxRepeats = Integer.parseInt(maxRepeats);
    }

    public boolean verify(String password) {
        if (minLength < 0 || maxRepeats < 0) {
            throw new IllegalStateException();
        }

        String[] chars = password.split("");
        int count = 1;

        if (password.length() < minLength) {
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            if (chars[i].equals(chars[i - 1])) {
                count++;
                if (count > maxRepeats) {
                    return false;
                }
            } else {
                count = 1;
            }
        }
        return count <= maxRepeats;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxRepeats() {
        return maxRepeats;
    }
}
