package quark.quark;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

import quark.quark.types.PlayedGame;

@Path("/api/reviews")
public class ReviewsController {

  private Set<PlayedGame> loggedGames = Collections.synchronizedSet(new HashSet<>());

  @Inject
  ReviewsService src;

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response gimmeAll() {
    var reviews = this.src.all();

    reviews.sort(
      Comparator.comparing(PlayedGame::rating)
        .reversed()
        .thenComparing(PlayedGame::name)
    );

    return Response.ok(reviews).build();
  }

  @GET
  @Path("/search/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response gimmeFound(
    @QueryParam("field") String fieldName,
    @QueryParam("search") String search
  ) {
    List<PlayedGame> reviews;

    if (fieldName == null || search == null) {
      reviews = this.src.all();
    } else {
      reviews = this.src.search(fieldName, search);
    }

    if (reviews.size() > 0) {
      return Response.ok(reviews).build();
    }
    return Response.noContent().build();
  }

  // POST and DELETE don't connect to the database
  // they still operate with dummy data
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response take(PlayedGame game) {
    var added = loggedGames.add(game);
    var res = added ? Response.ok(this.getSortedGameList()) : Response.notModified();

    return res.build();
  }

  @DELETE
  @Path("/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response dontWant(@PathParam("name") String name) {
    var removed = loggedGames.removeIf(g -> g.name().equals(name));
    var res = removed ? Response.ok(this.getSortedGameList()) : Response.noContent();

    return res.build();
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
}
