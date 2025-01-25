package com.example.littlelemmon2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemmon2.ui.theme.LittleLemonColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//@Preview(showBackground = true)
@Composable
fun Home(navController: NavHostController, databaseMenuItems: List<MenuItemRoom>) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    keyboardController?.hide()
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(30)
                        focusManager.clearFocus()
                    }
                })
            }
    ) {
        var searchPhrase by remember { mutableStateOf("") }
        var buttonState = remember { mutableStateOf(ButtonState.OFF) }
        var filteredItems: List<MenuItemRoom> = emptyList()

        Row(
            modifier = Modifier
                .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .size(width = 200.dp, height = 40.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(end = 30.dp))
            IconButton(
                onClick = {
                    navController.navigate(Profile.route)
                },
                modifier = Modifier
                    .size(width = 60.dp, height = 60.dp)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile",
                    )
            }
            Spacer(modifier = Modifier.padding(end = 20.dp))
        }
        Column (
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth()
                .background(color = LittleLemonColor.green)
        ) {
            Text(
                text = "Little Lemon",
                modifier = Modifier.padding(start = 15.dp),
                color = LittleLemonColor.yellow,
                fontSize = 64.sp,
                fontFamily = FontFamily(Font(R.font.markazitext_variablefont_wght, FontWeight.Medium))
            )
            Row(
                modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "Chicago",
                        color = LittleLemonColor.cloud,
                        fontSize = 40.sp,
                        fontFamily = FontFamily(Font(R.font.markazitext_variablefont_wght, FontWeight.Normal))
                    )
                    Text(
                        text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                        modifier = Modifier.width(220.dp).padding(top = 5.dp),
                        color = LittleLemonColor.cloud,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.karla_variablefont_wght, FontWeight.Medium))
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "hero image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(width = 140.dp, height = 160.dp),
                )
            }
            OutlinedTextField(
                value = searchPhrase,
                onValueChange = {searchPhrase = it},
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 10.dp)
                    .fillMaxWidth(),
                placeholder = { Text("Enter Search Phrase") },
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LittleLemonColor.cloud,
                    unfocusedContainerColor = LittleLemonColor.cloud
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ORDER FOR DELIVERY",
                color = LittleLemonColor.charcoal,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.karla_variablefont_wght, FontWeight.ExtraBold))
            )
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CategoryButtons(buttonState)

                filteredItems = when(buttonState.value) {
                    ButtonState.STARTERS -> databaseMenuItems.filter { it.category.equals("starters") }
                    ButtonState.MAINS -> databaseMenuItems.filter { it.category.equals("mains") }
                    ButtonState.DESSERTS -> databaseMenuItems.filter { it.category.equals("desserts") }
                    ButtonState.DRINKS -> databaseMenuItems.filter { it.category.equals("drinks") }
                    else -> databaseMenuItems
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 15.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        if (searchPhrase.isNotEmpty()) {
            filteredItems = filteredItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        }
        MenuItems(filteredItems)
    }
}

//@Preview
@Composable
fun CategoryButtons(buttonState: MutableState<Int>) {
    val categories = listOf("starters", "mains", "desserts", "drinks")

    for (category in categories) {
        CategoryButton(category, buttonState)
    }
}

//@Preview
@Composable
fun CategoryButton(category: String, buttonState: MutableState<Int>) {
    Button(
        onClick = {
            when(category) {
                "starters" -> changeState(buttonState, ButtonState.STARTERS)
                "mains" -> changeState(buttonState, ButtonState.MAINS)
                "desserts" -> changeState(buttonState, ButtonState.DESSERTS)
                "drinks" -> changeState(buttonState, ButtonState.DRINKS)
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.cloud),
        contentPadding = PaddingValues(10.dp)
    ) {
        Text(
            text = category,
            color = LittleLemonColor.charcoal,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.karla_variablefont_wght, FontWeight.ExtraBold))
        )
    }
}

fun changeState(buttonState: MutableState<Int>, targetState: Int) {
    if (buttonState.value == targetState) {
        buttonState.value = ButtonState.OFF
    } else {
        buttonState.value = targetState
    }
}


//@Preview(showBackground = true)
@Composable
fun MenuItems(menuItems: List<MenuItemRoom>) {
    LazyColumn (
        modifier = Modifier.padding(bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding())
    ) {
        items(
            items = menuItems,
            itemContent = { menu -> MenuItem(menu.title, menu.description, menu.price, menu.image) }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(title: String, description: String, price: String, image: String) {
    Row(
        modifier = Modifier.padding(horizontal = 15.dp).height(140.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.width(230.dp),
        ) {
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(
                text = title,
                color = LittleLemonColor.charcoal,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.karla_variablefont_wght, FontWeight.Bold))
            )
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(
                text = description,
                color = LittleLemonColor.charcoal,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.karla_variablefont_wght, FontWeight.Normal)),
                minLines = 4
            )
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(
                text = "$%.2f".format(price.toDouble()),
                color = LittleLemonColor.charcoal,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.karla_variablefont_wght, FontWeight.Medium)),
            )
        }
        GlideImage(
            model = convertUrl(image),
            contentDescription = title,
            modifier = Modifier.padding(top = 20.dp).size(90.dp),
            contentScale = ContentScale.Crop,
        )
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 15.dp),
        thickness = 1.dp,
        color = Color.LightGray
    )
}

fun convertUrl(githubUrl: String): String {
    val convertedUrl = if (githubUrl.contains("github.com")) {
        githubUrl.replace("github.com", "raw.githubusercontent.com").replace("/blob", "")
    } else {
        githubUrl
    }

    return convertedUrl
}

object ButtonState {
    const val OFF = 0
    const val STARTERS = 1
    const val MAINS = 2
    const val DESSERTS = 3
    const val DRINKS = 4
}
