# Android-Custom-Components
For custom components, I had not been systematic studying it , this repository will be constantly updated

[custom-components](http://www.android-doc.com/guide/topics/ui/custom-components.html)

`public class View extends Object implements Drawable.Callback KeyEvent.Callback AccessibilityEventSource`

###Known Direct Subclasses
* AnalogClock
* ImageView
* KeyboardView
* MediaRouteButton
* ProgressBar
* Space
* SurfaceView
* TextView
* TextureView
* ViewGroup
* ViewStub

###Known Indirect Subclasses
* AbsListView
* AbsSeekBar
* AbsSpinner
* AbsoluteLayout
* AdapterView<T extends Adapter>
* AdapterViewAnimator
* AdapterViewFlipper, and 56 others.

`public abstract class ViewGroup extends View implements ViewManager ViewParent`

###Known Direct Subclasses
* AbsoluteLayout
* AdapterView<T extends Adapter>
* DrawerLayout
* FragmentBreadCrumbs
* FrameLayout
* GridLayout
* LinearLayout
* PagerTitleStrip
* RelativeLayout
* SlidingDrawer
* SlidingPaneLayout
* ViewPager

###Known Indirect Subclasses
* AbsListView
* AbsSpinner
* AdapterViewAnimator
* AdapterViewFlipper
* AppWidgetHostView
* CalendarView
* DatePicker
* DialerFilter
* ExpandableListView, and 21 others.

###基本方法
下面是一个高度概括，当你开始创建自己的View组件时所需要知道的<br>

自定义类继承自View或者其subclass
重写父类中以"on"前缀的方法，例如 onDraw()，onMeasure()和onkeydown()。这和重写Activity和ListActivity的生命周期方法有点类似。
一旦完成，你的子类可以代替其他View<br>
提示：继承类可以被定义在Activity里面的内部类。因为它控制对它们的访问，但没有必要的（也许您要创建在应用程序中更广泛地使用的View）。

###完全定制的组件
完全定制的组件可以用于创建出你想要的图形组件。也许一个图形化的VU表，看起来像一个老的模拟计，或者一个跟着唱歌的TextView，反弹球沿着单词移动的话，你就可以跟着卡拉OK机唱歌。无论哪种方式，你想要的需求是无论你如何将内置组件结合起来也做不了。

幸运的是，你可以轻松地创建，看起来和表现的和以你想要的方式的组件，也许只受限于你的想象力，屏幕的大小和可用的处理能力

* 要创建一个完全定制的组件：

你可以扩展最通用的View是，勿庸置疑，View，所以你通常会通过扩展此View开始创建新的高级组件。
可以提供一个构造方法可以从XML中获取属性和参数，并且还可以使用自己的类似的属性和参数（可能是VU表的颜色和范围，或针的宽度和阻尼等）
你可能会想在你的组件类来创建自己的事件监听器，属性访问和修改，以及可能更复杂的行为。
您将几乎肯定要重写onMeasure()，并也有可能需要重写onDraw()如果您希望组件显示一些内容。虽然两者都有默认的逻辑，默认的onDraw()不会做任何处理，和默认onMeasure()将始终设置大小为100x100 - 这可能不是你想要的。
其他的方法...根据需要也可重写。

###onDraw()和onMeasure()延伸

* onDraw()方法提供你一个Canvas，通过Canvas可以实现你想要的任何东西：2D图形，其他标准或自定义组件，样式文本，或者其他任何你能想到的。

  注意： 并不适用于3D图形。如果你想使用3D图形，你必须继承SurfaceView，而不是View，从一个单独的线程draw。详情请参阅GLSurfaceViewActivity示例。

* onMeasure()涉及到很多知识点。 onMeasure()是你的组件和它的容器之间的合同渲染的关键部分。 onMeasure()应该被重写，高效，准确地报告其包含部分的测量信息。一旦测量宽度和高度被计算出来，这将使得稍微有点复杂，因为受限于父类的onMeasure()方法的要求，以及通过宽度和高度调用setMeasuredDimension()方法的要求，。如果你没有在重写的onMeasure()方法里调用这个方法，该方法，结果将是在测量时抛出异常。

在一个较高的水平，实现onMeasure()看起来是这样的：

重写的onMeasure()方法通过宽度和高度的度量规范（widthMeasureSpec和heightMeasureSpec参数，无论是代表尺寸整数代码）被调用，它应被视为对应产生的宽度和高度测量值的限制的要求。一个完整的参考样的这些规范可以要求限制，可以在参考文档中[View.onMeasure(INT，INT)](http://www.android-doc.com/reference/android/view/View.html#onMeasure(int, int))
组件的onMeasure()方法应该计算出测量的宽度和高度，这将被用来呈现组件。它应尽量传入测量规范之内的值，尽管它可以选择超过他们（在这种情况下，父类可以选择处理，包括剪切，滚动，抛出一个异常，或要求onMeasure()再次尝试，也许通过不同的测量规格[MeasureSpec](http://www.android-doc.com/reference/android/view/View.MeasureSpec.html)）。
一旦宽度和高度被计算， setMeasuredDimension(int width, int height)方法必须与计算出的测量结果被调用。如果不这样做将导致抛出异常。


###下面是一些常用的其他方法：

<table border="2" width="85%" align="center" cellpadding="5">
       <thead>
           <tr><th>Category</th> <th>Methods</th> <th>Description</th></tr>
       </thead>
       
       <tbody>
       <tr>
           <td rowspan="2">Creation</td>
           <td>Constructors</td>
           <td>一种形式的构造方法将会在由代码创建的View和由布局文件填充的View来调用，第二种形式的构造方法应该解析和应用那些定义在布局文件的属性.
           </td>
       </tr>
       <tr>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onFinishInflate()">onFinishInflate()</a></code></code></td>
           <td>当一个view和它所有的子元素从布局文件中被填充之后调用.</td>
       </tr>
       
       <tr>
           <td rowspan="3">Layout</td>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onMeasure(int, int)">onMeasure(int, int)</a></code></code></td>
           <td>用来决定view和他所以子元素的尺寸要求.
           </td>
       </tr>
       <tr>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onLayout(boolean, int, int, int, int)">onLayout(boolean, int, int, int, int)</a></code></code></td>
           <td>当这个view和它的子元素需要被指定一个大小和位置的时候调用.
           </td>
       </tr>
       <tr>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onSizeChanged(int, int, int, int)">onSizeChanged(int, int, int, int)</a></code></code></td>
           <td>当这个view的大小发生变化的时候调用.
           </td>
       </tr>
       
       <tr>
           <td>Drawing</td>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onDraw(android.graphics.Canvas)">onDraw(Canvas)</a></code></code></td>
           <td>当这个view需要渲染它的内容的时候调用.
           </td>
       </tr>
  
       <tr>
           <td rowspan="4">Event processing</td>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onKeyDown(int, android.view.KeyEvent)">onKeyDown(int, KeyEvent)</a></code></code></td>
           <td>当发送新的按键事件时调用.
           </td>
       </tr>
       <tr>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onKeyUp(int, android.view.KeyEvent)">onKeyUp(int, KeyEvent)</a></code></code></td>
           <td>当按键按起的事件发生时调用.
           </td>
       </tr>   
       <tr>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onTrackballEvent(android.view.MotionEvent)">onTrackballEvent(MotionEvent)</a></code></code></td>
           <td>当轨迹球移动事件发生时调用.
           </td>
       </tr>  
       <tr>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onTouchEvent(android.view.MotionEvent)">onTouchEvent(MotionEvent)</a></code></code></td>
           <td>当触摸屏幕移动事件发生时调用.
           </td>
       </tr>  
       
       <tr>
           <td rowspan="2">Focus</td>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onFocusChanged(boolean, int, android.graphics.Rect)">onFocusChanged(boolean, int, Rect)</a></code></code></td>
           <td>当view获得或失去焦点时调用.
           </td>
       </tr>
       
       <tr>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onWindowFocusChanged(boolean)">onWindowFocusChanged(boolean)</a></code></code></td>
           <td>当包含view的窗口获得或者失去焦点时调用.
           </td>
       </tr>
       
       <tr>
           <td rowspan="3">Attaching</td>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onAttachedToWindow()">onAttachedToWindow()</a></code></code></td>
           <td>当view添加到一个窗口时调用.
           </td>
       </tr>
  
       <tr>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onDetachedFromWindow()">onDetachedFromWindow()</a></code></code></td>
           <td>当view从窗口分离的时候调用.
           </td>
       </tr>     
  
       <tr>
           <td><code><code><a href="http://www.android-doc.com/reference/android/view/View.html#onWindowVisibilityChanged(int)">onWindowVisibilityChanged(int)</a></code></code></td>
           <td>当包含view的窗口可见性发生变化时调用.
           </td>
       </tr>
       
       <tr>
           <td></td>
           <td><code><code><a>ViewConfiguration.get(context).getScaledTouchSlop()</a></code></code></td>
           <td>获取当前手机屏幕最小滑动距离，在frameworks/base/core/res/res/values/config.xml文件中.
           </td>
       </tr>
       
       </tbody>
 </table>


###自定义view的示例

在APIDemos演示的[CustomView](http://www.android-doc.com/resources/samples/ApiDemos/index.html)示例提供了一个自定义视图的一个例子。自定义视图在LABELVIEW类中定义。

该[LABELVIEW](http://www.android-doc.com/resources/samples/ApiDemos/src/com/example/android/apis/view/LabelView.html)示例演示了一些自定义组件的不同方面：

* 扩展视图类完全自定义组件。
* 参数构造函数使用填充view的参数（在XML定义的参数）。其中一些通过view的父类传递，但更重要的是，在LableView中有许多自定义的属性使用。
* 你希望看到一个标签组件标准的公共方法，例如的setText()，setTextSize()，setTextColor()等。
* 一个重写onMeasure方法来确定和设置组件的呈现大小。 （注意，在LABELVIEW，真正的工作是由私有的measureWidth（）方法实现的。）
* 一个重写的onDraw()方法在提供的[Canvas](http://www.android-doc.com/reference/android/graphics/Canvas.html) [Canvas and Drawables](http://www.android-doc.com/guide/topics/graphics/2d-graphics.html#draw-with-canvas)上来绘制标签。

你可以看到一些用法在LABELVIEW自定义视图的示例中custom_view_1.xml 。特别是，你可以看到android:和app:命名空间参数的使用。这些app：自定义参数是LABELVIEW识别并使用的，并定义在示例的R资源定义类内部的样式内部类中。


###组合控件

如果你不希望创建一个完全自定义的组件，而是正在寻找放在一起由一组现有的控件，然后创建一个组合控件的，可重用组件可能适合该需求。简单地说，这个汇集了一批多个原子控件（或views）通过逻辑组合转换成可以被视为一个单一的东西。例如，组合框可以被认为是作为一个单一的线的EditText场和相邻钮带有附加PopupList的组合。如果从列表中按下按钮，选择的内容填充到EditText区域，但用户也可以直接输入一些东西到的EditText，如果他们愿意。

在Android中，其实有另外两个views已经做到这一点：[Spinner](http://www.android-doc.com/reference/android/widget/Spinner.html)和[AutoCompleteTextView](http://www.android-doc.com/reference/android/widget/AutoCompleteTextView.html)，但无论如何，一个组合框的概念是一个容易理解的例子。

要创建复合组件：

1.通常的起点是某种形式的布局，以便创建一个继承自布局的类。也许在一个组合框的情况下，我们可能会使用水平方向的LinearLayout中。请记住，其他布局可以嵌套里面，所以该组合控件可以是任意复杂的结构化。请注意，就像对待一个Activity，您可以使用声明（基于XML的）的方式来创建包含的组件，或者你可以通过编程方式在你的代码中嵌套组合控件。
2.在子类中的构造函数，将任何父类需要的参数通过父类的构造方法传递给父类。然后，你可以使用的其他views设置你的组件;这是您将创建的EditText场和PopupList。请注意，您也可能会引入自己在XML中的属性和参数，获得他们，并在构造函数中使用。
3.你还可以创建你的views可能发生的事件，例如为列表项目点击的监听器，点击选择某项时更新的EditText的内容。
4.还可以创建自己的属性的访问修饰符，例如，允许EditText值在需要时被初始化以及查询内容。
5.在继承自布局的情况下，你并不需要重写的onDraw()和onMeasure()方法，因为布局将有默认行为可能会工作得很好。但是，如果你需要仍然可以重写他们。
6.你可能会覆盖其他的...方法，如的onkeydown()，或许从组合框的弹出列表按下某个键时选择某些默认值。

总之，使用布局作为用于定制控制的基础的具有许多优点，包括：

- 您可以使用声明的XML文件，就像与Activity指定屏幕的布局，也可以以编程方式创建视图和通过代码嵌入到布局。
- onDraw()和onMeasure()方法（加上其他"on..."方法）有合适的逻辑代码，所以你不必重写他们。
- 最后，你可以非常快速地构建任意复杂的组合控件，就
好像它们是单一组件的来使用它们

###复合控件的例子

在附带的SDK API的演示项目中，有两个例子列表 - 示例4和示例6  Views/Lists/ 展示了一个SpeechView继承自LinearLayout以显示语音。示例代码对应的类是List4.java和List6.java。


###修改已经存在的View类型
在某些情况下,有一种更轻松的选择来创建很有用的自定义视图。如果已经有非常相似的你想要的组件，您可以简单地继承自该组件，重写你想改变的逻辑。你可以自定义一个完全自定义组件来完成你想要的，但如果用在视图层级中更专业类来开始自定义，你还可以得到很多正是你想要的的逻辑代码。

例如，SDK示例中包含了[NotePad application](http://www.android-doc.com/resources/samples/NotePad/index.html)。这个示例展示了使用Android平台很多方面，其中有一个继承自EditText的View来做内衬记事本。这不是一个完美的例子，这些APIs所做的可能会改变原来的显示，但它确实展示了使用方法。

如果你还没有这样做的话，导入记事本示例到Eclipse（或者只是看看提供的源码）。在[NoteEditor.java](http://www.android-doc.com/resources/samples/NotePad/src/com/example/android/notepad/NoteEditor.html)文件中的MyEditText。

###基本知识点
- VelocityTracker
  ```java
VelocityTracker velocityTracker = VelocityTracker.obtain();
velocityTracker.addMovement(event);
velocityTracker.computeCurrentVelocity(units);//1000ms
/**
*速度=(end-start)/units 可正可负
*/
float xVelocity = velocityTracker.getXVelocity();
float yVelocity = velocityTracker.getYVelocity();
LogUtil.show(TAG, "xV=" + xVelocity + " yV=" + yVelocity);
velocityTracker.clear();
velocityTracker.recycle();
```

- GestureDetector
```java
//解决长按屏幕后无法拖动的现象
gestureDetector.setIsLongpressEnabled(false);

public boolean onTouchEvent(MotionEvent event) {
    LogUtil.show(TAG, "onTouchEvent()", Log.INFO);
    return gestureDetector.onTouchEvent(event);
}

OnDoubleTapListener
OnGestureListener
```

- Scroller
```java
//有过渡效果的滑动
Scroller scroller = new Scroller(context);

private void smoothScrollTo(int desX, int desY) {
    int scrollX = getScrollX();
    int delta = desX - scrollX;
    scroller.startScroll(scrollX, 0, desX, desY, 1000);//没有进行滑动操作
    invalidate();//View重绘
}
@Override
public void computeScroll() {
    super.computeScroll();
    if (scroller.computeScrollOffset()) {
        scrollTo(scroller.getCurrX(), scroller.getCurrY());
        invalidate();
    }
}

View
/**
   * Called by a parent to request that a child update its values for mScrollX
   * and mScrollY if necessary. This will typically be done if the child is
   * animating a scroll using a {@link android.widget.Scroller Scroller}
   * object.
   */
public void computeScroll() {
}

Scoller
/**
 * Call this when you want to know the new location.  If it returns true,
 * the animation is not yet finished.
 */ 
public boolean computeScrollOffset() {
    if (mFinished) {
        return false;
    }

    int timePassed = (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime);

    if (timePassed < mDuration) {
        switch (mMode) {
        case SCROLL_MODE:
        //滑动的时间，计算出滑动的距离，根据起始位置，计算出当前的位置
            final float x = mInterpolator.getInterpolation(timePassed * mDurationReciprocal);
            mCurrX = mStartX + Math.round(x * mDeltaX);
            mCurrY = mStartY + Math.round(x * mDeltaY);
            break;
        case FLING_MODE:
            final float t = (float) timePassed / mDuration;
            final int index = (int) (NB_SAMPLES * t);
            float distanceCoef = 1.f;
            float velocityCoef = 0.f;
            if (index < NB_SAMPLES) {
                final float t_inf = (float) index / NB_SAMPLES;
                final float t_sup = (float) (index + 1) / NB_SAMPLES;
                final float d_inf = SPLINE_POSITION[index];
                final float d_sup = SPLINE_POSITION[index + 1];
                velocityCoef = (d_sup - d_inf) / (t_sup - t_inf);
                distanceCoef = d_inf + (t - t_inf) * velocityCoef;
            }

            mCurrVelocity = velocityCoef * mDistance / mDuration * 1000.0f;
            
            mCurrX = mStartX + Math.round(distanceCoef * (mFinalX - mStartX));
            // Pin to mMinX <= mCurrX <= mMaxX
            mCurrX = Math.min(mCurrX, mMaxX);
            mCurrX = Math.max(mCurrX, mMinX);
            
            mCurrY = mStartY + Math.round(distanceCoef * (mFinalY - mStartY));
            // Pin to mMinY <= mCurrY <= mMaxY
            mCurrY = Math.min(mCurrY, mMaxY);
            mCurrY = Math.max(mCurrY, mMinY);

            if (mCurrX == mFinalX && mCurrY == mFinalY) {
                mFinished = true;
            }

            break;
        }
    }
    else {
        mCurrX = mFinalX;
        mCurrY = mFinalY;
        mFinished = true;
    }
    return true;
}
Note:Scroller并不能实现View的滑动，需要配合View的computeScroll，通过滑动的时间，得到滑动的位置，通过scrollTo完成滑动，View的每次重绘都会调用computeScrollOffset
if (scroller.computeScrollOffset()) {
    scrollTo(scroller.getCurrX(), scroller.getCurrY());
    invalidate();
}
如果没有完成滑动，继续进行滑动
```

- View滑动三种方式
  - scrollTo/scrollBy
  ```java
  /**
     * Set the scrolled position of your view. This will cause a call to
     * {@link #onScrollChanged(int, int, int, int)} and the view will be
     * invalidated.
     * @param x the x position to scroll to
     * @param y the y position to scroll to
     */
    public void scrollTo(int x, int y) {
        if (mScrollX != x || mScrollY != y) {
            int oldX = mScrollX;
            int oldY = mScrollY;
            mScrollX = x;
            mScrollY = y;
            invalidateParentCaches();
            onScrollChanged(mScrollX, mScrollY, oldX, oldY);
            if (!awakenScrollBars()) {
                postInvalidateOnAnimation();
            }
        }
    }

    /**
     * Move the scrolled position of your view. This will cause a call to
     * {@link #onScrollChanged(int, int, int, int)} and the view will be
     * invalidated.
     * @param x the amount of pixels to scroll by horizontally
     * @param y the amount of pixels to scroll by vertically
     */
    public void scrollBy(int x, int y) {
        scrollTo(mScrollX + x, mScrollY + y);
    }
    Note:这种方式的滑动，只对View的内容进行滑动，View本身不滑动，另外，mScollX,mScrollY是View的左上边缘相对于内容的左上边缘的滑动距离，当从左向右滑动时，mScollX<0,当从右向左滑动时，mScollY>0;当从上向下滑动时，mScrollY<0,当从下向上滑动时，mScrollY>0.
  ```
  - TweenAnimation/PropertyAnimator
  ```java
    TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
    
    ObjectAnimator.ofFloat(Object target, String propertyName, float... values).setDuration(long duration).start();
    
    Note:TweenAnimation并不能改变View的位置参数，这时新位置的点击事件无效，PropertyAnimator可以解决这个问题，但是nineoldandroids动画兼容库在3.0之前的版本，本质上是TweenAnimation
  ```
  - LayoutParams
  ```java
    MarginLayoutParams params=(MarginLayoutParams)view.getLayoutParams():
    params.width+=100;
    params.leftMargin+=100;
    view.requesLayout();
    //view.setLayoutParams(params);
  ```
  
###View事件分发机制
-  点击事件传递规则,伪代码
```java
   /**
     * Pass the touch screen motion event down to the target view, or this
     * view if it is the target.
     *
     * @param event The motion event to be dispatched.
     * @return True if the event was handled by the view, false otherwise.
     */
    public boolean dispatchTouchEvent(MotionEvent event) {
      ...
    }

    ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent event){
        ...
        if(onClickListener!=null){
          onClickListener.onClick();
        }
    }
    
    public void dispatchTouchEvent(MotionEvent ev){
      boolean consume=false;
      if(onInterceptTouchEvent(ev)){
        consume=onTouchEvent(ev);
      }else{
        consume=child.dispatchTouchEvent(ev);
      }
      reture consume;
    }
    
    //View的事件处理
    if(onTouchListener!=null){
      if(!onTouchListener.onTouch()){
        onTouchEvent();
      }
    }

Note:点击事件：Activity->Window->View , if(!view.onTouchEvent)->if(!parent.onTouchEvent)->...->Acrivity.onTouchEvent;
1.一个事件序列，down->move...->up;
2.一个事件序列只能被一个View拦截消耗，但可以通过其他手段将事件传递给其他View，在onTouchEvent()强行传递给其他view
3.View没有这个onInterceptTouchEvent(),直接调用onTouchEvent(),默认返回true，除非它是不可点击.
4.View的enable不影响onTouchEvent默认返回值，只要clickable||longClickable=true -> onTouchEvent()=true;
5.事件传递由外向内，事件先传递给父元素，再由其传递给子元素，requestDisallowInterceptTouchEvent可以干预父元素的事件分发但是ActionDown事件除外.
    
    interface ViewParent
    /**
     * Called when a child does not want this parent and its ancestors to
     * intercept touch events with
     * {@link ViewGroup#onInterceptTouchEvent(MotionEvent)}.
     *
     * <p>This parent should pass this call onto its parents. This parent must obey
     * this request for the duration of the touch (that is, only clear the flag
     * after this parent has received an up or a cancel.</p>
     * 
     * @param disallowIntercept True if the child does not want the parent to
     *            intercept touch events.
     */
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept);
```

- 事件分发源码分析
```java
Activity#dispatchTouchEvent()
/**
 * Called to process touch screen events.  You can override this to
 * intercept all touch screen events before they are dispatched to the
 * window.  Be sure to call this implementation for touch screen events
 * that should be handled normally.
 *
 * @param ev The touch screen event.
 *
 * @return boolean Return true if this event was consumed.
 */
public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
        onUserInteraction();
    }
    if (getWindow().superDispatchTouchEvent(ev)) {
        return true;//事件处理结束
    }
    return onTouchEvent(ev);//当所有子元素没有做事件处理的时候调用onTouchEvent(ev);
}

PhoneWindow#superDispatchTouchEvent()
@Override
public boolean superDispatchTouchEvent(MotionEvent event) {
    return mDecor.superDispatchTouchEvent(event);
}

`private final class DecorView extends FrameLayout implements RootViewSurfaceTaker`
DecorView#superDispatchTouchEvent()
public boolean superDispatchTouchEvent(MotionEvent event) {
    return super.dispatchTouchEvent(event);
}

((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0)获取Activity设置的View，由于DecorView extends FrameLayout(ViewGroup),事件传递给
ViewGroup#dispatchTouchEvent()
@Override
public boolean dispatchTouchEvent(MotionEvent ev){
  // Handle an initial down.
  if (actionMasked == MotionEvent.ACTION_DOWN) {
      // Throw away all previous state when starting a new touch gesture.
      // The framework may have dropped the up or cancel event for the previous gesture
      // due to an app switch, ANR, or some other state change.
      cancelAndClearTouchTargets(ev);
      resetTouchState();
  }

  // Check for interception.
  final boolean intercepted;
  if (actionMasked == MotionEvent.ACTION_DOWN
          || mFirstTouchTarget != null) {
      final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
      if (!disallowIntercept) {
          intercepted = onInterceptTouchEvent(ev);
          ev.setAction(action); // restore action in case it was changed
      } else {
          intercepted = false;
      }
  } else {
      // There are no touch targets and this action is not an initial down
      // so this view group continues to intercept touches.
      intercepted = true;
  }
}
顶级View也叫根View，顶级View一般来说都是ViewGroup.FLAG_DISALLOW_INTERCEPT 可以通过这个标志位处理滑动冲突的问题
当ViewGroup不拦截事件，事件会向下传递给子view
```
- View的滑动冲突
  - 外部滑动方向和内部滑动方向不一致
    ViewPager和Fragment+ListView的场景，ViewPager默认做了滑动冲突的处理，如果是ScrollView就必须手动处理<br>
    处理规则：根据滑动的方向(多种判读方法，水平和竖直方向的距离大小)，判断由谁来拦截事件<br>
    解决方法：
    1.外部拦截法，父容器需要此事件就拦截，不需要就不拦截,重写父类的onInterceptTouchEvent,推荐采用这种
    ```java
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted=false;
        int x=(int)event.getX();
        int y=(int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted=false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(父容器需要此事件){
                  return true;
                }else{
                  return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted=false;
                break;
        }
        mLastX = x;
        mLastY = y;
        return intercepted;
    }
    ```
    2.内部拦截法，重写子元素的onInterceptTouchEvent
    ```java
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                parent.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int dX=x-mLastX;
                int dY=y-mLastY;
                
                if(父容器需要此事件){
                  parent.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.onInterceptTouchEvent(event);
    }
    ```
      父元素所做的修改
    ```java
    public boolean onInterceptTouchEvent(MotionEvent ev){
      int action=event.getAction();
      if(action==MotionEvent.ACTION_DOWN){
        return false;
      }else{
        return true;
      }
    }
    ```
  - 外部滑动方向和内部滑动方向一致
    ScrollView+ListView，同方向滑动<br>
    处理规则：根据业务需求做出相应的处理，详细的内容后续添加<br>
  - 两种情况的嵌套
    SlidingMenu+ViewPager+ListView<br>
    处理规则：根据业务需求做出相应的处理，详细的内容后续添加<br>

###View的工作原理(掌握三大流measure , layout ,draw 程以及常用回调方法)
- ViewRoot , DecorView<br>
  ViewRoot对应于ViewRootImpl是连接WindowManager和DecorView的纽带，三大流程均是通过ViewRoot完成<br>
  Activity创建完后会将DecorView添加到Window中，同时会创建ViewRootImpl，并将两者建立关联<br>
  ```java
    root=new ViewRootImpl(view.getContext(),display);
    root.setView(view,wparams,panelParentView);
    
    performTraversals()
    ViewGroup
    - root.performMeasure()->root.measure()->root.onMeasure()->View#measure
    - root.performLayout()->root.layout()->root.onLayout()->View#layout
    - root.performDraw()->root.draw()->root.onDraw()->View#draw
  ```
  
###此仓库包含的示例程序
- canvas的用法，自定义属性的用法<br>
  ![img](https://github.com/willkernel/Android-Custom-Components/blob/master/pngfiles/customview.png)
- CustomLayout,可拖拽Layout,以及改变布局参数<br>
  ![img](https://github.com/willkernel/Android-Custom-Components/blob/master/pngfiles/customlayout.png)
- 粒子效果之雨，学习极客学院的练习<br>
  ![img](https://github.com/willkernel/Android-Custom-Components/blob/master/pngfiles/rain.png)
- 音量显示<br>
  ![img](https://github.com/willkernel/Android-Custom-Components/blob/master/pngfiles/volume.png)
- LockView<br>
  ![img](https://github.com/willkernel/Android-Custom-Components/blob/master/pngfiles/lockview.png)
