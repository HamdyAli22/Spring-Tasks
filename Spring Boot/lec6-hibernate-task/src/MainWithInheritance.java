import com.hibernate.app.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainWithInheritance {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(AdminUser.class)
                .addAnnotatedClass(UserDetails.class)
                .addAnnotatedClass(Post.class)
                .addAnnotatedClass(Friends.class);
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction =  session.beginTransaction();

        //save user with its details
        User user = new User();
        user.setName("Ahmed");
        user.setAge(20);

        UserDetails userDetails = new UserDetails();
        userDetails.setAddress("Alex");
        userDetails.setPhone("02222222222");

        user.setUserDetails(userDetails);

        session.persist(user);

        //save admin with its details
        AdminUser admin = new AdminUser();
        admin.setName("Mona");
        admin.setAge(40);
        admin.setRole("SUPER_ADMIN");
        admin.setPermissions("ALL");

        UserDetails adminDetails = new UserDetails();
        adminDetails.setAddress("Cairo");
        adminDetails.setPhone("01111111111");

        admin.setUserDetails(adminDetails);
        session.persist(admin);

        transaction.commit();

        session.close();
        factory.close();
    }
}
