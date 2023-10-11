package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CG_PLAYER")
public class Player {
    private Long id;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
