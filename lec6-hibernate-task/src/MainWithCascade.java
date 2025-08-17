import com.hibernate.app.model.Friends;
import com.hibernate.app.model.Post;
import com.hibernate.app.model.User;
import com.hibernate.app.model.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainWithCascade {
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

        //create user
        User user = new User();
        user.setName("Ali");
        user.setAge(27);

        //create user detsils
        UserDetails details = new UserDetails();
        details.setAddress("Alex");
        details.setPhone("0123456789");
        user.setUserDetails(details);

        //create friends
        Friends friend1 = new Friends();
        friend1.setName("Hossam");

        Friends friend2 = new Friends();
        friend2.setName("Sara");

        user.getFriends().add(friend1);
        user.getFriends().add(friend2);

       //create posts
        Post post1 = new Post();
        post1.setHeader("Hibernate");
        post1.setContent("Cascade persist()");
        post1.setUser(user);

        Post post2 = new Post();
        post2.setHeader("JPA");
        post2.setContent("With Oracle");
        post2.setUser(user);

        user.getPosts().add(post1);
        user.getPosts().add(post2);

        session.persist(user); // Cascade will persist details, friends, posts automatically
        Long userId = user.getId();
        transaction.commit();
        session.close();

        //using featch lazy and featch EAGER between user and user details and get data
        Session session1 = factory.getCurrentSession();
        Transaction transaction1 =  session1.beginTransaction();
        User user1 = session1.get(User.class, userId);
        System.out.println("User Name: " + user1.getName());
        System.out.println("Address: " + user1.getUserDetails().getAddress());

        session1.close();

        factory.close();
    }
}
