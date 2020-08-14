package com.example.helpers

import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class GlideOptions {
    companion object {
        lateinit var options: RequestOptions
        fun centerCropAndRadius(radius: Int): RequestOptions {
            init()
            options = options.transform(CenterCrop(), RoundedCorners(radius))
            return options
        }

        fun centerCropAndCircleCrop(): RequestOptions {
            init()
            options = options.transform(CenterCrop(), CircleCrop())
            return options
        }

        fun init() {
            options = RequestOptions()
        }
    }
}