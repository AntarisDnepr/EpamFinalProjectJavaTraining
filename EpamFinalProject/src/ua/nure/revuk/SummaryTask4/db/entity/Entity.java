package ua.nure.revuk.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 02.02.2016
 */
public class Entity implements Serializable {

    private static final long serialVersionUID = 4428191584754818572L;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
