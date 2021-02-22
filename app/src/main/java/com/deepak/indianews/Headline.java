package com.deepak.indianews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepak.indianews.api.details.Article;
import com.deepak.indianews.api.details.HeadlineAdapter;
import com.deepak.indianews.api.details.Headlines;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Headline extends Fragment {
    private List<Article> articles = new ArrayList<>();
    private HeadlineAdapter adapter;
    RecyclerView headline_recycler_view;
    private static final String API_KEY = "f5f98e98f1e64d5e80f2a8b449dd8832";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.headline_fragment, container, false);
        headline_recycler_view = view.findViewById(R.id.headline_recycler_view);
        headline_recycler_view.setLayoutManager(new LinearLayoutManager(view.getContext()));


        NewsDetails newsDetails = RetrofitInformation.getRetrofit().create(NewsDetails.class);
        Call<Headlines> headlinesCall = newsDetails.getHeadline("in", API_KEY);
        headlinesCall.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (!response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(getContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else {
                    assert response.body() != null;
                    articles = response.body().getArticles();
                    adapter = new HeadlineAdapter(articles, getContext());
                }
                headline_recycler_view.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                t.printStackTrace();

            }
        });
        return view;
    }
}
