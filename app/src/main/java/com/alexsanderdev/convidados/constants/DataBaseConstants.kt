/*
 * Copyright (c) 2023. Created by Alexsander at 11/13. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 ******************************************************************************/

package com.alexsanderdev.convidados.constants

class DataBaseConstants private constructor() {
    object GUEST {
        const val ID = "guestid"
        const val TABLE_NAME = "Guest"
        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}