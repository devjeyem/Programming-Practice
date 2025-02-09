import java.security.SecureRandom;

public enum Job {
    ENGINEER, TEACHER, DOCTOR, LAWYER, ARTIST;

    public static Job getJob() {
        SecureRandom sr = new SecureRandom();
        return values()[sr.nextInt(values().length)];
    }

    public static double getSalary(Job job) {
        SecureRandom sr = new SecureRandom();
        switch (job) {
            case ENGINEER:
                return 4000 + sr.nextInt(2000); // Salary range: 4000-6000
            case TEACHER:
                return 3000 + sr.nextInt(1000); // Salary range: 3000-4000
            case DOCTOR:
                return 5000 + sr.nextInt(3000); // Salary range: 5000-8000
            case LAWYER:
                return 4500 + sr.nextInt(2500); // Salary range: 4500-7000
            case ARTIST:
                return 2500 + sr.nextInt(1500); // Salary range: 2500-4000
            default:
                return 3000; // Default salary
        }
    }

}
