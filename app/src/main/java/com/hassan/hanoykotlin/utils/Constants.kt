package com.hassan.hanoykotlin.utils

class Constants {
    companion object {
        const val TEXT1 = "text1"
        const val TEXT2 = "text2"
        const val URL = "url"
        const val SPLASH_DURATION: Long = 2000 // In milliseconds i.e 2 x 1000
        const val APP_OPEN_COUNT_FOR_DIALOG = 1

        const val API_URL = "https://pek0gi3eik.execute-api.ap-southeast-1.amazonaws.com/v1/result"
        val DATA_CRITERIA1 =
            arrayListOf("BET_GOV_HANOY", "BET_GOV_HANOY_SP", "BET_GOV_HANOY_VIP")
        val DATA_CRITERIA2 =
            arrayOf("BET_STOCK_TAIWAN", "BET_STOCK_KOREA", "BET_STOCK_SINGAPORE")
    }
}