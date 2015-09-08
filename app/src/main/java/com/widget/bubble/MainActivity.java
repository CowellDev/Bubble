package com.widget.bubble;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.library.bubble.BubbleLayout;
import com.library.bubble.BubblesManager;
import com.library.bubble.OnInitializedCallback;

public class MainActivity extends AppCompatActivity {

    private Button mBAddBuble;
    private BubblesManager mBubblesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeBubblesManager();

        mBAddBuble = (Button) findViewById(R.id.button_add);
        mBAddBuble.setOnClickListener(mOnClickListener);


    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addNewBubble();
        }
    };

    /**
     * add new bubble
     */
    private void addNewBubble(){
        BubbleLayout bubbleLayout = (BubbleLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_bubble, null);
        bubbleLayout.setOnBubbleClickListener(new BubbleLayout.OnBubbleClickListener() {
            @Override
            public void onBubbleClick(BubbleLayout bubble) {
                Toast.makeText(getApplicationContext(), "Clicked !", Toast.LENGTH_SHORT).show();
            }
        });

        bubbleLayout.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
            @Override
            public void onBubbleRemoved(BubbleLayout bubble) {

            }
        });
        bubbleLayout.setShouldStickToWall(true);
        mBubblesManager.addBubble(bubbleLayout, 60, 20);
    }

    /**
     * initialize Bubbles Manager
     */
    private void initializeBubblesManager(){

        BubblesManager.Builder builder = new BubblesManager.Builder(MainActivity.this);
        builder.setTrashLayout(R.layout.layout_bubble_trash);
        builder.setInitializationCallback(new OnInitializedCallback() {
            @Override
            public void onInitialized() {
                addNewBubble();
            }
        });
        mBubblesManager = builder.build();
        mBubblesManager.initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBubblesManager.recycle();
    }
}
