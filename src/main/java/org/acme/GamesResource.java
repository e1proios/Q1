package org.acme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;

import jakarta.ws.rs.Path;

import org.acme.types.PlayedGame;
import org.acme.types.GamePlatform;

@Path("/games")
public class GamesResource {

  private Set<PlayedGame> loggedGames = new HashSet<>();

  {
    this.loggedGames.add(PlayedGame.create("Alien Soldier", GamePlatform.SEGA_MD, 1995, 9));
    this.loggedGames.add(PlayedGame.create("Cave Story+", GamePlatform.PC_WIN, 2017, 9));
    this.loggedGames.add(PlayedGame.create("Diablo 2", GamePlatform.PC_WIN, 2000, 8));
    this.loggedGames.add(PlayedGame.create("DooM II: Hell on Earth", GamePlatform.PC_DOS, 1994, 10));
    this.loggedGames.add(PlayedGame.create("Gorky 17", GamePlatform.PC_WIN, 1999, 8));
    this.loggedGames.add(PlayedGame.create("HROT", GamePlatform.PC_WIN, 2023, 9));
    this.loggedGames.add(PlayedGame.create("Kururin Paradise", GamePlatform.GB_ADV, 2002, 8));
    this.loggedGames.add(PlayedGame.create("SYNTHETIK", GamePlatform.PC_WIN, 2018, 9));
    this.loggedGames.add(PlayedGame.create("The Legendary Axe II", GamePlatform.PC_ENGINE, 1990, 7));
    this.loggedGames.add(PlayedGame.create("The Lost Vikings", GamePlatform.PC_DOS, 1993, 9));
  }

  private Set<PlayedGame> safeCopy() {
    return Collections.synchronizedSet(loggedGames);
  }

  @GET
  public List<PlayedGame> gimme() {
    List<PlayedGame> ret = new ArrayList<>(this.safeCopy());

    ret.sort(
      Comparator.comparing(PlayedGame::rating)
        .reversed()
        .thenComparing(PlayedGame::name)
    );
    return ret;
  }

  @POST
  public Set<PlayedGame> take(PlayedGame game) {
    loggedGames.add(game);
    return this.safeCopy();
  }

  @DELETE
  public Set<PlayedGame> dontWant(String name) {
    loggedGames.removeIf(g -> g.name().equals(name));
    return safeCopy();
  }
}
