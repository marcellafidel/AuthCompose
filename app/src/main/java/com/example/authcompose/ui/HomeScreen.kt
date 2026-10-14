package com.example.authcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------- Data dummy ----------
private data class Category(val label: String, val icon: ImageVector)
private data class MenuItem(val title: String, val subtitle: String, val price: String)
private data class NavItem(val label: String, val icon: ImageVector)

private val categories = listOf(
    Category("Semua", Icons.Default.Home),
    Category("Favorit", Icons.Default.Favorite),
    Category("Populer", Icons.Default.Star),
    Category("Belanja", Icons.Default.ShoppingCart),
    Category("Lainnya", Icons.Default.Menu)
)

private val menuItems = listOf(
    MenuItem("Kopi Susu Gula Aren", "Minuman • Best seller", "Rp 18.000"),
    MenuItem("Croissant Butter", "Roti • Fresh baked", "Rp 22.000"),
    MenuItem("Matcha Latte", "Minuman • Dingin", "Rp 24.000"),
    MenuItem("Nasi Ayam Geprek", "Makanan • Pedas", "Rp 20.000"),
    MenuItem("Brownies Cokelat", "Dessert • Manis", "Rp 15.000")
)

private val navItems = listOf(
    NavItem("Beranda", Icons.Default.Home),
    NavItem("Cari", Icons.Default.Search),
    NavItem("Favorit", Icons.Default.Favorite),
    NavItem("Profil", Icons.Default.Person)
)

// ---------- Layar utama ----------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(userName: String) {
    var selectedNav by remember { mutableIntStateOf(0) }
    var selectedCategory by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Halo, $userName 👋", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text(
                            "Mau pesan apa hari ini?",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifikasi")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                navItems.forEachIndexed { index, nav ->
                    NavigationBarItem(
                        selected = selectedNav == index,
                        onClick = { selectedNav = index },
                        icon = { Icon(nav.icon, contentDescription = nav.label) },
                        label = { Text(nav.label) }
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { SearchBar() }
            item { PromoBanner() }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(categories.size) { i ->
                        FilterChip(
                            selected = selectedCategory == i,
                            onClick = { selectedCategory = i },
                            label = { Text(categories[i].label) },
                            leadingIcon = {
                                Icon(
                                    categories[i].icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Rekomendasi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Lihat semua",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { /* TODO */ }
                    )
                }
            }
            items(menuItems) { ItemCard(it) }
            item { Spacer(Modifier.height(8.dp)) }
        }
    }
}

// ---------- Komponen kecil ----------
@Composable
private fun SearchBar() {
    var query by remember { mutableStateOf("") }
    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Cari menu favoritmu…") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
private fun PromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF4F46E5), Color(0xFF7C3AED))))
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                "PROMO SPESIAL",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text("Diskon 30%", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(
                "Untuk pembelian pertamamu hari ini",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp
            )
            Spacer(Modifier.height(6.dp))
            Button(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF4F46E5)
                )
            ) { Text("Klaim sekarang") }
        }
    }
}

@Composable
private fun ItemCard(item: MenuItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    item.title.first().toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(item.title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(
                    item.subtitle,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(item.price, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    AppTheme { HomeScreen(userName = "Lla") }
}