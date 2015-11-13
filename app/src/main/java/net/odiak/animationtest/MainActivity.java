package net.odiak.animationtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/*
 * アクティビティ名をリストに表示して、各項目をタップするとそのアクティビティに遷移する。
 */
public class MainActivity extends AppCompatActivity {
    private Class[] mActivities = {
            FadeAnimationActivity.class,
            FlipAnimationActivity.class,
            FlipAnimation2Activity.class,
            AnimatedViewGroupActivity.class,
            AnimatedListViewActivity.class,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        final MyAdapter adapter = new MyAdapter(this);
        adapter.addAll(mActivities);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class klass = adapter.getItem(position);
                startActivity(new Intent(MainActivity.this, klass));
            }
        });
    }

    private static class MyAdapter extends ArrayAdapter<Class> {

        private LayoutInflater mLayoutInflater;

        public MyAdapter(Context context) {
            super(context, 0);
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            }

            Class klass = getItem(position);
            ((TextView) convertView).setText(klass.getSimpleName());

            return convertView;
        }
    }
}
