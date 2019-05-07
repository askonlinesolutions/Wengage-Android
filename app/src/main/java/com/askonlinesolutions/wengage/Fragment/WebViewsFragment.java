package com.askonlinesolutions.wengage.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;
import com.askonlinesolutions.wengage.utils.Constants;
import com.mukesh.tinydb.TinyDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewsFragment extends Fragment implements View.OnClickListener {
    private TextView headerTV, text;
    private LinearLayout home3_cross;
    private String headerTitle;
    private TinyDB tinyDB;
    private WebView webView;

    public WebViewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_web_view, container, false);
        tinyDB = new TinyDB(getActivity());
        headerTitle = tinyDB.getString(Constants.FROM_COMING);
        initUI(view);

        return view;
    }

    private void initUI(View view) {
        headerTV = view.findViewById(R.id.headerTV);
        text = view.findViewById(R.id.text);
        webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        if (headerTitle.equals("Demo")) {
            headerTV.setText("Demo");
            text.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        } else if (headerTitle.equals("About us")) {

            text.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            headerTV.setText("About us");
            webView.loadUrl("https://wengageapp.com/our-management-team/https://wengageapp.com/about/");
        } else if (headerTitle.equals("Faq")) {

            text.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            headerTV.setText("FAQ");
            webView.loadUrl("https://wengageapp.com/faqs/");
        } else if (headerTitle.equals("Policy")) {

            text.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            headerTV.setText("Privacy Policies");
            webView.loadUrl("https://wengageapp.com/privacy-policy/");
        } else if (headerTitle.equals("Terms & Conditions")) {

            text.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            headerTV.setText("Terms & Conditions");
            webView.loadUrl("https://wengageapp.com/terms-and-conditions/");
        } else if (headerTitle.equals("Terms & Guidelines")) {

            text.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            headerTV.setText("Terms & Guidelines");
            webView.loadUrl("https://wengageapp.com/terms-and-guidelines/");
        }
        webView.setWebViewClient(new MyCustomWebViewClient());
        home3_cross = view.findViewById(R.id.home3_cross);
        home3_cross.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        getActivity().onBackPressed();
    }

    class MyCustomWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;

        }
    }
}
