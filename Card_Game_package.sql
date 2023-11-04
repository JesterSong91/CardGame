create or replace package Card_Game is

   procedure all_cards_quantity(player_name IN VARCHAR2, quantity OUT NUMBER);
   procedure available_cards_quantity(player_name IN VARCHAR2, quantity OUT NUMBER);
   procedure player_avg_strength(player_name IN VARCHAR2, avg_strength OUT NUMBER);
   procedure reset_played_cards;
   procedure play_card(playing_card_id IN NUMBER);
   procedure player_strength(player_name IN VARCHAR2, strength OUT NUMBER);
   procedure mark_card_is_in_deck(playing_card_id IN NUMBER);
   procedure enemy_strength(player_name IN VARCHAR2, shield OUT NUMBER);

end Card_Game;
