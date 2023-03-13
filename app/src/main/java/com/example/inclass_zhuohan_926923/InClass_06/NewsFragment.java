package com.example.inclass_zhuohan_926923.InClass_06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inclass_zhuohan_926923.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    private static final String CATEGORY_KEY = "category";
    private static final String COUNTRY_KEY = "country";
    private RecyclerView newsRecyclerView;
    private final OkHttpClient client = new OkHttpClient();
    private Spinner categorySpinner;

    private Spinner countrySpinner;
    private Articles articles;

    private ArrayList<News> news;

    NewsFragment.NewsFragmentListener listener;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String category, String country) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_KEY, category);
        args.putString(COUNTRY_KEY, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news2, container, false);

        // Initialize the spinner and adapter
        categorySpinner = view.findViewById(R.id.InClass06_categorySpinner);
        countrySpinner = view.findViewById(R.id.InClass06_countrySpinner);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.categories, android.R.layout.simple_spinner_item);
        categorySpinner.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.countries, android.R.layout.simple_spinner_item);
        countrySpinner.setAdapter(countryAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int item, long l) {
                String category = categoryAdapter.getItem(item).toString().toLowerCase();
                String country = countryAdapter.getItem(item).toString().toLowerCase();
                loadNews(category, country);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int item, long l) {
                String category = categoryAdapter.getItem(item).toString().toLowerCase();
                String country = countryAdapter.getItem(item).toString().toLowerCase();
                loadNews(category, country);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String category = getArguments().getString(CATEGORY_KEY);
        int categoryIndex = categoryAdapter.getPosition(category);
        categorySpinner.setSelection(categoryIndex);

        String country = getArguments().getString(COUNTRY_KEY);
        int countryIndex = countryAdapter.getPosition(country);
        countrySpinner.setSelection(countryIndex);

        return view;
    }

    public void loadNews(String category, String country){

        String url = "https://newsapi.org/v2/top-headlines?country" + country + "&category=" + category + "&apiKey=eae326f1b23f4f14b7b4d45e24d6568c";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getContext() ,"No Internet", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    Gson gsonData = new Gson();
                    articles = gsonData.fromJson(response.body().charStream(), Articles.class);
                    listener.onNewsLoaded(articles.getArticles());

                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof NewsFragment.NewsFragmentListener){
            listener = (NewsFragment.NewsFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString() + "must implement ISpinnerItemSelected");
        }
    }

    public interface NewsFragmentListener{
        void onNewsLoaded(ArrayList<News> news);
    }
}