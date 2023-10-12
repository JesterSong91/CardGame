create or replace package body Card_Game is

  procedure cards_quantity(player_name IN VARCHAR2, quantity OUT NUMBER) is
  begin

    select count(p2c.card_id) into quantity from CG_PLAYER p
    join CG_PLAYER_TO_CARDS p2c on p2c.player_id = p.player_id
    where p.name = player_name;
  end cards_quantity;
  
  procedure player_avg_strength(player_name IN VARCHAR2, avg_strength OUT NUMBER) is
  begin

    select trunc(avg(c.strength)) into avg_strength from CG_PLAYER p
    join CG_PLAYER_TO_CARDS p2c on p2c.player_id = p.player_id
    join CG_CARD c on c.card_id = p2c.card_id
    where p.name = player_name
    group by p2c.player_id;
    
  end player_avg_strength;

end Card_Game;
