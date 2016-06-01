package com.keepingatimeline.kat;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AddEvent extends AppCompatActivity {

    private String timelineID;
    private String timelineName;
    private ViewPager viewPager;
    private EventPagerAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.keepingatimeline.kat.R.layout.activity_add_event);
        Firebase.setAndroidContext(this);

        // Uses a Toolbar as an ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView nextText = (TextView) findViewById(R.id.nextText);
        TextView cancelText = (TextView) findViewById(R.id.cancelText);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                timelineID = null;
                timelineName = "";
            } else {
                timelineID = extras.getString("Timeline ID");
                timelineName = extras.getString("Timeline Name");
            }
        } else {
            timelineID = (String) savedInstanceState.getSerializable("Timeline ID");
            timelineName = (String) savedInstanceState.getSerializable("Timeline Name");
        }

        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.eventTabs);

        viewPager = (ViewPager) findViewById(R.id.eventPager);
        eventAdapter = new EventPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(eventAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.grey500), Color.WHITE);

        nextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventPagerAdapter fragViewer = (EventPagerAdapter) viewPager.getAdapter();
                int position = viewPager.getCurrentItem();
                String[] data = fragViewer.getData(position);
                Firebase ref = Vars.getTimeline(timelineID);
                ref = ref.child("Events");
                ref = ref.push();
                Event event = new Event();
                event.setKey(ref.getKey());

                switch(position) {
                    case 0:
                        if (data[3] == null) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Please select a picture.", Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }
                        event.setType("photo");
                        event.setTitle(data[0]);
                        event.setDate(data[1]);
                        event.setString1(data[2]);
                        event.setString2(data[3]);
                        break;
                    case 1:
                        event.setType("quote");
                        event.setTitle(data[0]);
                        event.setDate(data[1]);
                        event.setString1(parseQuote(data[2]));
                        event.setString2(parseSpeaker(data[3]));
                        break;
                    case 2:
                        event.setType("text");
                        event.setTitle(data[0]);
                        event.setDate(data[1]);
                        event.setString1(data[2]);
                        break;
                    default:
                        event.setType("text");
                        break;
                }

                Vars.getTimeline(timelineID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String todaysDate = DateGen.getCurrentDate();
                        Vars.getTimeline(timelineID).child("LastModified").setValue(todaysDate);
                        for(DataSnapshot users : dataSnapshot.child("Users").getChildren()) {
                            Vars.getUser(users.getKey()).child("Timelines/" + timelineID + "/LastModified").setValue(todaysDate);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

                ref.setValue(event);
                finish();
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                EventPagerAdapter access = (EventPagerAdapter) viewPager.getAdapter();
                access.emptyTexts(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private String parseQuote(String quote) {
        if(quote.length() > 0 && quote.charAt(0) == '\"') {
            quote = quote.substring(1);
        }
        if(quote.length() > 0 && quote.charAt(quote.length()-1) == '\"') {
            quote = quote.substring(0, quote.length()-1);
        }
        return quote;
    }

    private String parseSpeaker(String speaker) {
        if(speaker.length() > 0 && speaker.charAt(0) == '-') {
            speaker = speaker.substring(1);
        }
        return speaker;
    }
}
