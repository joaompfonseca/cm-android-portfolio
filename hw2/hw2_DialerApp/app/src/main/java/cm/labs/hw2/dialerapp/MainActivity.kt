package cm.labs.hw2.dialerapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cm.labs.hw2.dialerapp.ui.theme.DialerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DialerAppLayout()
                }
            }
        }
    }
}

@Composable
fun DialerAppLayout() {
    var number by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = number,
            modifier = Modifier
                .size(50.dp)
                .wrapContentWidth()
            )
        Spacer(modifier = Modifier.height(100.dp))
        Row() {
            DialerButton(value = "1", onClick = { number = addDigit(number, "1") })
            DialerButton(value = "2", onClick = { number = addDigit(number, "2") })
            DialerButton(value = "3", onClick = { number = addDigit(number, "3") })
        }
        Row() {
            DialerButton(value = "4", onClick = { number = addDigit(number, "4") })
            DialerButton(value = "5", onClick = { number = addDigit(number, "5") })
            DialerButton(value = "6", onClick = { number = addDigit(number, "6") })
        }
        Row() {
            DialerButton(value = "7", onClick = { number = addDigit(number, "7") })
            DialerButton(value = "8", onClick = { number = addDigit(number, "8") })
            DialerButton(value = "9", onClick = { number = addDigit(number, "9") })
        }
        Row() {
            DialerButton(value = "\uD83D\uDCDE", onClick = { dial(number, context) })
            DialerButton(value = "0", onClick = { number = addDigit(number, "0") })
            DialerButton(value = "⌫", onClick = { number = removeDigit(number) })
        }
    }
}

@Composable
fun DialerButton(value: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(4.dp)
    ) {
        Text(value)
    }
}

private fun addDigit(number: String, digit: String): String {
    return "$number$digit"
}

private fun removeDigit(number: String): String {
    return if (number.isNotEmpty()) number.substring(0, number.length - 1) else number
}

private fun dial(number: String, ctx: Context) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$number")
    }
    ctx.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DialerAppTheme {
        DialerAppLayout()
    }
}