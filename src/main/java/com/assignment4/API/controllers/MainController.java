package com.assignment4.API.controllers;

import com.assignment4.API.Models.Quotes;
import com.assignment4.API.Models.QuotesRepo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    QuotesRepo quotesRepo;

    @RequestMapping("/")
    public ModelAndView doHome() {
        ModelAndView mv = new ModelAndView("index");
        String quoteId = "";
        String quoteMessage = "";
        String quoteAuthor = "";
        String quote = "";
        String apikey = "938e8ae83bmshfaf79b287324e89p1a0afbjsn905ead102b52";
        try {
            URL url = new URL("https://qvoca-bestquotes-v1.p.rapidapi.com/quote");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-rapidapi-host", "qvoca-bestquotes-v1.p.rapidapi.com");
            connection.setRequestProperty("x-rapidapi-key", apikey);
            connection.connect();
            BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                json.append(line);
            }
            JSONObject obj = new JSONObject(json.toString());
            quoteId = obj.getString("id");
            quoteMessage = obj.getString("message");
            quoteAuthor = obj.getString("author");

            Quotes quotes = new Quotes();
            quotes.setId(UUID.randomUUID().toString());
            quotes.setQuoteId(quoteId);
            quotes.setQuoteAuthor(quoteAuthor);
            quotes.setQuoteMessage(quoteMessage);
            quotesRepo.save(quotes);
            quote = quoteId + "  " + quoteMessage + " by " + quoteAuthor;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        mv.addObject("quote", quote);
        return mv;
    }

    @RequestMapping("/randomquotes")
    public ModelAndView randomquotes() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("quotesrandom", quotesRepo.findAll());
        return mv;
    }

    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("redirect:/randomquotes");
        quotesRepo.deleteById(id);
        return mv;
    }

}