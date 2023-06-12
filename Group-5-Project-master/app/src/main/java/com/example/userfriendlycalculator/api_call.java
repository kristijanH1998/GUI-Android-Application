package com.example.userfriendlycalculator;
// written by:Kristijan
// tested by: Kristijan
// debugged by:Kristijan, Anthony, Shane
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//source: https://www.youtube.com/watch?v=qzRKa8I36Ww

//Enables the connection with external API (Wolfram Alpha)
public class api_call {


    public static void main (String[] args){
        System.out.println(returnResult("9 + 8"));

    }

    //returnResult accepts the input from the Advanced Mode screen, and concatenates them with the http link, which is sent as an http request. Wolfram Alpha receives the request, does the
    //calculations, and returns the result, which is then saved in responseContent variable, and turned into a String type variable "result"
    public static String returnResult (String input){
        String result = "";

        input = input.replaceAll("\\s", "");
        input = "cos(0)";
        HttpURLConnection connection = null;
        BufferedReader reader;
        String line = "";
        StringBuffer responseContent = new StringBuffer();
        String url = "http://api.wolframalpha.com/v1/result?appid=XL554J-39UTLQWJTV&i=" + input;
        try {
            URL myurl = new URL(url);
            connection = (HttpURLConnection) myurl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();


            if(status>299){
                reader = new BufferedReader (new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }


        } catch(MalformedURLException exception){
            exception.printStackTrace();
        } catch(IOException exception2){
            exception2.printStackTrace();
        } finally {
            connection.disconnect();
        }
        result = responseContent.toString();
        System.out.println(result);
        return(result);

    }

}
