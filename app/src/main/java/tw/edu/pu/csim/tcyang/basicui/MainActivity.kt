package tw.edu.pu.csim.tcyang.basicui


import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.csim.tcyang.basicui.R
import tw.edu.pu.csim.tcyang.basicui.ui.theme.BasicUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var mper: MediaPlayer? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        onDispose {
            mper?.stop()
            mper?.release()
            mper = null
        }
    }

    var flag by remember { mutableStateOf("test") }
    var currentAnimal by remember { mutableStateOf(R.drawable.animal0) }

    val Animals = listOf(
        R.drawable.animal0, R.drawable.animal1, R.drawable.animal2, R.drawable.animal3,
        R.drawable.animal4, R.drawable.animal5, R.drawable.animal6, R.drawable.animal7,
        R.drawable.animal8, R.drawable.animal9
    )

    val AnimalsName = arrayListOf(
        "鴨子", "企鵝", "青蛙", "貓頭鷹", "海豚", "牛", "無尾熊", "獅子", "狐狸", "小雞"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0BBE4)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.app_title),
            fontSize = 25.sp,
            color = Color.Blue,
            fontFamily = FontFamily(Font(R.font.kai))
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = stringResource(R.string.app_author),
            fontSize = 20.sp,
            color = Color(0xFF654321)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Row {
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Android 圖示",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow),
                alpha = 0.6f,
            )

            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "Compose icon",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.firebase),
                contentDescription = "Firebase icon",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        LazyRow {
            items(51) { index ->
                Text(text = "$index:")
                Text(text = AnimalsName[index % 10])

                Image(
                    painter = painterResource(id = Animals[index % 10]),
                    contentDescription = "可愛動物",
                    modifier = Modifier.size(60.dp)
                )
            }
        }

        Spacer(modifier = Modifier.size(10.dp))

        Button(
            onClick = {
                if (flag == "test") {
                    flag = "abc"
                } else {
                    flag = "test"
                }

                Toast.makeText(
                    context,
                    "Compose 按鈕被點擊了！",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Text("按鈕測試")
        }

        Text(text = flag)

        Spacer(modifier = Modifier.size(10.dp))

        // 調整 Row 佈局，讓所有按鈕和圖片都能顯示在同一行
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // 平均分配空間
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 歡迎修課按鈕
            Button(
                onClick = {
                    mper?.release()
                    mper = null
                    mper = MediaPlayer.create(context, R.raw.tcyang)
                    mper?.start()
                },
                modifier = Modifier
                    .weight(0.35f) // 給予比之前 0.33f 略小的權重，避免佔滿
                    .fillMaxHeight(0.8f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "歡迎", color = Color.Blue)
                    Text(text = "修課", color = Color.Red)
                    Image(
                        painter = painterResource(id = R.drawable.teacher),
                        contentDescription = "teacher icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // 展翅飛翔按鈕
            Button(
                onClick = {
                    mper?.release()
                    mper = null
                    mper = MediaPlayer.create(context, R.raw.fly)
                    mper?.start()
                },
                modifier = Modifier
                    .weight(0.4f) // 調整權重
                    .fillMaxHeight(0.4f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "展翅飛翔", color = Color.White)
                    Image(
                        painter = painterResource(id = R.drawable.fly),
                        contentDescription = "fly icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // 結束App 按鈕 (使用 weight 確保空間分配)
            Button(
                onClick = {
                    mper?.stop()
                    mper?.release()
                    (context as? ComponentActivity)?.finish()
                },
                modifier = Modifier.weight(0.25f)
            ) {
                Text(text = "結束App")
            }

            // 切換圖片 (不再使用 weight，只佔用自身大小)
            Image(
                painter = painterResource(id = currentAnimal),
                contentDescription = if (currentAnimal == R.drawable.animal0) "鴨子" else "企鵝",
                modifier = Modifier
                    .size(50.dp) // 圖片尺寸進一步縮小，確保能容納
                    .clickable {
                        currentAnimal = if (currentAnimal == R.drawable.animal0) {
                            R.drawable.animal1
                        } else {
                            R.drawable.animal0
                        }
                    }
            )
        }
    }
}
