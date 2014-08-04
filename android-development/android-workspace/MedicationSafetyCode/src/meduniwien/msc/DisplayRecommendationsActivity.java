package meduniwien.msc;

import meduniwien.msc.model.RecommendationRulesMain;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class DisplayRecommendationsActivity extends ActionBarActivity {
	private String htmlPage = "" ;
	private String version = "";
	private String code = "";
	
	@Override
   	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final WebView webview = new WebView(this);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setAllowUniversalAccessFromFileURLs(true);		
		setContentView(webview);
		if(savedInstanceState != null){
			String html = savedInstanceState.getString("html");
			final String version = savedInstanceState.getString("version");
			final String code = savedInstanceState.getString("code");
			if(html!=null && !html.isEmpty()){
				htmlPage = html;				
				webview.loadDataWithBaseURL("file:///android_asset/", htmlPage, "text/html", "UTF-8", null);
			}else{
				final ProgressDialog ringProgressDialog = ProgressDialog.show(this, "Please wait ...", "Execution process ...", true);
				ringProgressDialog.setCancelable(true);
	    	
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							htmlPage = RecommendationRulesMain.getHTMLRecommendations(version,code);
							webview.loadDataWithBaseURL("file:///android_asset/", htmlPage, "text/html", "UTF-8", null);
	    				} catch (Exception e) {
	    				}
						ringProgressDialog.dismiss();
					}
				}).start();
			}
		}else{
		
			final Intent intent = getIntent();
			
			final ProgressDialog ringProgressDialog = ProgressDialog.show(this, "Please wait ...", "Execution process ...", true);
			ringProgressDialog.setCancelable(true);
    	
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// Get the version and code from the intent
						version = intent.getStringExtra(MainActivity.EXTRA_VERSION);
						code = intent.getStringExtra(MainActivity.EXTRA_CODE);
						htmlPage = RecommendationRulesMain.getHTMLRecommendations(version,code);
						webview.loadDataWithBaseURL("file:///android_asset/", htmlPage, "text/html", "UTF-8", null);
		
    				} catch (Exception e) {
    				}
					ringProgressDialog.dismiss();
				}
			}).start();
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
	   super.onSaveInstanceState(outState);
	   outState.putString("html", htmlPage);
	   outState.putString("version",version);
	   outState.putString("code",code);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.main, menu);
		return true;*/
		
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.display_recommendations, menu);
	    return super.onCreateOptionsMenu(menu);
	}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	/*switch (item.getItemId()){
		case R.id.action_search:
			openSearch();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}*/
		/*int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);*/
		    	
    	int id = item.getItemId();
        if (id == R.id.action_warning) {
        	Context context = getApplicationContext();
        	CharSequence text = "This service is provided for research purposes only and comes without any warranty. (C) 2014";
        	int duration = Toast.LENGTH_LONG;
        	Toast toast = Toast.makeText(context, text, duration);
        	toast.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
              View rootView = inflater.inflate(R.layout.fragment_display_recommendations,
                      container, false);
              return rootView;
        }
    }
}
