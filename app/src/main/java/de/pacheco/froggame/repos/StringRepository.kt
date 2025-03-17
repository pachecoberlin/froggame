package de.pacheco.froggame.repos

import android.content.Context
import de.pacheco.froggame.repos.interfaces.IStringRepository
import javax.inject.Inject

class StringRepository @Inject constructor(private val context: Context) :
    IStringRepository {
    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}

