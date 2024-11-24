import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class prac {
    public static void main(String[] args) {
//        int x = 5;
//        int y = 10;
//
//        String result = (x > y) ? "greater" : "lesser";
//        System.out.println(result);

        PasswordEncoder e = new BCryptPasswordEncoder();
        System.out.println(e.encode("testing"));


    }
}