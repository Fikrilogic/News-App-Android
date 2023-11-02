import com.android.build.api.dsl.BuildType
import extension.FRAMEWORK
import extension.MODEL
import extension.PROVIDER
import extension.addNetworkDependency
import java.util.Properties

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
}

android {
    namespace = "com.fikrisandi.repository"
    buildTypes{
        debug {
            val properties = Properties()
                .apply {
                    load(project.rootProject.file("local.properties").reader())
                }

            buildConfigStringField("API_KEY", properties.getProperty("API_KEY"))
        }
    }
}

fun BuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}

dependencies {
    PROVIDER

    FRAMEWORK
    MODEL

    addNetworkDependency()
}