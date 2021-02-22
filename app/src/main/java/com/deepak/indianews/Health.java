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
import com.deepak.indianews.api.details.BusinessNews;
import com.deepak.indianews.api.details.HeadlineAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Health extends Fragment {

    private List<Article> articles = new ArrayList<>();
    private HeadlineAdapter adapter;
    RecyclerView health_recycler_view;
    private static final String API_KEY = "f5f98e98f1e64d5e80f2a8b449dd8832";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_fragment, container, false);


        health_recycler_view = view.findViewById(R.id.health_recycler_view);
        health_recycler_view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        NewsDetails newsDetails = RetrofitInformation.getRetrofit().create(NewsDetails.class);
        Call<BusinessNews> businessNewsCall = newsDetails.getBusiness("in", "health", API_KEY);
        businessNewsCall.enqueue(new Callback<BusinessNews>() {
            @Override
            public void onResponse(Call<BusinessNews> call, Response<BusinessNews> response) {
                if (!response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(getContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else {
                    assert response.body() != null;
                    articles = response.body().getArticles();
                    adapter = new HeadlineAdapter(articles, getContext());
                }
                health_recycler_view.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BusinessNews> call, Throwable t) {

            }
        });
        return view;
    }
}
