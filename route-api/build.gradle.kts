import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.compose.compiler)
	alias(libs.plugins.jetbrains.compose)
	alias(libs.plugins.serialization)
	alias(libs.plugins.android.library)
	alias(libs.plugins.maven.publish)
}

val routeVersion = property("route.version")!!.toString()

group = "cn.vividcode.multiplatform.route.api"
version = routeVersion

kotlin {
	jvmToolchain(21)
	
	androidTarget {
		@OptIn(ExperimentalKotlinGradlePluginApi::class)
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_21)
		}
	}
	jvm("desktop") {
		@OptIn(ExperimentalKotlinGradlePluginApi::class)
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_21)
		}
	}
	listOf(
		iosX64(),
		iosArm64(),
		iosSimulatorArm64()
	).forEach { iosTarget ->
		iosTarget.binaries.framework {
			baseName = "VividCodeRouteApi"
			isStatic = true
		}
	}
	sourceSets {
		androidMain.dependencies {
			implementation(libs.activity.compose)
		}
		commonMain.dependencies {
			implementation(compose.runtime)
			implementation(compose.foundation)
			implementation(compose.material3)
			implementation(compose.ui)
		}
		desktopMain.dependencies {
			implementation(compose.desktop.currentOs)
			api(libs.kotlinx.coroutines.swing)
		}
	}
}

val NamedDomainObjectContainer<KotlinSourceSet>.desktopMain: KotlinSourceSet
	get() = this.getByName("desktopMain")

android {
	namespace = "cn.vividcode.multiplatform.route.api"
	compileSdk = 34
	
	sourceSets["main"].apply {
		manifest.srcFile("src/androidMain/AndroidManifest.xml")
		res.srcDirs("src/androidMain/res")
		resources.srcDirs("src/commonMain/resources")
	}
	
	defaultConfig {
		minSdk = 24
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
			merges += "/META-INF/DEPENDENCIES"
		}
	}
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}
	composeOptions {
		kotlinCompilerExtensionVersion = property("compose.kotlinCompilerVersion").toString()
	}
}

mavenPublishing {
	publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
	signAllPublications()
	
	coordinates("cn.vividcode.multiplatform", "route-api", routeVersion)
	
	pom {
		name.set("route-api")
		description.set("这是一个 Kotlin Multiplatform 的页面路由框架")
		inceptionYear.set("2024")
		url.set("https://github.com/vividcodex/multiplatform-route")
		licenses {
			license {
				name.set("The Apache License, Version 2.0")
				url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
				distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
			}
		}
		developers {
			developer {
				id.set("li-jia-wei")
				name.set("li-jia-wei")
				url.set("https://github.com/vividcodex/multiplatform-route")
			}
		}
		
		scm {
			url.set("https://github.com/vividcodex/multiplatform-route")
			connection.set("scm:git:git://github.com/vividcodex/multiplatform-route.git")
			developerConnection.set("scm:git:ssh://git@github.com:vividcodex/multiplatform-route.git")
		}
	}
}