/**
 * This class is the custom adapter for the Hole object. It maps the Hole data or values to tbe views defined in the item_list
 * layout file. An adapter is compulsory in recycler views and has two major functions i.e mapping data to fields in layout
 * and making the data suited fro the views. This is done using the helper methods defined in the Adapter and ViewHolder classes
 * below.*/
package com.bitland.golfscorecardreloaded;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.HoleViewHolder> {

    private Hole[] mHoles;

    /**The constructor enables us to create the adapter in the displaying activity (MainActivity)
     * and set its data.*/

    public HoleAdapter(Hole[] holes) {
        mHoles = holes;
    }

    /**Method called when a new ViewHolder is created.
     * These are re-used and are created as and when needed. */
    @Override
    public HoleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
        HoleViewHolder viewHolder = new HoleViewHolder(view);
        return viewHolder;
    }

    /**Method is the bridge between the adapter and the bindHole() method in the ViewHolder class.
     * @param holder is a ViewHolder object used to access the bindHole() method.
     * @param position is used to access an mHoles array object.*/
    @Override
    public void onBindViewHolder(HoleViewHolder holder, int position) {
        holder.bindHole(mHoles[position]);

    }

    //Method returns the number of items in the mHoles array.
    @Override
    public int getItemCount() {
        return mHoles.length;
    }

    public class HoleViewHolder extends RecyclerView.ViewHolder{

        //Declare member variables for each view defined in the item_list layout file
        public TextView mHoleLabel;
        public TextView mScoreLabel;
        public Button mAddButton;
        public Button mSubtractButton;

        /**Constructor to initialize the member variables declared above.
         * It is called when the view holder is created */
        public HoleViewHolder(View itemView) {
            super(itemView);

            mHoleLabel = (TextView)itemView.findViewById(R.id.holeLabel);
            mScoreLabel = (TextView)itemView.findViewById(R.id.scoreLabel);
            mAddButton = (Button) itemView.findViewById(R.id.addButton);
            mSubtractButton = (Button)itemView.findViewById(R.id.subtractButton);
        }

        /**Method to set or bind the views.
         * Inside the method, set the views.*/
        public void bindHole(final Hole hole){
            mHoleLabel.setText(hole.getLabel());
            mScoreLabel.setText(hole.getStrokeCount() + "");

            mSubtractButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int updatedStrokeCount = hole.getStrokeCount() - 1;
                    if(updatedStrokeCount < 0) updatedStrokeCount = 0;
                    hole.setStrokeCount(updatedStrokeCount);
                    mScoreLabel.setText(updatedStrokeCount + "");
                }
            });

            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int updatedStrokeCount = hole.getStrokeCount() + 1;
                    hole.setStrokeCount(updatedStrokeCount);
                    mScoreLabel.setText(updatedStrokeCount + "");
                }
            });

        }
    }
}
