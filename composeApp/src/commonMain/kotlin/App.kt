import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sixbynine.transit.hblr.ui.ProvideViewModelStoreOwner
import com.sixbynine.transit.hblr.ui.schedule.ScheduleScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ProvideViewModelStoreOwner {
        val scheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
        BoxWithConstraints(Modifier.fillMaxSize().background(Color.Black)) {
            MaterialTheme(colorScheme = scheme) {
                ScheduleScreen()
            }
        }
    }
}