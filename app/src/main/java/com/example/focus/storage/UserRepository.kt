package com.example.focus.storage

import androidx.lifecycle.LiveData

interface UserRepository {
    fun getCurrentTokens(): LiveData<Long>
    fun addTokens(duration: Long): Long
    fun getConversionRate(): Long
    fun getConversionRate(level: Int): Long

    fun getStartTime(): Long
    fun setStartTime(millis: Long)
    fun getCurrentDuration(): Long
    fun setCurrentDuration(millis: Long)

    fun getEfficiencyLevel(): LiveData<Int>
    fun getEfficiencyUpgradeCost(): Long
    fun getEfficiencyUpgradeCost(level: Int): Long
    fun upgradeEfficiency(): Boolean

    fun getCapacityLevel(): LiveData<Int>
    fun getMaxCapacity(): Int
    fun getCapacityUpgradeCost(): Long
    fun getCapacityUpgradeCost(level: Int): Long
    fun upgradeCapacity(): Boolean

    fun saveData()
}