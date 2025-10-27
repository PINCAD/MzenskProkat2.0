package com.mzenskprokat.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mzenskprokat.app.models.Result
import com.mzenskprokat.app.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    onNavigateToCatalog: () -> Unit,
    onNavigateToOrder: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val homeDataState by viewModel.homeData.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Заголовок компании
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Завод прецизионных сплавов",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Мценскпрокат",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Производитель прецизионных, медных и никелевых сплавов",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Преимущества
        item {
            Text(
                text = "Наши преимущества",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            FeatureCard(
                icon = Icons.Default.Star,
                title = "Прямые поставки",
                description = "От производителя без посредников"
            )
        }

        item {
            FeatureCard(
                icon = Icons.Default.List,
                title = "Широкий ассортимент",
                description = "Более 100 видов сплавов и сталей"
            )
        }

        item {
            FeatureCard(
                icon = Icons.Default.ShoppingCart,
                title = "Оптовые цены",
                description = "Выгодные условия для оптовых покупателей"
            )
        }

        item {
            FeatureCard(
                icon = Icons.Default.CheckCircle,
                title = "Высокое качество",
                description = "Соответствие ГОСТ и международным стандартам"
            )
        }

        // Основные категории продукции
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Основные категории",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            CategoryCard(
                title = "Прецизионные сплавы",
                description = "С высоким электрическим сопротивлением",
                icon = Icons.Default.Settings
            )
        }

        item {
            CategoryCard(
                title = "Магнитно-мягкие сплавы",
                description = "Высокая магнитная проницаемость",
                icon = Icons.Default.Settings
            )
        }

        item {
            CategoryCard(
                title = "Проволока нихром",
                description = "Диаметры от 0,1 мм до 10,0 мм",
                icon = Icons.Default.Settings
            )
        }

        item {
            CategoryCard(
                title = "Специальные стали",
                description = "Коррозионностойкие и жаростойкие",
                icon = Icons.Default.Settings
            )
        }

        // Кнопки действий
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onNavigateToCatalog,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.List, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Посмотреть каталог продукции")
            }
        }

        item {
            OutlinedButton(
                onClick = onNavigateToOrder,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Оформить заказ")
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun FeatureCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun CategoryCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}