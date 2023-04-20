package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SegmentListView() {
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "DATE",
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        val cardIcon: Painter = painterResource(R.drawable.train_icon)
        val primaryColor = Color(0xFF942035)
        val onPrimaryColor = MaterialTheme.colorScheme.onPrimary

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(primaryColor)
                .fillMaxWidth()
                .padding(8.dp),

            ) {
            Column {
                Row {
                    Image(
                        painter = cardIcon,
                        contentDescription = "Icône de train",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(top = 7.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Clisson",
                            color = onPrimaryColor,
                            fontSize = 10.sp
                        )
                        Text(
                            "Nantes",
                            color = onPrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            "Voie",
                            color = onPrimaryColor,
                            fontSize = 9.sp
                        )
                        Text(
                            "--",
                            color = onPrimaryColor,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 9.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                )
                Row {


                    Text(
                        modifier = Modifier
                            .padding(start = 7.dp),
                        text = "3 prs",
                        color = onPrimaryColor,
                        fontSize = 9.sp
                    )


                    Spacer(
                        modifier = Modifier
                            .width(50.dp)
                    )
                    Text(

                        text = "10:30",
                        color = onPrimaryColor,
                        fontSize = 9.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SegmentListView()
}
