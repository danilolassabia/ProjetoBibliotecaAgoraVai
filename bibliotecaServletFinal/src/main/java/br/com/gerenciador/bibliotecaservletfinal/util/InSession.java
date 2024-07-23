package br.com.gerenciador.bibliotecaservletfinal.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;

public class InSession {
    public static void inSession(Consumer<EntityManager> work) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bibliotecafinal");

        var entityManager = factory.createEntityManager();
        var transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            work.accept(entityManager);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;

        } finally {
            entityManager.close();
        }
    }



}