package com.fitgoapps.repository.response

import com.fitgoapps.model.Lapangan

data class LapanganResponse(
    var message: String?,
    var data: LapanganSubResponse?
)

data class LapanganSubResponse(
    var lapangan: MutableList<Lapangan>?,
    var lapanganRec: MutableList<*>?,
)
