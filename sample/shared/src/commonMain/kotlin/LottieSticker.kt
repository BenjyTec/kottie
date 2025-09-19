import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kottie.sample.shared.generated.resources.Res
import kottieComposition.KottieCompositionSpec
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition
import utils.KottieConstants

@Composable
fun LottieSticker() {

    var lottieFileString by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        lottieFileString = Res.readBytes(
            path = "files/cow.json"
        ).decodeToString()
    }
    val lottieResource = rememberKottieComposition(
        spec = KottieCompositionSpec.File(lottieFileString)
    )
    val lottieAnimationState by animateKottieCompositionAsState(
        composition = lottieResource,
        iterations = KottieConstants.IterateForever
    )

    KottieAnimation(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        progress = { lottieAnimationState.progress },
        composition = lottieResource,
        // iterations = LottieConstants.IterateForever
    )
}