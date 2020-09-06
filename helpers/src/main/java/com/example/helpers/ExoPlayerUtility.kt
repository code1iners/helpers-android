package com.example.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class ExoPlayerUtility {

    companion object {
        val TAG = ExoPlayerUtility::class.simpleName

        fun getPlayer(context: Context): SimpleExoPlayer {
            Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)
            return SimpleExoPlayer.Builder(context).build()
        }

        fun buildMediaSource(context: Context, uri: Uri): MediaSource {
            Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(context, "exoplayer-codelab")
            return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        }

        fun getPlayWhenReady(player: SimpleExoPlayer): Boolean {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            return player.playWhenReady
        }

        fun getCurrentPosition(player: SimpleExoPlayer): Long {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            return player.currentPosition
        }

        fun getCurrentWindowIndex(player: SimpleExoPlayer): Int {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            return player.currentWindowIndex
        }

        @SuppressLint("InlinedApi")
        fun hideSystemUi(view: View) {
            Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)
            try {
                view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

            } catch (e: Exception) {e.printStackTrace()}
        }
    }

    // note. [example] how to using listener
    class PlaybackStateListener: Player.EventListener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            var stateString: String? = null
            stateString = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED"
                else -> "UNKNOWN_STATE"
            }
            Log.v(TAG, "changed state to $stateString, playWhenReady:$playWhenReady")
        }
    }
}