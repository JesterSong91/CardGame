create or replace package Card_Game is

   procedure all_cards_quantity(player_name IN VARCHAR2, quantity OUT NUMBER);
   procedure available_cards_quantity(player_name IN VARCHAR2, quantity OUT NUMBER);
   procedure player_avg_strength(player_name IN VARCHAR2, avg_strength OUT NUMBER);
   procedure reset_played_cards;
   procedure play_card(playing_card_id IN NUMBER);

end Card_Game;
