import com.hibernate.app.model.Friends;
import com.hibernate.app.model.Post;
import com.hibernate.app.model.User;
import com.hibernate.app.model.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainNoCascade {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Friends.class)
                .addAnnotatedClass(Post.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(UserDetails.class);
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction =  session.beginTransaction();

        //Save UserDetails
        UserDetails details = new UserDetails();
        details.setAddress("Cairo");
        details.setPhone("01012222222");
        session.save(details);

        //Create user and assign details
        User user = new User();
        user.setName("Ahmed");
        user.setAge(20);
        user.setUserDetails(details);
        session.save(user);

        // Create posts and assign user
        Post post1 = new Post();
        post1.setHeader("Hibernate Tips");
        post1.setContent("How to avoid Cascade");
        post1.setUser(user);

        Post post2 = new Post();
        post2.setHeader("Spring Boot");
        post2.setContent("Integration with Hibernate");
        post2.setUser(user);

        session.save(post1);
        session.save(post2);

        //Create friends and save
        Friends friend1 = new Friends();
        friend1.setName("Ali");

        Friends friend2 = new Friends();
        friend2.setName("Mohamed");

        session.save(friend1);
        session.save(friend2);

        //Assign friends to user and update user
        user.getFriends().add(friend1);
        user.getFriends().add(friend2);

        session.update(user);

        transaction.commit();
        session.close();
        factory.close();
    }
}