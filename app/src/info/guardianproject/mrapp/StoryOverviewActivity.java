package info.guardianproject.mrapp;

import info.guardianproject.mrapp.model.Project;

import org.holoeverywhere.widget.Toast;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class StoryOverviewActivity extends BaseActivity {

	private Project mProject;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_overview);
			
		int pid = getIntent().getIntExtra("pid", -1); //project i
		if (pid < 0)
			return;
		mProject = Project.get(getApplicationContext(), pid);
		
	    initialize();
		setStoryInfo();
	}
	
	private void initialize() {
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	    Bundle tags = new Bundle();
	    tags.putStringArray("tags", mProject.getTagsAsArray());
	    
	    ProjectTagFragment fragPT = new ProjectTagFragment();
	    fragPT.setArguments(tags);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_tag_container, fragPT).commit();
	}
    	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getSupportMenuInflater().inflate(R.menu.activity_story_overview, menu);
	    return super.onCreateOptionsMenu(menu);   
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		
        switch (item.getItemId()) {
            case R.id.itemEditStory:
            	Intent intent = new Intent(this, StoryOverviewEditActivity.class);
            	intent.putExtra("pid", mProject.getId());
            	startActivity(intent);
            	
                break;
            case R.id.itemSendStory:
            	Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
            	break;
            case R.id.itemShareStory:
            	Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
            	break;
            case android.R.id.home:
            	onBackPressed();
            	break;             
        }
        
        return super.onOptionsItemSelected(item);
    }
	
	private void setStoryInfo() { 
    	
    	TextView tvStoryTitle = (TextView) findViewById(R.id.tv_story_title);
    	TextView tvStoryDesc = (TextView) findViewById(R.id.tv_story_desciption);
    	TextView tvStorySection = (TextView) findViewById(R.id.tv_story_section);
    	TextView tvStoryLocation = (TextView) findViewById(R.id.tv_story_location);
    	
    	tvStoryTitle.setText(mProject.getTitle());
    	tvStoryDesc.setText(mProject.getDescription());
    	tvStorySection.setText(mProject.getSection());
    	tvStoryLocation.setText(mProject.getLocation());
    }

}