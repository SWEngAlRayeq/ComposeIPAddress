package app.ip_address.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.ip_address.presentation.viewmodel.IpInfoViewModel

@Composable
fun IpInfoScreen() {

    val viewModel: IpInfoViewModel = viewModel()
    val state = viewModel.state
    val isLoading = viewModel.isLoading


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color(0xFF1DB954))
        } else {
            state?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📡 IP Address Info", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    InfoCard("🌍 IP", it.ip)
                    InfoCard("📍 City", it.city)
                    InfoCard("🏙️ Region", it.region)
                    InfoCard("🌐 Country", it.country)
                    it.postal?.let { value -> InfoCard("🧾 Postal", value) }
                    InfoCard("🧭 Timezone", it.timezone)
                    InfoCard("📌 Latitude", it.latitude.toString())
                    InfoCard("📌 Longitude", it.longitude.toString())

                    Button(
                        onClick = viewModel::fetchIpInfo,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954))
                    ) {
                        Text("🔁 Refresh", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(text = value, textAlign = TextAlign.End, modifier = Modifier.weight(2f))
        }
    }
}