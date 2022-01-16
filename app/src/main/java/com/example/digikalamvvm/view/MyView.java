package com.example.digikalamvvm.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.digikalamvvm.R;

public class MyView extends View {
    private Context context;
    float width,height,cx,cy;
    public MyView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();

    }

    public void init(){
        this.post(new Runnable() {
            @Override
            public void run() {
                width=getMeasuredWidth();
                height=getMeasuredHeight();
                cx=height;
                cy=height/8;
                invalidate();
            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint=new Paint();
        paint.setColor(getResources().getColor(R.color.myview));

        canvas.drawPaint(paint);
        paint.setColor(Color.BLACK);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.digikala);
        //Rect rectSrc=new Rect(0,0,1000,1000);
        //Rect rectDst=new Rect(500,500,1000,1000);
        //canvas.drawBitmap(bitmap,cx,cy,paint);
        canvas.drawBitmap(bitmap,cy,cx,paint);

    }


}
