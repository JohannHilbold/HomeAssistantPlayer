package com.johann.homeassistantplayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.johann.homeassistantplayer.ui.theme.HomeAssistantPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeAssistantPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    Button(
                        onClick = {

                        },
                        modifier = Modifier.wrapContentSize(),
                        content = {
                            Text(text = "Play")
                        }
                    )
                }
            }
        }
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        Log.d("MainActivity", "handleIntent ${intent?.extras}")
        if(intent!= null && intent.extras != null){
            if(intent.extras?.getString("videoUrl") != null){
                val videoUrl = intent.extras?.getString("videoUrl", "")?:""
                initExoPlayerAndPlayUrl(this, "http://192.168.0.53:8123/local/" + videoUrl)
            }
            else if(intent.extras?.getString("turn") != null){
                Log.d("MainActivity", "handleIntent turning "+intent.extras?.getString("turn")+" player")
            }
            else if(intent.extras?.getString("volume") != null){
                Log.d("MainActivity", "handleIntent volume: " + intent.extras?.getString("volume"))
            }
            else if(intent.extras?.getString("mute") != null){
                Log.d("MainActivity", "handleIntent mute: " + intent.extras?.getString("mute"))
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun initExoPlayerAndPlayUrl(mainActivity: MainActivity, videoUrl: String) {
        val player = ExoPlayer.Builder(mainActivity).build()
        val uri = Uri.parse(videoUrl)
        // Build the media item.
        val mediaItem = MediaItem.fromUri(uri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
        player.playWhenReady = true // Start playing when ready
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeAssistantPlayerTheme {
        Greeting("Android")
    }
}