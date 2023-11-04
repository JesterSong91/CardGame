create or replace package body Card_Game is

  procedure all_cards_quantity(player_name IN VARCHAR2, quantity OUT NUMBER) is
  begin

    select count(p2c.card_id) into quantity from CG_PLAYER p
    join CG_PLAYER_TO_CARDS p2c on p2c.player_id = p.player_id
    where p.name = player_name;
    
  end all_cards_quantity;
  
  procedure available_cards_quantity(player_name IN VARCHAR2, quantity OUT NUMBER) is
  begin

    select count(p2c.card_id) into quantity from CG_PLAYER p
    join CG_PLAYER_TO_CARDS p2c on p2c.player_id = p.player_id
    where p.name = player_name
          and p2c.is_played = 0;
    
  end available_cards_quantity;
  
  procedure player_avg_strength(player_name IN VARCHAR2, avg_strength OUT NUMBER) is
  begin

    select trunc(avg(c.strength)) into avg_strength from CG_PLAYER p
    join CG_PLAYER_TO_CARDS p2c on p2c.player_id = p.player_id
    join CG_CARD c on c.card_id = p2c.card_id
    where p.name = player_name
    group by p2c.player_id;
    
  end player_avg_strength;
  
  procedure reset_played_cards is
  begin
    
    update CG_PLAYER_TO_CARDS
    set is_played = 0,
    is_in_deck = 0;
    
    commit;
  
  end reset_played_cards;
  
  procedure play_card(playing_card_id IN NUMBER) is
  begin
    
    update CG_PLAYER_TO_CARDS p2c
    set p2c.is_played = 1,
    p2c.is_in_deck = 1
    where p2c.player_to_cards_id = playing_card_id;
            
    commit;
    
  end play_card;
  
  procedure player_strength(player_name IN VARCHAR2, strength OUT NUMBER) is
  begin

    select sum(c.strength) into strength from CG_PLAYER p
    join CG_PLAYER_TO_CARDS p2c on p2c.player_id = p.player_id
    join CG_CARD c on c.card_id = p2c.card_id
    where p.name = player_name
          and p2c.is_played = 1;

  end player_strength;
  
  procedure mark_card_is_in_deck(playing_card_id IN NUMBER) is
  begin
  
    update CG_PLAYER_TO_CARDS p2c
    set p2c.is_in_deck = 1
    where p2c.player_to_cards_id = playing_card_id;
    
    commit;
  
  end mark_card_is_in_deck;
  
  procedure enemy_strength(player_name IN VARCHAR2, shield OUT NUMBER) is 
  begin
    
     select nvl(sum(c.strength),0) into shield from CG_PLAYER p
     join CG_PLAYER_TO_CARDS p2c on p2c.player_id = p.player_id
     join CG_CARD c on c.card_id = p2c.card_id
     where p.name = player_name
          and p2c.is_played = 0
          and p2c.is_in_deck = 1
          and c.card_name = 'Clocker';
       
  end enemy_strength;

end Card_Game;
