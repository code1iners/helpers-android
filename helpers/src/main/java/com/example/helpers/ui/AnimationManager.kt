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

        fun getHeaderHide(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.header_hide)
        }

        fun getHeaderShow(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.header_show)
        }

        fun getMoveInRight(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.move_in_right)
        }

        fun getMoveOutLeft(context: Context): Animation {
            return AnimationUtils.loadAnimation(context, R.anim.move_out_left)
        }
    }
}

fun Context.getMoveInRight(): Animation { return AnimationUtils.loadAnimation(this, R.anim.move_in_right) }
fun Context.getMoveOutLeft(): Animation { return AnimationUtils.loadAnimation(this, R.anim.move_out_left) }
fun Context.getFadeOutUp(): Animation { return AnimationUtils.loadAnimation(this, R.anim.fade_out_up_001) }
fun Context.getFadeInDown(): Animation { return AnimationUtils.loadAnimation(this, R.anim.fade_in_down_001) }
fun Context.getHeartBeating(): Animation { return AnimationUtils.loadAnimation(this, R.anim.heart_beating_001) }
fun Context.getMoveLeft(): Animation { return AnimationUtils.loadAnimation(this, R.anim.move_left_001) }
fun Context.getMoveRight(): Animation { return AnimationUtils.loadAnimation(this, R.anim.move_right_001) }
fun Context.getRemoveLeft(): Animation { return AnimationUtils.loadAnimation(this, R.anim.remove_left_001) }
fun Context.getBlink(): Animation { return AnimationUtils.loadAnimation(this, R.anim.blink) }
fun Context.getFadeIn(): Animation { return AnimationUtils.loadAnimation(this, R.anim.fade_in) }
fun Context.getFadeOut(): Animation { return AnimationUtils.loadAnimation(this, R.anim.fade_out) }
fun Context.getRotateLeft45(): Animation { return AnimationUtils.loadAnimation(this, R.anim.rotate_left_45) }
fun Context.getRotateRight45(): Animation { return AnimationUtils.loadAnimation(this, R.anim.rotate_right_45) }
fun Context.getBottomNavHide(): Animation { return AnimationUtils.loadAnimation(this, R.anim.bottom_nav_hide) }
fun Context.getBottomNavShow(): Animation { return AnimationUtils.loadAnimation(this, R.anim.bottom_nav_show) }
fun Context.getHeaderHide(): Animation { return AnimationUtils.loadAnimation(this, R.anim.header_hide) }
fun Context.getHeaderShow(): Animation { return AnimationUtils.loadAnimation(this, R.anim.header_show) }