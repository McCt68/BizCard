package eu.example.bizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.example.bizcard.ui.theme.BizCardTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Introduction video with simple Compose layouts, showing how they are nested and used
// Scrollable list with LazyColumn

// Here are lots of examples for using Composable functions, modifiers, and parameters.
// Also Example of button and scrollable list with LazyColumn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CreateBizCard()

                }
            }
        }
    }
}

@Composable
fun CreateBizCard(){

    // see state of button if its clicked or not
    // remebers if the button is clicked or not clicked
    // using this inside of onClick
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 4.dp,
        ) {
            Column(modifier = Modifier
                .height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider()
                CreateInfo()
                Button(modifier = Modifier
                    .padding(),
                    onClick = {
                        // Log.d("Clicked", "CreateBizCard: Clicked by McCt")

                        // revert the value of buttonClickedstate to the oposide value,
                        // when we click it
                        buttonClickedState.value = !buttonClickedState.value

                    }) {
                    Text(
                        text = "Vis Job Profiler",
                        style = MaterialTheme.typography.button)
                }
                // run Content if button is true, else show an empty Box
                if (buttonClickedState.value) {
                    Content()
                } else {
                    Box() {

                    }
                }

            }
        }
    }
}

@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
    ){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(2.dp, color = Color.LightGray)
        ) {
            JobProfiles(data = listOf(
                "Podning",
                "Klargøring",
                "Aflevering",
                "Rengøring",
                "Prøver"))

        }
    }

}

// Scrollable list with LazyColumn
// Similar to recyclerView in XML
// Passing data as a list of strings
// Kinda iterating over each item in the list we provide as argument -
// when calling the function
// Each item in the list is given properties in LazyListScope
@Composable
fun JobProfiles(data: List<String>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data) {
            item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(7.dp)) {
                    CreateImageProfile(modifier = Modifier
                        .size(100.dp))
                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(text = "Certificeret i",
                            fontWeight = FontWeight.Bold)
                        Text(text = item,
                            style = MaterialTheme.typography.body2)


                    }

                }
            }
        }
    }

}

@Composable
private fun CreateInfo() {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Text(
            fontSize = 24.sp,
            text = "Michael Christensen.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant,
        )
        Text(
            modifier = Modifier
                .padding(3.dp),
            textAlign = TextAlign.Center,
            text = "Proces Operatør."
        )
        Text(
            modifier = Modifier
                .padding(3.dp),
            textAlign = TextAlign.Center,
            text = "xxxx@novozymes.com",
            style = MaterialTheme.typography.subtitle1
        )

    }
}

// Takes 1 parameter modifier, which is set to default Modifier
// So I can pass a modifier when i want to customize the Image
@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            modifier = modifier
                .size(130.dp),
            painter = painterResource(id = R.drawable.michael_thailand),
            contentDescription = "profile image",
            contentScale = ContentScale.Crop
        )
    }
}



// @Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardTheme {
        CreateBizCard()

    }
}