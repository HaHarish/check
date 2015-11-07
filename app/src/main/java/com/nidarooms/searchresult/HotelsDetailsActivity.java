package com.nidarooms.searchresult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nidarooms.R;

import java.util.ArrayList;

/**
 * Created by Harish on 29/10/15.
 */
public class HotelsDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_PARAM_ID = "place_id";
 //   private ListView mList;
    private ImageView mImageView;
    private TextView mTitle;
    private LinearLayout mTitleHolder;
    private FloatingActionButton mAddButton;
 //   private LinearLayout mRevealView;
 //   private EditText mEditTextTodo;
    private boolean isEditTextVisible;
    private InputMethodManager mInputManager;
    private Place mPlace;
    private ArrayList<String> mTodoList;
    private ArrayAdapter mToDoAdapter;
    int defaultColor;

    /** Button for Travel Points */
    Button btnTravel;

    /** Button for Restaurant Points */
    Button btnRestaurants;

    /** Button for Market Points */
    Button btnMarket;

    /** Button for Increase Guest */
    Button btnGuestPlus;

    /** Button for Decrease Guest */
    Button btnGuestMinus;

    /** Button for Increase Room */
    Button btnRoomPlus;

    /** Button for Decrease Room */
    Button btnRoomMinus;

    /** Textview for Display Result No. of Guest */
    TextView tvGuest;

    /** TextView for Display Result No. of Room */
    TextView tvRoom;

    /** Set Toolbar (Action Bar) */
    Toolbar toolbar;

    /** Initial Value for Guest */
    int guestValue = 1;

    /** Initial Value for Rooms */
    int roomValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_hotels_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbarHotelsDetails);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(HotelsDetailsActivity.this, LoginActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);*/
            }
        });

        // Set Title for Action Bar
        // getSupportActionBar().setTitle("Forgot Password");

        mPlace = PlaceData.placeList().get(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        // mList = (ListView) findViewById(R.id.list);
        mImageView = (ImageView) findViewById(R.id.placeImage);
        mTitle = (TextView) findViewById(R.id.textView);
        mTitleHolder = (LinearLayout) findViewById(R.id.placeNameHolder);
        mAddButton = (FloatingActionButton) findViewById(R.id.btn_add);
        //  mRevealView = (LinearLayout) findViewById(R.id.llEditTextHolder);
        //  mEditTextTodo = (EditText) findViewById(R.id.etTodo);

        btnTravel = (Button)findViewById(R.id.button_travel);
        btnRestaurants = (Button)findViewById(R.id.button_restaurants);
        btnMarket = (Button)findViewById(R.id.button_market);

        btnGuestPlus = (Button)findViewById(R.id.button_hotels_detail_guest_plus);
        btnGuestMinus = (Button)findViewById(R.id.button_hotels_detail_guest_minus);
        btnRoomPlus = (Button)findViewById(R.id.button_hotels_detail_room_plus);
        btnRoomMinus = (Button)findViewById(R.id.button_hotels_detail_room_minus);

        tvGuest = (TextView)findViewById(R.id.textview_hotels_detail_guest);
        tvRoom = (TextView)findViewById(R.id.textview_hotels_detail_room);

        btnTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnTravel.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnRestaurants.setTextColor(getResources().getColor(R.color.colorAccent));
                btnMarket.setTextColor(getResources().getColor(R.color.colorAccent));

            }
        });

        btnRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnTravel.setTextColor(getResources().getColor(R.color.colorAccent));
                btnRestaurants.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnMarket.setTextColor(getResources().getColor(R.color.colorAccent));

            }
        });

        btnMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnTravel.setTextColor(getResources().getColor(R.color.colorAccent));
                btnRestaurants.setTextColor(getResources().getColor(R.color.colorAccent));
                btnMarket.setTextColor(getResources().getColor(R.color.colorPrimary));

            }
        });

        btnGuestPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( guestValue < 16 ) {
                    tvGuest.setText(guestValue + " " + "Guest");
                }
                guestValue++;

            }
        });

        btnGuestMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( guestValue > 1 ) {
                    guestValue--;
                    tvGuest.setText(guestValue + " " + "Guest");
                }

            }
        });

        mAddButton.setOnClickListener(this);
        defaultColor = getResources().getColor(R.color.colorPrimaryDark);

        // setUpAdapter();
        loadPlace();
        windowTransition();
        getPhoto();
    }

    private void loadPlace() {
        mTitle.setText(mPlace.name);
        mImageView.setImageResource(mPlace.getImageResourceId(this));
    }

    private void windowTransition() {

    }

    private void addToDo(String todo) {
        mTodoList.add(todo);
    }

    private void getPhoto() {
        Bitmap photo = BitmapFactory.decodeResource(getResources(), mPlace.getImageResourceId(this));
    }

    private void colorize(Bitmap photo) {
    }

    private void applyPalette() {

    }

    /**
     * Floating Button Click functionality
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:

        }
    }

    private void revealEditText(LinearLayout view) {

    }

    private void hideEditText(final LinearLayout view) {

    }

    @Override
    public void onBackPressed() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(100);
        mAddButton.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mAddButton.setVisibility(View.GONE);
                finishAfterTransition();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
