package com.example.helpers

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class ViewPagerUtility {
    companion object {
        val TAG = ViewPagerUtility::class.simpleName
    }
    
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
        fun getPageTransformer(nextItemVisiblePx: Float, currentItemHorizontalMarginPx: Float): ViewPager2.PageTransformer {
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
    
    inner class ZoomOut {
        fun getPageTransformer(): ZoomOutPageTransformer {
            return ZoomOutPageTransformer()
        }
    }
    
    inner class Depth {
        fun getPageTransformer(): DepthPageTransformer? {
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                DepthPageTransformer()
            } else {
                Log.e(TAG, "Require API 21")
                return null
            }
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


private const val MIN_SCALE_FOR_ZOOM_OUT = 0.85f
private const val MIN_ALPHA_FOR_ZOOM_OUT = 0.5f

class ZoomOutPageTransformer: ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    val scaleFactor = Math.max(MIN_SCALE_FOR_ZOOM_OUT, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }
                    
                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    
                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA_FOR_ZOOM_OUT +
                            (((scaleFactor - MIN_SCALE_FOR_ZOOM_OUT) / (1 - MIN_SCALE_FOR_ZOOM_OUT)) * (1 - MIN_ALPHA_FOR_ZOOM_OUT)))
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}

private const val MIN_SCALE_FOR_DEPTH = 0.75f

@RequiresApi(21)
class DepthPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 0 -> { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    alpha = 1f
                    translationX = 0f
                    translationZ = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> { // (0,1]
                    // Fade the page out.
                    alpha = 1 - position
                    
                    // Counteract the default slide transition
                    translationX = pageWidth * -position
                    // Move it behind the left page
                    translationZ = -1f
                    
                    // Scale the page down (between MIN_SCALE and 1)
                    val scaleFactor = (MIN_SCALE_FOR_DEPTH + (1 - MIN_SCALE_FOR_DEPTH) * (1 - Math.abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}