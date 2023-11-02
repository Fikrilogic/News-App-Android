import extension.COMPONENT
import extension.DOMAIN
import extension.FRAMEWORK
import extension.MODEL
import extension.NEWS
import extension.PROVIDER
import extension.THEME
import extension.addDestinationDependency
import extension.addHiltDependency

plugins {
    id("commons.android-feature")
    id("commons.android-compose")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.fikrisandi.home"
}

ksp{
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "home")
}

dependencies {
    THEME
    PROVIDER
    COMPONENT

    FRAMEWORK

    MODEL
    DOMAIN

    NEWS

    addHiltDependency()
    addDestinationDependency()
}