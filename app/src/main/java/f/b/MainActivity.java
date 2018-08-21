package f.b;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import m.b.utils.TimeUtils;

public class MainActivity extends AppCompatActivity {

    private TextView time,condition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        condition = findViewById(R.id.condition);
        getData();
    }
    public void selectTime(View view) {
        //startActivity(new Intent(MainActivity.this,TestActivity.class));

    }
/*//时间选择器
        TimePickerView pvTime = new TimePickerBuilder(MainActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(MainActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                time.setText(getTime(date));
            }
        }).build();
        pvTime.show();*/

    /*public void selectCondition(View view){
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(MainActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1)+":"
                        + options2Items.get(option2)+":"
                        + options3Items.get(options3);
                condition.setText(tx);
            }
        }).build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }*/


    private String getTime(Date date) {
        return TimeUtils.dateToString(date,"yyyy-MM-dd HH:mm:ss");
    }

    List<String> options1Items,options2Items,options3Items;
    private void getData(){
        options1Items = new ArrayList<>();
        options2Items = new ArrayList<>();
        options3Items = new ArrayList<>();

        for(int i=0;i<10;i++){
            options1Items.add("一号联动"+i);
            options2Items.add("二号联动"+i);
            options3Items.add("三号联动"+i);
        }


    }

}
