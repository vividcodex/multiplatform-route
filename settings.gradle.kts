rootProject.name = "vividcode-multiplatform-route"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	repositories {
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
		google()
		gradlePluginPortal()
		mavenCentral()
	}
}
plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		google()
		mavenCentral()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}
include("route-api")
include("route-ksp")
