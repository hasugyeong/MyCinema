package com.cookandroid.projectmycinema;

import android.app.DatePickerDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;



public class SecondActivity extends TabActivity {

    String url;
    ListViewAdapter adapter;
    private String selectdate;
    Button btndate,btnview;
    TextView date,textview,movieinfotv,text3,moviename;
    int mYear,mMonth,mDay;
    String newMonth;
    String newDay;
    ListView listview;
    ImageView imageView2;
    JSONArray movies = null;
    Button btn1,btn2,btn21,btn22,btn23,btn24;
    String user;
    WebView web;
    static String a,b,c,d,g,f;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("MY CINEMA");
        btndate=(Button)findViewById(R.id.btndate);
        btnview=(Button)findViewById(R.id.btnview);
        date=(TextView) findViewById(R.id.date);
        movieinfotv=(TextView)findViewById(R.id.movieinfo);
        moviename=(TextView)findViewById(R.id.moviename);
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        listview = (ListView)findViewById(R.id.listview1);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn21=(Button)findViewById(R.id.golottecinema);
        btn22=(Button)findViewById(R.id.gomegabox);
        btn23=(Button)findViewById(R.id.gocgv);
        btn24=(Button)findViewById(R.id.gomap);

        MainActivity mac=new MainActivity();
        user=mac.userName;
        text3 =(TextView)findViewById(R.id.text3);
        text3.setText("'"+user+"'"+"님 환영합니다.");



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(intent);

            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), webview.class);
                url="http://www.lottecinema.co.kr/LCHS/Contents/ticketing/movie-schedule.aspx";
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), webview.class);
                url="http://www.megabox.co.kr/?menuId=theater-detail&region=10&cinema=1372";
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), webview.class);
                url="http://www.cgv.co.kr/reserve/show-times/";
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), webview.class);
                url="https://map.naver.com/";
                intent.putExtra("url", url);
                startActivity(intent);

            }
        });


        btndate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("click");

                DatePickerDialog dpd=new DatePickerDialog(SecondActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;
                                date.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay));
                                if(mMonth<10)
                                    newMonth="0"+(mMonth+1);
                                else
                                    newMonth=Integer.toString(mMonth);

                                if(mDay<10)
                                    newDay="0"+(mDay-1);
                                else
                                    newDay=Integer.toString(mDay-1);
                                selectdate=Integer.toString(mYear)+newMonth+newDay;

                            }
                        },mYear,mMonth,mDay);
                dpd.show();
            }

        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                DailyBoxOffice item = (DailyBoxOffice) adapterView.getItemAtPosition(i);


                String x=item.getRank();
                String y=item.getMovieNm();
                String z=item.getMovieCd();
                String counts=item.getaudiAcc();

                StrictMode.enableDefaults();

                //movieinfo받아오는 부분
                boolean initem = false, mgenre=false,mopendt=false,mshowtm=false,mnation=false,mwatchGradeNm=false,mpeopleNm=false;
                String genreNm = "", openDt = "", showTm = "", nationNm = "", watchGradeNm = "", peopleNm="";
                try{
                    URL url =new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.xml?" +
                            "key=e945eac34bfb98a9116bee3f5300e77f" +
                            "&movieCd="+z);



                    XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
                    parserCreator.setNamespaceAware(true);
                    XmlPullParser parser = parserCreator.newPullParser();

                    parser.setInput(url.openStream(), null);

                    int parserEvent = parser.getEventType();
                    System.out.println("파싱시작합니다.");
                    while (parserEvent != XmlPullParser.END_DOCUMENT){
                        switch(parserEvent){
                            case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                                if(parser.getName().equals("genreNm")){
                                    mgenre = true;
                                }
                                if(parser.getName().equals("openDt")){
                                    mopendt = true;
                                }
                                if(parser.getName().equals("showTm")){
                                    mshowtm = true;
                                }
                                if(parser.getName().equals("nationNm")){
                                    mnation = true;
                                }
                                if(parser.getName().equals("watchGradeNm")){
                                    mwatchGradeNm = true;
                                }
                                if(parser.getName().equals("director")){
                                    mpeopleNm = true;
                                }


                                break;

                            case XmlPullParser.TEXT://parser가 내용에 접근했을때
                                if(mgenre){
                                    genreNm = parser.getText();
                                    mgenre = false;
                                    a=genreNm;

                                }
                                if(mopendt){
                                    openDt = parser.getText();
                                    mopendt = false;
                                    b=openDt;

                                }
                                if(mshowtm){
                                    showTm = parser.getText();
                                    mshowtm = false;
                                    c=showTm;

                                }
                                if(mnation){
                                    nationNm = parser.getText();
                                    mnation = false;
                                    d=nationNm;

                                }
                                if(mwatchGradeNm){
                                    watchGradeNm = parser.getText();
                                    mwatchGradeNm = false;
                                    g=watchGradeNm;

                                }
                                if(mpeopleNm){
                                    peopleNm = parser.getText();
                                    mpeopleNm = false;
                                    f=peopleNm;


                                }
                                System.out.println("장르"+genreNm);
                                break;
                            case XmlPullParser.END_TAG:
                                if(parser.getName().equals("movieInfo")){
                                    System.out.println("장르"+genreNm);
                                    initem = false;
                                }
                                break;
                        }
                        parserEvent = parser.next();
                    }
                } catch(Exception e){
                    System.out.println("에러");
                }


                Drawable ic=item.getIcon();
                AlertDialog.Builder dlg=new AlertDialog.Builder(SecondActivity.this);
                View dlgView=(View) View.inflate(
                        SecondActivity.this,
                        R.layout.dialog1,null);
                dlg.setView(dlgView);
                dlg.setTitle("영화 정보");
                moviename=(TextView)dlgView.findViewById(R.id.moviename);
                movieinfotv=(TextView)dlgView.findViewById(R.id.movieinfo);
                imageView2=(ImageView) dlgView.findViewById(R.id.imageView2);

                String str="";
                str=" | 장르 : "+a+ "\n"+" | 개봉 날짜 : " +b+ "\n"+" | 상영시간 :"+ c+"분 "
                        +"\n"+" | 제작국가 : "+d+ "\n"+" | 등급 : "+g+"\n | 감독 : "+ f+"\n | 누적관객수 : "+counts +" 명";
                movieinfotv.setText(str);
                moviename.setText(y);
                imageView2.setImageDrawable(ic);
                dlg.setNegativeButton("닫기",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });//닫기 버튼
                dlg.show();
            }
        });



        //탭 부분
        TabHost tabHost;
        tabHost = getTabHost();

        TabHost.TabSpec tab1=tabHost.newTabSpec("tab1").setIndicator("일간박스오피스");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2=tabHost.newTabSpec("tab2").setIndicator("상영관찾기");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3=tabHost.newTabSpec("tab3").setIndicator("My");
        tab3.setContent(R.id.tab3);
        tabHost.addTab(tab3);

        tabHost.setCurrentTab(0);//초기 탭 설정


        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();

            }
        });

        if (AppHelper.requestqueue == null)
            AppHelper.requestqueue = Volley.newRequestQueue(getApplicationContext());



    }//onCreate

    public void sendRequest(){
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?" +
                "key=e945eac34bfb98a9116bee3f5300e77f" +
                "&targetDt="+selectdate;

        // 문자열을 주고 받기 위한 request 객체는 StringRequest 클래스를 이용해 만들 수 있다.
        // 요청방식, URL, 리스너, 에러 리스너를 전달한다.
        // 요청방식: GET, POST 메서드
        // URL: 웹서버의 URL 정보
        // 리스너: 응답을 성공적으로 받았을 때 리스너의 onResponse 메소드를 자동으로 호출
        // 에러 리스너: 에러가 발생했을 때 호출


        StringRequest request = new StringRequest(

                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new GsonBuilder().create();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject json =  (JSONObject) jsonObject.get("boxOfficeResult");
                            JSONArray array = (JSONArray)json.get("dailyBoxOfficeList");
                            adapter = new ListViewAdapter();
                            for (int i = 0; i < array.length(); i++) {
                                String nrank,nmovienm,moviecode,count;

                                JSONObject movieObject = array.getJSONObject(i);
                                nrank=Integer.toString(movieObject.getInt("rank"));
                                nmovienm=movieObject.getString("movieNm");
                                moviecode=movieObject.getString("movieCd");
                                count=movieObject.getString("audiAcc");

                                adapter.addItem(
                                        ContextCompat.getDrawable(SecondActivity.this,R.drawable.ic_movie_black_24dp)
                                        ,nrank,nmovienm,moviecode,count);
                                System.out.println(movieObject.getString("movieNm"));
                            }
                            listview.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 만약 POST 방식에서 전달할 요청 파라미터가 있다면 HashMap 객체에 넣어준다.
                Map<String, String> map = new HashMap<>();
                return map;
            }
        };

        request.setShouldCache(false); // 이전 결과가 있더라도 새로 요청해서 응답을 보여줌
        AppHelper.requestqueue.add(request); // request queue 에 request 객체를 넣어준다.
    }



}

