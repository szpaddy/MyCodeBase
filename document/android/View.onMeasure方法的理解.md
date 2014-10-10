View.onMeasure方法的理解
====================
[摘自](http://blog.sina.com.cn/s/blog_61fbf8d10100zzoy.html)

### 1、什么时候调用onMeasure方法？ 
当控件的父元素正要放置该控件时调用.父元素会问子控件一个问题，“你想要用多大地方啊？”，然后传入两个参数——widthMeasureSpec和heightMeasureSpec.
这两个参数指明控件可获得的空间以及关于这个空间描述的元数据.
更好的方法是你传递View的高度和宽度到setMeasuredDimension方法里,这样可以直接告诉父控件，需要多大地方放置子控件.

　　接下来的代码片段给出了如何重写onMeasure.注意，调用的本地空方法是来计算高度和宽度的.它们会译解widthHeightSpec和heightMeasureSpec值，并计算出合适的高度和宽度值.
java代码：

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    int measuredHeight = measureHeight(heightMeasureSpec);
	    int measuredWidth = measureWidth(widthMeasureSpec);
	    setMeasuredDimension(measuredHeight, measuredWidth);
    }

    private int measureHeight(int measureSpec) {

	    // Return measured widget height.
    }

    private int measureWidth(int measureSpec) {

  	  // Return measured widget width.
    }
    边界参数——widthMeasureSpec和heightMeasureSpec ，效率的原因以整数的方式传入。在它们使用之前，首先要做的是使用MeasureSpec类的静态方法getMode和getSize来译解，如下面的片段所示：

    java代码：
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

    依据specMode的值，（MeasureSpec有3种模式分别是UNSPECIFIED, EXACTLY和AT_MOST）
    如果是AT_MOST，specSize 代表的是最大可获得的空间；
    如果是EXACTLY，specSize 代表的是精确的尺寸；
    如果是UNSPECIFIED，对于控件尺寸来说，没有任何参考意义。
    
    
    ### 2、那么这些模式和我们平时设置的layout参数fill_parent, wrap_content有什么关系呢？
    当设置width或height为fill_parent时，容器在布局时调用子 view的measure方法传入的模式是EXACTLY，因为子view会占据剩余容器的空间，所以它大小是确定的。
    当设置为 wrap_content时，容器传进去的是AT_MOST, 表示子view的大小最多是多少，这样子view会根据这个上限来设置自己的尺寸。当子view的大小设置为精确值时，容器传入的是EXACTLY, 而MeasureSpec的UNSPECIFIED模式目前还没有发现在什么情况下使用。 
    View的onMeasure方法默认行为是当模式为UNSPECIFIED时，设置尺寸为mMinWidth(通常为0)或者背景drawable的最小尺寸，当模式为EXACTLY或者AT_MOST时，尺寸设置为传入的MeasureSpec的大小。 
    
    有个观念需要纠正的是，fill_parent应该是子view会占据剩下容器的空间，而不会覆盖前面已布局好的其他view空间，当然后面布局子 view就没有空间给分配了，所以fill_parent属性对布局顺序很重要。以前所想的是把所有容器的空间都占满了，难怪google在2.2版本里把fill_parent的名字改为match_parent.
