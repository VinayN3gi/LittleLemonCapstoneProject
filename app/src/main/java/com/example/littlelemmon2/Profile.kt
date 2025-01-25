package com.example.littlelemmon2

import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemmon2.ui.theme.LittleLemonColor


//@Preview(showBackground = true)
@Composable
fun Profile(navController: NavHostController, sharedPreferences: SharedPreferences) {
    Column() {
        Row(
            modifier = Modifier
                .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .size(width = 200.dp, height = 40.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Personal information",
                modifier = Modifier.padding(start = 15.dp, top = 65.dp, bottom = 25.dp),
                color = LittleLemonColor.charcoal,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp)
        ) {
            val firstName = sharedPreferences.getString("FirstName", "").toString()
            Text(text = "First name", fontSize = 14.sp)
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(
                text = firstName,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, LittleLemonColor.charcoal, shape = RoundedCornerShape(5.dp))
                    .padding(start = 10.dp, top = 15.dp, bottom = 15.dp),
                fontSize = 18.sp
            )
        }
        Column(
            modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp)
        ) {
            val lastName = sharedPreferences.getString("LastName", "").toString()
            Text(text = "Last name", fontSize = 14.sp)
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(
                text = lastName,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, LittleLemonColor.charcoal, shape = RoundedCornerShape(5.dp))
                    .padding(start = 10.dp, top = 15.dp, bottom = 15.dp),
                fontSize = 18.sp
            )
        }
        Column(
            modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp)
        ) {
            val email = sharedPreferences.getString("Email", "").toString()
            Text(text = "Email", fontSize = 14.sp)
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(
                text = email,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, LittleLemonColor.charcoal, shape = RoundedCornerShape(5.dp))
                    .padding(start = 10.dp, top = 15.dp, bottom = 15.dp),
                fontSize = 18.sp
            )
        }
        Box(
            modifier = Modifier.fillMaxSize().padding(
                start = 15.dp,
                end = 15.dp,
                bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
            ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    sharedPreferences.edit().putString("FirstName", "").apply()
                    sharedPreferences.edit().putString("LastName", "").apply()
                    sharedPreferences.edit().putString("Email", "").apply()
                    sharedPreferences.edit().putBoolean("Login", false).apply()
                    navController.navigate(Onboarding.route)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow),
                border = BorderStroke(width = 1.dp, color = Color.Yellow)
            ) {
                Text(
                    text = "Log out",
                    color = LittleLemonColor.charcoal,
                    fontSize = 16.sp
                )
            }
        }
    }
}
