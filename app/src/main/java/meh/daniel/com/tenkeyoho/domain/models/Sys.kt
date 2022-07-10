package meh.daniel.com.tenkeyoho.domain.models


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val pod: String?
)