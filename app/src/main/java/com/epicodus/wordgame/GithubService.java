package com.epicodus.wordgame;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by noahkittleson on 7/8/16.
 */
public class GithubService {
    public static void findRepos(String word, int page, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();
        String pageNo = page + "";
        String url = "https://api.github.com/users/" + word + "/repos?api_key=" + Constants.apiKey + "&page=" + pageNo + "&per_page=20";

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static ArrayList<Repo> processResults(Response response) {
        ArrayList<Repo> repos = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if (response.isSuccessful()) {
                JSONObject totalJSON = new JSONObject(jsonData);
                JSONArray JSONresults = totalJSON.getJSONArray("");

                for (int i = 0; i < JSONresults.length(); i++) {
                    JSONObject currentRepo = JSONresults.getJSONObject(i);
//                    String title = filmJSON.getString("title");
//                    String date = filmJSON.getString("release_date");
//                    String year = date.substring(0, 4);
//                    int id = filmJSON.getInt("id");
//                    double rating = filmJSON.getDouble("vote_average");
//                    String synopsis = filmJSON.getString("overview");
//                    String poster = filmJSON.getString("poster_path");

                    Repo repo = new Repo(/*whatever info I need*/);
                    repos.add(repo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repos;
    }

}
