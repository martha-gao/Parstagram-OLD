package me.martha_gao.parstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import me.martha_gao.parstagram.model.Post;

public class HomeActivity extends AppCompatActivity {

//    FrameLayout flContainer;
//    FragmentTransaction fragmentTransaction;
//
//    Fragment timelineFragment = new TimelineFragment;
//    Fragment cameraFragment = new CameraFragment;

//    private static final String imagePath = "/Desktop/Screen Shot 2018-07-09 at 10.37.10 AM";
//    // TODO - EDIT THIS TO NOT BE HARDCODED AND TO ASK USER FOR FILE INPUT
//    private EditText descriptionInput;
//    private Button createButton;
//    private Button refreshButton;
    private Button logoutButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        descriptionInput = findViewById(R.id.etDescription);
//        createButton = findViewById(R.id.btCreate);
//        refreshButton = findViewById(R.id.btRefresh);
        logoutButton = findViewById(R.id.btLogout);
//
//        createButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String description = descriptionInput.getText().toString();
//                final ParseUser user = ParseUser.getCurrentUser();
//
//                final File file = new File(imagePath);
//                final ParseFile parseFile = new ParseFile(file);
//
//                createPost(description, parseFile, user);
//            }
//        });
//
//        refreshButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadTopPosts();
//            }
//        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void createPost(String description, ParseFile imageFile, ParseUser user) {
        final Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setImage(imageFile);
        newPost.setUser(user);

        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("HomeActivity", "Create post success!");
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadTopPosts() {
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();


        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); ++i) {
                        Log.d("HomeActivity", "Post[" + "} = "
                                + objects.get(i).getDescription()
                                + "\nusername = " + objects.get(i).getUser().getUsername());
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
    }



}
