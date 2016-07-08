package com.epicodus.wordgame;
import android.util.Log;

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
    public static void findRepos(String user, int page, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();
        String pageNo = page + "";
        String url = "https://api.github.com/users/" + user + "/repos?api_key=" + Constants.apiKey + "&page=" + pageNo + "&per_page=20";

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
            Log.d("GITHUBSERVICE1", jsonData);

            if (response.isSuccessful()) {
                JSONArray JSONresults = new JSONArray(jsonData);


                for (int i = 0; i < JSONresults.length(); i++) {
                    JSONObject currentRepo = JSONresults.getJSONObject(i);
                    String name = currentRepo.getString("name");
                    int size = currentRepo.getInt("size");
                    String languagePrimary = currentRepo.getString("language");
                    //secondaryLanguage eventually....

                    Repo repo = new Repo(name, size, languagePrimary, "");
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
