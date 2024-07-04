package com.example.megamediatest.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class filePojo (@SerializedName ("title") var title:String="",
                      @SerializedName("description") var description:String="",
                      @SerializedName("img") var img:String="",
                      @SerializedName("url") var url:String="",
) : Serializable