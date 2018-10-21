package com.pietrantuono.iocdemo

import javax.inject.Inject


class Moka @Inject constructor(private val coffeBeans: CoffeBeans, private val water: Water) {
}