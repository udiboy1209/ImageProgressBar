Image Progress Bar
==================

Displays progress by cropping the back image wrt progress percentage, and placing it below front image.

![example](http://i.imgur.com/SfeUjKF.gif)

Latest version
--------------

0.3

Install
---------

add this line to dependencies in your `build.gradle`


    compile 'in.udiboy.ipb:image-progress-bar:0.3'


Use
--------

Directly defining in xml gives bugs currently.

You need to init in code and add it to a `ViewGroup`

        mIPB = new ImageProgressBar(this, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mIPB.setLayoutParams(lp);
        mIPB.setBackImage(R.drawable.star);
        mIPB.setFrontImage(R.drawable.star_border);

        ((FrameLayout) findViewById(R.id.ipb)).addView(mIPB);
        
Call `setProgress(double)` to update the progress where the double is a fraction between 0 and 1

License
-----------

MIT



