package fr.ak.number;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity {
	private Button btnPlay;
	private Button btnTrick;
	private Button btnAbout;
	private Button btnRate;
	private TextView txtBestIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnTrick = (Button) findViewById(R.id.btnTrick);
		btnAbout = (Button) findViewById(R.id.btnAbout);
		btnRate = (Button) findViewById(R.id.btnRate);
		txtBestIn = (TextView) findViewById(R.id.txtBestIn);

		btnPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, MainActivity.class);
				startActivity(i);
			}
		});
		btnTrick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, TrickActivity.class);
				startActivity(i);

			}
		});
		btnAbout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, AboutActivity.class);
				startActivity(i);

			}
		});
		btnRate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Intent localIntent = new Intent(
						"android.intent.action.VIEW");
				localIntent.setData(Uri
						.parse("market://details?id=fr.ak.number"));
				HomeActivity.this.startActivity(localIntent);

			}
		});
		txtBestIn.setText(BestTimeRegistered.getBestTime(this));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		txtBestIn.setText(BestTimeRegistered.getBestTime(this));
	}
}
