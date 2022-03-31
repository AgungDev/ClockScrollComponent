package fun5i.module.clockscroll;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.util.AttributeSet;
import android.widget.TextView;

public class NumberPickerScroll extends RelativeLayout {

    private static final String TAG = "ClockScroll";

    //interface
    public interface onClickBtnFunction{
        void theAction(String mData, boolean btn);
    }
    private onClickBtnFunction interfacee;
    public void Action(onClickBtnFunction ss){
        interfacee = ss;
    }

    private void btnClicked(){
        top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                interfacee.theAction(getInnerText(), true);
                if (START_POINT < _MAX){
                    START_POINT += 1;
                }
                // animation
                Animation fadeoutbtm = AnimationUtils.loadAnimation(getContext(), R.anim.fadeoutbtm);
                tv.startAnimation(fadeoutbtm);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvSET();
                        tv.clearAnimation();
                        Animation fadeintop = AnimationUtils.loadAnimation(getContext(), R.anim.fadeintop);
                        tv.startAnimation(fadeintop);
                    }
                },400);
            }
        });

        bottom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //bottomBTN();
                interfacee.theAction(getInnerText(), false);
                if (START_POINT > _MIN){
                    START_POINT -= 1;
                }
                // animation
                Animation fadeouttop = AnimationUtils.loadAnimation(getContext(), R.anim.fadeouttop);
                tv.startAnimation(fadeouttop);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvSET();
                        tv.clearAnimation();
                        Animation fadeinbtm = AnimationUtils.loadAnimation(getContext(), R.anim.fadeinbtm);
                        tv.startAnimation(fadeinbtm);
                    }
                },400);


            }
        });
    }

    private long then;

    public String getInnerText(){
        return tv.getText().toString();
    }

    private boolean acetilacetonato(int posision){
        return posision <= _MAX && posision >= _MIN;
    }

    public void setweight(int min, int max){
        _MIN = min;
        _MAX = max;
        TYPEE = 1;
    }

    public void setweight(String[] arr){
        mD = arr;
        TYPEE = 2;
    }

    public int TYPE_INTEGER = 1;
    public int TYPE_TEXT = -1;

    private void tvSET(){
        if (TYPEE == 1){
            //number
            if ( acetilacetonato(START_POINT) ){
                tv.setText(String.valueOf(START_POINT));
            }
        }else if(TYPEE == 2){
            //string
            if ( acetilacetonato(START_POINT) ){
                tv.setText(mD[START_POINT]);
            }
        }
    }

    private int TYPEE = 1;

    //component
    private RelativeLayout.LayoutParams paramsRel, param1Rel;
    private LinearLayout.LayoutParams params, params1;
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private ImageView top, bottom;
    private TextView tv;

    //component var
    private int WIDTH_DEFAULT = pxFromDp(40);
    private int MARGIN_BTN = -6;
    private float TEXT_SIZE = 20f;
    private int _MAX = 10;
    private int _MIN = 0;
    private String[] mD = {
            "AM",
            "PM"
    };
    private int START_POINT = 0;
    public int getPosition(){
        return START_POINT;
    }
    public void setSP(int start_point){
        START_POINT = start_point;
        if (TYPEE == 1){
            tv.setText(String.valueOf(start_point));
        }else if(TYPEE == 2){
            tv.setText(mD[start_point]);
        }

    }

    public NumberPickerScroll(Context context) {
        super(context);
        main();
    }

    public NumberPickerScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        main();

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.clock_scroll_gz);
        int count = typedArray.getIndexCount();

        for (int i=0; i<count; i++){
            int atr = typedArray.getIndex(i);
            if (atr == R.styleable.clock_scroll_gz_text){
                tv.setText(typedArray.getString(atr));
            }else if(atr == R.styleable.clock_scroll_gz_margin){
                /*int ab = (int) typedArray.getDimension(atr, 10f);
                MARGIN_BTN = Math.round(ab);
                setMar();*/
            }else if(atr == R.styleable.clock_scroll_gz_text_size){
                tv.setTextSize(typedArray.getDimension(atr, TEXT_SIZE));
            }else if(atr == R.styleable.clock_scroll_gz_any){

            }
        }

        btnClicked();


    }

    private void setMar(){
        param1Rel = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        tv.setLayoutParams(paramsRel);
        param1Rel.setMargins(0,pxFromDp(MARGIN_BTN),0,pxFromDp(MARGIN_BTN));
    }


    private void main(){
        setBackgroundColor(Color.TRANSPARENT);
        paramsRel = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(paramsRel);
        //paramsRel.width = LayoutParams.MATCH_PARENT;

        initComponent();
        addView(linearLayout);
        linearLayout.addView(relativeLayout);

        relativeLayout.addView(top);
        relativeLayout.addView(tv);
        relativeLayout.addView(bottom);
    }

    private void initComponent(){
        //init component 1
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        linearLayout.setLayoutParams(params);

        /*linearLayout.setGravity(CENTER_HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);*/

        //init component 1.1
        relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setId(generateViewId());
        paramsRel = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        paramsRel.setMargins(
                pxFromDp(5),
                pxFromDp(5),
                pxFromDp(5),
                pxFromDp(5)
        );
        relativeLayout.setLayoutParams(paramsRel);

        //init component 1.1.1
        top = new ImageView(getContext());
        top.setId(generateViewId());
        paramsRel = new RelativeLayout.LayoutParams(
                WIDTH_DEFAULT,
                WIDTH_DEFAULT
        );
        paramsRel.addRule(RelativeLayout.CENTER_HORIZONTAL);
        top.setLayoutParams(paramsRel);
        top.setImageResource(R.drawable.ic_top);

        //init component 1.1.2
        tv = new TextView(getContext());
        tv.setId(generateViewId());
        paramsRel = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        paramsRel.addRule(RelativeLayout.CENTER_HORIZONTAL);
        paramsRel.addRule(RelativeLayout.BELOW, top.getId());
        paramsRel.setMargins(0,pxFromDp(MARGIN_BTN),0,pxFromDp(MARGIN_BTN));
        tv.setLayoutParams(paramsRel);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TEXT_SIZE);
        tv.setText("0");

        //init component 1.1.3
        bottom = new ImageView(getContext());
        bottom.setId(generateViewId());
        paramsRel = new RelativeLayout.LayoutParams(
                WIDTH_DEFAULT,
                WIDTH_DEFAULT
        );
        paramsRel.addRule(RelativeLayout.CENTER_HORIZONTAL);
        paramsRel.addRule(RelativeLayout.BELOW, tv.getId());
        bottom.setLayoutParams(paramsRel);
        bottom.setImageResource(R.drawable.ic_bottom);

    }


    //function
    private int dpFromPx( int px) {
        return Math.round(px / getResources().getDisplayMetrics().density);
    }

    private int pxFromDp(int dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }
}
