package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CG_CARD")
public class Card {

    private Long id;

    private int strength;

    private String card_name;

    public Card() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "strength")
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Column(name = "card_name")
    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }
}
