import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";

    public static void main(String[] args) throws InterruptedException, IOException {
        int maxTokens = 100;
        // Set request parameters
        String prompt = "hey";
        //System.out.println(args[0]);
        //System.out.println(args[1]);
        if(args[0] != null){
            //System.out.println(args[0]);
            maxTokens = (int) Integer.parseInt(args[0]);
        }else{
            //System.out.println(args[0]);
            maxTokens = 100;
        }

        if(args[1] != null){
            //System.out.println(args[1]);
            prompt = args[1];
        }else{
            //System.out.println(args[1]);
            prompt = "Hello";
        }

        
        
        
        

        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);


        // Create the HTTP Client
        HttpClient client = HttpClient.newHttpClient();


        // Create the request object
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(API_ENDPOINT))
        .header("Content-Type", "application/json")
        .header("Authorization", String.format("Bearer %s", API_KEY))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
        .build();
        
        // Send the request and receive the response
        HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString()                                 
        );  

        //Proccess into Json Object
        JSONObject responseJson = new JSONObject(response.body()); 
        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");


        System.out.println(generatedText);

        //System.out.println(response.body().toString());

    


    }
}