package ua.lviv.lgs.Application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ua.lviv.lgs.Domain.Comment;
import ua.lviv.lgs.Domain.Post;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Application {

    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Post post = new Post();
        post.setTitle("New post");

        Comment comment1 = new Comment();
        comment1.setAuthorName("testAuthorName");
        comment1.setPost(post);
        Comment comment2 = new Comment();
        comment2.setAuthorName("qwerty");
        comment2.setPost(post);
        Comment comment3 = new Comment();
        comment3.setAuthorName("authorName");
        comment3.setPost(post);

        Set<Comment> comments = new HashSet<>(Arrays.asList(comment1,comment2,comment3));

        post.setComments(comments);
        System.out.println(comment2.getId());

        session.persist(post);
        transaction.commit();


        Post postDB = (Post) session.get(Post.class, 1);
        System.out.println(postDB + "---->" + postDB.getComments());

        Comment commentDB = (Comment) session.get(Comment.class, 2);
        System.out.println(commentDB + "---->" + commentDB.getPost());


        session.close();
    }


}
