package com.fitgoapps.model

data class Lapangan(
    var id: Int?,
    var userId: Int?,
    var sportTypeId: Int?,
    var address: String?,
    var name: String?,
    var city: String?,
    var lat: Double?,
    var long: Double?,
    var noHandphone: String?,
    var isOpenToday: Int?,
    var isActive: Int?,
    var isRecommended: Int?,
    var notes: String?,
    var createdAt: String?,
    var updatedAt: String?,
    var user_id: Int?,
    var sport_type_id: Int?,
    var TypeLapangans: MutableList<TypeLapangan>?,
    var lapanganImages: MutableList<LapanganImage>?
)
