package com.example.flexboxlayoutactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // This is the text that will be rendered in the screen.
    //private String textArr[] = {"dev2qa.com", "is", "a very good", "android example website", "there are", "a lot of", "android, java examples"};
    private String str = "dev2qa.com is a very good android example website there are a lot of android, java examples";
    private  String textArr[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("dev2qa.com - FlexboxLayout Example.");

         textArr = str.split(" ");
        // Get the RecyclerView object.
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.flex_box_recycler_view);

        // Create the FlexboxLayoutMananger, only flexbox library version 0.3.0 or higher support.
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getApplicationContext());
        // Set flex direction.
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        // Set JustifyContent.
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(flexboxLayoutManager);

        // Set adapter object.
        ViewAdapter viewAdapter = new ViewAdapter(this.initViewItemDtoList());
        recyclerView.setAdapter(viewAdapter);
    }

    private List<ViewItemDTO> initViewItemDtoList()
    {
        List<ViewItemDTO> ret = new ArrayList<ViewItemDTO>();

        for(int i=0;i < this.textArr.length; i++)
        {
            ViewItemDTO itemDto = new ViewItemDTO();
            itemDto.setText(this.textArr[i]);

            ret.add(itemDto);
        }
        return ret;
    }
}
