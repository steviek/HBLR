import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sixbynine.transit.hblr.ui.ProvideViewModelStoreOwner
import com.sixbynine.transit.hblr.ui.schedule.ScheduleScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ProvideViewModelStoreOwner {
        val scheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
        MaterialTheme(colorScheme = scheme) {
            ScheduleScreen()
        }
    }
}