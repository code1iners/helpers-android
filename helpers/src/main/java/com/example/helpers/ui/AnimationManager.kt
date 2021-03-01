package com.example.helpers.ui

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.helpers.R


class AnimationManager {
    companion object {

        fun getFadeOutUp(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.fade_out_up_001)
        }

        fun getFadeInDown(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.fade_in_down_001)
        }

        fun getHeartBeating(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.heart_beating_001)
        }

        fun getMoveLeft(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.move_left_001)
        }

        fun getMoveRight(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.move_right_001)
        }

        fun getRemoveLeft(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.remove_left_001)
        }

        fun getBlink(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.blink)
        }

        fun getFadeIn(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.fade_in)
        }

        fun getFadeOut(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.fade_out)
        }

        fun getRotateLeft45(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.rotate_left_45)
        }

        fun getRotateRight45(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.rotate_right_45)
        }

        fun getBottomNavHide(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.bottom_nav_hide)
        }

        fun getBottomNavShow(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.bottom_nav_show)
        }
    }
}