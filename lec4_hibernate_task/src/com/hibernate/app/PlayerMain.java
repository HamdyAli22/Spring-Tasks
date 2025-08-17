package com.hibernate.app;

import com.hibernate.app.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class PlayerMain {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Player.class);
        SessionFactory factory = configuration.buildSessionFactory();

        // ---------- 1. Save a Player ----------
        Session session1 = factory.getCurrentSession();
        Transaction tx1 =  session1.beginTransaction();

        Player player1 = new Player("Ali", 22, true);
        Player player2 = new Player("Ahmed", 18, false);
        Player player3 = new Player("Alaa", 24, true);
        Player player4 = new Player("Hossam", 15, false);
        session1.save(player1);
        session1.save(player2);
        session1.save(player3);
        session1.save(player4);
        tx1.commit();
        System.out.println("Saved multiple players.");

        // ---------- 2. Update the Player ----------
       Session session2 = factory.getCurrentSession();
        Transaction tx2 = session2.beginTransaction();

        Player existingPlayer = session2.get(Player.class, player1.getId());
        if (existingPlayer != null){
            existingPlayer.setName("Ali");
            existingPlayer.setAge(25);
            session2.update(existingPlayer);
            System.out.println("Player updated.");
        }else{
            System.out.println("Player not found for update.");
        }
        tx2.commit();

        // ---------- 3. Get Player by ID ----------
        Session session3 = factory.getCurrentSession();
        Transaction tx3 = session3.beginTransaction();

        Player fetchedPlayer = session3.get(Player.class, player1.getId());
        if (fetchedPlayer != null) {
            System.out.println("Fetched Player: " + fetchedPlayer);
        } else {
            System.out.println("Player not found.");
        }

        tx3.commit();



        // ---------- 4. Delete the Player ----------
        Session session4 = factory.getCurrentSession();
        Transaction tx4 = session4.beginTransaction();

        Player playerToDelete = session4.get(Player.class, player4.getId());
        if (playerToDelete != null) {
            session4.delete(playerToDelete);
            System.out.println("Player deleted.");
        } else {
            System.out.println("Player not found for deletion.");
        }
        tx4.commit();


        Session session5 = factory.getCurrentSession();
        Transaction tx5 = session5.beginTransaction();

        //get all student that name start with char a
        System.out.println("Players whose name starts with 'A':");
        List<Player> nameAList = session5.createQuery(
                        "FROM Player p WHERE LOWER(p.name) LIKE 'a%'", Player.class)
                .getResultList();
        nameAList.forEach(System.out::println);

        //get all student that age >= 20
        System.out.println("Players age >= 20:");
        List<Player> ageList = session5.createQuery("FROM Player p WHERE p.age >= 20",Player.class)
                        .getResultList();
        ageList.forEach(System.out::println);

        //get all student that status = true
        System.out.println("Players with status = true:");
        List<Player> statusList = session5.createQuery(
                        "FROM Player p WHERE p.status = true", Player.class)
                .getResultList();
        statusList.forEach(System.out::println);

        //get all student that id is even
        System.out.println("Players with even ID:");
        List<Player> evenIdList = session5.createQuery(
                        "FROM Player p WHERE MOD(p.id, 2) = 0", Player.class)
                .getResultList();
        evenIdList.forEach(System.out::println);

        tx5.commit();

        factory.close();
    }
}