
package com.savii.focus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF100D1C)
private val CardColor = Color(0xFF1D1930)
private val Purple = Color(0xFF9B7BFF)
private val Muted = Color(0xFFAAA3BC)
private val White = Color.White

private enum class AppScreen(val title: String) {
    HOME("Home"),
    LEARNING("Learning"),
    PLANNER("Planner"),
    FOCUS("Focus"),
    INSIGHTS("Insights"),
    AI("AI Assistant")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                SaviiFocusApp()
            }
        }
    }
}

@Composable
private fun SaviiFocusApp() {
    var currentScreen by rememberSaveable {
        mutableStateOf(AppScreen.HOME)
    }

    Scaffold(
        containerColor = Background,
        bottomBar = {
            NavigationBar(
                containerColor = CardColor,
                contentColor = White
            ) {
                val items = listOf(
                    AppScreen.HOME,
                    AppScreen.LEARNING,
                    AppScreen.PLANNER,
                    AppScreen.FOCUS,
                    AppScreen.INSIGHTS
                )

                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentScreen == screen,
                        onClick = { currentScreen = screen },
                        icon = {
                            Text(
                                text = when (screen) {
                                    AppScreen.HOME -> "⌂"
                                    AppScreen.LEARNING -> "L"
                                    AppScreen.PLANNER -> "P"
                                    AppScreen.FOCUS -> "F"
                                    AppScreen.INSIGHTS -> "I"
                                    else -> "•"
                                },
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        label = {
                            Text(screen.title, fontSize = 9.sp)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Purple,
                            selectedTextColor = Purple,
                            indicatorColor = Purple.copy(alpha = 0.15f),
                            unselectedIconColor = Muted,
                            unselectedTextColor = Muted
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (currentScreen) {
                AppScreen.HOME -> HomeScreen(
                    onNavigate = { currentScreen = it }
                )

                AppScreen.LEARNING -> FeatureScreen(
                    title = "Learning",
                    subtitle = "Your personal learning space.",
                    description = "Organize your subjects, lessons and learning goals."
                )

                AppScreen.PLANNER -> FeatureScreen(
                    title = "Planner",
                    subtitle = "Plan your day.",
                    description = "Organize your tasks and manage your daily plans."
                )

                AppScreen.FOCUS -> FeatureScreen(
                    title = "Focus",
                    subtitle = "Make every minute count.",
                    description = "Your focus timer and study sessions will be built here."
                )

                AppScreen.INSIGHTS -> FeatureScreen(
                    title = "Insights",
                    subtitle = "Track your progress.",
                    description = "Understand your productivity and learning progress."
                )

                AppScreen.AI -> FeatureScreen(
                    title = "AI Assistant",
                    subtitle = "Your intelligent assistant.",
                    description = "Your AI productivity assistant will be developed here."
                )
            }
        }
    }
}

@Composable
private fun HomeScreen(
    onNavigate: (AppScreen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "SAVII FOCUS",
            color = Purple,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your life.",
            color = White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "In focus.",
            color = Purple,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Make today count.",
            color = Muted,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        SectionTitle("YOUR OVERVIEW")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OverviewCard(
                title = "FOCUS",
                value = "00 min",
                modifier = Modifier.weight(1f)
            )

            OverviewCard(
                title = "TASKS",
                value = "0 / 0",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle("YOUR SPACE")

        FeatureCard(
            title = "Learning",
            description = "Organize your studies and learning goals.",
            symbol = "01",
            onClick = { onNavigate(AppScreen.LEARNING) }
        )

        FeatureCard(
            title = "Planner",
            description = "Plan your day and manage your tasks.",
            symbol = "02",
            onClick = { onNavigate(AppScreen.PLANNER) }
        )

        FeatureCard(
            title = "Focus",
            description = "Stay focused on what matters.",
            symbol = "03",
            onClick = { onNavigate(AppScreen.FOCUS) }
        )

        FeatureCard(
            title = "Insights",
            description = "Understand your progress and habits.",
            symbol = "04",
            onClick = { onNavigate(AppScreen.INSIGHTS) }
        )

        FeatureCard(
            title = "AI Assistant",
            description = "Your intelligent productivity companion.",
            symbol = "05",
            onClick = { onNavigate(AppScreen.AI) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your life. In focus.",
            color = Muted,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun FeatureScreen(
    title: String,
    subtitle: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "SAVII FOCUS",
            color = Purple,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = title,
            color = White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = subtitle,
            color = Purple,
            fontSize = 17.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardColor, RoundedCornerShape(20.dp))
                .padding(20.dp)
        ) {
            Text(
                text = "COMING NEXT",
                color = Purple,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = description,
                color = Muted,
                fontSize = 15.sp,
                lineHeight = 23.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "This section is part of the SAVII FOCUS development plan.",
            color = Muted,
            fontSize = 12.sp,
            lineHeight = 19.sp
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Muted,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.5.sp
    )

    Spacer(modifier = Modifier.height(14.dp))
}

@Composable
private fun OverviewCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(CardColor, RoundedCornerShape(18.dp))
            .padding(18.dp)
    ) {
        Text(
            text = title,
            color = Muted,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = value,
            color = White,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun FeatureCard(
    title: String,
    description: String,
    symbol: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .background(CardColor, RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .background(
                    Purple.copy(alpha = 0.15f),
                    RoundedCornerShape(14.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = symbol,
                color = Purple,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = White,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = description,
                color = Muted,
                fontSize = 12.sp,
                lineHeight = 18.sp
            )
        }

        Text(
            text = "›",
            color = Purple,
            fontSize = 25.sp
        )
    }
}
