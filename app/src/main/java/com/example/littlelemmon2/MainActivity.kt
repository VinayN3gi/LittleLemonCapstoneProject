package com.example.littlelemmon2

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemmon2.ui.theme.LittleLemmon2Theme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private suspend fun fetchMenuItems(): List<MenuItemNetwork> {
        val response = client
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>()

        return response.menu
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, MenuDatabase::class.java, "menu.db").build()
    }

    private fun saveMenuToDatabase(menuItemNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemNetwork.map{ it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val sharedPreferences = getSharedPreferences("LittleLemon", MODE_PRIVATE)
            val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

            MyNavigation(builder, sharedPreferences, databaseMenuItems)
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuItems = fetchMenuItems()
                saveMenuToDatabase(menuItems)
            }
        }
    }
}

@Composable
fun MyNavigation(builder: AlertDialog.Builder, sharedPreferences: SharedPreferences, databaseMenuItems: List<MenuItemRoom>) {
    val navController  = rememberNavController()
    Navigation(navController, builder, sharedPreferences, databaseMenuItems)
}