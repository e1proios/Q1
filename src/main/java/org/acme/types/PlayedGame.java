package org.acme.types;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record PlayedGame(
  String name,
  GamePlatform platform,
  int released,
  int rating
) {
  public static PlayedGame create(
    String name,
    GamePlatform platform,
    int released,
    int rating
  ) {
    return new PlayedGame(name, platform, released, rating);
  }
}
