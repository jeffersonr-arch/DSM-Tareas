package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

private val BackgroundGreen = Color(0xFFD2E8D4)
private val LogoBackground = Color(0xFF073042)
private val AccentGreen = Color(0xFF006D3B)
private val DarkText = Color(0xFF1B1B1B)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = BackgroundGreen
                ) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardHeader(
            name = stringResource(R.string.full_name),
            title = stringResource(R.string.title),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        ContactInfo(
            phone = stringResource(R.string.phone),
            socialHandle = stringResource(R.string.social_handle),
            email = stringResource(R.string.email),
            modifier = Modifier.padding(bottom = 32.dp)
        )
    }
}

@Composable
private fun CardHeader(name: String, title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .background(LogoBackground)
        )
        Text(
            text = name,
            fontSize = 40.sp,
            fontWeight = FontWeight.Light,
            color = DarkText,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = AccentGreen
        )
    }
}

@Composable
private fun ContactInfo(
    phone: String,
    socialHandle: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ContactRow(icon = Icons.Filled.Phone, text = phone)
        ContactRow(icon = Icons.Filled.Share, text = socialHandle)
        ContactRow(icon = Icons.Filled.Email, text = email)
    }
}

@Composable
private fun ContactRow(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AccentGreen
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = DarkText)
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard(modifier = Modifier.background(BackgroundGreen))
    }
}