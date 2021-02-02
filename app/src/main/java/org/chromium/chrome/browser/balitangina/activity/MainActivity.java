package org.chromium.chrome.browser.balitangina.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.chromium.chrome.browser.balitangina.bottomnavigationview.fragments.FragmentEntertainment;
import org.chromium.chrome.browser.balitangina.bottomnavigationview.fragments.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.chromium.chrome.browser.balitangina.R;
import org.chromium.chrome.browser.balitangina.adapter.MainArticleAdapter;
import org.chromium.chrome.browser.balitangina.bottomnavigationview.fragments.FragmentScience;
import org.chromium.chrome.browser.balitangina.bottomnavigationview.fragments.FragmentTechnology;
import org.chromium.chrome.browser.balitangina.model.Article;
import org.chromium.chrome.browser.balitangina.model.ResponseModel;
import org.chromium.chrome.browser.balitangina.rests.APIInterface;
import org.chromium.chrome.browser.balitangina.rests.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "aa4f56b9ae2246f1a21df133b226c4bb";

    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        final RecyclerView mainRecycler = findViewById(R.id.activity_main_rv);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mainRecycler.setLayoutManager(linearLayoutManager);

        final APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        Call<ResponseModel> call = apiService.getLatestNews("ph", "", API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                if (response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();
                    if (articleList.size() > 0) {
                        final MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(articleList, MainActivity.this);
                        mainArticleAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                        mainRecycler.setAdapter(mainArticleAdapter);
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseModel> call, Throwable t) {
                Log.e("out", t.toString());
            }

        });


        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_top_stories:
                        openFragment(new FragmentHome());

                        final APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
                        Call<ResponseModel> call = apiService.getLatestNews("ph", "", API_KEY);
                        call.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(retrofit2.Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                                if (response.body().getStatus().equals("ok")) {
                                    List<Article> articleList = response.body().getArticles();
                                    if (articleList.size() > 0) {
                                        final MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(articleList, MainActivity.this);
                                        mainArticleAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                                        mainRecycler.setAdapter(mainArticleAdapter);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(retrofit2.Call<ResponseModel> call, Throwable t) {
                                Log.e("out", t.toString());
                            }                                                            

                        });
                        return true;
                    case R.id.nav_tech:
                        openFragment(new FragmentTechnology());

                        final APIInterface apiService1 = ApiClient.getClient().create(APIInterface.class);
                        Call<ResponseModel> call1 = apiService1.getLatestNews("ph", "technology", API_KEY);
                        call1.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(retrofit2.Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                                if (response.body().getStatus().equals("ok")) {
                                    List<Article> articleList = response.body().getArticles();
                                    if (articleList.size() > 0) {
                                        final MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(articleList, MainActivity.this);
                                        mainArticleAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                                        mainRecycler.setAdapter(mainArticleAdapter);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(retrofit2.Call<ResponseModel> call, Throwable t) {
                                Log.e("out", t.toString());
                            }
                        });
                        return true;
                    case R.id.nav_science:
                         openFragment(new FragmentScience());

                         final APIInterface apiService2 = ApiClient.getClient().create(APIInterface.class);
                         Call<ResponseModel> call2 = apiService2.getLatestNews("ph", "science", API_KEY);
                         call2.enqueue(new Callback<ResponseModel>() {
                             @Override
                             public void onResponse(retrofit2.Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                                 if (response.body().getStatus().equals("ok")) {
                                     List<Article> articleList = response.body().getArticles();
                                     if (articleList.size() > 0) {
                                         final MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(articleList, MainActivity.this);
                                         mainArticleAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                                         mainRecycler.setAdapter(mainArticleAdapter);
                                     }
                                 }
                             }

                             @Override
                             public void onFailure(retrofit2.Call<ResponseModel> call, Throwable t) {
                                 Log.e("out", t.toString());
                             }
                         });
                        return true;
//                    case R.id.nav_science:
//                         openFragment(new FragmentScience());
//                        return true;
//                    case R.id.nav_entertainment:
//                         openFragment(new FragmentEntertainment());
//                        return true;
                }
                return false;
            }
        });
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onItemClick(int position, View view) {
        switch (view.getId()) {
            case R.id.article_adapter_ll_parent:
                Article article = (Article) view.getTag();
                if (!TextUtils.isEmpty(article.getUrl())) {
                    Log.e("clicked url", article.getUrl());
                    Intent webActivity = new Intent(this, WebActivity.class);
                    webActivity.putExtra("url", article.getUrl());
                    startActivity(webActivity);
                }
                break;
        }
    }
}
