package ai.softeer.caecae.user.domain.entity;


import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private int phone;
}
