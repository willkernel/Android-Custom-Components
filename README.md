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
下面是一个高度概括，当你开始创建自己的View组件是所需要知道的

自定义类继承自View或者其subclass
重写父类中以"on"前缀的方法，例如 onDraw（），onMeasure（）和onkeydown（）。这和重写Activity和ListActivity的生命周期方法有点类似
使用你的新建子类。一旦完成，你的子类可以代替其他View
提示：扩展类可以被定义在Activity里面的内部类。因为它控制对它们的访问，但没有必要的（也许您要创建在应用程序中更广泛地使用新的View），这非常有用。

###完全定制的组件
完全定制的组件可以用于创建出你想要的图形组件。也许一个图形化的VU表，看起来像一个老的模拟计，或者一个跟着唱歌的TextView，反弹球沿着单词移动的话，你就可以跟着卡拉OK机唱歌。无论哪种方式，你想要的需求是无论你如何将内置组件结合起来也做不了。

幸运的是，你可以轻松地创建，看起来和表现的和以你想要的方式的组件，也许只受限于你的想象力，屏幕的大小和可用的处理能力（请记住，最终您的应用程序可能需要与比你的桌面工作站显著少一些运行消耗）。

* 要创建一个完全定制的组件：

你可以扩展最通用的View是，勿庸置疑，View，所以你通常会通过扩展此View开始创建新的高级组件。
可以提供一个构造方法可以从XML中获取属性和参数，并且还可以使用自己的类似的属性和参数（可能是VU表的颜色和范围，或针的宽度和阻尼等）
你可能会想在你的组件类来创建自己的事件监听器，属性访问和修改，以及可能更复杂的行为。
您将几乎肯定要重写onMeasure（），并也有可能需要重写onDraw（）如果您希望组件显示一些内容。虽然两者都有默认的逻辑，默认的onDraw（）不会做任何处理，和默认onMeasure（）将始终设置大小为100x100 - 这可能不是你想要的。
其他的方法...根据需要也可重写。

###onDraw（）和onMeasure（）延伸

* onDraw（）方法提供你一个Canvas，通过Canvas可以实现你想要的任何东西：2D图形，其他标准或自定义组件，样式文本，或者其他任何你能想到的。

  注意： 并不适用于3D图形。如果你想使用3D图形，你必须继承SurfaceView，而不是View，从一个单独的线程draw。详情请参阅GLSurfaceViewActivity示例。

* onMeasure（）涉及到很多知识点。 onMeasure（）是你的组件和它的容器之间的合同渲染的关键部分。 onMeasure（）应该被重写，高效，准确地报告其包含部分的测量信息。一旦测量宽度和高度被计算出来，这将使得稍微有点复杂，因为受限于父类的onMeasure（）方法的要求，以及通过宽度和高度调用setMeasuredDimension()方法的要求，。如果你没有在重写的onMeasure（）方法里调用这个方法，该方法，结果将是在测量时抛出异常。

在一个较高的水平，实现onMeasure（）看起来是这样的：

重写的onMeasure（）方法通过宽度和高度的度量规范（widthMeasureSpec和heightMeasureSpec参数，无论是代表尺寸整数代码）被调用，它应被视为对应产生的宽度和高度测量值的限制的要求。一个完整的参考样的这些规范可以要求限制，可以在参考文档中[View.onMeasure(INT，INT)](http://www.android-doc.com/reference/android/view/View.html#onMeasure(int, int))
组件的onMeasure（）方法应该计算出测量的宽度和高度，这将被用来呈现组件。它应尽量传入测量规范之内的值，尽管它可以选择超过他们（在这种情况下，父类可以选择处理，包括剪切，滚动，抛出一个异常，或要求onMeasure（）再次尝试，也许通过不同的测量规格[MeasureSpec](http://www.android-doc.com/reference/android/view/View.MeasureSpec.html)）。
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
           <td>当包含view的窗口可见性发送变化时调用.
           </td>
       </tr>     
       </tbody>
 </table>


###自定义view的示例

在APIDemos演示的[CustomView](http://www.android-doc.com/resources/samples/ApiDemos/index.html)示例提供了一个自定义视图的一个例子。自定义视图在LABELVIEW类中定义。

该[LABELVIEW](http://www.android-doc.com/resources/samples/ApiDemos/src/com/example/android/apis/view/LabelView.html)示例演示了一些自定义组件的不同方面：

* 扩展视图类完全自定义组件。
* 参数构造函数使用填充view的参数（在XML定义的参数）。其中一些通过view的父类传递，但更重要的是，在LableView中有许多自定义的属性使用。
* 你希望看到一个标签组件标准的公共方法，例如的setText（），setTextSize（），setTextColor（）等。
* 一个重写onMeasure方法来确定和设置组件的呈现大小。 （注意，在LABELVIEW，真正的工作是由私有的measureWidth（）方法实现的。）
* 一个重写的onDraw（）方法在提供的[Canvas](http://www.android-doc.com/reference/android/graphics/Canvas.html) [Canvas and Drawables](http://www.android-doc.com/guide/topics/graphics/2d-graphics.html#draw-with-canvas)上来绘制标签。

你可以看到一些用法在LABELVIEW自定义视图的示例中custom_view_1.xml 。特别是，你可以看到android:和app:命名空间参数的使用。这些app：自定义参数是LABELVIEW识别并使用的，并定义在示例的R资源定义类内部的样式内部类中。


###组合控件

如果你不希望创建一个完全自定义的组件，而是正在寻找放在一起由一组现有的控件，然后创建一个组合控件的，可重用组件可能适合该需求。简单地说，这个汇集了一批多个原子控件（或views）通过逻辑组合转换成可以被视为一个单一的东西。例如，组合框可以被认为是作为一个单一的线的EditText场和相邻钮带有附加PopupList的组合。如果从列表中按下按钮，选择的内容填充到EditText区域，但用户也可以直接输入一些东西到的EditText，如果他们愿意。

在Android中，其实有另外两个views已经做到这一点：[Spinner](http://www.android-doc.com/reference/android/widget/Spinner.html)和[AutoCompleteTextView](http://www.android-doc.com/reference/android/widget/AutoCompleteTextView.html)，但无论如何，一个组合框的概念是一个容易理解的例子。

要创建复合组件：

1.通常的起点是某种形式的布局，以便创建一个继承自布局的类。也许在一个组合框的情况下，我们可能会使用水平方向的LinearLayout中。请记住，其他布局可以嵌套里面，所以该组合控件可以是任意复杂的结构化。请注意，就像对待一个Activity，您可以使用声明（基于XML的）的方式来创建包含的组件，或者你可以通过编程方式在你的代码中嵌套组合控件。
2.在子类中的构造函数，将任何父类需要的参数通过父类的构造方法传递给父类。然后，你可以使用的其他views设置你的组件;这是您将创建的EditText场和PopupList。请注意，您也可能会引入自己在XML中的属性和参数，获得他们，并在构造函数中使用。
3.你还可以创建你的views可能发生的事件，例如为列表项目点击的监听器，点击选择某项时更新的EditText的内容。
4.还可以创建自己的属性的访问修饰符，例如，允许EditText值在需要时被初始化以及查询内容。
5.在继承自布局的情况下，你并不需要重写的onDraw（）和onMeasure（）方法，因为布局将有默认行为可能会工作得很好。但是，如果你需要仍然可以重写他们。
6.你可能会覆盖其他的...方法，如的onkeydown（），或许从组合框的弹出列表按下某个键时选择某些默认值。

总之，使用布局作为用于定制控制的基础的具有许多优点，包括：

- 您可以使用声明的XML文件，就像与Activity指定屏幕的布局，也可以以编程方式创建视图和通过代码嵌入到布局。
- onDraw（）和onMeasure（）方法（加上其他"on..."方法）有合适的逻辑代码，所以你不必重写他们。
- 最后，你可以非常快速地构建任意复杂的组合控件，就
好像它们是单一组件的来使用它们

###复合控件的例子

在附带的SDK API的演示项目中，有两个例子列表 - 示例4和示例6  Views/Lists/ 展示了一个SpeechView继承自LinearLayout以显示语音引号中的。示例代码对应的类是List4.java和List6.java。


###Modifying an Existing View Type
在某些情况下,有一种更轻松的选择来创建很有用的自定义视图。如果已经有非常相似的你想要的组件，您可以简单地继承自该组件，重写你想改变的逻辑。你可以自定义一个完全自定义组件来完成你想要的，但如果用在视图层级中更专业类来开始自定义，你还可以得到很多正是你想要的的逻辑代码。

例如，SDK示例中包含了[NotePad application](http://www.android-doc.com/resources/samples/NotePad/index.html)。这个示例展示了使用Android平台很多方面，其中有一个继承自EditText的View来做内衬记事本。这不是一个完美的例子，这些APIs所做的可能会改变原来的显示，但它确实展示了使用方法。

如果你还没有这样做的话，导入记事本示例到Eclipse（或者只是看看提供的源码）。在[NoteEditor.java](http://www.android-doc.com/resources/samples/NotePad/src/com/example/android/notepad/NoteEditor.html)文件中的MyEditText。

