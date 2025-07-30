package quark.quark;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quark.quark.types.GamePlatform;
import quark.quark.types.PlayedGame;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/api/games")
public class GamesResource {

  private Set<PlayedGame> loggedGames = Collections.synchronizedSet(new HashSet<>());

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

  @GET
  public Response gimmeAll() {
    return Response.ok(this.getSortedGameList()).build();
  }

  @GET
  @Path("/{search}")
  public Response gimmeFound(@PathParam("search") String search) {
    Pattern nameRgx = Pattern.compile(search);

    List<PlayedGame> data = this.getSortedGameList().stream()
      .filter(game -> nameRgx.matcher(game.name()).find())
      .collect(Collectors.toList());

    if (data.size() > 0) {
      return Response.ok(data).build();
    }
    return Response.noContent().build();
  }

  private List<PlayedGame> getSortedGameList() {
    List<PlayedGame> list = new ArrayList<>(loggedGames);

    list.sort(
      Comparator.comparing(PlayedGame::rating)
        .reversed()
        .thenComparing(PlayedGame::name)
    );

    return list;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response take(PlayedGame game) {
    var added = loggedGames.add(game);
    var res = added ? Response.ok(this.getSortedGameList()) : Response.notModified();

    return res.build();
  }

  @DELETE
  @Path("/{name}")
  public Response dontWant(@PathParam("name") String name) {
    var removed = loggedGames.removeIf(g -> g.name().equals(name));
    var res = removed ? Response.ok(this.getSortedGameList()) : Response.noContent();

    return res.build();
  }
}
