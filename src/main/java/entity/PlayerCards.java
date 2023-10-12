package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CG_PLAYER_TO_CARDS")
public class PlayerCards {
    private Long id;
    private int player_id;
    private int card_id;
    private int is_played;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "player_to_cards_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "player_id")
    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    @Column(name = "card_id")
    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    @Column(name = "is_played")
    public int getIs_played() {
        return is_played;
    }

    public void setIs_played(int is_player) {
        this.is_played = is_player;
    }
}
