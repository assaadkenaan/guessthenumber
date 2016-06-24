package fr.ak.number;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends Dialog {

	private MainActivity mainActivity;
	private Button btnOk;
	private Button btnCancel;
	private TextView txtNumberToGuess;
	private final int numberToGuess;
	private final boolean win;

	public CustomDialog(MainActivity context, final boolean win,
			final int numberToGuess) {
		super(context);
		this.mainActivity = context;
		this.numberToGuess = numberToGuess;
		this.win = win;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_dialog);
		if (win) {
			setTitle("Good job");
		} else {
			setTitle("Try again ?");
		}
		txtNumberToGuess = (TextView) findViewById(R.id.txtNumberToGuess);
		txtNumberToGuess.setText("The number is : " + numberToGuess);
		btnOk = (Button) findViewById(R.id.btnOk);
		btnOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mainActivity.restart();
				dismiss();
			}
		});

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mainActivity.exit();
				dismiss();
			}
		});

	}

}