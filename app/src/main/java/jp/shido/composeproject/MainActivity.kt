package jp.shido.composeproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.unaryPlus
import androidx.ui.core.CurrentTextStyleProvider
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.ExpandedHeight
import androidx.ui.layout.Spacing
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myApp {
                MyScreenContent()
            }
        }
    }
}


@Composable
fun myApp(child: @Composable() () -> Unit) {
    MaterialTheme {
        Surface(color = Color.Yellow) {
            child()
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(
            text = "Hello $name!",
            modifier = Spacing(24.dp),
            style = +themeTextStyle { h1 }
    )
}

@Preview
@Composable
fun DefaultPreview() {
   CustomTheme {
        myApp {
            MyScreenContent()
        }
    }
}

@Composable
fun MyScreenContent(appState: AppState = AppState()) {
    Column(modifier = ExpandedHeight, crossAxisAlignment = CrossAxisAlignment.Center) {
        Column(modifier = Flexible(1f), crossAxisAlignment = CrossAxisAlignment.Center) {
            Greeting(name = "Android")
            Divider(color = Color.Black)
            Greeting(name = "There")
        }
        Counter(state = appState.counterState)
        //Divider(color = Color.Transparent, height = 32.dp)
        // Form(formState = FormState(false))
    }
}

class AppState(val counterState: CounterState = CounterState())


@Model
class CounterState(var count: Int = 0)

@Composable
fun Counter(state: CounterState) {
    Button(text = "I've been clicked ${state.count} times", onClick = {
        state.count++
    })
}

@Model
class FormState(var optionChecked: Boolean)

@Composable
fun Form(formState: FormState) {
    Text(text = formState.optionChecked.toString())
    Checkbox(checked = formState.optionChecked, onCheckedChange = { newState ->
        formState.optionChecked = newState
    })
}


val customGreen = Color(0xFF1EB980.toInt())
val customSurface = Color(0xFF26282F.toInt())
private val themeColors = MaterialColors(
        primary = customGreen,
        surface = customSurface,
        onSurface = Color.Black
)

@Composable
fun CustomTheme(children: @Composable() () -> Unit) {
    MaterialTheme(colors = themeColors) {
        val textStyle = TextStyle(color = Color.Red)
        CurrentTextStyleProvider(value = textStyle) {
            children()
        }
    }
}