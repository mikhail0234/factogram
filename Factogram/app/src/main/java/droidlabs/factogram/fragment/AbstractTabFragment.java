package droidlabs.factogram.fragment;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.view.View;


public class AbstractTabFragment extends Fragment{

    private String title;
    //private String text;

    protected Context context;
    protected View view;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /*public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    */

}
