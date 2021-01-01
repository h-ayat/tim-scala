name := "tim"

lazy val core = project.settings(Core.settings: _*)
lazy val http = project.settings(Http.settings: _*)
lazy val shell = project.settings(Shell.settings: _*)
lazy val cli = project.settings(Cli.settings: _*)

lazy val root = (project in file("."))
  .settings(Common.commonSettings: _*)
  .aggregate(core, http, shell, cli)
