package quark.quark.types;

public enum GamePlatform {
  SEGA_MD("Sega Mega Drive / Genesis"),
  PC_WIN("Microsoft Windows"),
  PC_DOS("Microsoft DOS"),
  GB_ADV("Game Boy Advance"),
  PC_ENGINE("PC Engine / TurboGrafx-16");

  private final String platform;

  GamePlatform(String platform) {
    this.platform = platform;
  }

  public String platform() {
    return this.platform;
  }
}
