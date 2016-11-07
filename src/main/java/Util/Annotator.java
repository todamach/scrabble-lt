package Util;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by harol on 10/25/2016.
 */
public class Annotator {

    public Annotator(){}

    public static boolean checkWord(String word){

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=---011000010111000001101001");
        RequestBody body = RequestBody.create(mediaType, "-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"tekstas\"\r\n\r\n" + word + "\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"pateikti\"\r\n\r\nLM\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"tipas\"\r\n\r\nanotuoti\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"veiksmas\"\r\n\r\nRezultatas puslapyje\r\n-----011000010111000001101001--");
        Request request = new Request.Builder()
                .url("http://donelaitis.vdu.lt/main_helper.php?id=4&nr=7_2")
                .post(body)
                .addHeader("content-type", "multipart/form-data; boundary=---011000010111000001101001")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "566fa449-50b6-d239-3407-ee147ab7923b")
                .build();

        Response response;
        String responseString = "";
        try {
            response = client.newCall(request).execute();
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!responseString.isEmpty()){
            responseString = responseString.split("</form>")[1];
            responseString = responseString.replaceAll("&lt;", "<");
            responseString = responseString.replaceAll("&gt;", ">");
            responseString = responseString.replaceAll("&quot;", "\"");
            Document doc1 = Jsoup.parse(responseString);
            String type = doc1.getElementsByAttribute("lemma").attr("type");
            if(type.equals("neþinomas")){
                return false;
            }
            String kalbosDalis = type.split("\\.")[0];
            if(kalbosDalis.equals("tikr")){
                return false;
            }else if(kalbosDalis.equals("sutr")){
                return false;
            }else if(kalbosDalis.equals("akronim")){
                return false;
            }
        }

        return true;
    }

}
