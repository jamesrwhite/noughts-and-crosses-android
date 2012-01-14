package com.JamesWhite.NoughtsAndCrosses;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class RemoteDatabase {

	public JSONArray getJSONArray() {

		// initialize
		InputStream input = null;
		String result = "";
		JSONArray jsonArray = null;

		// http post
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://jamesrwhite.co.uk/uni/programming/noughtsandcrosses/scores/GET/");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("app_secret",
					"61e059ec522ad650c96a5f0efef57e70"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			input = entity.getContent();

		}

		catch (Exception e) {

			Log.e("log_tag", "Error in http connection " + e.toString());

		}

		// convert response to string
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {

				sb.append(line + "\n");

			}

			input.close();
			result = sb.toString();

		}

		catch (Exception e) {

			Log.e("log_tag", "Error converting result " + e.toString());

		}

		// try parse the string to a JSON object
		try {

			jsonArray = new JSONArray(result);

		}

		catch (JSONException e) {

			Log.e("log_tag", "Error parsing data " + e.toString());

		}

		return jsonArray;

	}

	public void insertScore(String name, int score, int date) {

		// initialize
		InputStream input = null;
		String result = "";

		// http post
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://jamesrwhite.co.uk/uni/programming/noughtsandcrosses/scores/POST/");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
			nameValuePairs.add(new BasicNameValuePair("app_secret",
					"61e059ec522ad650c96a5f0efef57e70"));
			nameValuePairs.add(new BasicNameValuePair("name", name));
			nameValuePairs
					.add(new BasicNameValuePair("score", "" + score + ""));
			nameValuePairs.add(new BasicNameValuePair("date", "" + date + ""));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			input = entity.getContent();

		}

		catch (Exception e) {

			Log.e("log_tag", "Error in http connection " + e.toString());

		}

	}

}