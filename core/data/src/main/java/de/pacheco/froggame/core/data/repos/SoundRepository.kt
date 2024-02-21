package de.pacheco.froggame.core.data.repos

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import de.pacheco.froggame.core.data.repos.interfaces.ISoundRepository
import de.pacheco.froggame.core.ressources.R
import javax.inject.Inject

class SoundRepository @Inject constructor(@ApplicationContext private val context: Context) : ISoundRepository {
    override fun playSplat() {
        MediaPlayer.create(context, R.raw.splat).start()
    }
}