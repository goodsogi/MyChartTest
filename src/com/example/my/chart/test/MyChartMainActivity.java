package com.example.my.chart.test;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MyChartMainActivity extends Activity {
	// 지점 그래프용 데이터
	double[] graph_top_values_test = { 84.0, 86.0, 88.0, 90.0, 92.0 };

	double[] graph_values_test1 = { 208.7, 34.2, 46.4, 47.5, 30.8, 50.0 };
	double[] graph_values_test2 = { 88.8, 87.1, 88.3, 91.4, 91.3, 86.5 };
	double[] graph_values_test3 = { 185.4, 29.8, 40.9, 43.4, 28.1, 43.2 };

	double[] graph_bottom_values_test = { 0.0, 100.0, 200.0, 300.0 };

	String[] y_labels = { "합계", "중부2", "서울2", "서남2", "부산2", "경인2" };
	final static int BRANCH_TOP_MAX = 100;
	final static int BRANCH_BOTTOM_MAX = 300;

	// 영업소 그래표용 데이터
	double[] shop_graph_top_values = { 0.0, 50.0, 100.0, 150.0, 200.0 };

	double[] shop_graph_values1 = { 4995, 515, 409, 457, 532, 345, 507, 722,
			298, 354, 493, 363 };
	double[] shop_graph_values2 = { 86.5, 96.3, 84.0, 93.5, 88.6, 87.9, 83.0,
			82.7, 85.4, 78.2, 83.0, 89.2 };
	double[] shop_graph_values3 = { 4323, 496, 343, 427, 471, 303, 421, 597,
			254, 277, 409, 324 };

	double[] shop_bottom_values = { 0.0, 5000, 10000, 15000 };

	String[] shop_y_labels = { "합계", "(과)제주", "(과)일산", "(과)은평", "(과)용산",
			"(과)영등포", "(과)안산", "(과)서인천", "(과)부평", "(과)남동", "(과)금천", "(과)광명" };

	final static int SHOP_TOP_MAX = 200;
	final static int SHOP_BOTTOM_MAX = 15000;

	// 사원 그래표용 데이터
	double[] salesman_graph_top_values = { 0.0, 50.0, 100.0, 150.0, 200.0 };

	double[] salesman_graph_values1 = { 457000, 37000, 70400, 38300, 31500,
			42200, 76500, 39500, 33000, 32600, 26000, 30000 };
	double[] salesman_graph_values2 = { 93.4, 77.5, 96.2, 86.7, 90.7, 96.4,
			102.6, 93.4, 114.2, 83.0, 71.9, 97.3 };
	double[] salesman_graph_values3 = { 426849, 28679, 67702, 33206, 28585,
			40670, 78503, 36896, 37670, 27063, 18697, 29178 };

	double[] salesman_bottom_values = { 0.0, 200000, 400000, 600000, 800000 };

	String[] salesman_y_labels = { "합계", "주정훈", "조창희", "정진구", "임대규", "이종진",
			"오종현", "심철성", "박영효", "김병수", "김두용", "김근영" };

	final static int SALESMAN_TOP_MAX = 200;
	final static int SALESMAN_BOTTOM_MAX = 800000;

	double BAR_MAZ_SIZE;

	final static int BRANCH_LEVEL = 0;
	final static int SHOP_LEVEL = 1;
	final static int SALESMAN = 2;

	private int level = SHOP_LEVEL;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_chart_main);
		// 막대 바 최대 크기를 픽셀로 구합니다(220dip)
		BAR_MAZ_SIZE = convertDipToPx(this, 220);

		// 지점단위 그래프를 그립니다
		drawGraph(graph_top_values_test, y_labels, graph_values_test1,
				graph_values_test2, graph_values_test3,
				graph_bottom_values_test, BRANCH_TOP_MAX, BRANCH_BOTTOM_MAX);

	}

	private int convertDipToPx(Context context, int dip) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

	private void drawGraph(double[] top_values, String[] y_labels,
			double[] bar_values1, double[] bar_values2, double[] bar_values3,
			double[] bottom_values, int top_max, int bottom_max) {

		final ViewGroup parent = (ViewGroup) findViewById(R.id.scrollview_container);

		// 상단 바에 값을 삽입합니다.
		TextView top_graph_value1 = (TextView) findViewById(R.id.graph_top_value1);
		TextView top_graph_value2 = (TextView) findViewById(R.id.graph_top_value2);
		TextView top_graph_value3 = (TextView) findViewById(R.id.graph_top_value3);
		TextView top_graph_value4 = (TextView) findViewById(R.id.graph_top_value4);
		TextView top_graph_value5 = (TextView) findViewById(R.id.graph_top_value5);

		top_graph_value1.setText(top_values[0] + "%");
		top_graph_value2.setText(top_values[1] + "%");
		top_graph_value3.setText(top_values[2] + "%");
		top_graph_value4.setText(top_values[3] + "%");
		top_graph_value5.setText(top_values[4] + "%");

		// 막대 바를 생성합니다
		for (int i = 0; i < bar_values1.length; i++) {

			View bar_graph = LayoutInflater.from(getBaseContext()).inflate(
					R.layout.graph_bar, null);
			// y 라벨을 지정합니다
			TextView y_label = (TextView) bar_graph.findViewById(R.id.y_label);
			y_label.setText(y_labels[i]);

			// y 라벨을 클릭하면 하위 단계 통계 그래프로 이동합니다
			y_label.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					switch (level) {
					case SHOP_LEVEL:
						// 그래프를 깨끗하게 삭제하고 영업소 단위 그래프를 그립니다.
						parent.removeAllViews();
						drawGraph(shop_graph_top_values, shop_y_labels,
								shop_graph_values1, shop_graph_values2,
								shop_graph_values3, shop_bottom_values,
								SHOP_TOP_MAX, SHOP_BOTTOM_MAX);

						// 그래프 타이틀 텍스트를 변경합니다
						TextView graph_title1 = (TextView) findViewById(R.id.graph_title1);
						TextView graph_title2 = (TextView) findViewById(R.id.graph_title2);

						graph_title1.setText("영업소 단위");
						graph_title2.setText("[단위:백만]");

						level = SALESMAN;
						break;
					case SALESMAN:
						// 그래프를 깨끗하게 삭제하고 사원 단위 그래프를 그립니다.
						parent.removeAllViews();
						drawGraph(salesman_graph_top_values, salesman_y_labels,
								salesman_graph_values1, salesman_graph_values2,
								salesman_graph_values3, salesman_bottom_values,
								SALESMAN_TOP_MAX, SALESMAN_BOTTOM_MAX);

						// 그래프 타이틀 텍스트를 변경합니다
						TextView graph_title11 = (TextView) findViewById(R.id.graph_title1);
						TextView graph_title22 = (TextView) findViewById(R.id.graph_title2);

						graph_title11.setText("사원단위");
						graph_title22.setText("[단위:천원]");
						break;

					}
				}
			});

			// 막대 바의 크기를 지정합니다
			ImageView bar_img1 = (ImageView) bar_graph
					.findViewById(R.id.bar_img1);
			int width_bar_img1 = (int) (BAR_MAZ_SIZE * bar_values1[i] / bottom_max);
			bar_img1.setLayoutParams(new LayoutParams(width_bar_img1,
					LayoutParams.MATCH_PARENT));

			ImageView bar_img2 = (ImageView) bar_graph
					.findViewById(R.id.bar_img2);
			int width_bar_img2 = (int) (BAR_MAZ_SIZE * bar_values2[i] / top_max);
			bar_img2.setLayoutParams(new LayoutParams(width_bar_img2,
					LayoutParams.MATCH_PARENT));

			ImageView bar_img3 = (ImageView) bar_graph
					.findViewById(R.id.bar_img3);
			int width_bar_img3 = (int) (BAR_MAZ_SIZE * bar_values3[i] / bottom_max);
			bar_img3.setLayoutParams(new LayoutParams(width_bar_img3,
					LayoutParams.MATCH_PARENT));

			// 막대바의 값을 삽입합니다
			TextView bar_value1 = (TextView) bar_graph
					.findViewById(R.id.bar_value1);
			bar_value1.setText(formatDouble(bar_values1[i]));

			TextView bar_value2 = (TextView) bar_graph
					.findViewById(R.id.bar_value2);
			bar_value2.setText(bar_values2[i] + "%");

			TextView bar_value3 = (TextView) bar_graph
					.findViewById(R.id.bar_value3);
			bar_value3.setText(formatDouble(bar_values3[i]));

			// 하단에 여백을 삽입합니다.

			parent.addView(bar_graph);
		}

		// 하단 바에 값을 삽입합니다
		TextView bottom_graph_value1 = (TextView) findViewById(R.id.graph_bottom_value1);
		TextView bottom_graph_value2 = (TextView) findViewById(R.id.graph_bottom_value2);
		TextView bottom_graph_value3 = (TextView) findViewById(R.id.graph_bottom_value3);
		TextView bottom_graph_value4 = (TextView) findViewById(R.id.graph_bottom_value4);

		bottom_graph_value1.setText(formatDouble(bottom_values[0]));
		bottom_graph_value2.setText(formatDouble(bottom_values[1]));
		bottom_graph_value3.setText(formatDouble(bottom_values[2]));
		bottom_graph_value4.setText(formatDouble(bottom_values[3]));

		// parent.addView(view);

		// LinearLayout layout = (LinearLayout)
		// findViewById(R.id.scrollview_container);
		//
		// LinearLayout linearLayout = new
		// LinearLayout(MyChartMainActivity.this);
		// linearLayout.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		// linearLayout.setOrientation(LinearLayout.VERTICAL);
		// linearLayout.setPadding(50, 20, 20, 50);
		// TextView chat = new TextView(MyChartMainActivity.this);
		// chat.setTextSize(50);
		// chat.setTextColor(Color.BLACK);
		// chat.setPadding(50, 30, 50, 30);
		// chat.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		// chat.setText("테스트");
		//
		// linearLayout.addView(chat);
		//
		// layout.addView(linearLayout);

		// LinearLayout container = (LinearLayout)
		// findViewById(R.id.scrollview_container);
		//
		// // 그래프 상단 바를 생성합니다
		// View view = LayoutInflater.from(getBaseContext()).inflate(
		// R.layout.graph_top, null);
		//
		// LayoutInflater inflater = (LayoutInflater)
		// getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//
		// View layout = inflater.inflate(R.layout.graph_top, null);
		//
		// // View layout = inflater.inflate(R.layout.graph_top,
		// // (ViewGroup) findViewById(R.id.container_graph_top));
		//
		// // container.addView(layout);
		//
		// //상단 바에 값을 삽입합니다.
		// TextView top_graph_value1 = (TextView)
		// view.findViewById(R.id.graph_top_value1);
		// TextView top_graph_value2 = (TextView)
		// view.findViewById(R.id.graph_top_value2);
		// TextView top_graph_value3 = (TextView)
		// view.findViewById(R.id.graph_top_value3);
		// TextView top_graph_value4 = (TextView)
		// view.findViewById(R.id.graph_top_value4);
		// TextView top_graph_value5 = (TextView)
		// view.findViewById(R.id.graph_top_value5);
		//
		//
		// top_graph_value1.setText("테스트");
		// top_graph_value1.setText(graph_top_values_test[1] + "%");
		// top_graph_value2.setText(graph_top_values_test[1] + "%");
		// top_graph_value3.setText(graph_top_values_test[2] + "%");
		// top_graph_value4.setText(graph_top_values_test[3] + "%");
		// top_graph_value5.setText(graph_top_values_test[4] + "%");
		//
		// container.addView(layout);
		// scollview_container.addView(view);

		// LinearLayout top_bar = (LinearLayout)
		// layout.findViewById(R.id.container);
		// //상단 바에 값을 삽입합니다.
		// TextView top_graph_value1 = (TextView) top_bar.getChildAt(0);
		// TextView top_graph_value2 = (TextView) top_bar.getChildAt(1);
		// TextView top_graph_value3 = (TextView) top_bar.getChildAt(2);
		// TextView top_graph_value4 = (TextView) top_bar.getChildAt(3);
		// TextView top_graph_value5 = (TextView) top_bar.getChildAt(4);
		//
		// top_graph_value1.setText(graph_top_values_test[0] + "%");
		// top_graph_value2.setText(graph_top_values_test[1] + "%");
		// top_graph_value3.setText(graph_top_values_test[2] + "%");
		// top_graph_value4.setText(graph_top_values_test[3] + "%");
		// top_graph_value5.setText(graph_top_values_test[4] + "%");
		//
		// 아래는 원래 코드입니다
		// LinearLayout top_bar = (LinearLayout)
		// layout.findViewById(R.id.container_graph_bar);
		// //scrollview에 상단 바를 추가합니다
		// scollview_container.addView(top_bar);
		//
		//
		// //상단 바에 값을 삽입합니다.
		// TextView top_graph_value1 = (TextView)
		// top_bar.findViewById(R.id.graph_top_value1);
		// TextView top_graph_value2 = (TextView)
		// top_bar.findViewById(R.id.graph_top_value2);
		// TextView top_graph_value3 = (TextView)
		// top_bar.findViewById(R.id.graph_top_value3);
		// TextView top_graph_value4 = (TextView)
		// top_bar.findViewById(R.id.graph_top_value4);
		// TextView top_graph_value5 = (TextView)
		// layout.findViewById(R.id.graph_top_value5);
		//
		// top_graph_value1.setText(graph_top_values_test[0] + "%");
		// top_graph_value2.setText(graph_top_values_test[1] + "%");
		// top_graph_value3.setText(graph_top_values_test[2] + "%");
		// top_graph_value4.setText(graph_top_values_test[3] + "%");
		// top_graph_value5.setText(graph_top_values_test[4] + "%");

		// //막대 바를 생성합니다
		// for (int i =0; i < graph_values_test1.length; i++) {
		//
		// View bar_graph = inflater.inflate(R.layout.graph_bar,
		// (ViewGroup) findViewById(R.id.container_graph_bar));
		// //y 라벨을 지정합니다
		// TextView y_label = (TextView) bar_graph.findViewById(R.id.y_label);
		// y_label.setText(y_labels[i]);
		//
		// //막대 바의 크기를 지정합니다
		// ImageView bar_img1 = (ImageView)
		// bar_graph.findViewById(R.id.bar_img1);
		// int width_bar_img1 = (int) (BAR_MAZ_SIZE * graph_values_test1[i]
		// /300);
		// bar_img1.setLayoutParams(new LayoutParams(width_bar_img1,
		// LayoutParams.MATCH_PARENT));
		//
		// ImageView bar_img2 = (ImageView)
		// bar_graph.findViewById(R.id.bar_img2);
		// int width_bar_img2 = (int) (BAR_MAZ_SIZE * graph_values_test2[i]
		// /300);
		// bar_img2.setLayoutParams(new LayoutParams(width_bar_img2,
		// LayoutParams.MATCH_PARENT));
		//
		// ImageView bar_img3 = (ImageView)
		// bar_graph.findViewById(R.id.bar_img3);
		// int width_bar_img3 = (int) (BAR_MAZ_SIZE * graph_values_test3[i]
		// /300);
		// bar_img3.setLayoutParams(new LayoutParams(width_bar_img3,
		// LayoutParams.MATCH_PARENT));
		//
		// scollview_container.addView(bar_graph);
		// }
		//
		// //하단 바를 scrollview에 삽입합니다
		// View bottom_bar = inflater.inflate(R.layout.graph_bottom,
		// (ViewGroup) findViewById(R.id.container));
		//
		// //하단 바에 값을 삽입합니다
		// TextView bottom_graph_value1 = (TextView)
		// bottom_bar.findViewById(R.id.graph_bottom_value1);
		// TextView bottom_graph_value2 = (TextView)
		// bottom_bar.findViewById(R.id.graph_bottom_value2);
		// TextView bottom_graph_value3 = (TextView)
		// bottom_bar.findViewById(R.id.graph_bottom_value3);
		// TextView bottom_graph_value4 = (TextView)
		// bottom_bar.findViewById(R.id.graph_bottom_value4);
		//
		// bottom_graph_value1.setText(graph_bottom_values_test[0] + "%");
		// bottom_graph_value2.setText(graph_bottom_values_test[1] + "%");
		// bottom_graph_value3.setText(graph_bottom_values_test[2] + "%");
		// bottom_graph_value4.setText(graph_bottom_values_test[3] + "%");
		//
		// scollview_container.addView(bottom_bar);

	}

	private String formatDouble(double value) {

		DecimalFormat decimalFormat = new DecimalFormat("##,###,###");
		String formattedValue = decimalFormat.format(value);

		return formattedValue;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_my_chart_main, menu);
		return true;
	}
}
