package me.martha_gao.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.martha_gao.parstagram.model.Post;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("parsta")
                .clientKey("123Student789!")
                .server("http://fbu-parstagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
