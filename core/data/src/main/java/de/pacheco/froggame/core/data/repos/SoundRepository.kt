package de.pacheco.froggame.core.data.repos

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import de.pacheco.froggame.core.data.repos.interfaces.ISoundRepository
import de.pacheco.froggame.core.ressources.R
import javax.inject.Inject

class SoundRepository @Inject constructor(@ApplicationContext private val context: Context) : ISoundRepository {
    private val players = hashMapOf<Int, MediaPlayer?>()

    private fun getPlayer(soundResId: Int): MediaPlayer? {
        if (players[soundResId] == null) {
            players[soundResId] = MediaPlayer.create(context, soundResId)
        }
        return players[soundResId]
    }

    override fun playSplat() {
        getPlayer(R.raw.splat)?.start()
    }

    override fun releasePlayers() {
        players.values.forEach {
            it?.release()
        }
        players.clear()
    }
}