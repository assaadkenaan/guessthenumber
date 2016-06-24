package fr.ak.number;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView txtTimer, txtTry;
	private TextView txtNumber1, txtNumber2, txtNumber3;
	private TextView txtHistory;
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	private Button btn9;
	private Button btnC;
	private int numberEssaie = 1;
	private int numberEssaieMax = 15;
	private int numberTry = 0;
	private long startTime = 0L;

	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	private int max = 999;
	private int min = 0;
	Random rand = new Random();
	private int numberToGuess = rand.nextInt(max - min) + min;
	private boolean gameIsEnd = false;
	private boolean win = false;

	private Timer timer;
	private TimerTask timerTask;

	private int minute = 0;
	private int seconde = 0;
	private int milliseconde = 0;

	private final Handler handler = new Handler();
	private List<Button> listButton = new ArrayList<Button>();

	private void adaptSizeButtons() {

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int screenwidth = size.x;
		int screenheight = size.y;

		final int width = (int) (screenwidth / 3.35f);
		final int height = (int) ((screenheight / 2.1f) / 4f);

		for (final Button btn : listButton) {
			btn.setLayoutParams(new LinearLayout.LayoutParams(width, height));
		}

		// Toast.makeText(this, "w : " + btn1.getWidth() + " h : " +
		// btn1.getHeight(), Toast.LENGTH_LONG).show();
		Log.e("MainActivity", "Screen (w : " + screenwidth + " h : "
				+ screenheight + ")");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtTimer = (TextView) findViewById(R.id.txtTimer);
		txtNumber1 = (TextView) findViewById(R.id.txtNumber1);
		txtNumber2 = (TextView) findViewById(R.id.txtNumber2);
		txtNumber3 = (TextView) findViewById(R.id.txtNumber3);
		txtHistory = (TextView) findViewById(R.id.txtHistory);
		txtTry = (TextView) findViewById(R.id.txtTry);
		btn0 = (Button) findViewById(R.id.btn0);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		btn8 = (Button) findViewById(R.id.btn8);
		btn9 = (Button) findViewById(R.id.btn9);
		btnC = (Button) findViewById(R.id.btnC);
		listButton.add(btn0);
		listButton.add(btn1);
		listButton.add(btn2);
		listButton.add(btn3);
		listButton.add(btn4);
		listButton.add(btn5);
		listButton.add(btn6);
		listButton.add(btn7);
		listButton.add(btn8);
		listButton.add(btn9);
		listButton.add(btnC);
		adaptSizeButtons();

		txtTimer.setText("00:00:000");

		txtTry.setText("0/" + numberEssaieMax + " " + "Try");
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("1");
			}
		});

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("2");

			}
		});
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("3");

			}
		});
		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("4");

			}
		});
		btn5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("5");

			}
		});
		btn6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("6");

			}
		});
		btn7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("7");

			}
		});
		btn8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("8");

			}
		});
		btn9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("9");

			}
		});
		btn0.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTextNumber("0");

			}
		});

		btnC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!txtNumber1.getText().equals("_")
						&& (txtNumber2.getText().equals("_"))
						&& (txtNumber3.getText().equals("_"))) {
					txtNumber1.setText("_");
				} else if (!txtNumber2.getText().equals("_")
						&& (txtNumber3.getText().equals("_"))) {
					txtNumber2.setText("_");

				}
			}
		});
	}

	private void updateTextNumber(String number) {

		if (gameIsEnd == true) {
			return;
		}

		if (txtNumber1.getText().equals("_")
				&& (txtTry.getText().equals("0/" + numberEssaieMax + " "
						+ "Try"))) {
			updateTime();
			txtNumber1.setText(number);
		} else if (txtNumber1.getText().equals("_")) {
			txtNumber1.setText(number);

		} else if (!txtNumber1.getText().equals("_")
				&& txtNumber2.getText().equals("_")) {
			txtNumber2.setText(number);
		} else if (!txtNumber1.getText().equals("_")
				&& !txtNumber2.getText().equals("_")
				&& txtNumber3.getText().equals("_")) {

			txtNumber3.setText(number);

			final String strNumberSaisie = txtNumber1.getText().toString()
					+ txtNumber2.getText().toString()
					+ txtNumber3.getText().toString();
			final int numberSaisie = Integer.valueOf(strNumberSaisie);

			numberEssaie++;
			numberTry++;
			txtTry.setText(String.valueOf(numberTry) + "/" + numberEssaieMax
					+ " " + "Try");

			if (numberEssaie > numberEssaieMax) {

				gameIsEnd = true;
			}

			if (numberSaisie == numberToGuess) {
				win = true;
				gameIsEnd = true;
			}
			if (txtTry.getText().equals(
					numberEssaieMax + "/" + numberEssaieMax + " " + "Try")) {
				gameIsEnd = true;
			}

			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {

					txtNumber1.setText("_");
					txtNumber2.setText("_");
					txtNumber3.setText("_");
					String strHistory = "Number "
							+ (numberSaisie > numberToGuess ? " < " : " > ")
							+ strNumberSaisie;

					txtHistory.setText(strHistory);

				}
			}, 250);

		}

		if (gameIsEnd == true) {
			txtNumber1.setText("_");
			txtNumber2.setText("_");
			txtNumber3.setText("_");

			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {

					CustomDialog cdd = new CustomDialog(MainActivity.this, win,
							numberToGuess);
					cdd.setCanceledOnTouchOutside(false);
					cdd.show();

				}
			}, 550);

			stopTimerTask();

			if (win) {
				updatBestIn();
			}
		}
	}

	private void updatBestIn() {
		BestTimeRegistered.updateBestTime(this, minute, seconde, milliseconde);
	}

	public void restart() {
		txtHistory.setText("");
		numberEssaie = 1;
		txtTry.setText("0/" + numberEssaieMax + " " + "Try");
		numberTry = 0;
		gameIsEnd = false;
		win = false;
		txtTimer.setText("00:00:00");
		numberToGuess = rand.nextInt(max - min) + min;
	}

	private void updateTime() {
		timer = new Timer();

		startTime = SystemClock.uptimeMillis();

		initializeTimerTask();
		// 25
		timer.schedule(timerTask, 1, 1);

	}

	public void initializeTimerTask() {

		timerTask = new TimerTask() {
			@Override
			public void run() {
				handler.post(new Runnable() {

					@Override
					public void run() {

						updatedTime = SystemClock.uptimeMillis() - startTime;

						seconde = (int) (updatedTime / 1000);

						minute = seconde / 60;

						seconde = seconde % 60;

						milliseconde = (int) (updatedTime % 1000);

						txtTimer.setText("" + String.format("%02d", minute)
								+ ":" + String.format("%02d", seconde) + ":"
								+ String.format("%03d", milliseconde));

					}

				});

			}

		};

	}

	public void stopTimerTask() {

		if (timer != null) {
			timer.cancel();
			timer = null;
		}

	}

	public void exit() {
		System.exit(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}
}
