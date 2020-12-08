package com.example.helpers

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class ViewPagerUtility {
    inner class PreviewLeftRightViewPager2(val context: Context) {
        fun getPageTransformer(): ViewPager2.PageTransformer {
            val nextItemVisiblePx = context.resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx =
                context.resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)

            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            return ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                // Next line scales the item's height. You can remove it if you don't want this effect
                page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
                // If you want a fading effect uncomment the next line:
                // page.alpha = 0.25f + (1 - abs(position))
            }
        }
    
        fun getItemDecoration(): HorizontalMarginItemDecoration {
            return HorizontalMarginItemDecoration(context, R.dimen.viewpager_current_item_horizontal_margin)
        }
    
        fun getItemDecoration(horizontalMarginInDp: Int): HorizontalMarginItemDecoration {
            return HorizontalMarginItemDecoration(context, horizontalMarginInDp)
        }
    }
    
}

/**
 * Adds margin to the left and right sides of the RecyclerView item.
 * Adapted from https://stackoverflow.com/a/27664023/4034572
 * @param horizontalMarginInDp the margin resource, in dp.
 */
class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
    RecyclerView.ItemDecoration() {

    private val horizontalMarginInPx: Int =
        context.resources.getDimension(horizontalMarginInDp).toInt()

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.right = horizontalMarginInPx
        outRect.left = horizontalMarginInPx
    }
}