package de.ecomeal.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import de.ecomeal.R;

/**
 * Created by LS on 26.05.2016.
 */
public class WebFragment extends BaseToolbarFragment {

    private WebView mWebView;
    private String url;

    public static WebFragment newInstance(String url) {
        WebFragment fragment = new WebFragment();
        Bundle bundle = new Bundle();
        fragment.url = url;
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        mWebView = (WebView) view.findViewById(R.id.webView);
//        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("http")) { // Could be cleverer and use a regex
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    getActivity().startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        return view;

    }


}
