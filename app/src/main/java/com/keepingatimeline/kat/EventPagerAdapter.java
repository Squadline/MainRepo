package com.keepingatimeline.kat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Trevor on 5/19/2016.
 */
public class EventPagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs = 3;
    AddPhotoFragment tab1;
    AddQuoteFragment tab2;
    AddTextFragment tab3;
    private String tabTitles[] = new String[] {"Photo", "Quote", "Text"};

    public EventPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        //this.numOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new AddPhotoFragment();
                return tab1;
            case 1:
                tab2 = new AddQuoteFragment();
                return tab2;
            case 2:
                tab3 = new AddTextFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    public void emptyTexts(int position) {
        switch (position) {
            case 0:
                tab1.emptyTexts();
                break;
            case 1:
                tab2.emptyTexts();
                break;
            case 2:
                tab3.emptyTexts();
                break;
            default:
                break;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Generate title based on item position
        return tabTitles[position];
    }
}
