object Compose {
    private const val activityComposeVersion = "1.4.0"
    const val activity = "androidx.activity:activity-compose:$activityComposeVersion"
    const val compilerVersion = "1.1.0-rc02"
//    const val compiler = "androidx.compose.compiler:compiler:1.1.0-rc02"

    const val composeVersion = "1.0.5"
    const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val icons = "androidx.compose.material:material-icons-core:$composeVersion"
    const val iconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
    const val liveData = "androidx.compose.runtime:runtime-livedata:$composeVersion"
    const val rxJava = "androidx.compose.runtime:runtime-rxjava2:$composeVersion"

    private const val navigationVersion = "2.5.0-alpha01"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"
}

object ComposeTest {
    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Compose.composeVersion}"
}